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
@Named(value = "estadoCiudadMB")
@SessionScoped
public class EstadoCiudadMB implements Serializable {

    public EstadoCiudadMB() {
    }

    private Ciudad row_selected = null;
    boolean modificarRegistro = false;
    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private Integer asignedPais;
    private List<SelectItem> selectPaises;
    private List<Ciudad> all_records;
    private String estatusAnterior;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;

    @Inject
    @Named("estacionesMB")    
    EstacionesMB estacionesMB;
    
    @Inject
    @Named("localeInfo")        
    LocaleInfo localeInfo;

    /**
     * initial values
     *
     *
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
    public List<Ciudad> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Ciudad>();
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
    }

    /**
     * Get region of a country
     *
     * @param idPais
     * @return
     */
    public Region getRegion(int idPais) {
        return regionesFacadeRemote.findByPK(paisesFacadeRemote.findByPK(idPais).getRegion().getIdRegion());
    }

    /**
     * Get pais entiry
     *
     * @param idPais
     * @return
     */
    public Pais getPais(int idPais) {
        return paisesFacadeRemote.findByPK(idPais);
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/estadoCiudad?faces-redirect=true";
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
        String redirect = "/portal/generales/estadoCiudadDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Ciudad();
        asignedPais = -1;
        asignedRegion = -1;
        estatusAnterior = null;
        return redirect;
    }

    /**
     * validate on update and save
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

        if (boton.equals("form:saveButton")) {
            for (Ciudad ciudad : servicioFacadeRemote.findAll()) {
                if (ciudad.getNombre().equals(this.row_selected.getNombre())
                        && asignedPais == ciudad.getPais().getIdPais()
                        && asignedRegion == ciudad.getPais().getRegion().getIdRegion()) {
                    messgeValidateError(boton, "La ciudad ya existe, para la región y país seleccionado");
                    return false;
                }
            }
        } else {
            for (Ciudad ciudad : servicioFacadeRemote.findAll()) {
                if (ciudad.getNombre().equals(this.row_selected.getNombre())
                        && asignedPais == ciudad.getPais().getIdPais()
                        && asignedRegion == ciudad.getPais().getRegion().getIdRegion()
                        && estatusAnterior.equals(this.row_selected.getStatus() + "")) {
                    messgeValidateError(boton, "La ciudad ya existe, para la región y país seleccionado");
                    return false;
                }
            }
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
                Pais p = new Pais();
                p.setIdPais(asignedPais);
                row_selected.setPais(p);
                servicioFacadeRemote.create(row_selected);
                all_records = null;
//                estacionesMB.init();
                asignedPais = null;
                asignedRegion = null;
                selectPaises.clear();
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
     * @param row_selected
     * @return
     */
    public String modifyRow(Ciudad row_selected) {
        String redirect = "/portal/generales/estadoCiudadDetalle?faces-redirect=true";
        modificarRegistro = true;
        asignedRegion = paisesFacadeRemote.findByPK(row_selected.getPais().getIdPais()).getRegion().getIdRegion();

        selectPaises = new ArrayList<SelectItem>();
        selectPaises.add(localeInfo.createSelectIntKeyFirstItem());
        for (Pais regs : paisesFacadeRemote.findAll()) {
            if (regs.getRegion().getIdRegion() == asignedRegion) {
                selectPaises.add(new SelectItem(regs.getIdPais(), regs.getNombre()));
            }
        }

        asignedPais = row_selected.getPais().getIdPais();
        this.row_selected = row_selected;
        estatusAnterior = this.row_selected.getStatus() + "";

        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                Pais p = new Pais();
                p.setIdPais(asignedPais);
                row_selected.setPais(p);
                servicioFacadeRemote.update(row_selected);
                //update dependencies on cascade
                try {
                    if (row_selected.getStatus() == (short) 0) {
                        for (Estacion estacion : estacionesFacadeRemote.findAll()) {
                            if (estacion.getCiudad().getIdCiudad() == row_selected.getIdCiudad()) {
                                estacion.setStatus((short) 0);
                                estacionesFacadeRemote.update(estacion);

                            }
                        }
                    }
                } catch (Exception ex) {
                }
                all_records = null;
                findAll();
//                estacionesMB.init();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Ciudad getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Ciudad row_selected) {
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

    public List<Ciudad> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Ciudad> all_records) {
        this.all_records = all_records;
    }

    public EstacionesMB getEstacionesMB() {
        return estacionesMB;
    }

    public void setEstacionesMB(EstacionesMB estacionesMB) {
        this.estacionesMB = estacionesMB;
    }

    public String getEstatusAnterior() {
        return estatusAnterior;
    }

    public void setEstatusAnterior(String estatusAnterior) {
        this.estatusAnterior = estatusAnterior;
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
