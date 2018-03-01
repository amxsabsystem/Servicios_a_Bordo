package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ResponsableEstacion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ResponsableEstacionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table responsable_estacion by ResponsableEstacionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "responsableEstacion_RSB",
    mappedName   = "ResponsableEstacion_RSB",
    description  = "ResponsableEstacionFacadeRemote-Stateless-Session EJB-3.1"
)
public class ResponsableEstacionFacade extends AbstractFacade<ResponsableEstacion> implements ResponsableEstacionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ResponsableEstacionFacade() {
		super(ResponsableEstacion.class);
	}

	@Override
	public ResponsableEstacion create(ResponsableEstacion entity) {
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
        final List<Reporte> entity_reporteHasIdResponsableAmxList =  entity.getReporteHasIdResponsableAmxList();
        entity.setReporteHasIdResponsableAmxList(null);
        final List<Reporte> entity_reporteHasIdResponsableFinalAmxList =  entity.getReporteHasIdResponsableFinalAmxList();
        entity.setReporteHasIdResponsableFinalAmxList(null);
        final List<Seguimiento> entity_seguimientoHasResponsableEstacionList =  entity.getSeguimientoHasResponsableEstacionList();
        entity.setSeguimientoHasResponsableEstacionList(null);
        final List<TipoProductoReporte> entity_tipoProductoReporteList =  entity.getTipoProductoReporteList();
        entity.setTipoProductoReporteList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setReporteHasIdResponsableAmxList(entity_reporteHasIdResponsableAmxList);
        entity.setReporteHasIdResponsableFinalAmxList(entity_reporteHasIdResponsableFinalAmxList);
        entity.setSeguimientoHasResponsableEstacionList(entity_seguimientoHasResponsableEstacionList);
        entity.setTipoProductoReporteList(entity_tipoProductoReporteList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public ResponsableEstacion update(ResponsableEstacion entity) {
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
        final List<Reporte> entity_reporteHasIdResponsableAmxList =  entity.getReporteHasIdResponsableAmxList();
        entity.setReporteHasIdResponsableAmxList(null);
        final List<Reporte> entity_reporteHasIdResponsableFinalAmxList =  entity.getReporteHasIdResponsableFinalAmxList();
        entity.setReporteHasIdResponsableFinalAmxList(null);
        final List<Seguimiento> entity_seguimientoHasResponsableEstacionList =  entity.getSeguimientoHasResponsableEstacionList();
        entity.setSeguimientoHasResponsableEstacionList(null);
        final List<TipoProductoReporte> entity_tipoProductoReporteList =  entity.getTipoProductoReporteList();
        entity.setTipoProductoReporteList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setReporteHasIdResponsableAmxList(entity_reporteHasIdResponsableAmxList);
        entity.setReporteHasIdResponsableFinalAmxList(entity_reporteHasIdResponsableFinalAmxList);
        entity.setSeguimientoHasResponsableEstacionList(entity_seguimientoHasResponsableEstacionList);
        entity.setTipoProductoReporteList(entity_tipoProductoReporteList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<ResponsableEstacion> findAllLike(ResponsableEstacion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ResponsableEstacion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdResponsableEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idResponsableEstacion = :idResponsableEstacion");
			}
			if(x.getEmpleado() != null){
			    paramAsigned++;
			    sbq.append(" and x.empleado = :empleado");
			}
			if(x.getEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacion = :estacion");
			}
			if(x.getArea() != null){
			    paramAsigned++;
			    sbq.append(" and x.area = :area");
			}
			if(x.getEmpleadoJefe() != null){
			    paramAsigned++;
			    sbq.append(" and x.empleadoJefe = :empleadoJefe");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ResponsableEstacion> nq = em.createQuery(sbq.toString(), ResponsableEstacion.class);
		
		if(paramAsigned>0){
			if(x.getIdResponsableEstacion() != null){
			    nq.setParameter("idResponsableEstacion",x.getIdResponsableEstacion());
			}
			if(x.getEmpleado() != null){
			    nq.setParameter("empleado",x.getEmpleado());
			}
			if(x.getEstacion() != null){
			    nq.setParameter("estacion",x.getEstacion());
			}
			if(x.getArea() != null){
			    nq.setParameter("area",x.getArea());
			}
			if(x.getEmpleadoJefe() != null){
			    nq.setParameter("empleadoJefe",x.getEmpleadoJefe());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ResponsableEstacion findByPK_EAGER(Object pk){
        ResponsableEstacion x = getEntityManager().find(ResponsableEstacion.class, pk);
        if( x != null){
            if(x.getEmpleado() !=null && x.getEmpleado().getIdEmpleado()!=null){} 
            if(x.getEstacion() !=null && x.getEstacion().getIdEstacion()!=null){} 
            if(x.getArea() !=null && x.getArea().getIdArea()!=null){} 
            if(x.getEmpleadoJefe() !=null && x.getEmpleadoJefe().getIdEmpleado()!=null){} 
            for(Reporte x_reporte: x.getReporteHasIdResponsableAmxList() ) {} 
            for(Reporte x_reporte: x.getReporteHasIdResponsableFinalAmxList() ) {} 
            for(Seguimiento x_seguimiento: x.getSeguimientoHasResponsableEstacionList() ) {} 
            for(TipoProductoReporte x_TipoProductoReporte: x.getTipoProductoReporteList() ) {} 

        }
        return x;
    }

	@Override
	public List<ResponsableEstacion> findAll() {
		TypedQuery<ResponsableEstacion> nq = em.createNamedQuery("ResponsableEstacion.findAll", ResponsableEstacion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ResponsableEstacion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
