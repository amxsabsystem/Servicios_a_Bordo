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
 * Class for mapping JPA Entity of Table responsable_producto.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "responsable_producto")
@NamedQueries({
    @NamedQuery(name = "ResponsableProducto.findAll", query = "SELECT r FROM ResponsableProducto r")
    , @NamedQuery(name = "ResponsableProducto.countAll", query = "SELECT COUNT(r) FROM ResponsableProducto r")
    , @NamedQuery(name = "ResponsableProducto.findByresponsableEstacion", query = "SELECT r FROM ResponsableProducto r WHERE r.responsableEstacion = :responsableEstacion")
    , @NamedQuery(name = "ResponsableProducto.findBytipoProductoReporte", query = "SELECT r FROM ResponsableProducto r WHERE r.tipoProductoReporte = :tipoProductoReporte")
    , @NamedQuery(name = "ResponsableProducto.findByResponsableProductoPK", query = "SELECT r FROM ResponsableProducto r WHERE r.responsableProductoPK = :responsableProductoPK")
})
public class      ResponsableProducto 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 284720968;
    
    @JoinColumn(name = "ID_RESPONSABLE_ESTACION" , referencedColumnName = "ID_RESPONSABLE_ESTACION",  insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private ResponsableEstacion responsableEstacion;
    
    @JoinColumn(name = "ID_TIPO_PRODUCTO_REPORTE" , referencedColumnName = "ID_TIPO_PRODUCTO_REPORTE",  insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private TipoProductoReporte tipoProductoReporte;
    
    @EmbeddedId
    private ResponsableProductoPK responsableProductoPK;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ResponsableProducto() {
    }
    
    /**
     * Getters and Setters
     */
    public ResponsableEstacion getResponsableEstacion(){ return this.responsableEstacion ; }
    public void setResponsableEstacion(ResponsableEstacion x){ this.responsableEstacion = x; }
    
    public TipoProductoReporte getTipoProductoReporte(){ return this.tipoProductoReporte ; }
    public void setTipoProductoReporte(TipoProductoReporte x){ this.tipoProductoReporte = x; }
    
    public ResponsableProductoPK getResponsableProductoPK() { return this.responsableProductoPK;}
    public void setResponsableProductoPK(ResponsableProductoPK v) { this.responsableProductoPK = v; }
    
    // O2M <*>    
    // BUG-O2M: -->> Apply ?equipamiento_semifijo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :equipamiento_semifijo.numero_parte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :equipamiento_semifijo.id_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?relacion_flota, M2M_rt?false
    // BUG-O2M: -->> Apply ?Material, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Material.tipo_abastecimiento, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Material.id_categoria_material, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?comunicado_proveedor_estacion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :comunicado_proveedor_estacion.id_comunicado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :comunicado_proveedor_estacion.id_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Kit_TSU, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Kit_TSU.cve_kit_master, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Kit_TSU.id_tsu_operacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?area, M2M_rt?false
    // BUG-O2M: -->> Apply ?estacion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :estacion.id_ciudad, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?opcion_menu, M2M_rt?false
    // BUG-O2M: -->> Apply ?acomodo_producto, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo_producto.id_producto, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo_producto.id_acomodo, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?acomodo_producto_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?auditable, M2M_rt?false
    // BUG-O2M: -->> Apply ?respuesta, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :respuesta.id_responsable_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :respuesta.id_contacto_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :respuesta.id_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :respuesta.id_area_responsable, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?kit_master, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :kit_master.id_unidad_medida, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :kit_master.id_instrucciones_nacionales, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :kit_master.id_tipo_kit, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :kit_master.id_multimedio, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :kit_master.id_instrucciones_internac, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?reporte_respuesta, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte_respuesta.id_respuesta, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :reporte_respuesta.id_reporte, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?reporte_respuesta_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?Asignacion_Servicio, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio.id_modelo_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio.id_origen, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio.id_clase, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio.id_region, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?sistema_entretenimiento, M2M_rt?false
    // BUG-O2M: -->> Apply ?proveedor, M2M_rt?false
    // BUG-O2M: -->> Apply ?configuracion_reporte, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :configuracion_reporte.status, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :configuracion_reporte.id_tipo_producto_reporte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :configuracion_reporte.id_tipo_irregularidad, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :configuracion_reporte.id_criterio_irregularidad, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?acomodo_detalle, M2M_rt?false
    // BUG-O2M: -->> Apply ?pais, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :pais.id_region, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Codigo_Servicio, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Codigo_Servicio.id_clase, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Codigo_Servicio.id_tipo_ciclo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Codigo_Servicio.id_tipo_codigo_servicio, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?tipo_equipo_avion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :tipo_equipo_avion.id_modelo_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?avion_audifono, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :avion_audifono.numero_parte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :avion_audifono.id_clase, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :avion_audifono.id_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?tipo_irregularidad, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :tipo_irregularidad.estatus, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?reporte, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_estacion_destino, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_tipo_producto_reporte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_estatus_reporte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_responsable_proveedor_estacion_final, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_responsable_amx, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_vuelo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_estacion_origen, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.tipo_reporte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_area_responsable, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_responsable_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_responsable_final_amx, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte.id_clase, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?grafico_detalle, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :grafico_detalle.id_grafico, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?responsable_producto, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :responsable_producto.id_tipo_producto_reporte, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :responsable_producto.id_responsable_estacion, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?responsable_producto_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?ciudad, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :ciudad.id_pais, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?reporte_evidencia, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte_evidencia.id_evidencia, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :reporte_evidencia.id_reporte, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?reporte_evidencia_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?perfil_usuario, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :perfil_usuario.perfil, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :perfil_usuario.email_usuario, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?perfil_usuario_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?Configuracion_Ciclo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_noCiclo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_temporada, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_ciclo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_mes, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_tipo_temporada, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_tipo_quincena, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Configuracion_Ciclo.id_periodicidad, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?usuario, M2M_rt?false
    // BUG-O2M: -->> Apply ?tipo_producto_reporte, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :tipo_producto_reporte.estatus, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Duracion_vuelos, M2M_rt?false
    // BUG-O2M: -->> Apply ?modelo_avion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :modelo_avion.id_compania, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :modelo_avion.id_tipo_cabina, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :modelo_avion.id_fabricante, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?traduccion, M2M_rt?false
    // BUG-O2M: -->> Apply ?TSU, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :TSU.id_idioma, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :TSU.id_clase, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :TSU.id_revision, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?evidencia, M2M_rt?false
    // BUG-O2M: -->> Apply ?categoria_material, M2M_rt?false
    // BUG-O2M: -->> Apply ?contacto_proveedor_estacion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :contacto_proveedor_estacion.id_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :contacto_proveedor_estacion.id_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :contacto_proveedor_estacion.email_usuario, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :contacto_proveedor_estacion.clave_proveedor, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?grafico_producto, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :grafico_producto.id_producto, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :grafico_producto.id_grafico, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?grafico_producto_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?proveedor_region, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :proveedor_region.id_region, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :proveedor_region.clave_proveedor, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?proveedor_region_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?matriz, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :matriz.id_tipo_equipo_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :matriz.cve_kit_master, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :matriz.id_producto, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?archivo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :archivo.id_proceso, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Material_TSU, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Material_TSU.numero_parte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Material_TSU.id_tsu, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?vuelo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :vuelo.id_estacion_destino, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :vuelo.id_estacion_origen, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :vuelo.id_compania, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :vuelo.id_tipo_cabina, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?responsable_estacion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :responsable_estacion.id_empleado_jefe, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :responsable_estacion.id_area, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :responsable_estacion.id_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :responsable_estacion.id_empleado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?respuesta_evidencia, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :respuesta_evidencia.id_respuesta, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :respuesta_evidencia.id_evidencia, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?respuesta_evidencia_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?region, M2M_rt?false
    // BUG-O2M: -->> Apply ?potencial, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.id_TipoCiclo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.id_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.id_noCicloTemporada, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.id_codigo_servicio, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.id_estatusPotencial, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.clave_proveedor, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :potencial.id_clase, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Comunicado, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Comunicado.estatus_comunicado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Comunicado.id_area, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Comunicado.id_idioma, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Comunicado.id_empleado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Comunicado.id_tema, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Comunicado.accion_comunicado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?empleado, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :empleado.email_usuario, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :empleado.id_area, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :empleado.id_compania, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :empleado.id_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?horario, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :horario.id_region, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?action_form, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :action_form.id_opcion_menu, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?perfil, M2M_rt?false
    // BUG-O2M: -->> Apply ?Horario_Region, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Horario_Region.id_region, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?equipamiento_fijo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :equipamiento_fijo.id_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?tema_comunicado, M2M_rt?false
    // BUG-O2M: -->> Apply ?proveedor_estacion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :proveedor_estacion.clave_proveedor, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :proveedor_estacion.id_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?matriz_detalle, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :matriz_detalle.numero_parte, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :matriz_detalle.id_matriz, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?comunicado_areas, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :comunicado_areas.id_area, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :comunicado_areas.id_comunicado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?bitacora_comunicado, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :bitacora_comunicado.id_contacto_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :bitacora_comunicado.id_comunicado, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :bitacora_comunicado.id_proveedor_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Asignacion_Servicio_Duracion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio_Duracion.id_asignacion_servicio, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio_Duracion.id_codigo_servicio, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio_Duracion.id_duracion_vuelos, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Asignacion_Servicio_Duracion.id_horario_region, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?acomodo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo.id_modelo_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo.id_estacion_destino, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo.id_idioma, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo.id_acomodo_detalle, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :acomodo.id_estacion_origen, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?Proceso, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :Proceso.id_idioma, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :Proceso.tipo_proceso, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?criterio_irregularidad, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :criterio_irregularidad.estatus, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?proveedor_estacion_tipo_proveedor, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :proveedor_estacion_tipo_proveedor.id_proveedor_estacion, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :proveedor_estacion_tipo_proveedor.id_tipo_proveedor, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?proveedor_estacion_tipo_proveedor_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?grafico, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :grafico.id_modelo_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :grafico.id_idioma, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?tipo_proveedor, M2M_rt?false
    // BUG-O2M: -->> Apply ?ciclo, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :ciclo.id_TipoCiclo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :ciclo.clave_proveedor, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :ciclo.id_region, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :ciclo.id_estacion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :ciclo.id_origenVuelo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?multimedio, M2M_rt?false
    // BUG-O2M: -->> Apply ?Compania, M2M_rt?false
    // BUG-O2M: -->> Apply ?avion, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :avion.id_tipo_equipo_avion, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :avion.id_conectividad, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?tabla_abordamiento, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :tabla_abordamiento.id_origen_vuelo, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :tabla_abordamiento.id_estatus_abordamiento, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :tabla_abordamiento.id_codigo_servicio, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :tabla_abordamiento.id_tsu, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?clase, M2M_rt?false
    // BUG-O2M: -->> Apply ?producto, M2M_rt?false
    // BUG-O2M: -->> Apply ?material_kit_master, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :material_kit_master.id_unidad_medida, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :material_kit_master.numero_parte, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :material_kit_master.cve_kit_master, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :material_kit_master.id_instrucciones_nacionales, isPK?false, cfk_fTable is null?false
    // BUG-O2M: [responsable_producto]	-->> FK :material_kit_master.id_instrucciones_internac, isPK?false, cfk_fTable is null?false
    // BUG-O2M: -->> Apply ?material_kit_master_P_K, M2M_rt?false
    // BUG-O2M: -->> Apply ?parametro, M2M_rt?false
    // BUG-O2M: -->> Apply ?reporte_configuracion_reporte, M2M_rt?false
    // BUG-O2M: [responsable_producto]	-->> FK :reporte_configuracion_reporte.id_configuracion_reporte, isPK?true, cfk_fTable is null?true
    // BUG-O2M: [responsable_producto]	-->> FK :reporte_configuracion_reporte.id_reporte, isPK?true, cfk_fTable is null?true
    // BUG-O2M: -->> Apply ?reporte_configuracion_reporte_P_K, M2M_rt?false
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(responsableEstacion).hashCode();
		hash += String.valueOf(tipoProductoReporte).hashCode();
		hash += String.valueOf(responsableProductoPK).hashCode();
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
        if (!(o instanceof ResponsableProducto)) {
            return false;
        }		
		ResponsableProducto other = (ResponsableProducto ) o;
		if (!Objects.equals(this.responsableEstacion, other.responsableEstacion)) { return false; }		
		if (!Objects.equals(this.tipoProductoReporte, other.tipoProductoReporte)) { return false; }		
		if (!Objects.equals(this.responsableProductoPK, other.responsableProductoPK)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ResponsableProducto{");
		sb.append("responsableEstacion" ).append("=").append(responsableEstacion).append("|");
		sb.append("tipoProductoReporte" ).append("=").append(tipoProductoReporte).append("|");
		sb.append("responsableProductoPK" ).append("=").append(responsableProductoPK).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();

		return sb.toString().trim();
	}

}
