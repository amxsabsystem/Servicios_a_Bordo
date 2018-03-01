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
 * Class for mapping JPA Entity of Table avion_audifono.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "avion_audifono")
@NamedQueries({
    @NamedQuery(name = "AvionAudifono.findAll", query = "SELECT a FROM AvionAudifono a")
    , @NamedQuery(name = "AvionAudifono.countAll", query = "SELECT COUNT(a) FROM AvionAudifono a")
    , @NamedQuery(name = "AvionAudifono.findByIdAvionAudifono", query = "SELECT a FROM AvionAudifono a WHERE a.idAvionAudifono = :idAvionAudifono")
    , @NamedQuery(name = "AvionAudifono.findByavion", query = "SELECT a FROM AvionAudifono a WHERE a.avion = :avion")
    , @NamedQuery(name = "AvionAudifono.findByclase", query = "SELECT a FROM AvionAudifono a WHERE a.clase = :clase")
    , @NamedQuery(name = "AvionAudifono.findBymaterial", query = "SELECT a FROM AvionAudifono a WHERE a.material = :material")
    , @NamedQuery(name = "AvionAudifono.findByStatus", query = "SELECT a FROM AvionAudifono a WHERE a.status = :status")
    , @NamedQuery(name = "AvionAudifono.findByUsuarioCreo", query = "SELECT a FROM AvionAudifono a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "AvionAudifono.findByFechaCreo", query = "SELECT a FROM AvionAudifono a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "AvionAudifono.findByUsuarioModifico", query = "SELECT a FROM AvionAudifono a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "AvionAudifono.findByFechaModifico", query = "SELECT a FROM AvionAudifono a WHERE a.fechaModifico = :fechaModifico")
})
public class      AvionAudifono 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1938811535;
    
    /**
    * The 'id avion audifono' Maps to COLUMN 'id_avion_audifono'
    */
    
    @Id
    @Column(name = "ID_AVION_AUDIFONO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAvionAudifono;
    
    /**
    * The 'id avion' Maps to COLUMN 'id_avion'
    */
    
    @JoinColumn(name = "ID_AVION" , referencedColumnName = "ID_AVION")
    @ManyToOne(optional = false )
    private Avion avion;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material material;
    
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
    public AvionAudifono() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAvionAudifono() { return this.idAvionAudifono;}
    public void setIdAvionAudifono(Integer v) { this.idAvionAudifono = v; }
    
    public Avion getAvion(){ return this.avion ; }
    public void setAvion(Avion x){ this.avion = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
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
		hash += String.valueOf(idAvionAudifono).hashCode();
		hash += String.valueOf(avion).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(material).hashCode();
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
        if (!(o instanceof AvionAudifono)) {
            return false;
        }		
		AvionAudifono other = (AvionAudifono ) o;
		if (!Objects.equals(this.idAvionAudifono, other.idAvionAudifono)) { return false; }		
		if (!Objects.equals(this.avion, other.avion)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
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
		sb.append("AvionAudifono{");
		sb.append("idAvionAudifono" ).append("=").append(idAvionAudifono).append("|");
		sb.append("avion" ).append("=").append(avion).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("material" ).append("=").append(material).append("|");
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
		sb.append(" ").append(idAvionAudifono).append(" ");

		return sb.toString().trim();
	}

}
