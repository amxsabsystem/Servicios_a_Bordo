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
 * Class for mapping JPA Entity of Table Comunicado.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Comunicado")
@NamedQueries({
    @NamedQuery(name = "Comunicado.findAll", query = "SELECT c FROM Comunicado c")
    , @NamedQuery(name = "Comunicado.countAll", query = "SELECT COUNT(c) FROM Comunicado c")
    , @NamedQuery(name = "Comunicado.findByIdComunicado", query = "SELECT c FROM Comunicado c WHERE c.idComunicado = :idComunicado")
    , @NamedQuery(name = "Comunicado.findByReferencia", query = "SELECT c FROM Comunicado c WHERE c.referencia = :referencia")
    , @NamedQuery(name = "Comunicado.findByidIdioma", query = "SELECT c FROM Comunicado c WHERE c.idIdioma = :idIdioma")
    , @NamedQuery(name = "Comunicado.findByidTema", query = "SELECT c FROM Comunicado c WHERE c.idTema = :idTema")
    , @NamedQuery(name = "Comunicado.findByempleado", query = "SELECT c FROM Comunicado c WHERE c.empleado = :empleado")
    , @NamedQuery(name = "Comunicado.findByarea", query = "SELECT c FROM Comunicado c WHERE c.area = :area")
    , @NamedQuery(name = "Comunicado.findByestatusComunicado", query = "SELECT c FROM Comunicado c WHERE c.estatusComunicado = :estatusComunicado")
    , @NamedQuery(name = "Comunicado.findByaccionComunicado", query = "SELECT c FROM Comunicado c WHERE c.accionComunicado = :accionComunicado")
    , @NamedQuery(name = "Comunicado.findByRefOtroComunicado", query = "SELECT c FROM Comunicado c WHERE c.refOtroComunicado = :refOtroComunicado")
    , @NamedQuery(name = "Comunicado.findByMensaje", query = "SELECT c FROM Comunicado c WHERE c.mensaje = :mensaje")
    , @NamedQuery(name = "Comunicado.findByNoRevision", query = "SELECT c FROM Comunicado c WHERE c.noRevision = :noRevision")
    , @NamedQuery(name = "Comunicado.findByFechaInicioPublicacion", query = "SELECT c FROM Comunicado c WHERE c.fechaInicioPublicacion = :fechaInicioPublicacion")
    , @NamedQuery(name = "Comunicado.findByFechaFinPublicacion", query = "SELECT c FROM Comunicado c WHERE c.fechaFinPublicacion = :fechaFinPublicacion")
    , @NamedQuery(name = "Comunicado.findByTitulo", query = "SELECT c FROM Comunicado c WHERE c.titulo = :titulo")
    , @NamedQuery(name = "Comunicado.findByDirigidoSiNo", query = "SELECT c FROM Comunicado c WHERE c.dirigidoSiNo = :dirigidoSiNo")
    , @NamedQuery(name = "Comunicado.findByStatus", query = "SELECT c FROM Comunicado c WHERE c.status = :status")
    , @NamedQuery(name = "Comunicado.findByUsuarioCreo", query = "SELECT c FROM Comunicado c WHERE c.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Comunicado.findByFechaCreo", query = "SELECT c FROM Comunicado c WHERE c.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Comunicado.findByUsuarioModifico", query = "SELECT c FROM Comunicado c WHERE c.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Comunicado.findByFechaModifico", query = "SELECT c FROM Comunicado c WHERE c.fechaModifico = :fechaModifico")
    , @NamedQuery(name = "Comunicado.findByMensajeSlider", query = "SELECT c FROM Comunicado c WHERE c.mensajeSlider = :mensajeSlider")
})
public class      Comunicado 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1655250392;
    
    /**
    * The 'id comunicado' Maps to COLUMN 'id_comunicado'
    */
    
    @Id
    @Column(name = "ID_COMUNICADO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idComunicado;
    
    /**
    * The 'referencia' Maps to COLUMN 'referencia'
    */
    
    @Basic(optional = false)
    @Column(name = "REFERENCIA" , length=15, nullable=false)
    private String referencia;
    
    /**
    * The 'id idioma' Maps to COLUMN 'id_idioma'
    */
    
    @JoinColumn(name = "ID_IDIOMA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idIdioma;
    
    /**
    * The 'id tema' Maps to COLUMN 'id_tema'
    */
    
    @JoinColumn(name = "ID_TEMA" , referencedColumnName = "ID_TEMA_COMUNICADO")
    @ManyToOne(optional = false )
    private TemaComunicado idTema;
    
    /**
    * The 'id empleado' Maps to COLUMN 'id_empleado'
    */
    
    @JoinColumn(name = "ID_EMPLEADO" , referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(optional = false )
    private Empleado empleado;
    
    /**
    * The 'id area' Maps to COLUMN 'id_area'
    */
    
    @JoinColumn(name = "ID_AREA" , referencedColumnName = "ID_AREA")
    @ManyToOne(optional = false )
    private Area area;
    
    /**
    * The 'estatus comunicado' Maps to COLUMN 'estatus_comunicado'
    */
    
    @JoinColumn(name = "ESTATUS_COMUNICADO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro estatusComunicado;
    
    /**
    * The 'accion comunicado' Maps to COLUMN 'accion_comunicado'
    */
    
    @JoinColumn(name = "ACCION_COMUNICADO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro accionComunicado;
    
    /**
    * The 'ref otro comunicado' Maps to COLUMN 'ref_otro_comunicado'
    */
    
    @Basic(optional = true)
    @Column(name = "REF_OTRO_COMUNICADO" , length=15, nullable=true)
    private String refOtroComunicado;
    
    /**
    * The 'mensaje' Maps to COLUMN 'mensaje'
    */
    
    @Basic(optional = false)
    @Column(name = "MENSAJE" , length=1024, nullable=false)
    private String mensaje;
    
    /**
    * The 'no revision' Maps to COLUMN 'no_revision'
    */
    
    @Basic(optional = false)
    @Column(name = "NO_REVISION" , nullable=false)
    private Short noRevision;
    
    /**
    * The 'fecha inicio publicacion' Maps to COLUMN 'fecha_inicio_publicacion'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA_INICIO_PUBLICACION" , nullable=false)
    private java.sql.Date fechaInicioPublicacion;
    
    /**
    * The 'fecha fin publicacion' Maps to COLUMN 'fecha_fin_publicacion'
    */
    
    @Basic(optional = true)
    @Column(name = "FECHA_FIN_PUBLICACION" , nullable=true)
    private java.sql.Date fechaFinPublicacion;
    
    /**
    * The 'titulo' Maps to COLUMN 'titulo'
    */
    
    @Basic(optional = false)
    @Column(name = "TITULO" , length=255, nullable=false)
    private String titulo;
    
    /**
    * The 'dirigido si no' Maps to COLUMN 'dirigido_si_no'
    */
    
    @Basic(optional = true)
    @Column(name = "DIRIGIDO_SI_NO" , nullable=true)
    private Short dirigidoSiNo;
    
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
    * The 'mensaje slider' Maps to COLUMN 'mensaje_slider'
    */
    
    @Basic(optional = true)
    @Column(name = "MENSAJE_SLIDER" , length=30, nullable=true)
    private String mensajeSlider;
    /** 
    * Map the relation to comunicado_proveedor_estacion table where has id_comunicado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comunicado")
    private List<ComunicadoProveedorEstacion> comunicadoProveedorEstacionHasComunicadoList;
    
    /** 
    * Map the relation to comunicado_areas table where has id_comunicado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comunicado")
    private List<ComunicadoAreas> comunicadoAreasHasComunicadoList;
    
    /** 
    * Map the relation to bitacora_comunicado table where has id_comunicado object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comunicado")
    private List<BitacoraComunicado> bitacoraComunicadoHasComunicadoList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Comunicado() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdComunicado() { return this.idComunicado;}
    public void setIdComunicado(Integer v) { this.idComunicado = v; }
    
    public String getReferencia() { return this.referencia;}
    public void setReferencia(String v) { this.referencia = v; }
    
    public Parametro getIdIdioma(){ return this.idIdioma ; }
    public void setIdIdioma(Parametro x){ this.idIdioma = x; }
    
    public TemaComunicado getIdTema(){ return this.idTema ; }
    public void setIdTema(TemaComunicado x){ this.idTema = x; }
    
    public Empleado getEmpleado(){ return this.empleado ; }
    public void setEmpleado(Empleado x){ this.empleado = x; }
    
    public Area getArea(){ return this.area ; }
    public void setArea(Area x){ this.area = x; }
    
    public Parametro getEstatusComunicado(){ return this.estatusComunicado ; }
    public void setEstatusComunicado(Parametro x){ this.estatusComunicado = x; }
    
    public Parametro getAccionComunicado(){ return this.accionComunicado ; }
    public void setAccionComunicado(Parametro x){ this.accionComunicado = x; }
    
    public String getRefOtroComunicado() { return this.refOtroComunicado;}
    public void setRefOtroComunicado(String v) { this.refOtroComunicado = v; }
    
    public String getMensaje() { return this.mensaje;}
    public void setMensaje(String v) { this.mensaje = v; }
    
    public Short getNoRevision() { return this.noRevision;}
    public void setNoRevision(Short v) { this.noRevision = v; }
    
    public java.sql.Date getFechaInicioPublicacion() { return this.fechaInicioPublicacion;}
    public void setFechaInicioPublicacion(java.sql.Date v) { this.fechaInicioPublicacion = v; }
    
    public java.sql.Date getFechaFinPublicacion() { return this.fechaFinPublicacion;}
    public void setFechaFinPublicacion(java.sql.Date v) { this.fechaFinPublicacion = v; }
    
    public String getTitulo() { return this.titulo;}
    public void setTitulo(String v) { this.titulo = v; }
    
    public Short getDirigidoSiNo() { return this.dirigidoSiNo;}
    public void setDirigidoSiNo(Short v) { this.dirigidoSiNo = v; }
    
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
    
    public String getMensajeSlider() { return this.mensajeSlider;}
    public void setMensajeSlider(String v) { this.mensajeSlider = v; }
    
    // O2M <*>    
    public List<ComunicadoProveedorEstacion> getComunicadoProveedorEstacionHasComunicadoList(){ return this.comunicadoProveedorEstacionHasComunicadoList; }
    public void setComunicadoProveedorEstacionHasComunicadoList(List<ComunicadoProveedorEstacion> v){ this.comunicadoProveedorEstacionHasComunicadoList = v; }
    
    public List<ComunicadoAreas> getComunicadoAreasHasComunicadoList(){ return this.comunicadoAreasHasComunicadoList; }
    public void setComunicadoAreasHasComunicadoList(List<ComunicadoAreas> v){ this.comunicadoAreasHasComunicadoList = v; }
    
    public List<BitacoraComunicado> getBitacoraComunicadoHasComunicadoList(){ return this.bitacoraComunicadoHasComunicadoList; }
    public void setBitacoraComunicadoHasComunicadoList(List<BitacoraComunicado> v){ this.bitacoraComunicadoHasComunicadoList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idComunicado).hashCode();
		hash += String.valueOf(referencia).hashCode();
		hash += String.valueOf(idIdioma).hashCode();
		hash += String.valueOf(idTema).hashCode();
		hash += String.valueOf(empleado).hashCode();
		hash += String.valueOf(area).hashCode();
		hash += String.valueOf(estatusComunicado).hashCode();
		hash += String.valueOf(accionComunicado).hashCode();
		hash += String.valueOf(refOtroComunicado).hashCode();
		hash += String.valueOf(mensaje).hashCode();
		hash += String.valueOf(noRevision).hashCode();
		hash += String.valueOf(fechaInicioPublicacion).hashCode();
		hash += String.valueOf(fechaFinPublicacion).hashCode();
		hash += String.valueOf(titulo).hashCode();
		hash += String.valueOf(dirigidoSiNo).hashCode();
		hash += String.valueOf(status).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(usuarioModifico).hashCode();
		hash += String.valueOf(fechaModifico).hashCode();
		hash += String.valueOf(mensajeSlider).hashCode();
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
        if (!(o instanceof Comunicado)) {
            return false;
        }		
		Comunicado other = (Comunicado ) o;
		if (!Objects.equals(this.idComunicado, other.idComunicado)) { return false; }		
		if (!Objects.equals(this.referencia, other.referencia)) { return false; }		
		if (!Objects.equals(this.idIdioma, other.idIdioma)) { return false; }		
		if (!Objects.equals(this.idTema, other.idTema)) { return false; }		
		if (!Objects.equals(this.empleado, other.empleado)) { return false; }		
		if (!Objects.equals(this.area, other.area)) { return false; }		
		if (!Objects.equals(this.estatusComunicado, other.estatusComunicado)) { return false; }		
		if (!Objects.equals(this.accionComunicado, other.accionComunicado)) { return false; }		
		if (!Objects.equals(this.refOtroComunicado, other.refOtroComunicado)) { return false; }		
		if (!Objects.equals(this.mensaje, other.mensaje)) { return false; }		
		if (!Objects.equals(this.noRevision, other.noRevision)) { return false; }		
		if (!Objects.equals(this.fechaInicioPublicacion, other.fechaInicioPublicacion)) { return false; }		
		if (!Objects.equals(this.fechaFinPublicacion, other.fechaFinPublicacion)) { return false; }		
		if (!Objects.equals(this.titulo, other.titulo)) { return false; }		
		if (!Objects.equals(this.dirigidoSiNo, other.dirigidoSiNo)) { return false; }		
		if (!Objects.equals(this.status, other.status)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.usuarioModifico, other.usuarioModifico)) { return false; }		
		if (!Objects.equals(this.fechaModifico, other.fechaModifico)) { return false; }		
		if (!Objects.equals(this.mensajeSlider, other.mensajeSlider)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Comunicado{");
		sb.append("idComunicado" ).append("=").append(idComunicado).append("|");
		sb.append("referencia" ).append("=").append(referencia).append("|");
		sb.append("idIdioma" ).append("=").append(idIdioma).append("|");
		sb.append("idTema" ).append("=").append(idTema).append("|");
		sb.append("empleado" ).append("=").append(empleado).append("|");
		sb.append("area" ).append("=").append(area).append("|");
		sb.append("estatusComunicado" ).append("=").append(estatusComunicado).append("|");
		sb.append("accionComunicado" ).append("=").append(accionComunicado).append("|");
		sb.append("refOtroComunicado" ).append("=").append(refOtroComunicado).append("|");
		sb.append("mensaje" ).append("=").append(mensaje).append("|");
		sb.append("noRevision" ).append("=").append(noRevision).append("|");
		sb.append("fechaInicioPublicacion" ).append("=").append(fechaInicioPublicacion).append("|");
		sb.append("fechaFinPublicacion" ).append("=").append(fechaFinPublicacion).append("|");
		sb.append("titulo" ).append("=").append(titulo).append("|");
		sb.append("dirigidoSiNo" ).append("=").append(dirigidoSiNo).append("|");
		sb.append("status" ).append("=").append(status).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("usuarioModifico" ).append("=").append(usuarioModifico).append("|");
		sb.append("fechaModifico" ).append("=").append(fechaModifico).append("|");
		sb.append("mensajeSlider" ).append("=").append(mensajeSlider).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idComunicado).append(" ");

		return sb.toString().trim();
	}

}
