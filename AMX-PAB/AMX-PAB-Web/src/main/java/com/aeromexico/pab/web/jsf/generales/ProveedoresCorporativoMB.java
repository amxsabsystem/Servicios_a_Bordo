package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.ProveedorFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.Region;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "proveedoresCorporativoMB")
@SessionScoped
public class ProveedoresCorporativoMB implements Serializable {

    public ProveedoresCorporativoMB() {
    }

    private Proveedor row_selected = null;
    boolean modificarRegistro = false;
    private List<Proveedor> all_records;
    private List<Integer> asignedRegionIDs;
    private List<SelectItem> selectRegiones;
    private String claveAnterior = null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedor_RSB")
    ProveedorFacadeRemote proveedorFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;

    @Inject
    @Named("proveedorMB")
    ProveedorMB proveedorMB;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        asignedRegionIDs = new ArrayList<Integer>();
        if (selectRegiones == null) {
            selectRegiones = new ArrayList<SelectItem>();
            for (Region regs : regionesFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectRegiones.add(new SelectItem(regs.getIdRegion(), "[" + regs.getCveRegion() + "]" + regs.getNombre()));
                }
            }
        }
    }

    /**
     * Get all records
     *
     * @return
     */
    public List<Proveedor> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Proveedor>();
                all_records = proveedorFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public Region getRegion(Integer IdRegion) {
        return regionesFacadeRemote.findByPK(IdRegion);
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/proveedoresCorporativo?faces-redirect=true";
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
        String redirect = "/portal/generales/proveedoresCorporativoDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Proveedor();
        asignedRegionIDs = new ArrayList<Integer>();
        claveAnterior = null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedRegionIDs.isEmpty()) {
            messgeValidateError(boton, "Debe seleccionar una Región");
            return false;
        }

        try {
            for (Proveedor regs : proveedorFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getClaveProveedor().toUpperCase().equals(regs.getClaveProveedor().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Proveedor ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getClaveProveedor().toUpperCase().equals(claveAnterior.toUpperCase())
                            && row_selected.getClaveProveedor().toUpperCase().equals(regs.getClaveProveedor().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Proveedor ya existe");
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
                String claveUpper = row_selected.getClaveProveedor().toUpperCase();
                row_selected.setClaveProveedor(claveUpper);
                row_selected.setStatus((short) 1);

                List<Region> listRegion_selcted = new ArrayList<Region>();

                for (Object regionIDO : asignedRegionIDs) {
                    Object regionID = new Integer(regionIDO.toString());
                    Region reg_found = regionesFacadeRemote.findByPK(regionID);
                    listRegion_selcted.add(reg_found);
                }
                row_selected.setRegionList(listRegion_selcted);
                proveedorFacadeRemote.create(row_selected);
                asignedRegionIDs.clear();
                proveedorMB.init();
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
     * @param row_selected_p
     * @return
     */
    public String modifyRow(Proveedor row_selected_p) {
        String redirect = "/portal/generales/proveedoresCorporativoDetalle?faces-redirect=true";
        asignedRegionIDs.clear();
        modificarRegistro = true;
        this.row_selected = proveedorFacadeRemote.findByPK_EAGER(row_selected_p.getClaveProveedor());
        claveAnterior = this.row_selected.getClaveProveedor();
        List<Region> listRegion_selcted = new ArrayList<Region>();
        for (Region region : this.row_selected.getRegionList()) {
            asignedRegionIDs.add(region.getIdRegion());
        }
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                String claveUpper = row_selected.getClaveProveedor().toUpperCase();
                row_selected.setClaveProveedor(claveUpper);
                List<Region> listRegion_selcted = new ArrayList<Region>();

                for (Object regionIDO : asignedRegionIDs) {
                    Object regionID = new Integer(regionIDO.toString());
                    Region reg_found = regionesFacadeRemote.findByPK(regionID);
                    listRegion_selcted.add(reg_found);
                }
                row_selected.setRegionList(listRegion_selcted);
                proveedorFacadeRemote.update(row_selected);
                proveedorMB.init();
                all_records = null;
                findAll();
                asignedRegionIDs.clear();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Proveedor getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Proveedor row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<Integer> getAsignedRegionIDs() {
        return asignedRegionIDs;
    }

    public void setAsignedRegionIDs(List<Integer> asignedRegionIDs) {
        this.asignedRegionIDs = asignedRegionIDs;
    }

    public List<SelectItem> getSelectRegiones() {
        return selectRegiones;
    }

    public void setSelectRegiones(List<SelectItem> selectRegiones) {
        this.selectRegiones = selectRegiones;
    }

    public List<Proveedor> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Proveedor> all_records) {
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
