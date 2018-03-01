package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Proveedor;
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
public interface ProveedorFacadeRemote {

	Proveedor create(Proveedor entity);

	Proveedor update(Proveedor entity);

	void remove(Proveedor entity);

    void removeByPK(Object pk);

	Proveedor findByPK(Object pk);

    Proveedor findByPK_EAGER(Object pk);

	List<Proveedor> findAllLike(Proveedor entity);

	List<Proveedor> findAll();

	List<Proveedor> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Proveedor> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
