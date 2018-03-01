package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.BitacoraComunicado;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.BitacoraComunicadoFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table bitacora_comunicado by BitacoraComunicadoFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "bitacoraComunicado_RSB",
    mappedName   = "BitacoraComunicado_RSB",
    description  = "BitacoraComunicadoFacadeRemote-Stateless-Session EJB-3.1"
)
public class BitacoraComunicadoFacade extends AbstractFacade<BitacoraComunicado> implements BitacoraComunicadoFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BitacoraComunicadoFacade() {
		super(BitacoraComunicado.class);
	}

	@Override
	public BitacoraComunicado create(BitacoraComunicado entity) {
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
	public BitacoraComunicado update(BitacoraComunicado entity) {
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
	public List<BitacoraComunicado> findAllLike(BitacoraComunicado x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM BitacoraComunicado x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdBitacora() != null){
			    paramAsigned++;
			    sbq.append(" and x.idBitacora = :idBitacora");
			}
			if(x.getContactoProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.contactoProveedorEstacion = :contactoProveedorEstacion");
			}
			if(x.getComunicado() != null){
			    paramAsigned++;
			    sbq.append(" and x.comunicado = :comunicado");
			}
			if(x.getProveedorEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedorEstacion = :proveedorEstacion");
			}
			if(x.getFecha() != null){
			    paramAsigned++;
			    sbq.append(" and x.fecha = :fecha");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<BitacoraComunicado> nq = em.createQuery(sbq.toString(), BitacoraComunicado.class);
		
		if(paramAsigned>0){
			if(x.getIdBitacora() != null){
			    nq.setParameter("idBitacora",x.getIdBitacora());
			}
			if(x.getContactoProveedorEstacion() != null){
			    nq.setParameter("contactoProveedorEstacion",x.getContactoProveedorEstacion());
			}
			if(x.getComunicado() != null){
			    nq.setParameter("comunicado",x.getComunicado());
			}
			if(x.getProveedorEstacion() != null){
			    nq.setParameter("proveedorEstacion",x.getProveedorEstacion());
			}
			if(x.getFecha() != null){
			    nq.setParameter("fecha",x.getFecha());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public BitacoraComunicado findByPK_EAGER(Object pk){
        BitacoraComunicado x = getEntityManager().find(BitacoraComunicado.class, pk);
        if( x != null){
            if(x.getContactoProveedorEstacion() !=null && x.getContactoProveedorEstacion().getIdContactoProveedorEstacion()!=null){} 
            if(x.getComunicado() !=null && x.getComunicado().getIdComunicado()!=null){} 
            if(x.getProveedorEstacion() !=null && x.getProveedorEstacion().getIdProveedorEstacion()!=null){} 

        }
        return x;
    }

	@Override
	public List<BitacoraComunicado> findAll() {
		TypedQuery<BitacoraComunicado> nq = em.createNamedQuery("BitacoraComunicado.findAll", BitacoraComunicado.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("BitacoraComunicado.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
