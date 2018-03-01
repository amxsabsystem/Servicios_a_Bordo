package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Material;
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
public interface MaterialFacadeRemote {

	Material create(Material entity);

	Material update(Material entity);

	void remove(Material entity);

    void removeByPK(Object pk);

	Material findByPK(Object pk);

    Material findByPK_EAGER(Object pk);

	List<Material> findAllLike(Material entity);

	List<Material> findAll();

	List<Material> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Material> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
