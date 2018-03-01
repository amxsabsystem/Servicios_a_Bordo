package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.CantidadVueloComplementario;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CantidadVueloComplementarioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table cantidad_vuelo_complementario by CantidadVueloComplementarioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "cantidadVueloComplementario_RSB",
    mappedName   = "CantidadVueloComplementario_RSB",
    description  = "CantidadVueloComplementarioFacadeRemote-Stateless-Session EJB-3.1"
)
public class CantidadVueloComplementarioFacade extends AbstractFacade<CantidadVueloComplementario> implements CantidadVueloComplementarioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CantidadVueloComplementarioFacade() {
		super(CantidadVueloComplementario.class);
	}

	@Override
	public CantidadVueloComplementario create(CantidadVueloComplementario entity) {
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
	public CantidadVueloComplementario update(CantidadVueloComplementario entity) {
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
	public List<CantidadVueloComplementario> findAllLike(CantidadVueloComplementario x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM CantidadVueloComplementario x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdDetalleVueloComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.idDetalleVueloComplementario = :idDetalleVueloComplementario");
			}
			if(x.getIdDetalleComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.idDetalleComplementario = :idDetalleComplementario");
			}
			if(x.getModeloAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.modeloAvion = :modeloAvion");
			}
			if(x.getCantidad()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.cantidad = :cantidad");
			}
			if(x.getMaximo()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.maximo = :maximo");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<CantidadVueloComplementario> nq = em.createQuery(sbq.toString(), CantidadVueloComplementario.class);
		
		if(paramAsigned>0){
			if(x.getIdDetalleVueloComplementario() != null){
			    nq.setParameter("idDetalleVueloComplementario",x.getIdDetalleVueloComplementario());
			}
			if(x.getIdDetalleComplementario() != null){
			    nq.setParameter("idDetalleComplementario",x.getIdDetalleComplementario());
			}
			if(x.getModeloAvion() != null){
			    nq.setParameter("modeloAvion",x.getModeloAvion());
			}
			if(x.getCantidad()  != 0){
			    nq.setParameter("cantidad",x.getCantidad());
			}
			if(x.getMaximo()  != 0){
			    nq.setParameter("maximo",x.getMaximo());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public CantidadVueloComplementario findByPK_EAGER(Object pk){
        CantidadVueloComplementario x = getEntityManager().find(CantidadVueloComplementario.class, pk);
        if( x != null){
            if(x.getIdDetalleComplementario() !=null && x.getIdDetalleComplementario().getIdDetalleTablaComplementario()!=null){} 
            if(x.getModeloAvion() !=null && x.getModeloAvion().getIdModeloAvion()!=null){} 

        }
        return x;
    }

	@Override
	public List<CantidadVueloComplementario> findAll() {
		TypedQuery<CantidadVueloComplementario> nq = em.createNamedQuery("CantidadVueloComplementario.findAll", CantidadVueloComplementario.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("CantidadVueloComplementario.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
