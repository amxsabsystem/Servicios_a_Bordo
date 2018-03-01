package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Region;
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
public interface RegionFacadeRemote {

	Region create(Region entity);

	Region update(Region entity);

	void remove(Region entity);

    void removeByPK(Object pk);

	Region findByPK(Object pk);

    Region findByPK_EAGER(Object pk);

	List<Region> findAllLike(Region entity);

	List<Region> findAll();

	List<Region> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Region> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
