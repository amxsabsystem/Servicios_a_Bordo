package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;

import com.aeromexico.pab.entity.Clase;
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
@Named(value = "clasesMB")
@SessionScoped
public class CLasesMB implements Serializable {

    public CLasesMB() {
    }

    private Clase row_selected = null;
    boolean modificarRegistro = false;
    private List<Clase> all_records;
    private String claveAnterior = null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote servicioFacadeRemote;
    
    @Inject
    @Named("vuelosMB")    
    VuelosMB vuelosMB;

    /**
     * Get all records
     *
     * @return
     */
    public List<Clase> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Clase>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/clases?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRow() {
        String redirect = "/portal/generales/clasesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Clase();
        claveAnterior = null;
        return redirect;
    }

    /**
     * valid on update and save
     *
     * @param boton
     * @return
     */
    public boolean validate(String boton) {
        boolean retorno = true;
        try {
            for (Clase regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getClave().toUpperCase().equals(regs.getClave().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Clase ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getClave().toUpperCase().equals(claveAnterior)
                            && row_selected.getClave().toUpperCase().equals(regs.getClave().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Clase ya existe");
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
            if (validate("form:saveButton")) {

                String claveUpper = row_selected.getClave().toUpperCase();
                row_selected.setClave(claveUpper);
                row_selected.setStatus((short) 1);
                servicioFacadeRemote.create(row_selected);
                all_records = null;
                vuelosMB.init();
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Clase row_selected) {
        String redirect = "/portal/generales/clasesDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getClave();
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {

                String claveUpper = row_selected.getClave().toUpperCase();
                row_selected.setClave(claveUpper);
                servicioFacadeRemote.update(row_selected);
                all_records = null;
                vuelosMB.init();
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Clase getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Clase row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<Clase> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Clase> all_records) {
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
