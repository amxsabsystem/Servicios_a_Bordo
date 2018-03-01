package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.CriterioIrregularidad;
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
public interface CriterioIrregularidadFacadeRemote {

	CriterioIrregularidad create(CriterioIrregularidad entity);

	CriterioIrregularidad update(CriterioIrregularidad entity);

	void remove(CriterioIrregularidad entity);

    void removeByPK(Object pk);

	CriterioIrregularidad findByPK(Object pk);

    CriterioIrregularidad findByPK_EAGER(Object pk);

	List<CriterioIrregularidad> findAllLike(CriterioIrregularidad entity);

	List<CriterioIrregularidad> findAll();

	List<CriterioIrregularidad> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<CriterioIrregularidad> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
