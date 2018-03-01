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
 * Class for mapping JPA Entity of Table proveedor.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "proveedor")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.countAll", query = "SELECT COUNT(p) FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByClaveProveedor", query = "SELECT p FROM Proveedor p WHERE p.claveProveedor = :claveProveedor")
    , @NamedQuery(name = "Proveedor.findByNombre", query = "SELECT p FROM Proveedor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Proveedor.findByRazonSocial", query = "SELECT p FROM Proveedor p WHERE p.razonSocial = :razonSocial")
    , @NamedQuery(name = "Proveedor.findByStatus", query = "SELECT p FROM Proveedor p WHERE p.status = :status")
    , @NamedQuery(name = "Proveedor.findByUsuarioCreo", query = "SELECT p FROM Proveedor p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Proveedor.findByFechaCreo", query = "SELECT p FROM Proveedor p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Proveedor.findByUsuarioModifico", query = "SELECT p FROM Proveedor p WHERE p.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Proveedor.findByFechaModifico", query = "SELECT p FROM Proveedor p WHERE p.fechaModifico = :fechaModifico")
})
public class      Proveedor 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -551236898;
    
    /**
    * The 'clave proveedor' Maps to COLUMN 'clave_proveedor'
    */
    
    @Id
    @Column(name = "CLAVE_PROVEEDOR" , length=10, nullable=false  )
    private String claveProveedor;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=50, nullable=false)
    private String nombre;
    
    /**
    * The 'razon social' Maps to COLUMN 'razon_social'
    */
    
    @Basic(optional = true)
    @Column(name = "RAZON_SOCIAL" , length=150, nullable=true)
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
    * Map the relation to contacto_proveedor_estacion table where has clave_proveedor object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<ContactoProveedorEstacion> contactoProveedorEstacionHasProveedorList;
    
    /** 
    * Map the relation to potencial table where has clave_proveedor object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Potencial> potencialHasProveedorList;
    
    /** 
    * Map the relation to proveedor_estacion table where has clave_proveedor object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<ProveedorEstacion> proveedorEstacionHasProveedorList;
    
    /** 
    * Map the relation to ciclo table where has clave_proveedor object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Ciclo> cicloHasProveedorList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "PROVEEDOR_REGION",
               joinColumns        = {@JoinColumn(name = "CLAVE_PROVEEDOR", referencedColumnName ="CLAVE_PROVEEDOR")},
               inverseJoinColumns = {@JoinColumn(name = "ID_REGION", referencedColumnName ="ID_REGION")}
               )
    private List<Region> regionList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Proveedor() {
    }
    
    /**
     * Getters and Setters
     */
    public String getClaveProveedor() { return this.claveProveedor;}
    public void setClaveProveedor(String v) { this.claveProveedor = v; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
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
    public List<ContactoProveedorEstacion> getContactoProveedorEstacionHasProveedorList(){ return this.contactoProveedorEstacionHasProveedorList; }
    public void setContactoProveedorEstacionHasProveedorList(List<ContactoProveedorEstacion> v){ this.contactoProveedorEstacionHasProveedorList = v; }
    
    public List<Potencial> getPotencialHasProveedorList(){ return this.potencialHasProveedorList; }
    public void setPotencialHasProveedorList(List<Potencial> v){ this.potencialHasProveedorList = v; }
    
    public List<ProveedorEstacion> getProveedorEstacionHasProveedorList(){ return this.proveedorEstacionHasProveedorList; }
    public void setProveedorEstacionHasProveedorList(List<ProveedorEstacion> v){ this.proveedorEstacionHasProveedorList = v; }
    
    public List<Ciclo> getCicloHasProveedorList(){ return this.cicloHasProveedorList; }
    public void setCicloHasProveedorList(List<Ciclo> v){ this.cicloHasProveedorList = v; }
    
	// M2M <*>
    public List<Region> getRegionList() { return this.regionList; }
    public void setRegionList(List<Region>  v) { this.regionList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(claveProveedor).hashCode();
		hash += String.valueOf(nombre).hashCode();
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
        if (!(o instanceof Proveedor)) {
            return false;
        }		
		Proveedor other = (Proveedor ) o;
		if (!Objects.equals(this.claveProveedor, other.claveProveedor)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
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
		sb.append("Proveedor{");
		sb.append("claveProveedor" ).append("=").append(claveProveedor).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
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
		sb.append(" ").append(claveProveedor).append(" ");

		return sb.toString().trim();
	}

}
