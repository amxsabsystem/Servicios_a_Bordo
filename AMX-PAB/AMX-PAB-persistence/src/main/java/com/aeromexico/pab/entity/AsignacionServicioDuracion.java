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
 * Class for mapping JPA Entity of Table Asignacion_Servicio_Duracion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Asignacion_Servicio_Duracion")
@NamedQueries({
    @NamedQuery(name = "AsignacionServicioDuracion.findAll", query = "SELECT a FROM AsignacionServicioDuracion a")
    , @NamedQuery(name = "AsignacionServicioDuracion.countAll", query = "SELECT COUNT(a) FROM AsignacionServicioDuracion a")
    , @NamedQuery(name = "AsignacionServicioDuracion.findByIdAsignacionServicioDuracion", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.idAsignacionServicioDuracion = :idAsignacionServicioDuracion")
    , @NamedQuery(name = "AsignacionServicioDuracion.findBycodigoServicio", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.codigoServicio = :codigoServicio")
    , @NamedQuery(name = "AsignacionServicioDuracion.findByduracionVuelos", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.duracionVuelos = :duracionVuelos")
    , @NamedQuery(name = "AsignacionServicioDuracion.findByasignacionServicio", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.asignacionServicio = :asignacionServicio")
    , @NamedQuery(name = "AsignacionServicioDuracion.findByhorarioRegion", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.horarioRegion = :horarioRegion")
    , @NamedQuery(name = "AsignacionServicioDuracion.findByOrden", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.orden = :orden")
    , @NamedQuery(name = "AsignacionServicioDuracion.findByNoSecuencia", query = "SELECT a FROM AsignacionServicioDuracion a WHERE a.noSecuencia = :noSecuencia")
})
public class      AsignacionServicioDuracion 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -811560457;
    
    /**
    * The 'id Asignacion Servicio Duracion' Maps to COLUMN 'id_Asignacion_Servicio_Duracion'
    */
    
    @Id
    @Column(name = "ID_ASIGNACION_SERVICIO_DURACION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAsignacionServicioDuracion;
    
    /**
    * The 'id codigo servicio' Maps to COLUMN 'id_codigo_servicio'
    */
    
    @JoinColumn(name = "ID_CODIGO_SERVICIO" , referencedColumnName = "ID_CODIGO_SERVICIO")
    @ManyToOne(optional = false )
    private CodigoServicio codigoServicio;
    
    /**
    * The 'id duracion vuelos' Maps to COLUMN 'id_duracion_vuelos'
    */
    
    @JoinColumn(name = "ID_DURACION_VUELOS" , referencedColumnName = "ID_DURACION_VUELOS")
    @ManyToOne(optional = false )
    private DuracionVuelos duracionVuelos;
    
    /**
    * The 'id asignacion servicio' Maps to COLUMN 'id_asignacion_servicio'
    */
    
    @JoinColumn(name = "ID_ASIGNACION_SERVICIO" , referencedColumnName = "ID_ASIGNACION_SERVICIO")
    @ManyToOne(optional = false )
    private AsignacionServicio asignacionServicio;
    
    /**
    * The 'id horario region' Maps to COLUMN 'id_horario_region'
    */
    
    @JoinColumn(name = "ID_HORARIO_REGION" , referencedColumnName = "ID_HORARIO_REGION")
    @ManyToOne(optional = false )
    private HorarioRegion horarioRegion;
    
    /**
    * The 'orden' Maps to COLUMN 'orden'
    */
    
    @Basic(optional = false)
    @Column(name = "ORDEN" , nullable=false)
    private Short orden;
    
    /**
    * The 'no secuencia' Maps to COLUMN 'no_secuencia'
    */
    
    @Basic(optional = true)
    @Column(name = "NO_SECUENCIA" , nullable=true)
    private Integer noSecuencia;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public AsignacionServicioDuracion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAsignacionServicioDuracion() { return this.idAsignacionServicioDuracion;}
    public void setIdAsignacionServicioDuracion(Integer v) { this.idAsignacionServicioDuracion = v; }
    
    public CodigoServicio getCodigoServicio(){ return this.codigoServicio ; }
    public void setCodigoServicio(CodigoServicio x){ this.codigoServicio = x; }
    
    public DuracionVuelos getDuracionVuelos(){ return this.duracionVuelos ; }
    public void setDuracionVuelos(DuracionVuelos x){ this.duracionVuelos = x; }
    
    public AsignacionServicio getAsignacionServicio(){ return this.asignacionServicio ; }
    public void setAsignacionServicio(AsignacionServicio x){ this.asignacionServicio = x; }
    
    public HorarioRegion getHorarioRegion(){ return this.horarioRegion ; }
    public void setHorarioRegion(HorarioRegion x){ this.horarioRegion = x; }
    
    public Short getOrden() { return this.orden;}
    public void setOrden(Short v) { this.orden = v; }
    
    public Integer getNoSecuencia() { return this.noSecuencia;}
    public void setNoSecuencia(Integer v) { this.noSecuencia = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idAsignacionServicioDuracion).hashCode();
		hash += String.valueOf(codigoServicio).hashCode();
		hash += String.valueOf(duracionVuelos).hashCode();
		hash += String.valueOf(asignacionServicio).hashCode();
		hash += String.valueOf(horarioRegion).hashCode();
		hash += String.valueOf(orden).hashCode();
		hash += String.valueOf(noSecuencia).hashCode();
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
        if (!(o instanceof AsignacionServicioDuracion)) {
            return false;
        }		
		AsignacionServicioDuracion other = (AsignacionServicioDuracion ) o;
		if (!Objects.equals(this.idAsignacionServicioDuracion, other.idAsignacionServicioDuracion)) { return false; }		
		if (!Objects.equals(this.codigoServicio, other.codigoServicio)) { return false; }		
		if (!Objects.equals(this.duracionVuelos, other.duracionVuelos)) { return false; }		
		if (!Objects.equals(this.asignacionServicio, other.asignacionServicio)) { return false; }		
		if (!Objects.equals(this.horarioRegion, other.horarioRegion)) { return false; }		
		if (!Objects.equals(this.orden, other.orden)) { return false; }		
		if (!Objects.equals(this.noSecuencia, other.noSecuencia)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("AsignacionServicioDuracion{");
		sb.append("idAsignacionServicioDuracion" ).append("=").append(idAsignacionServicioDuracion).append("|");
		sb.append("codigoServicio" ).append("=").append(codigoServicio).append("|");
		sb.append("duracionVuelos" ).append("=").append(duracionVuelos).append("|");
		sb.append("asignacionServicio" ).append("=").append(asignacionServicio).append("|");
		sb.append("horarioRegion" ).append("=").append(horarioRegion).append("|");
		sb.append("orden" ).append("=").append(orden).append("|");
		sb.append("noSecuencia" ).append("=").append(noSecuencia).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idAsignacionServicioDuracion).append(" ");

		return sb.toString().trim();
	}

}
