package com.aeromexico.pab.web.jsf.diseno;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.CodigoServicioFacadeRemote;
import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.PotencialFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorFacadeRemote;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;
import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.CodigoServicio;
import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Perfil;
import com.aeromexico.pab.entity.Potencial;
import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import com.aeromexico.pab.web.jsf.SesionInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
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
import static org.omnifaces.util.Exceptions.is;
import org.primefaces.context.RequestContext;
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
@Named(value = "potencialesMB")
@SessionScoped
public class PotencialesMB implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(PotencialesMB.class.getName());

    public PotencialesMB() {
    }

    private Potencial row_selected = null;
    boolean modificarRegistro = false;
    private StreamedContent filedownload;

    private InputStream inputStreamFile;
    private String fileName;
    private String contentTypeUploaded;
    private List<Potencial> all_records;

    private String labelCodigo;
    private Integer asignedTipoCiclo;
    private List<SelectItem> selectTipoCiclos;
    private Integer asignedCodigoServicio;
    private List<SelectItem> selectCodigosServico;
    private Integer asignedCiclo;
    private List<SelectItem> selectCiclos;
    private Integer asignedClase;
    private List<SelectItem> selectClases;
    private Integer asignedEstatus;
    private List<SelectItem> selectEstatus;

    private int idTipoCicloNumerico;
    private int idTipoCicloTemporada;
    private String fechaSistema;
    private Integer dias_alerta;

    private Parametro estatusValido;
    private Parametro estatusInactivo;
    private Parametro estatusRechazado;
    private Parametro estatusEnviado;
    private boolean alerta;
    private boolean isalerta;
    private boolean perfilSUPPLIER;
    private boolean perfilDESIGN_ADMINISTRATOR;

    private String observaciones;
    private String urlPotencial_mod;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/potencial_RSB")
    PotencialFacadeRemote potencialFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedor_RSB")
    ProveedorFacadeRemote proveedorFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote claseFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/codigoServicio_RSB")
    CodigoServicioFacadeRemote codigoServicioFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/contactoProveedorEstacion_RSB")
    ContactoProveedorEstacionFacadeRemote contactoProveedorEstacionFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/usuario_RSB")
    UsuarioFacadeRemote usuarioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/empleado_RSB")
    EmpleadoFacadeRemote empleadoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    @Inject
    @Named("sesionInfo")
    SesionInfo sesionInfo;

    public List<Potencial> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Potencial>();
                
                //no es administrador
                if (!perfilDESIGN_ADMINISTRATOR ) {
                    for (Potencial potencial : potencialFacadeRemote.findAll()) {
                        if (potencial.getIdEstatuspotencial().getIdParametro() == estatusInactivo.getIdParametro()
                                || potencial.getIdEstatuspotencial().getIdParametro() == estatusValido.getIdParametro()) {
                            all_records.add(potencial);
                        } else if (potencial.getIdEstatuspotencial().getIdParametro() == estatusRechazado.getIdParametro()
                                && perfilSUPPLIER){
                            all_records.add(potencial);
                        }
                    }
                } else if (perfilDESIGN_ADMINISTRATOR) {
                    all_records = potencialFacadeRemote.findAll();
                } 

            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public void getCodigosServicio() {
        selectCodigosServico = new ArrayList<SelectItem>();
        selectCodigosServico.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (CodigoServicio regs : codigoServicioFacadeRemote.findAll()) {
                if (regs.getClase().getIdClase() == asignedClase && regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO)) {
                    selectCodigosServico.add(new SelectItem(regs.getIdCodigoServicio(), regs.getCveCodigoServicio()));
                }
            }
        } catch (Exception ex) {
        }
    }

    public void getCiclo() {
        selectCiclos = new ArrayList<SelectItem>();
        selectCiclos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO) && regs.getClave().equals("noCiclo") && asignedTipoCiclo == idTipoCicloNumerico) {
                    selectCiclos.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
                if (regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO) && regs.getClave().equals("mes") && asignedTipoCiclo == idTipoCicloTemporada) {
                    selectCiclos.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }

        } catch (Exception ex) {
        }
    }

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        perfilSUPPLIER = false;
        perfilDESIGN_ADMINISTRATOR = false;
        for (Perfil perfil : sesionInfo.getCurrentUser().getPerfilList()) {
            if (perfil.getPerfil().equals("SUPPLIER")) {
                perfilSUPPLIER = true;
            }
            if (perfil.getPerfil().equals("DESIGN_ADMINISTRATOR")) {
                perfilDESIGN_ADMINISTRATOR = true;
            }
        }

        alerta = false;
        isalerta = false;

        asignedEstatus = -1;
        selectEstatus = new ArrayList<SelectItem>();
        selectEstatus.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO) && regs.getClave().equals("estatusPotencial")) {
                    if (regs.getValorEn().equals("Valid")) {
                        estatusValido = regs;
                    }
                    if (regs.getValorEn().equals("Inactive")) {
                        estatusInactivo = regs;
                    }
                    if (regs.getValorEn().equals("Rejected")) {
                        estatusRechazado = regs;
                    }
                    if (regs.getValorEn().equals("Sent")) {
                        estatusEnviado = regs;
                    }
                    selectEstatus.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {

        }

        dias_alerta = 1;

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        fechaSistema = dateFormat.format(now);

        labelCodigo = "";
        asignedClase = -1;
        selectClases = new ArrayList<SelectItem>();
        selectClases.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Clase clase : claseFacadeRemote.findAll()) {
                if (clase.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO)) {
                    selectClases.add(new SelectItem(clase.getIdClase(), (localeInfo.getLanguage().equals("es") ? clase.getNombreEsp() : clase.getNombreEng())
                    ));
                }
            }
        } catch (Exception ex) {
        }

        asignedCodigoServicio = -1;
        selectCodigosServico = new ArrayList<SelectItem>();
        selectCodigosServico.add(localeInfo.createSelectIntKeyFirstItem());

        asignedTipoCiclo = -1;
        selectTipoCiclos = new ArrayList<SelectItem>();
        selectTipoCiclos.add(localeInfo.createSelectIntKeyFirstItem());
        selectTipoCiclos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO) && regs.getClave().equals("tipoCiclo")) {
                    if (regs.getValorEn().equals("Numeric")) {
                        idTipoCicloNumerico = regs.getIdParametro();
                    }
                    if (regs.getValorEn().equals("Season")) {
                        idTipoCicloTemporada = regs.getIdParametro();
                    }
                    selectTipoCiclos.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {

        }
        asignedCiclo = -1;
        selectCiclos = new ArrayList<SelectItem>();
        selectCiclos.add(localeInfo.createSelectIntKeyFirstItem());

    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/diseno/potenciales?faces-redirect=true";
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
        String redirect = "/portal/sab/diseno/potencialesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Potencial();
        inputStreamFile = null;
        isalerta = false;
        return redirect;
    }

    public void updateAlerta() {
        isalerta = alerta ? true : false;
    }

    public Proveedor getProveedor(String claveProveedor) {
        return proveedorFacadeRemote.findByPK(claveProveedor);
    }

    public void getCodigoDesc() {
        labelCodigo = codigoServicioFacadeRemote.findByPK(asignedCodigoServicio).getDescripcion();
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (inputStreamFile == null && boton.equals("form:saveButton")) {
            retorno = false;
            messgeValidateError(boton, "Debe cargar un archivo");
        }
        
        if(asignedClase==-1){
            retorno = false;
            messgeValidateError(boton, "Debe seleccionar una clase");
        }
        if(asignedCodigoServicio==-1){
            retorno = false;
            messgeValidateError(boton, "Debe seleccionar un código de servicio");
        }
        if(asignedCiclo==-1){
            retorno = false;
            messgeValidateError(boton, "Debe seleccionar un número de ciclo");
        }
        if(asignedTipoCiclo==-1){
            retorno = false;
            messgeValidateError(boton, "Debe seleccionar un tipo de ciclo");
        }
        try{
            if (boton.equals("form:saveButton") && retorno) {
                Potencial entity = new Potencial();
                entity.setClase(claseFacadeRemote.findByPK(asignedClase));
                entity.setCodigoServicio(codigoServicioFacadeRemote.findByPK(asignedCodigoServicio));
                entity.setIdNociclotemporada(parametroFacadeRemote.findByPK(asignedCiclo));
                entity.setIdTipociclo(parametroFacadeRemote.findByPK(asignedTipoCiclo));
                for (Potencial p : potencialFacadeRemote.findAllLike(entity)) {
                    if (Objects.equals(p.getIdEstatuspotencial().getIdParametro(), estatusEnviado.getIdParametro())) {
                        retorno = false;
                        messgeValidateError(boton, "Ya existe un registro en proceso de autorización");
                    }
                }
            }
        }catch(Exception ex){}
        
        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        try {
            if (validate("form:saveButton")) {
                row_selected.setClase(claseFacadeRemote.findByPK(asignedClase));
                row_selected.setCodigoServicio(codigoServicioFacadeRemote.findByPK(asignedCodigoServicio));

                row_selected.setFechaObservaciones(null);
                row_selected.setFechaObservaciones(null);
                row_selected.setNotaRecordatorio(null);
                row_selected.setFechaRecordatorio(null);

                row_selected.setIdEstatuspotencial(estatusEnviado);
                row_selected.setIdNociclotemporada(parametroFacadeRemote.findByPK(asignedCiclo));
                row_selected.setIdTipociclo(parametroFacadeRemote.findByPK(asignedTipoCiclo));

                ContactoProveedorEstacion contProvEst = new ContactoProveedorEstacion();
                ContactoProveedorEstacion entity = new ContactoProveedorEstacion();
                entity.setUsuario(usuarioFacadeRemote.findByPK(sesionInfo.getCurrentUser().getEmailUsuario()));
                for(ContactoProveedorEstacion cp: contactoProveedorEstacionFacadeRemote.findAllLike(entity)){
                    contProvEst=cp;
                }
                
                row_selected.setProveedor(contProvEst.getProveedor());
                row_selected.setProveedorEstacion(contProvEst.getProveedorEstacion());
                row_selected.setVersionPotencial(1);
                row_selected.setVigenciaPotencial(new java.sql.Timestamp(now.getTime()));
                row_selected.setUsuarioCreo(sesionInfo.getCurrentUser().getEmailUsuario());
                row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                

                if (inputStreamFile != null) {
                    row_selected.setMimeType(contentTypeUploaded);
                    row_selected.setUrlPotencial(saveFileOnServer_urlUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(row_selected.getUrlPotencial());
                }
                
                potencialFacadeRemote.create(row_selected);
                all_records = null;
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void getDetalle(Potencial row) {
        observaciones = row.getObservaciones();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('detalleDialogWV').show();");
    }

    public void cerrarDetalle() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('detalleDialogWV').hide();");
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Potencial row_selected) {
        String redirect = "/portal/sab/diseno/potencialesDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        inputStreamFile = null;

        asignedClase = this.row_selected.getClase().getIdClase();
        getCodigosServicio();
        asignedCodigoServicio = this.row_selected.getCodigoServicio().getIdCodigoServicio();
        getCodigoDesc();
        asignedTipoCiclo = this.row_selected.getIdTipociclo().getIdParametro();
        getCiclo();
        asignedCiclo = this.row_selected.getIdNociclotemporada().getIdParametro();

        urlPotencial_mod = this.row_selected.getUrlPotencial();
        
        return redirect;
    }

    /**
     * Update selected record
     *
     * @param boton
     */
    public void update(String boton) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        try {
            if (validate(boton.equals("R") ? "form:rechazarButton" : "form:publicarButton")) {

                if (boton.equals("R")) {
                    row_selected.setIdEstatuspotencial(estatusRechazado);
                } else {
                    row_selected.setIdEstatuspotencial(estatusValido);
                }
                row_selected.setFechaObservaciones(row_selected.getObservaciones() != null ? new java.sql.Date(now.getTime()) : null);
                row_selected.setUsuarioObservaciones(sesionInfo.getCurrentUser().getEmailUsuario());
                row_selected.setFechaRecordatorio(row_selected.getNotaRecordatorio() != null ? new java.sql.Timestamp(now.getTime()) : null);

                Calendar cal = Calendar.getInstance();
                cal.setTime(new java.sql.Timestamp(now.getTime()));
                cal.add(Calendar.DAY_OF_WEEK, dias_alerta);
                java.sql.Timestamp ts = new Timestamp(cal.getTime().getTime());
                row_selected.setFechaRecordatorio(ts);
                if (inputStreamFile != null) {
                    row_selected.setMimeType(contentTypeUploaded);
                    row_selected.setUrlPotencial(saveFileOnServer_urlUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(row_selected.getUrlPotencial());
                }
                
                //borrar los rechazados anteriores, actualizar los vigentes anteriores
                if (!boton.equals("R")) {
                    Potencial entity_a_borrar = new Potencial();
                    entity_a_borrar.setClase(row_selected.getClase());
                    entity_a_borrar.setCodigoServicio(row_selected.getCodigoServicio());
                    entity_a_borrar.setIdNociclotemporada(row_selected.getIdNociclotemporada());
                    entity_a_borrar.setIdTipociclo(row_selected.getIdTipociclo());
                    for (Potencial potenciales_borrar : potencialFacadeRemote.findAllLike(entity_a_borrar)) {
                        if (Objects.equals(potenciales_borrar.getIdEstatuspotencial().getIdParametro(), estatusRechazado.getIdParametro()) && !Objects.equals(row_selected.getIdPotencial(), potenciales_borrar.getIdPotencial())) {
                            potencialFacadeRemote.remove(potenciales_borrar);
                        }
                        if (Objects.equals(potenciales_borrar.getIdEstatuspotencial().getIdParametro(), estatusValido.getIdParametro()) && !Objects.equals(row_selected.getIdPotencial(), potenciales_borrar.getIdPotencial())) {
                            potenciales_borrar.setIdEstatuspotencial(estatusInactivo);
                            potencialFacadeRemote.update(potenciales_borrar);
                        }
                    }

                }
                
                potencialFacadeRemote.update(row_selected);
                
                
                
                all_records = null;
                findAll();
                isalerta = false;
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public String saveFileOnServer_urlUploaded() throws FileNotFoundException, IOException {
        OutputStream os = new ByteArrayOutputStream();                
        byte[] buffer = new byte[1024*32];
        int r;
        while((r = inputStreamFile.read(buffer, 0, buffer.length))!= -1){
            os.write(buffer, 0, r);
            os.flush();
        }
        byte[] contentUploaded = ((ByteArrayOutputStream)os).toByteArray();
        final String httpSessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);        
        String urlUploaded = storageServiceFacadeRemote.saveOrUpdateMedia(contentUploaded, fileName, contentTypeUploaded, httpSessionId);
        
        return urlUploaded;
    }

    public void fileListener(FileUploadEvent event) {
        try {

            if (event.getFile() != null) {
                UploadedFile uploadedFile = event.getFile();
                if ((uploadedFile != null && uploadedFile.getSize() > 0)) {
                    this.inputStreamFile = uploadedFile.getInputstream();
                    if (inputStreamFile != null) {
                        fileName = uploadedFile.getFileName();
                        contentTypeUploaded = uploadedFile.getContentType();
                    }
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {

        }

    }

    public void preparedownload(String path_filename_bd, String mimetypearcchivo) {
        InputStream stream = null;
        try {
            Path path = Paths.get(path_filename_bd);
            byte[] content = Files.readAllBytes(path);
            String mimetype = mimetypearcchivo;
            String filename = path_filename_bd;
            stream = new ByteArrayInputStream(content);
            this.filedownload = new DefaultStreamedContent(stream, mimetype, filename);
        } catch (Exception e) {

        }
    }

    public Potencial getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Potencial row_selected) {
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

    public List<Potencial> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Potencial> all_records) {
        this.all_records = all_records;
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

    public String getLabelCodigo() {
        return labelCodigo;
    }

    public void setLabelCodigo(String labelCodigo) {
        this.labelCodigo = labelCodigo;
    }

    public Integer getAsignedTipoCiclo() {
        return asignedTipoCiclo;
    }

    public void setAsignedTipoCiclo(Integer asignedTipoCiclo) {
        this.asignedTipoCiclo = asignedTipoCiclo;
    }

    public List<SelectItem> getSelectTipoCiclos() {
        return selectTipoCiclos;
    }

    public void setSelectTipoCiclos(List<SelectItem> selectTipoCiclos) {
        this.selectTipoCiclos = selectTipoCiclos;
    }

    public Integer getAsignedCodigoServicio() {
        return asignedCodigoServicio;
    }

    public void setAsignedCodigoServicio(Integer asignedCodigoServicio) {
        this.asignedCodigoServicio = asignedCodigoServicio;
    }

    public List<SelectItem> getSelectCodigosServico() {
        return selectCodigosServico;
    }

    public void setSelectCodigosServico(List<SelectItem> selectCodigosServico) {
        this.selectCodigosServico = selectCodigosServico;
    }

    public Integer getAsignedCiclo() {
        return asignedCiclo;
    }

    public void setAsignedCiclo(Integer asignedCiclo) {
        this.asignedCiclo = asignedCiclo;
    }

    public List<SelectItem> getSelectCiclos() {
        return selectCiclos;
    }

    public void setSelectCiclos(List<SelectItem> selectCiclos) {
        this.selectCiclos = selectCiclos;
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

    public String getContentTypeUploaded() {
        return contentTypeUploaded;
    }

    public void setContentTypeUploaded(String contentTypeUploaded) {
        this.contentTypeUploaded = contentTypeUploaded;
    }

    public int getIdTipoCicloNumerico() {
        return idTipoCicloNumerico;
    }

    public void setIdTipoCicloNumerico(int idTipoCicloNumerico) {
        this.idTipoCicloNumerico = idTipoCicloNumerico;
    }

    public int getIdTipoCicloTemporada() {
        return idTipoCicloTemporada;
    }

    public void setIdTipoCicloTemporada(int idTipoCicloTemporada) {
        this.idTipoCicloTemporada = idTipoCicloTemporada;
    }

    public String getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(String fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Integer getDias_alerta() {
        return dias_alerta;
    }

    public void setDias_alerta(Integer dias_alerta) {
        this.dias_alerta = dias_alerta;
    }

    public Integer getAsignedEstatus() {
        return asignedEstatus;
    }

    public void setAsignedEstatus(Integer asignedEstatus) {
        this.asignedEstatus = asignedEstatus;
    }

    public List<SelectItem> getSelectEstatus() {
        return selectEstatus;
    }

    public void setSelectEstatus(List<SelectItem> selectEstatus) {
        this.selectEstatus = selectEstatus;
    }

    public Parametro getEstatusValido() {
        return estatusValido;
    }

    public void setEstatusValido(Parametro estatusValido) {
        this.estatusValido = estatusValido;
    }

    public Parametro getEstatusInactivo() {
        return estatusInactivo;
    }

    public void setEstatusInactivo(Parametro estatusInactivo) {
        this.estatusInactivo = estatusInactivo;
    }

    public Parametro getEstatusRechazado() {
        return estatusRechazado;
    }

    public void setEstatusRechazado(Parametro estatusRechazado) {
        this.estatusRechazado = estatusRechazado;
    }

    public Parametro getEstatusEnviado() {
        return estatusEnviado;
    }

    public void setEstatusEnviado(Parametro estatusEnviado) {
        this.estatusEnviado = estatusEnviado;
    }

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public boolean isIsalerta() {
        return isalerta;
    }

    public void setIsalerta(boolean isalerta) {
        this.isalerta = isalerta;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }

    public boolean isPerfilSUPPLIER() {
        return perfilSUPPLIER;
    }

    public void setPerfilSUPPLIER(boolean perfilSUPPLIER) {
        this.perfilSUPPLIER = perfilSUPPLIER;
    }

    public boolean isPerfilDESIGN_ADMINISTRATOR() {
        return perfilDESIGN_ADMINISTRATOR;
    }

    public void setPerfilDESIGN_ADMINISTRATOR(boolean perfilDESIGN_ADMINISTRATOR) {
        this.perfilDESIGN_ADMINISTRATOR = perfilDESIGN_ADMINISTRATOR;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUrlPotencial_mod() {
        return urlPotencial_mod;
    }

    public void setUrlPotencial_mod(String urlPotencial_mod) {
        this.urlPotencial_mod = urlPotencial_mod;
    }

}
