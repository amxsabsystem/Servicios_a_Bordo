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
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeromexico.pab.entity.Perfil;
/**
 *
 * @author alfredo.estrada
 */
@Ignore
public class TestPerfil {
	private static Logger logger = LoggerFactory.getLogger(TestPerfil.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
	
	public TestPerfil() {
		logger.info("TestLoadPersistence() constructor");
	}
	
	@BeforeClass
	public static void setUpClass() {
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
	public void testCRUDPerfil() {
		entityManager.getTransaction().begin();
		logger.info("---------------------------[testPerfil]>>>");
		List<Perfil> perfilList1= entityManager.createNamedQuery("Perfil.findAll").getResultList();
		logger.info("Perfil before:-->");
		for(Perfil p: perfilList1){
			logger.info("\t->Perfil:"+p);
		}
		logger.info("Perfil before:<--");
		int totalRecordsBefore=perfilList1.size();
		Perfil perfilX=new Perfil();
		
		long t1=System.currentTimeMillis();
		perfilX.setPerfil("PERF_"+ ((int)(t1%1000)) );
		perfilX.setDescripcionEs("DESC_ES_"+perfilX.getPerfil());
        perfilX.setDescripcionEn("DESC_EN_"+perfilX.getPerfil());
		
		entityManager.persist(perfilX);
		
		List<Perfil> perfilList2= entityManager.createNamedQuery("Perfil.findAll").getResultList();
		logger.info("Perfil after:-->");
		for(Perfil p: perfilList2){
			logger.info("\t->Perfil:"+p);
		}
		logger.info("Perfil after:<--");
		Assert.assertEquals(totalRecordsBefore + 1, perfilList2.size());
                
		entityManager.getTransaction().commit();
		logger.info("---------------------------[testPerfil]<<<");
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
