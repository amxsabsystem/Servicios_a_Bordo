package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.TipoProveedorFacadeRemote;
import com.aeromexico.pab.entity.TipoProveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "tipoProveedorMB")
@SessionScoped
public class TipoProveedorMB implements Serializable {

    public TipoProveedorMB() { }

    private TipoProveedor row_selected = null;
    boolean modificarRegistro = false;
    private List<TipoProveedor> all_records;
    private String descripcionAnterior;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoProveedor_RSB")
    TipoProveedorFacadeRemote servicioFacadeRemote;

    @Inject
    @Named("proveedorMB")
    ProveedorMB proveedorMB;

    /**
     * Get all records
     *
     * @return
     */
    public List<TipoProveedor> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<TipoProveedor>();
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
        String redirect = "/portal/generales/tipoProveedor?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     * @return 
     */
    public String addRow() {
        String redirect = "/portal/generales/tipoProveedorDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new TipoProveedor();
        return redirect;
    }

    public void updateDependencies() {
        proveedorMB.init();
    }
    
    public boolean validate(String boton){
        boolean retorno = true;
        try {
            for (TipoProveedor regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getNombreEs().toUpperCase().equals(regs.getNombreEs().toUpperCase())) {
                        messgeValidateError(boton, "El tipo ya existe.");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getNombreEs().toUpperCase().equals(descripcionAnterior.toUpperCase())
                            && row_selected.getNombreEs().toUpperCase().equals(regs.getNombreEs().toUpperCase())) {
                        messgeValidateError(boton, "El tipo ya existe.");
                        retorno = false;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            if(validate("form:saveButton")){
            row_selected.setStatus((short) 1);
            servicioFacadeRemote.create(row_selected);
            all_records = null;
            updateDependencies();
            addRow();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to edit Row selected Page Detail
     * @param row_selected
     * @return 
     */
    public String modifyRow(TipoProveedor row_selected) {
        String redirect = "/portal/generales/tipoProveedorDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        descripcionAnterior = this.row_selected.getNombreEs();
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if(validate("form:updateButton")){
            servicioFacadeRemote.update(row_selected);
            updateDependencies();
            all_records = null;
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public TipoProveedor getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(TipoProveedor row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<TipoProveedor> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<TipoProveedor> all_records) {
        this.all_records = all_records;
    }

    public ProveedorMB getProveedorMB() {
        return proveedorMB;
    }

    public void setProveedorMB(ProveedorMB proveedorMB) {
        this.proveedorMB = proveedorMB;
    }

    public String getDescripcionAnterior() {
        return descripcionAnterior;
    }

    public void setDescripcionAnterior(String descripcionAnterior) {
        this.descripcionAnterior = descripcionAnterior;
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
