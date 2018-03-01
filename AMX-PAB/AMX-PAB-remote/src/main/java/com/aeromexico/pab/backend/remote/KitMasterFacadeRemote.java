package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.KitMaster;
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
public interface KitMasterFacadeRemote {

	KitMaster create(KitMaster entity);

	KitMaster update(KitMaster entity);

	void remove(KitMaster entity);

    void removeByPK(Object pk);

	KitMaster findByPK(Object pk);

    KitMaster findByPK_EAGER(Object pk);

	List<KitMaster> findAllLike(KitMaster entity);

	List<KitMaster> findAll();

	List<KitMaster> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<KitMaster> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
