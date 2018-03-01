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
 * Class for mapping JPA Entity of Table comunicado_areas.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "comunicado_areas")
@NamedQueries({
    @NamedQuery(name = "ComunicadoAreas.findAll", query = "SELECT c FROM ComunicadoAreas c")
    , @NamedQuery(name = "ComunicadoAreas.countAll", query = "SELECT COUNT(c) FROM ComunicadoAreas c")
    , @NamedQuery(name = "ComunicadoAreas.findByIdComunicadoAereas", query = "SELECT c FROM ComunicadoAreas c WHERE c.idComunicadoAereas = :idComunicadoAereas")
    , @NamedQuery(name = "ComunicadoAreas.findBycomunicado", query = "SELECT c FROM ComunicadoAreas c WHERE c.comunicado = :comunicado")
    , @NamedQuery(name = "ComunicadoAreas.findByarea", query = "SELECT c FROM ComunicadoAreas c WHERE c.area = :area")
})
public class      ComunicadoAreas 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1866089409;
    
    /**
    * The 'id comunicado aereas' Maps to COLUMN 'id_comunicado_aereas'
    */
    
    @Id
    @Column(name = "ID_COMUNICADO_AEREAS" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idComunicadoAereas;
    
    /**
    * The 'id comunicado' Maps to COLUMN 'id_comunicado'
    */
    
    @JoinColumn(name = "ID_COMUNICADO" , referencedColumnName = "ID_COMUNICADO")
    @ManyToOne(optional = false )
    private Comunicado comunicado;
    
    /**
    * The 'id area' Maps to COLUMN 'id_area'
    */
    
    @JoinColumn(name = "ID_AREA" , referencedColumnName = "ID_AREA")
    @ManyToOne(optional = false )
    private Area area;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ComunicadoAreas() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdComunicadoAereas() { return this.idComunicadoAereas;}
    public void setIdComunicadoAereas(Integer v) { this.idComunicadoAereas = v; }
    
    public Comunicado getComunicado(){ return this.comunicado ; }
    public void setComunicado(Comunicado x){ this.comunicado = x; }
    
    public Area getArea(){ return this.area ; }
    public void setArea(Area x){ this.area = x; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idComunicadoAereas).hashCode();
		hash += String.valueOf(comunicado).hashCode();
		hash += String.valueOf(area).hashCode();
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
        if (!(o instanceof ComunicadoAreas)) {
            return false;
        }		
		ComunicadoAreas other = (ComunicadoAreas ) o;
		if (!Objects.equals(this.idComunicadoAereas, other.idComunicadoAereas)) { return false; }		
		if (!Objects.equals(this.comunicado, other.comunicado)) { return false; }		
		if (!Objects.equals(this.area, other.area)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ComunicadoAreas{");
		sb.append("idComunicadoAereas" ).append("=").append(idComunicadoAereas).append("|");
		sb.append("comunicado" ).append("=").append(comunicado).append("|");
		sb.append("area" ).append("=").append(area).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idComunicadoAereas).append(" ");

		return sb.toString().trim();
	}

}
