package com.aeromexico.pab.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Embeddable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class for mapping JPA Entity of Table perfil.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "perfil")
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p")
    , @NamedQuery(name = "Perfil.countAll", query = "SELECT COUNT(p) FROM Perfil p")
    , @NamedQuery(name = "Perfil.findByPerfil", query = "SELECT p FROM Perfil p WHERE p.perfil = :perfil")
    , @NamedQuery(name = "Perfil.findByDescripcionEs", query = "SELECT p FROM Perfil p WHERE p.descripcionEs = :descripcionEs")
    , @NamedQuery(name = "Perfil.findByDescripcionEn", query = "SELECT p FROM Perfil p WHERE p.descripcionEn = :descripcionEn")
})
public class      Perfil 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -991729204;
    
    /**
    * The 'perfil' Maps to COLUMN 'perfil'
    */
    
    @Id
    @Column(name = "PERFIL" , length=50, nullable=false  )
    private String perfil;
    
    /**
    * The 'descripcion es' Maps to COLUMN 'descripcion_es'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_ES" , length=128, nullable=false)
    private String descripcionEs;
    
    /**
    * The 'descripcion en' Maps to COLUMN 'descripcion_en'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_EN" , length=128, nullable=false)
    private String descripcionEn;
    /** 
    * Map the relation to opcion_menu_perfil table where has id_perfil object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilid")
    private List<OpcionMenuPerfil> opcionMenuPerfilHasPerfilidList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "PERFIL_USUARIO",
               joinColumns        = {@JoinColumn(name = "PERFIL", referencedColumnName ="PERFIL")},
               inverseJoinColumns = {@JoinColumn(name = "EMAIL_USUARIO", referencedColumnName ="EMAIL_USUARIO")}
               )
    private List<Usuario> usuarioList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Perfil() {
    }
    
    /**
     * Getters and Setters
     */
    public String getPerfil() { return this.perfil;}
    public void setPerfil(String v) { this.perfil = v; }
    
    public String getDescripcionEs() { return this.descripcionEs;}
    public void setDescripcionEs(String v) { this.descripcionEs = v; }
    
    public String getDescripcionEn() { return this.descripcionEn;}
    public void setDescripcionEn(String v) { this.descripcionEn = v; }
    
    // O2M <*>    
    public List<OpcionMenuPerfil> getOpcionMenuPerfilHasPerfilidList(){ return this.opcionMenuPerfilHasPerfilidList; }
    public void setOpcionMenuPerfilHasPerfilidList(List<OpcionMenuPerfil> v){ this.opcionMenuPerfilHasPerfilidList = v; }
    
	// M2M <*>
    public List<Usuario> getUsuarioList() { return this.usuarioList; }
    public void setUsuarioList(List<Usuario>  v) { this.usuarioList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(perfil).hashCode();
		hash += String.valueOf(descripcionEs).hashCode();
		hash += String.valueOf(descripcionEn).hashCode();
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
        if (!(o instanceof Perfil)) {
            return false;
        }		
		Perfil other = (Perfil ) o;
		if (!Objects.equals(this.perfil, other.perfil)) { return false; }		
		if (!Objects.equals(this.descripcionEs, other.descripcionEs)) { return false; }		
		if (!Objects.equals(this.descripcionEn, other.descripcionEn)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Perfil{");
		sb.append("perfil" ).append("=").append(perfil).append("|");
		sb.append("descripcionEs" ).append("=").append(descripcionEs).append("|");
		sb.append("descripcionEn" ).append("=").append(descripcionEn).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(perfil).append(" ");

		return sb.toString().trim();
	}

}
