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
 * Class for mapping JPA Embedable PK of Table opcion_menu_perfil_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class OpcionMenuPerfilPK implements java.io.Serializable {
    private static final long serialVersionUID = 1534030866;
    
    @Basic(optional = false)
    @Column(name = "ID_PERFIL", nullable= false)
    private String idPerfil;
    
    @Basic(optional = false)
    @Column(name = "ID_OPCION_MENU", nullable= false)
    private Integer idOpcionMenu;

    /** 
     * Default Constructor
     */
    public OpcionMenuPerfilPK() {
    }
    
    /**
     * Getters and Setters
     */
    public String getIdPerfil() { return this.idPerfil;}
    public void setIdPerfil(String v) { this.idPerfil = v; }
    
    public Integer getIdOpcionMenu() { return this.idOpcionMenu;}
    public void setIdOpcionMenu(Integer v) { this.idOpcionMenu = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idPerfil).hashCode();
		hash += String.valueOf(idOpcionMenu).hashCode();
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
        if (!(o instanceof OpcionMenuPerfilPK)) {
            return false;
        }		
		OpcionMenuPerfilPK other = (OpcionMenuPerfilPK ) o;
		if (!Objects.equals(this.idPerfil, other.idPerfil)) { return false; }		
		if (!Objects.equals(this.idOpcionMenu, other.idOpcionMenu)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("OpcionMenuPerfilPK{");
		sb.append("idPerfil" ).append("=").append(idPerfil).append("|");
		sb.append("idOpcionMenu" ).append("=").append(idOpcionMenu).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
