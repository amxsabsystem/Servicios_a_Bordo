package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.CompaniaFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.VueloFacadeRemote;
import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.Compania;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Vuelo;
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
@Named(value = "vuelosMB")
@SessionScoped
public class VuelosMB implements Serializable {

    public VuelosMB() {
    }

    private Vuelo row_selected = null;
    boolean modificarRegistro = false;

    private Integer asignedCabina;
    private List<SelectItem> selectCabinas;
    private Integer asignedCompania;
    private List<SelectItem> selectCompanias;
    private Integer asignedClase;
    private List<SelectItem> selectClases;
    private List<SelectItem> selectTipoVuelos;
    private Integer asignedTipoVuelo;
    private List<Vuelo> all_records;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/vuelos_RSB")
    VueloFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clases_RSB")
    ClaseFacadeRemote claseFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/compania_RSB")
    CompaniaFacadeRemote companiaFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;

    @Inject
    @Named("localeInfo")    
    LocaleInfo localeInfo;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        asignedCabina = -1;
        asignedCompania = -1;
        asignedClase = -1;
        selectCabinas = new ArrayList<SelectItem>();
        selectCabinas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("cabina")) {
                    selectCabinas.add(new SelectItem(regs.getIdParametro(),
                            (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                    ));
                }
            }
        } catch (Exception ex) {

        }
        asignedTipoVuelo = -1;
        selectTipoVuelos = new ArrayList<SelectItem>();
        selectTipoVuelos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("tipoVuelo")) {
                    selectTipoVuelos.add(new SelectItem(regs.getIdParametro(),
                            (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                    ));
                }
            }
        } catch (Exception ex) {

        }

        selectCompanias = new ArrayList<SelectItem>();
        selectCompanias.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Compania regs : companiaFacadeRemote.findAll()) {
                selectCompanias.add(new SelectItem(regs.getIdCompania(), regs.getNombre()));
            }
        } catch (Exception ex) {

        }
        selectClases = new ArrayList<SelectItem>();
        selectClases.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Clase regs : claseFacadeRemote.findAll()) {
                selectClases.add(new SelectItem(regs.getIdClase(), regs.getNombreEsp()));
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Get all records
     *
     * @return
     */
    public List<Vuelo> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Vuelo>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/vuelos?faces-redirect=true";
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
        String redirect = "/portal/generales/vuelosDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Vuelo();
        asignedCabina = -1;
        asignedClase = -1;
        asignedCompania = -1;
        asignedTipoVuelo = -1;
        return redirect;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            row_selected.setStatus((short) 1);
            row_selected.setCompania(companiaFacadeRemote.findByPK(asignedCompania));
            //row_selected.setTipoVuelo(ParametroFacadeRemote.findByPK(asignedTipoVuelo));
            //row_selected.setClase(claseFacadeRemote.findByPK(asignedClase));
            row_selected.setIdTipoCabina(ParametroFacadeRemote.findByPK(asignedCabina));
            servicioFacadeRemote.create(row_selected);
            addRow();
            asignedCabina = -1;
            asignedClase = -1;
            asignedCompania = -1;
            asignedTipoVuelo = -1;
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
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
    public String modifyRow(Vuelo row_selected) {
        String redirect = "/portal/generales/vuelosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        asignedCabina = this.row_selected.getIdTipoCabina().getIdParametro();
        //asignedTipoVuelo = this.row_selected.getTipoCabina().getIdParametro();
        //asignedClase = this.row_selected.getClase().getIdClase();
        asignedCompania = this.row_selected.getCompania().getIdCompania();
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            row_selected.setCompania(companiaFacadeRemote.findByPK(asignedCompania));
            //row_selected.setTipoVuelo(ParametroFacadeRemote.findByPK(asignedTipoVuelo));
            //row_selected.setClase(claseFacadeRemote.findByPK(asignedClase));
            row_selected.setIdTipoCabina(ParametroFacadeRemote.findByPK(asignedCabina));
            servicioFacadeRemote.update(row_selected);

            all_records = null;
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Vuelo getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Vuelo row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedCabina() {
        return asignedCabina;
    }

    public void setAsignedCabina(Integer asignedCabina) {
        this.asignedCabina = asignedCabina;
    }

    public List<SelectItem> getSelectCabinas() {
        return selectCabinas;
    }

    public void setSelectCabinas(List<SelectItem> selectCabinas) {
        this.selectCabinas = selectCabinas;
    }

    public Integer getAsignedCompania() {
        return asignedCompania;
    }

    public void setAsignedCompania(Integer asignedCompania) {
        this.asignedCompania = asignedCompania;
    }

    public List<SelectItem> getSelectCompanias() {
        return selectCompanias;
    }

    public void setSelectCompanias(List<SelectItem> selectCompanias) {
        this.selectCompanias = selectCompanias;
    }

    public Integer getAsignedClase() {
        return asignedClase;
    }

    public void setAsignedClase(Integer asignedClase) {
        this.asignedClase = asignedClase;
    }

    public List<SelectItem> getSelectClases() {
        return selectClases;
    }

    public void setSelectClases(List<SelectItem> selectClases) {
        this.selectClases = selectClases;
    }

    public List<Vuelo> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Vuelo> all_records) {
        this.all_records = all_records;
    }

    public List<SelectItem> getSelectTipoVuelos() {
        return selectTipoVuelos;
    }

    public void setSelectTipoVuelos(List<SelectItem> selectTipoVuelos) {
        this.selectTipoVuelos = selectTipoVuelos;
    }

    public Integer getAsignedTipoVuelo() {
        return asignedTipoVuelo;
    }

    public void setAsignedTipoVuelo(Integer asignedTipoVuelo) {
        this.asignedTipoVuelo = asignedTipoVuelo;
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
