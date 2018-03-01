package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.CriterioIrregularidad;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CriterioIrregularidadFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table criterio_irregularidad by CriterioIrregularidadFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "criterioIrregularidad_RSB",
    mappedName   = "CriterioIrregularidad_RSB",
    description  = "CriterioIrregularidadFacadeRemote-Stateless-Session EJB-3.1"
)
public class CriterioIrregularidadFacade extends AbstractFacade<CriterioIrregularidad> implements CriterioIrregularidadFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CriterioIrregularidadFacade() {
		super(CriterioIrregularidad.class);
	}

	@Override
	public CriterioIrregularidad create(CriterioIrregularidad entity) {
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
        final List<ConfiguracionReporte> entity_configuracionReporteHasCriterioIrregularidadList =  entity.getConfiguracionReporteHasCriterioIrregularidadList();
        entity.setConfiguracionReporteHasCriterioIrregularidadList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasCriterioIrregularidadList =  entity.getConfiguracionReporteDetalleHasCriterioIrregularidadList();
        entity.setConfiguracionReporteDetalleHasCriterioIrregularidadList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setConfiguracionReporteHasCriterioIrregularidadList(entity_configuracionReporteHasCriterioIrregularidadList);
        entity.setConfiguracionReporteDetalleHasCriterioIrregularidadList(entity_configuracionReporteDetalleHasCriterioIrregularidadList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public CriterioIrregularidad update(CriterioIrregularidad entity) {
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
        final List<ConfiguracionReporte> entity_configuracionReporteHasCriterioIrregularidadList =  entity.getConfiguracionReporteHasCriterioIrregularidadList();
        entity.setConfiguracionReporteHasCriterioIrregularidadList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasCriterioIrregularidadList =  entity.getConfiguracionReporteDetalleHasCriterioIrregularidadList();
        entity.setConfiguracionReporteDetalleHasCriterioIrregularidadList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setConfiguracionReporteHasCriterioIrregularidadList(entity_configuracionReporteHasCriterioIrregularidadList);
        entity.setConfiguracionReporteDetalleHasCriterioIrregularidadList(entity_configuracionReporteDetalleHasCriterioIrregularidadList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<CriterioIrregularidad> findAllLike(CriterioIrregularidad x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM CriterioIrregularidad x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdCriterioIrregularidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.idCriterioIrregularidad = :idCriterioIrregularidad");
			}
			if(x.getDescripcion() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcion = :descripcion");
			}
			if(x.getEstatus() != null){
			    paramAsigned++;
			    sbq.append(" and x.estatus = :estatus");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<CriterioIrregularidad> nq = em.createQuery(sbq.toString(), CriterioIrregularidad.class);
		
		if(paramAsigned>0){
			if(x.getIdCriterioIrregularidad() != null){
			    nq.setParameter("idCriterioIrregularidad",x.getIdCriterioIrregularidad());
			}
			if(x.getDescripcion() != null){
			    nq.setParameter("descripcion",x.getDescripcion());
			}
			if(x.getEstatus() != null){
			    nq.setParameter("estatus",x.getEstatus());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public CriterioIrregularidad findByPK_EAGER(Object pk){
        CriterioIrregularidad x = getEntityManager().find(CriterioIrregularidad.class, pk);
        if( x != null){
            if(x.getEstatus() !=null && x.getEstatus().getIdParametro()!=null){} 
            for(ConfiguracionReporte x_configuracionReporte: x.getConfiguracionReporteHasCriterioIrregularidadList() ) {} 
            for(ConfiguracionReporteDetalle x_configuracionReporteDetalle: x.getConfiguracionReporteDetalleHasCriterioIrregularidadList() ) {} 

        }
        return x;
    }

	@Override
	public List<CriterioIrregularidad> findAll() {
		TypedQuery<CriterioIrregularidad> nq = em.createNamedQuery("CriterioIrregularidad.findAll", CriterioIrregularidad.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("CriterioIrregularidad.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
