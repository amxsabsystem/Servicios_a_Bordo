package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProductoFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoEquipoAvionFacadeRemote;
import com.aeromexico.pab.entity.Matriz;
import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.entity.TipoEquipoAvion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "matrizMB")
@SessionScoped
public class MatrizMB implements Serializable {

    public MatrizMB() {
    }

    private Matriz row_selected = null;
    boolean modificarRegistro = false;
    List<Integer> equipos = null;

    private List<Producto> all_productos;
    private List<TipoEquipoAvion> all_equipos;

    private List<EquiposProducto> lista_equiposProducto;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/producto_RSB")
    ProductoFacadeRemote productoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoEquipoAvion_RSB")
    TipoEquipoAvionFacadeRemote tipoEquipoAvionFacadeRemote;

    public List<TipoEquipoAvion> findAllTiposDeEquipo() {
        try {
            all_equipos = new ArrayList<TipoEquipoAvion>();
            all_equipos = tipoEquipoAvionFacadeRemote.findAll();
        } catch (Exception ex) {
            all_equipos = null;
        }

        return all_equipos;
    }

    public List<Producto> findAllProductos() {
        try {
            all_productos = new ArrayList<Producto>();
            all_productos = productoFacadeRemote.findAll();
        } catch (Exception ex) {
            all_productos = null;
        }

        return all_productos;
    }

    public List<EquiposProducto> findAll(Integer idTipoEquipo) {
        lista_equiposProducto.clear();
        for (TipoEquipoAvion equipo : tipoEquipoAvionFacadeRemote.findAll()) {
            if (equipo.getIdTipoEquipoAvion() == idTipoEquipo) {
                EquiposProducto equipoProd = null;
                for (Producto producto : productoFacadeRemote.findAll()) {
                    equipoProd = new EquiposProducto();
                    equipoProd.setEquipo(equipo);
                    equipoProd.setProducto(producto);
                    equipoProd.setIdequipoProducto(equipo.getIdTipoEquipoAvion() + "_" + producto.getIdProducto());
                    lista_equiposProducto.add(equipoProd);

                }

            }
        }
        return lista_equiposProducto;
    }

    @PostConstruct
    public void init() {

        lista_equiposProducto = new ArrayList<EquiposProducto>();

        equipos = new ArrayList<Integer>();
        equipos.add(new Integer(220));
        equipos.add(new Integer(739));
        equipos.add(new Integer(140));
        equipos.add(new Integer(221));
    }

    /**
     * Go back to Master Page
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/matriz?faces-redirect=true";
        //findAll();
        return redirect;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            //row_selected.setStatus((short)1);
            //ParametroFacadeRemote.create(row_selected);
            addRow();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to new Row Page Detail
     */
    public String addRow() {
        String redirect = "/portal/sab/operaciones/matrizDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Matriz();
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     */
    public String modifyRow(Matriz row_selected) {
        String redirect = "/portal/sab/operaciones/matrizDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            //row_selected.setStatus((short)1);
            //ParametroFacadeRemote.update(row_selected);
            // findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Matriz getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Matriz row_selected) {
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

    public List<Integer> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Integer> equipos) {
        this.equipos = equipos;
    }

    public List<Producto> getAll_productos() {
        return all_productos;
    }

    public void setAll_productos(List<Producto> all_productos) {
        this.all_productos = all_productos;
    }

    public List<TipoEquipoAvion> getAll_equipos() {
        return all_equipos;
    }

    public void setAll_equipos(List<TipoEquipoAvion> all_equipos) {
        this.all_equipos = all_equipos;
    }

    public List<EquiposProducto> getLista_equiposProducto() {
        return lista_equiposProducto;
    }

    public void setLista_equiposProducto(List<EquiposProducto> lista_equiposProducto) {
        this.lista_equiposProducto = lista_equiposProducto;
    }

}
