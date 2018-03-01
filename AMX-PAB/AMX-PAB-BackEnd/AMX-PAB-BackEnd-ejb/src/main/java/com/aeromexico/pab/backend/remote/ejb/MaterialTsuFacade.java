package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.MaterialTsu;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MaterialTsuFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Material_TSU by MaterialTsuFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "materialTsu_RSB",
    mappedName   = "MaterialTsu_RSB",
    description  = "MaterialTsuFacadeRemote-Stateless-Session EJB-3.1"
)
public class MaterialTsuFacade extends AbstractFacade<MaterialTsu> implements MaterialTsuFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MaterialTsuFacade() {
		super(MaterialTsu.class);
	}

	@Override
	public MaterialTsu create(MaterialTsu entity) {
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
	public MaterialTsu update(MaterialTsu entity) {
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
	public List<MaterialTsu> findAllLike(MaterialTsu x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM MaterialTsu x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdMaterialTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.idMaterialTsu = :idMaterialTsu");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
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
		
		TypedQuery<MaterialTsu> nq = em.createQuery(sbq.toString(), MaterialTsu.class);
		
		if(paramAsigned>0){
			if(x.getIdMaterialTsu() != null){
			    nq.setParameter("idMaterialTsu",x.getIdMaterialTsu());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
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
	public MaterialTsu findByPK_EAGER(Object pk){
        MaterialTsu x = getEntityManager().find(MaterialTsu.class, pk);
        if( x != null){
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 
            if(x.getTsu() !=null && x.getTsu().getIdTsu()!=null){} 

        }
        return x;
    }

	@Override
	public List<MaterialTsu> findAll() {
		TypedQuery<MaterialTsu> nq = em.createNamedQuery("MaterialTsu.findAll", MaterialTsu.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("MaterialTsu.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
