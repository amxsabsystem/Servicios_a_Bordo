package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Ciclo;
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
public interface CicloFacadeRemote {

	Ciclo create(Ciclo entity);

	Ciclo update(Ciclo entity);

	void remove(Ciclo entity);

    void removeByPK(Object pk);

	Ciclo findByPK(Object pk);

    Ciclo findByPK_EAGER(Object pk);

	List<Ciclo> findAllLike(Ciclo entity);

	List<Ciclo> findAll();

	List<Ciclo> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Ciclo> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
