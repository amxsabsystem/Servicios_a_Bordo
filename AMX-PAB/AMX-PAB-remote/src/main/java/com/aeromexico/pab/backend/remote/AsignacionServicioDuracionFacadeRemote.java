package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AsignacionServicioDuracion;
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
public interface AsignacionServicioDuracionFacadeRemote {

	AsignacionServicioDuracion create(AsignacionServicioDuracion entity);

	AsignacionServicioDuracion update(AsignacionServicioDuracion entity);

	void remove(AsignacionServicioDuracion entity);

    void removeByPK(Object pk);

	AsignacionServicioDuracion findByPK(Object pk);

    AsignacionServicioDuracion findByPK_EAGER(Object pk);

	List<AsignacionServicioDuracion> findAllLike(AsignacionServicioDuracion entity);

	List<AsignacionServicioDuracion> findAll();

	List<AsignacionServicioDuracion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AsignacionServicioDuracion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
