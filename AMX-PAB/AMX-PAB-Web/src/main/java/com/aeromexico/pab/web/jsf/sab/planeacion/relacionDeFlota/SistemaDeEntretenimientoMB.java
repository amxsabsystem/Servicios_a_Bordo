package com.aeromexico.pab.web.jsf.sab.planeacion.relacionDeFlota;

import com.aeromexico.pab.backend.remote.SistemaEntretenimientoFacadeRemote;
import com.aeromexico.pab.entity.SistemaEntretenimiento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "sistemaDeEntretenimientoMB")
@SessionScoped
public class SistemaDeEntretenimientoMB implements Serializable {

    public SistemaDeEntretenimientoMB() {
    }

    private SistemaEntretenimiento row_selected = null;
    boolean modificarRegistro = false;
    private List<SistemaEntretenimiento> all_records;
   // private Integer versionAnterior =null;
    private String version =null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/sistemaEntretenimiento_RSB")
    SistemaEntretenimientoFacadeRemote servicioFacadeRemote;
    
    @Inject
    @Named("avionMB")
    AvionMB avionMB;

    public List<SistemaEntretenimiento> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<SistemaEntretenimiento>();
                all_records = servicioFacadeRemote.findAll();
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
        String redirect = "/portal/sab/planeacion/relacionDeFlota/sistemaDeEntretenimiento?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     * @return 
     */
    public String addRow() {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/sistemaDeEntretenimientoDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new SistemaEntretenimiento();
        //versionAnterior =null;
        version =null;
        return redirect;
    }

    /**
     * Save a new record
     */
    public void save() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {

            row_selected.setStatus((short) 1);
            row_selected.setVersion(version);
            row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
            row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
            row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
            row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));

            
            servicioFacadeRemote.create(row_selected);
            avionMB.init();
            all_records = null;
           // versionAnterior =null;
        version =null;
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
    public String modifyRow(SistemaEntretenimiento row_selected) {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/sistemaDeEntretenimientoDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        version = this.row_selected.getVersion();
       // versionAnterior = this.row_selected.getVersion()!=null? Integer.parseInt(this.row_selected.getVersion()):null;
        return redirect;
    }
    
    public boolean validate(String boton){
    boolean retorno = true;
       /* if (boton.equals("form:updateButton") && version!=null && versionAnterior!=null) {
            
            if(versionAnterior >=  Integer.parseInt(version)){
            messgeValidateError(boton, "La versión debe ser mayor a la anterior");
            return false;
            }
        }
    */
       return retorno; 
    }

    /**
     * Update selected record
     */
    public void update() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
        if(validate("form:updateButton")){    
            row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
            row_selected.setVersion(version);
            row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
            servicioFacadeRemote.update(row_selected);
            avionMB.init();
            all_records = null;
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        }
        } catch (Exception e) {
            
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }



    public SistemaEntretenimiento getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(SistemaEntretenimiento row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<SistemaEntretenimiento> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<SistemaEntretenimiento> all_records) {
        this.all_records = all_records;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
