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
 * Class for mapping JPA Entity of Table modelo_avion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "modelo_avion")
@NamedQueries({
    @NamedQuery(name = "ModeloAvion.findAll", query = "SELECT m FROM ModeloAvion m")
    , @NamedQuery(name = "ModeloAvion.countAll", query = "SELECT COUNT(m) FROM ModeloAvion m")
    , @NamedQuery(name = "ModeloAvion.findByIdModeloAvion", query = "SELECT m FROM ModeloAvion m WHERE m.idModeloAvion = :idModeloAvion")
    , @NamedQuery(name = "ModeloAvion.findByModelo", query = "SELECT m FROM ModeloAvion m WHERE m.modelo = :modelo")
    , @NamedQuery(name = "ModeloAvion.findByDescripcion", query = "SELECT m FROM ModeloAvion m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "ModeloAvion.findByidTipoCabina", query = "SELECT m FROM ModeloAvion m WHERE m.idTipoCabina = :idTipoCabina")
    , @NamedQuery(name = "ModeloAvion.findBycompania", query = "SELECT m FROM ModeloAvion m WHERE m.compania = :compania")
    , @NamedQuery(name = "ModeloAvion.findByidFabricante", query = "SELECT m FROM ModeloAvion m WHERE m.idFabricante = :idFabricante")
    , @NamedQuery(name = "ModeloAvion.findByStatus", query = "SELECT m FROM ModeloAvion m WHERE m.status = :status")
    , @NamedQuery(name = "ModeloAvion.findByUsuarioCreo", query = "SELECT m FROM ModeloAvion m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "ModeloAvion.findByFechaCreo", query = "SELECT m FROM ModeloAvion m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "ModeloAvion.findByUsuarioModifico", query = "SELECT m FROM ModeloAvion m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "ModeloAvion.findByFechaModifico", query = "SELECT m FROM ModeloAvion m WHERE m.fechaModifico = :fechaModifico")
})
public class      ModeloAvion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 737807514;
    
    /**
    * The 'id modelo avion' Maps to COLUMN 'id_modelo_avion'
    */
    
    @Id
    @Column(name = "ID_MODELO_AVION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idModeloAvion;
    
    /**
    * The 'modelo' Maps to COLUMN 'modelo'
    */
    
    @Basic(optional = false)
    @Column(name = "MODELO" , length=50, nullable=false)
    private String modelo;
    
    /**
    * The 'descripcion' Maps to COLUMN 'descripcion'
    */
    
    @Basic(optional = true)
    @Column(name = "DESCRIPCION" , length=200, nullable=true)
    private String descripcion;
    
    /**
    * The 'id tipo cabina' Maps to COLUMN 'id_tipo_cabina'
    */
    
    @JoinColumn(name = "ID_TIPO_CABINA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idTipoCabina;
    
    /**
    * The 'id compania' Maps to COLUMN 'id_compania'
    */
    
    @JoinColumn(name = "ID_COMPANIA" , referencedColumnName = "ID_COMPANIA")
    @ManyToOne(optional = false )
    private Compania compania;
    
    /**
    * The 'id fabricante' Maps to COLUMN 'id_fabricante'
    */
    
    @JoinColumn(name = "ID_FABRICANTE" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idFabricante;
    
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
    * Map the relation to Asignacion_Servicio table where has id_modelo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloAvion")
    private List<AsignacionServicio> asignacionServicioHasModeloAvionList;
    
    /** 
    * Map the relation to cantidad_vuelo_complementario table where has id_modelo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloAvion")
    private List<CantidadVueloComplementario> cantidadVueloComplementarioHasModeloAvionList;
    
    /** 
    * Map the relation to tipo_equipo_avion table where has id_modelo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloAvion")
    private List<TipoEquipoAvion> tipoEquipoAvionHasModeloAvionList;
    
    /** 
    * Map the relation to pax_compementario table where has id_modelo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloAvion")
    private List<PaxCompementario> paxCompementarioHasModeloAvionList;
    
    /** 
    * Map the relation to acomodo table where has id_modelo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloAvion")
    private List<Acomodo> acomodoHasModeloAvionList;
    
    /** 
    * Map the relation to grafico table where has id_modelo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloAvion")
    private List<Grafico> graficoHasModeloAvionList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "DETALLE_MODELO_ABORDAJE",
               joinColumns        = {@JoinColumn(name = "ID_MODELO_AVION", referencedColumnName ="ID_MODELO_AVION")},
               inverseJoinColumns = {@JoinColumn(name = "ID_TABLA_ABORDAMIENTO", referencedColumnName ="ID_TABLA_ABORDAMIENTO")}
               )
    private List<TablaAbordamiento> tablaAbordamientoList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ModeloAvion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdModeloAvion() { return this.idModeloAvion;}
    public void setIdModeloAvion(Integer v) { this.idModeloAvion = v; }
    
    public String getModelo() { return this.modelo;}
    public void setModelo(String v) { this.modelo = v; }
    
    public String getDescripcion() { return this.descripcion;}
    public void setDescripcion(String v) { this.descripcion = v; }
    
    public Parametro getIdTipoCabina(){ return this.idTipoCabina ; }
    public void setIdTipoCabina(Parametro x){ this.idTipoCabina = x; }
    
    public Compania getCompania(){ return this.compania ; }
    public void setCompania(Compania x){ this.compania = x; }
    
    public Parametro getIdFabricante(){ return this.idFabricante ; }
    public void setIdFabricante(Parametro x){ this.idFabricante = x; }
    
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
    public List<AsignacionServicio> getAsignacionServicioHasModeloAvionList(){ return this.asignacionServicioHasModeloAvionList; }
    public void setAsignacionServicioHasModeloAvionList(List<AsignacionServicio> v){ this.asignacionServicioHasModeloAvionList = v; }
    
    public List<CantidadVueloComplementario> getCantidadVueloComplementarioHasModeloAvionList(){ return this.cantidadVueloComplementarioHasModeloAvionList; }
    public void setCantidadVueloComplementarioHasModeloAvionList(List<CantidadVueloComplementario> v){ this.cantidadVueloComplementarioHasModeloAvionList = v; }
    
    public List<TipoEquipoAvion> getTipoEquipoAvionHasModeloAvionList(){ return this.tipoEquipoAvionHasModeloAvionList; }
    public void setTipoEquipoAvionHasModeloAvionList(List<TipoEquipoAvion> v){ this.tipoEquipoAvionHasModeloAvionList = v; }
    
    public List<PaxCompementario> getPaxCompementarioHasModeloAvionList(){ return this.paxCompementarioHasModeloAvionList; }
    public void setPaxCompementarioHasModeloAvionList(List<PaxCompementario> v){ this.paxCompementarioHasModeloAvionList = v; }
    
    public List<Acomodo> getAcomodoHasModeloAvionList(){ return this.acomodoHasModeloAvionList; }
    public void setAcomodoHasModeloAvionList(List<Acomodo> v){ this.acomodoHasModeloAvionList = v; }
    
    public List<Grafico> getGraficoHasModeloAvionList(){ return this.graficoHasModeloAvionList; }
    public void setGraficoHasModeloAvionList(List<Grafico> v){ this.graficoHasModeloAvionList = v; }
    
	// M2M <*>
    public List<TablaAbordamiento> getTablaAbordamientoList() { return this.tablaAbordamientoList; }
    public void setTablaAbordamientoList(List<TablaAbordamiento>  v) { this.tablaAbordamientoList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idModeloAvion).hashCode();
		hash += String.valueOf(modelo).hashCode();
		hash += String.valueOf(descripcion).hashCode();
		hash += String.valueOf(idTipoCabina).hashCode();
		hash += String.valueOf(compania).hashCode();
		hash += String.valueOf(idFabricante).hashCode();
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
        if (!(o instanceof ModeloAvion)) {
            return false;
        }		
		ModeloAvion other = (ModeloAvion ) o;
		if (!Objects.equals(this.idModeloAvion, other.idModeloAvion)) { return false; }		
		if (!Objects.equals(this.modelo, other.modelo)) { return false; }		
		if (!Objects.equals(this.descripcion, other.descripcion)) { return false; }		
		if (!Objects.equals(this.idTipoCabina, other.idTipoCabina)) { return false; }		
		if (!Objects.equals(this.compania, other.compania)) { return false; }		
		if (!Objects.equals(this.idFabricante, other.idFabricante)) { return false; }		
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
		sb.append("ModeloAvion{");
		sb.append("idModeloAvion" ).append("=").append(idModeloAvion).append("|");
		sb.append("modelo" ).append("=").append(modelo).append("|");
		sb.append("descripcion" ).append("=").append(descripcion).append("|");
		sb.append("idTipoCabina" ).append("=").append(idTipoCabina).append("|");
		sb.append("compania" ).append("=").append(compania).append("|");
		sb.append("idFabricante" ).append("=").append(idFabricante).append("|");
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
		sb.append(" ").append(idModeloAvion).append(" ");

		return sb.toString().trim();
	}

}
