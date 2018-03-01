package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ProveedorEstacionTipoProveedor;
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
public interface ProveedorEstacionTipoProveedorFacadeRemote {

	ProveedorEstacionTipoProveedor create(ProveedorEstacionTipoProveedor entity);

	ProveedorEstacionTipoProveedor update(ProveedorEstacionTipoProveedor entity);

	void remove(ProveedorEstacionTipoProveedor entity);

    void removeByPK(Object pk);

	ProveedorEstacionTipoProveedor findByPK(Object pk);

    ProveedorEstacionTipoProveedor findByPK_EAGER(Object pk);

	List<ProveedorEstacionTipoProveedor> findAllLike(ProveedorEstacionTipoProveedor entity);

	List<ProveedorEstacionTipoProveedor> findAll();

	List<ProveedorEstacionTipoProveedor> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ProveedorEstacionTipoProveedor> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
