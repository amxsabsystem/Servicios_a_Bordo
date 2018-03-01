package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Compania;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CompaniaFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Compania by CompaniaFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "compania_RSB",
    mappedName   = "Compania_RSB",
    description  = "CompaniaFacadeRemote-Stateless-Session EJB-3.1"
)
public class CompaniaFacade extends AbstractFacade<Compania> implements CompaniaFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CompaniaFacade() {
		super(Compania.class);
	}

	@Override
	public Compania create(Compania entity) {
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
        final List<ModeloAvion> entity_modeloAvionHasCompaniaList =  entity.getModeloAvionHasCompaniaList();
        entity.setModeloAvionHasCompaniaList(null);
        final List<Vuelo> entity_vueloHasCompaniaList =  entity.getVueloHasCompaniaList();
        entity.setVueloHasCompaniaList(null);
        final List<Empleado> entity_empleadoHasCompaniaList =  entity.getEmpleadoHasCompaniaList();
        entity.setEmpleadoHasCompaniaList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setModeloAvionHasCompaniaList(entity_modeloAvionHasCompaniaList);
        entity.setVueloHasCompaniaList(entity_vueloHasCompaniaList);
        entity.setEmpleadoHasCompaniaList(entity_empleadoHasCompaniaList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Compania update(Compania entity) {
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
        final List<ModeloAvion> entity_modeloAvionHasCompaniaList =  entity.getModeloAvionHasCompaniaList();
        entity.setModeloAvionHasCompaniaList(null);
        final List<Vuelo> entity_vueloHasCompaniaList =  entity.getVueloHasCompaniaList();
        entity.setVueloHasCompaniaList(null);
        final List<Empleado> entity_empleadoHasCompaniaList =  entity.getEmpleadoHasCompaniaList();
        entity.setEmpleadoHasCompaniaList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setModeloAvionHasCompaniaList(entity_modeloAvionHasCompaniaList);
        entity.setVueloHasCompaniaList(entity_vueloHasCompaniaList);
        entity.setEmpleadoHasCompaniaList(entity_empleadoHasCompaniaList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Compania> findAllLike(Compania x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Compania x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdCompania() != null){
			    paramAsigned++;
			    sbq.append(" and x.idCompania = :idCompania");
			}
			if(x.getCveCompania() != null){
			    paramAsigned++;
			    sbq.append(" and x.cveCompania = :cveCompania");
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
		
		TypedQuery<Compania> nq = em.createQuery(sbq.toString(), Compania.class);
		
		if(paramAsigned>0){
			if(x.getIdCompania() != null){
			    nq.setParameter("idCompania",x.getIdCompania());
			}
			if(x.getCveCompania() != null){
			    nq.setParameter("cveCompania",x.getCveCompania());
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
	public Compania findByPK_EAGER(Object pk){
        Compania x = getEntityManager().find(Compania.class, pk);
        if( x != null){
            for(ModeloAvion x_modeloAvion: x.getModeloAvionHasCompaniaList() ) {} 
            for(Vuelo x_vuelo: x.getVueloHasCompaniaList() ) {} 
            for(Empleado x_empleado: x.getEmpleadoHasCompaniaList() ) {} 

        }
        return x;
    }

	@Override
	public List<Compania> findAll() {
		TypedQuery<Compania> nq = em.createNamedQuery("Compania.findAll", Compania.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Compania.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
