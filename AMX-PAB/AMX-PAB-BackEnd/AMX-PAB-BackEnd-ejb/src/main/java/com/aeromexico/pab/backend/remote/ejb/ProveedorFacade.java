package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProveedorFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table proveedor by ProveedorFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proveedor_RSB",
    mappedName   = "Proveedor_RSB",
    description  = "ProveedorFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProveedorFacade extends AbstractFacade<Proveedor> implements ProveedorFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProveedorFacade() {
		super(Proveedor.class);
	}

	@Override
	public Proveedor create(Proveedor entity) {
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
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasProveedorList =  entity.getContactoProveedorEstacionHasProveedorList();
        entity.setContactoProveedorEstacionHasProveedorList(null);
        final List<Potencial> entity_potencialHasProveedorList =  entity.getPotencialHasProveedorList();
        entity.setPotencialHasProveedorList(null);
        final List<ProveedorEstacion> entity_proveedorEstacionHasProveedorList =  entity.getProveedorEstacionHasProveedorList();
        entity.setProveedorEstacionHasProveedorList(null);
        final List<Ciclo> entity_cicloHasProveedorList =  entity.getCicloHasProveedorList();
        entity.setCicloHasProveedorList(null);
        final List<Region> entity_regionList =  entity.getRegionList();
        entity.setRegionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setContactoProveedorEstacionHasProveedorList(entity_contactoProveedorEstacionHasProveedorList);
        entity.setPotencialHasProveedorList(entity_potencialHasProveedorList);
        entity.setProveedorEstacionHasProveedorList(entity_proveedorEstacionHasProveedorList);
        entity.setCicloHasProveedorList(entity_cicloHasProveedorList);
        entity.setRegionList(entity_regionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Proveedor update(Proveedor entity) {
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
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasProveedorList =  entity.getContactoProveedorEstacionHasProveedorList();
        entity.setContactoProveedorEstacionHasProveedorList(null);
        final List<Potencial> entity_potencialHasProveedorList =  entity.getPotencialHasProveedorList();
        entity.setPotencialHasProveedorList(null);
        final List<ProveedorEstacion> entity_proveedorEstacionHasProveedorList =  entity.getProveedorEstacionHasProveedorList();
        entity.setProveedorEstacionHasProveedorList(null);
        final List<Ciclo> entity_cicloHasProveedorList =  entity.getCicloHasProveedorList();
        entity.setCicloHasProveedorList(null);
        final List<Region> entity_regionList =  entity.getRegionList();
        entity.setRegionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setContactoProveedorEstacionHasProveedorList(entity_contactoProveedorEstacionHasProveedorList);
        entity.setPotencialHasProveedorList(entity_potencialHasProveedorList);
        entity.setProveedorEstacionHasProveedorList(entity_proveedorEstacionHasProveedorList);
        entity.setCicloHasProveedorList(entity_cicloHasProveedorList);
        entity.setRegionList(entity_regionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Proveedor> findAllLike(Proveedor x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Proveedor x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getClaveProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.claveProveedor = :claveProveedor");
			}
			if(x.getNombre() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombre = :nombre");
			}
			if(x.getRazonSocial() != null){
			    paramAsigned++;
			    sbq.append(" and x.razonSocial = :razonSocial");
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
		
		TypedQuery<Proveedor> nq = em.createQuery(sbq.toString(), Proveedor.class);
		
		if(paramAsigned>0){
			if(x.getClaveProveedor() != null){
			    nq.setParameter("claveProveedor",x.getClaveProveedor());
			}
			if(x.getNombre() != null){
			    nq.setParameter("nombre",x.getNombre());
			}
			if(x.getRazonSocial() != null){
			    nq.setParameter("razonSocial",x.getRazonSocial());
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
	public Proveedor findByPK_EAGER(Object pk){
        Proveedor x = getEntityManager().find(Proveedor.class, pk);
        if( x != null){
            for(ContactoProveedorEstacion x_contactoProveedorEstacion: x.getContactoProveedorEstacionHasProveedorList() ) {} 
            for(Potencial x_potencial: x.getPotencialHasProveedorList() ) {} 
            for(ProveedorEstacion x_proveedorEstacion: x.getProveedorEstacionHasProveedorList() ) {} 
            for(Ciclo x_ciclo: x.getCicloHasProveedorList() ) {} 
            for(Region x_Region: x.getRegionList() ) {} 

        }
        return x;
    }

	@Override
	public List<Proveedor> findAll() {
		TypedQuery<Proveedor> nq = em.createNamedQuery("Proveedor.findAll", Proveedor.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Proveedor.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
