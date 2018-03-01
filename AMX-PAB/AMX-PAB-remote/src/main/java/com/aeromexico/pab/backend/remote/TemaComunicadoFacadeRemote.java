package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TemaComunicado;
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
public interface TemaComunicadoFacadeRemote {

	TemaComunicado create(TemaComunicado entity);

	TemaComunicado update(TemaComunicado entity);

	void remove(TemaComunicado entity);

    void removeByPK(Object pk);

	TemaComunicado findByPK(Object pk);

    TemaComunicado findByPK_EAGER(Object pk);

	List<TemaComunicado> findAllLike(TemaComunicado entity);

	List<TemaComunicado> findAll();

	List<TemaComunicado> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TemaComunicado> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
