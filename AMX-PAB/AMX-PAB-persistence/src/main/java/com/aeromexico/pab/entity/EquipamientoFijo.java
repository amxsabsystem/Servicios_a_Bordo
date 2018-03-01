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
 * Class for mapping JPA Entity of Table equipamiento_fijo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "equipamiento_fijo")
@NamedQueries({
    @NamedQuery(name = "EquipamientoFijo.findAll", query = "SELECT e FROM EquipamientoFijo e")
    , @NamedQuery(name = "EquipamientoFijo.countAll", query = "SELECT COUNT(e) FROM EquipamientoFijo e")
    , @NamedQuery(name = "EquipamientoFijo.findByIdEquipamientoFijo", query = "SELECT e FROM EquipamientoFijo e WHERE e.idEquipamientoFijo = :idEquipamientoFijo")
    , @NamedQuery(name = "EquipamientoFijo.findByavion", query = "SELECT e FROM EquipamientoFijo e WHERE e.avion = :avion")
    , @NamedQuery(name = "EquipamientoFijo.findByNombreEs", query = "SELECT e FROM EquipamientoFijo e WHERE e.nombreEs = :nombreEs")
    , @NamedQuery(name = "EquipamientoFijo.findByNombreEn", query = "SELECT e FROM EquipamientoFijo e WHERE e.nombreEn = :nombreEn")
    , @NamedQuery(name = "EquipamientoFijo.findByObservaciones", query = "SELECT e FROM EquipamientoFijo e WHERE e.observaciones = :observaciones")
    , @NamedQuery(name = "EquipamientoFijo.findByStatus", query = "SELECT e FROM EquipamientoFijo e WHERE e.status = :status")
    , @NamedQuery(name = "EquipamientoFijo.findByUsuarioCreo", query = "SELECT e FROM EquipamientoFijo e WHERE e.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "EquipamientoFijo.findByFechaCreo", query = "SELECT e FROM EquipamientoFijo e WHERE e.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "EquipamientoFijo.findByUsuarioModifico", query = "SELECT e FROM EquipamientoFijo e WHERE e.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "EquipamientoFijo.findByFechaModifico", query = "SELECT e FROM EquipamientoFijo e WHERE e.fechaModifico = :fechaModifico")
})
public class      EquipamientoFijo 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -776908074;
    
    /**
    * The 'id equipamiento fijo' Maps to COLUMN 'id_equipamiento_fijo'
    */
    
    @Id
    @Column(name = "ID_EQUIPAMIENTO_FIJO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEquipamientoFijo;
    
    /**
    * The 'id avion' Maps to COLUMN 'id_avion'
    */
    
    @JoinColumn(name = "ID_AVION" , referencedColumnName = "ID_AVION")
    @ManyToOne(optional = false )
    private Avion avion;
    
    /**
    * The 'nombre es' Maps to COLUMN 'nombre_es'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ES" , length=255, nullable=false)
    private String nombreEs;
    
    /**
    * The 'nombre en' Maps to COLUMN 'nombre_en'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_EN" , length=255, nullable=false)
    private String nombreEn;
    
    /**
    * The 'observaciones' Maps to COLUMN 'observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "OBSERVACIONES" , length=255, nullable=true)
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
    public EquipamientoFijo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdEquipamientoFijo() { return this.idEquipamientoFijo;}
    public void setIdEquipamientoFijo(Integer v) { this.idEquipamientoFijo = v; }
    
    public Avion getAvion(){ return this.avion ; }
    public void setAvion(Avion x){ this.avion = x; }
    
    public String getNombreEs() { return this.nombreEs;}
    public void setNombreEs(String v) { this.nombreEs = v; }
    
    public String getNombreEn() { return this.nombreEn;}
    public void setNombreEn(String v) { this.nombreEn = v; }
    
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
		hash += String.valueOf(idEquipamientoFijo).hashCode();
		hash += String.valueOf(avion).hashCode();
		hash += String.valueOf(nombreEs).hashCode();
		hash += String.valueOf(nombreEn).hashCode();
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
        if (!(o instanceof EquipamientoFijo)) {
            return false;
        }		
		EquipamientoFijo other = (EquipamientoFijo ) o;
		if (!Objects.equals(this.idEquipamientoFijo, other.idEquipamientoFijo)) { return false; }		
		if (!Objects.equals(this.avion, other.avion)) { return false; }		
		if (!Objects.equals(this.nombreEs, other.nombreEs)) { return false; }		
		if (!Objects.equals(this.nombreEn, other.nombreEn)) { return false; }		
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
		sb.append("EquipamientoFijo{");
		sb.append("idEquipamientoFijo" ).append("=").append(idEquipamientoFijo).append("|");
		sb.append("avion" ).append("=").append(avion).append("|");
		sb.append("nombreEs" ).append("=").append(nombreEs).append("|");
		sb.append("nombreEn" ).append("=").append(nombreEn).append("|");
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
		sb.append(" ").append(idEquipamientoFijo).append(" ");

		return sb.toString().trim();
	}

}
