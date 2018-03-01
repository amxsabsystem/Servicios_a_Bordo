package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.TablaAbordamiento;
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
public interface TablaAbordamientoFacadeRemote {

	TablaAbordamiento create(TablaAbordamiento entity);

	TablaAbordamiento update(TablaAbordamiento entity);

	void remove(TablaAbordamiento entity);

    void removeByPK(Object pk);

	TablaAbordamiento findByPK(Object pk);

    TablaAbordamiento findByPK_EAGER(Object pk);

	List<TablaAbordamiento> findAllLike(TablaAbordamiento entity);

	List<TablaAbordamiento> findAll();

	List<TablaAbordamiento> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<TablaAbordamiento> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
