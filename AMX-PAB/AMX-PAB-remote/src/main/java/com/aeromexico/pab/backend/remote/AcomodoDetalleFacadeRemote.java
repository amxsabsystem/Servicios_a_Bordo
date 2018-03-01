package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AcomodoDetalle;
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
public interface AcomodoDetalleFacadeRemote {

	AcomodoDetalle create(AcomodoDetalle entity);

	AcomodoDetalle update(AcomodoDetalle entity);

	void remove(AcomodoDetalle entity);

    void removeByPK(Object pk);

	AcomodoDetalle findByPK(Object pk);

    AcomodoDetalle findByPK_EAGER(Object pk);

	List<AcomodoDetalle> findAllLike(AcomodoDetalle entity);

	List<AcomodoDetalle> findAll();

	List<AcomodoDetalle> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AcomodoDetalle> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
