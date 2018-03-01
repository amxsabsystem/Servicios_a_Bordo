package com.aeromexico.pab.backend.remote.ejb.reportes;

import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.TsuFacadeRemote;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.aeromexico.pab.entity.Tsu;
import com.aeromexico.pab.backend.reportes.GeneradorReportesPDFFacadeRemote;
import com.aeromexico.pab.entity.KitTsu;
import com.aeromexico.pab.entity.MaterialTsu;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Alfredo Estrada
 */
@Stateless(
    name ="generadorReportesPDF_RSB",
    mappedName = "GeneradorReportesPDF_RSB",
    description = "generadorReportesPDF-Stateles-Session EJB-3.1"
)
public class GeneradorReportesPDF_RSB implements GeneradorReportesPDFFacadeRemote{
    private final static Logger logger = Logger.getLogger(GeneradorReportesPDFFacadeRemote.class.getName());
       
    @EJB(lookup = "java:module/tsu_RSB")
    TsuFacadeRemote tsuFacadeRemote;    

    @EJB(lookup = "java:module/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;
    
    @Resource 
    SessionContext ctx;
    
	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;
    
    @PostConstruct
    @TransactionAttribute(NOT_SUPPORTED)
    public void init(){
        logger.log(Level.INFO,"init: ");
    }
    
    @PreDestroy
    @TransactionAttribute(NOT_SUPPORTED)
    public void preDestroy(){        
        logger.log(Level.INFO,"predestroy: ");
    }
    
    @TransactionAttribute(REQUIRED)
    @Override
    public byte[] generarReporteTSU(String cveTsu,boolean dynamic,String urlContextRequest) {
        byte[] reportContent=null;
        
		String reportPath;
		String compiledReportPath;
		
		reportPath			= "/resportsDesigns/AMX_TSU_design2.jrxml";
		compiledReportPath	= "/resportsDesigns/AMX_TSU_design2.jasper";
			
		InputStream inputStream = null;
		Tsu tsu= null;
		JRDataSource dataSource = null;
		logger.log(Level.INFO,"Ok, JRDataSource created");
            
		try{
            Tsu tsuSearch= new Tsu();
            tsuSearch.setCveTsu(cveTsu);
			
			List<Tsu> tsuList =tsuFacadeRemote.findAllLike(tsuSearch);
            logger.log(Level.INFO,"Searching TSU list by cveTsu="+cveTsu);
            for(Tsu tsuIter: tsuList){
                logger.log(Level.INFO,"\t->found:tsu:"+tsuIter.getCveTsu());
                tsu = tsuFacadeRemote.findByPK_EAGER(tsuIter.getIdTsu());
                logger.log(Level.INFO,"\t->found:now get EAGER tsu:"+tsuIter.toString());
            }
            logger.log(Level.INFO,"tsu url="+tsu.getMultimedio().getUrl());
            
            
            logger.log(Level.INFO,"...ok, now preparing Data");
            final List<KitTsu> kitTsuHasTsuList = tsu.getKitTsuHasTsuList();
            final List<MaterialTsu> materialTsuHasTsuList = tsu.getMaterialTsuHasTsuList();
			
            Map parameters = new HashMap();
            
			parameters.put("cveTsu"      ,tsu.getCveTsu());
            parameters.put("nombreClase" ,tsu.getClase().getNombreEsp());
            parameters.put("notas"       ,tsu.getNotas());            
            String urlImagenTSU = null;
            //urlImagenTSU = "http://srv1-gonet.dynu.net:8080/AMX-PAB-Web/javax.faces.resource/images/logo-amx.png.xhtml?ln=ultima-layout"; 
            urlImagenTSU = urlContextRequest+ tsu.getMultimedio().getUrl().replace("media", "_media");
            logger.log(Level.INFO,"trying to locate from urlImagenTSU="+urlImagenTSU);
            parameters.put("urlImagenTSU", new URL(urlImagenTSU));
            try{
                byte[] imageBytes = storageServiceFacadeRemote.openMedia(tsu.getMultimedio().getUrl());
                if(imageBytes != null){
                    logger.log(Level.INFO,"imageBytes.length="+imageBytes.length);            
                    final ByteArrayInputStream baosImg = new ByteArrayInputStream(imageBytes);
                    final Image img = ImageIO.read(baosImg);
                    
                    parameters.put("imageTSU",img);
                    logger.log(Level.INFO,"put param(imageTSU,"+img+" class:"+img.getClass()+")");
                    parameters.put("bytesImagenTsu", baosImg);
                    logger.log(Level.INFO,"put param(bytesImagenTsu,"+baosImg+" class:"+baosImg.getClass()+")");
                } else {
                    logger.log(Level.INFO,"storageServiceFacadeRemote, Not found !");            
                }
            }catch(IOException ioe){
                logger.log(Level.WARNING,"Failed to open from storageServiceFacadeRemote: ",ioe);
            }            

			Collection<Map<String,?>> colData = new ArrayList<Map<String,?>>();            
            
            for(KitTsu kitTsu: kitTsuHasTsuList){
                Map<String,Object> regMap = new HashMap<String, Object>();
				
				regMap.put("equipo"  , kitTsu.getKitMaster().getNombreEs());
				regMap.put("noParte" , kitTsu.getKitMaster().getCveKitMaster());
				regMap.put("cantidad", String.valueOf(kitTsu.getCantidad()));
				
				colData.add(regMap);
            }
            
            for(MaterialTsu matTsu: materialTsuHasTsuList){
				Map<String,Object> regMap = new HashMap<String, Object>();
				
				regMap.put("equipo"  , matTsu.getMaterial().getDescripcionEs());
				regMap.put("noParte" , matTsu.getMaterial().getNumeroParte());
				regMap.put("cantidad", String.valueOf(matTsu.getCantidad()));
				
				colData.add(regMap);
			}
			dataSource = new JRMapCollectionDataSource(colData);
						
			JasperReport jasperReport = null;
			
            
            logger.log(Level.INFO,"Ok, ...before JR true reporting, dynamically?="+dynamic);
            if(dynamic){
                inputStream = getClass().getResourceAsStream(reportPath);
                logger.log(Level.INFO,"Ok, Loading .jrxml inputStream");
                JasperDesign jasperDesign = JRXmlLoader.load(inputStream);			
                logger.log(Level.INFO,"Ok, Now Compiling JasperReport");
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
            } else {
                logger.log(Level.INFO,"Ok, Loading .jasper inputStream");
                InputStream compiledReportStream = getClass().getResourceAsStream(compiledReportPath);
                logger.log(Level.INFO,"Ok, got the compiled .jasper to JR !");
                jasperReport = (JasperReport) JRLoader.loadObject(compiledReportStream);			
            }
            
            logger.log(Level.INFO,"Ok, JR Laoded, Now filling !");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			logger.log(Level.INFO,"Ok, Filled, Now exporting to PDF");
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
			
			logger.log(Level.INFO,"...report completed, to byte[]");
			
			reportContent = baos.toByteArray();
		}catch(Exception ex){
			ex.printStackTrace(System.err);
		}        
        return reportContent;
    }
    
}
