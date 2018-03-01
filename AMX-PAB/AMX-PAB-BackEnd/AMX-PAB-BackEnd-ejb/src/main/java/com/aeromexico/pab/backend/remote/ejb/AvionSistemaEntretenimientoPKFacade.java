package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.AvionSistemaEntretenimientoPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AvionSistemaEntretenimientoPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table avion_sistema_entretenimiento_P_K by AvionSistemaEntretenimientoPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "avionSistemaEntretenimientoPK_RSB",
    mappedName   = "AvionSistemaEntretenimientoPK_RSB",
    description  = "AvionSistemaEntretenimientoPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class AvionSistemaEntretenimientoPKFacade extends AbstractFacade<AvionSistemaEntretenimientoPK> implements AvionSistemaEntretenimientoPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AvionSistemaEntretenimientoPKFacade() {
		super(AvionSistemaEntretenimientoPK.class);
	}

	@Override
	public AvionSistemaEntretenimientoPK create(AvionSistemaEntretenimientoPK entity) {
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
	public AvionSistemaEntretenimientoPK update(AvionSistemaEntretenimientoPK entity) {
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
	public List<AvionSistemaEntretenimientoPK> findAllLike(AvionSistemaEntretenimientoPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM AvionSistemaEntretenimientoPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAvion = :idAvion");
			}
			if(x.getIdSistemaEntretenimiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.idSistemaEntretenimiento = :idSistemaEntretenimiento");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<AvionSistemaEntretenimientoPK> nq = em.createQuery(sbq.toString(), AvionSistemaEntretenimientoPK.class);
		
		if(paramAsigned>0){
			if(x.getIdAvion() != null){
			    nq.setParameter("idAvion",x.getIdAvion());
			}
			if(x.getIdSistemaEntretenimiento() != null){
			    nq.setParameter("idSistemaEntretenimiento",x.getIdSistemaEntretenimiento());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public AvionSistemaEntretenimientoPK findByPK_EAGER(Object pk){
        AvionSistemaEntretenimientoPK x = getEntityManager().find(AvionSistemaEntretenimientoPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<AvionSistemaEntretenimientoPK> findAll() {
		TypedQuery<AvionSistemaEntretenimientoPK> nq = em.createNamedQuery("AvionSistemaEntretenimientoPK.findAll", AvionSistemaEntretenimientoPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("AvionSistemaEntretenimientoPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
