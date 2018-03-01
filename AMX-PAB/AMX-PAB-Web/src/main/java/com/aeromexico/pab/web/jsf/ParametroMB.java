package com.aeromexico.pab.web.jsf;

import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.Parametro;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alfredo.estrada
 */
@Named(value = "parametroMB")
@SessionScoped
public class ParametroMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ParametroMB.class.getName());
    private Parametro row_selected = null;
    boolean modificarRegistro = false;
    private List<Parametro> all_records;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;
    
    /**
     * Creates a new instance of ParametroMB
     */
    public ParametroMB() { }

    private List<String> paramNames;
    
    public List<String> getParamNames() {
        if (paramNames == null) {
            try {
                paramNames = new ArrayList<String>();
                
                logger.info("->getParamNames:genericSearchFacadeRemote="+genericSearchFacadeRemote);
                
                final List qr = genericSearchFacadeRemote.loadQuery("SELECT distinct p.clave FROM Parametro p");
                logger.info("->getParamNames:qr="+qr);
                
                for(Object r: qr){
                    final String r_clave = (String)r;
                    logger.info("->getParamNames:\tr_clave="+r_clave);
                    paramNames.add(r_clave);
                }
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                paramNames = null;
            }
        }
        return paramNames;
    }    
    
    public List<Parametro> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Parametro>();
                all_records = ParametroFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }    

    /**
     * Go back to Master Page
     * @return 
     */
    public String returnMaster() {
        String redirect = "/portal/generales/parametros?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     * @return 
     */
    public String addRow() {
        String redirect = "/portal/generales/parametrosDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Parametro();
        return redirect;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            row_selected.setStatus((short) 1);
            ParametroFacadeRemote.create(row_selected);
            addRow();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to edit Row selected Page Detail
     * @param row_selected
     * @return 
     */
    public String modifyRow(Parametro row_selected) {
        String redirect = "/portal/generales/parametrosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            row_selected.setStatus((short) 1);
            ParametroFacadeRemote.update(row_selected);
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }


    public Parametro getRow_selected() {
        return row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<Parametro> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Parametro> all_records) {
        this.all_records = all_records;
    }
    
    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

}
