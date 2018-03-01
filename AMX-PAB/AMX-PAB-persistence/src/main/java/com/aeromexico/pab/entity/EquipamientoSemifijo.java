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
 * Class for mapping JPA Entity of Table equipamiento_semifijo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "equipamiento_semifijo")
@NamedQueries({
    @NamedQuery(name = "EquipamientoSemifijo.findAll", query = "SELECT e FROM EquipamientoSemifijo e")
    , @NamedQuery(name = "EquipamientoSemifijo.countAll", query = "SELECT COUNT(e) FROM EquipamientoSemifijo e")
    , @NamedQuery(name = "EquipamientoSemifijo.findByIdEquipamientoSemifijo", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.idEquipamientoSemifijo = :idEquipamientoSemifijo")
    , @NamedQuery(name = "EquipamientoSemifijo.findByavion", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.avion = :avion")
    , @NamedQuery(name = "EquipamientoSemifijo.findBymaterial", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.material = :material")
    , @NamedQuery(name = "EquipamientoSemifijo.findByCantidad", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.cantidad = :cantidad")
    , @NamedQuery(name = "EquipamientoSemifijo.findByPeso", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.peso = :peso")
    , @NamedQuery(name = "EquipamientoSemifijo.findByObservaciones", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.observaciones = :observaciones")
    , @NamedQuery(name = "EquipamientoSemifijo.findByStatus", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.status = :status")
    , @NamedQuery(name = "EquipamientoSemifijo.findByUsuarioCreo", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "EquipamientoSemifijo.findByFechaCreo", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "EquipamientoSemifijo.findByUsuarioModifico", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "EquipamientoSemifijo.findByFechaModifico", query = "SELECT e FROM EquipamientoSemifijo e WHERE e.fechaModifico = :fechaModifico")
})
public class      EquipamientoSemifijo 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1739570108;
    
    /**
    * The 'id equipamiento semifijo' Maps to COLUMN 'id_equipamiento_semifijo'
    */
    
    @Id
    @Column(name = "ID_EQUIPAMIENTO_SEMIFIJO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEquipamientoSemifijo;
    
    /**
    * The 'id avion' Maps to COLUMN 'id_avion'
    */
    
    @JoinColumn(name = "ID_AVION" , referencedColumnName = "ID_AVION")
    @ManyToOne(optional = false )
    private Avion avion;
    
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
    private int cantidad;
    
    /**
    * The 'peso' Maps to COLUMN 'peso'
    */
    
    @Basic(optional = false)
    @Column(name = "PESO" , nullable=false)
    private double peso;
    
    /**
    * The 'observaciones' Maps to COLUMN 'observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "OBSERVACIONES" , length=250, nullable=true)
    private String observaciones;
    
    /**
    * The 'status' Maps to COLUMN 'status'
    */
    
    @Basic(optional = false)
    @Column(name = "STATUS" , nullable=false)
    private Short status;
    
    /**
    * The 'usuario creo' Maps to COLUMN 'usuario_creo'
    */
    
    @Basic(optional = false)
    @Column(name = "USUARIO_CREO" , length=100, nullable=false)
    private String usuarioCreo;
    
    /**
    * The 'fecha creo' Maps to COLUMN 'fecha_creo'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA_CREO" , nullable=false)
    private java.sql.Timestamp fechaCreo;
    
    /**
    * The 'usuario modifico' Maps to COLUMN 'usuario_modifico'
    */
    
    @Basic(optional = false)
    @Column(name = "USUARIO_MODIFICO" , length=100, nullable=false)
    private String usuarioModifico;
    
    /**
    * The 'fecha modifico' Maps to COLUMN 'fecha_modifico'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA_MODIFICO" , nullable=false)
    private java.sql.Timestamp fechaModifico;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public EquipamientoSemifijo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdEquipamientoSemifijo() { return this.idEquipamientoSemifijo;}
    public void setIdEquipamientoSemifijo(Integer v) { this.idEquipamientoSemifijo = v; }
    
    public Avion getAvion(){ return this.avion ; }
    public void setAvion(Avion x){ this.avion = x; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public int getCantidad() { return this.cantidad;}
    public void setCantidad(int v) { this.cantidad = v; }
    
    public double getPeso() { return this.peso;}
    public void setPeso(double v) { this.peso = v; }
    
    public String getObservaciones() { return this.observaciones;}
    public void setObservaciones(String v) { this.observaciones = v; }
    
    public Short getStatus() { return this.status;}
    public void setStatus(Short v) { this.status = v; }
    
    public String getUsuarioCreo() { return this.usuarioCreo;}
    public void setUsuarioCreo(String v) { this.usuarioCreo = v; }
    
    public java.sql.Timestamp getFechaCreo() { return this.fechaCreo;}
    public void setFechaCreo(java.sql.Timestamp v) { this.fechaCreo = v; }
    
    public String getUsuarioModifico() { return this.usuarioModifico;}
    public void setUsuarioModifico(String v) { this.usuarioModifico = v; }
    
    public java.sql.Timestamp getFechaModifico() { return this.fechaModifico;}
    public void setFechaModifico(java.sql.Timestamp v) { this.fechaModifico = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idEquipamientoSemifijo).hashCode();
		hash += String.valueOf(avion).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(cantidad).hashCode();
		hash += String.valueOf(peso).hashCode();
		hash += String.valueOf(observaciones).hashCode();
		hash += String.valueOf(status).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(usuarioModifico).hashCode();
		hash += String.valueOf(fechaModifico).hashCode();
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
        if (!(o instanceof EquipamientoSemifijo)) {
            return false;
        }		
		EquipamientoSemifijo other = (EquipamientoSemifijo ) o;
		if (!Objects.equals(this.idEquipamientoSemifijo, other.idEquipamientoSemifijo)) { return false; }		
		if (!Objects.equals(this.avion, other.avion)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.cantidad, other.cantidad)) { return false; }		
		if (!Objects.equals(this.peso, other.peso)) { return false; }		
		if (!Objects.equals(this.observaciones, other.observaciones)) { return false; }		
		if (!Objects.equals(this.status, other.status)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.usuarioModifico, other.usuarioModifico)) { return false; }		
		if (!Objects.equals(this.fechaModifico, other.fechaModifico)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("EquipamientoSemifijo{");
		sb.append("idEquipamientoSemifijo" ).append("=").append(idEquipamientoSemifijo).append("|");
		sb.append("avion" ).append("=").append(avion).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("cantidad" ).append("=").append(cantidad).append("|");
		sb.append("peso" ).append("=").append(peso).append("|");
		sb.append("observaciones" ).append("=").append(observaciones).append("|");
		sb.append("status" ).append("=").append(status).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("usuarioModifico" ).append("=").append(usuarioModifico).append("|");
		sb.append("fechaModifico" ).append("=").append(fechaModifico).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idEquipamientoSemifijo).append(" ");

		return sb.toString().trim();
	}

}
