package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Sobreabordaje;
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
public interface SobreabordajeFacadeRemote {

	Sobreabordaje create(Sobreabordaje entity);

	Sobreabordaje update(Sobreabordaje entity);

	void remove(Sobreabordaje entity);

    void removeByPK(Object pk);

	Sobreabordaje findByPK(Object pk);

    Sobreabordaje findByPK_EAGER(Object pk);

	List<Sobreabordaje> findAllLike(Sobreabordaje entity);

	List<Sobreabordaje> findAll();

	List<Sobreabordaje> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Sobreabordaje> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
