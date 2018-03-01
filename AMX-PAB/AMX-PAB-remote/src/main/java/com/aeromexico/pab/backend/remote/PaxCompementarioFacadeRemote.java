package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.PaxCompementario;
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
public interface PaxCompementarioFacadeRemote {

	PaxCompementario create(PaxCompementario entity);

	PaxCompementario update(PaxCompementario entity);

	void remove(PaxCompementario entity);

    void removeByPK(Object pk);

	PaxCompementario findByPK(Object pk);

    PaxCompementario findByPK_EAGER(Object pk);

	List<PaxCompementario> findAllLike(PaxCompementario entity);

	List<PaxCompementario> findAll();

	List<PaxCompementario> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<PaxCompementario> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
