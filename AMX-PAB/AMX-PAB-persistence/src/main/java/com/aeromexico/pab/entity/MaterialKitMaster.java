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
 * Class for mapping JPA Entity of Table material_kit_master.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "material_kit_master")
@NamedQueries({
    @NamedQuery(name = "MaterialKitMaster.findAll", query = "SELECT m FROM MaterialKitMaster m")
    , @NamedQuery(name = "MaterialKitMaster.countAll", query = "SELECT COUNT(m) FROM MaterialKitMaster m")
    , @NamedQuery(name = "MaterialKitMaster.findByidUnidadMedida", query = "SELECT m FROM MaterialKitMaster m WHERE m.idUnidadMedida = :idUnidadMedida")
    , @NamedQuery(name = "MaterialKitMaster.findByidInstruccionesNacionales", query = "SELECT m FROM MaterialKitMaster m WHERE m.idInstruccionesNacionales = :idInstruccionesNacionales")
    , @NamedQuery(name = "MaterialKitMaster.findByidInstruccionesInternac", query = "SELECT m FROM MaterialKitMaster m WHERE m.idInstruccionesInternac = :idInstruccionesInternac")
    , @NamedQuery(name = "MaterialKitMaster.findByCantidad", query = "SELECT m FROM MaterialKitMaster m WHERE m.cantidad = :cantidad")
    , @NamedQuery(name = "MaterialKitMaster.findByReciclable", query = "SELECT m FROM MaterialKitMaster m WHERE m.reciclable = :reciclable")
    , @NamedQuery(name = "MaterialKitMaster.findByObservaciones", query = "SELECT m FROM MaterialKitMaster m WHERE m.observaciones = :observaciones")
    , @NamedQuery(name = "MaterialKitMaster.findByStatus", query = "SELECT m FROM MaterialKitMaster m WHERE m.status = :status")
    , @NamedQuery(name = "MaterialKitMaster.findByUsuarioCreo", query = "SELECT m FROM MaterialKitMaster m WHERE m.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "MaterialKitMaster.findByFechaCreo", query = "SELECT m FROM MaterialKitMaster m WHERE m.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "MaterialKitMaster.findByUsuarioModifico", query = "SELECT m FROM MaterialKitMaster m WHERE m.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "MaterialKitMaster.findByFechaModifico", query = "SELECT m FROM MaterialKitMaster m WHERE m.fechaModifico = :fechaModifico")
    , @NamedQuery(name = "MaterialKitMaster.findBymaterial", query = "SELECT m FROM MaterialKitMaster m WHERE m.material = :material")
    , @NamedQuery(name = "MaterialKitMaster.findBykitMaster", query = "SELECT m FROM MaterialKitMaster m WHERE m.kitMaster = :kitMaster")
    , @NamedQuery(name = "MaterialKitMaster.findByMaterialKitMasterPK", query = "SELECT m FROM MaterialKitMaster m WHERE m.materialKitMasterPK = :materialKitMasterPK")
})
public class      MaterialKitMaster 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 2101562883;
    
    /**
    * The 'id unidad medida' Maps to COLUMN 'id_unidad_medida'
    */
    
    @JoinColumn(name = "ID_UNIDAD_MEDIDA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idUnidadMedida;
    
    /**
    * The 'id instrucciones nacionales' Maps to COLUMN 'id_instrucciones_nacionales'
    */
    
    @JoinColumn(name = "ID_INSTRUCCIONES_NACIONALES" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idInstruccionesNacionales;
    
    /**
    * The 'id instrucciones internac' Maps to COLUMN 'id_instrucciones_internac'
    */
    
    @JoinColumn(name = "ID_INSTRUCCIONES_INTERNAC" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idInstruccionesInternac;
    
    /**
    * The 'cantidad' Maps to COLUMN 'cantidad'
    */
    
    @Basic(optional = false)
    @Column(name = "CANTIDAD" , nullable=false)
    private int cantidad;
    
    /**
    * The 'reciclable' Maps to COLUMN 'reciclable'
    */
    
    @Basic(optional = false)
    @Column(name = "RECICLABLE" , nullable=false)
    private Short reciclable;
    
    /**
    * The 'observaciones' Maps to COLUMN 'observaciones'
    */
    
    @Basic(optional = true)
    @Column(name = "OBSERVACIONES" , length=255, nullable=true)
    private String observaciones;
    
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
    * The 'numero parte' Maps to COLUMN 'numero_parte'
    */
    
    @JoinColumn(name = "NUMERO_PARTE" , referencedColumnName = "NUMERO_PARTE", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private Material material;
    
    /**
    * The 'cve kit master' Maps to COLUMN 'cve_kit_master'
    */
    
    @JoinColumn(name = "CVE_KIT_MASTER" , referencedColumnName = "CVE_KIT_MASTER", insertable = false, updatable = false)
    @ManyToOne(optional = false )
    private KitMaster kitMaster;
    
    /**
    * The 'material kit master P K' Maps to COLUMN 'material_kit_master_P_K'
    */
    
    @EmbeddedId
    private MaterialKitMasterPK materialKitMasterPK;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public MaterialKitMaster() {
    }
    
    /**
     * Getters and Setters
     */
    public Parametro getIdUnidadMedida(){ return this.idUnidadMedida ; }
    public void setIdUnidadMedida(Parametro x){ this.idUnidadMedida = x; }
    
    public Parametro getIdInstruccionesNacionales(){ return this.idInstruccionesNacionales ; }
    public void setIdInstruccionesNacionales(Parametro x){ this.idInstruccionesNacionales = x; }
    
    public Parametro getIdInstruccionesInternac(){ return this.idInstruccionesInternac ; }
    public void setIdInstruccionesInternac(Parametro x){ this.idInstruccionesInternac = x; }
    
    public int getCantidad() { return this.cantidad;}
    public void setCantidad(int v) { this.cantidad = v; }
    
    public Short getReciclable() { return this.reciclable;}
    public void setReciclable(Short v) { this.reciclable = v; }
    
    public String getObservaciones() { return this.observaciones;}
    public void setObservaciones(String v) { this.observaciones = v; }
    
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
    
    public Material getMaterial(){ return this.material ; }
    public void setMaterial(Material x){ this.material = x; }
    
    public KitMaster getKitMaster(){ return this.kitMaster ; }
    public void setKitMaster(KitMaster x){ this.kitMaster = x; }
    
    public MaterialKitMasterPK getMaterialKitMasterPK() { return this.materialKitMasterPK;}
    public void setMaterialKitMasterPK(MaterialKitMasterPK v) { this.materialKitMasterPK = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idUnidadMedida).hashCode();
		hash += String.valueOf(idInstruccionesNacionales).hashCode();
		hash += String.valueOf(idInstruccionesInternac).hashCode();
		hash += String.valueOf(cantidad).hashCode();
		hash += String.valueOf(reciclable).hashCode();
		hash += String.valueOf(observaciones).hashCode();
		hash += String.valueOf(status).hashCode();
		hash += String.valueOf(usuarioCreo).hashCode();
		hash += String.valueOf(fechaCreo).hashCode();
		hash += String.valueOf(usuarioModifico).hashCode();
		hash += String.valueOf(fechaModifico).hashCode();
		hash += String.valueOf(material).hashCode();
		hash += String.valueOf(kitMaster).hashCode();
		hash += String.valueOf(materialKitMasterPK).hashCode();
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
        if (!(o instanceof MaterialKitMaster)) {
            return false;
        }		
		MaterialKitMaster other = (MaterialKitMaster ) o;
		if (!Objects.equals(this.idUnidadMedida, other.idUnidadMedida)) { return false; }		
		if (!Objects.equals(this.idInstruccionesNacionales, other.idInstruccionesNacionales)) { return false; }		
		if (!Objects.equals(this.idInstruccionesInternac, other.idInstruccionesInternac)) { return false; }		
		if (!Objects.equals(this.cantidad, other.cantidad)) { return false; }		
		if (!Objects.equals(this.reciclable, other.reciclable)) { return false; }		
		if (!Objects.equals(this.observaciones, other.observaciones)) { return false; }		
		if (!Objects.equals(this.status, other.status)) { return false; }		
		if (!Objects.equals(this.usuarioCreo, other.usuarioCreo)) { return false; }		
		if (!Objects.equals(this.fechaCreo, other.fechaCreo)) { return false; }		
		if (!Objects.equals(this.usuarioModifico, other.usuarioModifico)) { return false; }		
		if (!Objects.equals(this.fechaModifico, other.fechaModifico)) { return false; }		
		if (!Objects.equals(this.material, other.material)) { return false; }		
		if (!Objects.equals(this.kitMaster, other.kitMaster)) { return false; }		
		if (!Objects.equals(this.materialKitMasterPK, other.materialKitMasterPK)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("MaterialKitMaster{");
		sb.append("idUnidadMedida" ).append("=").append(idUnidadMedida).append("|");
		sb.append("idInstruccionesNacionales" ).append("=").append(idInstruccionesNacionales).append("|");
		sb.append("idInstruccionesInternac" ).append("=").append(idInstruccionesInternac).append("|");
		sb.append("cantidad" ).append("=").append(cantidad).append("|");
		sb.append("reciclable" ).append("=").append(reciclable).append("|");
		sb.append("observaciones" ).append("=").append(observaciones).append("|");
		sb.append("status" ).append("=").append(status).append("|");
		sb.append("usuarioCreo" ).append("=").append(usuarioCreo).append("|");
		sb.append("fechaCreo" ).append("=").append(fechaCreo).append("|");
		sb.append("usuarioModifico" ).append("=").append(usuarioModifico).append("|");
		sb.append("fechaModifico" ).append("=").append(fechaModifico).append("|");
		sb.append("material" ).append("=").append(material).append("|");
		sb.append("kitMaster" ).append("=").append(kitMaster).append("|");
		sb.append("materialKitMasterPK" ).append("=").append(materialKitMasterPK).append("|");
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
