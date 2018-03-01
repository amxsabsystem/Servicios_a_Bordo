package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AvionCapacidadPK;
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
public interface AvionCapacidadPKFacadeRemote {

	AvionCapacidadPK create(AvionCapacidadPK entity);

	AvionCapacidadPK update(AvionCapacidadPK entity);

	void remove(AvionCapacidadPK entity);

    void removeByPK(Object pk);

	AvionCapacidadPK findByPK(Object pk);

    AvionCapacidadPK findByPK_EAGER(Object pk);

	List<AvionCapacidadPK> findAllLike(AvionCapacidadPK entity);

	List<AvionCapacidadPK> findAll();

	List<AvionCapacidadPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AvionCapacidadPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
