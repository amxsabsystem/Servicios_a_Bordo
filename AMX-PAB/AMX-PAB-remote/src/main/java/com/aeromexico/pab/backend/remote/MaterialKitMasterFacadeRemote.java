package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.MaterialKitMaster;
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
public interface MaterialKitMasterFacadeRemote {

	MaterialKitMaster create(MaterialKitMaster entity);

	MaterialKitMaster update(MaterialKitMaster entity);

	void remove(MaterialKitMaster entity);

    void removeByPK(Object pk);

	MaterialKitMaster findByPK(Object pk);

    MaterialKitMaster findByPK_EAGER(Object pk);

	List<MaterialKitMaster> findAllLike(MaterialKitMaster entity);

	List<MaterialKitMaster> findAll();

	List<MaterialKitMaster> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<MaterialKitMaster> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
