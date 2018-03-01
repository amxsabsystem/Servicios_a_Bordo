package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TipoEquipoAvion;
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
public interface TipoEquipoAvionFacadeRemote {

	TipoEquipoAvion create(TipoEquipoAvion entity);

	TipoEquipoAvion update(TipoEquipoAvion entity);

	void remove(TipoEquipoAvion entity);

    void removeByPK(Object pk);

	TipoEquipoAvion findByPK(Object pk);

    TipoEquipoAvion findByPK_EAGER(Object pk);

	List<TipoEquipoAvion> findAllLike(TipoEquipoAvion entity);

	List<TipoEquipoAvion> findAll();

	List<TipoEquipoAvion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TipoEquipoAvion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
