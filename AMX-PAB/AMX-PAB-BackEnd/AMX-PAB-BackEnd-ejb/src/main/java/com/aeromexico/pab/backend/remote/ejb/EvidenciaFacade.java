package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Evidencia;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.EvidenciaFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table evidencia by EvidenciaFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "evidencia_RSB",
    mappedName   = "Evidencia_RSB",
    description  = "EvidenciaFacadeRemote-Stateless-Session EJB-3.1"
)
public class EvidenciaFacade extends AbstractFacade<Evidencia> implements EvidenciaFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EvidenciaFacade() {
		super(Evidencia.class);
	}

	@Override
	public Evidencia create(Evidencia entity) {
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
	public Evidencia update(Evidencia entity) {
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
	public List<Evidencia> findAllLike(Evidencia x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Evidencia x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdEvidencia() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEvidencia = :idEvidencia");
			}
			if(x.getMultimedio() != null){
			    paramAsigned++;
			    sbq.append(" and x.multimedio = :multimedio");
			}
			if(x.getSeguimiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.seguimiento = :seguimiento");
			}
			if(x.getReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.reporte = :reporte");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Evidencia> nq = em.createQuery(sbq.toString(), Evidencia.class);
		
		if(paramAsigned>0){
			if(x.getIdEvidencia() != null){
			    nq.setParameter("idEvidencia",x.getIdEvidencia());
			}
			if(x.getMultimedio() != null){
			    nq.setParameter("multimedio",x.getMultimedio());
			}
			if(x.getSeguimiento() != null){
			    nq.setParameter("seguimiento",x.getSeguimiento());
			}
			if(x.getReporte() != null){
			    nq.setParameter("reporte",x.getReporte());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Evidencia findByPK_EAGER(Object pk){
        Evidencia x = getEntityManager().find(Evidencia.class, pk);
        if( x != null){
            if(x.getMultimedio() !=null && x.getMultimedio().getIdMultimedio()!=null){} 
            if(x.getSeguimiento() !=null && x.getSeguimiento().getIdSeguimiento()!=null){} 
            if(x.getReporte() !=null && x.getReporte().getIdReporte()!=null){} 

        }
        return x;
    }

	@Override
	public List<Evidencia> findAll() {
		TypedQuery<Evidencia> nq = em.createNamedQuery("Evidencia.findAll", Evidencia.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Evidencia.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
