package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Proceso;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProcesoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Proceso by ProcesoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proceso_RSB",
    mappedName   = "Proceso_RSB",
    description  = "ProcesoFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProcesoFacade extends AbstractFacade<Proceso> implements ProcesoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProcesoFacade() {
		super(Proceso.class);
	}

	@Override
	public Proceso create(Proceso entity) {
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
        final List<Archivo> entity_archivoHasProcesoList =  entity.getArchivoHasProcesoList();
        entity.setArchivoHasProcesoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setArchivoHasProcesoList(entity_archivoHasProcesoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Proceso update(Proceso entity) {
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
        final List<Archivo> entity_archivoHasProcesoList =  entity.getArchivoHasProcesoList();
        entity.setArchivoHasProcesoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setArchivoHasProcesoList(entity_archivoHasProcesoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Proceso> findAllLike(Proceso x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Proceso x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdProceso() != null){
			    paramAsigned++;
			    sbq.append(" and x.idProceso = :idProceso");
			}
			if(x.getIdIdioma() != null){
			    paramAsigned++;
			    sbq.append(" and x.idIdioma = :idIdioma");
			}
			if(x.getTipoProceso() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoProceso = :tipoProceso");
			}
			if(x.getTitulo() != null){
			    paramAsigned++;
			    sbq.append(" and x.titulo = :titulo");
			}
			if(x.getRevision()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.revision = :revision");
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
		
		TypedQuery<Proceso> nq = em.createQuery(sbq.toString(), Proceso.class);
		
		if(paramAsigned>0){
			if(x.getIdProceso() != null){
			    nq.setParameter("idProceso",x.getIdProceso());
			}
			if(x.getIdIdioma() != null){
			    nq.setParameter("idIdioma",x.getIdIdioma());
			}
			if(x.getTipoProceso() != null){
			    nq.setParameter("tipoProceso",x.getTipoProceso());
			}
			if(x.getTitulo() != null){
			    nq.setParameter("titulo",x.getTitulo());
			}
			if(x.getRevision()  != 0){
			    nq.setParameter("revision",x.getRevision());
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
	public Proceso findByPK_EAGER(Object pk){
        Proceso x = getEntityManager().find(Proceso.class, pk);
        if( x != null){
            if(x.getIdIdioma() !=null && x.getIdIdioma().getIdParametro()!=null){} 
            if(x.getTipoProceso() !=null && x.getTipoProceso().getIdParametro()!=null){} 
            for(Archivo x_archivo: x.getArchivoHasProcesoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Proceso> findAll() {
		TypedQuery<Proceso> nq = em.createNamedQuery("Proceso.findAll", Proceso.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Proceso.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
