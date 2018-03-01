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
 * Class for mapping JPA Entity of Table producto.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.countAll", query = "SELECT COUNT(p) FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByStatus", query = "SELECT p FROM Producto p WHERE p.status = :status")
    , @NamedQuery(name = "Producto.findByUsuarioCreo", query = "SELECT p FROM Producto p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Producto.findByFechaCreo", query = "SELECT p FROM Producto p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Producto.findByUsuarioModifico", query = "SELECT p FROM Producto p WHERE p.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Producto.findByFechaModifico", query = "SELECT p FROM Producto p WHERE p.fechaModifico = :fechaModifico")
})
public class      Producto 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1003761312;
    
    /**
    * The 'id producto' Maps to COLUMN 'id_producto'
    */
    
    @Id
    @Column(name = "ID_PRODUCTO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idProducto;
    
    /**
    * The 'nombre' Maps to COLUMN 'nombre'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE" , length=128, nullable=false)
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
    * Map the relation to matriz table where has id_producto object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Matriz> matrizHasProductoList;
    

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "productoList")
    private List<Acomodo> acomodoList;
    
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "productoList")
    private List<Grafico> graficoList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Producto() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdProducto() { return this.idProducto;}
    public void setIdProducto(Integer v) { this.idProducto = v; }
    
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
    public List<Matriz> getMatrizHasProductoList(){ return this.matrizHasProductoList; }
    public void setMatrizHasProductoList(List<Matriz> v){ this.matrizHasProductoList = v; }
    
	// M2M <*>
    public List<Acomodo> getAcomodoList() { return this.acomodoList; }
    public void setAcomodoList(List<Acomodo>  v) { this.acomodoList = v; }
    
    public List<Grafico> getGraficoList() { return this.graficoList; }
    public void setGraficoList(List<Grafico>  v) { this.graficoList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idProducto).hashCode();
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
        if (!(o instanceof Producto)) {
            return false;
        }		
		Producto other = (Producto ) o;
		if (!Objects.equals(this.idProducto, other.idProducto)) { return false; }		
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
		sb.append("Producto{");
		sb.append("idProducto" ).append("=").append(idProducto).append("|");
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
		sb.append(" ").append(nombre).append(" ");

		return sb.toString().trim();
	}

}
