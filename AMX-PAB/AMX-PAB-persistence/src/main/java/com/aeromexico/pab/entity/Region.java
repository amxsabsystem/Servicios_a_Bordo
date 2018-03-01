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
 * Class for mapping JPA Entity of Table region.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "region")
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
    , @NamedQuery(name = "Region.countAll", query = "SELECT COUNT(r) FROM Region r")
    , @NamedQuery(name = "Region.findByIdRegion", query = "SELECT r FROM Region r WHERE r.idRegion = :idRegion")
    , @NamedQuery(name = "Region.findByCveRegion", query = "SELECT r FROM Region r WHERE r.cveRegion = :cveRegion")
    , @NamedQuery(name = "Region.findByNombre", query = "SELECT r FROM Region r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "Region.findByStatus", query = "SELECT r FROM Region r WHERE r.status = :status")
    , @NamedQuery(name = "Region.findByUsuarioCreo", query = "SELECT r FROM Region r WHERE r.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Region.findByFechaCreo", query = "SELECT r FROM Region r WHERE r.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Region.findByUsuarioModifico", query = "SELECT r FROM Region r WHERE r.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Region.findByFechaModifico", query = "SELECT r FROM Region r WHERE r.fechaModifico = :fechaModifico")
})
public class      Region 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -934795532;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @Id
    @Column(name = "ID_REGION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idRegion;
    
    /**
    * The 'cve region' Maps to COLUMN 'cve_region'
    */
    
    @Basic(optional = false)
    @Column(name = "CVE_REGION" , length=3, nullable=false)
    private String cveRegion;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=50, nullable=false)
    private String nombre;
    
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
    * Map the relation to region_abordaje table where has id_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<RegionAbordaje> regionAbordajeHasRegionList;
    
    /** 
    * Map the relation to Asignacion_Servicio table where has id_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<AsignacionServicio> asignacionServicioHasRegionList;
    
    /** 
    * Map the relation to pais table where has id_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Pais> paisHasRegionList;
    
    /** 
    * Map the relation to horario table where has id_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Horario> horarioHasRegionList;
    
    /** 
    * Map the relation to Horario_Region table where has id_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<HorarioRegion> horarioRegionHasRegionList;
    
    /** 
    * Map the relation to ciclo table where has id_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Ciclo> cicloHasRegionList;
    

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "regionList")
    private List<Proveedor> proveedorList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Region() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdRegion() { return this.idRegion;}
    public void setIdRegion(Integer v) { this.idRegion = v; }
    
    public String getCveRegion() { return this.cveRegion;}
    public void setCveRegion(String v) { this.cveRegion = v; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
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
    public List<RegionAbordaje> getRegionAbordajeHasRegionList(){ return this.regionAbordajeHasRegionList; }
    public void setRegionAbordajeHasRegionList(List<RegionAbordaje> v){ this.regionAbordajeHasRegionList = v; }
    
    public List<AsignacionServicio> getAsignacionServicioHasRegionList(){ return this.asignacionServicioHasRegionList; }
    public void setAsignacionServicioHasRegionList(List<AsignacionServicio> v){ this.asignacionServicioHasRegionList = v; }
    
    public List<Pais> getPaisHasRegionList(){ return this.paisHasRegionList; }
    public void setPaisHasRegionList(List<Pais> v){ this.paisHasRegionList = v; }
    
    public List<Horario> getHorarioHasRegionList(){ return this.horarioHasRegionList; }
    public void setHorarioHasRegionList(List<Horario> v){ this.horarioHasRegionList = v; }
    
    public List<HorarioRegion> getHorarioRegionHasRegionList(){ return this.horarioRegionHasRegionList; }
    public void setHorarioRegionHasRegionList(List<HorarioRegion> v){ this.horarioRegionHasRegionList = v; }
    
    public List<Ciclo> getCicloHasRegionList(){ return this.cicloHasRegionList; }
    public void setCicloHasRegionList(List<Ciclo> v){ this.cicloHasRegionList = v; }
    
	// M2M <*>
    public List<Proveedor> getProveedorList() { return this.proveedorList; }
    public void setProveedorList(List<Proveedor>  v) { this.proveedorList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idRegion).hashCode();
		hash += String.valueOf(cveRegion).hashCode();
		hash += String.valueOf(nombre).hashCode();
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
        if (!(o instanceof Region)) {
            return false;
        }		
		Region other = (Region ) o;
		if (!Objects.equals(this.idRegion, other.idRegion)) { return false; }		
		if (!Objects.equals(this.cveRegion, other.cveRegion)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
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
		sb.append("Region{");
		sb.append("idRegion" ).append("=").append(idRegion).append("|");
		sb.append("cveRegion" ).append("=").append(cveRegion).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
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
		sb.append(" ").append(cveRegion).append(" ");
		sb.append(" ").append(nombre).append(" ");

		return sb.toString().trim();
	}

}
