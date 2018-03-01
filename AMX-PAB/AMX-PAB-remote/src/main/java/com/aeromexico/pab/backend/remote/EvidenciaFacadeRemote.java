package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Evidencia;
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
public interface EvidenciaFacadeRemote {

	Evidencia create(Evidencia entity);

	Evidencia update(Evidencia entity);

	void remove(Evidencia entity);

    void removeByPK(Object pk);

	Evidencia findByPK(Object pk);

    Evidencia findByPK_EAGER(Object pk);

	List<Evidencia> findAllLike(Evidencia entity);

	List<Evidencia> findAll();

	List<Evidencia> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Evidencia> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
