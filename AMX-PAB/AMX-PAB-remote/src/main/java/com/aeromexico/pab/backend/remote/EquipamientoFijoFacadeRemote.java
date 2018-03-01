package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.EquipamientoFijo;
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
public interface EquipamientoFijoFacadeRemote {

	EquipamientoFijo create(EquipamientoFijo entity);

	EquipamientoFijo update(EquipamientoFijo entity);

	void remove(EquipamientoFijo entity);

    void removeByPK(Object pk);

	EquipamientoFijo findByPK(Object pk);

    EquipamientoFijo findByPK_EAGER(Object pk);

	List<EquipamientoFijo> findAllLike(EquipamientoFijo entity);

	List<EquipamientoFijo> findAll();

	List<EquipamientoFijo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<EquipamientoFijo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
