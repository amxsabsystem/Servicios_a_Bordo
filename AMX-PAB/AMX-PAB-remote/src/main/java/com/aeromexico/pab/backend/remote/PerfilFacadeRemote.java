package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Perfil;
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
public interface PerfilFacadeRemote {

	Perfil create(Perfil entity);

	Perfil update(Perfil entity);

	void remove(Perfil entity);

    void removeByPK(Object pk);

	Perfil findByPK(Object pk);

    Perfil findByPK_EAGER(Object pk);

	List<Perfil> findAllLike(Perfil entity);

	List<Perfil> findAll();

	List<Perfil> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Perfil> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
