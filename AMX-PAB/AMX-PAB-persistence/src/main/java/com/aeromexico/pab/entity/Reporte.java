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
 * Class for mapping JPA Entity of Table reporte.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "reporte")
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r")
    , @NamedQuery(name = "Reporte.countAll", query = "SELECT COUNT(r) FROM Reporte r")
    , @NamedQuery(name = "Reporte.findByIdReporte", query = "SELECT r FROM Reporte r WHERE r.idReporte = :idReporte")
    , @NamedQuery(name = "Reporte.findByidTipoReporte", query = "SELECT r FROM Reporte r WHERE r.idTipoReporte = :idTipoReporte")
    , @NamedQuery(name = "Reporte.findByvuelo", query = "SELECT r FROM Reporte r WHERE r.vuelo = :vuelo")
    , @NamedQuery(name = "Reporte.findByclase", query = "SELECT r FROM Reporte r WHERE r.clase = :clase")
    , @NamedQuery(name = "Reporte.findByareaResponsable", query = "SELECT r FROM Reporte r WHERE r.areaResponsable = :areaResponsable")
    , @NamedQuery(name = "Reporte.findBytipoProductoReporte", query = "SELECT r FROM Reporte r WHERE r.tipoProductoReporte = :tipoProductoReporte")
    , @NamedQuery(name = "Reporte.findByidResponsableAmx", query = "SELECT r FROM Reporte r WHERE r.idResponsableAmx = :idResponsableAmx")
    , @NamedQuery(name = "Reporte.findByidResponsableFinalAmx", query = "SELECT r FROM Reporte r WHERE r.idResponsableFinalAmx = :idResponsableFinalAmx")
    , @NamedQuery(name = "Reporte.findByidResponsableProveedorEstacion", query = "SELECT r FROM Reporte r WHERE r.idResponsableProveedorEstacion = :idResponsableProveedorEstacion")
    , @NamedQuery(name = "Reporte.findByidResponsableProveedorEstacionFinal", query = "SELECT r FROM Reporte r WHERE r.idResponsableProveedorEstacionFinal = :idResponsableProveedorEstacionFinal")
    , @NamedQuery(name = "Reporte.findByidEstatusReporteActual", query = "SELECT r FROM Reporte r WHERE r.idEstatusReporteActual = :idEstatusReporteActual")
    , @NamedQuery(name = "Reporte.findByClaveReporte", query = "SELECT r FROM Reporte r WHERE r.claveReporte = :claveReporte")
    , @NamedQuery(name = "Reporte.findByFechaVuelo", query = "SELECT r FROM Reporte r WHERE r.fechaVuelo = :fechaVuelo")
    , @NamedQuery(name = "Reporte.findByDescripcion", query = "SELECT r FROM Reporte r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Reporte.findByStatus", query = "SELECT r FROM Reporte r WHERE r.status = :status")
    , @NamedQuery(name = "Reporte.findByUsuarioCreo", query = "SELECT r FROM Reporte r WHERE r.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Reporte.findByFechaCreo", query = "SELECT r FROM Reporte r WHERE r.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Reporte.findByUsuarioModifico", query = "SELECT r FROM Reporte r WHERE r.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Reporte.findByFechaModifico", query = "SELECT r FROM Reporte r WHERE r.fechaModifico = :fechaModifico")
})
public class      Reporte 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1094603185;
    
    /**
    * The 'id reporte' Maps to COLUMN 'id_reporte'
    */
    
    @Id
    @Column(name = "ID_REPORTE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idReporte;
    
    /**
    * The 'id tipo reporte' Maps to COLUMN 'id_tipo_reporte'
    */
    
    @JoinColumn(name = "ID_TIPO_REPORTE" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipoReporte;
    
    /**
    * The 'id vuelo' Maps to COLUMN 'id_vuelo'
    */
    
    @JoinColumn(name = "ID_VUELO" , referencedColumnName = "ID_VUELO")
    @ManyToOne(optional = false )
    private Vuelo vuelo;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'id area responsable' Maps to COLUMN 'id_area_responsable'
    */
    
    @JoinColumn(name = "ID_AREA_RESPONSABLE" , referencedColumnName = "ID_AREA")
    @ManyToOne(optional = true )
    private Area areaResponsable;
    
    /**
    * The 'id tipo producto reporte' Maps to COLUMN 'id_tipo_producto_reporte'
    */
    
    @JoinColumn(name = "ID_TIPO_PRODUCTO_REPORTE" , referencedColumnName = "ID_TIPO_PRODUCTO_REPORTE")
    @ManyToOne(optional = true )
    private TipoProductoReporte tipoProductoReporte;
    
    /**
    * The 'id responsable amx' Maps to COLUMN 'id_responsable_amx'
    */
    
    @JoinColumn(name = "ID_RESPONSABLE_AMX" , referencedColumnName = "ID_RESPONSABLE_ESTACION")
    @ManyToOne(optional = true )
    private ResponsableEstacion idResponsableAmx;
    
    /**
    * The 'id responsable final amx' Maps to COLUMN 'id_responsable_final_amx'
    */
    
    @JoinColumn(name = "ID_RESPONSABLE_FINAL_AMX" , referencedColumnName = "ID_RESPONSABLE_ESTACION")
    @ManyToOne(optional = true )
    private ResponsableEstacion idResponsableFinalAmx;
    
    /**
    * The 'id responsable proveedor estacion' Maps to COLUMN 'id_responsable_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_RESPONSABLE_PROVEEDOR_ESTACION" , referencedColumnName = "ID_PROVEEDOR_ESTACION")
    @ManyToOne(optional = true )
    private ProveedorEstacion idResponsableProveedorEstacion;
    
    /**
    * The 'id responsable proveedor estacion final' Maps to COLUMN 'id_responsable_proveedor_estacion_final'
    */
    
    @JoinColumn(name = "ID_RESPONSABLE_PROVEEDOR_ESTACION_FINAL" , referencedColumnName = "ID_PROVEEDOR_ESTACION")
    @ManyToOne(optional = true )
    private ProveedorEstacion idResponsableProveedorEstacionFinal;
    
    /**
    * The 'id estatus reporte actual' Maps to COLUMN 'id_estatus_reporte_actual'
    */
    
    @JoinColumn(name = "ID_ESTATUS_REPORTE_ACTUAL" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idEstatusReporteActual;
    
    /**
    * The 'clave reporte' Maps to COLUMN 'clave_reporte'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE_REPORTE" , length=20, nullable=false)
    private String claveReporte;
    
    /**
    * The 'fecha vuelo' Maps to COLUMN 'fecha_vuelo'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA_VUELO" , nullable=false)
    private java.sql.Date fechaVuelo;
    
    /**
    * The 'descripcion' Maps to COLUMN 'descripcion'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION" , length=150, nullable=false)
    private String descripcion;
    
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
    * Map the relation to evidencia table where has id_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporte")
    private List<Evidencia> evidenciaHasReporteList;
    
    /** 
    * Map the relation to configuracion_reporte_detalle table where has id_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporte")
    private List<ConfiguracionReporteDetalle> configuracionReporteDetalleHasReporteList;
    
    /** 
    * Map the relation to seguimiento table where has id_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporte")
    private List<Seguimiento> seguimientoHasReporteList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Reporte() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdReporte() { return this.idReporte;}
    public void setIdReporte(Integer v) { this.idReporte = v; }
    
    public Parametro getIdTipoReporte(){ return this.idTipoReporte ; }
    public void setIdTipoReporte(Parametro x){ this.idTipoReporte = x; }
    
    public Vuelo getVuelo(){ return this.vuelo ; }
    public void setVuelo(Vuelo x){ this.vuelo = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public Area getAreaResponsable(){ return this.areaResponsable ; }
    public void setAreaResponsable(Area x){ this.areaResponsable = x; }
    
    public TipoProductoReporte getTipoProductoReporte(){ return this.tipoProductoReporte ; }
    public void setTipoProductoReporte(TipoProductoReporte x){ this.tipoProductoReporte = x; }
    
    public ResponsableEstacion getIdResponsableAmx(){ return this.idResponsableAmx ; }
    public void setIdResponsableAmx(ResponsableEstacion x){ this.idResponsableAmx = x; }
    
    public ResponsableEstacion getIdResponsableFinalAmx(){ return this.idResponsableFinalAmx ; }
    public void setIdResponsableFinalAmx(ResponsableEstacion x){ this.idResponsableFinalAmx = x; }
    
    public ProveedorEstacion getIdResponsableProveedorEstacion(){ return this.idResponsableProveedorEstacion ; }
    public void setIdResponsableProveedorEstacion(ProveedorEstacion x){ this.idResponsableProveedorEstacion = x; }
    
    public ProveedorEstacion getIdResponsableProveedorEstacionFinal(){ return this.idResponsableProveedorEstacionFinal ; }
    public void setIdResponsableProveedorEstacionFinal(ProveedorEstacion x){ this.idResponsableProveedorEstacionFinal = x; }
    
    public Parametro getIdEstatusReporteActual(){ return this.idEstatusReporteActual ; }
    public void setIdEstatusReporteActual(Parametro x){ this.idEstatusReporteActual = x; }
    
    public String getClaveReporte() { return this.claveReporte;}
    public void setClaveReporte(String v) { this.claveReporte = v; }
    
    public java.sql.Date getFechaVuelo() { return this.fechaVuelo;}
    public void setFechaVuelo(java.sql.Date v) { this.fechaVuelo = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
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
    public List<Evidencia> getEvidenciaHasReporteList(){ return this.evidenciaHasReporteList; }
    public void setEvidenciaHasReporteList(List<Evidencia> v){ this.evidenciaHasReporteList = v; }
    
    public List<ConfiguracionReporteDetalle> getConfiguracionReporteDetalleHasReporteList(){ return this.configuracionReporteDetalleHasReporteList; }
    public void setConfiguracionReporteDetalleHasReporteList(List<ConfiguracionReporteDetalle> v){ this.configuracionReporteDetalleHasReporteList = v; }
    
    public List<Seguimiento> getSeguimientoHasReporteList(){ return this.seguimientoHasReporteList; }
    public void setSeguimientoHasReporteList(List<Seguimiento> v){ this.seguimientoHasReporteList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idReporte).hashCode();
		hash += String.valueOf(idTipoReporte).hashCode();
		hash += String.valueOf(vuelo).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(areaResponsable).hashCode();
		hash += String.valueOf(tipoProductoReporte).hashCode();
		hash += String.valueOf(idResponsableAmx).hashCode();
		hash += String.valueOf(idResponsableFinalAmx).hashCode();
		hash += String.valueOf(idResponsableProveedorEstacion).hashCode();
		hash += String.valueOf(idResponsableProveedorEstacionFinal).hashCode();
		hash += String.valueOf(idEstatusReporteActual).hashCode();
		hash += String.valueOf(claveReporte).hashCode();
		hash += String.valueOf(fechaVuelo).hashCode();
		hash += String.valueOf(descripcion).hashCode();
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
        if (!(o instanceof Reporte)) {
            return false;
        }		
		Reporte other = (Reporte ) o;
		if (!Objects.equals(this.idReporte, other.idReporte)) { return false; }		
		if (!Objects.equals(this.idTipoReporte, other.idTipoReporte)) { return false; }		
		if (!Objects.equals(this.vuelo, other.vuelo)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.areaResponsable, other.areaResponsable)) { return false; }		
		if (!Objects.equals(this.tipoProductoReporte, other.tipoProductoReporte)) { return false; }		
		if (!Objects.equals(this.idResponsableAmx, other.idResponsableAmx)) { return false; }		
		if (!Objects.equals(this.idResponsableFinalAmx, other.idResponsableFinalAmx)) { return false; }		
		if (!Objects.equals(this.idResponsableProveedorEstacion, other.idResponsableProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.idResponsableProveedorEstacionFinal, other.idResponsableProveedorEstacionFinal)) { return false; }		
		if (!Objects.equals(this.idEstatusReporteActual, other.idEstatusReporteActual)) { return false; }		
		if (!Objects.equals(this.claveReporte, other.claveReporte)) { return false; }		
		if (!Objects.equals(this.fechaVuelo, other.fechaVuelo)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
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
		sb.append("Reporte{");
		sb.append("idReporte" ).append("=").append(idReporte).append("|");
		sb.append("idTipoReporte" ).append("=").append(idTipoReporte).append("|");
		sb.append("vuelo" ).append("=").append(vuelo).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("areaResponsable" ).append("=").append(areaResponsable).append("|");
		sb.append("tipoProductoReporte" ).append("=").append(tipoProductoReporte).append("|");
		sb.append("idResponsableAmx" ).append("=").append(idResponsableAmx).append("|");
		sb.append("idResponsableFinalAmx" ).append("=").append(idResponsableFinalAmx).append("|");
		sb.append("idResponsableProveedorEstacion" ).append("=").append(idResponsableProveedorEstacion).append("|");
		sb.append("idResponsableProveedorEstacionFinal" ).append("=").append(idResponsableProveedorEstacionFinal).append("|");
		sb.append("idEstatusReporteActual" ).append("=").append(idEstatusReporteActual).append("|");
		sb.append("claveReporte" ).append("=").append(claveReporte).append("|");
		sb.append("fechaVuelo" ).append("=").append(fechaVuelo).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
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
		sb.append(" ").append(idReporte).append(" ");

		return sb.toString().trim();
	}

}
