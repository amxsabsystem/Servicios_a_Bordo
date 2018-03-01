package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ResponsableProducto;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ResponsableProductoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table responsable_producto by ResponsableProductoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "responsableProducto_RSB",
    mappedName   = "ResponsableProducto_RSB",
    description  = "ResponsableProductoFacadeRemote-Stateless-Session EJB-3.1"
)
public class ResponsableProductoFacade extends AbstractFacade<ResponsableProducto> implements ResponsableProductoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ResponsableProductoFacade() {
		super(ResponsableProducto.class);
	}

	@Override
	public ResponsableProducto create(ResponsableProducto entity) {
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
	public ResponsableProducto update(ResponsableProducto entity) {
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
	public List<ResponsableProducto> findAllLike(ResponsableProducto x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ResponsableProducto x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getResponsableProductoPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.responsableProductoPK = :responsableProductoPK");
			}
			if(x.getResponsableEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.responsableEstacion = :responsableEstacion");
			}
			if(x.getTipoProductoReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoProductoReporte = :tipoProductoReporte");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ResponsableProducto> nq = em.createQuery(sbq.toString(), ResponsableProducto.class);
		
		if(paramAsigned>0){
			if(x.getResponsableProductoPK() != null){
			    nq.setParameter("responsableProductoPK",x.getResponsableProductoPK());
			}
			if(x.getResponsableEstacion() != null){
			    nq.setParameter("responsableEstacion",x.getResponsableEstacion());
			}
			if(x.getTipoProductoReporte() != null){
			    nq.setParameter("tipoProductoReporte",x.getTipoProductoReporte());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ResponsableProducto findByPK_EAGER(Object pk){
        ResponsableProducto x = getEntityManager().find(ResponsableProducto.class, pk);
        if( x != null){
            if(x.getResponsableEstacion() !=null && x.getResponsableEstacion().getIdResponsableEstacion()!=null){} 
            if(x.getTipoProductoReporte() !=null && x.getTipoProductoReporte().getIdTipoProductoReporte()!=null){} 

        }
        return x;
    }

	@Override
	public List<ResponsableProducto> findAll() {
		TypedQuery<ResponsableProducto> nq = em.createNamedQuery("ResponsableProducto.findAll", ResponsableProducto.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ResponsableProducto.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
