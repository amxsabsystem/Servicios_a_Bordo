package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table Material by MaterialFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "material_RSB",
    mappedName   = "Material_RSB",
    description  = "MaterialFacadeRemote-Stateless-Session EJB-3.1"
)
public class MaterialFacade extends AbstractFacade<Material> implements MaterialFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MaterialFacade() {
		super(Material.class);
	}

	@Override
	public Material create(Material entity) {
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
        final List<EquipamientoSemifijo> entity_equipamientoSemifijoHasMaterialList =  entity.getEquipamientoSemifijoHasMaterialList();
        entity.setEquipamientoSemifijoHasMaterialList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialList =  entity.getMaterialExtraHasMaterialList();
        entity.setMaterialExtraHasMaterialList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialMontajeList =  entity.getMaterialExtraHasMaterialMontajeList();
        entity.setMaterialExtraHasMaterialMontajeList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialAmxList =  entity.getMaterialExtraHasMaterialAmxList();
        entity.setMaterialExtraHasMaterialAmxList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialMontajeAmxList =  entity.getMaterialExtraHasMaterialMontajeAmxList();
        entity.setMaterialExtraHasMaterialMontajeAmxList(null);
        final List<AvionAudifono> entity_avionAudifonoHasMaterialList =  entity.getAvionAudifonoHasMaterialList();
        entity.setAvionAudifonoHasMaterialList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialList =  entity.getDetalleTablaTsuHasMaterialList();
        entity.setDetalleTablaTsuHasMaterialList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialMontajeList =  entity.getDetalleTablaTsuHasMaterialMontajeList();
        entity.setDetalleTablaTsuHasMaterialMontajeList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialAmxList =  entity.getDetalleTablaTsuHasMaterialAmxList();
        entity.setDetalleTablaTsuHasMaterialAmxList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialMontajeAmxList =  entity.getDetalleTablaTsuHasMaterialMontajeAmxList();
        entity.setDetalleTablaTsuHasMaterialMontajeAmxList(null);
        final List<MaterialTsu> entity_materialTsuHasMaterialList =  entity.getMaterialTsuHasMaterialList();
        entity.setMaterialTsuHasMaterialList(null);
        final List<MatrizDetalle> entity_matrizDetalleHasMaterialList =  entity.getMatrizDetalleHasMaterialList();
        entity.setMatrizDetalleHasMaterialList(null);
        final List<Sobreabordaje> entity_sobreabordajeHasMaterialList =  entity.getSobreabordajeHasMaterialList();
        entity.setSobreabordajeHasMaterialList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setEquipamientoSemifijoHasMaterialList(entity_equipamientoSemifijoHasMaterialList);
        entity.setMaterialExtraHasMaterialList(entity_materialExtraHasMaterialList);
        entity.setMaterialExtraHasMaterialMontajeList(entity_materialExtraHasMaterialMontajeList);
        entity.setMaterialExtraHasMaterialAmxList(entity_materialExtraHasMaterialAmxList);
        entity.setMaterialExtraHasMaterialMontajeAmxList(entity_materialExtraHasMaterialMontajeAmxList);
        entity.setAvionAudifonoHasMaterialList(entity_avionAudifonoHasMaterialList);
        entity.setDetalleTablaTsuHasMaterialList(entity_detalleTablaTsuHasMaterialList);
        entity.setDetalleTablaTsuHasMaterialMontajeList(entity_detalleTablaTsuHasMaterialMontajeList);
        entity.setDetalleTablaTsuHasMaterialAmxList(entity_detalleTablaTsuHasMaterialAmxList);
        entity.setDetalleTablaTsuHasMaterialMontajeAmxList(entity_detalleTablaTsuHasMaterialMontajeAmxList);
        entity.setMaterialTsuHasMaterialList(entity_materialTsuHasMaterialList);
        entity.setMatrizDetalleHasMaterialList(entity_matrizDetalleHasMaterialList);
        entity.setSobreabordajeHasMaterialList(entity_sobreabordajeHasMaterialList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Material update(Material entity) {
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
        final List<EquipamientoSemifijo> entity_equipamientoSemifijoHasMaterialList =  entity.getEquipamientoSemifijoHasMaterialList();
        entity.setEquipamientoSemifijoHasMaterialList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialList =  entity.getMaterialExtraHasMaterialList();
        entity.setMaterialExtraHasMaterialList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialMontajeList =  entity.getMaterialExtraHasMaterialMontajeList();
        entity.setMaterialExtraHasMaterialMontajeList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialAmxList =  entity.getMaterialExtraHasMaterialAmxList();
        entity.setMaterialExtraHasMaterialAmxList(null);
        final List<MaterialExtra> entity_materialExtraHasMaterialMontajeAmxList =  entity.getMaterialExtraHasMaterialMontajeAmxList();
        entity.setMaterialExtraHasMaterialMontajeAmxList(null);
        final List<AvionAudifono> entity_avionAudifonoHasMaterialList =  entity.getAvionAudifonoHasMaterialList();
        entity.setAvionAudifonoHasMaterialList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialList =  entity.getDetalleTablaTsuHasMaterialList();
        entity.setDetalleTablaTsuHasMaterialList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialMontajeList =  entity.getDetalleTablaTsuHasMaterialMontajeList();
        entity.setDetalleTablaTsuHasMaterialMontajeList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialAmxList =  entity.getDetalleTablaTsuHasMaterialAmxList();
        entity.setDetalleTablaTsuHasMaterialAmxList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuHasMaterialMontajeAmxList =  entity.getDetalleTablaTsuHasMaterialMontajeAmxList();
        entity.setDetalleTablaTsuHasMaterialMontajeAmxList(null);
        final List<MaterialTsu> entity_materialTsuHasMaterialList =  entity.getMaterialTsuHasMaterialList();
        entity.setMaterialTsuHasMaterialList(null);
        final List<MatrizDetalle> entity_matrizDetalleHasMaterialList =  entity.getMatrizDetalleHasMaterialList();
        entity.setMatrizDetalleHasMaterialList(null);
        final List<Sobreabordaje> entity_sobreabordajeHasMaterialList =  entity.getSobreabordajeHasMaterialList();
        entity.setSobreabordajeHasMaterialList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setEquipamientoSemifijoHasMaterialList(entity_equipamientoSemifijoHasMaterialList);
        entity.setMaterialExtraHasMaterialList(entity_materialExtraHasMaterialList);
        entity.setMaterialExtraHasMaterialMontajeList(entity_materialExtraHasMaterialMontajeList);
        entity.setMaterialExtraHasMaterialAmxList(entity_materialExtraHasMaterialAmxList);
        entity.setMaterialExtraHasMaterialMontajeAmxList(entity_materialExtraHasMaterialMontajeAmxList);
        entity.setAvionAudifonoHasMaterialList(entity_avionAudifonoHasMaterialList);
        entity.setDetalleTablaTsuHasMaterialList(entity_detalleTablaTsuHasMaterialList);
        entity.setDetalleTablaTsuHasMaterialMontajeList(entity_detalleTablaTsuHasMaterialMontajeList);
        entity.setDetalleTablaTsuHasMaterialAmxList(entity_detalleTablaTsuHasMaterialAmxList);
        entity.setDetalleTablaTsuHasMaterialMontajeAmxList(entity_detalleTablaTsuHasMaterialMontajeAmxList);
        entity.setMaterialTsuHasMaterialList(entity_materialTsuHasMaterialList);
        entity.setMatrizDetalleHasMaterialList(entity_matrizDetalleHasMaterialList);
        entity.setSobreabordajeHasMaterialList(entity_sobreabordajeHasMaterialList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Material> findAllLike(Material x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Material x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getNumeroParte() != null){
			    paramAsigned++;
			    sbq.append(" and x.numeroParte = :numeroParte");
			}
			if(x.getTipoAbastecimiento() != null){
			    paramAsigned++;
			    sbq.append(" and x.tipoAbastecimiento = :tipoAbastecimiento");
			}
			if(x.getCategoriaMaterial() != null){
			    paramAsigned++;
			    sbq.append(" and x.categoriaMaterial = :categoriaMaterial");
			}
			if(x.getDescripcionEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcionEs = :descripcionEs");
			}
			if(x.getDescripcionEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.descripcionEn = :descripcionEn");
			}
			if(x.getUrlMultimedia() != null){
			    paramAsigned++;
			    sbq.append(" and x.urlMultimedia = :urlMultimedia");
			}
			if(x.getMimeType() != null){
			    paramAsigned++;
			    sbq.append(" and x.mimeType = :mimeType");
			}
			if(x.getPeso()  != 0.0){
			    paramAsigned++;
			    sbq.append(" and x.peso = :peso");
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
		
		TypedQuery<Material> nq = em.createQuery(sbq.toString(), Material.class);
		
		if(paramAsigned>0){
			if(x.getNumeroParte() != null){
			    nq.setParameter("numeroParte",x.getNumeroParte());
			}
			if(x.getTipoAbastecimiento() != null){
			    nq.setParameter("tipoAbastecimiento",x.getTipoAbastecimiento());
			}
			if(x.getCategoriaMaterial() != null){
			    nq.setParameter("categoriaMaterial",x.getCategoriaMaterial());
			}
			if(x.getDescripcionEs() != null){
			    nq.setParameter("descripcionEs",x.getDescripcionEs());
			}
			if(x.getDescripcionEn() != null){
			    nq.setParameter("descripcionEn",x.getDescripcionEn());
			}
			if(x.getUrlMultimedia() != null){
			    nq.setParameter("urlMultimedia",x.getUrlMultimedia());
			}
			if(x.getMimeType() != null){
			    nq.setParameter("mimeType",x.getMimeType());
			}
			if(x.getPeso()  != 0.0){
			    nq.setParameter("peso",x.getPeso());
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
	public Material findByPK_EAGER(Object pk){
        Material x = getEntityManager().find(Material.class, pk);
        if( x != null){
            if(x.getTipoAbastecimiento() !=null && x.getTipoAbastecimiento().getIdParametro()!=null){} 
            if(x.getCategoriaMaterial() !=null && x.getCategoriaMaterial().getIdCategoriaMaterial()!=null){} 
            for(EquipamientoSemifijo x_equipamientoSemifijo: x.getEquipamientoSemifijoHasMaterialList() ) {} 
            for(MaterialExtra x_materialExtra: x.getMaterialExtraHasMaterialList() ) {} 
            for(MaterialExtra x_materialExtra: x.getMaterialExtraHasMaterialMontajeList() ) {} 
            for(MaterialExtra x_materialExtra: x.getMaterialExtraHasMaterialAmxList() ) {} 
            for(MaterialExtra x_materialExtra: x.getMaterialExtraHasMaterialMontajeAmxList() ) {} 
            for(AvionAudifono x_avionAudifono: x.getAvionAudifonoHasMaterialList() ) {} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasMaterialList() ) {} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasMaterialMontajeList() ) {} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasMaterialAmxList() ) {} 
            for(DetalleTablaTsu x_detalleTablaTsu: x.getDetalleTablaTsuHasMaterialMontajeAmxList() ) {} 
            for(MaterialTsu x_materialTsu: x.getMaterialTsuHasMaterialList() ) {} 
            for(MatrizDetalle x_matrizDetalle: x.getMatrizDetalleHasMaterialList() ) {} 
            for(Sobreabordaje x_sobreabordaje: x.getSobreabordajeHasMaterialList() ) {} 

        }
        return x;
    }

	@Override
	public List<Material> findAll() {
		TypedQuery<Material> nq = em.createNamedQuery("Material.findAll", Material.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Material.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
