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
 * Class for mapping JPA Entity of Table proveedor_estacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "proveedor_estacion")
@NamedQueries({
    @NamedQuery(name = "ProveedorEstacion.findAll", query = "SELECT p FROM ProveedorEstacion p")
    , @NamedQuery(name = "ProveedorEstacion.countAll", query = "SELECT COUNT(p) FROM ProveedorEstacion p")
    , @NamedQuery(name = "ProveedorEstacion.findByIdProveedorEstacion", query = "SELECT p FROM ProveedorEstacion p WHERE p.idProveedorEstacion = :idProveedorEstacion")
    , @NamedQuery(name = "ProveedorEstacion.findByestacion", query = "SELECT p FROM ProveedorEstacion p WHERE p.estacion = :estacion")
    , @NamedQuery(name = "ProveedorEstacion.findByproveedor", query = "SELECT p FROM ProveedorEstacion p WHERE p.proveedor = :proveedor")
    , @NamedQuery(name = "ProveedorEstacion.findByClaveProveedorEstacion", query = "SELECT p FROM ProveedorEstacion p WHERE p.claveProveedorEstacion = :claveProveedorEstacion")
    , @NamedQuery(name = "ProveedorEstacion.findByRazonSocial", query = "SELECT p FROM ProveedorEstacion p WHERE p.razonSocial = :razonSocial")
    , @NamedQuery(name = "ProveedorEstacion.findByStatus", query = "SELECT p FROM ProveedorEstacion p WHERE p.status = :status")
    , @NamedQuery(name = "ProveedorEstacion.findByUsuarioCreo", query = "SELECT p FROM ProveedorEstacion p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "ProveedorEstacion.findByFechaCreo", query = "SELECT p FROM ProveedorEstacion p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "ProveedorEstacion.findByUsuarioModifico", query = "SELECT p FROM ProveedorEstacion p WHERE p.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "ProveedorEstacion.findByFechaModifico", query = "SELECT p FROM ProveedorEstacion p WHERE p.fechaModifico = :fechaModifico")
})
public class      ProveedorEstacion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1148107553;
    
    /**
    * The 'id proveedor estacion' Maps to COLUMN 'id_proveedor_estacion'
    */
    
    @Id
    @Column(name = "ID_PROVEEDOR_ESTACION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idProveedorEstacion;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @JoinColumn(name = "ID_ESTACION" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacion;
    
    /**
    * The 'clave proveedor' Maps to COLUMN 'clave_proveedor'
    */
    
    @JoinColumn(name = "CLAVE_PROVEEDOR" , referencedColumnName = "CLAVE_PROVEEDOR")
    @ManyToOne(optional = false )
    private Proveedor proveedor;
    
    /**
    * The 'clave proveedor estacion' Maps to COLUMN 'clave_proveedor_estacion'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE_PROVEEDOR_ESTACION" , length=6, nullable=false)
    private String claveProveedorEstacion;
    
    /**
    * The 'razon social' Maps to COLUMN 'razon_social'
    */
    
    @Basic(optional = false)
    @Column(name = "RAZON_SOCIAL" , length=50, nullable=false)
    private String razonSocial;
    
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
    * Map the relation to comunicado_proveedor_estacion table where has id_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorEstacion")
    private List<ComunicadoProveedorEstacion> comunicadoProveedorEstacionHasProveedorEstacionList;
    
    /** 
    * Map the relation to reporte table where has id_responsable_proveedor_estacion_final object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsableProveedorEstacionFinal")
    private List<Reporte> reporteHasIdResponsableProveedorEstacionFinalList;
    
    /** 
    * Map the relation to reporte table where has id_responsable_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsableProveedorEstacion")
    private List<Reporte> reporteHasIdResponsableProveedorEstacionList;
    
    /** 
    * Map the relation to contacto_proveedor_estacion table where has id_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorEstacion")
    private List<ContactoProveedorEstacion> contactoProveedorEstacionHasProveedorEstacionList;
    
    /** 
    * Map the relation to potencial table where has id_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorEstacion")
    private List<Potencial> potencialHasProveedorEstacionList;
    
    /** 
    * Map the relation to bitacora_comunicado table where has id_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorEstacion")
    private List<BitacoraComunicado> bitacoraComunicadoHasProveedorEstacionList;
    
    /** 
    * Map the relation to seguimiento table where has id_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorEstacion")
    private List<Seguimiento> seguimientoHasProveedorEstacionList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "PROVEEDOR_ESTACION_TIPO_PROVEEDOR",
               joinColumns        = {@JoinColumn(name = "ID_PROVEEDOR_ESTACION", referencedColumnName ="ID_PROVEEDOR_ESTACION")},
               inverseJoinColumns = {@JoinColumn(name = "ID_TIPO_PROVEEDOR", referencedColumnName ="ID_TIPO_PROVEEDOR")}
               )
    private List<TipoProveedor> tipoProveedorList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ProveedorEstacion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdProveedorEstacion() { return this.idProveedorEstacion;}
    public void setIdProveedorEstacion(Integer v) { this.idProveedorEstacion = v; }
    
    public Estacion getEstacion(){ return this.estacion ; }
    public void setEstacion(Estacion x){ this.estacion = x; }
    
    public Proveedor getProveedor(){ return this.proveedor ; }
    public void setProveedor(Proveedor x){ this.proveedor = x; }
    
    public String getClaveProveedorEstacion() { return this.claveProveedorEstacion;}
    public void setClaveProveedorEstacion(String v) { this.claveProveedorEstacion = v; }
    
    public String getRazonSocial() { return this.razonSocial;}
    public void setRazonSocial(String v) { this.razonSocial = v; }
    
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
    public List<ComunicadoProveedorEstacion> getComunicadoProveedorEstacionHasProveedorEstacionList(){ return this.comunicadoProveedorEstacionHasProveedorEstacionList; }
    public void setComunicadoProveedorEstacionHasProveedorEstacionList(List<ComunicadoProveedorEstacion> v){ this.comunicadoProveedorEstacionHasProveedorEstacionList = v; }
    
    public List<Reporte> getReporteHasIdResponsableProveedorEstacionFinalList(){ return this.reporteHasIdResponsableProveedorEstacionFinalList; }
    public void setReporteHasIdResponsableProveedorEstacionFinalList(List<Reporte> v){ this.reporteHasIdResponsableProveedorEstacionFinalList = v; }
    
    public List<Reporte> getReporteHasIdResponsableProveedorEstacionList(){ return this.reporteHasIdResponsableProveedorEstacionList; }
    public void setReporteHasIdResponsableProveedorEstacionList(List<Reporte> v){ this.reporteHasIdResponsableProveedorEstacionList = v; }
    
    public List<ContactoProveedorEstacion> getContactoProveedorEstacionHasProveedorEstacionList(){ return this.contactoProveedorEstacionHasProveedorEstacionList; }
    public void setContactoProveedorEstacionHasProveedorEstacionList(List<ContactoProveedorEstacion> v){ this.contactoProveedorEstacionHasProveedorEstacionList = v; }
    
    public List<Potencial> getPotencialHasProveedorEstacionList(){ return this.potencialHasProveedorEstacionList; }
    public void setPotencialHasProveedorEstacionList(List<Potencial> v){ this.potencialHasProveedorEstacionList = v; }
    
    public List<BitacoraComunicado> getBitacoraComunicadoHasProveedorEstacionList(){ return this.bitacoraComunicadoHasProveedorEstacionList; }
    public void setBitacoraComunicadoHasProveedorEstacionList(List<BitacoraComunicado> v){ this.bitacoraComunicadoHasProveedorEstacionList = v; }
    
    public List<Seguimiento> getSeguimientoHasProveedorEstacionList(){ return this.seguimientoHasProveedorEstacionList; }
    public void setSeguimientoHasProveedorEstacionList(List<Seguimiento> v){ this.seguimientoHasProveedorEstacionList = v; }
    
	// M2M <*>
    public List<TipoProveedor> getTipoProveedorList() { return this.tipoProveedorList; }
    public void setTipoProveedorList(List<TipoProveedor>  v) { this.tipoProveedorList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idProveedorEstacion).hashCode();
		hash += String.valueOf(estacion).hashCode();
		hash += String.valueOf(proveedor).hashCode();
		hash += String.valueOf(claveProveedorEstacion).hashCode();
		hash += String.valueOf(razonSocial).hashCode();
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
        if (!(o instanceof ProveedorEstacion)) {
            return false;
        }		
		ProveedorEstacion other = (ProveedorEstacion ) o;
		if (!Objects.equals(this.idProveedorEstacion, other.idProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.estacion, other.estacion)) { return false; }		
		if (!Objects.equals(this.proveedor, other.proveedor)) { return false; }		
		if (!Objects.equals(this.claveProveedorEstacion, other.claveProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.razonSocial, other.razonSocial)) { return false; }		
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
		sb.append("ProveedorEstacion{");
		sb.append("idProveedorEstacion" ).append("=").append(idProveedorEstacion).append("|");
		sb.append("estacion" ).append("=").append(estacion).append("|");
		sb.append("proveedor" ).append("=").append(proveedor).append("|");
		sb.append("claveProveedorEstacion" ).append("=").append(claveProveedorEstacion).append("|");
		sb.append("razonSocial" ).append("=").append(razonSocial).append("|");
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
		sb.append(" ").append(idProveedorEstacion).append(" ");

		return sb.toString().trim();
	}

}
