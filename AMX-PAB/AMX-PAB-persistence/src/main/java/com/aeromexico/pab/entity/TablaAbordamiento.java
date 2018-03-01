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
 * Class for mapping JPA Entity of Table tabla_abordamiento.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tabla_abordamiento")
@NamedQueries({
    @NamedQuery(name = "TablaAbordamiento.findAll", query = "SELECT t FROM TablaAbordamiento t")
    , @NamedQuery(name = "TablaAbordamiento.countAll", query = "SELECT COUNT(t) FROM TablaAbordamiento t")
    , @NamedQuery(name = "TablaAbordamiento.findByIdTablaAbordamiento", query = "SELECT t FROM TablaAbordamiento t WHERE t.idTablaAbordamiento = :idTablaAbordamiento")
    , @NamedQuery(name = "TablaAbordamiento.findByidOrigenVuelo", query = "SELECT t FROM TablaAbordamiento t WHERE t.idOrigenVuelo = :idOrigenVuelo")
    , @NamedQuery(name = "TablaAbordamiento.findByclase", query = "SELECT t FROM TablaAbordamiento t WHERE t.clase = :clase")
    , @NamedQuery(name = "TablaAbordamiento.findBycodigoServicio", query = "SELECT t FROM TablaAbordamiento t WHERE t.codigoServicio = :codigoServicio")
    , @NamedQuery(name = "TablaAbordamiento.findByRevision", query = "SELECT t FROM TablaAbordamiento t WHERE t.revision = :revision")
    , @NamedQuery(name = "TablaAbordamiento.findByTodasRegiones", query = "SELECT t FROM TablaAbordamiento t WHERE t.todasRegiones = :todasRegiones")
    , @NamedQuery(name = "TablaAbordamiento.findByTodosModelosAvion", query = "SELECT t FROM TablaAbordamiento t WHERE t.todosModelosAvion = :todosModelosAvion")
    , @NamedQuery(name = "TablaAbordamiento.findByAplicaTsu", query = "SELECT t FROM TablaAbordamiento t WHERE t.aplicaTsu = :aplicaTsu")
    , @NamedQuery(name = "TablaAbordamiento.findByAplicaSobreabordaje", query = "SELECT t FROM TablaAbordamiento t WHERE t.aplicaSobreabordaje = :aplicaSobreabordaje")
    , @NamedQuery(name = "TablaAbordamiento.findByStatus", query = "SELECT t FROM TablaAbordamiento t WHERE t.status = :status")
    , @NamedQuery(name = "TablaAbordamiento.findByUsuarioCreo", query = "SELECT t FROM TablaAbordamiento t WHERE t.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "TablaAbordamiento.findByFechaCreo", query = "SELECT t FROM TablaAbordamiento t WHERE t.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "TablaAbordamiento.findByUsuarioModifico", query = "SELECT t FROM TablaAbordamiento t WHERE t.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "TablaAbordamiento.findByFechaModifico", query = "SELECT t FROM TablaAbordamiento t WHERE t.fechaModifico = :fechaModifico")
})
public class      TablaAbordamiento 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 937371478;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @Id
    @Column(name = "ID_TABLA_ABORDAMIENTO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTablaAbordamiento;
    
    /**
    * The 'id origen vuelo' Maps to COLUMN 'id_origen_vuelo'
    */
    
    @JoinColumn(name = "ID_ORIGEN_VUELO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idOrigenVuelo;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'id codigo servicio' Maps to COLUMN 'id_codigo_servicio'
    */
    
    @JoinColumn(name = "ID_CODIGO_SERVICIO" , referencedColumnName = "ID_CODIGO_SERVICIO")
    @ManyToOne(optional = false )
    private CodigoServicio codigoServicio;
    
    /**
    * The 'revision' Maps to COLUMN 'revision'
    */
    
    @Basic(optional = false)
    @Column(name = "REVISION" , nullable=false)
    private int revision;
    
    /**
    * The 'todas regiones' Maps to COLUMN 'todas_regiones'
    */
    
    @Basic(optional = true)
    @Column(name = "TODAS_REGIONES" , nullable=true)
    private Short todasRegiones;
    
    /**
    * The 'todos modelos avion' Maps to COLUMN 'todos_modelos_avion'
    */
    
    @Basic(optional = true)
    @Column(name = "TODOS_MODELOS_AVION" , nullable=true)
    private Integer todosModelosAvion;
    
    /**
    * The 'aplica tsu' Maps to COLUMN 'aplica_tsu'
    */
    
    @Basic(optional = true)
    @Column(name = "APLICA_TSU" , nullable=true)
    private Short aplicaTsu;
    
    /**
    * The 'aplica sobreabordaje' Maps to COLUMN 'aplica_sobreabordaje'
    */
    
    @Basic(optional = true)
    @Column(name = "APLICA_SOBREABORDAJE" , nullable=true)
    private Short aplicaSobreabordaje;
    
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
    * Map the relation to region_abordaje table where has id_tabla_abordamiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tablaAbordamiento")
    private List<RegionAbordaje> regionAbordajeHasTablaAbordamientoList;
    
    /** 
    * Map the relation to tsu_abordamiento table where has id_tabla_abordamiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tablaAbordamiento")
    private List<TsuAbordamiento> tsuAbordamientoHasTablaAbordamientoList;
    
    /** 
    * Map the relation to detalle_tabla_tsu table where has id_tabla_abordamiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tablaAbordamiento")
    private List<DetalleTablaTsu> detalleTablaTsuHasTablaAbordamientoList;
    
    /** 
    * Map the relation to detalle_tabla_complementario table where has id_tabla_abordamiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tablaAbordamiento")
    private List<DetalleTablaComplementario> detalleTablaComplementarioHasTablaAbordamientoList;
    
    /** 
    * Map the relation to sobreabordaje table where has id_tabla_abordamiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tablaAbordamiento")
    private List<Sobreabordaje> sobreabordajeHasTablaAbordamientoList;
    

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "tablaAbordamientoList")
    private List<ModeloAvion> modeloAvionList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TablaAbordamiento() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTablaAbordamiento() { return this.idTablaAbordamiento;}
    public void setIdTablaAbordamiento(Integer v) { this.idTablaAbordamiento = v; }
    
    public Parametro getIdOrigenVuelo(){ return this.idOrigenVuelo ; }
    public void setIdOrigenVuelo(Parametro x){ this.idOrigenVuelo = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public CodigoServicio getCodigoServicio(){ return this.codigoServicio ; }
    public void setCodigoServicio(CodigoServicio x){ this.codigoServicio = x; }
    
    public int getRevision() { return this.revision;}
    public void setRevision(int v) { this.revision = v; }
    
    public Short getTodasRegiones() { return this.todasRegiones;}
    public void setTodasRegiones(Short v) { this.todasRegiones = v; }
    
    public Integer getTodosModelosAvion() { return this.todosModelosAvion;}
    public void setTodosModelosAvion(Integer v) { this.todosModelosAvion = v; }
    
    public Short getAplicaTsu() { return this.aplicaTsu;}
    public void setAplicaTsu(Short v) { this.aplicaTsu = v; }
    
    public Short getAplicaSobreabordaje() { return this.aplicaSobreabordaje;}
    public void setAplicaSobreabordaje(Short v) { this.aplicaSobreabordaje = v; }
    
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
    public List<RegionAbordaje> getRegionAbordajeHasTablaAbordamientoList(){ return this.regionAbordajeHasTablaAbordamientoList; }
    public void setRegionAbordajeHasTablaAbordamientoList(List<RegionAbordaje> v){ this.regionAbordajeHasTablaAbordamientoList = v; }
    
    public List<TsuAbordamiento> getTsuAbordamientoHasTablaAbordamientoList(){ return this.tsuAbordamientoHasTablaAbordamientoList; }
    public void setTsuAbordamientoHasTablaAbordamientoList(List<TsuAbordamiento> v){ this.tsuAbordamientoHasTablaAbordamientoList = v; }
    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasTablaAbordamientoList(){ return this.detalleTablaTsuHasTablaAbordamientoList; }
    public void setDetalleTablaTsuHasTablaAbordamientoList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasTablaAbordamientoList = v; }
    
    public List<DetalleTablaComplementario> getDetalleTablaComplementarioHasTablaAbordamientoList(){ return this.detalleTablaComplementarioHasTablaAbordamientoList; }
    public void setDetalleTablaComplementarioHasTablaAbordamientoList(List<DetalleTablaComplementario> v){ this.detalleTablaComplementarioHasTablaAbordamientoList = v; }
    
    public List<Sobreabordaje> getSobreabordajeHasTablaAbordamientoList(){ return this.sobreabordajeHasTablaAbordamientoList; }
    public void setSobreabordajeHasTablaAbordamientoList(List<Sobreabordaje> v){ this.sobreabordajeHasTablaAbordamientoList = v; }
    
	// M2M <*>
    public List<ModeloAvion> getModeloAvionList() { return this.modeloAvionList; }
    public void setModeloAvionList(List<ModeloAvion>  v) { this.modeloAvionList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTablaAbordamiento).hashCode();
		hash += String.valueOf(idOrigenVuelo).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(codigoServicio).hashCode();
		hash += String.valueOf(revision).hashCode();
		hash += String.valueOf(todasRegiones).hashCode();
		hash += String.valueOf(todosModelosAvion).hashCode();
		hash += String.valueOf(aplicaTsu).hashCode();
		hash += String.valueOf(aplicaSobreabordaje).hashCode();
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
        if (!(o instanceof TablaAbordamiento)) {
            return false;
        }		
		TablaAbordamiento other = (TablaAbordamiento ) o;
		if (!Objects.equals(this.idTablaAbordamiento, other.idTablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.idOrigenVuelo, other.idOrigenVuelo)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.codigoServicio, other.codigoServicio)) { return false; }		
		if (!Objects.equals(this.revision, other.revision)) { return false; }		
		if (!Objects.equals(this.todasRegiones, other.todasRegiones)) { return false; }		
		if (!Objects.equals(this.todosModelosAvion, other.todosModelosAvion)) { return false; }		
		if (!Objects.equals(this.aplicaTsu, other.aplicaTsu)) { return false; }		
		if (!Objects.equals(this.aplicaSobreabordaje, other.aplicaSobreabordaje)) { return false; }		
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
		sb.append("TablaAbordamiento{");
		sb.append("idTablaAbordamiento" ).append("=").append(idTablaAbordamiento).append("|");
		sb.append("idOrigenVuelo" ).append("=").append(idOrigenVuelo).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("codigoServicio" ).append("=").append(codigoServicio).append("|");
		sb.append("revision" ).append("=").append(revision).append("|");
		sb.append("todasRegiones" ).append("=").append(todasRegiones).append("|");
		sb.append("todosModelosAvion" ).append("=").append(todosModelosAvion).append("|");
		sb.append("aplicaTsu" ).append("=").append(aplicaTsu).append("|");
		sb.append("aplicaSobreabordaje" ).append("=").append(aplicaSobreabordaje).append("|");
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
		sb.append(" ").append(idTablaAbordamiento).append(" ");

		return sb.toString().trim();
	}

}
