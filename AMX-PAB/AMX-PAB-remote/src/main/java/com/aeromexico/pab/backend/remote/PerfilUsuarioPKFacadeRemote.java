package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.PerfilUsuarioPK;
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
public interface PerfilUsuarioPKFacadeRemote {

	PerfilUsuarioPK create(PerfilUsuarioPK entity);

	PerfilUsuarioPK update(PerfilUsuarioPK entity);

	void remove(PerfilUsuarioPK entity);

    void removeByPK(Object pk);

	PerfilUsuarioPK findByPK(Object pk);

    PerfilUsuarioPK findByPK_EAGER(Object pk);

	List<PerfilUsuarioPK> findAllLike(PerfilUsuarioPK entity);

	List<PerfilUsuarioPK> findAll();

	List<PerfilUsuarioPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<PerfilUsuarioPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
