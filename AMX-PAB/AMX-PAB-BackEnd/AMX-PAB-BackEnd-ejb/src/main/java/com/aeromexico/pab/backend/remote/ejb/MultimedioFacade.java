package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Multimedio;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MultimedioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table multimedio by MultimedioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "multimedio_RSB",
    mappedName   = "Multimedio_RSB",
    description  = "MultimedioFacadeRemote-Stateless-Session EJB-3.1"
)
public class MultimedioFacade extends AbstractFacade<Multimedio> implements MultimedioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MultimedioFacade() {
		super(Multimedio.class);
	}

	@Override
	public Multimedio create(Multimedio entity) {
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
        final List<KitMaster> entity_kitMasterHasMultimedioList =  entity.getKitMasterHasMultimedioList();
        entity.setKitMasterHasMultimedioList(null);
        final List<Tsu> entity_tsuHasMultimedioList =  entity.getTsuHasMultimedioList();
        entity.setTsuHasMultimedioList(null);
        final List<Evidencia> entity_evidenciaHasMultimedioList =  entity.getEvidenciaHasMultimedioList();
        entity.setEvidenciaHasMultimedioList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setKitMasterHasMultimedioList(entity_kitMasterHasMultimedioList);
        entity.setTsuHasMultimedioList(entity_tsuHasMultimedioList);
        entity.setEvidenciaHasMultimedioList(entity_evidenciaHasMultimedioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Multimedio update(Multimedio entity) {
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
        final List<KitMaster> entity_kitMasterHasMultimedioList =  entity.getKitMasterHasMultimedioList();
        entity.setKitMasterHasMultimedioList(null);
        final List<Tsu> entity_tsuHasMultimedioList =  entity.getTsuHasMultimedioList();
        entity.setTsuHasMultimedioList(null);
        final List<Evidencia> entity_evidenciaHasMultimedioList =  entity.getEvidenciaHasMultimedioList();
        entity.setEvidenciaHasMultimedioList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setKitMasterHasMultimedioList(entity_kitMasterHasMultimedioList);
        entity.setTsuHasMultimedioList(entity_tsuHasMultimedioList);
        entity.setEvidenciaHasMultimedioList(entity_evidenciaHasMultimedioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Multimedio> findAllLike(Multimedio x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Multimedio x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdMultimedio() != null){
			    paramAsigned++;
			    sbq.append(" and x.idMultimedio = :idMultimedio");
			}
			if(x.getHashMd5() != null){
			    paramAsigned++;
			    sbq.append(" and x.hashMd5 = :hashMd5");
			}
			if(x.getUrl() != null){
			    paramAsigned++;
			    sbq.append(" and x.url = :url");
			}
			if(x.getUrlLocal() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlLocal = :urlLocal");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
			}
			if(x.getSize()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.size = :size");
			}
			if(x.getFileName() != null){
			    paramAsigned++;
			    sbq.append(" and x.fileName = :fileName");
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
		
		TypedQuery<Multimedio> nq = em.createQuery(sbq.toString(), Multimedio.class);
		
		if(paramAsigned>0){
			if(x.getIdMultimedio() != null){
			    nq.setParameter("idMultimedio",x.getIdMultimedio());
			}
			if(x.getHashMd5() != null){
			    nq.setParameter("hashMd5",x.getHashMd5());
			}
			if(x.getUrl() != null){
			    nq.setParameter("url",x.getUrl());
			}
			if(x.getUrlLocal() != null){
			    nq.setParameter("urlLocal",x.getUrlLocal());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
			}
			if(x.getSize()  != 0){
			    nq.setParameter("size",x.getSize());
			}
			if(x.getFileName() != null){
			    nq.setParameter("fileName",x.getFileName());
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
	public Multimedio findByPK_EAGER(Object pk){
        Multimedio x = getEntityManager().find(Multimedio.class, pk);
        if( x != null){
            for(KitMaster x_kitMaster: x.getKitMasterHasMultimedioList() ) {} 
            for(Tsu x_tsu: x.getTsuHasMultimedioList() ) {} 
            for(Evidencia x_evidencia: x.getEvidenciaHasMultimedioList() ) {} 

        }
        return x;
    }

	@Override
	public List<Multimedio> findAll() {
		TypedQuery<Multimedio> nq = em.createNamedQuery("Multimedio.findAll", Multimedio.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Multimedio.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
