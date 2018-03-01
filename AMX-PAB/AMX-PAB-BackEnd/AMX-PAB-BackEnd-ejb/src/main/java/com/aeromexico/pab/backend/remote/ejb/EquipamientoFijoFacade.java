package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.EquipamientoFijo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.EquipamientoFijoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table equipamiento_fijo by EquipamientoFijoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "equipamientoFijo_RSB",
    mappedName   = "EquipamientoFijo_RSB",
    description  = "EquipamientoFijoFacadeRemote-Stateless-Session EJB-3.1"
)
public class EquipamientoFijoFacade extends AbstractFacade<EquipamientoFijo> implements EquipamientoFijoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EquipamientoFijoFacade() {
		super(EquipamientoFijo.class);
	}

	@Override
	public EquipamientoFijo create(EquipamientoFijo entity) {
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
	public EquipamientoFijo update(EquipamientoFijo entity) {
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
	public List<EquipamientoFijo> findAllLike(EquipamientoFijo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM EquipamientoFijo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdEquipamientoFijo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEquipamientoFijo = :idEquipamientoFijo");
			}
			if(x.getAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.avion = :avion");
			}
			if(x.getNombreEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEs = :nombreEs");
			}
			if(x.getNombreEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEn = :nombreEn");
			}
			if(x.getObservaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.observaciones = :observaciones");
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
		
		TypedQuery<EquipamientoFijo> nq = em.createQuery(sbq.toString(), EquipamientoFijo.class);
		
		if(paramAsigned>0){
			if(x.getIdEquipamientoFijo() != null){
			    nq.setParameter("idEquipamientoFijo",x.getIdEquipamientoFijo());
			}
			if(x.getAvion() != null){
			    nq.setParameter("avion",x.getAvion());
			}
			if(x.getNombreEs() != null){
			    nq.setParameter("nombreEs",x.getNombreEs());
			}
			if(x.getNombreEn() != null){
			    nq.setParameter("nombreEn",x.getNombreEn());
			}
			if(x.getObservaciones() != null){
			    nq.setParameter("observaciones",x.getObservaciones());
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
	public EquipamientoFijo findByPK_EAGER(Object pk){
        EquipamientoFijo x = getEntityManager().find(EquipamientoFijo.class, pk);
        if( x != null){
            if(x.getAvion() !=null && x.getAvion().getIdAvion()!=null){} 

        }
        return x;
    }

	@Override
	public List<EquipamientoFijo> findAll() {
		TypedQuery<EquipamientoFijo> nq = em.createNamedQuery("EquipamientoFijo.findAll", EquipamientoFijo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("EquipamientoFijo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
