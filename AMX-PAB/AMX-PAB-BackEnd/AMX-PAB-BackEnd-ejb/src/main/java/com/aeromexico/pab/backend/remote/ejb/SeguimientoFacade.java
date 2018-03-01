package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Seguimiento;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.SeguimientoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table seguimiento by SeguimientoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "seguimiento_RSB",
    mappedName   = "Seguimiento_RSB",
    description  = "SeguimientoFacadeRemote-Stateless-Session EJB-3.1"
)
public class SeguimientoFacade extends AbstractFacade<Seguimiento> implements SeguimientoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SeguimientoFacade() {
		super(Seguimiento.class);
	}

	@Override
	public Seguimiento create(Seguimiento entity) {
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
        final List<Evidencia> entity_evidenciaHasSeguimientoList =  entity.getEvidenciaHasSeguimientoList();
        entity.setEvidenciaHasSeguimientoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setEvidenciaHasSeguimientoList(entity_evidenciaHasSeguimientoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Seguimiento update(Seguimiento entity) {
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
        final List<Evidencia> entity_evidenciaHasSeguimientoList =  entity.getEvidenciaHasSeguimientoList();
        entity.setEvidenciaHasSeguimientoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setEvidenciaHasSeguimientoList(entity_evidenciaHasSeguimientoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Seguimiento> findAllLike(Seguimiento x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Seguimiento x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdSeguimiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.idSeguimiento = :idSeguimiento");
			}
			if(x.getMensajeRespuesta() != null){
			    paramAsigned++;
			    sbq.append(" and x.mensajeRespuesta = :mensajeRespuesta");
			}
			if(x.getAreaResponsable() != null){
			    paramAsigned++;
			    sbq.append(" and x.areaResponsable = :areaResponsable");
			}
			if(x.getResponsableEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.responsableEstacion = :responsableEstacion");
			}
			if(x.getProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacion = :proveedorEstacion");
			}
			if(x.getContactoProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.contactoProveedorEstacion = :contactoProveedorEstacion");
			}
			if(x.getReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.reporte = :reporte");
			}
			if(x.getFecha() != null){
			    paramAsigned++;
			    sbq.append(" and x.fecha = :fecha");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Seguimiento> nq = em.createQuery(sbq.toString(), Seguimiento.class);
		
		if(paramAsigned>0){
			if(x.getIdSeguimiento() != null){
			    nq.setParameter("idSeguimiento",x.getIdSeguimiento());
			}
			if(x.getMensajeRespuesta() != null){
			    nq.setParameter("mensajeRespuesta",x.getMensajeRespuesta());
			}
			if(x.getAreaResponsable() != null){
			    nq.setParameter("areaResponsable",x.getAreaResponsable());
			}
			if(x.getResponsableEstacion() != null){
			    nq.setParameter("responsableEstacion",x.getResponsableEstacion());
			}
			if(x.getProveedorEstacion() != null){
			    nq.setParameter("proveedorEstacion",x.getProveedorEstacion());
			}
			if(x.getContactoProveedorEstacion() != null){
			    nq.setParameter("contactoProveedorEstacion",x.getContactoProveedorEstacion());
			}
			if(x.getReporte() != null){
			    nq.setParameter("reporte",x.getReporte());
			}
			if(x.getFecha() != null){
			    nq.setParameter("fecha",x.getFecha());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Seguimiento findByPK_EAGER(Object pk){
        Seguimiento x = getEntityManager().find(Seguimiento.class, pk);
        if( x != null){
            if(x.getAreaResponsable() !=null && x.getAreaResponsable().getIdArea()!=null){} 
            if(x.getResponsableEstacion() !=null && x.getResponsableEstacion().getIdResponsableEstacion()!=null){} 
            if(x.getProveedorEstacion() !=null && x.getProveedorEstacion().getIdProveedorEstacion()!=null){} 
            if(x.getContactoProveedorEstacion() !=null && x.getContactoProveedorEstacion().getIdContactoProveedorEstacion()!=null){} 
            if(x.getReporte() !=null && x.getReporte().getIdReporte()!=null){} 
            for(Evidencia x_evidencia: x.getEvidenciaHasSeguimientoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Seguimiento> findAll() {
		TypedQuery<Seguimiento> nq = em.createNamedQuery("Seguimiento.findAll", Seguimiento.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Seguimiento.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
