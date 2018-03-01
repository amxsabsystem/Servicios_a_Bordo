package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Pais;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table pais by PaisFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "pais_RSB",
    mappedName   = "Pais_RSB",
    description  = "PaisFacadeRemote-Stateless-Session EJB-3.1"
)
public class PaisFacade extends AbstractFacade<Pais> implements PaisFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PaisFacade() {
		super(Pais.class);
	}

	@Override
	public Pais create(Pais entity) {
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
        final List<Ciudad> entity_ciudadHasPaisList =  entity.getCiudadHasPaisList();
        entity.setCiudadHasPaisList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setCiudadHasPaisList(entity_ciudadHasPaisList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Pais update(Pais entity) {
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
        final List<Ciudad> entity_ciudadHasPaisList =  entity.getCiudadHasPaisList();
        entity.setCiudadHasPaisList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setCiudadHasPaisList(entity_ciudadHasPaisList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Pais> findAllLike(Pais x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Pais x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdPais() != null){
			    paramAsigned++;
			    sbq.append(" and x.idPais = :idPais");
			}
			if(x.getNombre() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombre = :nombre");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
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
		
		TypedQuery<Pais> nq = em.createQuery(sbq.toString(), Pais.class);
		
		if(paramAsigned>0){
			if(x.getIdPais() != null){
			    nq.setParameter("idPais",x.getIdPais());
			}
			if(x.getNombre() != null){
			    nq.setParameter("nombre",x.getNombre());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
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
	public Pais findByPK_EAGER(Object pk){
        Pais x = getEntityManager().find(Pais.class, pk);
        if( x != null){
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 
            for(Ciudad x_ciudad: x.getCiudadHasPaisList() ) {} 

        }
        return x;
    }

	@Override
	public List<Pais> findAll() {
		TypedQuery<Pais> nq = em.createNamedQuery("Pais.findAll", Pais.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Pais.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
