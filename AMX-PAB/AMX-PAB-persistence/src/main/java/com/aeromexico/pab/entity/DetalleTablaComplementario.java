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
 * Class for mapping JPA Entity of Table detalle_tabla_complementario.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "detalle_tabla_complementario")
@NamedQueries({
    @NamedQuery(name = "DetalleTablaComplementario.findAll", query = "SELECT d FROM DetalleTablaComplementario d")
    , @NamedQuery(name = "DetalleTablaComplementario.countAll", query = "SELECT COUNT(d) FROM DetalleTablaComplementario d")
    , @NamedQuery(name = "DetalleTablaComplementario.findByIdDetalleTablaComplementario", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.idDetalleTablaComplementario = :idDetalleTablaComplementario")
    , @NamedQuery(name = "DetalleTablaComplementario.findBytablaAbordamiento", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.tablaAbordamiento = :tablaAbordamiento")
    , @NamedQuery(name = "DetalleTablaComplementario.findByNombreComplementario", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.nombreComplementario = :nombreComplementario")
    , @NamedQuery(name = "DetalleTablaComplementario.findByPorcentaje", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.porcentaje = :porcentaje")
    , @NamedQuery(name = "DetalleTablaComplementario.findByAplicaPax", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.aplicaPax = :aplicaPax")
    , @NamedQuery(name = "DetalleTablaComplementario.findByAplicaCantidadVuelo", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.aplicaCantidadVuelo = :aplicaCantidadVuelo")
    , @NamedQuery(name = "DetalleTablaComplementario.findByAplicaMaterialExtra", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.aplicaMaterialExtra = :aplicaMaterialExtra")
    , @NamedQuery(name = "DetalleTablaComplementario.findByAplicaKit", query = "SELECT d FROM DetalleTablaComplementario d WHERE d.aplicaKit = :aplicaKit")
})
public class      DetalleTablaComplementario 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1948723430;
    
    /**
    * The 'id detalle tabla complementario' Maps to COLUMN 'id_detalle_tabla_complementario'
    */
    
    @Id
    @Column(name = "ID_DETALLE_TABLA_COMPLEMENTARIO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDetalleTablaComplementario;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @JoinColumn(name = "ID_TABLA_ABORDAMIENTO" , referencedColumnName = "ID_TABLA_ABORDAMIENTO")
    @ManyToOne(optional = false )
    private TablaAbordamiento tablaAbordamiento;
    
    /**
    * The 'nombre complementario' Maps to COLUMN 'nombre_complementario'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_COMPLEMENTARIO" , length=90, nullable=false)
    private String nombreComplementario;
    
    /**
    * The 'porcentaje' Maps to COLUMN 'porcentaje'
    */
    
    @Basic(optional = false)
    @Column(name = "PORCENTAJE" , nullable=false)
    private int porcentaje;
    
    /**
    * The 'aplica pax' Maps to COLUMN 'aplica_pax'
    */
    
    @Basic(optional = true)
    @Column(name = "APLICA_PAX" , nullable=true)
    private Short aplicaPax;
    
    /**
    * The 'aplica cantidad vuelo' Maps to COLUMN 'aplica_cantidad_vuelo'
    */
    
    @Basic(optional = true)
    @Column(name = "APLICA_CANTIDAD_VUELO" , nullable=true)
    private Short aplicaCantidadVuelo;
    
    /**
    * The 'aplica material extra' Maps to COLUMN 'aplica_material_extra'
    */
    
    @Basic(optional = true)
    @Column(name = "APLICA_MATERIAL_EXTRA" , nullable=true)
    private Short aplicaMaterialExtra;
    
    /**
    * The 'aplica kit' Maps to COLUMN 'aplica_kit'
    */
    
    @Basic(optional = true)
    @Column(name = "APLICA_KIT" , nullable=true)
    private Short aplicaKit;
    /** 
    * Map the relation to material_extra table where has id_detalle_tabla_complementario object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleTablaComplementario")
    private List<MaterialExtra> materialExtraHasDetalleTablaComplementarioList;
    
    /** 
    * Map the relation to cantidad_vuelo_complementario table where has id_detalle_complementario object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalleComplementario")
    private List<CantidadVueloComplementario> cantidadVueloComplementarioHasIdDetalleComplementarioList;
    
    /** 
    * Map the relation to pax_compementario table where has id_detalle_tabla_complementario object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleTablaComplementario")
    private List<PaxCompementario> paxCompementarioHasDetalleTablaComplementarioList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "CICLO_COMPLEMENTARIO",
               joinColumns        = {@JoinColumn(name = "ID_DETALLE_TABLA_COMPLEMENTARIO", referencedColumnName ="ID_DETALLE_TABLA_COMPLEMENTARIO")},
               inverseJoinColumns = {@JoinColumn(name = "ID_CICLO_NUMERO_TEMPORADA", referencedColumnName ="ID_PARAMETRO")}
               )
    private List<Parametro> parametroList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public DetalleTablaComplementario() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdDetalleTablaComplementario() { return this.idDetalleTablaComplementario;}
    public void setIdDetalleTablaComplementario(Integer v) { this.idDetalleTablaComplementario = v; }
    
    public TablaAbordamiento getTablaAbordamiento(){ return this.tablaAbordamiento ; }
    public void setTablaAbordamiento(TablaAbordamiento x){ this.tablaAbordamiento = x; }
    
    public String getNombreComplementario() { return this.nombreComplementario;}
    public void setNombreComplementario(String v) { this.nombreComplementario = v; }
    
    public int getPorcentaje() { return this.porcentaje;}
    public void setPorcentaje(int v) { this.porcentaje = v; }
    
    public Short getAplicaPax() { return this.aplicaPax;}
    public void setAplicaPax(Short v) { this.aplicaPax = v; }
    
    public Short getAplicaCantidadVuelo() { return this.aplicaCantidadVuelo;}
    public void setAplicaCantidadVuelo(Short v) { this.aplicaCantidadVuelo = v; }
    
    public Short getAplicaMaterialExtra() { return this.aplicaMaterialExtra;}
    public void setAplicaMaterialExtra(Short v) { this.aplicaMaterialExtra = v; }
    
    public Short getAplicaKit() { return this.aplicaKit;}
    public void setAplicaKit(Short v) { this.aplicaKit = v; }
    
    // O2M <*>    
    public List<MaterialExtra> getMaterialExtraHasDetalleTablaComplementarioList(){ return this.materialExtraHasDetalleTablaComplementarioList; }
    public void setMaterialExtraHasDetalleTablaComplementarioList(List<MaterialExtra> v){ this.materialExtraHasDetalleTablaComplementarioList = v; }
    
    public List<CantidadVueloComplementario> getCantidadVueloComplementarioHasIdDetalleComplementarioList(){ return this.cantidadVueloComplementarioHasIdDetalleComplementarioList; }
    public void setCantidadVueloComplementarioHasIdDetalleComplementarioList(List<CantidadVueloComplementario> v){ this.cantidadVueloComplementarioHasIdDetalleComplementarioList = v; }
    
    public List<PaxCompementario> getPaxCompementarioHasDetalleTablaComplementarioList(){ return this.paxCompementarioHasDetalleTablaComplementarioList; }
    public void setPaxCompementarioHasDetalleTablaComplementarioList(List<PaxCompementario> v){ this.paxCompementarioHasDetalleTablaComplementarioList = v; }
    
	// M2M <*>
    public List<Parametro> getParametroList() { return this.parametroList; }
    public void setParametroList(List<Parametro>  v) { this.parametroList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idDetalleTablaComplementario).hashCode();
		hash += String.valueOf(tablaAbordamiento).hashCode();
		hash += String.valueOf(nombreComplementario).hashCode();
		hash += String.valueOf(porcentaje).hashCode();
		hash += String.valueOf(aplicaPax).hashCode();
		hash += String.valueOf(aplicaCantidadVuelo).hashCode();
		hash += String.valueOf(aplicaMaterialExtra).hashCode();
		hash += String.valueOf(aplicaKit).hashCode();
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
        if (!(o instanceof DetalleTablaComplementario)) {
            return false;
        }		
		DetalleTablaComplementario other = (DetalleTablaComplementario ) o;
		if (!Objects.equals(this.idDetalleTablaComplementario, other.idDetalleTablaComplementario)) { return false; }		
		if (!Objects.equals(this.tablaAbordamiento, other.tablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.nombreComplementario, other.nombreComplementario)) { return false; }		
		if (!Objects.equals(this.porcentaje, other.porcentaje)) { return false; }		
		if (!Objects.equals(this.aplicaPax, other.aplicaPax)) { return false; }		
		if (!Objects.equals(this.aplicaCantidadVuelo, other.aplicaCantidadVuelo)) { return false; }		
		if (!Objects.equals(this.aplicaMaterialExtra, other.aplicaMaterialExtra)) { return false; }		
		if (!Objects.equals(this.aplicaKit, other.aplicaKit)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("DetalleTablaComplementario{");
		sb.append("idDetalleTablaComplementario" ).append("=").append(idDetalleTablaComplementario).append("|");
		sb.append("tablaAbordamiento" ).append("=").append(tablaAbordamiento).append("|");
		sb.append("nombreComplementario" ).append("=").append(nombreComplementario).append("|");
		sb.append("porcentaje" ).append("=").append(porcentaje).append("|");
		sb.append("aplicaPax" ).append("=").append(aplicaPax).append("|");
		sb.append("aplicaCantidadVuelo" ).append("=").append(aplicaCantidadVuelo).append("|");
		sb.append("aplicaMaterialExtra" ).append("=").append(aplicaMaterialExtra).append("|");
		sb.append("aplicaKit" ).append("=").append(aplicaKit).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idDetalleTablaComplementario).append(" ");

		return sb.toString().trim();
	}

}
