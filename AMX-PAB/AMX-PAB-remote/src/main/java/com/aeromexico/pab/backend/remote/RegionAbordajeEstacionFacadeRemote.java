package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.RegionAbordajeEstacion;
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
public interface RegionAbordajeEstacionFacadeRemote {

	RegionAbordajeEstacion create(RegionAbordajeEstacion entity);

	RegionAbordajeEstacion update(RegionAbordajeEstacion entity);

	void remove(RegionAbordajeEstacion entity);

    void removeByPK(Object pk);

	RegionAbordajeEstacion findByPK(Object pk);

    RegionAbordajeEstacion findByPK_EAGER(Object pk);

	List<RegionAbordajeEstacion> findAllLike(RegionAbordajeEstacion entity);

	List<RegionAbordajeEstacion> findAll();

	List<RegionAbordajeEstacion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<RegionAbordajeEstacion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
