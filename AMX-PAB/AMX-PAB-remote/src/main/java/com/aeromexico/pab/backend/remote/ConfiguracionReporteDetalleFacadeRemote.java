package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ConfiguracionReporteDetalle;
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
public interface ConfiguracionReporteDetalleFacadeRemote {

	ConfiguracionReporteDetalle create(ConfiguracionReporteDetalle entity);

	ConfiguracionReporteDetalle update(ConfiguracionReporteDetalle entity);

	void remove(ConfiguracionReporteDetalle entity);

    void removeByPK(Object pk);

	ConfiguracionReporteDetalle findByPK(Object pk);

    ConfiguracionReporteDetalle findByPK_EAGER(Object pk);

	List<ConfiguracionReporteDetalle> findAllLike(ConfiguracionReporteDetalle entity);

	List<ConfiguracionReporteDetalle> findAll();

	List<ConfiguracionReporteDetalle> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ConfiguracionReporteDetalle> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
