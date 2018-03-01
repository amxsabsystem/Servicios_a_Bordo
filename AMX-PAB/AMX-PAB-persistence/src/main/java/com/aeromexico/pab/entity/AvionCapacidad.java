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
 * Class for mapping JPA Entity of Table avion_capacidad.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "avion_capacidad")
@NamedQueries({
    @NamedQuery(name = "AvionCapacidad.findAll", query = "SELECT a FROM AvionCapacidad a")
    , @NamedQuery(name = "AvionCapacidad.countAll", query = "SELECT COUNT(a) FROM AvionCapacidad a")
    , @NamedQuery(name = "AvionCapacidad.findByCapacidad", query = "SELECT a FROM AvionCapacidad a WHERE a.capacidad = :capacidad")
    , @NamedQuery(name = "AvionCapacidad.findByStatus", query = "SELECT a FROM AvionCapacidad a WHERE a.status = :status")
    , @NamedQuery(name = "AvionCapacidad.findByUsuarioCreo", query = "SELECT a FROM AvionCapacidad a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "AvionCapacidad.findByFechaCreo", query = "SELECT a FROM AvionCapacidad a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "AvionCapacidad.findByUsuarioModifico", query = "SELECT a FROM AvionCapacidad a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "AvionCapacidad.findByFechaModifico", query = "SELECT a FROM AvionCapacidad a WHERE a.fechaModifico = :fechaModifico")
    , @NamedQuery(name = "AvionCapacidad.findByavion", query = "SELECT a FROM AvionCapacidad a WHERE a.avion = :avion")
    , @NamedQuery(name = "AvionCapacidad.findByclase", query = "SELECT a FROM AvionCapacidad a WHERE a.clase = :clase")
    , @NamedQuery(name = "AvionCapacidad.findByAvionCapacidadPK", query = "SELECT a FROM AvionCapacidad a WHERE a.avionCapacidadPK = :avionCapacidadPK")
})
public class      AvionCapacidad 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1985322598;
    
    /**
    * The 'capacidad' Maps to COLUMN 'capacidad'
    */
    
    @Basic(optional = false)
    @Column(name = "CAPACIDAD" , nullable=false)
    private int capacidad;
    
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
    
    /**
    * The 'id avion' Maps to COLUMN 'id_avion'
    */
    
    @JoinColumn(name = "ID_AVION" , referencedColumnName = "ID_AVION", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private Avion avion;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'avion capacidad P K' Maps to COLUMN 'avion_capacidad_P_K'
    */
    
    @EmbeddedId
    private AvionCapacidadPK avionCapacidadPK;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public AvionCapacidad() {
    }
    
    /**
     * Getters and Setters
     */
    public int getCapacidad() { return this.capacidad;}
    public void setCapacidad(int v) { this.capacidad = v; }
    
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
    
    public Avion getAvion(){ return this.avion ; }
    public void setAvion(Avion x){ this.avion = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public AvionCapacidadPK getAvionCapacidadPK() { return this.avionCapacidadPK;}
    public void setAvionCapacidadPK(AvionCapacidadPK v) { this.avionCapacidadPK = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(capacidad).hashCode();
		hash += String.valueOf(status).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(usuarioModifico).hashCode();
		hash += String.valueOf(fechaModifico).hashCode();
		hash += String.valueOf(avion).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(avionCapacidadPK).hashCode();
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
        if (!(o instanceof AvionCapacidad)) {
            return false;
        }		
		AvionCapacidad other = (AvionCapacidad ) o;
		if (!Objects.equals(this.capacidad, other.capacidad)) { return false; }		
		if (!Objects.equals(this.status, other.status)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.usuarioModifico, other.usuarioModifico)) { return false; }		
		if (!Objects.equals(this.fechaModifico, other.fechaModifico)) { return false; }		
		if (!Objects.equals(this.avion, other.avion)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.avionCapacidadPK, other.avionCapacidadPK)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("AvionCapacidad{");
		sb.append("capacidad" ).append("=").append(capacidad).append("|");
		sb.append("status" ).append("=").append(status).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("usuarioModifico" ).append("=").append(usuarioModifico).append("|");
		sb.append("fechaModifico" ).append("=").append(fechaModifico).append("|");
		sb.append("avion" ).append("=").append(avion).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("avionCapacidadPK" ).append("=").append(avionCapacidadPK).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();

		return sb.toString().trim();
	}

}
