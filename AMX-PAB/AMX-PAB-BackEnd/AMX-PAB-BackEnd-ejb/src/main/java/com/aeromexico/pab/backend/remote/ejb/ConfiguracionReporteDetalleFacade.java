package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ConfiguracionReporteDetalle;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ConfiguracionReporteDetalleFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table configuracion_reporte_detalle by ConfiguracionReporteDetalleFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "configuracionReporteDetalle_RSB",
    mappedName   = "ConfiguracionReporteDetalle_RSB",
    description  = "ConfiguracionReporteDetalleFacadeRemote-Stateless-Session EJB-3.1"
)
public class ConfiguracionReporteDetalleFacade extends AbstractFacade<ConfiguracionReporteDetalle> implements ConfiguracionReporteDetalleFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ConfiguracionReporteDetalleFacade() {
		super(ConfiguracionReporteDetalle.class);
	}

	@Override
	public ConfiguracionReporteDetalle create(ConfiguracionReporteDetalle entity) {
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
	public ConfiguracionReporteDetalle update(ConfiguracionReporteDetalle entity) {
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
	public List<ConfiguracionReporteDetalle> findAllLike(ConfiguracionReporteDetalle x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ConfiguracionReporteDetalle x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdReporteConfiguracionDetalle() != null){
			    paramAsigned++;
			    sbq.append(" and x.idReporteConfiguracionDetalle = :idReporteConfiguracionDetalle");
			}
			if(x.getReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.reporte = :reporte");
			}
			if(x.getTipoProductoReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoProductoReporte = :tipoProductoReporte");
			}
			if(x.getTipoIrregularidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoIrregularidad = :tipoIrregularidad");
			}
			if(x.getCriterioIrregularidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.criterioIrregularidad = :criterioIrregularidad");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ConfiguracionReporteDetalle> nq = em.createQuery(sbq.toString(), ConfiguracionReporteDetalle.class);
		
		if(paramAsigned>0){
			if(x.getIdReporteConfiguracionDetalle() != null){
			    nq.setParameter("idReporteConfiguracionDetalle",x.getIdReporteConfiguracionDetalle());
			}
			if(x.getReporte() != null){
			    nq.setParameter("reporte",x.getReporte());
			}
			if(x.getTipoProductoReporte() != null){
			    nq.setParameter("tipoProductoReporte",x.getTipoProductoReporte());
			}
			if(x.getTipoIrregularidad() != null){
			    nq.setParameter("tipoIrregularidad",x.getTipoIrregularidad());
			}
			if(x.getCriterioIrregularidad() != null){
			    nq.setParameter("criterioIrregularidad",x.getCriterioIrregularidad());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ConfiguracionReporteDetalle findByPK_EAGER(Object pk){
        ConfiguracionReporteDetalle x = getEntityManager().find(ConfiguracionReporteDetalle.class, pk);
        if( x != null){
            if(x.getReporte() !=null && x.getReporte().getIdReporte()!=null){} 
            if(x.getTipoProductoReporte() !=null && x.getTipoProductoReporte().getIdTipoProductoReporte()!=null){} 
            if(x.getTipoIrregularidad() !=null && x.getTipoIrregularidad().getIdTipoIrregularidad()!=null){} 
            if(x.getCriterioIrregularidad() !=null && x.getCriterioIrregularidad().getIdCriterioIrregularidad()!=null){} 

        }
        return x;
    }

	@Override
	public List<ConfiguracionReporteDetalle> findAll() {
		TypedQuery<ConfiguracionReporteDetalle> nq = em.createNamedQuery("ConfiguracionReporteDetalle.findAll", ConfiguracionReporteDetalle.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ConfiguracionReporteDetalle.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
