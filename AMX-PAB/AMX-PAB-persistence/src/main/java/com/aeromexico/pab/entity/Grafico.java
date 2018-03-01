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
 * Class for mapping JPA Entity of Table grafico.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "grafico")
@NamedQueries({
    @NamedQuery(name = "Grafico.findAll", query = "SELECT g FROM Grafico g")
    , @NamedQuery(name = "Grafico.countAll", query = "SELECT COUNT(g) FROM Grafico g")
    , @NamedQuery(name = "Grafico.findByIdGrafico", query = "SELECT g FROM Grafico g WHERE g.idGrafico = :idGrafico")
    , @NamedQuery(name = "Grafico.findBymodeloAvion", query = "SELECT g FROM Grafico g WHERE g.modeloAvion = :modeloAvion")
    , @NamedQuery(name = "Grafico.findByidIdioma", query = "SELECT g FROM Grafico g WHERE g.idIdioma = :idIdioma")
    , @NamedQuery(name = "Grafico.findByStatus", query = "SELECT g FROM Grafico g WHERE g.status = :status")
    , @NamedQuery(name = "Grafico.findByUsuarioCreo", query = "SELECT g FROM Grafico g WHERE g.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Grafico.findByFechaCreo", query = "SELECT g FROM Grafico g WHERE g.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Grafico.findByUsuarioModifico", query = "SELECT g FROM Grafico g WHERE g.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Grafico.findByFechaModifico", query = "SELECT g FROM Grafico g WHERE g.fechaModifico = :fechaModifico")
})
public class      Grafico 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 280046149;
    
    /**
    * The 'id grafico' Maps to COLUMN 'id_grafico'
    */
    
    @Id
    @Column(name = "ID_GRAFICO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idGrafico;
    
    /**
    * The 'id modelo avion' Maps to COLUMN 'id_modelo_avion'
    */
    
    @JoinColumn(name = "ID_MODELO_AVION" , referencedColumnName = "ID_MODELO_AVION")
    @ManyToOne(optional = false )
    private ModeloAvion modeloAvion;
    
    /**
    * The 'id idioma' Maps to COLUMN 'id_idioma'
    */
    
    @JoinColumn(name = "ID_IDIOMA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idIdioma;
    
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
    * Map the relation to grafico_detalle table where has id_grafico object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grafico")
    private List<GraficoDetalle> graficoDetalleHasGraficoList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "GRAFICO_PRODUCTO",
               joinColumns        = {@JoinColumn(name = "ID_GRAFICO", referencedColumnName ="ID_GRAFICO")},
               inverseJoinColumns = {@JoinColumn(name = "ID_PRODUCTO", referencedColumnName ="ID_PRODUCTO")}
               )
    private List<Producto> productoList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Grafico() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdGrafico() { return this.idGrafico;}
    public void setIdGrafico(Integer v) { this.idGrafico = v; }
    
    public ModeloAvion getModeloAvion(){ return this.modeloAvion ; }
    public void setModeloAvion(ModeloAvion x){ this.modeloAvion = x; }
    
    public Parametro getIdIdioma(){ return this.idIdioma ; }
    public void setIdIdioma(Parametro x){ this.idIdioma = x; }
    
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
    public List<GraficoDetalle> getGraficoDetalleHasGraficoList(){ return this.graficoDetalleHasGraficoList; }
    public void setGraficoDetalleHasGraficoList(List<GraficoDetalle> v){ this.graficoDetalleHasGraficoList = v; }
    
	// M2M <*>
    public List<Producto> getProductoList() { return this.productoList; }
    public void setProductoList(List<Producto>  v) { this.productoList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idGrafico).hashCode();
		hash += String.valueOf(modeloAvion).hashCode();
		hash += String.valueOf(idIdioma).hashCode();
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
        if (!(o instanceof Grafico)) {
            return false;
        }		
		Grafico other = (Grafico ) o;
		if (!Objects.equals(this.idGrafico, other.idGrafico)) { return false; }		
		if (!Objects.equals(this.modeloAvion, other.modeloAvion)) { return false; }		
		if (!Objects.equals(this.idIdioma, other.idIdioma)) { return false; }		
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
		sb.append("Grafico{");
		sb.append("idGrafico" ).append("=").append(idGrafico).append("|");
		sb.append("modeloAvion" ).append("=").append(modeloAvion).append("|");
		sb.append("idIdioma" ).append("=").append(idIdioma).append("|");
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
		sb.append(" ").append(idGrafico).append(" ");

		return sb.toString().trim();
	}

}
