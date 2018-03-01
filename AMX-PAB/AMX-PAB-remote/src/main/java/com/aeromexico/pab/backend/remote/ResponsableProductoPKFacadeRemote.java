package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ResponsableProductoPK;
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
public interface ResponsableProductoPKFacadeRemote {

	ResponsableProductoPK create(ResponsableProductoPK entity);

	ResponsableProductoPK update(ResponsableProductoPK entity);

	void remove(ResponsableProductoPK entity);

    void removeByPK(Object pk);

	ResponsableProductoPK findByPK(Object pk);

    ResponsableProductoPK findByPK_EAGER(Object pk);

	List<ResponsableProductoPK> findAllLike(ResponsableProductoPK entity);

	List<ResponsableProductoPK> findAll();

	List<ResponsableProductoPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ResponsableProductoPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
