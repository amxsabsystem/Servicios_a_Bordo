package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.PerfilUsuario;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.PerfilUsuarioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table perfil_usuario by PerfilUsuarioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "perfilUsuario_RSB",
    mappedName   = "PerfilUsuario_RSB",
    description  = "PerfilUsuarioFacadeRemote-Stateless-Session EJB-3.1"
)
public class PerfilUsuarioFacade extends AbstractFacade<PerfilUsuario> implements PerfilUsuarioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PerfilUsuarioFacade() {
		super(PerfilUsuario.class);
	}

	@Override
	public PerfilUsuario create(PerfilUsuario entity) {
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
	public PerfilUsuario update(PerfilUsuario entity) {
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
	public List<PerfilUsuario> findAllLike(PerfilUsuario x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM PerfilUsuario x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getPerfilUsuarioPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.perfilUsuarioPK = :perfilUsuarioPK");
			}
			if(x.getPerfil() != null){
			    paramAsigned++;
			    sbq.append(" and x.perfil = :perfil");
			}
			if(x.getUsuario() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuario = :usuario");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<PerfilUsuario> nq = em.createQuery(sbq.toString(), PerfilUsuario.class);
		
		if(paramAsigned>0){
			if(x.getPerfilUsuarioPK() != null){
			    nq.setParameter("perfilUsuarioPK",x.getPerfilUsuarioPK());
			}
			if(x.getPerfil() != null){
			    nq.setParameter("perfil",x.getPerfil());
			}
			if(x.getUsuario() != null){
			    nq.setParameter("usuario",x.getUsuario());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public PerfilUsuario findByPK_EAGER(Object pk){
        PerfilUsuario x = getEntityManager().find(PerfilUsuario.class, pk);
        if( x != null){
            if(x.getPerfil() !=null && x.getPerfil().getPerfil()!=null){} 
            if(x.getUsuario() !=null && x.getUsuario().getEmailUsuario()!=null){} 

        }
        return x;
    }

	@Override
	public List<PerfilUsuario> findAll() {
		TypedQuery<PerfilUsuario> nq = em.createNamedQuery("PerfilUsuario.findAll", PerfilUsuario.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("PerfilUsuario.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
