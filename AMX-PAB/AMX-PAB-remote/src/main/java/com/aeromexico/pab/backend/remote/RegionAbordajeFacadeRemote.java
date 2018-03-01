package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.RegionAbordaje;
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
public interface RegionAbordajeFacadeRemote {

	RegionAbordaje create(RegionAbordaje entity);

	RegionAbordaje update(RegionAbordaje entity);

	void remove(RegionAbordaje entity);

    void removeByPK(Object pk);

	RegionAbordaje findByPK(Object pk);

    RegionAbordaje findByPK_EAGER(Object pk);

	List<RegionAbordaje> findAllLike(RegionAbordaje entity);

	List<RegionAbordaje> findAll();

	List<RegionAbordaje> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<RegionAbordaje> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
