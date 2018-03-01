package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Clase;
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
public interface ClaseFacadeRemote {

	Clase create(Clase entity);

	Clase update(Clase entity);

	void remove(Clase entity);

    void removeByPK(Object pk);

	Clase findByPK(Object pk);

    Clase findByPK_EAGER(Object pk);

	List<Clase> findAllLike(Clase entity);

	List<Clase> findAll();

	List<Clase> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Clase> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
