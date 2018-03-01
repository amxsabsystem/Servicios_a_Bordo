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
 * Class for mapping JPA Entity of Table Duracion_vuelos.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Duracion_vuelos")
@NamedQueries({
    @NamedQuery(name = "DuracionVuelos.findAll", query = "SELECT d FROM DuracionVuelos d")
    , @NamedQuery(name = "DuracionVuelos.countAll", query = "SELECT COUNT(d) FROM DuracionVuelos d")
    , @NamedQuery(name = "DuracionVuelos.findByIdDuracionVuelos", query = "SELECT d FROM DuracionVuelos d WHERE d.idDuracionVuelos = :idDuracionVuelos")
    , @NamedQuery(name = "DuracionVuelos.findByTiempoInicio", query = "SELECT d FROM DuracionVuelos d WHERE d.tiempoInicio = :tiempoInicio")
    , @NamedQuery(name = "DuracionVuelos.findByTiempoFin", query = "SELECT d FROM DuracionVuelos d WHERE d.tiempoFin = :tiempoFin")
    , @NamedQuery(name = "DuracionVuelos.findByStatus", query = "SELECT d FROM DuracionVuelos d WHERE d.status = :status")
    , @NamedQuery(name = "DuracionVuelos.findByUsuarioCreo", query = "SELECT d FROM DuracionVuelos d WHERE d.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "DuracionVuelos.findByFechaCreo", query = "SELECT d FROM DuracionVuelos d WHERE d.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "DuracionVuelos.findByUsuarioModifico", query = "SELECT d FROM DuracionVuelos d WHERE d.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "DuracionVuelos.findByFechaModifico", query = "SELECT d FROM DuracionVuelos d WHERE d.fechaModifico = :fechaModifico")
})
public class      DuracionVuelos 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1572339236;
    
    /**
    * The 'id duracion vuelos' Maps to COLUMN 'id_duracion_vuelos'
    */
    
    @Id
    @Column(name = "ID_DURACION_VUELOS" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDuracionVuelos;
    
    /**
    * The 'tiempo inicio' Maps to COLUMN 'tiempo_inicio'
    */
    
    @Basic(optional = false)
    @Column(name = "TIEMPO_INICIO" , length=5, nullable=false)
    private String tiempoInicio;
    
    /**
    * The 'tiempo fin' Maps to COLUMN 'tiempo_fin'
    */
    
    @Basic(optional = false)
    @Column(name = "TIEMPO_FIN" , length=5, nullable=false)
    private String tiempoFin;
    
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
    * Map the relation to Asignacion_Servicio_Duracion table where has id_duracion_vuelos object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "duracionVuelos")
    private List<AsignacionServicioDuracion> asignacionServicioDuracionHasDuracionVuelosList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public DuracionVuelos() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdDuracionVuelos() { return this.idDuracionVuelos;}
    public void setIdDuracionVuelos(Integer v) { this.idDuracionVuelos = v; }
    
    public String getTiempoInicio() { return this.tiempoInicio;}
    public void setTiempoInicio(String v) { this.tiempoInicio = v; }
    
    public String getTiempoFin() { return this.tiempoFin;}
    public void setTiempoFin(String v) { this.tiempoFin = v; }
    
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
    public List<AsignacionServicioDuracion> getAsignacionServicioDuracionHasDuracionVuelosList(){ return this.asignacionServicioDuracionHasDuracionVuelosList; }
    public void setAsignacionServicioDuracionHasDuracionVuelosList(List<AsignacionServicioDuracion> v){ this.asignacionServicioDuracionHasDuracionVuelosList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idDuracionVuelos).hashCode();
		hash += String.valueOf(tiempoInicio).hashCode();
		hash += String.valueOf(tiempoFin).hashCode();
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
        if (!(o instanceof DuracionVuelos)) {
            return false;
        }		
		DuracionVuelos other = (DuracionVuelos ) o;
		if (!Objects.equals(this.idDuracionVuelos, other.idDuracionVuelos)) { return false; }		
		if (!Objects.equals(this.tiempoInicio, other.tiempoInicio)) { return false; }		
		if (!Objects.equals(this.tiempoFin, other.tiempoFin)) { return false; }		
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
		sb.append("DuracionVuelos{");
		sb.append("idDuracionVuelos" ).append("=").append(idDuracionVuelos).append("|");
		sb.append("tiempoInicio" ).append("=").append(tiempoInicio).append("|");
		sb.append("tiempoFin" ).append("=").append(tiempoFin).append("|");
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
		sb.append(" ").append(idDuracionVuelos).append(" ");

		return sb.toString().trim();
	}

}
