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
 * Class for mapping JPA Embedable PK of Table proveedor_estacion_tipo_proveedor_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class ProveedorEstacionTipoProveedorPK implements java.io.Serializable {
    private static final long serialVersionUID = 589873731;
    
    @Basic(optional = false)
    @Column(name = "ID_PROVEEDOR_ESTACION", nullable= false)
    private Integer idProveedorEstacion;
    
    @Basic(optional = false)
    @Column(name = "ID_TIPO_PROVEEDOR", nullable= false)
    private Integer idTipoProveedor;

    /** 
     * Default Constructor
     */
    public ProveedorEstacionTipoProveedorPK() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdProveedorEstacion() { return this.idProveedorEstacion;}
    public void setIdProveedorEstacion(Integer v) { this.idProveedorEstacion = v; }
    
    public Integer getIdTipoProveedor() { return this.idTipoProveedor;}
    public void setIdTipoProveedor(Integer v) { this.idTipoProveedor = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idProveedorEstacion).hashCode();
		hash += String.valueOf(idTipoProveedor).hashCode();
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
        if (!(o instanceof ProveedorEstacionTipoProveedorPK)) {
            return false;
        }		
		ProveedorEstacionTipoProveedorPK other = (ProveedorEstacionTipoProveedorPK ) o;
		if (!Objects.equals(this.idProveedorEstacion, other.idProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.idTipoProveedor, other.idTipoProveedor)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ProveedorEstacionTipoProveedorPK{");
		sb.append("idProveedorEstacion" ).append("=").append(idProveedorEstacion).append("|");
		sb.append("idTipoProveedor" ).append("=").append(idTipoProveedor).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
