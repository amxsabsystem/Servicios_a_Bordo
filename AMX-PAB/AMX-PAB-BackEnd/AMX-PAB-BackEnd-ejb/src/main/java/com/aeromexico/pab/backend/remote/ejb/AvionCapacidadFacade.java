package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AvionCapacidad;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AvionCapacidadFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table avion_capacidad by AvionCapacidadFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "avionCapacidad_RSB",
    mappedName   = "AvionCapacidad_RSB",
    description  = "AvionCapacidadFacadeRemote-Stateless-Session EJB-3.1"
)
public class AvionCapacidadFacade extends AbstractFacade<AvionCapacidad> implements AvionCapacidadFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AvionCapacidadFacade() {
		super(AvionCapacidad.class);
	}

	@Override
	public AvionCapacidad create(AvionCapacidad entity) {
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
	public AvionCapacidad update(AvionCapacidad entity) {
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
	public List<AvionCapacidad> findAllLike(AvionCapacidad x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AvionCapacidad x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getAvionCapacidadPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.avionCapacidadPK = :avionCapacidadPK");
			}
			if(x.getCapacidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.capacidad = :capacidad");
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
			if(x.getAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.avion = :avion");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AvionCapacidad> nq = em.createQuery(sbq.toString(), AvionCapacidad.class);
		
		if(paramAsigned>0){
			if(x.getAvionCapacidadPK() != null){
			    nq.setParameter("avionCapacidadPK",x.getAvionCapacidadPK());
			}
			if(x.getCapacidad()  != 0){
			    nq.setParameter("capacidad",x.getCapacidad());
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
			if(x.getAvion() != null){
			    nq.setParameter("avion",x.getAvion());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AvionCapacidad findByPK_EAGER(Object pk){
        AvionCapacidad x = getEntityManager().find(AvionCapacidad.class, pk);
        if( x != null){
            if(x.getAvion() !=null && x.getAvion().getIdAvion()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 

        }
        return x;
    }

	@Override
	public List<AvionCapacidad> findAll() {
		TypedQuery<AvionCapacidad> nq = em.createNamedQuery("AvionCapacidad.findAll", AvionCapacidad.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AvionCapacidad.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
