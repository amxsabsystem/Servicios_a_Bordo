package com.aeromexico.pab.web.jsf.diseno;

import com.aeromexico.pab.web.dto.CicloMes;
import com.aeromexico.pab.web.dto.CicloTemporada;
import com.aeromexico.pab.web.dto.CicloQuincena;
import com.aeromexico.pab.backend.remote.CicloFacadeRemote;
import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.ConfiguracionCicloFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.entity.Ciclo;
import com.aeromexico.pab.entity.ConfiguracionCiclo;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Pais;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.Region;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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
@Named(value = "ciclosMB")
@SessionScoped
public class CiclosMB implements Serializable {

    public CiclosMB() {
    }

    private Ciclo row_selected = null;
    boolean modificarRegistro = false;

    private List<Ciclo> all_records;
    private List<CicloMes> all_records_Mes;
    private List<CicloQuincena> all_records_Quincena;
    private List<CicloTemporada> all_records_Temporada;

    private Integer asignedTipoCiclo;
    private Integer asignedPeriodicidad;

    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private Integer asignedEstacion;
    private List<SelectItem> selectEstaciones;
    private String asignedProveedor;
    private List<SelectItem> selectProveedores;

    private List<SelectItem> selectRevision;
    private Integer asignedInviernoIni;
    private Integer asignedInviernoFin;
    private Integer asignedPrimaveraIni;
    private Integer asignedPrimaveraFin;
    private Integer asignedVeranoIni;
    private Integer asignedVeranoFin;
    private Integer asignedOtonoIni;
    private Integer asignedOtonoFin;

    private Integer asignedOrigen;
    private List<SelectItem> selectOrigenes;
    private boolean isdobleAbastecido;
    private List<SelectItem> selectTipoCiclos;
    private List<SelectItem> selectPeriodicidades;
    private Integer idTipoCicloNumerico;
    private Integer idTipoCicloTemporada;
    private Integer idPeridicidadMes;
    private Integer idPeriodicidadQuincenal;
    private List<SelectItem> selectNoCiclos;
    private List<SelectItem> selectMeses;

    private Integer noCiclo_Mes_Numerico_Ene;
    private Integer noCiclo_Mes_Numerico_Jul;
    private Integer noCiclo_Mes_Numerico_Feb;
    private Integer noCiclo_Mes_Numerico_Ago;
    private Integer noCiclo_Mes_Numerico_Mar;
    private Integer noCiclo_Mes_Numerico_Sep;
    private Integer noCiclo_Mes_Numerico_Abr;
    private Integer noCiclo_Mes_Numerico_Oct;
    private Integer noCiclo_Mes_Numerico_May;
    private Integer noCiclo_Mes_Numerico_Nov;
    private Integer noCiclo_Mes_Numerico_Jun;
    private Integer noCiclo_Mes_Numerico_Dic;

    private Integer noCiclo_Quin1_Numerico_Ene, noCiclo_Quin2_Numerico_Ene;
    private Integer noCiclo_Quin1_Numerico_Jul, noCiclo_Quin2_Numerico_Jul;
    private Integer noCiclo_Quin1_Numerico_Feb, noCiclo_Quin2_Numerico_Feb;
    private Integer noCiclo_Quin1_Numerico_Ago, noCiclo_Quin2_Numerico_Ago;
    private Integer noCiclo_Quin1_Numerico_Mar, noCiclo_Quin2_Numerico_Mar;
    private Integer noCiclo_Quin1_Numerico_Sep, noCiclo_Quin2_Numerico_Sep;
    private Integer noCiclo_Quin1_Numerico_Abr, noCiclo_Quin2_Numerico_Abr;
    private Integer noCiclo_Quin1_Numerico_Oct, noCiclo_Quin2_Numerico_Oct;
    private Integer noCiclo_Quin1_Numerico_May, noCiclo_Quin2_Numerico_May;
    private Integer noCiclo_Quin1_Numerico_Nov, noCiclo_Quin2_Numerico_Nov;
    private Integer noCiclo_Quin1_Numerico_Jun, noCiclo_Quin2_Numerico_Jun;
    private Integer noCiclo_Quin1_Numerico_Dic, noCiclo_Quin2_Numerico_Dic;

    private Integer mes_Ene, mes_Feb, mes_Mar, mes_Abr, mes_May, mes_Jun;
    private Integer mes_Jul, mes_Ago, mes_Sep, mes_Oct, mes_Nov, mes_Dic;

    private Integer quincena_1, quincena_2;

    private Integer temp_Invierno, temp_Primavera, temp_Verano, temp_Otono;
    private Integer temp_Inicio, temp_Fin;

    private Integer asignedAnio;
    private boolean disabledEdicion;
    private Parametro origenDesdeMexico;
    private Parametro origenHaciaMexico;


    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciclo_RSB")
    CicloFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/configuracionCiclo_RSB")
    ConfiguracionCicloFacadeRemote configuracionCicloFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedor_RSB")
    ProveedorFacadeRemote proveedorCorporativoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        selectOrigenes = new ArrayList<>();
        ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("origenVuelo") && regs.getStatus().equals((short) 1))).map((regs) -> {
            asignedOrigen = regs.getIdParametro();
            return regs;
        }).map((regs) -> {
            if(regs.getValorEn().equals("From Mexico"))
                origenDesdeMexico =regs;
            else
                origenHaciaMexico=regs;
            return regs;
        }).forEachOrdered((regs) -> {
            selectOrigenes.add(new SelectItem(regs.getIdParametro(),
                    (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
            ));
        });

        updateRegiones();

        selectEstaciones = new ArrayList<>();
        selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());

        asignedEstacion = -1;

        asignedProveedor = "-1";

        selectProveedores = new ArrayList<>();
        selectProveedores.add(localeInfo.createSelectStringKeyFirstItem());
        

        selectRevision = new ArrayList<>();
        selectRevision.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("revision"))).forEachOrdered((regs) -> {
                selectRevision.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
        } catch (Exception ex) {
        }

        selectNoCiclos = new ArrayList<>();
        selectNoCiclos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("noCiclo"))).forEachOrdered((regs) -> {
                selectNoCiclos.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
        } catch (Exception ex) {
        }

        asignedTipoCiclo = -1;
        selectTipoCiclos = new ArrayList<>();
        selectTipoCiclos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("tipoCiclo"))).map((regs) -> {
                if (regs.getValorEn().equals("Numeric")) {
                    idTipoCicloNumerico = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("Season")) {
                    idTipoCicloTemporada = regs.getIdParametro();
                }
                return regs;
            }).forEachOrdered((regs) -> {
                selectTipoCiclos.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
        } catch (Exception ex) {

        }

        asignedPeriodicidad = -1;
        selectPeriodicidades = new ArrayList<>();
        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("periodicidad"))).map((regs) -> {
                if (regs.getValorEn().equals("Monthly")) {
                    idPeridicidadMes = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("Biweekly")) {
                    idPeriodicidadQuincenal = regs.getIdParametro();
                }
                return regs;
            }).forEachOrdered((regs) -> {
                selectPeriodicidades.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
        } catch (Exception ex) {

        }

        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("tipoQuincena"))).map((regs) -> {
                if (regs.getValorEn().equals("1 Biweekly")) {
                    quincena_1 = regs.getIdParametro();
                }
                return regs;
            }).filter((regs) -> (regs.getValorEn().equals("2 Biweekly"))).forEachOrdered((regs) -> {
                quincena_2 = regs.getIdParametro();
            });
        } catch (Exception ex) {

        }
        selectMeses = new ArrayList<>();
        selectMeses.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("mes"))).map((regs) -> {
                if (regs.getValorEn().equals("January")) {
                    mes_Ene = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("February")) {
                    mes_Feb = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("March")) {
                    mes_Mar = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("April")) {
                    mes_Abr = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("May")) {
                    mes_May = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("June")) {
                    mes_Jun = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("July")) {
                    mes_Jul = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("August")) {
                    mes_Ago = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("September")) {
                    mes_Sep = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("October")) {
                    mes_Oct = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("November")) {
                    mes_Nov = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("December")) {
                    mes_Dic = regs.getIdParametro();
                }
                return regs;
            }).forEachOrdered((regs) -> {
                selectMeses.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
        } catch (Exception ex) {

        }

        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("temporada"))).map((regs) -> {
                if (regs.getValorEn().equals("Winter")) {
                    temp_Invierno = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("Spring")) {
                    temp_Primavera = regs.getIdParametro();
                }
                return regs;
            }).map((regs) -> {
                if (regs.getValorEn().equals("Summer")) {
                    temp_Verano = regs.getIdParametro();
                }
                return regs;
            }).filter((regs) -> (regs.getValorEn().equals("Autumn"))).forEachOrdered((regs) -> {
                temp_Otono = regs.getIdParametro();
            });
        } catch (Exception ex) {

        }

        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1) && regs.getClave().equals("tipoTemporada"))).map((regs) -> {
                if (regs.getValorEn().equals("begin")) {
                    temp_Inicio = regs.getIdParametro();
                }
                return regs;
            }).filter((regs) -> (regs.getValorEn().equals("end"))).forEachOrdered((regs) -> {
                temp_Fin = regs.getIdParametro();
            });
        } catch (Exception ex) {

        }

    }

    public void getEstacion(Integer idEstacion) {
        try {
            Estacion estacion = estacionFacadeRemote.findByPK(idEstacion);
            selectEstaciones.clear();
            selectEstaciones.add(new SelectItem(estacion.getIdEstacion(), estacion.getClaveIata() + " - " + estacion.getNombre()));
        } catch (Exception ex) {
        }
    }
    
    public void updateRegiones(){
        asignedRegion = -1;
        selectRegiones = new ArrayList<>();
        selectRegiones.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            Pais mexico =null;
            for(Pais pais: paisesFacadeRemote.findAll()){
                if(pais.getNombre().equals("México")){
                mexico=pais;
                }
            }
            if(Objects.equals(origenDesdeMexico.getIdParametro(), ParametroFacadeRemote.findByPK(asignedOrigen).getIdParametro()))
                paisesFacadeRemote.findAll().stream().filter((pais) -> (pais.getNombre().equals("México") && pais.getStatus().equals((short) 1) )).forEachOrdered((pais) -> {
                    selectRegiones.add(new SelectItem(pais.getRegion().getIdRegion(), pais.getRegion().getCveRegion()));
            });
            if(Objects.equals(origenHaciaMexico.getIdParametro(), ParametroFacadeRemote.findByPK(asignedOrigen).getIdParametro()))
              for(Region reg: regionesFacadeRemote.findAll()){
                  if(!Objects.equals(mexico.getRegion().getIdRegion(), reg.getIdRegion()))
                  selectRegiones.add(new SelectItem(reg.getIdRegion(),reg.getCveRegion()));
              }
                
                
        } catch (Exception ex) {
        }
    }
    
    public void getEstaciones() {
        try {
            estacionFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                Integer idRegion = regs.getCiudad().getPais().getRegion().getIdRegion();
                if (Objects.equals(asignedRegion, idRegion)) {
                    selectEstaciones.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata() + " - " + regs.getNombre()));
                }
            });
        } catch (Exception ex) {
        }
    }

    public void getProveedores() {
        try {
            Region region =estacionFacadeRemote.findByPK(asignedEstacion).getCiudad().getPais().getRegion();
            for (Proveedor regs : proveedorCorporativoFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    Proveedor proveedor = proveedorCorporativoFacadeRemote.findByPK_EAGER(regs.getClaveProveedor());
                    for (Region regiones : proveedor.getRegionList()) {                        
                        if(Objects.equals(region.getIdRegion(), regiones.getIdRegion())){
                         selectProveedores.add(new SelectItem(regs.getClaveProveedor(), regs.getNombre()));   
                         break;
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }
    }    
    
    public List<CicloMes> findAllMes() {
        if (all_records_Mes == null) {
            try {
                consulta();
            } catch (Exception ex) {
                all_records_Mes = null;
            }
        }
        return all_records_Mes;
    }

    public List<CicloQuincena> findAllQuincena() {
        if (all_records_Quincena == null) {
            try {
                consulta();
            } catch (Exception ex) {
                all_records_Quincena = null;
            }
        }
        return all_records_Quincena;
    }

    public List<CicloTemporada> findAllTemporada() {
        if (all_records_Temporada == null) {
            try {
                consulta();
            } catch (Exception ex) {
                all_records_Temporada = null;
            }
        }
        return all_records_Temporada;
    }

    public void search() {
        consulta();
    }

    public void consulta() {
        all_records_Mes = new ArrayList<>();
        all_records_Quincena = new ArrayList<>();
        all_records_Temporada = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.US);
        try {
            boolean allProveedores = false, allAnios = false;
            if (asignedProveedor.equals("-1")) {
                allProveedores = true;
            }
            if (asignedAnio == null) {
                allAnios = true;
            }

            int idCiclo = 0;
            for (ConfiguracionCiclo conf_ciclo : configuracionCicloFacadeRemote.findAll()) {
                Date now = new Date(conf_ciclo.getCiclo().getFechaCreo().getTime());
                int year = Integer.parseInt(yearFormat.format(now));
                if (((allProveedores ? conf_ciclo.getCiclo().getProveedor().getClaveProveedor() : asignedProveedor).equals(conf_ciclo.getCiclo().getProveedor().getClaveProveedor()))
                        && ((allAnios ? year : asignedAnio.intValue()) == (year))) {

                    if (idCiclo != conf_ciclo.getCiclo().getIdCiclo()) {
                        idCiclo = conf_ciclo.getCiclo().getIdCiclo();

                        CicloMes cm = new CicloMes();
                        cm.setIdCiclo(conf_ciclo.getCiclo().getIdCiclo());
                        cm.setEstacion(conf_ciclo.getCiclo().getEstacion());
                        cm.setDobleAbastecido(conf_ciclo.getCiclo().getDobleAbastecido());
                        cm.setProveedor(conf_ciclo.getCiclo().getProveedor());
                        cm.setRegion(conf_ciclo.getCiclo().getRegion());
                        cm.setIdOrigenvuelo(conf_ciclo.getCiclo().getIdOrigenvuelo());
                        cm.setIdTipociclo(conf_ciclo.getCiclo().getIdTipociclo());

                        CicloQuincena cq = new CicloQuincena();
                        cq.setIdCiclo(conf_ciclo.getCiclo().getIdCiclo());
                        cq.setEstacion(conf_ciclo.getCiclo().getEstacion());
                        cq.setDobleAbastecido(conf_ciclo.getCiclo().getDobleAbastecido());
                        cq.setProveedor(conf_ciclo.getCiclo().getProveedor());
                        cq.setRegion(conf_ciclo.getCiclo().getRegion());
                        cq.setIdOrigenvuelo(conf_ciclo.getCiclo().getIdOrigenvuelo());
                        cq.setIdTipociclo(conf_ciclo.getCiclo().getIdTipociclo());

                        CicloTemporada ct = new CicloTemporada();
                        ct.setIdCiclo(conf_ciclo.getCiclo().getIdCiclo());
                        ct.setEstacion(conf_ciclo.getCiclo().getEstacion());
                        ct.setDobleAbastecido(conf_ciclo.getCiclo().getDobleAbastecido());
                        ct.setProveedor(conf_ciclo.getCiclo().getProveedor());
                        ct.setRegion(conf_ciclo.getCiclo().getRegion());
                        ct.setIdOrigenvuelo(conf_ciclo.getCiclo().getIdOrigenvuelo());
                        ct.setIdTipociclo(conf_ciclo.getCiclo().getIdTipociclo());

                        if (Objects.equals(conf_ciclo.getCiclo().getIdTipociclo().getIdParametro(), idTipoCicloNumerico)
                                && Objects.equals(conf_ciclo.getIdPeriodicidad().getIdParametro(), idPeridicidadMes)) {
                            all_records_Mes.add(cm);
                        }
                        if (Objects.equals(conf_ciclo.getCiclo().getIdTipociclo().getIdParametro(), idTipoCicloNumerico)
                                && Objects.equals(conf_ciclo.getIdPeriodicidad().getIdParametro(), idPeriodicidadQuincenal)) {
                            all_records_Quincena.add(cq);
                        }
                        if (Objects.equals(conf_ciclo.getCiclo().getIdTipociclo().getIdParametro(), idTipoCicloTemporada)) {
                            all_records_Temporada.add(ct);
                        }

                    }

                }
            }

            for (CicloMes cm : all_records_Mes) {
                ConfiguracionCiclo entity = new ConfiguracionCiclo();
                entity.setCiclo(servicioFacadeRemote.findByPK(cm.getIdCiclo()));
                ConfiguracionCiclo cc = new ConfiguracionCiclo();
                configuracionCicloFacadeRemote.findAllLike(entity).stream().map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Ene)) {
                        cm.setCicloEnero(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Feb)) {
                        cm.setCicloFebrero(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Mar)) {
                        cm.setCicloMarzo(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Abr)) {
                        cm.setCicloAbril(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_May)) {
                        cm.setCicloMayo(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Jun)) {
                        cm.setCicloJunio(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Jul)) {
                        cm.setCicloJulio(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Ago)) {
                        cm.setCicloAgosto(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Sep)) {
                        cm.setCicloSeptiembre(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Oct)) {
                        cm.setCicloOctubre(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Nov)) {
                        cm.setCicloNoviembre(conf_ciclo);
                    }
                    return conf_ciclo;
                }).filter((conf_ciclo) -> (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Dic))).forEachOrdered((conf_ciclo) -> {
                    cm.setCicloDiciembre(conf_ciclo);
                });

            }

            for (CicloQuincena cq : all_records_Quincena) {
                ConfiguracionCiclo entity = new ConfiguracionCiclo();
                entity.setCiclo(servicioFacadeRemote.findByPK(cq.getIdCiclo()));
                ConfiguracionCiclo cc = new ConfiguracionCiclo();
                configuracionCicloFacadeRemote.findAllLike(entity).stream().map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Ene) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloEneroQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Ene) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloEneroQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Feb) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloFebreroQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Feb) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloFebreroQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Mar) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloMarzoQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Mar) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloMarzoQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Abr) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloAbrilQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Abr) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloAbrilQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_May) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloMayoQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_May) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloMayoQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Jun) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloJunioQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Jun) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloJunioQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Jul) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloJulioQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Jul) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloJulioQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Ago) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloAgostoQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Ago) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloAgostoQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Sep) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloSeptiembreQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Sep) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloSeptiembreQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Oct) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloOctubreQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Oct) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloOctubreQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Nov) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloNoviembreQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Nov) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2)) {
                        cq.setCicloNoviembreQ2(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Dic) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_1)) {
                        cq.setCicloDiciembreQ1(conf_ciclo);
                    }
                    return conf_ciclo;
                }).filter((conf_ciclo) -> (Objects.equals(conf_ciclo.getIdMes().getIdParametro(), mes_Dic) && Objects.equals(conf_ciclo.getIdTipoQuincena().getIdParametro(), quincena_2))).forEachOrdered((conf_ciclo) -> {
                    cq.setCicloDiciembreQ2(conf_ciclo);
                });
            }

            for (CicloTemporada ct : all_records_Temporada) {
                ConfiguracionCiclo entity = new ConfiguracionCiclo();
                entity.setCiclo(servicioFacadeRemote.findByPK(ct.getIdCiclo()));
                configuracionCicloFacadeRemote.findAllLike(entity).stream().map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Invierno) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Inicio)) {
                        ct.setCicloInviernoInicio(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Invierno) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Fin)) {
                        ct.setCicloInviernoFin(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Primavera) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Inicio)) {
                        ct.setCicloPrimaveraInicio(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Primavera) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Fin)) {
                        ct.setCicloPrimaveraFin(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Verano) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Inicio)) {
                        ct.setCicloVeranoInicio(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Verano) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Fin)) {
                        ct.setCicloVeranoFin(conf_ciclo);
                    }
                    return conf_ciclo;
                }).map((conf_ciclo) -> {
                    if (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Otono) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Inicio)) {
                        ct.setCicloOtonoInicio(conf_ciclo);
                    }
                    return conf_ciclo;
                }).filter((conf_ciclo) -> (Objects.equals(conf_ciclo.getIdTemporada().getIdParametro(), temp_Otono) && Objects.equals(conf_ciclo.getIdTipoTemporada().getIdParametro(), temp_Fin))).forEachOrdered((conf_ciclo) -> {
                    ct.setCicloOtonoFin(conf_ciclo);
                });
            }

        } catch (Exception ex) {
            all_records_Quincena = null;
        }

    }

    /**
     * Go back to Master Page
     * @return 
     */
    public String returnMaster() {
        String redirect = "/portal/sab/diseno/ciclos?faces-redirect=true";
        disabledEdicion = false;
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     * @return 
     */
    public String addRow() {
        String redirect = "/portal/sab/diseno/ciclosDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Ciclo();
        disabledEdicion = false;
        return redirect;
    }

    public boolean validate(String boron) {
        boolean retorno = true;
        if (asignedOrigen == null) {
            messgeValidateError(boron, "Debe ingresar un Origen ");
            retorno = false;
        }
        if (asignedRegion == -1) {
            messgeValidateError(boron, "Debe ingresar una Región ");
            retorno = false;
        }
        if (asignedEstacion == -1) {
            messgeValidateError(boron, "Debe ingresar una Estación ");
            retorno = false;
        }
        if (asignedTipoCiclo == -1) {
            messgeValidateError(boron, "Debe ingresar un Tipo de Ciclo ");
            retorno = false;
        }
        if (asignedProveedor.equals("-1")) {
            messgeValidateError(boron, "Debe ingresar un Proveedor ");
            retorno = false;
        }

        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {

            //Encabezado
            Ciclo ciclo = null;
            if (validate("form:saveButton")) {
                row_selected.setStatus((short) 1);
                row_selected.setIdOrigenvuelo(ParametroFacadeRemote.findByPK(asignedOrigen));
                row_selected.setRegion(regionesFacadeRemote.findByPK(asignedRegion));
                row_selected.setEstacion(estacionFacadeRemote.findByPK(asignedEstacion));
                row_selected.setDobleAbastecido(isdobleAbastecido ? (short) 1 : (short) 0);
                row_selected.setIdTipociclo(ParametroFacadeRemote.findByPK(asignedTipoCiclo));
                row_selected.setProveedor(proveedorCorporativoFacadeRemote.findByPK(asignedProveedor));
                ciclo = servicioFacadeRemote.create(row_selected);
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }

            //Numerico - Mensual
            if (Objects.equals(asignedTipoCiclo, idTipoCicloNumerico) && Objects.equals(asignedPeriodicidad, idPeridicidadMes)) {
                if (noCiclo_Mes_Numerico_Ene != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Ene, noCiclo_Mes_Numerico_Ene, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Feb != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Feb, noCiclo_Mes_Numerico_Feb, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Mar != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Mar, noCiclo_Mes_Numerico_Mar, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Abr != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Abr, noCiclo_Mes_Numerico_Abr, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_May != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_May, noCiclo_Mes_Numerico_May, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Jun != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Jun, noCiclo_Mes_Numerico_Jun, idPeridicidadMes));
                }

                if (noCiclo_Mes_Numerico_Jul != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Jul, noCiclo_Mes_Numerico_Jul, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Ago != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Ago, noCiclo_Mes_Numerico_Ago, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Sep != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Sep, noCiclo_Mes_Numerico_Sep, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Oct != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Oct, noCiclo_Mes_Numerico_Oct, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Nov != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Nov, noCiclo_Mes_Numerico_Nov, idPeridicidadMes));
                }
                if (noCiclo_Mes_Numerico_Dic != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoMensual(ciclo, mes_Dic, noCiclo_Mes_Numerico_Dic, idPeridicidadMes));
                }

            }
            //Numerico - Quincenal
            if (Objects.equals(asignedTipoCiclo, idTipoCicloNumerico) && Objects.equals(asignedPeriodicidad, idPeriodicidadQuincenal)) {
                if (noCiclo_Quin1_Numerico_Ene != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Ene, quincena_1, noCiclo_Quin1_Numerico_Ene, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Ene != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Ene, quincena_2, noCiclo_Quin2_Numerico_Ene, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Feb != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Feb, quincena_1, noCiclo_Quin1_Numerico_Feb, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Feb != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Feb, quincena_2, noCiclo_Quin2_Numerico_Feb, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Mar != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Mar, quincena_1, noCiclo_Quin1_Numerico_Mar, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Mar != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Mar, quincena_2, noCiclo_Quin2_Numerico_Mar, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Abr != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Abr, quincena_1, noCiclo_Quin1_Numerico_Abr, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Abr != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Abr, quincena_2, noCiclo_Quin2_Numerico_Abr, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_May != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_May, quincena_1, noCiclo_Quin1_Numerico_May, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_May != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_May, quincena_2, noCiclo_Quin2_Numerico_May, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Jun != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Jun, quincena_1, noCiclo_Quin1_Numerico_Jun, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Jun != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Jun, quincena_2, noCiclo_Quin2_Numerico_Jun, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Jul != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Jul, quincena_1, noCiclo_Quin1_Numerico_Jul, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Jul != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Jul, quincena_2, noCiclo_Quin2_Numerico_Jul, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Ago != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Ago, quincena_1, noCiclo_Quin1_Numerico_Ago, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Ago != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Ago, quincena_2, noCiclo_Quin2_Numerico_Ago, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Sep != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Sep, quincena_1, noCiclo_Quin1_Numerico_Sep, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Sep != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Sep, quincena_2, noCiclo_Quin2_Numerico_Sep, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Oct != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Oct, quincena_1, noCiclo_Quin1_Numerico_Oct, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Oct != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Oct, quincena_2, noCiclo_Quin2_Numerico_Oct, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Nov != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Nov, quincena_1, noCiclo_Quin1_Numerico_Nov, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Nov != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Nov, quincena_2, noCiclo_Quin2_Numerico_Nov, idPeriodicidadQuincenal));
                }

                if (noCiclo_Quin1_Numerico_Dic != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Dic, quincena_1, noCiclo_Quin1_Numerico_Dic, idPeriodicidadQuincenal));
                }
                if (noCiclo_Quin2_Numerico_Dic != -1) {
                    configuracionCicloFacadeRemote.create(getCicloNumericoQuincenal(ciclo, mes_Dic, quincena_2, noCiclo_Quin2_Numerico_Dic, idPeriodicidadQuincenal));
                }
            }

            //Temporada
            if (asignedTipoCiclo == idTipoCicloTemporada) {
                if (asignedInviernoIni != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Invierno, temp_Inicio, asignedInviernoIni, idPeriodicidadQuincenal));
                }
                if (asignedInviernoFin != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Invierno, temp_Fin, asignedInviernoFin, idPeriodicidadQuincenal));
                }

                if (asignedPrimaveraIni != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Primavera, temp_Inicio, asignedPrimaveraIni, idPeriodicidadQuincenal));
                }
                if (asignedPrimaveraFin != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Primavera, temp_Fin, asignedPrimaveraFin, idPeriodicidadQuincenal));
                }

                if (asignedVeranoIni != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Verano, temp_Inicio, asignedVeranoIni, idPeriodicidadQuincenal));
                }
                if (asignedVeranoFin != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Verano, temp_Fin, asignedVeranoFin, idPeriodicidadQuincenal));
                }

                if (asignedOtonoIni != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Otono, temp_Inicio, asignedOtonoIni, idPeriodicidadQuincenal));
                }
                if (asignedOtonoFin != -1) {
                    configuracionCicloFacadeRemote.create(getCicloTemporada(ciclo, temp_Otono, temp_Fin, asignedOtonoFin, idPeriodicidadQuincenal));
                }
            }

        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void update() {
        try {
            disabledEdicion = false;
            //Encabezado
            Ciclo ciclo = row_selected;
            if (validate("form:updateButton")) {
                row_selected.setDobleAbastecido(isdobleAbastecido ? (short) 1 : (short) 0);
                servicioFacadeRemote.update(row_selected);
                messgeValidateInfo("form:updateButton", "Registro Modificado correctamente ");
            }

            //Numerico - Mensual
            ConfiguracionCiclo entity = new ConfiguracionCiclo();
            entity.setCiclo(ciclo);
            for (ConfiguracionCiclo conf_ciclo : configuracionCicloFacadeRemote.findAllLike(entity)) {
                if (asignedTipoCiclo == idTipoCicloNumerico && asignedPeriodicidad == idPeridicidadMes) {
                    if (noCiclo_Mes_Numerico_Ene != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Ene) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Ene, noCiclo_Mes_Numerico_Ene, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Feb != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Feb) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Feb, noCiclo_Mes_Numerico_Feb, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Mar != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Mar) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Mar, noCiclo_Mes_Numerico_Mar, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Abr != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Abr) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Abr, noCiclo_Mes_Numerico_Abr, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_May != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_May) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_May, noCiclo_Mes_Numerico_May, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Jun != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Jun) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Jun, noCiclo_Mes_Numerico_Jun, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Jul != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Jul) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Jul, noCiclo_Mes_Numerico_Jul, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Ago != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Ago) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Ago, noCiclo_Mes_Numerico_Ago, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Sep != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Sep) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Sep, noCiclo_Mes_Numerico_Sep, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Oct != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Oct) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Oct, noCiclo_Mes_Numerico_Oct, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Nov != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Nov) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Nov, noCiclo_Mes_Numerico_Nov, idPeridicidadMes));
                    }
                    if (noCiclo_Mes_Numerico_Dic != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Dic) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoMensualUpdate(conf_ciclo, ciclo, mes_Dic, noCiclo_Mes_Numerico_Dic, idPeridicidadMes));
                    }
                }

                //Numerico - Quincenal
                if (asignedTipoCiclo == idTipoCicloNumerico && asignedPeriodicidad == idPeriodicidadQuincenal) {
                    if (noCiclo_Quin1_Numerico_Ene != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Ene && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Ene, quincena_1, noCiclo_Quin1_Numerico_Ene, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Ene != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Ene && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Ene, quincena_2, noCiclo_Quin2_Numerico_Ene, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Feb != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Feb && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Feb, quincena_1, noCiclo_Quin1_Numerico_Feb, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Feb != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Feb && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Feb, quincena_2, noCiclo_Quin2_Numerico_Feb, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Mar != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Mar && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Mar, quincena_1, noCiclo_Quin1_Numerico_Mar, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Mar != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Mar && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Mar, quincena_2, noCiclo_Quin2_Numerico_Mar, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Abr != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Abr && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Abr, quincena_1, noCiclo_Quin1_Numerico_Abr, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Abr != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Abr && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Abr, quincena_2, noCiclo_Quin2_Numerico_Abr, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_May != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_May && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_May, quincena_1, noCiclo_Quin1_Numerico_May, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_May != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_May && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_May, quincena_2, noCiclo_Quin2_Numerico_May, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Jun != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Jun && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Jun, quincena_1, noCiclo_Quin1_Numerico_Jun, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Jun != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Jun && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Jun, quincena_2, noCiclo_Quin2_Numerico_Jun, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Jul != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Jul && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Jul, quincena_1, noCiclo_Quin1_Numerico_Jul, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Jul != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Jul && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Jul, quincena_2, noCiclo_Quin2_Numerico_Jul, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Ago != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Ago && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Ago, quincena_1, noCiclo_Quin1_Numerico_Ago, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Ago != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Ago && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Ago, quincena_2, noCiclo_Quin2_Numerico_Ago, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Sep != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Sep && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Sep, quincena_1, noCiclo_Quin1_Numerico_Sep, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Sep != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Sep && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Sep, quincena_2, noCiclo_Quin2_Numerico_Sep, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Oct != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Oct && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Oct, quincena_1, noCiclo_Quin1_Numerico_Oct, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Oct != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Oct && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Oct, quincena_2, noCiclo_Quin2_Numerico_Oct, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Nov != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Nov && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Nov, quincena_1, noCiclo_Quin1_Numerico_Nov, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Nov != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Nov && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Nov, quincena_2, noCiclo_Quin2_Numerico_Nov, idPeriodicidadQuincenal));
                    }

                    if (noCiclo_Quin1_Numerico_Dic != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Dic && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_1) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Dic, quincena_1, noCiclo_Quin1_Numerico_Dic, idPeriodicidadQuincenal));
                    }
                    if (noCiclo_Quin2_Numerico_Dic != -1 && conf_ciclo.getIdMes().getIdParametro() == mes_Dic && conf_ciclo.getIdTipoQuincena().getIdParametro() == quincena_2) {
                        configuracionCicloFacadeRemote.update(getCicloNumericoQuincenalUpdate(conf_ciclo, ciclo, mes_Dic, quincena_2, noCiclo_Quin2_Numerico_Dic, idPeriodicidadQuincenal));
                    }
                }

                //Temporada
                if (asignedTipoCiclo == idTipoCicloTemporada) {
                    if (asignedInviernoIni != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Invierno && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Inicio ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Invierno, temp_Inicio, asignedInviernoIni, idPeriodicidadQuincenal));
                    }
                    if (asignedInviernoFin != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Invierno && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Fin ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Invierno, temp_Fin, asignedInviernoFin, idPeriodicidadQuincenal));
                    }

                    if (asignedPrimaveraIni != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Primavera && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Inicio ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Primavera, temp_Inicio, asignedPrimaveraIni, idPeriodicidadQuincenal));
                    }
                    if (asignedPrimaveraFin != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Primavera && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Fin ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Primavera, temp_Fin, asignedPrimaveraFin, idPeriodicidadQuincenal));
                    }

                    if (asignedVeranoIni != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Verano && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Inicio ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Verano, temp_Inicio, asignedVeranoIni, idPeriodicidadQuincenal));
                    }
                    if (asignedVeranoFin != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Verano && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Fin ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Verano, temp_Fin, asignedVeranoFin, idPeriodicidadQuincenal));
                    }

                    if (asignedOtonoIni != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Otono && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Inicio ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Otono, temp_Inicio, asignedOtonoIni, idPeriodicidadQuincenal));
                    }
                    if (asignedOtonoFin != -1 && conf_ciclo.getIdTemporada().getIdParametro() == temp_Otono && conf_ciclo.getIdTipoTemporada().getIdParametro() ==  temp_Fin ) {
                        configuracionCicloFacadeRemote.update(getCicloTemporadaUpdate(conf_ciclo, ciclo, temp_Otono, temp_Fin, asignedOtonoFin, idPeriodicidadQuincenal));
                    }
                }

            }

        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public ConfiguracionCiclo getCicloTemporada(Ciclo ciclo, Integer temporada, Integer inicioFin, Integer noCiclo, Integer periodicidad) {
        ConfiguracionCiclo conf = new ConfiguracionCiclo();

        conf.setIdNociclo(ParametroFacadeRemote.findByPK(noCiclo)); //1, 2, 3, 4
        conf.setIdPeriodicidad(ParametroFacadeRemote.findByPK(periodicidad)); // Temporada
        conf.setCiclo(ciclo);
        conf.setIdTemporada(ParametroFacadeRemote.findByPK(temporada));//Primavera, Verano
        conf.setIdTipoTemporada(ParametroFacadeRemote.findByPK(inicioFin));//inicio, Fin
        return conf;

    }

    public ConfiguracionCiclo getCicloTemporadaUpdate(ConfiguracionCiclo conf, Ciclo ciclo, Integer temporada, Integer inicioFin, Integer noCiclo, Integer periodicidad) {
        conf.setIdNociclo(ParametroFacadeRemote.findByPK(noCiclo)); //1, 2, 3, 4
        return conf;
    }

    public ConfiguracionCiclo getCicloNumericoMensual(Ciclo ciclo, Integer mes, Integer noCiclo, Integer periodicidad) {
        ConfiguracionCiclo conf = new ConfiguracionCiclo();

        conf.setIdNociclo(ParametroFacadeRemote.findByPK(noCiclo)); //1, 2, 3, 4
        conf.setIdPeriodicidad(ParametroFacadeRemote.findByPK(periodicidad)); // Mensul o Quincenal
        conf.setCiclo(ciclo);
        conf.setIdMes(ParametroFacadeRemote.findByPK(mes));//Ene Feb ..
        return conf;

    }

    public ConfiguracionCiclo getCicloNumericoMensualUpdate(ConfiguracionCiclo conf, Ciclo ciclo, Integer mes, Integer noCiclo, Integer periodicidad) {
        conf.setIdNociclo(ParametroFacadeRemote.findByPK(noCiclo)); //1, 2, 3, 4
        return conf;
    }

    public ConfiguracionCiclo getCicloNumericoQuincenal(Ciclo ciclo, Integer mes, Integer quincena, Integer noCiclo, Integer periodicidad) {
        ConfiguracionCiclo conf = new ConfiguracionCiclo();

        conf.setIdNociclo(ParametroFacadeRemote.findByPK(noCiclo)); //1, 2, 3, 4
        conf.setIdPeriodicidad(ParametroFacadeRemote.findByPK(periodicidad)); // Mensul o Quincenal
        conf.setCiclo(ciclo);
        conf.setIdTipoQuincena(ParametroFacadeRemote.findByPK(quincena)); //quincena1 Quincena 2
        conf.setIdMes(ParametroFacadeRemote.findByPK(mes)); //Ene Feb ..
        return conf;

    }

    public ConfiguracionCiclo getCicloNumericoQuincenalUpdate(ConfiguracionCiclo conf, Ciclo ciclo, Integer mes, Integer quincena, Integer noCiclo, Integer periodicidad) {
        conf.setIdNociclo(ParametroFacadeRemote.findByPK(noCiclo)); //1, 2, 3, 4
        return conf;
    }

    /**
     * Go to edit Row selected Page Detail
     */
    public String modifyRowMes(CicloMes row_selected) {
        String redirect = "/portal/sab/diseno/ciclosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = servicioFacadeRemote.findByPK(row_selected.getIdCiclo());
        Ciclo ciclo = servicioFacadeRemote.findByPK(row_selected.getIdCiclo());
        asignedOrigen = ciclo.getIdOrigenvuelo().getIdParametro();
        asignedRegion = ciclo.getRegion().getIdRegion();
        asignedEstacion = ciclo.getEstacion().getIdEstacion();
        getEstacion(asignedEstacion);
        
        isdobleAbastecido = (ciclo.getDobleAbastecido() == ((short) 0) ? false : true);
        asignedProveedor = ciclo.getProveedor().getClaveProveedor();
        asignedTipoCiclo = ciclo.getIdTipociclo().getIdParametro();
        asignedPeriodicidad = idPeridicidadMes; //Mensual
        noCiclo_Mes_Numerico_Ene = row_selected.getCicloEnero() != null ? row_selected.getCicloEnero().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Feb = row_selected.getCicloFebrero() != null ? row_selected.getCicloFebrero().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Mar = row_selected.getCicloMarzo() != null ? row_selected.getCicloMarzo().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Abr = row_selected.getCicloAbril() != null ? row_selected.getCicloAbril().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_May = row_selected.getCicloMayo() != null ? row_selected.getCicloMayo().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Jun = row_selected.getCicloJunio() != null ? row_selected.getCicloJunio().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Jul = row_selected.getCicloJulio() != null ? row_selected.getCicloJulio().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Ago = row_selected.getCicloAgosto() != null ? row_selected.getCicloAgosto().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Sep = row_selected.getCicloSeptiembre() != null ? row_selected.getCicloSeptiembre().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Oct = row_selected.getCicloOctubre() != null ? row_selected.getCicloOctubre().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Nov = row_selected.getCicloNoviembre() != null ? row_selected.getCicloNoviembre().getIdNociclo().getIdParametro() : -1;
        noCiclo_Mes_Numerico_Dic = row_selected.getCicloDiciembre() != null ? row_selected.getCicloDiciembre().getIdNociclo().getIdParametro() : -1;
        disabledEdicion = true;
        return redirect;
    }

    public String modifyRowQuincena(CicloQuincena row_selected) {
        String redirect = "/portal/sab/diseno/ciclosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = servicioFacadeRemote.findByPK(row_selected.getIdCiclo());
        Ciclo ciclo = servicioFacadeRemote.findByPK(row_selected.getIdCiclo());
        asignedOrigen = ciclo.getIdOrigenvuelo().getIdParametro();
        asignedRegion = ciclo.getRegion().getIdRegion();
        asignedEstacion = ciclo.getEstacion().getIdEstacion();
        getEstacion(asignedEstacion);
        isdobleAbastecido = (ciclo.getDobleAbastecido() == ((short) 0) ? false : true);
        asignedProveedor = ciclo.getProveedor().getClaveProveedor();
        asignedTipoCiclo = ciclo.getIdTipociclo().getIdParametro();
        asignedPeriodicidad = idPeriodicidadQuincenal; //Quincenal
        noCiclo_Quin1_Numerico_Ene = row_selected.getCicloEneroQ1() != null ? row_selected.getCicloEneroQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Ene = row_selected.getCicloEneroQ2() != null ? row_selected.getCicloEneroQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Feb = row_selected.getCicloFebreroQ1() != null ? row_selected.getCicloFebreroQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Feb = row_selected.getCicloFebreroQ2() != null ? row_selected.getCicloFebreroQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Mar = row_selected.getCicloMarzoQ1() != null ? row_selected.getCicloMarzoQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Mar = row_selected.getCicloMarzoQ2() != null ? row_selected.getCicloMarzoQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Abr = row_selected.getCicloAbrilQ1() != null ? row_selected.getCicloAbrilQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Abr = row_selected.getCicloAbrilQ2() != null ? row_selected.getCicloAbrilQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_May = row_selected.getCicloMayoQ1() != null ? row_selected.getCicloMayoQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_May = row_selected.getCicloMayoQ2() != null ? row_selected.getCicloMayoQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Jun = row_selected.getCicloJunioQ1() != null ? row_selected.getCicloJunioQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Jun = row_selected.getCicloJunioQ2() != null ? row_selected.getCicloJunioQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Jul = row_selected.getCicloJulioQ1() != null ? row_selected.getCicloJulioQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Jul = row_selected.getCicloJulioQ2() != null ? row_selected.getCicloJulioQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Ago = row_selected.getCicloAgostoQ1() != null ? row_selected.getCicloAgostoQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Ago = row_selected.getCicloAgostoQ2() != null ? row_selected.getCicloAgostoQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Sep = row_selected.getCicloSeptiembreQ1() != null ? row_selected.getCicloSeptiembreQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Sep = row_selected.getCicloSeptiembreQ2() != null ? row_selected.getCicloSeptiembreQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Oct = row_selected.getCicloOctubreQ1() != null ? row_selected.getCicloOctubreQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Oct = row_selected.getCicloOctubreQ2() != null ? row_selected.getCicloOctubreQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Nov = row_selected.getCicloNoviembreQ1() != null ? row_selected.getCicloNoviembreQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Nov = row_selected.getCicloNoviembreQ2() != null ? row_selected.getCicloNoviembreQ2().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin1_Numerico_Dic = row_selected.getCicloDiciembreQ1() != null ? row_selected.getCicloDiciembreQ1().getIdNociclo().getIdParametro() : -1;
        noCiclo_Quin2_Numerico_Dic = row_selected.getCicloDiciembreQ2() != null ? row_selected.getCicloDiciembreQ2().getIdNociclo().getIdParametro() : -1;
        disabledEdicion = true;

        return redirect;
    }

    public String modifyRowTemporada(CicloTemporada row_selected) {
        String redirect = "/portal/sab/diseno/ciclosDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = servicioFacadeRemote.findByPK(row_selected.getIdCiclo());
        Ciclo ciclo = servicioFacadeRemote.findByPK(row_selected.getIdCiclo());
        asignedOrigen = ciclo.getIdOrigenvuelo().getIdParametro();
        asignedEstacion = ciclo.getEstacion().getIdEstacion();
        getEstacion(asignedEstacion);
        asignedEstacion = ciclo.getEstacion().getIdEstacion();
        isdobleAbastecido = (ciclo.getDobleAbastecido() == ((short) 0) ? false : true);
        asignedProveedor = ciclo.getProveedor().getClaveProveedor();
        asignedTipoCiclo = ciclo.getIdTipociclo().getIdParametro(); // Temporada

        asignedInviernoIni = row_selected.getCicloInviernoInicio() != null ? row_selected.getCicloInviernoInicio().getIdNociclo().getIdParametro() : -1;
        asignedInviernoFin = row_selected.getCicloInviernoFin() != null ? row_selected.getCicloInviernoFin().getIdNociclo().getIdParametro() : -1;
        asignedPrimaveraIni = row_selected.getCicloPrimaveraInicio() != null ? row_selected.getCicloPrimaveraInicio().getIdNociclo().getIdParametro() : -1;
        asignedPrimaveraFin = row_selected.getCicloPrimaveraFin() != null ? row_selected.getCicloPrimaveraFin().getIdNociclo().getIdParametro() : -1;
        asignedVeranoIni = row_selected.getCicloVeranoInicio() != null ? row_selected.getCicloVeranoInicio().getIdNociclo().getIdParametro() : -1;
        asignedVeranoFin = row_selected.getCicloVeranoFin() != null ? row_selected.getCicloVeranoFin().getIdNociclo().getIdParametro() : -1;
        asignedOtonoIni = row_selected.getCicloOtonoInicio() != null ? row_selected.getCicloOtonoInicio().getIdNociclo().getIdParametro() : -1;
        asignedOtonoFin = row_selected.getCicloOtonoFin() != null ? row_selected.getCicloOtonoFin().getIdNociclo().getIdParametro() : -1;
        disabledEdicion = true;
        return redirect;
    }

    public Ciclo getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Ciclo row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedTipoCiclo() {
        return asignedTipoCiclo;
    }

    public void setAsignedTipoCiclo(Integer asignedTipoCiclo) {
        this.asignedTipoCiclo = asignedTipoCiclo;
    }

    public Integer getAsignedPeriodicidad() {
        return asignedPeriodicidad;
    }

    public void setAsignedPeriodicidad(Integer asignedPeriodicidad) {
        this.asignedPeriodicidad = asignedPeriodicidad;
    }

    public List<Ciclo> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Ciclo> all_records) {
        this.all_records = all_records;
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

    public Integer getAsignedEstacion() {
        return asignedEstacion;
    }

    public void setAsignedEstacion(Integer asignedEstacion) {
        this.asignedEstacion = asignedEstacion;
    }

    public List<SelectItem> getSelectEstaciones() {
        return selectEstaciones;
    }

    public void setSelectEstaciones(List<SelectItem> selectEstaciones) {
        this.selectEstaciones = selectEstaciones;
    }

    public String getAsignedProveedor() {
        return asignedProveedor;
    }

    public void setAsignedProveedor(String asignedProveedor) {
        this.asignedProveedor = asignedProveedor;
    }

    public List<SelectItem> getSelectProveedores() { 
        return selectProveedores;
    }

    public void setSelectProveedores(List<SelectItem> selectProveedores) {
        this.selectProveedores = selectProveedores;
    }

    public List<SelectItem> getSelectRevision() {
        return selectRevision;
    }

    public void setSelectRevision(List<SelectItem> selectRevision) {
        this.selectRevision = selectRevision;
    }

    public Integer getAsignedInviernoIni() {
        return asignedInviernoIni;
    }

    public void setAsignedInviernoIni(Integer asignedInviernoIni) {
        this.asignedInviernoIni = asignedInviernoIni;
    }

    public Integer getAsignedInviernoFin() {
        return asignedInviernoFin;
    }

    public void setAsignedInviernoFin(Integer asignedInviernoFin) {
        this.asignedInviernoFin = asignedInviernoFin;
    }

    public Integer getAsignedPrimaveraIni() {
        return asignedPrimaveraIni;
    }

    public void setAsignedPrimaveraIni(Integer asignedPrimaveraIni) {
        this.asignedPrimaveraIni = asignedPrimaveraIni;
    }

    public Integer getAsignedPrimaveraFin() {
        return asignedPrimaveraFin;
    }

    public void setAsignedPrimaveraFin(Integer asignedPrimaveraFin) {
        this.asignedPrimaveraFin = asignedPrimaveraFin;
    }

    public Integer getAsignedVeranoIni() {
        return asignedVeranoIni;
    }

    public void setAsignedVeranoIni(Integer asignedVeranoIni) {
        this.asignedVeranoIni = asignedVeranoIni;
    }

    public Integer getAsignedVeranoFin() {
        return asignedVeranoFin;
    }

    public void setAsignedVeranoFin(Integer asignedVeranoFin) {
        this.asignedVeranoFin = asignedVeranoFin;
    }

    public Integer getAsignedOtonoIni() {
        return asignedOtonoIni;
    }

    public void setAsignedOtonoIni(Integer asignedOtonoIni) {
        this.asignedOtonoIni = asignedOtonoIni;
    }

    public Integer getAsignedOtonoFin() {
        return asignedOtonoFin;
    }

    public void setAsignedOtonoFin(Integer asignedOtonoFin) {
        this.asignedOtonoFin = asignedOtonoFin;
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

    public boolean isIsdobleAbastecido() {
        return isdobleAbastecido;
    }

    public void setIsdobleAbastecido(boolean isdobleAbastecido) {
        this.isdobleAbastecido = isdobleAbastecido;
    }

    public List<SelectItem> getSelectTipoCiclos() {
        return selectTipoCiclos;
    }

    public void setSelectTipoCiclos(List<SelectItem> selectTipoCiclos) {
        this.selectTipoCiclos = selectTipoCiclos;
    }

    public Integer getIdTipoCicloNumerico() {
        return idTipoCicloNumerico;
    }

    public void setIdTipoCicloNumerico(Integer idTipoCicloNumerico) {
        this.idTipoCicloNumerico = idTipoCicloNumerico;
    }

    public Integer getIdTipoCicloTemporada() {
        return idTipoCicloTemporada;
    }

    public void setIdTipoCicloTemporada(Integer idTipoCicloTemporada) {
        this.idTipoCicloTemporada = idTipoCicloTemporada;
    }

    public Integer getIdPeridicidadMes() {
        return idPeridicidadMes;
    }

    public void setIdPeridicidadMes(Integer idPeridicidadMes) {
        this.idPeridicidadMes = idPeridicidadMes;
    }

    public Integer getIdPeriodicidadQuincenal() {
        return idPeriodicidadQuincenal;
    }

    public void setIdPeriodicidadQuincenal(Integer idPeriodicidadQuincenal) {
        this.idPeriodicidadQuincenal = idPeriodicidadQuincenal;
    }

    public List<SelectItem> getSelectPeriodicidades() {
        return selectPeriodicidades;
    }

    public void setSelectPeriodicidades(List<SelectItem> selectPeriodicidades) {
        this.selectPeriodicidades = selectPeriodicidades;
    }

    public List<SelectItem> getSelectNoCiclos() {
        return selectNoCiclos;
    }

    public void setSelectNoCiclos(List<SelectItem> selectNoCiclos) {
        this.selectNoCiclos = selectNoCiclos;
    }

    public Integer getNoCiclo_Mes_Numerico_Ene() {
        return noCiclo_Mes_Numerico_Ene;
    }

    public void setNoCiclo_Mes_Numerico_Ene(Integer noCiclo_Mes_Numerico_Ene) {
        this.noCiclo_Mes_Numerico_Ene = noCiclo_Mes_Numerico_Ene;
    }

    public Integer getNoCiclo_Mes_Numerico_Jul() {
        return noCiclo_Mes_Numerico_Jul;
    }

    public void setNoCiclo_Mes_Numerico_Jul(Integer noCiclo_Mes_Numerico_Jul) {
        this.noCiclo_Mes_Numerico_Jul = noCiclo_Mes_Numerico_Jul;
    }

    public Integer getNoCiclo_Mes_Numerico_Feb() {
        return noCiclo_Mes_Numerico_Feb;
    }

    public void setNoCiclo_Mes_Numerico_Feb(Integer noCiclo_Mes_Numerico_Feb) {
        this.noCiclo_Mes_Numerico_Feb = noCiclo_Mes_Numerico_Feb;
    }

    public Integer getNoCiclo_Mes_Numerico_Ago() {
        return noCiclo_Mes_Numerico_Ago;
    }

    public void setNoCiclo_Mes_Numerico_Ago(Integer noCiclo_Mes_Numerico_Ago) {
        this.noCiclo_Mes_Numerico_Ago = noCiclo_Mes_Numerico_Ago;
    }

    public Integer getNoCiclo_Mes_Numerico_Mar() {
        return noCiclo_Mes_Numerico_Mar;
    }

    public void setNoCiclo_Mes_Numerico_Mar(Integer noCiclo_Mes_Numerico_Mar) {
        this.noCiclo_Mes_Numerico_Mar = noCiclo_Mes_Numerico_Mar;
    }

    public Integer getNoCiclo_Mes_Numerico_Sep() {
        return noCiclo_Mes_Numerico_Sep;
    }

    public void setNoCiclo_Mes_Numerico_Sep(Integer noCiclo_Mes_Numerico_Sep) {
        this.noCiclo_Mes_Numerico_Sep = noCiclo_Mes_Numerico_Sep;
    }

    public Integer getNoCiclo_Mes_Numerico_Abr() {
        return noCiclo_Mes_Numerico_Abr;
    }

    public void setNoCiclo_Mes_Numerico_Abr(Integer noCiclo_Mes_Numerico_Abr) {
        this.noCiclo_Mes_Numerico_Abr = noCiclo_Mes_Numerico_Abr;
    }

    public Integer getNoCiclo_Mes_Numerico_Oct() {
        return noCiclo_Mes_Numerico_Oct;
    }

    public void setNoCiclo_Mes_Numerico_Oct(Integer noCiclo_Mes_Numerico_Oct) {
        this.noCiclo_Mes_Numerico_Oct = noCiclo_Mes_Numerico_Oct;
    }

    public Integer getNoCiclo_Mes_Numerico_May() {
        return noCiclo_Mes_Numerico_May;
    }

    public void setNoCiclo_Mes_Numerico_May(Integer noCiclo_Mes_Numerico_May) {
        this.noCiclo_Mes_Numerico_May = noCiclo_Mes_Numerico_May;
    }

    public Integer getNoCiclo_Mes_Numerico_Nov() {
        return noCiclo_Mes_Numerico_Nov;
    }

    public void setNoCiclo_Mes_Numerico_Nov(Integer noCiclo_Mes_Numerico_Nov) {
        this.noCiclo_Mes_Numerico_Nov = noCiclo_Mes_Numerico_Nov;
    }

    public Integer getNoCiclo_Mes_Numerico_Jun() {
        return noCiclo_Mes_Numerico_Jun;
    }

    public void setNoCiclo_Mes_Numerico_Jun(Integer noCiclo_Mes_Numerico_Jun) {
        this.noCiclo_Mes_Numerico_Jun = noCiclo_Mes_Numerico_Jun;
    }

    public Integer getNoCiclo_Mes_Numerico_Dic() {
        return noCiclo_Mes_Numerico_Dic;
    }

    public void setNoCiclo_Mes_Numerico_Dic(Integer noCiclo_Mes_Numerico_Dic) {
        this.noCiclo_Mes_Numerico_Dic = noCiclo_Mes_Numerico_Dic;
    }

    public Integer getNoCiclo_Quin1_Numerico_Ene() {
        return noCiclo_Quin1_Numerico_Ene;
    }

    public void setNoCiclo_Quin1_Numerico_Ene(Integer noCiclo_Quin1_Numerico_Ene) {
        this.noCiclo_Quin1_Numerico_Ene = noCiclo_Quin1_Numerico_Ene;
    }

    public Integer getNoCiclo_Quin2_Numerico_Ene() {
        return noCiclo_Quin2_Numerico_Ene;
    }

    public void setNoCiclo_Quin2_Numerico_Ene(Integer noCiclo_Quin2_Numerico_Ene) {
        this.noCiclo_Quin2_Numerico_Ene = noCiclo_Quin2_Numerico_Ene;
    }

    public Integer getNoCiclo_Quin1_Numerico_Jul() {
        return noCiclo_Quin1_Numerico_Jul;
    }

    public void setNoCiclo_Quin1_Numerico_Jul(Integer noCiclo_Quin1_Numerico_Jul) {
        this.noCiclo_Quin1_Numerico_Jul = noCiclo_Quin1_Numerico_Jul;
    }

    public Integer getNoCiclo_Quin2_Numerico_Jul() {
        return noCiclo_Quin2_Numerico_Jul;
    }

    public void setNoCiclo_Quin2_Numerico_Jul(Integer noCiclo_Quin2_Numerico_Jul) {
        this.noCiclo_Quin2_Numerico_Jul = noCiclo_Quin2_Numerico_Jul;
    }

    public Integer getNoCiclo_Quin1_Numerico_Feb() {
        return noCiclo_Quin1_Numerico_Feb;
    }

    public void setNoCiclo_Quin1_Numerico_Feb(Integer noCiclo_Quin1_Numerico_Feb) {
        this.noCiclo_Quin1_Numerico_Feb = noCiclo_Quin1_Numerico_Feb;
    }

    public Integer getNoCiclo_Quin2_Numerico_Feb() {
        return noCiclo_Quin2_Numerico_Feb;
    }

    public void setNoCiclo_Quin2_Numerico_Feb(Integer noCiclo_Quin2_Numerico_Feb) {
        this.noCiclo_Quin2_Numerico_Feb = noCiclo_Quin2_Numerico_Feb;
    }

    public Integer getNoCiclo_Quin1_Numerico_Ago() {
        return noCiclo_Quin1_Numerico_Ago;
    }

    public void setNoCiclo_Quin1_Numerico_Ago(Integer noCiclo_Quin1_Numerico_Ago) {
        this.noCiclo_Quin1_Numerico_Ago = noCiclo_Quin1_Numerico_Ago;
    }

    public Integer getNoCiclo_Quin2_Numerico_Ago() {
        return noCiclo_Quin2_Numerico_Ago;
    }

    public void setNoCiclo_Quin2_Numerico_Ago(Integer noCiclo_Quin2_Numerico_Ago) {
        this.noCiclo_Quin2_Numerico_Ago = noCiclo_Quin2_Numerico_Ago;
    }

    public Integer getNoCiclo_Quin1_Numerico_Mar() {
        return noCiclo_Quin1_Numerico_Mar;
    }

    public void setNoCiclo_Quin1_Numerico_Mar(Integer noCiclo_Quin1_Numerico_Mar) {
        this.noCiclo_Quin1_Numerico_Mar = noCiclo_Quin1_Numerico_Mar;
    }

    public Integer getNoCiclo_Quin2_Numerico_Mar() {
        return noCiclo_Quin2_Numerico_Mar;
    }

    public void setNoCiclo_Quin2_Numerico_Mar(Integer noCiclo_Quin2_Numerico_Mar) {
        this.noCiclo_Quin2_Numerico_Mar = noCiclo_Quin2_Numerico_Mar;
    }

    public Integer getNoCiclo_Quin1_Numerico_Sep() {
        return noCiclo_Quin1_Numerico_Sep;
    }

    public void setNoCiclo_Quin1_Numerico_Sep(Integer noCiclo_Quin1_Numerico_Sep) {
        this.noCiclo_Quin1_Numerico_Sep = noCiclo_Quin1_Numerico_Sep;
    }

    public Integer getNoCiclo_Quin2_Numerico_Sep() {
        return noCiclo_Quin2_Numerico_Sep;
    }

    public void setNoCiclo_Quin2_Numerico_Sep(Integer noCiclo_Quin2_Numerico_Sep) {
        this.noCiclo_Quin2_Numerico_Sep = noCiclo_Quin2_Numerico_Sep;
    }

    public Integer getNoCiclo_Quin1_Numerico_Abr() {
        return noCiclo_Quin1_Numerico_Abr;
    }

    public void setNoCiclo_Quin1_Numerico_Abr(Integer noCiclo_Quin1_Numerico_Abr) {
        this.noCiclo_Quin1_Numerico_Abr = noCiclo_Quin1_Numerico_Abr;
    }

    public Integer getNoCiclo_Quin2_Numerico_Abr() {
        return noCiclo_Quin2_Numerico_Abr;
    }

    public void setNoCiclo_Quin2_Numerico_Abr(Integer noCiclo_Quin2_Numerico_Abr) {
        this.noCiclo_Quin2_Numerico_Abr = noCiclo_Quin2_Numerico_Abr;
    }

    public Integer getNoCiclo_Quin1_Numerico_Oct() {
        return noCiclo_Quin1_Numerico_Oct;
    }

    public void setNoCiclo_Quin1_Numerico_Oct(Integer noCiclo_Quin1_Numerico_Oct) {
        this.noCiclo_Quin1_Numerico_Oct = noCiclo_Quin1_Numerico_Oct;
    }

    public Integer getNoCiclo_Quin2_Numerico_Oct() {
        return noCiclo_Quin2_Numerico_Oct;
    }

    public void setNoCiclo_Quin2_Numerico_Oct(Integer noCiclo_Quin2_Numerico_Oct) {
        this.noCiclo_Quin2_Numerico_Oct = noCiclo_Quin2_Numerico_Oct;
    }

    public Integer getNoCiclo_Quin1_Numerico_May() {
        return noCiclo_Quin1_Numerico_May;
    }

    public void setNoCiclo_Quin1_Numerico_May(Integer noCiclo_Quin1_Numerico_May) {
        this.noCiclo_Quin1_Numerico_May = noCiclo_Quin1_Numerico_May;
    }

    public Integer getNoCiclo_Quin2_Numerico_May() {
        return noCiclo_Quin2_Numerico_May;
    }

    public void setNoCiclo_Quin2_Numerico_May(Integer noCiclo_Quin2_Numerico_May) {
        this.noCiclo_Quin2_Numerico_May = noCiclo_Quin2_Numerico_May;
    }

    public Integer getNoCiclo_Quin1_Numerico_Nov() {
        return noCiclo_Quin1_Numerico_Nov;
    }

    public void setNoCiclo_Quin1_Numerico_Nov(Integer noCiclo_Quin1_Numerico_Nov) {
        this.noCiclo_Quin1_Numerico_Nov = noCiclo_Quin1_Numerico_Nov;
    }

    public Integer getNoCiclo_Quin2_Numerico_Nov() {
        return noCiclo_Quin2_Numerico_Nov;
    }

    public void setNoCiclo_Quin2_Numerico_Nov(Integer noCiclo_Quin2_Numerico_Nov) {
        this.noCiclo_Quin2_Numerico_Nov = noCiclo_Quin2_Numerico_Nov;
    }

    public Integer getNoCiclo_Quin1_Numerico_Jun() {
        return noCiclo_Quin1_Numerico_Jun;
    }

    public void setNoCiclo_Quin1_Numerico_Jun(Integer noCiclo_Quin1_Numerico_Jun) {
        this.noCiclo_Quin1_Numerico_Jun = noCiclo_Quin1_Numerico_Jun;
    }

    public Integer getNoCiclo_Quin2_Numerico_Jun() {
        return noCiclo_Quin2_Numerico_Jun;
    }

    public void setNoCiclo_Quin2_Numerico_Jun(Integer noCiclo_Quin2_Numerico_Jun) {
        this.noCiclo_Quin2_Numerico_Jun = noCiclo_Quin2_Numerico_Jun;
    }

    public Integer getNoCiclo_Quin1_Numerico_Dic() {
        return noCiclo_Quin1_Numerico_Dic;
    }

    public void setNoCiclo_Quin1_Numerico_Dic(Integer noCiclo_Quin1_Numerico_Dic) {
        this.noCiclo_Quin1_Numerico_Dic = noCiclo_Quin1_Numerico_Dic;
    }

    public Integer getNoCiclo_Quin2_Numerico_Dic() {
        return noCiclo_Quin2_Numerico_Dic;
    }

    public void setNoCiclo_Quin2_Numerico_Dic(Integer noCiclo_Quin2_Numerico_Dic) {
        this.noCiclo_Quin2_Numerico_Dic = noCiclo_Quin2_Numerico_Dic;
    }

    public Integer getMes_Ene() {
        return mes_Ene;
    }

    public void setMes_Ene(Integer mes_Ene) {
        this.mes_Ene = mes_Ene;
    }

    public Integer getMes_Feb() {
        return mes_Feb;
    }

    public void setMes_Feb(Integer mes_Feb) {
        this.mes_Feb = mes_Feb;
    }

    public Integer getMes_Mar() {
        return mes_Mar;
    }

    public void setMes_Mar(Integer mes_Mar) {
        this.mes_Mar = mes_Mar;
    }

    public Integer getMes_Abr() {
        return mes_Abr;
    }

    public void setMes_Abr(Integer mes_Abr) {
        this.mes_Abr = mes_Abr;
    }

    public Integer getMes_May() {
        return mes_May;
    }

    public void setMes_May(Integer mes_May) {
        this.mes_May = mes_May;
    }

    public Integer getMes_Jun() {
        return mes_Jun;
    }

    public void setMes_Jun(Integer mes_Jun) {
        this.mes_Jun = mes_Jun;
    }

    public Integer getMes_Jul() {
        return mes_Jul;
    }

    public void setMes_Jul(Integer mes_Jul) {
        this.mes_Jul = mes_Jul;
    }

    public Integer getMes_Ago() {
        return mes_Ago;
    }

    public void setMes_Ago(Integer mes_Ago) {
        this.mes_Ago = mes_Ago;
    }

    public Integer getMes_Sep() {
        return mes_Sep;
    }

    public void setMes_Sep(Integer mes_Sep) {
        this.mes_Sep = mes_Sep;
    }

    public Integer getMes_Oct() {
        return mes_Oct;
    }

    public void setMes_Oct(Integer mes_Oct) {
        this.mes_Oct = mes_Oct;
    }

    public Integer getMes_Nov() {
        return mes_Nov;
    }

    public void setMes_Nov(Integer mes_Nov) {
        this.mes_Nov = mes_Nov;
    }

    public Integer getMes_Dic() {
        return mes_Dic;
    }

    public void setMes_Dic(Integer mes_Dic) {
        this.mes_Dic = mes_Dic;
    }

    public Integer getQuincena_1() {
        return quincena_1;
    }

    public void setQuincena_1(Integer quincena_1) {
        this.quincena_1 = quincena_1;
    }

    public Integer getQuincena_2() {
        return quincena_2;
    }

    public void setQuincena_2(Integer quincena_2) {
        this.quincena_2 = quincena_2;
    }

    public Integer getTemp_Invierno() {
        return temp_Invierno;
    }

    public void setTemp_Invierno(Integer temp_Invierno) {
        this.temp_Invierno = temp_Invierno;
    }

    public Integer getTemp_Primavera() {
        return temp_Primavera;
    }

    public void setTemp_Primavera(Integer temp_Primavera) {
        this.temp_Primavera = temp_Primavera;
    }

    public Integer getTemp_Verano() {
        return temp_Verano;
    }

    public void setTemp_Verano(Integer temp_Verano) {
        this.temp_Verano = temp_Verano;
    }

    public Integer getTemp_Otono() {
        return temp_Otono;
    }

    public void setTemp_Otono(Integer temp_Otono) {
        this.temp_Otono = temp_Otono;
    }

    public Integer getTemp_Inicio() {
        return temp_Inicio;
    }

    public void setTemp_Inicio(Integer temp_Inicio) {
        this.temp_Inicio = temp_Inicio;
    }

    public Integer getTemp_Fin() {
        return temp_Fin;
    }

    public void setTemp_Fin(Integer temp_Fin) {
        this.temp_Fin = temp_Fin;
    }

    public List<CicloMes> getAll_records_Mes() {
        return all_records_Mes;
    }

    public void setAll_records_Mes(List<CicloMes> all_records_Mes) {
        this.all_records_Mes = all_records_Mes;
    }

    public List<CicloQuincena> getAll_records_Quincena() {
        return all_records_Quincena;
    }

    public void setAll_records_Quincena(List<CicloQuincena> all_records_Quincena) {
        this.all_records_Quincena = all_records_Quincena;
    }

    public List<CicloTemporada> getAll_records_Temporada() {
        return all_records_Temporada;
    }

    public void setAll_records_Temporada(List<CicloTemporada> all_records_Temporada) {
        this.all_records_Temporada = all_records_Temporada;
    }

    public Integer getAsignedAnio() {
        return asignedAnio;
    }

    public void setAsignedAnio(Integer asignedAnio) {
        this.asignedAnio = asignedAnio;
    }

    public List<SelectItem> getSelectMeses() {
        return selectMeses;
    }

    public void setSelectMeses(List<SelectItem> selectMeses) {
        this.selectMeses = selectMeses;
    }

    public boolean isDisabledEdicion() {
        return disabledEdicion;
    }

    public void setDisabledEdicion(boolean disabledEdicion) {
        this.disabledEdicion = disabledEdicion;
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
