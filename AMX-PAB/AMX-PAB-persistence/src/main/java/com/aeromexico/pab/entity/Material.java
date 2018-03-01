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
 * Class for mapping JPA Entity of Table Material.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Material")
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m")
    , @NamedQuery(name = "Material.countAll", query = "SELECT COUNT(m) FROM Material m")
    , @NamedQuery(name = "Material.findByNumeroParte", query = "SELECT m FROM Material m WHERE m.numeroParte = :numeroParte")
    , @NamedQuery(name = "Material.findBytipoAbastecimiento", query = "SELECT m FROM Material m WHERE m.tipoAbastecimiento = :tipoAbastecimiento")
    , @NamedQuery(name = "Material.findBycategoriaMaterial", query = "SELECT m FROM Material m WHERE m.categoriaMaterial = :categoriaMaterial")
    , @NamedQuery(name = "Material.findByDescripcionEs", query = "SELECT m FROM Material m WHERE m.descripcionEs = :descripcionEs")
    , @NamedQuery(name = "Material.findByDescripcionEn", query = "SELECT m FROM Material m WHERE m.descripcionEn = :descripcionEn")
    , @NamedQuery(name = "Material.findByUrlMultimedia", query = "SELECT m FROM Material m WHERE m.urlMultimedia = :urlMultimedia")
    , @NamedQuery(name = "Material.findByMimeType", query = "SELECT m FROM Material m WHERE m.mimeType = :mimeType")
    , @NamedQuery(name = "Material.findByPeso", query = "SELECT m FROM Material m WHERE m.peso = :peso")
    , @NamedQuery(name = "Material.findByStatus", query = "SELECT m FROM Material m WHERE m.status = :status")
    , @NamedQuery(name = "Material.findByUsuarioCreo", query = "SELECT m FROM Material m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Material.findByFechaCreo", query = "SELECT m FROM Material m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Material.findByUsuarioModifico", query = "SELECT m FROM Material m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Material.findByFechaModifico", query = "SELECT m FROM Material m WHERE m.fechaModifico = :fechaModifico")
})
public class      Material 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 363710791;
    
    /**
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @Id
    @Column(name = "NUMERO_PARTE" , length=50, nullable=false  )
    private String numeroParte;
    
    /**
    * The 'tipo abastecimiento' Maps to COLUMN 'tipo_abastecimiento'
    */
    
    @JoinColumn(name = "TIPO_ABASTECIMIENTO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro tipoAbastecimiento;
    
    /**
    * The 'id categoria material' Maps to COLUMN 'id_categoria_material'
    */
    
    @JoinColumn(name = "ID_CATEGORIA_MATERIAL" , referencedColumnName = "ID_CATEGORIA_MATERIAL")
    @ManyToOne(optional = false )
    private CategoriaMaterial categoriaMaterial;
    
    /**
    * The 'descripcion es' Maps to COLUMN 'descripcion_es'
    */
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_ES" , length=255, nullable=false)
    private String descripcionEs;
    
    /**
    * The 'descripcion en' Maps to COLUMN 'descripcion_en'
    */
    
    @Basic(optional = true)
    @Column(name = "DESCRIPCION_EN" , length=255, nullable=true)
    private String descripcionEn;
    
    /**
    * The 'url multimedia' Maps to COLUMN 'url_multimedia'
    */
    
    @Basic(optional = true)
    @Column(name = "URL_MULTIMEDIA" , length=255, nullable=true)
    private String urlMultimedia;
    
    /**
    * The 'mime type' Maps to COLUMN 'mime_type'
    */
    
    @Basic(optional = true)
    @Column(name = "MIME_TYPE" , length=64, nullable=true)
    private String mimeType;
    
    /**
    * The 'peso' Maps to COLUMN 'peso'
    */
    
    @Basic(optional = false)
    @Column(name = "PESO" , nullable=false)
    private double peso;
    
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
    * Map the relation to equipamiento_semifijo table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<EquipamientoSemifijo> equipamientoSemifijoHasMaterialList;
    
    /** 
    * Map the relation to material_extra table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<MaterialExtra> materialExtraHasMaterialList;
    
    /** 
    * Map the relation to material_extra table where has material_montaje object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialMontaje")
    private List<MaterialExtra> materialExtraHasMaterialMontajeList;
    
    /** 
    * Map the relation to material_extra table where has material_amx object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialAmx")
    private List<MaterialExtra> materialExtraHasMaterialAmxList;
    
    /** 
    * Map the relation to material_extra table where has material_montaje_amx object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialMontajeAmx")
    private List<MaterialExtra> materialExtraHasMaterialMontajeAmxList;
    
    /** 
    * Map the relation to avion_audifono table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<AvionAudifono> avionAudifonoHasMaterialList;
    
    /** 
    * Map the relation to detalle_tabla_tsu table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<DetalleTablaTsu> detalleTablaTsuHasMaterialList;
    
    /** 
    * Map the relation to detalle_tabla_tsu table where has material_montaje object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialMontaje")
    private List<DetalleTablaTsu> detalleTablaTsuHasMaterialMontajeList;
    
    /** 
    * Map the relation to detalle_tabla_tsu table where has material_amx object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialAmx")
    private List<DetalleTablaTsu> detalleTablaTsuHasMaterialAmxList;
    
    /** 
    * Map the relation to detalle_tabla_tsu table where has material_montaje_amx object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialMontajeAmx")
    private List<DetalleTablaTsu> detalleTablaTsuHasMaterialMontajeAmxList;
    
    /** 
    * Map the relation to Material_TSU table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<MaterialTsu> materialTsuHasMaterialList;
    
    /** 
    * Map the relation to matriz_detalle table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<MatrizDetalle> matrizDetalleHasMaterialList;
    
    /** 
    * Map the relation to sobreabordaje table where has numero_parte object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<Sobreabordaje> sobreabordajeHasMaterialList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Material() {
    }
    
    /**
     * Getters and Setters
     */
    public String getNumeroParte() { return this.numeroParte;}
    public void setNumeroParte(String v) { this.numeroParte = v; }
    
    public Parametro getTipoAbastecimiento(){ return this.tipoAbastecimiento ; }
    public void setTipoAbastecimiento(Parametro x){ this.tipoAbastecimiento = x; }
    
    public CategoriaMaterial getCategoriaMaterial(){ return this.categoriaMaterial ; }
    public void setCategoriaMaterial(CategoriaMaterial x){ this.categoriaMaterial = x; }
    
    public String getDescripcionEs() { return this.descripcionEs;}
    public void setDescripcionEs(String v) { this.descripcionEs = v; }
    
    public String getDescripcionEn() { return this.descripcionEn;}
    public void setDescripcionEn(String v) { this.descripcionEn = v; }
    
    public String getUrlMultimedia() { return this.urlMultimedia;}
    public void setUrlMultimedia(String v) { this.urlMultimedia = v; }
    
    public String getMimeType() { return this.mimeType;}
    public void setMimeType(String v) { this.mimeType = v; }
    
    public double getPeso() { return this.peso;}
    public void setPeso(double v) { this.peso = v; }
    
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
    public List<EquipamientoSemifijo> getEquipamientoSemifijoHasMaterialList(){ return this.equipamientoSemifijoHasMaterialList; }
    public void setEquipamientoSemifijoHasMaterialList(List<EquipamientoSemifijo> v){ this.equipamientoSemifijoHasMaterialList = v; }
    
    public List<MaterialExtra> getMaterialExtraHasMaterialList(){ return this.materialExtraHasMaterialList; }
    public void setMaterialExtraHasMaterialList(List<MaterialExtra> v){ this.materialExtraHasMaterialList = v; }
    
    public List<MaterialExtra> getMaterialExtraHasMaterialMontajeList(){ return this.materialExtraHasMaterialMontajeList; }
    public void setMaterialExtraHasMaterialMontajeList(List<MaterialExtra> v){ this.materialExtraHasMaterialMontajeList = v; }
    
    public List<MaterialExtra> getMaterialExtraHasMaterialAmxList(){ return this.materialExtraHasMaterialAmxList; }
    public void setMaterialExtraHasMaterialAmxList(List<MaterialExtra> v){ this.materialExtraHasMaterialAmxList = v; }
    
    public List<MaterialExtra> getMaterialExtraHasMaterialMontajeAmxList(){ return this.materialExtraHasMaterialMontajeAmxList; }
    public void setMaterialExtraHasMaterialMontajeAmxList(List<MaterialExtra> v){ this.materialExtraHasMaterialMontajeAmxList = v; }
    
    public List<AvionAudifono> getAvionAudifonoHasMaterialList(){ return this.avionAudifonoHasMaterialList; }
    public void setAvionAudifonoHasMaterialList(List<AvionAudifono> v){ this.avionAudifonoHasMaterialList = v; }
    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasMaterialList(){ return this.detalleTablaTsuHasMaterialList; }
    public void setDetalleTablaTsuHasMaterialList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasMaterialList = v; }
    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasMaterialMontajeList(){ return this.detalleTablaTsuHasMaterialMontajeList; }
    public void setDetalleTablaTsuHasMaterialMontajeList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasMaterialMontajeList = v; }
    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasMaterialAmxList(){ return this.detalleTablaTsuHasMaterialAmxList; }
    public void setDetalleTablaTsuHasMaterialAmxList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasMaterialAmxList = v; }
    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasMaterialMontajeAmxList(){ return this.detalleTablaTsuHasMaterialMontajeAmxList; }
    public void setDetalleTablaTsuHasMaterialMontajeAmxList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasMaterialMontajeAmxList = v; }
    
    public List<MaterialTsu> getMaterialTsuHasMaterialList(){ return this.materialTsuHasMaterialList; }
    public void setMaterialTsuHasMaterialList(List<MaterialTsu> v){ this.materialTsuHasMaterialList = v; }
    
    public List<MatrizDetalle> getMatrizDetalleHasMaterialList(){ return this.matrizDetalleHasMaterialList; }
    public void setMatrizDetalleHasMaterialList(List<MatrizDetalle> v){ this.matrizDetalleHasMaterialList = v; }
    
    public List<Sobreabordaje> getSobreabordajeHasMaterialList(){ return this.sobreabordajeHasMaterialList; }
    public void setSobreabordajeHasMaterialList(List<Sobreabordaje> v){ this.sobreabordajeHasMaterialList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(numeroParte).hashCode();
		hash += String.valueOf(tipoAbastecimiento).hashCode();
		hash += String.valueOf(categoriaMaterial).hashCode();
		hash += String.valueOf(descripcionEs).hashCode();
		hash += String.valueOf(descripcionEn).hashCode();
		hash += String.valueOf(urlMultimedia).hashCode();
		hash += String.valueOf(mimeType).hashCode();
		hash += String.valueOf(peso).hashCode();
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
        if (!(o instanceof Material)) {
            return false;
        }		
		Material other = (Material ) o;
		if (!Objects.equals(this.numeroParte, other.numeroParte)) { return false; }		
		if (!Objects.equals(this.tipoAbastecimiento, other.tipoAbastecimiento)) { return false; }		
		if (!Objects.equals(this.categoriaMaterial, other.categoriaMaterial)) { return false; }		
		if (!Objects.equals(this.descripcionEs, other.descripcionEs)) { return false; }		
		if (!Objects.equals(this.descripcionEn, other.descripcionEn)) { return false; }		
		if (!Objects.equals(this.urlMultimedia, other.urlMultimedia)) { return false; }		
		if (!Objects.equals(this.mimeType, other.mimeType)) { return false; }		
		if (!Objects.equals(this.peso, other.peso)) { return false; }		
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
		sb.append("Material{");
		sb.append("numeroParte" ).append("=").append(numeroParte).append("|");
		sb.append("tipoAbastecimiento" ).append("=").append(tipoAbastecimiento).append("|");
		sb.append("categoriaMaterial" ).append("=").append(categoriaMaterial).append("|");
		sb.append("descripcionEs" ).append("=").append(descripcionEs).append("|");
		sb.append("descripcionEn" ).append("=").append(descripcionEn).append("|");
		sb.append("urlMultimedia" ).append("=").append(urlMultimedia).append("|");
		sb.append("mimeType" ).append("=").append(mimeType).append("|");
		sb.append("peso" ).append("=").append(peso).append("|");
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
		sb.append(" ").append(numeroParte).append(" ");

		return sb.toString().trim();
	}

}
