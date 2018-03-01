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
 * Class for mapping JPA Entity of Table cantidad_vuelo_complementario.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "cantidad_vuelo_complementario")
@NamedQueries({
    @NamedQuery(name = "CantidadVueloComplementario.findAll", query = "SELECT c FROM CantidadVueloComplementario c")
    , @NamedQuery(name = "CantidadVueloComplementario.countAll", query = "SELECT COUNT(c) FROM CantidadVueloComplementario c")
    , @NamedQuery(name = "CantidadVueloComplementario.findByIdDetalleVueloComplementario", query = "SELECT c FROM CantidadVueloComplementario c WHERE c.idDetalleVueloComplementario = :idDetalleVueloComplementario")
    , @NamedQuery(name = "CantidadVueloComplementario.findByidDetalleComplementario", query = "SELECT c FROM CantidadVueloComplementario c WHERE c.idDetalleComplementario = :idDetalleComplementario")
    , @NamedQuery(name = "CantidadVueloComplementario.findBymodeloAvion", query = "SELECT c FROM CantidadVueloComplementario c WHERE c.modeloAvion = :modeloAvion")
    , @NamedQuery(name = "CantidadVueloComplementario.findByCantidad", query = "SELECT c FROM CantidadVueloComplementario c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "CantidadVueloComplementario.findByMaximo", query = "SELECT c FROM CantidadVueloComplementario c WHERE c.maximo = :maximo")
})
public class      CantidadVueloComplementario 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -969511248;
    
    /**
    * The 'id detalle vuelo complementario' Maps to COLUMN 'id_detalle_vuelo_complementario'
    */
    
    @Id
    @Column(name = "ID_DETALLE_VUELO_COMPLEMENTARIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDetalleVueloComplementario;
    
    /**
    * The 'id detalle complementario' Maps to COLUMN 'id_detalle_complementario'
    */
    
    @JoinColumn(name = "ID_DETALLE_COMPLEMENTARIO" , referencedColumnName = "ID_DETALLE_TABLA_COMPLEMENTARIO")
    @ManyToOne(optional = false )
    private DetalleTablaComplementario idDetalleComplementario;
    
    /**
    * The 'id modelo avion' Maps to COLUMN 'id_modelo_avion'
    */
    
    @JoinColumn(name = "ID_MODELO_AVION" , referencedColumnName = "ID_MODELO_AVION")
    @ManyToOne(optional = false )
    private ModeloAvion modeloAvion;
    
    /**
    * The 'cantidad' Maps to COLUMN 'cantidad'
    */
    
    @Basic(optional = false)
    @Column(name = "CANTIDAD" , nullable=false)
    private int cantidad;
    
    /**
    * The 'maximo' Maps to COLUMN 'maximo'
    */
    
    @Basic(optional = false)
    @Column(name = "MAXIMO" , nullable=false)
    private int maximo;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public CantidadVueloComplementario() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdDetalleVueloComplementario() { return this.idDetalleVueloComplementario;}
    public void setIdDetalleVueloComplementario(Integer v) { this.idDetalleVueloComplementario = v; }
    
    public DetalleTablaComplementario getIdDetalleComplementario(){ return this.idDetalleComplementario ; }
    public void setIdDetalleComplementario(DetalleTablaComplementario x){ this.idDetalleComplementario = x; }
    
    public ModeloAvion getModeloAvion(){ return this.modeloAvion ; }
    public void setModeloAvion(ModeloAvion x){ this.modeloAvion = x; }
    
    public int getCantidad() { return this.cantidad;}
    public void setCantidad(int v) { this.cantidad = v; }
    
    public int getMaximo() { return this.maximo;}
    public void setMaximo(int v) { this.maximo = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idDetalleVueloComplementario).hashCode();
		hash += String.valueOf(idDetalleComplementario).hashCode();
		hash += String.valueOf(modeloAvion).hashCode();
		hash += String.valueOf(cantidad).hashCode();
		hash += String.valueOf(maximo).hashCode();
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
        if (!(o instanceof CantidadVueloComplementario)) {
            return false;
        }		
		CantidadVueloComplementario other = (CantidadVueloComplementario ) o;
		if (!Objects.equals(this.idDetalleVueloComplementario, other.idDetalleVueloComplementario)) { return false; }		
		if (!Objects.equals(this.idDetalleComplementario, other.idDetalleComplementario)) { return false; }		
		if (!Objects.equals(this.modeloAvion, other.modeloAvion)) { return false; }		
		if (!Objects.equals(this.cantidad, other.cantidad)) { return false; }		
		if (!Objects.equals(this.maximo, other.maximo)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("CantidadVueloComplementario{");
		sb.append("idDetalleVueloComplementario" ).append("=").append(idDetalleVueloComplementario).append("|");
		sb.append("idDetalleComplementario" ).append("=").append(idDetalleComplementario).append("|");
		sb.append("modeloAvion" ).append("=").append(modeloAvion).append("|");
		sb.append("cantidad" ).append("=").append(cantidad).append("|");
		sb.append("maximo" ).append("=").append(maximo).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idDetalleVueloComplementario).append(" ");

		return sb.toString().trim();
	}

}
