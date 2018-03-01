package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AcomodoProducto;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AcomodoProductoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table acomodo_producto by AcomodoProductoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "acomodoProducto_RSB",
    mappedName   = "AcomodoProducto_RSB",
    description  = "AcomodoProductoFacadeRemote-Stateless-Session EJB-3.1"
)
public class AcomodoProductoFacade extends AbstractFacade<AcomodoProducto> implements AcomodoProductoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AcomodoProductoFacade() {
		super(AcomodoProducto.class);
	}

	@Override
	public AcomodoProducto create(AcomodoProducto entity) {
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
	public AcomodoProducto update(AcomodoProducto entity) {
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
	public List<AcomodoProducto> findAllLike(AcomodoProducto x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AcomodoProducto x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getAcomodoProductoPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.acomodoProductoPK = :acomodoProductoPK");
			}
			if(x.getAcomodo() != null){
			    paramAsigned++;
			    sbq.append(" and x.acomodo = :acomodo");
			}
			if(x.getProducto() != null){
			    paramAsigned++;
			    sbq.append(" and x.producto = :producto");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AcomodoProducto> nq = em.createQuery(sbq.toString(), AcomodoProducto.class);
		
		if(paramAsigned>0){
			if(x.getAcomodoProductoPK() != null){
			    nq.setParameter("acomodoProductoPK",x.getAcomodoProductoPK());
			}
			if(x.getAcomodo() != null){
			    nq.setParameter("acomodo",x.getAcomodo());
			}
			if(x.getProducto() != null){
			    nq.setParameter("producto",x.getProducto());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AcomodoProducto findByPK_EAGER(Object pk){
        AcomodoProducto x = getEntityManager().find(AcomodoProducto.class, pk);
        if( x != null){
            if(x.getAcomodo() !=null && x.getAcomodo().getIdAcomodo()!=null){} 
            if(x.getProducto() !=null && x.getProducto().getIdProducto()!=null){} 

        }
        return x;
    }

	@Override
	public List<AcomodoProducto> findAll() {
		TypedQuery<AcomodoProducto> nq = em.createNamedQuery("AcomodoProducto.findAll", AcomodoProducto.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AcomodoProducto.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
