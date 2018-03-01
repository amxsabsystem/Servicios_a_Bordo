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
 * Class for mapping JPA Entity of Table ciclo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "ciclo")
@NamedQueries({
    @NamedQuery(name = "Ciclo.findAll", query = "SELECT c FROM Ciclo c")
    , @NamedQuery(name = "Ciclo.countAll", query = "SELECT COUNT(c) FROM Ciclo c")
    , @NamedQuery(name = "Ciclo.findByIdCiclo", query = "SELECT c FROM Ciclo c WHERE c.idCiclo = :idCiclo")
    , @NamedQuery(name = "Ciclo.findByidOrigenvuelo", query = "SELECT c FROM Ciclo c WHERE c.idOrigenvuelo = :idOrigenvuelo")
    , @NamedQuery(name = "Ciclo.findByidTipociclo", query = "SELECT c FROM Ciclo c WHERE c.idTipociclo = :idTipociclo")
    , @NamedQuery(name = "Ciclo.findByregion", query = "SELECT c FROM Ciclo c WHERE c.region = :region")
    , @NamedQuery(name = "Ciclo.findByestacion", query = "SELECT c FROM Ciclo c WHERE c.estacion = :estacion")
    , @NamedQuery(name = "Ciclo.findByproveedor", query = "SELECT c FROM Ciclo c WHERE c.proveedor = :proveedor")
    , @NamedQuery(name = "Ciclo.findByDobleAbastecido", query = "SELECT c FROM Ciclo c WHERE c.dobleAbastecido = :dobleAbastecido")
    , @NamedQuery(name = "Ciclo.findByStatus", query = "SELECT c FROM Ciclo c WHERE c.status = :status")
    , @NamedQuery(name = "Ciclo.findByUsuarioCreo", query = "SELECT c FROM Ciclo c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Ciclo.findByFechaCreo", query = "SELECT c FROM Ciclo c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Ciclo.findByUsuarioModifico", query = "SELECT c FROM Ciclo c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Ciclo.findByFechaModifico", query = "SELECT c FROM Ciclo c WHERE c.fechaModifico = :fechaModifico")
})
public class      Ciclo 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 94655232;
    
    /**
    * The 'id ciclo' Maps to COLUMN 'id_ciclo'
    */
    
    @Id
    @Column(name = "ID_CICLO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCiclo;
    
    /**
    * The 'id origenVuelo' Maps to COLUMN 'id_origenVuelo'
    */
    
    @JoinColumn(name = "ID_ORIGENVUELO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idOrigenvuelo;
    
    /**
    * The 'id TipoCiclo' Maps to COLUMN 'id_TipoCiclo'
    */
    
    @JoinColumn(name = "ID_TIPOCICLO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipociclo;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @JoinColumn(name = "ID_REGION" , referencedColumnName = "ID_REGION")
    @ManyToOne(optional = false )
    private Region region;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @JoinColumn(name = "ID_ESTACION" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacion;
    
    /**
    * The 'clave proveedor' Maps to COLUMN 'clave_proveedor'
    */
    
    @JoinColumn(name = "CLAVE_PROVEEDOR" , referencedColumnName = "CLAVE_PROVEEDOR")
    @ManyToOne(optional = false )
    private Proveedor proveedor;
    
    /**
    * The 'doble abastecido' Maps to COLUMN 'doble_abastecido'
    */
    
    @Basic(optional = false)
    @Column(name = "DOBLE_ABASTECIDO" , nullable=false)
    private Short dobleAbastecido;
    
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
    * Map the relation to Configuracion_Ciclo table where has id_ciclo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciclo")
    private List<ConfiguracionCiclo> configuracionCicloHasCicloList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Ciclo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdCiclo() { return this.idCiclo;}
    public void setIdCiclo(Integer v) { this.idCiclo = v; }
    
    public Parametro getIdOrigenvuelo(){ return this.idOrigenvuelo ; }
    public void setIdOrigenvuelo(Parametro x){ this.idOrigenvuelo = x; }
    
    public Parametro getIdTipociclo(){ return this.idTipociclo ; }
    public void setIdTipociclo(Parametro x){ this.idTipociclo = x; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
    public Estacion getEstacion(){ return this.estacion ; }
    public void setEstacion(Estacion x){ this.estacion = x; }
    
    public Proveedor getProveedor(){ return this.proveedor ; }
    public void setProveedor(Proveedor x){ this.proveedor = x; }
    
    public Short getDobleAbastecido() { return this.dobleAbastecido;}
    public void setDobleAbastecido(Short v) { this.dobleAbastecido = v; }
    
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
    public List<ConfiguracionCiclo> getConfiguracionCicloHasCicloList(){ return this.configuracionCicloHasCicloList; }
    public void setConfiguracionCicloHasCicloList(List<ConfiguracionCiclo> v){ this.configuracionCicloHasCicloList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idCiclo).hashCode();
		hash += String.valueOf(idOrigenvuelo).hashCode();
		hash += String.valueOf(idTipociclo).hashCode();
		hash += String.valueOf(region).hashCode();
		hash += String.valueOf(estacion).hashCode();
		hash += String.valueOf(proveedor).hashCode();
		hash += String.valueOf(dobleAbastecido).hashCode();
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
        if (!(o instanceof Ciclo)) {
            return false;
        }		
		Ciclo other = (Ciclo ) o;
		if (!Objects.equals(this.idCiclo, other.idCiclo)) { return false; }		
		if (!Objects.equals(this.idOrigenvuelo, other.idOrigenvuelo)) { return false; }		
		if (!Objects.equals(this.idTipociclo, other.idTipociclo)) { return false; }		
		if (!Objects.equals(this.region, other.region)) { return false; }		
		if (!Objects.equals(this.estacion, other.estacion)) { return false; }		
		if (!Objects.equals(this.proveedor, other.proveedor)) { return false; }		
		if (!Objects.equals(this.dobleAbastecido, other.dobleAbastecido)) { return false; }		
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
		sb.append("Ciclo{");
		sb.append("idCiclo" ).append("=").append(idCiclo).append("|");
		sb.append("idOrigenvuelo" ).append("=").append(idOrigenvuelo).append("|");
		sb.append("idTipociclo" ).append("=").append(idTipociclo).append("|");
		sb.append("region" ).append("=").append(region).append("|");
		sb.append("estacion" ).append("=").append(estacion).append("|");
		sb.append("proveedor" ).append("=").append(proveedor).append("|");
		sb.append("dobleAbastecido" ).append("=").append(dobleAbastecido).append("|");
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
		sb.append(" ").append(idCiclo).append(" ");

		return sb.toString().trim();
	}

}
