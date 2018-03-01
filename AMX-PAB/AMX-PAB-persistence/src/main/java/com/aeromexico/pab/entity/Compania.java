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
 * Class for mapping JPA Entity of Table Compania.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Compania")
@NamedQueries({
    @NamedQuery(name = "Compania.findAll", query = "SELECT c FROM Compania c")
    , @NamedQuery(name = "Compania.countAll", query = "SELECT COUNT(c) FROM Compania c")
    , @NamedQuery(name = "Compania.findByIdCompania", query = "SELECT c FROM Compania c WHERE c.idCompania = :idCompania")
    , @NamedQuery(name = "Compania.findByCveCompania", query = "SELECT c FROM Compania c WHERE c.cveCompania = :cveCompania")
    , @NamedQuery(name = "Compania.findByNombre", query = "SELECT c FROM Compania c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Compania.findByStatus", query = "SELECT c FROM Compania c WHERE c.status = :status")
    , @NamedQuery(name = "Compania.findByUsuarioCreo", query = "SELECT c FROM Compania c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Compania.findByFechaCreo", query = "SELECT c FROM Compania c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Compania.findByUsuarioModifico", query = "SELECT c FROM Compania c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Compania.findByFechaModifico", query = "SELECT c FROM Compania c WHERE c.fechaModifico = :fechaModifico")
})
public class      Compania 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -535120460;
    
    /**
    * The 'id compania' Maps to COLUMN 'id_compania'
    */
    
    @Id
    @Column(name = "ID_COMPANIA" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCompania;
    
    /**
    * The 'cve compania' Maps to COLUMN 'cve_compania'
    */
    
    @Basic(optional = false)
    @Column(name = "CVE_COMPANIA" , length=3, nullable=false)
    private String cveCompania;
    
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
    * Map the relation to modelo_avion table where has id_compania object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compania")
    private List<ModeloAvion> modeloAvionHasCompaniaList;
    
    /** 
    * Map the relation to vuelo table where has id_compania object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compania")
    private List<Vuelo> vueloHasCompaniaList;
    
    /** 
    * Map the relation to empleado table where has id_compania object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compania")
    private List<Empleado> empleadoHasCompaniaList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Compania() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdCompania() { return this.idCompania;}
    public void setIdCompania(Integer v) { this.idCompania = v; }
    
    public String getCveCompania() { return this.cveCompania;}
    public void setCveCompania(String v) { this.cveCompania = v; }
    
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
    public List<ModeloAvion> getModeloAvionHasCompaniaList(){ return this.modeloAvionHasCompaniaList; }
    public void setModeloAvionHasCompaniaList(List<ModeloAvion> v){ this.modeloAvionHasCompaniaList = v; }
    
    public List<Vuelo> getVueloHasCompaniaList(){ return this.vueloHasCompaniaList; }
    public void setVueloHasCompaniaList(List<Vuelo> v){ this.vueloHasCompaniaList = v; }
    
    public List<Empleado> getEmpleadoHasCompaniaList(){ return this.empleadoHasCompaniaList; }
    public void setEmpleadoHasCompaniaList(List<Empleado> v){ this.empleadoHasCompaniaList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idCompania).hashCode();
		hash += String.valueOf(cveCompania).hashCode();
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
        if (!(o instanceof Compania)) {
            return false;
        }		
		Compania other = (Compania ) o;
		if (!Objects.equals(this.idCompania, other.idCompania)) { return false; }		
		if (!Objects.equals(this.cveCompania, other.cveCompania)) { return false; }		
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
		sb.append("Compania{");
		sb.append("idCompania" ).append("=").append(idCompania).append("|");
		sb.append("cveCompania" ).append("=").append(cveCompania).append("|");
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
		sb.append(" ").append(cveCompania).append(" ");

		return sb.toString().trim();
	}

}
