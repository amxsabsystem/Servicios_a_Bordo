package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.CompaniaFacadeRemote;
import com.aeromexico.pab.entity.Compania;
import com.aeromexico.pab.web.jsf.sab.planeacion.relacionDeFlota.ModeloMB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Named(value = "companiaMB")
@SessionScoped
public class CompaniaMB implements Serializable {

    public CompaniaMB() {
    }

    private Compania row_selected = null;
    boolean modificarRegistro = false;
    private List<Compania> all_records;
    private String claveAnterior = null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/compania_RSB")
    CompaniaFacadeRemote servicioFacadeRemote;

    @Inject
    @Named("empleadosMB")
    EmpleadosMB empleadosMB;
    
    @Inject
    @Named("modeloMB")
    ModeloMB modeloMB;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {

    }

    /**
     * Get all records
     *
     * @return
     */
    public List<Compania> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Compania>();
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
        String redirect = "/portal/generales/compania?faces-redirect=true";
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
        String redirect = "/portal/generales/companiaDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Compania();
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
            for (Compania regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getCveCompania().toUpperCase().equals(regs.getCveCompania().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Compañia ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getCveCompania().toUpperCase().equals(claveAnterior)
                            && row_selected.getCveCompania().toUpperCase().equals(regs.getCveCompania().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Compañia ya existe");
                        retorno = false;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
        }

        return retorno;
    }

    public void updateDependencies() {
        empleadosMB.init();
        modeloMB.init();
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            if (validate("form:saveButton")) {
                String claveUpper = row_selected.getCveCompania().toUpperCase();
                row_selected.setCveCompania(claveUpper);
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
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Compania row_selected) {
        String redirect = "/portal/generales/companiaDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getCveCompania();
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                String claveUpper = row_selected.getCveCompania().toUpperCase();
                row_selected.setCveCompania(claveUpper);
                servicioFacadeRemote.update(row_selected);
                all_records = null;
                findAll();
                updateDependencies();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Compania getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Compania row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<Compania> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Compania> all_records) {
        this.all_records = all_records;
    }

    public EmpleadosMB getEmpleadosMB() {
        return empleadosMB;
    }

    public void setEmpleadosMB(EmpleadosMB empleadosMB) {
        this.empleadosMB = empleadosMB;
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
