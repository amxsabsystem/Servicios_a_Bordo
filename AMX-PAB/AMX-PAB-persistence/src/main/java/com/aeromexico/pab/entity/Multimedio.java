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
 * Class for mapping JPA Entity of Table multimedio.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "multimedio")
@NamedQueries({
    @NamedQuery(name = "Multimedio.findAll", query = "SELECT m FROM Multimedio m")
    , @NamedQuery(name = "Multimedio.countAll", query = "SELECT COUNT(m) FROM Multimedio m")
    , @NamedQuery(name = "Multimedio.findByIdMultimedio", query = "SELECT m FROM Multimedio m WHERE m.idMultimedio = :idMultimedio")
    , @NamedQuery(name = "Multimedio.findByHashMd5", query = "SELECT m FROM Multimedio m WHERE m.hashMd5 = :hashMd5")
    , @NamedQuery(name = "Multimedio.findByUrl", query = "SELECT m FROM Multimedio m WHERE m.url = :url")
    , @NamedQuery(name = "Multimedio.findByUrlLocal", query = "SELECT m FROM Multimedio m WHERE m.urlLocal = :urlLocal")
    , @NamedQuery(name = "Multimedio.findByMimeType", query = "SELECT m FROM Multimedio m WHERE m.mimeType = :mimeType")
    , @NamedQuery(name = "Multimedio.findBySize", query = "SELECT m FROM Multimedio m WHERE m.size = :size")
    , @NamedQuery(name = "Multimedio.findByFileName", query = "SELECT m FROM Multimedio m WHERE m.fileName = :fileName")
    , @NamedQuery(name = "Multimedio.findByStatus", query = "SELECT m FROM Multimedio m WHERE m.status = :status")
    , @NamedQuery(name = "Multimedio.findByUsuarioCreo", query = "SELECT m FROM Multimedio m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Multimedio.findByFechaCreo", query = "SELECT m FROM Multimedio m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Multimedio.findByUsuarioModifico", query = "SELECT m FROM Multimedio m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Multimedio.findByFechaModifico", query = "SELECT m FROM Multimedio m WHERE m.fechaModifico = :fechaModifico")
})
public class      Multimedio 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1262089817;
    
    /**
    * The 'id multimedio' Maps to COLUMN 'id_multimedio'
    */
    
    @Id
    @Column(name = "ID_MULTIMEDIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMultimedio;
    
    /**
    * The 'hash md5' Maps to COLUMN 'hash_md5'
    */
    
    @Basic(optional = false)
    @Column(name = "HASH_MD5" , length=32, nullable=false)
    private String hashMd5;
    
    /**
    * The 'url' Maps to COLUMN 'url'
    */
    
    @Basic(optional = false)
    @Column(name = "URL" , length=255, nullable=false)
    private String url;
    
    /**
    * The 'url local' Maps to COLUMN 'url_local'
    */
    
    @Basic(optional = false)
    @Column(name = "URL_LOCAL" , length=255, nullable=false)
    private String urlLocal;
    
    /**
    * The 'mime type' Maps to COLUMN 'mime_type'
    */
    
    @Basic(optional = false)
    @Column(name = "MIME_TYPE" , length=64, nullable=false)
    private String mimeType;
    
    /**
    * The 'size' Maps to COLUMN 'size'
    */
    
    @Basic(optional = false)
    @Column(name = "SIZE" , nullable=false)
    private int size;
    
    /**
    * The 'file name' Maps to COLUMN 'file_name'
    */
    
    @Basic(optional = true)
    @Column(name = "FILE_NAME" , length=255, nullable=true)
    private String fileName;
    
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
    * Map the relation to kit_master table where has id_multimedio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multimedio")
    private List<KitMaster> kitMasterHasMultimedioList;
    
    /** 
    * Map the relation to TSU table where has id_multimedio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multimedio")
    private List<Tsu> tsuHasMultimedioList;
    
    /** 
    * Map the relation to evidencia table where has id_multimedio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "multimedio")
    private List<Evidencia> evidenciaHasMultimedioList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Multimedio() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdMultimedio() { return this.idMultimedio;}
    public void setIdMultimedio(Integer v) { this.idMultimedio = v; }
    
    public String getHashMd5() { return this.hashMd5;}
    public void setHashMd5(String v) { this.hashMd5 = v; }
    
    public String getUrl() { return this.url;}
    public void setUrl(String v) { this.url = v; }
    
    public String getUrlLocal() { return this.urlLocal;}
    public void setUrlLocal(String v) { this.urlLocal = v; }
    
    public String getMimeType() { return this.mimeType;}
    public void setMimeType(String v) { this.mimeType = v; }
    
    public int getSize() { return this.size;}
    public void setSize(int v) { this.size = v; }
    
    public String getFileName() { return this.fileName;}
    public void setFileName(String v) { this.fileName = v; }
    
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
    public List<KitMaster> getKitMasterHasMultimedioList(){ return this.kitMasterHasMultimedioList; }
    public void setKitMasterHasMultimedioList(List<KitMaster> v){ this.kitMasterHasMultimedioList = v; }
    
    public List<Tsu> getTsuHasMultimedioList(){ return this.tsuHasMultimedioList; }
    public void setTsuHasMultimedioList(List<Tsu> v){ this.tsuHasMultimedioList = v; }
    
    public List<Evidencia> getEvidenciaHasMultimedioList(){ return this.evidenciaHasMultimedioList; }
    public void setEvidenciaHasMultimedioList(List<Evidencia> v){ this.evidenciaHasMultimedioList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idMultimedio).hashCode();
		hash += String.valueOf(hashMd5).hashCode();
		hash += String.valueOf(url).hashCode();
		hash += String.valueOf(urlLocal).hashCode();
		hash += String.valueOf(mimeType).hashCode();
		hash += String.valueOf(size).hashCode();
		hash += String.valueOf(fileName).hashCode();
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
        if (!(o instanceof Multimedio)) {
            return false;
        }		
		Multimedio other = (Multimedio ) o;
		if (!Objects.equals(this.idMultimedio, other.idMultimedio)) { return false; }		
		if (!Objects.equals(this.hashMd5, other.hashMd5)) { return false; }		
		if (!Objects.equals(this.url, other.url)) { return false; }		
		if (!Objects.equals(this.urlLocal, other.urlLocal)) { return false; }		
		if (!Objects.equals(this.mimeType, other.mimeType)) { return false; }		
		if (!Objects.equals(this.size, other.size)) { return false; }		
		if (!Objects.equals(this.fileName, other.fileName)) { return false; }		
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
		sb.append("Multimedio{");
		sb.append("idMultimedio" ).append("=").append(idMultimedio).append("|");
		sb.append("hashMd5" ).append("=").append(hashMd5).append("|");
		sb.append("url" ).append("=").append(url).append("|");
		sb.append("urlLocal" ).append("=").append(urlLocal).append("|");
		sb.append("mimeType" ).append("=").append(mimeType).append("|");
		sb.append("size" ).append("=").append(size).append("|");
		sb.append("fileName" ).append("=").append(fileName).append("|");
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
		sb.append(" ").append(idMultimedio).append(" ");

		return sb.toString().trim();
	}

}
