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
 * Class for mapping JPA Entity of Table configuracion_reporte_detalle.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "configuracion_reporte_detalle")
@NamedQueries({
    @NamedQuery(name = "ConfiguracionReporteDetalle.findAll", query = "SELECT c FROM ConfiguracionReporteDetalle c")
    , @NamedQuery(name = "ConfiguracionReporteDetalle.countAll", query = "SELECT COUNT(c) FROM ConfiguracionReporteDetalle c")
    , @NamedQuery(name = "ConfiguracionReporteDetalle.findByIdReporteConfiguracionDetalle", query = "SELECT c FROM ConfiguracionReporteDetalle c WHERE c.idReporteConfiguracionDetalle = :idReporteConfiguracionDetalle")
    , @NamedQuery(name = "ConfiguracionReporteDetalle.findByreporte", query = "SELECT c FROM ConfiguracionReporteDetalle c WHERE c.reporte = :reporte")
    , @NamedQuery(name = "ConfiguracionReporteDetalle.findBytipoProductoReporte", query = "SELECT c FROM ConfiguracionReporteDetalle c WHERE c.tipoProductoReporte = :tipoProductoReporte")
    , @NamedQuery(name = "ConfiguracionReporteDetalle.findBytipoIrregularidad", query = "SELECT c FROM ConfiguracionReporteDetalle c WHERE c.tipoIrregularidad = :tipoIrregularidad")
    , @NamedQuery(name = "ConfiguracionReporteDetalle.findBycriterioIrregularidad", query = "SELECT c FROM ConfiguracionReporteDetalle c WHERE c.criterioIrregularidad = :criterioIrregularidad")
})
public class      ConfiguracionReporteDetalle 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1283483823;
    
    /**
    * The 'id reporte configuracion detalle' Maps to COLUMN 'id_reporte_configuracion_detalle'
    */
    
    @Id
    @Column(name = "ID_REPORTE_CONFIGURACION_DETALLE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idReporteConfiguracionDetalle;
    
    /**
    * The 'id reporte' Maps to COLUMN 'id_reporte'
    */
    
    @JoinColumn(name = "ID_REPORTE" , referencedColumnName = "ID_REPORTE")
    @ManyToOne(optional = false )
    private Reporte reporte;
    
    /**
    * The 'id tipo producto reporte' Maps to COLUMN 'id_tipo_producto_reporte'
    */
    
    @JoinColumn(name = "ID_TIPO_PRODUCTO_REPORTE" , referencedColumnName = "ID_TIPO_PRODUCTO_REPORTE")
    @ManyToOne(optional = false )
    private TipoProductoReporte tipoProductoReporte;
    
    /**
    * The 'id tipo irregularidad' Maps to COLUMN 'id_tipo_irregularidad'
    */
    
    @JoinColumn(name = "ID_TIPO_IRREGULARIDAD" , referencedColumnName = "ID_TIPO_IRREGULARIDAD")
    @ManyToOne(optional = false )
    private TipoIrregularidad tipoIrregularidad;
    
    /**
    * The 'id criterio irregularidad' Maps to COLUMN 'id_criterio_irregularidad'
    */
    
    @JoinColumn(name = "ID_CRITERIO_IRREGULARIDAD" , referencedColumnName = "ID_CRITERIO_IRREGULARIDAD")
    @ManyToOne(optional = false )
    private CriterioIrregularidad criterioIrregularidad;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ConfiguracionReporteDetalle() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdReporteConfiguracionDetalle() { return this.idReporteConfiguracionDetalle;}
    public void setIdReporteConfiguracionDetalle(Integer v) { this.idReporteConfiguracionDetalle = v; }
    
    public Reporte getReporte(){ return this.reporte ; }
    public void setReporte(Reporte x){ this.reporte = x; }
    
    public TipoProductoReporte getTipoProductoReporte(){ return this.tipoProductoReporte ; }
    public void setTipoProductoReporte(TipoProductoReporte x){ this.tipoProductoReporte = x; }
    
    public TipoIrregularidad getTipoIrregularidad(){ return this.tipoIrregularidad ; }
    public void setTipoIrregularidad(TipoIrregularidad x){ this.tipoIrregularidad = x; }
    
    public CriterioIrregularidad getCriterioIrregularidad(){ return this.criterioIrregularidad ; }
    public void setCriterioIrregularidad(CriterioIrregularidad x){ this.criterioIrregularidad = x; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idReporteConfiguracionDetalle).hashCode();
		hash += String.valueOf(reporte).hashCode();
		hash += String.valueOf(tipoProductoReporte).hashCode();
		hash += String.valueOf(tipoIrregularidad).hashCode();
		hash += String.valueOf(criterioIrregularidad).hashCode();
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
        if (!(o instanceof ConfiguracionReporteDetalle)) {
            return false;
        }		
		ConfiguracionReporteDetalle other = (ConfiguracionReporteDetalle ) o;
		if (!Objects.equals(this.idReporteConfiguracionDetalle, other.idReporteConfiguracionDetalle)) { return false; }		
		if (!Objects.equals(this.reporte, other.reporte)) { return false; }		
		if (!Objects.equals(this.tipoProductoReporte, other.tipoProductoReporte)) { return false; }		
		if (!Objects.equals(this.tipoIrregularidad, other.tipoIrregularidad)) { return false; }		
		if (!Objects.equals(this.criterioIrregularidad, other.criterioIrregularidad)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ConfiguracionReporteDetalle{");
		sb.append("idReporteConfiguracionDetalle" ).append("=").append(idReporteConfiguracionDetalle).append("|");
		sb.append("reporte" ).append("=").append(reporte).append("|");
		sb.append("tipoProductoReporte" ).append("=").append(tipoProductoReporte).append("|");
		sb.append("tipoIrregularidad" ).append("=").append(tipoIrregularidad).append("|");
		sb.append("criterioIrregularidad" ).append("=").append(criterioIrregularidad).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idReporteConfiguracionDetalle).append(" ");

		return sb.toString().trim();
	}

}
