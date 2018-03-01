package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ActionForm;
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
public interface ActionFormFacadeRemote {

	ActionForm create(ActionForm entity);

	ActionForm update(ActionForm entity);

	void remove(ActionForm entity);

    void removeByPK(Object pk);

	ActionForm findByPK(Object pk);

    ActionForm findByPK_EAGER(Object pk);

	List<ActionForm> findAllLike(ActionForm entity);

	List<ActionForm> findAll();

	List<ActionForm> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ActionForm> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
