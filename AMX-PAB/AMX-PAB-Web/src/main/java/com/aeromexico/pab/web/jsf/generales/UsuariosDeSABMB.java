package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;
import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "usuariosDeSABMB")
@SessionScoped
public class UsuariosDeSABMB implements Serializable {

    private Usuario row_selected = null;
    boolean modificarRegistro = false;
    private String tipo;

    private List<Usuario> all_records;
    private String emailAnterior;
    private String intextLabel;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/usuario_RSB")
    UsuarioFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/contactoProveedorEstacion_RSB")
    ContactoProveedorEstacionFacadeRemote contactoProveedorFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/empleado_RSB")
    EmpleadoFacadeRemote empleadoFacadeRemote;

    @PostConstruct
    public void init() {
        intextLabel=null;
    }

    public List<Usuario> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Usuario>();
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
        String redirect = "/portal/generales/usuariosDeSAB?faces-redirect=true";
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
        String redirect = "/portal/generales/usuariosDeSABDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Usuario();
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;

        if (boton.equals("form:saveButton")) {
            for (Usuario usu : servicioFacadeRemote.findAll()) {
                if (this.row_selected.getEmailUsuario().equals(usu.getEmailUsuario())) {
                    messgeValidateError(boton, "El usuario ya existe");
                    break;
                }
            }
        } else {
            for (Usuario usu : servicioFacadeRemote.findAll()) {
                if (!this.row_selected.getEmailUsuario().equals(emailAnterior)
                        && this.row_selected.getEmailUsuario().equals(usu.getEmailUsuario())) {
                    messgeValidateError(boton, "El usuario ya existe");
                    break;
                }
            }

        }
        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        try {
            if (validate("form:saveButton")) {
                row_selected.setStatus((short) 1);
                row_selected.setContrasenia("");
                servicioFacadeRemote.create(row_selected);
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public String IntExt(String email) {
        String retorno = "";
        for (Empleado emp : empleadoFacadeRemote.findAll()) {
            if (emp.getUsuario() != null && emp.getUsuario().getEmailUsuario().equals(email)) {
                retorno = "Int";
            }
        }
        for (ContactoProveedorEstacion emp : contactoProveedorFacadeRemote.findAll()) {
            if (emp.getUsuario() != null && emp.getUsuario().getEmailUsuario().equals(email)) {
                retorno = "Ext";
            }
        }

        return retorno;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Usuario row_selected) {
        String redirect = "/portal/generales/usuariosDeSABDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        emailAnterior = this.row_selected.getEmailUsuario();
        intextLabel=IntExt(this.row_selected.getEmailUsuario());
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        try {
            if (validate("form:updateButton")) {
            row_selected.setContrasenia("");
            servicioFacadeRemote.update(row_selected);
            all_records = null;
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Usuario getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Usuario row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Usuario> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Usuario> all_records) {
        this.all_records = all_records;
    }

    public String getEmailAnterior() {
        return emailAnterior;
    }

    public void setEmailAnterior(String emailAnterior) {
        this.emailAnterior = emailAnterior;
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
