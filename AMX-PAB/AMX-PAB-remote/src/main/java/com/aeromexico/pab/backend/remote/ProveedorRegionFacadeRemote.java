package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ProveedorRegion;
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
public interface ProveedorRegionFacadeRemote {

	ProveedorRegion create(ProveedorRegion entity);

	ProveedorRegion update(ProveedorRegion entity);

	void remove(ProveedorRegion entity);

    void removeByPK(Object pk);

	ProveedorRegion findByPK(Object pk);

    ProveedorRegion findByPK_EAGER(Object pk);

	List<ProveedorRegion> findAllLike(ProveedorRegion entity);

	List<ProveedorRegion> findAll();

	List<ProveedorRegion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ProveedorRegion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
