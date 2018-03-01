package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Reporte;
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
public interface ReporteFacadeRemote {

	Reporte create(Reporte entity);

	Reporte update(Reporte entity);

	void remove(Reporte entity);

    void removeByPK(Object pk);

	Reporte findByPK(Object pk);

    Reporte findByPK_EAGER(Object pk);

	List<Reporte> findAllLike(Reporte entity);

	List<Reporte> findAll();

	List<Reporte> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Reporte> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
