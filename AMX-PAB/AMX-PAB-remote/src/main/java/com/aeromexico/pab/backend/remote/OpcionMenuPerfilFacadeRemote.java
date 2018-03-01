package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.OpcionMenuPerfil;
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
public interface OpcionMenuPerfilFacadeRemote {

	OpcionMenuPerfil create(OpcionMenuPerfil entity);

	OpcionMenuPerfil update(OpcionMenuPerfil entity);

	void remove(OpcionMenuPerfil entity);

    void removeByPK(Object pk);

	OpcionMenuPerfil findByPK(Object pk);

    OpcionMenuPerfil findByPK_EAGER(Object pk);

	List<OpcionMenuPerfil> findAllLike(OpcionMenuPerfil entity);

	List<OpcionMenuPerfil> findAll();

	List<OpcionMenuPerfil> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<OpcionMenuPerfil> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
