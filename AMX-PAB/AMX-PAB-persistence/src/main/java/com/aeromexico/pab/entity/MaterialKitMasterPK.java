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
 * Class for mapping JPA Embedable PK of Table material_kit_master_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class MaterialKitMasterPK implements java.io.Serializable {
    private static final long serialVersionUID = 1866660832;
    
    @Basic(optional = false)
    @Column(name = "NUMERO_PARTE", nullable= false)
    private String numeroParte;
    
    @Basic(optional = false)
    @Column(name = "CVE_KIT_MASTER", nullable= false)
    private String cveKitMaster;

    /** 
     * Default Constructor
     */
    public MaterialKitMasterPK() {
    }
    
    /**
     * Getters and Setters
     */
    public String getNumeroParte() { return this.numeroParte;}
    public void setNumeroParte(String v) { this.numeroParte = v; }
    
    public String getCveKitMaster() { return this.cveKitMaster;}
    public void setCveKitMaster(String v) { this.cveKitMaster = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(numeroParte).hashCode();
		hash += String.valueOf(cveKitMaster).hashCode();
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
        if (!(o instanceof MaterialKitMasterPK)) {
            return false;
        }		
		MaterialKitMasterPK other = (MaterialKitMasterPK ) o;
		if (!Objects.equals(this.numeroParte, other.numeroParte)) { return false; }		
		if (!Objects.equals(this.cveKitMaster, other.cveKitMaster)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("MaterialKitMasterPK{");
		sb.append("numeroParte" ).append("=").append(numeroParte).append("|");
		sb.append("cveKitMaster" ).append("=").append(cveKitMaster).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
