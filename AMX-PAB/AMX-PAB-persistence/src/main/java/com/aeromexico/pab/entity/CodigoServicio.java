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
 * Class for mapping JPA Entity of Table Codigo_Servicio.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Codigo_Servicio")
@NamedQueries({
    @NamedQuery(name = "CodigoServicio.findAll", query = "SELECT c FROM CodigoServicio c")
    , @NamedQuery(name = "CodigoServicio.countAll", query = "SELECT COUNT(c) FROM CodigoServicio c")
    , @NamedQuery(name = "CodigoServicio.findByIdCodigoServicio", query = "SELECT c FROM CodigoServicio c WHERE c.idCodigoServicio = :idCodigoServicio")
    , @NamedQuery(name = "CodigoServicio.findByclase", query = "SELECT c FROM CodigoServicio c WHERE c.clase = :clase")
    , @NamedQuery(name = "CodigoServicio.findByCveCodigoServicio", query = "SELECT c FROM CodigoServicio c WHERE c.cveCodigoServicio = :cveCodigoServicio")
    , @NamedQuery(name = "CodigoServicio.findByDescripcion", query = "SELECT c FROM CodigoServicio c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "CodigoServicio.findByidTipoCodigoServicio", query = "SELECT c FROM CodigoServicio c WHERE c.idTipoCodigoServicio = :idTipoCodigoServicio")
    , @NamedQuery(name = "CodigoServicio.findByidTipoCiclo", query = "SELECT c FROM CodigoServicio c WHERE c.idTipoCiclo = :idTipoCiclo")
    , @NamedQuery(name = "CodigoServicio.findByStatus", query = "SELECT c FROM CodigoServicio c WHERE c.status = :status")
    , @NamedQuery(name = "CodigoServicio.findByUsuarioCreo", query = "SELECT c FROM CodigoServicio c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "CodigoServicio.findByFechaCreo", query = "SELECT c FROM CodigoServicio c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "CodigoServicio.findByUsuarioModifico", query = "SELECT c FROM CodigoServicio c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "CodigoServicio.findByFechaModifico", query = "SELECT c FROM CodigoServicio c WHERE c.fechaModifico = :fechaModifico")
})
public class      CodigoServicio 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = -168609860;
    
    /**
    * The 'id codigo servicio' Maps to COLUMN 'id_codigo_servicio'
    */
    
    @Id
    @Column(name = "ID_CODIGO_SERVICIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCodigoServicio;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'cve codigo servicio' Maps to COLUMN 'cve_codigo_servicio'
    */
    
    @Basic(optional = false)
    @Column(name = "CVE_CODIGO_SERVICIO" , length=16, nullable=false)
    private String cveCodigoServicio;
    
    /**
    * The 'descripcion' Maps to COLUMN 'descripcion'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION" , length=64, nullable=false)
    private String descripcion;
    
    /**
    * The 'id tipo codigo servicio' Maps to COLUMN 'id_tipo_codigo_servicio'
    */
    
    @JoinColumn(name = "ID_TIPO_CODIGO_SERVICIO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipoCodigoServicio;
    
    /**
    * The 'id tipo ciclo' Maps to COLUMN 'id_tipo_ciclo'
    */
    
    @JoinColumn(name = "ID_TIPO_CICLO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipoCiclo;
    
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
    * Map the relation to potencial table where has id_codigo_servicio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoServicio")
    private List<Potencial> potencialHasCodigoServicioList;
    
    /** 
    * Map the relation to Asignacion_Servicio_Duracion table where has id_codigo_servicio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoServicio")
    private List<AsignacionServicioDuracion> asignacionServicioDuracionHasCodigoServicioList;
    
    /** 
    * Map the relation to tabla_abordamiento table where has id_codigo_servicio object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoServicio")
    private List<TablaAbordamiento> tablaAbordamientoHasCodigoServicioList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public CodigoServicio() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdCodigoServicio() { return this.idCodigoServicio;}
    public void setIdCodigoServicio(Integer v) { this.idCodigoServicio = v; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public String getCveCodigoServicio() { return this.cveCodigoServicio;}
    public void setCveCodigoServicio(String v) { this.cveCodigoServicio = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
    public Parametro getIdTipoCodigoServicio(){ return this.idTipoCodigoServicio ; }
    public void setIdTipoCodigoServicio(Parametro x){ this.idTipoCodigoServicio = x; }
    
    public Parametro getIdTipoCiclo(){ return this.idTipoCiclo ; }
    public void setIdTipoCiclo(Parametro x){ this.idTipoCiclo = x; }
    
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
    public List<Potencial> getPotencialHasCodigoServicioList(){ return this.potencialHasCodigoServicioList; }
    public void setPotencialHasCodigoServicioList(List<Potencial> v){ this.potencialHasCodigoServicioList = v; }
    
    public List<AsignacionServicioDuracion> getAsignacionServicioDuracionHasCodigoServicioList(){ return this.asignacionServicioDuracionHasCodigoServicioList; }
    public void setAsignacionServicioDuracionHasCodigoServicioList(List<AsignacionServicioDuracion> v){ this.asignacionServicioDuracionHasCodigoServicioList = v; }
    
    public List<TablaAbordamiento> getTablaAbordamientoHasCodigoServicioList(){ return this.tablaAbordamientoHasCodigoServicioList; }
    public void setTablaAbordamientoHasCodigoServicioList(List<TablaAbordamiento> v){ this.tablaAbordamientoHasCodigoServicioList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idCodigoServicio).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(cveCodigoServicio).hashCode();
		hash += String.valueOf(descripcion).hashCode();
		hash += String.valueOf(idTipoCodigoServicio).hashCode();
		hash += String.valueOf(idTipoCiclo).hashCode();
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
        if (!(o instanceof CodigoServicio)) {
            return false;
        }		
		CodigoServicio other = (CodigoServicio ) o;
		if (!Objects.equals(this.idCodigoServicio, other.idCodigoServicio)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.cveCodigoServicio, other.cveCodigoServicio)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
		if (!Objects.equals(this.idTipoCodigoServicio, other.idTipoCodigoServicio)) { return false; }		
		if (!Objects.equals(this.idTipoCiclo, other.idTipoCiclo)) { return false; }		
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
		sb.append("CodigoServicio{");
		sb.append("idCodigoServicio" ).append("=").append(idCodigoServicio).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("cveCodigoServicio" ).append("=").append(cveCodigoServicio).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
		sb.append("idTipoCodigoServicio" ).append("=").append(idTipoCodigoServicio).append("|");
		sb.append("idTipoCiclo" ).append("=").append(idTipoCiclo).append("|");
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
		sb.append(" ").append(idCodigoServicio).append(" ");

		return sb.toString().trim();
	}

}
