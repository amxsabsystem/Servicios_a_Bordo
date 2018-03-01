package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AsignacionServicio;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AsignacionServicioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Asignacion_Servicio by AsignacionServicioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "asignacionServicio_RSB",
    mappedName   = "AsignacionServicio_RSB",
    description  = "AsignacionServicioFacadeRemote-Stateless-Session EJB-3.1"
)
public class AsignacionServicioFacade extends AbstractFacade<AsignacionServicio> implements AsignacionServicioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AsignacionServicioFacade() {
		super(AsignacionServicio.class);
	}

	@Override
	public AsignacionServicio create(AsignacionServicio entity) {
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
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasAsignacionServicioList =  entity.getAsignacionServicioDuracionHasAsignacionServicioList();
        entity.setAsignacionServicioDuracionHasAsignacionServicioList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setAsignacionServicioDuracionHasAsignacionServicioList(entity_asignacionServicioDuracionHasAsignacionServicioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public AsignacionServicio update(AsignacionServicio entity) {
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
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasAsignacionServicioList =  entity.getAsignacionServicioDuracionHasAsignacionServicioList();
        entity.setAsignacionServicioDuracionHasAsignacionServicioList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setAsignacionServicioDuracionHasAsignacionServicioList(entity_asignacionServicioDuracionHasAsignacionServicioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<AsignacionServicio> findAllLike(AsignacionServicio x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AsignacionServicio x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAsignacionServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAsignacionServicio = :idAsignacionServicio");
			}
			if(x.getModeloAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.modeloAvion = :modeloAvion");
			}
			if(x.getIdOrigen() != null){
			    paramAsigned++;
			    sbq.append(" and x.idOrigen = :idOrigen");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
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
		
		TypedQuery<AsignacionServicio> nq = em.createQuery(sbq.toString(), AsignacionServicio.class);
		
		if(paramAsigned>0){
			if(x.getIdAsignacionServicio() != null){
			    nq.setParameter("idAsignacionServicio",x.getIdAsignacionServicio());
			}
			if(x.getModeloAvion() != null){
			    nq.setParameter("modeloAvion",x.getModeloAvion());
			}
			if(x.getIdOrigen() != null){
			    nq.setParameter("idOrigen",x.getIdOrigen());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
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
	public AsignacionServicio findByPK_EAGER(Object pk){
        AsignacionServicio x = getEntityManager().find(AsignacionServicio.class, pk);
        if( x != null){
            if(x.getModeloAvion() !=null && x.getModeloAvion().getIdModeloAvion()!=null){} 
            if(x.getIdOrigen() !=null && x.getIdOrigen().getIdParametro()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 
            for(AsignacionServicioDuracion x_asignacionServicioDuracion: x.getAsignacionServicioDuracionHasAsignacionServicioList() ) {} 

        }
        return x;
    }

	@Override
	public List<AsignacionServicio> findAll() {
		TypedQuery<AsignacionServicio> nq = em.createNamedQuery("AsignacionServicio.findAll", AsignacionServicio.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AsignacionServicio.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
