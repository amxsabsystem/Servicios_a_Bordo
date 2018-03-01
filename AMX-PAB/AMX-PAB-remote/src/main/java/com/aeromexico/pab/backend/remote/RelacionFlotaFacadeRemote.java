package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.RelacionFlota;
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
public interface RelacionFlotaFacadeRemote {

	RelacionFlota create(RelacionFlota entity);

	RelacionFlota update(RelacionFlota entity);

	void remove(RelacionFlota entity);

    void removeByPK(Object pk);

	RelacionFlota findByPK(Object pk);

    RelacionFlota findByPK_EAGER(Object pk);

	List<RelacionFlota> findAllLike(RelacionFlota entity);

	List<RelacionFlota> findAll();

	List<RelacionFlota> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<RelacionFlota> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
