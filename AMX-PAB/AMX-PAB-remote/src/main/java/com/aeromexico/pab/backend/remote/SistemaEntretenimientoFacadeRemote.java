package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.SistemaEntretenimiento;
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
public interface SistemaEntretenimientoFacadeRemote {

	SistemaEntretenimiento create(SistemaEntretenimiento entity);

	SistemaEntretenimiento update(SistemaEntretenimiento entity);

	void remove(SistemaEntretenimiento entity);

    void removeByPK(Object pk);

	SistemaEntretenimiento findByPK(Object pk);

    SistemaEntretenimiento findByPK_EAGER(Object pk);

	List<SistemaEntretenimiento> findAllLike(SistemaEntretenimiento entity);

	List<SistemaEntretenimiento> findAll();

	List<SistemaEntretenimiento> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<SistemaEntretenimiento> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
