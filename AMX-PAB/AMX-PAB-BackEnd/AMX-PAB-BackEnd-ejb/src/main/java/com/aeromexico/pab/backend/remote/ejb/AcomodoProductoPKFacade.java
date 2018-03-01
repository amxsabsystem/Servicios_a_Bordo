package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AcomodoProductoPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AcomodoProductoPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table acomodo_producto_P_K by AcomodoProductoPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "acomodoProductoPK_RSB",
    mappedName   = "AcomodoProductoPK_RSB",
    description  = "AcomodoProductoPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class AcomodoProductoPKFacade extends AbstractFacade<AcomodoProductoPK> implements AcomodoProductoPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AcomodoProductoPKFacade() {
		super(AcomodoProductoPK.class);
	}

	@Override
	public AcomodoProductoPK create(AcomodoProductoPK entity) {
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
	public AcomodoProductoPK update(AcomodoProductoPK entity) {
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
	public List<AcomodoProductoPK> findAllLike(AcomodoProductoPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AcomodoProductoPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAcomodo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAcomodo = :idAcomodo");
			}
			if(x.getIdProducto() != null){
			    paramAsigned++;
			    sbq.append(" and x.idProducto = :idProducto");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AcomodoProductoPK> nq = em.createQuery(sbq.toString(), AcomodoProductoPK.class);
		
		if(paramAsigned>0){
			if(x.getIdAcomodo() != null){
			    nq.setParameter("idAcomodo",x.getIdAcomodo());
			}
			if(x.getIdProducto() != null){
			    nq.setParameter("idProducto",x.getIdProducto());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AcomodoProductoPK findByPK_EAGER(Object pk){
        AcomodoProductoPK x = getEntityManager().find(AcomodoProductoPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<AcomodoProductoPK> findAll() {
		TypedQuery<AcomodoProductoPK> nq = em.createNamedQuery("AcomodoProductoPK.findAll", AcomodoProductoPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AcomodoProductoPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
