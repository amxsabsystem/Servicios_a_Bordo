package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.MaterialKitMasterPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MaterialKitMasterPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table material_kit_master_P_K by MaterialKitMasterPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "materialKitMasterPK_RSB",
    mappedName   = "MaterialKitMasterPK_RSB",
    description  = "MaterialKitMasterPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class MaterialKitMasterPKFacade extends AbstractFacade<MaterialKitMasterPK> implements MaterialKitMasterPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MaterialKitMasterPKFacade() {
		super(MaterialKitMasterPK.class);
	}

	@Override
	public MaterialKitMasterPK create(MaterialKitMasterPK entity) {
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
	public MaterialKitMasterPK update(MaterialKitMasterPK entity) {
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
	public List<MaterialKitMasterPK> findAllLike(MaterialKitMasterPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM MaterialKitMasterPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getNumeroParte() != null){
			    paramAsigned++;
			    sbq.append(" and x.numeroParte = :numeroParte");
			}
			if(x.getCveKitMaster() != null){
			    paramAsigned++;
			    sbq.append(" and x.cveKitMaster = :cveKitMaster");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<MaterialKitMasterPK> nq = em.createQuery(sbq.toString(), MaterialKitMasterPK.class);
		
		if(paramAsigned>0){
			if(x.getNumeroParte() != null){
			    nq.setParameter("numeroParte",x.getNumeroParte());
			}
			if(x.getCveKitMaster() != null){
			    nq.setParameter("cveKitMaster",x.getCveKitMaster());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public MaterialKitMasterPK findByPK_EAGER(Object pk){
        MaterialKitMasterPK x = getEntityManager().find(MaterialKitMasterPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<MaterialKitMasterPK> findAll() {
		TypedQuery<MaterialKitMasterPK> nq = em.createNamedQuery("MaterialKitMasterPK.findAll", MaterialKitMasterPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("MaterialKitMasterPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
