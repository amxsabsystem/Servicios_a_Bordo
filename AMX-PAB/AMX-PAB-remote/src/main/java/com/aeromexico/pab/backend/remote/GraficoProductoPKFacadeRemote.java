package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.GraficoProductoPK;
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
public interface GraficoProductoPKFacadeRemote {

	GraficoProductoPK create(GraficoProductoPK entity);

	GraficoProductoPK update(GraficoProductoPK entity);

	void remove(GraficoProductoPK entity);

    void removeByPK(Object pk);

	GraficoProductoPK findByPK(Object pk);

    GraficoProductoPK findByPK_EAGER(Object pk);

	List<GraficoProductoPK> findAllLike(GraficoProductoPK entity);

	List<GraficoProductoPK> findAll();

	List<GraficoProductoPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<GraficoProductoPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
