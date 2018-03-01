package com.aeromexico.pab.web.jsf.diseno;

import com.aeromexico.pab.web.dto.Secuencia;
import com.aeromexico.pab.web.dto.HorarioRegionDuracion;
import com.aeromexico.pab.web.dto.AsignacionServicioHorariosDuracion;
import com.aeromexico.pab.web.dto.AsignacionServicioDuracionSecuencias;
import com.aeromexico.pab.backend.remote.AsignacionServicioDuracionFacadeRemote;
import com.aeromexico.pab.backend.remote.AsignacionServicioFacadeRemote;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.CodigoServicioFacadeRemote;
import com.aeromexico.pab.backend.remote.DuracionVuelosFacadeRemote;
import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.HorarioRegionFacadeRemote;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.entity.AsignacionServicio;
import com.aeromexico.pab.entity.AsignacionServicioDuracion;
import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.CodigoServicio;
import com.aeromexico.pab.entity.DuracionVuelos;
import com.aeromexico.pab.entity.HorarioRegion;
import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.Parametro;

import com.aeromexico.pab.entity.Region;
import com.aeromexico.pab.web.dto.ProductoDTO;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "asignacionDeServiciosMB")
@SessionScoped
public class AsignacionDeServiciosMB implements Serializable {

    public AsignacionDeServiciosMB() {
    }

    private AsignacionServicio row_selected = null;
    boolean modificarRegistro = false;

    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private Integer asignedModelo;
    private List<SelectItem> selectModelos;
    private Integer asignedClase;
    private List<SelectItem> selectClases;
    private Integer asignedOrigen;
    private List<SelectItem> selectOrigenes;
    private List<SelectItem> selectDuraciones;
    private List<AsignacionServicio> all_records;
    private List<HorarioRegion> all_records_HorarioRegion;
    private List<DuracionVuelos> all_records_DuracionVuelos;
    private List<String> asignedCodigos;
    private List<SelectItem> selectedCodigos;
    private List<String> asignedCodigoComples;
    private List<SelectItem> selectedCodigosComple;
    private Date horaInicio;
    private Date horaFin;
    private HorarioRegion row_selected_hr;
    private DuracionVuelos row_selected_dv;
    private String labelCompania;
    private AsignacionServicioHorariosDuracion row_as;
    private String saliendoDe;
    private String clase;
    private String regiondesc;
    private String modelo;
    private boolean isasigHorDur;
    private List<AsignacionServicioDuracionSecuencias> all_records_vp;
    private List<AsignacionServicioDuracionSecuencias> all_records_SalMEX_P;
    private List<AsignacionServicioDuracionSecuencias> all_records_SalMEX_T;
    private List<AsignacionServicioDuracionSecuencias> all_records_HaciMEX_P;
    private List<AsignacionServicioDuracionSecuencias> all_records_HaciMEX_T;
    private List<CodigoServicio> codigos_Servicio_borrado;
    private List<CodigoServicio> codigos_Servicio_original;
    private List<CodigoServicio> codigos_Servicio;
    private List<CodigoServicio> codigos_Servicio_a_Modificar;
    private List<Secuencia> lista_secuencias;
    private String horarioregionSelectServ;
    private String duracionesSelectServ;
    private DuracionVuelos duracionSelected;
    private HorarioRegion horarioregionSelected;
    private Integer idAsignacionServicio;
    private Clase clasePremier;
    private Clase claseTurista;
    private Parametro origenSalMEX;
    private Parametro origenHaciMEX;
    private List<AsignacionServicioDuracion> listaUpdate;
    private AsignacionServicioDuracionSecuencias row_modified;
    private Integer asignedSecuencia;
    private List<SelectItem> selectSecuencias;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.US);

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/asignacionServicio_RSB")
    AsignacionServicioFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/horarioRegion_RSB")
    HorarioRegionFacadeRemote horarioRegionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/duracionVuelos_RSB")
    DuracionVuelosFacadeRemote duracionVuelosFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote modeloAvionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote claseFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/codigoServicio_RSB")
    CodigoServicioFacadeRemote codigoServicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/asignacionServicioDuracion_RSB")
    AsignacionServicioDuracionFacadeRemote asignacionServicioDuracionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;

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
        asignedCodigos = new ArrayList<>();
        all_records_vp = new ArrayList<>();
        lista_secuencias = new ArrayList<>();
        row_selected = new AsignacionServicio();
        horarioregionSelectServ = "";
        duracionesSelectServ = "";
        isasigHorDur = true;
        saliendoDe = null;
        clase = null;
        regiondesc = null;
        modelo = null;
        selectOrigenes = new ArrayList<>();
        codigos_Servicio = new ArrayList<>();
        codigos_Servicio_a_Modificar= new ArrayList<>();
        codigos_Servicio_borrado = new ArrayList<>();
        parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("origenVuelo") && regs.getStatus().equals((short) 1))).map((regs) -> {
            if (regs.getValorEn().equals("From Mexico")) {
                origenSalMEX = regs;
            }
            return regs;
        }).map((regs) -> {
            if (regs.getValorEn().equals("to Mexico")) {
                origenHaciMEX = regs;
            }
            return regs;
        }).map((regs) -> {
            asignedOrigen = regs.getIdParametro();
            return regs;
        }).forEachOrdered((regs) -> {
            selectOrigenes.add(new SelectItem(regs.getIdParametro(),
                    (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
            ));
        });

        row_selected_hr = new HorarioRegion();
        asignedRegion = -1;
        selectRegiones = new ArrayList<>();
        selectRegiones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            regionesFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectRegiones.add(new SelectItem(regs.getIdRegion(), regs.getCveRegion()));
            });
        } catch (Exception ex) {
        }

        asignedModelo = -1;
        selectModelos = new ArrayList<>();
        selectModelos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            modeloAvionFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectModelos.add(new SelectItem(regs.getIdModeloAvion(), regs.getModelo()));
            });
        } catch (Exception ex) {
        }

        asignedClase = -1;
        selectClases = new ArrayList<>();
        selectClases.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            claseFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).map((regs) -> {
                if (regs.getClave().equals("CJ")) {
                    clasePremier = regs;
                }
                return regs;
            }).map((regs) -> {
                if (regs.getClave().equals("CY")) {
                    claseTurista = regs;
                }
                return regs;
            }).forEachOrdered((regs) -> {
                selectClases.add(new SelectItem(regs.getIdClase(),
                        (localeInfo.getLanguage().equals("es") ? regs.getNombreEsp() : regs.getNombreEng())
                ));
            });
        } catch (Exception ex) {
        }

        selectDuraciones = new ArrayList<>();
        try {
            
            List<Object[]> result = genericSearchFacadeRemote.loadQuery("SELECT d.idDuracionVuelos,d.tiempoInicio|| '-' ||d.tiempoFin FROM DuracionVuelos d Where d.status = 1 order by d.tiempoInicio");
            for(Object[] oa: result){
                selectDuraciones.add(new SelectItem((Integer)oa[0],(String)oa[1]));
            }
/*            
            duracionVuelosFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectDuraciones.add(new SelectItem(regs.getIdDuracionVuelos(),(regs.getTiempoInicio() + "-" + regs.getTiempoFin())
                ));
            });
            */
        } catch (Exception ex) {
        }

    }

    public void agregarCodigo() {

        if (asignedCodigos != null && !asignedCodigos.isEmpty()) {
            asignedCodigos.stream().map((asignedCodigo) -> codigoServicioFacadeRemote.findByPK(new Integer(asignedCodigo))).forEachOrdered((cs) -> {
                codigos_Servicio.add(cs);
            });
            asignedCodigos = new ArrayList<>();
        }
        if (asignedCodigoComples != null && !asignedCodigoComples.isEmpty()) {
            asignedCodigoComples.stream().map((asignedCodigoComple) -> codigoServicioFacadeRemote.findByPK(new Integer(asignedCodigoComple))).forEachOrdered((cs) -> {
                codigos_Servicio.add(cs);
            });
            asignedCodigoComples = new ArrayList<>();
        }
    }

    public void quitarCodigoServicio() {
        Map<String, String> params = (Map<String, String>) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String idCodigoServicio = params.get("selectedElement");
        List<CodigoServicio> cs_list = new ArrayList<>();
        for (Object cs : codigos_Servicio) {
            if (!((String) cs).equals((String) idCodigoServicio)) {
                cs_list.add(codigoServicioFacadeRemote.findByPK(new Integer((String) cs)));
            } else {
                codigos_Servicio_borrado.add(codigoServicioFacadeRemote.findByPK(new Integer((String) cs)));
            }
        }
        codigos_Servicio.clear();

        codigos_Servicio = cs_list;

    }

    public void asignarServicios(Integer idModeloAvion, Integer idClase, Integer idOrigen, Integer idRegion, Integer idHorarioRegion, Integer idDuraciones) {

        horarioregionSelected = horarioRegionFacadeRemote.findByPK(idHorarioRegion);
        horarioregionSelectServ = horarioregionSelected.getHoraInicio() + " - " + horarioregionSelected.getHoraFin();
        duracionSelected = duracionVuelosFacadeRemote.findByPK(idDuraciones);

        duracionesSelectServ = duracionSelected.getTiempoInicio() + " - " + duracionSelected.getTiempoFin();
        asignedModelo = idModeloAvion;
        asignedOrigen = idOrigen;
        asignedClase = idClase;
        asignedRegion = idRegion;
        asignedCodigos = new ArrayList<>();

        selectedCodigos = new ArrayList<>();
        try {
            codigoServicioFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).filter((regs) -> (Objects.equals(regs.getClase().getIdClase(), idClase) && regs.getIdTipoCodigoServicio().getValorEs().equals("Alimentos"))).forEachOrdered((regs) -> {
                selectedCodigos.add(new SelectItem(regs.getIdCodigoServicio(), regs.getCveCodigoServicio()));
            });
        } catch (Exception ex) {
        }

        asignedCodigoComples = new ArrayList<>();
        selectedCodigosComple = new ArrayList<>();
        try {
            codigoServicioFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).filter((regs) -> (Objects.equals(regs.getClase().getIdClase(), idClase) && regs.getIdTipoCodigoServicio().getValorEs().equals("Complementarios"))).forEachOrdered((regs) -> {
                selectedCodigosComple.add(new SelectItem(regs.getIdCodigoServicio(), regs.getCveCodigoServicio()));
            });
        } catch (Exception ex) {
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('agregarSecuencia').show();");

    }

    public void regeresarHorariosEncabezado() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('agregarSecuencia').hide();");
        codigos_Servicio.clear();
        asignedCodigos = new ArrayList<>();
        asignedCodigoComples = new ArrayList<>();
        lista_secuencias.clear();

    }

    public void terminar() {
        codigos_Servicio.clear();
        asignedCodigos = new ArrayList<>();
        asignedCodigoComples = new ArrayList<>();
        lista_secuencias.clear();
        asignedModelo = -1;
        asignedClase = -1;
        asignedRegion = -1;
        labelCompania = null;
        row_as = null;
        idAsignacionServicio = null;
        all_records_vp = null;
        isasigHorDur = true;
    }

    public void regresarDialogSecuencias() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('agregarSecuencia').show();");

        context.execute("PF('vistaPreliminar').hide();");

    }

    public void agregarOtraSecuencia() {
        Integer idSecuencia = lista_secuencias.size() + 1;
        String codigos = "";

        if (!codigos_Servicio.isEmpty()) {
            Secuencia secuencia = new Secuencia();
            secuencia.setIdSecuencia(idSecuencia);
            //List<String> codigos_Servicio_String = (List<Object>)codigos_Servicio;
            codigos = codigos_Servicio.stream().map((cs) -> cs + "|").reduce(codigos, String::concat);
            secuencia.setCodigos_Servicio(codigos);
            secuencia.setIdDuracion(duracionSelected.getIdDuracionVuelos());
            secuencia.setIdHorarioRegion(horarioregionSelected.getIdHorarioRegion());
            lista_secuencias.add(secuencia);
            codigos_Servicio.clear();
            asignedCodigos = new ArrayList<>();
            asignedCodigoComples = new ArrayList<>();
        } else {
            messgeValidateError("form:agregarOtraSecuencia", "Primero debe, agregar a Secuencia Actual");
        }
    }

    public void update() {
        agregarOtraSecuencia();

        short orden = 1;
        int nosec = 2;

        for (AsignacionServicioDuracion as : listaUpdate) {
            if (as.getNoSecuencia() >= nosec) {
                nosec = as.getNoSecuencia() + 1;
            }
        }

        boolean encontrado = false;
        boolean borrado = false;
        AsignacionServicioDuracion entitylike = new AsignacionServicioDuracion();
        for (Secuencia sec : lista_secuencias) {

            String split[] = sec.getCodigos_Servicio().split("\\|");
            for (String valor : split) {
                if (valor.equals("|")) {
                    break;
                }
                for (AsignacionServicioDuracion as : listaUpdate) {
                    if (as.getCodigoServicio().getIdCodigoServicio() == Integer.parseInt(valor)) {
                        entitylike = as;
                        encontrado = true;
                        break;
                    } else {
                        encontrado = false;
                    }
                }

                if (encontrado) {
                    entitylike.setOrden(orden);
                    //entitylike.setNoSecuencia(nosec);
                    asignacionServicioDuracionFacadeRemote.update(entitylike);
                    System.out.println("paso por update clave:" + valor);
                    orden++;
                } else {
                    AsignacionServicioDuracion entity = new AsignacionServicioDuracion();
                    entity.setAsignacionServicio(row_modified.getAsignacionServicio());
                    entity.setCodigoServicio(codigoServicioFacadeRemote.findByPK(Integer.parseInt(valor)));
                    entity.setDuracionVuelos(duracionSelected);
                    entity.setHorarioRegion(horarioregionSelected);
                    entity.setOrden(orden);
                    entity.setNoSecuencia(nosec);
                    asignacionServicioDuracionFacadeRemote.create(entity);
                    System.out.println("paso por create clave:" + valor);
                    orden++;
                }

                encontrado = false;
                borrado = false;
            }

            nosec++;
        }

        for (CodigoServicio cs : codigos_Servicio_borrado) {
            for (AsignacionServicioDuracion as : listaUpdate) {
                if (Objects.equals(cs.getIdCodigoServicio(), as.getCodigoServicio().getIdCodigoServicio())) {
                    asignacionServicioDuracionFacadeRemote.remove(as);
                    break;
                }
            }
        }

        listaUpdate.clear();
        codigos_Servicio_original.clear();
        codigos_Servicio_borrado.clear();

        asignedModelo = row_modified.getAsignacionServicio().getModeloAvion().getIdModeloAvion();
        asignedRegion = row_modified.getAsignacionServicio().getRegion().getIdRegion();
        buscar();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('agregarSecuencia').hide();");
    }

    public void save() {
        // if (validateEncabezado("form:saveButton")) {

        agregarOtraSecuencia();
        AsignacionServicio as = new AsignacionServicio();
        if (idAsignacionServicio == null) {
            row_selected.setStatus((short) 1);
            row_selected.setModeloAvion(modeloAvionFacadeRemote.findByPK(asignedModelo));
            row_selected.setIdOrigen(parametroFacadeRemote.findByPK(asignedOrigen));
            row_selected.setClase(claseFacadeRemote.findByPK(asignedClase));
            row_selected.setRegion(regionesFacadeRemote.findByPK(asignedRegion));
            as = servicioFacadeRemote.create(row_selected);
            idAsignacionServicio = as.getIdAsignacionServicio();
        } else {
            as = servicioFacadeRemote.findByPK(idAsignacionServicio);
        }

        //isasigHorDur = true;
        short orden = 1;
        int nosec = 1;
        for (Secuencia sec : lista_secuencias) {

            String split[] = sec.getCodigos_Servicio().split("\\|");
            for (String valor : split) {
                if (valor.equals("|")) {
                    break;
                }
                AsignacionServicioDuracion entity = new AsignacionServicioDuracion();
                entity.setAsignacionServicio(as);
                entity.setCodigoServicio(codigoServicioFacadeRemote.findByPK(Integer.parseInt(valor)));
                entity.setDuracionVuelos(duracionSelected);
                entity.setHorarioRegion(horarioregionSelected);
                entity.setOrden(orden);
                entity.setNoSecuencia(nosec);
                orden++;
                asignacionServicioDuracionFacadeRemote.create(entity);
            }
            nosec++;
        }

        all_records_vp = null;
        asignados();
        regeresarHorariosEncabezado();
        messgeValidateInfo("form:saveAll", "Registro Cargado correctamente ");

    }

    /**
     * Get all records
     *
     * @return
     */
    public List<AsignacionServicio> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public List<AsignacionServicioDuracionSecuencias> asignados() {
        if (all_records_vp == null) {
            all_records_vp = new ArrayList<>();
            try {
                if (idAsignacionServicio != null) {
                    int idasig = 0;
                    int horario = 0;
                    int duracion = 0;

                    for (AsignacionServicioDuracion as : asignacionServicioDuracionFacadeRemote.findAll()) {
                        if (Objects.equals(as.getAsignacionServicio().getIdAsignacionServicio(), idAsignacionServicio)) {

                            if (idasig != as.getAsignacionServicio().getIdAsignacionServicio().intValue()
                                    || horario != as.getHorarioRegion().getIdHorarioRegion().intValue()
                                    || duracion != as.getDuracionVuelos().getIdDuracionVuelos().intValue()) {

                                idasig = as.getAsignacionServicio().getIdAsignacionServicio();
                                horario = as.getHorarioRegion().getIdHorarioRegion();
                                duracion = as.getDuracionVuelos().getIdDuracionVuelos();
                                AsignacionServicioDuracionSecuencias e = new AsignacionServicioDuracionSecuencias();
                                e.setAsignacionServicio(as.getAsignacionServicio());
                                e.setHorarioRegion(as.getHorarioRegion());
                                e.setDuracionVuelos(as.getDuracionVuelos());
                                all_records_vp.add(e);

                            }

                        }
                    }

                    all_records_vp.forEach((lads) -> {
                        AsignacionServicioDuracion entity = new AsignacionServicioDuracion();
                        entity.setAsignacionServicio(lads.getAsignacionServicio());
                        entity.setHorarioRegion(lads.getHorarioRegion());
                        entity.setDuracionVuelos(lads.getDuracionVuelos());
                        String secuencia = "";
                        Integer noSec = 1;
                        for (AsignacionServicioDuracion codigo : asignacionServicioDuracionFacadeRemote.findAllLike(entity)) {
                            if (!Objects.equals(noSec, codigo.getNoSecuencia())) {
                                noSec = codigo.getNoSecuencia();
                                secuencia += " / ";
                            }
                            secuencia += codigo.getCodigoServicio().getCveCodigoServicio() + " ";

                        }
                        lads.setSecuencia(secuencia);
                    });

                }

            } catch (Exception ex) {
                all_records_vp = null;
            }
        }
        return all_records_vp;
    }

    public void buscar() {
        all_records_vp = null;
        all_records_SalMEX_P = consulta(clasePremier.getIdClase(), origenSalMEX.getIdParametro());
        all_records_SalMEX_T = consulta(claseTurista.getIdClase(), origenSalMEX.getIdParametro());
        all_records_HaciMEX_P = consulta(clasePremier.getIdClase(), origenHaciMEX.getIdParametro());
        all_records_HaciMEX_T = consulta(claseTurista.getIdClase(), origenHaciMEX.getIdParametro());

    }

    public List<AsignacionServicioDuracionSecuencias> consulta(Integer clase, Integer origen) {

        List<AsignacionServicioDuracionSecuencias> response = new ArrayList<>();
        try {
            boolean allmodelos = false, allregiones = false;
            if (asignedModelo == -1) {
                allmodelos = true;
            }
            if (asignedRegion == -1) {
                allregiones = true;
            }

            int idasig = 0;
            int horario = 0;
            int duracion = 0;
            for (AsignacionServicioDuracion as : asignacionServicioDuracionFacadeRemote.findAll()) {

                if ((Objects.equals(allmodelos ? as.getAsignacionServicio().getModeloAvion().getIdModeloAvion() : asignedModelo, as.getAsignacionServicio().getModeloAvion().getIdModeloAvion()))
                        && (Objects.equals(allregiones ? as.getAsignacionServicio().getRegion().getIdRegion() : asignedRegion, as.getAsignacionServicio().getRegion().getIdRegion()))
                        && (Objects.equals(as.getAsignacionServicio().getClase().getIdClase(), clase)
                        && Objects.equals(as.getAsignacionServicio().getIdOrigen().getIdParametro(), origen))) {

                    if (idasig != as.getAsignacionServicio().getIdAsignacionServicio().intValue()
                            || horario != as.getHorarioRegion().getIdHorarioRegion().intValue()
                            || duracion != as.getDuracionVuelos().getIdDuracionVuelos().intValue()) {

                        idasig = as.getAsignacionServicio().getIdAsignacionServicio();
                        horario = as.getHorarioRegion().getIdHorarioRegion();
                        duracion = as.getDuracionVuelos().getIdDuracionVuelos();

                        AsignacionServicioDuracionSecuencias e = new AsignacionServicioDuracionSecuencias();
                        e.setAsignacionServicio(as.getAsignacionServicio());
                        e.setHorarioRegion(as.getHorarioRegion());
                        e.setDuracionVuelos(as.getDuracionVuelos());
                        response.add(e);

                    }
                }

            }

            for (AsignacionServicioDuracionSecuencias lads : response) {
                if (Objects.equals(lads.getAsignacionServicio().getClase().getIdClase(), clase)
                        && Objects.equals(lads.getAsignacionServicio().getIdOrigen().getIdParametro(), origen)) {
                    AsignacionServicioDuracion entity = new AsignacionServicioDuracion();
                    entity.setAsignacionServicio(lads.getAsignacionServicio());
                    entity.setHorarioRegion(lads.getHorarioRegion());
                    entity.setDuracionVuelos(lads.getDuracionVuelos());
                    String secuencia = "";
                    Integer noSec = 1;
                    List<AsignacionServicioDuracion> listSorted = asignacionServicioDuracionFacadeRemote.findAllLike(entity);
                    listSorted.sort(Comparator.comparing(AsignacionServicioDuracion::getOrden));

                    for (AsignacionServicioDuracion codigo : listSorted) {

                        if (!Objects.equals(noSec, codigo.getNoSecuencia())) {
                            noSec = codigo.getNoSecuencia();
                            secuencia += " / ";
                        }
                        secuencia += codigo.getCodigoServicio().getCveCodigoServicio() + " ";

                    }
                    lads.setSecuencia(secuencia);
                }
            }

        } catch (Exception ex) {
            response = null;
        }

        return response;
    }

    public List<String> vistaPreliminarDatos(Integer idHorarioRegion) {
        List<String> retorno = new ArrayList<>();
        // duracionSelected
        // horarioregionSelected
        String codigos = "";
        try {
            for (DuracionVuelos dv : duracionVuelosFacadeRemote.findAll()) {
                for (Secuencia sec : lista_secuencias) {
                    if (Objects.equals(sec.getIdHorarioRegion(), idHorarioRegion) && Objects.equals(sec.getIdDuracion(), dv.getIdDuracionVuelos())) {
                        codigos = " ";
                        retorno.add(codigos);
                    }
                }
            }
        } catch (Exception ex) {

        }

        return retorno;
    }

    /**
     * Get all records
     *
     * @return
     */
    public List<HorarioRegion> findAllHorariosPorRegion() {
        if (all_records_HorarioRegion == null) {
            try {
                all_records_HorarioRegion = new ArrayList<>();
                all_records_HorarioRegion = horarioRegionFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records_HorarioRegion = null;
            }
        }
        return all_records_HorarioRegion;
    }

    /**
     * Get all records
     *
     * @return
     */
    public List<DuracionVuelos> findAllDuraciones() {
        if (all_records_DuracionVuelos == null) {
            try {
                all_records_DuracionVuelos = new ArrayList<>();
                all_records_DuracionVuelos = duracionVuelosFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records_DuracionVuelos = null;
            }
        }
        return all_records_DuracionVuelos;
    }

    public boolean validateHorariosPorRegion(String boton) {
        boolean retorno = true;
        if (asignedRegion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Región");
            return false;
        }

        if (horaInicio == null) {
            messgeValidateError(boton, "Debe incluir un Horario de Inicio");
            return false;
        }

        if (horaFin == null) {
            messgeValidateError(boton, "Debe incluir un Horario de Fin");
            return false;
        }
        return retorno;
    }

    public boolean validateDuraciones(String boton) {
        boolean retorno = true;

        if (horaInicio == null) {
            messgeValidateError(boton, "Debe incluir un Horario de Inicio");
            return false;
        }

        if (horaFin == null) {
            messgeValidateError(boton, "Debe incluir un Horario de Fin");
            return false;
        }
        return retorno;
    }

    /**
     * Save a new record
     */
    public void saveHorariosPorRegion() {

        try {
            if (validateHorariosPorRegion("form:saveButton")) {
                HorarioRegion hr = new HorarioRegion();
                hr.setStatus((short) 1);
                hr.setRegion(regionesFacadeRemote.findByPK(asignedRegion));
                hr.setHoraInicio(dateFormat.format(horaInicio));
                hr.setHoraFin(dateFormat.format(horaFin));
                horarioRegionFacadeRemote.create(hr);
                all_records_HorarioRegion = null;
                findAllHorariosPorRegion();
                asignedRegion = -1;
                horaInicio = null;
                horaFin = null;
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Save a new record
     */
    public void saveDuraciones() {

        try {
            if (validateDuraciones("form:saveButton")) {
                DuracionVuelos dv = new DuracionVuelos();
                dv.setStatus((short) 1);
                //dv.setRegion(regionesFacadeRemote.findByPK(asignedRegion));
                dv.setTiempoInicio(dateFormat.format(horaInicio));
                dv.setTiempoFin(dateFormat.format(horaFin));
                duracionVuelosFacadeRemote.create(dv);
                all_records_DuracionVuelos = null;

                findAllDuraciones();
                horaInicio = null;
                horaFin = null;
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void updateLabels() {
        labelCompania = modeloAvionFacadeRemote.findByPK(asignedModelo).getCompania().getCveCompania();
    }

    public Parametro getParametro(Integer idParametro) {
        return parametroFacadeRemote.findByPK(idParametro);
    }

    public String getModelo(Integer idModelo) {
        ModeloAvion ma = modeloAvionFacadeRemote.findByPK(idModelo);
        return ma.getModelo() + ", " + ma.getCompania().getNombre();
    }

    public Region getRegion(Integer idRegion) {
        return regionesFacadeRemote.findByPK(idRegion);
    }

    public Clase getClase(Integer idClase) {
        return claseFacadeRemote.findByPK(idClase);
    }

    public boolean validateEncabezado(String boton) {
        boolean retorno = true;
        if (asignedOrigen == null) {
            messgeValidateError("form:saveButton", "Debe seleccionar un Origen");
            retorno = false;
        }
        if (asignedClase == -1) {
            messgeValidateError("form:saveButton", "Debe seleccionar una Clase");
            retorno = false;
        }
        if (asignedRegion == -1) {
            messgeValidateError("form:saveButton", "Debe seleccionar una Región");
            retorno = false;
        }
        if (asignedModelo == -1) {
            messgeValidateError("form:saveButton", "Debe seleccionar un Modelo");
            retorno = false;
        }
        try {
            List<HorarioRegion> prueba = new ArrayList<>();
            horarioRegionFacadeRemote.findAll().stream().filter((hr) -> (Objects.equals(hr.getRegion().getIdRegion(), asignedRegion))).forEachOrdered((hr) -> {
                prueba.add(hr);
            });
            if (prueba.isEmpty()) {
                messgeValidateError("form:saveButton", "No hay Horarios para la reigión seleccionada");
                retorno = false;
            }
        } catch (Exception ex) {
            messgeValidateError("form:saveButton", "No hay Horarios para la reigión seleccionada");
            retorno = false;
        }

        if (selectDuraciones.isEmpty()) {
            messgeValidateError("form:saveButton", "No hay duraciones creadas en el catálogo");
            retorno = false;
        }

        return retorno;
    }

    public void saveEncabezado() {
        try {

            selectDuraciones = new ArrayList<>();
            try {
                List<Object[]> result = genericSearchFacadeRemote.loadQuery("SELECT d.idDuracionVuelos,d.tiempoInicio|| '-' ||d.tiempoFin FROM DuracionVuelos d Where d.status = 1 order by d.tiempoInicio");
                for(Object[] oa: result){
                    selectDuraciones.add(new SelectItem((Integer)oa[0],(String)oa[1]));
                }
            } catch (Exception ex) {
            }

            if (validateEncabezado("form:saveButton")) {

                isasigHorDur = false;
                saliendoDe = getParametro(asignedOrigen).getValorEs();
                clase = getClase(asignedClase).getNombreEsp();
                regiondesc = getRegion(asignedRegion).getCveRegion() + " - " + getRegion(asignedRegion).getNombre();
                modelo = getModelo(asignedModelo);

                row_as = new AsignacionServicioHorariosDuracion();
                row_as.setIdModeloAvion(asignedModelo);
                row_as.setIdOrigen(asignedOrigen);
                row_as.setIdClase(asignedClase);
                row_as.setIdRegion(asignedRegion);
                try {
                    List<HorarioRegionDuracion> lista_hrd = new ArrayList<>();
                    horarioRegionFacadeRemote.findAll().stream().filter((hr) -> (Objects.equals(hr.getRegion().getIdRegion(), asignedRegion))).map((hr) -> {
                        HorarioRegionDuracion hd = new HorarioRegionDuracion();
                        hd.setHorarioRegion(hr);
                        return hd;
                    }).forEachOrdered((hd) -> {
                        lista_hrd.add(hd);
                    });
                    row_as.setHorarioRegionDuracion(lista_hrd);
                } catch (Exception ex) {
                }

                asignedModelo = -1;
                asignedClase = -1;
                asignedRegion = -1;
                labelCompania = null;

            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void updateEncabezado() {
    }

    /**
     * Go to edit Row selected Page Detail
     */
    public String modifyRowHorariosPorRegion(HorarioRegion hr) {
        String redirect = "/portal/sab/diseno/asignacionDeServicios/HorariosPorRegion?faces-redirect=true";
        try {
            row_selected_hr = hr;
            modificarRegistro = true;
            asignedRegion = hr.getRegion().getIdRegion();
            horaInicio = dateFormat.parse(hr.getHoraInicio());
            horaFin = dateFormat.parse(hr.getHoraFin());
        } catch (Exception ex) {
        }
        return redirect;
    }
    
    public void cerrarDialog(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('seleccionarSecuenciaDialog').hide();");
    }
    
    public void editarSecuencia(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('agregarSecuencia').show();");
        
        asignedCodigos = new ArrayList<>();
        asignedCodigoComples = new ArrayList<>();

        lista_secuencias = new ArrayList<>();
        asignarServicios(row_modified.getAsignacionServicio().getModeloAvion().getIdModeloAvion(),
                row_modified.getAsignacionServicio().getClase().getIdClase(),
                row_modified.getAsignacionServicio().getIdOrigen().getIdParametro(),
                row_modified.getAsignacionServicio().getRegion().getIdRegion(),
                row_modified.getHorarioRegion().getIdHorarioRegion(),
                row_modified.getDuracionVuelos().getIdDuracionVuelos()
        );
        codigos_Servicio_original = new ArrayList<>();
        codigos_Servicio = new ArrayList<>();
        codigos_Servicio_a_Modificar= new ArrayList<>();

        AsignacionServicioDuracion entity = new AsignacionServicioDuracion();
        entity.setAsignacionServicio(row_modified.getAsignacionServicio());
        entity.setHorarioRegion(row_modified.getHorarioRegion());
        entity.setDuracionVuelos(row_modified.getDuracionVuelos());
        
        

        for (AsignacionServicioDuracion codigo : listaUpdate) {
            System.out.println("codigo:"+codigo.getCodigoServicio().getDescripcion()+" #secuencia:"+codigo.getNoSecuencia()+" orden:"+codigo.getOrden());
            codigos_Servicio.add(codigo.getCodigoServicio());
            codigos_Servicio_original.add(codigo.getCodigoServicio());
        }
    }

    public void modifyRow(AsignacionServicioDuracionSecuencias row, String tipo) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('seleccionarSecuenciaDialog').show();");
        context.execute("PF('agregarSecuencia').hide();");

        row_modified = row;
        AsignacionServicioDuracion entity = new AsignacionServicioDuracion();
        entity.setAsignacionServicio(row.getAsignacionServicio());
        entity.setHorarioRegion(row.getHorarioRegion());
        entity.setDuracionVuelos(row.getDuracionVuelos());
        

        listaUpdate = new ArrayList<>();
        listaUpdate = asignacionServicioDuracionFacadeRemote.findAllLike(entity);
        listaUpdate.sort(Comparator.comparing(AsignacionServicioDuracion::getOrden));
        
        asignedSecuencia=-1;
        selectSecuencias =new ArrayList<>();
        selectSecuencias.add(localeInfo.createSelectIntKeyFirstItem());
        List<Integer> secuencias = new ArrayList<>();
        Integer num_sec=0;
        for (AsignacionServicioDuracion codigo : listaUpdate) {
            if(!Objects.equals(codigo.getNoSecuencia(), num_sec)){
                num_sec=codigo.getNoSecuencia();
                secuencias.add(num_sec);
            }
        }
        for(Integer secuencia: secuencias){
            String cadena="";
            for (AsignacionServicioDuracion codigo : listaUpdate) {
                if(Objects.equals(secuencia, codigo.getNoSecuencia())){
                cadena+= codigo.getCodigoServicio().getCveCodigoServicio()+" ";
                }
            }
            selectSecuencias.add(new SelectItem(secuencia, cadena));
        }
        
        
        

    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param dv
     * @return
     */
    public String modifyRowDuraciones(DuracionVuelos dv) {
        String redirect = "/portal/sab/diseno/asignacionDeServicios/duraciones?faces-redirect=true";
        try {
            row_selected_dv = dv;
            modificarRegistro = true;

            horaInicio = dateFormat.parse(dv.getTiempoInicio());
            horaFin = dateFormat.parse(dv.getTiempoFin());
        } catch (Exception ex) {
        }
        return redirect;
    }

    /**
     * Update selected record
     */
    public void updateHorariosPorRegion() {
        try {
            if (validateHorariosPorRegion("form:saveButton")) {
                row_selected_hr.setStatus((short) 1);
                row_selected_hr.setRegion(regionesFacadeRemote.findByPK(asignedRegion));
                row_selected_hr.setHoraInicio(dateFormat.format(horaInicio));
                row_selected_hr.setHoraFin(dateFormat.format(horaFin));
                horarioRegionFacadeRemote.update(row_selected_hr);
                all_records_HorarioRegion = null;
                findAllHorariosPorRegion();
                asignedRegion = -1;
                horaInicio = null;
                horaFin = null;

                modificarRegistro = false;

                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Update selected record
     */
    public void updateDuraciones() {
        try {
            if (validateDuraciones("form:saveButton")) {
                row_selected_dv.setStatus((short) 1);
                //row_selected_dv.setRegion(regionesFacadeRemote.findByPK(asignedRegion));
                row_selected_dv.setTiempoInicio(dateFormat.format(horaInicio));
                row_selected_dv.setTiempoFin(dateFormat.format(horaFin));
                duracionVuelosFacadeRemote.update(row_selected_dv);
                all_records_DuracionVuelos = null;
                findAllDuraciones();

                horaInicio = null;
                horaFin = null;

                modificarRegistro = false;

                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public AsignacionServicio getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(AsignacionServicio row_selected) {
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

    public List<AsignacionServicio> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<AsignacionServicio> all_records) {
        this.all_records = all_records;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public List<HorarioRegion> getAll_records_HorarioRegion() {
        return all_records_HorarioRegion;
    }

    public void setAll_records_HorarioRegion(List<HorarioRegion> all_records_HorarioRegion) {
        this.all_records_HorarioRegion = all_records_HorarioRegion;
    }

    public List<DuracionVuelos> getAll_records_DuracionVuelos() {
        return all_records_DuracionVuelos;
    }

    public void setAll_records_DuracionVuelos(List<DuracionVuelos> all_records_DuracionVuelos) {
        this.all_records_DuracionVuelos = all_records_DuracionVuelos;
    }

    public HorarioRegion getRow_selected_hr() {
        return row_selected_hr;
    }

    public void setRow_selected_hr(HorarioRegion row_selected_hr) {
        this.row_selected_hr = row_selected_hr;
    }

    public DuracionVuelos getRow_selected_dv() {
        return row_selected_dv;
    }

    public void setRow_selected_dv(DuracionVuelos row_selected_dv) {
        this.row_selected_dv = row_selected_dv;
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

    public String getLabelCompania() {
        return labelCompania;
    }

    public void setLabelCompania(String labelCompania) {
        this.labelCompania = labelCompania;
    }

    public Integer getAsignedOrigen() {
        return asignedOrigen;
    }

    public void setAsignedOrigen(Integer asignedOrigen) {
        this.asignedOrigen = asignedOrigen;
    }

    public List<SelectItem> getSelectOrigenes() {
        return selectOrigenes;
    }

    public void setSelectOrigenes(List<SelectItem> selectOrigenes) {
        this.selectOrigenes = selectOrigenes;
    }

    public AsignacionServicioHorariosDuracion getRow_as() {
        return row_as;
    }

    public void setRow_as(AsignacionServicioHorariosDuracion row_as) {
        this.row_as = row_as;
    }

    public List<SelectItem> getSelectDuraciones() {
        return selectDuraciones;
    }

    public void setSelectDuraciones(List<SelectItem> selectDuraciones) {
        this.selectDuraciones = selectDuraciones;
    }

    public String getSaliendoDe() {
        return saliendoDe;
    }

    public void setSaliendoDe(String saliendoDe) {
        this.saliendoDe = saliendoDe;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getRegiondesc() {
        return regiondesc;
    }

    public void setRegiondesc(String regiondesc) {
        this.regiondesc = regiondesc;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isIsasigHorDur() {
        return isasigHorDur;
    }

    public void setIsasigHorDur(boolean isasigHorDur) {
        this.isasigHorDur = isasigHorDur;
    }

    public List<SelectItem> getSelectedCodigos() {
        return selectedCodigos;
    }

    public void setSelectedCodigos(List<SelectItem> selectedCodigos) {
        this.selectedCodigos = selectedCodigos;
    }

    public List<CodigoServicio> getCodigos_Servicio() {
        return codigos_Servicio;
    }

    public void setCodigos_Servicio(List<CodigoServicio> codigos_Servicio) {
        this.codigos_Servicio = codigos_Servicio;
    }

    public String getHorarioregionSelectServ() {
        return horarioregionSelectServ;
    }

    public void setHorarioregionSelectServ(String horarioregionSelectServ) {
        this.horarioregionSelectServ = horarioregionSelectServ;
    }

    public String getDuracionesSelectServ() {
        return duracionesSelectServ;
    }

    public void setDuracionesSelectServ(String duracionesSelectServ) {
        this.duracionesSelectServ = duracionesSelectServ;
    }

    public DuracionVuelos getDuracionSelected() {
        return duracionSelected;
    }

    public void setDuracionSelected(DuracionVuelos duracionSelected) {
        this.duracionSelected = duracionSelected;
    }

    public HorarioRegion getHorarioregionSelected() {
        return horarioregionSelected;
    }

    public void setHorarioregionSelected(HorarioRegion horarioregionSelected) {
        this.horarioregionSelected = horarioregionSelected;
    }

    public List<Secuencia> getLista_secuencias() {
        return lista_secuencias;
    }

    public void setLista_secuencias(List<Secuencia> lista_secuencias) {
        this.lista_secuencias = lista_secuencias;
    }

    public List<String> getAsignedCodigoComples() {
        return asignedCodigoComples;
    }

    public void setAsignedCodigoComples(List<String> asignedCodigoComples) {
        this.asignedCodigoComples = asignedCodigoComples;
    }

    public List<SelectItem> getSelectedCodigosComple() {
        return selectedCodigosComple;
    }

    public void setSelectedCodigosComple(List<SelectItem> selectedCodigosComple) {
        this.selectedCodigosComple = selectedCodigosComple;
    }

    public List<AsignacionServicioDuracionSecuencias> getAll_records_vp() {
        return all_records_vp;
    }

    public void setAll_records_vp(List<AsignacionServicioDuracionSecuencias> all_records_vp) {
        this.all_records_vp = all_records_vp;
    }

    public Integer getIdAsignacionServicio() {
        return idAsignacionServicio;
    }

    public void setIdAsignacionServicio(Integer idAsignacionServicio) {
        this.idAsignacionServicio = idAsignacionServicio;
    }

    public List<AsignacionServicioDuracionSecuencias> getAll_records_SalMEX_T() {
        return all_records_SalMEX_T;
    }

    public void setAll_records_SalMEX_T(List<AsignacionServicioDuracionSecuencias> all_records_SalMEX_T) {
        this.all_records_SalMEX_T = all_records_SalMEX_T;
    }

    public List<AsignacionServicioDuracionSecuencias> getAll_records_HaciMEX_P() {
        return all_records_HaciMEX_P;
    }

    public void setAll_records_HaciMEX_P(List<AsignacionServicioDuracionSecuencias> all_records_HaciMEX_P) {
        this.all_records_HaciMEX_P = all_records_HaciMEX_P;
    }

    public List<AsignacionServicioDuracionSecuencias> getAll_records_HaciMEX_T() {
        return all_records_HaciMEX_T;
    }

    public void setAll_records_HaciMEX_T(List<AsignacionServicioDuracionSecuencias> all_records_HaciMEX_T) {
        this.all_records_HaciMEX_T = all_records_HaciMEX_T;
    }

    public List<AsignacionServicioDuracionSecuencias> getAll_records_SalMEX_P() {
        return all_records_SalMEX_P;
    }

    public void setAll_records_SalMEX_P(List<AsignacionServicioDuracionSecuencias> all_records_SalMEX_P) {
        this.all_records_SalMEX_P = all_records_SalMEX_P;
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

    public List<String> getAsignedCodigos() {
        return asignedCodigos;
    }

    public void setAsignedCodigos(List<String> asignedCodigos) {
        this.asignedCodigos = asignedCodigos;
    }

    public List<CodigoServicio> getCodigos_Servicio_borrado() {
        return codigos_Servicio_borrado;
    }

    public void setCodigos_Servicio_borrado(List<CodigoServicio> codigos_Servicio_borrado) {
        this.codigos_Servicio_borrado = codigos_Servicio_borrado;
    }

    public List<CodigoServicio> getCodigos_Servicio_original() {
        return codigos_Servicio_original;
    }

    public void setCodigos_Servicio_original(List<CodigoServicio> codigos_Servicio_original) {
        this.codigos_Servicio_original = codigos_Servicio_original;
    }

    public Parametro getOrigenSalMEX() {
        return origenSalMEX;
    }

    public void setOrigenSalMEX(Parametro origenSalMEX) {
        this.origenSalMEX = origenSalMEX;
    }

    public Parametro getOrigenHaciMEX() {
        return origenHaciMEX;
    }

    public void setOrigenHaciMEX(Parametro origenHaciMEX) {
        this.origenHaciMEX = origenHaciMEX;
    }

    public List<AsignacionServicioDuracion> getListaUpdate() {
        return listaUpdate;
    }

    public void setListaUpdate(List<AsignacionServicioDuracion> listaUpdate) {
        this.listaUpdate = listaUpdate;
    }

    public AsignacionServicioDuracionSecuencias getRow_modified() {
        return row_modified;
    }

    public void setRow_modified(AsignacionServicioDuracionSecuencias row_modified) {
        this.row_modified = row_modified;
    }

    public Integer getAsignedSecuencia() {
        return asignedSecuencia;
    }

    public void setAsignedSecuencia(Integer asignedSecuencia) {
        this.asignedSecuencia = asignedSecuencia;
    }

    public List<SelectItem> getSelectSecuencias() {
        return selectSecuencias;
    }

    public void setSelectSecuencias(List<SelectItem> selectSecuencias) {
        this.selectSecuencias = selectSecuencias;
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
