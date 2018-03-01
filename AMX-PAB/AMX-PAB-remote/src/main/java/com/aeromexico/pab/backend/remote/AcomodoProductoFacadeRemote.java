package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AcomodoProducto;
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
public interface AcomodoProductoFacadeRemote {

	AcomodoProducto create(AcomodoProducto entity);

	AcomodoProducto update(AcomodoProducto entity);

	void remove(AcomodoProducto entity);

    void removeByPK(Object pk);

	AcomodoProducto findByPK(Object pk);

    AcomodoProducto findByPK_EAGER(Object pk);

	List<AcomodoProducto> findAllLike(AcomodoProducto entity);

	List<AcomodoProducto> findAll();

	List<AcomodoProducto> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AcomodoProducto> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
