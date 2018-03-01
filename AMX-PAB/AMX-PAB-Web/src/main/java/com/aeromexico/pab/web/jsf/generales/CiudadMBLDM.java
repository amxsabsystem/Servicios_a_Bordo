package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.util.PaginatedResult;
import com.aeromexico.pab.entity.Ciudad;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alfredo.estrada@gonet.us
 */
@Named(value = "ciudadMBLDM")
@SessionScoped
public class CiudadMBLDM extends LazyDataModel<Ciudad> implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(CiudadMBLDM.class.getName());

    private Ciudad row_selected = null;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote servicioFacadeRemote;
    
    private static final int[] ROWS_PER_PAGE = new int[]{8,16,32,64,128,256,512};
    
    private static final int   defaultRowsPerPage = ROWS_PER_PAGE[1];

    private static String rowsPerPageOptions = null;

    private PaginatedResult<Ciudad> loadPaginated = null;
    
    private Ciudad selected;

    private List<Ciudad> datasource;

    public CiudadMBLDM() {
        logger.debug("->CiudadLDM.CiudadMBLDM()");
    }

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        logger.debug("->CiudadLDM.init():@PostConstruct");
    }
    //==========================================================================
    
    public String getRowsPerPageOptions() {
        if(rowsPerPageOptions == null){
            StringBuilder sbRowsPerPageOptions=new StringBuilder();
            int ri=0;
            for(int r: ROWS_PER_PAGE){
                if(ri>0){
                    sbRowsPerPageOptions.append(",");
                }
                sbRowsPerPageOptions.append(r);
                ri++;
            }            
            rowsPerPageOptions = sbRowsPerPageOptions.toString();            
        }
        return rowsPerPageOptions;
    }

    public int getDefaultRowsPerPage() {
        return defaultRowsPerPage;
    }
    
    public LazyDataModel<Ciudad> getItemsLDM() {        
        return this;
    }


    public Ciudad getSelected() {
        return selected;
    }

    public void setSelected(Ciudad selected) {
        this.selected = selected;
    }
         
    @Override
    public Ciudad getRowData(String rowKey) {
        Ciudad x = servicioFacadeRemote.findByPK_EAGER(new Integer(rowKey));
        return x;
    }
 
    @Override
    public Object getRowKey(Ciudad m) {
        logger.debug("->CiudadLDM.getRowKey(m="+m+")");
        return m.getIdCiudad();
    }
 
    @Override
    public List<Ciudad> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        datasource = null;
        
        logger.debug("->CiudadLDM.load(first="+first+",pageSize="+pageSize+",sortField="+sortField+",sortOrder="+sortOrder+",filters="+filters+")");
        
        loadPaginated = servicioFacadeRemote.loadPaginated(first, pageSize, sortField, sortOrder.toString(), filters);
        //loadPaginated = new PaginatedResult<Ciudad>(new ArrayList<Ciudad>(),0,pageSize);
        
        logger.debug("->CiudadLDM.load: loadPaginated.getTotalCount()"+loadPaginated.getTotalCount());
        
        setRowCount(loadPaginated.getTotalCount());
        setPageSize(pageSize);
        
        datasource = loadPaginated.getPaginatedResultList();
                
        return datasource;
    }    
}
