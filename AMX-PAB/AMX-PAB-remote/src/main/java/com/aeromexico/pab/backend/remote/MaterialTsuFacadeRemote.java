package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.MaterialTsu;
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
public interface MaterialTsuFacadeRemote {

	MaterialTsu create(MaterialTsu entity);

	MaterialTsu update(MaterialTsu entity);

	void remove(MaterialTsu entity);

    void removeByPK(Object pk);

	MaterialTsu findByPK(Object pk);

    MaterialTsu findByPK_EAGER(Object pk);

	List<MaterialTsu> findAllLike(MaterialTsu entity);

	List<MaterialTsu> findAll();

	List<MaterialTsu> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<MaterialTsu> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
