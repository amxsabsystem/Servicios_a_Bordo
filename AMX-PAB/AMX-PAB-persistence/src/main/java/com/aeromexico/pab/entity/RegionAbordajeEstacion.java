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
 * Class for mapping JPA Entity of Table region_abordaje_estacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "region_abordaje_estacion")
@NamedQueries({
    @NamedQuery(name = "RegionAbordajeEstacion.findAll", query = "SELECT r FROM RegionAbordajeEstacion r")
    , @NamedQuery(name = "RegionAbordajeEstacion.countAll", query = "SELECT COUNT(r) FROM RegionAbordajeEstacion r")
    , @NamedQuery(name = "RegionAbordajeEstacion.findByIdDetalleEstacionAbordaje", query = "SELECT r FROM RegionAbordajeEstacion r WHERE r.idDetalleEstacionAbordaje = :idDetalleEstacionAbordaje")
    , @NamedQuery(name = "RegionAbordajeEstacion.findByregionAbordaje", query = "SELECT r FROM RegionAbordajeEstacion r WHERE r.regionAbordaje = :regionAbordaje")
    , @NamedQuery(name = "RegionAbordajeEstacion.findByestacion", query = "SELECT r FROM RegionAbordajeEstacion r WHERE r.estacion = :estacion")
})
public class      RegionAbordajeEstacion 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1414027016;
    
    /**
    * The 'id detalle estacion abordaje' Maps to COLUMN 'id_detalle_estacion_abordaje'
    */
    
    @Id
    @Column(name = "ID_DETALLE_ESTACION_ABORDAJE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDetalleEstacionAbordaje;
    
    /**
    * The 'id region abordaje' Maps to COLUMN 'id_region_abordaje'
    */
    
    @JoinColumn(name = "ID_REGION_ABORDAJE" , referencedColumnName = "ID_REGION_ABORDAJE")
    @ManyToOne(optional = true )
    private RegionAbordaje regionAbordaje;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @JoinColumn(name = "ID_ESTACION" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacion;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public RegionAbordajeEstacion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdDetalleEstacionAbordaje() { return this.idDetalleEstacionAbordaje;}
    public void setIdDetalleEstacionAbordaje(Integer v) { this.idDetalleEstacionAbordaje = v; }
    
    public RegionAbordaje getRegionAbordaje(){ return this.regionAbordaje ; }
    public void setRegionAbordaje(RegionAbordaje x){ this.regionAbordaje = x; }
    
    public Estacion getEstacion(){ return this.estacion ; }
    public void setEstacion(Estacion x){ this.estacion = x; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idDetalleEstacionAbordaje).hashCode();
		hash += String.valueOf(regionAbordaje).hashCode();
		hash += String.valueOf(estacion).hashCode();
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
        if (!(o instanceof RegionAbordajeEstacion)) {
            return false;
        }		
		RegionAbordajeEstacion other = (RegionAbordajeEstacion ) o;
		if (!Objects.equals(this.idDetalleEstacionAbordaje, other.idDetalleEstacionAbordaje)) { return false; }		
		if (!Objects.equals(this.regionAbordaje, other.regionAbordaje)) { return false; }		
		if (!Objects.equals(this.estacion, other.estacion)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("RegionAbordajeEstacion{");
		sb.append("idDetalleEstacionAbordaje" ).append("=").append(idDetalleEstacionAbordaje).append("|");
		sb.append("regionAbordaje" ).append("=").append(regionAbordaje).append("|");
		sb.append("estacion" ).append("=").append(estacion).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idDetalleEstacionAbordaje).append(" ");

		return sb.toString().trim();
	}

}
