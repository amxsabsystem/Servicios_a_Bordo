package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.AvionAudifono;
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
public interface AvionAudifonoFacadeRemote {

	AvionAudifono create(AvionAudifono entity);

	AvionAudifono update(AvionAudifono entity);

	void remove(AvionAudifono entity);

    void removeByPK(Object pk);

	AvionAudifono findByPK(Object pk);

    AvionAudifono findByPK_EAGER(Object pk);

	List<AvionAudifono> findAllLike(AvionAudifono entity);

	List<AvionAudifono> findAll();

	List<AvionAudifono> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<AvionAudifono> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
