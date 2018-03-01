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
 * Class for mapping JPA Entity of Table avion_sistema_entretenimiento.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "avion_sistema_entretenimiento")
@NamedQueries({
    @NamedQuery(name = "AvionSistemaEntretenimiento.findAll", query = "SELECT a FROM AvionSistemaEntretenimiento a")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.countAll", query = "SELECT COUNT(a) FROM AvionSistemaEntretenimiento a")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByStatus", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.status = :status")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByUsuarioCreo", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByFechaCreo", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByUsuarioModifico", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByFechaModifico", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.fechaModifico = :fechaModifico")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByavion", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.avion = :avion")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findBysistemaEntretenimiento", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.sistemaEntretenimiento = :sistemaEntretenimiento")
    , @NamedQuery(name = "AvionSistemaEntretenimiento.findByAvionSistemaEntretenimientoPK", query = "SELECT a FROM AvionSistemaEntretenimiento a WHERE a.avionSistemaEntretenimientoPK = :avionSistemaEntretenimientoPK")
})
public class      AvionSistemaEntretenimiento 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -2005869151;
    
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
    * The 'id avion' Maps to COLUMN 'id_avion'
    */
    
    @JoinColumn(name = "ID_AVION" , referencedColumnName = "ID_AVION", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private Avion avion;
    
    /**
    * The 'id sistema entretenimiento' Maps to COLUMN 'id_sistema_entretenimiento'
    */
    
    @JoinColumn(name = "ID_SISTEMA_ENTRETENIMIENTO" , referencedColumnName = "ID_SISTEMA_ENTRETENIMIENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private SistemaEntretenimiento sistemaEntretenimiento;
    
    /**
    * The 'avion sistema entretenimiento P K' Maps to COLUMN 'avion_sistema_entretenimiento_P_K'
    */
    
    @EmbeddedId
    private AvionSistemaEntretenimientoPK avionSistemaEntretenimientoPK;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public AvionSistemaEntretenimiento() {
    }
    
    /**
     * Getters and Setters
     */
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
    
    public Avion getAvion(){ return this.avion ; }
    public void setAvion(Avion x){ this.avion = x; }
    
    public SistemaEntretenimiento getSistemaEntretenimiento(){ return this.sistemaEntretenimiento ; }
    public void setSistemaEntretenimiento(SistemaEntretenimiento x){ this.sistemaEntretenimiento = x; }
    
    public AvionSistemaEntretenimientoPK getAvionSistemaEntretenimientoPK() { return this.avionSistemaEntretenimientoPK;}
    public void setAvionSistemaEntretenimientoPK(AvionSistemaEntretenimientoPK v) { this.avionSistemaEntretenimientoPK = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(status).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(usuarioModifico).hashCode();
		hash += String.valueOf(fechaModifico).hashCode();
		hash += String.valueOf(avion).hashCode();
		hash += String.valueOf(sistemaEntretenimiento).hashCode();
		hash += String.valueOf(avionSistemaEntretenimientoPK).hashCode();
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
        if (!(o instanceof AvionSistemaEntretenimiento)) {
            return false;
        }		
		AvionSistemaEntretenimiento other = (AvionSistemaEntretenimiento ) o;
		if (!Objects.equals(this.status, other.status)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.usuarioModifico, other.usuarioModifico)) { return false; }		
		if (!Objects.equals(this.fechaModifico, other.fechaModifico)) { return false; }		
		if (!Objects.equals(this.avion, other.avion)) { return false; }		
		if (!Objects.equals(this.sistemaEntretenimiento, other.sistemaEntretenimiento)) { return false; }		
		if (!Objects.equals(this.avionSistemaEntretenimientoPK, other.avionSistemaEntretenimientoPK)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("AvionSistemaEntretenimiento{");
		sb.append("status" ).append("=").append(status).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("usuarioModifico" ).append("=").append(usuarioModifico).append("|");
		sb.append("fechaModifico" ).append("=").append(fechaModifico).append("|");
		sb.append("avion" ).append("=").append(avion).append("|");
		sb.append("sistemaEntretenimiento" ).append("=").append(sistemaEntretenimiento).append("|");
		sb.append("avionSistemaEntretenimientoPK" ).append("=").append(avionSistemaEntretenimientoPK).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();

		return sb.toString().trim();
	}

}
