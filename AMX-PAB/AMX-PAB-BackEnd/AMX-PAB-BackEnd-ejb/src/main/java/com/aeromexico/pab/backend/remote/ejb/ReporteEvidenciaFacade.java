package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ReporteEvidencia;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ReporteEvidenciaFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table reporte_evidencia by ReporteEvidenciaFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "reporteEvidencia_RSB",
    mappedName   = "ReporteEvidencia_RSB",
    description  = "ReporteEvidenciaFacadeRemote-Stateless-Session EJB-3.1"
)
public class ReporteEvidenciaFacade extends AbstractFacade<ReporteEvidencia> implements ReporteEvidenciaFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ReporteEvidenciaFacade() {
		super(ReporteEvidencia.class);
	}

	@Override
	public ReporteEvidencia create(ReporteEvidencia entity) {
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
	public ReporteEvidencia update(ReporteEvidencia entity) {
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
	public List<ReporteEvidencia> findAllLike(ReporteEvidencia x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ReporteEvidencia x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getReporteEvidenciaPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.reporteEvidenciaPK = :reporteEvidenciaPK");
			}
			if(x.getReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.reporte = :reporte");
			}
			if(x.getEvidencia() != null){
			    paramAsigned++;
			    sbq.append(" and x.evidencia = :evidencia");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ReporteEvidencia> nq = em.createQuery(sbq.toString(), ReporteEvidencia.class);
		
		if(paramAsigned>0){
			if(x.getReporteEvidenciaPK() != null){
			    nq.setParameter("reporteEvidenciaPK",x.getReporteEvidenciaPK());
			}
			if(x.getReporte() != null){
			    nq.setParameter("reporte",x.getReporte());
			}
			if(x.getEvidencia() != null){
			    nq.setParameter("evidencia",x.getEvidencia());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ReporteEvidencia findByPK_EAGER(Object pk){
        ReporteEvidencia x = getEntityManager().find(ReporteEvidencia.class, pk);
        if( x != null){
            if(x.getReporte() !=null && x.getReporte().getIdReporte()!=null){} 
            if(x.getEvidencia() !=null && x.getEvidencia().getIdEvidencia()!=null){} 

        }
        return x;
    }

	@Override
	public List<ReporteEvidencia> findAll() {
		TypedQuery<ReporteEvidencia> nq = em.createNamedQuery("ReporteEvidencia.findAll", ReporteEvidencia.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ReporteEvidencia.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
