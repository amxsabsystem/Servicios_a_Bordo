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
 * Class for mapping JPA Embedable PK of Table grafico_producto_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class GraficoProductoPK implements java.io.Serializable {
    private static final long serialVersionUID = 1296064247;
    
    @Basic(optional = false)
    @Column(name = "ID_GRAFICO", nullable= false)
    private Integer idGrafico;
    
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO", nullable= false)
    private Integer idProducto;

    /** 
     * Default Constructor
     */
    public GraficoProductoPK() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdGrafico() { return this.idGrafico;}
    public void setIdGrafico(Integer v) { this.idGrafico = v; }
    
    public Integer getIdProducto() { return this.idProducto;}
    public void setIdProducto(Integer v) { this.idProducto = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idGrafico).hashCode();
		hash += String.valueOf(idProducto).hashCode();
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
        if (!(o instanceof GraficoProductoPK)) {
            return false;
        }		
		GraficoProductoPK other = (GraficoProductoPK ) o;
		if (!Objects.equals(this.idGrafico, other.idGrafico)) { return false; }		
		if (!Objects.equals(this.idProducto, other.idProducto)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("GraficoProductoPK{");
		sb.append("idGrafico" ).append("=").append(idGrafico).append("|");
		sb.append("idProducto" ).append("=").append(idProducto).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
