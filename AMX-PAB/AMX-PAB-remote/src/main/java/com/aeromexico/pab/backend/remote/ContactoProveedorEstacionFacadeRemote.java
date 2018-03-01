package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.ContactoProveedorEstacion;
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
public interface ContactoProveedorEstacionFacadeRemote {

	ContactoProveedorEstacion create(ContactoProveedorEstacion entity);

	ContactoProveedorEstacion update(ContactoProveedorEstacion entity);

	void remove(ContactoProveedorEstacion entity);

    void removeByPK(Object pk);

	ContactoProveedorEstacion findByPK(Object pk);

    ContactoProveedorEstacion findByPK_EAGER(Object pk);

	List<ContactoProveedorEstacion> findAllLike(ContactoProveedorEstacion entity);

	List<ContactoProveedorEstacion> findAll();

	List<ContactoProveedorEstacion> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<ContactoProveedorEstacion> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
