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
 * Class for mapping JPA Entity of Table region_abordaje.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "region_abordaje")
@NamedQueries({
    @NamedQuery(name = "RegionAbordaje.findAll", query = "SELECT r FROM RegionAbordaje r")
    , @NamedQuery(name = "RegionAbordaje.countAll", query = "SELECT COUNT(r) FROM RegionAbordaje r")
    , @NamedQuery(name = "RegionAbordaje.findByIdRegionAbordaje", query = "SELECT r FROM RegionAbordaje r WHERE r.idRegionAbordaje = :idRegionAbordaje")
    , @NamedQuery(name = "RegionAbordaje.findBytablaAbordamiento", query = "SELECT r FROM RegionAbordaje r WHERE r.tablaAbordamiento = :tablaAbordamiento")
    , @NamedQuery(name = "RegionAbordaje.findByregion", query = "SELECT r FROM RegionAbordaje r WHERE r.region = :region")
    , @NamedQuery(name = "RegionAbordaje.findByTodasEstaciones", query = "SELECT r FROM RegionAbordaje r WHERE r.todasEstaciones = :todasEstaciones")
})
public class      RegionAbordaje 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 725327655;
    
    /**
    * The 'id region abordaje' Maps to COLUMN 'id_region_abordaje'
    */
    
    @Id
    @Column(name = "ID_REGION_ABORDAJE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRegionAbordaje;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @JoinColumn(name = "ID_TABLA_ABORDAMIENTO" , referencedColumnName = "ID_TABLA_ABORDAMIENTO")
    @ManyToOne(optional = false )
    private TablaAbordamiento tablaAbordamiento;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @JoinColumn(name = "ID_REGION" , referencedColumnName = "ID_REGION")
    @ManyToOne(optional = false )
    private Region region;
    
    /**
    * The 'todas estaciones' Maps to COLUMN 'todas_estaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "TODAS_ESTACIONES" , nullable=true)
    private Short todasEstaciones;
    /** 
    * Map the relation to region_abordaje_estacion table where has id_region_abordaje object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionAbordaje")
    private List<RegionAbordajeEstacion> regionAbordajeEstacionHasRegionAbordajeList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public RegionAbordaje() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdRegionAbordaje() { return this.idRegionAbordaje;}
    public void setIdRegionAbordaje(Integer v) { this.idRegionAbordaje = v; }
    
    public TablaAbordamiento getTablaAbordamiento(){ return this.tablaAbordamiento ; }
    public void setTablaAbordamiento(TablaAbordamiento x){ this.tablaAbordamiento = x; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
    public Short getTodasEstaciones() { return this.todasEstaciones;}
    public void setTodasEstaciones(Short v) { this.todasEstaciones = v; }
    
    // O2M <*>    
    public List<RegionAbordajeEstacion> getRegionAbordajeEstacionHasRegionAbordajeList(){ return this.regionAbordajeEstacionHasRegionAbordajeList; }
    public void setRegionAbordajeEstacionHasRegionAbordajeList(List<RegionAbordajeEstacion> v){ this.regionAbordajeEstacionHasRegionAbordajeList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idRegionAbordaje).hashCode();
		hash += String.valueOf(tablaAbordamiento).hashCode();
		hash += String.valueOf(region).hashCode();
		hash += String.valueOf(todasEstaciones).hashCode();
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
        if (!(o instanceof RegionAbordaje)) {
            return false;
        }		
		RegionAbordaje other = (RegionAbordaje ) o;
		if (!Objects.equals(this.idRegionAbordaje, other.idRegionAbordaje)) { return false; }		
		if (!Objects.equals(this.tablaAbordamiento, other.tablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.region, other.region)) { return false; }		
		if (!Objects.equals(this.todasEstaciones, other.todasEstaciones)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("RegionAbordaje{");
		sb.append("idRegionAbordaje" ).append("=").append(idRegionAbordaje).append("|");
		sb.append("tablaAbordamiento" ).append("=").append(tablaAbordamiento).append("|");
		sb.append("region" ).append("=").append(region).append("|");
		sb.append("todasEstaciones" ).append("=").append(todasEstaciones).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idRegionAbordaje).append(" ");

		return sb.toString().trim();
	}

}
