package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ReporteEvidenciaPK;
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
public interface ReporteEvidenciaPKFacadeRemote {

	ReporteEvidenciaPK create(ReporteEvidenciaPK entity);

	ReporteEvidenciaPK update(ReporteEvidenciaPK entity);

	void remove(ReporteEvidenciaPK entity);

    void removeByPK(Object pk);

	ReporteEvidenciaPK findByPK(Object pk);

    ReporteEvidenciaPK findByPK_EAGER(Object pk);

	List<ReporteEvidenciaPK> findAllLike(ReporteEvidenciaPK entity);

	List<ReporteEvidenciaPK> findAll();

	List<ReporteEvidenciaPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ReporteEvidenciaPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
