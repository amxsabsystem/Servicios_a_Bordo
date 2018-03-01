package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.Producto;
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
@Named(value = "calculoCantidadxMatrizMB")
@SessionScoped
public class CalculoCantidadxMatrizMB implements Serializable{
    
    public CalculoCantidadxMatrizMB(){}
    
    
    private Producto row_selected =null;
    boolean modificarRegistro =false;
	
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
	ParametroFacadeRemote ParametroFacadeRemote;
    
    public List<Producto> findAll(){
            List<Producto> retorno=new ArrayList<Producto>();
            return retorno;
    }
        
    /**
     * Go back to Master Page
     */
    public String returnMaster(){
		String	redirect=  "/portal/sab/operaciones/calculoCantidadxMatriz?faces-redirect=true";
                findAll();
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
	 * Go to new Row Page Detail
	 */
    public String addRow(){
		String	redirect=  "/portal/sab/operaciones/calculoCantidadxMatrizDetalle?faces-redirect=true";
                modificarRegistro = false;
                row_selected = new Producto();                 
		return redirect;
    }
    
    /**
	 * Go to edit Row selected Page Detail
	 */
    public String modifyRow(Producto row_selected){
    	String	redirect=  "/portal/sab/operaciones/calculoCantidadxMatrizDetalle?faces-redirect=true";
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
    


    public Producto getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Producto row_selected) {
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
