package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Grafico;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.GraficoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table grafico by GraficoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "grafico_RSB",
    mappedName   = "Grafico_RSB",
    description  = "GraficoFacadeRemote-Stateless-Session EJB-3.1"
)
public class GraficoFacade extends AbstractFacade<Grafico> implements GraficoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public GraficoFacade() {
		super(Grafico.class);
	}

	@Override
	public Grafico create(Grafico entity) {
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
        final List<GraficoDetalle> entity_graficoDetalleHasGraficoList =  entity.getGraficoDetalleHasGraficoList();
        entity.setGraficoDetalleHasGraficoList(null);
        final List<Producto> entity_productoList =  entity.getProductoList();
        entity.setProductoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setGraficoDetalleHasGraficoList(entity_graficoDetalleHasGraficoList);
        entity.setProductoList(entity_productoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Grafico update(Grafico entity) {
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
        final List<GraficoDetalle> entity_graficoDetalleHasGraficoList =  entity.getGraficoDetalleHasGraficoList();
        entity.setGraficoDetalleHasGraficoList(null);
        final List<Producto> entity_productoList =  entity.getProductoList();
        entity.setProductoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setGraficoDetalleHasGraficoList(entity_graficoDetalleHasGraficoList);
        entity.setProductoList(entity_productoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Grafico> findAllLike(Grafico x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Grafico x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdGrafico() != null){
			    paramAsigned++;
			    sbq.append(" and x.idGrafico = :idGrafico");
			}
			if(x.getModeloAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.modeloAvion = :modeloAvion");
			}
			if(x.getIdIdioma() != null){
			    paramAsigned++;
			    sbq.append(" and x.idIdioma = :idIdioma");
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
		
		TypedQuery<Grafico> nq = em.createQuery(sbq.toString(), Grafico.class);
		
		if(paramAsigned>0){
			if(x.getIdGrafico() != null){
			    nq.setParameter("idGrafico",x.getIdGrafico());
			}
			if(x.getModeloAvion() != null){
			    nq.setParameter("modeloAvion",x.getModeloAvion());
			}
			if(x.getIdIdioma() != null){
			    nq.setParameter("idIdioma",x.getIdIdioma());
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
	public Grafico findByPK_EAGER(Object pk){
        Grafico x = getEntityManager().find(Grafico.class, pk);
        if( x != null){
            if(x.getModeloAvion() !=null && x.getModeloAvion().getIdModeloAvion()!=null){} 
            if(x.getIdIdioma() !=null && x.getIdIdioma().getIdParametro()!=null){} 
            for(GraficoDetalle x_graficoDetalle: x.getGraficoDetalleHasGraficoList() ) {} 
            for(Producto x_Producto: x.getProductoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Grafico> findAll() {
		TypedQuery<Grafico> nq = em.createNamedQuery("Grafico.findAll", Grafico.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Grafico.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
