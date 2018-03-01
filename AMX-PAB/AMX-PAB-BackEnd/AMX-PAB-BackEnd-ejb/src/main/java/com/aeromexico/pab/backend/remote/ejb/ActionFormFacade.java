package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ActionForm;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ActionFormFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table action_form by ActionFormFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "actionForm_RSB",
    mappedName   = "ActionForm_RSB",
    description  = "ActionFormFacadeRemote-Stateless-Session EJB-3.1"
)
public class ActionFormFacade extends AbstractFacade<ActionForm> implements ActionFormFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ActionFormFacade() {
		super(ActionForm.class);
	}

	@Override
	public ActionForm create(ActionForm entity) {
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
	public ActionForm update(ActionForm entity) {
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
	public List<ActionForm> findAllLike(ActionForm x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ActionForm x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdActionForm() != null){
			    paramAsigned++;
			    sbq.append(" and x.idActionForm = :idActionForm");
			}
			if(x.getOpcionMenu() != null){
			    paramAsigned++;
			    sbq.append(" and x.opcionMenu = :opcionMenu");
			}
			if(x.getUrl() != null){
			    paramAsigned++;
			    sbq.append(" and x.url = :url");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ActionForm> nq = em.createQuery(sbq.toString(), ActionForm.class);
		
		if(paramAsigned>0){
			if(x.getIdActionForm() != null){
			    nq.setParameter("idActionForm",x.getIdActionForm());
			}
			if(x.getOpcionMenu() != null){
			    nq.setParameter("opcionMenu",x.getOpcionMenu());
			}
			if(x.getUrl() != null){
			    nq.setParameter("url",x.getUrl());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ActionForm findByPK_EAGER(Object pk){
        ActionForm x = getEntityManager().find(ActionForm.class, pk);
        if( x != null){
            if(x.getOpcionMenu() !=null && x.getOpcionMenu().getIdOpcionMenu()!=null){} 

        }
        return x;
    }

	@Override
	public List<ActionForm> findAll() {
		TypedQuery<ActionForm> nq = em.createNamedQuery("ActionForm.findAll", ActionForm.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ActionForm.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
