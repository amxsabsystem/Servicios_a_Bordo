package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ComunicadoProveedorEstacion;
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
public interface ComunicadoProveedorEstacionFacadeRemote {

	ComunicadoProveedorEstacion create(ComunicadoProveedorEstacion entity);

	ComunicadoProveedorEstacion update(ComunicadoProveedorEstacion entity);

	void remove(ComunicadoProveedorEstacion entity);

    void removeByPK(Object pk);

	ComunicadoProveedorEstacion findByPK(Object pk);

    ComunicadoProveedorEstacion findByPK_EAGER(Object pk);

	List<ComunicadoProveedorEstacion> findAllLike(ComunicadoProveedorEstacion entity);

	List<ComunicadoProveedorEstacion> findAll();

	List<ComunicadoProveedorEstacion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ComunicadoProveedorEstacion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
