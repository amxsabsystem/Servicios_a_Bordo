package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.OpcionMenu;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.OpcionMenuFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table opcion_menu by OpcionMenuFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "opcionMenu_RSB",
    mappedName   = "OpcionMenu_RSB",
    description  = "OpcionMenuFacadeRemote-Stateless-Session EJB-3.1"
)
public class OpcionMenuFacade extends AbstractFacade<OpcionMenu> implements OpcionMenuFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OpcionMenuFacade() {
		super(OpcionMenu.class);
	}

	@Override
	public OpcionMenu create(OpcionMenu entity) {
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
        final List<OpcionMenuPerfil> entity_opcionMenuPerfilHasOpcionMenuList =  entity.getOpcionMenuPerfilHasOpcionMenuList();
        entity.setOpcionMenuPerfilHasOpcionMenuList(null);
        final List<ActionForm> entity_actionFormHasOpcionMenuList =  entity.getActionFormHasOpcionMenuList();
        entity.setActionFormHasOpcionMenuList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setOpcionMenuPerfilHasOpcionMenuList(entity_opcionMenuPerfilHasOpcionMenuList);
        entity.setActionFormHasOpcionMenuList(entity_actionFormHasOpcionMenuList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public OpcionMenu update(OpcionMenu entity) {
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
        final List<OpcionMenuPerfil> entity_opcionMenuPerfilHasOpcionMenuList =  entity.getOpcionMenuPerfilHasOpcionMenuList();
        entity.setOpcionMenuPerfilHasOpcionMenuList(null);
        final List<ActionForm> entity_actionFormHasOpcionMenuList =  entity.getActionFormHasOpcionMenuList();
        entity.setActionFormHasOpcionMenuList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setOpcionMenuPerfilHasOpcionMenuList(entity_opcionMenuPerfilHasOpcionMenuList);
        entity.setActionFormHasOpcionMenuList(entity_actionFormHasOpcionMenuList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<OpcionMenu> findAllLike(OpcionMenu x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM OpcionMenu x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdOpcionMenu() != null){
			    paramAsigned++;
			    sbq.append(" and x.idOpcionMenu = :idOpcionMenu");
			}
			if(x.getClaveI18n() != null){
			    paramAsigned++;
			    sbq.append(" and x.claveI18n = :claveI18n");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<OpcionMenu> nq = em.createQuery(sbq.toString(), OpcionMenu.class);
		
		if(paramAsigned>0){
			if(x.getIdOpcionMenu() != null){
			    nq.setParameter("idOpcionMenu",x.getIdOpcionMenu());
			}
			if(x.getClaveI18n() != null){
			    nq.setParameter("claveI18n",x.getClaveI18n());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public OpcionMenu findByPK_EAGER(Object pk){
        OpcionMenu x = getEntityManager().find(OpcionMenu.class, pk);
        if( x != null){
            for(OpcionMenuPerfil x_opcionMenuPerfil: x.getOpcionMenuPerfilHasOpcionMenuList() ) {} 
            for(ActionForm x_actionForm: x.getActionFormHasOpcionMenuList() ) {} 

        }
        return x;
    }

	@Override
	public List<OpcionMenu> findAll() {
		TypedQuery<OpcionMenu> nq = em.createNamedQuery("OpcionMenu.findAll", OpcionMenu.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("OpcionMenu.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
