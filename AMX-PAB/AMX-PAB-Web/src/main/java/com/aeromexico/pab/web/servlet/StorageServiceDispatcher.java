package com.aeromexico.pab.web.servlet;

import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alfredo Estrada
 */
@WebServlet(name = "StorageServiceDispatcher", urlPatterns = {"/media/*"})
public class StorageServiceDispatcher extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(StorageServiceDispatcher.class.getName());

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

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
        logger.debug("");
        String path = null;
        InputStream isReource=null;
        path = request.getRequestURI();
        logger.debug(" param: path=["+path+"]");
        
        byte[] resourceLoaded2=null;
        try{
            logger.info("invoking EJB service: storageServiceFacadeRemote.openMedia("+path+")");
            resourceLoaded2= storageServiceFacadeRemote.openMedia(path);
            
            if(resourceLoaded2 == null){
                throw new IOException("Resource not found");
            }
            final String pathLC = path.toLowerCase();
            logger.debug(" storageServiceFacadeRemote: resourceLoaded2.length="+resourceLoaded2.length); 
            
            String contentType=null;
            if(pathLC.endsWith(".jpeg") || pathLC.endsWith(".jpg")){
                contentType="image/jpeg";            
            } else if(pathLC.endsWith(".png")){
                contentType="image/png";
            } else if(pathLC.endsWith(".gif")){
                contentType="image/gif";
            } else if(pathLC.endsWith(".pdf")){
                contentType="application/pdf";
            } else {
                contentType="application/octet-stream";            
            }

            response.setContentType(contentType);
            response.setContentLength(resourceLoaded2.length);

            isReource=new ByteArrayInputStream(resourceLoaded2);
            byte[] buffer = new byte[1024*32];
            int r;
            OutputStream osResource = response.getOutputStream();
            logger.debug(" Dispatching:");
            while((r = isReource.read(buffer, 0, buffer.length))!= -1){
                osResource.write(buffer, 0, r);
                osResource.flush();
            }
            osResource.close();
            isReource.close();
            logger.debug(" Dispatching: END Dispatching !");        
        }catch(IOException ioe){
            logger.error("IOExcetion:"+ioe.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace(System.err);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        return "Short description";
    }// </editor-fold>

}
