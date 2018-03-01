package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.KitTsu;
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
public interface KitTsuFacadeRemote {

	KitTsu create(KitTsu entity);

	KitTsu update(KitTsu entity);

	void remove(KitTsu entity);

    void removeByPK(Object pk);

	KitTsu findByPK(Object pk);

    KitTsu findByPK_EAGER(Object pk);

	List<KitTsu> findAllLike(KitTsu entity);

	List<KitTsu> findAll();

	List<KitTsu> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<KitTsu> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
