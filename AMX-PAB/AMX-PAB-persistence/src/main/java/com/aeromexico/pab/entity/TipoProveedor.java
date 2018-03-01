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
 * Class for mapping JPA Entity of Table tipo_proveedor.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tipo_proveedor")
@NamedQueries({
    @NamedQuery(name = "TipoProveedor.findAll", query = "SELECT t FROM TipoProveedor t")
    , @NamedQuery(name = "TipoProveedor.countAll", query = "SELECT COUNT(t) FROM TipoProveedor t")
    , @NamedQuery(name = "TipoProveedor.findByIdTipoProveedor", query = "SELECT t FROM TipoProveedor t WHERE t.idTipoProveedor = :idTipoProveedor")
    , @NamedQuery(name = "TipoProveedor.findByNombreEs", query = "SELECT t FROM TipoProveedor t WHERE t.nombreEs = :nombreEs")
    , @NamedQuery(name = "TipoProveedor.findByNombreEn", query = "SELECT t FROM TipoProveedor t WHERE t.nombreEn = :nombreEn")
    , @NamedQuery(name = "TipoProveedor.findByStatus", query = "SELECT t FROM TipoProveedor t WHERE t.status = :status")
    , @NamedQuery(name = "TipoProveedor.findByUsuarioCreo", query = "SELECT t FROM TipoProveedor t WHERE t.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "TipoProveedor.findByFechaCreo", query = "SELECT t FROM TipoProveedor t WHERE t.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "TipoProveedor.findByUsuarioModifico", query = "SELECT t FROM TipoProveedor t WHERE t.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "TipoProveedor.findByFechaModifico", query = "SELECT t FROM TipoProveedor t WHERE t.fechaModifico = :fechaModifico")
})
public class      TipoProveedor 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1618696301;
    
    /**
    * The 'id tipo proveedor' Maps to COLUMN 'id_tipo_proveedor'
    */
    
    @Id
    @Column(name = "ID_TIPO_PROVEEDOR" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTipoProveedor;
    
    /**
    * The 'nombre es' Maps to COLUMN 'nombre_es'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ES" , length=50, nullable=false)
    private String nombreEs;
    
    /**
    * The 'nombre en' Maps to COLUMN 'nombre_en'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_EN" , length=50, nullable=false)
    private String nombreEn;
    
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

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "tipoProveedorList")
    private List<ProveedorEstacion> proveedorEstacionList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TipoProveedor() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTipoProveedor() { return this.idTipoProveedor;}
    public void setIdTipoProveedor(Integer v) { this.idTipoProveedor = v; }
    
    public String getNombreEs() { return this.nombreEs;}
    public void setNombreEs(String v) { this.nombreEs = v; }
    
    public String getNombreEn() { return this.nombreEn;}
    public void setNombreEn(String v) { this.nombreEn = v; }
    
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
    public List<ProveedorEstacion> getProveedorEstacionList() { return this.proveedorEstacionList; }
    public void setProveedorEstacionList(List<ProveedorEstacion>  v) { this.proveedorEstacionList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTipoProveedor).hashCode();
		hash += String.valueOf(nombreEs).hashCode();
		hash += String.valueOf(nombreEn).hashCode();
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
        if (!(o instanceof TipoProveedor)) {
            return false;
        }		
		TipoProveedor other = (TipoProveedor ) o;
		if (!Objects.equals(this.idTipoProveedor, other.idTipoProveedor)) { return false; }		
		if (!Objects.equals(this.nombreEs, other.nombreEs)) { return false; }		
		if (!Objects.equals(this.nombreEn, other.nombreEn)) { return false; }		
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
		sb.append("TipoProveedor{");
		sb.append("idTipoProveedor" ).append("=").append(idTipoProveedor).append("|");
		sb.append("nombreEs" ).append("=").append(nombreEs).append("|");
		sb.append("nombreEn" ).append("=").append(nombreEn).append("|");
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
		sb.append(" ").append(idTipoProveedor).append(" ");

		return sb.toString().trim();
	}

}
