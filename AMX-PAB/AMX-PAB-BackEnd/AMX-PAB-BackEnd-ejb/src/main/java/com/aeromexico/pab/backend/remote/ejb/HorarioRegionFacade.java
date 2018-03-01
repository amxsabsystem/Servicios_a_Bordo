package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.HorarioRegion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.HorarioRegionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Horario_Region by HorarioRegionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "horarioRegion_RSB",
    mappedName   = "HorarioRegion_RSB",
    description  = "HorarioRegionFacadeRemote-Stateless-Session EJB-3.1"
)
public class HorarioRegionFacade extends AbstractFacade<HorarioRegion> implements HorarioRegionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public HorarioRegionFacade() {
		super(HorarioRegion.class);
	}

	@Override
	public HorarioRegion create(HorarioRegion entity) {
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
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasHorarioRegionList =  entity.getAsignacionServicioDuracionHasHorarioRegionList();
        entity.setAsignacionServicioDuracionHasHorarioRegionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setAsignacionServicioDuracionHasHorarioRegionList(entity_asignacionServicioDuracionHasHorarioRegionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public HorarioRegion update(HorarioRegion entity) {
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
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasHorarioRegionList =  entity.getAsignacionServicioDuracionHasHorarioRegionList();
        entity.setAsignacionServicioDuracionHasHorarioRegionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setAsignacionServicioDuracionHasHorarioRegionList(entity_asignacionServicioDuracionHasHorarioRegionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<HorarioRegion> findAllLike(HorarioRegion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM HorarioRegion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdHorarioRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idHorarioRegion = :idHorarioRegion");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
			}
			if(x.getHoraInicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.horaInicio = :horaInicio");
			}
			if(x.getHoraFin() != null){
			    paramAsigned++;
			    sbq.append(" and x.horaFin = :horaFin");
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
		
		TypedQuery<HorarioRegion> nq = em.createQuery(sbq.toString(), HorarioRegion.class);
		
		if(paramAsigned>0){
			if(x.getIdHorarioRegion() != null){
			    nq.setParameter("idHorarioRegion",x.getIdHorarioRegion());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
			}
			if(x.getHoraInicio() != null){
			    nq.setParameter("horaInicio",x.getHoraInicio());
			}
			if(x.getHoraFin() != null){
			    nq.setParameter("horaFin",x.getHoraFin());
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
	public HorarioRegion findByPK_EAGER(Object pk){
        HorarioRegion x = getEntityManager().find(HorarioRegion.class, pk);
        if( x != null){
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 
            for(AsignacionServicioDuracion x_asignacionServicioDuracion: x.getAsignacionServicioDuracionHasHorarioRegionList() ) {} 

        }
        return x;
    }

	@Override
	public List<HorarioRegion> findAll() {
		TypedQuery<HorarioRegion> nq = em.createNamedQuery("HorarioRegion.findAll", HorarioRegion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("HorarioRegion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
