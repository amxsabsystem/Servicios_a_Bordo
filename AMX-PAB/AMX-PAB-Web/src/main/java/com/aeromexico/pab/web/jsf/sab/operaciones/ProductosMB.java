package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.ProductoFacadeRemote;
import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.web.dto.ProductoDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
@Named(value = "productosMB")
@SessionScoped
public class ProductosMB implements Serializable {

    public ProductosMB() {
    }

    private Producto row_selected = null;
    boolean modificarRegistro = false;
    private List<Producto> all_records;
    
    private List<ProductoDTO> productoDTOList;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/producto_RSB")
    ProductoFacadeRemote servicioFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;

    public List<Producto> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Producto>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public List<ProductoDTO> getProductoDTOList() {
        if(productoDTOList == null){
            List<Object[]> result = genericSearchFacadeRemote.loadQuery("SELECT p.idProducto,p.nombre,p.status FROM Producto p order by p.nombre");
            productoDTOList = new ArrayList<ProductoDTO>();
            for(Object[] oa: result){
                productoDTOList.add(new ProductoDTO((Integer)oa[0],(String)oa[1], (Short)oa[2]));
            }
        }
        return productoDTOList;
    }
    
    

    /**
     * Go back to Master Page
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/productos?faces-redirect=true";
        all_records = null;
        findAll();
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

            row_selected.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
            row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
            row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
            row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
            row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));

            servicioFacadeRemote.create(row_selected);
            all_records = null;
            addRow();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to new Row Page Det
     *
     * @return ail
     */
    public String addRow() {
        String redirect = "/portal/sab/operaciones/productosDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Producto();
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRowDTO(int idProducto) {
        this.row_selected = servicioFacadeRemote.findByPK(idProducto);
        return modifyRow(row_selected);
    }
    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Producto row_selected) {
        String redirect = "/portal/sab/operaciones/productosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            servicioFacadeRemote.update(row_selected);
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
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

    public List<Producto> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Producto> all_records) {
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
