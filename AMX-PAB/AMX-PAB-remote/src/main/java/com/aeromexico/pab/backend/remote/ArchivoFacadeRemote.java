package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Archivo;
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
public interface ArchivoFacadeRemote {

	Archivo create(Archivo entity);

	Archivo update(Archivo entity);

	void remove(Archivo entity);

    void removeByPK(Object pk);

	Archivo findByPK(Object pk);

    Archivo findByPK_EAGER(Object pk);

	List<Archivo> findAllLike(Archivo entity);

	List<Archivo> findAll();

	List<Archivo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Archivo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
