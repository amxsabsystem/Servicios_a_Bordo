package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ProveedorRegion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProveedorRegionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table proveedor_region by ProveedorRegionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proveedorRegion_RSB",
    mappedName   = "ProveedorRegion_RSB",
    description  = "ProveedorRegionFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProveedorRegionFacade extends AbstractFacade<ProveedorRegion> implements ProveedorRegionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProveedorRegionFacade() {
		super(ProveedorRegion.class);
	}

	@Override
	public ProveedorRegion create(ProveedorRegion entity) {
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
	public ProveedorRegion update(ProveedorRegion entity) {
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
	public List<ProveedorRegion> findAllLike(ProveedorRegion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ProveedorRegion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getProveedorRegionPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorRegionPK = :proveedorRegionPK");
			}
			if(x.getProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedor = :proveedor");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ProveedorRegion> nq = em.createQuery(sbq.toString(), ProveedorRegion.class);
		
		if(paramAsigned>0){
			if(x.getProveedorRegionPK() != null){
			    nq.setParameter("proveedorRegionPK",x.getProveedorRegionPK());
			}
			if(x.getProveedor() != null){
			    nq.setParameter("proveedor",x.getProveedor());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ProveedorRegion findByPK_EAGER(Object pk){
        ProveedorRegion x = getEntityManager().find(ProveedorRegion.class, pk);
        if( x != null){
            if(x.getProveedor() !=null && x.getProveedor().getClaveProveedor()!=null){} 
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 

        }
        return x;
    }

	@Override
	public List<ProveedorRegion> findAll() {
		TypedQuery<ProveedorRegion> nq = em.createNamedQuery("ProveedorRegion.findAll", ProveedorRegion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ProveedorRegion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
