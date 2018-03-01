package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.PerfilUsuarioPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.PerfilUsuarioPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table perfil_usuario_P_K by PerfilUsuarioPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "perfilUsuarioPK_RSB",
    mappedName   = "PerfilUsuarioPK_RSB",
    description  = "PerfilUsuarioPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class PerfilUsuarioPKFacade extends AbstractFacade<PerfilUsuarioPK> implements PerfilUsuarioPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PerfilUsuarioPKFacade() {
		super(PerfilUsuarioPK.class);
	}

	@Override
	public PerfilUsuarioPK create(PerfilUsuarioPK entity) {
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
	public PerfilUsuarioPK update(PerfilUsuarioPK entity) {
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
	public List<PerfilUsuarioPK> findAllLike(PerfilUsuarioPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM PerfilUsuarioPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getPerfil() != null){
			    paramAsigned++;
			    sbq.append(" and x.perfil = :perfil");
			}
			if(x.getEmailUsuario() != null){
			    paramAsigned++;
			    sbq.append(" and x.emailUsuario = :emailUsuario");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<PerfilUsuarioPK> nq = em.createQuery(sbq.toString(), PerfilUsuarioPK.class);
		
		if(paramAsigned>0){
			if(x.getPerfil() != null){
			    nq.setParameter("perfil",x.getPerfil());
			}
			if(x.getEmailUsuario() != null){
			    nq.setParameter("emailUsuario",x.getEmailUsuario());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public PerfilUsuarioPK findByPK_EAGER(Object pk){
        PerfilUsuarioPK x = getEntityManager().find(PerfilUsuarioPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<PerfilUsuarioPK> findAll() {
		TypedQuery<PerfilUsuarioPK> nq = em.createNamedQuery("PerfilUsuarioPK.findAll", PerfilUsuarioPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("PerfilUsuarioPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
