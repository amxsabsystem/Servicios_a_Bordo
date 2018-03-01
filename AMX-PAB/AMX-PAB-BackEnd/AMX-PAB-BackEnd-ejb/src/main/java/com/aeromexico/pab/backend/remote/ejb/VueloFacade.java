package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Vuelo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.VueloFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table vuelo by VueloFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "vuelo_RSB",
    mappedName   = "Vuelo_RSB",
    description  = "VueloFacadeRemote-Stateless-Session EJB-3.1"
)
public class VueloFacade extends AbstractFacade<Vuelo> implements VueloFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public VueloFacade() {
		super(Vuelo.class);
	}

	@Override
	public Vuelo create(Vuelo entity) {
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
        final List<Reporte> entity_reporteHasVueloList =  entity.getReporteHasVueloList();
        entity.setReporteHasVueloList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setReporteHasVueloList(entity_reporteHasVueloList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Vuelo update(Vuelo entity) {
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
        final List<Reporte> entity_reporteHasVueloList =  entity.getReporteHasVueloList();
        entity.setReporteHasVueloList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setReporteHasVueloList(entity_reporteHasVueloList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Vuelo> findAllLike(Vuelo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Vuelo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdVuelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idVuelo = :idVuelo");
			}
			if(x.getNumeroVuelo()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.numeroVuelo = :numeroVuelo");
			}
			if(x.getEstacionOrigen() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacionOrigen = :estacionOrigen");
			}
			if(x.getEstacionDestino() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacionDestino = :estacionDestino");
			}
			if(x.getIdTipoCabina() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoCabina = :idTipoCabina");
			}
			if(x.getCompania() != null){
			    paramAsigned++;
			    sbq.append(" and x.compania = :compania");
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
		
		TypedQuery<Vuelo> nq = em.createQuery(sbq.toString(), Vuelo.class);
		
		if(paramAsigned>0){
			if(x.getIdVuelo() != null){
			    nq.setParameter("idVuelo",x.getIdVuelo());
			}
			if(x.getNumeroVuelo()  != 0){
			    nq.setParameter("numeroVuelo",x.getNumeroVuelo());
			}
			if(x.getEstacionOrigen() != null){
			    nq.setParameter("estacionOrigen",x.getEstacionOrigen());
			}
			if(x.getEstacionDestino() != null){
			    nq.setParameter("estacionDestino",x.getEstacionDestino());
			}
			if(x.getIdTipoCabina() != null){
			    nq.setParameter("idTipoCabina",x.getIdTipoCabina());
			}
			if(x.getCompania() != null){
			    nq.setParameter("compania",x.getCompania());
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
	public Vuelo findByPK_EAGER(Object pk){
        Vuelo x = getEntityManager().find(Vuelo.class, pk);
        if( x != null){
            if(x.getEstacionOrigen() !=null && x.getEstacionOrigen().getIdEstacion()!=null){} 
            if(x.getEstacionDestino() !=null && x.getEstacionDestino().getIdEstacion()!=null){} 
            if(x.getIdTipoCabina() !=null && x.getIdTipoCabina().getIdParametro()!=null){} 
            if(x.getCompania() !=null && x.getCompania().getIdCompania()!=null){} 
            for(Reporte x_reporte: x.getReporteHasVueloList() ) {} 

        }
        return x;
    }

	@Override
	public List<Vuelo> findAll() {
		TypedQuery<Vuelo> nq = em.createNamedQuery("Vuelo.findAll", Vuelo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Vuelo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
