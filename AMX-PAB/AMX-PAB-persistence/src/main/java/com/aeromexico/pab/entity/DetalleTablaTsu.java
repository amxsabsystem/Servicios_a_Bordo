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
 * Class for mapping JPA Entity of Table detalle_tabla_tsu.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "detalle_tabla_tsu")
@NamedQueries({
    @NamedQuery(name = "DetalleTablaTsu.findAll", query = "SELECT d FROM DetalleTablaTsu d")
    , @NamedQuery(name = "DetalleTablaTsu.countAll", query = "SELECT COUNT(d) FROM DetalleTablaTsu d")
    , @NamedQuery(name = "DetalleTablaTsu.findByIdDetalleTablaTsu", query = "SELECT d FROM DetalleTablaTsu d WHERE d.idDetalleTablaTsu = :idDetalleTablaTsu")
    , @NamedQuery(name = "DetalleTablaTsu.findBytablaAbordamiento", query = "SELECT d FROM DetalleTablaTsu d WHERE d.tablaAbordamiento = :tablaAbordamiento")
    , @NamedQuery(name = "DetalleTablaTsu.findBytsu", query = "SELECT d FROM DetalleTablaTsu d WHERE d.tsu = :tsu")
    , @NamedQuery(name = "DetalleTablaTsu.findBymaterial", query = "SELECT d FROM DetalleTablaTsu d WHERE d.material = :material")
    , @NamedQuery(name = "DetalleTablaTsu.findBymaterialAmx", query = "SELECT d FROM DetalleTablaTsu d WHERE d.materialAmx = :materialAmx")
    , @NamedQuery(name = "DetalleTablaTsu.findBymaterialMontaje", query = "SELECT d FROM DetalleTablaTsu d WHERE d.materialMontaje = :materialMontaje")
    , @NamedQuery(name = "DetalleTablaTsu.findBymaterialMontajeAmx", query = "SELECT d FROM DetalleTablaTsu d WHERE d.materialMontajeAmx = :materialMontajeAmx")
    , @NamedQuery(name = "DetalleTablaTsu.findByNombrePlatillo", query = "SELECT d FROM DetalleTablaTsu d WHERE d.nombrePlatillo = :nombrePlatillo")
    , @NamedQuery(name = "DetalleTablaTsu.findByPorcentaje", query = "SELECT d FROM DetalleTablaTsu d WHERE d.porcentaje = :porcentaje")
    , @NamedQuery(name = "DetalleTablaTsu.findByMaterialComisariato", query = "SELECT d FROM DetalleTablaTsu d WHERE d.materialComisariato = :materialComisariato")
    , @NamedQuery(name = "DetalleTablaTsu.findByMaterialComisariatoMontaje", query = "SELECT d FROM DetalleTablaTsu d WHERE d.materialComisariatoMontaje = :materialComisariatoMontaje")
    , @NamedQuery(name = "DetalleTablaTsu.findByInstrucciones", query = "SELECT d FROM DetalleTablaTsu d WHERE d.instrucciones = :instrucciones")
    , @NamedQuery(name = "DetalleTablaTsu.findBydetalleTsuAbordajetsuPorcentaje", query = "SELECT d FROM DetalleTablaTsu d WHERE d.detalleTsuAbordajetsuPorcentaje = :detalleTsuAbordajetsuPorcentaje")
})
public class      DetalleTablaTsu 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1705572583;
    
    /**
    * The 'id detalle tabla tsu' Maps to COLUMN 'id_detalle_tabla_tsu'
    */
    
    @Id
    @Column(name = "ID_DETALLE_TABLA_TSU" , nullable=false  )
    private Integer idDetalleTablaTsu;
    
    /**
    * The 'id tabla abordamiento' Maps to COLUMN 'id_tabla_abordamiento'
    */
    
    @JoinColumn(name = "ID_TABLA_ABORDAMIENTO" , referencedColumnName = "ID_TABLA_ABORDAMIENTO")
    @ManyToOne(optional = false )
    private TablaAbordamiento tablaAbordamiento;
    
    /**
    * The 'id tsu' Maps to COLUMN 'id_tsu'
    */
    
    @JoinColumn(name = "ID_TSU" , referencedColumnName = "ID_TSU")
    @ManyToOne(optional = false )
    private Tsu tsu;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material material;
    
    /**
    * The 'material amx' Maps to COLUMN 'material_amx'
    */
    
    @JoinColumn(name = "MATERIAL_AMX" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material materialAmx;
    
    /**
    * The 'material montaje' Maps to COLUMN 'material_montaje'
    */
    
    @JoinColumn(name = "MATERIAL_MONTAJE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material materialMontaje;
    
    /**
    * The 'material montaje amx' Maps to COLUMN 'material_montaje_amx'
    */
    
    @JoinColumn(name = "MATERIAL_MONTAJE_AMX" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = false )
    private Material materialMontajeAmx;
    
    /**
    * The 'nombre platillo' Maps to COLUMN 'nombre_platillo'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_PLATILLO" , length=90, nullable=false)
    private String nombrePlatillo;
    
    /**
    * The 'porcentaje' Maps to COLUMN 'porcentaje'
    */
    
    @Basic(optional = false)
    @Column(name = "PORCENTAJE" , nullable=false)
    private int porcentaje;
    
    /**
    * The 'material comisariato' Maps to COLUMN 'material_comisariato'
    */
    
    @Basic(optional = true)
    @Column(name = "MATERIAL_COMISARIATO" , length=50, nullable=true)
    private String materialComisariato;
    
    /**
    * The 'material comisariato montaje' Maps to COLUMN 'material_comisariato_montaje'
    */
    
    @Basic(optional = true)
    @Column(name = "MATERIAL_COMISARIATO_MONTAJE" , length=50, nullable=true)
    private String materialComisariatoMontaje;
    
    /**
    * The 'instrucciones' Maps to COLUMN 'instrucciones'
    */
    
    @Basic(optional = true)
    @Column(name = "INSTRUCCIONES" , length=100, nullable=true)
    private String instrucciones;
    
    /**
    * The 'detalle tsu abordajetsu porcentaje' Maps to COLUMN 'detalle_tsu_abordajetsu_porcentaje'
    */
    
    @JoinColumn(name = "DETALLE_TSU_ABORDAJETSU_PORCENTAJE" , referencedColumnName = "ID_TSU_ABORDAMIENTO")
    @ManyToOne(optional = true )
    private TsuAbordamiento detalleTsuAbordajetsuPorcentaje;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "TSU_CICLO",
               joinColumns        = {@JoinColumn(name = "ID_DETALLE_TABLA_TSU", referencedColumnName ="ID_DETALLE_TABLA_TSU")},
               inverseJoinColumns = {@JoinColumn(name = "ID_CICLO_NUMERO_TEMPORADA", referencedColumnName ="ID_PARAMETRO")}
               )
    private List<Parametro> parametroList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public DetalleTablaTsu() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdDetalleTablaTsu() { return this.idDetalleTablaTsu;}
    public void setIdDetalleTablaTsu(Integer v) { this.idDetalleTablaTsu = v; }
    
    public TablaAbordamiento getTablaAbordamiento(){ return this.tablaAbordamiento ; }
    public void setTablaAbordamiento(TablaAbordamiento x){ this.tablaAbordamiento = x; }
    
    public Tsu getTsu(){ return this.tsu ; }
    public void setTsu(Tsu x){ this.tsu = x; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public Material getMaterialAmx(){ return this.materialAmx ; }
    public void setMaterialAmx(Material x){ this.materialAmx = x; }
    
    public Material getMaterialMontaje(){ return this.materialMontaje ; }
    public void setMaterialMontaje(Material x){ this.materialMontaje = x; }
    
    public Material getMaterialMontajeAmx(){ return this.materialMontajeAmx ; }
    public void setMaterialMontajeAmx(Material x){ this.materialMontajeAmx = x; }
    
    public String getNombrePlatillo() { return this.nombrePlatillo;}
    public void setNombrePlatillo(String v) { this.nombrePlatillo = v; }
    
    public int getPorcentaje() { return this.porcentaje;}
    public void setPorcentaje(int v) { this.porcentaje = v; }
    
    public String getMaterialComisariato() { return this.materialComisariato;}
    public void setMaterialComisariato(String v) { this.materialComisariato = v; }
    
    public String getMaterialComisariatoMontaje() { return this.materialComisariatoMontaje;}
    public void setMaterialComisariatoMontaje(String v) { this.materialComisariatoMontaje = v; }
    
    public String getInstrucciones() { return this.instrucciones;}
    public void setInstrucciones(String v) { this.instrucciones = v; }
    
    public TsuAbordamiento getDetalleTsuAbordajetsuPorcentaje(){ return this.detalleTsuAbordajetsuPorcentaje ; }
    public void setDetalleTsuAbordajetsuPorcentaje(TsuAbordamiento x){ this.detalleTsuAbordajetsuPorcentaje = x; }
    
    // O2M <*>    
	// M2M <*>
    public List<Parametro> getParametroList() { return this.parametroList; }
    public void setParametroList(List<Parametro>  v) { this.parametroList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idDetalleTablaTsu).hashCode();
		hash += String.valueOf(tablaAbordamiento).hashCode();
		hash += String.valueOf(tsu).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(materialAmx).hashCode();
		hash += String.valueOf(materialMontaje).hashCode();
		hash += String.valueOf(materialMontajeAmx).hashCode();
		hash += String.valueOf(nombrePlatillo).hashCode();
		hash += String.valueOf(porcentaje).hashCode();
		hash += String.valueOf(materialComisariato).hashCode();
		hash += String.valueOf(materialComisariatoMontaje).hashCode();
		hash += String.valueOf(instrucciones).hashCode();
		hash += String.valueOf(detalleTsuAbordajetsuPorcentaje).hashCode();
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
        if (!(o instanceof DetalleTablaTsu)) {
            return false;
        }		
		DetalleTablaTsu other = (DetalleTablaTsu ) o;
		if (!Objects.equals(this.idDetalleTablaTsu, other.idDetalleTablaTsu)) { return false; }		
		if (!Objects.equals(this.tablaAbordamiento, other.tablaAbordamiento)) { return false; }		
		if (!Objects.equals(this.tsu, other.tsu)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.materialAmx, other.materialAmx)) { return false; }		
		if (!Objects.equals(this.materialMontaje, other.materialMontaje)) { return false; }		
		if (!Objects.equals(this.materialMontajeAmx, other.materialMontajeAmx)) { return false; }		
		if (!Objects.equals(this.nombrePlatillo, other.nombrePlatillo)) { return false; }		
		if (!Objects.equals(this.porcentaje, other.porcentaje)) { return false; }		
		if (!Objects.equals(this.materialComisariato, other.materialComisariato)) { return false; }		
		if (!Objects.equals(this.materialComisariatoMontaje, other.materialComisariatoMontaje)) { return false; }		
		if (!Objects.equals(this.instrucciones, other.instrucciones)) { return false; }		
		if (!Objects.equals(this.detalleTsuAbordajetsuPorcentaje, other.detalleTsuAbordajetsuPorcentaje)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("DetalleTablaTsu{");
		sb.append("idDetalleTablaTsu" ).append("=").append(idDetalleTablaTsu).append("|");
		sb.append("tablaAbordamiento" ).append("=").append(tablaAbordamiento).append("|");
		sb.append("tsu" ).append("=").append(tsu).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("materialAmx" ).append("=").append(materialAmx).append("|");
		sb.append("materialMontaje" ).append("=").append(materialMontaje).append("|");
		sb.append("materialMontajeAmx" ).append("=").append(materialMontajeAmx).append("|");
		sb.append("nombrePlatillo" ).append("=").append(nombrePlatillo).append("|");
		sb.append("porcentaje" ).append("=").append(porcentaje).append("|");
		sb.append("materialComisariato" ).append("=").append(materialComisariato).append("|");
		sb.append("materialComisariatoMontaje" ).append("=").append(materialComisariatoMontaje).append("|");
		sb.append("instrucciones" ).append("=").append(instrucciones).append("|");
		sb.append("detalleTsuAbordajetsuPorcentaje" ).append("=").append(detalleTsuAbordajetsuPorcentaje).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idDetalleTablaTsu).append(" ");

		return sb.toString().trim();
	}

}
