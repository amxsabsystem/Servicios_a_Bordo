package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Usuario;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table usuario by UsuarioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "usuario_RSB",
    mappedName   = "Usuario_RSB",
    description  = "UsuarioFacadeRemote-Stateless-Session EJB-3.1"
)
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioFacade() {
		super(Usuario.class);
	}

	@Override
	public Usuario create(Usuario entity) {
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
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasUsuarioList =  entity.getContactoProveedorEstacionHasUsuarioList();
        entity.setContactoProveedorEstacionHasUsuarioList(null);
        final List<Empleado> entity_empleadoHasUsuarioList =  entity.getEmpleadoHasUsuarioList();
        entity.setEmpleadoHasUsuarioList(null);
        final List<Perfil> entity_perfilList =  entity.getPerfilList();
        entity.setPerfilList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setContactoProveedorEstacionHasUsuarioList(entity_contactoProveedorEstacionHasUsuarioList);
        entity.setEmpleadoHasUsuarioList(entity_empleadoHasUsuarioList);
        entity.setPerfilList(entity_perfilList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Usuario update(Usuario entity) {
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
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasUsuarioList =  entity.getContactoProveedorEstacionHasUsuarioList();
        entity.setContactoProveedorEstacionHasUsuarioList(null);
        final List<Empleado> entity_empleadoHasUsuarioList =  entity.getEmpleadoHasUsuarioList();
        entity.setEmpleadoHasUsuarioList(null);
        final List<Perfil> entity_perfilList =  entity.getPerfilList();
        entity.setPerfilList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setContactoProveedorEstacionHasUsuarioList(entity_contactoProveedorEstacionHasUsuarioList);
        entity.setEmpleadoHasUsuarioList(entity_empleadoHasUsuarioList);
        entity.setPerfilList(entity_perfilList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Usuario> findAllLike(Usuario x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Usuario x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getEmailUsuario() != null){
			    paramAsigned++;
			    sbq.append(" and x.emailUsuario = :emailUsuario");
			}
			if(x.getContrasenia() != null){
			    paramAsigned++;
			    sbq.append(" and x.contrasenia = :contrasenia");
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
			if(x.getTipo() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipo = :tipo");
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
		
		TypedQuery<Usuario> nq = em.createQuery(sbq.toString(), Usuario.class);
		
		if(paramAsigned>0){
			if(x.getEmailUsuario() != null){
			    nq.setParameter("emailUsuario",x.getEmailUsuario());
			}
			if(x.getContrasenia() != null){
			    nq.setParameter("contrasenia",x.getContrasenia());
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
			if(x.getTipo() != null){
			    nq.setParameter("tipo",x.getTipo());
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
	public Usuario findByPK_EAGER(Object pk){
        Usuario x = getEntityManager().find(Usuario.class, pk);
        if( x != null){
            for(ContactoProveedorEstacion x_contactoProveedorEstacion: x.getContactoProveedorEstacionHasUsuarioList() ) {} 
            for(Empleado x_empleado: x.getEmpleadoHasUsuarioList() ) {} 
            for(Perfil x_Perfil: x.getPerfilList() ) {} 

        }
        return x;
    }

	@Override
	public List<Usuario> findAll() {
		TypedQuery<Usuario> nq = em.createNamedQuery("Usuario.findAll", Usuario.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Usuario.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
