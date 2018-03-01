package com.aeromexico.pab.web.jsf.procesos;

import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.Empleado;
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
@Named(value = "manualesMB")
@SessionScoped
public class ManualesMB implements Serializable {
    
    public ManualesMB(){}
    
    private Empleado row_selected =null;
    boolean modificarRegistro =false;
	
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
	ParametroFacadeRemote ParametroFacadeRemote;
    
    public List<Empleado> findAll(){
            List<Empleado> retorno=new ArrayList<Empleado>();
            return retorno;
    }
        
    /**
     * Go back to Master Page
     */
    public String returnMaster(){
		String	redirect=  "/portal/procesos/manuales?faces-redirect=true";
                findAll();
		return redirect;
    }
    
    /**
	 * Go to new Row Page Detail
	 */
    public String addRow(){
		String	redirect=  "/portal/procesos/manualesDetalle?faces-redirect=true";
                modificarRegistro = false;
                row_selected = new Empleado();
		return redirect;
    }
    
        /**
	 * Save a new record
	 */
        public void save() {
		try {
                    row_selected.setStatus((short)1);
                    //ParametroFacadeRemote.create(row_selected);
			addRow();
                       messgeValidateInfo("form:saveButton","Registro Cargado correctamente "); 
		} catch (Exception e) {
			
                        messgeValidateError("form:saveButton","Error:"+e.getMessage());
		}
	}    
    
    /**
	 * Go to edit Row selected Page Detail
	 */
    public String modifyRow(Empleado row_selected){
    	String	redirect=  "/portal/procesos/manualesDetalle?faces-redirect=true";
        modificarRegistro =true;
        this.row_selected = row_selected;
		return redirect;
    }
    
        /**
	 * Update selected record
	 */
        public void update() {
		try {
                    row_selected.setStatus((short)1);
                    //ParametroFacadeRemote.update(row_selected);
                    findAll();
                    messgeValidateInfo("form:updateButton","Registro actualizado correctamente ");
		} catch (Exception e) {
			
                        messgeValidateError("form:updateButton","Error:"+e.getMessage());
		}
	}
    
  

    public Empleado getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Empleado row_selected) {
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
}
