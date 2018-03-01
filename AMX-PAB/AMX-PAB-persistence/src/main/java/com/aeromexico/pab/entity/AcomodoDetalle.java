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
 * Class for mapping JPA Entity of Table acomodo_detalle.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "acomodo_detalle")
@NamedQueries({
    @NamedQuery(name = "AcomodoDetalle.findAll", query = "SELECT a FROM AcomodoDetalle a")
    , @NamedQuery(name = "AcomodoDetalle.countAll", query = "SELECT COUNT(a) FROM AcomodoDetalle a")
    , @NamedQuery(name = "AcomodoDetalle.findByIdAcomodoDetalle", query = "SELECT a FROM AcomodoDetalle a WHERE a.idAcomodoDetalle = :idAcomodoDetalle")
    , @NamedQuery(name = "AcomodoDetalle.findByNoRev", query = "SELECT a FROM AcomodoDetalle a WHERE a.noRev = :noRev")
    , @NamedQuery(name = "AcomodoDetalle.findByAnioRev", query = "SELECT a FROM AcomodoDetalle a WHERE a.anioRev = :anioRev")
    , @NamedQuery(name = "AcomodoDetalle.findByUrlAcomodo", query = "SELECT a FROM AcomodoDetalle a WHERE a.urlAcomodo = :urlAcomodo")
    , @NamedQuery(name = "AcomodoDetalle.findByMimeType", query = "SELECT a FROM AcomodoDetalle a WHERE a.mimeType = :mimeType")
    , @NamedQuery(name = "AcomodoDetalle.findByStatus", query = "SELECT a FROM AcomodoDetalle a WHERE a.status = :status")
    , @NamedQuery(name = "AcomodoDetalle.findByUsuarioCreo", query = "SELECT a FROM AcomodoDetalle a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "AcomodoDetalle.findByFechaCreo", query = "SELECT a FROM AcomodoDetalle a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "AcomodoDetalle.findByUsuarioModifico", query = "SELECT a FROM AcomodoDetalle a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "AcomodoDetalle.findByFechaModifico", query = "SELECT a FROM AcomodoDetalle a WHERE a.fechaModifico = :fechaModifico")
})
public class      AcomodoDetalle 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1162472946;
    
    /**
    * The 'id acomodo detalle' Maps to COLUMN 'id_acomodo_detalle'
    */
    
    @Id
    @Column(name = "ID_ACOMODO_DETALLE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAcomodoDetalle;
    
    /**
    * The 'no rev' Maps to COLUMN 'no_rev'
    */
    
    @Basic(optional = false)
    @Column(name = "NO_REV" , nullable=false)
    private Short noRev;
    
    /**
    * The 'anio rev' Maps to COLUMN 'anio_rev'
    */
    
    @Basic(optional = false)
    @Column(name = "ANIO_REV" , nullable=false)
    private int anioRev;
    
    /**
    * The 'url acomodo' Maps to COLUMN 'url_acomodo'
    */
    
    @Basic(optional = false)
    @Column(name = "URL_ACOMODO" , length=255, nullable=false)
    private String urlAcomodo;
    
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
    /** 
    * Map the relation to acomodo table where has id_acomodo_detalle object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acomodoDetalle")
    private List<Acomodo> acomodoHasAcomodoDetalleList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public AcomodoDetalle() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAcomodoDetalle() { return this.idAcomodoDetalle;}
    public void setIdAcomodoDetalle(Integer v) { this.idAcomodoDetalle = v; }
    
    public Short getNoRev() { return this.noRev;}
    public void setNoRev(Short v) { this.noRev = v; }
    
    public int getAnioRev() { return this.anioRev;}
    public void setAnioRev(int v) { this.anioRev = v; }
    
    public String getUrlAcomodo() { return this.urlAcomodo;}
    public void setUrlAcomodo(String v) { this.urlAcomodo = v; }
    
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
    public List<Acomodo> getAcomodoHasAcomodoDetalleList(){ return this.acomodoHasAcomodoDetalleList; }
    public void setAcomodoHasAcomodoDetalleList(List<Acomodo> v){ this.acomodoHasAcomodoDetalleList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idAcomodoDetalle).hashCode();
		hash += String.valueOf(noRev).hashCode();
		hash += String.valueOf(anioRev).hashCode();
		hash += String.valueOf(urlAcomodo).hashCode();
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
        if (!(o instanceof AcomodoDetalle)) {
            return false;
        }		
		AcomodoDetalle other = (AcomodoDetalle ) o;
		if (!Objects.equals(this.idAcomodoDetalle, other.idAcomodoDetalle)) { return false; }		
		if (!Objects.equals(this.noRev, other.noRev)) { return false; }		
		if (!Objects.equals(this.anioRev, other.anioRev)) { return false; }		
		if (!Objects.equals(this.urlAcomodo, other.urlAcomodo)) { return false; }		
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
		sb.append("AcomodoDetalle{");
		sb.append("idAcomodoDetalle" ).append("=").append(idAcomodoDetalle).append("|");
		sb.append("noRev" ).append("=").append(noRev).append("|");
		sb.append("anioRev" ).append("=").append(anioRev).append("|");
		sb.append("urlAcomodo" ).append("=").append(urlAcomodo).append("|");
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
		sb.append(" ").append(idAcomodoDetalle).append(" ");

		return sb.toString().trim();
	}

}
