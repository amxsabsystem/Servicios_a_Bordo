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
 * Class for mapping JPA Entity of Table archivo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "archivo")
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a")
    , @NamedQuery(name = "Archivo.countAll", query = "SELECT COUNT(a) FROM Archivo a")
    , @NamedQuery(name = "Archivo.findByIdArchivo", query = "SELECT a FROM Archivo a WHERE a.idArchivo = :idArchivo")
    , @NamedQuery(name = "Archivo.findByproceso", query = "SELECT a FROM Archivo a WHERE a.proceso = :proceso")
    , @NamedQuery(name = "Archivo.findByNombreArchivo", query = "SELECT a FROM Archivo a WHERE a.nombreArchivo = :nombreArchivo")
    , @NamedQuery(name = "Archivo.findByUrlArchivo", query = "SELECT a FROM Archivo a WHERE a.urlArchivo = :urlArchivo")
    , @NamedQuery(name = "Archivo.findByMimeType", query = "SELECT a FROM Archivo a WHERE a.mimeType = :mimeType")
    , @NamedQuery(name = "Archivo.findByStatus", query = "SELECT a FROM Archivo a WHERE a.status = :status")
    , @NamedQuery(name = "Archivo.findByUsuarioCreo", query = "SELECT a FROM Archivo a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Archivo.findByFechaCreo", query = "SELECT a FROM Archivo a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Archivo.findByUsuarioModifico", query = "SELECT a FROM Archivo a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Archivo.findByFechaModifico", query = "SELECT a FROM Archivo a WHERE a.fechaModifico = :fechaModifico")
})
public class      Archivo 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -748101428;
    
    /**
    * The 'id archivo' Maps to COLUMN 'id_archivo'
    */
    
    @Id
    @Column(name = "ID_ARCHIVO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idArchivo;
    
    /**
    * The 'id proceso' Maps to COLUMN 'id_proceso'
    */
    
    @JoinColumn(name = "ID_PROCESO" , referencedColumnName = "ID_PROCESO")
    @ManyToOne(optional = false )
    private Proceso proceso;
    
    /**
    * The 'nombre archivo' Maps to COLUMN 'nombre_archivo'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ARCHIVO" , length=100, nullable=false)
    private String nombreArchivo;
    
    /**
    * The 'url archivo' Maps to COLUMN 'url_archivo'
    */
    
    @Basic(optional = false)
    @Column(name = "URL_ARCHIVO" , length=512, nullable=false)
    private String urlArchivo;
    
    /**
    * The 'mime type' Maps to COLUMN 'mime_type'
    */
    
    @Basic(optional = false)
    @Column(name = "MIME_TYPE" , length=64, nullable=false)
    private String mimeType;
    
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
    public Archivo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdArchivo() { return this.idArchivo;}
    public void setIdArchivo(Integer v) { this.idArchivo = v; }
    
    public Proceso getProceso(){ return this.proceso ; }
    public void setProceso(Proceso x){ this.proceso = x; }
    
    public String getNombreArchivo() { return this.nombreArchivo;}
    public void setNombreArchivo(String v) { this.nombreArchivo = v; }
    
    public String getUrlArchivo() { return this.urlArchivo;}
    public void setUrlArchivo(String v) { this.urlArchivo = v; }
    
    public String getMimeType() { return this.mimeType;}
    public void setMimeType(String v) { this.mimeType = v; }
    
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
		hash += String.valueOf(idArchivo).hashCode();
		hash += String.valueOf(proceso).hashCode();
		hash += String.valueOf(nombreArchivo).hashCode();
		hash += String.valueOf(urlArchivo).hashCode();
		hash += String.valueOf(mimeType).hashCode();
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
        if (!(o instanceof Archivo)) {
            return false;
        }		
		Archivo other = (Archivo ) o;
		if (!Objects.equals(this.idArchivo, other.idArchivo)) { return false; }		
		if (!Objects.equals(this.proceso, other.proceso)) { return false; }		
		if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) { return false; }		
		if (!Objects.equals(this.urlArchivo, other.urlArchivo)) { return false; }		
		if (!Objects.equals(this.mimeType, other.mimeType)) { return false; }		
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
		sb.append("Archivo{");
		sb.append("idArchivo" ).append("=").append(idArchivo).append("|");
		sb.append("proceso" ).append("=").append(proceso).append("|");
		sb.append("nombreArchivo" ).append("=").append(nombreArchivo).append("|");
		sb.append("urlArchivo" ).append("=").append(urlArchivo).append("|");
		sb.append("mimeType" ).append("=").append(mimeType).append("|");
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
		sb.append(" ").append(idArchivo).append(" ");

		return sb.toString().trim();
	}

}
