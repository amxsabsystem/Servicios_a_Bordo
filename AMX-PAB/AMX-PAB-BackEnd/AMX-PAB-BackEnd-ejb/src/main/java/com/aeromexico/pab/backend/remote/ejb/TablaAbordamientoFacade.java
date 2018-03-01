package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.TablaAbordamiento;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.TablaAbordamientoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table tabla_abordamiento by TablaAbordamientoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "tablaAbordamiento_RSB",
    mappedName   = "TablaAbordamiento_RSB",
    description  = "TablaAbordamientoFacadeRemote-Stateless-Session EJB-3.1"
)
public class TablaAbordamientoFacade extends AbstractFacade<TablaAbordamiento> implements TablaAbordamientoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TablaAbordamientoFacade() {
		super(TablaAbordamiento.class);
	}

	@Override
	public TablaAbordamiento create(TablaAbordamiento entity) {
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
        final List<RegionAbordaje> entity_regionAbordajeHasTablaAbordamientoList =  entity.getRegionAbordajeHasTablaAbordamientoList();
        entity.setRegionAbordajeHasTablaAbordamientoList(null);
        final List<TsuAbordamiento> entity_tsuAbordamientoHasTablaAbordamientoList =  entity.getTsuAbordamientoHasTablaAbordamientoList();
        entity.setTsuAbordamientoHasTablaAbordamientoList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasTablaAbordamientoList =  entity.getDetalleTablaTsuHasTablaAbordamientoList();
        entity.setDetalleTablaTsuHasTablaAbordamientoList(null);
        final List<DetalleTablaComplementario> entity_detalleTablaComplementarioHasTablaAbordamientoList =  entity.getDetalleTablaComplementarioHasTablaAbordamientoList();
        entity.setDetalleTablaComplementarioHasTablaAbordamientoList(null);
        final List<Sobreabordaje> entity_sobreabordajeHasTablaAbordamientoList =  entity.getSobreabordajeHasTablaAbordamientoList();
        entity.setSobreabordajeHasTablaAbordamientoList(null);
        final List<ModeloAvion> entity_modeloAvionList =  entity.getModeloAvionList();
        entity.setModeloAvionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setRegionAbordajeHasTablaAbordamientoList(entity_regionAbordajeHasTablaAbordamientoList);
        entity.setTsuAbordamientoHasTablaAbordamientoList(entity_tsuAbordamientoHasTablaAbordamientoList);
        entity.setDetalleTablaTsuHasTablaAbordamientoList(entity_detalleTablaTsuHasTablaAbordamientoList);
        entity.setDetalleTablaComplementarioHasTablaAbordamientoList(entity_detalleTablaComplementarioHasTablaAbordamientoList);
        entity.setSobreabordajeHasTablaAbordamientoList(entity_sobreabordajeHasTablaAbordamientoList);
        entity.setModeloAvionList(entity_modeloAvionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public TablaAbordamiento update(TablaAbordamiento entity) {
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
        final List<RegionAbordaje> entity_regionAbordajeHasTablaAbordamientoList =  entity.getRegionAbordajeHasTablaAbordamientoList();
        entity.setRegionAbordajeHasTablaAbordamientoList(null);
        final List<TsuAbordamiento> entity_tsuAbordamientoHasTablaAbordamientoList =  entity.getTsuAbordamientoHasTablaAbordamientoList();
        entity.setTsuAbordamientoHasTablaAbordamientoList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasTablaAbordamientoList =  entity.getDetalleTablaTsuHasTablaAbordamientoList();
        entity.setDetalleTablaTsuHasTablaAbordamientoList(null);
        final List<DetalleTablaComplementario> entity_detalleTablaComplementarioHasTablaAbordamientoList =  entity.getDetalleTablaComplementarioHasTablaAbordamientoList();
        entity.setDetalleTablaComplementarioHasTablaAbordamientoList(null);
        final List<Sobreabordaje> entity_sobreabordajeHasTablaAbordamientoList =  entity.getSobreabordajeHasTablaAbordamientoList();
        entity.setSobreabordajeHasTablaAbordamientoList(null);
        final List<ModeloAvion> entity_modeloAvionList =  entity.getModeloAvionList();
        entity.setModeloAvionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setRegionAbordajeHasTablaAbordamientoList(entity_regionAbordajeHasTablaAbordamientoList);
        entity.setTsuAbordamientoHasTablaAbordamientoList(entity_tsuAbordamientoHasTablaAbordamientoList);
        entity.setDetalleTablaTsuHasTablaAbordamientoList(entity_detalleTablaTsuHasTablaAbordamientoList);
        entity.setDetalleTablaComplementarioHasTablaAbordamientoList(entity_detalleTablaComplementarioHasTablaAbordamientoList);
        entity.setSobreabordajeHasTablaAbordamientoList(entity_sobreabordajeHasTablaAbordamientoList);
        entity.setModeloAvionList(entity_modeloAvionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<TablaAbordamiento> findAllLike(TablaAbordamiento x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM TablaAbordamiento x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTablaAbordamiento = :idTablaAbordamiento");
			}
			if(x.getIdOrigenVuelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idOrigenVuelo = :idOrigenVuelo");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getCodigoServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.codigoServicio = :codigoServicio");
			}
			if(x.getRevision()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.revision = :revision");
			}
			if(x.getTodasRegiones() != null){
			    paramAsigned++;
			    sbq.append(" and x.todasRegiones = :todasRegiones");
			}
			if(x.getTodosModelosAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.todosModelosAvion = :todosModelosAvion");
			}
			if(x.getAplicaTsu() != null){
			    paramAsigned++;
			    sbq.append(" and x.aplicaTsu = :aplicaTsu");
			}
			if(x.getAplicaSobreabordaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.aplicaSobreabordaje = :aplicaSobreabordaje");
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
		
		TypedQuery<TablaAbordamiento> nq = em.createQuery(sbq.toString(), TablaAbordamiento.class);
		
		if(paramAsigned>0){
			if(x.getIdTablaAbordamiento() != null){
			    nq.setParameter("idTablaAbordamiento",x.getIdTablaAbordamiento());
			}
			if(x.getIdOrigenVuelo() != null){
			    nq.setParameter("idOrigenVuelo",x.getIdOrigenVuelo());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getCodigoServicio() != null){
			    nq.setParameter("codigoServicio",x.getCodigoServicio());
			}
			if(x.getRevision()  != 0){
			    nq.setParameter("revision",x.getRevision());
			}
			if(x.getTodasRegiones() != null){
			    nq.setParameter("todasRegiones",x.getTodasRegiones());
			}
			if(x.getTodosModelosAvion() != null){
			    nq.setParameter("todosModelosAvion",x.getTodosModelosAvion());
			}
			if(x.getAplicaTsu() != null){
			    nq.setParameter("aplicaTsu",x.getAplicaTsu());
			}
			if(x.getAplicaSobreabordaje() != null){
			    nq.setParameter("aplicaSobreabordaje",x.getAplicaSobreabordaje());
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
	public TablaAbordamiento findByPK_EAGER(Object pk){
        TablaAbordamiento x = getEntityManager().find(TablaAbordamiento.class, pk);
        if( x != null){
            if(x.getIdOrigenVuelo() !=null && x.getIdOrigenVuelo().getIdParametro()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getCodigoServicio() !=null && x.getCodigoServicio().getIdCodigoServicio()!=null){} 
            for(RegionAbordaje x_regionAbordaje: x.getRegionAbordajeHasTablaAbordamientoList() ) {} 
            for(TsuAbordamiento x_tsuAbordamiento: x.getTsuAbordamientoHasTablaAbordamientoList() ) {} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasTablaAbordamientoList() ) {} 
            for(DetalleTablaComplementario x_detalleTablaComplementario: x.getDetalleTablaComplementarioHasTablaAbordamientoList() ) {} 
            for(Sobreabordaje x_sobreabordaje: x.getSobreabordajeHasTablaAbordamientoList() ) {} 
            for(ModeloAvion x_ModeloAvion: x.getModeloAvionList() ) {} 

        }
        return x;
    }

	@Override
	public List<TablaAbordamiento> findAll() {
		TypedQuery<TablaAbordamiento> nq = em.createNamedQuery("TablaAbordamiento.findAll", TablaAbordamiento.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("TablaAbordamiento.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
