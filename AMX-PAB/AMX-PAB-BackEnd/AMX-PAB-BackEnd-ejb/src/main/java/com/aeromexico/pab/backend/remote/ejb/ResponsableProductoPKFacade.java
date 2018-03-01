package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ResponsableProductoPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ResponsableProductoPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table responsable_producto_P_K by ResponsableProductoPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "responsableProductoPK_RSB",
    mappedName   = "ResponsableProductoPK_RSB",
    description  = "ResponsableProductoPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class ResponsableProductoPKFacade extends AbstractFacade<ResponsableProductoPK> implements ResponsableProductoPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ResponsableProductoPKFacade() {
		super(ResponsableProductoPK.class);
	}

	@Override
	public ResponsableProductoPK create(ResponsableProductoPK entity) {
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
	public ResponsableProductoPK update(ResponsableProductoPK entity) {
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
	public List<ResponsableProductoPK> findAllLike(ResponsableProductoPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ResponsableProductoPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdResponsableEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idResponsableEstacion = :idResponsableEstacion");
			}
			if(x.getIdTipoProductoReporte() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoProductoReporte = :idTipoProductoReporte");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ResponsableProductoPK> nq = em.createQuery(sbq.toString(), ResponsableProductoPK.class);
		
		if(paramAsigned>0){
			if(x.getIdResponsableEstacion() != null){
			    nq.setParameter("idResponsableEstacion",x.getIdResponsableEstacion());
			}
			if(x.getIdTipoProductoReporte() != null){
			    nq.setParameter("idTipoProductoReporte",x.getIdTipoProductoReporte());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ResponsableProductoPK findByPK_EAGER(Object pk){
        ResponsableProductoPK x = getEntityManager().find(ResponsableProductoPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<ResponsableProductoPK> findAll() {
		TypedQuery<ResponsableProductoPK> nq = em.createNamedQuery("ResponsableProductoPK.findAll", ResponsableProductoPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ResponsableProductoPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
