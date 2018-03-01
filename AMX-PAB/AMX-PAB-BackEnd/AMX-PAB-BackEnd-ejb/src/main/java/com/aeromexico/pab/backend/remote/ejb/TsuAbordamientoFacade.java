package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.TsuAbordamiento;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TsuAbordamientoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table tsu_abordamiento by TsuAbordamientoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "tsuAbordamiento_RSB",
    mappedName   = "TsuAbordamiento_RSB",
    description  = "TsuAbordamientoFacadeRemote-Stateless-Session EJB-3.1"
)
public class TsuAbordamientoFacade extends AbstractFacade<TsuAbordamiento> implements TsuAbordamientoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TsuAbordamientoFacade() {
		super(TsuAbordamiento.class);
	}

	@Override
	public TsuAbordamiento create(TsuAbordamiento entity) {
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
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList =  entity.getDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList();
        entity.setDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList(entity_detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public TsuAbordamiento update(TsuAbordamiento entity) {
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
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList =  entity.getDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList();
        entity.setDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList(entity_detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<TsuAbordamiento> findAllLike(TsuAbordamiento x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM TsuAbordamiento x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTsuAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTsuAbordamiento = :idTsuAbordamiento");
			}
			if(x.getTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tablaAbordamiento = :tablaAbordamiento");
			}
			if(x.getTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.tsu = :tsu");
			}
			if(x.getPorcentaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.porcentaje = :porcentaje");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<TsuAbordamiento> nq = em.createQuery(sbq.toString(), TsuAbordamiento.class);
		
		if(paramAsigned>0){
			if(x.getIdTsuAbordamiento() != null){
			    nq.setParameter("idTsuAbordamiento",x.getIdTsuAbordamiento());
			}
			if(x.getTablaAbordamiento() != null){
			    nq.setParameter("tablaAbordamiento",x.getTablaAbordamiento());
			}
			if(x.getTsu() != null){
			    nq.setParameter("tsu",x.getTsu());
			}
			if(x.getPorcentaje() != null){
			    nq.setParameter("porcentaje",x.getPorcentaje());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public TsuAbordamiento findByPK_EAGER(Object pk){
        TsuAbordamiento x = getEntityManager().find(TsuAbordamiento.class, pk);
        if( x != null){
            if(x.getTablaAbordamiento() !=null && x.getTablaAbordamiento().getIdTablaAbordamiento()!=null){} 
            if(x.getTsu() !=null && x.getTsu().getIdTsu()!=null){} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList() ) {} 

        }
        return x;
    }

	@Override
	public List<TsuAbordamiento> findAll() {
		TypedQuery<TsuAbordamiento> nq = em.createNamedQuery("TsuAbordamiento.findAll", TsuAbordamiento.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("TsuAbordamiento.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
