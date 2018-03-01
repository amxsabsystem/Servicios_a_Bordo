package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ProveedorEstacion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ProveedorEstacionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table proveedor_estacion by ProveedorEstacionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "proveedorEstacion_RSB",
    mappedName   = "ProveedorEstacion_RSB",
    description  = "ProveedorEstacionFacadeRemote-Stateless-Session EJB-3.1"
)
public class ProveedorEstacionFacade extends AbstractFacade<ProveedorEstacion> implements ProveedorEstacionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProveedorEstacionFacade() {
		super(ProveedorEstacion.class);
	}

	@Override
	public ProveedorEstacion create(ProveedorEstacion entity) {
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
        final List<ComunicadoProveedorEstacion> entity_comunicadoProveedorEstacionHasProveedorEstacionList =  entity.getComunicadoProveedorEstacionHasProveedorEstacionList();
        entity.setComunicadoProveedorEstacionHasProveedorEstacionList(null);
        final List<Reporte> entity_reporteHasIdResponsableProveedorEstacionFinalList =  entity.getReporteHasIdResponsableProveedorEstacionFinalList();
        entity.setReporteHasIdResponsableProveedorEstacionFinalList(null);
        final List<Reporte> entity_reporteHasIdResponsableProveedorEstacionList =  entity.getReporteHasIdResponsableProveedorEstacionList();
        entity.setReporteHasIdResponsableProveedorEstacionList(null);
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasProveedorEstacionList =  entity.getContactoProveedorEstacionHasProveedorEstacionList();
        entity.setContactoProveedorEstacionHasProveedorEstacionList(null);
        final List<Potencial> entity_potencialHasProveedorEstacionList =  entity.getPotencialHasProveedorEstacionList();
        entity.setPotencialHasProveedorEstacionList(null);
        final List<BitacoraComunicado> entity_bitacoraComunicadoHasProveedorEstacionList =  entity.getBitacoraComunicadoHasProveedorEstacionList();
        entity.setBitacoraComunicadoHasProveedorEstacionList(null);
        final List<Seguimiento> entity_seguimientoHasProveedorEstacionList =  entity.getSeguimientoHasProveedorEstacionList();
        entity.setSeguimientoHasProveedorEstacionList(null);
        final List<TipoProveedor> entity_tipoProveedorList =  entity.getTipoProveedorList();
        entity.setTipoProveedorList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setComunicadoProveedorEstacionHasProveedorEstacionList(entity_comunicadoProveedorEstacionHasProveedorEstacionList);
        entity.setReporteHasIdResponsableProveedorEstacionFinalList(entity_reporteHasIdResponsableProveedorEstacionFinalList);
        entity.setReporteHasIdResponsableProveedorEstacionList(entity_reporteHasIdResponsableProveedorEstacionList);
        entity.setContactoProveedorEstacionHasProveedorEstacionList(entity_contactoProveedorEstacionHasProveedorEstacionList);
        entity.setPotencialHasProveedorEstacionList(entity_potencialHasProveedorEstacionList);
        entity.setBitacoraComunicadoHasProveedorEstacionList(entity_bitacoraComunicadoHasProveedorEstacionList);
        entity.setSeguimientoHasProveedorEstacionList(entity_seguimientoHasProveedorEstacionList);
        entity.setTipoProveedorList(entity_tipoProveedorList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public ProveedorEstacion update(ProveedorEstacion entity) {
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
        final List<ComunicadoProveedorEstacion> entity_comunicadoProveedorEstacionHasProveedorEstacionList =  entity.getComunicadoProveedorEstacionHasProveedorEstacionList();
        entity.setComunicadoProveedorEstacionHasProveedorEstacionList(null);
        final List<Reporte> entity_reporteHasIdResponsableProveedorEstacionFinalList =  entity.getReporteHasIdResponsableProveedorEstacionFinalList();
        entity.setReporteHasIdResponsableProveedorEstacionFinalList(null);
        final List<Reporte> entity_reporteHasIdResponsableProveedorEstacionList =  entity.getReporteHasIdResponsableProveedorEstacionList();
        entity.setReporteHasIdResponsableProveedorEstacionList(null);
        final List<ContactoProveedorEstacion> entity_contactoProveedorEstacionHasProveedorEstacionList =  entity.getContactoProveedorEstacionHasProveedorEstacionList();
        entity.setContactoProveedorEstacionHasProveedorEstacionList(null);
        final List<Potencial> entity_potencialHasProveedorEstacionList =  entity.getPotencialHasProveedorEstacionList();
        entity.setPotencialHasProveedorEstacionList(null);
        final List<BitacoraComunicado> entity_bitacoraComunicadoHasProveedorEstacionList =  entity.getBitacoraComunicadoHasProveedorEstacionList();
        entity.setBitacoraComunicadoHasProveedorEstacionList(null);
        final List<Seguimiento> entity_seguimientoHasProveedorEstacionList =  entity.getSeguimientoHasProveedorEstacionList();
        entity.setSeguimientoHasProveedorEstacionList(null);
        final List<TipoProveedor> entity_tipoProveedorList =  entity.getTipoProveedorList();
        entity.setTipoProveedorList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setComunicadoProveedorEstacionHasProveedorEstacionList(entity_comunicadoProveedorEstacionHasProveedorEstacionList);
        entity.setReporteHasIdResponsableProveedorEstacionFinalList(entity_reporteHasIdResponsableProveedorEstacionFinalList);
        entity.setReporteHasIdResponsableProveedorEstacionList(entity_reporteHasIdResponsableProveedorEstacionList);
        entity.setContactoProveedorEstacionHasProveedorEstacionList(entity_contactoProveedorEstacionHasProveedorEstacionList);
        entity.setPotencialHasProveedorEstacionList(entity_potencialHasProveedorEstacionList);
        entity.setBitacoraComunicadoHasProveedorEstacionList(entity_bitacoraComunicadoHasProveedorEstacionList);
        entity.setSeguimientoHasProveedorEstacionList(entity_seguimientoHasProveedorEstacionList);
        entity.setTipoProveedorList(entity_tipoProveedorList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<ProveedorEstacion> findAllLike(ProveedorEstacion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ProveedorEstacion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idProveedorEstacion = :idProveedorEstacion");
			}
			if(x.getEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacion = :estacion");
			}
			if(x.getProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedor = :proveedor");
			}
			if(x.getClaveProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.claveProveedorEstacion = :claveProveedorEstacion");
			}
			if(x.getRazonSocial() != null){
			    paramAsigned++;
			    sbq.append(" and x.razonSocial = :razonSocial");
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
		
		TypedQuery<ProveedorEstacion> nq = em.createQuery(sbq.toString(), ProveedorEstacion.class);
		
		if(paramAsigned>0){
			if(x.getIdProveedorEstacion() != null){
			    nq.setParameter("idProveedorEstacion",x.getIdProveedorEstacion());
			}
			if(x.getEstacion() != null){
			    nq.setParameter("estacion",x.getEstacion());
			}
			if(x.getProveedor() != null){
			    nq.setParameter("proveedor",x.getProveedor());
			}
			if(x.getClaveProveedorEstacion() != null){
			    nq.setParameter("claveProveedorEstacion",x.getClaveProveedorEstacion());
			}
			if(x.getRazonSocial() != null){
			    nq.setParameter("razonSocial",x.getRazonSocial());
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
	public ProveedorEstacion findByPK_EAGER(Object pk){
        ProveedorEstacion x = getEntityManager().find(ProveedorEstacion.class, pk);
        if( x != null){
            if(x.getEstacion() !=null && x.getEstacion().getIdEstacion()!=null){} 
            if(x.getProveedor() !=null && x.getProveedor().getClaveProveedor()!=null){} 
            for(ComunicadoProveedorEstacion x_comunicadoProveedorEstacion: x.getComunicadoProveedorEstacionHasProveedorEstacionList() ) {} 
            for(Reporte x_reporte: x.getReporteHasIdResponsableProveedorEstacionFinalList() ) {} 
            for(Reporte x_reporte: x.getReporteHasIdResponsableProveedorEstacionList() ) {} 
            for(ContactoProveedorEstacion x_contactoProveedorEstacion: x.getContactoProveedorEstacionHasProveedorEstacionList() ) {} 
            for(Potencial x_potencial: x.getPotencialHasProveedorEstacionList() ) {} 
            for(BitacoraComunicado x_bitacoraComunicado: x.getBitacoraComunicadoHasProveedorEstacionList() ) {} 
            for(Seguimiento x_seguimiento: x.getSeguimientoHasProveedorEstacionList() ) {} 
            for(TipoProveedor x_TipoProveedor: x.getTipoProveedorList() ) {} 

        }
        return x;
    }

	@Override
	public List<ProveedorEstacion> findAll() {
		TypedQuery<ProveedorEstacion> nq = em.createNamedQuery("ProveedorEstacion.findAll", ProveedorEstacion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ProveedorEstacion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
