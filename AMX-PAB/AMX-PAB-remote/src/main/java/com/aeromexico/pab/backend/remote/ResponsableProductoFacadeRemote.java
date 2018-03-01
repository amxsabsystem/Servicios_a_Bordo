package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ResponsableProducto;
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
public interface ResponsableProductoFacadeRemote {

	ResponsableProducto create(ResponsableProducto entity);

	ResponsableProducto update(ResponsableProducto entity);

	void remove(ResponsableProducto entity);

    void removeByPK(Object pk);

	ResponsableProducto findByPK(Object pk);

    ResponsableProducto findByPK_EAGER(Object pk);

	List<ResponsableProducto> findAllLike(ResponsableProducto entity);

	List<ResponsableProducto> findAll();

	List<ResponsableProducto> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ResponsableProducto> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
