package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.MaterialExtra;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MaterialExtraFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table material_extra by MaterialExtraFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "materialExtra_RSB",
    mappedName   = "MaterialExtra_RSB",
    description  = "MaterialExtraFacadeRemote-Stateless-Session EJB-3.1"
)
public class MaterialExtraFacade extends AbstractFacade<MaterialExtra> implements MaterialExtraFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MaterialExtraFacade() {
		super(MaterialExtra.class);
	}

	@Override
	public MaterialExtra create(MaterialExtra entity) {
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
	public MaterialExtra update(MaterialExtra entity) {
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
	public List<MaterialExtra> findAllLike(MaterialExtra x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM MaterialExtra x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdMaterialExtra() != null){
			    paramAsigned++;
			    sbq.append(" and x.idMaterialExtra = :idMaterialExtra");
			}
			if(x.getDetalleTablaComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.detalleTablaComplementario = :detalleTablaComplementario");
			}
			if(x.getKitMaster() != null){
			    paramAsigned++;
			    sbq.append(" and x.kitMaster = :kitMaster");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
			}
			if(x.getMaterialAmx() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialAmx = :materialAmx");
			}
			if(x.getMaterialMontaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialMontaje = :materialMontaje");
			}
			if(x.getMaterialMontajeAmx() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialMontajeAmx = :materialMontajeAmx");
			}
			if(x.getInstrucciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.instrucciones = :instrucciones");
			}
			if(x.getMaterialComisariato() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialComisariato = :materialComisariato");
			}
			if(x.getMaterialComisariatoMontaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialComisariatoMontaje = :materialComisariatoMontaje");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<MaterialExtra> nq = em.createQuery(sbq.toString(), MaterialExtra.class);
		
		if(paramAsigned>0){
			if(x.getIdMaterialExtra() != null){
			    nq.setParameter("idMaterialExtra",x.getIdMaterialExtra());
			}
			if(x.getDetalleTablaComplementario() != null){
			    nq.setParameter("detalleTablaComplementario",x.getDetalleTablaComplementario());
			}
			if(x.getKitMaster() != null){
			    nq.setParameter("kitMaster",x.getKitMaster());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
			}
			if(x.getMaterialAmx() != null){
			    nq.setParameter("materialAmx",x.getMaterialAmx());
			}
			if(x.getMaterialMontaje() != null){
			    nq.setParameter("materialMontaje",x.getMaterialMontaje());
			}
			if(x.getMaterialMontajeAmx() != null){
			    nq.setParameter("materialMontajeAmx",x.getMaterialMontajeAmx());
			}
			if(x.getInstrucciones() != null){
			    nq.setParameter("instrucciones",x.getInstrucciones());
			}
			if(x.getMaterialComisariato() != null){
			    nq.setParameter("materialComisariato",x.getMaterialComisariato());
			}
			if(x.getMaterialComisariatoMontaje() != null){
			    nq.setParameter("materialComisariatoMontaje",x.getMaterialComisariatoMontaje());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public MaterialExtra findByPK_EAGER(Object pk){
        MaterialExtra x = getEntityManager().find(MaterialExtra.class, pk);
        if( x != null){
            if(x.getDetalleTablaComplementario() !=null && x.getDetalleTablaComplementario().getIdDetalleTablaComplementario()!=null){} 
            if(x.getKitMaster() !=null && x.getKitMaster().getCveKitMaster()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 
            if(x.getMaterialAmx() !=null && x.getMaterialAmx().getNumeroParte()!=null){} 
            if(x.getMaterialMontaje() !=null && x.getMaterialMontaje().getNumeroParte()!=null){} 
            if(x.getMaterialMontajeAmx() !=null && x.getMaterialMontajeAmx().getNumeroParte()!=null){} 

        }
        return x;
    }

	@Override
	public List<MaterialExtra> findAll() {
		TypedQuery<MaterialExtra> nq = em.createNamedQuery("MaterialExtra.findAll", MaterialExtra.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("MaterialExtra.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
