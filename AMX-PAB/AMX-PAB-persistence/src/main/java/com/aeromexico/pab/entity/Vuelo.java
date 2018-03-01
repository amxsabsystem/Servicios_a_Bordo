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
 * Class for mapping JPA Entity of Table vuelo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "vuelo")
@NamedQueries({
    @NamedQuery(name = "Vuelo.findAll", query = "SELECT v FROM Vuelo v")
    , @NamedQuery(name = "Vuelo.countAll", query = "SELECT COUNT(v) FROM Vuelo v")
    , @NamedQuery(name = "Vuelo.findByIdVuelo", query = "SELECT v FROM Vuelo v WHERE v.idVuelo = :idVuelo")
    , @NamedQuery(name = "Vuelo.findByNumeroVuelo", query = "SELECT v FROM Vuelo v WHERE v.numeroVuelo = :numeroVuelo")
    , @NamedQuery(name = "Vuelo.findByestacionOrigen", query = "SELECT v FROM Vuelo v WHERE v.estacionOrigen = :estacionOrigen")
    , @NamedQuery(name = "Vuelo.findByestacionDestino", query = "SELECT v FROM Vuelo v WHERE v.estacionDestino = :estacionDestino")
    , @NamedQuery(name = "Vuelo.findByidTipoCabina", query = "SELECT v FROM Vuelo v WHERE v.idTipoCabina = :idTipoCabina")
    , @NamedQuery(name = "Vuelo.findBycompania", query = "SELECT v FROM Vuelo v WHERE v.compania = :compania")
    , @NamedQuery(name = "Vuelo.findByStatus", query = "SELECT v FROM Vuelo v WHERE v.status = :status")
    , @NamedQuery(name = "Vuelo.findByUsuarioCreo", query = "SELECT v FROM Vuelo v WHERE v.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Vuelo.findByFechaCreo", query = "SELECT v FROM Vuelo v WHERE v.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Vuelo.findByUsuarioModifico", query = "SELECT v FROM Vuelo v WHERE v.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Vuelo.findByFechaModifico", query = "SELECT v FROM Vuelo v WHERE v.fechaModifico = :fechaModifico")
})
public class      Vuelo 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 112561545;
    
    /**
    * The 'id vuelo' Maps to COLUMN 'id_vuelo'
    */
    
    @Id
    @Column(name = "ID_VUELO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idVuelo;
    
    /**
    * The 'numero vuelo' Maps to COLUMN 'numero_vuelo'
    */
    
    @Basic(optional = false)
    @Column(name = "NUMERO_VUELO" , nullable=false)
    private int numeroVuelo;
    
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
    * The 'id tipo cabina' Maps to COLUMN 'id_tipo_cabina'
    */
    
    @JoinColumn(name = "ID_TIPO_CABINA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipoCabina;
    
    /**
    * The 'id compania' Maps to COLUMN 'id_compania'
    */
    
    @JoinColumn(name = "ID_COMPANIA" , referencedColumnName = "ID_COMPANIA")
    @ManyToOne(optional = false )
    private Compania compania;
    
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
    * Map the relation to reporte table where has id_vuelo object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vuelo")
    private List<Reporte> reporteHasVueloList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Vuelo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdVuelo() { return this.idVuelo;}
    public void setIdVuelo(Integer v) { this.idVuelo = v; }
    
    public int getNumeroVuelo() { return this.numeroVuelo;}
    public void setNumeroVuelo(int v) { this.numeroVuelo = v; }
    
    public Estacion getEstacionOrigen(){ return this.estacionOrigen ; }
    public void setEstacionOrigen(Estacion x){ this.estacionOrigen = x; }
    
    public Estacion getEstacionDestino(){ return this.estacionDestino ; }
    public void setEstacionDestino(Estacion x){ this.estacionDestino = x; }
    
    public Parametro getIdTipoCabina(){ return this.idTipoCabina ; }
    public void setIdTipoCabina(Parametro x){ this.idTipoCabina = x; }
    
    public Compania getCompania(){ return this.compania ; }
    public void setCompania(Compania x){ this.compania = x; }
    
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
    public List<Reporte> getReporteHasVueloList(){ return this.reporteHasVueloList; }
    public void setReporteHasVueloList(List<Reporte> v){ this.reporteHasVueloList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idVuelo).hashCode();
		hash += String.valueOf(numeroVuelo).hashCode();
		hash += String.valueOf(estacionOrigen).hashCode();
		hash += String.valueOf(estacionDestino).hashCode();
		hash += String.valueOf(idTipoCabina).hashCode();
		hash += String.valueOf(compania).hashCode();
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
        if (!(o instanceof Vuelo)) {
            return false;
        }		
		Vuelo other = (Vuelo ) o;
		if (!Objects.equals(this.idVuelo, other.idVuelo)) { return false; }		
		if (!Objects.equals(this.numeroVuelo, other.numeroVuelo)) { return false; }		
		if (!Objects.equals(this.estacionOrigen, other.estacionOrigen)) { return false; }		
		if (!Objects.equals(this.estacionDestino, other.estacionDestino)) { return false; }		
		if (!Objects.equals(this.idTipoCabina, other.idTipoCabina)) { return false; }		
		if (!Objects.equals(this.compania, other.compania)) { return false; }		
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
		sb.append("Vuelo{");
		sb.append("idVuelo" ).append("=").append(idVuelo).append("|");
		sb.append("numeroVuelo" ).append("=").append(numeroVuelo).append("|");
		sb.append("estacionOrigen" ).append("=").append(estacionOrigen).append("|");
		sb.append("estacionDestino" ).append("=").append(estacionDestino).append("|");
		sb.append("idTipoCabina" ).append("=").append(idTipoCabina).append("|");
		sb.append("compania" ).append("=").append(compania).append("|");
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
		sb.append(" ").append(idVuelo).append(" ");

		return sb.toString().trim();
	}

}
