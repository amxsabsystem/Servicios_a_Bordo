package com.aeromexico.pab.backend.entity.test;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.entity.Grafico;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import javax.persistence.Query;
import org.junit.Ignore;
/**
 *
 * @author alfredo.estrada
 */
@Ignore
public class TestProducto {
	private static Logger logger = LoggerFactory.getLogger(TestProducto.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
    private static Random rand;
	
	public TestProducto() {
		logger.info("TestLoadPersistence() constructor");
	}
	
	@BeforeClass
	public static void setUpClass() {
        rand=new Random(System.currentTimeMillis());
		//System.setProperty("derby.stream.error.method","com.gonet.papadoc.persistence.test.DerbySlf4jBridge.bridge");
		System.setProperty("org.jboss.logging.provider", "slf4j");
		System.setProperty("derby.system.home", System.getProperty("user.home")+"/DERBY_DATABASES");
        
        logger.info("loading persistence.xml ");
		entityManagerFactory = Persistence.createEntityManagerFactory("AMX_PAB_PU");
		logger.info("ok loaded persistence.xml ");
		logger.info("entityManager = "+entityManagerFactory);
		entityManager = entityManagerFactory.createEntityManager();
		logger.info("entityManager = "+entityManager);
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		logger.info("====================================>>>");
		
		logger.info("JPA Entities: ");		
		for(EntityType et:entities){
			logger.info("\tEntity:"+et.getName());
		}
		
		logger.info("Total Entites List size:"+entities.size());
	}
	
	@Before
	public void setUpEveryTest() {
		
	}
		
	@Test
	public void testCRUDProducto() {
		entityManager.getTransaction().begin();
		logger.info("---------------------------[testProducto]>>> T1");
		List<Producto> productoList1= entityManager.createNamedQuery("Producto.findAll").getResultList();
		logger.info("Producto before:-->");
		for(Producto p: productoList1){
			logger.info("\t->Producto:"+p);
		}
		logger.info("Producto before:<--");
		int totalRecordsBefore=productoList1.size();
        
        
        List<Grafico> graficoList1= entityManager.createNamedQuery("Grafico.findAll").getResultList();
		logger.info("Grafico before:-->");
        List<Grafico> graficoToAssign=new ArrayList<Grafico>();
        
		for(Grafico r: graficoList1){
            if(rand.nextDouble()>=0.5){
                logger.info("\t->(+)Grafico:"+r);
                graficoToAssign.add(r);
            } else {
                logger.info("\t->   Grafico:"+r);
            }
		}
		logger.info("Grafico before:<--");
        if(graficoToAssign.size()==0 && graficoList1.size()>0){
            int indexToAddForced=rand.nextInt(graficoList1.size());
            final Grafico rxa = graficoList1.get(indexToAddForced);
            graficoToAssign.add(rxa);
            logger.info("\t->(+)Grafico["+indexToAddForced+"]:"+rxa);
        }
		Producto productoX=new Producto();
		
		long t1=System.currentTimeMillis();		
		productoX.setNombre("PROD_"+rand.nextInt(1000));
        
        productoX.setStatus((short)1);
        productoX.setUsuarioCreo("junit");
        productoX.setFechaCreo(new Timestamp(System.currentTimeMillis()));
        productoX.setUsuarioModifico("junit");
        productoX.setFechaModifico(new Timestamp(System.currentTimeMillis()));
		
		entityManager.persist(productoX);
        entityManager.flush();
        
        //productoX.setGraficoList(graficoToAssign);
        
        entityManager.merge(productoX);
        entityManager.flush();
        entityManager.getTransaction().commit();
		logger.info("---------------------------[testProducto]<<< T1");
        
        final Query nq1 = entityManager.createNativeQuery("SELECT ID_GRAFICO,ID_PRODUCTO FROM GRAFICO_PRODUCTO");
        final List<Object[]> resultNQList = nq1.getResultList();
        logger.info("\t==>>GRAFICO_PRODUCTO {");
        int countRP=0;
        for(Object[] objNQ:resultNQList){            
            logger.info("\t\t==>>REGION: ID_GRAFICO="+objNQ[0]+",ID_PRODUCTO="+objNQ[1]);
            countRP++;
        }
        logger.info("\t}countRP="+countRP);
        // Can't manage M-2-M slave Insert
        // Assert.assertEquals(countRP, graficoToAssign.size());
        
        entityManager.getTransaction().begin();
		logger.info("---------------------------[testProducto]>>> T2");
        
		List<Producto> proveedorList2= entityManager.createNamedQuery("Producto.findAll").getResultList();
		logger.info("Producto after:-->");
		for(Producto p: proveedorList2){
			logger.info("\t->Producto:"+p);
            //final List<Grafico> graficoList = p.getGraficoList();
            //for(Grafico rx: graficoList){
            //    logger.info("\t\t->Grafico:"+rx);
            //}
		}
		logger.info("Producto after:<--");
        entityManager.getTransaction().commit();
		logger.info("---------------------------[testProducto]<<< T2");
        
		Assert.assertEquals(totalRecordsBefore + 1, proveedorList2.size());
                
		
	}
	
	@After
	public void tearDownEveryTest() {
				
	}
	
	@AfterClass
	public static void tearDownClass() {
        logger.info("<<<====================================");		
		if(entityManager != null){
			entityManager.close();
			logger.info("entityManager closed");
		}
		if(entityManagerFactory !=null){
			entityManagerFactory.close();
			logger.info("entityManagerFatory closed");
		}
	}

}
