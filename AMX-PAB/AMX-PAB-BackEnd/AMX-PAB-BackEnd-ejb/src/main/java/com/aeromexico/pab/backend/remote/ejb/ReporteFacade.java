package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Reporte;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ReporteFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table reporte by ReporteFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "reporte_RSB",
    mappedName   = "Reporte_RSB",
    description  = "ReporteFacadeRemote-Stateless-Session EJB-3.1"
)
public class ReporteFacade extends AbstractFacade<Reporte> implements ReporteFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ReporteFacade() {
		super(Reporte.class);
	}

	@Override
	public Reporte create(Reporte entity) {
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
        final List<Evidencia> entity_evidenciaHasReporteList =  entity.getEvidenciaHasReporteList();
        entity.setEvidenciaHasReporteList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasReporteList =  entity.getConfiguracionReporteDetalleHasReporteList();
        entity.setConfiguracionReporteDetalleHasReporteList(null);
        final List<Seguimiento> entity_seguimientoHasReporteList =  entity.getSeguimientoHasReporteList();
        entity.setSeguimientoHasReporteList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setEvidenciaHasReporteList(entity_evidenciaHasReporteList);
        entity.setConfiguracionReporteDetalleHasReporteList(entity_configuracionReporteDetalleHasReporteList);
        entity.setSeguimientoHasReporteList(entity_seguimientoHasReporteList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Reporte update(Reporte entity) {
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
        final List<Evidencia> entity_evidenciaHasReporteList =  entity.getEvidenciaHasReporteList();
        entity.setEvidenciaHasReporteList(null);
        final List<ConfiguracionReporteDetalle> entity_configuracionReporteDetalleHasReporteList =  entity.getConfiguracionReporteDetalleHasReporteList();
        entity.setConfiguracionReporteDetalleHasReporteList(null);
        final List<Seguimiento> entity_seguimientoHasReporteList =  entity.getSeguimientoHasReporteList();
        entity.setSeguimientoHasReporteList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setEvidenciaHasReporteList(entity_evidenciaHasReporteList);
        entity.setConfiguracionReporteDetalleHasReporteList(entity_configuracionReporteDetalleHasReporteList);
        entity.setSeguimientoHasReporteList(entity_seguimientoHasReporteList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Reporte> findAllLike(Reporte x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Reporte x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.idReporte = :idReporte");
			}
			if(x.getIdTipoReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoReporte = :idTipoReporte");
			}
			if(x.getVuelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.vuelo = :vuelo");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getAreaResponsable() != null){
			    paramAsigned++;
			    sbq.append(" and x.areaResponsable = :areaResponsable");
			}
			if(x.getTipoProductoReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoProductoReporte = :tipoProductoReporte");
			}
			if(x.getIdResponsableAmx() != null){
			    paramAsigned++;
			    sbq.append(" and x.idResponsableAmx = :idResponsableAmx");
			}
			if(x.getIdResponsableFinalAmx() != null){
			    paramAsigned++;
			    sbq.append(" and x.idResponsableFinalAmx = :idResponsableFinalAmx");
			}
			if(x.getIdResponsableProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idResponsableProveedorEstacion = :idResponsableProveedorEstacion");
			}
			if(x.getIdResponsableProveedorEstacionFinal() != null){
			    paramAsigned++;
			    sbq.append(" and x.idResponsableProveedorEstacionFinal = :idResponsableProveedorEstacionFinal");
			}
			if(x.getIdEstatusReporteActual() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEstatusReporteActual = :idEstatusReporteActual");
			}
			if(x.getClaveReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.claveReporte = :claveReporte");
			}
			if(x.getFechaVuelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaVuelo = :fechaVuelo");
			}
			if(x.getDescripcion() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcion = :descripcion");
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
		
		TypedQuery<Reporte> nq = em.createQuery(sbq.toString(), Reporte.class);
		
		if(paramAsigned>0){
			if(x.getIdReporte() != null){
			    nq.setParameter("idReporte",x.getIdReporte());
			}
			if(x.getIdTipoReporte() != null){
			    nq.setParameter("idTipoReporte",x.getIdTipoReporte());
			}
			if(x.getVuelo() != null){
			    nq.setParameter("vuelo",x.getVuelo());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getAreaResponsable() != null){
			    nq.setParameter("areaResponsable",x.getAreaResponsable());
			}
			if(x.getTipoProductoReporte() != null){
			    nq.setParameter("tipoProductoReporte",x.getTipoProductoReporte());
			}
			if(x.getIdResponsableAmx() != null){
			    nq.setParameter("idResponsableAmx",x.getIdResponsableAmx());
			}
			if(x.getIdResponsableFinalAmx() != null){
			    nq.setParameter("idResponsableFinalAmx",x.getIdResponsableFinalAmx());
			}
			if(x.getIdResponsableProveedorEstacion() != null){
			    nq.setParameter("idResponsableProveedorEstacion",x.getIdResponsableProveedorEstacion());
			}
			if(x.getIdResponsableProveedorEstacionFinal() != null){
			    nq.setParameter("idResponsableProveedorEstacionFinal",x.getIdResponsableProveedorEstacionFinal());
			}
			if(x.getIdEstatusReporteActual() != null){
			    nq.setParameter("idEstatusReporteActual",x.getIdEstatusReporteActual());
			}
			if(x.getClaveReporte() != null){
			    nq.setParameter("claveReporte",x.getClaveReporte());
			}
			if(x.getFechaVuelo() != null){
			    nq.setParameter("fechaVuelo",x.getFechaVuelo());
			}
			if(x.getDescripcion() != null){
			    nq.setParameter("descripcion",x.getDescripcion());
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
	public Reporte findByPK_EAGER(Object pk){
        Reporte x = getEntityManager().find(Reporte.class, pk);
        if( x != null){
            if(x.getIdTipoReporte() !=null && x.getIdTipoReporte().getIdParametro()!=null){} 
            if(x.getVuelo() !=null && x.getVuelo().getIdVuelo()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getAreaResponsable() !=null && x.getAreaResponsable().getIdArea()!=null){} 
            if(x.getTipoProductoReporte() !=null && x.getTipoProductoReporte().getIdTipoProductoReporte()!=null){} 
            if(x.getIdResponsableAmx() !=null && x.getIdResponsableAmx().getIdResponsableEstacion()!=null){} 
            if(x.getIdResponsableFinalAmx() !=null && x.getIdResponsableFinalAmx().getIdResponsableEstacion()!=null){} 
            if(x.getIdResponsableProveedorEstacion() !=null && x.getIdResponsableProveedorEstacion().getIdProveedorEstacion()!=null){} 
            if(x.getIdResponsableProveedorEstacionFinal() !=null && x.getIdResponsableProveedorEstacionFinal().getIdProveedorEstacion()!=null){} 
            if(x.getIdEstatusReporteActual() !=null && x.getIdEstatusReporteActual().getIdParametro()!=null){} 
            for(Evidencia x_evidencia: x.getEvidenciaHasReporteList() ) {} 
            for(ConfiguracionReporteDetalle x_configuracionReporteDetalle: x.getConfiguracionReporteDetalleHasReporteList() ) {} 
            for(Seguimiento x_seguimiento: x.getSeguimientoHasReporteList() ) {} 

        }
        return x;
    }

	@Override
	public List<Reporte> findAll() {
		TypedQuery<Reporte> nq = em.createNamedQuery("Reporte.findAll", Reporte.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Reporte.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
