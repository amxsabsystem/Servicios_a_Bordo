package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ConfiguracionReporte;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ConfiguracionReporteFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table configuracion_reporte by ConfiguracionReporteFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "configuracionReporte_RSB",
    mappedName   = "ConfiguracionReporte_RSB",
    description  = "ConfiguracionReporteFacadeRemote-Stateless-Session EJB-3.1"
)
public class ConfiguracionReporteFacade extends AbstractFacade<ConfiguracionReporte> implements ConfiguracionReporteFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ConfiguracionReporteFacade() {
		super(ConfiguracionReporte.class);
	}

	@Override
	public ConfiguracionReporte create(ConfiguracionReporte entity) {
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
	public ConfiguracionReporte update(ConfiguracionReporte entity) {
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
	public List<ConfiguracionReporte> findAllLike(ConfiguracionReporte x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ConfiguracionReporte x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdConfiguracionReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.idConfiguracionReporte = :idConfiguracionReporte");
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
			if(x.getStatus() != null){
			    paramAsigned++;
			    sbq.append(" and x.status = :status");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ConfiguracionReporte> nq = em.createQuery(sbq.toString(), ConfiguracionReporte.class);
		
		if(paramAsigned>0){
			if(x.getIdConfiguracionReporte() != null){
			    nq.setParameter("idConfiguracionReporte",x.getIdConfiguracionReporte());
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
			if(x.getStatus() != null){
			    nq.setParameter("status",x.getStatus());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ConfiguracionReporte findByPK_EAGER(Object pk){
        ConfiguracionReporte x = getEntityManager().find(ConfiguracionReporte.class, pk);
        if( x != null){
            if(x.getTipoProductoReporte() !=null && x.getTipoProductoReporte().getIdTipoProductoReporte()!=null){} 
            if(x.getTipoIrregularidad() !=null && x.getTipoIrregularidad().getIdTipoIrregularidad()!=null){} 
            if(x.getCriterioIrregularidad() !=null && x.getCriterioIrregularidad().getIdCriterioIrregularidad()!=null){} 
            if(x.getStatus() !=null && x.getStatus().getIdParametro()!=null){} 

        }
        return x;
    }

	@Override
	public List<ConfiguracionReporte> findAll() {
		TypedQuery<ConfiguracionReporte> nq = em.createNamedQuery("ConfiguracionReporte.findAll", ConfiguracionReporte.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ConfiguracionReporte.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
