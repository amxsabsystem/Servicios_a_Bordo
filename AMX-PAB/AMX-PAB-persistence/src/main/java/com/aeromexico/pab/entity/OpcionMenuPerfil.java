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
 * Class for mapping JPA Entity of Table opcion_menu_perfil.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "opcion_menu_perfil")
@NamedQueries({
    @NamedQuery(name = "OpcionMenuPerfil.findAll", query = "SELECT o FROM OpcionMenuPerfil o")
    , @NamedQuery(name = "OpcionMenuPerfil.countAll", query = "SELECT COUNT(o) FROM OpcionMenuPerfil o")
    , @NamedQuery(name = "OpcionMenuPerfil.findByIdOpcionMenuPerfil", query = "SELECT o FROM OpcionMenuPerfil o WHERE o.idOpcionMenuPerfil = :idOpcionMenuPerfil")
    , @NamedQuery(name = "OpcionMenuPerfil.findByperfilid", query = "SELECT o FROM OpcionMenuPerfil o WHERE o.perfilid = :perfilid")
    , @NamedQuery(name = "OpcionMenuPerfil.findByopcionMenu", query = "SELECT o FROM OpcionMenuPerfil o WHERE o.opcionMenu = :opcionMenu")
    , @NamedQuery(name = "OpcionMenuPerfil.findByPermisoRwd", query = "SELECT o FROM OpcionMenuPerfil o WHERE o.permisoRwd = :permisoRwd")
})
public class      OpcionMenuPerfil 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 1046182931;
    
    /**
    * The 'id opcion menu perfil' Maps to COLUMN 'id_opcion_menu_perfil'
    */
    
    @Id
    @Column(name = "ID_OPCION_MENU_PERFIL" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idOpcionMenuPerfil;
    
    /**
    * The 'id perfil' Maps to COLUMN 'id_perfil'
    */
    
    @JoinColumn(name = "ID_PERFIL" , referencedColumnName = "PERFIL")
    @ManyToOne(optional = false )
    private Perfil perfilid;
    
    /**
    * The 'id opcion menu' Maps to COLUMN 'id_opcion_menu'
    */
    
    @JoinColumn(name = "ID_OPCION_MENU" , referencedColumnName = "ID_OPCION_MENU")
    @ManyToOne(optional = false )
    private OpcionMenu opcionMenu;
    
    /**
    * The 'permiso rwd' Maps to COLUMN 'permiso_rwd'
    */
    
    @Basic(optional = false)
    @Column(name = "PERMISO_RWD" , nullable=false)
    private Short permisoRwd;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public OpcionMenuPerfil() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdOpcionMenuPerfil() { return this.idOpcionMenuPerfil;}
    public void setIdOpcionMenuPerfil(Integer v) { this.idOpcionMenuPerfil = v; }
    
    public Perfil getPerfilid(){ return this.perfilid ; }
    public void setPerfilid(Perfil x){ this.perfilid = x; }
    
    public OpcionMenu getOpcionMenu(){ return this.opcionMenu ; }
    public void setOpcionMenu(OpcionMenu x){ this.opcionMenu = x; }
    
    public Short getPermisoRwd() { return this.permisoRwd;}
    public void setPermisoRwd(Short v) { this.permisoRwd = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idOpcionMenuPerfil).hashCode();
		hash += String.valueOf(perfilid).hashCode();
		hash += String.valueOf(opcionMenu).hashCode();
		hash += String.valueOf(permisoRwd).hashCode();
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
        if (!(o instanceof OpcionMenuPerfil)) {
            return false;
        }		
		OpcionMenuPerfil other = (OpcionMenuPerfil ) o;
		if (!Objects.equals(this.idOpcionMenuPerfil, other.idOpcionMenuPerfil)) { return false; }		
		if (!Objects.equals(this.perfilid, other.perfilid)) { return false; }		
		if (!Objects.equals(this.opcionMenu, other.opcionMenu)) { return false; }		
		if (!Objects.equals(this.permisoRwd, other.permisoRwd)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("OpcionMenuPerfil{");
		sb.append("idOpcionMenuPerfil" ).append("=").append(idOpcionMenuPerfil).append("|");
		sb.append("perfilid" ).append("=").append(perfilid).append("|");
		sb.append("opcionMenu" ).append("=").append(opcionMenu).append("|");
		sb.append("permisoRwd" ).append("=").append(permisoRwd).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idOpcionMenuPerfil).append(" ");

		return sb.toString().trim();
	}

}
