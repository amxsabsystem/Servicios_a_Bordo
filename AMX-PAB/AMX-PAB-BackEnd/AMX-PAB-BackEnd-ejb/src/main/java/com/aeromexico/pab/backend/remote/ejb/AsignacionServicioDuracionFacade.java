package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AsignacionServicioDuracion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AsignacionServicioDuracionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Asignacion_Servicio_Duracion by AsignacionServicioDuracionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "asignacionServicioDuracion_RSB",
    mappedName   = "AsignacionServicioDuracion_RSB",
    description  = "AsignacionServicioDuracionFacadeRemote-Stateless-Session EJB-3.1"
)
public class AsignacionServicioDuracionFacade extends AbstractFacade<AsignacionServicioDuracion> implements AsignacionServicioDuracionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AsignacionServicioDuracionFacade() {
		super(AsignacionServicioDuracion.class);
	}

	@Override
	public AsignacionServicioDuracion create(AsignacionServicioDuracion entity) {
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
	public AsignacionServicioDuracion update(AsignacionServicioDuracion entity) {
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
	public List<AsignacionServicioDuracion> findAllLike(AsignacionServicioDuracion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AsignacionServicioDuracion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAsignacionServicioDuracion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAsignacionServicioDuracion = :idAsignacionServicioDuracion");
			}
			if(x.getCodigoServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.codigoServicio = :codigoServicio");
			}
			if(x.getDuracionVuelos() != null){
			    paramAsigned++;
			    sbq.append(" and x.duracionVuelos = :duracionVuelos");
			}
			if(x.getAsignacionServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.asignacionServicio = :asignacionServicio");
			}
			if(x.getHorarioRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.horarioRegion = :horarioRegion");
			}
			if(x.getOrden() != null){
			    paramAsigned++;
			    sbq.append(" and x.orden = :orden");
			}
			if(x.getNoSecuencia() != null){
			    paramAsigned++;
			    sbq.append(" and x.noSecuencia = :noSecuencia");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AsignacionServicioDuracion> nq = em.createQuery(sbq.toString(), AsignacionServicioDuracion.class);
		
		if(paramAsigned>0){
			if(x.getIdAsignacionServicioDuracion() != null){
			    nq.setParameter("idAsignacionServicioDuracion",x.getIdAsignacionServicioDuracion());
			}
			if(x.getCodigoServicio() != null){
			    nq.setParameter("codigoServicio",x.getCodigoServicio());
			}
			if(x.getDuracionVuelos() != null){
			    nq.setParameter("duracionVuelos",x.getDuracionVuelos());
			}
			if(x.getAsignacionServicio() != null){
			    nq.setParameter("asignacionServicio",x.getAsignacionServicio());
			}
			if(x.getHorarioRegion() != null){
			    nq.setParameter("horarioRegion",x.getHorarioRegion());
			}
			if(x.getOrden() != null){
			    nq.setParameter("orden",x.getOrden());
			}
			if(x.getNoSecuencia() != null){
			    nq.setParameter("noSecuencia",x.getNoSecuencia());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AsignacionServicioDuracion findByPK_EAGER(Object pk){
        AsignacionServicioDuracion x = getEntityManager().find(AsignacionServicioDuracion.class, pk);
        if( x != null){
            if(x.getCodigoServicio() !=null && x.getCodigoServicio().getIdCodigoServicio()!=null){} 
            if(x.getDuracionVuelos() !=null && x.getDuracionVuelos().getIdDuracionVuelos()!=null){} 
            if(x.getAsignacionServicio() !=null && x.getAsignacionServicio().getIdAsignacionServicio()!=null){} 
            if(x.getHorarioRegion() !=null && x.getHorarioRegion().getIdHorarioRegion()!=null){} 

        }
        return x;
    }

	@Override
	public List<AsignacionServicioDuracion> findAll() {
		TypedQuery<AsignacionServicioDuracion> nq = em.createNamedQuery("AsignacionServicioDuracion.findAll", AsignacionServicioDuracion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AsignacionServicioDuracion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
