package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table contacto_proveedor_estacion by ContactoProveedorEstacionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "contactoProveedorEstacion_RSB",
    mappedName   = "ContactoProveedorEstacion_RSB",
    description  = "ContactoProveedorEstacionFacadeRemote-Stateless-Session EJB-3.1"
)
public class ContactoProveedorEstacionFacade extends AbstractFacade<ContactoProveedorEstacion> implements ContactoProveedorEstacionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ContactoProveedorEstacionFacade() {
		super(ContactoProveedorEstacion.class);
	}

	@Override
	public ContactoProveedorEstacion create(ContactoProveedorEstacion entity) {
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
        final List<BitacoraComunicado> entity_bitacoraComunicadoHasContactoProveedorEstacionList =  entity.getBitacoraComunicadoHasContactoProveedorEstacionList();
        entity.setBitacoraComunicadoHasContactoProveedorEstacionList(null);
        final List<Seguimiento> entity_seguimientoHasContactoProveedorEstacionList =  entity.getSeguimientoHasContactoProveedorEstacionList();
        entity.setSeguimientoHasContactoProveedorEstacionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setBitacoraComunicadoHasContactoProveedorEstacionList(entity_bitacoraComunicadoHasContactoProveedorEstacionList);
        entity.setSeguimientoHasContactoProveedorEstacionList(entity_seguimientoHasContactoProveedorEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public ContactoProveedorEstacion update(ContactoProveedorEstacion entity) {
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
        final List<BitacoraComunicado> entity_bitacoraComunicadoHasContactoProveedorEstacionList =  entity.getBitacoraComunicadoHasContactoProveedorEstacionList();
        entity.setBitacoraComunicadoHasContactoProveedorEstacionList(null);
        final List<Seguimiento> entity_seguimientoHasContactoProveedorEstacionList =  entity.getSeguimientoHasContactoProveedorEstacionList();
        entity.setSeguimientoHasContactoProveedorEstacionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setBitacoraComunicadoHasContactoProveedorEstacionList(entity_bitacoraComunicadoHasContactoProveedorEstacionList);
        entity.setSeguimientoHasContactoProveedorEstacionList(entity_seguimientoHasContactoProveedorEstacionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<ContactoProveedorEstacion> findAllLike(ContactoProveedorEstacion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ContactoProveedorEstacion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdContactoProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idContactoProveedorEstacion = :idContactoProveedorEstacion");
			}
			if(x.getUsuario() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuario = :usuario");
			}
			if(x.getNombre() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombre = :nombre");
			}
			if(x.getApellidoPaterno() != null){
			    paramAsigned++;
			    sbq.append(" and x.apellidoPaterno = :apellidoPaterno");
			}
			if(x.getApellidoMaterno() != null){
			    paramAsigned++;
			    sbq.append(" and x.apellidoMaterno = :apellidoMaterno");
			}
			if(x.getProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedor = :proveedor");
			}
			if(x.getEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacion = :estacion");
			}
			if(x.getProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacion = :proveedorEstacion");
			}
			if(x.getTelefono() != null){
			    paramAsigned++;
			    sbq.append(" and x.telefono = :telefono");
			}
			if(x.getExtension() != null){
			    paramAsigned++;
			    sbq.append(" and x.extension = :extension");
			}
			if(x.getPuestoAreaEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.puestoAreaEn = :puestoAreaEn");
			}
			if(x.getPuestoAreaEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.puestoAreaEs = :puestoAreaEs");
			}
			if(x.getDirectorioPab() != null){
			    paramAsigned++;
			    sbq.append(" and x.directorioPab = :directorioPab");
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
		
		TypedQuery<ContactoProveedorEstacion> nq = em.createQuery(sbq.toString(), ContactoProveedorEstacion.class);
		
		if(paramAsigned>0){
			if(x.getIdContactoProveedorEstacion() != null){
			    nq.setParameter("idContactoProveedorEstacion",x.getIdContactoProveedorEstacion());
			}
			if(x.getUsuario() != null){
			    nq.setParameter("usuario",x.getUsuario());
			}
			if(x.getNombre() != null){
			    nq.setParameter("nombre",x.getNombre());
			}
			if(x.getApellidoPaterno() != null){
			    nq.setParameter("apellidoPaterno",x.getApellidoPaterno());
			}
			if(x.getApellidoMaterno() != null){
			    nq.setParameter("apellidoMaterno",x.getApellidoMaterno());
			}
			if(x.getProveedor() != null){
			    nq.setParameter("proveedor",x.getProveedor());
			}
			if(x.getEstacion() != null){
			    nq.setParameter("estacion",x.getEstacion());
			}
			if(x.getProveedorEstacion() != null){
			    nq.setParameter("proveedorEstacion",x.getProveedorEstacion());
			}
			if(x.getTelefono() != null){
			    nq.setParameter("telefono",x.getTelefono());
			}
			if(x.getExtension() != null){
			    nq.setParameter("extension",x.getExtension());
			}
			if(x.getPuestoAreaEn() != null){
			    nq.setParameter("puestoAreaEn",x.getPuestoAreaEn());
			}
			if(x.getPuestoAreaEs() != null){
			    nq.setParameter("puestoAreaEs",x.getPuestoAreaEs());
			}
			if(x.getDirectorioPab() != null){
			    nq.setParameter("directorioPab",x.getDirectorioPab());
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
	public ContactoProveedorEstacion findByPK_EAGER(Object pk){
        ContactoProveedorEstacion x = getEntityManager().find(ContactoProveedorEstacion.class, pk);
        if( x != null){
            if(x.getUsuario() !=null && x.getUsuario().getEmailUsuario()!=null){} 
            if(x.getProveedor() !=null && x.getProveedor().getClaveProveedor()!=null){} 
            if(x.getEstacion() !=null && x.getEstacion().getIdEstacion()!=null){} 
            if(x.getProveedorEstacion() !=null && x.getProveedorEstacion().getIdProveedorEstacion()!=null){} 
            for(BitacoraComunicado x_bitacoraComunicado: x.getBitacoraComunicadoHasContactoProveedorEstacionList() ) {} 
            for(Seguimiento x_seguimiento: x.getSeguimientoHasContactoProveedorEstacionList() ) {} 

        }
        return x;
    }

	@Override
	public List<ContactoProveedorEstacion> findAll() {
		TypedQuery<ContactoProveedorEstacion> nq = em.createNamedQuery("ContactoProveedorEstacion.findAll", ContactoProveedorEstacion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ContactoProveedorEstacion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
