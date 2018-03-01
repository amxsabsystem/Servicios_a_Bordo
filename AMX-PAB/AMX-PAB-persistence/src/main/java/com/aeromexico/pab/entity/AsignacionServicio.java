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
 * Class for mapping JPA Entity of Table Asignacion_Servicio.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Asignacion_Servicio")
@NamedQueries({
    @NamedQuery(name = "AsignacionServicio.findAll", query = "SELECT a FROM AsignacionServicio a")
    , @NamedQuery(name = "AsignacionServicio.countAll", query = "SELECT COUNT(a) FROM AsignacionServicio a")
    , @NamedQuery(name = "AsignacionServicio.findByIdAsignacionServicio", query = "SELECT a FROM AsignacionServicio a WHERE a.idAsignacionServicio = :idAsignacionServicio")
    , @NamedQuery(name = "AsignacionServicio.findBymodeloAvion", query = "SELECT a FROM AsignacionServicio a WHERE a.modeloAvion = :modeloAvion")
    , @NamedQuery(name = "AsignacionServicio.findByidOrigen", query = "SELECT a FROM AsignacionServicio a WHERE a.idOrigen = :idOrigen")
    , @NamedQuery(name = "AsignacionServicio.findByclase", query = "SELECT a FROM AsignacionServicio a WHERE a.clase = :clase")
    , @NamedQuery(name = "AsignacionServicio.findByregion", query = "SELECT a FROM AsignacionServicio a WHERE a.region = :region")
    , @NamedQuery(name = "AsignacionServicio.findByStatus", query = "SELECT a FROM AsignacionServicio a WHERE a.status = :status")
    , @NamedQuery(name = "AsignacionServicio.findByUsuarioCreo", query = "SELECT a FROM AsignacionServicio a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "AsignacionServicio.findByFechaCreo", query = "SELECT a FROM AsignacionServicio a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "AsignacionServicio.findByUsuarioModifico", query = "SELECT a FROM AsignacionServicio a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "AsignacionServicio.findByFechaModifico", query = "SELECT a FROM AsignacionServicio a WHERE a.fechaModifico = :fechaModifico")
})
public class      AsignacionServicio 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -718413459;
    
    /**
    * The 'id asignacion servicio' Maps to COLUMN 'id_asignacion_servicio'
    */
    
    @Id
    @Column(name = "ID_ASIGNACION_SERVICIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAsignacionServicio;
    
    /**
    * The 'id modelo avion' Maps to COLUMN 'id_modelo_avion'
    */
    
    @JoinColumn(name = "ID_MODELO_AVION" , referencedColumnName = "ID_MODELO_AVION")
    @ManyToOne(optional = false )
    private ModeloAvion modeloAvion;
    
    /**
    * The 'id origen' Maps to COLUMN 'id_origen'
    */
    
    @JoinColumn(name = "ID_ORIGEN" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idOrigen;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @JoinColumn(name = "ID_REGION" , referencedColumnName = "ID_REGION")
    @ManyToOne(optional = false )
    private Region region;
    
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
    * Map the relation to Asignacion_Servicio_Duracion table where has id_asignacion_servicio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionServicio")
    private List<AsignacionServicioDuracion> asignacionServicioDuracionHasAsignacionServicioList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public AsignacionServicio() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAsignacionServicio() { return this.idAsignacionServicio;}
    public void setIdAsignacionServicio(Integer v) { this.idAsignacionServicio = v; }
    
    public ModeloAvion getModeloAvion(){ return this.modeloAvion ; }
    public void setModeloAvion(ModeloAvion x){ this.modeloAvion = x; }
    
    public Parametro getIdOrigen(){ return this.idOrigen ; }
    public void setIdOrigen(Parametro x){ this.idOrigen = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
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
    public List<AsignacionServicioDuracion> getAsignacionServicioDuracionHasAsignacionServicioList(){ return this.asignacionServicioDuracionHasAsignacionServicioList; }
    public void setAsignacionServicioDuracionHasAsignacionServicioList(List<AsignacionServicioDuracion> v){ this.asignacionServicioDuracionHasAsignacionServicioList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idAsignacionServicio).hashCode();
		hash += String.valueOf(modeloAvion).hashCode();
		hash += String.valueOf(idOrigen).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(region).hashCode();
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
        if (!(o instanceof AsignacionServicio)) {
            return false;
        }		
		AsignacionServicio other = (AsignacionServicio ) o;
		if (!Objects.equals(this.idAsignacionServicio, other.idAsignacionServicio)) { return false; }		
		if (!Objects.equals(this.modeloAvion, other.modeloAvion)) { return false; }		
		if (!Objects.equals(this.idOrigen, other.idOrigen)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.region, other.region)) { return false; }		
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
		sb.append("AsignacionServicio{");
		sb.append("idAsignacionServicio" ).append("=").append(idAsignacionServicio).append("|");
		sb.append("modeloAvion" ).append("=").append(modeloAvion).append("|");
		sb.append("idOrigen" ).append("=").append(idOrigen).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("region" ).append("=").append(region).append("|");
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
		sb.append(" ").append(idAsignacionServicio).append(" ");

		return sb.toString().trim();
	}

}
