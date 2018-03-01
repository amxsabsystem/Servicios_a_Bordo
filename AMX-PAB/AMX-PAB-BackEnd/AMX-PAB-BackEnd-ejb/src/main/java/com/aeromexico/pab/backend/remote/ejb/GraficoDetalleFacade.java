package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.GraficoDetalle;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.GraficoDetalleFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table grafico_detalle by GraficoDetalleFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "graficoDetalle_RSB",
    mappedName   = "GraficoDetalle_RSB",
    description  = "GraficoDetalleFacadeRemote-Stateless-Session EJB-3.1"
)
public class GraficoDetalleFacade extends AbstractFacade<GraficoDetalle> implements GraficoDetalleFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public GraficoDetalleFacade() {
		super(GraficoDetalle.class);
	}

	@Override
	public GraficoDetalle create(GraficoDetalle entity) {
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
	public GraficoDetalle update(GraficoDetalle entity) {
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
	public List<GraficoDetalle> findAllLike(GraficoDetalle x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM GraficoDetalle x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdGraficoDetalle() != null){
			    paramAsigned++;
			    sbq.append(" and x.idGraficoDetalle = :idGraficoDetalle");
			}
			if(x.getGrafico() != null){
			    paramAsigned++;
			    sbq.append(" and x.grafico = :grafico");
			}
			if(x.getNoRev() != null){
			    paramAsigned++;
			    sbq.append(" and x.noRev = :noRev");
			}
			if(x.getAnioRev()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.anioRev = :anioRev");
			}
			if(x.getUrlGrafico() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlGrafico = :urlGrafico");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
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
		
		TypedQuery<GraficoDetalle> nq = em.createQuery(sbq.toString(), GraficoDetalle.class);
		
		if(paramAsigned>0){
			if(x.getIdGraficoDetalle() != null){
			    nq.setParameter("idGraficoDetalle",x.getIdGraficoDetalle());
			}
			if(x.getGrafico() != null){
			    nq.setParameter("grafico",x.getGrafico());
			}
			if(x.getNoRev() != null){
			    nq.setParameter("noRev",x.getNoRev());
			}
			if(x.getAnioRev()  != 0){
			    nq.setParameter("anioRev",x.getAnioRev());
			}
			if(x.getUrlGrafico() != null){
			    nq.setParameter("urlGrafico",x.getUrlGrafico());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
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
	public GraficoDetalle findByPK_EAGER(Object pk){
        GraficoDetalle x = getEntityManager().find(GraficoDetalle.class, pk);
        if( x != null){
            if(x.getGrafico() !=null && x.getGrafico().getIdGrafico()!=null){} 

        }
        return x;
    }

	@Override
	public List<GraficoDetalle> findAll() {
		TypedQuery<GraficoDetalle> nq = em.createNamedQuery("GraficoDetalle.findAll", GraficoDetalle.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("GraficoDetalle.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
