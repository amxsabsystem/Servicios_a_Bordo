package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AvionSistemaEntretenimiento;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AvionSistemaEntretenimientoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table avion_sistema_entretenimiento by AvionSistemaEntretenimientoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "avionSistemaEntretenimiento_RSB",
    mappedName   = "AvionSistemaEntretenimiento_RSB",
    description  = "AvionSistemaEntretenimientoFacadeRemote-Stateless-Session EJB-3.1"
)
public class AvionSistemaEntretenimientoFacade extends AbstractFacade<AvionSistemaEntretenimiento> implements AvionSistemaEntretenimientoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AvionSistemaEntretenimientoFacade() {
		super(AvionSistemaEntretenimiento.class);
	}

	@Override
	public AvionSistemaEntretenimiento create(AvionSistemaEntretenimiento entity) {
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
	public AvionSistemaEntretenimiento update(AvionSistemaEntretenimiento entity) {
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
	public List<AvionSistemaEntretenimiento> findAllLike(AvionSistemaEntretenimiento x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AvionSistemaEntretenimiento x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getAvionSistemaEntretenimientoPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.avionSistemaEntretenimientoPK = :avionSistemaEntretenimientoPK");
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
			if(x.getSistemaEntretenimiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.sistemaEntretenimiento = :sistemaEntretenimiento");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AvionSistemaEntretenimiento> nq = em.createQuery(sbq.toString(), AvionSistemaEntretenimiento.class);
		
		if(paramAsigned>0){
			if(x.getAvionSistemaEntretenimientoPK() != null){
			    nq.setParameter("avionSistemaEntretenimientoPK",x.getAvionSistemaEntretenimientoPK());
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
			if(x.getSistemaEntretenimiento() != null){
			    nq.setParameter("sistemaEntretenimiento",x.getSistemaEntretenimiento());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AvionSistemaEntretenimiento findByPK_EAGER(Object pk){
        AvionSistemaEntretenimiento x = getEntityManager().find(AvionSistemaEntretenimiento.class, pk);
        if( x != null){
            if(x.getAvion() !=null && x.getAvion().getIdAvion()!=null){} 
            if(x.getSistemaEntretenimiento() !=null && x.getSistemaEntretenimiento().getIdSistemaEntretenimiento()!=null){} 

        }
        return x;
    }

	@Override
	public List<AvionSistemaEntretenimiento> findAll() {
		TypedQuery<AvionSistemaEntretenimiento> nq = em.createNamedQuery("AvionSistemaEntretenimiento.findAll", AvionSistemaEntretenimiento.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AvionSistemaEntretenimiento.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
