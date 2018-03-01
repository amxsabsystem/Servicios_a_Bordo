package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Multimedio;
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
public interface MultimedioFacadeRemote {

	Multimedio create(Multimedio entity);

	Multimedio update(Multimedio entity);

	void remove(Multimedio entity);

    void removeByPK(Object pk);

	Multimedio findByPK(Object pk);

    Multimedio findByPK_EAGER(Object pk);

	List<Multimedio> findAllLike(Multimedio entity);

	List<Multimedio> findAll();

	List<Multimedio> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Multimedio> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
