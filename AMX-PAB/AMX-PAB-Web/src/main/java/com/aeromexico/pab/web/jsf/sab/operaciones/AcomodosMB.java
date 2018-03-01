package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.remote.AcomodoDetalleFacadeRemote;
import com.aeromexico.pab.backend.remote.AcomodoFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProductoFacadeRemote;
import com.aeromexico.pab.entity.Acomodo;
import com.aeromexico.pab.entity.AcomodoDetalle;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Producto;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "acomodosMB")
@SessionScoped
public class AcomodosMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(AcomodosMB.class.getName());
    private List<Integer> asignedProductos;
    private List<SelectItem> selectProductos;
    boolean modificarRegistro = false;
    private Acomodo row_selected = null;
    private StreamedContent filedownload;
    private List<Acomodo> all_records;

    private InputStream inputStreamFile;
    private String fileName;

    private Integer asignedModeloAvion;
    private List<SelectItem> selectTipoEquipos;
    private Integer asignedOrigen;
    private List<SelectItem> selectOrigenes;
    private Integer asignedDestino;
    private List<SelectItem> selectDestinos;
    private String contenttype;
    private String aniorev;
    private String norev;
        private Integer asignedIdioma;
    private List<SelectItem> selectIdiomas;

    public AcomodosMB() {
    }

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/acomodo_RSB")
    AcomodoFacadeRemote servicioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/acomodoDetalle_RSB")
    AcomodoDetalleFacadeRemote acomodoDetalleFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote modeloAvionFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/producto_RSB")
    ProductoFacadeRemote productosFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    public List<Acomodo> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Acomodo>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    @PostConstruct
    public void init() {

        asignedProductos = new ArrayList<Integer>();
        if (selectProductos == null) {
            selectProductos = new ArrayList<SelectItem>();
            for (Producto regs : productosFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectProductos.add(new SelectItem(regs.getIdProducto(), regs.getNombre()));
                }
            }
        }

        asignedModeloAvion = -1;
        asignedOrigen = -1;
        asignedDestino = -1;

        selectTipoEquipos = new ArrayList<SelectItem>();
        selectTipoEquipos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (ModeloAvion regs : modeloAvionFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectTipoEquipos.add(new SelectItem(regs.getIdModeloAvion(), regs.getModelo()+" "+regs.getDescripcion()));
                }
            }
        } catch (Exception ex) {
        }
        selectOrigenes = new ArrayList<SelectItem>();
        selectOrigenes.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Estacion regs : estacionesFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectOrigenes.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata()+" - "+regs.getNombre()
                    ));
                }
            }
        } catch (Exception ex) {
        }
        selectDestinos = new ArrayList<SelectItem>();
        selectDestinos.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Estacion regs : estacionesFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectDestinos.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata()+" - "+regs.getNombre()
                    ));
                }
            }
        } catch (Exception ex) {
        }
        
        selectIdiomas = new ArrayList<SelectItem>();
        try {
            for (Parametro regs : parametroFacadeRemote.findAll()) {
                if (regs.getClave().equals("idioma")) {
                    if (!regs.getValorEs().equals("Ambos")) {
                        selectIdiomas.add(new SelectItem(regs.getIdParametro(),
                                localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                        ));
                    }
                }
            }
            asignedIdioma = (Integer) selectIdiomas.get(0).getValue();
        } catch (Exception ex) {
        }
    }

    /**
     * Go back to Master Page
     * @return 
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/acomodos?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     * @return 
     */
    public String addRow() {
        String redirect = "/portal/sab/operaciones/acomodosDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Acomodo();
        inputStreamFile = null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedModeloAvion == -1) {
            messgeValidateError(boton, "Debe seleccionar un Tipo De Equipo");
            return false;
        }
        if (asignedOrigen == -1) {
            messgeValidateError(boton, "Debe seleccionar un Origen");
            return false;
        }
        if (asignedDestino.equals("-1")) {
            messgeValidateError(boton, "Debe seleccionar un Destino");
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
                row_selected.setStatus((short) 1);
                row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
                row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));

                row_selected.setModeloAvion(modeloAvionFacadeRemote.findByPK(asignedModeloAvion));
                row_selected.setEstacionDestino(estacionesFacadeRemote.findByPK(asignedDestino));
                row_selected.setEstacionOrigen(estacionesFacadeRemote.findByPK(asignedOrigen));

                AcomodoDetalle acomodoDetalle = new AcomodoDetalle();
                acomodoDetalle.setAnioRev(Integer.parseInt(aniorev));
                acomodoDetalle.setNoRev(Short.parseShort(norev));
                
                acomodoDetalle.setUsuarioCreo(request.getUserPrincipal().getName());
                acomodoDetalle.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                acomodoDetalle.setUsuarioModifico(request.getUserPrincipal().getName());
                acomodoDetalle.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                acomodoDetalle.setStatus((short) 1);
                if (inputStreamFile != null) {
                    acomodoDetalle.setMimeType(contenttype);
                    acomodoDetalle.setUrlAcomodo(fileName);
                }
                AcomodoDetalle acomodo= acomodoDetalleFacadeRemote.create(acomodoDetalle);
                saveFileOnServer();
                row_selected.setAcomodoDetalle(acomodo);
List<Producto> listProducto_selcted = new ArrayList<Producto>();
                for (Object reg_ids : asignedProductos) {                    
                    Object prodID=new Integer(reg_ids.toString());
                    Producto reg_found = productosFacadeRemote.findByPK(prodID);
                    listProducto_selcted.add(reg_found);                    
                }
                row_selected.setIdIdioma(parametroFacadeRemote.findByPK(asignedIdioma));
                row_selected.setProductoList(listProducto_selcted);
                servicioFacadeRemote.create(row_selected);
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to edit Row selected Page Detail
     * @return 
     */
    public String modifyRow(Acomodo row_selected) {
        String redirect = "/portal/sab/operaciones/acomodosDetalle?faces-redirect=true";
        modificarRegistro = true;
        inputStreamFile=null;
        this.row_selected = servicioFacadeRemote.findByPK_EAGER(row_selected.getIdAcomodo());
        
        
        asignedDestino = this.row_selected.getEstacionDestino().getIdEstacion();
        asignedOrigen = this.row_selected.getEstacionOrigen().getIdEstacion();
        asignedModeloAvion = this.row_selected.getModeloAvion().getIdModeloAvion();
        
        aniorev = this.row_selected.getAcomodoDetalle().getAnioRev()+"";
        norev = this.row_selected.getAcomodoDetalle().getNoRev()+"";
        contenttype = this.row_selected.getAcomodoDetalle().getMimeType();
        for (Producto producto : this.row_selected.getProductoList()) {            
            asignedProductos.add(producto.getIdProducto());
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

                
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                row_selected.setIdIdioma(parametroFacadeRemote.findByPK(asignedIdioma));
                AcomodoDetalle acomodoDetalle= row_selected.getAcomodoDetalle();
                acomodoDetalle.setUsuarioModifico(request.getUserPrincipal().getName());
                acomodoDetalle.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                acomodoDetalle.setAnioRev(Integer.parseInt(aniorev));
                acomodoDetalle.setNoRev(Short.parseShort(norev));
                if (inputStreamFile != null) {
                    acomodoDetalle.setMimeType(contenttype);
                    acomodoDetalle.setUrlAcomodo(fileName);
                }
                acomodoDetalleFacadeRemote.update(acomodoDetalle);
                if (inputStreamFile != null) {
                saveFileOnServer();
                }
                row_selected.setAcomodoDetalle(acomodoDetalle);
                List<Producto> listProducto_selcted = new ArrayList<Producto>();
                for (Object reg_ids : asignedProductos) {                    
                    Object prodID=new Integer(reg_ids.toString());
                    Producto reg_found = productosFacadeRemote.findByPK(prodID);
                    listProducto_selcted.add(reg_found);                    
                }
                row_selected.setProductoList(listProducto_selcted);
                servicioFacadeRemote.update(row_selected);
                all_records = null;
                asignedDestino =-1;
                asignedOrigen =-1;
                asignedModeloAvion=-1;
                aniorev =null;
                norev=null;
                        
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
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
                    this.contenttype =uploadedFile.getContentType();

                    if (inputStreamFile != null) {
                        fileName = uploadedFile.getFileName();
                    }
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {

        }

    }

    public void preparedownload(String filename_bd, String mimetypearcchivo) {
        InputStream stream = null;
        try {
            Path path = Paths.get(System.getProperty("AMX_PAB_MEDIA_FS_STORAGE") + "/" + filename_bd);
            byte[] content = Files.readAllBytes(path);
            String mimetype = mimetypearcchivo;
            String filename = filename_bd;
            stream = new ByteArrayInputStream(content);
            this.filedownload = new DefaultStreamedContent(stream, mimetype, filename);
        } catch (Exception e) {

        }
    }

    public Acomodo getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Acomodo row_selected) {
        this.row_selected = row_selected;
    }

    public List<Integer> getAsignedProductos() {
        return asignedProductos;
    }

    public void setAsignedProductos(List<Integer> asignedProductos) {
        this.asignedProductos = asignedProductos;
    }

    public List<SelectItem> getSelectProductos() {
        return selectProductos;
    }

    public void setSelectProductos(List<SelectItem> selectProductos) {
        this.selectProductos = selectProductos;
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

    public List<Acomodo> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Acomodo> all_records) {
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

    public Integer getAsignedModeloAvion() {
        return asignedModeloAvion;
    }

    public void setAsignedModeloAvion(Integer asignedModeloAvion) {
        this.asignedModeloAvion = asignedModeloAvion;
    }

    public List<SelectItem> getSelectTipoEquipos() {
        return selectTipoEquipos;
    }

    public void setSelectTipoEquipos(List<SelectItem> selectTipoEquipos) {
        this.selectTipoEquipos = selectTipoEquipos;
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

    public Integer getAsignedDestino() {
        return asignedDestino;
    }

    public void setAsignedDestino(Integer asignedDestino) {
        this.asignedDestino = asignedDestino;
    }

    public List<SelectItem> getSelectDestinos() {
        return selectDestinos;
    }

    public void setSelectDestinos(List<SelectItem> selectDestinos) {
        this.selectDestinos = selectDestinos;
    }

    public void setFiledownload(StreamedContent filedownload) {
        this.filedownload = filedownload;
    }

    public String getAniorev() {
        return aniorev;
    }

    public void setAniorev(String aniorev) {
        this.aniorev = aniorev;
    }

    public String getNorev() {
        return norev;
    }

    public void setNorev(String norev) {
        this.norev = norev;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
    
    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
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

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

}
