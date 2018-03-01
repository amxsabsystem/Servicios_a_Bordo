package com.aeromexico.pab.web.jsf.reportes;

import com.aeromexico.pab.backend.remote.ConfiguracionReporteFacadeRemote;
import com.aeromexico.pab.backend.remote.CriterioIrregularidadFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoIrregularidadFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoProductoReporteFacadeRemote;
import com.aeromexico.pab.entity.ConfiguracionReporte;
import com.aeromexico.pab.entity.CriterioIrregularidad;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.TipoIrregularidad;
import com.aeromexico.pab.entity.TipoProductoReporte;
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
@Named(value = "irregularidadesMB")
@SessionScoped
public class IrregularidadesMB implements Serializable {

    public IrregularidadesMB() {
    }

    private ConfiguracionReporte row_selected = null;
    boolean modificarRegistro = false;
    private List<ConfiguracionReporte> all_records;
    private Integer asignedTiposProducto;
    private List<SelectItem> selectTiposProducto;
    private Integer asignedIrregularidades;
    private List<SelectItem> selectIrregularidades;
    private List<String> asignedCriterios;
    private List<SelectItem> selectCriterios;
    private Integer asignedEstatusActive;
    private Integer asignedEstatus;
    private List<SelectItem> selectParametrosEstatus;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/configuracionReporte_RSB")
    ConfiguracionReporteFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/criterioIrregularidad_RSB")
    CriterioIrregularidadFacadeRemote criterioIrregularidadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoIrregularidad_RSB")
    TipoIrregularidadFacadeRemote tipoIrregularidadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoProductoReporte_RSB")
    TipoProductoReporteFacadeRemote tipoProductoReporteFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    @PostConstruct
    public void init() {
        row_selected = new ConfiguracionReporte();
        asignedTiposProducto =-1;
        selectTiposProducto = new ArrayList<SelectItem>();
        selectTiposProducto.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            for (TipoProductoReporte regs : tipoProductoReporteFacadeRemote.findAll()) {
                if (regs.getEstatus().getValorEs().equals("Activo")) {
                    selectTiposProducto.add(new SelectItem(regs.getIdTipoProductoReporte(), regs.getDescripcion()));
                }
            }
        } catch (Exception ex) {
        }
        
        asignedIrregularidades = -1;
        
            selectIrregularidades = new ArrayList<SelectItem>();
            selectIrregularidades.add(localeInfo.createSelectStringKeyFirstItem());
            for (TipoIrregularidad regs : tipoIrregularidadFacadeRemote.findAll()) {
                if (regs.getEstatus().getValorEs().equals("Activo")) {
                    selectIrregularidades.add(new SelectItem(regs.getIdTipoIrregularidad(), regs.getDescripcion()));
                }
            }
        
        
        selectCriterios = new ArrayList<>();
        try {
            final List<CriterioIrregularidad> Criterios = criterioIrregularidadFacadeRemote.findAll();
            for (CriterioIrregularidad regs : Criterios) {
                if (regs.getEstatus().getValorEs().equals("Activo")) {
                selectCriterios.add(new SelectItem(String.valueOf(regs.getIdCriterioIrregularidad()), regs.getDescripcion()));
                }
            }
        } catch (Exception ex) {
        }
        
        
        if (selectParametrosEstatus == null) {
            selectParametrosEstatus = new ArrayList<SelectItem>();
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("estatus")) {
                    if(regs.getValorEn().equals("Active"))
                       asignedEstatusActive =regs.getIdParametro();
                    selectParametrosEstatus.add(new SelectItem(regs.getIdParametro(), 
                        (localeInfo.getLanguage().equals("es")? regs.getValorEs():regs.getValorEn())
                    ));
                }
            }
        }
        
    }
    
    public  List<ConfiguracionReporte> findAllReporte(){
    if (all_records == null) {
            try {
                all_records = new ArrayList<ConfiguracionReporte>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }


    public boolean validate(String boton, String catalogo) {
        return true;
    }
    
    public void getCriterios(){
        asignedCriterios.clear();
    }
    
    public void addCriterio(){
    
    asignedCriterios.clear();
    }
    
    public boolean validate(String boton){
        boolean retorno = true;
        try {
            for (ConfiguracionReporte regs : servicioFacadeRemote.findAll()) {
                
                    if ( asignedTiposProducto == regs.getTipoProductoReporte().getIdTipoProductoReporte() &&
                         asignedIrregularidades == regs.getTipoIrregularidad().getIdTipoIrregularidad() 
                         //asignedCriterios == regs.getCriterioIrregularidad().getIdCriterioIrregularidad()        
                        ) {
                        for (Object rowIDO : asignedCriterios) {
                            Object objectID = new Integer(rowIDO.toString());
                            CriterioIrregularidad reg_found = criterioIrregularidadFacadeRemote.findByPK(objectID);
                            if(reg_found.getIdCriterioIrregularidad() == regs.getCriterioIrregularidad().getIdCriterioIrregularidad() ){
                            messgeValidateError(boton, "La clave ya existe");
                            retorno = false;
                            break;
                            }
                        }
                    }
                
            }
        } catch (Exception ex) {
        }
        return retorno;
    }
    
    
    public void saveReporte() {
        try {
            if (validate("form:saveButton")) {

                List<CriterioIrregularidad> listCriterioIrregularidad_selcted = new ArrayList<CriterioIrregularidad>();

                for (Object rowIDO : asignedCriterios) {
                    Object objectID = new Integer(rowIDO.toString());
                    CriterioIrregularidad reg_found = criterioIrregularidadFacadeRemote.findByPK(objectID);
                    listCriterioIrregularidad_selcted.add(reg_found);
                }
                
                for(CriterioIrregularidad criterio: listCriterioIrregularidad_selcted){
                    row_selected.setStatus(parametroFacadeRemote.findByPK(asignedEstatusActive));
                    row_selected.setCriterioIrregularidad(criterio);
                    row_selected.setTipoIrregularidad(tipoIrregularidadFacadeRemote.findByPK(asignedIrregularidades));
                    row_selected.setTipoProductoReporte(tipoProductoReporteFacadeRemote.findByPK(asignedTiposProducto));
                    servicioFacadeRemote.create(row_selected);
                }
                all_records =null;
                findAllReporte();
                asignedIrregularidades =-1;
                asignedTiposProducto =-1;
                asignedCriterios.clear();
                modificarRegistro = false;
                row_selected = new ConfiguracionReporte();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }    

     public void updateReporte() {
        try {
            if (validate("form:updateButton")) {

                List<CriterioIrregularidad> listCriterioIrregularidad_selcted = new ArrayList<CriterioIrregularidad>();

                for (Object rowIDO : asignedCriterios) {
                    Object objectID = new Integer(rowIDO.toString());
                    CriterioIrregularidad reg_found = criterioIrregularidadFacadeRemote.findByPK(objectID);
                    listCriterioIrregularidad_selcted.add(reg_found);
                }
                
                for(CriterioIrregularidad criterio: listCriterioIrregularidad_selcted){
                    row_selected.setStatus(parametroFacadeRemote.findByPK(asignedEstatusActive));
                    row_selected.setCriterioIrregularidad(criterio);
                    row_selected.setTipoIrregularidad(tipoIrregularidadFacadeRemote.findByPK(asignedIrregularidades));
                    row_selected.setTipoProductoReporte(tipoProductoReporteFacadeRemote.findByPK(asignedTiposProducto));
                    servicioFacadeRemote.update(row_selected);
                }
                all_records =null;
                findAllReporte();
                asignedIrregularidades =-1;
                asignedTiposProducto =-1;
                asignedCriterios.clear();
                modificarRegistro = false;
                row_selected = new ConfiguracionReporte();
                messgeValidateInfo("form:updateButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }   
    
    
 
    
    public String modifyRowReporte(ConfiguracionReporte row_selected){
        String redirect = "/portal/reportes/irregularidades/reporteDeIrregularidades?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        asignedIrregularidades = this.row_selected.getTipoIrregularidad().getIdTipoIrregularidad();
        asignedTiposProducto = this.row_selected.getTipoProductoReporte().getIdTipoProductoReporte();
        asignedCriterios.clear();
        asignedCriterios.add(String.valueOf(this.row_selected.getCriterioIrregularidad().getIdCriterioIrregularidad()));
        asignedEstatus= this.row_selected.getStatus().getIdParametro();
        return redirect;
    }



    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public LocaleInfo getLocaleInfo() {
        return localeInfo;
    }

    public void setLocaleInfo(LocaleInfo localeInfo) {
        this.localeInfo = localeInfo;
    }

    public ConfiguracionReporte getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(ConfiguracionReporte row_selected) {
        this.row_selected = row_selected;
    }

     public List<SelectItem> getSelectTiposProducto() {
        return selectTiposProducto;
    }

    public void setSelectTiposProducto(List<SelectItem> selectTiposProducto) {
        this.selectTiposProducto = selectTiposProducto;
    }

    public Integer getAsignedIrregularidades() {
        return asignedIrregularidades;
    }

    public void setAsignedIrregularidades(Integer asignedIrregularidades) {
        this.asignedIrregularidades = asignedIrregularidades;
    }

    public List<SelectItem> getSelectParametrosEstatus() {
        return selectParametrosEstatus;
    }

    public void setSelectParametrosEstatus(List<SelectItem> selectParametrosEstatus) {
        this.selectParametrosEstatus = selectParametrosEstatus;
    }


    public List<String> getAsignedCriterios() {
        return asignedCriterios;
    }

    public void setAsignedCriterios(List<String> asignedCriterios) {
        this.asignedCriterios = asignedCriterios;
    }

    public List<SelectItem> getSelectCriterios() {
        return selectCriterios;
    }

    public void setSelectCriterios(List<SelectItem> selectCriterios) {
        this.selectCriterios = selectCriterios;
    }

    public Integer getAsignedTiposProducto() {
        return asignedTiposProducto;
    }

    public void setAsignedTiposProducto(Integer asignedTiposProducto) {
        this.asignedTiposProducto = asignedTiposProducto;
    }

    public List<SelectItem> getSelectIrregularidades() {
        return selectIrregularidades;
    }

    public void setSelectIrregularidades(List<SelectItem> selectIrregularidades) {
        this.selectIrregularidades = selectIrregularidades;
    }

    public List<ConfiguracionReporte> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<ConfiguracionReporte> all_records) {
        this.all_records = all_records;
    }

    public Integer getAsignedEstatus() {
        return asignedEstatus;
    }

    public void setAsignedEstatus(Integer asignedEstatus) {
        this.asignedEstatus = asignedEstatus;
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
