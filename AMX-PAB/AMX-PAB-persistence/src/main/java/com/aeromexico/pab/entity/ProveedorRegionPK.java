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
 * Class for mapping JPA Embedable PK of Table proveedor_region_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class ProveedorRegionPK implements java.io.Serializable {
    private static final long serialVersionUID = 443308702;
    
    @Basic(optional = false)
    @Column(name = "CLAVE_PROVEEDOR", nullable= false)
    private String claveProveedor;
    
    @Basic(optional = false)
    @Column(name = "ID_REGION", nullable= false)
    private Integer idRegion;

    /** 
     * Default Constructor
     */
    public ProveedorRegionPK() {
    }
    
    /**
     * Getters and Setters
     */
    public String getClaveProveedor() { return this.claveProveedor;}
    public void setClaveProveedor(String v) { this.claveProveedor = v; }
    
    public Integer getIdRegion() { return this.idRegion;}
    public void setIdRegion(Integer v) { this.idRegion = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(claveProveedor).hashCode();
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
        if (!(o instanceof ProveedorRegionPK)) {
            return false;
        }		
		ProveedorRegionPK other = (ProveedorRegionPK ) o;
		if (!Objects.equals(this.claveProveedor, other.claveProveedor)) { return false; }		
		if (!Objects.equals(this.idRegion, other.idRegion)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ProveedorRegionPK{");
		sb.append("claveProveedor" ).append("=").append(claveProveedor).append("|");
		sb.append("idRegion" ).append("=").append(idRegion).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
