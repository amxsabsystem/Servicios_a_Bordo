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
 * Class for mapping JPA Entity of Table horario.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "horario")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
    , @NamedQuery(name = "Horario.countAll", query = "SELECT COUNT(h) FROM Horario h")
    , @NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario")
    , @NamedQuery(name = "Horario.findByregion", query = "SELECT h FROM Horario h WHERE h.region = :region")
    , @NamedQuery(name = "Horario.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horaInicio = :horaInicio")
    , @NamedQuery(name = "Horario.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin")
    , @NamedQuery(name = "Horario.findByStatus", query = "SELECT h FROM Horario h WHERE h.status = :status")
    , @NamedQuery(name = "Horario.findByUsuarioCreo", query = "SELECT h FROM Horario h WHERE h.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Horario.findByFechaCreo", query = "SELECT h FROM Horario h WHERE h.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Horario.findByUsuarioModifico", query = "SELECT h FROM Horario h WHERE h.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Horario.findByFechaModifico", query = "SELECT h FROM Horario h WHERE h.fechaModifico = :fechaModifico")
})
public class      Horario 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1097222114;
    
    /**
    * The 'id horario' Maps to COLUMN 'id_horario'
    */
    
    @Id
    @Column(name = "ID_HORARIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idHorario;
    
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
    @Column(name = "HORA_INICIO" , nullable=false)
    private java.sql.Time horaInicio;
    
    /**
    * The 'hora fin' Maps to COLUMN 'hora_fin'
    */
    
    @Basic(optional = false)
    @Column(name = "HORA_FIN" , nullable=false)
    private java.sql.Time horaFin;
    
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
    public Horario() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdHorario() { return this.idHorario;}
    public void setIdHorario(Integer v) { this.idHorario = v; }
    
    public Region getRegion(){ return this.region ; }
    public void setRegion(Region x){ this.region = x; }
    
    public java.sql.Time getHoraInicio() { return this.horaInicio;}
    public void setHoraInicio(java.sql.Time v) { this.horaInicio = v; }
    
    public java.sql.Time getHoraFin() { return this.horaFin;}
    public void setHoraFin(java.sql.Time v) { this.horaFin = v; }
    
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
		hash += String.valueOf(idHorario).hashCode();
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
        if (!(o instanceof Horario)) {
            return false;
        }		
		Horario other = (Horario ) o;
		if (!Objects.equals(this.idHorario, other.idHorario)) { return false; }		
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
		sb.append("Horario{");
		sb.append("idHorario" ).append("=").append(idHorario).append("|");
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
		sb.append(" ").append(idHorario).append(" ");

		return sb.toString().trim();
	}

}
