package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Estacion;
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
public interface EstacionFacadeRemote {

	Estacion create(Estacion entity);

	Estacion update(Estacion entity);

	void remove(Estacion entity);

    void removeByPK(Object pk);

	Estacion findByPK(Object pk);

    Estacion findByPK_EAGER(Object pk);

	List<Estacion> findAllLike(Estacion entity);

	List<Estacion> findAll();

	List<Estacion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Estacion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
