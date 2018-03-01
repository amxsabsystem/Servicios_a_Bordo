package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.DetalleTablaComplementario;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.DetalleTablaComplementarioFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table detalle_tabla_complementario by DetalleTablaComplementarioFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "detalleTablaComplementario_RSB",
    mappedName   = "DetalleTablaComplementario_RSB",
    description  = "DetalleTablaComplementarioFacadeRemote-Stateless-Session EJB-3.1"
)
public class DetalleTablaComplementarioFacade extends AbstractFacade<DetalleTablaComplementario> implements DetalleTablaComplementarioFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DetalleTablaComplementarioFacade() {
		super(DetalleTablaComplementario.class);
	}

	@Override
	public DetalleTablaComplementario create(DetalleTablaComplementario entity) {
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
        final List<MaterialExtra> entity_materialExtraHasDetalleTablaComplementarioList =  entity.getMaterialExtraHasDetalleTablaComplementarioList();
        entity.setMaterialExtraHasDetalleTablaComplementarioList(null);
        final List<CantidadVueloComplementario> entity_cantidadVueloComplementarioHasIdDetalleComplementarioList =  entity.getCantidadVueloComplementarioHasIdDetalleComplementarioList();
        entity.setCantidadVueloComplementarioHasIdDetalleComplementarioList(null);
        final List<PaxCompementario> entity_paxCompementarioHasDetalleTablaComplementarioList =  entity.getPaxCompementarioHasDetalleTablaComplementarioList();
        entity.setPaxCompementarioHasDetalleTablaComplementarioList(null);
        final List<Parametro> entity_parametroList =  entity.getParametroList();
        entity.setParametroList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setMaterialExtraHasDetalleTablaComplementarioList(entity_materialExtraHasDetalleTablaComplementarioList);
        entity.setCantidadVueloComplementarioHasIdDetalleComplementarioList(entity_cantidadVueloComplementarioHasIdDetalleComplementarioList);
        entity.setPaxCompementarioHasDetalleTablaComplementarioList(entity_paxCompementarioHasDetalleTablaComplementarioList);
        entity.setParametroList(entity_parametroList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public DetalleTablaComplementario update(DetalleTablaComplementario entity) {
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
        final List<MaterialExtra> entity_materialExtraHasDetalleTablaComplementarioList =  entity.getMaterialExtraHasDetalleTablaComplementarioList();
        entity.setMaterialExtraHasDetalleTablaComplementarioList(null);
        final List<CantidadVueloComplementario> entity_cantidadVueloComplementarioHasIdDetalleComplementarioList =  entity.getCantidadVueloComplementarioHasIdDetalleComplementarioList();
        entity.setCantidadVueloComplementarioHasIdDetalleComplementarioList(null);
        final List<PaxCompementario> entity_paxCompementarioHasDetalleTablaComplementarioList =  entity.getPaxCompementarioHasDetalleTablaComplementarioList();
        entity.setPaxCompementarioHasDetalleTablaComplementarioList(null);
        final List<Parametro> entity_parametroList =  entity.getParametroList();
        entity.setParametroList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setMaterialExtraHasDetalleTablaComplementarioList(entity_materialExtraHasDetalleTablaComplementarioList);
        entity.setCantidadVueloComplementarioHasIdDetalleComplementarioList(entity_cantidadVueloComplementarioHasIdDetalleComplementarioList);
        entity.setPaxCompementarioHasDetalleTablaComplementarioList(entity_paxCompementarioHasDetalleTablaComplementarioList);
        entity.setParametroList(entity_parametroList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<DetalleTablaComplementario> findAllLike(DetalleTablaComplementario x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM DetalleTablaComplementario x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdDetalleTablaComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.idDetalleTablaComplementario = :idDetalleTablaComplementario");
			}
			if(x.getTablaAbordamiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tablaAbordamiento = :tablaAbordamiento");
			}
			if(x.getNombreComplementario() != null){
			    paramAsigned++;
			    sbq.append(" and x.nombreComplementario = :nombreComplementario");
			}
			if(x.getPorcentaje()  != 0){
			    paramAsigned++;
			    sbq.append(" and x.porcentaje = :porcentaje");
			}
			if(x.getAplicaPax() != null){
			    paramAsigned++;
			    sbq.append(" and x.aplicaPax = :aplicaPax");
			}
			if(x.getAplicaCantidadVuelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.aplicaCantidadVuelo = :aplicaCantidadVuelo");
			}
			if(x.getAplicaMaterialExtra() != null){
			    paramAsigned++;
			    sbq.append(" and x.aplicaMaterialExtra = :aplicaMaterialExtra");
			}
			if(x.getAplicaKit() != null){
			    paramAsigned++;
			    sbq.append(" and x.aplicaKit = :aplicaKit");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<DetalleTablaComplementario> nq = em.createQuery(sbq.toString(), DetalleTablaComplementario.class);
		
		if(paramAsigned>0){
			if(x.getIdDetalleTablaComplementario() != null){
			    nq.setParameter("idDetalleTablaComplementario",x.getIdDetalleTablaComplementario());
			}
			if(x.getTablaAbordamiento() != null){
			    nq.setParameter("tablaAbordamiento",x.getTablaAbordamiento());
			}
			if(x.getNombreComplementario() != null){
			    nq.setParameter("nombreComplementario",x.getNombreComplementario());
			}
			if(x.getPorcentaje()  != 0){
			    nq.setParameter("porcentaje",x.getPorcentaje());
			}
			if(x.getAplicaPax() != null){
			    nq.setParameter("aplicaPax",x.getAplicaPax());
			}
			if(x.getAplicaCantidadVuelo() != null){
			    nq.setParameter("aplicaCantidadVuelo",x.getAplicaCantidadVuelo());
			}
			if(x.getAplicaMaterialExtra() != null){
			    nq.setParameter("aplicaMaterialExtra",x.getAplicaMaterialExtra());
			}
			if(x.getAplicaKit() != null){
			    nq.setParameter("aplicaKit",x.getAplicaKit());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public DetalleTablaComplementario findByPK_EAGER(Object pk){
        DetalleTablaComplementario x = getEntityManager().find(DetalleTablaComplementario.class, pk);
        if( x != null){
            if(x.getTablaAbordamiento() !=null && x.getTablaAbordamiento().getIdTablaAbordamiento()!=null){} 
            for(MaterialExtra x_materialExtra: x.getMaterialExtraHasDetalleTablaComplementarioList() ) {} 
            for(CantidadVueloComplementario x_cantidadVueloComplementario: x.getCantidadVueloComplementarioHasIdDetalleComplementarioList() ) {} 
            for(PaxCompementario x_paxCompementario: x.getPaxCompementarioHasDetalleTablaComplementarioList() ) {} 
            for(Parametro x_Parametro: x.getParametroList() ) {} 

        }
        return x;
    }

	@Override
	public List<DetalleTablaComplementario> findAll() {
		TypedQuery<DetalleTablaComplementario> nq = em.createNamedQuery("DetalleTablaComplementario.findAll", DetalleTablaComplementario.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("DetalleTablaComplementario.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
