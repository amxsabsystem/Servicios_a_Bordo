package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Ciclo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.CicloFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table ciclo by CicloFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "ciclo_RSB",
    mappedName   = "Ciclo_RSB",
    description  = "CicloFacadeRemote-Stateless-Session EJB-3.1"
)
public class CicloFacade extends AbstractFacade<Ciclo> implements CicloFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CicloFacade() {
		super(Ciclo.class);
	}

	@Override
	public Ciclo create(Ciclo entity) {
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
        final List<ConfiguracionCiclo> entity_configuracionCicloHasCicloList =  entity.getConfiguracionCicloHasCicloList();
        entity.setConfiguracionCicloHasCicloList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setConfiguracionCicloHasCicloList(entity_configuracionCicloHasCicloList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Ciclo update(Ciclo entity) {
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
        final List<ConfiguracionCiclo> entity_configuracionCicloHasCicloList =  entity.getConfiguracionCicloHasCicloList();
        entity.setConfiguracionCicloHasCicloList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setConfiguracionCicloHasCicloList(entity_configuracionCicloHasCicloList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Ciclo> findAllLike(Ciclo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Ciclo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdCiclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idCiclo = :idCiclo");
			}
			if(x.getIdOrigenvuelo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idOrigenvuelo = :idOrigenvuelo");
			}
			if(x.getIdTipociclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipociclo = :idTipociclo");
			}
			if(x.getRegion() != null){
			    paramAsigned++;
			    sbq.append(" and x.region = :region");
			}
			if(x.getEstacion() != null){
			    paramAsigned++;
			    sbq.append(" and x.estacion = :estacion");
			}
			if(x.getProveedor() != null){
			    paramAsigned++;
			    sbq.append(" and x.proveedor = :proveedor");
			}
			if(x.getDobleAbastecido() != null){
			    paramAsigned++;
			    sbq.append(" and x.dobleAbastecido = :dobleAbastecido");
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
		
		TypedQuery<Ciclo> nq = em.createQuery(sbq.toString(), Ciclo.class);
		
		if(paramAsigned>0){
			if(x.getIdCiclo() != null){
			    nq.setParameter("idCiclo",x.getIdCiclo());
			}
			if(x.getIdOrigenvuelo() != null){
			    nq.setParameter("idOrigenvuelo",x.getIdOrigenvuelo());
			}
			if(x.getIdTipociclo() != null){
			    nq.setParameter("idTipociclo",x.getIdTipociclo());
			}
			if(x.getRegion() != null){
			    nq.setParameter("region",x.getRegion());
			}
			if(x.getEstacion() != null){
			    nq.setParameter("estacion",x.getEstacion());
			}
			if(x.getProveedor() != null){
			    nq.setParameter("proveedor",x.getProveedor());
			}
			if(x.getDobleAbastecido() != null){
			    nq.setParameter("dobleAbastecido",x.getDobleAbastecido());
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
	public Ciclo findByPK_EAGER(Object pk){
        Ciclo x = getEntityManager().find(Ciclo.class, pk);
        if( x != null){
            if(x.getIdOrigenvuelo() !=null && x.getIdOrigenvuelo().getIdParametro()!=null){} 
            if(x.getIdTipociclo() !=null && x.getIdTipociclo().getIdParametro()!=null){} 
            if(x.getRegion() !=null && x.getRegion().getIdRegion()!=null){} 
            if(x.getEstacion() !=null && x.getEstacion().getIdEstacion()!=null){} 
            if(x.getProveedor() !=null && x.getProveedor().getClaveProveedor()!=null){} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasCicloList() ) {} 

        }
        return x;
    }

	@Override
	public List<Ciclo> findAll() {
		TypedQuery<Ciclo> nq = em.createNamedQuery("Ciclo.findAll", Ciclo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Ciclo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
