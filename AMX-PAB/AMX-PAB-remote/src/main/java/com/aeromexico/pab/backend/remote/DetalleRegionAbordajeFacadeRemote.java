package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.DetalleRegionAbordaje;
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
public interface DetalleRegionAbordajeFacadeRemote {

	DetalleRegionAbordaje create(DetalleRegionAbordaje entity);

	DetalleRegionAbordaje update(DetalleRegionAbordaje entity);

	void remove(DetalleRegionAbordaje entity);

    void removeByPK(Object pk);

	DetalleRegionAbordaje findByPK(Object pk);

    DetalleRegionAbordaje findByPK_EAGER(Object pk);

	List<DetalleRegionAbordaje> findAllLike(DetalleRegionAbordaje entity);

	List<DetalleRegionAbordaje> findAll();

	List<DetalleRegionAbordaje> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<DetalleRegionAbordaje> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
