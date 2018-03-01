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
 * Class for mapping JPA Entity of Table Material_TSU.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Material_TSU")
@NamedQueries({
    @NamedQuery(name = "MaterialTsu.findAll", query = "SELECT m FROM MaterialTsu m")
    , @NamedQuery(name = "MaterialTsu.countAll", query = "SELECT COUNT(m) FROM MaterialTsu m")
    , @NamedQuery(name = "MaterialTsu.findByIdMaterialTsu", query = "SELECT m FROM MaterialTsu m WHERE m.idMaterialTsu = :idMaterialTsu")
    , @NamedQuery(name = "MaterialTsu.findBymaterial", query = "SELECT m FROM MaterialTsu m WHERE m.material = :material")
    , @NamedQuery(name = "MaterialTsu.findBytsu", query = "SELECT m FROM MaterialTsu m WHERE m.tsu = :tsu")
    , @NamedQuery(name = "MaterialTsu.findByCantidad", query = "SELECT m FROM MaterialTsu m WHERE m.cantidad = :cantidad")
    , @NamedQuery(name = "MaterialTsu.findByStatus", query = "SELECT m FROM MaterialTsu m WHERE m.status = :status")
    , @NamedQuery(name = "MaterialTsu.findByUsuarioCreo", query = "SELECT m FROM MaterialTsu m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "MaterialTsu.findByFechaCreo", query = "SELECT m FROM MaterialTsu m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "MaterialTsu.findByUsuarioModifico", query = "SELECT m FROM MaterialTsu m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "MaterialTsu.findByFechaModifico", query = "SELECT m FROM MaterialTsu m WHERE m.fechaModifico = :fechaModifico")
})
public class      MaterialTsu 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1950989634;
    
    /**
    * The 'id material tsu' Maps to COLUMN 'id_material_tsu'
    */
    
    @Id
    @Column(name = "ID_MATERIAL_TSU" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMaterialTsu;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material material;
    
    /**
    * The 'id tsu' Maps to COLUMN 'id_tsu'
    */
    
    @JoinColumn(name = "ID_TSU" , referencedColumnName = "ID_TSU")
    @ManyToOne(optional = true )
    private Tsu tsu;
    
    /**
    * The 'cantidad' Maps to COLUMN 'cantidad'
    */
    
    @Basic(optional = false)
    @Column(name = "CANTIDAD" , nullable=false)
    private int cantidad;
    
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

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public MaterialTsu() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdMaterialTsu() { return this.idMaterialTsu;}
    public void setIdMaterialTsu(Integer v) { this.idMaterialTsu = v; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public Tsu getTsu(){ return this.tsu ; }
    public void setTsu(Tsu x){ this.tsu = x; }
    
    public int getCantidad() { return this.cantidad;}
    public void setCantidad(int v) { this.cantidad = v; }
    
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
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idMaterialTsu).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(tsu).hashCode();
		hash += String.valueOf(cantidad).hashCode();
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
        if (!(o instanceof MaterialTsu)) {
            return false;
        }		
		MaterialTsu other = (MaterialTsu ) o;
		if (!Objects.equals(this.idMaterialTsu, other.idMaterialTsu)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.tsu, other.tsu)) { return false; }		
		if (!Objects.equals(this.cantidad, other.cantidad)) { return false; }		
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
		sb.append("MaterialTsu{");
		sb.append("idMaterialTsu" ).append("=").append(idMaterialTsu).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("tsu" ).append("=").append(tsu).append("|");
		sb.append("cantidad" ).append("=").append(cantidad).append("|");
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
		sb.append(" ").append(idMaterialTsu).append(" ");

		return sb.toString().trim();
	}

}
