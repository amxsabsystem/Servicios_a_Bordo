package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Grafico;
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
public interface GraficoFacadeRemote {

	Grafico create(Grafico entity);

	Grafico update(Grafico entity);

	void remove(Grafico entity);

    void removeByPK(Object pk);

	Grafico findByPK(Object pk);

    Grafico findByPK_EAGER(Object pk);

	List<Grafico> findAllLike(Grafico entity);

	List<Grafico> findAll();

	List<Grafico> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Grafico> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
