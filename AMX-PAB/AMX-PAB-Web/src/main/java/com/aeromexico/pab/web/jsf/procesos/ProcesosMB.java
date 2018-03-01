package com.aeromexico.pab.web.jsf.procesos;

import com.aeromexico.pab.backend.remote.ArchivoFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProcesoFacadeRemote;
import com.aeromexico.pab.entity.Archivo;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Proceso;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "procesosMB")
@SessionScoped
public class ProcesosMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ProcesosMB.class.getName());
    private Proceso row_selected = null;
    boolean modificarRegistro = false;
    private StreamedContent filedownload;

    private InputStream inputStreamFile;
    private String fileName;
    private String mimetype;
    private List<Proceso> all_records;
    private List<Proceso> all_records_Manuales;
    private List<Proceso> all_records_Instrucciones;
    private List<Proceso> all_records_Procedimientos;

    private Parametro parametro_manuales;
    private Parametro parametro_procedimientos;
    private Parametro parametro_instrucciones;

    private Integer asignedTipoProceso;
    private List<SelectItem> selectTiposProceso;
    private Integer asignedIdioma;
    private List<SelectItem> selectIdiomas;
    private String areaEmpleado;
    private String fechaSistema;
    private String revision;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proceso_RSB")
    ProcesoFacadeRemote procesoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/archivo_RSB")
    ArchivoFacadeRemote archivoFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    public ProcesosMB() {
    }

    @PostConstruct
    public void init() {
        inputStreamFile = null;
        row_selected = new Proceso();

        asignedTipoProceso = -1;
        selectTiposProceso = new ArrayList<SelectItem>();
        selectTiposProceso.add(localeInfo.createSelectStringKeyFirstItem());
        for (Parametro param : parametroFacadeRemote.findAll()) {
            if (param.getStatus().equals((short) 1)) {
                if (param.getClave().equals("tipoProceso")) {
                    if (param.getValorEn().equals("Manual")) {
                        parametro_manuales = param;
                    }
                    if (param.getValorEn().equals("Process")) {
                        parametro_procedimientos = param;
                    }
                    if (param.getValorEn().equals("Instructions")) {
                        parametro_instrucciones = param;
                    }
                    selectTiposProceso.add(new SelectItem(param.getIdParametro(), (localeInfo.getLanguage().equals("es") ? param.getValorEs() : param.getValorEn())
                    ));
                }
            }
        }

        asignedIdioma = -1;
        selectIdiomas = new ArrayList<SelectItem>();
        selectIdiomas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro param : parametroFacadeRemote.findAll()) {
                if (param.getStatus().equals((short) 1)) {
                    if (param.getClave().equals("idioma")) {
                        selectIdiomas.add(new SelectItem(param.getIdParametro(), (localeInfo.getLanguage().equals("es") ? param.getValorEs() : param.getValorEn())
                        ));
                    }
                }
            }
        } catch (Exception ex) {
        }

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        fechaSistema = dateFormat.format(now);

    }

    public List<Proceso> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Proceso>();
                all_records = procesoFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public List<Proceso> findAllManuales(Parametro tipo) {
        if (all_records_Manuales == null) {
            try {
                all_records_Manuales = new ArrayList<Proceso>();
                for (Proceso proceso : procesoFacadeRemote.findAll()) {
                    if (Objects.equals(proceso.getTipoProceso().getIdParametro(), tipo.getIdParametro())) {
                        all_records_Manuales.add(proceso);
                    }
                }
            } catch (Exception ex) {
                all_records_Manuales = null;
            }
        }
        return all_records_Manuales;
    }

    public List<Proceso> findAllInstrucciones(Parametro tipo) {
        if (all_records_Instrucciones == null) {
            try {
                all_records_Instrucciones = new ArrayList<Proceso>();
                for (Proceso proceso : procesoFacadeRemote.findAll()) {
                    if (Objects.equals(proceso.getTipoProceso().getIdParametro(), tipo.getIdParametro())) {
                        all_records_Instrucciones.add(proceso);
                    }
                }
            } catch (Exception ex) {
                all_records_Instrucciones = null;
            }
        }
        return all_records_Instrucciones;
    }

    public List<Proceso> findAllProcedimientos(Parametro tipo) {
        if (all_records_Procedimientos == null) {
            try {
                all_records_Procedimientos = new ArrayList<Proceso>();
                for (Proceso proceso : procesoFacadeRemote.findAll()) {
                    if (Objects.equals(proceso.getTipoProceso().getIdParametro(), tipo.getIdParametro())) {
                        all_records_Procedimientos.add(proceso);
                    }
                }
            } catch (Exception ex) {
                all_records_Procedimientos = null;
            }
        }
        return all_records_Procedimientos;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedIdioma == -1) {
            messgeValidateError(boton, "Debe seleccionar un Idioma");
            return false;
        }
        if (asignedTipoProceso == -1) {
            messgeValidateError(boton, "Debe seleccionar un Yipo");
            return false;
        }
        if (inputStreamFile == null) {
            messgeValidateError(boton, "Debe seleccionar un Archivo");
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
                row_selected.setIdIdioma(parametroFacadeRemote.findByPK(asignedIdioma));
                row_selected.setTipoProceso(parametroFacadeRemote.findByPK(asignedTipoProceso));
                row_selected.setRevision(Integer.parseInt(revision));
                Proceso procesoGenerado = procesoFacadeRemote.create(row_selected);
                if (inputStreamFile != null) {
                    Archivo archivo = new Archivo();
                    archivo.setStatus((short) 1);
                    archivo.setProceso(procesoGenerado);
                    archivo.setUrlArchivo(fileName);
                    archivo.setMimeType(mimetype);
                    archivo.setNombreArchivo(fileName);
                    archivoFacadeRemote.create(archivo);
                    saveFileOnServer();
                }
                if (Objects.equals(asignedTipoProceso, parametro_manuales.getIdParametro())) {
                    all_records_Manuales = null;
                    findAllManuales(parametro_manuales);
                }
                if (Objects.equals(asignedTipoProceso, parametro_instrucciones.getIdParametro())) {
                    all_records_Instrucciones = null;
                    findAllManuales(parametro_instrucciones);
                }
                if (Objects.equals(asignedTipoProceso, parametro_procedimientos.getIdParametro())) {
                    all_records_Procedimientos = null;
                    findAllManuales(parametro_procedimientos);
                }
                all_records = null;
                findAll();

                clear();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void clear() {
        asignedIdioma = -1;
        asignedTipoProceso = -1;
        revision = null;
        inputStreamFile = null;
        row_selected = new Proceso();

    }

    public void saveFileOnServer() throws FileNotFoundException, IOException {
        File file = new File(System.getProperty("AMX_PAB_MEDIA_FS_STORAGE") + "/" + fileName);
        InputStream is = this.inputStreamFile;
        OutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        is.close();
        out.close();
    }

    public void fileListener(FileUploadEvent event) {
        try {

            if (event.getFile() != null) {
                UploadedFile uploadedFile = event.getFile();
                if ((uploadedFile != null && uploadedFile.getSize() > 0)) {
                    this.inputStreamFile = uploadedFile.getInputstream();

                    if (inputStreamFile != null) {
                        fileName = uploadedFile.getFileName();
                        mimetype = uploadedFile.getContentType();
                    }
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {

        }

    }

    public void preparedownload(Proceso proceso) {
        InputStream stream = null;
        try {
            Archivo entity = new Archivo();
            entity.setProceso(proceso);
            Archivo archivo = null;
            for (Archivo ar : archivoFacadeRemote.findAllLike(entity)) {
                archivo = ar;
            }

            Path path = Paths.get(System.getProperty("AMX_PAB_MEDIA_FS_STORAGE") + "/" + archivo.getNombreArchivo());
            byte[] content = Files.readAllBytes(path);
            String mimetype = archivo.getMimeType();
            String filename = archivo.getNombreArchivo();
            stream = new ByteArrayInputStream(content);
            this.filedownload = new DefaultStreamedContent(stream, mimetype, filename);
        } catch (Exception e) {

        }
    }

    public Proceso getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Proceso row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public StreamedContent getFiledownload() {
        return filedownload;
    }

    public void setFiledownload(StreamedContent filedownload) {
        this.filedownload = filedownload;
    }

    public InputStream getInputStreamFile() {
        return inputStreamFile;
    }

    public void setInputStreamFile(InputStream inputStreamFile) {
        this.inputStreamFile = inputStreamFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Proceso> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Proceso> all_records) {
        this.all_records = all_records;
    }

    public Parametro getParametro_manuales() {
        return parametro_manuales;
    }

    public void setParametro_manuales(Parametro parametro_manuales) {
        this.parametro_manuales = parametro_manuales;
    }

    public Parametro getParametro_procedimientos() {
        return parametro_procedimientos;
    }

    public void setParametro_procedimientos(Parametro parametro_procedimientos) {
        this.parametro_procedimientos = parametro_procedimientos;
    }

    public Parametro getParametro_instrucciones() {
        return parametro_instrucciones;
    }

    public void setParametro_instrucciones(Parametro parametro_instrucciones) {
        this.parametro_instrucciones = parametro_instrucciones;
    }

    public Integer getAsignedTipoProceso() {
        return asignedTipoProceso;
    }

    public void setAsignedTipoProceso(Integer asignedTipoProceso) {
        this.asignedTipoProceso = asignedTipoProceso;
    }

    public List<SelectItem> getSelectTiposProceso() {
        return selectTiposProceso;
    }

    public void setSelectTiposProceso(List<SelectItem> selectTiposProceso) {
        this.selectTiposProceso = selectTiposProceso;
    }

    public Integer getAsignedIdioma() {
        return asignedIdioma;
    }

    public void setAsignedIdioma(Integer asignedIdioma) {
        this.asignedIdioma = asignedIdioma;
    }

    public List<SelectItem> getSelectIdiomas() {
        return selectIdiomas;
    }

    public void setSelectIdiomas(List<SelectItem> selectIdiomas) {
        this.selectIdiomas = selectIdiomas;
    }

    public String getAreaEmpleado() {
        return areaEmpleado;
    }

    public void setAreaEmpleado(String areaEmpleado) {
        this.areaEmpleado = areaEmpleado;
    }

    public String getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(String fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public List<Proceso> getAll_records_Manuales() {
        return all_records_Manuales;
    }

    public void setAll_records_Manuales(List<Proceso> all_records_Manuales) {
        this.all_records_Manuales = all_records_Manuales;
    }

    public List<Proceso> getAll_records_Instrucciones() {
        return all_records_Instrucciones;
    }

    public void setAll_records_Instrucciones(List<Proceso> all_records_Instrucciones) {
        this.all_records_Instrucciones = all_records_Instrucciones;
    }

    public List<Proceso> getAll_records_Procedimientos() {
        return all_records_Procedimientos;
    }

    public void setAll_records_Procedimientos(List<Proceso> all_records_Procedimientos) {
        this.all_records_Procedimientos = all_records_Procedimientos;
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
