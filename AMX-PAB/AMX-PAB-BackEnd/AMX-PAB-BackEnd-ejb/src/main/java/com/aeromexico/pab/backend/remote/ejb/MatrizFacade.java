package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Matriz;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MatrizFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table matriz by MatrizFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "matriz_RSB",
    mappedName   = "Matriz_RSB",
    description  = "MatrizFacadeRemote-Stateless-Session EJB-3.1"
)
public class MatrizFacade extends AbstractFacade<Matriz> implements MatrizFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MatrizFacade() {
		super(Matriz.class);
	}

	@Override
	public Matriz create(Matriz entity) {
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
        final List<MatrizDetalle> entity_matrizDetalleHasMatrizList =  entity.getMatrizDetalleHasMatrizList();
        entity.setMatrizDetalleHasMatrizList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setMatrizDetalleHasMatrizList(entity_matrizDetalleHasMatrizList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Matriz update(Matriz entity) {
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
        final List<MatrizDetalle> entity_matrizDetalleHasMatrizList =  entity.getMatrizDetalleHasMatrizList();
        entity.setMatrizDetalleHasMatrizList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setMatrizDetalleHasMatrizList(entity_matrizDetalleHasMatrizList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Matriz> findAllLike(Matriz x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Matriz x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdMatriz() != null){
			    paramAsigned++;
			    sbq.append(" and x.idMatriz = :idMatriz");
			}
			if(x.getKitMaster() != null){
			    paramAsigned++;
			    sbq.append(" and x.kitMaster = :kitMaster");
			}
			if(x.getProducto() != null){
			    paramAsigned++;
			    sbq.append(" and x.producto = :producto");
			}
			if(x.getTipoEquipoAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoEquipoAvion = :tipoEquipoAvion");
			}
			if(x.getCantidadCy()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidadCy = :cantidadCy");
			}
			if(x.getCantidadCj()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidadCj = :cantidadCj");
			}
			if(x.getTotalCantidadCjCy()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.totalCantidadCjCy = :totalCantidadCjCy");
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
		
		TypedQuery<Matriz> nq = em.createQuery(sbq.toString(), Matriz.class);
		
		if(paramAsigned>0){
			if(x.getIdMatriz() != null){
			    nq.setParameter("idMatriz",x.getIdMatriz());
			}
			if(x.getKitMaster() != null){
			    nq.setParameter("kitMaster",x.getKitMaster());
			}
			if(x.getProducto() != null){
			    nq.setParameter("producto",x.getProducto());
			}
			if(x.getTipoEquipoAvion() != null){
			    nq.setParameter("tipoEquipoAvion",x.getTipoEquipoAvion());
			}
			if(x.getCantidadCy()  != 0){
			    nq.setParameter("cantidadCy",x.getCantidadCy());
			}
			if(x.getCantidadCj()  != 0){
			    nq.setParameter("cantidadCj",x.getCantidadCj());
			}
			if(x.getTotalCantidadCjCy()  != 0){
			    nq.setParameter("totalCantidadCjCy",x.getTotalCantidadCjCy());
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
	public Matriz findByPK_EAGER(Object pk){
        Matriz x = getEntityManager().find(Matriz.class, pk);
        if( x != null){
            if(x.getKitMaster() !=null && x.getKitMaster().getCveKitMaster()!=null){} 
            if(x.getProducto() !=null && x.getProducto().getIdProducto()!=null){} 
            if(x.getTipoEquipoAvion() !=null && x.getTipoEquipoAvion().getIdTipoEquipoAvion()!=null){} 
            for(MatrizDetalle x_matrizDetalle: x.getMatrizDetalleHasMatrizList() ) {} 

        }
        return x;
    }

	@Override
	public List<Matriz> findAll() {
		TypedQuery<Matriz> nq = em.createNamedQuery("Matriz.findAll", Matriz.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Matriz.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
