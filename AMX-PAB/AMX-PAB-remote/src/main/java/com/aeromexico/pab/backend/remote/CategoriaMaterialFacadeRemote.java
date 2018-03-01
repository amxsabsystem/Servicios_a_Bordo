package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.CategoriaMaterial;
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
public interface CategoriaMaterialFacadeRemote {

	CategoriaMaterial create(CategoriaMaterial entity);

	CategoriaMaterial update(CategoriaMaterial entity);

	void remove(CategoriaMaterial entity);

    void removeByPK(Object pk);

	CategoriaMaterial findByPK(Object pk);

    CategoriaMaterial findByPK_EAGER(Object pk);

	List<CategoriaMaterial> findAllLike(CategoriaMaterial entity);

	List<CategoriaMaterial> findAll();

	List<CategoriaMaterial> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<CategoriaMaterial> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
