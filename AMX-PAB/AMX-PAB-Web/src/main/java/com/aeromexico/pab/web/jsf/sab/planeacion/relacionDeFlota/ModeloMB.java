package com.aeromexico.pab.web.jsf.sab.planeacion.relacionDeFlota;

import com.aeromexico.pab.backend.remote.CompaniaFacadeRemote;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.Compania;
import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.web.jsf.LocaleInfo;
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
@Named(value = "modeloMB")
@SessionScoped
public class ModeloMB implements Serializable {

    public ModeloMB() {
    }

    private ModeloAvion row_selected = null;
    boolean modificarRegistro = false;
    private List<ModeloAvion> all_records;
    private Integer asignedCompania;
    private List<SelectItem> selectCompanias;
    private Integer asignedFabricante;
    private List<SelectItem> selectFabricantes;
    private Integer asignedCabina;
    private List<SelectItem> selectCabinas;
    private String descripcionAnterior;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote servicioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/compania_RSB")
    CompaniaFacadeRemote companiaFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    
    @Inject
    @Named("tipoDeEquipoMB")
    TipoDeEquipoMB tipoDeEquipoMB;
    
    @Inject
    @Named("avionMB")
    AvionMB avionMB;

    public List<ModeloAvion> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<ModeloAvion>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    @PostConstruct
    public void init() {
        asignedCompania = -1;
        asignedFabricante = -1;
        asignedCabina = -1;

        selectCompanias = new ArrayList<SelectItem>();
        selectCompanias.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Compania regs : companiaFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectCompanias.add(new SelectItem(regs.getIdCompania(), regs.getNombre()));
                }
            }
        } catch (Exception ex) {
        }
        selectFabricantes = new ArrayList<SelectItem>();
        selectFabricantes.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1) && regs.getClave().equals("fabricante")) {
                    selectFabricantes.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }
        selectCabinas = new ArrayList<SelectItem>();
        selectCabinas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1) && regs.getClave().equals("tipoCabina")) {
                    selectCabinas.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }
    }

    public Compania getCompania(int idCompania) {
        return companiaFacadeRemote.findByPK(idCompania);
    }

    public String getFabricante(int idparametro) {
        return ParametroFacadeRemote.findByPK(idparametro).getValorEs();//localeInfo.getLanguage().equals("es")?ParametroFacadeRemote.findByPK(idparametro).getValorEs():ParametroFacadeRemote.findByPK(idparametro).getValorEn();
    }

    public String getCabina(int idparametro) {
        return ParametroFacadeRemote.findByPK(idparametro).getValorEs();//localeInfo.getLanguage().equals("es")?ParametroFacadeRemote.findByPK(idparametro).getValorEs():ParametroFacadeRemote.findByPK(idparametro).getValorEn();
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/modelo?faces-redirect=true";
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
        String redirect = "/portal/sab/planeacion/relacionDeFlota/modeloDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new ModeloAvion();
        asignedCabina = -1;
        asignedCompania = -1;
        asignedFabricante = -1;
        descripcionAnterior = null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedCabina == -1) {
            messgeValidateError(boton, "Debe seleccionar una Cabina");
            return false;
        }
        if (asignedFabricante == -1) {
            messgeValidateError(boton, "Debe seleccionar un Fabricante");
            return false;
        }
        if (asignedCompania == -1) {
            messgeValidateError(boton, "Debe seleccionar una Compania");
            return false;
        }

        try {
            for (ModeloAvion regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getDescripcion().toUpperCase().equals(regs.getDescripcion().toUpperCase())
                            && asignedCompania.equals(regs.getCompania().getIdCompania())
                            && asignedFabricante == (regs.getIdFabricante().getIdParametro())
                            && asignedCabina == (regs.getIdTipoCabina().getIdParametro())) {
                        messgeValidateError(boton, "El Modelo de Avión ya existe.");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getDescripcion().toUpperCase().equals(descripcionAnterior.toUpperCase())
                            && row_selected.getCompania().getCveCompania().equals(regs.getCompania().getCveCompania().toUpperCase())
                            && row_selected.getIdFabricante().getIdParametro() == (regs.getIdFabricante().getIdParametro())
                            && row_selected.getIdTipoCabina().getIdParametro() == (regs.getIdTipoCabina().getIdParametro())
                            && row_selected.getDescripcion().toUpperCase().equals(regs.getDescripcion().toUpperCase())) {
                        messgeValidateError(boton, "El Modelo de Avión ya existe.");
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
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            if (validate("form:saveButton")) {
                row_selected.setStatus((short) 1);
                row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
                row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));

                Compania ac = new Compania();
                ac.setIdCompania(asignedCompania);
                row_selected.setCompania(ac);

                Parametro af = new Parametro();
                af.setIdParametro(asignedFabricante);
                row_selected.setIdFabricante(af);

                Parametro ab = new Parametro();
                ab.setIdParametro(asignedCabina);
                row_selected.setIdTipoCabina(ab);

                servicioFacadeRemote.create(row_selected);
                tipoDeEquipoMB.init();
                avionMB.init();
                asignedCabina = -1;
                asignedCompania = -1;
                asignedFabricante = -1;
                descripcionAnterior = null;

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
    public String modifyRow(ModeloAvion row_selected) {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/modeloDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        asignedCabina = this.row_selected.getIdTipoCabina().getIdParametro();
        asignedCompania = this.row_selected.getCompania().getIdCompania();
        asignedFabricante = this.row_selected.getIdFabricante().getIdParametro();
        descripcionAnterior = this.row_selected.getDescripcion();
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
            if (validate("form:updateButton")) {
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));

                Compania ac = new Compania();
                ac.setIdCompania(asignedCompania);
                row_selected.setCompania(ac);

                Parametro af = new Parametro();
                af.setIdParametro(asignedFabricante);
                row_selected.setIdFabricante(af);

                Parametro ab = new Parametro();
                ab.setIdParametro(asignedCabina);
                row_selected.setIdTipoCabina(ab);

                servicioFacadeRemote.update(row_selected);
                tipoDeEquipoMB.init();

                asignedCabina = -1;
                asignedCompania = -1;
                asignedFabricante = -1;

                all_records = null;
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public ModeloAvion getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(ModeloAvion row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<ModeloAvion> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<ModeloAvion> all_records) {
        this.all_records = all_records;
    }

    public LocaleInfo getLocaleInfo() {
        return localeInfo;
    }

    public void setLocaleInfo(LocaleInfo localeInfo) {
        this.localeInfo = localeInfo;
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

    public Integer getAsignedFabricante() {
        return asignedFabricante;
    }

    public void setAsignedFabricante(Integer asignedFabricante) {
        this.asignedFabricante = asignedFabricante;
    }

    public List<SelectItem> getSelectFabricantes() {
        return selectFabricantes;
    }

    public void setSelectFabricantes(List<SelectItem> selectFabricantes) {
        this.selectFabricantes = selectFabricantes;
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

    public String getDescripcionAnterior() {
        return descripcionAnterior;
    }

    public void setDescripcionAnterior(String descripcionAnterior) {
        this.descripcionAnterior = descripcionAnterior;
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
