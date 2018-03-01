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
 * Class for mapping JPA Entity of Table acomodo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "acomodo")
@NamedQueries({
    @NamedQuery(name = "Acomodo.findAll", query = "SELECT a FROM Acomodo a")
    , @NamedQuery(name = "Acomodo.countAll", query = "SELECT COUNT(a) FROM Acomodo a")
    , @NamedQuery(name = "Acomodo.findByIdAcomodo", query = "SELECT a FROM Acomodo a WHERE a.idAcomodo = :idAcomodo")
    , @NamedQuery(name = "Acomodo.findByacomodoDetalle", query = "SELECT a FROM Acomodo a WHERE a.acomodoDetalle = :acomodoDetalle")
    , @NamedQuery(name = "Acomodo.findByestacionOrigen", query = "SELECT a FROM Acomodo a WHERE a.estacionOrigen = :estacionOrigen")
    , @NamedQuery(name = "Acomodo.findByestacionDestino", query = "SELECT a FROM Acomodo a WHERE a.estacionDestino = :estacionDestino")
    , @NamedQuery(name = "Acomodo.findBymodeloAvion", query = "SELECT a FROM Acomodo a WHERE a.modeloAvion = :modeloAvion")
    , @NamedQuery(name = "Acomodo.findByidIdioma", query = "SELECT a FROM Acomodo a WHERE a.idIdioma = :idIdioma")
    , @NamedQuery(name = "Acomodo.findByStatus", query = "SELECT a FROM Acomodo a WHERE a.status = :status")
    , @NamedQuery(name = "Acomodo.findByUsuarioCreo", query = "SELECT a FROM Acomodo a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Acomodo.findByFechaCreo", query = "SELECT a FROM Acomodo a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Acomodo.findByUsuarioModifico", query = "SELECT a FROM Acomodo a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Acomodo.findByFechaModifico", query = "SELECT a FROM Acomodo a WHERE a.fechaModifico = :fechaModifico")
})
public class      Acomodo 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1166302278;
    
    /**
    * The 'id acomodo' Maps to COLUMN 'id_acomodo'
    */
    
    @Id
    @Column(name = "ID_ACOMODO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAcomodo;
    
    /**
    * The 'id acomodo detalle' Maps to COLUMN 'id_acomodo_detalle'
    */
    
    @JoinColumn(name = "ID_ACOMODO_DETALLE" , referencedColumnName = "ID_ACOMODO_DETALLE")
    @ManyToOne(optional = false )
    private AcomodoDetalle acomodoDetalle;
    
    /**
    * The 'id estacion origen' Maps to COLUMN 'id_estacion_origen'
    */
    
    @JoinColumn(name = "ID_ESTACION_ORIGEN" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacionOrigen;
    
    /**
    * The 'id estacion destino' Maps to COLUMN 'id_estacion_destino'
    */
    
    @JoinColumn(name = "ID_ESTACION_DESTINO" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacionDestino;
    
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "ACOMODO_PRODUCTO",
               joinColumns        = {@JoinColumn(name = "ID_ACOMODO", referencedColumnName ="ID_ACOMODO")},
               inverseJoinColumns = {@JoinColumn(name = "ID_PRODUCTO", referencedColumnName ="ID_PRODUCTO")}
               )
    private List<Producto> productoList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Acomodo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAcomodo() { return this.idAcomodo;}
    public void setIdAcomodo(Integer v) { this.idAcomodo = v; }
    
    public AcomodoDetalle getAcomodoDetalle(){ return this.acomodoDetalle ; }
    public void setAcomodoDetalle(AcomodoDetalle x){ this.acomodoDetalle = x; }
    
    public Estacion getEstacionOrigen(){ return this.estacionOrigen ; }
    public void setEstacionOrigen(Estacion x){ this.estacionOrigen = x; }
    
    public Estacion getEstacionDestino(){ return this.estacionDestino ; }
    public void setEstacionDestino(Estacion x){ this.estacionDestino = x; }
    
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
	// M2M <*>
    public List<Producto> getProductoList() { return this.productoList; }
    public void setProductoList(List<Producto>  v) { this.productoList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idAcomodo).hashCode();
		hash += String.valueOf(acomodoDetalle).hashCode();
		hash += String.valueOf(estacionOrigen).hashCode();
		hash += String.valueOf(estacionDestino).hashCode();
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
        if (!(o instanceof Acomodo)) {
            return false;
        }		
		Acomodo other = (Acomodo ) o;
		if (!Objects.equals(this.idAcomodo, other.idAcomodo)) { return false; }		
		if (!Objects.equals(this.acomodoDetalle, other.acomodoDetalle)) { return false; }		
		if (!Objects.equals(this.estacionOrigen, other.estacionOrigen)) { return false; }		
		if (!Objects.equals(this.estacionDestino, other.estacionDestino)) { return false; }		
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
		sb.append("Acomodo{");
		sb.append("idAcomodo" ).append("=").append(idAcomodo).append("|");
		sb.append("acomodoDetalle" ).append("=").append(acomodoDetalle).append("|");
		sb.append("estacionOrigen" ).append("=").append(estacionOrigen).append("|");
		sb.append("estacionDestino" ).append("=").append(estacionDestino).append("|");
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
		sb.append(" ").append(idAcomodo).append(" ");

		return sb.toString().trim();
	}

}
