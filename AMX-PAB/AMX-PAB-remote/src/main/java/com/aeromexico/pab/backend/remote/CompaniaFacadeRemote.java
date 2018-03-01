package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Compania;
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
public interface CompaniaFacadeRemote {

	Compania create(Compania entity);

	Compania update(Compania entity);

	void remove(Compania entity);

    void removeByPK(Object pk);

	Compania findByPK(Object pk);

    Compania findByPK_EAGER(Object pk);

	List<Compania> findAllLike(Compania entity);

	List<Compania> findAll();

	List<Compania> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Compania> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
