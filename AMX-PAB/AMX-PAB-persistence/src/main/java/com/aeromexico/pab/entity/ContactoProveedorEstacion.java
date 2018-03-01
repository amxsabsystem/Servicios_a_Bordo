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
 * Class for mapping JPA Entity of Table contacto_proveedor_estacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "contacto_proveedor_estacion")
@NamedQueries({
    @NamedQuery(name = "ContactoProveedorEstacion.findAll", query = "SELECT c FROM ContactoProveedorEstacion c")
    , @NamedQuery(name = "ContactoProveedorEstacion.countAll", query = "SELECT COUNT(c) FROM ContactoProveedorEstacion c")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByIdContactoProveedorEstacion", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.idContactoProveedorEstacion = :idContactoProveedorEstacion")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByusuario", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByNombre", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByApellidoPaterno", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByApellidoMaterno", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByproveedor", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.proveedor = :proveedor")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByestacion", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.estacion = :estacion")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByproveedorEstacion", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.proveedorEstacion = :proveedorEstacion")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByTelefono", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByExtension", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.extension = :extension")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByPuestoAreaEn", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.puestoAreaEn = :puestoAreaEn")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByPuestoAreaEs", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.puestoAreaEs = :puestoAreaEs")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByDirectorioPab", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.directorioPab = :directorioPab")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByStatus", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.status = :status")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByUsuarioCreo", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByFechaCreo", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByUsuarioModifico", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "ContactoProveedorEstacion.findByFechaModifico", query = "SELECT c FROM ContactoProveedorEstacion c WHERE c.fechaModifico = :fechaModifico")
})
public class      ContactoProveedorEstacion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -760166127;
    
    /**
    * The 'id contacto proveedor estacion' Maps to COLUMN 'id_contacto_proveedor_estacion'
    */
    
    @Id
    @Column(name = "ID_CONTACTO_PROVEEDOR_ESTACION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idContactoProveedorEstacion;
    
    /**
    * The 'email usuario' Maps to COLUMN 'email_usuario'
    */
    
    @JoinColumn(name = "EMAIL_USUARIO" , referencedColumnName = "EMAIL_USUARIO")
    @ManyToOne(optional = true )
    private Usuario usuario;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=50, nullable=false)
    private String nombre;
    
    /**
    * The 'apellido paterno' Maps to COLUMN 'apellido_paterno'
    */
    
    @Basic(optional = false)
    @Column(name = "APELLIDO_PATERNO" , length=50, nullable=false)
    private String apellidoPaterno;
    
    /**
    * The 'apellido materno' Maps to COLUMN 'apellido_materno'
    */
    
    @Basic(optional = false)
    @Column(name = "APELLIDO_MATERNO" , length=50, nullable=false)
    private String apellidoMaterno;
    
    /**
    * The 'clave proveedor' Maps to COLUMN 'clave_proveedor'
    */
    
    @JoinColumn(name = "CLAVE_PROVEEDOR" , referencedColumnName = "CLAVE_PROVEEDOR")
    @ManyToOne(optional = false )
    private Proveedor proveedor;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @JoinColumn(name = "ID_ESTACION" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacion;
    
    /**
    * The 'id proveedor estacion' Maps to COLUMN 'id_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_PROVEEDOR_ESTACION" , referencedColumnName = "ID_PROVEEDOR_ESTACION")
    @ManyToOne(optional = false )
    private ProveedorEstacion proveedorEstacion;
    
    /**
    * The 'telefono' Maps to COLUMN 'telefono'
    */
    
    @Basic(optional = true)
    @Column(name = "TELEFONO" , length=15, nullable=true)
    private String telefono;
    
    /**
    * The 'extension' Maps to COLUMN 'extension'
    */
    
    @Basic(optional = true)
    @Column(name = "EXTENSION" , length=5, nullable=true)
    private String extension;
    
    /**
    * The 'puesto area en' Maps to COLUMN 'puesto_area_en'
    */
    
    @Basic(optional = true)
    @Column(name = "PUESTO_AREA_EN" , length=150, nullable=true)
    private String puestoAreaEn;
    
    /**
    * The 'puesto area es' Maps to COLUMN 'puesto_area_es'
    */
    
    @Basic(optional = true)
    @Column(name = "PUESTO_AREA_ES" , length=150, nullable=true)
    private String puestoAreaEs;
    
    /**
    * The 'directorio pab' Maps to COLUMN 'directorio_pab'
    */
    
    @Basic(optional = false)
    @Column(name = "DIRECTORIO_PAB" , nullable=false)
    private Short directorioPab;
    
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
    * Map the relation to bitacora_comunicado table where has id_contacto_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactoProveedorEstacion")
    private List<BitacoraComunicado> bitacoraComunicadoHasContactoProveedorEstacionList;
    
    /** 
    * Map the relation to seguimiento table where has id_contacto_proveedor_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactoProveedorEstacion")
    private List<Seguimiento> seguimientoHasContactoProveedorEstacionList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ContactoProveedorEstacion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdContactoProveedorEstacion() { return this.idContactoProveedorEstacion;}
    public void setIdContactoProveedorEstacion(Integer v) { this.idContactoProveedorEstacion = v; }
    
    public Usuario getUsuario(){ return this.usuario ; }
    public void setUsuario(Usuario x){ this.usuario = x; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
    public String getApellidoPaterno() { return this.apellidoPaterno;}
    public void setApellidoPaterno(String v) { this.apellidoPaterno = v; }
    
    public String getApellidoMaterno() { return this.apellidoMaterno;}
    public void setApellidoMaterno(String v) { this.apellidoMaterno = v; }
    
    public Proveedor getProveedor(){ return this.proveedor ; }
    public void setProveedor(Proveedor x){ this.proveedor = x; }
    
    public Estacion getEstacion(){ return this.estacion ; }
    public void setEstacion(Estacion x){ this.estacion = x; }
    
    public ProveedorEstacion getProveedorEstacion(){ return this.proveedorEstacion ; }
    public void setProveedorEstacion(ProveedorEstacion x){ this.proveedorEstacion = x; }
    
    public String getTelefono() { return this.telefono;}
    public void setTelefono(String v) { this.telefono = v; }
    
    public String getExtension() { return this.extension;}
    public void setExtension(String v) { this.extension = v; }
    
    public String getPuestoAreaEn() { return this.puestoAreaEn;}
    public void setPuestoAreaEn(String v) { this.puestoAreaEn = v; }
    
    public String getPuestoAreaEs() { return this.puestoAreaEs;}
    public void setPuestoAreaEs(String v) { this.puestoAreaEs = v; }
    
    public Short getDirectorioPab() { return this.directorioPab;}
    public void setDirectorioPab(Short v) { this.directorioPab = v; }
    
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
    public List<BitacoraComunicado> getBitacoraComunicadoHasContactoProveedorEstacionList(){ return this.bitacoraComunicadoHasContactoProveedorEstacionList; }
    public void setBitacoraComunicadoHasContactoProveedorEstacionList(List<BitacoraComunicado> v){ this.bitacoraComunicadoHasContactoProveedorEstacionList = v; }
    
    public List<Seguimiento> getSeguimientoHasContactoProveedorEstacionList(){ return this.seguimientoHasContactoProveedorEstacionList; }
    public void setSeguimientoHasContactoProveedorEstacionList(List<Seguimiento> v){ this.seguimientoHasContactoProveedorEstacionList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idContactoProveedorEstacion).hashCode();
		hash += String.valueOf(usuario).hashCode();
		hash += String.valueOf(nombre).hashCode();
		hash += String.valueOf(apellidoPaterno).hashCode();
		hash += String.valueOf(apellidoMaterno).hashCode();
		hash += String.valueOf(proveedor).hashCode();
		hash += String.valueOf(estacion).hashCode();
		hash += String.valueOf(proveedorEstacion).hashCode();
		hash += String.valueOf(telefono).hashCode();
		hash += String.valueOf(extension).hashCode();
		hash += String.valueOf(puestoAreaEn).hashCode();
		hash += String.valueOf(puestoAreaEs).hashCode();
		hash += String.valueOf(directorioPab).hashCode();
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
        if (!(o instanceof ContactoProveedorEstacion)) {
            return false;
        }		
		ContactoProveedorEstacion other = (ContactoProveedorEstacion ) o;
		if (!Objects.equals(this.idContactoProveedorEstacion, other.idContactoProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.usuario, other.usuario)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
		if (!Objects.equals(this.apellidoPaterno, other.apellidoPaterno)) { return false; }		
		if (!Objects.equals(this.apellidoMaterno, other.apellidoMaterno)) { return false; }		
		if (!Objects.equals(this.proveedor, other.proveedor)) { return false; }		
		if (!Objects.equals(this.estacion, other.estacion)) { return false; }		
		if (!Objects.equals(this.proveedorEstacion, other.proveedorEstacion)) { return false; }		
		if (!Objects.equals(this.telefono, other.telefono)) { return false; }		
		if (!Objects.equals(this.extension, other.extension)) { return false; }		
		if (!Objects.equals(this.puestoAreaEn, other.puestoAreaEn)) { return false; }		
		if (!Objects.equals(this.puestoAreaEs, other.puestoAreaEs)) { return false; }		
		if (!Objects.equals(this.directorioPab, other.directorioPab)) { return false; }		
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
		sb.append("ContactoProveedorEstacion{");
		sb.append("idContactoProveedorEstacion" ).append("=").append(idContactoProveedorEstacion).append("|");
		sb.append("usuario" ).append("=").append(usuario).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
		sb.append("apellidoPaterno" ).append("=").append(apellidoPaterno).append("|");
		sb.append("apellidoMaterno" ).append("=").append(apellidoMaterno).append("|");
		sb.append("proveedor" ).append("=").append(proveedor).append("|");
		sb.append("estacion" ).append("=").append(estacion).append("|");
		sb.append("proveedorEstacion" ).append("=").append(proveedorEstacion).append("|");
		sb.append("telefono" ).append("=").append(telefono).append("|");
		sb.append("extension" ).append("=").append(extension).append("|");
		sb.append("puestoAreaEn" ).append("=").append(puestoAreaEn).append("|");
		sb.append("puestoAreaEs" ).append("=").append(puestoAreaEs).append("|");
		sb.append("directorioPab" ).append("=").append(directorioPab).append("|");
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
		sb.append(" ").append(idContactoProveedorEstacion).append(" ");

		return sb.toString().trim();
	}

}
