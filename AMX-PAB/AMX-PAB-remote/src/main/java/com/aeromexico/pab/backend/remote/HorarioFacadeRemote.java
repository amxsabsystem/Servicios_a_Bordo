package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.Horario;
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
public interface HorarioFacadeRemote {

	Horario create(Horario entity);

	Horario update(Horario entity);

	void remove(Horario entity);

    void removeByPK(Object pk);

	Horario findByPK(Object pk);

    Horario findByPK_EAGER(Object pk);

	List<Horario> findAllLike(Horario entity);

	List<Horario> findAll();

	List<Horario> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<Horario> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
