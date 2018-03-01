package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.TipoProveedor;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TipoProveedorFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table tipo_proveedor by TipoProveedorFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "tipoProveedor_RSB",
    mappedName   = "TipoProveedor_RSB",
    description  = "TipoProveedorFacadeRemote-Stateless-Session EJB-3.1"
)
public class TipoProveedorFacade extends AbstractFacade<TipoProveedor> implements TipoProveedorFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TipoProveedorFacade() {
		super(TipoProveedor.class);
	}

	@Override
	public TipoProveedor create(TipoProveedor entity) {
        if(ctx != null){
            final Principal authenticatedRealmUser = ctx.getCallerPrincipal();
            // obtain the caller principal.
            if(authenticatedRealmUser != null){                
                if(entity instanceof AuditableEntity){
                    final Timestamp nowTimeStamp = new Timestamp(System.currentTimeMillis());
                    ((AuditableEntity) entity).setFechaCreo(nowTimeStamp);
                    ((AuditableEntity) entity).setUsuarioCreo(authenticatedRealmUser.getName());
                    ((AuditableEntity) entity).setFechaModifico(nowTimeStamp);
                    ((AuditableEntity) entity).setUsuarioModifico(authenticatedRealmUser.getName());
                }
            }
        }
        final List<ProveedorEstacion> entity_proveedorEstacionList =  entity.getProveedorEstacionList();
        entity.setProveedorEstacionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setProveedorEstacionList(entity_proveedorEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public TipoProveedor update(TipoProveedor entity) {
        if(ctx != null){
            final Principal authenticatedRealmUser = ctx.getCallerPrincipal();
            // obtain the caller principal.
            if(authenticatedRealmUser != null){                
                if(entity instanceof AuditableEntity){
                    ((AuditableEntity) entity).setFechaModifico(new Timestamp(System.currentTimeMillis()));
                    ((AuditableEntity) entity).setUsuarioModifico(authenticatedRealmUser.getName());
                }
            }
        }
        final List<ProveedorEstacion> entity_proveedorEstacionList =  entity.getProveedorEstacionList();
        entity.setProveedorEstacionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setProveedorEstacionList(entity_proveedorEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<TipoProveedor> findAllLike(TipoProveedor x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM TipoProveedor x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTipoProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoProveedor = :idTipoProveedor");
			}
			if(x.getNombreEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEs = :nombreEs");
			}
			if(x.getNombreEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEn = :nombreEn");
			}
			if(x.getStatus() != null){
			    paramAsigned++;
			    sbq.append(" and x.status = :status");
			}
			if(x.getUsuarioCreo() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuarioCreo = :usuarioCreo");
			}
			if(x.getFechaCreo() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaCreo = :fechaCreo");
			}
			if(x.getUsuarioModifico() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuarioModifico = :usuarioModifico");
			}
			if(x.getFechaModifico() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaModifico = :fechaModifico");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<TipoProveedor> nq = em.createQuery(sbq.toString(), TipoProveedor.class);
		
		if(paramAsigned>0){
			if(x.getIdTipoProveedor() != null){
			    nq.setParameter("idTipoProveedor",x.getIdTipoProveedor());
			}
			if(x.getNombreEs() != null){
			    nq.setParameter("nombreEs",x.getNombreEs());
			}
			if(x.getNombreEn() != null){
			    nq.setParameter("nombreEn",x.getNombreEn());
			}
			if(x.getStatus() != null){
			    nq.setParameter("status",x.getStatus());
			}
			if(x.getUsuarioCreo() != null){
			    nq.setParameter("usuarioCreo",x.getUsuarioCreo());
			}
			if(x.getFechaCreo() != null){
			    nq.setParameter("fechaCreo",x.getFechaCreo());
			}
			if(x.getUsuarioModifico() != null){
			    nq.setParameter("usuarioModifico",x.getUsuarioModifico());
			}
			if(x.getFechaModifico() != null){
			    nq.setParameter("fechaModifico",x.getFechaModifico());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public TipoProveedor findByPK_EAGER(Object pk){
        TipoProveedor x = getEntityManager().find(TipoProveedor.class, pk);
        if( x != null){
            for(ProveedorEstacion x_ProveedorEstacion: x.getProveedorEstacionList() ) {} 

        }
        return x;
    }

	@Override
	public List<TipoProveedor> findAll() {
		TypedQuery<TipoProveedor> nq = em.createNamedQuery("TipoProveedor.findAll", TipoProveedor.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("TipoProveedor.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
