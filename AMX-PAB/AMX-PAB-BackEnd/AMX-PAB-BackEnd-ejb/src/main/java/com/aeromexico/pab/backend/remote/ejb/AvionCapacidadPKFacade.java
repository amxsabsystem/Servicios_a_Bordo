package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AvionCapacidadPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AvionCapacidadPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table avion_capacidad_P_K by AvionCapacidadPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "avionCapacidadPK_RSB",
    mappedName   = "AvionCapacidadPK_RSB",
    description  = "AvionCapacidadPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class AvionCapacidadPKFacade extends AbstractFacade<AvionCapacidadPK> implements AvionCapacidadPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AvionCapacidadPKFacade() {
		super(AvionCapacidadPK.class);
	}

	@Override
	public AvionCapacidadPK create(AvionCapacidadPK entity) {
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
	public AvionCapacidadPK update(AvionCapacidadPK entity) {
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
	public List<AvionCapacidadPK> findAllLike(AvionCapacidadPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AvionCapacidadPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAvion = :idAvion");
			}
			if(x.getIdClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.idClase = :idClase");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AvionCapacidadPK> nq = em.createQuery(sbq.toString(), AvionCapacidadPK.class);
		
		if(paramAsigned>0){
			if(x.getIdAvion() != null){
			    nq.setParameter("idAvion",x.getIdAvion());
			}
			if(x.getIdClase() != null){
			    nq.setParameter("idClase",x.getIdClase());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AvionCapacidadPK findByPK_EAGER(Object pk){
        AvionCapacidadPK x = getEntityManager().find(AvionCapacidadPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<AvionCapacidadPK> findAll() {
		TypedQuery<AvionCapacidadPK> nq = em.createNamedQuery("AvionCapacidadPK.findAll", AvionCapacidadPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AvionCapacidadPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
