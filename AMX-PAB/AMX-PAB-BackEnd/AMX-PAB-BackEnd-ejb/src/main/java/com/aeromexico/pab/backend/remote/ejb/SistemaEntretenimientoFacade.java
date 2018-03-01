package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.SistemaEntretenimiento;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.SistemaEntretenimientoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table sistema_entretenimiento by SistemaEntretenimientoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "sistemaEntretenimiento_RSB",
    mappedName   = "SistemaEntretenimiento_RSB",
    description  = "SistemaEntretenimientoFacadeRemote-Stateless-Session EJB-3.1"
)
public class SistemaEntretenimientoFacade extends AbstractFacade<SistemaEntretenimiento> implements SistemaEntretenimientoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SistemaEntretenimientoFacade() {
		super(SistemaEntretenimiento.class);
	}

	@Override
	public SistemaEntretenimiento create(SistemaEntretenimiento entity) {
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
	public SistemaEntretenimiento update(SistemaEntretenimiento entity) {
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
	public List<SistemaEntretenimiento> findAllLike(SistemaEntretenimiento x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM SistemaEntretenimiento x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdSistemaEntretenimiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.idSistemaEntretenimiento = :idSistemaEntretenimiento");
			}
			if(x.getTipo() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipo = :tipo");
			}
			if(x.getVersion() != null){
			    paramAsigned++;
			    sbq.append(" and x.version = :version");
			}
			if(x.getDescripcion() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcion = :descripcion");
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
		
		TypedQuery<SistemaEntretenimiento> nq = em.createQuery(sbq.toString(), SistemaEntretenimiento.class);
		
		if(paramAsigned>0){
			if(x.getIdSistemaEntretenimiento() != null){
			    nq.setParameter("idSistemaEntretenimiento",x.getIdSistemaEntretenimiento());
			}
			if(x.getTipo() != null){
			    nq.setParameter("tipo",x.getTipo());
			}
			if(x.getVersion() != null){
			    nq.setParameter("version",x.getVersion());
			}
			if(x.getDescripcion() != null){
			    nq.setParameter("descripcion",x.getDescripcion());
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
	public SistemaEntretenimiento findByPK_EAGER(Object pk){
        SistemaEntretenimiento x = getEntityManager().find(SistemaEntretenimiento.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<SistemaEntretenimiento> findAll() {
		TypedQuery<SistemaEntretenimiento> nq = em.createNamedQuery("SistemaEntretenimiento.findAll", SistemaEntretenimiento.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("SistemaEntretenimiento.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
