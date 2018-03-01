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
 * Class for mapping JPA Entity of Table matriz_detalle.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "matriz_detalle")
@NamedQueries({
    @NamedQuery(name = "MatrizDetalle.findAll", query = "SELECT m FROM MatrizDetalle m")
    , @NamedQuery(name = "MatrizDetalle.countAll", query = "SELECT COUNT(m) FROM MatrizDetalle m")
    , @NamedQuery(name = "MatrizDetalle.findByIdMatrizDetalle", query = "SELECT m FROM MatrizDetalle m WHERE m.idMatrizDetalle = :idMatrizDetalle")
    , @NamedQuery(name = "MatrizDetalle.findBymatriz", query = "SELECT m FROM MatrizDetalle m WHERE m.matriz = :matriz")
    , @NamedQuery(name = "MatrizDetalle.findBymaterial", query = "SELECT m FROM MatrizDetalle m WHERE m.material = :material")
    , @NamedQuery(name = "MatrizDetalle.findByTotalCantidadCj", query = "SELECT m FROM MatrizDetalle m WHERE m.totalCantidadCj = :totalCantidadCj")
    , @NamedQuery(name = "MatrizDetalle.findByTotalCantidadCy", query = "SELECT m FROM MatrizDetalle m WHERE m.totalCantidadCy = :totalCantidadCy")
    , @NamedQuery(name = "MatrizDetalle.findByStatus", query = "SELECT m FROM MatrizDetalle m WHERE m.status = :status")
    , @NamedQuery(name = "MatrizDetalle.findByUsuarioCreo", query = "SELECT m FROM MatrizDetalle m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "MatrizDetalle.findByFechaCreo", query = "SELECT m FROM MatrizDetalle m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "MatrizDetalle.findByUsuarioModifico", query = "SELECT m FROM MatrizDetalle m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "MatrizDetalle.findByFechaModifico", query = "SELECT m FROM MatrizDetalle m WHERE m.fechaModifico = :fechaModifico")
})
public class      MatrizDetalle 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 428853499;
    
    /**
    * The 'id matriz detalle' Maps to COLUMN 'id_matriz_detalle'
    */
    
    @Id
    @Column(name = "ID_MATRIZ_DETALLE" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMatrizDetalle;
    
    /**
    * The 'id matriz' Maps to COLUMN 'id_matriz'
    */
    
    @JoinColumn(name = "ID_MATRIZ" , referencedColumnName = "ID_MATRIZ")
    @ManyToOne(optional = false )
    private Matriz matriz;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material material;
    
    /**
    * The 'total cantidad CJ' Maps to COLUMN 'total_cantidad_CJ'
    */
    
    @Basic(optional = false)
    @Column(name = "TOTAL_CANTIDAD_CJ" , nullable=false)
    private int totalCantidadCj;
    
    /**
    * The 'total cantidad CY' Maps to COLUMN 'total_cantidad_CY'
    */
    
    @Basic(optional = false)
    @Column(name = "TOTAL_CANTIDAD_CY" , nullable=false)
    private int totalCantidadCy;
    
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
    public MatrizDetalle() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdMatrizDetalle() { return this.idMatrizDetalle;}
    public void setIdMatrizDetalle(Integer v) { this.idMatrizDetalle = v; }
    
    public Matriz getMatriz(){ return this.matriz ; }
    public void setMatriz(Matriz x){ this.matriz = x; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public int getTotalCantidadCj() { return this.totalCantidadCj;}
    public void setTotalCantidadCj(int v) { this.totalCantidadCj = v; }
    
    public int getTotalCantidadCy() { return this.totalCantidadCy;}
    public void setTotalCantidadCy(int v) { this.totalCantidadCy = v; }
    
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
		hash += String.valueOf(idMatrizDetalle).hashCode();
		hash += String.valueOf(matriz).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(totalCantidadCj).hashCode();
		hash += String.valueOf(totalCantidadCy).hashCode();
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
        if (!(o instanceof MatrizDetalle)) {
            return false;
        }		
		MatrizDetalle other = (MatrizDetalle ) o;
		if (!Objects.equals(this.idMatrizDetalle, other.idMatrizDetalle)) { return false; }		
		if (!Objects.equals(this.matriz, other.matriz)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.totalCantidadCj, other.totalCantidadCj)) { return false; }		
		if (!Objects.equals(this.totalCantidadCy, other.totalCantidadCy)) { return false; }		
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
		sb.append("MatrizDetalle{");
		sb.append("idMatrizDetalle" ).append("=").append(idMatrizDetalle).append("|");
		sb.append("matriz" ).append("=").append(matriz).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("totalCantidadCj" ).append("=").append(totalCantidadCj).append("|");
		sb.append("totalCantidadCy" ).append("=").append(totalCantidadCy).append("|");
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
		sb.append(" ").append(idMatrizDetalle).append(" ");

		return sb.toString().trim();
	}

}
