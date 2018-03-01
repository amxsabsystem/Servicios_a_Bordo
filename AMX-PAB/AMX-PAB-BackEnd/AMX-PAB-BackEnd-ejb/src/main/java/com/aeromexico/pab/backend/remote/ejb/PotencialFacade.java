package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Potencial;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.PotencialFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table potencial by PotencialFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "potencial_RSB",
    mappedName   = "Potencial_RSB",
    description  = "PotencialFacadeRemote-Stateless-Session EJB-3.1"
)
public class PotencialFacade extends AbstractFacade<Potencial> implements PotencialFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PotencialFacade() {
		super(Potencial.class);
	}

	@Override
	public Potencial create(Potencial entity) {
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
	public Potencial update(Potencial entity) {
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
	public List<Potencial> findAllLike(Potencial x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Potencial x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdPotencial() != null){
			    paramAsigned++;
			    sbq.append(" and x.idPotencial = :idPotencial");
			}
			if(x.getProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacion = :proveedorEstacion");
			}
			if(x.getProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedor = :proveedor");
			}
			if(x.getCodigoServicio() != null){
			    paramAsigned++;
			    sbq.append(" and x.codigoServicio = :codigoServicio");
			}
			if(x.getClase() != null){
			    paramAsigned++;
			    sbq.append(" and x.clase = :clase");
			}
			if(x.getIdNociclotemporada() != null){
			    paramAsigned++;
			    sbq.append(" and x.idNociclotemporada = :idNociclotemporada");
			}
			if(x.getIdTipociclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipociclo = :idTipociclo");
			}
			if(x.getIdEstatuspotencial() != null){
			    paramAsigned++;
			    sbq.append(" and x.idEstatuspotencial = :idEstatuspotencial");
			}
			if(x.getUrlPotencial() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlPotencial = :urlPotencial");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
			}
			if(x.getUsuarioCreo() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuarioCreo = :usuarioCreo");
			}
			if(x.getFechaCreo() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaCreo = :fechaCreo");
			}
			if(x.getObservaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.observaciones = :observaciones");
			}
			if(x.getUsuarioObservaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.usuarioObservaciones = :usuarioObservaciones");
			}
			if(x.getFechaObservaciones() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaObservaciones = :fechaObservaciones");
			}
			if(x.getNotaRecordatorio() != null){
			    paramAsigned++;
			    sbq.append(" and x.notaRecordatorio = :notaRecordatorio");
			}
			if(x.getFechaRecordatorio() != null){
			    paramAsigned++;
			    sbq.append(" and x.fechaRecordatorio = :fechaRecordatorio");
			}
			if(x.getVersionPotencial() != null){
			    paramAsigned++;
			    sbq.append(" and x.versionPotencial = :versionPotencial");
			}
			if(x.getVigenciaPotencial() != null){
			    paramAsigned++;
			    sbq.append(" and x.vigenciaPotencial = :vigenciaPotencial");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<Potencial> nq = em.createQuery(sbq.toString(), Potencial.class);
		
		if(paramAsigned>0){
			if(x.getIdPotencial() != null){
			    nq.setParameter("idPotencial",x.getIdPotencial());
			}
			if(x.getProveedorEstacion() != null){
			    nq.setParameter("proveedorEstacion",x.getProveedorEstacion());
			}
			if(x.getProveedor() != null){
			    nq.setParameter("proveedor",x.getProveedor());
			}
			if(x.getCodigoServicio() != null){
			    nq.setParameter("codigoServicio",x.getCodigoServicio());
			}
			if(x.getClase() != null){
			    nq.setParameter("clase",x.getClase());
			}
			if(x.getIdNociclotemporada() != null){
			    nq.setParameter("idNociclotemporada",x.getIdNociclotemporada());
			}
			if(x.getIdTipociclo() != null){
			    nq.setParameter("idTipociclo",x.getIdTipociclo());
			}
			if(x.getIdEstatuspotencial() != null){
			    nq.setParameter("idEstatuspotencial",x.getIdEstatuspotencial());
			}
			if(x.getUrlPotencial() != null){
			    nq.setParameter("urlPotencial",x.getUrlPotencial());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
			}
			if(x.getUsuarioCreo() != null){
			    nq.setParameter("usuarioCreo",x.getUsuarioCreo());
			}
			if(x.getFechaCreo() != null){
			    nq.setParameter("fechaCreo",x.getFechaCreo());
			}
			if(x.getObservaciones() != null){
			    nq.setParameter("observaciones",x.getObservaciones());
			}
			if(x.getUsuarioObservaciones() != null){
			    nq.setParameter("usuarioObservaciones",x.getUsuarioObservaciones());
			}
			if(x.getFechaObservaciones() != null){
			    nq.setParameter("fechaObservaciones",x.getFechaObservaciones());
			}
			if(x.getNotaRecordatorio() != null){
			    nq.setParameter("notaRecordatorio",x.getNotaRecordatorio());
			}
			if(x.getFechaRecordatorio() != null){
			    nq.setParameter("fechaRecordatorio",x.getFechaRecordatorio());
			}
			if(x.getVersionPotencial() != null){
			    nq.setParameter("versionPotencial",x.getVersionPotencial());
			}
			if(x.getVigenciaPotencial() != null){
			    nq.setParameter("vigenciaPotencial",x.getVigenciaPotencial());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public Potencial findByPK_EAGER(Object pk){
        Potencial x = getEntityManager().find(Potencial.class, pk);
        if( x != null){
            if(x.getProveedorEstacion() !=null && x.getProveedorEstacion().getIdProveedorEstacion()!=null){} 
            if(x.getProveedor() !=null && x.getProveedor().getClaveProveedor()!=null){} 
            if(x.getCodigoServicio() !=null && x.getCodigoServicio().getIdCodigoServicio()!=null){} 
            if(x.getClase() !=null && x.getClase().getIdClase()!=null){} 
            if(x.getIdNociclotemporada() !=null && x.getIdNociclotemporada().getIdParametro()!=null){} 
            if(x.getIdTipociclo() !=null && x.getIdTipociclo().getIdParametro()!=null){} 
            if(x.getIdEstatuspotencial() !=null && x.getIdEstatuspotencial().getIdParametro()!=null){} 

        }
        return x;
    }

	@Override
	public List<Potencial> findAll() {
		TypedQuery<Potencial> nq = em.createNamedQuery("Potencial.findAll", Potencial.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Potencial.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
