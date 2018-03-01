package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Sobreabordaje;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.SobreabordajeFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table sobreabordaje by SobreabordajeFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "sobreabordaje_RSB",
    mappedName   = "Sobreabordaje_RSB",
    description  = "SobreabordajeFacadeRemote-Stateless-Session EJB-3.1"
)
public class SobreabordajeFacade extends AbstractFacade<Sobreabordaje> implements SobreabordajeFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SobreabordajeFacade() {
		super(Sobreabordaje.class);
	}

	@Override
	public Sobreabordaje create(Sobreabordaje entity) {
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
	public Sobreabordaje update(Sobreabordaje entity) {
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
	public List<Sobreabordaje> findAllLike(Sobreabordaje x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Sobreabordaje x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdSobreabordaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.idSobreabordaje = :idSobreabordaje");
			}
			if(x.getTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tablaAbordamiento = :tablaAbordamiento");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
			}
			if(x.getCantidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.cantidad = :cantidad");
			}
			if(x.getInstrucciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.instrucciones = :instrucciones");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Sobreabordaje> nq = em.createQuery(sbq.toString(), Sobreabordaje.class);
		
		if(paramAsigned>0){
			if(x.getIdSobreabordaje() != null){
			    nq.setParameter("idSobreabordaje",x.getIdSobreabordaje());
			}
			if(x.getTablaAbordamiento() != null){
			    nq.setParameter("tablaAbordamiento",x.getTablaAbordamiento());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
			}
			if(x.getCantidad() != null){
			    nq.setParameter("cantidad",x.getCantidad());
			}
			if(x.getInstrucciones() != null){
			    nq.setParameter("instrucciones",x.getInstrucciones());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Sobreabordaje findByPK_EAGER(Object pk){
        Sobreabordaje x = getEntityManager().find(Sobreabordaje.class, pk);
        if( x != null){
            if(x.getTablaAbordamiento() !=null && x.getTablaAbordamiento().getIdTablaAbordamiento()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 

        }
        return x;
    }

	@Override
	public List<Sobreabordaje> findAll() {
		TypedQuery<Sobreabordaje> nq = em.createNamedQuery("Sobreabordaje.findAll", Sobreabordaje.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Sobreabordaje.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
