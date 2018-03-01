package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ComunicadoProveedorEstacion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ComunicadoProveedorEstacionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table comunicado_proveedor_estacion by ComunicadoProveedorEstacionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "comunicadoProveedorEstacion_RSB",
    mappedName   = "ComunicadoProveedorEstacion_RSB",
    description  = "ComunicadoProveedorEstacionFacadeRemote-Stateless-Session EJB-3.1"
)
public class ComunicadoProveedorEstacionFacade extends AbstractFacade<ComunicadoProveedorEstacion> implements ComunicadoProveedorEstacionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ComunicadoProveedorEstacionFacade() {
		super(ComunicadoProveedorEstacion.class);
	}

	@Override
	public ComunicadoProveedorEstacion create(ComunicadoProveedorEstacion entity) {
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
	public ComunicadoProveedorEstacion update(ComunicadoProveedorEstacion entity) {
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
	public List<ComunicadoProveedorEstacion> findAllLike(ComunicadoProveedorEstacion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ComunicadoProveedorEstacion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdComunicadoProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idComunicadoProveedorEstacion = :idComunicadoProveedorEstacion");
			}
			if(x.getComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.comunicado = :comunicado");
			}
			if(x.getProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacion = :proveedorEstacion");
			}
			if(x.getContactosDirigidos() != null){
			    paramAsigned++;
			    sbq.append(" and x.contactosDirigidos = :contactosDirigidos");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ComunicadoProveedorEstacion> nq = em.createQuery(sbq.toString(), ComunicadoProveedorEstacion.class);
		
		if(paramAsigned>0){
			if(x.getIdComunicadoProveedorEstacion() != null){
			    nq.setParameter("idComunicadoProveedorEstacion",x.getIdComunicadoProveedorEstacion());
			}
			if(x.getComunicado() != null){
			    nq.setParameter("comunicado",x.getComunicado());
			}
			if(x.getProveedorEstacion() != null){
			    nq.setParameter("proveedorEstacion",x.getProveedorEstacion());
			}
			if(x.getContactosDirigidos() != null){
			    nq.setParameter("contactosDirigidos",x.getContactosDirigidos());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ComunicadoProveedorEstacion findByPK_EAGER(Object pk){
        ComunicadoProveedorEstacion x = getEntityManager().find(ComunicadoProveedorEstacion.class, pk);
        if( x != null){
            if(x.getComunicado() !=null && x.getComunicado().getIdComunicado()!=null){} 
            if(x.getProveedorEstacion() !=null && x.getProveedorEstacion().getIdProveedorEstacion()!=null){} 

        }
        return x;
    }

	@Override
	public List<ComunicadoProveedorEstacion> findAll() {
		TypedQuery<ComunicadoProveedorEstacion> nq = em.createNamedQuery("ComunicadoProveedorEstacion.findAll", ComunicadoProveedorEstacion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ComunicadoProveedorEstacion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
