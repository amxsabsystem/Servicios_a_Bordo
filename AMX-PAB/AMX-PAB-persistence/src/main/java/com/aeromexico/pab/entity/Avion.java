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
 * Class for mapping JPA Entity of Table avion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "avion")
@NamedQueries({
    @NamedQuery(name = "Avion.findAll", query = "SELECT a FROM Avion a")
    , @NamedQuery(name = "Avion.countAll", query = "SELECT COUNT(a) FROM Avion a")
    , @NamedQuery(name = "Avion.findByIdAvion", query = "SELECT a FROM Avion a WHERE a.idAvion = :idAvion")
    , @NamedQuery(name = "Avion.findBytipoEquipoAvion", query = "SELECT a FROM Avion a WHERE a.tipoEquipoAvion = :tipoEquipoAvion")
    , @NamedQuery(name = "Avion.findByidConectividad", query = "SELECT a FROM Avion a WHERE a.idConectividad = :idConectividad")
    , @NamedQuery(name = "Avion.findByMatricula", query = "SELECT a FROM Avion a WHERE a.matricula = :matricula")
    , @NamedQuery(name = "Avion.findByMatriculaCorta", query = "SELECT a FROM Avion a WHERE a.matriculaCorta = :matriculaCorta")
    , @NamedQuery(name = "Avion.findByStatus", query = "SELECT a FROM Avion a WHERE a.status = :status")
    , @NamedQuery(name = "Avion.findByUsuarioCreo", query = "SELECT a FROM Avion a WHERE a.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Avion.findByFechaCreo", query = "SELECT a FROM Avion a WHERE a.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Avion.findByUsuarioModifico", query = "SELECT a FROM Avion a WHERE a.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Avion.findByFechaModifico", query = "SELECT a FROM Avion a WHERE a.fechaModifico = :fechaModifico")
})
public class      Avion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 93201331;
    
    /**
    * The 'id avion' Maps to COLUMN 'id_avion'
    */
    
    @Id
    @Column(name = "ID_AVION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAvion;
    
    /**
    * The 'id tipo equipo avion' Maps to COLUMN 'id_tipo_equipo_avion'
    */
    
    @JoinColumn(name = "ID_TIPO_EQUIPO_AVION" , referencedColumnName = "ID_TIPO_EQUIPO_AVION")
    @ManyToOne(optional = false )
    private TipoEquipoAvion tipoEquipoAvion;
    
    /**
    * The 'id conectividad' Maps to COLUMN 'id_conectividad'
    */
    
    @JoinColumn(name = "ID_CONECTIVIDAD" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idConectividad;
    
    /**
    * The 'matricula' Maps to COLUMN 'matricula'
    */
    
    @Basic(optional = false)
    @Column(name = "MATRICULA" , length=50, nullable=false)
    private String matricula;
    
    /**
    * The 'matricula corta' Maps to COLUMN 'matricula_corta'
    */
    
    @Basic(optional = false)
    @Column(name = "MATRICULA_CORTA" , length=10, nullable=false)
    private String matriculaCorta;
    
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
    * Map the relation to equipamiento_semifijo table where has id_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avion")
    private List<EquipamientoSemifijo> equipamientoSemifijoHasAvionList;
    
    /** 
    * Map the relation to avion_audifono table where has id_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avion")
    private List<AvionAudifono> avionAudifonoHasAvionList;
    
    /** 
    * Map the relation to equipamiento_fijo table where has id_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avion")
    private List<EquipamientoFijo> equipamientoFijoHasAvionList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Avion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdAvion() { return this.idAvion;}
    public void setIdAvion(Integer v) { this.idAvion = v; }
    
    public TipoEquipoAvion getTipoEquipoAvion(){ return this.tipoEquipoAvion ; }
    public void setTipoEquipoAvion(TipoEquipoAvion x){ this.tipoEquipoAvion = x; }
    
    public Parametro getIdConectividad(){ return this.idConectividad ; }
    public void setIdConectividad(Parametro x){ this.idConectividad = x; }
    
    public String getMatricula() { return this.matricula;}
    public void setMatricula(String v) { this.matricula = v; }
    
    public String getMatriculaCorta() { return this.matriculaCorta;}
    public void setMatriculaCorta(String v) { this.matriculaCorta = v; }
    
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
    public List<EquipamientoSemifijo> getEquipamientoSemifijoHasAvionList(){ return this.equipamientoSemifijoHasAvionList; }
    public void setEquipamientoSemifijoHasAvionList(List<EquipamientoSemifijo> v){ this.equipamientoSemifijoHasAvionList = v; }
    
    public List<AvionAudifono> getAvionAudifonoHasAvionList(){ return this.avionAudifonoHasAvionList; }
    public void setAvionAudifonoHasAvionList(List<AvionAudifono> v){ this.avionAudifonoHasAvionList = v; }
    
    public List<EquipamientoFijo> getEquipamientoFijoHasAvionList(){ return this.equipamientoFijoHasAvionList; }
    public void setEquipamientoFijoHasAvionList(List<EquipamientoFijo> v){ this.equipamientoFijoHasAvionList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idAvion).hashCode();
		hash += String.valueOf(tipoEquipoAvion).hashCode();
		hash += String.valueOf(idConectividad).hashCode();
		hash += String.valueOf(matricula).hashCode();
		hash += String.valueOf(matriculaCorta).hashCode();
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
        if (!(o instanceof Avion)) {
            return false;
        }		
		Avion other = (Avion ) o;
		if (!Objects.equals(this.idAvion, other.idAvion)) { return false; }		
		if (!Objects.equals(this.tipoEquipoAvion, other.tipoEquipoAvion)) { return false; }		
		if (!Objects.equals(this.idConectividad, other.idConectividad)) { return false; }		
		if (!Objects.equals(this.matricula, other.matricula)) { return false; }		
		if (!Objects.equals(this.matriculaCorta, other.matriculaCorta)) { return false; }		
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
		sb.append("Avion{");
		sb.append("idAvion" ).append("=").append(idAvion).append("|");
		sb.append("tipoEquipoAvion" ).append("=").append(tipoEquipoAvion).append("|");
		sb.append("idConectividad" ).append("=").append(idConectividad).append("|");
		sb.append("matricula" ).append("=").append(matricula).append("|");
		sb.append("matriculaCorta" ).append("=").append(matriculaCorta).append("|");
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
		sb.append(" ").append(idAvion).append(" ");

		return sb.toString().trim();
	}

}
