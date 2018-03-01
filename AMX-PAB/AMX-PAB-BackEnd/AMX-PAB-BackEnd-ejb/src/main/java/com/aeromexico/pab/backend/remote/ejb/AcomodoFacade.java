package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Acomodo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AcomodoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table acomodo by AcomodoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "acomodo_RSB",
    mappedName   = "Acomodo_RSB",
    description  = "AcomodoFacadeRemote-Stateless-Session EJB-3.1"
)
public class AcomodoFacade extends AbstractFacade<Acomodo> implements AcomodoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AcomodoFacade() {
		super(Acomodo.class);
	}

	@Override
	public Acomodo create(Acomodo entity) {
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
        final List<Producto> entity_productoList =  entity.getProductoList();
        entity.setProductoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setProductoList(entity_productoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Acomodo update(Acomodo entity) {
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
        final List<Producto> entity_productoList =  entity.getProductoList();
        entity.setProductoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setProductoList(entity_productoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Acomodo> findAllLike(Acomodo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Acomodo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAcomodo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAcomodo = :idAcomodo");
			}
			if(x.getAcomodoDetalle() != null){
			    paramAsigned++;
			    sbq.append(" and x.acomodoDetalle = :acomodoDetalle");
			}
			if(x.getEstacionOrigen() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacionOrigen = :estacionOrigen");
			}
			if(x.getEstacionDestino() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacionDestino = :estacionDestino");
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
		
		TypedQuery<Acomodo> nq = em.createQuery(sbq.toString(), Acomodo.class);
		
		if(paramAsigned>0){
			if(x.getIdAcomodo() != null){
			    nq.setParameter("idAcomodo",x.getIdAcomodo());
			}
			if(x.getAcomodoDetalle() != null){
			    nq.setParameter("acomodoDetalle",x.getAcomodoDetalle());
			}
			if(x.getEstacionOrigen() != null){
			    nq.setParameter("estacionOrigen",x.getEstacionOrigen());
			}
			if(x.getEstacionDestino() != null){
			    nq.setParameter("estacionDestino",x.getEstacionDestino());
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
	public Acomodo findByPK_EAGER(Object pk){
        Acomodo x = getEntityManager().find(Acomodo.class, pk);
        if( x != null){
            if(x.getAcomodoDetalle() !=null && x.getAcomodoDetalle().getIdAcomodoDetalle()!=null){} 
            if(x.getEstacionOrigen() !=null && x.getEstacionOrigen().getIdEstacion()!=null){} 
            if(x.getEstacionDestino() !=null && x.getEstacionDestino().getIdEstacion()!=null){} 
            if(x.getModeloAvion() !=null && x.getModeloAvion().getIdModeloAvion()!=null){} 
            if(x.getIdIdioma() !=null && x.getIdIdioma().getIdParametro()!=null){} 
            for(Producto x_Producto: x.getProductoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Acomodo> findAll() {
		TypedQuery<Acomodo> nq = em.createNamedQuery("Acomodo.findAll", Acomodo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Acomodo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
