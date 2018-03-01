package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AcomodoProductoPK;
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
public interface AcomodoProductoPKFacadeRemote {

	AcomodoProductoPK create(AcomodoProductoPK entity);

	AcomodoProductoPK update(AcomodoProductoPK entity);

	void remove(AcomodoProductoPK entity);

    void removeByPK(Object pk);

	AcomodoProductoPK findByPK(Object pk);

    AcomodoProductoPK findByPK_EAGER(Object pk);

	List<AcomodoProductoPK> findAllLike(AcomodoProductoPK entity);

	List<AcomodoProductoPK> findAll();

	List<AcomodoProductoPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AcomodoProductoPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
