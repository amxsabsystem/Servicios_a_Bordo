package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ProveedorRegionPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProveedorRegionPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table proveedor_region_P_K by ProveedorRegionPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proveedorRegionPK_RSB",
    mappedName   = "ProveedorRegionPK_RSB",
    description  = "ProveedorRegionPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProveedorRegionPKFacade extends AbstractFacade<ProveedorRegionPK> implements ProveedorRegionPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProveedorRegionPKFacade() {
		super(ProveedorRegionPK.class);
	}

	@Override
	public ProveedorRegionPK create(ProveedorRegionPK entity) {
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
	public ProveedorRegionPK update(ProveedorRegionPK entity) {
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
	public List<ProveedorRegionPK> findAllLike(ProveedorRegionPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ProveedorRegionPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getClaveProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.claveProveedor = :claveProveedor");
			}
			if(x.getIdRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idRegion = :idRegion");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ProveedorRegionPK> nq = em.createQuery(sbq.toString(), ProveedorRegionPK.class);
		
		if(paramAsigned>0){
			if(x.getClaveProveedor() != null){
			    nq.setParameter("claveProveedor",x.getClaveProveedor());
			}
			if(x.getIdRegion() != null){
			    nq.setParameter("idRegion",x.getIdRegion());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ProveedorRegionPK findByPK_EAGER(Object pk){
        ProveedorRegionPK x = getEntityManager().find(ProveedorRegionPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<ProveedorRegionPK> findAll() {
		TypedQuery<ProveedorRegionPK> nq = em.createNamedQuery("ProveedorRegionPK.findAll", ProveedorRegionPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ProveedorRegionPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
