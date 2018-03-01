package com.aeromexico.pab.web.jsf.reportes;

import com.aeromexico.pab.backend.remote.CriterioIrregularidadFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.CriterioIrregularidad;
import com.aeromexico.pab.entity.Parametro;
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
@Named(value = "criteriosDeIrregularidadesMB")
@SessionScoped
public class CriteriosDeIrregularidadesMB implements Serializable {
    
    public CriteriosDeIrregularidadesMB(){}
    
    private CriterioIrregularidad row_selected = null;
    private List<CriterioIrregularidad> all_records;
    boolean modificarRegistro = false;
    private Integer asignedEstatusActive;
    private Integer asignedEstatus;
    private List<SelectItem> selectParametrosEstatus;
    private Integer estatusAnterior;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/criterioIrregularidad_RSB")
    CriterioIrregularidadFacadeRemote criterioIrregularidadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    
    @Inject
    @Named("irregularidadesMB")
    IrregularidadesMB irregularidadesMB;

    @PostConstruct
    public void init() {
        if (selectParametrosEstatus == null) {
            selectParametrosEstatus = new ArrayList<SelectItem>();
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("estatus")) {
                    if (regs.getValorEn().equals("Active")) {
                        asignedEstatusActive = regs.getIdParametro();
                    }
                    selectParametrosEstatus.add(new SelectItem(regs.getIdParametro(),
                            (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                    ));
                }
            }
        }
    }

    public List<CriterioIrregularidad> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<CriterioIrregularidad>();
                all_records = criterioIrregularidadFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }
    
    public String returnMaster() {
        String redirect = "/portal/reportes/irregularidades/criteriosDeIrregularidades?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    public String addRow() {
        String redirect = "/portal/reportes/irregularidades/criteriosDeIrregularidadesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new CriterioIrregularidad();
        return redirect;
    }
    
       public boolean validate(String boton) {
        boolean retorno = true;
        for (CriterioIrregularidad tp : criterioIrregularidadFacadeRemote.findAll()) {
            if (row_selected.getDescripcion().equals(tp.getDescripcion()) && boton.equals("form:saveButton")) {
                messgeValidateError(boton, "El tipo de Irregularidad ya existe");
                retorno = false;
                break;
            }
            if (row_selected.getDescripcion().equals(tp.getDescripcion()) && boton.equals("form:updateButton") && estatusAnterior== asignedEstatus) {
                messgeValidateError(boton, "El tipo de Irregularidad ya existe");
                retorno = false;
                break;
            }
        }
        return retorno;
    }    

    public void save() {
        try {
            if (validate("form:saveButton")) {
            row_selected.setEstatus(parametroFacadeRemote.findByPK(asignedEstatusActive));
            criterioIrregularidadFacadeRemote.create(row_selected);
            row_selected = new CriterioIrregularidad();
            irregularidadesMB.init();
            all_records = null;
            findAll();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public String modifyRow(CriterioIrregularidad row_selected) {
        String redirect = "/portal/reportes/irregularidades/criteriosDeIrregularidadesDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        asignedEstatus = this.row_selected.getEstatus().getIdParametro();
         estatusAnterior = this.row_selected.getEstatus().getIdParametro();
        return redirect;
    }

    public void update() {
        try {
            if (validate("form:updateButton")) {
            row_selected.setEstatus(parametroFacadeRemote.findByPK(asignedEstatus));
            criterioIrregularidadFacadeRemote.update(row_selected);
            irregularidadesMB.init();
            all_records = null;
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public CriterioIrregularidad getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(CriterioIrregularidad row_selected) {
        this.row_selected = row_selected;
    }

    public List<CriterioIrregularidad> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<CriterioIrregularidad> all_records) {
        this.all_records = all_records;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedEstatusActive() {
        return asignedEstatusActive;
    }

    public void setAsignedEstatusActive(Integer asignedEstatusActive) {
        this.asignedEstatusActive = asignedEstatusActive;
    }

    public Integer getAsignedEstatus() {
        return asignedEstatus;
    }

    public void setAsignedEstatus(Integer asignedEstatus) {
        this.asignedEstatus = asignedEstatus;
    }

    public List<SelectItem> getSelectParametrosEstatus() {
        return selectParametrosEstatus;
    }

    public void setSelectParametrosEstatus(List<SelectItem> selectParametrosEstatus) {
        this.selectParametrosEstatus = selectParametrosEstatus;
    }

    public Integer getEstatusAnterior() {
        return estatusAnterior;
    }

    public void setEstatusAnterior(Integer estatusAnterior) {
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
