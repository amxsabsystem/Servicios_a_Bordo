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
 * Class for mapping JPA Embedable PK of Table perfil_usuario_P_K.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Embeddable

@XmlRootElement
public class PerfilUsuarioPK implements java.io.Serializable {
    private static final long serialVersionUID = 1349393271;
    
    @Basic(optional = false)
    @Column(name = "PERFIL", nullable= false)
    private String perfil;
    
    @Basic(optional = false)
    @Column(name = "EMAIL_USUARIO", nullable= false)
    private String emailUsuario;

    /** 
     * Default Constructor
     */
    public PerfilUsuarioPK() {
    }
    
    /**
     * Getters and Setters
     */
    public String getPerfil() { return this.perfil;}
    public void setPerfil(String v) { this.perfil = v; }
    
    public String getEmailUsuario() { return this.emailUsuario;}
    public void setEmailUsuario(String v) { this.emailUsuario = v; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(perfil).hashCode();
		hash += String.valueOf(emailUsuario).hashCode();
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
        if (!(o instanceof PerfilUsuarioPK)) {
            return false;
        }		
		PerfilUsuarioPK other = (PerfilUsuarioPK ) o;
		if (!Objects.equals(this.perfil, other.perfil)) { return false; }		
		if (!Objects.equals(this.emailUsuario, other.emailUsuario)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("PerfilUsuarioPK{");
		sb.append("perfil" ).append("=").append(perfil).append("|");
		sb.append("emailUsuario" ).append("=").append(emailUsuario).append("|");
		sb.append("serialVersionUID=").append(serialVersionUID).append("}");
		return sb.toString();
	}

}
