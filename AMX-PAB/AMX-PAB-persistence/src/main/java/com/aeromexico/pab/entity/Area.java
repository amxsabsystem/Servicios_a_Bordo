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
 * Class for mapping JPA Entity of Table area.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "area")
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
    , @NamedQuery(name = "Area.countAll", query = "SELECT COUNT(a) FROM Area a")
    , @NamedQuery(name = "Area.findByIdArea", query = "SELECT a FROM Area a WHERE a.idArea = :idArea")
    , @NamedQuery(name = "Area.findByClave", query = "SELECT a FROM Area a WHERE a.clave = :clave")
    , @NamedQuery(name = "Area.findByNombreEs", query = "SELECT a FROM Area a WHERE a.nombreEs = :nombreEs")
    , @NamedQuery(name = "Area.findByNombreEn", query = "SELECT a FROM Area a WHERE a.nombreEn = :nombreEn")
    , @NamedQuery(name = "Area.findByUrlMultimedia", query = "SELECT a FROM Area a WHERE a.urlMultimedia = :urlMultimedia")
    , @NamedQuery(name = "Area.findByMimeType", query = "SELECT a FROM Area a WHERE a.mimeType = :mimeType")
    , @NamedQuery(name = "Area.findByStatus", query = "SELECT a FROM Area a WHERE a.status = :status")
    , @NamedQuery(name = "Area.findByUsuarioCreo", query = "SELECT a FROM Area a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Area.findByFechaCreo", query = "SELECT a FROM Area a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Area.findByUsuarioModifico", query = "SELECT a FROM Area a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Area.findByFechaModifico", query = "SELECT a FROM Area a WHERE a.fechaModifico = :fechaModifico")
})
public class      Area 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 3002509;
    
    /**
    * The 'id area' Maps to COLUMN 'id_area'
    */
    
    @Id
    @Column(name = "ID_AREA" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idArea;
    
    /**
    * The 'clave' Maps to COLUMN 'clave'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE" , length=6, nullable=false)
    private String clave;
    
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
    
    @Basic(optional = true)
    @Column(name = "URL_MULTIMEDIA" , length=255, nullable=true)
    private String urlMultimedia;
    
    /**
    * The 'mime type' Maps to COLUMN 'mime_type'
    */
    
    @Basic(optional = true)
    @Column(name = "MIME_TYPE" , length=64, nullable=true)
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
    * Map the relation to reporte table where has id_area_responsable object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaResponsable")
    private List<Reporte> reporteHasAreaResponsableList;
    
    /** 
    * Map the relation to responsable_estacion table where has id_area object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<ResponsableEstacion> responsableEstacionHasAreaList;
    
    /** 
    * Map the relation to Comunicado table where has id_area object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<Comunicado> comunicadoHasAreaList;
    
    /** 
    * Map the relation to empleado table where has id_area object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<Empleado> empleadoHasAreaList;
    
    /** 
    * Map the relation to comunicado_areas table where has id_area object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<ComunicadoAreas> comunicadoAreasHasAreaList;
    
    /** 
    * Map the relation to seguimiento table where has id_area_responsable object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaResponsable")
    private List<Seguimiento> seguimientoHasAreaResponsableList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Area() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdArea() { return this.idArea;}
    public void setIdArea(Integer v) { this.idArea = v; }
    
    public String getClave() { return this.clave;}
    public void setClave(String v) { this.clave = v; }
    
    public String getNombreEs() { return this.nombreEs;}
    public void setNombreEs(String v) { this.nombreEs = v; }
    
    public String getNombreEn() { return this.nombreEn;}
    public void setNombreEn(String v) { this.nombreEn = v; }
    
    public String getUrlMultimedia() { return this.urlMultimedia;}
    public void setUrlMultimedia(String v) { this.urlMultimedia = v; }
    
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
    public List<Reporte> getReporteHasAreaResponsableList(){ return this.reporteHasAreaResponsableList; }
    public void setReporteHasAreaResponsableList(List<Reporte> v){ this.reporteHasAreaResponsableList = v; }
    
    public List<ResponsableEstacion> getResponsableEstacionHasAreaList(){ return this.responsableEstacionHasAreaList; }
    public void setResponsableEstacionHasAreaList(List<ResponsableEstacion> v){ this.responsableEstacionHasAreaList = v; }
    
    public List<Comunicado> getComunicadoHasAreaList(){ return this.comunicadoHasAreaList; }
    public void setComunicadoHasAreaList(List<Comunicado> v){ this.comunicadoHasAreaList = v; }
    
    public List<Empleado> getEmpleadoHasAreaList(){ return this.empleadoHasAreaList; }
    public void setEmpleadoHasAreaList(List<Empleado> v){ this.empleadoHasAreaList = v; }
    
    public List<ComunicadoAreas> getComunicadoAreasHasAreaList(){ return this.comunicadoAreasHasAreaList; }
    public void setComunicadoAreasHasAreaList(List<ComunicadoAreas> v){ this.comunicadoAreasHasAreaList = v; }
    
    public List<Seguimiento> getSeguimientoHasAreaResponsableList(){ return this.seguimientoHasAreaResponsableList; }
    public void setSeguimientoHasAreaResponsableList(List<Seguimiento> v){ this.seguimientoHasAreaResponsableList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idArea).hashCode();
		hash += String.valueOf(clave).hashCode();
		hash += String.valueOf(nombreEs).hashCode();
		hash += String.valueOf(nombreEn).hashCode();
		hash += String.valueOf(urlMultimedia).hashCode();
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
        if (!(o instanceof Area)) {
            return false;
        }		
		Area other = (Area ) o;
		if (!Objects.equals(this.idArea, other.idArea)) { return false; }		
		if (!Objects.equals(this.clave, other.clave)) { return false; }		
		if (!Objects.equals(this.nombreEs, other.nombreEs)) { return false; }		
		if (!Objects.equals(this.nombreEn, other.nombreEn)) { return false; }		
		if (!Objects.equals(this.urlMultimedia, other.urlMultimedia)) { return false; }		
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
		sb.append("Area{");
		sb.append("idArea" ).append("=").append(idArea).append("|");
		sb.append("clave" ).append("=").append(clave).append("|");
		sb.append("nombreEs" ).append("=").append(nombreEs).append("|");
		sb.append("nombreEn" ).append("=").append(nombreEn).append("|");
		sb.append("urlMultimedia" ).append("=").append(urlMultimedia).append("|");
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
		sb.append(" ").append(clave).append(" ");
		sb.append(" ").append(nombreEs).append(" ");

		return sb.toString().trim();
	}

}
