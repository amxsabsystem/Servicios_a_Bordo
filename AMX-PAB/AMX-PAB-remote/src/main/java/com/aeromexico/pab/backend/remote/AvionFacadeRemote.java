package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Avion;
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
public interface AvionFacadeRemote {

	Avion create(Avion entity);

	Avion update(Avion entity);

	void remove(Avion entity);

    void removeByPK(Object pk);

	Avion findByPK(Object pk);

    Avion findByPK_EAGER(Object pk);

	List<Avion> findAllLike(Avion entity);

	List<Avion> findAll();

	List<Avion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Avion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
