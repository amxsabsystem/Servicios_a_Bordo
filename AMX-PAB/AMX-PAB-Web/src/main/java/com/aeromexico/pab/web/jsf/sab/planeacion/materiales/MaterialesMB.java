package com.aeromexico.pab.web.jsf.sab.planeacion.materiales;

import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.CategoriaMaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.CategoriaMaterial;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "materialesMB")
@SessionScoped
public class MaterialesMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(MaterialesMB.class.getName());

    private Material row_selected = null;
    boolean modificarRegistro = false;
    private List<Material> all_records;
    private Integer asignedCategoria;
    private List<SelectItem> selectCategorias;
    private Integer asignedTipoAbastecimiento;
    private List<SelectItem> selectTipoAbastecimientos;

    private StreamedContent filedownload;

    private InputStream inputStreamFile;
    private String fileName;
    private String peso;
    private String urlUploaded;
    private String contentTypeUploaded;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    MaterialFacadeRemote materialFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/categoriaMaterial_RSB")
    CategoriaMaterialFacadeRemote categoriaMaterialFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    /**
     * Creates a new instance of MaterialesMB
     */
    public MaterialesMB() {
    }

    public List<Material> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Material>();
                all_records = materialFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    @PostConstruct
    public void init() {
        asignedCategoria = -1;
        asignedTipoAbastecimiento = -1;
        urlUploaded = null;
        contentTypeUploaded = null;

        selectCategorias = new ArrayList<SelectItem>();
        selectCategorias.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (CategoriaMaterial regs : categoriaMaterialFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectCategorias.add(new SelectItem(regs.getIdCategoriaMaterial(),
                            localeInfo.getLanguage().equals("es") ? regs.getNombreEs() : regs.getNombreEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }
        selectTipoAbastecimientos = new ArrayList<SelectItem>();
        selectTipoAbastecimientos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1) && regs.getClave().equals("tipoAbastecimiento")) {
                    selectTipoAbastecimientos.add(new SelectItem(regs.getIdParametro(),
                            localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }

    }

    public CategoriaMaterial getCategoria(Integer idCategoria) {
        return categoriaMaterialFacadeRemote.findByPK(idCategoria);
    }

    public Parametro getTipo(Integer idParametro) {
        return parametroFacadeRemote.findByPK(idParametro);
    }

    /**
     * @return Go back to Master Page
     */
    public String returnMaster() {
        String redirect = "/portal/sab/planeacion/materiales/materiales?faces-redirect=true";
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
        String redirect = "/portal/sab/planeacion/materiales/materialesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Material();
        inputStreamFile = null;
        urlUploaded = null;
        contentTypeUploaded = null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedCategoria == -1) {
            messgeValidateError(boton, "Debe seleccionar una Categoría");
            return false;
        }
        if (asignedTipoAbastecimiento == -1) {
            messgeValidateError(boton, "Debe seleccionar un Tipo de Abastecimiento");
            return false;
        }
        return retorno;
    }

    public void fileListener(FileUploadEvent event) {
        try {

            if (event.getFile() != null) {
                UploadedFile uploadedFile = event.getFile();
                if ((uploadedFile != null && uploadedFile.getSize() > 0)) {
                    this.inputStreamFile = uploadedFile.getInputstream();

                    InputStream isUploaded     = uploadedFile.getInputstream();
                    String fileNameUploaded    = uploadedFile.getFileName();
                    contentTypeUploaded = uploadedFile.getContentType();
                    logger.info("------>>>StorageService:"); 
                    OutputStream os = new ByteArrayOutputStream();                
                    byte[] buffer = new byte[1024*32];
                    int r;
                    while((r = isUploaded.read(buffer, 0, buffer.length))!= -1){
                        os.write(buffer, 0, r);
                        os.flush();
                    }
                    byte[] contentUploaded = ((ByteArrayOutputStream)os).toByteArray();
                    final String httpSessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);                
                    urlUploaded = storageServiceFacadeRemote.saveOrUpdateMedia(contentUploaded, fileNameUploaded, contentTypeUploaded, httpSessionId);
                    logger.info("-->>>after storageServiceFacadeRemote.saveOrUpdateMedia: urlUploaded="+urlUploaded+", contentTypeUploaded="+contentTypeUploaded);
                    logger.info("-->>>urlUploaded="+urlUploaded+", contentTypeUploaded="+contentTypeUploaded);                    
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {

        }

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
                row_selected.setPeso(Double.parseDouble(peso));

                CategoriaMaterial cm = new CategoriaMaterial();
                cm.setIdCategoriaMaterial(asignedCategoria);
                row_selected.setCategoriaMaterial(cm);

                Parametro ta = new Parametro();
                ta.setIdParametro(asignedTipoAbastecimiento);
                row_selected.setTipoAbastecimiento(ta);
                
                row_selected.setMimeType(contentTypeUploaded);
                row_selected.setUrlMultimedia(urlUploaded);
                
                storageServiceFacadeRemote.commitSaveOrUpdateMedia(urlUploaded);
                
                materialFacadeRemote.create(row_selected);
                
                asignedCategoria =-1;
                asignedTipoAbastecimiento =-1;
                peso =null;
                
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
     */
    public String modifyRow(Material row_selected) {
        String redirect = "/portal/sab/planeacion/materiales/materialesDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        peso = String.valueOf(this.row_selected.getPeso());
        asignedCategoria = row_selected.getCategoriaMaterial().getIdCategoriaMaterial();
        asignedTipoAbastecimiento = row_selected.getTipoAbastecimiento().getIdParametro();
        inputStreamFile=null;
        urlUploaded = null;
        contentTypeUploaded = null;
        return redirect;
    }

    /*
	 * Update selected record
     */
    public void update() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            if (validate("form:updateButton")) {
                row_selected.setStatus((short) 1);
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                row_selected.setPeso(Double.parseDouble(peso));
                CategoriaMaterial cm = new CategoriaMaterial();
                cm.setIdCategoriaMaterial(asignedCategoria);
                row_selected.setCategoriaMaterial(cm);

                Parametro ta = new Parametro();
                ta.setIdParametro(asignedTipoAbastecimiento);
                row_selected.setTipoAbastecimiento(ta);

                row_selected.setMimeType(contentTypeUploaded);
                row_selected.setUrlMultimedia(urlUploaded);                
                
                storageServiceFacadeRemote.commitSaveOrUpdateMedia(urlUploaded);                
                
                materialFacadeRemote.update(row_selected);

                asignedCategoria =-1;
                asignedTipoAbastecimiento =-1;
                peso =null;
                
                
                all_records = null;
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Material getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Material row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public List<Material> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Material> all_records) {
        this.all_records = all_records;
    }

    public Integer getAsignedCategoria() {
        return asignedCategoria;
    }

    public void setAsignedCategoria(Integer asignedCategoria) {
        this.asignedCategoria = asignedCategoria;
    }

    public List<SelectItem> getSelectCategorias() {
        return selectCategorias;
    }

    public void setSelectCategorias(List<SelectItem> selectCategorias) {
        this.selectCategorias = selectCategorias;
    }

    public Integer getAsignedTipoAbastecimiento() {
        return asignedTipoAbastecimiento;
    }

    public void setAsignedTipoAbastecimiento(Integer asignedTipoAbastecimiento) {
        this.asignedTipoAbastecimiento = asignedTipoAbastecimiento;
    }

    public List<SelectItem> getSelectTipoAbastecimientos() {
        return selectTipoAbastecimientos;
    }

    public void setSelectTipoAbastecimientos(List<SelectItem> selectTipoAbastecimientos) {
        this.selectTipoAbastecimientos = selectTipoAbastecimientos;
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
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
