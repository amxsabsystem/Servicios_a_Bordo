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
 * Class for mapping JPA Entity of Table clase.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "clase")
@NamedQueries({
    @NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c")
    , @NamedQuery(name = "Clase.countAll", query = "SELECT COUNT(c) FROM Clase c")
    , @NamedQuery(name = "Clase.findByIdClase", query = "SELECT c FROM Clase c WHERE c.idClase = :idClase")
    , @NamedQuery(name = "Clase.findByClave", query = "SELECT c FROM Clase c WHERE c.clave = :clave")
    , @NamedQuery(name = "Clase.findByNombreEsp", query = "SELECT c FROM Clase c WHERE c.nombreEsp = :nombreEsp")
    , @NamedQuery(name = "Clase.findByNombreEng", query = "SELECT c FROM Clase c WHERE c.nombreEng = :nombreEng")
    , @NamedQuery(name = "Clase.findByStatus", query = "SELECT c FROM Clase c WHERE c.status = :status")
    , @NamedQuery(name = "Clase.findByUsuarioCreo", query = "SELECT c FROM Clase c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Clase.findByFechaCreo", query = "SELECT c FROM Clase c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Clase.findByUsuarioModifico", query = "SELECT c FROM Clase c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Clase.findByFechaModifico", query = "SELECT c FROM Clase c WHERE c.fechaModifico = :fechaModifico")
})
public class      Clase 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 94742890;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @Id
    @Column(name = "ID_CLASE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idClase;
    
    /**
    * The 'clave' Maps to COLUMN 'clave'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE" , length=10, nullable=false)
    private String clave;
    
    /**
    * The 'nombre esp' Maps to COLUMN 'nombre_esp'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ESP" , length=50, nullable=false)
    private String nombreEsp;
    
    /**
    * The 'nombre eng' Maps to COLUMN 'nombre_eng'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ENG" , length=64, nullable=false)
    private String nombreEng;
    
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
    * Map the relation to Asignacion_Servicio table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<AsignacionServicio> asignacionServicioHasClaseList;
    
    /** 
    * Map the relation to Codigo_Servicio table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<CodigoServicio> codigoServicioHasClaseList;
    
    /** 
    * Map the relation to avion_audifono table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<AvionAudifono> avionAudifonoHasClaseList;
    
    /** 
    * Map the relation to reporte table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<Reporte> reporteHasClaseList;
    
    /** 
    * Map the relation to TSU table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<Tsu> tsuHasClaseList;
    
    /** 
    * Map the relation to potencial table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<Potencial> potencialHasClaseList;
    
    /** 
    * Map the relation to tabla_abordamiento table where has id_clase object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clase")
    private List<TablaAbordamiento> tablaAbordamientoHasClaseList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Clase() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdClase() { return this.idClase;}
    public void setIdClase(Integer v) { this.idClase = v; }
    
    public String getClave() { return this.clave;}
    public void setClave(String v) { this.clave = v; }
    
    public String getNombreEsp() { return this.nombreEsp;}
    public void setNombreEsp(String v) { this.nombreEsp = v; }
    
    public String getNombreEng() { return this.nombreEng;}
    public void setNombreEng(String v) { this.nombreEng = v; }
    
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
    public List<AsignacionServicio> getAsignacionServicioHasClaseList(){ return this.asignacionServicioHasClaseList; }
    public void setAsignacionServicioHasClaseList(List<AsignacionServicio> v){ this.asignacionServicioHasClaseList = v; }
    
    public List<CodigoServicio> getCodigoServicioHasClaseList(){ return this.codigoServicioHasClaseList; }
    public void setCodigoServicioHasClaseList(List<CodigoServicio> v){ this.codigoServicioHasClaseList = v; }
    
    public List<AvionAudifono> getAvionAudifonoHasClaseList(){ return this.avionAudifonoHasClaseList; }
    public void setAvionAudifonoHasClaseList(List<AvionAudifono> v){ this.avionAudifonoHasClaseList = v; }
    
    public List<Reporte> getReporteHasClaseList(){ return this.reporteHasClaseList; }
    public void setReporteHasClaseList(List<Reporte> v){ this.reporteHasClaseList = v; }
    
    public List<Tsu> getTsuHasClaseList(){ return this.tsuHasClaseList; }
    public void setTsuHasClaseList(List<Tsu> v){ this.tsuHasClaseList = v; }
    
    public List<Potencial> getPotencialHasClaseList(){ return this.potencialHasClaseList; }
    public void setPotencialHasClaseList(List<Potencial> v){ this.potencialHasClaseList = v; }
    
    public List<TablaAbordamiento> getTablaAbordamientoHasClaseList(){ return this.tablaAbordamientoHasClaseList; }
    public void setTablaAbordamientoHasClaseList(List<TablaAbordamiento> v){ this.tablaAbordamientoHasClaseList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idClase).hashCode();
		hash += String.valueOf(clave).hashCode();
		hash += String.valueOf(nombreEsp).hashCode();
		hash += String.valueOf(nombreEng).hashCode();
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
        if (!(o instanceof Clase)) {
            return false;
        }		
		Clase other = (Clase ) o;
		if (!Objects.equals(this.idClase, other.idClase)) { return false; }		
		if (!Objects.equals(this.clave, other.clave)) { return false; }		
		if (!Objects.equals(this.nombreEsp, other.nombreEsp)) { return false; }		
		if (!Objects.equals(this.nombreEng, other.nombreEng)) { return false; }		
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
		sb.append("Clase{");
		sb.append("idClase" ).append("=").append(idClase).append("|");
		sb.append("clave" ).append("=").append(clave).append("|");
		sb.append("nombreEsp" ).append("=").append(nombreEsp).append("|");
		sb.append("nombreEng" ).append("=").append(nombreEng).append("|");
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
		sb.append(" ").append(idClase).append(" ");

		return sb.toString().trim();
	}

}
