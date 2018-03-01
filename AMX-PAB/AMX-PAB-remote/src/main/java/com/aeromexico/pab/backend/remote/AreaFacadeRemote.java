package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Area;
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
public interface AreaFacadeRemote {

	Area create(Area entity);

	Area update(Area entity);

	void remove(Area entity);

    void removeByPK(Object pk);

	Area findByPK(Object pk);

    Area findByPK_EAGER(Object pk);

	List<Area> findAllLike(Area entity);

	List<Area> findAll();

	List<Area> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Area> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
