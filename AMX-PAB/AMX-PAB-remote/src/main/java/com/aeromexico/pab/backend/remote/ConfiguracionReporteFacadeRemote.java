package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ConfiguracionReporte;
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
public interface ConfiguracionReporteFacadeRemote {

	ConfiguracionReporte create(ConfiguracionReporte entity);

	ConfiguracionReporte update(ConfiguracionReporte entity);

	void remove(ConfiguracionReporte entity);

    void removeByPK(Object pk);

	ConfiguracionReporte findByPK(Object pk);

    ConfiguracionReporte findByPK_EAGER(Object pk);

	List<ConfiguracionReporte> findAllLike(ConfiguracionReporte entity);

	List<ConfiguracionReporte> findAll();

	List<ConfiguracionReporte> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ConfiguracionReporte> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
