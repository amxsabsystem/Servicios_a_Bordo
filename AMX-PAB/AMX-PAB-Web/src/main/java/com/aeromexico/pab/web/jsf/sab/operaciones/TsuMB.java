package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.KitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.TsuFacadeRemote;
import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.KitTsu;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.MaterialTsu;

import com.aeromexico.pab.entity.Tsu;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
@Named(value = "tsuMB")
@SessionScoped
public class TsuMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(TsuMB.class.getName());
    public TsuMB() {
    }

    private Tsu row_selected = null;
    boolean modificarRegistro = false;
    private List<Tsu> all_records;
    private List<MaterialTsu> all_records_Material;
    private List<KitTsu> all_records_Kit;

    private String norev;
    private String aniorev;
    
    private String numeroParte;
    

    private Integer asignedClase;
    private List<SelectItem> selectClases;
    
    private String asignedMaterial;
    private String cantidadMat;
    private List<SelectItem> selectMateriales;
        
    private String asignedKit;
    private String cantidadKit;    
    private List<SelectItem> selectKits;

    private String contentTypeUploaded;
    private InputStream inputStreamFile;
    private String fileName;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tsu_RSB")
    TsuFacadeRemote tsuFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote claseFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    MaterialFacadeRemote materialFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/kitMaster_RSB")
    private KitMasterFacadeRemote kitMasterFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;

    LocaleInfo localeInfo;
    
    public static enum EstatusTSU{
        INITIAL,
        NEW_TSU,
        EDITING_TSU,
        EDITING_TSU_PICTURE,
        NEW_TSU_PICTURE
    };
    
    private EstatusTSU estatus=null;

    public List<Tsu> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Tsu>();
                all_records = tsuFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    @PostConstruct
    public void init() {
        logger.info("->init");
        asignedClase = -1;
        asignedMaterial = "-1";
        selectClases = new ArrayList<SelectItem>();
        selectClases.add(getLocaleInfo().createSelectIntKeyFirstItem());
        try {
            logger.info("->init: loading selectClases:");
            for (Clase regs : claseFacadeRemote.findAll()) {
                logger.info("->init: loading selectClases:\tClase:"+regs.getClave());
                selectClases.add(new SelectItem(regs.getIdClase(), regs.getNombreEsp()));
            }
            logger.info("->init: end loading selectClases");
        } catch (Exception ex) {
            logger.error("Error  loading selectClases:",ex);
        }
        selectMateriales = new ArrayList<SelectItem>();
        selectMateriales.add(getLocaleInfo().createSelectStringKeyFirstItem());
        try {
            for (Material regs : materialFacadeRemote.findAll()) {
                selectMateriales.add(new SelectItem(regs.getNumeroParte(), regs.getNumeroParte() + " - "
                        + (getLocaleInfo().getLanguage().equals("es") ? regs.getDescripcionEs() : regs.getDescripcionEn())
                ));
            }
        } catch (Exception ex) {
            logger.error("Error  loading selectMateriales:",ex);
        }
        
        selectKits = new ArrayList<SelectItem>();
        selectKits.add(getLocaleInfo().createSelectStringKeyFirstItem());
        try {
            for (KitMaster regs : kitMasterFacadeRemote.findAll()) {
                selectKits.add(new SelectItem(regs.getCveKitMaster(), 
                         (getLocaleInfo().getLanguage().equals("es") ? regs.getNombreEs(): regs.getNombreEn())
                ));
            }
        } catch (Exception ex) {
            logger.error("Error  loading selectKits:",ex);
        }        
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/tsu?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    public void deleteMaterialRow(MaterialTsu row) {
        List<MaterialTsu> retorno = new ArrayList<MaterialTsu>();
        for (MaterialTsu regs : all_records_Material) {
            if (!Objects.equals(regs.getIdMaterialTsu(), row.getIdMaterialTsu())) {
                retorno.add(regs);
            }
        }
        all_records_Material.clear();
        all_records_Material = retorno;
    }
    
    public void deleteKitRow(KitTsu row) {
        List<KitTsu> retorno = new ArrayList<KitTsu>();
        for (KitTsu regs : all_records_Kit) {
            if (!Objects.equals(regs.getIdKitTsu(), row.getIdKitTsu())) {
                retorno.add(regs);
            }
        }
        all_records_Kit.clear();
        all_records_Kit = retorno;
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

    public void addMaterial() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        MaterialTsu mat = new MaterialTsu();
        mat.setCantidad(Integer.parseInt(cantidadMat));
        mat.setMaterial(materialFacadeRemote.findByPK(asignedMaterial));
        mat.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);        
        if(all_records_Material==null)
            all_records_Material = new ArrayList<MaterialTsu>();
        all_records_Material.add(mat);        
    }
    
    public void addKit() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        KitTsu kit = new KitTsu();
        kit.setCantidad(Integer.parseInt(cantidadKit));
        kit.setKitMaster(kitMasterFacadeRemote.findByPK(asignedKit));
        kit.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
        if(all_records_Kit==null)
            all_records_Kit = new ArrayList<KitTsu>();
        all_records_Kit.add(kit);
    }

    public boolean validate(String boton) {
        boolean validationResult = true;
        
        if (all_records_Material==null || all_records_Material==null) {
            messgeValidateError(boton, "Debe seleccionar 1 Kit o Material");
            return false;
        }
        if ((all_records_Kit.size() + all_records_Material.size()) ==0) {
            messgeValidateError(boton, "Debe agregar por lo menos 1 Kit o Material");
            return false;
        }
        
        return validationResult;
    }

    /**
     * Save a new record
     */
    public void save() {        
        try {
            if (validate("form:saveButton")) {
                row_selected.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                
                tsuFacadeRemote.create(row_selected);
                addRow();
                all_records = null;
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * @return * Go to new Row Page Detail
     */
    public String addRow() {
        String redirect = "/portal/sab/operaciones/tsuDetalle?faces-redirect=true";
        modificarRegistro = false;
        all_records = null;
        row_selected = new Tsu();
        all_records_Kit      = null;
        all_records_Material = null;
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Tsu tsu) {
        String redirect = "/portal/sab/operaciones/tsuDetalle?faces-redirect=true";
        modificarRegistro = true;
        all_records = null;
        this.row_selected = tsuFacadeRemote.findByPK_EAGER(tsu.getIdTsu());
        norev = this.row_selected.getIdRevision().getValorEs();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        aniorev = sdf.format(this.row_selected.getFechaVigenciaFin());

        all_records_Kit = this.row_selected.getKitTsuHasTsuList();
        all_records_Material = this.row_selected.getMaterialTsuHasTsuList();
        
        estatus =EstatusTSU.EDITING_TSU;
        
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {        
        try {
            if (validate("form:updateButton")) {

                tsuFacadeRemote.update(row_selected);
                all_records = null;
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }


    public Tsu getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Tsu row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public String getNorev() {
        return norev;
    }

    public void setNorev(String norev) {
        this.norev = norev;
    }

    public String getAniorev() {
        return aniorev;
    }

    public void setAniorev(String aniorev) {
        this.aniorev = aniorev;
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

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getCantidadMat() {
        return cantidadMat;
    }

    public void setCantidadMat(String cantidadMat) {
        this.cantidadMat = cantidadMat;
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

    public List<MaterialTsu> getAll_records_Material() {
        return all_records_Material;
    }

    public List<KitTsu> getAll_records_Kit() {
        return all_records_Kit;
    }
    
    public String getAsignedKit() {
        return asignedKit;
    }

    public void setAsignedKit(String asignedKit) {
        this.asignedKit = asignedKit;
    }

    public String getCantidadKit() {
        return cantidadKit;
    }

    public void setCantidadKit(String cantidadKit) {
        this.cantidadKit = cantidadKit;
    }
    
    public List<SelectItem> getSelectKits() {
        return selectKits;
    }

    public EstatusTSU getEstatus() {
        return estatus;
    }

    public boolean isEstatus_INITIAL(){
        return estatus == EstatusTSU.INITIAL;
    }
    public boolean isEstatus_NEW_TSU(){
        return estatus == EstatusTSU.NEW_TSU;
    }
    public boolean isEstatus_EDITING_TSU(){
        return estatus == EstatusTSU.EDITING_TSU;
    }
    public boolean isEstatus_EDITING_TSU_PICTURE(){
        return estatus == EstatusTSU.EDITING_TSU_PICTURE;
    }
    public boolean isEstatus_NEW_TSU_PICTURE(){
        return estatus == EstatusTSU.NEW_TSU_PICTURE;
    }
    
    public LocaleInfo getLocaleInfo() {
        if(localeInfo == null){
            localeInfo = (LocaleInfo) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{localeInfo}", LocaleInfo.class)
                .getValue(FacesContext.getCurrentInstance().getELContext());            
        } 
        return localeInfo;
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
