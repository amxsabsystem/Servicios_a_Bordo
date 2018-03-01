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
 * Class for mapping JPA Entity of Table detalle_region_abordaje.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "detalle_region_abordaje")
@NamedQueries({
    @NamedQuery(name = "DetalleRegionAbordaje.findAll", query = "SELECT d FROM DetalleRegionAbordaje d")
    , @NamedQuery(name = "DetalleRegionAbordaje.countAll", query = "SELECT COUNT(d) FROM DetalleRegionAbordaje d")
    , @NamedQuery(name = "DetalleRegionAbordaje.findByTodasEstaciones", query = "SELECT d FROM DetalleRegionAbordaje d WHERE d.todasEstaciones = :todasEstaciones")
    , @NamedQuery(name = "DetalleRegionAbordaje.findBytablaAbordamiento", query = "SELECT d FROM DetalleRegionAbordaje d WHERE d.tablaAbordamiento = :tablaAbordamiento")
    , @NamedQuery(name = "DetalleRegionAbordaje.findByregion", query = "SELECT d FROM DetalleRegionAbordaje d WHERE d.region = :region")
    , @NamedQuery(name = "DetalleRegionAbordaje.findByDetalleRegionAbordajePK", query = "SELECT d FROM DetalleRegionAbordaje d WHERE d.detalleRegionAbordajePK = :detalleRegionAbordajePK")
})
public class      DetalleRegionAbordaje 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -325966753;
    
    /**
    * The 'todas estaciones' Maps to COLUMN 'todas_estaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "TODAS_ESTACIONES" , nullable=true)
    private Short todasEstaciones;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @JoinColumn(name = "ID_TABLA_ABORDAMIENTO" , referencedColumnName = "ID_TABLA_ABORDAMIENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private TablaAbordamiento tablaAbordamiento;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @JoinColumn(name = "ID_REGION" , referencedColumnName = "ID_REGION", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private Region region;
    
    /**
    * The 'detalle region abordaje P K' Maps to COLUMN 'detalle_region_abordaje_P_K'
    */
    
    @EmbeddedId
    private DetalleRegionAbordajePK detalleRegionAbordajePK;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public DetalleRegionAbordaje() {
    }
    
    /**
     * Getters and Setters
     */
    public Short getTodasEstaciones() { return this.todasEstaciones;}
    public void setTodasEstaciones(Short v) { this.todasEstaciones = v; }
    
    public TablaAbordamiento getTablaAbordamiento(){ return this.tablaAbordamiento ; }
    public void setTablaAbordamiento(TablaAbordamiento x){ this.tablaAbordamiento = x; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
    public DetalleRegionAbordajePK getDetalleRegionAbordajePK() { return this.detalleRegionAbordajePK;}
    public void setDetalleRegionAbordajePK(DetalleRegionAbordajePK v) { this.detalleRegionAbordajePK = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(todasEstaciones).hashCode();
		hash += String.valueOf(tablaAbordamiento).hashCode();
		hash += String.valueOf(region).hashCode();
		hash += String.valueOf(detalleRegionAbordajePK).hashCode();
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
        if (!(o instanceof DetalleRegionAbordaje)) {
            return false;
        }		
		DetalleRegionAbordaje other = (DetalleRegionAbordaje ) o;
		if (!Objects.equals(this.todasEstaciones, other.todasEstaciones)) { return false; }		
		if (!Objects.equals(this.tablaAbordamiento, other.tablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.region, other.region)) { return false; }		
		if (!Objects.equals(this.detalleRegionAbordajePK, other.detalleRegionAbordajePK)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("DetalleRegionAbordaje{");
		sb.append("todasEstaciones" ).append("=").append(todasEstaciones).append("|");
		sb.append("tablaAbordamiento" ).append("=").append(tablaAbordamiento).append("|");
		sb.append("region" ).append("=").append(region).append("|");
		sb.append("detalleRegionAbordajePK" ).append("=").append(detalleRegionAbordajePK).append("|");
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
