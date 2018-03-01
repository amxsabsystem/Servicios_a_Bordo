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
 * Class for mapping JPA Entity of Table kit_master.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "kit_master")
@NamedQueries({
    @NamedQuery(name = "KitMaster.findAll", query = "SELECT k FROM KitMaster k")
    , @NamedQuery(name = "KitMaster.countAll", query = "SELECT COUNT(k) FROM KitMaster k")
    , @NamedQuery(name = "KitMaster.findByCveKitMaster", query = "SELECT k FROM KitMaster k WHERE k.cveKitMaster = :cveKitMaster")
    , @NamedQuery(name = "KitMaster.findByidUnidadMedida", query = "SELECT k FROM KitMaster k WHERE k.idUnidadMedida = :idUnidadMedida")
    , @NamedQuery(name = "KitMaster.findByidInstruccionesNacionales", query = "SELECT k FROM KitMaster k WHERE k.idInstruccionesNacionales = :idInstruccionesNacionales")
    , @NamedQuery(name = "KitMaster.findByidInstruccionesInternac", query = "SELECT k FROM KitMaster k WHERE k.idInstruccionesInternac = :idInstruccionesInternac")
    , @NamedQuery(name = "KitMaster.findByidTipoKit", query = "SELECT k FROM KitMaster k WHERE k.idTipoKit = :idTipoKit")
    , @NamedQuery(name = "KitMaster.findBymultimedio", query = "SELECT k FROM KitMaster k WHERE k.multimedio = :multimedio")
    , @NamedQuery(name = "KitMaster.findByNombreEs", query = "SELECT k FROM KitMaster k WHERE k.nombreEs = :nombreEs")
    , @NamedQuery(name = "KitMaster.findByNombreEn", query = "SELECT k FROM KitMaster k WHERE k.nombreEn = :nombreEn")
    , @NamedQuery(name = "KitMaster.findByUrlMultimedia", query = "SELECT k FROM KitMaster k WHERE k.urlMultimedia = :urlMultimedia")
    , @NamedQuery(name = "KitMaster.findByMimeType", query = "SELECT k FROM KitMaster k WHERE k.mimeType = :mimeType")
    , @NamedQuery(name = "KitMaster.findByContenedor", query = "SELECT k FROM KitMaster k WHERE k.contenedor = :contenedor")
    , @NamedQuery(name = "KitMaster.findByStatus", query = "SELECT k FROM KitMaster k WHERE k.status = :status")
    , @NamedQuery(name = "KitMaster.findByUsuarioCreo", query = "SELECT k FROM KitMaster k WHERE k.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "KitMaster.findByFechaCreo", query = "SELECT k FROM KitMaster k WHERE k.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "KitMaster.findByUsuarioModifico", query = "SELECT k FROM KitMaster k WHERE k.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "KitMaster.findByFechaModifico", query = "SELECT k FROM KitMaster k WHERE k.fechaModifico = :fechaModifico")
})
public class      KitMaster 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1573473259;
    
    /**
    * The 'cve kit master' Maps to COLUMN 'cve_kit_master'
    */
    
    @Id
    @Column(name = "CVE_KIT_MASTER" , length=50, nullable=false  )
    private String cveKitMaster;
    
    /**
    * The 'id unidad medida' Maps to COLUMN 'id_unidad_medida'
    */
    
    @JoinColumn(name = "ID_UNIDAD_MEDIDA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idUnidadMedida;
    
    /**
    * The 'id instrucciones nacionales' Maps to COLUMN 'id_instrucciones_nacionales'
    */
    
    @JoinColumn(name = "ID_INSTRUCCIONES_NACIONALES" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idInstruccionesNacionales;
    
    /**
    * The 'id instrucciones internac' Maps to COLUMN 'id_instrucciones_internac'
    */
    
    @JoinColumn(name = "ID_INSTRUCCIONES_INTERNAC" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idInstruccionesInternac;
    
    /**
    * The 'id tipo kit' Maps to COLUMN 'id_tipo_kit'
    */
    
    @JoinColumn(name = "ID_TIPO_KIT" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipoKit;
    
    /**
    * The 'id multimedio' Maps to COLUMN 'id_multimedio'
    */
    
    @JoinColumn(name = "ID_MULTIMEDIO" , referencedColumnName = "ID_MULTIMEDIO")
    @ManyToOne(optional = true )
    private Multimedio multimedio;
    
    /**
    * The 'nombre es' Maps to COLUMN 'nombre_es'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ES" , length=50, nullable=false)
    private String nombreEs;
    
    /**
    * The 'nombre en' Maps to COLUMN 'nombre_en'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_EN" , length=50, nullable=false)
    private String nombreEn;
    
    /**
    * The 'url multimedia' Maps to COLUMN 'url_multimedia'
    */
    
    @Basic(optional = false)
    @Column(name = "URL_MULTIMEDIA" , length=255, nullable=false)
    private String urlMultimedia;
    
    /**
    * The 'mime type' Maps to COLUMN 'mime_type'
    */
    
    @Basic(optional = false)
    @Column(name = "MIME_TYPE" , length=64, nullable=false)
    private String mimeType;
    
    /**
    * The 'contenedor' Maps to COLUMN 'contenedor'
    */
    
    @Basic(optional = true)
    @Column(name = "CONTENEDOR" , length=50, nullable=true)
    private String contenedor;
    
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
    * Map the relation to Kit_TSU table where has cve_kit_master object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kitMaster")
    private List<KitTsu> kitTsuHasKitMasterList;
    
    /** 
    * Map the relation to material_extra table where has cve_kit_master object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kitMaster")
    private List<MaterialExtra> materialExtraHasKitMasterList;
    
    /** 
    * Map the relation to matriz table where has cve_kit_master object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kitMaster")
    private List<Matriz> matrizHasKitMasterList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public KitMaster() {
    }
    
    /**
     * Getters and Setters
     */
    public String getCveKitMaster() { return this.cveKitMaster;}
    public void setCveKitMaster(String v) { this.cveKitMaster = v; }
    
    public Parametro getIdUnidadMedida(){ return this.idUnidadMedida ; }
    public void setIdUnidadMedida(Parametro x){ this.idUnidadMedida = x; }
    
    public Parametro getIdInstruccionesNacionales(){ return this.idInstruccionesNacionales ; }
    public void setIdInstruccionesNacionales(Parametro x){ this.idInstruccionesNacionales = x; }
    
    public Parametro getIdInstruccionesInternac(){ return this.idInstruccionesInternac ; }
    public void setIdInstruccionesInternac(Parametro x){ this.idInstruccionesInternac = x; }
    
    public Parametro getIdTipoKit(){ return this.idTipoKit ; }
    public void setIdTipoKit(Parametro x){ this.idTipoKit = x; }
    
    public Multimedio getMultimedio(){ return this.multimedio ; }
    public void setMultimedio(Multimedio x){ this.multimedio = x; }
    
    public String getNombreEs() { return this.nombreEs;}
    public void setNombreEs(String v) { this.nombreEs = v; }
    
    public String getNombreEn() { return this.nombreEn;}
    public void setNombreEn(String v) { this.nombreEn = v; }
    
    public String getUrlMultimedia() { return this.urlMultimedia;}
    public void setUrlMultimedia(String v) { this.urlMultimedia = v; }
    
    public String getMimeType() { return this.mimeType;}
    public void setMimeType(String v) { this.mimeType = v; }
    
    public String getContenedor() { return this.contenedor;}
    public void setContenedor(String v) { this.contenedor = v; }
    
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
    public List<KitTsu> getKitTsuHasKitMasterList(){ return this.kitTsuHasKitMasterList; }
    public void setKitTsuHasKitMasterList(List<KitTsu> v){ this.kitTsuHasKitMasterList = v; }
    
    public List<MaterialExtra> getMaterialExtraHasKitMasterList(){ return this.materialExtraHasKitMasterList; }
    public void setMaterialExtraHasKitMasterList(List<MaterialExtra> v){ this.materialExtraHasKitMasterList = v; }
    
    public List<Matriz> getMatrizHasKitMasterList(){ return this.matrizHasKitMasterList; }
    public void setMatrizHasKitMasterList(List<Matriz> v){ this.matrizHasKitMasterList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(cveKitMaster).hashCode();
		hash += String.valueOf(idUnidadMedida).hashCode();
		hash += String.valueOf(idInstruccionesNacionales).hashCode();
		hash += String.valueOf(idInstruccionesInternac).hashCode();
		hash += String.valueOf(idTipoKit).hashCode();
		hash += String.valueOf(multimedio).hashCode();
		hash += String.valueOf(nombreEs).hashCode();
		hash += String.valueOf(nombreEn).hashCode();
		hash += String.valueOf(urlMultimedia).hashCode();
		hash += String.valueOf(mimeType).hashCode();
		hash += String.valueOf(contenedor).hashCode();
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
        if (!(o instanceof KitMaster)) {
            return false;
        }		
		KitMaster other = (KitMaster ) o;
		if (!Objects.equals(this.cveKitMaster, other.cveKitMaster)) { return false; }		
		if (!Objects.equals(this.idUnidadMedida, other.idUnidadMedida)) { return false; }		
		if (!Objects.equals(this.idInstruccionesNacionales, other.idInstruccionesNacionales)) { return false; }		
		if (!Objects.equals(this.idInstruccionesInternac, other.idInstruccionesInternac)) { return false; }		
		if (!Objects.equals(this.idTipoKit, other.idTipoKit)) { return false; }		
		if (!Objects.equals(this.multimedio, other.multimedio)) { return false; }		
		if (!Objects.equals(this.nombreEs, other.nombreEs)) { return false; }		
		if (!Objects.equals(this.nombreEn, other.nombreEn)) { return false; }		
		if (!Objects.equals(this.urlMultimedia, other.urlMultimedia)) { return false; }		
		if (!Objects.equals(this.mimeType, other.mimeType)) { return false; }		
		if (!Objects.equals(this.contenedor, other.contenedor)) { return false; }		
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
		sb.append("KitMaster{");
		sb.append("cveKitMaster" ).append("=").append(cveKitMaster).append("|");
		sb.append("idUnidadMedida" ).append("=").append(idUnidadMedida).append("|");
		sb.append("idInstruccionesNacionales" ).append("=").append(idInstruccionesNacionales).append("|");
		sb.append("idInstruccionesInternac" ).append("=").append(idInstruccionesInternac).append("|");
		sb.append("idTipoKit" ).append("=").append(idTipoKit).append("|");
		sb.append("multimedio" ).append("=").append(multimedio).append("|");
		sb.append("nombreEs" ).append("=").append(nombreEs).append("|");
		sb.append("nombreEn" ).append("=").append(nombreEn).append("|");
		sb.append("urlMultimedia" ).append("=").append(urlMultimedia).append("|");
		sb.append("mimeType" ).append("=").append(mimeType).append("|");
		sb.append("contenedor" ).append("=").append(contenedor).append("|");
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
		sb.append(" ").append(cveKitMaster).append(" ");

		return sb.toString().trim();
	}

}
