package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.ConfiguracionCiclo;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ConfiguracionCicloFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Configuracion_Ciclo by ConfiguracionCicloFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "configuracionCiclo_RSB",
    mappedName   = "ConfiguracionCiclo_RSB",
    description  = "ConfiguracionCicloFacadeRemote-Stateless-Session EJB-3.1"
)
public class ConfiguracionCicloFacade extends AbstractFacade<ConfiguracionCiclo> implements ConfiguracionCicloFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ConfiguracionCicloFacade() {
		super(ConfiguracionCiclo.class);
	}

	@Override
	public ConfiguracionCiclo create(ConfiguracionCiclo entity) {
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
	public ConfiguracionCiclo update(ConfiguracionCiclo entity) {
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
	public List<ConfiguracionCiclo> findAllLike(ConfiguracionCiclo x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM ConfiguracionCiclo x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdConfiguracionCiclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idConfiguracionCiclo = :idConfiguracionCiclo");
			}
			if(x.getCiclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.ciclo = :ciclo");
			}
			if(x.getIdMes() != null){
			    paramAsigned++;
			    sbq.append(" and x.idMes = :idMes");
			}
			if(x.getIdPeriodicidad() != null){
			    paramAsigned++;
			    sbq.append(" and x.idPeriodicidad = :idPeriodicidad");
			}
			if(x.getIdNociclo() != null){
			    paramAsigned++;
			    sbq.append(" and x.idNociclo = :idNociclo");
			}
			if(x.getIdTemporada() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTemporada = :idTemporada");
			}
			if(x.getIdTipoQuincena() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoQuincena = :idTipoQuincena");
			}
			if(x.getIdTipoTemporada() != null){
			    paramAsigned++;
			    sbq.append(" and x.idTipoTemporada = :idTipoTemporada");
			}
			
		} else {
			sbq.append(" 1=2 ");
		}
		
		TypedQuery<ConfiguracionCiclo> nq = em.createQuery(sbq.toString(), ConfiguracionCiclo.class);
		
		if(paramAsigned>0){
			if(x.getIdConfiguracionCiclo() != null){
			    nq.setParameter("idConfiguracionCiclo",x.getIdConfiguracionCiclo());
			}
			if(x.getCiclo() != null){
			    nq.setParameter("ciclo",x.getCiclo());
			}
			if(x.getIdMes() != null){
			    nq.setParameter("idMes",x.getIdMes());
			}
			if(x.getIdPeriodicidad() != null){
			    nq.setParameter("idPeriodicidad",x.getIdPeriodicidad());
			}
			if(x.getIdNociclo() != null){
			    nq.setParameter("idNociclo",x.getIdNociclo());
			}
			if(x.getIdTemporada() != null){
			    nq.setParameter("idTemporada",x.getIdTemporada());
			}
			if(x.getIdTipoQuincena() != null){
			    nq.setParameter("idTipoQuincena",x.getIdTipoQuincena());
			}
			if(x.getIdTipoTemporada() != null){
			    nq.setParameter("idTipoTemporada",x.getIdTipoTemporada());
			}
			
		}		
		return nq.getResultList();
		
	}
    
    @Override
	public ConfiguracionCiclo findByPK_EAGER(Object pk){
        ConfiguracionCiclo x = getEntityManager().find(ConfiguracionCiclo.class, pk);
        if( x != null){
            if(x.getCiclo() !=null && x.getCiclo().getIdCiclo()!=null){} 
            if(x.getIdMes() !=null && x.getIdMes().getIdParametro()!=null){} 
            if(x.getIdPeriodicidad() !=null && x.getIdPeriodicidad().getIdParametro()!=null){} 
            if(x.getIdNociclo() !=null && x.getIdNociclo().getIdParametro()!=null){} 
            if(x.getIdTemporada() !=null && x.getIdTemporada().getIdParametro()!=null){} 
            if(x.getIdTipoQuincena() !=null && x.getIdTipoQuincena().getIdParametro()!=null){} 
            if(x.getIdTipoTemporada() !=null && x.getIdTipoTemporada().getIdParametro()!=null){} 

        }
        return x;
    }

	@Override
	public List<ConfiguracionCiclo> findAll() {
		TypedQuery<ConfiguracionCiclo> nq = em.createNamedQuery("ConfiguracionCiclo.findAll", ConfiguracionCiclo.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("ConfiguracionCiclo.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
