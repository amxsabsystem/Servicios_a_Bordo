package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.remote.KitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialKitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.MaterialKitMaster;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alfredo Estrada
 */
@Named(value = "masterMB")
@SessionScoped
public class MasterMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(MasterMB.class.getName());

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/kitMaster_RSB")
    private KitMasterFacadeRemote kitMasterFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/materialKitMaster_RSB")
    private MaterialKitMasterFacadeRemote servicioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    private MaterialFacadeRemote materialFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    private ParametroFacadeRemote ParametroFacadeRemote;

    private FacesContext context;
    
    
    List<KitMaster> all_records;
    KitMaster row_selected_kit = null;
    private String cveKitMasterSelected;
    HashMap<String, List<MaterialKitMaster>> all_records_material_kit;
    List<MaterialKitMaster> records_material_kit;
    
    boolean modificarRegistroKit = false;
    
    boolean modificarRegistroAGr = false;
    
    String lastModifyEnvent=null;
    
    public static final int TIPOKIT_AGRANEL = 59;
    public static final int TIPOKIT_LOTE = 60;

    //Lazy-Inject
    MasterKitMB masterKitMB;
    
    //Lazy-Inject
    LocaleInfo localeInfo; 
    
    public MasterMB() {
        logger.info("--->>> MasterMB(): ");
    }

    @PostConstruct
    public void init() {
        logger.info("--->>> init");        
    }

    public List<KitMaster> findAll() {
        logger.info("--->>>findAll(): all_records is null?="+(all_records == null));
        if (all_records == null) {
            try {
                all_records = kitMasterFacadeRemote.findAll();
                all_records_material_kit = new HashMap<String, List<MaterialKitMaster>>();
                List<MaterialKitMaster> allMMList = servicioFacadeRemote.findAll();
                for (KitMaster km : all_records) {
                    for (MaterialKitMaster mat : allMMList) {
                        if (mat.getKitMaster().getCveKitMaster().equals(km.getCveKitMaster())) {
                            if (!all_records_material_kit.containsKey(km.getCveKitMaster())) {
                                all_records_material_kit.put(km.getCveKitMaster(), new ArrayList<MaterialKitMaster>());
                            }
                            all_records_material_kit.get(km.getCveKitMaster()).add(mat);
                        }
                    }
                }
                logger.info("--->>>findAll(): ok, all list loaded !");
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
                    .getExternalContext().redirect("./masterAGranelDetalle.xhtml");
        } catch (IOException e) {
        }
    }
    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected_kit
     * @return
     */
    
    public String modifyRow(KitMaster row_selected_kit) {
        String redirect = "/portal/home";
        logger.info("--->>>modifyRow: CveKitMaster="+row_selected_kit.getCveKitMaster());
        this.row_selected_kit = row_selected_kit;
        this.cveKitMasterSelected = this.row_selected_kit.getCveKitMaster();
        
        final Parametro idTipoKit = this.row_selected_kit.getIdTipoKit();
        if (idTipoKit.getIdParametro() == TIPOKIT_AGRANEL) {
            redirect = "/portal/sab/operaciones/masterAGranelDetalle?faces-redirect=true";
            modificarRegistroKit = false;
            modificarRegistroAGr = true;
            lastModifyEnvent="redirect masterAGranelDetalle,CveKitMaster="+row_selected_kit.getCveKitMaster();
        } else if (idTipoKit.getIdParametro() == TIPOKIT_LOTE) {
            redirect = "/portal/sab/operaciones/masterKitDetalle?faces-redirect=true";
            modificarRegistroKit = true;
            modificarRegistroAGr = false;
            lastModifyEnvent="redirect masterKitDetalle,CveKitMaster="+row_selected_kit.getCveKitMaster();
            getMasterKitMB().editMasterKit(cveKitMasterSelected);
        }

        return redirect;
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
            logger.info("--->>> getLocaleInfo(): localeInfo="+localeInfo);
        }               
        return localeInfo;
    }

    public String getCveKitMasterSelected() {
        return cveKitMasterSelected;
    }

    public void setCveKitMasterSelected(String cveKitMasterSelected) {
        this.cveKitMasterSelected = cveKitMasterSelected;
    }

    public MasterKitMB getMasterKitMB() {
        if(masterKitMB == null){
            masterKitMB = (MasterKitMB) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{masterKitMB}", MasterKitMB.class)
                .getValue(FacesContext.getCurrentInstance().getELContext());
            logger.info("--->>> getMasterKitMB(): masterKitMB="+masterKitMB);
        }
        return masterKitMB;
    }    
}
