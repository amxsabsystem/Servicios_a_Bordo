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
 * Class for mapping JPA Embedable PK of Table avion_sistema_entretenimiento_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class AvionSistemaEntretenimientoPK implements java.io.Serializable {
    private static final long serialVersionUID = 63147134;
    
    @Basic(optional = false)
    @Column(name = "ID_AVION", nullable= false)
    private Integer idAvion;
    
    @Basic(optional = false)
    @Column(name = "ID_SISTEMA_ENTRETENIMIENTO", nullable= false)
    private Integer idSistemaEntretenimiento;

    /** 
     * Default Constructor
     */
    public AvionSistemaEntretenimientoPK() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAvion() { return this.idAvion;}
    public void setIdAvion(Integer v) { this.idAvion = v; }
    
    public Integer getIdSistemaEntretenimiento() { return this.idSistemaEntretenimiento;}
    public void setIdSistemaEntretenimiento(Integer v) { this.idSistemaEntretenimiento = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idAvion).hashCode();
		hash += String.valueOf(idSistemaEntretenimiento).hashCode();
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
        if (!(o instanceof AvionSistemaEntretenimientoPK)) {
            return false;
        }		
		AvionSistemaEntretenimientoPK other = (AvionSistemaEntretenimientoPK ) o;
		if (!Objects.equals(this.idAvion, other.idAvion)) { return false; }		
		if (!Objects.equals(this.idSistemaEntretenimiento, other.idSistemaEntretenimiento)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("AvionSistemaEntretenimientoPK{");
		sb.append("idAvion" ).append("=").append(idAvion).append("|");
		sb.append("idSistemaEntretenimiento" ).append("=").append(idSistemaEntretenimiento).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
