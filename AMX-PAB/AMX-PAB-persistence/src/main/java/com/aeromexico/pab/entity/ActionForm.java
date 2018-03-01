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
 * Class for mapping JPA Entity of Table action_form.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "action_form")
@NamedQueries({
    @NamedQuery(name = "ActionForm.findAll", query = "SELECT a FROM ActionForm a")
    , @NamedQuery(name = "ActionForm.countAll", query = "SELECT COUNT(a) FROM ActionForm a")
    , @NamedQuery(name = "ActionForm.findByIdActionForm", query = "SELECT a FROM ActionForm a WHERE a.idActionForm = :idActionForm")
    , @NamedQuery(name = "ActionForm.findByopcionMenu", query = "SELECT a FROM ActionForm a WHERE a.opcionMenu = :opcionMenu")
    , @NamedQuery(name = "ActionForm.findByUrl", query = "SELECT a FROM ActionForm a WHERE a.url = :url")
})
public class      ActionForm 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 1583331629;
    
    /**
    * The 'id action form' Maps to COLUMN 'id_action_form'
    */
    
    @Id
    @Column(name = "ID_ACTION_FORM" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idActionForm;
    
    /**
    * The 'id opcion menu' Maps to COLUMN 'id_opcion_menu'
    */
    
    @JoinColumn(name = "ID_OPCION_MENU" , referencedColumnName = "ID_OPCION_MENU")
    @ManyToOne(optional = false )
    private OpcionMenu opcionMenu;
    
    /**
    * The 'url' Maps to COLUMN 'url'
    */
    
    @Basic(optional = false)
    @Column(name = "URL" , length=255, nullable=false)
    private String url;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ActionForm() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdActionForm() { return this.idActionForm;}
    public void setIdActionForm(Integer v) { this.idActionForm = v; }
    
    public OpcionMenu getOpcionMenu(){ return this.opcionMenu ; }
    public void setOpcionMenu(OpcionMenu x){ this.opcionMenu = x; }
    
    public String getUrl() { return this.url;}
    public void setUrl(String v) { this.url = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idActionForm).hashCode();
		hash += String.valueOf(opcionMenu).hashCode();
		hash += String.valueOf(url).hashCode();
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
        if (!(o instanceof ActionForm)) {
            return false;
        }		
		ActionForm other = (ActionForm ) o;
		if (!Objects.equals(this.idActionForm, other.idActionForm)) { return false; }		
		if (!Objects.equals(this.opcionMenu, other.opcionMenu)) { return false; }		
		if (!Objects.equals(this.url, other.url)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ActionForm{");
		sb.append("idActionForm" ).append("=").append(idActionForm).append("|");
		sb.append("opcionMenu" ).append("=").append(opcionMenu).append("|");
		sb.append("url" ).append("=").append(url).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idActionForm).append(" ");

		return sb.toString().trim();
	}

}
