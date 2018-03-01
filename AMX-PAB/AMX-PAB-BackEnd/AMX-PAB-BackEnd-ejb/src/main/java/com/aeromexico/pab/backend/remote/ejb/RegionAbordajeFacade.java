package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.RegionAbordaje;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.RegionAbordajeFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table region_abordaje by RegionAbordajeFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "regionAbordaje_RSB",
    mappedName   = "RegionAbordaje_RSB",
    description  = "RegionAbordajeFacadeRemote-Stateless-Session EJB-3.1"
)
public class RegionAbordajeFacade extends AbstractFacade<RegionAbordaje> implements RegionAbordajeFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RegionAbordajeFacade() {
		super(RegionAbordaje.class);
	}

	@Override
	public RegionAbordaje create(RegionAbordaje entity) {
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
        final List<RegionAbordajeEstacion> entity_regionAbordajeEstacionHasRegionAbordajeList =  entity.getRegionAbordajeEstacionHasRegionAbordajeList();
        entity.setRegionAbordajeEstacionHasRegionAbordajeList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setRegionAbordajeEstacionHasRegionAbordajeList(entity_regionAbordajeEstacionHasRegionAbordajeList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public RegionAbordaje update(RegionAbordaje entity) {
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
        final List<RegionAbordajeEstacion> entity_regionAbordajeEstacionHasRegionAbordajeList =  entity.getRegionAbordajeEstacionHasRegionAbordajeList();
        entity.setRegionAbordajeEstacionHasRegionAbordajeList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setRegionAbordajeEstacionHasRegionAbordajeList(entity_regionAbordajeEstacionHasRegionAbordajeList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<RegionAbordaje> findAllLike(RegionAbordaje x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM RegionAbordaje x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdRegionAbordaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.idRegionAbordaje = :idRegionAbordaje");
			}
			if(x.getTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tablaAbordamiento = :tablaAbordamiento");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
			}
			if(x.getTodasEstaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.todasEstaciones = :todasEstaciones");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<RegionAbordaje> nq = em.createQuery(sbq.toString(), RegionAbordaje.class);
		
		if(paramAsigned>0){
			if(x.getIdRegionAbordaje() != null){
			    nq.setParameter("idRegionAbordaje",x.getIdRegionAbordaje());
			}
			if(x.getTablaAbordamiento() != null){
			    nq.setParameter("tablaAbordamiento",x.getTablaAbordamiento());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
			}
			if(x.getTodasEstaciones() != null){
			    nq.setParameter("todasEstaciones",x.getTodasEstaciones());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public RegionAbordaje findByPK_EAGER(Object pk){
        RegionAbordaje x = getEntityManager().find(RegionAbordaje.class, pk);
        if( x != null){
            if(x.getTablaAbordamiento() !=null && x.getTablaAbordamiento().getIdTablaAbordamiento()!=null){} 
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 
            for(RegionAbordajeEstacion x_regionAbordajeEstacion: x.getRegionAbordajeEstacionHasRegionAbordajeList() ) {} 

        }
        return x;
    }

	@Override
	public List<RegionAbordaje> findAll() {
		TypedQuery<RegionAbordaje> nq = em.createNamedQuery("RegionAbordaje.findAll", RegionAbordaje.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("RegionAbordaje.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
