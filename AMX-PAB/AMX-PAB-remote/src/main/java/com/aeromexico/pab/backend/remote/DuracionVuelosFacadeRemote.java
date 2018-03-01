package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.DuracionVuelos;
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
public interface DuracionVuelosFacadeRemote {

	DuracionVuelos create(DuracionVuelos entity);

	DuracionVuelos update(DuracionVuelos entity);

	void remove(DuracionVuelos entity);

    void removeByPK(Object pk);

	DuracionVuelos findByPK(Object pk);

    DuracionVuelos findByPK_EAGER(Object pk);

	List<DuracionVuelos> findAllLike(DuracionVuelos entity);

	List<DuracionVuelos> findAll();

	List<DuracionVuelos> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<DuracionVuelos> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
