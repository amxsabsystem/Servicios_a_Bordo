package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.*;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import java.sql.Timestamp;
import java.util.List;
import java.security.Principal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * SSB JPA Entity Facade of Table parametro by ParametroFacadeRemote.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */
@Stateless(
    name         = "parametro_RSB",
    mappedName   = "Parametro_RSB",
    description  = "ParametroFacadeRemote-Stateless-Session EJB-3.1"
)
public class ParametroFacade extends AbstractFacade<Parametro> implements ParametroFacadeRemote {

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ParametroFacade() {
		super(Parametro.class);
	}

	@Override
	public Parametro create(Parametro entity) {
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
        final List<Material> entity_materialHasTipoAbastecimientoList =  entity.getMaterialHasTipoAbastecimientoList();
        entity.setMaterialHasTipoAbastecimientoList(null);
        final List<KitMaster> entity_kitMasterHasIdUnidadMedidaList =  entity.getKitMasterHasIdUnidadMedidaList();
        entity.setKitMasterHasIdUnidadMedidaList(null);
        final List<KitMaster> entity_kitMasterHasIdInstruccionesNacionalesList =  entity.getKitMasterHasIdInstruccionesNacionalesList();
        entity.setKitMasterHasIdInstruccionesNacionalesList(null);
        final List<KitMaster> entity_kitMasterHasIdTipoKitList =  entity.getKitMasterHasIdTipoKitList();
        entity.setKitMasterHasIdTipoKitList(null);
        final List<KitMaster> entity_kitMasterHasIdInstruccionesInternacList =  entity.getKitMasterHasIdInstruccionesInternacList();
        entity.setKitMasterHasIdInstruccionesInternacList(null);
        final List<AsignacionServicio> entity_asignacionServicioHasIdOrigenList =  entity.getAsignacionServicioHasIdOrigenList();
        entity.setAsignacionServicioHasIdOrigenList(null);
        final List<ConfiguracionReporte> entity_configuracionReporteHasStatusList =  entity.getConfiguracionReporteHasStatusList();
        entity.setConfiguracionReporteHasStatusList(null);
        final List<CodigoServicio> entity_codigoServicioHasIdTipoCicloList =  entity.getCodigoServicioHasIdTipoCicloList();
        entity.setCodigoServicioHasIdTipoCicloList(null);
        final List<CodigoServicio> entity_codigoServicioHasIdTipoCodigoServicioList =  entity.getCodigoServicioHasIdTipoCodigoServicioList();
        entity.setCodigoServicioHasIdTipoCodigoServicioList(null);
        final List<TipoIrregularidad> entity_tipoIrregularidadHasEstatusList =  entity.getTipoIrregularidadHasEstatusList();
        entity.setTipoIrregularidadHasEstatusList(null);
        final List<Reporte> entity_reporteHasIdEstatusReporteActualList =  entity.getReporteHasIdEstatusReporteActualList();
        entity.setReporteHasIdEstatusReporteActualList(null);
        final List<Reporte> entity_reporteHasIdTipoReporteList =  entity.getReporteHasIdTipoReporteList();
        entity.setReporteHasIdTipoReporteList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdNocicloList =  entity.getConfiguracionCicloHasIdNocicloList();
        entity.setConfiguracionCicloHasIdNocicloList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdTemporadaList =  entity.getConfiguracionCicloHasIdTemporadaList();
        entity.setConfiguracionCicloHasIdTemporadaList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdMesList =  entity.getConfiguracionCicloHasIdMesList();
        entity.setConfiguracionCicloHasIdMesList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdTipoTemporadaList =  entity.getConfiguracionCicloHasIdTipoTemporadaList();
        entity.setConfiguracionCicloHasIdTipoTemporadaList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdTipoQuincenaList =  entity.getConfiguracionCicloHasIdTipoQuincenaList();
        entity.setConfiguracionCicloHasIdTipoQuincenaList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdPeriodicidadList =  entity.getConfiguracionCicloHasIdPeriodicidadList();
        entity.setConfiguracionCicloHasIdPeriodicidadList(null);
        final List<TipoProductoReporte> entity_tipoProductoReporteHasEstatusList =  entity.getTipoProductoReporteHasEstatusList();
        entity.setTipoProductoReporteHasEstatusList(null);
        final List<ModeloAvion> entity_modeloAvionHasIdTipoCabinaList =  entity.getModeloAvionHasIdTipoCabinaList();
        entity.setModeloAvionHasIdTipoCabinaList(null);
        final List<ModeloAvion> entity_modeloAvionHasIdFabricanteList =  entity.getModeloAvionHasIdFabricanteList();
        entity.setModeloAvionHasIdFabricanteList(null);
        final List<Tsu> entity_tsuHasIdIdiomaList =  entity.getTsuHasIdIdiomaList();
        entity.setTsuHasIdIdiomaList(null);
        final List<Tsu> entity_tsuHasIdRevisionList =  entity.getTsuHasIdRevisionList();
        entity.setTsuHasIdRevisionList(null);
        final List<Vuelo> entity_vueloHasIdTipoCabinaList =  entity.getVueloHasIdTipoCabinaList();
        entity.setVueloHasIdTipoCabinaList(null);
        final List<Potencial> entity_potencialHasIdTipocicloList =  entity.getPotencialHasIdTipocicloList();
        entity.setPotencialHasIdTipocicloList(null);
        final List<Potencial> entity_potencialHasIdNociclotemporadaList =  entity.getPotencialHasIdNociclotemporadaList();
        entity.setPotencialHasIdNociclotemporadaList(null);
        final List<Potencial> entity_potencialHasIdEstatuspotencialList =  entity.getPotencialHasIdEstatuspotencialList();
        entity.setPotencialHasIdEstatuspotencialList(null);
        final List<Comunicado> entity_comunicadoHasEstatusComunicadoList =  entity.getComunicadoHasEstatusComunicadoList();
        entity.setComunicadoHasEstatusComunicadoList(null);
        final List<Comunicado> entity_comunicadoHasIdIdiomaList =  entity.getComunicadoHasIdIdiomaList();
        entity.setComunicadoHasIdIdiomaList(null);
        final List<Comunicado> entity_comunicadoHasAccionComunicadoList =  entity.getComunicadoHasAccionComunicadoList();
        entity.setComunicadoHasAccionComunicadoList(null);
        final List<Acomodo> entity_acomodoHasIdIdiomaList =  entity.getAcomodoHasIdIdiomaList();
        entity.setAcomodoHasIdIdiomaList(null);
        final List<Proceso> entity_procesoHasIdIdiomaList =  entity.getProcesoHasIdIdiomaList();
        entity.setProcesoHasIdIdiomaList(null);
        final List<Proceso> entity_procesoHasTipoProcesoList =  entity.getProcesoHasTipoProcesoList();
        entity.setProcesoHasTipoProcesoList(null);
        final List<CriterioIrregularidad> entity_criterioIrregularidadHasEstatusList =  entity.getCriterioIrregularidadHasEstatusList();
        entity.setCriterioIrregularidadHasEstatusList(null);
        final List<Grafico> entity_graficoHasIdIdiomaList =  entity.getGraficoHasIdIdiomaList();
        entity.setGraficoHasIdIdiomaList(null);
        final List<Ciclo> entity_cicloHasIdTipocicloList =  entity.getCicloHasIdTipocicloList();
        entity.setCicloHasIdTipocicloList(null);
        final List<Ciclo> entity_cicloHasIdOrigenvueloList =  entity.getCicloHasIdOrigenvueloList();
        entity.setCicloHasIdOrigenvueloList(null);
        final List<Avion> entity_avionHasIdConectividadList =  entity.getAvionHasIdConectividadList();
        entity.setAvionHasIdConectividadList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoHasIdOrigenVueloList =  entity.getTablaAbordamientoHasIdOrigenVueloList();
        entity.setTablaAbordamientoHasIdOrigenVueloList(null);
        final List<MaterialKitMaster> entity_materialKitMasterHasIdUnidadMedidaList =  entity.getMaterialKitMasterHasIdUnidadMedidaList();
        entity.setMaterialKitMasterHasIdUnidadMedidaList(null);
        final List<MaterialKitMaster> entity_materialKitMasterHasIdInstruccionesNacionalesList =  entity.getMaterialKitMasterHasIdInstruccionesNacionalesList();
        entity.setMaterialKitMasterHasIdInstruccionesNacionalesList(null);
        final List<MaterialKitMaster> entity_materialKitMasterHasIdInstruccionesInternacList =  entity.getMaterialKitMasterHasIdInstruccionesInternacList();
        entity.setMaterialKitMasterHasIdInstruccionesInternacList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuList =  entity.getDetalleTablaTsuList();
        entity.setDetalleTablaTsuList(null);
        final List<DetalleTablaComplementario> entity_detalleTablaComplementarioList =  entity.getDetalleTablaComplementarioList();
        entity.setDetalleTablaComplementarioList(null);

		getEntityManager().persist(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist 
        entity.setMaterialHasTipoAbastecimientoList(entity_materialHasTipoAbastecimientoList);
        entity.setKitMasterHasIdUnidadMedidaList(entity_kitMasterHasIdUnidadMedidaList);
        entity.setKitMasterHasIdInstruccionesNacionalesList(entity_kitMasterHasIdInstruccionesNacionalesList);
        entity.setKitMasterHasIdTipoKitList(entity_kitMasterHasIdTipoKitList);
        entity.setKitMasterHasIdInstruccionesInternacList(entity_kitMasterHasIdInstruccionesInternacList);
        entity.setAsignacionServicioHasIdOrigenList(entity_asignacionServicioHasIdOrigenList);
        entity.setConfiguracionReporteHasStatusList(entity_configuracionReporteHasStatusList);
        entity.setCodigoServicioHasIdTipoCicloList(entity_codigoServicioHasIdTipoCicloList);
        entity.setCodigoServicioHasIdTipoCodigoServicioList(entity_codigoServicioHasIdTipoCodigoServicioList);
        entity.setTipoIrregularidadHasEstatusList(entity_tipoIrregularidadHasEstatusList);
        entity.setReporteHasIdEstatusReporteActualList(entity_reporteHasIdEstatusReporteActualList);
        entity.setReporteHasIdTipoReporteList(entity_reporteHasIdTipoReporteList);
        entity.setConfiguracionCicloHasIdNocicloList(entity_configuracionCicloHasIdNocicloList);
        entity.setConfiguracionCicloHasIdTemporadaList(entity_configuracionCicloHasIdTemporadaList);
        entity.setConfiguracionCicloHasIdMesList(entity_configuracionCicloHasIdMesList);
        entity.setConfiguracionCicloHasIdTipoTemporadaList(entity_configuracionCicloHasIdTipoTemporadaList);
        entity.setConfiguracionCicloHasIdTipoQuincenaList(entity_configuracionCicloHasIdTipoQuincenaList);
        entity.setConfiguracionCicloHasIdPeriodicidadList(entity_configuracionCicloHasIdPeriodicidadList);
        entity.setTipoProductoReporteHasEstatusList(entity_tipoProductoReporteHasEstatusList);
        entity.setModeloAvionHasIdTipoCabinaList(entity_modeloAvionHasIdTipoCabinaList);
        entity.setModeloAvionHasIdFabricanteList(entity_modeloAvionHasIdFabricanteList);
        entity.setTsuHasIdIdiomaList(entity_tsuHasIdIdiomaList);
        entity.setTsuHasIdRevisionList(entity_tsuHasIdRevisionList);
        entity.setVueloHasIdTipoCabinaList(entity_vueloHasIdTipoCabinaList);
        entity.setPotencialHasIdTipocicloList(entity_potencialHasIdTipocicloList);
        entity.setPotencialHasIdNociclotemporadaList(entity_potencialHasIdNociclotemporadaList);
        entity.setPotencialHasIdEstatuspotencialList(entity_potencialHasIdEstatuspotencialList);
        entity.setComunicadoHasEstatusComunicadoList(entity_comunicadoHasEstatusComunicadoList);
        entity.setComunicadoHasIdIdiomaList(entity_comunicadoHasIdIdiomaList);
        entity.setComunicadoHasAccionComunicadoList(entity_comunicadoHasAccionComunicadoList);
        entity.setAcomodoHasIdIdiomaList(entity_acomodoHasIdIdiomaList);
        entity.setProcesoHasIdIdiomaList(entity_procesoHasIdIdiomaList);
        entity.setProcesoHasTipoProcesoList(entity_procesoHasTipoProcesoList);
        entity.setCriterioIrregularidadHasEstatusList(entity_criterioIrregularidadHasEstatusList);
        entity.setGraficoHasIdIdiomaList(entity_graficoHasIdIdiomaList);
        entity.setCicloHasIdTipocicloList(entity_cicloHasIdTipocicloList);
        entity.setCicloHasIdOrigenvueloList(entity_cicloHasIdOrigenvueloList);
        entity.setAvionHasIdConectividadList(entity_avionHasIdConectividadList);
        entity.setTablaAbordamientoHasIdOrigenVueloList(entity_tablaAbordamientoHasIdOrigenVueloList);
        entity.setMaterialKitMasterHasIdUnidadMedidaList(entity_materialKitMasterHasIdUnidadMedidaList);
        entity.setMaterialKitMasterHasIdInstruccionesNacionalesList(entity_materialKitMasterHasIdInstruccionesNacionalesList);
        entity.setMaterialKitMasterHasIdInstruccionesInternacList(entity_materialKitMasterHasIdInstruccionesInternacList);
        entity.setDetalleTablaTsuList(entity_detalleTablaTsuList);
        entity.setDetalleTablaComplementarioList(entity_detalleTablaComplementarioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();

		return entity;
	}

	@Override
	public Parametro update(Parametro entity) {
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
        final List<Material> entity_materialHasTipoAbastecimientoList =  entity.getMaterialHasTipoAbastecimientoList();
        entity.setMaterialHasTipoAbastecimientoList(null);
        final List<KitMaster> entity_kitMasterHasIdUnidadMedidaList =  entity.getKitMasterHasIdUnidadMedidaList();
        entity.setKitMasterHasIdUnidadMedidaList(null);
        final List<KitMaster> entity_kitMasterHasIdInstruccionesNacionalesList =  entity.getKitMasterHasIdInstruccionesNacionalesList();
        entity.setKitMasterHasIdInstruccionesNacionalesList(null);
        final List<KitMaster> entity_kitMasterHasIdTipoKitList =  entity.getKitMasterHasIdTipoKitList();
        entity.setKitMasterHasIdTipoKitList(null);
        final List<KitMaster> entity_kitMasterHasIdInstruccionesInternacList =  entity.getKitMasterHasIdInstruccionesInternacList();
        entity.setKitMasterHasIdInstruccionesInternacList(null);
        final List<AsignacionServicio> entity_asignacionServicioHasIdOrigenList =  entity.getAsignacionServicioHasIdOrigenList();
        entity.setAsignacionServicioHasIdOrigenList(null);
        final List<ConfiguracionReporte> entity_configuracionReporteHasStatusList =  entity.getConfiguracionReporteHasStatusList();
        entity.setConfiguracionReporteHasStatusList(null);
        final List<CodigoServicio> entity_codigoServicioHasIdTipoCicloList =  entity.getCodigoServicioHasIdTipoCicloList();
        entity.setCodigoServicioHasIdTipoCicloList(null);
        final List<CodigoServicio> entity_codigoServicioHasIdTipoCodigoServicioList =  entity.getCodigoServicioHasIdTipoCodigoServicioList();
        entity.setCodigoServicioHasIdTipoCodigoServicioList(null);
        final List<TipoIrregularidad> entity_tipoIrregularidadHasEstatusList =  entity.getTipoIrregularidadHasEstatusList();
        entity.setTipoIrregularidadHasEstatusList(null);
        final List<Reporte> entity_reporteHasIdEstatusReporteActualList =  entity.getReporteHasIdEstatusReporteActualList();
        entity.setReporteHasIdEstatusReporteActualList(null);
        final List<Reporte> entity_reporteHasIdTipoReporteList =  entity.getReporteHasIdTipoReporteList();
        entity.setReporteHasIdTipoReporteList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdNocicloList =  entity.getConfiguracionCicloHasIdNocicloList();
        entity.setConfiguracionCicloHasIdNocicloList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdTemporadaList =  entity.getConfiguracionCicloHasIdTemporadaList();
        entity.setConfiguracionCicloHasIdTemporadaList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdMesList =  entity.getConfiguracionCicloHasIdMesList();
        entity.setConfiguracionCicloHasIdMesList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdTipoTemporadaList =  entity.getConfiguracionCicloHasIdTipoTemporadaList();
        entity.setConfiguracionCicloHasIdTipoTemporadaList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdTipoQuincenaList =  entity.getConfiguracionCicloHasIdTipoQuincenaList();
        entity.setConfiguracionCicloHasIdTipoQuincenaList(null);
        final List<ConfiguracionCiclo> entity_configuracionCicloHasIdPeriodicidadList =  entity.getConfiguracionCicloHasIdPeriodicidadList();
        entity.setConfiguracionCicloHasIdPeriodicidadList(null);
        final List<TipoProductoReporte> entity_tipoProductoReporteHasEstatusList =  entity.getTipoProductoReporteHasEstatusList();
        entity.setTipoProductoReporteHasEstatusList(null);
        final List<ModeloAvion> entity_modeloAvionHasIdTipoCabinaList =  entity.getModeloAvionHasIdTipoCabinaList();
        entity.setModeloAvionHasIdTipoCabinaList(null);
        final List<ModeloAvion> entity_modeloAvionHasIdFabricanteList =  entity.getModeloAvionHasIdFabricanteList();
        entity.setModeloAvionHasIdFabricanteList(null);
        final List<Tsu> entity_tsuHasIdIdiomaList =  entity.getTsuHasIdIdiomaList();
        entity.setTsuHasIdIdiomaList(null);
        final List<Tsu> entity_tsuHasIdRevisionList =  entity.getTsuHasIdRevisionList();
        entity.setTsuHasIdRevisionList(null);
        final List<Vuelo> entity_vueloHasIdTipoCabinaList =  entity.getVueloHasIdTipoCabinaList();
        entity.setVueloHasIdTipoCabinaList(null);
        final List<Potencial> entity_potencialHasIdTipocicloList =  entity.getPotencialHasIdTipocicloList();
        entity.setPotencialHasIdTipocicloList(null);
        final List<Potencial> entity_potencialHasIdNociclotemporadaList =  entity.getPotencialHasIdNociclotemporadaList();
        entity.setPotencialHasIdNociclotemporadaList(null);
        final List<Potencial> entity_potencialHasIdEstatuspotencialList =  entity.getPotencialHasIdEstatuspotencialList();
        entity.setPotencialHasIdEstatuspotencialList(null);
        final List<Comunicado> entity_comunicadoHasEstatusComunicadoList =  entity.getComunicadoHasEstatusComunicadoList();
        entity.setComunicadoHasEstatusComunicadoList(null);
        final List<Comunicado> entity_comunicadoHasIdIdiomaList =  entity.getComunicadoHasIdIdiomaList();
        entity.setComunicadoHasIdIdiomaList(null);
        final List<Comunicado> entity_comunicadoHasAccionComunicadoList =  entity.getComunicadoHasAccionComunicadoList();
        entity.setComunicadoHasAccionComunicadoList(null);
        final List<Acomodo> entity_acomodoHasIdIdiomaList =  entity.getAcomodoHasIdIdiomaList();
        entity.setAcomodoHasIdIdiomaList(null);
        final List<Proceso> entity_procesoHasIdIdiomaList =  entity.getProcesoHasIdIdiomaList();
        entity.setProcesoHasIdIdiomaList(null);
        final List<Proceso> entity_procesoHasTipoProcesoList =  entity.getProcesoHasTipoProcesoList();
        entity.setProcesoHasTipoProcesoList(null);
        final List<CriterioIrregularidad> entity_criterioIrregularidadHasEstatusList =  entity.getCriterioIrregularidadHasEstatusList();
        entity.setCriterioIrregularidadHasEstatusList(null);
        final List<Grafico> entity_graficoHasIdIdiomaList =  entity.getGraficoHasIdIdiomaList();
        entity.setGraficoHasIdIdiomaList(null);
        final List<Ciclo> entity_cicloHasIdTipocicloList =  entity.getCicloHasIdTipocicloList();
        entity.setCicloHasIdTipocicloList(null);
        final List<Ciclo> entity_cicloHasIdOrigenvueloList =  entity.getCicloHasIdOrigenvueloList();
        entity.setCicloHasIdOrigenvueloList(null);
        final List<Avion> entity_avionHasIdConectividadList =  entity.getAvionHasIdConectividadList();
        entity.setAvionHasIdConectividadList(null);
        final List<TablaAbordamiento> entity_tablaAbordamientoHasIdOrigenVueloList =  entity.getTablaAbordamientoHasIdOrigenVueloList();
        entity.setTablaAbordamientoHasIdOrigenVueloList(null);
        final List<MaterialKitMaster> entity_materialKitMasterHasIdUnidadMedidaList =  entity.getMaterialKitMasterHasIdUnidadMedidaList();
        entity.setMaterialKitMasterHasIdUnidadMedidaList(null);
        final List<MaterialKitMaster> entity_materialKitMasterHasIdInstruccionesNacionalesList =  entity.getMaterialKitMasterHasIdInstruccionesNacionalesList();
        entity.setMaterialKitMasterHasIdInstruccionesNacionalesList(null);
        final List<MaterialKitMaster> entity_materialKitMasterHasIdInstruccionesInternacList =  entity.getMaterialKitMasterHasIdInstruccionesInternacList();
        entity.setMaterialKitMasterHasIdInstruccionesInternacList(null);
        final List<DetalleTablaTsu> entity_detalleTablaTsuList =  entity.getDetalleTablaTsuList();
        entity.setDetalleTablaTsuList(null);
        final List<DetalleTablaComplementario> entity_detalleTablaComplementarioList =  entity.getDetalleTablaComplementarioList();
        entity.setDetalleTablaComplementarioList(null);

		getEntityManager().merge(entity);
		getEntityManager().flush();
        // EAGER M-2-M persist
        entity.setMaterialHasTipoAbastecimientoList(entity_materialHasTipoAbastecimientoList);
        entity.setKitMasterHasIdUnidadMedidaList(entity_kitMasterHasIdUnidadMedidaList);
        entity.setKitMasterHasIdInstruccionesNacionalesList(entity_kitMasterHasIdInstruccionesNacionalesList);
        entity.setKitMasterHasIdTipoKitList(entity_kitMasterHasIdTipoKitList);
        entity.setKitMasterHasIdInstruccionesInternacList(entity_kitMasterHasIdInstruccionesInternacList);
        entity.setAsignacionServicioHasIdOrigenList(entity_asignacionServicioHasIdOrigenList);
        entity.setConfiguracionReporteHasStatusList(entity_configuracionReporteHasStatusList);
        entity.setCodigoServicioHasIdTipoCicloList(entity_codigoServicioHasIdTipoCicloList);
        entity.setCodigoServicioHasIdTipoCodigoServicioList(entity_codigoServicioHasIdTipoCodigoServicioList);
        entity.setTipoIrregularidadHasEstatusList(entity_tipoIrregularidadHasEstatusList);
        entity.setReporteHasIdEstatusReporteActualList(entity_reporteHasIdEstatusReporteActualList);
        entity.setReporteHasIdTipoReporteList(entity_reporteHasIdTipoReporteList);
        entity.setConfiguracionCicloHasIdNocicloList(entity_configuracionCicloHasIdNocicloList);
        entity.setConfiguracionCicloHasIdTemporadaList(entity_configuracionCicloHasIdTemporadaList);
        entity.setConfiguracionCicloHasIdMesList(entity_configuracionCicloHasIdMesList);
        entity.setConfiguracionCicloHasIdTipoTemporadaList(entity_configuracionCicloHasIdTipoTemporadaList);
        entity.setConfiguracionCicloHasIdTipoQuincenaList(entity_configuracionCicloHasIdTipoQuincenaList);
        entity.setConfiguracionCicloHasIdPeriodicidadList(entity_configuracionCicloHasIdPeriodicidadList);
        entity.setTipoProductoReporteHasEstatusList(entity_tipoProductoReporteHasEstatusList);
        entity.setModeloAvionHasIdTipoCabinaList(entity_modeloAvionHasIdTipoCabinaList);
        entity.setModeloAvionHasIdFabricanteList(entity_modeloAvionHasIdFabricanteList);
        entity.setTsuHasIdIdiomaList(entity_tsuHasIdIdiomaList);
        entity.setTsuHasIdRevisionList(entity_tsuHasIdRevisionList);
        entity.setVueloHasIdTipoCabinaList(entity_vueloHasIdTipoCabinaList);
        entity.setPotencialHasIdTipocicloList(entity_potencialHasIdTipocicloList);
        entity.setPotencialHasIdNociclotemporadaList(entity_potencialHasIdNociclotemporadaList);
        entity.setPotencialHasIdEstatuspotencialList(entity_potencialHasIdEstatuspotencialList);
        entity.setComunicadoHasEstatusComunicadoList(entity_comunicadoHasEstatusComunicadoList);
        entity.setComunicadoHasIdIdiomaList(entity_comunicadoHasIdIdiomaList);
        entity.setComunicadoHasAccionComunicadoList(entity_comunicadoHasAccionComunicadoList);
        entity.setAcomodoHasIdIdiomaList(entity_acomodoHasIdIdiomaList);
        entity.setProcesoHasIdIdiomaList(entity_procesoHasIdIdiomaList);
        entity.setProcesoHasTipoProcesoList(entity_procesoHasTipoProcesoList);
        entity.setCriterioIrregularidadHasEstatusList(entity_criterioIrregularidadHasEstatusList);
        entity.setGraficoHasIdIdiomaList(entity_graficoHasIdIdiomaList);
        entity.setCicloHasIdTipocicloList(entity_cicloHasIdTipocicloList);
        entity.setCicloHasIdOrigenvueloList(entity_cicloHasIdOrigenvueloList);
        entity.setAvionHasIdConectividadList(entity_avionHasIdConectividadList);
        entity.setTablaAbordamientoHasIdOrigenVueloList(entity_tablaAbordamientoHasIdOrigenVueloList);
        entity.setMaterialKitMasterHasIdUnidadMedidaList(entity_materialKitMasterHasIdUnidadMedidaList);
        entity.setMaterialKitMasterHasIdInstruccionesNacionalesList(entity_materialKitMasterHasIdInstruccionesNacionalesList);
        entity.setMaterialKitMasterHasIdInstruccionesInternacList(entity_materialKitMasterHasIdInstruccionesInternacList);
        entity.setDetalleTablaTsuList(entity_detalleTablaTsuList);
        entity.setDetalleTablaComplementarioList(entity_detalleTablaComplementarioList);

        getEntityManager().merge(entity);
        getEntityManager().flush();
		return entity;
	}


	@Override
	public List<Parametro> findAllLike(Parametro x){				
		StringBuilder sbq=new StringBuilder("SELECT x FROM Parametro x WHERE ");
		int paramAsigned=0;
		if(x != null){
			sbq.append(" 1=1 ");
			if(x.getIdParametro() != null){
			    paramAsigned++;
			    sbq.append(" and x.idParametro = :idParametro");
			}
			if(x.getClave() != null){
			    paramAsigned++;
			    sbq.append(" and x.clave = :clave");
			}
			if(x.getValorEs() != null){
			    paramAsigned++;
			    sbq.append(" and x.valorEs = :valorEs");
			}
			if(x.getValorEn() != null){
			    paramAsigned++;
			    sbq.append(" and x.valorEn = :valorEn");
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
		
		TypedQuery<Parametro> nq = em.createQuery(sbq.toString(), Parametro.class);
		
		if(paramAsigned>0){
			if(x.getIdParametro() != null){
			    nq.setParameter("idParametro",x.getIdParametro());
			}
			if(x.getClave() != null){
			    nq.setParameter("clave",x.getClave());
			}
			if(x.getValorEs() != null){
			    nq.setParameter("valorEs",x.getValorEs());
			}
			if(x.getValorEn() != null){
			    nq.setParameter("valorEn",x.getValorEn());
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
	public Parametro findByPK_EAGER(Object pk){
        Parametro x = getEntityManager().find(Parametro.class, pk);
        if( x != null){
            for(Material x_material: x.getMaterialHasTipoAbastecimientoList() ) {} 
            for(KitMaster x_kitMaster: x.getKitMasterHasIdUnidadMedidaList() ) {} 
            for(KitMaster x_kitMaster: x.getKitMasterHasIdInstruccionesNacionalesList() ) {} 
            for(KitMaster x_kitMaster: x.getKitMasterHasIdTipoKitList() ) {} 
            for(KitMaster x_kitMaster: x.getKitMasterHasIdInstruccionesInternacList() ) {} 
            for(AsignacionServicio x_asignacionServicio: x.getAsignacionServicioHasIdOrigenList() ) {} 
            for(ConfiguracionReporte x_configuracionReporte: x.getConfiguracionReporteHasStatusList() ) {} 
            for(CodigoServicio x_codigoServicio: x.getCodigoServicioHasIdTipoCicloList() ) {} 
            for(CodigoServicio x_codigoServicio: x.getCodigoServicioHasIdTipoCodigoServicioList() ) {} 
            for(TipoIrregularidad x_tipoIrregularidad: x.getTipoIrregularidadHasEstatusList() ) {} 
            for(Reporte x_reporte: x.getReporteHasIdEstatusReporteActualList() ) {} 
            for(Reporte x_reporte: x.getReporteHasIdTipoReporteList() ) {} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasIdNocicloList() ) {} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasIdTemporadaList() ) {} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasIdMesList() ) {} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasIdTipoTemporadaList() ) {} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasIdTipoQuincenaList() ) {} 
            for(ConfiguracionCiclo x_configuracionCiclo: x.getConfiguracionCicloHasIdPeriodicidadList() ) {} 
            for(TipoProductoReporte x_tipoProductoReporte: x.getTipoProductoReporteHasEstatusList() ) {} 
            for(ModeloAvion x_modeloAvion: x.getModeloAvionHasIdTipoCabinaList() ) {} 
            for(ModeloAvion x_modeloAvion: x.getModeloAvionHasIdFabricanteList() ) {} 
            for(Tsu x_tsu: x.getTsuHasIdIdiomaList() ) {} 
            for(Tsu x_tsu: x.getTsuHasIdRevisionList() ) {} 
            for(Vuelo x_vuelo: x.getVueloHasIdTipoCabinaList() ) {} 
            for(Potencial x_potencial: x.getPotencialHasIdTipocicloList() ) {} 
            for(Potencial x_potencial: x.getPotencialHasIdNociclotemporadaList() ) {} 
            for(Potencial x_potencial: x.getPotencialHasIdEstatuspotencialList() ) {} 
            for(Comunicado x_comunicado: x.getComunicadoHasEstatusComunicadoList() ) {} 
            for(Comunicado x_comunicado: x.getComunicadoHasIdIdiomaList() ) {} 
            for(Comunicado x_comunicado: x.getComunicadoHasAccionComunicadoList() ) {} 
            for(Acomodo x_acomodo: x.getAcomodoHasIdIdiomaList() ) {} 
            for(Proceso x_proceso: x.getProcesoHasIdIdiomaList() ) {} 
            for(Proceso x_proceso: x.getProcesoHasTipoProcesoList() ) {} 
            for(CriterioIrregularidad x_criterioIrregularidad: x.getCriterioIrregularidadHasEstatusList() ) {} 
            for(Grafico x_grafico: x.getGraficoHasIdIdiomaList() ) {} 
            for(Ciclo x_ciclo: x.getCicloHasIdTipocicloList() ) {} 
            for(Ciclo x_ciclo: x.getCicloHasIdOrigenvueloList() ) {} 
            for(Avion x_avion: x.getAvionHasIdConectividadList() ) {} 
            for(TablaAbordamiento x_tablaAbordamiento: x.getTablaAbordamientoHasIdOrigenVueloList() ) {} 
            for(MaterialKitMaster x_materialKitMaster: x.getMaterialKitMasterHasIdUnidadMedidaList() ) {} 
            for(MaterialKitMaster x_materialKitMaster: x.getMaterialKitMasterHasIdInstruccionesNacionalesList() ) {} 
            for(MaterialKitMaster x_materialKitMaster: x.getMaterialKitMasterHasIdInstruccionesInternacList() ) {} 
            for(DetalleTablaTsu x_DetalleTablaTsu: x.getDetalleTablaTsuList() ) {} 
            for(DetalleTablaComplementario x_DetalleTablaComplementario: x.getDetalleTablaComplementarioList() ) {} 

        }
        return x;
    }

	@Override
	public List<Parametro> findAll() {
		TypedQuery<Parametro> nq = em.createNamedQuery("Parametro.findAll", Parametro.class);		
		return nq.getResultList();
	}
	
	@Override
	public Long countAll() {
		TypedQuery<Long> nq = em.createNamedQuery("Parametro.countAll", Long.class);		
		return (Long)nq.getSingleResult().longValue();
	}
	
}
