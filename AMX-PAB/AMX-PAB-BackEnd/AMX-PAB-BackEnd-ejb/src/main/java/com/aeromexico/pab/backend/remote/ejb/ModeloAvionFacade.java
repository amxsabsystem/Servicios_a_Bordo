package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table modelo_avion by ModeloAvionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "modeloAvion_RSB",
    mappedName   = "ModeloAvion_RSB",
    description  = "ModeloAvionFacadeRemote-Stateless-Session EJB-3.1"
)
public class ModeloAvionFacade extends AbstractFacade<ModeloAvion> implements ModeloAvionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ModeloAvionFacade() {
		super(ModeloAvion.class);
	}

	@Override
	public ModeloAvion create(ModeloAvion entity) {
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
        final List<AsignacionServicio> entity_asignacionServicioHasModeloAvionList =  entity.getAsignacionServicioHasModeloAvionList();
        entity.setAsignacionServicioHasModeloAvionList(null);
        final List<CantidadVueloComplementario> entity_cantidadVueloComplementarioHasModeloAvionList =  entity.getCantidadVueloComplementarioHasModeloAvionList();
        entity.setCantidadVueloComplementarioHasModeloAvionList(null);
        final List<TipoEquipoAvion> entity_tipoEquipoAvionHasModeloAvionList =  entity.getTipoEquipoAvionHasModeloAvionList();
        entity.setTipoEquipoAvionHasModeloAvionList(null);
        final List<PaxCompementario> entity_paxCompementarioHasModeloAvionList =  entity.getPaxCompementarioHasModeloAvionList();
        entity.setPaxCompementarioHasModeloAvionList(null);
        final List<Acomodo> entity_acomodoHasModeloAvionList =  entity.getAcomodoHasModeloAvionList();
        entity.setAcomodoHasModeloAvionList(null);
        final List<Grafico> entity_graficoHasModeloAvionList =  entity.getGraficoHasModeloAvionList();
        entity.setGraficoHasModeloAvionList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoList =  entity.getTablaAbordamientoList();
        entity.setTablaAbordamientoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setAsignacionServicioHasModeloAvionList(entity_asignacionServicioHasModeloAvionList);
        entity.setCantidadVueloComplementarioHasModeloAvionList(entity_cantidadVueloComplementarioHasModeloAvionList);
        entity.setTipoEquipoAvionHasModeloAvionList(entity_tipoEquipoAvionHasModeloAvionList);
        entity.setPaxCompementarioHasModeloAvionList(entity_paxCompementarioHasModeloAvionList);
        entity.setAcomodoHasModeloAvionList(entity_acomodoHasModeloAvionList);
        entity.setGraficoHasModeloAvionList(entity_graficoHasModeloAvionList);
        entity.setTablaAbordamientoList(entity_tablaAbordamientoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public ModeloAvion update(ModeloAvion entity) {
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
        final List<AsignacionServicio> entity_asignacionServicioHasModeloAvionList =  entity.getAsignacionServicioHasModeloAvionList();
        entity.setAsignacionServicioHasModeloAvionList(null);
        final List<CantidadVueloComplementario> entity_cantidadVueloComplementarioHasModeloAvionList =  entity.getCantidadVueloComplementarioHasModeloAvionList();
        entity.setCantidadVueloComplementarioHasModeloAvionList(null);
        final List<TipoEquipoAvion> entity_tipoEquipoAvionHasModeloAvionList =  entity.getTipoEquipoAvionHasModeloAvionList();
        entity.setTipoEquipoAvionHasModeloAvionList(null);
        final List<PaxCompementario> entity_paxCompementarioHasModeloAvionList =  entity.getPaxCompementarioHasModeloAvionList();
        entity.setPaxCompementarioHasModeloAvionList(null);
        final List<Acomodo> entity_acomodoHasModeloAvionList =  entity.getAcomodoHasModeloAvionList();
        entity.setAcomodoHasModeloAvionList(null);
        final List<Grafico> entity_graficoHasModeloAvionList =  entity.getGraficoHasModeloAvionList();
        entity.setGraficoHasModeloAvionList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoList =  entity.getTablaAbordamientoList();
        entity.setTablaAbordamientoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setAsignacionServicioHasModeloAvionList(entity_asignacionServicioHasModeloAvionList);
        entity.setCantidadVueloComplementarioHasModeloAvionList(entity_cantidadVueloComplementarioHasModeloAvionList);
        entity.setTipoEquipoAvionHasModeloAvionList(entity_tipoEquipoAvionHasModeloAvionList);
        entity.setPaxCompementarioHasModeloAvionList(entity_paxCompementarioHasModeloAvionList);
        entity.setAcomodoHasModeloAvionList(entity_acomodoHasModeloAvionList);
        entity.setGraficoHasModeloAvionList(entity_graficoHasModeloAvionList);
        entity.setTablaAbordamientoList(entity_tablaAbordamientoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<ModeloAvion> findAllLike(ModeloAvion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ModeloAvion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdModeloAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idModeloAvion = :idModeloAvion");
			}
			if(x.getModelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.modelo = :modelo");
			}
			if(x.getDescripcion() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcion = :descripcion");
			}
			if(x.getIdTipoCabina() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoCabina = :idTipoCabina");
			}
			if(x.getCompania() != null){
			    paramAsigned++;
			    sbq.append(" and x.compania = :compania");
			}
			if(x.getIdFabricante() != null){
			    paramAsigned++;
			    sbq.append(" and x.idFabricante = :idFabricante");
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
		
		TypedQuery<ModeloAvion> nq = em.createQuery(sbq.toString(), ModeloAvion.class);
		
		if(paramAsigned>0){
			if(x.getIdModeloAvion() != null){
			    nq.setParameter("idModeloAvion",x.getIdModeloAvion());
			}
			if(x.getModelo() != null){
			    nq.setParameter("modelo",x.getModelo());
			}
			if(x.getDescripcion() != null){
			    nq.setParameter("descripcion",x.getDescripcion());
			}
			if(x.getIdTipoCabina() != null){
			    nq.setParameter("idTipoCabina",x.getIdTipoCabina());
			}
			if(x.getCompania() != null){
			    nq.setParameter("compania",x.getCompania());
			}
			if(x.getIdFabricante() != null){
			    nq.setParameter("idFabricante",x.getIdFabricante());
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
	public ModeloAvion findByPK_EAGER(Object pk){
        ModeloAvion x = getEntityManager().find(ModeloAvion.class, pk);
        if( x != null){
            if(x.getIdTipoCabina() !=null && x.getIdTipoCabina().getIdParametro()!=null){} 
            if(x.getCompania() !=null && x.getCompania().getIdCompania()!=null){} 
            if(x.getIdFabricante() !=null && x.getIdFabricante().getIdParametro()!=null){} 
            for(AsignacionServicio x_asignacionServicio: x.getAsignacionServicioHasModeloAvionList() ) {} 
            for(CantidadVueloComplementario x_cantidadVueloComplementario: x.getCantidadVueloComplementarioHasModeloAvionList() ) {} 
            for(TipoEquipoAvion x_tipoEquipoAvion: x.getTipoEquipoAvionHasModeloAvionList() ) {} 
            for(PaxCompementario x_paxCompementario: x.getPaxCompementarioHasModeloAvionList() ) {} 
            for(Acomodo x_acomodo: x.getAcomodoHasModeloAvionList() ) {} 
            for(Grafico x_grafico: x.getGraficoHasModeloAvionList() ) {} 
            for(TablaAbordamiento x_TablaAbordamiento: x.getTablaAbordamientoList() ) {} 

        }
        return x;
    }

	@Override
	public List<ModeloAvion> findAll() {
		TypedQuery<ModeloAvion> nq = em.createNamedQuery("ModeloAvion.findAll", ModeloAvion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ModeloAvion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
