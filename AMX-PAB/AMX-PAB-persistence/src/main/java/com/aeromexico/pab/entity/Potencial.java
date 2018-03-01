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
 * Class for mapping JPA Entity of Table potencial.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "potencial")
@NamedQueries({
    @NamedQuery(name = "Potencial.findAll", query = "SELECT p FROM Potencial p")
    , @NamedQuery(name = "Potencial.countAll", query = "SELECT COUNT(p) FROM Potencial p")
    , @NamedQuery(name = "Potencial.findByIdPotencial", query = "SELECT p FROM Potencial p WHERE p.idPotencial = :idPotencial")
    , @NamedQuery(name = "Potencial.findByproveedorEstacion", query = "SELECT p FROM Potencial p WHERE p.proveedorEstacion = :proveedorEstacion")
    , @NamedQuery(name = "Potencial.findByproveedor", query = "SELECT p FROM Potencial p WHERE p.proveedor = :proveedor")
    , @NamedQuery(name = "Potencial.findBycodigoServicio", query = "SELECT p FROM Potencial p WHERE p.codigoServicio = :codigoServicio")
    , @NamedQuery(name = "Potencial.findByclase", query = "SELECT p FROM Potencial p WHERE p.clase = :clase")
    , @NamedQuery(name = "Potencial.findByidNociclotemporada", query = "SELECT p FROM Potencial p WHERE p.idNociclotemporada = :idNociclotemporada")
    , @NamedQuery(name = "Potencial.findByidTipociclo", query = "SELECT p FROM Potencial p WHERE p.idTipociclo = :idTipociclo")
    , @NamedQuery(name = "Potencial.findByidEstatuspotencial", query = "SELECT p FROM Potencial p WHERE p.idEstatuspotencial = :idEstatuspotencial")
    , @NamedQuery(name = "Potencial.findByUrlPotencial", query = "SELECT p FROM Potencial p WHERE p.urlPotencial = :urlPotencial")
    , @NamedQuery(name = "Potencial.findByMimeType", query = "SELECT p FROM Potencial p WHERE p.mimeType = :mimeType")
    , @NamedQuery(name = "Potencial.findByUsuarioCreo", query = "SELECT p FROM Potencial p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Potencial.findByFechaCreo", query = "SELECT p FROM Potencial p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Potencial.findByObservaciones", query = "SELECT p FROM Potencial p WHERE p.observaciones = :observaciones")
    , @NamedQuery(name = "Potencial.findByUsuarioObservaciones", query = "SELECT p FROM Potencial p WHERE p.usuarioObservaciones = :usuarioObservaciones")
    , @NamedQuery(name = "Potencial.findByFechaObservaciones", query = "SELECT p FROM Potencial p WHERE p.fechaObservaciones = :fechaObservaciones")
    , @NamedQuery(name = "Potencial.findByNotaRecordatorio", query = "SELECT p FROM Potencial p WHERE p.notaRecordatorio = :notaRecordatorio")
    , @NamedQuery(name = "Potencial.findByFechaRecordatorio", query = "SELECT p FROM Potencial p WHERE p.fechaRecordatorio = :fechaRecordatorio")
    , @NamedQuery(name = "Potencial.findByVersionPotencial", query = "SELECT p FROM Potencial p WHERE p.versionPotencial = :versionPotencial")
    , @NamedQuery(name = "Potencial.findByVigenciaPotencial", query = "SELECT p FROM Potencial p WHERE p.vigenciaPotencial = :vigenciaPotencial")
})
public class      Potencial 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1820588593;
    
    /**
    * The 'id potencial' Maps to COLUMN 'id_potencial'
    */
    
    @Id
    @Column(name = "ID_POTENCIAL" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPotencial;
    
    /**
    * The 'id proveedor estacion' Maps to COLUMN 'id_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_PROVEEDOR_ESTACION" , referencedColumnName = "ID_PROVEEDOR_ESTACION")
    @ManyToOne(optional = true )
    private ProveedorEstacion proveedorEstacion;
    
    /**
    * The 'clave proveedor' Maps to COLUMN 'clave_proveedor'
    */
    
    @JoinColumn(name = "CLAVE_PROVEEDOR" , referencedColumnName = "CLAVE_PROVEEDOR")
    @ManyToOne(optional = true )
    private Proveedor proveedor;
    
    /**
    * The 'id codigo servicio' Maps to COLUMN 'id_codigo_servicio'
    */
    
    @JoinColumn(name = "ID_CODIGO_SERVICIO" , referencedColumnName = "ID_CODIGO_SERVICIO")
    @ManyToOne(optional = true )
    private CodigoServicio codigoServicio;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'id noCicloTemporada' Maps to COLUMN 'id_noCicloTemporada'
    */
    
    @JoinColumn(name = "ID_NOCICLOTEMPORADA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idNociclotemporada;
    
    /**
    * The 'id TipoCiclo' Maps to COLUMN 'id_TipoCiclo'
    */
    
    @JoinColumn(name = "ID_TIPOCICLO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idTipociclo;
    
    /**
    * The 'id estatusPotencial' Maps to COLUMN 'id_estatusPotencial'
    */
    
    @JoinColumn(name = "ID_ESTATUSPOTENCIAL" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idEstatuspotencial;
    
    /**
    * The 'url potencial' Maps to COLUMN 'url_potencial'
    */
    
    @Basic(optional = false)
    @Column(name = "URL_POTENCIAL" , length=255, nullable=false)
    private String urlPotencial;
    
    /**
    * The 'mime type' Maps to COLUMN 'mime_type'
    */
    
    @Basic(optional = false)
    @Column(name = "MIME_TYPE" , length=64, nullable=false)
    private String mimeType;
    
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
    * The 'observaciones' Maps to COLUMN 'observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "OBSERVACIONES" , length=255, nullable=true)
    private String observaciones;
    
    /**
    * The 'usuario observaciones' Maps to COLUMN 'usuario_observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "USUARIO_OBSERVACIONES" , length=100, nullable=true)
    private String usuarioObservaciones;
    
    /**
    * The 'fecha observaciones' Maps to COLUMN 'fecha_observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "FECHA_OBSERVACIONES" , nullable=true)
    private java.sql.Date fechaObservaciones;
    
    /**
    * The 'nota recordatorio' Maps to COLUMN 'nota_recordatorio'
    */
    
    @Basic(optional = true)
    @Column(name = "NOTA_RECORDATORIO" , length=255, nullable=true)
    private String notaRecordatorio;
    
    /**
    * The 'fecha recordatorio' Maps to COLUMN 'fecha_recordatorio'
    */
    
    @Basic(optional = true)
    @Column(name = "FECHA_RECORDATORIO" , nullable=true)
    private java.sql.Timestamp fechaRecordatorio;
    
    /**
    * The 'version potencial' Maps to COLUMN 'version_potencial'
    */
    
    @Basic(optional = true)
    @Column(name = "VERSION_POTENCIAL" , nullable=true)
    private Integer versionPotencial;
    
    /**
    * The 'vigencia potencial' Maps to COLUMN 'vigencia_potencial'
    */
    
    @Basic(optional = true)
    @Column(name = "VIGENCIA_POTENCIAL" , nullable=true)
    private java.sql.Timestamp vigenciaPotencial;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Potencial() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdPotencial() { return this.idPotencial;}
    public void setIdPotencial(Integer v) { this.idPotencial = v; }
    
    public ProveedorEstacion getProveedorEstacion(){ return this.proveedorEstacion ; }
    public void setProveedorEstacion(ProveedorEstacion x){ this.proveedorEstacion = x; }
    
    public Proveedor getProveedor(){ return this.proveedor ; }
    public void setProveedor(Proveedor x){ this.proveedor = x; }
    
    public CodigoServicio getCodigoServicio(){ return this.codigoServicio ; }
    public void setCodigoServicio(CodigoServicio x){ this.codigoServicio = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public Parametro getIdNociclotemporada(){ return this.idNociclotemporada ; }
    public void setIdNociclotemporada(Parametro x){ this.idNociclotemporada = x; }
    
    public Parametro getIdTipociclo(){ return this.idTipociclo ; }
    public void setIdTipociclo(Parametro x){ this.idTipociclo = x; }
    
    public Parametro getIdEstatuspotencial(){ return this.idEstatuspotencial ; }
    public void setIdEstatuspotencial(Parametro x){ this.idEstatuspotencial = x; }
    
    public String getUrlPotencial() { return this.urlPotencial;}
    public void setUrlPotencial(String v) { this.urlPotencial = v; }
    
    public String getMimeType() { return this.mimeType;}
    public void setMimeType(String v) { this.mimeType = v; }
    
    public String getUsuarioCreo() { return this.usuarioCreo;}
    public void setUsuarioCreo(String v) { this.usuarioCreo = v; }
    
    public java.sql.Timestamp getFechaCreo() { return this.fechaCreo;}
    public void setFechaCreo(java.sql.Timestamp v) { this.fechaCreo = v; }
    
    public String getObservaciones() { return this.observaciones;}
    public void setObservaciones(String v) { this.observaciones = v; }
    
    public String getUsuarioObservaciones() { return this.usuarioObservaciones;}
    public void setUsuarioObservaciones(String v) { this.usuarioObservaciones = v; }
    
    public java.sql.Date getFechaObservaciones() { return this.fechaObservaciones;}
    public void setFechaObservaciones(java.sql.Date v) { this.fechaObservaciones = v; }
    
    public String getNotaRecordatorio() { return this.notaRecordatorio;}
    public void setNotaRecordatorio(String v) { this.notaRecordatorio = v; }
    
    public java.sql.Timestamp getFechaRecordatorio() { return this.fechaRecordatorio;}
    public void setFechaRecordatorio(java.sql.Timestamp v) { this.fechaRecordatorio = v; }
    
    public Integer getVersionPotencial() { return this.versionPotencial;}
    public void setVersionPotencial(Integer v) { this.versionPotencial = v; }
    
    public java.sql.Timestamp getVigenciaPotencial() { return this.vigenciaPotencial;}
    public void setVigenciaPotencial(java.sql.Timestamp v) { this.vigenciaPotencial = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idPotencial).hashCode();
		hash += String.valueOf(proveedorEstacion).hashCode();
		hash += String.valueOf(proveedor).hashCode();
		hash += String.valueOf(codigoServicio).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(idNociclotemporada).hashCode();
		hash += String.valueOf(idTipociclo).hashCode();
		hash += String.valueOf(idEstatuspotencial).hashCode();
		hash += String.valueOf(urlPotencial).hashCode();
		hash += String.valueOf(mimeType).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(observaciones).hashCode();
		hash += String.valueOf(usuarioObservaciones).hashCode();
		hash += String.valueOf(fechaObservaciones).hashCode();
		hash += String.valueOf(notaRecordatorio).hashCode();
		hash += String.valueOf(fechaRecordatorio).hashCode();
		hash += String.valueOf(versionPotencial).hashCode();
		hash += String.valueOf(vigenciaPotencial).hashCode();
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
        if (!(o instanceof Potencial)) {
            return false;
        }		
		Potencial other = (Potencial ) o;
		if (!Objects.equals(this.idPotencial, other.idPotencial)) { return false; }		
		if (!Objects.equals(this.proveedorEstacion, other.proveedorEstacion)) { return false; }		
		if (!Objects.equals(this.proveedor, other.proveedor)) { return false; }		
		if (!Objects.equals(this.codigoServicio, other.codigoServicio)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.idNociclotemporada, other.idNociclotemporada)) { return false; }		
		if (!Objects.equals(this.idTipociclo, other.idTipociclo)) { return false; }		
		if (!Objects.equals(this.idEstatuspotencial, other.idEstatuspotencial)) { return false; }		
		if (!Objects.equals(this.urlPotencial, other.urlPotencial)) { return false; }		
		if (!Objects.equals(this.mimeType, other.mimeType)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.observaciones, other.observaciones)) { return false; }		
		if (!Objects.equals(this.usuarioObservaciones, other.usuarioObservaciones)) { return false; }		
		if (!Objects.equals(this.fechaObservaciones, other.fechaObservaciones)) { return false; }		
		if (!Objects.equals(this.notaRecordatorio, other.notaRecordatorio)) { return false; }		
		if (!Objects.equals(this.fechaRecordatorio, other.fechaRecordatorio)) { return false; }		
		if (!Objects.equals(this.versionPotencial, other.versionPotencial)) { return false; }		
		if (!Objects.equals(this.vigenciaPotencial, other.vigenciaPotencial)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Potencial{");
		sb.append("idPotencial" ).append("=").append(idPotencial).append("|");
		sb.append("proveedorEstacion" ).append("=").append(proveedorEstacion).append("|");
		sb.append("proveedor" ).append("=").append(proveedor).append("|");
		sb.append("codigoServicio" ).append("=").append(codigoServicio).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("idNociclotemporada" ).append("=").append(idNociclotemporada).append("|");
		sb.append("idTipociclo" ).append("=").append(idTipociclo).append("|");
		sb.append("idEstatuspotencial" ).append("=").append(idEstatuspotencial).append("|");
		sb.append("urlPotencial" ).append("=").append(urlPotencial).append("|");
		sb.append("mimeType" ).append("=").append(mimeType).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("observaciones" ).append("=").append(observaciones).append("|");
		sb.append("usuarioObservaciones" ).append("=").append(usuarioObservaciones).append("|");
		sb.append("fechaObservaciones" ).append("=").append(fechaObservaciones).append("|");
		sb.append("notaRecordatorio" ).append("=").append(notaRecordatorio).append("|");
		sb.append("fechaRecordatorio" ).append("=").append(fechaRecordatorio).append("|");
		sb.append("versionPotencial" ).append("=").append(versionPotencial).append("|");
		sb.append("vigenciaPotencial" ).append("=").append(vigenciaPotencial).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idPotencial).append(" ");

		return sb.toString().trim();
	}

}
