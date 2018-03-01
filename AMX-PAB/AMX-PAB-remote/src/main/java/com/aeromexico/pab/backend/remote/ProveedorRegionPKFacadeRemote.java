package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ProveedorRegionPK;
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
public interface ProveedorRegionPKFacadeRemote {

	ProveedorRegionPK create(ProveedorRegionPK entity);

	ProveedorRegionPK update(ProveedorRegionPK entity);

	void remove(ProveedorRegionPK entity);

    void removeByPK(Object pk);

	ProveedorRegionPK findByPK(Object pk);

    ProveedorRegionPK findByPK_EAGER(Object pk);

	List<ProveedorRegionPK> findAllLike(ProveedorRegionPK entity);

	List<ProveedorRegionPK> findAll();

	List<ProveedorRegionPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ProveedorRegionPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
