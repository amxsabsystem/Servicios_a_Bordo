package com.aeromexico.pab.amx.pab.reportes;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Alfredo Estrada
 */
public class GeneradorReportes {
    private final static Logger logger = Logger.getLogger(GeneradorReportes.class.getName());
    //private static JFrame f=null;
    public static byte[] generarReporteTSU(HashMap<String,Object> parameters,Collection<Map<String,?>> colData,boolean dynamic) {
        byte[] reportContent=null;
        
		String reportPath;
		String compiledReportPath;
		
		reportPath			= "/resportsDesigns/AMX_TSU_design2.jrxml";
		compiledReportPath	= "/resportsDesigns/AMX_TSU_design2.jasper";
			
		InputStream inputStream = null;
		JRDataSource dataSource = null;
		logger.log(Level.INFO,"Ok, JRDataSource created");
            
		try{
//            String strImg="/9j/4AAQSkZJRgABAQEASABIAAD//gATQ3JlYXRlZCB3aXRoIEdJTVD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wgARCAAFAAUDAREAAhEBAxEB/8QAFAABAAAAAAAAAAAAAAAAAAAAB//EABUBAQEAAAAAAAAAAAAAAAAAAAAC/9oADAMBAAIQAxAAAAEjh//EABcQAAMBAAAAAAAAAAAAAAAAAAEDBQT/2gAIAQEAAQUCTWySj//EAB0RAAIBBAMAAAAAAAAAAAAAAAECIQMEBhEFMfD/2gAIAQMBAT8ByTGroWHHmleFCUkqui0JLaafHssT/8QAHhEAAQMEAwAAAAAAAAAAAAAAEQISQQABAyEEBVH/2gAIAQIBAT8B67mtSrDlS5osdRYeSDO6/8QAHhAAAQMEAwAAAAAAAAAAAAAAAgEDBAAFMVETImH/2gAIAQEABj8CJ961xpgSu4NqIpxeYXaaxX//xAAZEAADAAMAAAAAAAAAAAAAAAABESEAMUH/2gAIAQEAAT8hMrGTe0NoCDqmTn//2gAMAwEAAgADAAAAEN//xAAZEQEAAgMAAAAAAAAAAAAAAAABABEhMVH/2gAIAQMBAT8Qe6BYZuBUGOXdJj//xAAYEQEBAAMAAAAAAAAAAAAAAAABESExQf/aAAgBAgEBPxCU7zAFaXrQqqlqqv8A/8QAGBABAQADAAAAAAAAAAAAAAAAAREAIUH/2gAIAQEAAT8QMSoUhaErpR0Kz//Z";
//            // imagen_5x5.jpg
//            byte[] imageBytes = Base64.getDecoder().decode(strImg);
//            final ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
//            Image img = ImageIO.read(bais);
            
//            f=new JFrame("img");
//            f.getContentPane().add(new JLabel(new ImageIcon(img)));
//            f.pack();
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    f.setVisible(true);
//                }
//            });            
            parameters.put("urlImagenTSU"   ,new URL("http://localhost:8080/AMX-PAB-Web/_media/922967270.png"));
//            parameters.put("bytesImagenTsu", bais);
//            logger.log(Level.INFO,"imageTSU="+img);
//            parameters.put("imageTSU"      , img );
//            logger.log(Level.INFO,"imageBytes.length="+imageBytes.length);
            
            logger.log(Level.INFO,"...ok, now preparing Data");
            
            dataSource = new JRMapCollectionDataSource(colData);
						
			JasperReport jasperReport = null;			
            
            logger.log(Level.INFO,"Ok, ...before JR true reporting, dynamically?="+dynamic);
            if(dynamic){
                inputStream = GeneradorReportes.class.getResourceAsStream(reportPath);
                logger.log(Level.INFO,"Ok, Loading .jrxml inputStream");
                JasperDesign jasperDesign = JRXmlLoader.load(inputStream);			
                logger.log(Level.INFO,"Ok, Now Compiling JasperReport");
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
            } else{			
                logger.log(Level.INFO,"Ok, Loading .jasper inputStream");
                InputStream compiledReportStream = GeneradorReportes.class.getResourceAsStream(compiledReportPath);
                logger.log(Level.INFO,"Ok, Loading compiled .jasper to JR !");
                jasperReport = (JasperReport) JRLoader.loadObject(compiledReportStream);			
            }
            
            logger.log(Level.INFO,"Ok, JR Laoded, Now filling");
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
