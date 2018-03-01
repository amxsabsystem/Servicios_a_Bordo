package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.MaterialExtra;
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
public interface MaterialExtraFacadeRemote {

	MaterialExtra create(MaterialExtra entity);

	MaterialExtra update(MaterialExtra entity);

	void remove(MaterialExtra entity);

    void removeByPK(Object pk);

	MaterialExtra findByPK(Object pk);

    MaterialExtra findByPK_EAGER(Object pk);

	List<MaterialExtra> findAllLike(MaterialExtra entity);

	List<MaterialExtra> findAll();

	List<MaterialExtra> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<MaterialExtra> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
