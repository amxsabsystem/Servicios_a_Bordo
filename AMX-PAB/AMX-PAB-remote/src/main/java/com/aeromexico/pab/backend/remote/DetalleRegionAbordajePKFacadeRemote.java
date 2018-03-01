package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.DetalleRegionAbordajePK;
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
public interface DetalleRegionAbordajePKFacadeRemote {

	DetalleRegionAbordajePK create(DetalleRegionAbordajePK entity);

	DetalleRegionAbordajePK update(DetalleRegionAbordajePK entity);

	void remove(DetalleRegionAbordajePK entity);

    void removeByPK(Object pk);

	DetalleRegionAbordajePK findByPK(Object pk);

    DetalleRegionAbordajePK findByPK_EAGER(Object pk);

	List<DetalleRegionAbordajePK> findAllLike(DetalleRegionAbordajePK entity);

	List<DetalleRegionAbordajePK> findAll();

	List<DetalleRegionAbordajePK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<DetalleRegionAbordajePK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
