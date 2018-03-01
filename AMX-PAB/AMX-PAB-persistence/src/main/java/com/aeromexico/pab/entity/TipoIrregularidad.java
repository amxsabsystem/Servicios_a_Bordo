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
 * Class for mapping JPA Entity of Table tipo_irregularidad.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tipo_irregularidad")
@NamedQueries({
    @NamedQuery(name = "TipoIrregularidad.findAll", query = "SELECT t FROM TipoIrregularidad t")
    , @NamedQuery(name = "TipoIrregularidad.countAll", query = "SELECT COUNT(t) FROM TipoIrregularidad t")
    , @NamedQuery(name = "TipoIrregularidad.findByIdTipoIrregularidad", query = "SELECT t FROM TipoIrregularidad t WHERE t.idTipoIrregularidad = :idTipoIrregularidad")
    , @NamedQuery(name = "TipoIrregularidad.findByDescripcion", query = "SELECT t FROM TipoIrregularidad t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoIrregularidad.findByestatus", query = "SELECT t FROM TipoIrregularidad t WHERE t.estatus = :estatus")
})
public class      TipoIrregularidad 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1388054138;
    
    /**
    * The 'id tipo irregularidad' Maps to COLUMN 'id_tipo_irregularidad'
    */
    
    @Id
    @Column(name = "ID_TIPO_IRREGULARIDAD" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTipoIrregularidad;
    
    /**
    * The 'descripcion' Maps to COLUMN 'descripcion'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION" , length=30, nullable=false)
    private String descripcion;
    
    /**
    * The 'estatus' Maps to COLUMN 'estatus'
    */
    
    @JoinColumn(name = "ESTATUS" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro estatus;
    /** 
    * Map the relation to configuracion_reporte table where has id_tipo_irregularidad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIrregularidad")
    private List<ConfiguracionReporte> configuracionReporteHasTipoIrregularidadList;
    
    /** 
    * Map the relation to configuracion_reporte_detalle table where has id_tipo_irregularidad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoIrregularidad")
    private List<ConfiguracionReporteDetalle> configuracionReporteDetalleHasTipoIrregularidadList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TipoIrregularidad() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTipoIrregularidad() { return this.idTipoIrregularidad;}
    public void setIdTipoIrregularidad(Integer v) { this.idTipoIrregularidad = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
    public Parametro getEstatus(){ return this.estatus ; }
    public void setEstatus(Parametro x){ this.estatus = x; }
    
    // O2M <*>    
    public List<ConfiguracionReporte> getConfiguracionReporteHasTipoIrregularidadList(){ return this.configuracionReporteHasTipoIrregularidadList; }
    public void setConfiguracionReporteHasTipoIrregularidadList(List<ConfiguracionReporte> v){ this.configuracionReporteHasTipoIrregularidadList = v; }
    
    public List<ConfiguracionReporteDetalle> getConfiguracionReporteDetalleHasTipoIrregularidadList(){ return this.configuracionReporteDetalleHasTipoIrregularidadList; }
    public void setConfiguracionReporteDetalleHasTipoIrregularidadList(List<ConfiguracionReporteDetalle> v){ this.configuracionReporteDetalleHasTipoIrregularidadList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTipoIrregularidad).hashCode();
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
        if (!(o instanceof TipoIrregularidad)) {
            return false;
        }		
		TipoIrregularidad other = (TipoIrregularidad ) o;
		if (!Objects.equals(this.idTipoIrregularidad, other.idTipoIrregularidad)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
		if (!Objects.equals(this.estatus, other.estatus)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("TipoIrregularidad{");
		sb.append("idTipoIrregularidad" ).append("=").append(idTipoIrregularidad).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
		sb.append("estatus" ).append("=").append(estatus).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idTipoIrregularidad).append(" ");

		return sb.toString().trim();
	}

}
