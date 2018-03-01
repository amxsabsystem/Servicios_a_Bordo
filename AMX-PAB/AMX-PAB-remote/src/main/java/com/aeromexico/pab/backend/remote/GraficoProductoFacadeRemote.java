package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.GraficoProducto;
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
public interface GraficoProductoFacadeRemote {

	GraficoProducto create(GraficoProducto entity);

	GraficoProducto update(GraficoProducto entity);

	void remove(GraficoProducto entity);

    void removeByPK(Object pk);

	GraficoProducto findByPK(Object pk);

    GraficoProducto findByPK_EAGER(Object pk);

	List<GraficoProducto> findAllLike(GraficoProducto entity);

	List<GraficoProducto> findAll();

	List<GraficoProducto> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<GraficoProducto> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
