package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.KitMaster;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.KitMasterFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table kit_master by KitMasterFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "kitMaster_RSB",
    mappedName   = "KitMaster_RSB",
    description  = "KitMasterFacadeRemote-Stateless-Session EJB-3.1"
)
public class KitMasterFacade extends AbstractFacade<KitMaster> implements KitMasterFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public KitMasterFacade() {
		super(KitMaster.class);
	}

	@Override
	public KitMaster create(KitMaster entity) {
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
        final List<KitTsu> entity_kitTsuHasKitMasterList =  entity.getKitTsuHasKitMasterList();
        entity.setKitTsuHasKitMasterList(null);
        final List<MaterialExtra> entity_materialExtraHasKitMasterList =  entity.getMaterialExtraHasKitMasterList();
        entity.setMaterialExtraHasKitMasterList(null);
        final List<Matriz> entity_matrizHasKitMasterList =  entity.getMatrizHasKitMasterList();
        entity.setMatrizHasKitMasterList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setKitTsuHasKitMasterList(entity_kitTsuHasKitMasterList);
        entity.setMaterialExtraHasKitMasterList(entity_materialExtraHasKitMasterList);
        entity.setMatrizHasKitMasterList(entity_matrizHasKitMasterList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public KitMaster update(KitMaster entity) {
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
        final List<KitTsu> entity_kitTsuHasKitMasterList =  entity.getKitTsuHasKitMasterList();
        entity.setKitTsuHasKitMasterList(null);
        final List<MaterialExtra> entity_materialExtraHasKitMasterList =  entity.getMaterialExtraHasKitMasterList();
        entity.setMaterialExtraHasKitMasterList(null);
        final List<Matriz> entity_matrizHasKitMasterList =  entity.getMatrizHasKitMasterList();
        entity.setMatrizHasKitMasterList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setKitTsuHasKitMasterList(entity_kitTsuHasKitMasterList);
        entity.setMaterialExtraHasKitMasterList(entity_materialExtraHasKitMasterList);
        entity.setMatrizHasKitMasterList(entity_matrizHasKitMasterList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<KitMaster> findAllLike(KitMaster x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM KitMaster x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getCveKitMaster() != null){
			    paramAsigned++;
			    sbq.append(" and x.cveKitMaster = :cveKitMaster");
			}
			if(x.getIdUnidadMedida() != null){
			    paramAsigned++;
			    sbq.append(" and x.idUnidadMedida = :idUnidadMedida");
			}
			if(x.getIdInstruccionesNacionales() != null){
			    paramAsigned++;
			    sbq.append(" and x.idInstruccionesNacionales = :idInstruccionesNacionales");
			}
			if(x.getIdInstruccionesInternac() != null){
			    paramAsigned++;
			    sbq.append(" and x.idInstruccionesInternac = :idInstruccionesInternac");
			}
			if(x.getIdTipoKit() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoKit = :idTipoKit");
			}
			if(x.getMultimedio() != null){
			    paramAsigned++;
			    sbq.append(" and x.multimedio = :multimedio");
			}
			if(x.getNombreEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEs = :nombreEs");
			}
			if(x.getNombreEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreEn = :nombreEn");
			}
			if(x.getUrlMultimedia() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlMultimedia = :urlMultimedia");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
			}
			if(x.getContenedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.contenedor = :contenedor");
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
		
		TypedQuery<KitMaster> nq = em.createQuery(sbq.toString(), KitMaster.class);
		
		if(paramAsigned>0){
			if(x.getCveKitMaster() != null){
			    nq.setParameter("cveKitMaster",x.getCveKitMaster());
			}
			if(x.getIdUnidadMedida() != null){
			    nq.setParameter("idUnidadMedida",x.getIdUnidadMedida());
			}
			if(x.getIdInstruccionesNacionales() != null){
			    nq.setParameter("idInstruccionesNacionales",x.getIdInstruccionesNacionales());
			}
			if(x.getIdInstruccionesInternac() != null){
			    nq.setParameter("idInstruccionesInternac",x.getIdInstruccionesInternac());
			}
			if(x.getIdTipoKit() != null){
			    nq.setParameter("idTipoKit",x.getIdTipoKit());
			}
			if(x.getMultimedio() != null){
			    nq.setParameter("multimedio",x.getMultimedio());
			}
			if(x.getNombreEs() != null){
			    nq.setParameter("nombreEs",x.getNombreEs());
			}
			if(x.getNombreEn() != null){
			    nq.setParameter("nombreEn",x.getNombreEn());
			}
			if(x.getUrlMultimedia() != null){
			    nq.setParameter("urlMultimedia",x.getUrlMultimedia());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
			}
			if(x.getContenedor() != null){
			    nq.setParameter("contenedor",x.getContenedor());
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
	public KitMaster findByPK_EAGER(Object pk){
        KitMaster x = getEntityManager().find(KitMaster.class, pk);
        if( x != null){
            if(x.getIdUnidadMedida() !=null && x.getIdUnidadMedida().getIdParametro()!=null){} 
            if(x.getIdInstruccionesNacionales() !=null && x.getIdInstruccionesNacionales().getIdParametro()!=null){} 
            if(x.getIdInstruccionesInternac() !=null && x.getIdInstruccionesInternac().getIdParametro()!=null){} 
            if(x.getIdTipoKit() !=null && x.getIdTipoKit().getIdParametro()!=null){} 
            if(x.getMultimedio() !=null && x.getMultimedio().getIdMultimedio()!=null){} 
            for(KitTsu x_kitTsu: x.getKitTsuHasKitMasterList() ) {} 
            for(MaterialExtra x_materialExtra: x.getMaterialExtraHasKitMasterList() ) {} 
            for(Matriz x_matriz: x.getMatrizHasKitMasterList() ) {} 

        }
        return x;
    }

	@Override
	public List<KitMaster> findAll() {
		TypedQuery<KitMaster> nq = em.createNamedQuery("KitMaster.findAll", KitMaster.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("KitMaster.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
