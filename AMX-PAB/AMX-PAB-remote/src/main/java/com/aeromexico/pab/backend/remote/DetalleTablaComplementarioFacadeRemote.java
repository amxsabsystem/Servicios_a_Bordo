package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.DetalleTablaComplementario;
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
public interface DetalleTablaComplementarioFacadeRemote {

	DetalleTablaComplementario create(DetalleTablaComplementario entity);

	DetalleTablaComplementario update(DetalleTablaComplementario entity);

	void remove(DetalleTablaComplementario entity);

    void removeByPK(Object pk);

	DetalleTablaComplementario findByPK(Object pk);

    DetalleTablaComplementario findByPK_EAGER(Object pk);

	List<DetalleTablaComplementario> findAllLike(DetalleTablaComplementario entity);

	List<DetalleTablaComplementario> findAll();

	List<DetalleTablaComplementario> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<DetalleTablaComplementario> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
