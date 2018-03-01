package com.aeromexico.pab.web.jsf.sab.planeacion.materiales;

import com.aeromexico.pab.backend.remote.CategoriaMaterialFacadeRemote;
import com.aeromexico.pab.entity.CategoriaMaterial;
import com.aeromexico.pab.web.jsf.sab.planeacion.relacionDeFlota.AvionMB;
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
@Named(value = "categoriasMB")
@SessionScoped
public class CategoriasMB implements Serializable{
    
    public CategoriasMB(){}
    
    private CategoriaMaterial row_selected =null;
    boolean modificarRegistro =false;
    private List<CategoriaMaterial> all_records;
	
	@EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/categoriaMaterial_RSB")	
	CategoriaMaterialFacadeRemote servicioFacadeRemote;
    
    @Inject
    @Named("materialesMB")
    MaterialesMB materialesMB;
    
    public List<CategoriaMaterial> findAll() {
        if(all_records ==null){
            try{
                all_records =new ArrayList<CategoriaMaterial>();
                all_records = servicioFacadeRemote.findAll();
            } catch(Exception ex){
                all_records=null;
            }
        }
        return all_records;
    }
        
    /**
     * Go back to Master Page
     * @return 
     */
    public String returnMaster(){
		String	redirect=  "/portal/sab/planeacion/materiales/categorias?faces-redirect=true";
        all_records=null;
                findAll();
		return redirect;
    }
    
    /**
    * Go to new Row Page Detail
     * @return 
    */
    public String addRow(){
	String	redirect=  "/portal/sab/planeacion/materiales/categoriasDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new CategoriaMaterial();
        all_records=null;
        return redirect;
    }
    
        /**
	 * Save a new record
	 */
        public void save() {
                    Calendar calendar = Calendar.getInstance();
                    java.util.Date now = calendar.getTime();
                    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();            
		try {
                    row_selected.setStatus((short)1);
                    row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
                    row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                    row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                    row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    servicioFacadeRemote.create(row_selected);
                    materialesMB.init();
                    all_records=null;
			addRow();
                       messgeValidateInfo("form:saveButton","Registro Cargado correctamente "); 
		} catch (Exception e) {
			
                        messgeValidateError("form:saveButton","Error:"+e.getMessage());
		}
	}     
    
    /**
	 * Go to edit Row selected Page Det
     * @param row_selected
     * @return 
	 */
    public String modifyRow(CategoriaMaterial row_selected){
    	String	redirect=  "/portal/sab/planeacion/materiales/categoriasDetalle?faces-redirect=true";
        modificarRegistro =true;
        this.row_selected = row_selected;
	return redirect;
    }
 
        /**
	 * Update selected record
	 */
        public void update() {
                    Calendar calendar = Calendar.getInstance();
                    java.util.Date now = calendar.getTime();
                    HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();            
		try {
                    
                    row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                    row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    servicioFacadeRemote.update(row_selected);
                    materialesMB.init();
                    all_records=null;
                    findAll();
                    messgeValidateInfo("form:updateButton","Registro actualizado correctamente ");
		} catch (Exception e) {
			
                        messgeValidateError("form:updateButton","Error:"+e.getMessage());
		}
	}
    


    public CategoriaMaterial getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(CategoriaMaterial row_selected) {
        this.row_selected = row_selected;
    }
    
    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<CategoriaMaterial> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<CategoriaMaterial> all_records) {
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
