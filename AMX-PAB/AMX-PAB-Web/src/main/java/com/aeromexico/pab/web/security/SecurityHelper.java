package com.aeromexico.pab.web.security;

import com.aeromexico.pab.web.jsf.SesionInfo;
import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alfredo Estrada
 */
@Named(value="SecurityHelper")
@ConversationScoped
public class SecurityHelper  implements Serializable{
    private static Logger logger = LoggerFactory.getLogger(SecurityHelper.class.getName());
    @Inject
    @Named("sesionInfo")
    SesionInfo sesionInfo;

    public boolean isValidState(){
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final String requestContextPath = facesContext.getExternalContext().getRequestContextPath();
        final String requestPathInfo = facesContext.getExternalContext().getRequestPathInfo();
        final String requestServletPath = facesContext.getExternalContext().getRequestServletPath();
        final String requestRequestLocale = facesContext.getExternalContext().getRequestLocale().toString();
        logger.info("-->>isValidState: [PORTAL_BASICUSER?"+
                sesionInfo.isUser_PORTAL_BASICUSER()+
                "]"+
                "  requestContextPath="+requestContextPath+
                ", requestPathInfo="+requestPathInfo+
                ", requestServletPath="+requestServletPath+
                ", requestRequestLocale="+requestRequestLocale);
        
        return true;
    }
}
