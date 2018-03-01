package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ProveedorEstacion;
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
public interface ProveedorEstacionFacadeRemote {

	ProveedorEstacion create(ProveedorEstacion entity);

	ProveedorEstacion update(ProveedorEstacion entity);

	void remove(ProveedorEstacion entity);

    void removeByPK(Object pk);

	ProveedorEstacion findByPK(Object pk);

    ProveedorEstacion findByPK_EAGER(Object pk);

	List<ProveedorEstacion> findAllLike(ProveedorEstacion entity);

	List<ProveedorEstacion> findAll();

	List<ProveedorEstacion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ProveedorEstacion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
