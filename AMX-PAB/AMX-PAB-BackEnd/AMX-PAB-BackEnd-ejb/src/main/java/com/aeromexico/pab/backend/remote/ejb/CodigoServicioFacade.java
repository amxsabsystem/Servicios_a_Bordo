package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.CodigoServicio;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CodigoServicioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Codigo_Servicio by CodigoServicioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "codigoServicio_RSB",
    mappedName   = "CodigoServicio_RSB",
    description  = "CodigoServicioFacadeRemote-Stateless-Session EJB-3.1"
)
public class CodigoServicioFacade extends AbstractFacade<CodigoServicio> implements CodigoServicioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CodigoServicioFacade() {
		super(CodigoServicio.class);
	}

	@Override
	public CodigoServicio create(CodigoServicio entity) {
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
        final List<Potencial> entity_potencialHasCodigoServicioList =  entity.getPotencialHasCodigoServicioList();
        entity.setPotencialHasCodigoServicioList(null);
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasCodigoServicioList =  entity.getAsignacionServicioDuracionHasCodigoServicioList();
        entity.setAsignacionServicioDuracionHasCodigoServicioList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoHasCodigoServicioList =  entity.getTablaAbordamientoHasCodigoServicioList();
        entity.setTablaAbordamientoHasCodigoServicioList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setPotencialHasCodigoServicioList(entity_potencialHasCodigoServicioList);
        entity.setAsignacionServicioDuracionHasCodigoServicioList(entity_asignacionServicioDuracionHasCodigoServicioList);
        entity.setTablaAbordamientoHasCodigoServicioList(entity_tablaAbordamientoHasCodigoServicioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public CodigoServicio update(CodigoServicio entity) {
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
        final List<Potencial> entity_potencialHasCodigoServicioList =  entity.getPotencialHasCodigoServicioList();
        entity.setPotencialHasCodigoServicioList(null);
        final List<AsignacionServicioDuracion> entity_asignacionServicioDuracionHasCodigoServicioList =  entity.getAsignacionServicioDuracionHasCodigoServicioList();
        entity.setAsignacionServicioDuracionHasCodigoServicioList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoHasCodigoServicioList =  entity.getTablaAbordamientoHasCodigoServicioList();
        entity.setTablaAbordamientoHasCodigoServicioList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setPotencialHasCodigoServicioList(entity_potencialHasCodigoServicioList);
        entity.setAsignacionServicioDuracionHasCodigoServicioList(entity_asignacionServicioDuracionHasCodigoServicioList);
        entity.setTablaAbordamientoHasCodigoServicioList(entity_tablaAbordamientoHasCodigoServicioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<CodigoServicio> findAllLike(CodigoServicio x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM CodigoServicio x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdCodigoServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.idCodigoServicio = :idCodigoServicio");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getCveCodigoServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.cveCodigoServicio = :cveCodigoServicio");
			}
			if(x.getDescripcion() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcion = :descripcion");
			}
			if(x.getIdTipoCodigoServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoCodigoServicio = :idTipoCodigoServicio");
			}
			if(x.getIdTipoCiclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoCiclo = :idTipoCiclo");
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
		
		TypedQuery<CodigoServicio> nq = em.createQuery(sbq.toString(), CodigoServicio.class);
		
		if(paramAsigned>0){
			if(x.getIdCodigoServicio() != null){
			    nq.setParameter("idCodigoServicio",x.getIdCodigoServicio());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getCveCodigoServicio() != null){
			    nq.setParameter("cveCodigoServicio",x.getCveCodigoServicio());
			}
			if(x.getDescripcion() != null){
			    nq.setParameter("descripcion",x.getDescripcion());
			}
			if(x.getIdTipoCodigoServicio() != null){
			    nq.setParameter("idTipoCodigoServicio",x.getIdTipoCodigoServicio());
			}
			if(x.getIdTipoCiclo() != null){
			    nq.setParameter("idTipoCiclo",x.getIdTipoCiclo());
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
	public CodigoServicio findByPK_EAGER(Object pk){
        CodigoServicio x = getEntityManager().find(CodigoServicio.class, pk);
        if( x != null){
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getIdTipoCodigoServicio() !=null && x.getIdTipoCodigoServicio().getIdParametro()!=null){} 
            if(x.getIdTipoCiclo() !=null && x.getIdTipoCiclo().getIdParametro()!=null){} 
            for(Potencial x_potencial: x.getPotencialHasCodigoServicioList() ) {} 
            for(AsignacionServicioDuracion x_asignacionServicioDuracion: x.getAsignacionServicioDuracionHasCodigoServicioList() ) {} 
            for(TablaAbordamiento x_tablaAbordamiento: x.getTablaAbordamientoHasCodigoServicioList() ) {} 

        }
        return x;
    }

	@Override
	public List<CodigoServicio> findAll() {
		TypedQuery<CodigoServicio> nq = em.createNamedQuery("CodigoServicio.findAll", CodigoServicio.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("CodigoServicio.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
