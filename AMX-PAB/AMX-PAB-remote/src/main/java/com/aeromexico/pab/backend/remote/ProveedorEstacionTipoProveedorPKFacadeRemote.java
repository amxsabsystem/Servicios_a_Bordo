package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ProveedorEstacionTipoProveedorPK;
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
public interface ProveedorEstacionTipoProveedorPKFacadeRemote {

	ProveedorEstacionTipoProveedorPK create(ProveedorEstacionTipoProveedorPK entity);

	ProveedorEstacionTipoProveedorPK update(ProveedorEstacionTipoProveedorPK entity);

	void remove(ProveedorEstacionTipoProveedorPK entity);

    void removeByPK(Object pk);

	ProveedorEstacionTipoProveedorPK findByPK(Object pk);

    ProveedorEstacionTipoProveedorPK findByPK_EAGER(Object pk);

	List<ProveedorEstacionTipoProveedorPK> findAllLike(ProveedorEstacionTipoProveedorPK entity);

	List<ProveedorEstacionTipoProveedorPK> findAll();

	List<ProveedorEstacionTipoProveedorPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ProveedorEstacionTipoProveedorPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
