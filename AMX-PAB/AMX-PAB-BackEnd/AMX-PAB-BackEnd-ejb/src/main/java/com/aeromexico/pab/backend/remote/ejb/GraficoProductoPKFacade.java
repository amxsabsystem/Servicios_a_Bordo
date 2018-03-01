package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.GraficoProductoPK;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.GraficoProductoPKFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table grafico_producto_P_K by GraficoProductoPKFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "graficoProductoPK_RSB",
    mappedName   = "GraficoProductoPK_RSB",
    description  = "GraficoProductoPKFacadeRemote-Stateless-Session EJB-3.1"
)
public class GraficoProductoPKFacade extends AbstractFacade<GraficoProductoPK> implements GraficoProductoPKFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public GraficoProductoPKFacade() {
		super(GraficoProductoPK.class);
	}

	@Override
	public GraficoProductoPK create(GraficoProductoPK entity) {
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
	public GraficoProductoPK update(GraficoProductoPK entity) {
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
	public List<GraficoProductoPK> findAllLike(GraficoProductoPK x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM GraficoProductoPK x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdGrafico() != null){
			    paramAsigned++;
			    sbq.append(" and x.idGrafico = :idGrafico");
			}
			if(x.getIdProducto() != null){
			    paramAsigned++;
			    sbq.append(" and x.idProducto = :idProducto");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<GraficoProductoPK> nq = em.createQuery(sbq.toString(), GraficoProductoPK.class);
		
		if(paramAsigned>0){
			if(x.getIdGrafico() != null){
			    nq.setParameter("idGrafico",x.getIdGrafico());
			}
			if(x.getIdProducto() != null){
			    nq.setParameter("idProducto",x.getIdProducto());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public GraficoProductoPK findByPK_EAGER(Object pk){
        GraficoProductoPK x = getEntityManager().find(GraficoProductoPK.class, pk);
        if( x != null){

        }
        return x;
    }

	@Override
	public List<GraficoProductoPK> findAll() {
		TypedQuery<GraficoProductoPK> nq = em.createNamedQuery("GraficoProductoPK.findAll", GraficoProductoPK.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("GraficoProductoPK.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
