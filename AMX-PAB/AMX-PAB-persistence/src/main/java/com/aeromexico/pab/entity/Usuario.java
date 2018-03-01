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
 * Class for mapping JPA Entity of Table usuario.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.countAll", query = "SELECT COUNT(u) FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByEmailUsuario", query = "SELECT u FROM Usuario u WHERE u.emailUsuario = :emailUsuario")
    , @NamedQuery(name = "Usuario.findByContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidoPaterno", query = "SELECT u FROM Usuario u WHERE u.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Usuario.findByApellidoMaterno", query = "SELECT u FROM Usuario u WHERE u.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo")
    , @NamedQuery(name = "Usuario.findByStatus", query = "SELECT u FROM Usuario u WHERE u.status = :status")
    , @NamedQuery(name = "Usuario.findByUsuarioCreo", query = "SELECT u FROM Usuario u WHERE u.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Usuario.findByFechaCreo", query = "SELECT u FROM Usuario u WHERE u.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Usuario.findByUsuarioModifico", query = "SELECT u FROM Usuario u WHERE u.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Usuario.findByFechaModifico", query = "SELECT u FROM Usuario u WHERE u.fechaModifico = :fechaModifico")
})
public class      Usuario 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -132844754;
    
    /**
    * The 'email usuario' Maps to COLUMN 'email_usuario'
    */
    
    @Id
    @Column(name = "EMAIL_USUARIO" , length=100, nullable=false  )
    private String emailUsuario;
    
    /**
    * The 'contrasenia' Maps to COLUMN 'contrasenia'
    */
    
    @Basic(optional = false)
    @Column(name = "CONTRASENIA" , length=255, nullable=false)
    private String contrasenia;
    
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
    
    @Basic(optional = true)
    @Column(name = "APELLIDO_MATERNO" , length=50, nullable=true)
    private String apellidoMaterno;
    
    /**
    * The 'tipo' Maps to COLUMN 'tipo'
    */
    
    @Basic(optional = false)
    @Column(name = "TIPO" , length=1, nullable=false)
    private String tipo;
    
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
    * Map the relation to contacto_proveedor_estacion table where has email_usuario object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<ContactoProveedorEstacion> contactoProveedorEstacionHasUsuarioList;
    
    /** 
    * Map the relation to empleado table where has email_usuario object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Empleado> empleadoHasUsuarioList;
    

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "usuarioList")
    private List<Perfil> perfilList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Usuario() {
    }
    
    /**
     * Getters and Setters
     */
    public String getEmailUsuario() { return this.emailUsuario;}
    public void setEmailUsuario(String v) { this.emailUsuario = v; }
    
    public String getContrasenia() { return this.contrasenia;}
    public void setContrasenia(String v) { this.contrasenia = v; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
    public String getApellidoPaterno() { return this.apellidoPaterno;}
    public void setApellidoPaterno(String v) { this.apellidoPaterno = v; }
    
    public String getApellidoMaterno() { return this.apellidoMaterno;}
    public void setApellidoMaterno(String v) { this.apellidoMaterno = v; }
    
    public String getTipo() { return this.tipo;}
    public void setTipo(String v) { this.tipo = v; }
    
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
    public List<ContactoProveedorEstacion> getContactoProveedorEstacionHasUsuarioList(){ return this.contactoProveedorEstacionHasUsuarioList; }
    public void setContactoProveedorEstacionHasUsuarioList(List<ContactoProveedorEstacion> v){ this.contactoProveedorEstacionHasUsuarioList = v; }
    
    public List<Empleado> getEmpleadoHasUsuarioList(){ return this.empleadoHasUsuarioList; }
    public void setEmpleadoHasUsuarioList(List<Empleado> v){ this.empleadoHasUsuarioList = v; }
    
	// M2M <*>
    public List<Perfil> getPerfilList() { return this.perfilList; }
    public void setPerfilList(List<Perfil>  v) { this.perfilList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(emailUsuario).hashCode();
		hash += String.valueOf(contrasenia).hashCode();
		hash += String.valueOf(nombre).hashCode();
		hash += String.valueOf(apellidoPaterno).hashCode();
		hash += String.valueOf(apellidoMaterno).hashCode();
		hash += String.valueOf(tipo).hashCode();
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
        if (!(o instanceof Usuario)) {
            return false;
        }		
		Usuario other = (Usuario ) o;
		if (!Objects.equals(this.emailUsuario, other.emailUsuario)) { return false; }		
		if (!Objects.equals(this.contrasenia, other.contrasenia)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
		if (!Objects.equals(this.apellidoPaterno, other.apellidoPaterno)) { return false; }		
		if (!Objects.equals(this.apellidoMaterno, other.apellidoMaterno)) { return false; }		
		if (!Objects.equals(this.tipo, other.tipo)) { return false; }		
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
		sb.append("Usuario{");
		sb.append("emailUsuario" ).append("=").append(emailUsuario).append("|");
		sb.append("contrasenia" ).append("=").append(contrasenia).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
		sb.append("apellidoPaterno" ).append("=").append(apellidoPaterno).append("|");
		sb.append("apellidoMaterno" ).append("=").append(apellidoMaterno).append("|");
		sb.append("tipo" ).append("=").append(tipo).append("|");
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
		sb.append(" ").append(nombre).append(" ");
		sb.append(" ").append(apellidoPaterno).append(" ");
		sb.append(" ").append(apellidoMaterno).append(" ");

		return sb.toString().trim();
	}

}
