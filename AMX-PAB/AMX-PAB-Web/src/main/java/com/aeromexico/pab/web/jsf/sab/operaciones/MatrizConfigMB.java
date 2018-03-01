package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.KitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialKitMasterFacadeRemote;
import com.aeromexico.pab.backend.remote.MatrizDetalleFacadeRemote;
import com.aeromexico.pab.backend.remote.MatrizFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProductoFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoEquipoAvionFacadeRemote;
import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.MaterialKitMaster;
import com.aeromexico.pab.entity.Matriz;
import com.aeromexico.pab.entity.MatrizDetalle;
import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.entity.TipoEquipoAvion;
import com.aeromexico.pab.web.dto.MatrizDTO;
import com.aeromexico.pab.web.dto.MatrizSearchResult;
import com.aeromexico.pab.web.dto.ProductoDTO;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alfredo Estrada
 */
@Named(value = "matrizConfigMB")
@SessionScoped 
public class MatrizConfigMB  implements Serializable{
    private static Logger logger = LoggerFactory.getLogger(MatrizConfigMB.class.getName());

    private String                  kitMasterCve_selected;
    private String                  kitMasterCve_view;
    private Integer                 idProducto_selected;
    private Integer                 idTipoEquipoAvion_selected;
    private Integer                 cj;
    private Integer                 cy;

    private List<String>            kitMasterCveList;
    private List<Producto>          productosList;
    private List<TipoEquipoAvion>   tipoEquipoAvionList;
    private List<ProductoDTO>       productoDTOList;
    
    private ArrayList<SelectItem>   selectKitMasterCve;
    private ArrayList<SelectItem>   selectProducto;
    private ArrayList<SelectItem>   selectTipoEquipoAvion;    
    
    private Matriz                  matrizSelected;
    //--------------------------------------------------------------------------
    private List<Object[]>          resultMD_P_TEA;    
    private ArrayList<SelectItem>   selectKitMasterCveView;
    private ArrayList<SelectItem>   selectProductoView;
    private ArrayList<SelectItem>   selectTipoEquipoAvionView;
    private Integer                 idProducto_view;
    private Integer                 idTipoEquipoAvion_view;    

    private List<MatrizDTO>         matrizDTOList;    
    private List<MatrizDetalle>     matrizDetalleSearchList;
    private List<MatrizSearchResult> matrizSearchResultList;
    
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

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/producto_RSB")
    ProductoFacadeRemote productoFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoEquipoAvion_RSB")
    TipoEquipoAvionFacadeRemote tipoEquipoAvionFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/matriz_RSB")
    MatrizFacadeRemote matrizFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/matrizDetalle_RSB")
    MatrizDetalleFacadeRemote matrizDetalleFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    
    public MatrizConfigMB() {
        logger.info("-->>MatrizConfigMB():");
        matrizSelected = null;
        idProducto_view = -1;
        idTipoEquipoAvion_view =-1;    
        kitMasterCve_view = "-1";
    }
    
    @PostConstruct
    public void init(){
        logger.info("-->>init():");
        resetForm();
    }

    private void resetForm() {
        logger.info("--->>>reset");
        resultMD_P_TEA              = null;
        selectProductoView          = null;
        selectTipoEquipoAvionView   = null;
        matrizDetalleSearchList     = null;
        matrizSearchResultList      = null;
        if(matrizSelected == null){
            kitMasterCve_selected = "-1";
            idProducto_selected =-1;
            idTipoEquipoAvion_selected=-1;
            cj=0;
            cy=0;
        } else {
            kitMasterCve_selected      = matrizSelected.getKitMaster().getCveKitMaster();
            idProducto_selected        = matrizSelected.getProducto().getIdProducto();
            idTipoEquipoAvion_selected = matrizSelected.getTipoEquipoAvion().getIdTipoEquipoAvion();
            cj                         = matrizSelected.getCantidadCj();
            cy                         = matrizSelected.getCantidadCy();
        }
    }

    public List<String> getKitMasterCveList() {
        if(kitMasterCveList == null){
            List listResult = genericSearchFacadeRemote.loadQuery("SELECT k.cveKitMaster FROM KitMaster k");
            kitMasterCveList = (List<String>)listResult;            
        }
        return kitMasterCveList;
    }

    public String getKitMasterCve_selected() {
        return kitMasterCve_selected;
    }

    public void setKitMasterCve_selected(String kitMasterCve_selected) {
        this.kitMasterCve_selected = kitMasterCve_selected;
    }
    
    public String getKitMasterCve_view() {
        return kitMasterCve_view;
    }

    public void setKitMasterCve_view(String kitMasterCve_view) {
        this.kitMasterCve_view = kitMasterCve_view;
    }
    
    public void kitMasterCveViewChanged(){
        logger.info("--->>> kitMasterCveViewChanged: kitMasterCve_view="+kitMasterCve_view);
        resultMD_P_TEA              = null;
        selectProductoView          = null;
        selectTipoEquipoAvionView   = null;
        idProducto_view             = -1;
        idTipoEquipoAvion_view      = -1;    
    }
    
    public void kitMasterCveChanged(){
        logger.info("--->>> kitMasterCveChanged: kitMasterCve_selected="+kitMasterCve_selected);
        matrizDTOList = null;
    }
    
    public void idProductoChanged(){
        logger.info("--->>> idProductoChanged: idProducto_selected="+idProducto_selected);        
    }
    
    public void idProducto_ViewChanged(){
        logger.info("--->>> idProducto_ViewChanged: idProducto_view="+idProducto_view);        
    }
    
    public void tipoEquipoAvionChanged(){
        logger.info("--->>> tipoEquipoAvionChanged: idTipoEquipoAvion_selected="+idTipoEquipoAvion_selected);
    }
    
    public void tipoEquipoAvion_ViewChanged(){
        logger.info("--->>> tipoEquipoAvion_ViewChanged: idTipoEquipoAvion_view="+idTipoEquipoAvion_view);
    }
    
    public List<ProductoDTO> getProductoDTOList() {
        if(productoDTOList == null){
            List<Object[]> result = genericSearchFacadeRemote.loadQuery("SELECT p.idProducto,p.nombre,p.status FROM Producto p order by p.nombre");
            productoDTOList = new ArrayList<ProductoDTO>();
            for(Object[] oa: result){
                productoDTOList.add(new ProductoDTO((Integer)oa[0],(String)oa[1], (Short)oa[2]));
            }
        }
        return productoDTOList;
    }

    public Integer getIdTipoEquipoAvion_selected() {
        return idTipoEquipoAvion_selected;
    }

    public void setIdTipoEquipoAvion_selected(Integer idTipoEquipoAvion_selected) {
        this.idTipoEquipoAvion_selected = idTipoEquipoAvion_selected;
    }

    public Integer getIdProducto_selected() {
        return idProducto_selected;
    }

    public void setIdProducto_selected(Integer idProducto_selected) {
        this.idProducto_selected = idProducto_selected;
    }

    public Integer getCj() {
        return cj;
    }

    public void setCj(Integer cj) {
        this.cj = cj;
    }

    public Integer getCy() {
        return cy;
    }

    public void setCy(Integer cy) {
        this.cy = cy;
    }

    public List<MatrizDTO> getMatrizDTOList() {
        if(matrizDTOList == null){
            logger.info("-->> getMatrizDTOList:");
            if(    1==1
                && kitMasterCve_selected       != null && !kitMasterCve_selected.equals("-1") 
            //    && idProducto_selected         != -1 
            //    && idTipoEquipoAvion_selected  !=-1
                    ){

                matrizDTOList = new ArrayList<MatrizDTO>();
                logger.info("-->> getMatrizDTOList: execute query");
                List<Object[]> result = genericSearchFacadeRemote.loadQuery(
                        "SELECT m.idMatriz,m.producto.idProducto,m.producto.nombre,m.tipoEquipoAvion.idTipoEquipoAvion,m.tipoEquipoAvion.tipoEquipo,m.cantidadCj,m.cantidadCy "+
                        " FROM  Matriz m "+
                        " WHERE 1=1 "+
                        "   and m.kitMaster = '"+kitMasterCve_selected+"'"+
              //          "   and m.producto.idProducto="+idProducto_selected+
              //          "   and m.tipoEquipoAvion="+idTipoEquipoAvion_selected
                        "");
                for(Object[] rmo:result){
                    MatrizDTO rm=new MatrizDTO();

                    rm.setIdMatriz                          ((Integer)rmo[0]);
                    rm.setProducto_idProducto               ((Integer)rmo[1]);
                    rm.setProducto_nombre                   ((String )rmo[2]);
                    rm.setTipoEquipoAvion_idTipoEquipoAvion ((Integer)rmo[3]);
                    rm.setTipoEquipoAvion_tipoEquipo        ((String )rmo[4]);
                    rm.setCantidadCj                        ((Integer)rmo[5]);
                    rm.setCantidadCy                        ((Integer)rmo[6]);

                    matrizDTOList.add(rm);
                }
                logger.info("-->> getMatrizDTOList: end query");
            }
        }
        return matrizDTOList;
    }
    
    public List<SelectItem> getSelectTipoEquipo() {
        if(selectTipoEquipoAvion == null){
            logger.info("-->> getSelectTipoEquipo(): fill list !");
            selectTipoEquipoAvion = new ArrayList<SelectItem>();
            selectTipoEquipoAvion.add(localeInfo.createSelectIntKeyFirstItem());
            if(tipoEquipoAvionList == null){
                tipoEquipoAvionList = tipoEquipoAvionFacadeRemote.findAll();
            }
            for (TipoEquipoAvion tea : tipoEquipoAvionList) {
                selectTipoEquipoAvion.add(new SelectItem(tea.getIdTipoEquipoAvion(), tea.getTipoEquipo()));
            }
        }
        return selectTipoEquipoAvion;
    }

    public List<SelectItem> getSelectProducto() {
        if(selectProducto == null){
            logger.info("-->> getSelectProducto(): fill list !");
            selectProducto = new ArrayList<SelectItem>();
            selectProducto.add(localeInfo.createSelectIntKeyFirstItem());
            if(productosList == null){
                productosList = productoFacadeRemote.findAll();
            }
            for (Producto p : productosList) {
                selectProducto.add(new SelectItem(p.getIdProducto(), p.getNombre()));
            }
        }
        return selectProducto;
    }

    public ArrayList<SelectItem> getSelectKitMasterCve() {
        if(selectKitMasterCve == null){
            selectKitMasterCve = new ArrayList<SelectItem>();
            selectKitMasterCve.add(localeInfo.createSelectStringKeyFirstItem());
            List listResult = genericSearchFacadeRemote.loadQuery("SELECT k.cveKitMaster FROM KitMaster k");
            
            for (Object km : listResult) {
                String kitMasterCve=(String)km;
                selectKitMasterCve.add(new SelectItem(kitMasterCve, kitMasterCve));
            }
        }

        return selectKitMasterCve;
    }
    
    public ArrayList<SelectItem> getSelectKitMasterCveView() {
        if(selectKitMasterCveView == null){
            selectKitMasterCveView = new ArrayList<SelectItem>();
            selectKitMasterCveView.add(new SelectItem("-1", localeInfo.getLocalized("SELECT_ALL_LBL")));
            List listResult = genericSearchFacadeRemote.loadQuery("SELECT k.cveKitMaster FROM KitMaster k");
            
            for (Object km : listResult) {
                String kitMasterCve=(String)km;
                selectKitMasterCveView.add(new SelectItem(kitMasterCve, kitMasterCve));
            }
        }

        return selectKitMasterCveView;
    }

    public void save(){
        logger.info("-->>save:");
        String redirectPage = "/portal/sab/operacions/matrizConfig";
        Matriz matriz = null;
        try{
            matriz = new Matriz();
            logger.info("-->> Setting header:");
            KitMaster km = null;
            //km = kitMasterFacadeRemote.findByPK(kitMasterCve_selected);
            km = new KitMaster();
            km.setCveKitMaster(kitMasterCve_selected);
            
            matriz.setIdMatriz(null);
            matriz.setKitMaster(km);
            matriz.setCantidadCj(cj);
            matriz.setCantidadCy(cy);
            
            final Producto producto = new Producto();//productoFacadeRemote.findByPK(idProducto_selected);
            producto.setIdProducto(idProducto_selected);
            matriz.setProducto(producto);
            
            final TipoEquipoAvion tipoEquipoAvion = new TipoEquipoAvion();//tipoEquipoAvionFacadeRemote.findByPK(idTipoEquipoAvion_selected);
            tipoEquipoAvion.setIdTipoEquipoAvion(idTipoEquipoAvion_selected);
            matriz.setTipoEquipoAvion(tipoEquipoAvion);
            
            matriz.setTotalCantidadCjCy(cj + cy);
            matriz.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
            logger.info("-->> Before create:");
            matriz = matrizFacadeRemote.create(matriz);            
            logger.info("-->> after save: matriz.IdMatriz="+matriz.getIdMatriz());
            
            List<Object[]> result = genericSearchFacadeRemote.loadQuery(
                        "SELECT m.material.numeroParte,m.cantidad "+
                        " FROM  MaterialKitMaster m  "+
                        " WHERE 1=1 "+
                        " and   m.materialKitMasterPK.cveKitMaster = '"+kitMasterCve_selected+"'"+
                        "");
            List<MaterialKitMaster> mkmList = new ArrayList<MaterialKitMaster>();
            List<MatrizDetalle>     mdList  = new ArrayList<MatrizDetalle>();
            logger.info("-->> Generating MatrizDetalle:");
            for(Object[] rmo:result) {
                String numeroParte = (String)rmo[0];
                Integer cantidad = (Integer)rmo[1];
                Material mat = new Material();
                mat.setNumeroParte(numeroParte);
                MatrizDetalle md=new MatrizDetalle();
                
                md.setMatriz(matriz);
                md.setMaterial(mat);
                md.setTotalCantidadCj( cantidad * cj);
                md.setTotalCantidadCy( cantidad * cy);
                md.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                logger.info("\t-->> SAVE MatrizDetalle:"+numeroParte+", ["+md.getTotalCantidadCj()+"]["+md.getTotalCantidadCy()+"]");
                matrizDetalleFacadeRemote.create(md);
                logger.info("<<--- AFTER SAVE ?");
                mdList.add(md);
            }

            messgeValidateInfo("form:saveButton", "Registro guardado correctamente ");
            
            matrizDTOList = null;
            logger.info("<<--- end SAVE, ...refreshing all data ?");
        } catch (Exception e) {
            logger.error("-->> Afetr save:", e);
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
        //return redirectPage;
    }

    public void update(){
        logger.info("-->>update:");
        String redirectPage = "/portal/sab/operacions/matrizConfig";
        Matriz matriz = matrizSelected;
        try{
            logger.info("-->> Setting header:");
            
            KitMaster km = null;
            //km = kitMasterFacadeRemote.findByPK(kitMasterCve_selected);
            km = new KitMaster();
            km.setCveKitMaster(kitMasterCve_selected);
                        
            matriz.setKitMaster(km);
            matriz.setCantidadCj(cj);
            matriz.setCantidadCy(cy);
            
            final Producto producto = new Producto();//productoFacadeRemote.findByPK(idProducto_selected);
            producto.setIdProducto(idProducto_selected);
            matriz.setProducto(producto);
            
            final TipoEquipoAvion tipoEquipoAvion = new TipoEquipoAvion();//tipoEquipoAvionFacadeRemote.findByPK(idTipoEquipoAvion_selected);
            tipoEquipoAvion.setIdTipoEquipoAvion(idTipoEquipoAvion_selected);
            matriz.setTipoEquipoAvion(tipoEquipoAvion);
            
            matriz.setTotalCantidadCjCy(cj + cy);
            matriz.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
            
            List<MatrizDetalle> matrizDetalleHasMatrizList = matriz.getMatrizDetalleHasMatrizList();
            logger.info("-->> Removing MatrizDetalle found:");
            for(MatrizDetalle md:matrizDetalleHasMatrizList){
                logger.info("\t-->> Removing MatrizDetalle:IdMatrizDetalle="+md.getIdMatrizDetalle());
                logger.info("\t-->> After Removing MatrizDetalle");
            }
            logger.info("-->> after removing MatrizDetalle:");
            matriz.setMatrizDetalleHasMatrizList(null);
            
            logger.info("------------>>> Before real UPDATE!");
            matriz = matrizFacadeRemote.update(matriz);
            logger.info("<<------------- After real UPDATE!");
            
            List<Object[]> result = genericSearchFacadeRemote.loadQuery(
                        "SELECT m.material.numeroParte,m.cantidad "+
                        " FROM  MaterialKitMaster m  "+
                        " WHERE 1=1 "+
                        " and   m.materialKitMasterPK.cveKitMaster = '"+kitMasterCve_selected+"'"+
                        "");
            List<MaterialKitMaster> mkmList = new ArrayList<MaterialKitMaster>();
            List<MatrizDetalle>     mdList  = new ArrayList<MatrizDetalle>();
            logger.info("-->> Generating MatrizDetalle:");
            for(Object[] rmo:result) {
                String numeroParte = (String)rmo[0];
                Integer cantidad = (Integer)rmo[1];
                Material mat = null;
                mat = materialFacadeRemote.findByPK(numeroParte);
//                mat = new Material();
//                mat.setNumeroParte(numeroParte);
                
                MatrizDetalle md=new MatrizDetalle();
                md.setMatriz(matriz);
                md.setMaterial(mat);
                md.setTotalCantidadCj( cantidad * cj);
                md.setTotalCantidadCy( cantidad * cy);
                md.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                logger.info("\t-->> SAVE FOR UPDATE MatrizDetalle:"+numeroParte+", ["+md.getTotalCantidadCj()+"]["+md.getTotalCantidadCy()+"]");
                matrizDetalleFacadeRemote.create(md);
                logger.info("<<--- AFTER SAVE FOR UPDATE ?");
                mdList.add(md);
            }
                        
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            
            matrizDTOList = null;
            logger.info("<<--- end UPDATE, ...refreshing all data ?");
        } catch (Exception e) {
            logger.error("-->> After update:", e);
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
        //return redirectPage;
    }
        
    public void cancel(){
        logger.info("-->>cancel:");
        matrizSelected = null;
        matrizDTOList = null;
        resultMD_P_TEA = null;
        selectProductoView = null;
        selectTipoEquipoAvionView = null;
        kitMasterCve_view = "-1";
        idProducto_view = -1;
        idTipoEquipoAvion_view = -1;    
        matrizDTOList = null;        
        matrizDetalleSearchList = null;
        resetForm();        
    }
    
    public void search(){
        logger.info("-->>search:");        
        
        List resultMD = genericSearchFacadeRemote.loadQuery(
                "SELECT md "+
                " FROM MatrizDetalle md "+
                " WHERE 1=1 "+
                "   and md.matriz.producto.idProducto="+idProducto_view+
                "   and md.matriz.tipoEquipoAvion.idTipoEquipoAvion="+idTipoEquipoAvion_view+
                " ORDER BY "+
                "       md.matriz.idMatriz,md.idMatrizDetalle");
        
        matrizDetalleSearchList = new ArrayList<MatrizDetalle>();
        matrizSearchResultList  = new ArrayList<MatrizSearchResult>();
        
        HashMap<Integer,Matriz> matrizMap=new HashMap<Integer,Matriz>();
        HashMap<Integer,List<MatrizDetalle>> matrizDetalleSearchMap=new HashMap<Integer,List<MatrizDetalle>>();
        
        
        for(Object orMD:resultMD){
            MatrizDetalle md=(MatrizDetalle)orMD;
            logger.info("\t-->>MatrizDetalle:{"+md.getMatriz().getIdMatriz()+","+md.getIdMatrizDetalle()+
                    ","+md.getMaterial().getNumeroParte()+","+md.getMatriz().getProducto().getNombre()+","+md.getMatriz().getTipoEquipoAvion().getTipoEquipo()+
                    ",["+md.getMatriz().getCantidadCy()+"]["+md.getMatriz().getCantidadCj()+"]>>["+md.getTotalCantidadCy()+"]["+md.getTotalCantidadCj()+"]");
            matrizDetalleSearchList.add(md);
            List<MatrizDetalle>     matrizDetalleOfThisMatrizList = null;
            matrizMap.put(md.getMatriz().getIdMatriz(), md.getMatriz());
            matrizDetalleOfThisMatrizList = matrizDetalleSearchMap.get(md.getMatriz().getIdMatriz());
            if(matrizDetalleOfThisMatrizList == null){
                matrizDetalleOfThisMatrizList = new ArrayList<MatrizDetalle>();
                matrizDetalleSearchMap.put(md.getMatriz().getIdMatriz(), matrizDetalleOfThisMatrizList);
                //matrizDetalleSearchMap.put(md.getMatriz().getIdMatriz(), matrizDetalleSearchList);
            }
            matrizDetalleOfThisMatrizList.add(md);            
        }
        
        for(Integer idMatriz:matrizMap.keySet()){
            Matriz m = matrizMap.get(idMatriz);
            List<MatrizDetalle> mdL=matrizDetalleSearchMap.get(idMatriz);
            MatrizSearchResult mrr=new MatrizSearchResult(m,mdL);            
            matrizSearchResultList.add(mrr);
        }
        logger.info("-->>matrizSearchResultList:{");
        for(MatrizSearchResult mrr:matrizSearchResultList){
            logger.info("-->>\tMatrizSearchResult:{");
            logger.info("-->>\t\tMatrizUniqueList{");
            for(Matriz m:mrr.getMatrizUniqueList()){
                logger.info("-->>\t\t\tMatriz:"+m.getIdMatriz());
            }
            logger.info("-->>\t\t}");
            logger.info("-->>\t}");
            logger.info("-->>\tMatrizDetalleList{");
            for(MatrizDetalle md:mrr.getMatrizDetalleList()){
                logger.info("-->>\t\t:MatrizDetalle:"+md.getIdMatrizDetalle());
            }
            logger.info("-->>\t}");
        }
        logger.info("-->>}");
        
    }

    public List<MatrizSearchResult> getMatrizSearchResultList() {
        return matrizSearchResultList;
    }
        
    public List<MatrizDetalle> getMatrizDetalleSearchList() {
        return matrizDetalleSearchList;
    }
    
    public boolean isRadyToEdit_CJ_CY(){
        return idProducto_selected != -1 && idTipoEquipoAvion_selected != -1;
    }
    
    public boolean isreadyToSearch(){
        return idProducto_view != -1 && idTipoEquipoAvion_view != -1;
    }


    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void modifyRowMatriz(){        
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        String idMatriz = params.get("idMatriz");
        logger.info("--->>>modifyRowMatriz: "+idMatriz);
        
        matrizSelected = matrizFacadeRemote.findByPK_EAGER(new Integer(idMatriz));
        logger.info("--->>>modifyRowMatriz: matrizSelected="+matrizSelected);
        resetForm();
        messgeValidateInfo("form:saveButton", "Registro encontrado correctamente");
    }
    
    public void deleteRowMatriz(){        
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        String idMatriz = params.get("idMatriz");
        logger.info("\t--->>>deleteRowMatriz: "+idMatriz);
    }

    public Matriz getMatrizSelected() {
        return matrizSelected;
    }

    /**
     * @return the idProducto_view
     */
    public Integer getIdProducto_view() {
        return idProducto_view;
    }

    /**
     * @param idProducto_view the idProducto_view to set
     */
    public void setIdProducto_view(Integer idProducto_view) {
        this.idProducto_view = idProducto_view;
    }

    /**
     * @return the idTipoEquipoAvion_view
     */
    public Integer getIdTipoEquipoAvion_view() {
        return idTipoEquipoAvion_view;
    }

    /**
     * @param idTipoEquipoAvion_view the idTipoEquipoAvion_view to set
     */
    public void setIdTipoEquipoAvion_view(Integer idTipoEquipoAvion_view) {
        this.idTipoEquipoAvion_view = idTipoEquipoAvion_view;
    }
    
    public List<Object[]> getResultMD_P_TEA() {
        if(resultMD_P_TEA ==null){
            if(kitMasterCve_view != null && kitMasterCve_view.equals("-1")){
                logger.info("-->getResultMD_P_TEA: ALL kitMasterCve_view="+kitMasterCve_view);
                resultMD_P_TEA = genericSearchFacadeRemote.loadQuery(
                        "SELECT md.matriz.producto.idProducto               , md.matriz.producto.nombre           ,"+
                        "       md.matriz.tipoEquipoAvion.idTipoEquipoAvion , md.matriz.tipoEquipoAvion.tipoEquipo"+
                        " FROM MatrizDetalle md "+
                        " WHERE 1=1 "+
                        " ORDER BY "+
                        "       md.matriz.idMatriz,md.idMatrizDetalle");
                
            } else if(kitMasterCve_view != null && !kitMasterCve_view.equals("-1")){
                logger.info("-->getResultMD_P_TEA: FILTERED kitMasterCve_view="+kitMasterCve_view);
                resultMD_P_TEA = genericSearchFacadeRemote.loadQuery(
                        "SELECT md.matriz.producto.idProducto               , md.matriz.producto.nombre           ,"+
                        "       md.matriz.tipoEquipoAvion.idTipoEquipoAvion , md.matriz.tipoEquipoAvion.tipoEquipo"+
                        " FROM MatrizDetalle md "+
                        " WHERE 1=1 "+
                        " and   md.matriz.kitMaster.cveKitMaster = '"+kitMasterCve_view+"'"+
                        " ORDER BY "+
                        "       md.matriz.idMatriz,md.idMatrizDetalle");
            }else {
                throw new IllegalStateException("kitMasterCve_view value is"+kitMasterCve_view);
            }
            logger.info("resultMD_P_TEA{ idProd,prod,idTEA,TEA");
            
            for(Object[] ox:resultMD_P_TEA){                
                logger.info("\t==>>["+ox[0]+"]["+ox[1]+"]["+ox[2]+"]["+ox[3]+"]");
            }
            logger.info("}");
        }
        
        return resultMD_P_TEA;
    }
    
    
    public List<SelectItem> getSelectTipoEquipoView() {
        if(selectTipoEquipoAvionView == null){
            logger.info("-->> getSelectTipoEquipoView(): fill list !");
            selectTipoEquipoAvionView = new ArrayList<SelectItem>();
            selectTipoEquipoAvionView.add(localeInfo.createSelectIntKeyFirstItem());            
            //matrizDetalleSearchList = new ArrayList<MatrizDetalle>();
            HashSet<Integer>  justDistinctIDs=new HashSet<Integer>();
            for(Object[] orMD_P_TEA:getResultMD_P_TEA()){
                Integer idObj   = (Integer)orMD_P_TEA[2];
                String  descObj = (String)orMD_P_TEA [3];
                if(!justDistinctIDs.contains(idObj)){
                    justDistinctIDs.add(idObj);
                    selectTipoEquipoAvionView.add(new SelectItem(idObj,descObj));
                }
            }            
        }
        return selectTipoEquipoAvionView;
    }

    public List<SelectItem> getSelectProductoView() {
        if(selectProductoView == null){
            logger.info("-->> getSelectProductoView(): fill list !");
            selectProductoView = new ArrayList<SelectItem>();
            selectProductoView.add(localeInfo.createSelectIntKeyFirstItem());                        
            //matrizDetalleSearchList = new ArrayList<MatrizDetalle>();
            HashSet<Integer>  justDistinctIDs=new HashSet<Integer>();
            for(Object[] orMD_P_TEA:getResultMD_P_TEA()){
                Integer idObj   = (Integer)orMD_P_TEA[0];
                String  descObj = (String)orMD_P_TEA [1];
                if(!justDistinctIDs.contains(idObj)){
                    justDistinctIDs.add(idObj);
                    selectProductoView.add(new SelectItem(idObj,descObj));
                }
            }
        }
        return selectProductoView;
    }
}
