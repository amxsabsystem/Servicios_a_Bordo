package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Pais;
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
public interface PaisFacadeRemote {

	Pais create(Pais entity);

	Pais update(Pais entity);

	void remove(Pais entity);

    void removeByPK(Object pk);

	Pais findByPK(Object pk);

    Pais findByPK_EAGER(Object pk);

	List<Pais> findAllLike(Pais entity);

	List<Pais> findAll();

	List<Pais> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Pais> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
