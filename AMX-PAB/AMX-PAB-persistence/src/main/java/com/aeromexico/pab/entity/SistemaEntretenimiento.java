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
 * Class for mapping JPA Entity of Table sistema_entretenimiento.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "sistema_entretenimiento")
@NamedQueries({
    @NamedQuery(name = "SistemaEntretenimiento.findAll", query = "SELECT s FROM SistemaEntretenimiento s")
    , @NamedQuery(name = "SistemaEntretenimiento.countAll", query = "SELECT COUNT(s) FROM SistemaEntretenimiento s")
    , @NamedQuery(name = "SistemaEntretenimiento.findByIdSistemaEntretenimiento", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.idSistemaEntretenimiento = :idSistemaEntretenimiento")
    , @NamedQuery(name = "SistemaEntretenimiento.findByTipo", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "SistemaEntretenimiento.findByVersion", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.version = :version")
    , @NamedQuery(name = "SistemaEntretenimiento.findByDescripcion", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "SistemaEntretenimiento.findByStatus", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.status = :status")
    , @NamedQuery(name = "SistemaEntretenimiento.findByUsuarioCreo", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "SistemaEntretenimiento.findByFechaCreo", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "SistemaEntretenimiento.findByUsuarioModifico", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "SistemaEntretenimiento.findByFechaModifico", query = "SELECT s FROM SistemaEntretenimiento s WHERE s.fechaModifico = :fechaModifico")
})
public class      SistemaEntretenimiento 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1073864237;
    
    /**
    * The 'id sistema entretenimiento' Maps to COLUMN 'id_sistema_entretenimiento'
    */
    
    @Id
    @Column(name = "ID_SISTEMA_ENTRETENIMIENTO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSistemaEntretenimiento;
    
    /**
    * The 'tipo' Maps to COLUMN 'tipo'
    */
    
    @Basic(optional = false)
    @Column(name = "TIPO" , length=50, nullable=false)
    private String tipo;
    
    /**
    * The 'version' Maps to COLUMN 'version'
    */
    
    @Basic(optional = true)
    @Column(name = "VERSION" , length=15, nullable=true)
    private String version;
    
    /**
    * The 'descripcion' Maps to COLUMN 'descripcion'
    */
    
    @Basic(optional = true)
    @Column(name = "DESCRIPCION" , length=200, nullable=true)
    private String descripcion;
    
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
    public SistemaEntretenimiento() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdSistemaEntretenimiento() { return this.idSistemaEntretenimiento;}
    public void setIdSistemaEntretenimiento(Integer v) { this.idSistemaEntretenimiento = v; }
    
    public String getTipo() { return this.tipo;}
    public void setTipo(String v) { this.tipo = v; }
    
    public String getVersion() { return this.version;}
    public void setVersion(String v) { this.version = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
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
		hash += String.valueOf(idSistemaEntretenimiento).hashCode();
		hash += String.valueOf(tipo).hashCode();
		hash += String.valueOf(version).hashCode();
		hash += String.valueOf(descripcion).hashCode();
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
        if (!(o instanceof SistemaEntretenimiento)) {
            return false;
        }		
		SistemaEntretenimiento other = (SistemaEntretenimiento ) o;
		if (!Objects.equals(this.idSistemaEntretenimiento, other.idSistemaEntretenimiento)) { return false; }		
		if (!Objects.equals(this.tipo, other.tipo)) { return false; }		
		if (!Objects.equals(this.version, other.version)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
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
		sb.append("SistemaEntretenimiento{");
		sb.append("idSistemaEntretenimiento" ).append("=").append(idSistemaEntretenimiento).append("|");
		sb.append("tipo" ).append("=").append(tipo).append("|");
		sb.append("version" ).append("=").append(version).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
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
		sb.append(" ").append(idSistemaEntretenimiento).append(" ");

		return sb.toString().trim();
	}

}
