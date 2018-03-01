package com.aeromexico.pab.web.jsf;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Perfil;
import com.aeromexico.pab.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author alfredo
 */

@Named(value="sesionInfo")
@SessionScoped
public class SesionInfo implements Serializable{
	private static Logger logger = LoggerFactory.getLogger(SesionInfo.class.getName());
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/usuario_RSB")
    UsuarioFacadeRemote servicioFacadeRemote;

    Usuario currentUser;
    
	public SesionInfo(){
		logger.debug("->SesionMB():");
	}

    @PostConstruct
    public void init() {
		logger.debug("->init: ");        
    }
    
    public String getCurrentUserName() {
        StringBuilder sbName=new StringBuilder();
        sbName.append(getCurrentUser().getNombre());
        if(getCurrentUser().getApellidoPaterno() != null){
            sbName.append(" ");
            sbName.append(getCurrentUser().getApellidoPaterno());
        }
        if(getCurrentUser().getApellidoMaterno() != null){
            sbName.append(" ");
            sbName.append(getCurrentUser().getApellidoMaterno());
        }
        return sbName.toString();
    }

    public Usuario getCurrentUser() {        
        if(currentUser == null){
            logger.info("1st time call, localeInfo="+localeInfo);
            final String remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
            logger.info("remoteUser="+remoteUser);
            currentUser = servicioFacadeRemote.findByPK_EAGER(remoteUser);
            logger.info("currentUser="+currentUser);
            final List<Perfil> perfilList = currentUser.getPerfilList();
            logger.info("perfilList{");
            for(Perfil p: perfilList){
                logger.info("\t"+p);
            }
            logger.info("->}");
            logger.info("empleadoList{");
            final List<Empleado> empleadoList = currentUser.getEmpleadoHasUsuarioList();
            for(Empleado e: empleadoList){
                logger.info("\t"+e);
            }
            logger.info("->}");
        }
        return currentUser;
    }

    public boolean isUser_PORTAL_BASICUSER(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.PORTAL_BASICUSER.toString());    
    }
    
    public boolean isUser_PORTAL_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.PORTAL_ADMINISTRATOR.toString());    
    }
    
    public boolean isUser_OPERATION_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.OPERATION_ADMINISTRATOR.toString());    
    }
    
    public boolean isUser_DESIGN_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.DESIGN_ADMINISTRATOR.toString());    
    }
    
    public boolean isUser_PLANNING_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.PLANNING_ADMINISTRATOR.toString());    
    }
    
    public boolean isUser_EVALUATION_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.EVALUATION_ADMINISTRATOR.toString());    
    }

    public boolean isUser_ONBOARD_STD_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.ONBOARD_STD_ADMINISTRATOR.toString());    
    }

    public boolean isUser_FLIGHT_ATTENDANT_ADMINISTRATOR(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.FLIGHT_ATTENDANT_ADMINISTRATOR.toString());    
    }
    
    public boolean isUser_SUPPLIER(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(Constantes.PERFIL.SUPPLIER.toString());    
    }
    
    public LocaleInfo getLocaleBean() { 
        return localeInfo;
    }

}