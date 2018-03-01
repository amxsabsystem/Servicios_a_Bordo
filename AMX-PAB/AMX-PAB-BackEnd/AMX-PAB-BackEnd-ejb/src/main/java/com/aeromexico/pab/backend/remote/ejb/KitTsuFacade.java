package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.KitTsu;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.KitTsuFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Kit_TSU by KitTsuFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "kitTsu_RSB",
    mappedName   = "KitTsu_RSB",
    description  = "KitTsuFacadeRemote-Stateless-Session EJB-3.1"
)
public class KitTsuFacade extends AbstractFacade<KitTsu> implements KitTsuFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public KitTsuFacade() {
		super(KitTsu.class);
	}

	@Override
	public KitTsu create(KitTsu entity) {
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
	public KitTsu update(KitTsu entity) {
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
	public List<KitTsu> findAllLike(KitTsu x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM KitTsu x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdKitTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.idKitTsu = :idKitTsu");
			}
			if(x.getKitMaster() != null){
			    paramAsigned++;
			    sbq.append(" and x.kitMaster = :kitMaster");
			}
			if(x.getTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.tsu = :tsu");
			}
			if(x.getCantidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidad = :cantidad");
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
		
		TypedQuery<KitTsu> nq = em.createQuery(sbq.toString(), KitTsu.class);
		
		if(paramAsigned>0){
			if(x.getIdKitTsu() != null){
			    nq.setParameter("idKitTsu",x.getIdKitTsu());
			}
			if(x.getKitMaster() != null){
			    nq.setParameter("kitMaster",x.getKitMaster());
			}
			if(x.getTsu() != null){
			    nq.setParameter("tsu",x.getTsu());
			}
			if(x.getCantidad()  != 0){
			    nq.setParameter("cantidad",x.getCantidad());
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
	public KitTsu findByPK_EAGER(Object pk){
        KitTsu x = getEntityManager().find(KitTsu.class, pk);
        if( x != null){
            if(x.getKitMaster() !=null && x.getKitMaster().getCveKitMaster()!=null){} 
            if(x.getTsu() !=null && x.getTsu().getIdTsu()!=null){} 

        }
        return x;
    }

	@Override
	public List<KitTsu> findAll() {
		TypedQuery<KitTsu> nq = em.createNamedQuery("KitTsu.findAll", KitTsu.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("KitTsu.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
