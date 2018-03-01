package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Comunicado;
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
public interface ComunicadoFacadeRemote {

	Comunicado create(Comunicado entity);

	Comunicado update(Comunicado entity);

	void remove(Comunicado entity);

    void removeByPK(Object pk);

	Comunicado findByPK(Object pk);

    Comunicado findByPK_EAGER(Object pk);

	List<Comunicado> findAllLike(Comunicado entity);

	List<Comunicado> findAll();

	List<Comunicado> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Comunicado> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
