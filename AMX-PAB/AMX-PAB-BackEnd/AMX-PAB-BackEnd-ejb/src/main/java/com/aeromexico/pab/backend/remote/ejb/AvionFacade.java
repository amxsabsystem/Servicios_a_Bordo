package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Avion;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.AvionFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table avion by AvionFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "avion_RSB",
    mappedName   = "Avion_RSB",
    description  = "AvionFacadeRemote-Stateless-Session EJB-3.1"
)
public class AvionFacade extends AbstractFacade<Avion> implements AvionFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public AvionFacade() {
		super(Avion.class);
	}

	@Override
	public Avion create(Avion entity) {
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
        final List<EquipamientoSemifijo> entity_equipamientoSemifijoHasAvionList =  entity.getEquipamientoSemifijoHasAvionList();
        entity.setEquipamientoSemifijoHasAvionList(null);
        final List<AvionAudifono> entity_avionAudifonoHasAvionList =  entity.getAvionAudifonoHasAvionList();
        entity.setAvionAudifonoHasAvionList(null);
        final List<EquipamientoFijo> entity_equipamientoFijoHasAvionList =  entity.getEquipamientoFijoHasAvionList();
        entity.setEquipamientoFijoHasAvionList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setEquipamientoSemifijoHasAvionList(entity_equipamientoSemifijoHasAvionList);
        entity.setAvionAudifonoHasAvionList(entity_avionAudifonoHasAvionList);
        entity.setEquipamientoFijoHasAvionList(entity_equipamientoFijoHasAvionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Avion update(Avion entity) {
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
        final List<EquipamientoSemifijo> entity_equipamientoSemifijoHasAvionList =  entity.getEquipamientoSemifijoHasAvionList();
        entity.setEquipamientoSemifijoHasAvionList(null);
        final List<AvionAudifono> entity_avionAudifonoHasAvionList =  entity.getAvionAudifonoHasAvionList();
        entity.setAvionAudifonoHasAvionList(null);
        final List<EquipamientoFijo> entity_equipamientoFijoHasAvionList =  entity.getEquipamientoFijoHasAvionList();
        entity.setEquipamientoFijoHasAvionList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setEquipamientoSemifijoHasAvionList(entity_equipamientoSemifijoHasAvionList);
        entity.setAvionAudifonoHasAvionList(entity_avionAudifonoHasAvionList);
        entity.setEquipamientoFijoHasAvionList(entity_equipamientoFijoHasAvionList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Avion> findAllLike(Avion x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Avion x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.idAvion = :idAvion");
			}
			if(x.getTipoEquipoAvion() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoEquipoAvion = :tipoEquipoAvion");
			}
			if(x.getIdConectividad() != null){
			    paramAsigned++;
			    sbq.append(" and x.idConectividad = :idConectividad");
			}
			if(x.getMatricula() != null){
			    paramAsigned++;
			    sbq.append(" and x.matricula = :matricula");
			}
			if(x.getMatriculaCorta() != null){
			    paramAsigned++;
			    sbq.append(" and x.matriculaCorta = :matriculaCorta");
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
		
		TypedQuery<Avion> nq = em.createQuery(sbq.toString(), Avion.class);
		
		if(paramAsigned>0){
			if(x.getIdAvion() != null){
			    nq.setParameter("idAvion",x.getIdAvion());
			}
			if(x.getTipoEquipoAvion() != null){
			    nq.setParameter("tipoEquipoAvion",x.getTipoEquipoAvion());
			}
			if(x.getIdConectividad() != null){
			    nq.setParameter("idConectividad",x.getIdConectividad());
			}
			if(x.getMatricula() != null){
			    nq.setParameter("matricula",x.getMatricula());
			}
			if(x.getMatriculaCorta() != null){
			    nq.setParameter("matriculaCorta",x.getMatriculaCorta());
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
	public Avion findByPK_EAGER(Object pk){
        Avion x = getEntityManager().find(Avion.class, pk);
        if( x != null){
            if(x.getTipoEquipoAvion() !=null && x.getTipoEquipoAvion().getIdTipoEquipoAvion()!=null){} 
            if(x.getIdConectividad() !=null && x.getIdConectividad().getIdParametro()!=null){} 
            for(EquipamientoSemifijo x_equipamientoSemifijo: x.getEquipamientoSemifijoHasAvionList() ) {} 
            for(AvionAudifono x_avionAudifono: x.getAvionAudifonoHasAvionList() ) {} 
            for(EquipamientoFijo x_equipamientoFijo: x.getEquipamientoFijoHasAvionList() ) {} 

        }
        return x;
    }

	@Override
	public List<Avion> findAll() {
		TypedQuery<Avion> nq = em.createNamedQuery("Avion.findAll", Avion.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Avion.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
