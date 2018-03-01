package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProductoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table producto by ProductoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "producto_RSB",
    mappedName   = "Producto_RSB",
    description  = "ProductoFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProductoFacade() {
		super(Producto.class);
	}

	@Override
	public Producto create(Producto entity) {
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
        final List<Matriz> entity_matrizHasProductoList =  entity.getMatrizHasProductoList();
        entity.setMatrizHasProductoList(null);
        final List<Acomodo> entity_acomodoList =  entity.getAcomodoList();
        entity.setAcomodoList(null);
        final List<Grafico> entity_graficoList =  entity.getGraficoList();
        entity.setGraficoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setMatrizHasProductoList(entity_matrizHasProductoList);
        entity.setAcomodoList(entity_acomodoList);
        entity.setGraficoList(entity_graficoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Producto update(Producto entity) {
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
        final List<Matriz> entity_matrizHasProductoList =  entity.getMatrizHasProductoList();
        entity.setMatrizHasProductoList(null);
        final List<Acomodo> entity_acomodoList =  entity.getAcomodoList();
        entity.setAcomodoList(null);
        final List<Grafico> entity_graficoList =  entity.getGraficoList();
        entity.setGraficoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setMatrizHasProductoList(entity_matrizHasProductoList);
        entity.setAcomodoList(entity_acomodoList);
        entity.setGraficoList(entity_graficoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Producto> findAllLike(Producto x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Producto x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdProducto() != null){
			    paramAsigned++;
			    sbq.append(" and x.idProducto = :idProducto");
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
		
		TypedQuery<Producto> nq = em.createQuery(sbq.toString(), Producto.class);
		
		if(paramAsigned>0){
			if(x.getIdProducto() != null){
			    nq.setParameter("idProducto",x.getIdProducto());
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
	public Producto findByPK_EAGER(Object pk){
        Producto x = getEntityManager().find(Producto.class, pk);
        if( x != null){
            for(Matriz x_matriz: x.getMatrizHasProductoList() ) {} 
            for(Acomodo x_Acomodo: x.getAcomodoList() ) {} 
            for(Grafico x_Grafico: x.getGraficoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Producto> findAll() {
		TypedQuery<Producto> nq = em.createNamedQuery("Producto.findAll", Producto.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Producto.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
