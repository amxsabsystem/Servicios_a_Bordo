package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Perfil;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.PerfilFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table perfil by PerfilFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "perfil_RSB",
    mappedName   = "Perfil_RSB",
    description  = "PerfilFacadeRemote-Stateless-Session EJB-3.1"
)
public class PerfilFacade extends AbstractFacade<Perfil> implements PerfilFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PerfilFacade() {
		super(Perfil.class);
	}

	@Override
	public Perfil create(Perfil entity) {
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
        final List<OpcionMenuPerfil> entity_opcionMenuPerfilHasPerfilidList =  entity.getOpcionMenuPerfilHasPerfilidList();
        entity.setOpcionMenuPerfilHasPerfilidList(null);
        final List<Usuario> entity_usuarioList =  entity.getUsuarioList();
        entity.setUsuarioList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setOpcionMenuPerfilHasPerfilidList(entity_opcionMenuPerfilHasPerfilidList);
        entity.setUsuarioList(entity_usuarioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Perfil update(Perfil entity) {
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
        final List<OpcionMenuPerfil> entity_opcionMenuPerfilHasPerfilidList =  entity.getOpcionMenuPerfilHasPerfilidList();
        entity.setOpcionMenuPerfilHasPerfilidList(null);
        final List<Usuario> entity_usuarioList =  entity.getUsuarioList();
        entity.setUsuarioList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setOpcionMenuPerfilHasPerfilidList(entity_opcionMenuPerfilHasPerfilidList);
        entity.setUsuarioList(entity_usuarioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Perfil> findAllLike(Perfil x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Perfil x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getPerfil() != null){
			    paramAsigned++;
			    sbq.append(" and x.perfil = :perfil");
			}
			if(x.getDescripcionEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcionEs = :descripcionEs");
			}
			if(x.getDescripcionEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcionEn = :descripcionEn");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Perfil> nq = em.createQuery(sbq.toString(), Perfil.class);
		
		if(paramAsigned>0){
			if(x.getPerfil() != null){
			    nq.setParameter("perfil",x.getPerfil());
			}
			if(x.getDescripcionEs() != null){
			    nq.setParameter("descripcionEs",x.getDescripcionEs());
			}
			if(x.getDescripcionEn() != null){
			    nq.setParameter("descripcionEn",x.getDescripcionEn());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Perfil findByPK_EAGER(Object pk){
        Perfil x = getEntityManager().find(Perfil.class, pk);
        if( x != null){
            for(OpcionMenuPerfil x_opcionMenuPerfil: x.getOpcionMenuPerfilHasPerfilidList() ) {} 
            for(Usuario x_Usuario: x.getUsuarioList() ) {} 

        }
        return x;
    }

	@Override
	public List<Perfil> findAll() {
		TypedQuery<Perfil> nq = em.createNamedQuery("Perfil.findAll", Perfil.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Perfil.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
