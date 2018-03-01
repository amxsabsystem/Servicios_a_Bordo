package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.MatrizDetalle;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MatrizDetalleFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table matriz_detalle by MatrizDetalleFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "matrizDetalle_RSB",
    mappedName   = "MatrizDetalle_RSB",
    description  = "MatrizDetalleFacadeRemote-Stateless-Session EJB-3.1"
)
public class MatrizDetalleFacade extends AbstractFacade<MatrizDetalle> implements MatrizDetalleFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MatrizDetalleFacade() {
		super(MatrizDetalle.class);
	}

	@Override
	public MatrizDetalle create(MatrizDetalle entity) {
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
	public MatrizDetalle update(MatrizDetalle entity) {
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
	public List<MatrizDetalle> findAllLike(MatrizDetalle x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM MatrizDetalle x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdMatrizDetalle() != null){
			    paramAsigned++;
			    sbq.append(" and x.idMatrizDetalle = :idMatrizDetalle");
			}
			if(x.getMatriz() != null){
			    paramAsigned++;
			    sbq.append(" and x.matriz = :matriz");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
			}
			if(x.getTotalCantidadCj()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.totalCantidadCj = :totalCantidadCj");
			}
			if(x.getTotalCantidadCy()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.totalCantidadCy = :totalCantidadCy");
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
		
		TypedQuery<MatrizDetalle> nq = em.createQuery(sbq.toString(), MatrizDetalle.class);
		
		if(paramAsigned>0){
			if(x.getIdMatrizDetalle() != null){
			    nq.setParameter("idMatrizDetalle",x.getIdMatrizDetalle());
			}
			if(x.getMatriz() != null){
			    nq.setParameter("matriz",x.getMatriz());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
			}
			if(x.getTotalCantidadCj()  != 0){
			    nq.setParameter("totalCantidadCj",x.getTotalCantidadCj());
			}
			if(x.getTotalCantidadCy()  != 0){
			    nq.setParameter("totalCantidadCy",x.getTotalCantidadCy());
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
	public MatrizDetalle findByPK_EAGER(Object pk){
        MatrizDetalle x = getEntityManager().find(MatrizDetalle.class, pk);
        if( x != null){
            if(x.getMatriz() !=null && x.getMatriz().getIdMatriz()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 

        }
        return x;
    }

	@Override
	public List<MatrizDetalle> findAll() {
		TypedQuery<MatrizDetalle> nq = em.createNamedQuery("MatrizDetalle.findAll", MatrizDetalle.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("MatrizDetalle.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
