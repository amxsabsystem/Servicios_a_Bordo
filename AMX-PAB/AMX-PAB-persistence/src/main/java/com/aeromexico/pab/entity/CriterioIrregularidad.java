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
 * Class for mapping JPA Entity of Table criterio_irregularidad.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "criterio_irregularidad")
@NamedQueries({
    @NamedQuery(name = "CriterioIrregularidad.findAll", query = "SELECT c FROM CriterioIrregularidad c")
    , @NamedQuery(name = "CriterioIrregularidad.countAll", query = "SELECT COUNT(c) FROM CriterioIrregularidad c")
    , @NamedQuery(name = "CriterioIrregularidad.findByIdCriterioIrregularidad", query = "SELECT c FROM CriterioIrregularidad c WHERE c.idCriterioIrregularidad = :idCriterioIrregularidad")
    , @NamedQuery(name = "CriterioIrregularidad.findByDescripcion", query = "SELECT c FROM CriterioIrregularidad c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CriterioIrregularidad.findByestatus", query = "SELECT c FROM CriterioIrregularidad c WHERE c.estatus = :estatus")
})
public class      CriterioIrregularidad 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1474482689;
    
    /**
    * The 'id criterio irregularidad' Maps to COLUMN 'id_criterio_irregularidad'
    */
    
    @Id
    @Column(name = "ID_CRITERIO_IRREGULARIDAD" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCriterioIrregularidad;
    
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
    * Map the relation to configuracion_reporte table where has id_criterio_irregularidad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterioIrregularidad")
    private List<ConfiguracionReporte> configuracionReporteHasCriterioIrregularidadList;
    
    /** 
    * Map the relation to configuracion_reporte_detalle table where has id_criterio_irregularidad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterioIrregularidad")
    private List<ConfiguracionReporteDetalle> configuracionReporteDetalleHasCriterioIrregularidadList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public CriterioIrregularidad() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdCriterioIrregularidad() { return this.idCriterioIrregularidad;}
    public void setIdCriterioIrregularidad(Integer v) { this.idCriterioIrregularidad = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
    public Parametro getEstatus(){ return this.estatus ; }
    public void setEstatus(Parametro x){ this.estatus = x; }
    
    // O2M <*>    
    public List<ConfiguracionReporte> getConfiguracionReporteHasCriterioIrregularidadList(){ return this.configuracionReporteHasCriterioIrregularidadList; }
    public void setConfiguracionReporteHasCriterioIrregularidadList(List<ConfiguracionReporte> v){ this.configuracionReporteHasCriterioIrregularidadList = v; }
    
    public List<ConfiguracionReporteDetalle> getConfiguracionReporteDetalleHasCriterioIrregularidadList(){ return this.configuracionReporteDetalleHasCriterioIrregularidadList; }
    public void setConfiguracionReporteDetalleHasCriterioIrregularidadList(List<ConfiguracionReporteDetalle> v){ this.configuracionReporteDetalleHasCriterioIrregularidadList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idCriterioIrregularidad).hashCode();
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
        if (!(o instanceof CriterioIrregularidad)) {
            return false;
        }		
		CriterioIrregularidad other = (CriterioIrregularidad ) o;
		if (!Objects.equals(this.idCriterioIrregularidad, other.idCriterioIrregularidad)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
		if (!Objects.equals(this.estatus, other.estatus)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("CriterioIrregularidad{");
		sb.append("idCriterioIrregularidad" ).append("=").append(idCriterioIrregularidad).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
		sb.append("estatus" ).append("=").append(estatus).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idCriterioIrregularidad).append(" ");

		return sb.toString().trim();
	}

}
