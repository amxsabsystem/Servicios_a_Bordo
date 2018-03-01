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
 * Class for mapping JPA Entity of Table Kit_TSU.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Kit_TSU")
@NamedQueries({
    @NamedQuery(name = "KitTsu.findAll", query = "SELECT k FROM KitTsu k")
    , @NamedQuery(name = "KitTsu.countAll", query = "SELECT COUNT(k) FROM KitTsu k")
    , @NamedQuery(name = "KitTsu.findByIdKitTsu", query = "SELECT k FROM KitTsu k WHERE k.idKitTsu = :idKitTsu")
    , @NamedQuery(name = "KitTsu.findBykitMaster", query = "SELECT k FROM KitTsu k WHERE k.kitMaster = :kitMaster")
    , @NamedQuery(name = "KitTsu.findBytsu", query = "SELECT k FROM KitTsu k WHERE k.tsu = :tsu")
    , @NamedQuery(name = "KitTsu.findByCantidad", query = "SELECT k FROM KitTsu k WHERE k.cantidad = :cantidad")
    , @NamedQuery(name = "KitTsu.findByStatus", query = "SELECT k FROM KitTsu k WHERE k.status = :status")
    , @NamedQuery(name = "KitTsu.findByUsuarioCreo", query = "SELECT k FROM KitTsu k WHERE k.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "KitTsu.findByFechaCreo", query = "SELECT k FROM KitTsu k WHERE k.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "KitTsu.findByUsuarioModifico", query = "SELECT k FROM KitTsu k WHERE k.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "KitTsu.findByFechaModifico", query = "SELECT k FROM KitTsu k WHERE k.fechaModifico = :fechaModifico")
})
public class      KitTsu 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 959402157;
    
    /**
    * The 'id kit tsu' Maps to COLUMN 'id_kit_tsu'
    */
    
    @Id
    @Column(name = "ID_KIT_TSU" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idKitTsu;
    
    /**
    * The 'cve kit master' Maps to COLUMN 'cve_kit_master'
    */
    
    @JoinColumn(name = "CVE_KIT_MASTER" , referencedColumnName = "CVE_KIT_MASTER")
    @ManyToOne(optional = true )
    private KitMaster kitMaster;
    
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
    public KitTsu() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdKitTsu() { return this.idKitTsu;}
    public void setIdKitTsu(Integer v) { this.idKitTsu = v; }
    
    public KitMaster getKitMaster(){ return this.kitMaster ; }
    public void setKitMaster(KitMaster x){ this.kitMaster = x; }
    
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
		hash += String.valueOf(idKitTsu).hashCode();
		hash += String.valueOf(kitMaster).hashCode();
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
        if (!(o instanceof KitTsu)) {
            return false;
        }		
		KitTsu other = (KitTsu ) o;
		if (!Objects.equals(this.idKitTsu, other.idKitTsu)) { return false; }		
		if (!Objects.equals(this.kitMaster, other.kitMaster)) { return false; }		
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
		sb.append("KitTsu{");
		sb.append("idKitTsu" ).append("=").append(idKitTsu).append("|");
		sb.append("kitMaster" ).append("=").append(kitMaster).append("|");
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
		sb.append(" ").append(idKitTsu).append(" ");

		return sb.toString().trim();
	}

}
