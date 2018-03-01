package com.aeromexico.pab.backend.entity.test;

import com.aeromexico.pab.entity.KitMaster;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeromexico.pab.entity.Matriz;
import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.entity.TipoEquipoAvion;
import java.sql.Timestamp;
import java.util.Random;
import org.junit.Assert;
import org.junit.Ignore;
/**
 *
 * @author alfredo.estrada
 */
@Ignore
public class TestMatriz {
	private static Logger logger = LoggerFactory.getLogger(TestMatriz.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
    private static Random rand;
    
	public TestMatriz() {
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
	public void testCRUDMaterialKitMaster() {
		entityManager.getTransaction().begin();
		logger.info("---------------------------[testMaterialKitMaster]>>> T1");
        
        long t1=System.currentTimeMillis();
        
        List<Matriz> matrizList1= entityManager.createNamedQuery("Matriz.findAll").getResultList();
		logger.info("Matriz before:-->");
		for(Matriz m: matrizList1){
			logger.info("\t->Matriz:"+m);
		}
		logger.info("Matriz before:<--");
        int totalRecordsBefore=matrizList1.size();
        Matriz m = new Matriz();
        final KitMaster km = new KitMaster();
        km.setCveKitMaster("JAR0302");
        m.setKitMaster(km);
        final Producto p = new Producto();
        p.setIdProducto(1);
        m.setProducto(p);
        final TipoEquipoAvion tipoEquipoAvion = new TipoEquipoAvion();
        tipoEquipoAvion.setIdTipoEquipoAvion(2);
        m.setTipoEquipoAvion(tipoEquipoAvion);
        m.setCantidadCj(1);
        m.setCantidadCy(2);
        m.setStatus((short)1);
        m.setUsuarioCreo("junit");
        m.setFechaCreo(new Timestamp(t1));
        m.setUsuarioModifico("junit");
        m.setFechaModifico(new Timestamp(t1));        
        
		entityManager.persist(m);
        entityManager.flush();
        
        logger.info("Matriz: idMatriz="+m.getIdMatriz());
        
        entityManager.getTransaction().commit();
		logger.info("---------------------------[testMaterialKitMaster]<<< T1");
        
        entityManager.getTransaction().begin();
		logger.info("---------------------------[testMaterialKitMaster]>>> T2");
        
		List<Matriz> matrizList2= entityManager.createNamedQuery("Matriz.findAll").getResultList();
		logger.info("Matriz after:-->");
		for(Matriz m2: matrizList2){
			logger.info("\t->Matriz:"+m2);
		}
		logger.info("Matriz after:<--");
        Assert.assertEquals(totalRecordsBefore + 1, matrizList2.size());
        
        entityManager.getTransaction().commit();
        
		logger.info("---------------------------[testMaterialKitMaster]<<< T2");        
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
