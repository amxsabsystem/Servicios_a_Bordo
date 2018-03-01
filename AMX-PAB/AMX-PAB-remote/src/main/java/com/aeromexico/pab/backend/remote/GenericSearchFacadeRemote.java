package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AcomodoDetalle;
import com.aeromexico.pab.backend.remote.util.PaginatedResult;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 * @author Alfredo Estrada
 */
@Remote
public interface GenericSearchFacadeRemote {

    List<Object[]> loadQuery(String jql);
    
    PaginatedResult<Object[]> loadPaginated(int first,int pageSize,String jqlBase,String sortField,Object sortOrder, Map<String,Object> filters);
}
