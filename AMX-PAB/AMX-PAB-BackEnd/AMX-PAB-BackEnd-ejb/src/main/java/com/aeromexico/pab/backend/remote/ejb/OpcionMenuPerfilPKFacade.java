package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.OpcionMenuPerfilPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.OpcionMenuPerfilPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table opcion_menu_perfil_P_K by OpcionMenuPerfilPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "opcionMenuPerfilPK_RSB",
    mappedName   = "OpcionMenuPerfilPK_RSB",
    description  = "OpcionMenuPerfilPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class OpcionMenuPerfilPKFacade extends AbstractFacade<OpcionMenuPerfilPK> implements OpcionMenuPerfilPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OpcionMenuPerfilPKFacade() {
		super(OpcionMenuPerfilPK.class);
	}

	@Override
	public OpcionMenuPerfilPK create(OpcionMenuPerfilPK entity) {
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
	public OpcionMenuPerfilPK update(OpcionMenuPerfilPK entity) {
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
	public List<OpcionMenuPerfilPK> findAllLike(OpcionMenuPerfilPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM OpcionMenuPerfilPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdPerfil() != null){
			    paramAsigned++;
			    sbq.append(" and x.idPerfil = :idPerfil");
			}
			if(x.getIdOpcionMenu() != null){
			    paramAsigned++;
			    sbq.append(" and x.idOpcionMenu = :idOpcionMenu");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<OpcionMenuPerfilPK> nq = em.createQuery(sbq.toString(), OpcionMenuPerfilPK.class);
		
		if(paramAsigned>0){
			if(x.getIdPerfil() != null){
			    nq.setParameter("idPerfil",x.getIdPerfil());
			}
			if(x.getIdOpcionMenu() != null){
			    nq.setParameter("idOpcionMenu",x.getIdOpcionMenu());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public OpcionMenuPerfilPK findByPK_EAGER(Object pk){
        OpcionMenuPerfilPK x = getEntityManager().find(OpcionMenuPerfilPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<OpcionMenuPerfilPK> findAll() {
		TypedQuery<OpcionMenuPerfilPK> nq = em.createNamedQuery("OpcionMenuPerfilPK.findAll", OpcionMenuPerfilPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("OpcionMenuPerfilPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
