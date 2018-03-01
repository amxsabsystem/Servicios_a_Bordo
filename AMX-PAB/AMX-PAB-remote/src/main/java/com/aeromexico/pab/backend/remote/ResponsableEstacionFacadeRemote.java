package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ResponsableEstacion;
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
public interface ResponsableEstacionFacadeRemote {

	ResponsableEstacion create(ResponsableEstacion entity);

	ResponsableEstacion update(ResponsableEstacion entity);

	void remove(ResponsableEstacion entity);

    void removeByPK(Object pk);

	ResponsableEstacion findByPK(Object pk);

    ResponsableEstacion findByPK_EAGER(Object pk);

	List<ResponsableEstacion> findAllLike(ResponsableEstacion entity);

	List<ResponsableEstacion> findAll();

	List<ResponsableEstacion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ResponsableEstacion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
