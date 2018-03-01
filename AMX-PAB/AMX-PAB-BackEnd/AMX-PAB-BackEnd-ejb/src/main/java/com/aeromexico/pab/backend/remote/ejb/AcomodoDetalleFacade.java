package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AcomodoDetalle;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AcomodoDetalleFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table acomodo_detalle by AcomodoDetalleFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "acomodoDetalle_RSB",
    mappedName   = "AcomodoDetalle_RSB",
    description  = "AcomodoDetalleFacadeRemote-Stateless-Session EJB-3.1"
)
public class AcomodoDetalleFacade extends AbstractFacade<AcomodoDetalle> implements AcomodoDetalleFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AcomodoDetalleFacade() {
		super(AcomodoDetalle.class);
	}

	@Override
	public AcomodoDetalle create(AcomodoDetalle entity) {
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
        final List<Acomodo> entity_acomodoHasAcomodoDetalleList =  entity.getAcomodoHasAcomodoDetalleList();
        entity.setAcomodoHasAcomodoDetalleList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setAcomodoHasAcomodoDetalleList(entity_acomodoHasAcomodoDetalleList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public AcomodoDetalle update(AcomodoDetalle entity) {
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
        final List<Acomodo> entity_acomodoHasAcomodoDetalleList =  entity.getAcomodoHasAcomodoDetalleList();
        entity.setAcomodoHasAcomodoDetalleList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setAcomodoHasAcomodoDetalleList(entity_acomodoHasAcomodoDetalleList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<AcomodoDetalle> findAllLike(AcomodoDetalle x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AcomodoDetalle x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAcomodoDetalle() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAcomodoDetalle = :idAcomodoDetalle");
			}
			if(x.getNoRev() != null){
			    paramAsigned++;
			    sbq.append(" and x.noRev = :noRev");
			}
			if(x.getAnioRev()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.anioRev = :anioRev");
			}
			if(x.getUrlAcomodo() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlAcomodo = :urlAcomodo");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
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
		
		TypedQuery<AcomodoDetalle> nq = em.createQuery(sbq.toString(), AcomodoDetalle.class);
		
		if(paramAsigned>0){
			if(x.getIdAcomodoDetalle() != null){
			    nq.setParameter("idAcomodoDetalle",x.getIdAcomodoDetalle());
			}
			if(x.getNoRev() != null){
			    nq.setParameter("noRev",x.getNoRev());
			}
			if(x.getAnioRev()  != 0){
			    nq.setParameter("anioRev",x.getAnioRev());
			}
			if(x.getUrlAcomodo() != null){
			    nq.setParameter("urlAcomodo",x.getUrlAcomodo());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
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
	public AcomodoDetalle findByPK_EAGER(Object pk){
        AcomodoDetalle x = getEntityManager().find(AcomodoDetalle.class, pk);
        if( x != null){
            for(Acomodo x_acomodo: x.getAcomodoHasAcomodoDetalleList() ) {} 

        }
        return x;
    }

	@Override
	public List<AcomodoDetalle> findAll() {
		TypedQuery<AcomodoDetalle> nq = em.createNamedQuery("AcomodoDetalle.findAll", AcomodoDetalle.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AcomodoDetalle.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
