package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Tsu;
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
public interface TsuFacadeRemote {

	Tsu create(Tsu entity);

	Tsu update(Tsu entity);

	void remove(Tsu entity);

    void removeByPK(Object pk);

	Tsu findByPK(Object pk);

    Tsu findByPK_EAGER(Object pk);

	List<Tsu> findAllLike(Tsu entity);

	List<Tsu> findAll();

	List<Tsu> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Tsu> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
