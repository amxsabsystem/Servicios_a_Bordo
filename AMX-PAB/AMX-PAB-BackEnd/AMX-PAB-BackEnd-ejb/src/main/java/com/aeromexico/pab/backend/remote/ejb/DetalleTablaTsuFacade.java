package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.DetalleTablaTsu;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.DetalleTablaTsuFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table detalle_tabla_tsu by DetalleTablaTsuFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "detalleTablaTsu_RSB",
    mappedName   = "DetalleTablaTsu_RSB",
    description  = "DetalleTablaTsuFacadeRemote-Stateless-Session EJB-3.1"
)
public class DetalleTablaTsuFacade extends AbstractFacade<DetalleTablaTsu> implements DetalleTablaTsuFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DetalleTablaTsuFacade() {
		super(DetalleTablaTsu.class);
	}

	@Override
	public DetalleTablaTsu create(DetalleTablaTsu entity) {
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
        final List<Parametro> entity_parametroList =  entity.getParametroList();
        entity.setParametroList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setParametroList(entity_parametroList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public DetalleTablaTsu update(DetalleTablaTsu entity) {
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
        final List<Parametro> entity_parametroList =  entity.getParametroList();
        entity.setParametroList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setParametroList(entity_parametroList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<DetalleTablaTsu> findAllLike(DetalleTablaTsu x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM DetalleTablaTsu x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdDetalleTablaTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.idDetalleTablaTsu = :idDetalleTablaTsu");
			}
			if(x.getTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tablaAbordamiento = :tablaAbordamiento");
			}
			if(x.getTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.tsu = :tsu");
			}
			if(x.getMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.material = :material");
			}
			if(x.getMaterialAmx() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialAmx = :materialAmx");
			}
			if(x.getMaterialMontaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialMontaje = :materialMontaje");
			}
			if(x.getMaterialMontajeAmx() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialMontajeAmx = :materialMontajeAmx");
			}
			if(x.getNombrePlatillo() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombrePlatillo = :nombrePlatillo");
			}
			if(x.getPorcentaje()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.porcentaje = :porcentaje");
			}
			if(x.getMaterialComisariato() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialComisariato = :materialComisariato");
			}
			if(x.getMaterialComisariatoMontaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.materialComisariatoMontaje = :materialComisariatoMontaje");
			}
			if(x.getInstrucciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.instrucciones = :instrucciones");
			}
			if(x.getDetalleTsuAbordajetsuPorcentaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.detalleTsuAbordajetsuPorcentaje = :detalleTsuAbordajetsuPorcentaje");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<DetalleTablaTsu> nq = em.createQuery(sbq.toString(), DetalleTablaTsu.class);
		
		if(paramAsigned>0){
			if(x.getIdDetalleTablaTsu() != null){
			    nq.setParameter("idDetalleTablaTsu",x.getIdDetalleTablaTsu());
			}
			if(x.getTablaAbordamiento() != null){
			    nq.setParameter("tablaAbordamiento",x.getTablaAbordamiento());
			}
			if(x.getTsu() != null){
			    nq.setParameter("tsu",x.getTsu());
			}
			if(x.getMaterial() != null){
			    nq.setParameter("material",x.getMaterial());
			}
			if(x.getMaterialAmx() != null){
			    nq.setParameter("materialAmx",x.getMaterialAmx());
			}
			if(x.getMaterialMontaje() != null){
			    nq.setParameter("materialMontaje",x.getMaterialMontaje());
			}
			if(x.getMaterialMontajeAmx() != null){
			    nq.setParameter("materialMontajeAmx",x.getMaterialMontajeAmx());
			}
			if(x.getNombrePlatillo() != null){
			    nq.setParameter("nombrePlatillo",x.getNombrePlatillo());
			}
			if(x.getPorcentaje()  != 0){
			    nq.setParameter("porcentaje",x.getPorcentaje());
			}
			if(x.getMaterialComisariato() != null){
			    nq.setParameter("materialComisariato",x.getMaterialComisariato());
			}
			if(x.getMaterialComisariatoMontaje() != null){
			    nq.setParameter("materialComisariatoMontaje",x.getMaterialComisariatoMontaje());
			}
			if(x.getInstrucciones() != null){
			    nq.setParameter("instrucciones",x.getInstrucciones());
			}
			if(x.getDetalleTsuAbordajetsuPorcentaje() != null){
			    nq.setParameter("detalleTsuAbordajetsuPorcentaje",x.getDetalleTsuAbordajetsuPorcentaje());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public DetalleTablaTsu findByPK_EAGER(Object pk){
        DetalleTablaTsu x = getEntityManager().find(DetalleTablaTsu.class, pk);
        if( x != null){
            if(x.getTablaAbordamiento() !=null && x.getTablaAbordamiento().getIdTablaAbordamiento()!=null){} 
            if(x.getTsu() !=null && x.getTsu().getIdTsu()!=null){} 
            if(x.getMaterial() !=null && x.getMaterial().getNumeroParte()!=null){} 
            if(x.getMaterialAmx() !=null && x.getMaterialAmx().getNumeroParte()!=null){} 
            if(x.getMaterialMontaje() !=null && x.getMaterialMontaje().getNumeroParte()!=null){} 
            if(x.getMaterialMontajeAmx() !=null && x.getMaterialMontajeAmx().getNumeroParte()!=null){} 
            if(x.getDetalleTsuAbordajetsuPorcentaje() !=null && x.getDetalleTsuAbordajetsuPorcentaje().getIdTsuAbordamiento()!=null){} 
            for(Parametro x_Parametro: x.getParametroList() ) {} 

        }
        return x;
    }

	@Override
	public List<DetalleTablaTsu> findAll() {
		TypedQuery<DetalleTablaTsu> nq = em.createNamedQuery("DetalleTablaTsu.findAll", DetalleTablaTsu.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("DetalleTablaTsu.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
