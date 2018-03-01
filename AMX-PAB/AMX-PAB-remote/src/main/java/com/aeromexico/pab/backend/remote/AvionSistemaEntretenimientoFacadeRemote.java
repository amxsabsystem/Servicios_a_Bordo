package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AvionSistemaEntretenimiento;
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
public interface AvionSistemaEntretenimientoFacadeRemote {

	AvionSistemaEntretenimiento create(AvionSistemaEntretenimiento entity);

	AvionSistemaEntretenimiento update(AvionSistemaEntretenimiento entity);

	void remove(AvionSistemaEntretenimiento entity);

    void removeByPK(Object pk);

	AvionSistemaEntretenimiento findByPK(Object pk);

    AvionSistemaEntretenimiento findByPK_EAGER(Object pk);

	List<AvionSistemaEntretenimiento> findAllLike(AvionSistemaEntretenimiento entity);

	List<AvionSistemaEntretenimiento> findAll();

	List<AvionSistemaEntretenimiento> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AvionSistemaEntretenimiento> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
