package com.aeromexico.pab.web.servlet;

import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web application lifecycle listener.
 *
 * @author Alfredo Estrada
 */
@Named(value = "appContextListener")
@ApplicationScoped
public class AppContextListener implements ServletContextListener, HttpSessionListener  {
    private static Logger logger = LoggerFactory.getLogger(AppContextListener.class.getName());
   
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("===================================[AppContextListener]==================================>>>>>");
        final String contextPath = sce.getServletContext().getContextPath();        
        logger.info("contextPath=->"+contextPath+"<-");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {                
        storageServiceFacadeRemote.forgetUncommitedInSession(null);
        logger.info("<<<<<==============================[AppContextListener]=======================================");        
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("----------------------------------[HttpSessionListener]---------------------------------->>>>>");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId=se.getSession().getId();
        final List<String> notCommitedList = storageServiceFacadeRemote.getNotCommitedList(sessionId);
        storageServiceFacadeRemote.forgetUncommitedInSession(sessionId);
        logger.info("<<<<<----------------------------------[HttpSessionListener]----------------------------------");
    }
}
