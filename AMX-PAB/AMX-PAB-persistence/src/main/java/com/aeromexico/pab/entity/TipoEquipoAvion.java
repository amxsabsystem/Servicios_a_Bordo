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
 * Class for mapping JPA Entity of Table tipo_equipo_avion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tipo_equipo_avion")
@NamedQueries({
    @NamedQuery(name = "TipoEquipoAvion.findAll", query = "SELECT t FROM TipoEquipoAvion t")
    , @NamedQuery(name = "TipoEquipoAvion.countAll", query = "SELECT COUNT(t) FROM TipoEquipoAvion t")
    , @NamedQuery(name = "TipoEquipoAvion.findByIdTipoEquipoAvion", query = "SELECT t FROM TipoEquipoAvion t WHERE t.idTipoEquipoAvion = :idTipoEquipoAvion")
    , @NamedQuery(name = "TipoEquipoAvion.findBymodeloAvion", query = "SELECT t FROM TipoEquipoAvion t WHERE t.modeloAvion = :modeloAvion")
    , @NamedQuery(name = "TipoEquipoAvion.findByTotalAviones", query = "SELECT t FROM TipoEquipoAvion t WHERE t.totalAviones = :totalAviones")
    , @NamedQuery(name = "TipoEquipoAvion.findByTipoEquipo", query = "SELECT t FROM TipoEquipoAvion t WHERE t.tipoEquipo = :tipoEquipo")
    , @NamedQuery(name = "TipoEquipoAvion.findByStatus", query = "SELECT t FROM TipoEquipoAvion t WHERE t.status = :status")
    , @NamedQuery(name = "TipoEquipoAvion.findByUsuarioCreo", query = "SELECT t FROM TipoEquipoAvion t WHERE t.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "TipoEquipoAvion.findByFechaCreo", query = "SELECT t FROM TipoEquipoAvion t WHERE t.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "TipoEquipoAvion.findByUsuarioModifico", query = "SELECT t FROM TipoEquipoAvion t WHERE t.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "TipoEquipoAvion.findByFechaModifico", query = "SELECT t FROM TipoEquipoAvion t WHERE t.fechaModifico = :fechaModifico")
})
public class      TipoEquipoAvion 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 356656350;
    
    /**
    * The 'id tipo equipo avion' Maps to COLUMN 'id_tipo_equipo_avion'
    */
    
    @Id
    @Column(name = "ID_TIPO_EQUIPO_AVION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTipoEquipoAvion;
    
    /**
    * The 'id modelo avion' Maps to COLUMN 'id_modelo_avion'
    */
    
    @JoinColumn(name = "ID_MODELO_AVION" , referencedColumnName = "ID_MODELO_AVION")
    @ManyToOne(optional = false )
    private ModeloAvion modeloAvion;
    
    /**
    * The 'total aviones' Maps to COLUMN 'total_aviones'
    */
    
    @Basic(optional = false)
    @Column(name = "TOTAL_AVIONES" , nullable=false)
    private Short totalAviones;
    
    /**
    * The 'tipo equipo' Maps to COLUMN 'tipo_equipo'
    */
    
    @Basic(optional = false)
    @Column(name = "TIPO_EQUIPO" , length=6, nullable=false)
    private String tipoEquipo;
    
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
    * Map the relation to matriz table where has id_tipo_equipo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEquipoAvion")
    private List<Matriz> matrizHasTipoEquipoAvionList;
    
    /** 
    * Map the relation to avion table where has id_tipo_equipo_avion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEquipoAvion")
    private List<Avion> avionHasTipoEquipoAvionList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TipoEquipoAvion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTipoEquipoAvion() { return this.idTipoEquipoAvion;}
    public void setIdTipoEquipoAvion(Integer v) { this.idTipoEquipoAvion = v; }
    
    public ModeloAvion getModeloAvion(){ return this.modeloAvion ; }
    public void setModeloAvion(ModeloAvion x){ this.modeloAvion = x; }
    
    public Short getTotalAviones() { return this.totalAviones;}
    public void setTotalAviones(Short v) { this.totalAviones = v; }
    
    public String getTipoEquipo() { return this.tipoEquipo;}
    public void setTipoEquipo(String v) { this.tipoEquipo = v; }
    
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
    public List<Matriz> getMatrizHasTipoEquipoAvionList(){ return this.matrizHasTipoEquipoAvionList; }
    public void setMatrizHasTipoEquipoAvionList(List<Matriz> v){ this.matrizHasTipoEquipoAvionList = v; }
    
    public List<Avion> getAvionHasTipoEquipoAvionList(){ return this.avionHasTipoEquipoAvionList; }
    public void setAvionHasTipoEquipoAvionList(List<Avion> v){ this.avionHasTipoEquipoAvionList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTipoEquipoAvion).hashCode();
		hash += String.valueOf(modeloAvion).hashCode();
		hash += String.valueOf(totalAviones).hashCode();
		hash += String.valueOf(tipoEquipo).hashCode();
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
        if (!(o instanceof TipoEquipoAvion)) {
            return false;
        }		
		TipoEquipoAvion other = (TipoEquipoAvion ) o;
		if (!Objects.equals(this.idTipoEquipoAvion, other.idTipoEquipoAvion)) { return false; }		
		if (!Objects.equals(this.modeloAvion, other.modeloAvion)) { return false; }		
		if (!Objects.equals(this.totalAviones, other.totalAviones)) { return false; }		
		if (!Objects.equals(this.tipoEquipo, other.tipoEquipo)) { return false; }		
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
		sb.append("TipoEquipoAvion{");
		sb.append("idTipoEquipoAvion" ).append("=").append(idTipoEquipoAvion).append("|");
		sb.append("modeloAvion" ).append("=").append(modeloAvion).append("|");
		sb.append("totalAviones" ).append("=").append(totalAviones).append("|");
		sb.append("tipoEquipo" ).append("=").append(tipoEquipo).append("|");
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
		sb.append(" ").append(idTipoEquipoAvion).append(" ");

		return sb.toString().trim();
	}

}
