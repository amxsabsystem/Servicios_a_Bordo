package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.DetalleRegionAbordajePK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.DetalleRegionAbordajePKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table detalle_region_abordaje_P_K by DetalleRegionAbordajePKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "detalleRegionAbordajePK_RSB",
    mappedName   = "DetalleRegionAbordajePK_RSB",
    description  = "DetalleRegionAbordajePKFacadeRemote-Stateless-Session EJB-3.1"
)
public class DetalleRegionAbordajePKFacade extends AbstractFacade<DetalleRegionAbordajePK> implements DetalleRegionAbordajePKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DetalleRegionAbordajePKFacade() {
		super(DetalleRegionAbordajePK.class);
	}

	@Override
	public DetalleRegionAbordajePK create(DetalleRegionAbordajePK entity) {
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
	public DetalleRegionAbordajePK update(DetalleRegionAbordajePK entity) {
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
	public List<DetalleRegionAbordajePK> findAllLike(DetalleRegionAbordajePK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM DetalleRegionAbordajePK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTablaAbordamiento = :idTablaAbordamiento");
			}
			if(x.getIdRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idRegion = :idRegion");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<DetalleRegionAbordajePK> nq = em.createQuery(sbq.toString(), DetalleRegionAbordajePK.class);
		
		if(paramAsigned>0){
			if(x.getIdTablaAbordamiento() != null){
			    nq.setParameter("idTablaAbordamiento",x.getIdTablaAbordamiento());
			}
			if(x.getIdRegion() != null){
			    nq.setParameter("idRegion",x.getIdRegion());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public DetalleRegionAbordajePK findByPK_EAGER(Object pk){
        DetalleRegionAbordajePK x = getEntityManager().find(DetalleRegionAbordajePK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<DetalleRegionAbordajePK> findAll() {
		TypedQuery<DetalleRegionAbordajePK> nq = em.createNamedQuery("DetalleRegionAbordajePK.findAll", DetalleRegionAbordajePK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("DetalleRegionAbordajePK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
