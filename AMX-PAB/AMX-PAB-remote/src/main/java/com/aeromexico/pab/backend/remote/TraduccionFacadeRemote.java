package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Traduccion;
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
public interface TraduccionFacadeRemote {

	Traduccion create(Traduccion entity);

	Traduccion update(Traduccion entity);

	void remove(Traduccion entity);

    void removeByPK(Object pk);

	Traduccion findByPK(Object pk);

    Traduccion findByPK_EAGER(Object pk);

	List<Traduccion> findAllLike(Traduccion entity);

	List<Traduccion> findAll();

	List<Traduccion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Traduccion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
