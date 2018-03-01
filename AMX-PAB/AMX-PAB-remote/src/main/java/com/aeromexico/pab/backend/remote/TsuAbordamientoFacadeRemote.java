package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TsuAbordamiento;
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
public interface TsuAbordamientoFacadeRemote {

	TsuAbordamiento create(TsuAbordamiento entity);

	TsuAbordamiento update(TsuAbordamiento entity);

	void remove(TsuAbordamiento entity);

    void removeByPK(Object pk);

	TsuAbordamiento findByPK(Object pk);

    TsuAbordamiento findByPK_EAGER(Object pk);

	List<TsuAbordamiento> findAllLike(TsuAbordamiento entity);

	List<TsuAbordamiento> findAll();

	List<TsuAbordamiento> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TsuAbordamiento> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
