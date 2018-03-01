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
 * Class for mapping JPA Entity of Table tsu_abordamiento.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tsu_abordamiento")
@NamedQueries({
    @NamedQuery(name = "TsuAbordamiento.findAll", query = "SELECT t FROM TsuAbordamiento t")
    , @NamedQuery(name = "TsuAbordamiento.countAll", query = "SELECT COUNT(t) FROM TsuAbordamiento t")
    , @NamedQuery(name = "TsuAbordamiento.findByIdTsuAbordamiento", query = "SELECT t FROM TsuAbordamiento t WHERE t.idTsuAbordamiento = :idTsuAbordamiento")
    , @NamedQuery(name = "TsuAbordamiento.findBytablaAbordamiento", query = "SELECT t FROM TsuAbordamiento t WHERE t.tablaAbordamiento = :tablaAbordamiento")
    , @NamedQuery(name = "TsuAbordamiento.findBytsu", query = "SELECT t FROM TsuAbordamiento t WHERE t.tsu = :tsu")
    , @NamedQuery(name = "TsuAbordamiento.findByPorcentaje", query = "SELECT t FROM TsuAbordamiento t WHERE t.porcentaje = :porcentaje")
})
public class      TsuAbordamiento 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1946364726;
    
    /**
    * The 'id tsu abordamiento' Maps to COLUMN 'id_tsu_abordamiento'
    */
    
    @Id
    @Column(name = "ID_TSU_ABORDAMIENTO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTsuAbordamiento;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @JoinColumn(name = "ID_TABLA_ABORDAMIENTO" , referencedColumnName = "ID_TABLA_ABORDAMIENTO")
    @ManyToOne(optional = false )
    private TablaAbordamiento tablaAbordamiento;
    
    /**
    * The 'id tsu' Maps to COLUMN 'id_tsu'
    */
    
    @JoinColumn(name = "ID_TSU" , referencedColumnName = "ID_TSU")
    @ManyToOne(optional = false )
    private Tsu tsu;
    
    /**
    * The 'porcentaje' Maps to COLUMN 'porcentaje'
    */
    
    @Basic(optional = true)
    @Column(name = "PORCENTAJE" , nullable=true)
    private Integer porcentaje;
    /** 
    * Map the relation to detalle_tabla_tsu table where has detalle_tsu_abordajetsu_porcentaje object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleTsuAbordajetsuPorcentaje")
    private List<DetalleTablaTsu> detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TsuAbordamiento() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTsuAbordamiento() { return this.idTsuAbordamiento;}
    public void setIdTsuAbordamiento(Integer v) { this.idTsuAbordamiento = v; }
    
    public TablaAbordamiento getTablaAbordamiento(){ return this.tablaAbordamiento ; }
    public void setTablaAbordamiento(TablaAbordamiento x){ this.tablaAbordamiento = x; }
    
    public Tsu getTsu(){ return this.tsu ; }
    public void setTsu(Tsu x){ this.tsu = x; }
    
    public Integer getPorcentaje() { return this.porcentaje;}
    public void setPorcentaje(Integer v) { this.porcentaje = v; }
    
    // O2M <*>    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList(){ return this.detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList; }
    public void setDetalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasDetalleTsuAbordajetsuPorcentajeList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTsuAbordamiento).hashCode();
		hash += String.valueOf(tablaAbordamiento).hashCode();
		hash += String.valueOf(tsu).hashCode();
		hash += String.valueOf(porcentaje).hashCode();
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
        if (!(o instanceof TsuAbordamiento)) {
            return false;
        }		
		TsuAbordamiento other = (TsuAbordamiento ) o;
		if (!Objects.equals(this.idTsuAbordamiento, other.idTsuAbordamiento)) { return false; }		
		if (!Objects.equals(this.tablaAbordamiento, other.tablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.tsu, other.tsu)) { return false; }		
		if (!Objects.equals(this.porcentaje, other.porcentaje)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("TsuAbordamiento{");
		sb.append("idTsuAbordamiento" ).append("=").append(idTsuAbordamiento).append("|");
		sb.append("tablaAbordamiento" ).append("=").append(tablaAbordamiento).append("|");
		sb.append("tsu" ).append("=").append(tsu).append("|");
		sb.append("porcentaje" ).append("=").append(porcentaje).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idTsuAbordamiento).append(" ");

		return sb.toString().trim();
	}

}
