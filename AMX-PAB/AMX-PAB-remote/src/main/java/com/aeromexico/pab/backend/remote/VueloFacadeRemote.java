package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Vuelo;
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
public interface VueloFacadeRemote {

	Vuelo create(Vuelo entity);

	Vuelo update(Vuelo entity);

	void remove(Vuelo entity);

    void removeByPK(Object pk);

	Vuelo findByPK(Object pk);

    Vuelo findByPK_EAGER(Object pk);

	List<Vuelo> findAllLike(Vuelo entity);

	List<Vuelo> findAll();

	List<Vuelo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Vuelo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
