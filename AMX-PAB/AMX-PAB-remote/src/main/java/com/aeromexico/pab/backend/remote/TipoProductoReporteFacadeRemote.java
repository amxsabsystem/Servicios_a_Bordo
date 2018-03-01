package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TipoProductoReporte;
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
public interface TipoProductoReporteFacadeRemote {

	TipoProductoReporte create(TipoProductoReporte entity);

	TipoProductoReporte update(TipoProductoReporte entity);

	void remove(TipoProductoReporte entity);

    void removeByPK(Object pk);

	TipoProductoReporte findByPK(Object pk);

    TipoProductoReporte findByPK_EAGER(Object pk);

	List<TipoProductoReporte> findAllLike(TipoProductoReporte entity);

	List<TipoProductoReporte> findAll();

	List<TipoProductoReporte> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TipoProductoReporte> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
