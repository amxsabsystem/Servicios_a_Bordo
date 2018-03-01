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
 * Class for mapping JPA Entity of Table ciudad.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "ciudad")
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.countAll", query = "SELECT COUNT(c) FROM Ciudad c")
    , @NamedQuery(name = "Ciudad.findByIdCiudad", query = "SELECT c FROM Ciudad c WHERE c.idCiudad = :idCiudad")
    , @NamedQuery(name = "Ciudad.findByNombre", query = "SELECT c FROM Ciudad c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Ciudad.findBypais", query = "SELECT c FROM Ciudad c WHERE c.pais = :pais")
    , @NamedQuery(name = "Ciudad.findByStatus", query = "SELECT c FROM Ciudad c WHERE c.status = :status")
    , @NamedQuery(name = "Ciudad.findByUsuarioCreo", query = "SELECT c FROM Ciudad c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Ciudad.findByFechaCreo", query = "SELECT c FROM Ciudad c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Ciudad.findByUsuarioModifico", query = "SELECT c FROM Ciudad c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Ciudad.findByFechaModifico", query = "SELECT c FROM Ciudad c WHERE c.fechaModifico = :fechaModifico")
})
public class      Ciudad 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1360126888;
    
    /**
    * The 'id ciudad' Maps to COLUMN 'id_ciudad'
    */
    
    @Id
    @Column(name = "ID_CIUDAD" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCiudad;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=50, nullable=false)
    private String nombre;
    
    /**
    * The 'id pais' Maps to COLUMN 'id_pais'
    */
    
    @JoinColumn(name = "ID_PAIS" , referencedColumnName = "ID_PAIS")
    @ManyToOne(optional = false )
    private Pais pais;
    
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
    * Map the relation to estacion table where has id_ciudad object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudad")
    private List<Estacion> estacionHasCiudadList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Ciudad() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdCiudad() { return this.idCiudad;}
    public void setIdCiudad(Integer v) { this.idCiudad = v; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
    public Pais getPais(){ return this.pais ; }
    public void setPais(Pais x){ this.pais = x; }
    
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
    public List<Estacion> getEstacionHasCiudadList(){ return this.estacionHasCiudadList; }
    public void setEstacionHasCiudadList(List<Estacion> v){ this.estacionHasCiudadList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idCiudad).hashCode();
		hash += String.valueOf(nombre).hashCode();
		hash += String.valueOf(pais).hashCode();
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
        if (!(o instanceof Ciudad)) {
            return false;
        }		
		Ciudad other = (Ciudad ) o;
		if (!Objects.equals(this.idCiudad, other.idCiudad)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
		if (!Objects.equals(this.pais, other.pais)) { return false; }		
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
		sb.append("Ciudad{");
		sb.append("idCiudad" ).append("=").append(idCiudad).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
		sb.append("pais" ).append("=").append(pais).append("|");
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
		sb.append(" ").append(idCiudad).append(" ");

		return sb.toString().trim();
	}

}
