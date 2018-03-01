package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ProveedorEstacionTipoProveedor;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProveedorEstacionTipoProveedorFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table proveedor_estacion_tipo_proveedor by ProveedorEstacionTipoProveedorFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proveedorEstacionTipoProveedor_RSB",
    mappedName   = "ProveedorEstacionTipoProveedor_RSB",
    description  = "ProveedorEstacionTipoProveedorFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProveedorEstacionTipoProveedorFacade extends AbstractFacade<ProveedorEstacionTipoProveedor> implements ProveedorEstacionTipoProveedorFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProveedorEstacionTipoProveedorFacade() {
		super(ProveedorEstacionTipoProveedor.class);
	}

	@Override
	public ProveedorEstacionTipoProveedor create(ProveedorEstacionTipoProveedor entity) {
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
	public ProveedorEstacionTipoProveedor update(ProveedorEstacionTipoProveedor entity) {
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
	public List<ProveedorEstacionTipoProveedor> findAllLike(ProveedorEstacionTipoProveedor x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ProveedorEstacionTipoProveedor x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getProveedorEstacionTipoProveedorPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacionTipoProveedorPK = :proveedorEstacionTipoProveedorPK");
			}
			if(x.getProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacion = :proveedorEstacion");
			}
			if(x.getTipoProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoProveedor = :tipoProveedor");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ProveedorEstacionTipoProveedor> nq = em.createQuery(sbq.toString(), ProveedorEstacionTipoProveedor.class);
		
		if(paramAsigned>0){
			if(x.getProveedorEstacionTipoProveedorPK() != null){
			    nq.setParameter("proveedorEstacionTipoProveedorPK",x.getProveedorEstacionTipoProveedorPK());
			}
			if(x.getProveedorEstacion() != null){
			    nq.setParameter("proveedorEstacion",x.getProveedorEstacion());
			}
			if(x.getTipoProveedor() != null){
			    nq.setParameter("tipoProveedor",x.getTipoProveedor());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ProveedorEstacionTipoProveedor findByPK_EAGER(Object pk){
        ProveedorEstacionTipoProveedor x = getEntityManager().find(ProveedorEstacionTipoProveedor.class, pk);
        if( x != null){
            if(x.getProveedorEstacion() !=null && x.getProveedorEstacion().getIdProveedorEstacion()!=null){} 
            if(x.getTipoProveedor() !=null && x.getTipoProveedor().getIdTipoProveedor()!=null){} 

        }
        return x;
    }

	@Override
	public List<ProveedorEstacionTipoProveedor> findAll() {
		TypedQuery<ProveedorEstacionTipoProveedor> nq = em.createNamedQuery("ProveedorEstacionTipoProveedor.findAll", ProveedorEstacionTipoProveedor.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ProveedorEstacionTipoProveedor.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
