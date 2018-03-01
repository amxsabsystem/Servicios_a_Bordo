package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ReporteEvidenciaPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ReporteEvidenciaPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table reporte_evidencia_P_K by ReporteEvidenciaPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "reporteEvidenciaPK_RSB",
    mappedName   = "ReporteEvidenciaPK_RSB",
    description  = "ReporteEvidenciaPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class ReporteEvidenciaPKFacade extends AbstractFacade<ReporteEvidenciaPK> implements ReporteEvidenciaPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ReporteEvidenciaPKFacade() {
		super(ReporteEvidenciaPK.class);
	}

	@Override
	public ReporteEvidenciaPK create(ReporteEvidenciaPK entity) {
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
	public ReporteEvidenciaPK update(ReporteEvidenciaPK entity) {
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
	public List<ReporteEvidenciaPK> findAllLike(ReporteEvidenciaPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ReporteEvidenciaPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.idReporte = :idReporte");
			}
			if(x.getIdEvidencia() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEvidencia = :idEvidencia");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ReporteEvidenciaPK> nq = em.createQuery(sbq.toString(), ReporteEvidenciaPK.class);
		
		if(paramAsigned>0){
			if(x.getIdReporte() != null){
			    nq.setParameter("idReporte",x.getIdReporte());
			}
			if(x.getIdEvidencia() != null){
			    nq.setParameter("idEvidencia",x.getIdEvidencia());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ReporteEvidenciaPK findByPK_EAGER(Object pk){
        ReporteEvidenciaPK x = getEntityManager().find(ReporteEvidenciaPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<ReporteEvidenciaPK> findAll() {
		TypedQuery<ReporteEvidenciaPK> nq = em.createNamedQuery("ReporteEvidenciaPK.findAll", ReporteEvidenciaPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ReporteEvidenciaPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
