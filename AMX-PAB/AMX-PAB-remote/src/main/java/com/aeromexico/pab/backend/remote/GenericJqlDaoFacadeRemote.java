package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Acomodo;
import com.aeromexico.pab.backend.remote.util.PaginatedResult;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 * JPA Entity @Remote Facade Interface.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Remote
public interface GenericJqlDaoFacadeRemote {

    PaginatedResult<Object> loadPaginated(String jql,int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
