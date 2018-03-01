package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.EquipamientoSemifijo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.EquipamientoSemifijoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table equipamiento_semifijo by EquipamientoSemifijoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "equipamientoSemifijo_RSB",
    mappedName   = "EquipamientoSemifijo_RSB",
    description  = "EquipamientoSemifijoFacadeRemote-Stateless-Session EJB-3.1"
)
public class EquipamientoSemifijoFacade extends AbstractFacade<EquipamientoSemifijo> implements EquipamientoSemifijoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EquipamientoSemifijoFacade() {
		super(EquipamientoSemifijo.class);
	}

	@Override
	public EquipamientoSemifijo create(EquipamientoSemifijo entity) {
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
	public EquipamientoSemifijo update(EquipamientoSemifijo entity) {
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
	public List<EquipamientoSemifijo> findAllLike(EquipamientoSemifijo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM EquipamientoSemifijo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdEquipamientoSemifijo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEquipamientoSemifijo = :idEquipamientoSemifijo");
			}
			if(x.getAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.avion = :avion");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
			}
			if(x.getCantidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidad = :cantidad");
			}
			if(x.getPeso()  != 0.0){
			    paramAsigned++;
			    sbq.append(" and x.peso = :peso");
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
		
		TypedQuery<EquipamientoSemifijo> nq = em.createQuery(sbq.toString(), EquipamientoSemifijo.class);
		
		if(paramAsigned>0){
			if(x.getIdEquipamientoSemifijo() != null){
			    nq.setParameter("idEquipamientoSemifijo",x.getIdEquipamientoSemifijo());
			}
			if(x.getAvion() != null){
			    nq.setParameter("avion",x.getAvion());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
			}
			if(x.getCantidad()  != 0){
			    nq.setParameter("cantidad",x.getCantidad());
			}
			if(x.getPeso()  != 0.0){
			    nq.setParameter("peso",x.getPeso());
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
	public EquipamientoSemifijo findByPK_EAGER(Object pk){
        EquipamientoSemifijo x = getEntityManager().find(EquipamientoSemifijo.class, pk);
        if( x != null){
            if(x.getAvion() !=null && x.getAvion().getIdAvion()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 

        }
        return x;
    }

	@Override
	public List<EquipamientoSemifijo> findAll() {
		TypedQuery<EquipamientoSemifijo> nq = em.createNamedQuery("EquipamientoSemifijo.findAll", EquipamientoSemifijo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("EquipamientoSemifijo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
