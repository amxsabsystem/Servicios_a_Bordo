package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Parametro;
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
public interface ParametroFacadeRemote {

	Parametro create(Parametro entity);

	Parametro update(Parametro entity);

	void remove(Parametro entity);

    void removeByPK(Object pk);

	Parametro findByPK(Object pk);

    Parametro findByPK_EAGER(Object pk);

	List<Parametro> findAllLike(Parametro entity);

	List<Parametro> findAll();

	List<Parametro> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Parametro> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
