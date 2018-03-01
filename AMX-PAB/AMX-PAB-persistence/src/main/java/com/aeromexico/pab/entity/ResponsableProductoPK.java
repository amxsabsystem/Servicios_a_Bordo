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
 * Class for mapping JPA Embedable PK of Table responsable_producto_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class ResponsableProductoPK implements java.io.Serializable {
    private static final long serialVersionUID = 1313922862;
    
    @Basic(optional = false)
    @Column(name = "ID_RESPONSABLE_ESTACION", nullable= false)
    private Integer idResponsableEstacion;
    
    @Basic(optional = false)
    @Column(name = "ID_TIPO_PRODUCTO_REPORTE", nullable= false)
    private Integer idTipoProductoReporte;

    /** 
     * Default Constructor
     */
    public ResponsableProductoPK() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdResponsableEstacion() { return this.idResponsableEstacion;}
    public void setIdResponsableEstacion(Integer v) { this.idResponsableEstacion = v; }
    
    public Integer getIdTipoProductoReporte() { return this.idTipoProductoReporte;}
    public void setIdTipoProductoReporte(Integer v) { this.idTipoProductoReporte = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idResponsableEstacion).hashCode();
		hash += String.valueOf(idTipoProductoReporte).hashCode();
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
        if (!(o instanceof ResponsableProductoPK)) {
            return false;
        }		
		ResponsableProductoPK other = (ResponsableProductoPK ) o;
		if (!Objects.equals(this.idResponsableEstacion, other.idResponsableEstacion)) { return false; }		
		if (!Objects.equals(this.idTipoProductoReporte, other.idTipoProductoReporte)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ResponsableProductoPK{");
		sb.append("idResponsableEstacion" ).append("=").append(idResponsableEstacion).append("|");
		sb.append("idTipoProductoReporte" ).append("=").append(idTipoProductoReporte).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
