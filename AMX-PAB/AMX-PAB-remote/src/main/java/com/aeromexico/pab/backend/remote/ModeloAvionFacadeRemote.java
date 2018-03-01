package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ModeloAvion;
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
public interface ModeloAvionFacadeRemote {

	ModeloAvion create(ModeloAvion entity);

	ModeloAvion update(ModeloAvion entity);

	void remove(ModeloAvion entity);

    void removeByPK(Object pk);

	ModeloAvion findByPK(Object pk);

    ModeloAvion findByPK_EAGER(Object pk);

	List<ModeloAvion> findAllLike(ModeloAvion entity);

	List<ModeloAvion> findAll();

	List<ModeloAvion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ModeloAvion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
