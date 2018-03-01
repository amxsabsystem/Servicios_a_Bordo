package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.PerfilUsuario;
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
public interface PerfilUsuarioFacadeRemote {

	PerfilUsuario create(PerfilUsuario entity);

	PerfilUsuario update(PerfilUsuario entity);

	void remove(PerfilUsuario entity);

    void removeByPK(Object pk);

	PerfilUsuario findByPK(Object pk);

    PerfilUsuario findByPK_EAGER(Object pk);

	List<PerfilUsuario> findAllLike(PerfilUsuario entity);

	List<PerfilUsuario> findAll();

	List<PerfilUsuario> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<PerfilUsuario> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
