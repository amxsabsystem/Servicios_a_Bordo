package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.HorarioRegion;
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
public interface HorarioRegionFacadeRemote {

	HorarioRegion create(HorarioRegion entity);

	HorarioRegion update(HorarioRegion entity);

	void remove(HorarioRegion entity);

    void removeByPK(Object pk);

	HorarioRegion findByPK(Object pk);

    HorarioRegion findByPK_EAGER(Object pk);

	List<HorarioRegion> findAllLike(HorarioRegion entity);

	List<HorarioRegion> findAll();

	List<HorarioRegion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<HorarioRegion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
