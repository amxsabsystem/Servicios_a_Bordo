package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Matriz;
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
public interface MatrizFacadeRemote {

	Matriz create(Matriz entity);

	Matriz update(Matriz entity);

	void remove(Matriz entity);

    void removeByPK(Object pk);

	Matriz findByPK(Object pk);

    Matriz findByPK_EAGER(Object pk);

	List<Matriz> findAllLike(Matriz entity);

	List<Matriz> findAll();

	List<Matriz> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Matriz> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
