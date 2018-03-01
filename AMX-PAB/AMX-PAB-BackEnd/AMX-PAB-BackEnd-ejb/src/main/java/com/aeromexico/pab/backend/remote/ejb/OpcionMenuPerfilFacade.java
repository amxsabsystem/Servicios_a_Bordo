package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.OpcionMenuPerfil;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.OpcionMenuPerfilFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table opcion_menu_perfil by OpcionMenuPerfilFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "opcionMenuPerfil_RSB",
    mappedName   = "OpcionMenuPerfil_RSB",
    description  = "OpcionMenuPerfilFacadeRemote-Stateless-Session EJB-3.1"
)
public class OpcionMenuPerfilFacade extends AbstractFacade<OpcionMenuPerfil> implements OpcionMenuPerfilFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OpcionMenuPerfilFacade() {
		super(OpcionMenuPerfil.class);
	}

	@Override
	public OpcionMenuPerfil create(OpcionMenuPerfil entity) {
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
	public OpcionMenuPerfil update(OpcionMenuPerfil entity) {
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
	public List<OpcionMenuPerfil> findAllLike(OpcionMenuPerfil x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM OpcionMenuPerfil x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdOpcionMenuPerfil() != null){
			    paramAsigned++;
			    sbq.append(" and x.idOpcionMenuPerfil = :idOpcionMenuPerfil");
			}
			if(x.getPerfilid() != null){
			    paramAsigned++;
			    sbq.append(" and x.perfilid = :perfilid");
			}
			if(x.getOpcionMenu() != null){
			    paramAsigned++;
			    sbq.append(" and x.opcionMenu = :opcionMenu");
			}
			if(x.getPermisoRwd() != null){
			    paramAsigned++;
			    sbq.append(" and x.permisoRwd = :permisoRwd");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<OpcionMenuPerfil> nq = em.createQuery(sbq.toString(), OpcionMenuPerfil.class);
		
		if(paramAsigned>0){
			if(x.getIdOpcionMenuPerfil() != null){
			    nq.setParameter("idOpcionMenuPerfil",x.getIdOpcionMenuPerfil());
			}
			if(x.getPerfilid() != null){
			    nq.setParameter("perfilid",x.getPerfilid());
			}
			if(x.getOpcionMenu() != null){
			    nq.setParameter("opcionMenu",x.getOpcionMenu());
			}
			if(x.getPermisoRwd() != null){
			    nq.setParameter("permisoRwd",x.getPermisoRwd());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public OpcionMenuPerfil findByPK_EAGER(Object pk){
        OpcionMenuPerfil x = getEntityManager().find(OpcionMenuPerfil.class, pk);
        if( x != null){
            if(x.getPerfilid() !=null && x.getPerfilid().getPerfil()!=null){} 
            if(x.getOpcionMenu() !=null && x.getOpcionMenu().getIdOpcionMenu()!=null){} 

        }
        return x;
    }

	@Override
	public List<OpcionMenuPerfil> findAll() {
		TypedQuery<OpcionMenuPerfil> nq = em.createNamedQuery("OpcionMenuPerfil.findAll", OpcionMenuPerfil.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("OpcionMenuPerfil.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
