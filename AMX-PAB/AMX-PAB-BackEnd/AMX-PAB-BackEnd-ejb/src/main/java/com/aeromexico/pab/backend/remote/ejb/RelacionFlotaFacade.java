package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.RelacionFlota;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.RelacionFlotaFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table relacion_flota by RelacionFlotaFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "relacionFlota_RSB",
    mappedName   = "RelacionFlota_RSB",
    description  = "RelacionFlotaFacadeRemote-Stateless-Session EJB-3.1"
)
public class RelacionFlotaFacade extends AbstractFacade<RelacionFlota> implements RelacionFlotaFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RelacionFlotaFacade() {
		super(RelacionFlota.class);
	}

	@Override
	public RelacionFlota create(RelacionFlota entity) {
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

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public RelacionFlota update(RelacionFlota entity) {
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

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<RelacionFlota> findAllLike(RelacionFlota x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM RelacionFlota x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdRelacionFlota() != null){
			    paramAsigned++;
			    sbq.append(" and x.idRelacionFlota = :idRelacionFlota");
			}
			if(x.getRevision()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.revision = :revision");
			}
			if(x.getFecha() != null){
			    paramAsigned++;
			    sbq.append(" and x.fecha = :fecha");
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
		
		TypedQuery<RelacionFlota> nq = em.createQuery(sbq.toString(), RelacionFlota.class);
		
		if(paramAsigned>0){
			if(x.getIdRelacionFlota() != null){
			    nq.setParameter("idRelacionFlota",x.getIdRelacionFlota());
			}
			if(x.getRevision()  != 0){
			    nq.setParameter("revision",x.getRevision());
			}
			if(x.getFecha() != null){
			    nq.setParameter("fecha",x.getFecha());
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
	public RelacionFlota findByPK_EAGER(Object pk){
        RelacionFlota x = getEntityManager().find(RelacionFlota.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<RelacionFlota> findAll() {
		TypedQuery<RelacionFlota> nq = em.createNamedQuery("RelacionFlota.findAll", RelacionFlota.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("RelacionFlota.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
