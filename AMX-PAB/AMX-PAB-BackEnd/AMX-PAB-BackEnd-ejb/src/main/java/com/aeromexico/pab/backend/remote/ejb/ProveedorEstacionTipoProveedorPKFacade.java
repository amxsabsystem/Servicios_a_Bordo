package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ProveedorEstacionTipoProveedorPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProveedorEstacionTipoProveedorPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table proveedor_estacion_tipo_proveedor_P_K by ProveedorEstacionTipoProveedorPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proveedorEstacionTipoProveedorPK_RSB",
    mappedName   = "ProveedorEstacionTipoProveedorPK_RSB",
    description  = "ProveedorEstacionTipoProveedorPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProveedorEstacionTipoProveedorPKFacade extends AbstractFacade<ProveedorEstacionTipoProveedorPK> implements ProveedorEstacionTipoProveedorPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProveedorEstacionTipoProveedorPKFacade() {
		super(ProveedorEstacionTipoProveedorPK.class);
	}

	@Override
	public ProveedorEstacionTipoProveedorPK create(ProveedorEstacionTipoProveedorPK entity) {
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
	public ProveedorEstacionTipoProveedorPK update(ProveedorEstacionTipoProveedorPK entity) {
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
	public List<ProveedorEstacionTipoProveedorPK> findAllLike(ProveedorEstacionTipoProveedorPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ProveedorEstacionTipoProveedorPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idProveedorEstacion = :idProveedorEstacion");
			}
			if(x.getIdTipoProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoProveedor = :idTipoProveedor");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ProveedorEstacionTipoProveedorPK> nq = em.createQuery(sbq.toString(), ProveedorEstacionTipoProveedorPK.class);
		
		if(paramAsigned>0){
			if(x.getIdProveedorEstacion() != null){
			    nq.setParameter("idProveedorEstacion",x.getIdProveedorEstacion());
			}
			if(x.getIdTipoProveedor() != null){
			    nq.setParameter("idTipoProveedor",x.getIdTipoProveedor());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ProveedorEstacionTipoProveedorPK findByPK_EAGER(Object pk){
        ProveedorEstacionTipoProveedorPK x = getEntityManager().find(ProveedorEstacionTipoProveedorPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<ProveedorEstacionTipoProveedorPK> findAll() {
		TypedQuery<ProveedorEstacionTipoProveedorPK> nq = em.createNamedQuery("ProveedorEstacionTipoProveedorPK.findAll", ProveedorEstacionTipoProveedorPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ProveedorEstacionTipoProveedorPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
