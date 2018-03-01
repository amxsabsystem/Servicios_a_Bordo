package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AsignacionServicio;
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
public interface AsignacionServicioFacadeRemote {

	AsignacionServicio create(AsignacionServicio entity);

	AsignacionServicio update(AsignacionServicio entity);

	void remove(AsignacionServicio entity);

    void removeByPK(Object pk);

	AsignacionServicio findByPK(Object pk);

    AsignacionServicio findByPK_EAGER(Object pk);

	List<AsignacionServicio> findAllLike(AsignacionServicio entity);

	List<AsignacionServicio> findAll();

	List<AsignacionServicio> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AsignacionServicio> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
