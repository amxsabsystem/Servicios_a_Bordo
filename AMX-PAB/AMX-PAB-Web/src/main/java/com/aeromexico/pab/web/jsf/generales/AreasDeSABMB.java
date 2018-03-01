package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.AreaFacadeRemote;
import com.aeromexico.pab.entity.Area;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "areasDeSABMB")
@SessionScoped
public class AreasDeSABMB implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(AreasDeSABMB.class.getName());

    public AreasDeSABMB() {
    }

    private Area row_selected = null;
    boolean modificarRegistro = false;
    private StreamedContent filedownload;
    private String contentTypeUploaded;
    private InputStream inputStreamFile;
    private String fileName;
    private List<Area> all_records;
    private String claveAnterior = null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/area_RSB")
    AreaFacadeRemote areaFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

    @Inject
    @Named("empleadosMB")
    EmpleadosMB empleadosMB;

    public List<Area> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Area>();
                all_records = areaFacadeRemote.findAll();
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
        String redirect = "/portal/generales/areasDeSAB?faces-redirect=true";
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
        String redirect = "/portal/generales/areasDeSABDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Area();
        claveAnterior = null;
        inputStreamFile = null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;

        try {
            for (Area regs : areaFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getClave().toUpperCase().equals(regs.getClave().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Area ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getClave().toUpperCase().equals(claveAnterior)
                            && row_selected.getClave().toUpperCase().equals(regs.getClave().toUpperCase())) {
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
     */
    public void save() {
        try {
            if (validate("form:saveButton")) {
                String claveUpper = row_selected.getClave().toUpperCase();
                row_selected.setClave(claveUpper);
                row_selected.setStatus((short) 1);

                if (inputStreamFile != null) {
                    row_selected.setMimeType(contentTypeUploaded);
                    row_selected.setUrlMultimedia(saveFileOnServer_urlUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(row_selected.getUrlMultimedia());
                }
                areaFacadeRemote.create(row_selected);
                empleadosMB.init();

                
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
    public String modifyRow(Area row_selected) {
        String redirect = "/portal/generales/areasDeSABDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getClave();
        inputStreamFile = null;
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                String claveUpper = row_selected.getClave().toUpperCase();
                row_selected.setClave(claveUpper);

                if (inputStreamFile != null) {
                    row_selected.setMimeType(contentTypeUploaded);
                    row_selected.setUrlMultimedia(saveFileOnServer_urlUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(row_selected.getUrlMultimedia());
                }
                areaFacadeRemote.update(row_selected);
                empleadosMB.init();


                all_records = null;
                findAll();
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

    public Area getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Area row_selected) {
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

    public List<Area> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Area> all_records) {
        this.all_records = all_records;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }

    public String getContentTypeUploaded() {
        return contentTypeUploaded;
    }

    public void setContentTypeUploaded(String contentTypeUploaded) {
        this.contentTypeUploaded = contentTypeUploaded;
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

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }
}
