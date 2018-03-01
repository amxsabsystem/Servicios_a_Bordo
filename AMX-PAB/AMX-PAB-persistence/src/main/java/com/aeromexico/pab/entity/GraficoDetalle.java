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
 * Class for mapping JPA Entity of Table grafico_detalle.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "grafico_detalle")
@NamedQueries({
    @NamedQuery(name = "GraficoDetalle.findAll", query = "SELECT g FROM GraficoDetalle g")
    , @NamedQuery(name = "GraficoDetalle.countAll", query = "SELECT COUNT(g) FROM GraficoDetalle g")
    , @NamedQuery(name = "GraficoDetalle.findByIdGraficoDetalle", query = "SELECT g FROM GraficoDetalle g WHERE g.idGraficoDetalle = :idGraficoDetalle")
    , @NamedQuery(name = "GraficoDetalle.findBygrafico", query = "SELECT g FROM GraficoDetalle g WHERE g.grafico = :grafico")
    , @NamedQuery(name = "GraficoDetalle.findByNoRev", query = "SELECT g FROM GraficoDetalle g WHERE g.noRev = :noRev")
    , @NamedQuery(name = "GraficoDetalle.findByAnioRev", query = "SELECT g FROM GraficoDetalle g WHERE g.anioRev = :anioRev")
    , @NamedQuery(name = "GraficoDetalle.findByUrlGrafico", query = "SELECT g FROM GraficoDetalle g WHERE g.urlGrafico = :urlGrafico")
    , @NamedQuery(name = "GraficoDetalle.findByMimeType", query = "SELECT g FROM GraficoDetalle g WHERE g.mimeType = :mimeType")
    , @NamedQuery(name = "GraficoDetalle.findByStatus", query = "SELECT g FROM GraficoDetalle g WHERE g.status = :status")
    , @NamedQuery(name = "GraficoDetalle.findByUsuarioCreo", query = "SELECT g FROM GraficoDetalle g WHERE g.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "GraficoDetalle.findByFechaCreo", query = "SELECT g FROM GraficoDetalle g WHERE g.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "GraficoDetalle.findByUsuarioModifico", query = "SELECT g FROM GraficoDetalle g WHERE g.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "GraficoDetalle.findByFechaModifico", query = "SELECT g FROM GraficoDetalle g WHERE g.fechaModifico = :fechaModifico")
})
public class      GraficoDetalle 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -373180035;
    
    /**
    * The 'id grafico detalle' Maps to COLUMN 'id_grafico_detalle'
    */
    
    @Id
    @Column(name = "ID_GRAFICO_DETALLE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idGraficoDetalle;
    
    /**
    * The 'id grafico' Maps to COLUMN 'id_grafico'
    */
    
    @JoinColumn(name = "ID_GRAFICO" , referencedColumnName = "ID_GRAFICO")
    @ManyToOne(optional = false )
    private Grafico grafico;
    
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
    * The 'url grafico' Maps to COLUMN 'url_grafico'
    */
    
    @Basic(optional = false)
    @Column(name = "URL_GRAFICO" , length=255, nullable=false)
    private String urlGrafico;
    
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
    public GraficoDetalle() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdGraficoDetalle() { return this.idGraficoDetalle;}
    public void setIdGraficoDetalle(Integer v) { this.idGraficoDetalle = v; }
    
    public Grafico getGrafico(){ return this.grafico ; }
    public void setGrafico(Grafico x){ this.grafico = x; }
    
    public Short getNoRev() { return this.noRev;}
    public void setNoRev(Short v) { this.noRev = v; }
    
    public int getAnioRev() { return this.anioRev;}
    public void setAnioRev(int v) { this.anioRev = v; }
    
    public String getUrlGrafico() { return this.urlGrafico;}
    public void setUrlGrafico(String v) { this.urlGrafico = v; }
    
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
		hash += String.valueOf(idGraficoDetalle).hashCode();
		hash += String.valueOf(grafico).hashCode();
		hash += String.valueOf(noRev).hashCode();
		hash += String.valueOf(anioRev).hashCode();
		hash += String.valueOf(urlGrafico).hashCode();
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
        if (!(o instanceof GraficoDetalle)) {
            return false;
        }		
		GraficoDetalle other = (GraficoDetalle ) o;
		if (!Objects.equals(this.idGraficoDetalle, other.idGraficoDetalle)) { return false; }		
		if (!Objects.equals(this.grafico, other.grafico)) { return false; }		
		if (!Objects.equals(this.noRev, other.noRev)) { return false; }		
		if (!Objects.equals(this.anioRev, other.anioRev)) { return false; }		
		if (!Objects.equals(this.urlGrafico, other.urlGrafico)) { return false; }		
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
		sb.append("GraficoDetalle{");
		sb.append("idGraficoDetalle" ).append("=").append(idGraficoDetalle).append("|");
		sb.append("grafico" ).append("=").append(grafico).append("|");
		sb.append("noRev" ).append("=").append(noRev).append("|");
		sb.append("anioRev" ).append("=").append(anioRev).append("|");
		sb.append("urlGrafico" ).append("=").append(urlGrafico).append("|");
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
		sb.append(" ").append(noRev).append(" ");
		sb.append(" ").append(anioRev).append(" ");

		return sb.toString().trim();
	}

}
