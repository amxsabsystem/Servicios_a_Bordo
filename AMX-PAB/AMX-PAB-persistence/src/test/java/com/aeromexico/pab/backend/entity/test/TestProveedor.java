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

import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.Region;
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
public class TestProveedor {
	private static Logger logger = LoggerFactory.getLogger(TestProveedor.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
    private static Random rand;
    
	public TestProveedor() {
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
	public void testCRUDProveedor() {
		entityManager.getTransaction().begin();
		logger.info("---------------------------[testProveedor]>>> T1");
		List<Proveedor> perfilList1= entityManager.createNamedQuery("Proveedor.findAll").getResultList();
		logger.info("Proveedor before:-->");
		for(Proveedor p: perfilList1){
			logger.info("\t->Proveedor:"+p);
		}
		logger.info("Proveedor before:<--");
		int totalRecordsBefore=perfilList1.size();
        
        List<Region> regionList1= entityManager.createNamedQuery("Region.findAll").getResultList();
		logger.info("Region before:-->");
        List<Region> regionToAssign=new ArrayList<Region>();
        
		for(Region r: regionList1){
            if(rand.nextDouble()>=0.5){
                logger.info("\t->(+)Region:"+r);
                regionToAssign.add(r);
            } else {
                logger.info("\t->   Region:"+r);
            }
		}
		logger.info("Region before:<--");
        if(regionToAssign.size()==0 && regionList1.size()>0){
            int indexToAddForced=rand.nextInt(regionList1.size());
            final Region rxa = regionList1.get(indexToAddForced);
            regionToAssign.add(rxa);
            logger.info("\t->(+)Region["+indexToAddForced+"]:"+rxa);
        }
        
		Proveedor provvedorX=new Proveedor();
		
		long t1=System.currentTimeMillis();
		provvedorX.setClaveProveedor("P"+ ((int)(t1%1000)) );
		provvedorX.setNombre("PROV_"+provvedorX.getClaveProveedor());
        provvedorX.setRazonSocial("RS_PROV_"+provvedorX.getClaveProveedor());
        
        provvedorX.setStatus((short)1);
        provvedorX.setUsuarioCreo("junit");
        provvedorX.setFechaCreo(new Timestamp(System.currentTimeMillis()));
        provvedorX.setUsuarioModifico("junit");
        provvedorX.setFechaModifico(new Timestamp(System.currentTimeMillis()));
		
		entityManager.persist(provvedorX);
        entityManager.flush();
        
        provvedorX.setRegionList(regionToAssign);
        
        entityManager.merge(provvedorX);
        entityManager.flush();
        entityManager.getTransaction().commit();
		logger.info("---------------------------[testProveedor]<<< T1");
        
        final Query nq1 = entityManager.createNativeQuery("SELECT ID_REGION,CLAVE_PROVEEDOR FROM PROVEEDOR_REGION");
        final List<Object[]> resultNQList = nq1.getResultList();
        logger.info("\t==>>REGION_PROVEEDOR {");
        int countRP=0;
        for(Object[] objNQ:resultNQList){            
            logger.info("\t\t==>>REGION: ID_REGION="+objNQ[0]+",CLAVE_PROVEEDOR="+objNQ[1]);
            countRP++;
        }
        logger.info("\t}countRP="+countRP);
        
        Assert.assertEquals(countRP, regionToAssign.size());
        
        entityManager.getTransaction().begin();
		logger.info("---------------------------[testProveedor]>>> T2");
        
		List<Proveedor> proveedorList2= entityManager.createNamedQuery("Proveedor.findAll").getResultList();
		logger.info("Proveedor after:-->");
		for(Proveedor p: proveedorList2){
			logger.info("\t->Proveedor:"+p);
            final List<Region> regionList = p.getRegionList();
            for(Region rx: regionList){
                logger.info("\t\t->Region:"+rx);
            }
		}
		logger.info("Proveedor after:<--");
        entityManager.getTransaction().commit();
		logger.info("---------------------------[testProveedor]<<< T2");
        
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
