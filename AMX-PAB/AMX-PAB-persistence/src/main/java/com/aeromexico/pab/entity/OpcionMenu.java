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
 * Class for mapping JPA Entity of Table opcion_menu.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "opcion_menu")
@NamedQueries({
    @NamedQuery(name = "OpcionMenu.findAll", query = "SELECT o FROM OpcionMenu o")
    , @NamedQuery(name = "OpcionMenu.countAll", query = "SELECT COUNT(o) FROM OpcionMenu o")
    , @NamedQuery(name = "OpcionMenu.findByIdOpcionMenu", query = "SELECT o FROM OpcionMenu o WHERE o.idOpcionMenu = :idOpcionMenu")
    , @NamedQuery(name = "OpcionMenu.findByClaveI18n", query = "SELECT o FROM OpcionMenu o WHERE o.claveI18n = :claveI18n")
})
public class      OpcionMenu 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 303294424;
    
    /**
    * The 'id opcion menu' Maps to COLUMN 'id_opcion_menu'
    */
    
    @Id
    @Column(name = "ID_OPCION_MENU" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idOpcionMenu;
    
    /**
    * The 'clave i18n' Maps to COLUMN 'clave_i18n'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE_I18N" , length=256, nullable=false)
    private String claveI18n;
    /** 
    * Map the relation to opcion_menu_perfil table where has id_opcion_menu object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionMenu")
    private List<OpcionMenuPerfil> opcionMenuPerfilHasOpcionMenuList;
    
    /** 
    * Map the relation to action_form table where has id_opcion_menu object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionMenu")
    private List<ActionForm> actionFormHasOpcionMenuList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public OpcionMenu() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdOpcionMenu() { return this.idOpcionMenu;}
    public void setIdOpcionMenu(Integer v) { this.idOpcionMenu = v; }
    
    public String getClaveI18n() { return this.claveI18n;}
    public void setClaveI18n(String v) { this.claveI18n = v; }
    
    // O2M <*>    
    public List<OpcionMenuPerfil> getOpcionMenuPerfilHasOpcionMenuList(){ return this.opcionMenuPerfilHasOpcionMenuList; }
    public void setOpcionMenuPerfilHasOpcionMenuList(List<OpcionMenuPerfil> v){ this.opcionMenuPerfilHasOpcionMenuList = v; }
    
    public List<ActionForm> getActionFormHasOpcionMenuList(){ return this.actionFormHasOpcionMenuList; }
    public void setActionFormHasOpcionMenuList(List<ActionForm> v){ this.actionFormHasOpcionMenuList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idOpcionMenu).hashCode();
		hash += String.valueOf(claveI18n).hashCode();
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
        if (!(o instanceof OpcionMenu)) {
            return false;
        }		
		OpcionMenu other = (OpcionMenu ) o;
		if (!Objects.equals(this.idOpcionMenu, other.idOpcionMenu)) { return false; }		
		if (!Objects.equals(this.claveI18n, other.claveI18n)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("OpcionMenu{");
		sb.append("idOpcionMenu" ).append("=").append(idOpcionMenu).append("|");
		sb.append("claveI18n" ).append("=").append(claveI18n).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idOpcionMenu).append(" ");

		return sb.toString().trim();
	}

}
