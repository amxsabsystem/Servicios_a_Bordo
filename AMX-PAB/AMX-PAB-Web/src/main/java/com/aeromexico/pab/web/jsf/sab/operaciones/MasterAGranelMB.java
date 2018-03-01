package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.web.dto.MasterMaterial;
import com.aeromexico.pab.backend.remote.KitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialKitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialKitMasterPKFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.MaterialKitMaster;
import com.aeromexico.pab.entity.MaterialKitMasterPK;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alfredo Estrada
 */
@Named(value = "masterAGranelMB")
@SessionScoped
public class MasterAGranelMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(MasterAGranelMB.class.getName());

    /**
     * @return the assignedContenedor
     */
    public String getAssignedContenedor() {
        return assignedContenedor;
    }

    /**
     * @param assignedContenedor the assignedContenedor to set
     */
    public void setAssignedContenedor(String assignedContenedor) {
        this.assignedContenedor = assignedContenedor;
    }

    public MasterAGranelMB() {
    }
    private List<KitMaster> all_records;
    private KitMaster row_selected_kit = null;
    private MaterialKitMaster row_selected = null;
    boolean modificarRegistro = false;
    boolean showPreview = false;
    private HashMap<String, List<MaterialKitMaster>> all_records_material_kit;
    private List<MaterialKitMaster> records_material_kit;
    private String asignedMaterial;
    private List<SelectItem> selectMateriales;
    private String material_Desc_Es;
    private String material_Desc_En;
    private String asignedUnidadDeMedida;

    private String cantidad;
    private String peso;
    private Integer asignedUnidad;
    private List<SelectItem> selectUnidades;
    private boolean reciclable;
    private String assignedContenedor;
    private Integer asignedEstNacional;
    private List<SelectItem> asignedEstNacionales;
    private Integer asignedEstInternac;
    private List<SelectItem> asignedEstInternacionales;

    private String observaciones;

    private List<MasterMaterial> all_records_previewAgranel;

    private StreamedContent filedownload;

    private InputStream inputStreamFile;
    private String fileName;
    public static final int TIPOKIT_AGRANEL = 59;
    public static final int TIPOKIT_LOTE = 60;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/materialKitMaster_RSB")
    MaterialKitMasterFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/materialMasterPK_RSB")
    MaterialKitMasterPKFacadeRemote materialMasterPKFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/kitMaster_RSB")
    KitMasterFacadeRemote kitMasterFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    MaterialFacadeRemote materialFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    public List<KitMaster> findAll() {
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
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
        return all_records;
    }

    public List<MaterialKitMaster> findAllKit(Object idKitMaster) {
        return all_records_material_kit.get(idKitMaster.toString());
    }

    @PostConstruct
    public void init() {
        all_records_previewAgranel = new ArrayList<MasterMaterial>();
        asignedUnidad = -1;
        asignedUnidadDeMedida = "-1";
        asignedEstNacional = -1;
        asignedEstInternac = -1;
        asignedMaterial = "-1";

        selectMateriales = new ArrayList<SelectItem>();
        selectMateriales.add(localeInfo.createSelectStringKeyFirstItem());

        for (Material regs : materialFacadeRemote.findAll()) {
            selectMateriales.add(new SelectItem(regs.getNumeroParte(), regs.getNumeroParte() + " - "
                    + (localeInfo.getLanguage().equals("es") ? regs.getDescripcionEs() : regs.getDescripcionEn())
            ));
        }

        final List<Parametro> allParametros = ParametroFacadeRemote.findAll();
        selectUnidades = new ArrayList<SelectItem>();
        selectUnidades.add(localeInfo.createSelectIntKeyFirstItem());

        for (Parametro regs : allParametros) {
            logger.debug("claves:" + regs.getClave());

            if (regs.getClave().equals("um")) {
                selectUnidades.add(new SelectItem(regs.getIdParametro(),
                        (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                ));
            }
        }

        asignedEstInternacionales = new ArrayList<SelectItem>();
        asignedEstInternacionales.add(localeInfo.createSelectIntKeyFirstItem());

        for (Parametro regs : allParametros) {
            if (regs.getClave().equals("insEstIn")) {
                asignedEstInternacionales.add(new SelectItem(regs.getIdParametro(),
                        (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                ));
            }
        }

        asignedEstNacionales = new ArrayList<SelectItem>();
        asignedEstNacionales.add(localeInfo.createSelectIntKeyFirstItem());

        for (Parametro regs : allParametros) {
            if (regs.getClave().equals("insEstNac")) {
                asignedEstNacionales.add(new SelectItem(regs.getIdParametro(),
                        (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
                ));
            }
        }

    }

    public void updateLabels() {
        asignedUnidad = this.row_selected_kit.getIdUnidadMedida().getIdParametro();
        asignedEstNacional = this.row_selected_kit.getIdInstruccionesNacionales().getIdParametro();
        asignedEstInternac = this.row_selected_kit.getIdInstruccionesInternac().getIdParametro();
        assignedContenedor = this.row_selected_kit.getContenedor();
        if (localeInfo.getLanguage().equals("es")) {
            asignedUnidadDeMedida = this.row_selected_kit.getIdUnidadMedida().getValorEs();
        } else {
            asignedUnidadDeMedida = this.row_selected_kit.getIdUnidadMedida().getValorEn();
        }
        if (this.row_selected != null) {
            cantidad = String.valueOf(this.row_selected.getCantidad());
            observaciones = this.row_selected.getObservaciones();
            reciclable = this.row_selected.getReciclable() == 1;
        }
    }

    public void checkboxChangedMasterKit() {
        try {

            modificarRegistro = false;
            row_selected = new MaterialKitMaster();
            row_selected_kit = new KitMaster();
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("./masterKitDetalle.xhtml");
        } catch (IOException e) {
        }
    }

    public void checkboxChangedMasterAGranel() {
        try {
            all_records_previewAgranel = new ArrayList<MasterMaterial>();
            modificarRegistro = false;
            row_selected = new MaterialKitMaster();
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("./masterAGranelDetalle.xhtml");
        } catch (IOException e) {
        }

    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/sab/operaciones/masterSelect?faces-redirect=true";
        findAll();
        return redirect;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            KitMaster kitMasterToAssign = kitMasterFacadeRemote.findByPK(1);

            MaterialKitMasterPK pk = new MaterialKitMasterPK();
            pk.setCveKitMaster(kitMasterToAssign.getCveKitMaster());
            pk.setNumeroParte(asignedMaterial);
            // MaterialKitMasterPK pkReturned = materialMasterPKFacadeRemote.create(pk);
            row_selected.setStatus((short) 1);
            row_selected.setMaterialKitMasterPK(pk);
            row_selected.setIdUnidadMedida(ParametroFacadeRemote.findByPK(asignedUnidad));
            row_selected.setIdInstruccionesInternac(ParametroFacadeRemote.findByPK(asignedEstInternac));
            row_selected.setIdInstruccionesNacionales(ParametroFacadeRemote.findByPK(asignedEstNacional));
            row_selected.setCantidad(Integer.parseInt(cantidad));
            //row_selected.setPeso(Float.parseFloat(peso));
            row_selected.setReciclable(reciclable ? (short) 1 : (short) 0);
            row_selected.setObservaciones(observaciones);
            servicioFacadeRemote.create(row_selected);
            addRow();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void addMaterial() {

        MasterMaterial master = new MasterMaterial();

        master.setNumeroParte(asignedMaterial);

        master.setDescripcionEn(material_Desc_En);
        master.setDescripcionEs(material_Desc_Es);

        master.setUm(asignedUnidad + "");
        master.setEstacionesInter(asignedEstInternac + "");
        master.setEstacionesNac(asignedEstNacional + "");
        master.setCantidad(cantidad != null ? Integer.parseInt(cantidad) : null);
        master.setPeso(peso != null ? Float.parseFloat(peso) : null);
        master.setIsReciclable(reciclable);
        master.setObservaciones(observaciones);

        all_records_previewAgranel.add(master);

        asignedMaterial = null;
        material_Desc_En = null;
        material_Desc_Es = null;

        cantidad = null;
        peso = null;
        reciclable = false;
        observaciones = null;

    }

    /**
     * Save a new record
     */
    public void saveKit() {
        try {
            row_selected_kit.setStatus((short) 1);
            if (inputStreamFile != null) {
                //row_selected_kit.setMimeType("application/pdf");
                row_selected_kit.setUrlMultimedia(fileName);
            }

            row_selected_kit.setIdUnidadMedida(ParametroFacadeRemote.findByPK(asignedUnidad));
            row_selected_kit.setIdInstruccionesInternac(ParametroFacadeRemote.findByPK(asignedEstInternac));
            row_selected_kit.setIdInstruccionesNacionales(ParametroFacadeRemote.findByPK(asignedEstNacional));
            row_selected_kit.setIdTipoKit(ParametroFacadeRemote.findByPK(asignedEstNacional));
            KitMaster kitMasterToAssign = kitMasterFacadeRemote.create(row_selected_kit);

            for (MasterMaterial material : all_records_previewAgranel) {

                MaterialKitMasterPK pk = new MaterialKitMasterPK();
                pk.setCveKitMaster(kitMasterToAssign.getCveKitMaster());
                pk.setNumeroParte(material.getNumeroParte());

                row_selected.setStatus((short) 1);
                row_selected.setMaterialKitMasterPK(pk);
                row_selected.setIdUnidadMedida(ParametroFacadeRemote.findByPK(Integer.parseInt(material.getUm())));
                row_selected.setIdInstruccionesInternac(ParametroFacadeRemote.findByPK(Integer.parseInt(material.getEstacionesInter())));
                row_selected.setIdInstruccionesNacionales(ParametroFacadeRemote.findByPK(Integer.parseInt(material.getEstacionesNac())));
                row_selected.setCantidad(material.getCantidad());
                //row_selected.setPeso(Float.parseFloat(material.getPeso()+""));
                row_selected.setReciclable(material.isIsReciclable() ? (short) 1 : (short) 0);
                row_selected.setObservaciones(material.getObservaciones());
                servicioFacadeRemote.create(row_selected);

            }

            addRow();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRow() {
        String redirect = "/portal/sab/operaciones/masterDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new MaterialKitMaster();
        return redirect;
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected_kit
     * @return
     */
    public String modifyRow(KitMaster row_selected_kit) {
        String redirect = "/portal/home";
        modificarRegistro = true;

        this.row_selected_kit = row_selected_kit;
        final Parametro idTipoKit = this.row_selected_kit.getIdTipoKit();
        records_material_kit = findAllKit(this.row_selected_kit.getCveKitMaster());

        
        if (idTipoKit.getIdParametro() == TIPOKIT_AGRANEL) {
            redirect = "/portal/sab/operaciones/masterAGranelDetalle?faces-redirect=true";
            asignedMaterial = this.row_selected_kit.getCveKitMaster();

            for (MaterialKitMaster mkm : records_material_kit) {
                row_selected = mkm;
            }
            Material mat = materialFacadeRemote.findByPK(asignedMaterial);
            material_Desc_Es = mat.getDescripcionEs();
            material_Desc_En = mat.getDescripcionEn();
            peso = String.valueOf(mat.getPeso());
            updateLabels();
        } else if (idTipoKit.getIdParametro() == TIPOKIT_LOTE) {
            redirect = "/portal/sab/operaciones/masterKitDetalle?faces-redirect=true";
            for(MaterialKitMaster mkm:records_material_kit){
                Material m=materialFacadeRemote.findByPK_EAGER(mkm.getMaterialKitMasterPK().getNumeroParte());
                mkm.setMaterial(m);
            }
            updateLabels();
        }

        return redirect;
    }

    public void preview() {
        showPreview = true;
        /*
        MasterMaterial master = new MasterMaterial();

        master.setNumeroParte(asignedMaterial);

        master.setDescripcionEn(material_Desc_En);
        master.setDescripcionEs(material_Desc_Es);

        master.setUm(asignedUnidad != null ? (localeInfo.getLanguage().equals("es") ? ParametroFacadeRemote.findByPK(asignedUnidad).getValorEs() : ParametroFacadeRemote.findByPK(asignedUnidad).getValorEn()) : null);
        master.setEstacionesInter(asignedEstInternac != null ? (localeInfo.getLanguage().equals("es") ? ParametroFacadeRemote.findByPK(asignedEstInternac).getValorEs() : ParametroFacadeRemote.findByPK(asignedEstInternac).getValorEn()) : null
        );
        master.setEstacionesNac(
                asignedEstNacional != null ? (localeInfo.getLanguage().equals("es") ? ParametroFacadeRemote.findByPK(asignedEstNacional).getValorEs() : ParametroFacadeRemote.findByPK(asignedEstNacional).getValorEn()) : null
        );
        master.setCantidad(cantidad != null ? Integer.parseInt(cantidad) : null);
        master.setPeso(peso != null ? Float.parseFloat(peso) : null);

        all_records_previewAgranel.clear();
        all_records_previewAgranel.add(master);
        */
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            //row_selected.setStatus((short)1);
            //ParametroFacadeRemote.update(row_selected);
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Update selected record
     */
    public void updateKit() {
        try {
            //row_selected.setStatus((short)1);
            //ParametroFacadeRemote.update(row_selected);
            findAll();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
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

    public List<MaterialKitMaster> getRecords_material_kit() {
        return records_material_kit;
    }
    
    public MaterialKitMaster getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(MaterialKitMaster row_selected) {
        this.row_selected = row_selected;
    }

    public List<KitMaster> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<KitMaster> all_records) {
        this.all_records = all_records;
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

    public void setSelectMateriales(List<SelectItem> selectMateriales) {
        this.selectMateriales = selectMateriales;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public String getMaterial_Desc_Es() {
        return material_Desc_Es;
    }

    public void setMaterial_Desc_Es(String material_Desc_Es) {
        this.material_Desc_Es = material_Desc_Es;
    }

    public String getMaterial_Desc_En() {
        return material_Desc_En;
    }

    public void setMaterial_Desc_En(String material_Desc_En) {
        this.material_Desc_En = material_Desc_En;
    }

    public String getAsignedUnidadDeMedida() {
        return asignedUnidadDeMedida;
    }

    public void setAsignedUnidadDeMedida(String asignedUnidadDeMedida) {
        this.asignedUnidadDeMedida = asignedUnidadDeMedida;
    }

    public Integer getAsignedEstNacional() {
        return asignedEstNacional;
    }

    public void setAsignedEstNacional(Integer asignedEstNacional) {
        this.asignedEstNacional = asignedEstNacional;
    }

    public List<SelectItem> getAsignedEstNacionales() {
        return asignedEstNacionales;
    }

    public void setAsignedEstNacionales(List<SelectItem> asignedEstNacionales) {
        this.asignedEstNacionales = asignedEstNacionales;
    }

    public Integer getAsignedEstInternac() {
        return asignedEstInternac;
    }

    public void setAsignedEstInternac(Integer asignedEstInternac) {
        this.asignedEstInternac = asignedEstInternac;
    }

    public List<SelectItem> getAsignedEstInternacionales() {
        return asignedEstInternacionales;
    }

    public void setAsignedEstInternacionales(List<SelectItem> asignedEstInternacionales) {
        this.asignedEstInternacionales = asignedEstInternacionales;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Integer getAsignedUnidad() {
        return asignedUnidad;
    }

    public void setAsignedUnidad(Integer asignedUnidad) {
        this.asignedUnidad = asignedUnidad;
    }

    public List<SelectItem> getSelectUnidades() {
        return selectUnidades;
    }

    public void setSelectUnidades(List<SelectItem> selectUnidades) {
        this.selectUnidades = selectUnidades;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<MasterMaterial> getAll_records_previewAgranel() {
        return all_records_previewAgranel;
    }

    public void setAll_records_previewAgranel(List<MasterMaterial> all_records_previewAgranel) {
        this.all_records_previewAgranel = all_records_previewAgranel;
    }

    public boolean isShowPreview() {
        return showPreview;
    }

    public void setShowPreview(boolean showPreview) {
        this.showPreview = showPreview;
    }

    public KitMaster getRow_selected_kit() {
        return row_selected_kit;
    }

    public void setRow_selected_kit(KitMaster row_selected_kit) {
        this.row_selected_kit = row_selected_kit;
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

    public boolean isReciclable() {
        return reciclable;
    }

    public void setReciclable(boolean reciclable) {
        this.reciclable = reciclable;
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
