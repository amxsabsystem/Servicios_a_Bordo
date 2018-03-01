package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TipoIrregularidad;
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
public interface TipoIrregularidadFacadeRemote {

	TipoIrregularidad create(TipoIrregularidad entity);

	TipoIrregularidad update(TipoIrregularidad entity);

	void remove(TipoIrregularidad entity);

    void removeByPK(Object pk);

	TipoIrregularidad findByPK(Object pk);

    TipoIrregularidad findByPK_EAGER(Object pk);

	List<TipoIrregularidad> findAllLike(TipoIrregularidad entity);

	List<TipoIrregularidad> findAll();

	List<TipoIrregularidad> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TipoIrregularidad> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
