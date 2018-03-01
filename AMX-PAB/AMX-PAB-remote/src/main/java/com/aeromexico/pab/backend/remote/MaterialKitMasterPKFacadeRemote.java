package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.MaterialKitMasterPK;
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
public interface MaterialKitMasterPKFacadeRemote {

	MaterialKitMasterPK create(MaterialKitMasterPK entity);

	MaterialKitMasterPK update(MaterialKitMasterPK entity);

	void remove(MaterialKitMasterPK entity);

    void removeByPK(Object pk);

	MaterialKitMasterPK findByPK(Object pk);

    MaterialKitMasterPK findByPK_EAGER(Object pk);

	List<MaterialKitMasterPK> findAllLike(MaterialKitMasterPK entity);

	List<MaterialKitMasterPK> findAll();

	List<MaterialKitMasterPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<MaterialKitMasterPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
