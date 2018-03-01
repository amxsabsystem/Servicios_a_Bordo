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
 * Class for mapping JPA Entity of Table matriz.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "matriz")
@NamedQueries({
    @NamedQuery(name = "Matriz.findAll", query = "SELECT m FROM Matriz m")
    , @NamedQuery(name = "Matriz.countAll", query = "SELECT COUNT(m) FROM Matriz m")
    , @NamedQuery(name = "Matriz.findByIdMatriz", query = "SELECT m FROM Matriz m WHERE m.idMatriz = :idMatriz")
    , @NamedQuery(name = "Matriz.findBykitMaster", query = "SELECT m FROM Matriz m WHERE m.kitMaster = :kitMaster")
    , @NamedQuery(name = "Matriz.findByproducto", query = "SELECT m FROM Matriz m WHERE m.producto = :producto")
    , @NamedQuery(name = "Matriz.findBytipoEquipoAvion", query = "SELECT m FROM Matriz m WHERE m.tipoEquipoAvion = :tipoEquipoAvion")
    , @NamedQuery(name = "Matriz.findByCantidadCy", query = "SELECT m FROM Matriz m WHERE m.cantidadCy = :cantidadCy")
    , @NamedQuery(name = "Matriz.findByCantidadCj", query = "SELECT m FROM Matriz m WHERE m.cantidadCj = :cantidadCj")
    , @NamedQuery(name = "Matriz.findByTotalCantidadCjCy", query = "SELECT m FROM Matriz m WHERE m.totalCantidadCjCy = :totalCantidadCjCy")
    , @NamedQuery(name = "Matriz.findByStatus", query = "SELECT m FROM Matriz m WHERE m.status = :status")
    , @NamedQuery(name = "Matriz.findByUsuarioCreo", query = "SELECT m FROM Matriz m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Matriz.findByFechaCreo", query = "SELECT m FROM Matriz m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Matriz.findByUsuarioModifico", query = "SELECT m FROM Matriz m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Matriz.findByFechaModifico", query = "SELECT m FROM Matriz m WHERE m.fechaModifico = :fechaModifico")
})
public class      Matriz 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1081239613;
    
    /**
    * The 'id matriz' Maps to COLUMN 'id_matriz'
    */
    
    @Id
    @Column(name = "ID_MATRIZ" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMatriz;
    
    /**
    * The 'cve kit master' Maps to COLUMN 'cve_kit_master'
    */
    
    @JoinColumn(name = "CVE_KIT_MASTER" , referencedColumnName = "CVE_KIT_MASTER")
    @ManyToOne(optional = false )
    private KitMaster kitMaster;
    
    /**
    * The 'id producto' Maps to COLUMN 'id_producto'
    */
    
    @JoinColumn(name = "ID_PRODUCTO" , referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false )
    private Producto producto;
    
    /**
    * The 'id tipo equipo avion' Maps to COLUMN 'id_tipo_equipo_avion'
    */
    
    @JoinColumn(name = "ID_TIPO_EQUIPO_AVION" , referencedColumnName = "ID_TIPO_EQUIPO_AVION")
    @ManyToOne(optional = false )
    private TipoEquipoAvion tipoEquipoAvion;
    
    /**
    * The 'cantidad CY' Maps to COLUMN 'cantidad_CY'
    */
    
    @Basic(optional = false)
    @Column(name = "CANTIDAD_CY" , nullable=false)
    private int cantidadCy;
    
    /**
    * The 'cantidad CJ' Maps to COLUMN 'cantidad_CJ'
    */
    
    @Basic(optional = false)
    @Column(name = "CANTIDAD_CJ" , nullable=false)
    private int cantidadCj;
    
    /**
    * The 'total cantidad CJ CY' Maps to COLUMN 'total_cantidad_CJ_CY'
    */
    
    @Basic(optional = false)
    @Column(name = "TOTAL_CANTIDAD_CJ_CY" , nullable=false)
    private int totalCantidadCjCy;
    
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
    * Map the relation to matriz_detalle table where has id_matriz object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matriz")
    private List<MatrizDetalle> matrizDetalleHasMatrizList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Matriz() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdMatriz() { return this.idMatriz;}
    public void setIdMatriz(Integer v) { this.idMatriz = v; }
    
    public KitMaster getKitMaster(){ return this.kitMaster ; }
    public void setKitMaster(KitMaster x){ this.kitMaster = x; }
    
    public Producto getProducto(){ return this.producto ; }
    public void setProducto(Producto x){ this.producto = x; }
    
    public TipoEquipoAvion getTipoEquipoAvion(){ return this.tipoEquipoAvion ; }
    public void setTipoEquipoAvion(TipoEquipoAvion x){ this.tipoEquipoAvion = x; }
    
    public int getCantidadCy() { return this.cantidadCy;}
    public void setCantidadCy(int v) { this.cantidadCy = v; }
    
    public int getCantidadCj() { return this.cantidadCj;}
    public void setCantidadCj(int v) { this.cantidadCj = v; }
    
    public int getTotalCantidadCjCy() { return this.totalCantidadCjCy;}
    public void setTotalCantidadCjCy(int v) { this.totalCantidadCjCy = v; }
    
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
    public List<MatrizDetalle> getMatrizDetalleHasMatrizList(){ return this.matrizDetalleHasMatrizList; }
    public void setMatrizDetalleHasMatrizList(List<MatrizDetalle> v){ this.matrizDetalleHasMatrizList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idMatriz).hashCode();
		hash += String.valueOf(kitMaster).hashCode();
		hash += String.valueOf(producto).hashCode();
		hash += String.valueOf(tipoEquipoAvion).hashCode();
		hash += String.valueOf(cantidadCy).hashCode();
		hash += String.valueOf(cantidadCj).hashCode();
		hash += String.valueOf(totalCantidadCjCy).hashCode();
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
        if (!(o instanceof Matriz)) {
            return false;
        }		
		Matriz other = (Matriz ) o;
		if (!Objects.equals(this.idMatriz, other.idMatriz)) { return false; }		
		if (!Objects.equals(this.kitMaster, other.kitMaster)) { return false; }		
		if (!Objects.equals(this.producto, other.producto)) { return false; }		
		if (!Objects.equals(this.tipoEquipoAvion, other.tipoEquipoAvion)) { return false; }		
		if (!Objects.equals(this.cantidadCy, other.cantidadCy)) { return false; }		
		if (!Objects.equals(this.cantidadCj, other.cantidadCj)) { return false; }		
		if (!Objects.equals(this.totalCantidadCjCy, other.totalCantidadCjCy)) { return false; }		
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
		sb.append("Matriz{");
		sb.append("idMatriz" ).append("=").append(idMatriz).append("|");
		sb.append("kitMaster" ).append("=").append(kitMaster).append("|");
		sb.append("producto" ).append("=").append(producto).append("|");
		sb.append("tipoEquipoAvion" ).append("=").append(tipoEquipoAvion).append("|");
		sb.append("cantidadCy" ).append("=").append(cantidadCy).append("|");
		sb.append("cantidadCj" ).append("=").append(cantidadCj).append("|");
		sb.append("totalCantidadCjCy" ).append("=").append(totalCantidadCjCy).append("|");
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
		sb.append(" ").append(idMatriz).append(" ");

		return sb.toString().trim();
	}

}
