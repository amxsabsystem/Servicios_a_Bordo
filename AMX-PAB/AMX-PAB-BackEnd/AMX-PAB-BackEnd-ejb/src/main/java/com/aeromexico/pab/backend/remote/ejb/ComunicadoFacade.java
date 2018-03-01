package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Comunicado;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ComunicadoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Comunicado by ComunicadoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "comunicado_RSB",
    mappedName   = "Comunicado_RSB",
    description  = "ComunicadoFacadeRemote-Stateless-Session EJB-3.1"
)
public class ComunicadoFacade extends AbstractFacade<Comunicado> implements ComunicadoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ComunicadoFacade() {
		super(Comunicado.class);
	}

	@Override
	public Comunicado create(Comunicado entity) {
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
        final List<ComunicadoProveedorEstacion> entity_comunicadoProveedorEstacionHasComunicadoList =  entity.getComunicadoProveedorEstacionHasComunicadoList();
        entity.setComunicadoProveedorEstacionHasComunicadoList(null);
        final List<ComunicadoAreas> entity_comunicadoAreasHasComunicadoList =  entity.getComunicadoAreasHasComunicadoList();
        entity.setComunicadoAreasHasComunicadoList(null);
        final List<BitacoraComunicado> entity_bitacoraComunicadoHasComunicadoList =  entity.getBitacoraComunicadoHasComunicadoList();
        entity.setBitacoraComunicadoHasComunicadoList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setComunicadoProveedorEstacionHasComunicadoList(entity_comunicadoProveedorEstacionHasComunicadoList);
        entity.setComunicadoAreasHasComunicadoList(entity_comunicadoAreasHasComunicadoList);
        entity.setBitacoraComunicadoHasComunicadoList(entity_bitacoraComunicadoHasComunicadoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Comunicado update(Comunicado entity) {
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
        final List<ComunicadoProveedorEstacion> entity_comunicadoProveedorEstacionHasComunicadoList =  entity.getComunicadoProveedorEstacionHasComunicadoList();
        entity.setComunicadoProveedorEstacionHasComunicadoList(null);
        final List<ComunicadoAreas> entity_comunicadoAreasHasComunicadoList =  entity.getComunicadoAreasHasComunicadoList();
        entity.setComunicadoAreasHasComunicadoList(null);
        final List<BitacoraComunicado> entity_bitacoraComunicadoHasComunicadoList =  entity.getBitacoraComunicadoHasComunicadoList();
        entity.setBitacoraComunicadoHasComunicadoList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setComunicadoProveedorEstacionHasComunicadoList(entity_comunicadoProveedorEstacionHasComunicadoList);
        entity.setComunicadoAreasHasComunicadoList(entity_comunicadoAreasHasComunicadoList);
        entity.setBitacoraComunicadoHasComunicadoList(entity_bitacoraComunicadoHasComunicadoList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Comunicado> findAllLike(Comunicado x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Comunicado x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.idComunicado = :idComunicado");
			}
			if(x.getReferencia() != null){
			    paramAsigned++;
			    sbq.append(" and x.referencia = :referencia");
			}
			if(x.getIdIdioma() != null){
			    paramAsigned++;
			    sbq.append(" and x.idIdioma = :idIdioma");
			}
			if(x.getIdTema() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTema = :idTema");
			}
			if(x.getEmpleado() != null){
			    paramAsigned++;
			    sbq.append(" and x.empleado = :empleado");
			}
			if(x.getArea() != null){
			    paramAsigned++;
			    sbq.append(" and x.area = :area");
			}
			if(x.getEstatusComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.estatusComunicado = :estatusComunicado");
			}
			if(x.getAccionComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.accionComunicado = :accionComunicado");
			}
			if(x.getRefOtroComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.refOtroComunicado = :refOtroComunicado");
			}
			if(x.getMensaje() != null){
			    paramAsigned++;
			    sbq.append(" and x.mensaje = :mensaje");
			}
			if(x.getNoRevision() != null){
			    paramAsigned++;
			    sbq.append(" and x.noRevision = :noRevision");
			}
			if(x.getFechaInicioPublicacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaInicioPublicacion = :fechaInicioPublicacion");
			}
			if(x.getFechaFinPublicacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaFinPublicacion = :fechaFinPublicacion");
			}
			if(x.getTitulo() != null){
			    paramAsigned++;
			    sbq.append(" and x.titulo = :titulo");
			}
			if(x.getDirigidoSiNo() != null){
			    paramAsigned++;
			    sbq.append(" and x.dirigidoSiNo = :dirigidoSiNo");
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
			if(x.getMensajeSlider() != null){
			    paramAsigned++;
			    sbq.append(" and x.mensajeSlider = :mensajeSlider");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Comunicado> nq = em.createQuery(sbq.toString(), Comunicado.class);
		
		if(paramAsigned>0){
			if(x.getIdComunicado() != null){
			    nq.setParameter("idComunicado",x.getIdComunicado());
			}
			if(x.getReferencia() != null){
			    nq.setParameter("referencia",x.getReferencia());
			}
			if(x.getIdIdioma() != null){
			    nq.setParameter("idIdioma",x.getIdIdioma());
			}
			if(x.getIdTema() != null){
			    nq.setParameter("idTema",x.getIdTema());
			}
			if(x.getEmpleado() != null){
			    nq.setParameter("empleado",x.getEmpleado());
			}
			if(x.getArea() != null){
			    nq.setParameter("area",x.getArea());
			}
			if(x.getEstatusComunicado() != null){
			    nq.setParameter("estatusComunicado",x.getEstatusComunicado());
			}
			if(x.getAccionComunicado() != null){
			    nq.setParameter("accionComunicado",x.getAccionComunicado());
			}
			if(x.getRefOtroComunicado() != null){
			    nq.setParameter("refOtroComunicado",x.getRefOtroComunicado());
			}
			if(x.getMensaje() != null){
			    nq.setParameter("mensaje",x.getMensaje());
			}
			if(x.getNoRevision() != null){
			    nq.setParameter("noRevision",x.getNoRevision());
			}
			if(x.getFechaInicioPublicacion() != null){
			    nq.setParameter("fechaInicioPublicacion",x.getFechaInicioPublicacion());
			}
			if(x.getFechaFinPublicacion() != null){
			    nq.setParameter("fechaFinPublicacion",x.getFechaFinPublicacion());
			}
			if(x.getTitulo() != null){
			    nq.setParameter("titulo",x.getTitulo());
			}
			if(x.getDirigidoSiNo() != null){
			    nq.setParameter("dirigidoSiNo",x.getDirigidoSiNo());
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
			if(x.getMensajeSlider() != null){
			    nq.setParameter("mensajeSlider",x.getMensajeSlider());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Comunicado findByPK_EAGER(Object pk){
        Comunicado x = getEntityManager().find(Comunicado.class, pk);
        if( x != null){
            if(x.getIdIdioma() !=null && x.getIdIdioma().getIdParametro()!=null){} 
            if(x.getIdTema() !=null && x.getIdTema().getIdTemaComunicado()!=null){} 
            if(x.getEmpleado() !=null && x.getEmpleado().getIdEmpleado()!=null){} 
            if(x.getArea() !=null && x.getArea().getIdArea()!=null){} 
            if(x.getEstatusComunicado() !=null && x.getEstatusComunicado().getIdParametro()!=null){} 
            if(x.getAccionComunicado() !=null && x.getAccionComunicado().getIdParametro()!=null){} 
            for(ComunicadoProveedorEstacion x_comunicadoProveedorEstacion: x.getComunicadoProveedorEstacionHasComunicadoList() ) {} 
            for(ComunicadoAreas x_comunicadoAreas: x.getComunicadoAreasHasComunicadoList() ) {} 
            for(BitacoraComunicado x_bitacoraComunicado: x.getBitacoraComunicadoHasComunicadoList() ) {} 

        }
        return x;
    }

	@Override
	public List<Comunicado> findAll() {
		TypedQuery<Comunicado> nq = em.createNamedQuery("Comunicado.findAll", Comunicado.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Comunicado.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
