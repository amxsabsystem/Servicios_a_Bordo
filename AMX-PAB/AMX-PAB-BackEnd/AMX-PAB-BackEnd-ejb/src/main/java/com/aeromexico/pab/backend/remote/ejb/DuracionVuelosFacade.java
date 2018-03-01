package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.DuracionVuelos;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.DuracionVuelosFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Duracion_vuelos by DuracionVuelosFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "duracionVuelos_RSB",
    mappedName   = "DuracionVuelos_RSB",
    description  = "DuracionVuelosFacadeRemote-Stateless-Session EJB-3.1"
)
public class DuracionVuelosFacade extends AbstractFacade<DuracionVuelos> implements DuracionVuelosFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DuracionVuelosFacade() {
		super(DuracionVuelos.class);
	}

	@Override
	public DuracionVuelos create(DuracionVuelos entity) {
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
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasDuracionVuelosList =  entity.getAsignacionServicioDuracionHasDuracionVuelosList();
        entity.setAsignacionServicioDuracionHasDuracionVuelosList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setAsignacionServicioDuracionHasDuracionVuelosList(entity_asignacionServicioDuracionHasDuracionVuelosList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public DuracionVuelos update(DuracionVuelos entity) {
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
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasDuracionVuelosList =  entity.getAsignacionServicioDuracionHasDuracionVuelosList();
        entity.setAsignacionServicioDuracionHasDuracionVuelosList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setAsignacionServicioDuracionHasDuracionVuelosList(entity_asignacionServicioDuracionHasDuracionVuelosList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<DuracionVuelos> findAllLike(DuracionVuelos x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM DuracionVuelos x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdDuracionVuelos() != null){
			    paramAsigned++;
			    sbq.append(" and x.idDuracionVuelos = :idDuracionVuelos");
			}
			if(x.getTiempoInicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.tiempoInicio = :tiempoInicio");
			}
			if(x.getTiempoFin() != null){
			    paramAsigned++;
			    sbq.append(" and x.tiempoFin = :tiempoFin");
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
		
		TypedQuery<DuracionVuelos> nq = em.createQuery(sbq.toString(), DuracionVuelos.class);
		
		if(paramAsigned>0){
			if(x.getIdDuracionVuelos() != null){
			    nq.setParameter("idDuracionVuelos",x.getIdDuracionVuelos());
			}
			if(x.getTiempoInicio() != null){
			    nq.setParameter("tiempoInicio",x.getTiempoInicio());
			}
			if(x.getTiempoFin() != null){
			    nq.setParameter("tiempoFin",x.getTiempoFin());
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
	public DuracionVuelos findByPK_EAGER(Object pk){
        DuracionVuelos x = getEntityManager().find(DuracionVuelos.class, pk);
        if( x != null){
            for(AsignacionServicioDuracion x_asignacionServicioDuracion: x.getAsignacionServicioDuracionHasDuracionVuelosList() ) {} 

        }
        return x;
    }

	@Override
	public List<DuracionVuelos> findAll() {
		TypedQuery<DuracionVuelos> nq = em.createNamedQuery("DuracionVuelos.findAll", DuracionVuelos.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("DuracionVuelos.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
