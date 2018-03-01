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
 * Class for mapping JPA Entity of Table pax_compementario.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "pax_compementario")
@NamedQueries({
    @NamedQuery(name = "PaxCompementario.findAll", query = "SELECT p FROM PaxCompementario p")
    , @NamedQuery(name = "PaxCompementario.countAll", query = "SELECT COUNT(p) FROM PaxCompementario p")
    , @NamedQuery(name = "PaxCompementario.findByIdPaxComplementario", query = "SELECT p FROM PaxCompementario p WHERE p.idPaxComplementario = :idPaxComplementario")
    , @NamedQuery(name = "PaxCompementario.findBydetalleTablaComplementario", query = "SELECT p FROM PaxCompementario p WHERE p.detalleTablaComplementario = :detalleTablaComplementario")
    , @NamedQuery(name = "PaxCompementario.findBymodeloAvion", query = "SELECT p FROM PaxCompementario p WHERE p.modeloAvion = :modeloAvion")
    , @NamedQuery(name = "PaxCompementario.findByCantidad", query = "SELECT p FROM PaxCompementario p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "PaxCompementario.findByNoPasajeros", query = "SELECT p FROM PaxCompementario p WHERE p.noPasajeros = :noPasajeros")
    , @NamedQuery(name = "PaxCompementario.findByMaxPasajeros", query = "SELECT p FROM PaxCompementario p WHERE p.maxPasajeros = :maxPasajeros")
})
public class      PaxCompementario 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -437487853;
    
    /**
    * The 'id pax complementario' Maps to COLUMN 'id_pax_complementario'
    */
    
    @Id
    @Column(name = "ID_PAX_COMPLEMENTARIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPaxComplementario;
    
    /**
    * The 'id detalle tabla complementario' Maps to COLUMN 'id_detalle_tabla_complementario'
    */
    
    @JoinColumn(name = "ID_DETALLE_TABLA_COMPLEMENTARIO" , referencedColumnName = "ID_DETALLE_TABLA_COMPLEMENTARIO")
    @ManyToOne(optional = false )
    private DetalleTablaComplementario detalleTablaComplementario;
    
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
    * The 'no pasajeros' Maps to COLUMN 'no_pasajeros'
    */
    
    @Basic(optional = false)
    @Column(name = "NO_PASAJEROS" , nullable=false)
    private int noPasajeros;
    
    /**
    * The 'max pasajeros' Maps to COLUMN 'max_pasajeros'
    */
    
    @Basic(optional = false)
    @Column(name = "MAX_PASAJEROS" , nullable=false)
    private int maxPasajeros;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public PaxCompementario() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdPaxComplementario() { return this.idPaxComplementario;}
    public void setIdPaxComplementario(Integer v) { this.idPaxComplementario = v; }
    
    public DetalleTablaComplementario getDetalleTablaComplementario(){ return this.detalleTablaComplementario ; }
    public void setDetalleTablaComplementario(DetalleTablaComplementario x){ this.detalleTablaComplementario = x; }
    
    public ModeloAvion getModeloAvion(){ return this.modeloAvion ; }
    public void setModeloAvion(ModeloAvion x){ this.modeloAvion = x; }
    
    public int getCantidad() { return this.cantidad;}
    public void setCantidad(int v) { this.cantidad = v; }
    
    public int getNoPasajeros() { return this.noPasajeros;}
    public void setNoPasajeros(int v) { this.noPasajeros = v; }
    
    public int getMaxPasajeros() { return this.maxPasajeros;}
    public void setMaxPasajeros(int v) { this.maxPasajeros = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idPaxComplementario).hashCode();
		hash += String.valueOf(detalleTablaComplementario).hashCode();
		hash += String.valueOf(modeloAvion).hashCode();
		hash += String.valueOf(cantidad).hashCode();
		hash += String.valueOf(noPasajeros).hashCode();
		hash += String.valueOf(maxPasajeros).hashCode();
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
        if (!(o instanceof PaxCompementario)) {
            return false;
        }		
		PaxCompementario other = (PaxCompementario ) o;
		if (!Objects.equals(this.idPaxComplementario, other.idPaxComplementario)) { return false; }		
		if (!Objects.equals(this.detalleTablaComplementario, other.detalleTablaComplementario)) { return false; }		
		if (!Objects.equals(this.modeloAvion, other.modeloAvion)) { return false; }		
		if (!Objects.equals(this.cantidad, other.cantidad)) { return false; }		
		if (!Objects.equals(this.noPasajeros, other.noPasajeros)) { return false; }		
		if (!Objects.equals(this.maxPasajeros, other.maxPasajeros)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("PaxCompementario{");
		sb.append("idPaxComplementario" ).append("=").append(idPaxComplementario).append("|");
		sb.append("detalleTablaComplementario" ).append("=").append(detalleTablaComplementario).append("|");
		sb.append("modeloAvion" ).append("=").append(modeloAvion).append("|");
		sb.append("cantidad" ).append("=").append(cantidad).append("|");
		sb.append("noPasajeros" ).append("=").append(noPasajeros).append("|");
		sb.append("maxPasajeros" ).append("=").append(maxPasajeros).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idPaxComplementario).append(" ");

		return sb.toString().trim();
	}

}
