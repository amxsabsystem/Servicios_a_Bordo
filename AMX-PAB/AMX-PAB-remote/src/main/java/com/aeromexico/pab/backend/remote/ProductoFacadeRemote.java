package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Producto;
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
public interface ProductoFacadeRemote {

	Producto create(Producto entity);

	Producto update(Producto entity);

	void remove(Producto entity);

    void removeByPK(Object pk);

	Producto findByPK(Object pk);

    Producto findByPK_EAGER(Object pk);

	List<Producto> findAllLike(Producto entity);

	List<Producto> findAll();

	List<Producto> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Producto> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
