package com.aeromexico.pab.backend.remote;

import com.aeromexico.pab.entity.BitacoraComunicado;
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
public interface BitacoraComunicadoFacadeRemote {

	BitacoraComunicado create(BitacoraComunicado entity);

	BitacoraComunicado update(BitacoraComunicado entity);

	void remove(BitacoraComunicado entity);

    void removeByPK(Object pk);

	BitacoraComunicado findByPK(Object pk);

    BitacoraComunicado findByPK_EAGER(Object pk);

	List<BitacoraComunicado> findAllLike(BitacoraComunicado entity);

	List<BitacoraComunicado> findAll();

	List<BitacoraComunicado> findRange(int[] range);

	Long count();

	Long countAll();

    PaginatedResult<BitacoraComunicado> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters);

}
