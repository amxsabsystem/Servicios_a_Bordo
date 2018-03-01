package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Empleado;
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
public interface EmpleadoFacadeRemote {

	Empleado create(Empleado entity);

	Empleado update(Empleado entity);

	void remove(Empleado entity);

    void removeByPK(Object pk);

	Empleado findByPK(Object pk);

    Empleado findByPK_EAGER(Object pk);

	List<Empleado> findAllLike(Empleado entity);

	List<Empleado> findAll();

	List<Empleado> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Empleado> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
