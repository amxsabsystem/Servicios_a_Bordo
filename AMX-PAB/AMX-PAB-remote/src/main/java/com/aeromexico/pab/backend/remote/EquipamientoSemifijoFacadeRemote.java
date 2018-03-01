package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.EquipamientoSemifijo;
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
public interface EquipamientoSemifijoFacadeRemote {

	EquipamientoSemifijo create(EquipamientoSemifijo entity);

	EquipamientoSemifijo update(EquipamientoSemifijo entity);

	void remove(EquipamientoSemifijo entity);

    void removeByPK(Object pk);

	EquipamientoSemifijo findByPK(Object pk);

    EquipamientoSemifijo findByPK_EAGER(Object pk);

	List<EquipamientoSemifijo> findAllLike(EquipamientoSemifijo entity);

	List<EquipamientoSemifijo> findAll();

	List<EquipamientoSemifijo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<EquipamientoSemifijo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
