package com.aeromexico.pab.backend.entity.test;

import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.Material;
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

import com.aeromexico.pab.entity.MaterialKitMaster;
import com.aeromexico.pab.entity.MaterialKitMasterPK;
import com.aeromexico.pab.entity.Parametro;
import java.sql.Timestamp;
import java.util.Random;
import org.junit.Assert;
import org.junit.Ignore;
/**
 *
 * @author alfredo.estrada
 */
@Ignore
public class TestMaterialKitMaster {
	private static Logger logger = LoggerFactory.getLogger(TestMaterialKitMaster.class.getName());
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager        entityManager;
    private static Random rand;
    
	public TestMaterialKitMaster() {
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
        
        List<KitMaster> kitMasterList1= entityManager.createNamedQuery("KitMaster.findAll").getResultList();
		logger.info("KitMaster before:-->");
		for(KitMaster km: kitMasterList1){
			logger.info("\t->KitMaster:"+km);
		}
		logger.info("KitMaster before:<--");
        
        
        Parametro p_um = new Parametro();
        p_um.setIdParametro(24); //pza
        
        Parametro p_intNac = new Parametro();
        p_intNac.setIdParametro(32); // NE
        
        Parametro p_intInt = new Parametro();
        p_intInt.setIdParametro(36); // LE
        
        Parametro p_tipoKit = new Parametro();
        p_tipoKit.setIdParametro(60); // A Granel
        
        KitMaster kitMaster = new KitMaster();
        final String kitToTest = "JU_"+String.valueOf(System.currentTimeMillis()/1000/60);        
        kitMaster.setCveKitMaster(kitToTest);
        
        kitMaster.setIdUnidadMedida(p_um);
        kitMaster.setIdInstruccionesNacionales(p_intNac);        
        kitMaster.setIdInstruccionesInternac(p_intInt);
        kitMaster.setIdTipoKit(p_tipoKit);
        
        kitMaster.setNombreEs("N_ES_"+kitMaster.getCveKitMaster());
        kitMaster.setNombreEn("N_EN_"+kitMaster.getCveKitMaster());
        kitMaster.setUrlMultimedia("http://URL?CVE="+kitMaster.getCveKitMaster());
        kitMaster.setMimeType("image/jpeg");
        kitMaster.setContenedor("CONT_JUNIT_X");
        
        kitMaster.setStatus((short)1);
        kitMaster.setUsuarioCreo("junit");
        kitMaster.setFechaCreo(new Timestamp(t1));
        kitMaster.setUsuarioModifico("junit");
        kitMaster.setFechaModifico(new Timestamp(t1));
        
        entityManager.persist(kitMaster);
        entityManager.flush();
        
		List<MaterialKitMaster> materialMasterList1= entityManager.createNamedQuery("MaterialKitMaster.findAll").getResultList();
		logger.info("MaterialKitMaster before:-->");
		for(MaterialKitMaster mm: materialMasterList1){
			logger.info("\t->MaterialKitMaster:"+mm);
		}
		logger.info("MaterialKitMaster before:<--");
        logger.info("MaterialKitMaster now ?");
		int totalRecordsBefore=materialMasterList1.size();
        
        List<Material> materialList1= entityManager.createNamedQuery("Material.findAll").getResultList();
        Material materialToAssign=null;		
		for(Material m: materialList1){
            if(materialToAssign==null && rand.nextBoolean()){
                logger.info("\t->+Material:"+m);
                materialToAssign = m;
                break;
            } else{
                logger.info("\t-> Material:"+m);
            }
		}		
        if(materialToAssign==null && materialList1.size()>0){
            materialToAssign = materialList1.get(rand.nextInt(materialList1.size()));
            logger.info("\t->+Material:"+materialToAssign);
        }
        
        List<KitMaster> kitMasterList2= entityManager.createNamedQuery("KitMaster.findAll").getResultList();
        KitMaster kitMasterToAssign=null;		
        logger.info("MaterialKitMaster after:-->");
		for(KitMaster kmm: kitMasterList2){
            //if(kitMasterToAssign==null && rand.nextBoolean()){
            if(kitMasterToAssign==null && kmm.getCveKitMaster().endsWith(kitToTest)){
                logger.info("\t->+KitMaster>>["+kitToTest+"]:"+kmm);
                kitMasterToAssign = kmm;
                break;
            } else{
                logger.info("\t-> KitMaster:"+kmm);
            }
		}
        logger.info("MaterialKitMaster after:<--");
        if(kitMasterToAssign==null && kitMasterList2.size()>0){
            kitMasterToAssign = kitMasterList2.get(rand.nextInt(kitMasterList2.size()));
            logger.info("\t->+KitMaster:"+kitMasterToAssign);
        }
        
        logger.info("--> Filling MaterialKitMaster");
		MaterialKitMaster materialKitMasterX=new MaterialKitMaster();		
        
		materialKitMasterX.setMaterial(materialToAssign);
        materialKitMasterX.setKitMaster(kitMasterToAssign);
        
        MaterialKitMasterPK mmPK = new MaterialKitMasterPK();
        mmPK.setNumeroParte (materialToAssign.getNumeroParte());        
        mmPK.setCveKitMaster(kitMasterToAssign.getCveKitMaster());
        logger.info("--> Filling MaterialKitMaster: MaterialKitMasterPK: MAtrial="+materialToAssign.getNumeroParte()+", Kit="+kitMasterToAssign.getCveKitMaster());
        
        materialKitMasterX.setMaterialKitMasterPK(mmPK);
        
		materialKitMasterX.setCantidad(rand.nextInt(100));        
        //materialMasterX.setPeso((float)(rand.nextDouble()*200));
        
        
        materialKitMasterX.setIdUnidadMedida(p_um);
                
        materialKitMasterX.setReciclable((short)1);        
        materialKitMasterX.setObservaciones("OBSERVACIONES_"+t1);
        
        materialKitMasterX.setIdInstruccionesNacionales(p_intNac);        
        materialKitMasterX.setIdInstruccionesInternac(p_tipoKit);
        
        materialKitMasterX.setStatus((short)1);
        materialKitMasterX.setUsuarioCreo("junit");
        materialKitMasterX.setFechaCreo(new Timestamp(t1));
        materialKitMasterX.setUsuarioModifico("junit");
        materialKitMasterX.setFechaModifico(new Timestamp(t1));
		
        logger.info("--> before to persist MaterialKitMaster:"+materialKitMasterX.toLargeString());
        
		entityManager.persist(materialKitMasterX);
        entityManager.flush();
        
        entityManager.getTransaction().commit();
		logger.info("---------------------------[testMaterialKitMaster]<<< T1");
        
        entityManager.getTransaction().begin();
		logger.info("---------------------------[testMaterialKitMaster]>>> T2");
        
		List<MaterialKitMaster> materialMasterList2= entityManager.createNamedQuery("MaterialKitMaster.findAll").getResultList();
		logger.info("MaterialKitMaster after:-->");
		for(MaterialKitMaster m: materialMasterList2){
			logger.info("\t->MaterialKitMaster:"+m.toLargeString());
		}
		logger.info("MaterialKitMaster after:<--");
        
        Assert.assertEquals(totalRecordsBefore + 1, materialMasterList2.size());
        
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
