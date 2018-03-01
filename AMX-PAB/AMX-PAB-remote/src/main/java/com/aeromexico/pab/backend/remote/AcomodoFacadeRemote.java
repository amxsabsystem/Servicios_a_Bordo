package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Acomodo;
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
public interface AcomodoFacadeRemote {

	Acomodo create(Acomodo entity);

	Acomodo update(Acomodo entity);

	void remove(Acomodo entity);

    void removeByPK(Object pk);

	Acomodo findByPK(Object pk);

    Acomodo findByPK_EAGER(Object pk);

	List<Acomodo> findAllLike(Acomodo entity);

	List<Acomodo> findAll();

	List<Acomodo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Acomodo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
