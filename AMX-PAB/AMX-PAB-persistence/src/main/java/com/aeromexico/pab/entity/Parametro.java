package com.aeromexico.pab.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Embeddable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class for mapping JPA Entity of Table parametro.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "parametro")
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
    , @NamedQuery(name = "Parametro.countAll", query = "SELECT COUNT(p) FROM Parametro p")
    , @NamedQuery(name = "Parametro.findByIdParametro", query = "SELECT p FROM Parametro p WHERE p.idParametro = :idParametro")
    , @NamedQuery(name = "Parametro.findByClave", query = "SELECT p FROM Parametro p WHERE p.clave = :clave")
    , @NamedQuery(name = "Parametro.findByValorEs", query = "SELECT p FROM Parametro p WHERE p.valorEs = :valorEs")
    , @NamedQuery(name = "Parametro.findByValorEn", query = "SELECT p FROM Parametro p WHERE p.valorEn = :valorEn")
    , @NamedQuery(name = "Parametro.findByStatus", query = "SELECT p FROM Parametro p WHERE p.status = :status")
    , @NamedQuery(name = "Parametro.findByUsuarioCreo", query = "SELECT p FROM Parametro p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Parametro.findByFechaCreo", query = "SELECT p FROM Parametro p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Parametro.findByUsuarioModifico", query = "SELECT p FROM Parametro p WHERE p.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Parametro.findByFechaModifico", query = "SELECT p FROM Parametro p WHERE p.fechaModifico = :fechaModifico")
})
public class      Parametro 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1954460985;
    
    /**
    * The 'id parametro' Maps to COLUMN 'id_parametro'
    */
    
    @Id
    @Column(name = "ID_PARAMETRO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idParametro;
    
    /**
    * The 'clave' Maps to COLUMN 'clave'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE" , length=50, nullable=false)
    private String clave;
    
    /**
    * The 'valor es' Maps to COLUMN 'valor_es'
    */
    
    @Basic(optional = false)
    @Column(name = "VALOR_ES" , length=255, nullable=false)
    private String valorEs;
    
    /**
    * The 'valor en' Maps to COLUMN 'valor_en'
    */
    
    @Basic(optional = false)
    @Column(name = "VALOR_EN" , length=255, nullable=false)
    private String valorEn;
    
    /**
    * The 'status' Maps to COLUMN 'status'
    */
    
    @Basic(optional = false)
    @Column(name = "STATUS" , nullable=false)
    private Short status;
    
    /**
    * The 'usuario creo' Maps to COLUMN 'usuario_creo'
    */
    
    @Basic(optional = false)
    @Column(name = "USUARIO_CREO" , length=100, nullable=false)
    private String usuarioCreo;
    
    /**
    * The 'fecha creo' Maps to COLUMN 'fecha_creo'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA_CREO" , nullable=false)
    private java.sql.Timestamp fechaCreo;
    
    /**
    * The 'usuario modifico' Maps to COLUMN 'usuario_modifico'
    */
    
    @Basic(optional = false)
    @Column(name = "USUARIO_MODIFICO" , length=100, nullable=false)
    private String usuarioModifico;
    
    /**
    * The 'fecha modifico' Maps to COLUMN 'fecha_modifico'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA_MODIFICO" , nullable=false)
    private java.sql.Timestamp fechaModifico;
    /** 
    * Map the relation to Material table where has tipo_abastecimiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAbastecimiento")
    private List<Material> materialHasTipoAbastecimientoList;
    
    /** 
    * Map the relation to kit_master table where has id_unidad_medida object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadMedida")
    private List<KitMaster> kitMasterHasIdUnidadMedidaList;
    
    /** 
    * Map the relation to kit_master table where has id_instrucciones_nacionales object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstruccionesNacionales")
    private List<KitMaster> kitMasterHasIdInstruccionesNacionalesList;
    
    /** 
    * Map the relation to kit_master table where has id_tipo_kit object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoKit")
    private List<KitMaster> kitMasterHasIdTipoKitList;
    
    /** 
    * Map the relation to kit_master table where has id_instrucciones_internac object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstruccionesInternac")
    private List<KitMaster> kitMasterHasIdInstruccionesInternacList;
    
    /** 
    * Map the relation to Asignacion_Servicio table where has id_origen object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrigen")
    private List<AsignacionServicio> asignacionServicioHasIdOrigenList;
    
    /** 
    * Map the relation to configuracion_reporte table where has status object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<ConfiguracionReporte> configuracionReporteHasStatusList;
    
    /** 
    * Map the relation to Codigo_Servicio table where has id_tipo_ciclo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCiclo")
    private List<CodigoServicio> codigoServicioHasIdTipoCicloList;
    
    /** 
    * Map the relation to Codigo_Servicio table where has id_tipo_codigo_servicio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCodigoServicio")
    private List<CodigoServicio> codigoServicioHasIdTipoCodigoServicioList;
    
    /** 
    * Map the relation to tipo_irregularidad table where has estatus object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatus")
    private List<TipoIrregularidad> tipoIrregularidadHasEstatusList;
    
    /** 
    * Map the relation to reporte table where has id_estatus_reporte_actual object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatusReporteActual")
    private List<Reporte> reporteHasIdEstatusReporteActualList;
    
    /** 
    * Map the relation to reporte table where has id_tipo_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoReporte")
    private List<Reporte> reporteHasIdTipoReporteList;
    
    /** 
    * Map the relation to Configuracion_Ciclo table where has id_noCiclo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNociclo")
    private List<ConfiguracionCiclo> configuracionCicloHasIdNocicloList;
    
    /** 
    * Map the relation to Configuracion_Ciclo table where has id_temporada object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTemporada")
    private List<ConfiguracionCiclo> configuracionCicloHasIdTemporadaList;
    
    /** 
    * Map the relation to Configuracion_Ciclo table where has id_mes object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMes")
    private List<ConfiguracionCiclo> configuracionCicloHasIdMesList;
    
    /** 
    * Map the relation to Configuracion_Ciclo table where has id_tipo_temporada object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoTemporada")
    private List<ConfiguracionCiclo> configuracionCicloHasIdTipoTemporadaList;
    
    /** 
    * Map the relation to Configuracion_Ciclo table where has id_tipo_quincena object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoQuincena")
    private List<ConfiguracionCiclo> configuracionCicloHasIdTipoQuincenaList;
    
    /** 
    * Map the relation to Configuracion_Ciclo table where has id_periodicidad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodicidad")
    private List<ConfiguracionCiclo> configuracionCicloHasIdPeriodicidadList;
    
    /** 
    * Map the relation to tipo_producto_reporte table where has estatus object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatus")
    private List<TipoProductoReporte> tipoProductoReporteHasEstatusList;
    
    /** 
    * Map the relation to modelo_avion table where has id_tipo_cabina object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCabina")
    private List<ModeloAvion> modeloAvionHasIdTipoCabinaList;
    
    /** 
    * Map the relation to modelo_avion table where has id_fabricante object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFabricante")
    private List<ModeloAvion> modeloAvionHasIdFabricanteList;
    
    /** 
    * Map the relation to TSU table where has id_idioma object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private List<Tsu> tsuHasIdIdiomaList;
    
    /** 
    * Map the relation to TSU table where has id_revision object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRevision")
    private List<Tsu> tsuHasIdRevisionList;
    
    /** 
    * Map the relation to vuelo table where has id_tipo_cabina object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCabina")
    private List<Vuelo> vueloHasIdTipoCabinaList;
    
    /** 
    * Map the relation to potencial table where has id_TipoCiclo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipociclo")
    private List<Potencial> potencialHasIdTipocicloList;
    
    /** 
    * Map the relation to potencial table where has id_noCicloTemporada object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNociclotemporada")
    private List<Potencial> potencialHasIdNociclotemporadaList;
    
    /** 
    * Map the relation to potencial table where has id_estatusPotencial object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstatuspotencial")
    private List<Potencial> potencialHasIdEstatuspotencialList;
    
    /** 
    * Map the relation to Comunicado table where has estatus_comunicado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatusComunicado")
    private List<Comunicado> comunicadoHasEstatusComunicadoList;
    
    /** 
    * Map the relation to Comunicado table where has id_idioma object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private List<Comunicado> comunicadoHasIdIdiomaList;
    
    /** 
    * Map the relation to Comunicado table where has accion_comunicado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accionComunicado")
    private List<Comunicado> comunicadoHasAccionComunicadoList;
    
    /** 
    * Map the relation to acomodo table where has id_idioma object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private List<Acomodo> acomodoHasIdIdiomaList;
    
    /** 
    * Map the relation to Proceso table where has id_idioma object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private List<Proceso> procesoHasIdIdiomaList;
    
    /** 
    * Map the relation to Proceso table where has tipo_proceso object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProceso")
    private List<Proceso> procesoHasTipoProcesoList;
    
    /** 
    * Map the relation to criterio_irregularidad table where has estatus object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatus")
    private List<CriterioIrregularidad> criterioIrregularidadHasEstatusList;
    
    /** 
    * Map the relation to grafico table where has id_idioma object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private List<Grafico> graficoHasIdIdiomaList;
    
    /** 
    * Map the relation to ciclo table where has id_TipoCiclo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipociclo")
    private List<Ciclo> cicloHasIdTipocicloList;
    
    /** 
    * Map the relation to ciclo table where has id_origenVuelo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrigenvuelo")
    private List<Ciclo> cicloHasIdOrigenvueloList;
    
    /** 
    * Map the relation to avion table where has id_conectividad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConectividad")
    private List<Avion> avionHasIdConectividadList;
    
    /** 
    * Map the relation to tabla_abordamiento table where has id_origen_vuelo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrigenVuelo")
    private List<TablaAbordamiento> tablaAbordamientoHasIdOrigenVueloList;
    
    /** 
    * Map the relation to material_kit_master table where has id_unidad_medida object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadMedida")
    private List<MaterialKitMaster> materialKitMasterHasIdUnidadMedidaList;
    
    /** 
    * Map the relation to material_kit_master table where has id_instrucciones_nacionales object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstruccionesNacionales")
    private List<MaterialKitMaster> materialKitMasterHasIdInstruccionesNacionalesList;
    
    /** 
    * Map the relation to material_kit_master table where has id_instrucciones_internac object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstruccionesInternac")
    private List<MaterialKitMaster> materialKitMasterHasIdInstruccionesInternacList;
    

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "parametroList")
    private List<DetalleTablaTsu> detalleTablaTsuList;
    
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "parametroList")
    private List<DetalleTablaComplementario> detalleTablaComplementarioList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Parametro() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdParametro() { return this.idParametro;}
    public void setIdParametro(Integer v) { this.idParametro = v; }
    
    public String getClave() { return this.clave;}
    public void setClave(String v) { this.clave = v; }
    
    public String getValorEs() { return this.valorEs;}
    public void setValorEs(String v) { this.valorEs = v; }
    
    public String getValorEn() { return this.valorEn;}
    public void setValorEn(String v) { this.valorEn = v; }
    
    public Short getStatus() { return this.status;}
    public void setStatus(Short v) { this.status = v; }
    
    public String getUsuarioCreo() { return this.usuarioCreo;}
    public void setUsuarioCreo(String v) { this.usuarioCreo = v; }
    
    public java.sql.Timestamp getFechaCreo() { return this.fechaCreo;}
    public void setFechaCreo(java.sql.Timestamp v) { this.fechaCreo = v; }
    
    public String getUsuarioModifico() { return this.usuarioModifico;}
    public void setUsuarioModifico(String v) { this.usuarioModifico = v; }
    
    public java.sql.Timestamp getFechaModifico() { return this.fechaModifico;}
    public void setFechaModifico(java.sql.Timestamp v) { this.fechaModifico = v; }
    
    // O2M <*>    
    public List<Material> getMaterialHasTipoAbastecimientoList(){ return this.materialHasTipoAbastecimientoList; }
    public void setMaterialHasTipoAbastecimientoList(List<Material> v){ this.materialHasTipoAbastecimientoList = v; }
    
    public List<KitMaster> getKitMasterHasIdUnidadMedidaList(){ return this.kitMasterHasIdUnidadMedidaList; }
    public void setKitMasterHasIdUnidadMedidaList(List<KitMaster> v){ this.kitMasterHasIdUnidadMedidaList = v; }
    
    public List<KitMaster> getKitMasterHasIdInstruccionesNacionalesList(){ return this.kitMasterHasIdInstruccionesNacionalesList; }
    public void setKitMasterHasIdInstruccionesNacionalesList(List<KitMaster> v){ this.kitMasterHasIdInstruccionesNacionalesList = v; }
    
    public List<KitMaster> getKitMasterHasIdTipoKitList(){ return this.kitMasterHasIdTipoKitList; }
    public void setKitMasterHasIdTipoKitList(List<KitMaster> v){ this.kitMasterHasIdTipoKitList = v; }
    
    public List<KitMaster> getKitMasterHasIdInstruccionesInternacList(){ return this.kitMasterHasIdInstruccionesInternacList; }
    public void setKitMasterHasIdInstruccionesInternacList(List<KitMaster> v){ this.kitMasterHasIdInstruccionesInternacList = v; }
    
    public List<AsignacionServicio> getAsignacionServicioHasIdOrigenList(){ return this.asignacionServicioHasIdOrigenList; }
    public void setAsignacionServicioHasIdOrigenList(List<AsignacionServicio> v){ this.asignacionServicioHasIdOrigenList = v; }
    
    public List<ConfiguracionReporte> getConfiguracionReporteHasStatusList(){ return this.configuracionReporteHasStatusList; }
    public void setConfiguracionReporteHasStatusList(List<ConfiguracionReporte> v){ this.configuracionReporteHasStatusList = v; }
    
    public List<CodigoServicio> getCodigoServicioHasIdTipoCicloList(){ return this.codigoServicioHasIdTipoCicloList; }
    public void setCodigoServicioHasIdTipoCicloList(List<CodigoServicio> v){ this.codigoServicioHasIdTipoCicloList = v; }
    
    public List<CodigoServicio> getCodigoServicioHasIdTipoCodigoServicioList(){ return this.codigoServicioHasIdTipoCodigoServicioList; }
    public void setCodigoServicioHasIdTipoCodigoServicioList(List<CodigoServicio> v){ this.codigoServicioHasIdTipoCodigoServicioList = v; }
    
    public List<TipoIrregularidad> getTipoIrregularidadHasEstatusList(){ return this.tipoIrregularidadHasEstatusList; }
    public void setTipoIrregularidadHasEstatusList(List<TipoIrregularidad> v){ this.tipoIrregularidadHasEstatusList = v; }
    
    public List<Reporte> getReporteHasIdEstatusReporteActualList(){ return this.reporteHasIdEstatusReporteActualList; }
    public void setReporteHasIdEstatusReporteActualList(List<Reporte> v){ this.reporteHasIdEstatusReporteActualList = v; }
    
    public List<Reporte> getReporteHasIdTipoReporteList(){ return this.reporteHasIdTipoReporteList; }
    public void setReporteHasIdTipoReporteList(List<Reporte> v){ this.reporteHasIdTipoReporteList = v; }
    
    public List<ConfiguracionCiclo> getConfiguracionCicloHasIdNocicloList(){ return this.configuracionCicloHasIdNocicloList; }
    public void setConfiguracionCicloHasIdNocicloList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasIdNocicloList = v; }
    
    public List<ConfiguracionCiclo> getConfiguracionCicloHasIdTemporadaList(){ return this.configuracionCicloHasIdTemporadaList; }
    public void setConfiguracionCicloHasIdTemporadaList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasIdTemporadaList = v; }
    
    public List<ConfiguracionCiclo> getConfiguracionCicloHasIdMesList(){ return this.configuracionCicloHasIdMesList; }
    public void setConfiguracionCicloHasIdMesList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasIdMesList = v; }
    
    public List<ConfiguracionCiclo> getConfiguracionCicloHasIdTipoTemporadaList(){ return this.configuracionCicloHasIdTipoTemporadaList; }
    public void setConfiguracionCicloHasIdTipoTemporadaList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasIdTipoTemporadaList = v; }
    
    public List<ConfiguracionCiclo> getConfiguracionCicloHasIdTipoQuincenaList(){ return this.configuracionCicloHasIdTipoQuincenaList; }
    public void setConfiguracionCicloHasIdTipoQuincenaList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasIdTipoQuincenaList = v; }
    
    public List<ConfiguracionCiclo> getConfiguracionCicloHasIdPeriodicidadList(){ return this.configuracionCicloHasIdPeriodicidadList; }
    public void setConfiguracionCicloHasIdPeriodicidadList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasIdPeriodicidadList = v; }
    
    public List<TipoProductoReporte> getTipoProductoReporteHasEstatusList(){ return this.tipoProductoReporteHasEstatusList; }
    public void setTipoProductoReporteHasEstatusList(List<TipoProductoReporte> v){ this.tipoProductoReporteHasEstatusList = v; }
    
    public List<ModeloAvion> getModeloAvionHasIdTipoCabinaList(){ return this.modeloAvionHasIdTipoCabinaList; }
    public void setModeloAvionHasIdTipoCabinaList(List<ModeloAvion> v){ this.modeloAvionHasIdTipoCabinaList = v; }
    
    public List<ModeloAvion> getModeloAvionHasIdFabricanteList(){ return this.modeloAvionHasIdFabricanteList; }
    public void setModeloAvionHasIdFabricanteList(List<ModeloAvion> v){ this.modeloAvionHasIdFabricanteList = v; }
    
    public List<Tsu> getTsuHasIdIdiomaList(){ return this.tsuHasIdIdiomaList; }
    public void setTsuHasIdIdiomaList(List<Tsu> v){ this.tsuHasIdIdiomaList = v; }
    
    public List<Tsu> getTsuHasIdRevisionList(){ return this.tsuHasIdRevisionList; }
    public void setTsuHasIdRevisionList(List<Tsu> v){ this.tsuHasIdRevisionList = v; }
    
    public List<Vuelo> getVueloHasIdTipoCabinaList(){ return this.vueloHasIdTipoCabinaList; }
    public void setVueloHasIdTipoCabinaList(List<Vuelo> v){ this.vueloHasIdTipoCabinaList = v; }
    
    public List<Potencial> getPotencialHasIdTipocicloList(){ return this.potencialHasIdTipocicloList; }
    public void setPotencialHasIdTipocicloList(List<Potencial> v){ this.potencialHasIdTipocicloList = v; }
    
    public List<Potencial> getPotencialHasIdNociclotemporadaList(){ return this.potencialHasIdNociclotemporadaList; }
    public void setPotencialHasIdNociclotemporadaList(List<Potencial> v){ this.potencialHasIdNociclotemporadaList = v; }
    
    public List<Potencial> getPotencialHasIdEstatuspotencialList(){ return this.potencialHasIdEstatuspotencialList; }
    public void setPotencialHasIdEstatuspotencialList(List<Potencial> v){ this.potencialHasIdEstatuspotencialList = v; }
    
    public List<Comunicado> getComunicadoHasEstatusComunicadoList(){ return this.comunicadoHasEstatusComunicadoList; }
    public void setComunicadoHasEstatusComunicadoList(List<Comunicado> v){ this.comunicadoHasEstatusComunicadoList = v; }
    
    public List<Comunicado> getComunicadoHasIdIdiomaList(){ return this.comunicadoHasIdIdiomaList; }
    public void setComunicadoHasIdIdiomaList(List<Comunicado> v){ this.comunicadoHasIdIdiomaList = v; }
    
    public List<Comunicado> getComunicadoHasAccionComunicadoList(){ return this.comunicadoHasAccionComunicadoList; }
    public void setComunicadoHasAccionComunicadoList(List<Comunicado> v){ this.comunicadoHasAccionComunicadoList = v; }
    
    public List<Acomodo> getAcomodoHasIdIdiomaList(){ return this.acomodoHasIdIdiomaList; }
    public void setAcomodoHasIdIdiomaList(List<Acomodo> v){ this.acomodoHasIdIdiomaList = v; }
    
    public List<Proceso> getProcesoHasIdIdiomaList(){ return this.procesoHasIdIdiomaList; }
    public void setProcesoHasIdIdiomaList(List<Proceso> v){ this.procesoHasIdIdiomaList = v; }
    
    public List<Proceso> getProcesoHasTipoProcesoList(){ return this.procesoHasTipoProcesoList; }
    public void setProcesoHasTipoProcesoList(List<Proceso> v){ this.procesoHasTipoProcesoList = v; }
    
    public List<CriterioIrregularidad> getCriterioIrregularidadHasEstatusList(){ return this.criterioIrregularidadHasEstatusList; }
    public void setCriterioIrregularidadHasEstatusList(List<CriterioIrregularidad> v){ this.criterioIrregularidadHasEstatusList = v; }
    
    public List<Grafico> getGraficoHasIdIdiomaList(){ return this.graficoHasIdIdiomaList; }
    public void setGraficoHasIdIdiomaList(List<Grafico> v){ this.graficoHasIdIdiomaList = v; }
    
    public List<Ciclo> getCicloHasIdTipocicloList(){ return this.cicloHasIdTipocicloList; }
    public void setCicloHasIdTipocicloList(List<Ciclo> v){ this.cicloHasIdTipocicloList = v; }
    
    public List<Ciclo> getCicloHasIdOrigenvueloList(){ return this.cicloHasIdOrigenvueloList; }
    public void setCicloHasIdOrigenvueloList(List<Ciclo> v){ this.cicloHasIdOrigenvueloList = v; }
    
    public List<Avion> getAvionHasIdConectividadList(){ return this.avionHasIdConectividadList; }
    public void setAvionHasIdConectividadList(List<Avion> v){ this.avionHasIdConectividadList = v; }
    
    public List<TablaAbordamiento> getTablaAbordamientoHasIdOrigenVueloList(){ return this.tablaAbordamientoHasIdOrigenVueloList; }
    public void setTablaAbordamientoHasIdOrigenVueloList(List<TablaAbordamiento> v){ this.tablaAbordamientoHasIdOrigenVueloList = v; }
    
    public List<MaterialKitMaster> getMaterialKitMasterHasIdUnidadMedidaList(){ return this.materialKitMasterHasIdUnidadMedidaList; }
    public void setMaterialKitMasterHasIdUnidadMedidaList(List<MaterialKitMaster> v){ this.materialKitMasterHasIdUnidadMedidaList = v; }
    
    public List<MaterialKitMaster> getMaterialKitMasterHasIdInstruccionesNacionalesList(){ return this.materialKitMasterHasIdInstruccionesNacionalesList; }
    public void setMaterialKitMasterHasIdInstruccionesNacionalesList(List<MaterialKitMaster> v){ this.materialKitMasterHasIdInstruccionesNacionalesList = v; }
    
    public List<MaterialKitMaster> getMaterialKitMasterHasIdInstruccionesInternacList(){ return this.materialKitMasterHasIdInstruccionesInternacList; }
    public void setMaterialKitMasterHasIdInstruccionesInternacList(List<MaterialKitMaster> v){ this.materialKitMasterHasIdInstruccionesInternacList = v; }
    
	// M2M <*>
    public List<DetalleTablaTsu> getDetalleTablaTsuList() { return this.detalleTablaTsuList; }
    public void setDetalleTablaTsuList(List<DetalleTablaTsu>  v) { this.detalleTablaTsuList = v; }
    
    public List<DetalleTablaComplementario> getDetalleTablaComplementarioList() { return this.detalleTablaComplementarioList; }
    public void setDetalleTablaComplementarioList(List<DetalleTablaComplementario>  v) { this.detalleTablaComplementarioList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idParametro).hashCode();
		hash += String.valueOf(clave).hashCode();
		hash += String.valueOf(valorEs).hashCode();
		hash += String.valueOf(valorEn).hashCode();
		hash += String.valueOf(status).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(usuarioModifico).hashCode();
		hash += String.valueOf(fechaModifico).hashCode();
        return hash;
    }

	@Override
    public boolean equals(Object o){
		if (this == o) {
			return true;
	    }
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}        
        if (!(o instanceof Parametro)) {
            return false;
        }		
		Parametro other = (Parametro ) o;
		if (!Objects.equals(this.idParametro, other.idParametro)) { return false; }		
		if (!Objects.equals(this.clave, other.clave)) { return false; }		
		if (!Objects.equals(this.valorEs, other.valorEs)) { return false; }		
		if (!Objects.equals(this.valorEn, other.valorEn)) { return false; }		
		if (!Objects.equals(this.status, other.status)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.usuarioModifico, other.usuarioModifico)) { return false; }		
		if (!Objects.equals(this.fechaModifico, other.fechaModifico)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Parametro{");
		sb.append("idParametro" ).append("=").append(idParametro).append("|");
		sb.append("clave" ).append("=").append(clave).append("|");
		sb.append("valorEs" ).append("=").append(valorEs).append("|");
		sb.append("valorEn" ).append("=").append(valorEn).append("|");
		sb.append("status" ).append("=").append(status).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("usuarioModifico" ).append("=").append(usuarioModifico).append("|");
		sb.append("fechaModifico" ).append("=").append(fechaModifico).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(clave).append(" ");

		return sb.toString().trim();
	}

}
