package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Auditable;
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
public interface AuditableFacadeRemote {

	Auditable create(Auditable entity);

	Auditable update(Auditable entity);

	void remove(Auditable entity);

    void removeByPK(Object pk);

	Auditable findByPK(Object pk);

    Auditable findByPK_EAGER(Object pk);

	List<Auditable> findAllLike(Auditable entity);

	List<Auditable> findAll();

	List<Auditable> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Auditable> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
