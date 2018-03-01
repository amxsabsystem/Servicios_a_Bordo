package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.DetalleTablaTsu;
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
public interface DetalleTablaTsuFacadeRemote {

	DetalleTablaTsu create(DetalleTablaTsu entity);

	DetalleTablaTsu update(DetalleTablaTsu entity);

	void remove(DetalleTablaTsu entity);

    void removeByPK(Object pk);

	DetalleTablaTsu findByPK(Object pk);

    DetalleTablaTsu findByPK_EAGER(Object pk);

	List<DetalleTablaTsu> findAllLike(DetalleTablaTsu entity);

	List<DetalleTablaTsu> findAll();

	List<DetalleTablaTsu> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<DetalleTablaTsu> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
