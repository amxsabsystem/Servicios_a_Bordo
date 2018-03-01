package com.aeromexico.pab.web.jsf.generales;


import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "directorioAMXMB")
@SessionScoped
public class DirectorioAMXMB implements Serializable{

    private Usuario row_selected = null;
    boolean modificarRegistro = false;
    private List<Empleado> all_records;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/empleado_RSB")
    EmpleadoFacadeRemote empleadoFacadeRemote;
     
    public List<Empleado> findAllUsers() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Empleado>();
                for(Empleado emp:empleadoFacadeRemote.findAll()){
                    if (emp.getDirectorioSab().equals((short) 1)) {
                        all_records.add(emp);
                    }
                }
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
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

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }   

    public List<Empleado> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Empleado> all_records) {
        this.all_records = all_records;
    }
    
}
