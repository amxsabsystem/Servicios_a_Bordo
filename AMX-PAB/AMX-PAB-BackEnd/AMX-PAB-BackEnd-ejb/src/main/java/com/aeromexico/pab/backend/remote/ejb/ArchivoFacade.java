package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Archivo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ArchivoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table archivo by ArchivoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "archivo_RSB",
    mappedName   = "Archivo_RSB",
    description  = "ArchivoFacadeRemote-Stateless-Session EJB-3.1"
)
public class ArchivoFacade extends AbstractFacade<Archivo> implements ArchivoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ArchivoFacade() {
		super(Archivo.class);
	}

	@Override
	public Archivo create(Archivo entity) {
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
	public Archivo update(Archivo entity) {
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
	public List<Archivo> findAllLike(Archivo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Archivo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdArchivo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idArchivo = :idArchivo");
			}
			if(x.getProceso() != null){
			    paramAsigned++;
			    sbq.append(" and x.proceso = :proceso");
			}
			if(x.getNombreArchivo() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreArchivo = :nombreArchivo");
			}
			if(x.getUrlArchivo() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlArchivo = :urlArchivo");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
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
		
		TypedQuery<Archivo> nq = em.createQuery(sbq.toString(), Archivo.class);
		
		if(paramAsigned>0){
			if(x.getIdArchivo() != null){
			    nq.setParameter("idArchivo",x.getIdArchivo());
			}
			if(x.getProceso() != null){
			    nq.setParameter("proceso",x.getProceso());
			}
			if(x.getNombreArchivo() != null){
			    nq.setParameter("nombreArchivo",x.getNombreArchivo());
			}
			if(x.getUrlArchivo() != null){
			    nq.setParameter("urlArchivo",x.getUrlArchivo());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
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
	public Archivo findByPK_EAGER(Object pk){
        Archivo x = getEntityManager().find(Archivo.class, pk);
        if( x != null){
            if(x.getProceso() !=null && x.getProceso().getIdProceso()!=null){} 

        }
        return x;
    }

	@Override
	public List<Archivo> findAll() {
		TypedQuery<Archivo> nq = em.createNamedQuery("Archivo.findAll", Archivo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Archivo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
