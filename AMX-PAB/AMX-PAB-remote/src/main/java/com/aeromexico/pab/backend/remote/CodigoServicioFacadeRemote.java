package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.CodigoServicio;
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
public interface CodigoServicioFacadeRemote {

	CodigoServicio create(CodigoServicio entity);

	CodigoServicio update(CodigoServicio entity);

	void remove(CodigoServicio entity);

    void removeByPK(Object pk);

	CodigoServicio findByPK(Object pk);

    CodigoServicio findByPK_EAGER(Object pk);

	List<CodigoServicio> findAllLike(CodigoServicio entity);

	List<CodigoServicio> findAll();

	List<CodigoServicio> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<CodigoServicio> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
