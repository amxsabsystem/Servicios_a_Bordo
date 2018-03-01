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
 * Class for mapping JPA Entity of Table material_extra.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "material_extra")
@NamedQueries({
    @NamedQuery(name = "MaterialExtra.findAll", query = "SELECT m FROM MaterialExtra m")
    , @NamedQuery(name = "MaterialExtra.countAll", query = "SELECT COUNT(m) FROM MaterialExtra m")
    , @NamedQuery(name = "MaterialExtra.findByIdMaterialExtra", query = "SELECT m FROM MaterialExtra m WHERE m.idMaterialExtra = :idMaterialExtra")
    , @NamedQuery(name = "MaterialExtra.findBydetalleTablaComplementario", query = "SELECT m FROM MaterialExtra m WHERE m.detalleTablaComplementario = :detalleTablaComplementario")
    , @NamedQuery(name = "MaterialExtra.findBykitMaster", query = "SELECT m FROM MaterialExtra m WHERE m.kitMaster = :kitMaster")
    , @NamedQuery(name = "MaterialExtra.findBymaterial", query = "SELECT m FROM MaterialExtra m WHERE m.material = :material")
    , @NamedQuery(name = "MaterialExtra.findBymaterialAmx", query = "SELECT m FROM MaterialExtra m WHERE m.materialAmx = :materialAmx")
    , @NamedQuery(name = "MaterialExtra.findBymaterialMontaje", query = "SELECT m FROM MaterialExtra m WHERE m.materialMontaje = :materialMontaje")
    , @NamedQuery(name = "MaterialExtra.findBymaterialMontajeAmx", query = "SELECT m FROM MaterialExtra m WHERE m.materialMontajeAmx = :materialMontajeAmx")
    , @NamedQuery(name = "MaterialExtra.findByInstrucciones", query = "SELECT m FROM MaterialExtra m WHERE m.instrucciones = :instrucciones")
    , @NamedQuery(name = "MaterialExtra.findByMaterialComisariato", query = "SELECT m FROM MaterialExtra m WHERE m.materialComisariato = :materialComisariato")
    , @NamedQuery(name = "MaterialExtra.findByMaterialComisariatoMontaje", query = "SELECT m FROM MaterialExtra m WHERE m.materialComisariatoMontaje = :materialComisariatoMontaje")
})
public class      MaterialExtra 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1276468488;
    
    /**
    * The 'id material extra' Maps to COLUMN 'id_material_extra'
    */
    
    @Id
    @Column(name = "ID_MATERIAL_EXTRA" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMaterialExtra;
    
    /**
    * The 'id detalle tabla complementario' Maps to COLUMN 'id_detalle_tabla_complementario'
    */
    
    @JoinColumn(name = "ID_DETALLE_TABLA_COMPLEMENTARIO" , referencedColumnName = "ID_DETALLE_TABLA_COMPLEMENTARIO")
    @ManyToOne(optional = false )
    private DetalleTablaComplementario detalleTablaComplementario;
    
    /**
    * The 'cve kit master' Maps to COLUMN 'cve_kit_master'
    */
    
    @JoinColumn(name = "CVE_KIT_MASTER" , referencedColumnName = "CVE_KIT_MASTER")
    @ManyToOne(optional = true )
    private KitMaster kitMaster;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = true )
    private Material material;
    
    /**
    * The 'material amx' Maps to COLUMN 'material_amx'
    */
    
    @JoinColumn(name = "MATERIAL_AMX" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = true )
    private Material materialAmx;
    
    /**
    * The 'material montaje' Maps to COLUMN 'material_montaje'
    */
    
    @JoinColumn(name = "MATERIAL_MONTAJE" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = true )
    private Material materialMontaje;
    
    /**
    * The 'material montaje amx' Maps to COLUMN 'material_montaje_amx'
    */
    
    @JoinColumn(name = "MATERIAL_MONTAJE_AMX" , referencedColumnName = "NUMERO_PARTE")
    @ManyToOne(optional = true )
    private Material materialMontajeAmx;
    
    /**
    * The 'instrucciones' Maps to COLUMN 'instrucciones'
    */
    
    @Basic(optional = true)
    @Column(name = "INSTRUCCIONES" , length=90, nullable=true)
    private String instrucciones;
    
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
    @Column(name = "MATERIAL_COMISARIATO_MONTAJE" , nullable=true)
    private Integer materialComisariatoMontaje;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public MaterialExtra() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdMaterialExtra() { return this.idMaterialExtra;}
    public void setIdMaterialExtra(Integer v) { this.idMaterialExtra = v; }
    
    public DetalleTablaComplementario getDetalleTablaComplementario(){ return this.detalleTablaComplementario ; }
    public void setDetalleTablaComplementario(DetalleTablaComplementario x){ this.detalleTablaComplementario = x; }
    
    public KitMaster getKitMaster(){ return this.kitMaster ; }
    public void setKitMaster(KitMaster x){ this.kitMaster = x; }
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public Material getMaterialAmx(){ return this.materialAmx ; }
    public void setMaterialAmx(Material x){ this.materialAmx = x; }
    
    public Material getMaterialMontaje(){ return this.materialMontaje ; }
    public void setMaterialMontaje(Material x){ this.materialMontaje = x; }
    
    public Material getMaterialMontajeAmx(){ return this.materialMontajeAmx ; }
    public void setMaterialMontajeAmx(Material x){ this.materialMontajeAmx = x; }
    
    public String getInstrucciones() { return this.instrucciones;}
    public void setInstrucciones(String v) { this.instrucciones = v; }
    
    public String getMaterialComisariato() { return this.materialComisariato;}
    public void setMaterialComisariato(String v) { this.materialComisariato = v; }
    
    public Integer getMaterialComisariatoMontaje() { return this.materialComisariatoMontaje;}
    public void setMaterialComisariatoMontaje(Integer v) { this.materialComisariatoMontaje = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idMaterialExtra).hashCode();
		hash += String.valueOf(detalleTablaComplementario).hashCode();
		hash += String.valueOf(kitMaster).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(materialAmx).hashCode();
		hash += String.valueOf(materialMontaje).hashCode();
		hash += String.valueOf(materialMontajeAmx).hashCode();
		hash += String.valueOf(instrucciones).hashCode();
		hash += String.valueOf(materialComisariato).hashCode();
		hash += String.valueOf(materialComisariatoMontaje).hashCode();
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
        if (!(o instanceof MaterialExtra)) {
            return false;
        }		
		MaterialExtra other = (MaterialExtra ) o;
		if (!Objects.equals(this.idMaterialExtra, other.idMaterialExtra)) { return false; }		
		if (!Objects.equals(this.detalleTablaComplementario, other.detalleTablaComplementario)) { return false; }		
		if (!Objects.equals(this.kitMaster, other.kitMaster)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.materialAmx, other.materialAmx)) { return false; }		
		if (!Objects.equals(this.materialMontaje, other.materialMontaje)) { return false; }		
		if (!Objects.equals(this.materialMontajeAmx, other.materialMontajeAmx)) { return false; }		
		if (!Objects.equals(this.instrucciones, other.instrucciones)) { return false; }		
		if (!Objects.equals(this.materialComisariato, other.materialComisariato)) { return false; }		
		if (!Objects.equals(this.materialComisariatoMontaje, other.materialComisariatoMontaje)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("MaterialExtra{");
		sb.append("idMaterialExtra" ).append("=").append(idMaterialExtra).append("|");
		sb.append("detalleTablaComplementario" ).append("=").append(detalleTablaComplementario).append("|");
		sb.append("kitMaster" ).append("=").append(kitMaster).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("materialAmx" ).append("=").append(materialAmx).append("|");
		sb.append("materialMontaje" ).append("=").append(materialMontaje).append("|");
		sb.append("materialMontajeAmx" ).append("=").append(materialMontajeAmx).append("|");
		sb.append("instrucciones" ).append("=").append(instrucciones).append("|");
		sb.append("materialComisariato" ).append("=").append(materialComisariato).append("|");
		sb.append("materialComisariatoMontaje" ).append("=").append(materialComisariatoMontaje).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idMaterialExtra).append(" ");

		return sb.toString().trim();
	}

}
