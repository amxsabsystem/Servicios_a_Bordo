package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ComunicadoAreas;
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
public interface ComunicadoAreasFacadeRemote {

	ComunicadoAreas create(ComunicadoAreas entity);

	ComunicadoAreas update(ComunicadoAreas entity);

	void remove(ComunicadoAreas entity);

    void removeByPK(Object pk);

	ComunicadoAreas findByPK(Object pk);

    ComunicadoAreas findByPK_EAGER(Object pk);

	List<ComunicadoAreas> findAllLike(ComunicadoAreas entity);

	List<ComunicadoAreas> findAll();

	List<ComunicadoAreas> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ComunicadoAreas> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
