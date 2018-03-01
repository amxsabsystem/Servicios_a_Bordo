package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.CategoriaMaterial;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CategoriaMaterialFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table categoria_material by CategoriaMaterialFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "categoriaMaterial_RSB",
    mappedName   = "CategoriaMaterial_RSB",
    description  = "CategoriaMaterialFacadeRemote-Stateless-Session EJB-3.1"
)
public class CategoriaMaterialFacade extends AbstractFacade<CategoriaMaterial> implements CategoriaMaterialFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CategoriaMaterialFacade() {
		super(CategoriaMaterial.class);
	}

	@Override
	public CategoriaMaterial create(CategoriaMaterial entity) {
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
        final List<Material> entity_materialHasCategoriaMaterialList =  entity.getMaterialHasCategoriaMaterialList();
        entity.setMaterialHasCategoriaMaterialList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setMaterialHasCategoriaMaterialList(entity_materialHasCategoriaMaterialList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public CategoriaMaterial update(CategoriaMaterial entity) {
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
        final List<Material> entity_materialHasCategoriaMaterialList =  entity.getMaterialHasCategoriaMaterialList();
        entity.setMaterialHasCategoriaMaterialList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setMaterialHasCategoriaMaterialList(entity_materialHasCategoriaMaterialList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<CategoriaMaterial> findAllLike(CategoriaMaterial x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM CategoriaMaterial x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdCategoriaMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.idCategoriaMaterial = :idCategoriaMaterial");
			}
			if(x.getNombreEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEs = :nombreEs");
			}
			if(x.getNombreEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEn = :nombreEn");
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
		
		TypedQuery<CategoriaMaterial> nq = em.createQuery(sbq.toString(), CategoriaMaterial.class);
		
		if(paramAsigned>0){
			if(x.getIdCategoriaMaterial() != null){
			    nq.setParameter("idCategoriaMaterial",x.getIdCategoriaMaterial());
			}
			if(x.getNombreEs() != null){
			    nq.setParameter("nombreEs",x.getNombreEs());
			}
			if(x.getNombreEn() != null){
			    nq.setParameter("nombreEn",x.getNombreEn());
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
	public CategoriaMaterial findByPK_EAGER(Object pk){
        CategoriaMaterial x = getEntityManager().find(CategoriaMaterial.class, pk);
        if( x != null){
            for(Material x_material: x.getMaterialHasCategoriaMaterialList() ) {} 

        }
        return x;
    }

	@Override
	public List<CategoriaMaterial> findAll() {
		TypedQuery<CategoriaMaterial> nq = em.createNamedQuery("CategoriaMaterial.findAll", CategoriaMaterial.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("CategoriaMaterial.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
