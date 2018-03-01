package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.OpcionMenu;
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
public interface OpcionMenuFacadeRemote {

	OpcionMenu create(OpcionMenu entity);

	OpcionMenu update(OpcionMenu entity);

	void remove(OpcionMenu entity);

    void removeByPK(Object pk);

	OpcionMenu findByPK(Object pk);

    OpcionMenu findByPK_EAGER(Object pk);

	List<OpcionMenu> findAllLike(OpcionMenu entity);

	List<OpcionMenu> findAll();

	List<OpcionMenu> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<OpcionMenu> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
