package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.RegionAbordajeEstacion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.RegionAbordajeEstacionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table region_abordaje_estacion by RegionAbordajeEstacionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "regionAbordajeEstacion_RSB",
    mappedName   = "RegionAbordajeEstacion_RSB",
    description  = "RegionAbordajeEstacionFacadeRemote-Stateless-Session EJB-3.1"
)
public class RegionAbordajeEstacionFacade extends AbstractFacade<RegionAbordajeEstacion> implements RegionAbordajeEstacionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RegionAbordajeEstacionFacade() {
		super(RegionAbordajeEstacion.class);
	}

	@Override
	public RegionAbordajeEstacion create(RegionAbordajeEstacion entity) {
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
	public RegionAbordajeEstacion update(RegionAbordajeEstacion entity) {
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
	public List<RegionAbordajeEstacion> findAllLike(RegionAbordajeEstacion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM RegionAbordajeEstacion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdDetalleEstacionAbordaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.idDetalleEstacionAbordaje = :idDetalleEstacionAbordaje");
			}
			if(x.getRegionAbordaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.regionAbordaje = :regionAbordaje");
			}
			if(x.getEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacion = :estacion");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<RegionAbordajeEstacion> nq = em.createQuery(sbq.toString(), RegionAbordajeEstacion.class);
		
		if(paramAsigned>0){
			if(x.getIdDetalleEstacionAbordaje() != null){
			    nq.setParameter("idDetalleEstacionAbordaje",x.getIdDetalleEstacionAbordaje());
			}
			if(x.getRegionAbordaje() != null){
			    nq.setParameter("regionAbordaje",x.getRegionAbordaje());
			}
			if(x.getEstacion() != null){
			    nq.setParameter("estacion",x.getEstacion());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public RegionAbordajeEstacion findByPK_EAGER(Object pk){
        RegionAbordajeEstacion x = getEntityManager().find(RegionAbordajeEstacion.class, pk);
        if( x != null){
            if(x.getRegionAbordaje() !=null && x.getRegionAbordaje().getIdRegionAbordaje()!=null){} 
            if(x.getEstacion() !=null && x.getEstacion().getIdEstacion()!=null){} 

        }
        return x;
    }

	@Override
	public List<RegionAbordajeEstacion> findAll() {
		TypedQuery<RegionAbordajeEstacion> nq = em.createNamedQuery("RegionAbordajeEstacion.findAll", RegionAbordajeEstacion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("RegionAbordajeEstacion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
