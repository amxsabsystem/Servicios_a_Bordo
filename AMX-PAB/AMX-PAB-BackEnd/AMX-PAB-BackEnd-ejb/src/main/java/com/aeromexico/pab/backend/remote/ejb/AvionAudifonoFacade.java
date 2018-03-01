package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AvionAudifono;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AvionAudifonoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table avion_audifono by AvionAudifonoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "avionAudifono_RSB",
    mappedName   = "AvionAudifono_RSB",
    description  = "AvionAudifonoFacadeRemote-Stateless-Session EJB-3.1"
)
public class AvionAudifonoFacade extends AbstractFacade<AvionAudifono> implements AvionAudifonoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AvionAudifonoFacade() {
		super(AvionAudifono.class);
	}

	@Override
	public AvionAudifono create(AvionAudifono entity) {
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
	public AvionAudifono update(AvionAudifono entity) {
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
	public List<AvionAudifono> findAllLike(AvionAudifono x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AvionAudifono x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAvionAudifono() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAvionAudifono = :idAvionAudifono");
			}
			if(x.getAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.avion = :avion");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
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
		
		TypedQuery<AvionAudifono> nq = em.createQuery(sbq.toString(), AvionAudifono.class);
		
		if(paramAsigned>0){
			if(x.getIdAvionAudifono() != null){
			    nq.setParameter("idAvionAudifono",x.getIdAvionAudifono());
			}
			if(x.getAvion() != null){
			    nq.setParameter("avion",x.getAvion());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
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
	public AvionAudifono findByPK_EAGER(Object pk){
        AvionAudifono x = getEntityManager().find(AvionAudifono.class, pk);
        if( x != null){
            if(x.getAvion() !=null && x.getAvion().getIdAvion()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 

        }
        return x;
    }

	@Override
	public List<AvionAudifono> findAll() {
		TypedQuery<AvionAudifono> nq = em.createNamedQuery("AvionAudifono.findAll", AvionAudifono.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AvionAudifono.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
