package com.aeromexico.pab.web.jsf.reportes;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.ConfiguracionReporteDetalleFacadeRemote;
import com.aeromexico.pab.backend.remote.ConfiguracionReporteFacadeRemote;
import com.aeromexico.pab.backend.remote.CriterioIrregularidadFacadeRemote;
import com.aeromexico.pab.backend.remote.EvidenciaFacadeRemote;
import com.aeromexico.pab.backend.remote.MultimedioFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ReporteFacadeRemote;
import com.aeromexico.pab.backend.remote.ResponsableProductoFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoIrregularidadFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoProductoReporteFacadeRemote;
import com.aeromexico.pab.backend.remote.VueloFacadeRemote;
import com.aeromexico.pab.entity.Area;
import com.aeromexico.pab.entity.ConfiguracionReporteDetalle;
import com.aeromexico.pab.entity.Evidencia;
import com.aeromexico.pab.entity.Multimedio;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Reporte;
import com.aeromexico.pab.entity.ResponsableEstacion;
import com.aeromexico.pab.entity.ResponsableProducto;
import com.aeromexico.pab.entity.TipoIrregularidad;
import com.aeromexico.pab.entity.Vuelo;
import com.aeromexico.pab.web.jsf.FileDTO;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
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
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "capturaMB")
@SessionScoped
public class CapturaMB implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(CapturaMB.class.getName());

    public CapturaMB() {
    }

    private Reporte row_selected = null;
    private List<Reporte> all_records;
    private List<TipoIrregularidad> list_tiposIrregularidades;
    boolean modificarRegistro = false;
    private Map<Integer, Integer> asignedIrregularidades;
    private Integer asignedVuelo;
    private List<SelectItem> selectVuelos;
    private Integer asignedRuta;
    private List<SelectItem> selectRutas;
    private Integer asignedClase;
    private List<SelectItem> selectClases;
    private Integer asignedTipoReporte;
    private List<SelectItem> selectTipoReportes;
    private String fechaSistema;
    private String labelEstaciónResponsable;
    private String labelCompania;
    private Date fechaDeVuelo;
    private boolean isInforme;
    private boolean isReporte;
    private String textArea;
    private Integer asignedTiposProducto;
    private List<SelectItem> selectTiposProducto;
    private List<FileDTO> fileDTO_List;
    private Parametro estatusAsignado;
    private Parametro estatusConfirmado;
    private Parametro estatusCerrado;
    private Parametro estatusDefasado;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/reporte_RSB")
    ReporteFacadeRemote reporteFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/vuelo_RSB")
    VueloFacadeRemote vueloFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote clasesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/configuracionReporte_RSB")
    ConfiguracionReporteFacadeRemote configuracionReporteFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/criterioIrregularidad_RSB")
    CriterioIrregularidadFacadeRemote criterioIrregularidadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoIrregularidad_RSB")
    TipoIrregularidadFacadeRemote tipoIrregularidadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoProductoReporte_RSB")
    TipoProductoReporteFacadeRemote tipoProductoReporteFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/responsableProducto_RSB")
    ResponsableProductoFacadeRemote responsableProductoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/configuracionReporteDetalle_RSB")
    ConfiguracionReporteDetalleFacadeRemote configuracionReporteDetalleFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/multimedio_RSB")
    MultimedioFacadeRemote multimedioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/evidencia_RSB")
    EvidenciaFacadeRemote evidenciaFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    @Inject
    @Named("reporteMB")
    ReporteMB reporteMB;

    @PostConstruct
    public void init() {
        fileDTO_List = new ArrayList<>();
        asignedIrregularidades = new HashMap<>();
        isInforme = false;
        isReporte = false;
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        fechaSistema = dateFormat.format(now);

        asignedVuelo = -1;
        selectVuelos = new ArrayList<>();
        Map<Integer, Integer> mapa = new HashMap<>();
        selectVuelos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            vueloFacadeRemote.findAll().stream().filter((regs) -> (!mapa.containsKey(regs.getNumeroVuelo()))).map((regs) -> {
                mapa.put(regs.getNumeroVuelo(), regs.getNumeroVuelo());
                return regs;
            }).forEachOrdered((regs) -> {
                selectVuelos.add(new SelectItem(regs.getIdVuelo(), regs.getNumeroVuelo() + ""));
            });
            mapa.clear();
        } catch (Exception ex) {
        }

        asignedClase = -1;
        selectClases = new ArrayList<>();
        selectClases.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            clasesFacadeRemote.findAll().forEach((regs) -> {
                selectClases.add(new SelectItem(regs.getIdClase(), regs.getNombreEsp()));
            });
        } catch (Exception ex) {
        }

        selectTipoReportes = new ArrayList<>();
        selectTipoReportes.add(localeInfo.createSelectIntKeyFirstItem());
        parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("tipoReporte"))).forEachOrdered((regs) -> {
            selectTipoReportes.add(new SelectItem(regs.getIdParametro(),
                    (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
            ));
        });

        asignedTiposProducto = -1;
        selectTiposProducto = new ArrayList<>();
        selectTiposProducto.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            tipoProductoReporteFacadeRemote.findAll().stream().filter((regs) -> (regs.getEstatus().getValorEs().equals("Activo"))).forEachOrdered((regs) -> {
                selectTiposProducto.add(new SelectItem(regs.getIdTipoProductoReporte(), regs.getDescripcion()));
            });
        } catch (Exception ex) {
        }

        parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO) && regs.getClave().equals("estatusReporte"))).map((regs) -> {
            if (regs.getValorEn().equals("Assigned")) {
                estatusAsignado = regs;
            }
            return regs;
        }).map((regs) -> {
            if (regs.getValorEn().equals("Confirmed")) {
                estatusConfirmado = regs;
            }
            return regs;
        }).map((regs) -> {
            if (regs.getValorEn().equals("Closed")) {
                estatusCerrado = regs;
            }
            return regs;
        }).filter((regs) -> (regs.getValorEn().equals("Defected"))).forEachOrdered((regs) -> {
            estatusDefasado = regs;
        });

    }

    public void getRutas() {
        selectRutas = new ArrayList<>();
        selectRutas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            Integer num = vueloFacadeRemote.findByPK(asignedVuelo).getNumeroVuelo();
            vueloFacadeRemote.findAll().stream().filter((regs) -> (regs.getNumeroVuelo() == num)).forEachOrdered((regs) -> {
                selectRutas.add(new SelectItem(regs.getIdVuelo(), "[" + regs.getNumeroVuelo() + "]:" + regs.getEstacionOrigen().getClaveIata() + " - " + regs.getEstacionDestino().getClaveIata()));
            });
        } catch (Exception ex) {
        }
    }

    public void setLabelEstacion() {
        Vuelo vueloOrigen = vueloFacadeRemote.findByPK(asignedRuta);
        labelEstaciónResponsable = vueloOrigen.getEstacionOrigen().getClaveIata() + " - " + vueloOrigen.getEstacionOrigen().getNombre();
        labelCompania = vueloOrigen.getCompania().getCveCompania();
    }

    public void updateTipoReporte() {
        isInforme = asignedTipoReporte != -1 ? parametroFacadeRemote.findByPK(asignedTipoReporte).getValorEn().equals("Irregularity") : false;
        isReporte = asignedTipoReporte != -1 ? !parametroFacadeRemote.findByPK(asignedTipoReporte).getValorEn().equals("Irregularity") : false;
    }

    public List<Reporte> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<>();
                all_records = reporteFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public String returnMaster() {
        String redirect = "/portal/reportes/captura?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    public String addRow() {
        String redirect = "/portal/reportes/capturaDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Reporte();
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedVuelo == -1) {
            messgeValidateError(boton, "Debe seleccionar un Vuelo");
            return false;
        }
        if (asignedClase == -1) {
            messgeValidateError(boton, "Debe seleccionar una Clase");
            return false;
        }
        if (fechaDeVuelo == null) {
            messgeValidateError(boton, "Debe seleccionar una fecha de Vuelo");
            return false;
        }
        if (textArea.isEmpty()) {
            messgeValidateError(boton, "Debe ingresar una descripción");
            return false;
        }
        if (asignedTipoReporte == -1) {
            messgeValidateError(boton, "Debe ingresar un Tipo de Reporte");
            return false;
        }

        if (isInforme && asignedTiposProducto == -1) {
            messgeValidateError(boton, "Debe ingresar un Tipo de Producto");
            return false;
        }

        return retorno;
    }
    
    public void limpiarCampos(){
        asignedVuelo =-1;
        asignedRuta =-1;
        selectRutas = new ArrayList<>();
        labelEstaciónResponsable =null;
        labelCompania =null;
        asignedClase=-1;
        fechaDeVuelo =null;
        asignedTipoReporte=-1;
        updateTipoReporte();
        textArea=null;
        fileDTO_List= new ArrayList<>();
        list_tiposIrregularidades = new ArrayList<>();
    }

    public void save() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            if (validate("form:saveButton")) {
                Vuelo vuelo = vueloFacadeRemote.findByPK(asignedVuelo);
                row_selected = new Reporte();
                row_selected.setVuelo(vuelo);
                row_selected.setClase(clasesFacadeRemote.findByPK(asignedClase));
                row_selected.setClaveReporte("REP");
                row_selected.setDescripcion(textArea);
                row_selected.setFechaVuelo(new java.sql.Date(fechaDeVuelo.getTime()));
                row_selected.setIdEstatusReporteActual(isInforme ? estatusAsignado : estatusCerrado);
                row_selected.setIdTipoReporte(parametroFacadeRemote.findByPK(asignedTipoReporte));
                row_selected.setTipoProductoReporte(isInforme ? tipoProductoReporteFacadeRemote.findByPK(asignedTiposProducto) : null);
                if (isInforme) {
                    ResponsableEstacion responsable = null;
                    Area area = null;
                    for (ResponsableProducto rp : responsableProductoFacadeRemote.findAll()) {
                        if (Objects.equals(rp.getTipoProductoReporte().getIdTipoProductoReporte(), asignedTiposProducto)
                                && Objects.equals(rp.getResponsableEstacion().getEstacion().getIdEstacion(), vuelo.getEstacionOrigen().getIdEstacion())) {
                            area = rp.getResponsableEstacion().getArea();
                            responsable = rp.getResponsableEstacion();
                            break;
                        }
                    }
                    if (area == null || responsable == null) {
                        throw new Exception("No existe reponsable en la estación y Tipo Producto seleccionado");
                    }
                    row_selected.setAreaResponsable(area);
                    row_selected.setIdResponsableAmx(responsable);
                    row_selected.setIdResponsableFinalAmx(null);
                    row_selected.setIdResponsableProveedorEstacion(null);
                    row_selected.setIdResponsableProveedorEstacionFinal(null);
                }
                row_selected.setStatus((short) 1);
                Reporte reporte = reporteFacadeRemote.create(row_selected);
                reporte.setClaveReporte("REP" + (new Formatter().format("%07d", reporte.getIdReporte())));
                for (FileDTO fileDTO : fileDTO_List) {
                    Multimedio mm = saveFileOnServer_urlUploaded(fileDTO.getInputStreamFile(), fileDTO.getFileName(), fileDTO.getContentTypeUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(mm.getUrl());
                    mm.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                    mm.setUsuarioCreo(request.getUserPrincipal().getName());
                    mm.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                    mm.setUsuarioModifico(request.getUserPrincipal().getName());
                    mm.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    Multimedio resp = multimedioFacadeRemote.create(mm);
                    Evidencia evidencia = new Evidencia();
                    evidencia.setMultimedio(resp);
                    evidencia.setReporte(reporte);
                    evidenciaFacadeRemote.create(evidencia);
                }
                reporteFacadeRemote.update(reporte);
                asignedIrregularidades.entrySet().stream().filter((map) -> !(map.getValue() == -1)).map((map) -> configuracionReporteFacadeRemote.findByPK(map.getValue())).map((config_reporte) -> {
                    ConfiguracionReporteDetalle confRep_detalle = new ConfiguracionReporteDetalle();
                    confRep_detalle.setCriterioIrregularidad(config_reporte.getCriterioIrregularidad());
                    confRep_detalle.setReporte(reporte);
                    confRep_detalle.setTipoIrregularidad(config_reporte.getTipoIrregularidad());
                    confRep_detalle.setTipoProductoReporte(config_reporte.getTipoProductoReporte());
                    return confRep_detalle;
                }).forEachOrdered((confRep_detalle) -> {
                    configuracionReporteDetalleFacadeRemote.create(confRep_detalle);
                });

                reporteMB.setAll_records(null);
                reporteMB.findAll();
                
                limpiarCampos();
                
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void putTiposIrregularidades() {
        list_tiposIrregularidades = new ArrayList<>();
        Map<Integer, Integer> mapaingresados = new HashMap<>();
        configuracionReporteFacadeRemote.findAll().stream().filter((cr) -> (Objects.equals(cr.getTipoProductoReporte().getIdTipoProductoReporte(), asignedTiposProducto)
                && !mapaingresados.containsKey(cr.getTipoIrregularidad().getIdTipoIrregularidad()))).map((cr) -> {
            mapaingresados.put(cr.getTipoIrregularidad().getIdTipoIrregularidad(), 1);
            return cr;
        }).forEachOrdered((cr) -> {
            list_tiposIrregularidades.add(cr.getTipoIrregularidad());
        });
    }

    public List<SelectItem> generateIrregularidades(Integer idIrregularidad) {
        List<SelectItem> retorno = new ArrayList<>();
        retorno.add(localeInfo.createSelectIntKeyFirstItem());
        configuracionReporteFacadeRemote.findAll().stream().filter((cr) -> (Objects.equals(cr.getTipoProductoReporte().getIdTipoProductoReporte(), asignedTiposProducto) && Objects.equals(idIrregularidad, cr.getTipoIrregularidad().getIdTipoIrregularidad()))).forEachOrdered((cr) -> {
            retorno.add(new SelectItem(cr.getIdConfiguracionReporte(), cr.getCriterioIrregularidad().getDescripcion()));
        });
        return retorno;
    }

    public Multimedio saveFileOnServer_urlUploaded(InputStream inputStreamFile, String fileName, String contentTypeUploaded) throws FileNotFoundException, IOException {
        OutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 32];
        int r;
        while ((r = inputStreamFile.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer, 0, r);
            os.flush();
        }
        byte[] contentUploaded = ((ByteArrayOutputStream) os).toByteArray();
        final String httpSessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
        Multimedio multimedio = storageServiceFacadeRemote.saveOrUpdateMultimedio(contentUploaded, fileName, contentTypeUploaded, httpSessionId);

        return multimedio;
    }

    public void fileListener(FileUploadEvent event) {
        try {

            if (event.getFile() != null) {
                UploadedFile uploadedFile = event.getFile();
                if ((uploadedFile != null && uploadedFile.getSize() > 0)) {
                    InputStream inputStreamFile = uploadedFile.getInputstream();
                    if (inputStreamFile != null) {
                        FileDTO fileDTO = new FileDTO();
                        fileDTO.setInputStreamFile(inputStreamFile);
                        fileDTO.setFileName(uploadedFile.getFileName());
                        fileDTO.setContentTypeUploaded(uploadedFile.getContentType());
                        fileDTO_List.add(fileDTO);
                    }
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {

        }

    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Reporte getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Reporte row_selected) {
        this.row_selected = row_selected;
    }

    public List<Reporte> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Reporte> all_records) {
        this.all_records = all_records;
    }

    public Integer getAsignedVuelo() {
        return asignedVuelo;
    }

    public void setAsignedVuelo(Integer asignedVuelo) {
        this.asignedVuelo = asignedVuelo;
    }

    public List<SelectItem> getSelectVuelos() {
        return selectVuelos;
    }

    public void setSelectVuelos(List<SelectItem> selectVuelos) {
        this.selectVuelos = selectVuelos;
    }

    public Integer getAsignedRuta() {
        return asignedRuta;
    }

    public void setAsignedRuta(Integer asignedRuta) {
        this.asignedRuta = asignedRuta;
    }

    public List<SelectItem> getSelectRutas() {
        return selectRutas;
    }

    public void setSelectRutas(List<SelectItem> selectRutas) {
        this.selectRutas = selectRutas;
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

    public List<SelectItem> getSelectTipoReportes() {
        return selectTipoReportes;
    }

    public void setSelectTipoReportes(List<SelectItem> selectTipoReportes) {
        this.selectTipoReportes = selectTipoReportes;
    }

    public String getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(String fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Integer getAsignedTipoReporte() {
        return asignedTipoReporte;
    }

    public void setAsignedTipoReporte(Integer asignedTipoReporte) {
        this.asignedTipoReporte = asignedTipoReporte;
    }

    public String getLabelEstaciónResponsable() {
        return labelEstaciónResponsable;
    }

    public void setLabelEstaciónResponsable(String labelEstaciónResponsable) {
        this.labelEstaciónResponsable = labelEstaciónResponsable;
    }

    public String getLabelCompania() {
        return labelCompania;
    }

    public void setLabelCompania(String labelCompania) {
        this.labelCompania = labelCompania;
    }

    public Date getFechaDeVuelo() {
        return fechaDeVuelo;
    }

    public void setFechaDeVuelo(Date fechaDeVuelo) {
        this.fechaDeVuelo = fechaDeVuelo;
    }

    public boolean isIsInforme() {
        return isInforme;
    }

    public void setIsInforme(boolean isInforme) {
        this.isInforme = isInforme;
    }

    public boolean isIsReporte() {
        return isReporte;
    }

    public void setIsReporte(boolean isReporte) {
        this.isReporte = isReporte;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public Integer getAsignedTiposProducto() {
        return asignedTiposProducto;
    }

    public void setAsignedTiposProducto(Integer asignedTiposProducto) {
        this.asignedTiposProducto = asignedTiposProducto;
    }

    public List<SelectItem> getSelectTiposProducto() {
        return selectTiposProducto;
    }

    public void setSelectTiposProducto(List<SelectItem> selectTiposProducto) {
        this.selectTiposProducto = selectTiposProducto;
    }

    public List<TipoIrregularidad> getList_tiposIrregularidades() {
        return list_tiposIrregularidades;
    }

    public void setList_tiposIrregularidades(List<TipoIrregularidad> list_tiposIrregularidades) {
        this.list_tiposIrregularidades = list_tiposIrregularidades;
    }

    public Map<Integer, Integer> getAsignedIrregularidades() {
        return asignedIrregularidades;
    }

    public void setAsignedIrregularidades(Map<Integer, Integer> asignedIrregularidades) {
        this.asignedIrregularidades = asignedIrregularidades;
    }

    public List<FileDTO> getFileDTO_List() {
        return fileDTO_List;
    }

    public void setFileDTO_List(List<FileDTO> fileDTO_List) {
        this.fileDTO_List = fileDTO_List;
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
