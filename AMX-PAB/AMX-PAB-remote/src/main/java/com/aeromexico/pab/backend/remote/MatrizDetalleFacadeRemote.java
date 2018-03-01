package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.MatrizDetalle;
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
public interface MatrizDetalleFacadeRemote {

	MatrizDetalle create(MatrizDetalle entity);

	MatrizDetalle update(MatrizDetalle entity);

	void remove(MatrizDetalle entity);

    void removeByPK(Object pk);

	MatrizDetalle findByPK(Object pk);

    MatrizDetalle findByPK_EAGER(Object pk);

	List<MatrizDetalle> findAllLike(MatrizDetalle entity);

	List<MatrizDetalle> findAll();

	List<MatrizDetalle> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<MatrizDetalle> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
