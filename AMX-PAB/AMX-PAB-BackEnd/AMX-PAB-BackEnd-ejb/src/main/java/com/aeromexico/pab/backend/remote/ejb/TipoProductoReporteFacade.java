package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.TipoProductoReporte;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TipoProductoReporteFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table tipo_producto_reporte by TipoProductoReporteFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "tipoProductoReporte_RSB",
    mappedName   = "TipoProductoReporte_RSB",
    description  = "TipoProductoReporteFacadeRemote-Stateless-Session EJB-3.1"
)
public class TipoProductoReporteFacade extends AbstractFacade<TipoProductoReporte> implements TipoProductoReporteFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TipoProductoReporteFacade() {
		super(TipoProductoReporte.class);
	}

	@Override
	public TipoProductoReporte create(TipoProductoReporte entity) {
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
        final List<ConfiguracionReporte> entity_configuracionReporteHasTipoProductoReporteList =  entity.getConfiguracionReporteHasTipoProductoReporteList();
        entity.setConfiguracionReporteHasTipoProductoReporteList(null);
        final List<Reporte> entity_reporteHasTipoProductoReporteList =  entity.getReporteHasTipoProductoReporteList();
        entity.setReporteHasTipoProductoReporteList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasTipoProductoReporteList =  entity.getConfiguracionReporteDetalleHasTipoProductoReporteList();
        entity.setConfiguracionReporteDetalleHasTipoProductoReporteList(null);
        final List<ResponsableEstacion> entity_responsableEstacionList =  entity.getResponsableEstacionList();
        entity.setResponsableEstacionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setConfiguracionReporteHasTipoProductoReporteList(entity_configuracionReporteHasTipoProductoReporteList);
        entity.setReporteHasTipoProductoReporteList(entity_reporteHasTipoProductoReporteList);
        entity.setConfiguracionReporteDetalleHasTipoProductoReporteList(entity_configuracionReporteDetalleHasTipoProductoReporteList);
        entity.setResponsableEstacionList(entity_responsableEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public TipoProductoReporte update(TipoProductoReporte entity) {
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
        final List<ConfiguracionReporte> entity_configuracionReporteHasTipoProductoReporteList =  entity.getConfiguracionReporteHasTipoProductoReporteList();
        entity.setConfiguracionReporteHasTipoProductoReporteList(null);
        final List<Reporte> entity_reporteHasTipoProductoReporteList =  entity.getReporteHasTipoProductoReporteList();
        entity.setReporteHasTipoProductoReporteList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasTipoProductoReporteList =  entity.getConfiguracionReporteDetalleHasTipoProductoReporteList();
        entity.setConfiguracionReporteDetalleHasTipoProductoReporteList(null);
        final List<ResponsableEstacion> entity_responsableEstacionList =  entity.getResponsableEstacionList();
        entity.setResponsableEstacionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setConfiguracionReporteHasTipoProductoReporteList(entity_configuracionReporteHasTipoProductoReporteList);
        entity.setReporteHasTipoProductoReporteList(entity_reporteHasTipoProductoReporteList);
        entity.setConfiguracionReporteDetalleHasTipoProductoReporteList(entity_configuracionReporteDetalleHasTipoProductoReporteList);
        entity.setResponsableEstacionList(entity_responsableEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<TipoProductoReporte> findAllLike(TipoProductoReporte x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM TipoProductoReporte x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTipoProductoReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoProductoReporte = :idTipoProductoReporte");
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
		
		TypedQuery<TipoProductoReporte> nq = em.createQuery(sbq.toString(), TipoProductoReporte.class);
		
		if(paramAsigned>0){
			if(x.getIdTipoProductoReporte() != null){
			    nq.setParameter("idTipoProductoReporte",x.getIdTipoProductoReporte());
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
	public TipoProductoReporte findByPK_EAGER(Object pk){
        TipoProductoReporte x = getEntityManager().find(TipoProductoReporte.class, pk);
        if( x != null){
            if(x.getEstatus() !=null && x.getEstatus().getIdParametro()!=null){} 
            for(ConfiguracionReporte x_configuracionReporte: x.getConfiguracionReporteHasTipoProductoReporteList() ) {} 
            for(Reporte x_reporte: x.getReporteHasTipoProductoReporteList() ) {} 
            for(ConfiguracionReporteDetalle x_configuracionReporteDetalle: x.getConfiguracionReporteDetalleHasTipoProductoReporteList() ) {} 
            for(ResponsableEstacion x_ResponsableEstacion: x.getResponsableEstacionList() ) {} 

        }
        return x;
    }

	@Override
	public List<TipoProductoReporte> findAll() {
		TypedQuery<TipoProductoReporte> nq = em.createNamedQuery("TipoProductoReporte.findAll", TipoProductoReporte.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("TipoProductoReporte.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
