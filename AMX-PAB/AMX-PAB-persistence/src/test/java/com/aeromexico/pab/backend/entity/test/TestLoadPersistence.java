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
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aeromexico.pab.entity.Perfil;
import com.aeromexico.pab.entity.Usuario;

/**
 *
 * @author alfredo.estrada
 */
@Ignore
public class TestLoadPersistence {
	private static Logger logger = LoggerFactory.getLogger(TestLoadPersistence.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
	    
	public TestLoadPersistence() {
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
	public void testListInitialData() {
		logger.info("---------------------------[testListInitialData]>>>");
		entityManager.getTransaction().begin();
		List<Perfil> perfilList1= entityManager.createNamedQuery("Perfil.findAll").getResultList();
		logger.info("Perfil before:-->");
		for(Perfil p: perfilList1){
			logger.info("\t->Perfil:"+p);
		}
		logger.info("Perfil before:<--");
		List<Usuario> usuarioList1= entityManager.createNamedQuery("Usuario.findAll").getResultList();
		logger.info("Usuario before:-->");
		for(Usuario u: usuarioList1){
			logger.info("\t->Usuario:"+u);
		}
		logger.info("Usuario before:<--");
		entityManager.getTransaction().commit();
		logger.info("---------------------------[testListInitialData]<<<");
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
