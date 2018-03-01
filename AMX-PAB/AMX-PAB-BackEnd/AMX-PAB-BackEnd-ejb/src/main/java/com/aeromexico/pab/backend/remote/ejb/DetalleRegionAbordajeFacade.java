package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.DetalleRegionAbordaje;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.DetalleRegionAbordajeFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table detalle_region_abordaje by DetalleRegionAbordajeFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "detalleRegionAbordaje_RSB",
    mappedName   = "DetalleRegionAbordaje_RSB",
    description  = "DetalleRegionAbordajeFacadeRemote-Stateless-Session EJB-3.1"
)
public class DetalleRegionAbordajeFacade extends AbstractFacade<DetalleRegionAbordaje> implements DetalleRegionAbordajeFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DetalleRegionAbordajeFacade() {
		super(DetalleRegionAbordaje.class);
	}

	@Override
	public DetalleRegionAbordaje create(DetalleRegionAbordaje entity) {
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
	public DetalleRegionAbordaje update(DetalleRegionAbordaje entity) {
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
	public List<DetalleRegionAbordaje> findAllLike(DetalleRegionAbordaje x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM DetalleRegionAbordaje x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getDetalleRegionAbordajePK() != null){
			    paramAsigned++;
			    sbq.append(" and x.detalleRegionAbordajePK = :detalleRegionAbordajePK");
			}
			if(x.getTodasEstaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.todasEstaciones = :todasEstaciones");
			}
			if(x.getTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tablaAbordamiento = :tablaAbordamiento");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<DetalleRegionAbordaje> nq = em.createQuery(sbq.toString(), DetalleRegionAbordaje.class);
		
		if(paramAsigned>0){
			if(x.getDetalleRegionAbordajePK() != null){
			    nq.setParameter("detalleRegionAbordajePK",x.getDetalleRegionAbordajePK());
			}
			if(x.getTodasEstaciones() != null){
			    nq.setParameter("todasEstaciones",x.getTodasEstaciones());
			}
			if(x.getTablaAbordamiento() != null){
			    nq.setParameter("tablaAbordamiento",x.getTablaAbordamiento());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public DetalleRegionAbordaje findByPK_EAGER(Object pk){
        DetalleRegionAbordaje x = getEntityManager().find(DetalleRegionAbordaje.class, pk);
        if( x != null){
            if(x.getTablaAbordamiento() !=null && x.getTablaAbordamiento().getIdTablaAbordamiento()!=null){} 
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 

        }
        return x;
    }

	@Override
	public List<DetalleRegionAbordaje> findAll() {
		TypedQuery<DetalleRegionAbordaje> nq = em.createNamedQuery("DetalleRegionAbordaje.findAll", DetalleRegionAbordaje.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("DetalleRegionAbordaje.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
