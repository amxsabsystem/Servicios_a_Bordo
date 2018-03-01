package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Area;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AreaFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table area by AreaFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "area_RSB",
    mappedName   = "Area_RSB",
    description  = "AreaFacadeRemote-Stateless-Session EJB-3.1"
)
public class AreaFacade extends AbstractFacade<Area> implements AreaFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AreaFacade() {
		super(Area.class);
	}

	@Override
	public Area create(Area entity) {
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
        final List<Reporte> entity_reporteHasAreaResponsableList =  entity.getReporteHasAreaResponsableList();
        entity.setReporteHasAreaResponsableList(null);
        final List<ResponsableEstacion> entity_responsableEstacionHasAreaList =  entity.getResponsableEstacionHasAreaList();
        entity.setResponsableEstacionHasAreaList(null);
        final List<Comunicado> entity_comunicadoHasAreaList =  entity.getComunicadoHasAreaList();
        entity.setComunicadoHasAreaList(null);
        final List<Empleado> entity_empleadoHasAreaList =  entity.getEmpleadoHasAreaList();
        entity.setEmpleadoHasAreaList(null);
        final List<ComunicadoAreas> entity_comunicadoAreasHasAreaList =  entity.getComunicadoAreasHasAreaList();
        entity.setComunicadoAreasHasAreaList(null);
        final List<Seguimiento> entity_seguimientoHasAreaResponsableList =  entity.getSeguimientoHasAreaResponsableList();
        entity.setSeguimientoHasAreaResponsableList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setReporteHasAreaResponsableList(entity_reporteHasAreaResponsableList);
        entity.setResponsableEstacionHasAreaList(entity_responsableEstacionHasAreaList);
        entity.setComunicadoHasAreaList(entity_comunicadoHasAreaList);
        entity.setEmpleadoHasAreaList(entity_empleadoHasAreaList);
        entity.setComunicadoAreasHasAreaList(entity_comunicadoAreasHasAreaList);
        entity.setSeguimientoHasAreaResponsableList(entity_seguimientoHasAreaResponsableList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Area update(Area entity) {
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
        final List<Reporte> entity_reporteHasAreaResponsableList =  entity.getReporteHasAreaResponsableList();
        entity.setReporteHasAreaResponsableList(null);
        final List<ResponsableEstacion> entity_responsableEstacionHasAreaList =  entity.getResponsableEstacionHasAreaList();
        entity.setResponsableEstacionHasAreaList(null);
        final List<Comunicado> entity_comunicadoHasAreaList =  entity.getComunicadoHasAreaList();
        entity.setComunicadoHasAreaList(null);
        final List<Empleado> entity_empleadoHasAreaList =  entity.getEmpleadoHasAreaList();
        entity.setEmpleadoHasAreaList(null);
        final List<ComunicadoAreas> entity_comunicadoAreasHasAreaList =  entity.getComunicadoAreasHasAreaList();
        entity.setComunicadoAreasHasAreaList(null);
        final List<Seguimiento> entity_seguimientoHasAreaResponsableList =  entity.getSeguimientoHasAreaResponsableList();
        entity.setSeguimientoHasAreaResponsableList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setReporteHasAreaResponsableList(entity_reporteHasAreaResponsableList);
        entity.setResponsableEstacionHasAreaList(entity_responsableEstacionHasAreaList);
        entity.setComunicadoHasAreaList(entity_comunicadoHasAreaList);
        entity.setEmpleadoHasAreaList(entity_empleadoHasAreaList);
        entity.setComunicadoAreasHasAreaList(entity_comunicadoAreasHasAreaList);
        entity.setSeguimientoHasAreaResponsableList(entity_seguimientoHasAreaResponsableList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Area> findAllLike(Area x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Area x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdArea() != null){
			    paramAsigned++;
			    sbq.append(" and x.idArea = :idArea");
			}
			if(x.getClave() != null){
			    paramAsigned++;
			    sbq.append(" and x.clave = :clave");
			}
			if(x.getNombreEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEs = :nombreEs");
			}
			if(x.getNombreEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEn = :nombreEn");
			}
			if(x.getUrlMultimedia() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlMultimedia = :urlMultimedia");
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
		
		TypedQuery<Area> nq = em.createQuery(sbq.toString(), Area.class);
		
		if(paramAsigned>0){
			if(x.getIdArea() != null){
			    nq.setParameter("idArea",x.getIdArea());
			}
			if(x.getClave() != null){
			    nq.setParameter("clave",x.getClave());
			}
			if(x.getNombreEs() != null){
			    nq.setParameter("nombreEs",x.getNombreEs());
			}
			if(x.getNombreEn() != null){
			    nq.setParameter("nombreEn",x.getNombreEn());
			}
			if(x.getUrlMultimedia() != null){
			    nq.setParameter("urlMultimedia",x.getUrlMultimedia());
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
	public Area findByPK_EAGER(Object pk){
        Area x = getEntityManager().find(Area.class, pk);
        if( x != null){
            for(Reporte x_reporte: x.getReporteHasAreaResponsableList() ) {} 
            for(ResponsableEstacion x_responsableEstacion: x.getResponsableEstacionHasAreaList() ) {} 
            for(Comunicado x_comunicado: x.getComunicadoHasAreaList() ) {} 
            for(Empleado x_empleado: x.getEmpleadoHasAreaList() ) {} 
            for(ComunicadoAreas x_comunicadoAreas: x.getComunicadoAreasHasAreaList() ) {} 
            for(Seguimiento x_seguimiento: x.getSeguimientoHasAreaResponsableList() ) {} 

        }
        return x;
    }

	@Override
	public List<Area> findAll() {
		TypedQuery<Area> nq = em.createNamedQuery("Area.findAll", Area.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Area.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
