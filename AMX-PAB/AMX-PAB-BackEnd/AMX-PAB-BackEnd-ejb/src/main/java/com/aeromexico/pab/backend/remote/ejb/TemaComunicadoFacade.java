package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.TemaComunicado;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TemaComunicadoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table tema_comunicado by TemaComunicadoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "temaComunicado_RSB",
    mappedName   = "TemaComunicado_RSB",
    description  = "TemaComunicadoFacadeRemote-Stateless-Session EJB-3.1"
)
public class TemaComunicadoFacade extends AbstractFacade<TemaComunicado> implements TemaComunicadoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TemaComunicadoFacade() {
		super(TemaComunicado.class);
	}

	@Override
	public TemaComunicado create(TemaComunicado entity) {
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
        final List<Comunicado> entity_comunicadoHasIdTemaList =  entity.getComunicadoHasIdTemaList();
        entity.setComunicadoHasIdTemaList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setComunicadoHasIdTemaList(entity_comunicadoHasIdTemaList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public TemaComunicado update(TemaComunicado entity) {
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
        final List<Comunicado> entity_comunicadoHasIdTemaList =  entity.getComunicadoHasIdTemaList();
        entity.setComunicadoHasIdTemaList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setComunicadoHasIdTemaList(entity_comunicadoHasIdTemaList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<TemaComunicado> findAllLike(TemaComunicado x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM TemaComunicado x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTemaComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTemaComunicado = :idTemaComunicado");
			}
			if(x.getTemaEspaniol() != null){
			    paramAsigned++;
			    sbq.append(" and x.temaEspaniol = :temaEspaniol");
			}
			if(x.getTemaIngles() != null){
			    paramAsigned++;
			    sbq.append(" and x.temaIngles = :temaIngles");
			}
			if(x.getStatus() != null){
			    paramAsigned++;
			    sbq.append(" and x.status = :status");
			}
			if(x.getUsuarioCreo() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuarioCreo = :usuarioCreo");
			}
			if(x.getFechaCreo() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaCreo = :fechaCreo");
			}
			if(x.getUsuarioModifico() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuarioModifico = :usuarioModifico");
			}
			if(x.getFechaModifico() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaModifico = :fechaModifico");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<TemaComunicado> nq = em.createQuery(sbq.toString(), TemaComunicado.class);
		
		if(paramAsigned>0){
			if(x.getIdTemaComunicado() != null){
			    nq.setParameter("idTemaComunicado",x.getIdTemaComunicado());
			}
			if(x.getTemaEspaniol() != null){
			    nq.setParameter("temaEspaniol",x.getTemaEspaniol());
			}
			if(x.getTemaIngles() != null){
			    nq.setParameter("temaIngles",x.getTemaIngles());
			}
			if(x.getStatus() != null){
			    nq.setParameter("status",x.getStatus());
			}
			if(x.getUsuarioCreo() != null){
			    nq.setParameter("usuarioCreo",x.getUsuarioCreo());
			}
			if(x.getFechaCreo() != null){
			    nq.setParameter("fechaCreo",x.getFechaCreo());
			}
			if(x.getUsuarioModifico() != null){
			    nq.setParameter("usuarioModifico",x.getUsuarioModifico());
			}
			if(x.getFechaModifico() != null){
			    nq.setParameter("fechaModifico",x.getFechaModifico());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public TemaComunicado findByPK_EAGER(Object pk){
        TemaComunicado x = getEntityManager().find(TemaComunicado.class, pk);
        if( x != null){
            for(Comunicado x_comunicado: x.getComunicadoHasIdTemaList() ) {} 

        }
        return x;
    }

	@Override
	public List<TemaComunicado> findAll() {
		TypedQuery<TemaComunicado> nq = em.createNamedQuery("TemaComunicado.findAll", TemaComunicado.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("TemaComunicado.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
