package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Proceso;
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
public interface ProcesoFacadeRemote {

	Proceso create(Proceso entity);

	Proceso update(Proceso entity);

	void remove(Proceso entity);

    void removeByPK(Object pk);

	Proceso findByPK(Object pk);

    Proceso findByPK_EAGER(Object pk);

	List<Proceso> findAllLike(Proceso entity);

	List<Proceso> findAll();

	List<Proceso> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Proceso> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
