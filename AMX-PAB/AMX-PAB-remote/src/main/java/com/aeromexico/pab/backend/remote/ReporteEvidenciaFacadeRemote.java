package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ReporteEvidencia;
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
public interface ReporteEvidenciaFacadeRemote {

	ReporteEvidencia create(ReporteEvidencia entity);

	ReporteEvidencia update(ReporteEvidencia entity);

	void remove(ReporteEvidencia entity);

    void removeByPK(Object pk);

	ReporteEvidencia findByPK(Object pk);

    ReporteEvidencia findByPK_EAGER(Object pk);

	List<ReporteEvidencia> findAllLike(ReporteEvidencia entity);

	List<ReporteEvidencia> findAll();

	List<ReporteEvidencia> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ReporteEvidencia> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
