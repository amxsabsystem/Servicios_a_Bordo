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
 * Class for mapping JPA Entity of Table estacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "estacion")
@NamedQueries({
    @NamedQuery(name = "Estacion.findAll", query = "SELECT e FROM Estacion e")
    , @NamedQuery(name = "Estacion.countAll", query = "SELECT COUNT(e) FROM Estacion e")
    , @NamedQuery(name = "Estacion.findByIdEstacion", query = "SELECT e FROM Estacion e WHERE e.idEstacion = :idEstacion")
    , @NamedQuery(name = "Estacion.findByNombre", query = "SELECT e FROM Estacion e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estacion.findByClaveIata", query = "SELECT e FROM Estacion e WHERE e.claveIata = :claveIata")
    , @NamedQuery(name = "Estacion.findByciudad", query = "SELECT e FROM Estacion e WHERE e.ciudad = :ciudad")
    , @NamedQuery(name = "Estacion.findByObservaciones", query = "SELECT e FROM Estacion e WHERE e.observaciones = :observaciones")
    , @NamedQuery(name = "Estacion.findByStatus", query = "SELECT e FROM Estacion e WHERE e.status = :status")
    , @NamedQuery(name = "Estacion.findByUsuarioCreo", query = "SELECT e FROM Estacion e WHERE e.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Estacion.findByFechaCreo", query = "SELECT e FROM Estacion e WHERE e.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Estacion.findByUsuarioModifico", query = "SELECT e FROM Estacion e WHERE e.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Estacion.findByFechaModifico", query = "SELECT e FROM Estacion e WHERE e.fechaModifico = :fechaModifico")
})
public class      Estacion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1967457568;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @Id
    @Column(name = "ID_ESTACION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEstacion;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=100, nullable=false)
    private String nombre;
    
    /**
    * The 'clave iata' Maps to COLUMN 'clave_iata'
    */
    
    @Basic(optional = false)
    @Column(name = "CLAVE_IATA" , length=3, nullable=false)
    private String claveIata;
    
    /**
    * The 'id ciudad' Maps to COLUMN 'id_ciudad'
    */
    
    @JoinColumn(name = "ID_CIUDAD" , referencedColumnName = "ID_CIUDAD")
    @ManyToOne(optional = false )
    private Ciudad ciudad;
    
    /**
    * The 'observaciones' Maps to COLUMN 'observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "OBSERVACIONES" , length=150, nullable=true)
    private String observaciones;
    
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
    * Map the relation to contacto_proveedor_estacion table where has id_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private List<ContactoProveedorEstacion> contactoProveedorEstacionHasEstacionList;
    
    /** 
    * Map the relation to vuelo table where has id_estacion_destino object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionDestino")
    private List<Vuelo> vueloHasEstacionDestinoList;
    
    /** 
    * Map the relation to vuelo table where has id_estacion_origen object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionOrigen")
    private List<Vuelo> vueloHasEstacionOrigenList;
    
    /** 
    * Map the relation to responsable_estacion table where has id_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private List<ResponsableEstacion> responsableEstacionHasEstacionList;
    
    /** 
    * Map the relation to empleado table where has id_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private List<Empleado> empleadoHasEstacionList;
    
    /** 
    * Map the relation to proveedor_estacion table where has id_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private List<ProveedorEstacion> proveedorEstacionHasEstacionList;
    
    /** 
    * Map the relation to acomodo table where has id_estacion_destino object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionDestino")
    private List<Acomodo> acomodoHasEstacionDestinoList;
    
    /** 
    * Map the relation to acomodo table where has id_estacion_origen object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacionOrigen")
    private List<Acomodo> acomodoHasEstacionOrigenList;
    
    /** 
    * Map the relation to ciclo table where has id_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private List<Ciclo> cicloHasEstacionList;
    
    /** 
    * Map the relation to region_abordaje_estacion table where has id_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private List<RegionAbordajeEstacion> regionAbordajeEstacionHasEstacionList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Estacion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdEstacion() { return this.idEstacion;}
    public void setIdEstacion(Integer v) { this.idEstacion = v; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
    public String getClaveIata() { return this.claveIata;}
    public void setClaveIata(String v) { this.claveIata = v; }
    
    public Ciudad getCiudad(){ return this.ciudad ; }
    public void setCiudad(Ciudad x){ this.ciudad = x; }
    
    public String getObservaciones() { return this.observaciones;}
    public void setObservaciones(String v) { this.observaciones = v; }
    
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
    public List<ContactoProveedorEstacion> getContactoProveedorEstacionHasEstacionList(){ return this.contactoProveedorEstacionHasEstacionList; }
    public void setContactoProveedorEstacionHasEstacionList(List<ContactoProveedorEstacion> v){ this.contactoProveedorEstacionHasEstacionList = v; }
    
    public List<Vuelo> getVueloHasEstacionDestinoList(){ return this.vueloHasEstacionDestinoList; }
    public void setVueloHasEstacionDestinoList(List<Vuelo> v){ this.vueloHasEstacionDestinoList = v; }
    
    public List<Vuelo> getVueloHasEstacionOrigenList(){ return this.vueloHasEstacionOrigenList; }
    public void setVueloHasEstacionOrigenList(List<Vuelo> v){ this.vueloHasEstacionOrigenList = v; }
    
    public List<ResponsableEstacion> getResponsableEstacionHasEstacionList(){ return this.responsableEstacionHasEstacionList; }
    public void setResponsableEstacionHasEstacionList(List<ResponsableEstacion> v){ this.responsableEstacionHasEstacionList = v; }
    
    public List<Empleado> getEmpleadoHasEstacionList(){ return this.empleadoHasEstacionList; }
    public void setEmpleadoHasEstacionList(List<Empleado> v){ this.empleadoHasEstacionList = v; }
    
    public List<ProveedorEstacion> getProveedorEstacionHasEstacionList(){ return this.proveedorEstacionHasEstacionList; }
    public void setProveedorEstacionHasEstacionList(List<ProveedorEstacion> v){ this.proveedorEstacionHasEstacionList = v; }
    
    public List<Acomodo> getAcomodoHasEstacionDestinoList(){ return this.acomodoHasEstacionDestinoList; }
    public void setAcomodoHasEstacionDestinoList(List<Acomodo> v){ this.acomodoHasEstacionDestinoList = v; }
    
    public List<Acomodo> getAcomodoHasEstacionOrigenList(){ return this.acomodoHasEstacionOrigenList; }
    public void setAcomodoHasEstacionOrigenList(List<Acomodo> v){ this.acomodoHasEstacionOrigenList = v; }
    
    public List<Ciclo> getCicloHasEstacionList(){ return this.cicloHasEstacionList; }
    public void setCicloHasEstacionList(List<Ciclo> v){ this.cicloHasEstacionList = v; }
    
    public List<RegionAbordajeEstacion> getRegionAbordajeEstacionHasEstacionList(){ return this.regionAbordajeEstacionHasEstacionList; }
    public void setRegionAbordajeEstacionHasEstacionList(List<RegionAbordajeEstacion> v){ this.regionAbordajeEstacionHasEstacionList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idEstacion).hashCode();
		hash += String.valueOf(nombre).hashCode();
		hash += String.valueOf(claveIata).hashCode();
		hash += String.valueOf(ciudad).hashCode();
		hash += String.valueOf(observaciones).hashCode();
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
        if (!(o instanceof Estacion)) {
            return false;
        }		
		Estacion other = (Estacion ) o;
		if (!Objects.equals(this.idEstacion, other.idEstacion)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
		if (!Objects.equals(this.claveIata, other.claveIata)) { return false; }		
		if (!Objects.equals(this.ciudad, other.ciudad)) { return false; }		
		if (!Objects.equals(this.observaciones, other.observaciones)) { return false; }		
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
		sb.append("Estacion{");
		sb.append("idEstacion" ).append("=").append(idEstacion).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
		sb.append("claveIata" ).append("=").append(claveIata).append("|");
		sb.append("ciudad" ).append("=").append(ciudad).append("|");
		sb.append("observaciones" ).append("=").append(observaciones).append("|");
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
		sb.append(" ").append(idEstacion).append(" ");

		return sb.toString().trim();
	}

}
