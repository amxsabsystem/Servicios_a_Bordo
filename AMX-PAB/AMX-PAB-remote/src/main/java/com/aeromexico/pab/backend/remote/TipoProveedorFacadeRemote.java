package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TipoProveedor;
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
public interface TipoProveedorFacadeRemote {

	TipoProveedor create(TipoProveedor entity);

	TipoProveedor update(TipoProveedor entity);

	void remove(TipoProveedor entity);

    void removeByPK(Object pk);

	TipoProveedor findByPK(Object pk);

    TipoProveedor findByPK_EAGER(Object pk);

	List<TipoProveedor> findAllLike(TipoProveedor entity);

	List<TipoProveedor> findAll();

	List<TipoProveedor> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TipoProveedor> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
