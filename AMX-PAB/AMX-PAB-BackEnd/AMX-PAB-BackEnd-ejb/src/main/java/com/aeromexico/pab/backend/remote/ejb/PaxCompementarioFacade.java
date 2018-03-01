package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.PaxCompementario;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.PaxCompementarioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table pax_compementario by PaxCompementarioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "paxCompementario_RSB",
    mappedName   = "PaxCompementario_RSB",
    description  = "PaxCompementarioFacadeRemote-Stateless-Session EJB-3.1"
)
public class PaxCompementarioFacade extends AbstractFacade<PaxCompementario> implements PaxCompementarioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PaxCompementarioFacade() {
		super(PaxCompementario.class);
	}

	@Override
	public PaxCompementario create(PaxCompementario entity) {
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
	public PaxCompementario update(PaxCompementario entity) {
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
	public List<PaxCompementario> findAllLike(PaxCompementario x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM PaxCompementario x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdPaxComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.idPaxComplementario = :idPaxComplementario");
			}
			if(x.getDetalleTablaComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.detalleTablaComplementario = :detalleTablaComplementario");
			}
			if(x.getModeloAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.modeloAvion = :modeloAvion");
			}
			if(x.getCantidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidad = :cantidad");
			}
			if(x.getNoPasajeros()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.noPasajeros = :noPasajeros");
			}
			if(x.getMaxPasajeros()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.maxPasajeros = :maxPasajeros");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<PaxCompementario> nq = em.createQuery(sbq.toString(), PaxCompementario.class);
		
		if(paramAsigned>0){
			if(x.getIdPaxComplementario() != null){
			    nq.setParameter("idPaxComplementario",x.getIdPaxComplementario());
			}
			if(x.getDetalleTablaComplementario() != null){
			    nq.setParameter("detalleTablaComplementario",x.getDetalleTablaComplementario());
			}
			if(x.getModeloAvion() != null){
			    nq.setParameter("modeloAvion",x.getModeloAvion());
			}
			if(x.getCantidad()  != 0){
			    nq.setParameter("cantidad",x.getCantidad());
			}
			if(x.getNoPasajeros()  != 0){
			    nq.setParameter("noPasajeros",x.getNoPasajeros());
			}
			if(x.getMaxPasajeros()  != 0){
			    nq.setParameter("maxPasajeros",x.getMaxPasajeros());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public PaxCompementario findByPK_EAGER(Object pk){
        PaxCompementario x = getEntityManager().find(PaxCompementario.class, pk);
        if( x != null){
            if(x.getDetalleTablaComplementario() !=null && x.getDetalleTablaComplementario().getIdDetalleTablaComplementario()!=null){} 
            if(x.getModeloAvion() !=null && x.getModeloAvion().getIdModeloAvion()!=null){} 

        }
        return x;
    }

	@Override
	public List<PaxCompementario> findAll() {
		TypedQuery<PaxCompementario> nq = em.createNamedQuery("PaxCompementario.findAll", PaxCompementario.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("PaxCompementario.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
