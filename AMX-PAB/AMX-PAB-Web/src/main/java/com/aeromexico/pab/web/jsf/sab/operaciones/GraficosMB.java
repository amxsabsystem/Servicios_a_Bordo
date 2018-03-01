package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.remote.GraficoDetalleFacadeRemote;
import com.aeromexico.pab.backend.remote.GraficoFacadeRemote;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProductoFacadeRemote;
import com.aeromexico.pab.entity.Grafico;
import com.aeromexico.pab.entity.GraficoDetalle;
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
@Named(value = "graficosMB")
@SessionScoped
public class GraficosMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(GraficosMB.class.getName());
    
    private Grafico row_selected = null;
    boolean modificarRegistro = false;
    private StreamedContent filedownload;
    private List<GraficoDetalle> all_records;

    private InputStream inputStreamFile;
    private String fileName;
    private String mimetypeArchivo;
    private Integer asignedModeloAvion;
    private List<SelectItem> selectModelosAvion;

    private List<Integer> asignedProductos;
    private List<SelectItem> selectProductos;
        private Integer asignedIdioma;
    private List<SelectItem> selectIdiomas;

    private String aniorev;
    private String norev;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/grafico_RSB")
    GraficoFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/graficoDetalle_RSB")
    GraficoDetalleFacadeRemote graficoDetalleFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote modeloAvionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/producto_RSB")
    ProductoFacadeRemote productosFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    public GraficosMB() {
    }

    public List<GraficoDetalle> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<GraficoDetalle>();
                all_records = graficoDetalleFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    @PostConstruct
    public void init() {
        asignedModeloAvion = -1;

        selectModelosAvion = new ArrayList<SelectItem>();
        selectModelosAvion.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (ModeloAvion regs : modeloAvionFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectModelosAvion.add(new SelectItem(regs.getIdModeloAvion(), regs.getModelo()+" "+regs.getDescripcion()));
                }
            }
        } catch (Exception ex) {
        }

        asignedProductos = new ArrayList<Integer>();
        if (selectProductos == null) {
            selectProductos = new ArrayList<SelectItem>();
            for (Producto regs : productosFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectProductos.add(new SelectItem(regs.getIdProducto(), regs.getNombre()));
                }
            }
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
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/graficos?faces-redirect=true";
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
        String redirect = "/portal/sab/operaciones/graficosDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Grafico();
        inputStreamFile = null;
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedModeloAvion == -1) {
            messgeValidateError(boton, "Debe seleccionar un Tipo de Equipo");
            return false;
        }

        return retorno;
    }
    
    public GraficoDetalle getDetalle(Integer rowId){
        return graficoDetalleFacadeRemote.findByPK(rowId);
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
                //row_selected.setTipoEquipoAvion(tipoEquipoFacadeRemote.findByPK(asignedModeloAvion));
                
                row_selected.setIdIdioma(parametroFacadeRemote.findByPK(asignedIdioma));
                row_selected.setModeloAvion(modeloAvionFacadeRemote.findByPK(asignedModeloAvion));
                List<Producto> listProducto_selcted = new ArrayList<Producto>();
                for (Object reg_ids : asignedProductos) {                    
                    Object prodID=new Integer(reg_ids.toString());
                    Producto reg_found = productosFacadeRemote.findByPK(prodID);
                    listProducto_selcted.add(reg_found);                    
                }
                row_selected.setProductoList(listProducto_selcted);
                Grafico grafico = servicioFacadeRemote.create(row_selected);
                
                GraficoDetalle detalle = new GraficoDetalle();
                detalle.setGrafico(grafico);
                detalle.setAnioRev(Integer.parseInt(aniorev));
                detalle.setNoRev(Short.parseShort(norev));
                detalle.setStatus((short) 1);
                detalle.setUsuarioCreo(request.getUserPrincipal().getName());
                detalle.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                detalle.setUsuarioModifico(request.getUserPrincipal().getName());
                detalle.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                if (inputStreamFile != null) {
                    detalle.setMimeType(mimetypeArchivo);
                    detalle.setUrlGrafico(fileName);
                }
                graficoDetalleFacadeRemote.create(detalle);
                if (inputStreamFile != null) {
                saveFileOnServer();
                }
                all_records = null;
                asignedModeloAvion = -1;
                aniorev = null;
                norev = null;
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
     * @param row
     * @return
     */
    public String modifyRow(GraficoDetalle row) {
        String redirect = "/portal/sab/operaciones/graficosDetalle?faces-redirect=true";
        modificarRegistro = true;
        row_selected = servicioFacadeRemote.findByPK_EAGER(row.getGrafico().getIdGrafico());
        inputStreamFile=null;
        asignedModeloAvion = this.row_selected.getModeloAvion().getIdModeloAvion();
        
        aniorev = row.getAnioRev()+"";
        norev = row.getNoRev()+"";
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
                List<Producto> listProducto_selcted = new ArrayList<Producto>();
                for (Object reg_ids : asignedProductos) {                    
                    Object prodID=new Integer(reg_ids.toString());
                    Producto reg_found = productosFacadeRemote.findByPK(prodID);
                    listProducto_selcted.add(reg_found);                    
                }
                row_selected.setIdIdioma(parametroFacadeRemote.findByPK(asignedIdioma));
                row_selected.setProductoList(listProducto_selcted);
                servicioFacadeRemote.update(row_selected);
                all_records = null;
                asignedModeloAvion = -1;
                aniorev = null;
                norev = null;
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

    public void preparedownload(String filename_bd, String mimetypeArchivo) {
        InputStream stream = null;
        try {
            Path path = Paths.get(System.getProperty("AMX_PAB_MEDIA_FS_STORAGE") + "/" + filename_bd);
            byte[] content = Files.readAllBytes(path);
            String mimetype = mimetypeArchivo;
            String filename = filename_bd;
            stream = new ByteArrayInputStream(content);
            this.filedownload = new DefaultStreamedContent(stream, mimetype, filename);
        } catch (Exception e) {

        }
    }

    public void fileListener(FileUploadEvent event) {
        try {

            if (event.getFile() != null) {
                UploadedFile uploadedFile = event.getFile();
                if ((uploadedFile != null && uploadedFile.getSize() > 0)) {
                    this.inputStreamFile = uploadedFile.getInputstream();
                    this.mimetypeArchivo = uploadedFile.getContentType();
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

    public Grafico getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Grafico row_selected) {
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

    public List<GraficoDetalle> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<GraficoDetalle> all_records) {
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

    public List<SelectItem> getSelectModelosAvion() {
        return selectModelosAvion;
    }

    public void setSelectModelosAvion(List<SelectItem> selectModelosAvion) {
        this.selectModelosAvion = selectModelosAvion;
    }

    public ProductoFacadeRemote getProductosFacadeRemote() {
        return productosFacadeRemote;
    }

    public void setProductosFacadeRemote(ProductoFacadeRemote productosFacadeRemote) {
        this.productosFacadeRemote = productosFacadeRemote;
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

    public String getMimetypeArchivo() {
        return mimetypeArchivo;
    }

    public void setMimetypeArchivo(String mimetypeArchivo) {
        this.mimetypeArchivo = mimetypeArchivo;
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
    

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }
}
