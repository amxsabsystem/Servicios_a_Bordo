package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.TipoIrregularidad;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TipoIrregularidadFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table tipo_irregularidad by TipoIrregularidadFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "tipoIrregularidad_RSB",
    mappedName   = "TipoIrregularidad_RSB",
    description  = "TipoIrregularidadFacadeRemote-Stateless-Session EJB-3.1"
)
public class TipoIrregularidadFacade extends AbstractFacade<TipoIrregularidad> implements TipoIrregularidadFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TipoIrregularidadFacade() {
		super(TipoIrregularidad.class);
	}

	@Override
	public TipoIrregularidad create(TipoIrregularidad entity) {
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
        final List<ConfiguracionReporte> entity_configuracionReporteHasTipoIrregularidadList =  entity.getConfiguracionReporteHasTipoIrregularidadList();
        entity.setConfiguracionReporteHasTipoIrregularidadList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasTipoIrregularidadList =  entity.getConfiguracionReporteDetalleHasTipoIrregularidadList();
        entity.setConfiguracionReporteDetalleHasTipoIrregularidadList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setConfiguracionReporteHasTipoIrregularidadList(entity_configuracionReporteHasTipoIrregularidadList);
        entity.setConfiguracionReporteDetalleHasTipoIrregularidadList(entity_configuracionReporteDetalleHasTipoIrregularidadList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public TipoIrregularidad update(TipoIrregularidad entity) {
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
        final List<ConfiguracionReporte> entity_configuracionReporteHasTipoIrregularidadList =  entity.getConfiguracionReporteHasTipoIrregularidadList();
        entity.setConfiguracionReporteHasTipoIrregularidadList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasTipoIrregularidadList =  entity.getConfiguracionReporteDetalleHasTipoIrregularidadList();
        entity.setConfiguracionReporteDetalleHasTipoIrregularidadList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setConfiguracionReporteHasTipoIrregularidadList(entity_configuracionReporteHasTipoIrregularidadList);
        entity.setConfiguracionReporteDetalleHasTipoIrregularidadList(entity_configuracionReporteDetalleHasTipoIrregularidadList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<TipoIrregularidad> findAllLike(TipoIrregularidad x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM TipoIrregularidad x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTipoIrregularidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoIrregularidad = :idTipoIrregularidad");
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
		
		TypedQuery<TipoIrregularidad> nq = em.createQuery(sbq.toString(), TipoIrregularidad.class);
		
		if(paramAsigned>0){
			if(x.getIdTipoIrregularidad() != null){
			    nq.setParameter("idTipoIrregularidad",x.getIdTipoIrregularidad());
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
	public TipoIrregularidad findByPK_EAGER(Object pk){
        TipoIrregularidad x = getEntityManager().find(TipoIrregularidad.class, pk);
        if( x != null){
            if(x.getEstatus() !=null && x.getEstatus().getIdParametro()!=null){} 
            for(ConfiguracionReporte x_configuracionReporte: x.getConfiguracionReporteHasTipoIrregularidadList() ) {} 
            for(ConfiguracionReporteDetalle x_configuracionReporteDetalle: x.getConfiguracionReporteDetalleHasTipoIrregularidadList() ) {} 

        }
        return x;
    }

	@Override
	public List<TipoIrregularidad> findAll() {
		TypedQuery<TipoIrregularidad> nq = em.createNamedQuery("TipoIrregularidad.findAll", TipoIrregularidad.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("TipoIrregularidad.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
