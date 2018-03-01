package com.aeromexico.pab.web.servlet;

import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.TsuFacadeRemote;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aeromexico.pab.backend.reportes.GeneradorReportesPDFFacadeRemote;
import com.aeromexico.pab.entity.KitTsu;
import com.aeromexico.pab.entity.MaterialTsu;
import com.aeromexico.pab.entity.Tsu;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
@WebServlet(name = "GeneradorReportesAdapter", urlPatterns = {"/reportes/*"})
public class GeneradorReportesAdapter extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(StorageServiceDispatcher.class.getName());
    
    public static final String TSU_REPORTES_PATH = "/tsu/";    

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/generadorReportesPDF_RSB")
    GeneradorReportesPDFFacadeRemote generadorReportesPDF;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tsu_RSB")
    TsuFacadeRemote tsuFacadeRemote;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        logger.info("--->>>GeneradorReportesAdapter:RequestURI="+request.getRequestURI());
        String param_dynamic = request.getParameter("dynamic");
        
        if(param_dynamic==null){
            param_dynamic="false";
        }else if(!param_dynamic.equals("true")){
            param_dynamic="false";
        }
        String absoluteURL = 
            request.getScheme() + "://" +   // "http" + "://
            request.getServerName() +       // "localhost"
            ":" +                           // ":"
            request.getServerPort() +       // "8080"
            request.getContextPath();       // "/AMX-PAB-Web"
        
        if(request.getRequestURI().indexOf(TSU_REPORTES_PATH)>TSU_REPORTES_PATH.length()+2){
            logger.info("--->>>GeneradorReportesAdapter:TSU Report !");
        }
        
        String cveTsu = null;        
        cveTsu = request.getRequestURI().substring(request.getRequestURI().indexOf(TSU_REPORTES_PATH)+TSU_REPORTES_PATH.length());
        logger.info("--->>>GeneradorReportesAdapter:tsuUri="+cveTsu);
        
                
        InputStream is=null;
        OutputStream os = null;
        byte[] contentPdf = null;
        
        try{
            logger.info("--->>>GeneradorReportesAdapter: calling EJB, param_dynamic="+param_dynamic+", absoluteURL="+absoluteURL);

            contentPdf = generadorReportesPDF.generarReporteTSU(cveTsu,param_dynamic.equals("true"), absoluteURL);
            
            logger.info("--->>>GeneradorReportesAdapter: after call, contentPdf="+contentPdf);
            if(contentPdf == null){
                throw new IOException("Erro generating, not generated");
            }
        }catch(IOException ex){
            logger.error("Error Searchibg:",ex);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }catch(Exception ex){
            logger.error("Error Searchibg:",ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        try{
            is=new ByteArrayInputStream(contentPdf);
            response.getOutputStream();

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentLength(contentPdf.length);
            response.setContentType("application/pdf");                        
            response.setHeader("Content-Disposition", "inline; filename=TSU_212123.PDF");
            
            byte[] buffer = new byte[1024*32];
            int r;
            os = response.getOutputStream();
            logger.info("--->>>GeneradorReportesAdapter: contentn.length="+contentPdf.length);
            while((r = is.read(buffer, 0, buffer.length))!= -1){
                os.write(buffer, 0, r);
                os.flush();
            }
            os.close();
            is.close();
            logger.info("--->>>GeneradorReportesAdapter: END Dispatching !");        
        }catch(IOException io){
            logger.error("Error Rendering",io);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet Adapter to call generadorReportesPDF-Stateles-Session";
    }// </editor-fold>    
}
