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

import com.aeromexico.pab.entity.Ciudad;
/**
 *
 * @author alfredo.estrada
 */
@Ignore
public class TestCiudad {
	private static Logger logger = LoggerFactory.getLogger(TestCiudad.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
	
	public TestCiudad() {
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
	public void testLazyLoadCiudad() {
        logger.info("---------------------------[testCiudad]>>> T1");
		entityManager.getTransaction().begin();		
        
        List<Ciudad> ciudadList2= entityManager.createQuery("select x from Ciudad x order by x.pais.region.nombre,x.pais.nombre,x.nombre").getResultList();
        
        List<Object[]> ciudadList3= entityManager.createQuery("select x.pais.region.nombre,x.pais.nombre,x from Ciudad x").getResultList();
        
        logger.info("---------------------------[testCiudad]<<< T1");
		entityManager.getTransaction().commit();
        entityManager.close();
        
        logger.info("Ciudad before:--> LazyList Eagered ?");
		for(Ciudad c: ciudadList2){
//            c.getPais().getRegion().getNombre();
//            c.getPais().getNombre();
//            c.getNombre();
            logger.info("\t->Ciudad "+c.getNombre());
		}
		logger.info("Ciudad before:<-- LazyList");
        
        logger.info("JQL Query result:-->");
        for(Object[] objReg:ciudadList3){
            String pais_region_nombre=(String)objReg[0];
            String pais_nombre       =(String)objReg[1];
            Ciudad ciudad            =(Ciudad)objReg[2];
            logger.info("\t->Object :"+pais_region_nombre+", "+pais_nombre+", Ciudad:"+ciudad.getNombre());
        }
        logger.info("JQL Query result:<--");
        
		logger.info("---------------------------[testCiudad]<<<");        
	}
	
	@After
	public void tearDownEveryTest() {
				
	}
	
	@AfterClass
	public static void tearDownClass() {
        logger.info("<<<====================================");		
		if(entityManager != null){
            if(entityManager.isOpen()){
                entityManager.close();
                logger.info("entityManager closed");
            }
		}
		if(entityManagerFactory !=null){
            if(entityManagerFactory.isOpen()){
                entityManagerFactory.close();
                logger.info("entityManagerFatory closed");
            }
		}
	}

}
