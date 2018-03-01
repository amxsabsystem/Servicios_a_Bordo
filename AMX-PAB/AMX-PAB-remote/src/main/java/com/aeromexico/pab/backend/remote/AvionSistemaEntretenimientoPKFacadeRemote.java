package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AvionSistemaEntretenimientoPK;
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
public interface AvionSistemaEntretenimientoPKFacadeRemote {

	AvionSistemaEntretenimientoPK create(AvionSistemaEntretenimientoPK entity);

	AvionSistemaEntretenimientoPK update(AvionSistemaEntretenimientoPK entity);

	void remove(AvionSistemaEntretenimientoPK entity);

    void removeByPK(Object pk);

	AvionSistemaEntretenimientoPK findByPK(Object pk);

    AvionSistemaEntretenimientoPK findByPK_EAGER(Object pk);

	List<AvionSistemaEntretenimientoPK> findAllLike(AvionSistemaEntretenimientoPK entity);

	List<AvionSistemaEntretenimientoPK> findAll();

	List<AvionSistemaEntretenimientoPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AvionSistemaEntretenimientoPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
