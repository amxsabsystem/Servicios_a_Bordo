package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.entity.Ciudad;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Pais;
import com.aeromexico.pab.entity.Region;
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
@Named(value = "estacionesMB")
@SessionScoped
public class EstacionesMB implements Serializable {

    public EstacionesMB() {
    }

    private Estacion row_selected = null;
    boolean modificarRegistro = false;

    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private Integer asignedPais;
    private List<SelectItem> selectPaises;
    private Integer asignedEstadoCiudad;
    private List<SelectItem> selectEstadoCiudad;
    private List<Estacion> all_records;
    private String claveAnterior = null;
    private String version;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;

    @Inject
    @Named("localeInfo")    
    LocaleInfo localeInfo;

    @Inject
    @Named("empleadosMB")    
    EmpleadosMB empleadosMB;
    
    @Inject
    @Named("proveedorMB")        
    ProveedorMB proveedorMB;
    
    @Inject
    @Named("contactoProveedoresMB")
    ContactoProveedoresMB contactoProveedoresMB;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        asignedRegion = -1;
        selectRegiones = new ArrayList<SelectItem>();
        selectRegiones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Region regs : regionesFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectRegiones.add(new SelectItem(regs.getIdRegion(), regs.getCveRegion()));
                }
            }
        } catch (Exception ex) {

        }

    }

    /**
     * Get all records
     *
     * @return
     */
    public List<Estacion> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Estacion>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    /**
     * Get all Paises from region selected
     *
     */
    public void getPaises() {
        asignedPais = -1;
        selectPaises = new ArrayList<SelectItem>();
        selectPaises.add(localeInfo.createSelectIntKeyFirstItem());
        for (Pais regs : paisesFacadeRemote.findAll()) {
            if (regs.getRegion().getIdRegion() == asignedRegion) {
                if (regs.getStatus().equals((short) 1)) {
                    selectPaises.add(new SelectItem(regs.getIdPais(), regs.getNombre()));
                }
            }
        }

        selectEstadoCiudad = new ArrayList<SelectItem>();
        selectEstadoCiudad.add(localeInfo.createSelectIntKeyFirstItem());
    }

    /**
     * Get all CiudadesEstados from pais selected
     *
     */
    public void getCiudadesEstados() {
        asignedEstadoCiudad = -1;
        selectEstadoCiudad = new ArrayList<SelectItem>();
        selectEstadoCiudad.add(localeInfo.createSelectIntKeyFirstItem());
        for (Ciudad regs : estadoCiudadFacadeRemote.findAll()) {
            if (regs.getPais().getIdPais() == asignedPais) {
                if (regs.getStatus().equals((short) 1)) {
                    selectEstadoCiudad.add(new SelectItem(regs.getIdCiudad(), regs.getNombre()));
                }
            }
        }
    }

    /**
     * Get region of a ciudad
     *
     */
    public Region getRegion(int idCiudad) {
        return regionesFacadeRemote.findByPK(paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(idCiudad).getPais().getIdPais()).getRegion().getIdRegion());
    }

    /**
     * Get pais of a ciudad
     *
     */
    public Pais getPais(int idCiudad) {
        return paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(idCiudad).getPais().getIdPais());
    }

    /**
     * Get ciudad entity
     *
     */
    public Ciudad getCiudad(int idCiudad) {
        return estadoCiudadFacadeRemote.findByPK(idCiudad);
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/estaciones?faces-redirect=true";
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
        String redirect = "/portal/generales/estacionesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Estacion();
        claveAnterior = null;
        version = null;
        asignedEstadoCiudad = -1;
        asignedPais = -1;
        asignedRegion = -1;
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
        if (asignedRegion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Región");
            return false;
        }
        if (asignedPais == -1 || asignedPais == 0) {
            messgeValidateError(boton, "Debe seleccionar un País");
            return false;
        }
        if (asignedEstadoCiudad == -1 || asignedEstadoCiudad == 0) {
            messgeValidateError(boton, "Debe seleccionar una Ciudad o Estado");
            return false;
        }

        try {
            for (Estacion regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getClaveIata().toUpperCase().equals(regs.getClaveIata().toUpperCase())) {
                        messgeValidateError(boton, "La clave IATA ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getClaveIata().toUpperCase().equals(claveAnterior)
                            && row_selected.getClaveIata().toUpperCase().equals(regs.getClaveIata().toUpperCase())) {
                        messgeValidateError(boton, "La clave IATA ya existe");
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
        proveedorMB.init();
        contactoProveedoresMB.init();
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            if (validate("form:saveButton")) {
                String claveUpper = row_selected.getClaveIata().toUpperCase();
                row_selected.setClaveIata(claveUpper);
                row_selected.setStatus((short) 1);
                Ciudad c = new Ciudad();
                c.setIdCiudad(asignedEstadoCiudad);
                row_selected.setCiudad(c);
                servicioFacadeRemote.create(row_selected);
                all_records = null;
                updateDependencies();
                asignedRegion = -1;
                asignedPais = -1;
                selectPaises = null;
                asignedEstadoCiudad = -1;
                selectEstadoCiudad = null;
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                String claveUpper = row_selected.getClaveIata().toUpperCase();
                row_selected.setClaveIata(claveUpper);
                Ciudad c = new Ciudad();
                c.setIdCiudad(asignedEstadoCiudad);
                row_selected.setCiudad(c);
                servicioFacadeRemote.update(row_selected);
                all_records = null;
                updateDependencies();
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Estacion row_selected) {
        String redirect = "/portal/generales/estacionesDetalle?faces-redirect=true";
        modificarRegistro = true;
        asignedRegion = getRegion(row_selected.getCiudad().getIdCiudad()).getIdRegion();
        getPaises();
        asignedPais = getPais(row_selected.getCiudad().getIdCiudad()).getIdPais();
        getCiudadesEstados();
        asignedEstadoCiudad = row_selected.getCiudad().getIdCiudad();
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getClaveIata();
        return redirect;
    }

    public Estacion getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Estacion row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedRegion() {
        return asignedRegion;
    }

    public void setAsignedRegion(Integer asignedRegion) {
        this.asignedRegion = asignedRegion;
    }

    public List<SelectItem> getSelectRegiones() {
        return selectRegiones;
    }

    public void setSelectRegiones(List<SelectItem> selectRegiones) {
        this.selectRegiones = selectRegiones;
    }

    public Integer getAsignedPais() {
        return asignedPais;
    }

    public void setAsignedPais(Integer asignedPais) {
        this.asignedPais = asignedPais;
    }

    public List<SelectItem> getSelectPaises() {
        return selectPaises;
    }

    public void setSelectPaises(List<SelectItem> selectPaises) {
        this.selectPaises = selectPaises;
    }

    public Integer getAsignedEstadoCiudad() {
        return asignedEstadoCiudad;
    }

    public void setAsignedEstadoCiudad(Integer asignedEstadoCiudad) {
        this.asignedEstadoCiudad = asignedEstadoCiudad;
    }

    public List<SelectItem> getSelectEstadoCiudad() {
        return selectEstadoCiudad;
    }

    public void setSelectEstadoCiudad(List<SelectItem> selectEstadoCiudad) {
        this.selectEstadoCiudad = selectEstadoCiudad;
    }

    public List<Estacion> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Estacion> all_records) {
        this.all_records = all_records;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
