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
 * Class for mapping JPA Entity of Table sobreabordaje.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "sobreabordaje")
@NamedQueries({
    @NamedQuery(name = "Sobreabordaje.findAll", query = "SELECT s FROM Sobreabordaje s")
    , @NamedQuery(name = "Sobreabordaje.countAll", query = "SELECT COUNT(s) FROM Sobreabordaje s")
    , @NamedQuery(name = "Sobreabordaje.findByIdSobreabordaje", query = "SELECT s FROM Sobreabordaje s WHERE s.idSobreabordaje = :idSobreabordaje")
    , @NamedQuery(name = "Sobreabordaje.findBytablaAbordamiento", query = "SELECT s FROM Sobreabordaje s WHERE s.tablaAbordamiento = :tablaAbordamiento")
    , @NamedQuery(name = "Sobreabordaje.findBymaterial", query = "SELECT s FROM Sobreabordaje s WHERE s.material = :material")
    , @NamedQuery(name = "Sobreabordaje.findByCantidad", query = "SELECT s FROM Sobreabordaje s WHERE s.cantidad = :cantidad")
    , @NamedQuery(name = "Sobreabordaje.findByInstrucciones", query = "SELECT s FROM Sobreabordaje s WHERE s.instrucciones = :instrucciones")
})
public class      Sobreabordaje 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 578239349;
    
    /**
    * The 'id sobreabordaje' Maps to COLUMN 'id_sobreabordaje'
    */
    
    @Id
    @Column(name = "ID_SOBREABORDAJE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSobreabordaje;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @JoinColumn(name = "ID_TABLA_ABORDAMIENTO" , referencedColumnName = "ID_TABLA_ABORDAMIENTO")
    @ManyToOne(optional = false )
    private TablaAbordamiento tablaAbordamiento;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material material;
    
    /**
    * The 'cantidad' Maps to COLUMN 'cantidad'
    */
    
    @Basic(optional = false)
    @Column(name = "CANTIDAD" , nullable=false)
    private Short cantidad;
    
    /**
    * The 'instrucciones' Maps to COLUMN 'instrucciones'
    */
    
    @Basic(optional = true)
    @Column(name = "INSTRUCCIONES" , length=100, nullable=true)
    private String instrucciones;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Sobreabordaje() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdSobreabordaje() { return this.idSobreabordaje;}
    public void setIdSobreabordaje(Integer v) { this.idSobreabordaje = v; }
    
    public TablaAbordamiento getTablaAbordamiento(){ return this.tablaAbordamiento ; }
    public void setTablaAbordamiento(TablaAbordamiento x){ this.tablaAbordamiento = x; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public Short getCantidad() { return this.cantidad;}
    public void setCantidad(Short v) { this.cantidad = v; }
    
    public String getInstrucciones() { return this.instrucciones;}
    public void setInstrucciones(String v) { this.instrucciones = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idSobreabordaje).hashCode();
		hash += String.valueOf(tablaAbordamiento).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(cantidad).hashCode();
		hash += String.valueOf(instrucciones).hashCode();
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
        if (!(o instanceof Sobreabordaje)) {
            return false;
        }		
		Sobreabordaje other = (Sobreabordaje ) o;
		if (!Objects.equals(this.idSobreabordaje, other.idSobreabordaje)) { return false; }		
		if (!Objects.equals(this.tablaAbordamiento, other.tablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.cantidad, other.cantidad)) { return false; }		
		if (!Objects.equals(this.instrucciones, other.instrucciones)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Sobreabordaje{");
		sb.append("idSobreabordaje" ).append("=").append(idSobreabordaje).append("|");
		sb.append("tablaAbordamiento" ).append("=").append(tablaAbordamiento).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("cantidad" ).append("=").append(cantidad).append("|");
		sb.append("instrucciones" ).append("=").append(instrucciones).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idSobreabordaje).append(" ");

		return sb.toString().trim();
	}

}
