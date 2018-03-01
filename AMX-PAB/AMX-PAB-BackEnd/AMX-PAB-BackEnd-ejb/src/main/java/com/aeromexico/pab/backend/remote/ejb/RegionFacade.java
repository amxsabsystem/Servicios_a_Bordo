package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Region;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table region by RegionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "region_RSB",
    mappedName   = "Region_RSB",
    description  = "RegionFacadeRemote-Stateless-Session EJB-3.1"
)
public class RegionFacade extends AbstractFacade<Region> implements RegionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RegionFacade() {
		super(Region.class);
	}

	@Override
	public Region create(Region entity) {
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
        final List<RegionAbordaje> entity_regionAbordajeHasRegionList =  entity.getRegionAbordajeHasRegionList();
        entity.setRegionAbordajeHasRegionList(null);
        final List<AsignacionServicio> entity_asignacionServicioHasRegionList =  entity.getAsignacionServicioHasRegionList();
        entity.setAsignacionServicioHasRegionList(null);
        final List<Pais> entity_paisHasRegionList =  entity.getPaisHasRegionList();
        entity.setPaisHasRegionList(null);
        final List<Horario> entity_horarioHasRegionList =  entity.getHorarioHasRegionList();
        entity.setHorarioHasRegionList(null);
        final List<HorarioRegion> entity_horarioRegionHasRegionList =  entity.getHorarioRegionHasRegionList();
        entity.setHorarioRegionHasRegionList(null);
        final List<Ciclo> entity_cicloHasRegionList =  entity.getCicloHasRegionList();
        entity.setCicloHasRegionList(null);
        final List<Proveedor> entity_proveedorList =  entity.getProveedorList();
        entity.setProveedorList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setRegionAbordajeHasRegionList(entity_regionAbordajeHasRegionList);
        entity.setAsignacionServicioHasRegionList(entity_asignacionServicioHasRegionList);
        entity.setPaisHasRegionList(entity_paisHasRegionList);
        entity.setHorarioHasRegionList(entity_horarioHasRegionList);
        entity.setHorarioRegionHasRegionList(entity_horarioRegionHasRegionList);
        entity.setCicloHasRegionList(entity_cicloHasRegionList);
        entity.setProveedorList(entity_proveedorList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Region update(Region entity) {
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
        final List<RegionAbordaje> entity_regionAbordajeHasRegionList =  entity.getRegionAbordajeHasRegionList();
        entity.setRegionAbordajeHasRegionList(null);
        final List<AsignacionServicio> entity_asignacionServicioHasRegionList =  entity.getAsignacionServicioHasRegionList();
        entity.setAsignacionServicioHasRegionList(null);
        final List<Pais> entity_paisHasRegionList =  entity.getPaisHasRegionList();
        entity.setPaisHasRegionList(null);
        final List<Horario> entity_horarioHasRegionList =  entity.getHorarioHasRegionList();
        entity.setHorarioHasRegionList(null);
        final List<HorarioRegion> entity_horarioRegionHasRegionList =  entity.getHorarioRegionHasRegionList();
        entity.setHorarioRegionHasRegionList(null);
        final List<Ciclo> entity_cicloHasRegionList =  entity.getCicloHasRegionList();
        entity.setCicloHasRegionList(null);
        final List<Proveedor> entity_proveedorList =  entity.getProveedorList();
        entity.setProveedorList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setRegionAbordajeHasRegionList(entity_regionAbordajeHasRegionList);
        entity.setAsignacionServicioHasRegionList(entity_asignacionServicioHasRegionList);
        entity.setPaisHasRegionList(entity_paisHasRegionList);
        entity.setHorarioHasRegionList(entity_horarioHasRegionList);
        entity.setHorarioRegionHasRegionList(entity_horarioRegionHasRegionList);
        entity.setCicloHasRegionList(entity_cicloHasRegionList);
        entity.setProveedorList(entity_proveedorList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Region> findAllLike(Region x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Region x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idRegion = :idRegion");
			}
			if(x.getCveRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.cveRegion = :cveRegion");
			}
			if(x.getNombre() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombre = :nombre");
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
		
		TypedQuery<Region> nq = em.createQuery(sbq.toString(), Region.class);
		
		if(paramAsigned>0){
			if(x.getIdRegion() != null){
			    nq.setParameter("idRegion",x.getIdRegion());
			}
			if(x.getCveRegion() != null){
			    nq.setParameter("cveRegion",x.getCveRegion());
			}
			if(x.getNombre() != null){
			    nq.setParameter("nombre",x.getNombre());
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
	public Region findByPK_EAGER(Object pk){
        Region x = getEntityManager().find(Region.class, pk);
        if( x != null){
            for(RegionAbordaje x_regionAbordaje: x.getRegionAbordajeHasRegionList() ) {} 
            for(AsignacionServicio x_asignacionServicio: x.getAsignacionServicioHasRegionList() ) {} 
            for(Pais x_pais: x.getPaisHasRegionList() ) {} 
            for(Horario x_horario: x.getHorarioHasRegionList() ) {} 
            for(HorarioRegion x_horarioRegion: x.getHorarioRegionHasRegionList() ) {} 
            for(Ciclo x_ciclo: x.getCicloHasRegionList() ) {} 
            for(Proveedor x_Proveedor: x.getProveedorList() ) {} 

        }
        return x;
    }

	@Override
	public List<Region> findAll() {
		TypedQuery<Region> nq = em.createNamedQuery("Region.findAll", Region.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Region.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
