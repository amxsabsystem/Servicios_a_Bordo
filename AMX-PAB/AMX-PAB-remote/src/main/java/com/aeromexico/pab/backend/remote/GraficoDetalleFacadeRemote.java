package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.GraficoDetalle;
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
public interface GraficoDetalleFacadeRemote {

	GraficoDetalle create(GraficoDetalle entity);

	GraficoDetalle update(GraficoDetalle entity);

	void remove(GraficoDetalle entity);

    void removeByPK(Object pk);

	GraficoDetalle findByPK(Object pk);

    GraficoDetalle findByPK_EAGER(Object pk);

	List<GraficoDetalle> findAllLike(GraficoDetalle entity);

	List<GraficoDetalle> findAll();

	List<GraficoDetalle> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<GraficoDetalle> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
