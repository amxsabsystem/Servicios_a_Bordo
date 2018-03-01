package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Seguimiento;
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
public interface SeguimientoFacadeRemote {

	Seguimiento create(Seguimiento entity);

	Seguimiento update(Seguimiento entity);

	void remove(Seguimiento entity);

    void removeByPK(Object pk);

	Seguimiento findByPK(Object pk);

    Seguimiento findByPK_EAGER(Object pk);

	List<Seguimiento> findAllLike(Seguimiento entity);

	List<Seguimiento> findAll();

	List<Seguimiento> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Seguimiento> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
