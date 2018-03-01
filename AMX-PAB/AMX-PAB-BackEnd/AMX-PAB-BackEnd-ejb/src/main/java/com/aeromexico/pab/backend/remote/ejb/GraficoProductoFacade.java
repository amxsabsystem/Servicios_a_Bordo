package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.GraficoProducto;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.GraficoProductoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table grafico_producto by GraficoProductoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "graficoProducto_RSB",
    mappedName   = "GraficoProducto_RSB",
    description  = "GraficoProductoFacadeRemote-Stateless-Session EJB-3.1"
)
public class GraficoProductoFacade extends AbstractFacade<GraficoProducto> implements GraficoProductoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public GraficoProductoFacade() {
		super(GraficoProducto.class);
	}

	@Override
	public GraficoProducto create(GraficoProducto entity) {
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
	public GraficoProducto update(GraficoProducto entity) {
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
	public List<GraficoProducto> findAllLike(GraficoProducto x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM GraficoProducto x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getGraficoProductoPK() != null){
			    paramAsigned++;
			    sbq.append(" and x.graficoProductoPK = :graficoProductoPK");
			}
			if(x.getGrafico() != null){
			    paramAsigned++;
			    sbq.append(" and x.grafico = :grafico");
			}
			if(x.getProducto() != null){
			    paramAsigned++;
			    sbq.append(" and x.producto = :producto");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<GraficoProducto> nq = em.createQuery(sbq.toString(), GraficoProducto.class);
		
		if(paramAsigned>0){
			if(x.getGraficoProductoPK() != null){
			    nq.setParameter("graficoProductoPK",x.getGraficoProductoPK());
			}
			if(x.getGrafico() != null){
			    nq.setParameter("grafico",x.getGrafico());
			}
			if(x.getProducto() != null){
			    nq.setParameter("producto",x.getProducto());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public GraficoProducto findByPK_EAGER(Object pk){
        GraficoProducto x = getEntityManager().find(GraficoProducto.class, pk);
        if( x != null){
            if(x.getGrafico() !=null && x.getGrafico().getIdGrafico()!=null){} 
            if(x.getProducto() !=null && x.getProducto().getIdProducto()!=null){} 

        }
        return x;
    }

	@Override
	public List<GraficoProducto> findAll() {
		TypedQuery<GraficoProducto> nq = em.createNamedQuery("GraficoProducto.findAll", GraficoProducto.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("GraficoProducto.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
