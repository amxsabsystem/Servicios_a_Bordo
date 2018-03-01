package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table estacion by EstacionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "estacion_RSB",
    mappedName   = "Estacion_RSB",
    description  = "EstacionFacadeRemote-Stateless-Session EJB-3.1"
)
public class EstacionFacade extends AbstractFacade<Estacion> implements EstacionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EstacionFacade() {
		super(Estacion.class);
	}

	@Override
	public Estacion create(Estacion entity) {
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
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasEstacionList =  entity.getContactoProveedorEstacionHasEstacionList();
        entity.setContactoProveedorEstacionHasEstacionList(null);
        final List<Vuelo> entity_vueloHasEstacionDestinoList =  entity.getVueloHasEstacionDestinoList();
        entity.setVueloHasEstacionDestinoList(null);
        final List<Vuelo> entity_vueloHasEstacionOrigenList =  entity.getVueloHasEstacionOrigenList();
        entity.setVueloHasEstacionOrigenList(null);
        final List<ResponsableEstacion> entity_responsableEstacionHasEstacionList =  entity.getResponsableEstacionHasEstacionList();
        entity.setResponsableEstacionHasEstacionList(null);
        final List<Empleado> entity_empleadoHasEstacionList =  entity.getEmpleadoHasEstacionList();
        entity.setEmpleadoHasEstacionList(null);
        final List<ProveedorEstacion> entity_proveedorEstacionHasEstacionList =  entity.getProveedorEstacionHasEstacionList();
        entity.setProveedorEstacionHasEstacionList(null);
        final List<Acomodo> entity_acomodoHasEstacionDestinoList =  entity.getAcomodoHasEstacionDestinoList();
        entity.setAcomodoHasEstacionDestinoList(null);
        final List<Acomodo> entity_acomodoHasEstacionOrigenList =  entity.getAcomodoHasEstacionOrigenList();
        entity.setAcomodoHasEstacionOrigenList(null);
        final List<Ciclo> entity_cicloHasEstacionList =  entity.getCicloHasEstacionList();
        entity.setCicloHasEstacionList(null);
        final List<RegionAbordajeEstacion> entity_regionAbordajeEstacionHasEstacionList =  entity.getRegionAbordajeEstacionHasEstacionList();
        entity.setRegionAbordajeEstacionHasEstacionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setContactoProveedorEstacionHasEstacionList(entity_contactoProveedorEstacionHasEstacionList);
        entity.setVueloHasEstacionDestinoList(entity_vueloHasEstacionDestinoList);
        entity.setVueloHasEstacionOrigenList(entity_vueloHasEstacionOrigenList);
        entity.setResponsableEstacionHasEstacionList(entity_responsableEstacionHasEstacionList);
        entity.setEmpleadoHasEstacionList(entity_empleadoHasEstacionList);
        entity.setProveedorEstacionHasEstacionList(entity_proveedorEstacionHasEstacionList);
        entity.setAcomodoHasEstacionDestinoList(entity_acomodoHasEstacionDestinoList);
        entity.setAcomodoHasEstacionOrigenList(entity_acomodoHasEstacionOrigenList);
        entity.setCicloHasEstacionList(entity_cicloHasEstacionList);
        entity.setRegionAbordajeEstacionHasEstacionList(entity_regionAbordajeEstacionHasEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Estacion update(Estacion entity) {
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
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasEstacionList =  entity.getContactoProveedorEstacionHasEstacionList();
        entity.setContactoProveedorEstacionHasEstacionList(null);
        final List<Vuelo> entity_vueloHasEstacionDestinoList =  entity.getVueloHasEstacionDestinoList();
        entity.setVueloHasEstacionDestinoList(null);
        final List<Vuelo> entity_vueloHasEstacionOrigenList =  entity.getVueloHasEstacionOrigenList();
        entity.setVueloHasEstacionOrigenList(null);
        final List<ResponsableEstacion> entity_responsableEstacionHasEstacionList =  entity.getResponsableEstacionHasEstacionList();
        entity.setResponsableEstacionHasEstacionList(null);
        final List<Empleado> entity_empleadoHasEstacionList =  entity.getEmpleadoHasEstacionList();
        entity.setEmpleadoHasEstacionList(null);
        final List<ProveedorEstacion> entity_proveedorEstacionHasEstacionList =  entity.getProveedorEstacionHasEstacionList();
        entity.setProveedorEstacionHasEstacionList(null);
        final List<Acomodo> entity_acomodoHasEstacionDestinoList =  entity.getAcomodoHasEstacionDestinoList();
        entity.setAcomodoHasEstacionDestinoList(null);
        final List<Acomodo> entity_acomodoHasEstacionOrigenList =  entity.getAcomodoHasEstacionOrigenList();
        entity.setAcomodoHasEstacionOrigenList(null);
        final List<Ciclo> entity_cicloHasEstacionList =  entity.getCicloHasEstacionList();
        entity.setCicloHasEstacionList(null);
        final List<RegionAbordajeEstacion> entity_regionAbordajeEstacionHasEstacionList =  entity.getRegionAbordajeEstacionHasEstacionList();
        entity.setRegionAbordajeEstacionHasEstacionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setContactoProveedorEstacionHasEstacionList(entity_contactoProveedorEstacionHasEstacionList);
        entity.setVueloHasEstacionDestinoList(entity_vueloHasEstacionDestinoList);
        entity.setVueloHasEstacionOrigenList(entity_vueloHasEstacionOrigenList);
        entity.setResponsableEstacionHasEstacionList(entity_responsableEstacionHasEstacionList);
        entity.setEmpleadoHasEstacionList(entity_empleadoHasEstacionList);
        entity.setProveedorEstacionHasEstacionList(entity_proveedorEstacionHasEstacionList);
        entity.setAcomodoHasEstacionDestinoList(entity_acomodoHasEstacionDestinoList);
        entity.setAcomodoHasEstacionOrigenList(entity_acomodoHasEstacionOrigenList);
        entity.setCicloHasEstacionList(entity_cicloHasEstacionList);
        entity.setRegionAbordajeEstacionHasEstacionList(entity_regionAbordajeEstacionHasEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Estacion> findAllLike(Estacion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Estacion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEstacion = :idEstacion");
			}
			if(x.getNombre() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombre = :nombre");
			}
			if(x.getClaveIata() != null){
			    paramAsigned++;
			    sbq.append(" and x.claveIata = :claveIata");
			}
			if(x.getCiudad() != null){
			    paramAsigned++;
			    sbq.append(" and x.ciudad = :ciudad");
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
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Estacion> nq = em.createQuery(sbq.toString(), Estacion.class);
		
		if(paramAsigned>0){
			if(x.getIdEstacion() != null){
			    nq.setParameter("idEstacion",x.getIdEstacion());
			}
			if(x.getNombre() != null){
			    nq.setParameter("nombre",x.getNombre());
			}
			if(x.getClaveIata() != null){
			    nq.setParameter("claveIata",x.getClaveIata());
			}
			if(x.getCiudad() != null){
			    nq.setParameter("ciudad",x.getCiudad());
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
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Estacion findByPK_EAGER(Object pk){
        Estacion x = getEntityManager().find(Estacion.class, pk);
        if( x != null){
            if(x.getCiudad() !=null && x.getCiudad().getIdCiudad()!=null){} 
            for(ContactoProveedorEstacion x_contactoProveedorEstacion: x.getContactoProveedorEstacionHasEstacionList() ) {} 
            for(Vuelo x_vuelo: x.getVueloHasEstacionDestinoList() ) {} 
            for(Vuelo x_vuelo: x.getVueloHasEstacionOrigenList() ) {} 
            for(ResponsableEstacion x_responsableEstacion: x.getResponsableEstacionHasEstacionList() ) {} 
            for(Empleado x_empleado: x.getEmpleadoHasEstacionList() ) {} 
            for(ProveedorEstacion x_proveedorEstacion: x.getProveedorEstacionHasEstacionList() ) {} 
            for(Acomodo x_acomodo: x.getAcomodoHasEstacionDestinoList() ) {} 
            for(Acomodo x_acomodo: x.getAcomodoHasEstacionOrigenList() ) {} 
            for(Ciclo x_ciclo: x.getCicloHasEstacionList() ) {} 
            for(RegionAbordajeEstacion x_regionAbordajeEstacion: x.getRegionAbordajeEstacionHasEstacionList() ) {} 

        }
        return x;
    }

	@Override
	public List<Estacion> findAll() {
		TypedQuery<Estacion> nq = em.createNamedQuery("Estacion.findAll", Estacion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Estacion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
