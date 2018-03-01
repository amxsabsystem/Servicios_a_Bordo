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
 * Class for mapping JPA Entity of Table configuracion_reporte.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "configuracion_reporte")
@NamedQueries({
    @NamedQuery(name = "ConfiguracionReporte.findAll", query = "SELECT c FROM ConfiguracionReporte c")
    , @NamedQuery(name = "ConfiguracionReporte.countAll", query = "SELECT COUNT(c) FROM ConfiguracionReporte c")
    , @NamedQuery(name = "ConfiguracionReporte.findByIdConfiguracionReporte", query = "SELECT c FROM ConfiguracionReporte c WHERE c.idConfiguracionReporte = :idConfiguracionReporte")
    , @NamedQuery(name = "ConfiguracionReporte.findBytipoProductoReporte", query = "SELECT c FROM ConfiguracionReporte c WHERE c.tipoProductoReporte = :tipoProductoReporte")
    , @NamedQuery(name = "ConfiguracionReporte.findBytipoIrregularidad", query = "SELECT c FROM ConfiguracionReporte c WHERE c.tipoIrregularidad = :tipoIrregularidad")
    , @NamedQuery(name = "ConfiguracionReporte.findBycriterioIrregularidad", query = "SELECT c FROM ConfiguracionReporte c WHERE c.criterioIrregularidad = :criterioIrregularidad")
    , @NamedQuery(name = "ConfiguracionReporte.findBystatus", query = "SELECT c FROM ConfiguracionReporte c WHERE c.status = :status")
})
public class      ConfiguracionReporte 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 1464081433;
    
    /**
    * The 'id configuracion reporte' Maps to COLUMN 'id_configuracion_reporte'
    */
    
    @Id
    @Column(name = "ID_CONFIGURACION_REPORTE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idConfiguracionReporte;
    
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
    
    /**
    * The 'status' Maps to COLUMN 'status'
    */
    
    @JoinColumn(name = "STATUS" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro status;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ConfiguracionReporte() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdConfiguracionReporte() { return this.idConfiguracionReporte;}
    public void setIdConfiguracionReporte(Integer v) { this.idConfiguracionReporte = v; }
    
    public TipoProductoReporte getTipoProductoReporte(){ return this.tipoProductoReporte ; }
    public void setTipoProductoReporte(TipoProductoReporte x){ this.tipoProductoReporte = x; }
    
    public TipoIrregularidad getTipoIrregularidad(){ return this.tipoIrregularidad ; }
    public void setTipoIrregularidad(TipoIrregularidad x){ this.tipoIrregularidad = x; }
    
    public CriterioIrregularidad getCriterioIrregularidad(){ return this.criterioIrregularidad ; }
    public void setCriterioIrregularidad(CriterioIrregularidad x){ this.criterioIrregularidad = x; }
    
    public Parametro getStatus(){ return this.status ; }
    public void setStatus(Parametro x){ this.status = x; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idConfiguracionReporte).hashCode();
		hash += String.valueOf(tipoProductoReporte).hashCode();
		hash += String.valueOf(tipoIrregularidad).hashCode();
		hash += String.valueOf(criterioIrregularidad).hashCode();
		hash += String.valueOf(status).hashCode();
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
        if (!(o instanceof ConfiguracionReporte)) {
            return false;
        }		
		ConfiguracionReporte other = (ConfiguracionReporte ) o;
		if (!Objects.equals(this.idConfiguracionReporte, other.idConfiguracionReporte)) { return false; }		
		if (!Objects.equals(this.tipoProductoReporte, other.tipoProductoReporte)) { return false; }		
		if (!Objects.equals(this.tipoIrregularidad, other.tipoIrregularidad)) { return false; }		
		if (!Objects.equals(this.criterioIrregularidad, other.criterioIrregularidad)) { return false; }		
		if (!Objects.equals(this.status, other.status)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ConfiguracionReporte{");
		sb.append("idConfiguracionReporte" ).append("=").append(idConfiguracionReporte).append("|");
		sb.append("tipoProductoReporte" ).append("=").append(tipoProductoReporte).append("|");
		sb.append("tipoIrregularidad" ).append("=").append(tipoIrregularidad).append("|");
		sb.append("criterioIrregularidad" ).append("=").append(criterioIrregularidad).append("|");
		sb.append("status" ).append("=").append(status).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idConfiguracionReporte).append(" ");

		return sb.toString().trim();
	}

}
