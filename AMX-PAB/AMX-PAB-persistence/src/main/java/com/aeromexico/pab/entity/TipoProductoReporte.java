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
 * Class for mapping JPA Entity of Table tipo_producto_reporte.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tipo_producto_reporte")
@NamedQueries({
    @NamedQuery(name = "TipoProductoReporte.findAll", query = "SELECT t FROM TipoProductoReporte t")
    , @NamedQuery(name = "TipoProductoReporte.countAll", query = "SELECT COUNT(t) FROM TipoProductoReporte t")
    , @NamedQuery(name = "TipoProductoReporte.findByIdTipoProductoReporte", query = "SELECT t FROM TipoProductoReporte t WHERE t.idTipoProductoReporte = :idTipoProductoReporte")
    , @NamedQuery(name = "TipoProductoReporte.findByDescripcion", query = "SELECT t FROM TipoProductoReporte t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoProductoReporte.findByestatus", query = "SELECT t FROM TipoProductoReporte t WHERE t.estatus = :estatus")
})
public class      TipoProductoReporte 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1966618755;
    
    /**
    * The 'id tipo producto reporte' Maps to COLUMN 'id_tipo_producto_reporte'
    */
    
    @Id
    @Column(name = "ID_TIPO_PRODUCTO_REPORTE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTipoProductoReporte;
    
    /**
    * The 'descripcion' Maps to COLUMN 'descripcion'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION" , length=20, nullable=false)
    private String descripcion;
    
    /**
    * The 'estatus' Maps to COLUMN 'estatus'
    */
    
    @JoinColumn(name = "ESTATUS" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro estatus;
    /** 
    * Map the relation to configuracion_reporte table where has id_tipo_producto_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProductoReporte")
    private List<ConfiguracionReporte> configuracionReporteHasTipoProductoReporteList;
    
    /** 
    * Map the relation to reporte table where has id_tipo_producto_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProductoReporte")
    private List<Reporte> reporteHasTipoProductoReporteList;
    
    /** 
    * Map the relation to configuracion_reporte_detalle table where has id_tipo_producto_reporte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProductoReporte")
    private List<ConfiguracionReporteDetalle> configuracionReporteDetalleHasTipoProductoReporteList;
    

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "tipoProductoReporteList")
    private List<ResponsableEstacion> responsableEstacionList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TipoProductoReporte() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTipoProductoReporte() { return this.idTipoProductoReporte;}
    public void setIdTipoProductoReporte(Integer v) { this.idTipoProductoReporte = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
    public Parametro getEstatus(){ return this.estatus ; }
    public void setEstatus(Parametro x){ this.estatus = x; }
    
    // O2M <*>    
    public List<ConfiguracionReporte> getConfiguracionReporteHasTipoProductoReporteList(){ return this.configuracionReporteHasTipoProductoReporteList; }
    public void setConfiguracionReporteHasTipoProductoReporteList(List<ConfiguracionReporte> v){ this.configuracionReporteHasTipoProductoReporteList = v; }
    
    public List<Reporte> getReporteHasTipoProductoReporteList(){ return this.reporteHasTipoProductoReporteList; }
    public void setReporteHasTipoProductoReporteList(List<Reporte> v){ this.reporteHasTipoProductoReporteList = v; }
    
    public List<ConfiguracionReporteDetalle> getConfiguracionReporteDetalleHasTipoProductoReporteList(){ return this.configuracionReporteDetalleHasTipoProductoReporteList; }
    public void setConfiguracionReporteDetalleHasTipoProductoReporteList(List<ConfiguracionReporteDetalle> v){ this.configuracionReporteDetalleHasTipoProductoReporteList = v; }
    
	// M2M <*>
    public List<ResponsableEstacion> getResponsableEstacionList() { return this.responsableEstacionList; }
    public void setResponsableEstacionList(List<ResponsableEstacion>  v) { this.responsableEstacionList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTipoProductoReporte).hashCode();
		hash += String.valueOf(descripcion).hashCode();
		hash += String.valueOf(estatus).hashCode();
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
        if (!(o instanceof TipoProductoReporte)) {
            return false;
        }		
		TipoProductoReporte other = (TipoProductoReporte ) o;
		if (!Objects.equals(this.idTipoProductoReporte, other.idTipoProductoReporte)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
		if (!Objects.equals(this.estatus, other.estatus)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("TipoProductoReporte{");
		sb.append("idTipoProductoReporte" ).append("=").append(idTipoProductoReporte).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
		sb.append("estatus" ).append("=").append(estatus).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idTipoProductoReporte).append(" ");

		return sb.toString().trim();
	}

}
