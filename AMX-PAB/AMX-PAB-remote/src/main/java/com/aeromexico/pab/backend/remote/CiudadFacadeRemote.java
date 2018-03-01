package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Ciudad;
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
public interface CiudadFacadeRemote {

	Ciudad create(Ciudad entity);

	Ciudad update(Ciudad entity);

	void remove(Ciudad entity);

    void removeByPK(Object pk);

	Ciudad findByPK(Object pk);

    Ciudad findByPK_EAGER(Object pk);

	List<Ciudad> findAllLike(Ciudad entity);

	List<Ciudad> findAll();

	List<Ciudad> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Ciudad> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
