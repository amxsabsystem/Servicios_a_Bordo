package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.MaterialKitMaster;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MaterialKitMasterFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table material_kit_master by MaterialKitMasterFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "materialKitMaster_RSB",
    mappedName   = "MaterialKitMaster_RSB",
    description  = "MaterialKitMasterFacadeRemote-Stateless-Session EJB-3.1"
)
public class MaterialKitMasterFacade extends AbstractFacade<MaterialKitMaster> implements MaterialKitMasterFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MaterialKitMasterFacade() {
		super(MaterialKitMaster.class);
	}

	@Override
	public MaterialKitMaster create(MaterialKitMaster entity) {
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
	public MaterialKitMaster update(MaterialKitMaster entity) {
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
	public List<MaterialKitMaster> findAllLike(MaterialKitMaster x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM MaterialKitMaster x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getMaterialKitMasterPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialKitMasterPK = :materialKitMasterPK");
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
			if(x.getCantidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidad = :cantidad");
			}
			if(x.getReciclable() != null){
			    paramAsigned++;
			    sbq.append(" and x.reciclable = :reciclable");
			}
			if(x.getObservaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.observaciones = :observaciones");
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
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
			}
			if(x.getKitMaster() != null){
			    paramAsigned++;
			    sbq.append(" and x.kitMaster = :kitMaster");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<MaterialKitMaster> nq = em.createQuery(sbq.toString(), MaterialKitMaster.class);
		
		if(paramAsigned>0){
			if(x.getMaterialKitMasterPK() != null){
			    nq.setParameter("materialKitMasterPK",x.getMaterialKitMasterPK());
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
			if(x.getCantidad()  != 0){
			    nq.setParameter("cantidad",x.getCantidad());
			}
			if(x.getReciclable() != null){
			    nq.setParameter("reciclable",x.getReciclable());
			}
			if(x.getObservaciones() != null){
			    nq.setParameter("observaciones",x.getObservaciones());
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
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
			}
			if(x.getKitMaster() != null){
			    nq.setParameter("kitMaster",x.getKitMaster());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public MaterialKitMaster findByPK_EAGER(Object pk){
        MaterialKitMaster x = getEntityManager().find(MaterialKitMaster.class, pk);
        if( x != null){
            if(x.getIdUnidadMedida() !=null && x.getIdUnidadMedida().getIdParametro()!=null){} 
            if(x.getIdInstruccionesNacionales() !=null && x.getIdInstruccionesNacionales().getIdParametro()!=null){} 
            if(x.getIdInstruccionesInternac() !=null && x.getIdInstruccionesInternac().getIdParametro()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 
            if(x.getKitMaster() !=null && x.getKitMaster().getCveKitMaster()!=null){} 

        }
        return x;
    }

	@Override
	public List<MaterialKitMaster> findAll() {
		TypedQuery<MaterialKitMaster> nq = em.createNamedQuery("MaterialKitMaster.findAll", MaterialKitMaster.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("MaterialKitMaster.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
