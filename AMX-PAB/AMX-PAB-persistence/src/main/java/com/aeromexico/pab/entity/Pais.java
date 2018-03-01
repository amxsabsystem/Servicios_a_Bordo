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
 * Class for mapping JPA Entity of Table pais.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "pais")
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
    , @NamedQuery(name = "Pais.countAll", query = "SELECT COUNT(p) FROM Pais p")
    , @NamedQuery(name = "Pais.findByIdPais", query = "SELECT p FROM Pais p WHERE p.idPais = :idPais")
    , @NamedQuery(name = "Pais.findByNombre", query = "SELECT p FROM Pais p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Pais.findByregion", query = "SELECT p FROM Pais p WHERE p.region = :region")
    , @NamedQuery(name = "Pais.findByStatus", query = "SELECT p FROM Pais p WHERE p.status = :status")
    , @NamedQuery(name = "Pais.findByUsuarioCreo", query = "SELECT p FROM Pais p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Pais.findByFechaCreo", query = "SELECT p FROM Pais p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Pais.findByUsuarioModifico", query = "SELECT p FROM Pais p WHERE p.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Pais.findByFechaModifico", query = "SELECT p FROM Pais p WHERE p.fechaModifico = :fechaModifico")
})
public class      Pais 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 3433179;
    
    /**
    * The 'id pais' Maps to COLUMN 'id_pais'
    */
    
    @Id
    @Column(name = "ID_PAIS" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idPais;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=50, nullable=false)
    private String nombre;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @JoinColumn(name = "ID_REGION" , referencedColumnName = "ID_REGION")
    @ManyToOne(optional = false )
    private Region region;
    
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
    * Map the relation to ciudad table where has id_pais object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    private List<Ciudad> ciudadHasPaisList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Pais() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdPais() { return this.idPais;}
    public void setIdPais(Integer v) { this.idPais = v; }
    
    public String getNombre() { return this.nombre;}
    public void setNombre(String v) { this.nombre = v; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
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
    public List<Ciudad> getCiudadHasPaisList(){ return this.ciudadHasPaisList; }
    public void setCiudadHasPaisList(List<Ciudad> v){ this.ciudadHasPaisList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idPais).hashCode();
		hash += String.valueOf(nombre).hashCode();
		hash += String.valueOf(region).hashCode();
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
        if (!(o instanceof Pais)) {
            return false;
        }		
		Pais other = (Pais ) o;
		if (!Objects.equals(this.idPais, other.idPais)) { return false; }		
		if (!Objects.equals(this.nombre, other.nombre)) { return false; }		
		if (!Objects.equals(this.region, other.region)) { return false; }		
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
		sb.append("Pais{");
		sb.append("idPais" ).append("=").append(idPais).append("|");
		sb.append("nombre" ).append("=").append(nombre).append("|");
		sb.append("region" ).append("=").append(region).append("|");
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
		sb.append(" ").append(idPais).append(" ");

		return sb.toString().trim();
	}

}
