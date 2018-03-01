package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Potencial;
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
public interface PotencialFacadeRemote {

	Potencial create(Potencial entity);

	Potencial update(Potencial entity);

	void remove(Potencial entity);

    void removeByPK(Object pk);

	Potencial findByPK(Object pk);

    Potencial findByPK_EAGER(Object pk);

	List<Potencial> findAllLike(Potencial entity);

	List<Potencial> findAll();

	List<Potencial> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Potencial> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
