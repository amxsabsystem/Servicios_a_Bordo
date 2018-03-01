package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.OpcionMenuPerfilPK;
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
public interface OpcionMenuPerfilPKFacadeRemote {

	OpcionMenuPerfilPK create(OpcionMenuPerfilPK entity);

	OpcionMenuPerfilPK update(OpcionMenuPerfilPK entity);

	void remove(OpcionMenuPerfilPK entity);

    void removeByPK(Object pk);

	OpcionMenuPerfilPK findByPK(Object pk);

    OpcionMenuPerfilPK findByPK_EAGER(Object pk);

	List<OpcionMenuPerfilPK> findAllLike(OpcionMenuPerfilPK entity);

	List<OpcionMenuPerfilPK> findAll();

	List<OpcionMenuPerfilPK> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<OpcionMenuPerfilPK> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
