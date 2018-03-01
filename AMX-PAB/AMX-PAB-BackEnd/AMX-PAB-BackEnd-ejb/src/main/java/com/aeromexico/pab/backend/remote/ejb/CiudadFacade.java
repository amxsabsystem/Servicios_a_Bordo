package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Ciudad;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table ciudad by CiudadFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "ciudad_RSB",
    mappedName   = "Ciudad_RSB",
    description  = "CiudadFacadeRemote-Stateless-Session EJB-3.1"
)
public class CiudadFacade extends AbstractFacade<Ciudad> implements CiudadFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CiudadFacade() {
		super(Ciudad.class);
	}

	@Override
	public Ciudad create(Ciudad entity) {
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
        final List<Estacion> entity_estacionHasCiudadList =  entity.getEstacionHasCiudadList();
        entity.setEstacionHasCiudadList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setEstacionHasCiudadList(entity_estacionHasCiudadList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Ciudad update(Ciudad entity) {
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
        final List<Estacion> entity_estacionHasCiudadList =  entity.getEstacionHasCiudadList();
        entity.setEstacionHasCiudadList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setEstacionHasCiudadList(entity_estacionHasCiudadList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Ciudad> findAllLike(Ciudad x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Ciudad x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdCiudad() != null){
			    paramAsigned++;
			    sbq.append(" and x.idCiudad = :idCiudad");
			}
			if(x.getNombre() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombre = :nombre");
			}
			if(x.getPais() != null){
			    paramAsigned++;
			    sbq.append(" and x.pais = :pais");
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
		
		TypedQuery<Ciudad> nq = em.createQuery(sbq.toString(), Ciudad.class);
		
		if(paramAsigned>0){
			if(x.getIdCiudad() != null){
			    nq.setParameter("idCiudad",x.getIdCiudad());
			}
			if(x.getNombre() != null){
			    nq.setParameter("nombre",x.getNombre());
			}
			if(x.getPais() != null){
			    nq.setParameter("pais",x.getPais());
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
	public Ciudad findByPK_EAGER(Object pk){
        Ciudad x = getEntityManager().find(Ciudad.class, pk);
        if( x != null){
            if(x.getPais() !=null && x.getPais().getIdPais()!=null){} 
            for(Estacion x_estacion: x.getEstacionHasCiudadList() ) {} 

        }
        return x;
    }

	@Override
	public List<Ciudad> findAll() {
		TypedQuery<Ciudad> nq = em.createNamedQuery("Ciudad.findAll", Ciudad.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Ciudad.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
