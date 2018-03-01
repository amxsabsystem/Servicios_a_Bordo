package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Clase;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table clase by ClaseFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "clase_RSB",
    mappedName   = "Clase_RSB",
    description  = "ClaseFacadeRemote-Stateless-Session EJB-3.1"
)
public class ClaseFacade extends AbstractFacade<Clase> implements ClaseFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ClaseFacade() {
		super(Clase.class);
	}

	@Override
	public Clase create(Clase entity) {
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
        final List<AsignacionServicio> entity_asignacionServicioHasClaseList =  entity.getAsignacionServicioHasClaseList();
        entity.setAsignacionServicioHasClaseList(null);
        final List<CodigoServicio> entity_codigoServicioHasClaseList =  entity.getCodigoServicioHasClaseList();
        entity.setCodigoServicioHasClaseList(null);
        final List<AvionAudifono> entity_avionAudifonoHasClaseList =  entity.getAvionAudifonoHasClaseList();
        entity.setAvionAudifonoHasClaseList(null);
        final List<Reporte> entity_reporteHasClaseList =  entity.getReporteHasClaseList();
        entity.setReporteHasClaseList(null);
        final List<Tsu> entity_tsuHasClaseList =  entity.getTsuHasClaseList();
        entity.setTsuHasClaseList(null);
        final List<Potencial> entity_potencialHasClaseList =  entity.getPotencialHasClaseList();
        entity.setPotencialHasClaseList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoHasClaseList =  entity.getTablaAbordamientoHasClaseList();
        entity.setTablaAbordamientoHasClaseList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setAsignacionServicioHasClaseList(entity_asignacionServicioHasClaseList);
        entity.setCodigoServicioHasClaseList(entity_codigoServicioHasClaseList);
        entity.setAvionAudifonoHasClaseList(entity_avionAudifonoHasClaseList);
        entity.setReporteHasClaseList(entity_reporteHasClaseList);
        entity.setTsuHasClaseList(entity_tsuHasClaseList);
        entity.setPotencialHasClaseList(entity_potencialHasClaseList);
        entity.setTablaAbordamientoHasClaseList(entity_tablaAbordamientoHasClaseList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Clase update(Clase entity) {
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
        final List<AsignacionServicio> entity_asignacionServicioHasClaseList =  entity.getAsignacionServicioHasClaseList();
        entity.setAsignacionServicioHasClaseList(null);
        final List<CodigoServicio> entity_codigoServicioHasClaseList =  entity.getCodigoServicioHasClaseList();
        entity.setCodigoServicioHasClaseList(null);
        final List<AvionAudifono> entity_avionAudifonoHasClaseList =  entity.getAvionAudifonoHasClaseList();
        entity.setAvionAudifonoHasClaseList(null);
        final List<Reporte> entity_reporteHasClaseList =  entity.getReporteHasClaseList();
        entity.setReporteHasClaseList(null);
        final List<Tsu> entity_tsuHasClaseList =  entity.getTsuHasClaseList();
        entity.setTsuHasClaseList(null);
        final List<Potencial> entity_potencialHasClaseList =  entity.getPotencialHasClaseList();
        entity.setPotencialHasClaseList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoHasClaseList =  entity.getTablaAbordamientoHasClaseList();
        entity.setTablaAbordamientoHasClaseList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setAsignacionServicioHasClaseList(entity_asignacionServicioHasClaseList);
        entity.setCodigoServicioHasClaseList(entity_codigoServicioHasClaseList);
        entity.setAvionAudifonoHasClaseList(entity_avionAudifonoHasClaseList);
        entity.setReporteHasClaseList(entity_reporteHasClaseList);
        entity.setTsuHasClaseList(entity_tsuHasClaseList);
        entity.setPotencialHasClaseList(entity_potencialHasClaseList);
        entity.setTablaAbordamientoHasClaseList(entity_tablaAbordamientoHasClaseList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Clase> findAllLike(Clase x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Clase x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.idClase = :idClase");
			}
			if(x.getClave() != null){
			    paramAsigned++;
			    sbq.append(" and x.clave = :clave");
			}
			if(x.getNombreEsp() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEsp = :nombreEsp");
			}
			if(x.getNombreEng() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEng = :nombreEng");
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
		
		TypedQuery<Clase> nq = em.createQuery(sbq.toString(), Clase.class);
		
		if(paramAsigned>0){
			if(x.getIdClase() != null){
			    nq.setParameter("idClase",x.getIdClase());
			}
			if(x.getClave() != null){
			    nq.setParameter("clave",x.getClave());
			}
			if(x.getNombreEsp() != null){
			    nq.setParameter("nombreEsp",x.getNombreEsp());
			}
			if(x.getNombreEng() != null){
			    nq.setParameter("nombreEng",x.getNombreEng());
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
	public Clase findByPK_EAGER(Object pk){
        Clase x = getEntityManager().find(Clase.class, pk);
        if( x != null){
            for(AsignacionServicio x_asignacionServicio: x.getAsignacionServicioHasClaseList() ) {} 
            for(CodigoServicio x_codigoServicio: x.getCodigoServicioHasClaseList() ) {} 
            for(AvionAudifono x_avionAudifono: x.getAvionAudifonoHasClaseList() ) {} 
            for(Reporte x_reporte: x.getReporteHasClaseList() ) {} 
            for(Tsu x_tsu: x.getTsuHasClaseList() ) {} 
            for(Potencial x_potencial: x.getPotencialHasClaseList() ) {} 
            for(TablaAbordamiento x_tablaAbordamiento: x.getTablaAbordamientoHasClaseList() ) {} 

        }
        return x;
    }

	@Override
	public List<Clase> findAll() {
		TypedQuery<Clase> nq = em.createNamedQuery("Clase.findAll", Clase.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Clase.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
