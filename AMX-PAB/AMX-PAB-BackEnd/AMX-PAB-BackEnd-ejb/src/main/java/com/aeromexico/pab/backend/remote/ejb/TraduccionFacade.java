package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Traduccion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TraduccionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table traduccion by TraduccionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "traduccion_RSB",
    mappedName   = "Traduccion_RSB",
    description  = "TraduccionFacadeRemote-Stateless-Session EJB-3.1"
)
public class TraduccionFacade extends AbstractFacade<Traduccion> implements TraduccionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TraduccionFacade() {
		super(Traduccion.class);
	}

	@Override
	public Traduccion create(Traduccion entity) {
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
	public Traduccion update(Traduccion entity) {
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
	public List<Traduccion> findAllLike(Traduccion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Traduccion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTraduccion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTraduccion = :idTraduccion");
			}
			if(x.getNombreEntidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEntidad = :nombreEntidad");
			}
			if(x.getIdEntidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.idEntidad = :idEntidad");
			}
			if(x.getCampo() != null){
			    paramAsigned++;
			    sbq.append(" and x.campo = :campo");
			}
			if(x.getValor() != null){
			    paramAsigned++;
			    sbq.append(" and x.valor = :valor");
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
		
		TypedQuery<Traduccion> nq = em.createQuery(sbq.toString(), Traduccion.class);
		
		if(paramAsigned>0){
			if(x.getIdTraduccion() != null){
			    nq.setParameter("idTraduccion",x.getIdTraduccion());
			}
			if(x.getNombreEntidad() != null){
			    nq.setParameter("nombreEntidad",x.getNombreEntidad());
			}
			if(x.getIdEntidad()  != 0){
			    nq.setParameter("idEntidad",x.getIdEntidad());
			}
			if(x.getCampo() != null){
			    nq.setParameter("campo",x.getCampo());
			}
			if(x.getValor() != null){
			    nq.setParameter("valor",x.getValor());
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
	public Traduccion findByPK_EAGER(Object pk){
        Traduccion x = getEntityManager().find(Traduccion.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<Traduccion> findAll() {
		TypedQuery<Traduccion> nq = em.createNamedQuery("Traduccion.findAll", Traduccion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Traduccion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
