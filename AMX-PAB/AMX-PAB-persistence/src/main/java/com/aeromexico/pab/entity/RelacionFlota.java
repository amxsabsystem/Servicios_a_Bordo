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
 * Class for mapping JPA Entity of Table relacion_flota.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "relacion_flota")
@NamedQueries({
    @NamedQuery(name = "RelacionFlota.findAll", query = "SELECT r FROM RelacionFlota r")
    , @NamedQuery(name = "RelacionFlota.countAll", query = "SELECT COUNT(r) FROM RelacionFlota r")
    , @NamedQuery(name = "RelacionFlota.findByIdRelacionFlota", query = "SELECT r FROM RelacionFlota r WHERE r.idRelacionFlota = :idRelacionFlota")
    , @NamedQuery(name = "RelacionFlota.findByRevision", query = "SELECT r FROM RelacionFlota r WHERE r.revision = :revision")
    , @NamedQuery(name = "RelacionFlota.findByFecha", query = "SELECT r FROM RelacionFlota r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RelacionFlota.findByStatus", query = "SELECT r FROM RelacionFlota r WHERE r.status = :status")
    , @NamedQuery(name = "RelacionFlota.findByUsuarioCreo", query = "SELECT r FROM RelacionFlota r WHERE r.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "RelacionFlota.findByFechaCreo", query = "SELECT r FROM RelacionFlota r WHERE r.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "RelacionFlota.findByUsuarioModifico", query = "SELECT r FROM RelacionFlota r WHERE r.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "RelacionFlota.findByFechaModifico", query = "SELECT r FROM RelacionFlota r WHERE r.fechaModifico = :fechaModifico")
})
public class      RelacionFlota 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 235114884;
    
    /**
    * The 'id relacion flota' Maps to COLUMN 'id_relacion_flota'
    */
    
    @Id
    @Column(name = "ID_RELACION_FLOTA" , nullable=false  )
    private Integer idRelacionFlota;
    
    /**
    * The 'revision' Maps to COLUMN 'revision'
    */
    
    @Basic(optional = false)
    @Column(name = "REVISION" , nullable=false)
    private int revision;
    
    /**
    * The 'fecha' Maps to COLUMN 'fecha'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA" , nullable=false)
    private java.sql.Date fecha;
    
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
    public RelacionFlota() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdRelacionFlota() { return this.idRelacionFlota;}
    public void setIdRelacionFlota(Integer v) { this.idRelacionFlota = v; }
    
    public int getRevision() { return this.revision;}
    public void setRevision(int v) { this.revision = v; }
    
    public java.sql.Date getFecha() { return this.fecha;}
    public void setFecha(java.sql.Date v) { this.fecha = v; }
    
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
		hash += String.valueOf(idRelacionFlota).hashCode();
		hash += String.valueOf(revision).hashCode();
		hash += String.valueOf(fecha).hashCode();
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
        if (!(o instanceof RelacionFlota)) {
            return false;
        }		
		RelacionFlota other = (RelacionFlota ) o;
		if (!Objects.equals(this.idRelacionFlota, other.idRelacionFlota)) { return false; }		
		if (!Objects.equals(this.revision, other.revision)) { return false; }		
		if (!Objects.equals(this.fecha, other.fecha)) { return false; }		
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
		sb.append("RelacionFlota{");
		sb.append("idRelacionFlota" ).append("=").append(idRelacionFlota).append("|");
		sb.append("revision" ).append("=").append(revision).append("|");
		sb.append("fecha" ).append("=").append(fecha).append("|");
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
		sb.append(" ").append(idRelacionFlota).append(" ");

		return sb.toString().trim();
	}

}
