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
 * Class for mapping JPA Entity of Table empleado.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.countAll", query = "SELECT COUNT(e) FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Empleado.findByusuario", query = "SELECT e FROM Empleado e WHERE e.usuario = :usuario")
    , @NamedQuery(name = "Empleado.findBycompania", query = "SELECT e FROM Empleado e WHERE e.compania = :compania")
    , @NamedQuery(name = "Empleado.findByarea", query = "SELECT e FROM Empleado e WHERE e.area = :area")
    , @NamedQuery(name = "Empleado.findByestacion", query = "SELECT e FROM Empleado e WHERE e.estacion = :estacion")
    , @NamedQuery(name = "Empleado.findByEmailJefe", query = "SELECT e FROM Empleado e WHERE e.emailJefe = :emailJefe")
    , @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleado.findByExtension", query = "SELECT e FROM Empleado e WHERE e.extension = :extension")
    , @NamedQuery(name = "Empleado.findByDirectorioSab", query = "SELECT e FROM Empleado e WHERE e.directorioSab = :directorioSab")
    , @NamedQuery(name = "Empleado.findByPuestoEs", query = "SELECT e FROM Empleado e WHERE e.puestoEs = :puestoEs")
    , @NamedQuery(name = "Empleado.findByPuestoEn", query = "SELECT e FROM Empleado e WHERE e.puestoEn = :puestoEn")
    , @NamedQuery(name = "Empleado.findByStatus", query = "SELECT e FROM Empleado e WHERE e.status = :status")
    , @NamedQuery(name = "Empleado.findByUsuarioCreo", query = "SELECT e FROM Empleado e WHERE e.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Empleado.findByFechaCreo", query = "SELECT e FROM Empleado e WHERE e.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Empleado.findByUsuarioModifico", query = "SELECT e FROM Empleado e WHERE e.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Empleado.findByFechaModifico", query = "SELECT e FROM Empleado e WHERE e.fechaModifico = :fechaModifico")
})
public class      Empleado 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1193148619;
    
    /**
    * The 'id empleado' Maps to COLUMN 'id_empleado'
    */
    
    @Id
    @Column(name = "ID_EMPLEADO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEmpleado;
    
    /**
    * The 'email usuario' Maps to COLUMN 'email_usuario'
    */
    
    @JoinColumn(name = "EMAIL_USUARIO" , referencedColumnName = "EMAIL_USUARIO")
    @ManyToOne(optional = false )
    private Usuario usuario;
    
    /**
    * The 'id compania' Maps to COLUMN 'id_compania'
    */
    
    @JoinColumn(name = "ID_COMPANIA" , referencedColumnName = "ID_COMPANIA")
    @ManyToOne(optional = false )
    private Compania compania;
    
    /**
    * The 'id area' Maps to COLUMN 'id_area'
    */
    
    @JoinColumn(name = "ID_AREA" , referencedColumnName = "ID_AREA")
    @ManyToOne(optional = false )
    private Area area;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @JoinColumn(name = "ID_ESTACION" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = true )
    private Estacion estacion;
    
    /**
    * The 'email jefe' Maps to COLUMN 'email_jefe'
    */
    
    @Basic(optional = true)
    @Column(name = "EMAIL_JEFE" , length=100, nullable=true)
    private String emailJefe;
    
    /**
    * The 'telefono' Maps to COLUMN 'telefono'
    */
    
    @Basic(optional = true)
    @Column(name = "TELEFONO" , length=25, nullable=true)
    private String telefono;
    
    /**
    * The 'extension' Maps to COLUMN 'extension'
    */
    
    @Basic(optional = true)
    @Column(name = "EXTENSION" , length=10, nullable=true)
    private String extension;
    
    /**
    * The 'directorio sab' Maps to COLUMN 'directorio_sab'
    */
    
    @Basic(optional = false)
    @Column(name = "DIRECTORIO_SAB" , nullable=false)
    private Short directorioSab;
    
    /**
    * The 'puesto es' Maps to COLUMN 'puesto_es'
    */
    
    @Basic(optional = false)
    @Column(name = "PUESTO_ES" , length=64, nullable=false)
    private String puestoEs;
    
    /**
    * The 'puesto en' Maps to COLUMN 'puesto_en'
    */
    
    @Basic(optional = false)
    @Column(name = "PUESTO_EN" , length=64, nullable=false)
    private String puestoEn;
    
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
    * Map the relation to responsable_estacion table where has id_empleado_jefe object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoJefe")
    private List<ResponsableEstacion> responsableEstacionHasEmpleadoJefeList;
    
    /** 
    * Map the relation to responsable_estacion table where has id_empleado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<ResponsableEstacion> responsableEstacionHasEmpleadoList;
    
    /** 
    * Map the relation to Comunicado table where has id_empleado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Comunicado> comunicadoHasEmpleadoList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Empleado() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdEmpleado() { return this.idEmpleado;}
    public void setIdEmpleado(Integer v) { this.idEmpleado = v; }
    
    public Usuario getUsuario(){ return this.usuario ; }
    public void setUsuario(Usuario x){ this.usuario = x; }
    
    public Compania getCompania(){ return this.compania ; }
    public void setCompania(Compania x){ this.compania = x; }
    
    public Area getArea(){ return this.area ; }
    public void setArea(Area x){ this.area = x; }
    
    public Estacion getEstacion(){ return this.estacion ; }
    public void setEstacion(Estacion x){ this.estacion = x; }
    
    public String getEmailJefe() { return this.emailJefe;}
    public void setEmailJefe(String v) { this.emailJefe = v; }
    
    public String getTelefono() { return this.telefono;}
    public void setTelefono(String v) { this.telefono = v; }
    
    public String getExtension() { return this.extension;}
    public void setExtension(String v) { this.extension = v; }
    
    public Short getDirectorioSab() { return this.directorioSab;}
    public void setDirectorioSab(Short v) { this.directorioSab = v; }
    
    public String getPuestoEs() { return this.puestoEs;}
    public void setPuestoEs(String v) { this.puestoEs = v; }
    
    public String getPuestoEn() { return this.puestoEn;}
    public void setPuestoEn(String v) { this.puestoEn = v; }
    
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
    public List<ResponsableEstacion> getResponsableEstacionHasEmpleadoJefeList(){ return this.responsableEstacionHasEmpleadoJefeList; }
    public void setResponsableEstacionHasEmpleadoJefeList(List<ResponsableEstacion> v){ this.responsableEstacionHasEmpleadoJefeList = v; }
    
    public List<ResponsableEstacion> getResponsableEstacionHasEmpleadoList(){ return this.responsableEstacionHasEmpleadoList; }
    public void setResponsableEstacionHasEmpleadoList(List<ResponsableEstacion> v){ this.responsableEstacionHasEmpleadoList = v; }
    
    public List<Comunicado> getComunicadoHasEmpleadoList(){ return this.comunicadoHasEmpleadoList; }
    public void setComunicadoHasEmpleadoList(List<Comunicado> v){ this.comunicadoHasEmpleadoList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idEmpleado).hashCode();
		hash += String.valueOf(usuario).hashCode();
		hash += String.valueOf(compania).hashCode();
		hash += String.valueOf(area).hashCode();
		hash += String.valueOf(estacion).hashCode();
		hash += String.valueOf(emailJefe).hashCode();
		hash += String.valueOf(telefono).hashCode();
		hash += String.valueOf(extension).hashCode();
		hash += String.valueOf(directorioSab).hashCode();
		hash += String.valueOf(puestoEs).hashCode();
		hash += String.valueOf(puestoEn).hashCode();
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
        if (!(o instanceof Empleado)) {
            return false;
        }		
		Empleado other = (Empleado ) o;
		if (!Objects.equals(this.idEmpleado, other.idEmpleado)) { return false; }		
		if (!Objects.equals(this.usuario, other.usuario)) { return false; }		
		if (!Objects.equals(this.compania, other.compania)) { return false; }		
		if (!Objects.equals(this.area, other.area)) { return false; }		
		if (!Objects.equals(this.estacion, other.estacion)) { return false; }		
		if (!Objects.equals(this.emailJefe, other.emailJefe)) { return false; }		
		if (!Objects.equals(this.telefono, other.telefono)) { return false; }		
		if (!Objects.equals(this.extension, other.extension)) { return false; }		
		if (!Objects.equals(this.directorioSab, other.directorioSab)) { return false; }		
		if (!Objects.equals(this.puestoEs, other.puestoEs)) { return false; }		
		if (!Objects.equals(this.puestoEn, other.puestoEn)) { return false; }		
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
		sb.append("Empleado{");
		sb.append("idEmpleado" ).append("=").append(idEmpleado).append("|");
		sb.append("usuario" ).append("=").append(usuario).append("|");
		sb.append("compania" ).append("=").append(compania).append("|");
		sb.append("area" ).append("=").append(area).append("|");
		sb.append("estacion" ).append("=").append(estacion).append("|");
		sb.append("emailJefe" ).append("=").append(emailJefe).append("|");
		sb.append("telefono" ).append("=").append(telefono).append("|");
		sb.append("extension" ).append("=").append(extension).append("|");
		sb.append("directorioSab" ).append("=").append(directorioSab).append("|");
		sb.append("puestoEs" ).append("=").append(puestoEs).append("|");
		sb.append("puestoEn" ).append("=").append(puestoEn).append("|");
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
		sb.append(" ").append(idEmpleado).append(" ");

		return sb.toString().trim();
	}

}
