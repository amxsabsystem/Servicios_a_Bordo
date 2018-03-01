package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.KitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialKitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.MaterialKitMaster;
import com.aeromexico.pab.entity.MaterialKitMasterPK;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alfredo Estrada
 */
@Named(value = "masterKitMB")
@SessionScoped
public class MasterKitMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(MasterKitMB.class.getName());

    private List<KitMaster> all_records;
    private HashMap<String, List<MaterialKitMaster>> all_records_material_kit;

    private boolean   km_create       = false;
    private boolean   imageUploaded   = false;
    private KitMaster km_selected = null;
    private MaterialKitMaster mkm_selected = null;
    
    private List<MaterialKitMaster> mkm_list;
    
    private List<Parametro> allParametros;
    
    private List<SelectItem> selectMateriales;
    private List<SelectItem> selectUnidades;
    private List<SelectItem> selectInstNacionales;
    private List<SelectItem> selectInstInternac;    
    
    public static final int TIPOKIT_AGRANEL = 59;
    public static final int TIPOKIT_LOTE = 60;
    
    public static enum EstatusMasterKit{
        INITIAL,
        NEW_MASTER_KIT,
        EDITING_MASTER_KIT,
        EDITING_MASTER_KIT_PICTURE,
        EDITING_MATERIAL_KIT,
        NEW_MASTER_KIT_PICTURE,
        NEW_MASTER_MATERIAL_KIT
    };
    
    
    private FormDTO formDTO;
    
    private EstatusMasterKit estatus=null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/kitMaster_RSB")
    private KitMasterFacadeRemote kitMasterFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/materialKitMaster_RSB")
    private MaterialKitMasterFacadeRemote materialKitMasterFacadeRemote;        
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    private MaterialFacadeRemote materialFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    private ParametroFacadeRemote ParametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;
    
    //Lazy-Inject
    LocaleInfo localeInfo; 
    
    public MasterKitMB() {
        logger.debug("--->>>MasterKitMB():");
    }
    
    @PostConstruct
    public void init() {
        logger.debug("--->>>init():");
        estatus = EstatusMasterKit.INITIAL;
        formDTO = new FormDTO();
        km_selected = null;        
        mkm_selected = null;
        imageUploaded   = false;
        mkm_list = new ArrayList<MaterialKitMaster>();
    }

    public List<KitMaster> findAll() {
        logger.debug("--->>>findAll(): all_records is null?="+(all_records == null));
        if (all_records == null) {
            try {
                
                all_records = kitMasterFacadeRemote.findAll();
                all_records_material_kit = new HashMap<String, List<MaterialKitMaster>>();                                
                
                List<MaterialKitMaster> allMMList = materialKitMasterFacadeRemote.findAll();
                for(KitMaster km:all_records){                
                    for(MaterialKitMaster mkm:allMMList){
                        if (mkm.getMaterialKitMasterPK().getCveKitMaster().equals(km.getCveKitMaster())) {
                            if (!all_records_material_kit.containsKey(km.getCveKitMaster())) {
                                all_records_material_kit.put(km.getCveKitMaster(), new ArrayList<MaterialKitMaster>());
                            }                            
                            all_records_material_kit.get(km.getCveKitMaster()).add(mkm);
                        }
                    }
                }
                logger.debug("--->>>findAll(): ok, all list loaded !");
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
        return all_records;
    }

    public List<MaterialKitMaster> findAllKit(Object idKitMaster) {
        return all_records_material_kit.get(idKitMaster.toString());
    }

    public void checkboxChangedMasterKit() {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("./masterKitDetalle.xhtml");
        } catch (IOException e) {
        }
    }

    public void checkboxChangedMasterAGranel() {
        try {            
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("./masterKitDetalle.xhtml");
        } catch (IOException e) {
        }
    }
    
    public String createMasterKit() {
        return createMaster(TIPOKIT_LOTE);
    }
    
    public String createMasterAGranel() {
        return createMaster(TIPOKIT_AGRANEL);
    }
    
    public String createMaster(int tipoKit) {
        km_create = true;
        imageUploaded = false;
        String redirect = "/portal/sab/operaciones/masterKitDetalle?faces-redirect=true";;
        logger.debug("--->>>createMasterKit():tipoKit="+tipoKit);
        km_selected = new KitMaster();
        mkm_list = new ArrayList<MaterialKitMaster>();
        Parametro idTipoKit = getParametro(tipoKit);
        km_selected.setIdTipoKit(idTipoKit);
        estatus = EstatusMasterKit.NEW_MASTER_KIT;
        fillFormDTOFromKM (formDTO,km_selected);
        fillFormDTOFromMKM(formDTO,null);
        return redirect;
    }
    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected_kit
     * @return
     */
    
    public String modifyRowKit(KitMaster row_selected_kit) {
        String redirect = "/portal/home";
        logger.debug("--->>>modifyRowKit: CveKitMaster="+row_selected_kit.getCveKitMaster());
        this.km_selected = row_selected_kit;
        imageUploaded = false;
        final Parametro idTipoKit = this.km_selected.getIdTipoKit();
        if (idTipoKit.getIdParametro() == TIPOKIT_AGRANEL) {
            redirect = "/portal/sab/operaciones/masterKitDetalle?faces-redirect=true";
            estatus = EstatusMasterKit.EDITING_MASTER_KIT;
            editMasterKit(this.km_selected.getCveKitMaster());
        } else if (idTipoKit.getIdParametro() == TIPOKIT_LOTE) {
            redirect = "/portal/sab/operaciones/masterKitDetalle?faces-redirect=true";
            estatus = EstatusMasterKit.EDITING_MASTER_KIT;
            editMasterKit(this.km_selected.getCveKitMaster());
        }
        return redirect;
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/masterSelect?faces-redirect=true";        
        all_records = null;
        estatus = EstatusMasterKit.INITIAL;
        return redirect;
    }
    
    public void changePic(){
        logger.debug("->changePic:");
        estatus = EstatusMasterKit.EDITING_MASTER_KIT_PICTURE;
    }
    
    public void addPic(){
        logger.debug("->changePic:");
        estatus = EstatusMasterKit.NEW_MASTER_KIT_PICTURE;
    }
    
    public void cancelChangePic(){
        logger.debug("->cancelChangePic:");
        if(km_create){
            estatus = EstatusMasterKit.NEW_MASTER_KIT;
        } else{
            estatus = EstatusMasterKit.EDITING_MASTER_KIT;
        }
        
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            throw new IllegalStateException("Not implemented");
            //messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void addMaterial() {
        logger.debug("-->> addMaterial: estatus="+formDTO.mkm_status_record);
        if(formDTO.mkm_status_record == ESTATUS_RECORD_MATERIAL_NEW){
            if(isTipoKit_AGRANEL()){
                formDTO.cveKitMaster = mkm_selected.getMaterial().getNumeroParte();
            }
            
            mkm_selected.setReciclable      (formDTO.mkm_reciclable?Constantes.PERSITENCE_TRUE:Constantes.PERSITENCE_FALSE);
            mkm_selected.setObservaciones   (formDTO.mkm_observaciones);
            mkm_selected.setCantidad        (formDTO.mkm_cantidad);
            mkm_selected.setIdUnidadMedida  (getParametro(formDTO.mkm_idUnidadMedida));
            final MaterialKitMasterPK materialKitMasterPK = new MaterialKitMasterPK();
            materialKitMasterPK.setNumeroParte (formDTO.mkm_numeroParte);
            materialKitMasterPK.setCveKitMaster(formDTO.cveKitMaster);
            mkm_selected.setMaterialKitMasterPK(materialKitMasterPK);
            mkm_list.add(mkm_selected);
            
            formDTO.mkm_status_record = ESTATUS_RECORD_MATERIAL_INITAL;
            if(km_create){
                estatus = EstatusMasterKit.NEW_MASTER_KIT;
            } else {
                estatus = EstatusMasterKit.EDITING_MASTER_KIT;
            }
            fillFormDTOFromMKM(formDTO,null);
        } else {
            throw new IllegalStateException("This state is wrong");
        }
    }
    
    public void updateMaterial() {
        logger.debug("-->> updateMaterial: estatus="+formDTO.mkm_status_record);
        if(formDTO.mkm_status_record == ESTATUS_RECORD_MATERIAL_EDITING){            
            mkm_selected.setReciclable      (formDTO.mkm_reciclable?Constantes.PERSITENCE_TRUE:Constantes.PERSITENCE_FALSE);
            mkm_selected.setObservaciones   (formDTO.mkm_observaciones);
            mkm_selected.setCantidad        (formDTO.mkm_cantidad);
            mkm_selected.setIdUnidadMedida  (getParametro(formDTO.mkm_idUnidadMedida));
            
            formDTO.mkm_status_record = ESTATUS_RECORD_MATERIAL_INITAL;
            if(km_create){
                estatus = EstatusMasterKit.NEW_MASTER_KIT;
            } else {
                estatus = EstatusMasterKit.EDITING_MASTER_KIT;
            }
            fillFormDTOFromMKM(formDTO,null);
        } else {
            throw new IllegalStateException("This state is wrong");
        }
    }
    
    public void cancelMaterial() {
        logger.debug("-->> cancelMaterial: estatus="+formDTO.mkm_status_record);
        
        formDTO.mkm_status_record = ESTATUS_RECORD_MATERIAL_INITAL;
        if(km_create){
            estatus = EstatusMasterKit.NEW_MASTER_KIT;
        } else {
            estatus = EstatusMasterKit.EDITING_MASTER_KIT;
        }
        fillFormDTOFromMKM(formDTO,null);

    }

    /**
     * Save a new record
     */
    public void saveKit() {
        try {
            logger.info("---------------------------------------------->> saveKit():CveKitMaster="+formDTO.getCveKitMaster()+", AGRANEL?"+isTipoKit_AGRANEL());
            km_selected.setCveKitMaster (formDTO.getCveKitMaster());
            km_selected.setNombreEs     (formDTO.getNombreEs());
            km_selected.setNombreEn     (formDTO.getNombreEn());
            km_selected.setContenedor   (formDTO.getContenedor());
            
            Parametro paramIdUnidadMedida=new Parametro();
            paramIdUnidadMedida.setIdParametro(formDTO.getIdUnidadMedida());
            km_selected.setIdUnidadMedida(paramIdUnidadMedida);
            
            Parametro paramIdInstruccionesNacionales=new Parametro();
            paramIdInstruccionesNacionales.setIdParametro(formDTO.getIdInstruccionesNacionales());
            km_selected.setIdInstruccionesNacionales(paramIdInstruccionesNacionales);
            
            Parametro paramIdInstruccionesInternac=new Parametro();
            paramIdInstruccionesInternac.setIdParametro(formDTO.getIdInstruccionesInternac());
            km_selected.setIdInstruccionesInternac(paramIdInstruccionesInternac);
            
            km_selected.setStatus(
                    formDTO.isEstatus()?
                            Constantes.PERSITENCE_STATUS_ACTIVO:
                            Constantes.PERSITENCE_STATUS_IANACTIVO);
            
            km_selected.setMimeType     (formDTO.getMimeType());
            km_selected.setUrlMultimedia(formDTO.getUrlMultimedia());
            if(imageUploaded){
                logger.debug("imageUploaded, then commit !");
                storageServiceFacadeRemote.commitSaveOrUpdateMedia(formDTO.getUrlMultimedia());
            }
            logger.debug("before create:IdUnidadMedida="+km_selected.getIdUnidadMedida()+", IdTipoKit="+km_selected.getIdTipoKit()+", InstruccionesInternac="+km_selected.getIdInstruccionesInternac()+", InstruccionesNacionales="+km_selected.getIdInstruccionesNacionales()+", IdUnidadMedida="+km_selected.getIdUnidadMedida());
            kitMasterFacadeRemote.create(km_selected);
            logger.debug("persistence, saved ok, mkm_list shuld be 1 item !");
			for(MaterialKitMaster mkm: mkm_list){
				final MaterialKitMasterPK mkm_PK_create = new MaterialKitMasterPK();
				
				mkm_PK_create.setCveKitMaster   (km_selected.getCveKitMaster());
				mkm_PK_create.setNumeroParte    (mkm.getMaterial().getNumeroParte());
				
				mkm.setMaterialKitMasterPK(mkm_PK_create);
                
                mkm.setIdInstruccionesInternac  (km_selected.getIdInstruccionesInternac());
                mkm.setIdInstruccionesNacionales(km_selected.getIdInstruccionesNacionales());
                mkm.setStatus                   (Constantes.PERSITENCE_STATUS_ACTIVO);
                
				materialKitMasterFacadeRemote.create(mkm);
				logger.debug("\tpersistence, created mkm ok");
			}
            
            messgeValidateInfo("form:updateButton", "Registro agregado correctamente ");
        } catch (Exception e) {
            logger.error("-->> Afetr save:", e);
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }
    /**
     * Update selected record
     */
    public void updateKit() {
        try {
            logger.debug("---------------------------------------------->> updateKit():");
            km_selected.setNombreEs  (formDTO.getNombreEs());
            km_selected.setNombreEn  (formDTO.getNombreEn());
            km_selected.setContenedor(formDTO.getContenedor());
            
            Parametro paramIdUnidadMedida=new Parametro();
            paramIdUnidadMedida.setIdParametro(formDTO.getIdUnidadMedida());
            km_selected.setIdUnidadMedida(paramIdUnidadMedida);
            
            Parametro paramIdInstruccionesNacionales=new Parametro();
            paramIdInstruccionesNacionales.setIdParametro(formDTO.getIdInstruccionesNacionales());
            km_selected.setIdUnidadMedida(paramIdInstruccionesNacionales);
            
            Parametro paramIdInstruccionesInternac=new Parametro();
            paramIdInstruccionesInternac.setIdParametro(formDTO.getIdInstruccionesInternac());
            km_selected.setIdInstruccionesInternac(paramIdInstruccionesInternac);
            
            km_selected.setStatus(
                    formDTO.isEstatus()?
                            Constantes.PERSITENCE_STATUS_ACTIVO:
                            Constantes.PERSITENCE_STATUS_IANACTIVO);
            
            km_selected.setMimeType     (formDTO.getMimeType());
            km_selected.setUrlMultimedia(formDTO.getUrlMultimedia());
			if(imageUploaded){
                storageServiceFacadeRemote.commitSaveOrUpdateMedia(formDTO.getUrlMultimedia());
            }
            List allMKM_deleteList = genericSearchFacadeRemote.loadQuery("SELECT m.materialKitMasterPK.numeroParte FROM MaterialKitMaster m WHERE m.materialKitMasterPK.cveKitMaster='"+km_selected.getCveKitMaster()+"'");
			logger.debug("\t-->> updateKit(): ok, getting all mkm for delete and add after:CveKitMaster="+km_selected.getCveKitMaster());
			for(Object mkm_oa: allMKM_deleteList){
                //MaterialKitMaster mkm_d=(MaterialKitMaster);
                String numeroParte = (String)mkm_oa;
                MaterialKitMaster mkm_delete=new MaterialKitMaster();
                final MaterialKitMasterPK mkm_PK_detele = new MaterialKitMasterPK();
                mkm_PK_detele.setCveKitMaster(km_selected.getCveKitMaster());
                mkm_PK_detele.setNumeroParte (numeroParte);
                mkm_delete.setMaterialKitMasterPK(mkm_PK_detele);
                
                logger.debug("\t-->> updateKit(): before remove mkm ("+km_selected.getCveKitMaster()+","+numeroParte+"):");
				materialKitMasterFacadeRemote.remove(mkm_delete);
				logger.debug("\t-->> updateKit(): removed mkm ok");
			}
			logger.debug("\t-->> updateKit(): all for insert ");
			for(MaterialKitMaster mkm: mkm_list){
				final MaterialKitMasterPK mkm_PK_create = new MaterialKitMasterPK();
				
				mkm_PK_create.setCveKitMaster   (km_selected.getCveKitMaster());
				mkm_PK_create.setNumeroParte    (mkm.getMaterial().getNumeroParte());
				
				mkm.setMaterialKitMasterPK(mkm_PK_create);
                mkm.setIdInstruccionesInternac  (km_selected.getIdInstruccionesInternac());
                mkm.setIdInstruccionesNacionales(km_selected.getIdInstruccionesNacionales());
                mkm.setStatus                   (Constantes.PERSITENCE_STATUS_ACTIVO);
                
				materialKitMasterFacadeRemote.create(mkm);
				logger.debug("\t-->> updateKit(): persistence, created mkm ok");
			}
            
            kitMasterFacadeRemote.update(km_selected);
            logger.debug("-->> updateKit(): persistence, saved ok");
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {
            logger.error("-->> Afetr UPDATE:",e);
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRow() {
        String redirect = "/portal/sab/operaciones/masterDetalle?faces-redirect=true";
        mkm_selected = new MaterialKitMaster();
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected_kit
     * @return
     */
    public void editMasterKit(String cveKitMaster) {
        logger.debug("--->>>editMasterKit:cveKitMaster="+cveKitMaster);
        
        this.km_selected = kitMasterFacadeRemote.findByPK_EAGER(cveKitMaster);
        
        final Parametro idTipoKit = this.km_selected.getIdTipoKit();
        
        MaterialKitMaster mkmForM=new MaterialKitMaster();
        mkmForM.setKitMaster(km_selected);
        mkm_list = materialKitMasterFacadeRemote.findAllLike(mkmForM);
        logger.debug("--->>>modifyRow:mkm_list.size="+mkm_list.size()+" {");
        for(MaterialKitMaster mkm:mkm_list){
            logger.debug("\t--->>>modifyRow:mkm.material="+mkm.getMaterial());
        }
        logger.debug("--->>>modifyRow: }");
        fillFormDTOFromKM (formDTO,km_selected);
        fillFormDTOFromMKM(formDTO,null);
        estatus = EstatusMasterKit.EDITING_MASTER_KIT;
    }    
    
    public void modifyRowMaterial(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        String numeroParte = params.get("numeroParte");
        
        formDTO.mkm_numeroParte = numeroParte;
        logger.debug("\t--->>>SELECT FORCED:"+formDTO.mkm_numeroParte);
        logger.debug("\t--->>>modifyRowMaterial: "+numeroParte);

        editMaterialFromList(numeroParte);
    }
    
    public void deleteRowMaterial(){        
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        String numeroParte = params.get("numeroParte");
        logger.debug("\t--->>>deleteRowMaterial: "+numeroParte);
        Date today=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");

        if(numeroParte != null){
            List<MaterialKitMaster> mkm_list_afterDelete = new ArrayList<MaterialKitMaster>();
            boolean deleted=false;
            for(MaterialKitMaster mkm : mkm_list){
                if(!mkm.getMaterialKitMasterPK().getNumeroParte().equals(numeroParte)){
                    mkm_list_afterDelete.add(mkm);
                    deleted=true;
                }
            }
            if(deleted){                
                mkm_selected = null;
            }
            logger.debug("\t--->>>deleteRowMaterial: deleted?"+deleted);
            mkm_list = mkm_list_afterDelete;
        }
    }
    
    public void materialChanged(){
        logger.debug("--->>> materialChanged :formDTO.mkm_numeroParte="+formDTO.mkm_numeroParte);
        editMaterialFromList(formDTO.mkm_numeroParte);
    }
    
    private void editMaterialFromList(String numeroParte){
        logger.info("--->>> editMaterialFromList: numeroParte="+numeroParte);
        
        if(numeroParte!=null && !numeroParte.equals("-1")){
            
            if(km_create && isTipoKit_AGRANEL()){
                formDTO.setCveKitMaster(numeroParte);
            }
            
            estatus = EstatusMasterKit.EDITING_MATERIAL_KIT;
            mkm_selected = null;
            for(MaterialKitMaster mkm:mkm_list){
                if(mkm.getMaterialKitMasterPK().getNumeroParte().equals(numeroParte)){
                    mkm_selected = mkm;
                    break;
                }
            }
            
            if(mkm_selected != null){
                logger.debug("\t--->>>editMaterialFromList: [===]");
                fillFormDTOFromMKM(formDTO, mkm_selected);                                
            } else{
                logger.debug("\t--->>>editMaterialFromList: [___]");
                mkm_selected = new MaterialKitMaster();
                Material materialSelected = null;
                materialSelected = materialFacadeRemote.findByPK_EAGER(numeroParte);
                mkm_selected.setMaterial(materialSelected);                
                fillFormDTOFromMKM(formDTO, mkm_selected);                
            }
        } else {
            if(km_create && isTipoKit_AGRANEL()){
                formDTO.setCveKitMaster("-1");
            }
            logger.debug("\t--->>>editMaterialFromList: [XXX]");
            fillFormDTOFromMKM(formDTO, null);
        }
    }
    
    /**
     * Update selected record
     */
    public void update() {
        try {
            //row_selected.setStatus((short)1);
            //ParametroFacadeRemote.update(mkm_selected);            
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public void fileListener(FileUploadEvent event) {
        logger.debug("-->>>fileListener:");
        try {
            UploadedFile uploadedFile = event.getFile();
            logger.debug("-->>>fileListener: uploadedFile="+uploadedFile);
            if (uploadedFile != null && uploadedFile.getSize() > 0 ) {
                logger.debug("-->>>fileListener: uf: size="+uploadedFile.getSize()+", fileName="+uploadedFile.getFileName()+", contentType="+uploadedFile.getContentType());                

                InputStream is             = uploadedFile.getInputstream();
                String fileNameUploaded    = uploadedFile.getFileName();
                String contentTypeUploaded = uploadedFile.getContentType();                
                String          urlUploaded=null;
                
                OutputStream os = new ByteArrayOutputStream();                
                byte[] buffer = new byte[1024*32];
                int r;
                while((r = is.read(buffer, 0, buffer.length))!= -1){
                    os.write(buffer, 0, r);
                    os.flush();
                }
                byte[] contentUploaded = ((ByteArrayOutputStream)os).toByteArray();
                final String httpSessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);                
                urlUploaded = storageServiceFacadeRemote.saveOrUpdateMedia(contentUploaded, fileNameUploaded, contentTypeUploaded, httpSessionId);
                logger.debug("-->>>after storageServiceFacadeRemote.saveOrUpdateMedia: urlUploaded="+urlUploaded+", contentTypeUploaded="+contentTypeUploaded);
                
                formDTO.setUrlMultimedia(urlUploaded);
                formDTO.setMimeType(contentTypeUploaded);                
                
                imageUploaded = true;
                
                if(km_create){
                    estatus = EstatusMasterKit.NEW_MASTER_KIT;
                }else{
                    estatus = EstatusMasterKit.EDITING_MASTER_KIT;
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {
            logger.error("--->>>fileListener: saving :( ?", ex);
        }
    }

    public List<MaterialKitMaster> getMkm_list() {
        return mkm_list;
    }
    
    public MaterialKitMaster getMkm_selected() {
        logger.debug("--->>>getMkm_selected():");
        return mkm_selected;
    }

    public void setMkm_selected(MaterialKitMaster mkm_selected) {
        this.mkm_selected = mkm_selected;
    }

    public List<SelectItem> getSelectMateriales() {
        if(selectMateriales == null){
            selectMateriales = new ArrayList<SelectItem>();
            selectMateriales.add(getLocaleInfo().createSelectStringKeyFirstItem());        
            for (Material regs : materialFacadeRemote.findAll()) {
                selectMateriales.add(new SelectItem(regs.getNumeroParte(), regs.getNumeroParte() + " - "
                        + (getLocaleInfo().getLanguage().equals("es") ? regs.getDescripcionEs() : regs.getDescripcionEn())
                ));
            }

        }
        return selectMateriales;
    }

    public void setSelectMateriales(List<SelectItem> selectMateriales) {
        this.selectMateriales = selectMateriales;
    }

    public List<SelectItem> getSelectInstNacionales() {
        if(selectInstNacionales == null){
            selectInstNacionales = new ArrayList<SelectItem>();
            selectInstNacionales.add(getLocaleInfo().createSelectIntKeyFirstItem());

            for (Parametro regs : allParametros) {
                if (regs.getClave().equals("insEstNac")) {
                    selectInstNacionales.add(new SelectItem(regs.getIdParametro(),
                            (getLocaleInfo().getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                    ));
                }
            }


        }
        return selectInstNacionales;
    }

    public void setSelectInstNacionales(List<SelectItem> selectInstNacionales) {
        this.selectInstNacionales = selectInstNacionales;
    }

    public List<SelectItem> getSelectInstInternac() {
        if(selectInstInternac == null){
            selectInstInternac = new ArrayList<SelectItem>();
            selectInstInternac.add(getLocaleInfo().createSelectIntKeyFirstItem());

            for (Parametro regs : allParametros) {
                if (regs.getClave().equals("insEstIn")) {
                    selectInstInternac.add(new SelectItem(regs.getIdParametro(),
                            (getLocaleInfo().getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                    ));
                }
            }
        }
        return selectInstInternac;
    }

    public void setSelectInstInternac(List<SelectItem> selectInstInternac) {
        this.selectInstInternac = selectInstInternac;
    }

    public List<SelectItem> getSelectUnidades() {
        if( selectUnidades == null){
            selectUnidades = new ArrayList<SelectItem>();
            selectUnidades.add(getLocaleInfo().createSelectIntKeyFirstItem());

            for (Parametro regs : getAllParametros()) {
                logger.debug("claves:" + regs.getClave());

                if (regs.getClave().equals("um")) {
                    selectUnidades.add(new SelectItem(regs.getIdParametro(),
                            (getLocaleInfo().getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                    ));
                }
            }
        }
        return selectUnidades;
    }

    public void setSelectUnidades(List<SelectItem> selectUnidades) {
        this.selectUnidades = selectUnidades;
    }

    public KitMaster getKm_selected() {
        return km_selected;
    }

    public void setKm_selected(KitMaster km_selected) {
        this.km_selected = km_selected;
    }
    
    public Parametro getParametro(int idParaemtro){
        for(Parametro p:getAllParametros()){
            if(p.getIdParametro().intValue() == idParaemtro){
                return p;
            }
        }
        return null;
    }

    public List<Parametro> getAllParametros() {
        if(allParametros == null){
            allParametros = ParametroFacadeRemote.findAll();
        }
        return allParametros;
    }
    
    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }
    
    public LocaleInfo getLocaleInfo() {
        if(localeInfo == null){
            localeInfo = (LocaleInfo) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{localeInfo}", LocaleInfo.class)
                .getValue(FacesContext.getCurrentInstance().getELContext());
            logger.debug("--->>> getLocaleInfo(): localeInfo="+localeInfo);
        }               
        return localeInfo;
    }
        
    public EstatusMasterKit getEstatus() {
        return estatus;
    }

    public boolean isEstatus_INITIAL(){
        return estatus == EstatusMasterKit.INITIAL;
    }
    public boolean isEstatus_NEW_MASTER_KIT(){
        return estatus == EstatusMasterKit.NEW_MASTER_KIT;
    }
    public boolean isEstatus_EDITING_MASTER_KIT(){
        return estatus == EstatusMasterKit.EDITING_MASTER_KIT;
    }
    public boolean isEstatus_EDITING_MASTER_KIT_PICTURE(){
        return estatus == EstatusMasterKit.EDITING_MASTER_KIT_PICTURE;
    }
    public boolean isEstatus_EDITING_MATERIAL_KIT(){
        return estatus == EstatusMasterKit.EDITING_MATERIAL_KIT;
    }
    public boolean isEstatus_NEW_MASTER_KIT_PICTURE(){
        return estatus == EstatusMasterKit.NEW_MASTER_KIT_PICTURE;
    }
    public boolean isEstatus_NEW_MASTER_MATERIAL_KIT(){
        return estatus == EstatusMasterKit.NEW_MASTER_MATERIAL_KIT;
    }
    
    public boolean isTipoKit_LOTE(){
        return km_selected.getIdTipoKit().getIdParametro() == TIPOKIT_LOTE;
    }
    public boolean isTipoKit_AGRANEL(){
        return km_selected.getIdTipoKit().getIdParametro() == TIPOKIT_AGRANEL;
    }
    public boolean isCanAddMaterial(){
        if(km_selected.getIdTipoKit().getIdParametro() == TIPOKIT_AGRANEL){
            if(mkm_list!=null){
               return mkm_list.size() < 1;
            }else{
                throw new IllegalStateException();
            }
        }else if(km_selected.getIdTipoKit().getIdParametro() == TIPOKIT_LOTE){
            return true;
        }
        return true;
    }
    
    public FormDTO getFormDTO() {
        return formDTO;
    }
    
    private void fillFormDTOFromKM(FormDTO f,KitMaster km){
        if(km !=null){
            if(!km_create){
                f.setCveKitMaster(km.getCveKitMaster());
                f.setNombreEs(km.getNombreEs());
                f.setNombreEn(km.getNombreEn());
                f.setContenedor(km.getContenedor());
                f.setIdUnidadMedida((int) km.getIdUnidadMedida().getIdParametro());
                f.setIdInstruccionesNacionales((int) km.getIdInstruccionesNacionales().getIdParametro());
                f.setIdInstruccionesInternac((int) km.getIdInstruccionesInternac().getIdParametro());
                f.setEstatus(km.getStatus()==Constantes.PERSITENCE_STATUS_ACTIVO);
                f.setUrlMultimedia(km.getUrlMultimedia());
                f.setMimeType(km.getMimeType());
            } else{
                f.setCveKitMaster(null);
                f.setNombreEs(null);
                f.setNombreEn(null);
                f.setContenedor(null);
                f.setIdUnidadMedida(0);
                f.setIdInstruccionesNacionales(0);
                f.setIdInstruccionesInternac(0);
                f.setEstatus(false);
                f.setUrlMultimedia(null);
                f.setMimeType(null);
            }
        } else {
            f.setCveKitMaster(null);
            f.setNombreEs(null);
            f.setNombreEn(null);
            f.setContenedor(null);
            f.setIdUnidadMedida(0);
            f.setIdInstruccionesNacionales(0);
            f.setIdInstruccionesInternac(0);
            f.setEstatus(false);
            f.setUrlMultimedia(null);
            f.setMimeType(null);
        }        
    }
    
    private void fillFormDTOFromMKM(FormDTO f,MaterialKitMaster mkm){
        if(mkm != null){
            final Material m = mkm.getMaterial();
            f.setM_descripcionEs(m.getDescripcionEs());        
            f.setM_descripcionEn(m.getDescripcionEn());
            f.setM_peso(m.getPeso());

            if(mkm.getMaterialKitMasterPK() != null){
                f.setMkm_status_record(ESTATUS_RECORD_MATERIAL_EDITING);
                f.setMkm_reciclable     (mkm.getReciclable()==Constantes.PERSITENCE_TRUE);
                f.setMkm_cantidad       (mkm.getCantidad());
                f.setMkm_idUnidadMedida (mkm.getIdUnidadMedida().getIdParametro());
                f.setMkm_observaciones  (mkm.getObservaciones());
            } else{
                f.setMkm_status_record(ESTATUS_RECORD_MATERIAL_NEW);
                f.setMkm_reciclable     (false);                
                f.setMkm_cantidad       (0);
                f.setMkm_idUnidadMedida (-1);
                f.setMkm_observaciones  (null);
            }
        } else {
            f.setMkm_status_record(ESTATUS_RECORD_MATERIAL_INITAL);
            f.setM_descripcionEs(null);
            f.setM_descripcionEn(null);
            f.setM_peso(0.0);

            f.setMkm_numeroParte    ("-1");
            f.setMkm_cantidad       (0);
            f.setMkm_idUnidadMedida (-1);
            f.setMkm_reciclable     (false);
            f.setMkm_observaciones  (null);
        }
    }
    
    public static final int ESTATUS_RECORD_MATERIAL_INITAL  = 0;
    public static final int ESTATUS_RECORD_MATERIAL_NEW     = 1;
    public static final int ESTATUS_RECORD_MATERIAL_EDITING = 2;
    
    public static class FormDTO {
        private int     tipoKit;
        private String  cveKitMaster;
        private String  nombreEs;
        private String  nombreEn;
        private String  contenedor;
        private int     idUnidadMedida;
        private int     idInstruccionesNacionales;
        private int     idInstruccionesInternac;
        private String  urlMultimedia;
        private String  mimeType;
        private boolean estatus;
        
        private String  m_descripcionEs;
        private String  m_descripcionEn;
        private double  m_peso;
        
        private int     mkm_status_record;
        private String  mkm_numeroParte;
        private int     mkm_idUnidadMedida;
        private int     mkm_cantidad;
        private boolean mkm_reciclable;
        private String  mkm_observaciones;        

        /**
         * @return the cveKitMaster
         */
        public String getCveKitMaster() {
            return cveKitMaster;
        }

        /**
         * @param cveKitMaster the cveKitMaster to set
         */
        public void setCveKitMaster(String cveKitMaster) {
            this.cveKitMaster = cveKitMaster;
        }

        /**
         * @return the nombreEs
         */
        public String getNombreEs() {
            return nombreEs;
        }

        /**
         * @param nombreEs the nombreEs to set
         */
        public void setNombreEs(String nombreEs) {
            this.nombreEs = nombreEs;
        }

        /**
         * @return the nombreEn
         */
        public String getNombreEn() {
            return nombreEn;
        }

        /**
         * @param nombreEn the nombreEn to set
         */
        public void setNombreEn(String nombreEn) {
            this.nombreEn = nombreEn;
        }

        /**
         * @return the contenedor
         */
        public String getContenedor() {
            return contenedor;
        }

        /**
         * @param contenedor the contenedor to set
         */
        public void setContenedor(String contenedor) {
            this.contenedor = contenedor;
        }

        /**
         * @return the idUnidadMedida
         */
        public int getIdUnidadMedida() {
            return idUnidadMedida;
        }

        /**
         * @param idUnidadMedida the idUnidadMedida to set
         */
        public void setIdUnidadMedida(int idUnidadMedida) {
            this.idUnidadMedida = idUnidadMedida;
        }

        /**
         * @return the idInstruccionesNacionales
         */
        public int getIdInstruccionesNacionales() {
            return idInstruccionesNacionales;
        }

        /**
         * @param idInstruccionesNacionales the idInstruccionesNacionales to set
         */
        public void setIdInstruccionesNacionales(int idInstruccionesNacionales) {
            this.idInstruccionesNacionales = idInstruccionesNacionales;
        }

        /**
         * @return the idInstruccionesInternac
         */
        public int getIdInstruccionesInternac() {
            return idInstruccionesInternac;
        }

        /**
         * @param idInstruccionesInternac the idInstruccionesInternac to set
         */
        public void setIdInstruccionesInternac(int idInstruccionesInternac) {
            this.idInstruccionesInternac = idInstruccionesInternac;
        }

        /**
         * @return the urlMultimedia
         */
        public String getUrlMultimedia() {
            return urlMultimedia;
        }

        /**
         * @param urlMultimedia the urlMultimedia to set
         */
        public void setUrlMultimedia(String urlMultimedia) {
            this.urlMultimedia = urlMultimedia;
        }

        /**
         * @return the mimeType
         */
        public String getMimeType() {
            return mimeType;
        }

        /**
         * @param mimeType the mimeType to set
         */
        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        /**
         * @return the estatus
         */
        public boolean isEstatus() {
            return estatus;
        }

        /**
         * @param estatus the estatus to set
         */
        public void setEstatus(boolean estatus) {
            this.estatus = estatus;
        }

        /**
         * @return the m_descripcionEs
         */
        public String getM_descripcionEs() {
            return m_descripcionEs;
        }

        /**
         * @param m_descripcionEs the m_descripcionEs to set
         */
        public void setM_descripcionEs(String m_descripcionEs) {
            this.m_descripcionEs = m_descripcionEs;
        }

        /**
         * @return the m_descripcionEn
         */
        public String getM_descripcionEn() {
            return m_descripcionEn;
        }

        /**
         * @param m_descripcionEn the m_descripcionEn to set
         */
        public void setM_descripcionEn(String m_descripcionEn) {
            this.m_descripcionEn = m_descripcionEn;
        }

        /**
         * @return the m_peso
         */
        public double getM_peso() {
            return m_peso;
        }

        /**
         * @param m_peso the m_peso to set
         */
        public void setM_peso(double m_peso) {
            this.m_peso = m_peso;
        }

        /**
         * @return the mkm_numeroParte
         */
        public String getMkm_numeroParte() {
            return mkm_numeroParte;
        }

        /**
         * @param mkm_numeroParte the mkm_numeroParte to set
         */
        public void setMkm_numeroParte(String mkm_numeroParte) {
            this.mkm_numeroParte = mkm_numeroParte;
        }

        /**
         * @return the mkm_cantidad
         */
        public int getMkm_cantidad() {
            return mkm_cantidad;
        }

        /**
         * @param mkm_cantidad the mkm_cantidad to set
         */
        public void setMkm_cantidad(int mkm_cantidad) {
            this.mkm_cantidad = mkm_cantidad;
        }

        /**
         * @return the mkm_reciclable
         */
        public boolean isMkm_reciclable() {
            return mkm_reciclable;
        }

        /**
         * @param mkm_reciclable the mkm_reciclable to set
         */
        public void setMkm_reciclable(boolean mkm_reciclable) {
            this.mkm_reciclable = mkm_reciclable;
        }

        /**
         * @return the mkm_observaciones
         */
        public String getMkm_observaciones() {
            return mkm_observaciones;
        }

        /**
         * @param mkm_observaciones the mkm_observaciones to set
         */
        public void setMkm_observaciones(String mkm_observaciones) {
            this.mkm_observaciones = mkm_observaciones;
        }

        public int getMkm_status_record() {
            return mkm_status_record;
        }

        public void setMkm_status_record(int mkm_status_record) {
            this.mkm_status_record = mkm_status_record;
        }
        
        public boolean isMKMSR_INITAL(){
            return this.mkm_status_record == ESTATUS_RECORD_MATERIAL_INITAL;
        }
        
        public boolean isMKMSR_NEW(){
            return this.mkm_status_record == ESTATUS_RECORD_MATERIAL_NEW;
        }
        
        public boolean isMKMSR_EDITING(){
            return this.mkm_status_record == ESTATUS_RECORD_MATERIAL_EDITING;
        }

        /**
         * @return the mkm_idUnidadMedida
         */
        public int getMkm_idUnidadMedida() {
            return mkm_idUnidadMedida;
        }

        /**
         * @param mkm_idUnidadMedida the mkm_idUnidadMedida to set
         */
        public void setMkm_idUnidadMedida(int mkm_idUnidadMedida) {
            this.mkm_idUnidadMedida = mkm_idUnidadMedida;
        }

        /**
         * @return the tipoKit
         */
        public int getTipoKit() {
            return tipoKit;
        }

        /**
         * @param tipoKit the tipoKit to set
         */
        public void setTipoKit(int tipoKit) {
            this.tipoKit = tipoKit;
        }
    }
    
}
