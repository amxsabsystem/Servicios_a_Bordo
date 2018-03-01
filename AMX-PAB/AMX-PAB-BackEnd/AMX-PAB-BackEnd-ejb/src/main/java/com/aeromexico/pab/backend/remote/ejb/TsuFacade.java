package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Tsu;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TsuFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table TSU by TsuFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "tsu_RSB",
    mappedName   = "Tsu_RSB",
    description  = "TsuFacadeRemote-Stateless-Session EJB-3.1"
)
public class TsuFacade extends AbstractFacade<Tsu> implements TsuFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TsuFacade() {
		super(Tsu.class);
	}

	@Override
	public Tsu create(Tsu entity) {
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
        final List<KitTsu> entity_kitTsuHasTsuList =  entity.getKitTsuHasTsuList();
        entity.setKitTsuHasTsuList(null);
        final List<TsuAbordamiento> entity_tsuAbordamientoHasTsuList =  entity.getTsuAbordamientoHasTsuList();
        entity.setTsuAbordamientoHasTsuList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasTsuList =  entity.getDetalleTablaTsuHasTsuList();
        entity.setDetalleTablaTsuHasTsuList(null);
        final List<MaterialTsu> entity_materialTsuHasTsuList =  entity.getMaterialTsuHasTsuList();
        entity.setMaterialTsuHasTsuList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setKitTsuHasTsuList(entity_kitTsuHasTsuList);
        entity.setTsuAbordamientoHasTsuList(entity_tsuAbordamientoHasTsuList);
        entity.setDetalleTablaTsuHasTsuList(entity_detalleTablaTsuHasTsuList);
        entity.setMaterialTsuHasTsuList(entity_materialTsuHasTsuList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Tsu update(Tsu entity) {
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
        final List<KitTsu> entity_kitTsuHasTsuList =  entity.getKitTsuHasTsuList();
        entity.setKitTsuHasTsuList(null);
        final List<TsuAbordamiento> entity_tsuAbordamientoHasTsuList =  entity.getTsuAbordamientoHasTsuList();
        entity.setTsuAbordamientoHasTsuList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasTsuList =  entity.getDetalleTablaTsuHasTsuList();
        entity.setDetalleTablaTsuHasTsuList(null);
        final List<MaterialTsu> entity_materialTsuHasTsuList =  entity.getMaterialTsuHasTsuList();
        entity.setMaterialTsuHasTsuList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setKitTsuHasTsuList(entity_kitTsuHasTsuList);
        entity.setTsuAbordamientoHasTsuList(entity_tsuAbordamientoHasTsuList);
        entity.setDetalleTablaTsuHasTsuList(entity_detalleTablaTsuHasTsuList);
        entity.setMaterialTsuHasTsuList(entity_materialTsuHasTsuList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Tsu> findAllLike(Tsu x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Tsu x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTsu = :idTsu");
			}
			if(x.getCveTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.cveTsu = :cveTsu");
			}
			if(x.getIdRevision() != null){
			    paramAsigned++;
			    sbq.append(" and x.idRevision = :idRevision");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getIdIdioma() != null){
			    paramAsigned++;
			    sbq.append(" and x.idIdioma = :idIdioma");
			}
			if(x.getMultimedio() != null){
			    paramAsigned++;
			    sbq.append(" and x.multimedio = :multimedio");
			}
			if(x.getNotas() != null){
			    paramAsigned++;
			    sbq.append(" and x.notas = :notas");
			}
			if(x.getFechaVigenciaInicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaVigenciaInicio = :fechaVigenciaInicio");
			}
			if(x.getFechaVigenciaFin() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaVigenciaFin = :fechaVigenciaFin");
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
		
		TypedQuery<Tsu> nq = em.createQuery(sbq.toString(), Tsu.class);
		
		if(paramAsigned>0){
			if(x.getIdTsu() != null){
			    nq.setParameter("idTsu",x.getIdTsu());
			}
			if(x.getCveTsu() != null){
			    nq.setParameter("cveTsu",x.getCveTsu());
			}
			if(x.getIdRevision() != null){
			    nq.setParameter("idRevision",x.getIdRevision());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getIdIdioma() != null){
			    nq.setParameter("idIdioma",x.getIdIdioma());
			}
			if(x.getMultimedio() != null){
			    nq.setParameter("multimedio",x.getMultimedio());
			}
			if(x.getNotas() != null){
			    nq.setParameter("notas",x.getNotas());
			}
			if(x.getFechaVigenciaInicio() != null){
			    nq.setParameter("fechaVigenciaInicio",x.getFechaVigenciaInicio());
			}
			if(x.getFechaVigenciaFin() != null){
			    nq.setParameter("fechaVigenciaFin",x.getFechaVigenciaFin());
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
	public Tsu findByPK_EAGER(Object pk){
        Tsu x = getEntityManager().find(Tsu.class, pk);
        if( x != null){
            if(x.getIdRevision() !=null && x.getIdRevision().getIdParametro()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getIdIdioma() !=null && x.getIdIdioma().getIdParametro()!=null){} 
            if(x.getMultimedio() !=null && x.getMultimedio().getIdMultimedio()!=null){} 
            for(KitTsu x_kitTsu: x.getKitTsuHasTsuList() ) {} 
            for(TsuAbordamiento x_tsuAbordamiento: x.getTsuAbordamientoHasTsuList() ) {} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasTsuList() ) {} 
            for(MaterialTsu x_materialTsu: x.getMaterialTsuHasTsuList() ) {} 

        }
        return x;
    }

	@Override
	public List<Tsu> findAll() {
		TypedQuery<Tsu> nq = em.createNamedQuery("Tsu.findAll", Tsu.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Tsu.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
