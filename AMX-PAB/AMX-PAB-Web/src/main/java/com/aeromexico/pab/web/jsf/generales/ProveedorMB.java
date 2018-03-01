package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoProveedorFacadeRemote;
import com.aeromexico.pab.entity.Ciudad;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Pais;
import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.ProveedorEstacion;
import com.aeromexico.pab.entity.Region;
import com.aeromexico.pab.entity.TipoProveedor;
import com.aeromexico.pab.web.jsf.LocaleInfo;
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
@Named(value = "proveedorMB")
//@ManagedBean(name="proveedorMB", eager=true)
@SessionScoped
public class ProveedorMB implements Serializable {

    private List<String> asignedtipos;
    private List<SelectItem> selectedTipos;
    boolean modificarRegistro = false;

    private String asignedProveedor;
    private List<SelectItem> selectProveedores;
    private Integer asignedEstacion;
    private List<SelectItem> selectEstaciones;
    private List<ProveedorEstacion> all_records;

    private String labelRegion;
    private String labelPais;
    private String labelCiudad;
    private String labelClave;
    private String claveProveedor;

    public ProveedorMB() { }

    private ProveedorEstacion row_selected = null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedorEstacion_RSB")
    ProveedorEstacionFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedor_RSB")
    ProveedorFacadeRemote proveedorCorporativoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacioneFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoProveedor_RSB")
    TipoProveedorFacadeRemote tipoProveedorFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;

    @Inject
    @Named("contactoProveedoresMB")
    ContactoProveedoresMB contactoProveedoresMB;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        selectedTipos = new ArrayList<>();
        try {
            final List<TipoProveedor> tipoPRoveedorList = tipoProveedorFacadeRemote.findAll();
            for (TipoProveedor tp : tipoPRoveedorList) {
                if (tp.getStatus().equals((short) 1)) {
                selectedTipos.add(new SelectItem(String.valueOf(tp.getIdTipoProveedor()), tp.getNombreEs()));
                }
            }
        } catch (Exception ex) {
        }

        selectProveedores = new ArrayList<SelectItem>();
        selectProveedores.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            for (Proveedor regs : proveedorCorporativoFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectProveedores.add(new SelectItem(regs.getClaveProveedor(), regs.getNombre()));
                }
            }
        } catch (Exception ex) {
        }

        selectEstaciones = new ArrayList<SelectItem>();
        selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Estacion regs : estacioneFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectEstaciones.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata()+" - "+regs.getNombre()));
                }
            }
        } catch (Exception ex) {
        }
        asignedEstacion = -1;
        asignedProveedor = "-1";
        asignedtipos = null;
    }
    
    /**
     * Get all records
     *
     * @return
     */
    public List<ProveedorEstacion> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<ProveedorEstacion>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }    

    public Proveedor getProveedor(String claveProveedor) {
        return proveedorCorporativoFacadeRemote.findByPK(claveProveedor);
    }

    public TipoProveedor getTipoProveedor(int idTipoProveedor) {
        return tipoProveedorFacadeRemote.findByPK(idTipoProveedor);
    }

    public Region getRegion(Integer idEstacion) {
        return regionesFacadeRemote.findByPK(paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(estacioneFacadeRemote.findByPK(idEstacion).getCiudad().getIdCiudad()).getPais().getIdPais()).getRegion().getIdRegion());
    }

    public Pais getPais(Integer idEstacion) {
        return paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(estacioneFacadeRemote.findByPK(idEstacion).getCiudad().getIdCiudad()).getPais().getIdPais());
    }

    public Ciudad getEstadoCiudad(Integer idEstacion) {
        return estadoCiudadFacadeRemote.findByPK(estacioneFacadeRemote.findByPK(idEstacion).getCiudad().getIdCiudad());
    }

    public Estacion getEstacion(Integer idEstacion) {
        return estacioneFacadeRemote.findByPK(idEstacion);
    }

    public void updateProveedorClave() {
        claveProveedor = asignedProveedor;
    }

    public void updateLabels() {
        labelRegion = getRegion(asignedEstacion).getNombre();
        labelPais = getPais(asignedEstacion).getNombre();
        labelCiudad = getEstadoCiudad(asignedEstacion).getNombre();
        labelClave = claveProveedor + (getEstacion(asignedEstacion)).getClaveIata();
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/proveedores?faces-redirect=true";
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
        String redirect = "/portal/generales/proveedoresDetalle?faces-redirect=true";
        modificarRegistro = false;
        asignedEstacion = -1;
        asignedProveedor = "-1";

        row_selected = new ProveedorEstacion();
        asignedEstacion = -1;
        asignedProveedor = null;
        labelRegion = null;
        labelPais = null;
        labelCiudad = null;
        labelClave = null;
        claveProveedor = null;
        asignedtipos = new ArrayList<String>();
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedEstacion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Estación");
            return false;
        }
        if (asignedProveedor == null || asignedProveedor.equals("-1")) {
            messgeValidateError(boton, "Debe seleccionar un Proveedor");
            return false;
        }

        if (asignedtipos == null || asignedtipos.isEmpty()) {
            messgeValidateError(boton, "Debe seleccionar un Tipo");
            return false;
        }

        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {

            if (validate("form:saveButton")) {
                row_selected.setStatus((short) 1);
                Estacion e = new Estacion();
                e.setIdEstacion(asignedEstacion);
                row_selected.setEstacion(e);
                final ArrayList<TipoProveedor> tipoProveedorList = new ArrayList<TipoProveedor>();
                row_selected.setTipoProveedorList(tipoProveedorList);
                for (String tipoProveedorSelected : asignedtipos) {
                    TipoProveedor tps = tipoProveedorFacadeRemote.findByPK(new Integer(tipoProveedorSelected));
                    tipoProveedorList.add(tps);
                }
                row_selected.setTipoProveedorList(tipoProveedorList);

                Proveedor p = new Proveedor();
                p.setClaveProveedor(asignedProveedor);
                row_selected.setProveedor(p);
                row_selected.setClaveProveedorEstacion(asignedProveedor + (getEstacion(asignedEstacion)).getClaveIata());
                servicioFacadeRemote.create(row_selected);
                contactoProveedoresMB.init();

                asignedEstacion = -1;
                asignedProveedor = null;
                labelRegion = null;
                labelPais = null;
                labelCiudad = null;
                labelClave = null;
                claveProveedor = null;
                asignedtipos.clear();

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
     * @param rs
     * @return
     */
    public String modifyRow(ProveedorEstacion rs) {
        String redirect = "/portal/generales/proveedoresDetalle?faces-redirect=true";
        modificarRegistro = true;
        row_selected = servicioFacadeRemote.findByPK_EAGER(rs.getIdProveedorEstacion());

        asignedEstacion = this.row_selected.getEstacion().getIdEstacion();
        asignedProveedor = this.row_selected.getProveedor().getClaveProveedor();
        claveProveedor = this.row_selected.getProveedor().getClaveProveedor();
        asignedtipos = new ArrayList<String>();

        List<TipoProveedor> tipoProveedorList = row_selected.getTipoProveedorList();
        for (TipoProveedor tp : tipoProveedorList) {
            asignedtipos.add(String.valueOf(tp.getIdTipoProveedor()));
        }

        updateLabels();
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                Estacion e = new Estacion();
                e.setIdEstacion(asignedEstacion);
                row_selected.setEstacion(e);

                ArrayList<TipoProveedor> tipoProveedorList = new ArrayList<TipoProveedor>();
                row_selected.setTipoProveedorList(tipoProveedorList);
                for (String tipoProveedorSelected : asignedtipos) {
                    TipoProveedor tps = tipoProveedorFacadeRemote.findByPK(new Integer(tipoProveedorSelected));
                    tipoProveedorList.add(tps);
                }
                row_selected.setTipoProveedorList(tipoProveedorList);

                
                Proveedor p = new Proveedor();
                p.setClaveProveedor(asignedProveedor);
                row_selected.setProveedor(p);
row_selected.setClaveProveedorEstacion(asignedProveedor + (getEstacion(asignedEstacion)).getClaveIata());
                servicioFacadeRemote.update(row_selected);
                contactoProveedoresMB.init();
                all_records = null;
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public ProveedorEstacion getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(ProveedorEstacion row_selected) {
        this.row_selected = row_selected;
    }

    public List<SelectItem> getSelectedTipos() {
        return selectedTipos;
    }

    public void setSelectedTipos(List<SelectItem> selectedTipos) {
        this.selectedTipos = selectedTipos;
    }

    public List<String> getAsignedtipos() {
        return asignedtipos;
    }

    public void setAsignedtipos(List<String> asignedtipos) {
        this.asignedtipos = asignedtipos;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public String getAsignedProveedor() {
        return asignedProveedor;
    }

    public void setAsignedProveedor(String asignedProveedor) {
        this.asignedProveedor = asignedProveedor;
    }

    public List<SelectItem> getSelectProveedores() {
        return selectProveedores;
    }

    public void setSelectProveedores(List<SelectItem> selectProveedores) {
        this.selectProveedores = selectProveedores;
    }

    public Integer getAsignedEstacion() {
        return asignedEstacion;
    }

    public void setAsignedEstacion(Integer asignedEstacion) {
        this.asignedEstacion = asignedEstacion;
    }

    public List<SelectItem> getSelectEstaciones() {
        return selectEstaciones;
    }

    public void setSelectEstaciones(List<SelectItem> selectEstaciones) {
        this.selectEstaciones = selectEstaciones;
    }

    public String getLabelRegion() {
        return labelRegion;
    }

    public void setLabelRegion(String labelRegion) {
        this.labelRegion = labelRegion;
    }

    public String getLabelPais() {
        return labelPais;
    }

    public void setLabelPais(String labelPais) {
        this.labelPais = labelPais;
    }

    public String getLabelCiudad() {
        return labelCiudad;
    }

    public void setLabelCiudad(String labelCiudad) {
        this.labelCiudad = labelCiudad;
    }

    public String getLabelClave() {
        return labelClave;
    }

    public void setLabelClave(String labelClave) {
        this.labelClave = labelClave;
    }

    public List<ProveedorEstacion> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<ProveedorEstacion> all_records) {
        this.all_records = all_records;
    }

    public String getClaveProveedor() {
        return claveProveedor;
    }

    public void setClaveProveedor(String claveProveedor) {
        this.claveProveedor = claveProveedor;
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
