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
 * Class for mapping JPA Entity of Table Horario_Region.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Horario_Region")
@NamedQueries({
    @NamedQuery(name = "HorarioRegion.findAll", query = "SELECT h FROM HorarioRegion h")
    , @NamedQuery(name = "HorarioRegion.countAll", query = "SELECT COUNT(h) FROM HorarioRegion h")
    , @NamedQuery(name = "HorarioRegion.findByIdHorarioRegion", query = "SELECT h FROM HorarioRegion h WHERE h.idHorarioRegion = :idHorarioRegion")
    , @NamedQuery(name = "HorarioRegion.findByregion", query = "SELECT h FROM HorarioRegion h WHERE h.region = :region")
    , @NamedQuery(name = "HorarioRegion.findByHoraInicio", query = "SELECT h FROM HorarioRegion h WHERE h.horaInicio = :horaInicio")
    , @NamedQuery(name = "HorarioRegion.findByHoraFin", query = "SELECT h FROM HorarioRegion h WHERE h.horaFin = :horaFin")
    , @NamedQuery(name = "HorarioRegion.findByStatus", query = "SELECT h FROM HorarioRegion h WHERE h.status = :status")
    , @NamedQuery(name = "HorarioRegion.findByUsuarioCreo", query = "SELECT h FROM HorarioRegion h WHERE h.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "HorarioRegion.findByFechaCreo", query = "SELECT h FROM HorarioRegion h WHERE h.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "HorarioRegion.findByUsuarioModifico", query = "SELECT h FROM HorarioRegion h WHERE h.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "HorarioRegion.findByFechaModifico", query = "SELECT h FROM HorarioRegion h WHERE h.fechaModifico = :fechaModifico")
})
public class      HorarioRegion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -1619439055;
    
    /**
    * The 'id horario region' Maps to COLUMN 'id_horario_region'
    */
    
    @Id
    @Column(name = "ID_HORARIO_REGION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idHorarioRegion;
    
    /**
    * The 'id region' Maps to COLUMN 'id_region'
    */
    
    @JoinColumn(name = "ID_REGION" , referencedColumnName = "ID_REGION")
    @ManyToOne(optional = false )
    private Region region;
    
    /**
    * The 'hora inicio' Maps to COLUMN 'hora_inicio'
    */
    
    @Basic(optional = false)
    @Column(name = "HORA_INICIO" , length=5, nullable=false)
    private String horaInicio;
    
    /**
    * The 'hora fin' Maps to COLUMN 'hora_fin'
    */
    
    @Basic(optional = false)
    @Column(name = "HORA_FIN" , length=5, nullable=false)
    private String horaFin;
    
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
    * Map the relation to Asignacion_Servicio_Duracion table where has id_horario_region object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horarioRegion")
    private List<AsignacionServicioDuracion> asignacionServicioDuracionHasHorarioRegionList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public HorarioRegion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdHorarioRegion() { return this.idHorarioRegion;}
    public void setIdHorarioRegion(Integer v) { this.idHorarioRegion = v; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
    public String getHoraInicio() { return this.horaInicio;}
    public void setHoraInicio(String v) { this.horaInicio = v; }
    
    public String getHoraFin() { return this.horaFin;}
    public void setHoraFin(String v) { this.horaFin = v; }
    
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
    public List<AsignacionServicioDuracion> getAsignacionServicioDuracionHasHorarioRegionList(){ return this.asignacionServicioDuracionHasHorarioRegionList; }
    public void setAsignacionServicioDuracionHasHorarioRegionList(List<AsignacionServicioDuracion> v){ this.asignacionServicioDuracionHasHorarioRegionList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idHorarioRegion).hashCode();
		hash += String.valueOf(region).hashCode();
		hash += String.valueOf(horaInicio).hashCode();
		hash += String.valueOf(horaFin).hashCode();
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
        if (!(o instanceof HorarioRegion)) {
            return false;
        }		
		HorarioRegion other = (HorarioRegion ) o;
		if (!Objects.equals(this.idHorarioRegion, other.idHorarioRegion)) { return false; }		
		if (!Objects.equals(this.region, other.region)) { return false; }		
		if (!Objects.equals(this.horaInicio, other.horaInicio)) { return false; }		
		if (!Objects.equals(this.horaFin, other.horaFin)) { return false; }		
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
		sb.append("HorarioRegion{");
		sb.append("idHorarioRegion" ).append("=").append(idHorarioRegion).append("|");
		sb.append("region" ).append("=").append(region).append("|");
		sb.append("horaInicio" ).append("=").append(horaInicio).append("|");
		sb.append("horaFin" ).append("=").append(horaFin).append("|");
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
		sb.append(" ").append(idHorarioRegion).append(" ");

		return sb.toString().trim();
	}

}
