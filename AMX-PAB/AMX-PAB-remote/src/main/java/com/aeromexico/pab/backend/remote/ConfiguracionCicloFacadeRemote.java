package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ConfiguracionCiclo;
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
public interface ConfiguracionCicloFacadeRemote {

	ConfiguracionCiclo create(ConfiguracionCiclo entity);

	ConfiguracionCiclo update(ConfiguracionCiclo entity);

	void remove(ConfiguracionCiclo entity);

    void removeByPK(Object pk);

	ConfiguracionCiclo findByPK(Object pk);

    ConfiguracionCiclo findByPK_EAGER(Object pk);

	List<ConfiguracionCiclo> findAllLike(ConfiguracionCiclo entity);

	List<ConfiguracionCiclo> findAll();

	List<ConfiguracionCiclo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ConfiguracionCiclo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
