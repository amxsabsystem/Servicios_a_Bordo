package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table empleado by EmpleadoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "empleado_RSB",
    mappedName   = "Empleado_RSB",
    description  = "EmpleadoFacadeRemote-Stateless-Session EJB-3.1"
)
public class EmpleadoFacade extends AbstractFacade<Empleado> implements EmpleadoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EmpleadoFacade() {
		super(Empleado.class);
	}

	@Override
	public Empleado create(Empleado entity) {
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
        final List<ResponsableEstacion> entity_responsableEstacionHasEmpleadoJefeList =  entity.getResponsableEstacionHasEmpleadoJefeList();
        entity.setResponsableEstacionHasEmpleadoJefeList(null);
        final List<ResponsableEstacion> entity_responsableEstacionHasEmpleadoList =  entity.getResponsableEstacionHasEmpleadoList();
        entity.setResponsableEstacionHasEmpleadoList(null);
        final List<Comunicado> entity_comunicadoHasEmpleadoList =  entity.getComunicadoHasEmpleadoList();
        entity.setComunicadoHasEmpleadoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setResponsableEstacionHasEmpleadoJefeList(entity_responsableEstacionHasEmpleadoJefeList);
        entity.setResponsableEstacionHasEmpleadoList(entity_responsableEstacionHasEmpleadoList);
        entity.setComunicadoHasEmpleadoList(entity_comunicadoHasEmpleadoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Empleado update(Empleado entity) {
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
        final List<ResponsableEstacion> entity_responsableEstacionHasEmpleadoJefeList =  entity.getResponsableEstacionHasEmpleadoJefeList();
        entity.setResponsableEstacionHasEmpleadoJefeList(null);
        final List<ResponsableEstacion> entity_responsableEstacionHasEmpleadoList =  entity.getResponsableEstacionHasEmpleadoList();
        entity.setResponsableEstacionHasEmpleadoList(null);
        final List<Comunicado> entity_comunicadoHasEmpleadoList =  entity.getComunicadoHasEmpleadoList();
        entity.setComunicadoHasEmpleadoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setResponsableEstacionHasEmpleadoJefeList(entity_responsableEstacionHasEmpleadoJefeList);
        entity.setResponsableEstacionHasEmpleadoList(entity_responsableEstacionHasEmpleadoList);
        entity.setComunicadoHasEmpleadoList(entity_comunicadoHasEmpleadoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Empleado> findAllLike(Empleado x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Empleado x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdEmpleado() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEmpleado = :idEmpleado");
			}
			if(x.getUsuario() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuario = :usuario");
			}
			if(x.getCompania() != null){
			    paramAsigned++;
			    sbq.append(" and x.compania = :compania");
			}
			if(x.getArea() != null){
			    paramAsigned++;
			    sbq.append(" and x.area = :area");
			}
			if(x.getEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacion = :estacion");
			}
			if(x.getEmailJefe() != null){
			    paramAsigned++;
			    sbq.append(" and x.emailJefe = :emailJefe");
			}
			if(x.getTelefono() != null){
			    paramAsigned++;
			    sbq.append(" and x.telefono = :telefono");
			}
			if(x.getExtension() != null){
			    paramAsigned++;
			    sbq.append(" and x.extension = :extension");
			}
			if(x.getDirectorioSab() != null){
			    paramAsigned++;
			    sbq.append(" and x.directorioSab = :directorioSab");
			}
			if(x.getPuestoEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.puestoEs = :puestoEs");
			}
			if(x.getPuestoEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.puestoEn = :puestoEn");
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
		
		TypedQuery<Empleado> nq = em.createQuery(sbq.toString(), Empleado.class);
		
		if(paramAsigned>0){
			if(x.getIdEmpleado() != null){
			    nq.setParameter("idEmpleado",x.getIdEmpleado());
			}
			if(x.getUsuario() != null){
			    nq.setParameter("usuario",x.getUsuario());
			}
			if(x.getCompania() != null){
			    nq.setParameter("compania",x.getCompania());
			}
			if(x.getArea() != null){
			    nq.setParameter("area",x.getArea());
			}
			if(x.getEstacion() != null){
			    nq.setParameter("estacion",x.getEstacion());
			}
			if(x.getEmailJefe() != null){
			    nq.setParameter("emailJefe",x.getEmailJefe());
			}
			if(x.getTelefono() != null){
			    nq.setParameter("telefono",x.getTelefono());
			}
			if(x.getExtension() != null){
			    nq.setParameter("extension",x.getExtension());
			}
			if(x.getDirectorioSab() != null){
			    nq.setParameter("directorioSab",x.getDirectorioSab());
			}
			if(x.getPuestoEs() != null){
			    nq.setParameter("puestoEs",x.getPuestoEs());
			}
			if(x.getPuestoEn() != null){
			    nq.setParameter("puestoEn",x.getPuestoEn());
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
	public Empleado findByPK_EAGER(Object pk){
        Empleado x = getEntityManager().find(Empleado.class, pk);
        if( x != null){
            if(x.getUsuario() !=null && x.getUsuario().getEmailUsuario()!=null){} 
            if(x.getCompania() !=null && x.getCompania().getIdCompania()!=null){} 
            if(x.getArea() !=null && x.getArea().getIdArea()!=null){} 
            if(x.getEstacion() !=null && x.getEstacion().getIdEstacion()!=null){} 
            for(ResponsableEstacion x_responsableEstacion: x.getResponsableEstacionHasEmpleadoJefeList() ) {} 
            for(ResponsableEstacion x_responsableEstacion: x.getResponsableEstacionHasEmpleadoList() ) {} 
            for(Comunicado x_comunicado: x.getComunicadoHasEmpleadoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Empleado> findAll() {
		TypedQuery<Empleado> nq = em.createNamedQuery("Empleado.findAll", Empleado.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Empleado.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
