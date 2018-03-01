package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ComunicadoAreas;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ComunicadoAreasFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table comunicado_areas by ComunicadoAreasFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "comunicadoAreas_RSB",
    mappedName   = "ComunicadoAreas_RSB",
    description  = "ComunicadoAreasFacadeRemote-Stateless-Session EJB-3.1"
)
public class ComunicadoAreasFacade extends AbstractFacade<ComunicadoAreas> implements ComunicadoAreasFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ComunicadoAreasFacade() {
		super(ComunicadoAreas.class);
	}

	@Override
	public ComunicadoAreas create(ComunicadoAreas entity) {
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
	public ComunicadoAreas update(ComunicadoAreas entity) {
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
	public List<ComunicadoAreas> findAllLike(ComunicadoAreas x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ComunicadoAreas x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdComunicadoAereas() != null){
			    paramAsigned++;
			    sbq.append(" and x.idComunicadoAereas = :idComunicadoAereas");
			}
			if(x.getComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.comunicado = :comunicado");
			}
			if(x.getArea() != null){
			    paramAsigned++;
			    sbq.append(" and x.area = :area");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ComunicadoAreas> nq = em.createQuery(sbq.toString(), ComunicadoAreas.class);
		
		if(paramAsigned>0){
			if(x.getIdComunicadoAereas() != null){
			    nq.setParameter("idComunicadoAereas",x.getIdComunicadoAereas());
			}
			if(x.getComunicado() != null){
			    nq.setParameter("comunicado",x.getComunicado());
			}
			if(x.getArea() != null){
			    nq.setParameter("area",x.getArea());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ComunicadoAreas findByPK_EAGER(Object pk){
        ComunicadoAreas x = getEntityManager().find(ComunicadoAreas.class, pk);
        if( x != null){
            if(x.getComunicado() !=null && x.getComunicado().getIdComunicado()!=null){} 
            if(x.getArea() !=null && x.getArea().getIdArea()!=null){} 

        }
        return x;
    }

	@Override
	public List<ComunicadoAreas> findAll() {
		TypedQuery<ComunicadoAreas> nq = em.createNamedQuery("ComunicadoAreas.findAll", ComunicadoAreas.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ComunicadoAreas.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
