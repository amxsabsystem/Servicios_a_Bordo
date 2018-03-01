package com.aeromexico.pab.web.jsf.diseno;

import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.CodigoServicioFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.CodigoServicio;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.IOException;
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
@Named(value = "codigoDeServiciosMB")
@SessionScoped
public class CodigoDeServiciosMB implements Serializable {

    public CodigoDeServiciosMB() {
    }

    private CodigoServicio row_selected = null;
    private List<CodigoServicio> all_records;
    private List<CodigoServicio> all_recordsComplementarios;
    boolean modificarRegistro = false;

    private Integer asignedServicioTurista;
    private List<SelectItem> selectServiciosTurista;
    private Integer asignedServicioPremier;
    private List<SelectItem> selectServiciosPremier;

    private List<Integer> asignedTipoTurista;
    private List<SelectItem> selectTiposTurista;
    private List<Integer> asignedTipoPremier;
    private List<SelectItem> selectTiposPremier;

    private Clase clasePremier;
    private Clase claseTurista;
    private String claveAnterior;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/codigoServicio_RSB")
    CodigoServicioFacadeRemote servicioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote claseFacadeRemote;
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    public List<CodigoServicio> findAll(String clase) {
        if (all_records == null) {
            try {
                all_records = new ArrayList<CodigoServicio>();
                for (CodigoServicio cs : servicioFacadeRemote.findAll()) {
                    if (cs.getClase().getClave().equals(clase) && cs.getIdTipoCodigoServicio().getValorEs().equals("Alimentos")) {
                        all_records.add(cs);
                    }
                }
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public List<CodigoServicio> findAllComplementarios(String clase) {
        if (all_recordsComplementarios == null) {
            try {
                all_recordsComplementarios = new ArrayList<CodigoServicio>();
                for (CodigoServicio cs : servicioFacadeRemote.findAll()) {
                    if (cs.getClase().getClave().equals(clase) && cs.getIdTipoCodigoServicio().getValorEs().equals("Complementarios")) {
                        all_recordsComplementarios.add(cs);
                    }
                }
            } catch (Exception ex) {
                all_recordsComplementarios = null;
            }
        }
        return all_recordsComplementarios;
    }

    @PostConstruct
    public void init() {

        selectServiciosTurista = new ArrayList<SelectItem>();
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("tipoCodigoServicio")) {
                    selectServiciosTurista.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }
        selectServiciosPremier = new ArrayList<SelectItem>();
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("tipoCodigoServicio")) {
                    selectServiciosPremier.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }

        selectTiposPremier = new ArrayList<SelectItem>();
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("tipoCiclo")) {
                    selectTiposPremier.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }

        selectTiposTurista = new ArrayList<SelectItem>();
        try {
            for (Parametro regs : ParametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("tipoCiclo")) {
                    selectTiposTurista.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }

        for (Clase clase : claseFacadeRemote.findAll()) {
            if (clase.getClave().equals("CJ")) {
                clasePremier = clase;
            }
            if (clase.getClave().equals("CY")) {
                claseTurista = clase;
            }
        }
    }

    public void checkboxChangedTurista(String clase) {
        try {
            all_recordsComplementarios = null;
            all_records = null;
            findAll(clase);
            findAllComplementarios(clase);
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("./codigoDeServiciosTurista.xhtml");
        } catch (IOException e) {
        }
    }

    public void checkboxChangedPremier(String clase) {
        try {
            all_recordsComplementarios = null;
            all_records = null;
            findAll(clase);
            findAllComplementarios(clase);
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("./codigoDeServicios.xhtml");
        } catch (IOException e) {
        }

    }

    public String returnPremier(String clase) {
        String redirect = "/portal/sab/diseno/codigoDeServicios?faces-redirect=true";
        findAll(clase);
        return redirect;
    }

    public String returnTurista(String clase) {
        String redirect = "/portal/sab/diseno/codigoDeServiciosTurista?faces-redirect=true";
        findAll(clase);
        return redirect;
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster(String clase) {
        String redirect = "/portal/sab/diseno/codigoDeServicioSelect?faces-redirect=true";
        all_recordsComplementarios = null;
        all_records = null;
        findAll(clase);
        findAllComplementarios(clase);
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRowPremier() {
        String redirect = "/portal/sab/diseno/codigoDeServicioDetallePremier?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new CodigoServicio();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRowTurista() {
        String redirect = "/portal/sab/diseno/codigoDeServicioDetalleTurista?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new CodigoServicio();
        return redirect;
    }
    
    public boolean validate(String boton, String clase){
        boolean retorno=false;
        
        
       try {
            for (CodigoServicio regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getCveCodigoServicio().toUpperCase().equals(regs.getCveCodigoServicio().toUpperCase())
                            && clase.equals(regs.getClase().getClave())
                            ) {
                        messgeValidateError(boton, "El código ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getCveCodigoServicio().toUpperCase().equals(claveAnterior)
                            && row_selected.getCveCodigoServicio().toUpperCase().equals(regs.getCveCodigoServicio().toUpperCase())
                            && clase.equals(regs.getClase().getClave())
                            ) {
                        messgeValidateError(boton, "La clave de Area ya existe");
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
     *
     * @param tipo
     */
    public void save(String clase) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            
            if(validate("form:saveButton",clase)){
            row_selected.setStatus((short) 1);
            row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
            row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
            row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
            row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
            row_selected.setIdTipoCiclo(clase.equals("CY") ? ParametroFacadeRemote.findByPK(Integer.parseInt((String) ((Object) asignedTipoTurista.get(0)))) : ParametroFacadeRemote.findByPK(Integer.parseInt((String) ((Object) asignedTipoPremier.get(0)))));
            row_selected.setIdTipoCodigoServicio(clase.equals("CY") ? ParametroFacadeRemote.findByPK(asignedServicioTurista) : ParametroFacadeRemote.findByPK(asignedServicioPremier));
            row_selected.setClase(clase.equals("CY") ? claseTurista : clasePremier);

            servicioFacadeRemote.create(row_selected);
            all_recordsComplementarios = null;
            all_records = null;
            findAll(clase);
            findAllComplementarios(clase);
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }
    
    
    public String modifyRowAlimentos(CodigoServicio row_selected, String clase){
        String redirect = clase.equals("CJ")?"/portal/sab/diseno/codigoDeServicioDetallePremier?faces-redirect=true":"/portal/sab/diseno/codigoDeServicioDetalleTurista?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        if(clase.equals("CJ")){
        asignedServicioPremier = this.row_selected.getIdTipoCodigoServicio().getIdParametro();
        asignedTipoPremier= new ArrayList<Integer>();
        asignedTipoPremier.add(this.row_selected.getIdTipoCiclo().getIdParametro());
        }else{
        asignedServicioTurista = this.row_selected.getIdTipoCodigoServicio().getIdParametro();
        asignedTipoTurista = new ArrayList<Integer>();
        asignedTipoTurista.add(this.row_selected.getIdTipoCiclo().getIdParametro());
        }
        claveAnterior = this.row_selected.getCveCodigoServicio();
        return redirect;
    }
    
    public String modifyRowComplementarios(CodigoServicio row_selected, String clase){
        String redirect = clase.equals("CJ")?"/portal/sab/diseno/codigoDeServicioDetallePremier?faces-redirect=true":"/portal/sab/diseno/codigoDeServicioDetalleTurista?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        if(clase.equals("CJ")){
        asignedServicioPremier = this.row_selected.getIdTipoCodigoServicio().getIdParametro();
        asignedTipoPremier= new ArrayList<Integer>();
        asignedTipoPremier.add(this.row_selected.getIdTipoCiclo().getIdParametro());
        }else{
        asignedServicioTurista = this.row_selected.getIdTipoCodigoServicio().getIdParametro();
        asignedTipoTurista= new ArrayList<Integer>();
        asignedTipoTurista.add(this.row_selected.getIdTipoCiclo().getIdParametro());
        }
        claveAnterior = this.row_selected.getCveCodigoServicio();
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRowTurista(CodigoServicio row_selected) {
        String redirect = "/portal/sab/diseno/codigosDeServiciosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getCveCodigoServicio();
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRowPremier(CodigoServicio row_selected) {
        String redirect = "/portal/sab/diseno/codigosDeServiciosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getCveCodigoServicio();
        return redirect;
    }

    /**
     * Update selected record
     *
     * @param tipo
     */
    public void update(String clase) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
            row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
            row_selected.setIdTipoCiclo(clase.equals("CY") ? ParametroFacadeRemote.findByPK(Integer.parseInt((String) ((Object) asignedTipoTurista.get(0)))) : ParametroFacadeRemote.findByPK(Integer.parseInt((String) ((Object) asignedTipoPremier.get(0)))));
            row_selected.setIdTipoCodigoServicio(clase.equals("CY") ? ParametroFacadeRemote.findByPK(asignedServicioTurista) : ParametroFacadeRemote.findByPK(asignedServicioPremier));
            row_selected.setClase(clase.equals("CY") ? claseTurista : clasePremier);
            servicioFacadeRemote.update(row_selected);
            all_records = null;
            all_recordsComplementarios = null;
            findAll(clase);
            findAllComplementarios(clase);
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public CodigoServicio getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(CodigoServicio row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedServicioTurista() {
        return asignedServicioTurista;
    }

    public void setAsignedServicioTurista(Integer asignedServicioTurista) {
        this.asignedServicioTurista = asignedServicioTurista;
    }

    public Integer getAsignedServicioPremier() {
        return asignedServicioPremier;
    }

    public void setAsignedServicioPremier(Integer asignedServicioPremier) {
        this.asignedServicioPremier = asignedServicioPremier;
    }

    public List<Integer> getAsignedTipoTurista() {
        return asignedTipoTurista;
    }

    public void setAsignedTipoTurista(List<Integer> asignedTipoTurista) {
        this.asignedTipoTurista = asignedTipoTurista;
    }

    public List<SelectItem> getSelectTiposTurista() {
        return selectTiposTurista;
    }

    public void setSelectTiposTurista(List<SelectItem> selectTiposTurista) {
        this.selectTiposTurista = selectTiposTurista;
    }

    public List<Integer> getAsignedTipoPremier() {
        return asignedTipoPremier;
    }

    public void setAsignedTipoPremier(List<Integer> asignedTipoPremier) {
        this.asignedTipoPremier = asignedTipoPremier;
    }

    public List<SelectItem> getSelectTiposPremier() {
        return selectTiposPremier;
    }

    public void setSelectTiposPremier(List<SelectItem> selectTiposPremier) {
        this.selectTiposPremier = selectTiposPremier;
    }

    public List<SelectItem> getSelectServiciosTurista() {
        return selectServiciosTurista;
    }

    public void setSelectServiciosTurista(List<SelectItem> selectServiciosTurista) {
        this.selectServiciosTurista = selectServiciosTurista;
    }

    public List<SelectItem> getSelectServiciosPremier() {
        return selectServiciosPremier;
    }

    public void setSelectServiciosPremier(List<SelectItem> selectServiciosPremier) {
        this.selectServiciosPremier = selectServiciosPremier;
    }

    public List<CodigoServicio> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<CodigoServicio> all_records) {
        this.all_records = all_records;
    }

    public List<CodigoServicio> getAll_recordsComplementarios() {
        return all_recordsComplementarios;
    }

    public void setAll_recordsComplementarios(List<CodigoServicio> all_recordsComplementarios) {
        this.all_recordsComplementarios = all_recordsComplementarios;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
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
