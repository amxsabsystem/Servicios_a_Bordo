package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AvionCapacidad;
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
public interface AvionCapacidadFacadeRemote {

	AvionCapacidad create(AvionCapacidad entity);

	AvionCapacidad update(AvionCapacidad entity);

	void remove(AvionCapacidad entity);

    void removeByPK(Object pk);

	AvionCapacidad findByPK(Object pk);

    AvionCapacidad findByPK_EAGER(Object pk);

	List<AvionCapacidad> findAllLike(AvionCapacidad entity);

	List<AvionCapacidad> findAll();

	List<AvionCapacidad> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AvionCapacidad> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
