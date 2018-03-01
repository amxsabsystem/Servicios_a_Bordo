package com.aeromexico.pab.entity;

import java.util.Set;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// Hibernate Validator 5x is not compatible with validation-api 1.0.x
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class for mapping JPA Embedable PK of Table detalle_region_abordaje_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class DetalleRegionAbordajePK implements java.io.Serializable {
    private static final long serialVersionUID = 1413956668;
    
    @Basic(optional = false)
    @Column(name = "ID_TABLA_ABORDAMIENTO", nullable= false)
    private Integer idTablaAbordamiento;
    
    @Basic(optional = false)
    @Column(name = "ID_REGION", nullable= false)
    private Integer idRegion;

    /** 
     * Default Constructor
     */
    public DetalleRegionAbordajePK() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTablaAbordamiento() { return this.idTablaAbordamiento;}
    public void setIdTablaAbordamiento(Integer v) { this.idTablaAbordamiento = v; }
    
    public Integer getIdRegion() { return this.idRegion;}
    public void setIdRegion(Integer v) { this.idRegion = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTablaAbordamiento).hashCode();
		hash += String.valueOf(idRegion).hashCode();
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
        if (!(o instanceof DetalleRegionAbordajePK)) {
            return false;
        }		
		DetalleRegionAbordajePK other = (DetalleRegionAbordajePK ) o;
		if (!Objects.equals(this.idTablaAbordamiento, other.idTablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.idRegion, other.idRegion)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("DetalleRegionAbordajePK{");
		sb.append("idTablaAbordamiento" ).append("=").append(idTablaAbordamiento).append("|");
		sb.append("idRegion" ).append("=").append(idRegion).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
