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
 * Class for mapping JPA Embedable PK of Table reporte_evidencia_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class ReporteEvidenciaPK implements java.io.Serializable {
    private static final long serialVersionUID = 1534030866;
    
    @Basic(optional = false)
    @Column(name = "ID_REPORTE", nullable= false)
    private Integer idReporte;
    
    @Basic(optional = false)
    @Column(name = "ID_EVIDENCIA", nullable= false)
    private Integer idEvidencia;

    /** 
     * Default Constructor
     */
    public ReporteEvidenciaPK() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdReporte() { return this.idReporte;}
    public void setIdReporte(Integer v) { this.idReporte = v; }
    
    public Integer getIdEvidencia() { return this.idEvidencia;}
    public void setIdEvidencia(Integer v) { this.idEvidencia = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idReporte).hashCode();
		hash += String.valueOf(idEvidencia).hashCode();
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
        if (!(o instanceof ReporteEvidenciaPK)) {
            return false;
        }		
		ReporteEvidenciaPK other = (ReporteEvidenciaPK ) o;
		if (!Objects.equals(this.idReporte, other.idReporte)) { return false; }		
		if (!Objects.equals(this.idEvidencia, other.idEvidencia)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ReporteEvidenciaPK{");
		sb.append("idReporte" ).append("=").append(idReporte).append("|");
		sb.append("idEvidencia" ).append("=").append(idEvidencia).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
