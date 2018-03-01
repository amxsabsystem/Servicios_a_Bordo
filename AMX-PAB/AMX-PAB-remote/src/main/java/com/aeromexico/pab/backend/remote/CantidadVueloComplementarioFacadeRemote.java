package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.CantidadVueloComplementario;
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
public interface CantidadVueloComplementarioFacadeRemote {

	CantidadVueloComplementario create(CantidadVueloComplementario entity);

	CantidadVueloComplementario update(CantidadVueloComplementario entity);

	void remove(CantidadVueloComplementario entity);

    void removeByPK(Object pk);

	CantidadVueloComplementario findByPK(Object pk);

    CantidadVueloComplementario findByPK_EAGER(Object pk);

	List<CantidadVueloComplementario> findAllLike(CantidadVueloComplementario entity);

	List<CantidadVueloComplementario> findAll();

	List<CantidadVueloComplementario> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<CantidadVueloComplementario> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
