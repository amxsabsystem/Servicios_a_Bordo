package com.aeromexico.pab.web.jsf.sab.planeacion.relacionDeFlota;

import com.aeromexico.pab.web.dto.MaterialEquipamientoComisariato;
import com.aeromexico.pab.backend.remote.AvionAudifonoFacadeRemote;
import com.aeromexico.pab.backend.remote.AvionCapacidadFacadeRemote;
import com.aeromexico.pab.backend.remote.AvionFacadeRemote;
import com.aeromexico.pab.backend.remote.AvionSistemaEntretenimientoFacadeRemote;
import com.aeromexico.pab.backend.remote.CategoriaMaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.EquipamientoFijoFacadeRemote;
import com.aeromexico.pab.backend.remote.EquipamientoSemifijoFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.SistemaEntretenimientoFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoEquipoAvionFacadeRemote;
import com.aeromexico.pab.entity.Avion;
import com.aeromexico.pab.entity.AvionAudifono;
import com.aeromexico.pab.entity.AvionCapacidad;
import com.aeromexico.pab.entity.AvionCapacidadPK;
import com.aeromexico.pab.entity.AvionSistemaEntretenimiento;
import com.aeromexico.pab.entity.AvionSistemaEntretenimientoPK;
import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.Compania;
import com.aeromexico.pab.entity.EquipamientoFijo;
import com.aeromexico.pab.entity.EquipamientoSemifijo;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.SistemaEntretenimiento;
import com.aeromexico.pab.entity.TipoEquipoAvion;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "avionMB")
@SessionScoped
public class AvionMB implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(AvionMB.class.getName());

    public AvionMB() {
    }

    private Avion row_selected = null;
    boolean modificarRegistro = false;
    private List<Avion> all_records;
    private Integer asignedModelo;
    private List<SelectItem> selectModelos;
    private Integer asignedTipoEquipo;
    private List<SelectItem> selectTipoEquipos;

    private String labelCompania = null;
    private String cantidadPremier;
    private String cantidadTurista;
    private boolean isTuristaAMPlus;
    private Clase clasePremier;
    private Clase claseTurista;

   // private boolean istcWIFI;
   // private boolean istcGOGO2k;
   // private boolean istcNA;
    private Parametro conectividadWIFI;
    private Parametro conectividadGOGO2k;
    private Parametro conectividadNA;

    private String asignedAudifonoPremier;
    private String asignedAudifonoTurista;
    private List<SelectItem> selectAudifonos;
    private Integer asignedSistemaEntretenimiento;
    private List<SelectItem> selectSistemaEntretenimiento;
    private Integer selectedSistemaEntretenimiento;
    private List<SelectItem> selectedSistemasEntretenimiento;

    private String asignedMaterial;
    private List<SelectItem> selectMateriales;

    private String peso;
    private String cantidad;
    private String descripcionEs;
    private String descripcionEn;
    private String observaciones;
    private String descripcionEsEF;
    private String descripcionEnEF;
    private String observacionesEF;
    
    private Integer asignedConect;
    private List<SelectItem> selectConectividades;

    private List<MaterialEquipamientoComisariato> all_records_EquipamientoComisariato;
    private List<MaterialEquipamientoComisariato> all_records_EquipamientoComisariatoEF;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/avion_RSB")
    AvionFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote modeloAvionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoEquipoAvion_RSB")
    TipoEquipoAvionFacadeRemote tipoEquipoAvionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote claseFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/avionCapacidad_RSB")
    AvionCapacidadFacadeRemote avionCapacidadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/avionAudifono_RSB")
    AvionAudifonoFacadeRemote avionAudifonoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/avionSistemaEntretenimiento_RSB")
    AvionSistemaEntretenimientoFacadeRemote avionSistemaEntretenimientoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/sistemaEntretenimiento_RSB")
    SistemaEntretenimientoFacadeRemote sistemaEntretenimientoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    MaterialFacadeRemote materialFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/categoriaMaterial_RSB")
    CategoriaMaterialFacadeRemote categoriaMaterialFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/equipamientoSemifijo_RSB")
    EquipamientoSemifijoFacadeRemote equipamientoSemifijoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/equipamientoFijo_RSB")
    EquipamientoFijoFacadeRemote equipamientoFijoFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    public List<Avion> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Avion>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    @PostConstruct
    public void init() {
        all_records_EquipamientoComisariato = new ArrayList<MaterialEquipamientoComisariato>();
        all_records_EquipamientoComisariatoEF = new ArrayList<MaterialEquipamientoComisariato>();
        selectedSistemasEntretenimiento = new ArrayList<SelectItem>();

        asignedMaterial = "-1";
        selectMateriales = new ArrayList<SelectItem>();
        selectMateriales.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            for (Material regs : materialFacadeRemote.findAll()) {
                selectMateriales.add(new SelectItem(regs.getNumeroParte(), regs.getNumeroParte() + " - "
                        + (localeInfo.getLanguage().equals("es") ? regs.getDescripcionEs() : regs.getDescripcionEn())
                ));
            }
        } catch (Exception ex) {
        }

        asignedSistemaEntretenimiento = -1;
        selectSistemaEntretenimiento = new ArrayList<SelectItem>();
        selectSistemaEntretenimiento.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (SistemaEntretenimiento regs : sistemaEntretenimientoFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectSistemaEntretenimiento.add(new SelectItem(regs.getIdSistemaEntretenimiento(), regs.getTipo() + "-" + regs.getVersion()));
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
        
        
        asignedConect=-1;
        selectConectividades = new ArrayList<SelectItem>();
        //selectConectividades.add(localeInfo.createSelectIntKeyFirstItem());
        //selectConectividades.add(new SelectItem(-1, "GGO2K"));
        for (Parametro param : ParametroFacadeRemote.findAll()) {
            if (param.getClave().equals("tipoConectividad") && param.getStatus().equals((short) 1)) {
                if (param.getValorEn().equals("WIFI")) {
                conectividadWIFI = param;
                }
                //if(param.getClave().equals("conec") && param.getValorEn().equals("GGO2K")) conectividadGOGO2k =param;
                if (param.getValorEn().equals("NA")) {
                conectividadNA = param; 
                }
                selectConectividades.add(new SelectItem(param.getIdParametro(),param.getValorEs() ));
            }
        }
        conectividadGOGO2k = null;

        asignedAudifonoPremier = "-1";
        asignedAudifonoTurista = "-1";
        selectAudifonos = new ArrayList<SelectItem>();
        selectAudifonos.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            for (Material regs : materialFacadeRemote.findAll()) {
                //logger.info("cat:" + regs.getCategoriaMaterial().getNombreEn());
                if (regs.getCategoriaMaterial().getNombreEn().equals("HEADPHONES") && regs.getStatus().equals((short) 1)) {
                    selectAudifonos.add(new SelectItem(regs.getNumeroParte(),
                            (localeInfo.getLanguage().equals("es") ? "[" + regs.getNumeroParte() + "] " + regs.getDescripcionEs() : "[" + regs.getNumeroParte() + "] " + regs.getDescripcionEn())
                    ));
                }
            }
        } catch (Exception ex) {
        }

        isTuristaAMPlus = false;
        asignedModelo = -1;
    }

    public void agregarSistema() {
        boolean found = false;
        for (SelectItem item : selectedSistemasEntretenimiento) {
            if ((Integer) (item.getValue()) == asignedSistemaEntretenimiento) {
                found = true;
                break;
            }
        }
        if (!found && asignedSistemaEntretenimiento != -1) {
            SistemaEntretenimiento sist = sistemaEntretenimientoFacadeRemote.findByPK(asignedSistemaEntretenimiento);
            selectedSistemasEntretenimiento.add(new SelectItem(sist.getIdSistemaEntretenimiento(), sist.getTipo() + "-" + sist.getVersion()));
        }
    }

    public void quitarSistema() {
        boolean found = false;
        List<SelectItem> nuevaLista = new ArrayList<SelectItem>();
        for (SelectItem item : selectedSistemasEntretenimiento) {
            if ((Integer) (item.getValue()) != selectedSistemaEntretenimiento) {
                nuevaLista.add(item);
            }
        }
        selectedSistemasEntretenimiento.clear();
        selectedSistemasEntretenimiento = nuevaLista;
    }

    public void updateLabels() {
        Material mat = materialFacadeRemote.findByPK(asignedMaterial);
        if (mat != null) {
            descripcionEs = mat.getDescripcionEs();
            descripcionEn = mat.getDescripcionEn();
            peso = mat.getPeso() + "";

        } else {
            descripcionEs = null;
            descripcionEn = null;
            peso = null;

        }
    }

    public boolean validateMaterial() {
        boolean retorno = true;
        if (asignedMaterial.equals("-1")) {
            messgeValidateError("form:addButtonTable", "Debe seleccionar un Material");
            return false;
        }
        if (peso == null) {
            messgeValidateError("form:addButtonTable", "Debe ingresar el peso del Material");
            return false;
        }
        if (cantidad == null) {
            messgeValidateError("form:addButtonTable", "Debe ingresar la cantidad del Material");
            return false;
        }

        if (all_records_EquipamientoComisariato != null && !all_records_EquipamientoComisariato.isEmpty()) {
            for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariato) {
                if (material.getNumeroParte().equals(asignedMaterial)) {
                    messgeValidateError("form:addButtonTableEF", "El equipamiento ya existe");
                    return false;
                }
            }
        }
        return retorno;
    }

    public void agregarMaterial() {
        if (validateMaterial()) {
            MaterialEquipamientoComisariato materialEqp = new MaterialEquipamientoComisariato();
            materialEqp.setCantidad(Integer.parseInt(cantidad));
            materialEqp.setPeso(Double.parseDouble(peso));
            Material mat = materialFacadeRemote.findByPK(asignedMaterial);
            materialEqp.setNombreEn(mat.getDescripcionEn());
            materialEqp.setNombreEs(mat.getDescripcionEs());
            materialEqp.setNumeroParte(asignedMaterial);
            materialEqp.setObservaciones(observaciones);
            all_records_EquipamientoComisariato.add(materialEqp);

            descripcionEs = null;
            descripcionEn = null;
            peso = null;
            cantidad = null;
            asignedMaterial = "-1";
            observaciones = null;
        }
    }

    public boolean validateMaterialEF() {
        boolean retorno = true;
        if (descripcionEsEF == null) {
            messgeValidateError("form:addButtonTableEF", "Debe ingresar una Descripción en Español");
            return false;
        }
        if (descripcionEnEF == null) {
            messgeValidateError("form:addButtonTableEF", "Debe ingresar una Descripción en Ingles");
            return false;
        }

        if (all_records_EquipamientoComisariatoEF != null && !all_records_EquipamientoComisariatoEF.isEmpty()) {
            for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariatoEF) {
                if (material.getNombreEs().equals(descripcionEsEF)) {
                    messgeValidateError("form:addButtonTableEF", "El equipamiento ya existe");
                    return false;
                }
            }
        }
        return retorno;
    }

    public void agregarMaterialEF() {
        if (validateMaterialEF()) {
            MaterialEquipamientoComisariato materialEqp = new MaterialEquipamientoComisariato();
            materialEqp.setNombreEn(descripcionEnEF);
            materialEqp.setNombreEs(descripcionEsEF);
            materialEqp.setObservaciones(observacionesEF);
            all_records_EquipamientoComisariatoEF.add(materialEqp);
            descripcionEsEF = null;
            descripcionEnEF = null;
            observacionesEF = null;
        }
    }

    public void quitarMaterial(String noParte, String nombreEs) {
        List<MaterialEquipamientoComisariato> nuevaLista = new ArrayList<MaterialEquipamientoComisariato>();
        for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariato) {
            if (material.getNumeroParte() != null && !material.getNumeroParte().equals(noParte)) {
                nuevaLista.add(material);
            } else if (noParte == null && !nombreEs.equals(material.getNombreEs())) {
                nuevaLista.add(material);
            }
        }
        all_records_EquipamientoComisariato.clear();
        all_records_EquipamientoComisariato = nuevaLista;
    }

    public void quitarMaterialEF(String nombreEs) {
        List<MaterialEquipamientoComisariato> nuevaLista = new ArrayList<MaterialEquipamientoComisariato>();
        for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariatoEF) {
            if (!material.getNombreEs().equals(nombreEs)) {
                nuevaLista.add(material);
            }
        }
        all_records_EquipamientoComisariatoEF.clear();
        all_records_EquipamientoComisariatoEF = nuevaLista;
        descripcionEsEF =null;
        descripcionEnEF =null;
        observacionesEF = null;
    }

    public void getTiposDeEquipo() {
        asignedTipoEquipo = -1;
        selectTipoEquipos = new ArrayList<SelectItem>();
        selectTipoEquipos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (TipoEquipoAvion regs : tipoEquipoAvionFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1) && regs.getModeloAvion().getIdModeloAvion() == asignedModelo) {
                    selectTipoEquipos.add(new SelectItem(regs.getIdTipoEquipoAvion(), regs.getTipoEquipo()));
                }
            }
        } catch (Exception ex) {
        }
        labelCompania = modeloAvionFacadeRemote.findByPK(asignedModelo).getCompania().getCveCompania();
    }

    public Compania getCompania(Integer idTipoEquipoAvion) {
        TipoEquipoAvion te = tipoEquipoAvionFacadeRemote.findByPK(idTipoEquipoAvion);
        return modeloAvionFacadeRemote.findByPK(te.getModeloAvion().getIdModeloAvion()).getCompania();
    }

    public ModeloAvion getModelo(Integer idTipoEquipoAvion) {
        return tipoEquipoAvionFacadeRemote.findByPK(idTipoEquipoAvion).getModeloAvion();
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/avion?faces-redirect=true";
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
        String redirect = "/portal/sab/planeacion/relacionDeFlota/avionDetalle?faces-redirect=true";
        modificarRegistro = false;

        selectModelos = new ArrayList<SelectItem>();
        selectModelos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (ModeloAvion regs : modeloAvionFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectModelos.add(new SelectItem(regs.getIdModeloAvion(), regs.getModelo()));
                }
            }
        } catch (Exception ex) {
        }
        row_selected = new Avion();
        setnulls();
        return redirect;
    }

    public void setnulls() {

        descripcionEs = null;
        descripcionEn = null;
        descripcionEsEF = null;
        descripcionEnEF = null;
        peso = null;
        cantidad = null;
        asignedMaterial = "-1";
        cantidadPremier = null;
        cantidadTurista = null;
        isTuristaAMPlus = false;
       // istcWIFI = false;
       // istcGOGO2k = false;
       // istcNA = false;
        asignedConect=null;
        all_records_EquipamientoComisariato.clear();
        all_records_EquipamientoComisariatoEF.clear();
        asignedModelo = -1;
        asignedTipoEquipo = -1;
        asignedSistemaEntretenimiento = -1;
        selectedSistemasEntretenimiento.clear();
        labelCompania = null;
        observaciones = null;
        observacionesEF = null;
        asignedAudifonoPremier = "-1";
        asignedAudifonoTurista = "-1";
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedTipoEquipo == -1) {
            messgeValidateError(boton, "Debe seleccionar un Equipo");
            return false;
        }
        //if (!istcWIFI && !istcGOGO2k && !istcNA) {
        if( asignedConect ==null){    
            messgeValidateError(boton, "Debe seleccionar una Conectividad");
            return false;
        }
        if (clasePremier == null) {
            messgeValidateError(boton, "No existe la clase premier enel Catálogo de clases");
            return false;
        }
        if (claseTurista == null) {
            messgeValidateError(boton, "No existe la clase turista enel Catálogo de clases");
            return false;
        }
/*
        if (asignedAudifonoPremier.equals("-1")) {
            messgeValidateError(boton, "Debe seleccionar un tipo de Audífono para clase Premier");
            return false;
        }
        if (asignedAudifonoTurista.equals("-1")) {
            messgeValidateError(boton, "Debe seleccionar un tipo de Audífono para clase Turista");
            return false;
        }
*/
        if (selectedSistemasEntretenimiento == null || (selectedSistemasEntretenimiento != null && selectedSistemasEntretenimiento.isEmpty())) {
            messgeValidateError(boton, "Debe agregar un Sistema de Entretenimiento");
            return false;
        }

        if (all_records_EquipamientoComisariato == null || (all_records_EquipamientoComisariato != null && all_records_EquipamientoComisariato.isEmpty())) {
            messgeValidateError(boton, "Debe agregar un Equipamiento Semi Fijo");
            return false;
        }

        if (all_records_EquipamientoComisariatoEF == null || (all_records_EquipamientoComisariatoEF != null && all_records_EquipamientoComisariatoEF.isEmpty())) {
            messgeValidateError(boton, "Debe agregar un Equipamiento Fijo");
            return false;
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
                //Avion
                row_selected.setStatus((short) 1);
                row_selected.setTipoEquipoAvion(tipoEquipoAvionFacadeRemote.findByPK(asignedTipoEquipo));
                row_selected.setIdConectividad(ParametroFacadeRemote.findByPK(asignedConect));//istcWIFI ? conectividadWIFI : istcGOGO2k ? conectividadGOGO2k : conectividadNA);
                Avion avion = servicioFacadeRemote.create(row_selected);

                if(cantidadPremier!=null && !cantidadPremier.equals("")){
                AvionCapacidadPK acpkP = new AvionCapacidadPK();
                acpkP.setIdAvion(avion.getIdAvion());
                acpkP.setIdClase(clasePremier.getIdClase());
                AvionCapacidad capacidadP = new AvionCapacidad();
                capacidadP.setClase(clasePremier);
                capacidadP.setCapacidad(Integer.parseInt(cantidadPremier));
                capacidadP.setStatus((short) 1);
                capacidadP.setAvionCapacidadPK(acpkP);
                avionCapacidadFacadeRemote.create(capacidadP);
                }
                if(cantidadTurista!=null && !cantidadTurista.equals("")){
                AvionCapacidadPK acpkT = new AvionCapacidadPK();
                acpkT.setIdAvion(avion.getIdAvion());
                acpkT.setIdClase(claseTurista.getIdClase());
                AvionCapacidad capacidadT = new AvionCapacidad();
                capacidadT.setAvionCapacidadPK(acpkT);
                capacidadT.setClase(claseTurista);
                capacidadT.setCapacidad(Integer.parseInt(cantidadTurista));
                capacidadT.setStatus((short) 1);
                avionCapacidadFacadeRemote.create(capacidadT);
                }

                if(!asignedAudifonoPremier.equals("-1")){
                AvionAudifono audifonoP = new AvionAudifono();
                audifonoP.setMaterial(materialFacadeRemote.findByPK(asignedAudifonoPremier));
                audifonoP.setAvion(avion);
                audifonoP.setClase(clasePremier);
                audifonoP.setStatus((short) 1);
                avionAudifonoFacadeRemote.create(audifonoP);
                }
                if(!asignedAudifonoTurista.equals("-1")){
                AvionAudifono audifonoT = new AvionAudifono();
                audifonoT.setMaterial(materialFacadeRemote.findByPK(asignedAudifonoTurista));
                audifonoT.setAvion(avion);
                audifonoT.setClase(claseTurista);
                audifonoT.setStatus((short) 1);
                avionAudifonoFacadeRemote.create(audifonoT);
                }

                for (SelectItem item : selectedSistemasEntretenimiento) {
                    SistemaEntretenimiento sistema = sistemaEntretenimientoFacadeRemote.findByPK((Integer) item.getValue());
                    AvionSistemaEntretenimientoPK asePK = new AvionSistemaEntretenimientoPK();
                    asePK.setIdAvion(avion.getIdAvion());
                    asePK.setIdSistemaEntretenimiento(sistema.getIdSistemaEntretenimiento());
                    AvionSistemaEntretenimiento sistEntretenimiento = new AvionSistemaEntretenimiento();
                    sistEntretenimiento.setAvionSistemaEntretenimientoPK(asePK);
                    sistEntretenimiento.setStatus((short) 1);
                    sistEntretenimiento.setSistemaEntretenimiento(sistema);
                    sistEntretenimiento.setUsuarioCreo(request.getUserPrincipal().getName());
            sistEntretenimiento.setFechaCreo(new java.sql.Timestamp(now.getTime()));
            sistEntretenimiento.setUsuarioModifico(request.getUserPrincipal().getName());
            sistEntretenimiento.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    avionSistemaEntretenimientoFacadeRemote.create(sistEntretenimiento);
                }

                for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariato) {

                    EquipamientoSemifijo equipamiento = new EquipamientoSemifijo();
                    equipamiento.setAvion(avion);
                    equipamiento.setCantidad(material.getCantidad());
                    equipamiento.setMaterial(materialFacadeRemote.findByPK(material.getNumeroParte()));
                    equipamiento.setObservaciones(material.getObservaciones());
                    equipamiento.setPeso(material.getPeso());
                    equipamiento.setCantidad(material.getCantidad());
                    equipamiento.setStatus((short) 1);
                    equipamientoSemifijoFacadeRemote.create(equipamiento);

                }

                for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariatoEF) {

                    EquipamientoFijo equipamiento = new EquipamientoFijo();
                    equipamiento.setAvion(avion);
                    equipamiento.setNombreEn(material.getNombreEn());
                    equipamiento.setNombreEs(material.getNombreEs());
                    equipamiento.setObservaciones(material.getObservaciones());
                    equipamiento.setStatus((short) 1);
                    equipamientoFijoFacadeRemote.create(equipamiento);

                }

            }

            addRow();
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
    public String modifyRow(Avion row_selected) {
        String redirect = "/portal/sab/planeacion/relacionDeFlota/avionDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        selectModelos = new ArrayList<SelectItem>();
        selectModelos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (ModeloAvion regs : modeloAvionFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectModelos.add(new SelectItem(regs.getIdModeloAvion(), regs.getModelo()));
                }
            }
        } catch (Exception ex) {
        }

        
asignedTipoEquipo = this.row_selected.getTipoEquipoAvion().getIdTipoEquipoAvion();
        ModeloAvion modelo = tipoEquipoAvionFacadeRemote.findByPK(asignedTipoEquipo).getModeloAvion();
        asignedModelo = modelo.getIdModeloAvion();
        getTiposDeEquipo();
        asignedTipoEquipo = this.row_selected.getTipoEquipoAvion().getIdTipoEquipoAvion();
        labelCompania = modeloAvionFacadeRemote.findByPK(asignedModelo).getCompania().getCveCompania();

        try {
            for (AvionCapacidad ac : avionCapacidadFacadeRemote.findAll()) {

                if (ac.getAvion().getIdAvion() == this.row_selected.getIdAvion() && ac.getClase().getClave().equals("CJ")) {
                    cantidadPremier = ac.getCapacidad() + "";
                }
                if (ac.getAvion().getIdAvion() == this.row_selected.getIdAvion() && ac.getClase().getClave().equals("CY")) {
                    cantidadTurista = ac.getCapacidad() + "";
                }

            }
        } catch (Exception ex) {
        }

        isTuristaAMPlus = false;
        Parametro conect = this.row_selected.getIdConectividad();
        asignedConect = conect.getIdParametro();
        // istcWIFI = (conect.getClave().equals("tipoConectividad") && conect.getValorEn().equals("WIFI"));
        // istcGOGO2k = (conect.getClave().equals("tipoConectividad") && conect.getValorEn().equals("GOGO2K"));
        // istcNA = (conect.getClave().equals("tipoConectividad") && conect.getValorEn().equals("NA"));

        try {
            for (AvionAudifono au : avionAudifonoFacadeRemote.findAll()) {
                if (au.getStatus().equals((short) 1) && au.getAvion().getIdAvion() == this.row_selected.getIdAvion() && au.getClase().getClave().equals("CJ")) {
                    asignedAudifonoPremier = au.getMaterial().getNumeroParte();
                }
                if (au.getStatus().equals((short) 1) && au.getAvion().getIdAvion() == this.row_selected.getIdAvion() && au.getClase().getClave().equals("CY")) {
                    asignedAudifonoTurista = au.getMaterial().getNumeroParte();
                }
            }
        } catch (Exception ex) {
        }
        try {
            selectedSistemasEntretenimiento.clear();
            for (AvionSistemaEntretenimiento se : avionSistemaEntretenimientoFacadeRemote.findAll()) {
                if (se.getStatus().equals((short) 1)) {
                    if (se.getAvion().getIdAvion() == this.row_selected.getIdAvion()) {
                        selectedSistemasEntretenimiento.add(new SelectItem(se.getSistemaEntretenimiento().getIdSistemaEntretenimiento(), se.getSistemaEntretenimiento().getTipo() + "-" + se.getSistemaEntretenimiento().getVersion()));

                    }
                }
            }
        } catch (Exception ex) {
        }
        try {
all_records_EquipamientoComisariato.clear();
            for (EquipamientoSemifijo ec : equipamientoSemifijoFacadeRemote.findAll()) {
                if (ec.getStatus().equals((short) 1)) {
                    if (ec.getAvion().getIdAvion() == this.row_selected.getIdAvion()) {
                        MaterialEquipamientoComisariato mat = new MaterialEquipamientoComisariato();
                        mat.setCantidad(ec.getCantidad());
                        mat.setPeso(ec.getPeso());
                        mat.setNombreEn(ec.getMaterial().getDescripcionEn());
                        mat.setNombreEs(ec.getMaterial().getDescripcionEs());
                        mat.setNumeroParte(ec.getMaterial() != null ? ec.getMaterial().getNumeroParte() : null);
                        mat.setObservaciones(ec.getObservaciones());
                        all_records_EquipamientoComisariato.add(mat);

                    }
                }
            }
all_records_EquipamientoComisariatoEF.clear();
            for (EquipamientoFijo ec : equipamientoFijoFacadeRemote.findAll()) {
                if (ec.getStatus().equals((short) 1)) {
                    if (ec.getAvion().getIdAvion() == this.row_selected.getIdAvion()) {
                        MaterialEquipamientoComisariato mat = new MaterialEquipamientoComisariato();

                        mat.setNombreEn(ec.getNombreEn());
                        mat.setNombreEs(ec.getNombreEs());
                        mat.setObservaciones(ec.getObservaciones());
                        all_records_EquipamientoComisariatoEF.add(mat);

                    }
                }
            }

        } catch (Exception ex) {
        }

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
                //Avion
                row_selected.setTipoEquipoAvion(tipoEquipoAvionFacadeRemote.findByPK(asignedTipoEquipo));
                row_selected.setIdConectividad(ParametroFacadeRemote.findByPK(asignedConect));//istcWIFI ? conectividadWIFI : istcGOGO2k ? conectividadGOGO2k : conectividadNA);
                servicioFacadeRemote.update(row_selected);

                try {
                    for (AvionCapacidad capacidadP : avionCapacidadFacadeRemote.findAll()) {
                        if(cantidadTurista!=null && !cantidadTurista.equals("")){
                        if (capacidadP.getAvion().getIdAvion() == this.row_selected.getIdAvion() && capacidadP.getClase().getClave().equals("CY")) {
                            capacidadP.setCapacidad(Integer.parseInt(cantidadTurista));
                            avionCapacidadFacadeRemote.update(capacidadP);
                        }}
                        if(cantidadPremier!=null && !cantidadPremier.equals("")){
                        if (capacidadP.getAvion().getIdAvion() == this.row_selected.getIdAvion() && capacidadP.getClase().getClave().equals("CJ")) {
                            capacidadP.setCapacidad(Integer.parseInt(cantidadPremier));
                            avionCapacidadFacadeRemote.update(capacidadP);
                        }}
                    }
                } catch (Exception ex) {
                }

                //modif para update
                if(asignedAudifonoPremier.equals("-1") || asignedAudifonoTurista.equals("-1")){
                for (AvionAudifono audifonos : avionAudifonoFacadeRemote.findAll()) {
                    if (audifonos.getAvion().getIdAvion() == row_selected.getIdAvion()) {
                        audifonos.setStatus((short) 0);
                        avionAudifonoFacadeRemote.update(audifonos);
                    }
                }
                
                if(!asignedAudifonoPremier.equals("-1")){
                AvionAudifono audifonoP = new AvionAudifono();
                audifonoP.setMaterial(materialFacadeRemote.findByPK(asignedAudifonoPremier));
                audifonoP.setAvion(row_selected);
                audifonoP.setClase(clasePremier);
                audifonoP.setStatus((short) 1);
                avionAudifonoFacadeRemote.create(audifonoP);
                }
                if(!asignedAudifonoTurista.equals("-1")){
                AvionAudifono audifonoT = new AvionAudifono();
                audifonoT.setMaterial(materialFacadeRemote.findByPK(asignedAudifonoTurista));
                audifonoT.setAvion(row_selected);
                audifonoT.setClase(claseTurista);
                audifonoT.setStatus((short) 1);
                avionAudifonoFacadeRemote.create(audifonoT);
                }
                }

                for (AvionSistemaEntretenimiento se : avionSistemaEntretenimientoFacadeRemote.findAll()) {
                    se.setStatus((short) 0);
                    avionSistemaEntretenimientoFacadeRemote.update(se);
                }

                for (SelectItem item : selectedSistemasEntretenimiento) {
                    SistemaEntretenimiento sistema = sistemaEntretenimientoFacadeRemote.findByPK((Integer) item.getValue());
                    AvionSistemaEntretenimientoPK asePK = new AvionSistemaEntretenimientoPK();
                    asePK.setIdAvion(row_selected.getIdAvion());
                    asePK.setIdSistemaEntretenimiento(sistema.getIdSistemaEntretenimiento());
                    AvionSistemaEntretenimiento sistEntretenimiento = new AvionSistemaEntretenimiento();
                    sistEntretenimiento.setAvionSistemaEntretenimientoPK(asePK);
                    sistEntretenimiento.setStatus((short) 1);
                    sistEntretenimiento.setSistemaEntretenimiento(sistema);
                    sistEntretenimiento.setUsuarioCreo(request.getUserPrincipal().getName());
            sistEntretenimiento.setFechaCreo(new java.sql.Timestamp(now.getTime()));
            sistEntretenimiento.setUsuarioModifico(request.getUserPrincipal().getName());
            sistEntretenimiento.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    avionSistemaEntretenimientoFacadeRemote.update(sistEntretenimiento);
                }

                for (EquipamientoSemifijo se : equipamientoSemifijoFacadeRemote.findAll()) {
                    se.setStatus((short) 0);
                    equipamientoSemifijoFacadeRemote.update(se);
                }

                for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariato) {

                    EquipamientoSemifijo equipamiento = new EquipamientoSemifijo();
                    equipamiento.setAvion(row_selected);
                    equipamiento.setCantidad(material.getCantidad());
                    equipamiento.setMaterial(materialFacadeRemote.findByPK(material.getNumeroParte()));
                    equipamiento.setObservaciones(material.getObservaciones());
                    equipamiento.setPeso(material.getPeso());
                    equipamiento.setCantidad(material.getCantidad());
                    equipamiento.setStatus((short) 1);
                    equipamientoSemifijoFacadeRemote.create(equipamiento);

                }

                for (EquipamientoFijo se : equipamientoFijoFacadeRemote.findAll()) {
                    se.setStatus((short) 0);
                    equipamientoFijoFacadeRemote.update(se);
                }

                for (MaterialEquipamientoComisariato material : all_records_EquipamientoComisariatoEF) {

                    EquipamientoFijo equipamiento = new EquipamientoFijo();
                    equipamiento.setAvion(row_selected);
                    equipamiento.setNombreEn(material.getNombreEn());
                    equipamiento.setNombreEs(material.getNombreEs());
                    equipamiento.setObservaciones(material.getObservaciones());
                    equipamiento.setStatus((short) 1);
                    equipamientoFijoFacadeRemote.create(equipamiento);

                }

                servicioFacadeRemote.update(row_selected);
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Avion getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Avion row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
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

    public Integer getAsignedTipoEquipo() {
        return asignedTipoEquipo;
    }

    public void setAsignedTipoEquipo(Integer asignedTipoEquipo) {
        this.asignedTipoEquipo = asignedTipoEquipo;
    }

    public List<SelectItem> getSelectTipoEquipos() {
        return selectTipoEquipos;
    }

    public void setSelectTipoEquipos(List<SelectItem> selectTipoEquipos) {
        this.selectTipoEquipos = selectTipoEquipos;
    }

    public String getLabelCompania() {
        return labelCompania;
    }

    public void setLabelCompania(String labelCompania) {
        this.labelCompania = labelCompania;
    }

    public List<Avion> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Avion> all_records) {
        this.all_records = all_records;
    }

    public String getCantidadPremier() {
        return cantidadPremier;
    }

    public void setCantidadPremier(String cantidadPremier) {
        this.cantidadPremier = cantidadPremier;
    }

    public String getCantidadTurista() {
        return cantidadTurista;
    }

    public void setCantidadTurista(String cantidadTurista) {
        this.cantidadTurista = cantidadTurista;
    }

    public boolean isIsTuristaAMPlus() {
        return isTuristaAMPlus;
    }

    public void setIsTuristaAMPlus(boolean isTuristaAMPlus) {
        this.isTuristaAMPlus = isTuristaAMPlus;
    }

    public Clase getClasePremier() {
        return clasePremier;
    }

    public void setClasePremier(Clase clasePremier) {
        this.clasePremier = clasePremier;
    }

    public Clase getClaseTurista() {
        return claseTurista;
    }

    public void setClaseTurista(Clase claseTurista) {
        this.claseTurista = claseTurista;
    }

    public Parametro getConectividadWIFI() {
        return conectividadWIFI;
    }

    public void setConectividadWIFI(Parametro conectividadWIFI) {
        this.conectividadWIFI = conectividadWIFI;
    }

    public Parametro getConectividadGOGO2k() {
        return conectividadGOGO2k;
    }

    public void setConectividadGOGO2k(Parametro conectividadGOGO2k) {
        this.conectividadGOGO2k = conectividadGOGO2k;
    }

    public Parametro getConectividadNA() {
        return conectividadNA;
    }

    public void setConectividadNA(Parametro conectividadNA) {
        this.conectividadNA = conectividadNA;
    }

    public String getAsignedAudifonoPremier() {
        return asignedAudifonoPremier;
    }

    public void setAsignedAudifonoPremier(String asignedAudifonoPremier) {
        this.asignedAudifonoPremier = asignedAudifonoPremier;
    }

    public String getAsignedAudifonoTurista() {
        return asignedAudifonoTurista;
    }

    public void setAsignedAudifonoTurista(String asignedAudifonoTurista) {
        this.asignedAudifonoTurista = asignedAudifonoTurista;
    }

    public List<SelectItem> getSelectAudifonos() {
        return selectAudifonos;
    }

    public void setSelectAudifonos(List<SelectItem> selectAudifonos) {
        this.selectAudifonos = selectAudifonos;
    }

    public Integer getAsignedSistemaEntretenimiento() {
        return asignedSistemaEntretenimiento;
    }

    public void setAsignedSistemaEntretenimiento(Integer asignedSistemaEntretenimiento) {
        this.asignedSistemaEntretenimiento = asignedSistemaEntretenimiento;
    }

    public List<SelectItem> getSelectSistemaEntretenimiento() {
        return selectSistemaEntretenimiento;
    }

    public void setSelectSistemaEntretenimiento(List<SelectItem> selectSistemaEntretenimiento) {
        this.selectSistemaEntretenimiento = selectSistemaEntretenimiento;
    }

    public Integer getSelectedSistemaEntretenimiento() {
        return selectedSistemaEntretenimiento;
    }

    public void setSelectedSistemaEntretenimiento(Integer selectedSistemaEntretenimiento) {
        this.selectedSistemaEntretenimiento = selectedSistemaEntretenimiento;
    }

    public List<SelectItem> getSelectedSistemasEntretenimiento() {
        return selectedSistemasEntretenimiento;
    }

    public void setSelectedSistemasEntretenimiento(List<SelectItem> selectedSistemasEntretenimiento) {
        this.selectedSistemasEntretenimiento = selectedSistemasEntretenimiento;
    }

    public String getAsignedMaterial() {
        return asignedMaterial;
    }

    public void setAsignedMaterial(String asignedMaterial) {
        this.asignedMaterial = asignedMaterial;
    }

    public List<SelectItem> getSelectMateriales() {
        return selectMateriales;
    }

    public void setSelectMateriales(List<SelectItem> selectMateriales) {
        this.selectMateriales = selectMateriales;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcionEs() {
        return descripcionEs;
    }

    public void setDescripcionEs(String descripcionEs) {
        this.descripcionEs = descripcionEs;
    }

    public String getDescripcionEn() {
        return descripcionEn;
    }

    public void setDescripcionEn(String descripcionEn) {
        this.descripcionEn = descripcionEn;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<MaterialEquipamientoComisariato> getAll_records_EquipamientoComisariato() {
        return all_records_EquipamientoComisariato;
    }

    public void setAll_records_EquipamientoComisariato(List<MaterialEquipamientoComisariato> all_records_EquipamientoComisariato) {
        this.all_records_EquipamientoComisariato = all_records_EquipamientoComisariato;
    }

    public String getDescripcionEsEF() {
        return descripcionEsEF;
    }

    public void setDescripcionEsEF(String descripcionEsEF) {
        this.descripcionEsEF = descripcionEsEF;
    }

    public String getDescripcionEnEF() {
        return descripcionEnEF;
    }

    public void setDescripcionEnEF(String descripcionEnEF) {
        this.descripcionEnEF = descripcionEnEF;
    }

    public String getObservacionesEF() {
        return observacionesEF;
    }

    public void setObservacionesEF(String observacionesEF) {
        this.observacionesEF = observacionesEF;
    }

    public List<MaterialEquipamientoComisariato> getAll_records_EquipamientoComisariatoEF() {
        return all_records_EquipamientoComisariatoEF;
    }

    public void setAll_records_EquipamientoComisariatoEF(List<MaterialEquipamientoComisariato> all_records_EquipamientoComisariatoEF) {
        this.all_records_EquipamientoComisariatoEF = all_records_EquipamientoComisariatoEF;
    }

    public Integer getAsignedConect() { 
        return asignedConect;
    }

    public void setAsignedConect(Integer asignedConect) { 
        this.asignedConect = asignedConect;
    }

    public List<SelectItem> getSelectConectividades() {
        return selectConectividades;
    }

    public void setSelectConectividades(List<SelectItem> selectConectividades) {
        this.selectConectividades = selectConectividades;
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
