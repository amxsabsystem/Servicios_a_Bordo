package com.aeromexico.pab.web.jsf.sab.planeacion.relacionDeFlota;

import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoEquipoAvionFacadeRemote;
import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.TipoEquipoAvion;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import com.aeromexico.pab.web.jsf.sab.operaciones.GraficosMB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "tipoDeEquipoMB")
@SessionScoped
public class TipoDeEquipoMB implements Serializable {

    private TipoEquipoAvion row_selected = null;
    boolean modificarRegistro = false;
    private List<TipoEquipoAvion> all_records;

    private String totalAviones;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoEquipoAvion_RSB")
    TipoEquipoAvionFacadeRemote servicioFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote modeloFacadeRemote;

    @Inject
    @Named("avionMB")
    AvionMB avionMB;
    
    @Inject
    @Named("graficosMB")
    GraficosMB graficosMB;
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    private Integer asignedModelo;
    private List<SelectItem> selectModelos;

    public List<TipoEquipoAvion> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<TipoEquipoAvion>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public ModeloAvion getModelo(Integer idModelo) {
        return modeloFacadeRemote.findByPK(idModelo);
    }

    @PostConstruct
    public void init() {
        asignedModelo = -1;
        selectModelos = new ArrayList<SelectItem>();
        selectModelos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (ModeloAvion regs : modeloFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectModelos.add(new SelectItem(regs.getIdModeloAvion(), regs.getModelo()));
                }
            }
        } catch (Exception ex) {
        }

    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/tipoDeEquipo?faces-redirect=true";
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
        String redirect = "/portal/sab/planeacion/relacionDeFlota/tipoDeEquipoDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new TipoEquipoAvion();
        totalAviones =null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedModelo == -1) {
            messgeValidateError(boton, "Debe seleccionar un Modelo");
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
                row_selected.setTotalAviones((short) 0);
                ModeloAvion ma = new ModeloAvion();
                ma.setIdModeloAvion(asignedModelo);
                row_selected.setModeloAvion(ma);

                servicioFacadeRemote.create(row_selected);
                avionMB.init();
                graficosMB.init();
                asignedModelo = -1;
                all_records = null;
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
    public String modifyRow(TipoEquipoAvion row_selected) {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/tipoDeEquipoDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        asignedModelo = this.row_selected.getModeloAvion().getIdModeloAvion();
        totalAviones =this.row_selected.getTotalAviones()+"";
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
//row_selected.setTotalAviones(Short.parseShort(totalAviones));
                ModeloAvion ma = new ModeloAvion();
                ma.setIdModeloAvion(asignedModelo);
                row_selected.setModeloAvion(ma);
                servicioFacadeRemote.update(row_selected);
                all_records = null;
                asignedModelo = -1;
                avionMB.init();
                graficosMB.init();
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public TipoEquipoAvion getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(TipoEquipoAvion row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<TipoEquipoAvion> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<TipoEquipoAvion> all_records) {
        this.all_records = all_records;
    }

    public Integer getAsignedModelo() {
        return asignedModelo;
    }

    public void setAsignedModelo(Integer asignedModelo) {
        this.asignedModelo = asignedModelo;
    }

    public List<SelectItem> getSelectModelos() {
        return selectModelos;
    }

    public void setSelectModelos(List<SelectItem> selectModelos) {
        this.selectModelos = selectModelos;
    }

    public String getTotalAviones() {
        return totalAviones;
    }

    public void setTotalAviones(String totalAviones) {
        this.totalAviones = totalAviones;
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
