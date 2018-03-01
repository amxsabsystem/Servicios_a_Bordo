package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Usuario;
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
public interface UsuarioFacadeRemote {

	Usuario create(Usuario entity);

	Usuario update(Usuario entity);

	void remove(Usuario entity);

    void removeByPK(Object pk);

	Usuario findByPK(Object pk);

    Usuario findByPK_EAGER(Object pk);

	List<Usuario> findAllLike(Usuario entity);

	List<Usuario> findAll();

	List<Usuario> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Usuario> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
