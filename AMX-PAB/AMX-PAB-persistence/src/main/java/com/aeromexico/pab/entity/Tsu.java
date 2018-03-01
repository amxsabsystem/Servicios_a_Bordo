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
 * Class for mapping JPA Entity of Table TSU.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "TSU")
@NamedQueries({
    @NamedQuery(name = "Tsu.findAll", query = "SELECT t FROM Tsu t")
    , @NamedQuery(name = "Tsu.countAll", query = "SELECT COUNT(t) FROM Tsu t")
    , @NamedQuery(name = "Tsu.findByIdTsu", query = "SELECT t FROM Tsu t WHERE t.idTsu = :idTsu")
    , @NamedQuery(name = "Tsu.findByCveTsu", query = "SELECT t FROM Tsu t WHERE t.cveTsu = :cveTsu")
    , @NamedQuery(name = "Tsu.findByidRevision", query = "SELECT t FROM Tsu t WHERE t.idRevision = :idRevision")
    , @NamedQuery(name = "Tsu.findByclase", query = "SELECT t FROM Tsu t WHERE t.clase = :clase")
    , @NamedQuery(name = "Tsu.findByidIdioma", query = "SELECT t FROM Tsu t WHERE t.idIdioma = :idIdioma")
    , @NamedQuery(name = "Tsu.findBymultimedio", query = "SELECT t FROM Tsu t WHERE t.multimedio = :multimedio")
    , @NamedQuery(name = "Tsu.findByNotas", query = "SELECT t FROM Tsu t WHERE t.notas = :notas")
    , @NamedQuery(name = "Tsu.findByFechaVigenciaInicio", query = "SELECT t FROM Tsu t WHERE t.fechaVigenciaInicio = :fechaVigenciaInicio")
    , @NamedQuery(name = "Tsu.findByFechaVigenciaFin", query = "SELECT t FROM Tsu t WHERE t.fechaVigenciaFin = :fechaVigenciaFin")
    , @NamedQuery(name = "Tsu.findByStatus", query = "SELECT t FROM Tsu t WHERE t.status = :status")
    , @NamedQuery(name = "Tsu.findByUsuarioCreo", query = "SELECT t FROM Tsu t WHERE t.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Tsu.findByFechaCreo", query = "SELECT t FROM Tsu t WHERE t.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Tsu.findByUsuarioModifico", query = "SELECT t FROM Tsu t WHERE t.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Tsu.findByFechaModifico", query = "SELECT t FROM Tsu t WHERE t.fechaModifico = :fechaModifico")
})
public class      Tsu 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 83382;
    
    /**
    * The 'id tsu' Maps to COLUMN 'id_tsu'
    */
    
    @Id
    @Column(name = "ID_TSU" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTsu;
    
    /**
    * The 'cve tsu' Maps to COLUMN 'cve_tsu'
    */
    
    @Basic(optional = false)
    @Column(name = "CVE_TSU" , length=10, nullable=false)
    private String cveTsu;
    
    /**
    * The 'id revision' Maps to COLUMN 'id_revision'
    */
    
    @JoinColumn(name = "ID_REVISION" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idRevision;
    
    /**
    * The 'id clase' Maps to COLUMN 'id_clase'
    */
    
    @JoinColumn(name = "ID_CLASE" , referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false )
    private Clase clase;
    
    /**
    * The 'id idioma' Maps to COLUMN 'id_idioma'
    */
    
    @JoinColumn(name = "ID_IDIOMA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idIdioma;
    
    /**
    * The 'id multimedio' Maps to COLUMN 'id_multimedio'
    */
    
    @JoinColumn(name = "ID_MULTIMEDIO" , referencedColumnName = "ID_MULTIMEDIO")
    @ManyToOne(optional = false )
    private Multimedio multimedio;
    
    /**
    * The 'notas' Maps to COLUMN 'notas'
    */
    
    @Basic(optional = true)
    @Column(name = "NOTAS" , length=512, nullable=true)
    private String notas;
    
    /**
    * The 'fecha vigencia inicio' Maps to COLUMN 'fecha_vigencia_inicio'
    */
    
    @Basic(optional = true)
    @Column(name = "FECHA_VIGENCIA_INICIO" , nullable=true)
    private java.sql.Date fechaVigenciaInicio;
    
    /**
    * The 'fecha vigencia fin' Maps to COLUMN 'fecha_vigencia_fin'
    */
    
    @Basic(optional = true)
    @Column(name = "FECHA_VIGENCIA_FIN" , nullable=true)
    private java.sql.Date fechaVigenciaFin;
    
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
    * Map the relation to Kit_TSU table where has id_tsu object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tsu")
    private List<KitTsu> kitTsuHasTsuList;
    
    /** 
    * Map the relation to tsu_abordamiento table where has id_tsu object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tsu")
    private List<TsuAbordamiento> tsuAbordamientoHasTsuList;
    
    /** 
    * Map the relation to detalle_tabla_tsu table where has id_tsu object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tsu")
    private List<DetalleTablaTsu> detalleTablaTsuHasTsuList;
    
    /** 
    * Map the relation to Material_TSU table where has id_tsu object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tsu")
    private List<MaterialTsu> materialTsuHasTsuList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Tsu() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTsu() { return this.idTsu;}
    public void setIdTsu(Integer v) { this.idTsu = v; }
    
    public String getCveTsu() { return this.cveTsu;}
    public void setCveTsu(String v) { this.cveTsu = v; }
    
    public Parametro getIdRevision(){ return this.idRevision ; }
    public void setIdRevision(Parametro x){ this.idRevision = x; }
    
    public Clase getClase(){ return this.clase ; }
    public void setClase(Clase x){ this.clase = x; }
    
    public Parametro getIdIdioma(){ return this.idIdioma ; }
    public void setIdIdioma(Parametro x){ this.idIdioma = x; }
    
    public Multimedio getMultimedio(){ return this.multimedio ; }
    public void setMultimedio(Multimedio x){ this.multimedio = x; }
    
    public String getNotas() { return this.notas;}
    public void setNotas(String v) { this.notas = v; }
    
    public java.sql.Date getFechaVigenciaInicio() { return this.fechaVigenciaInicio;}
    public void setFechaVigenciaInicio(java.sql.Date v) { this.fechaVigenciaInicio = v; }
    
    public java.sql.Date getFechaVigenciaFin() { return this.fechaVigenciaFin;}
    public void setFechaVigenciaFin(java.sql.Date v) { this.fechaVigenciaFin = v; }
    
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
    public List<KitTsu> getKitTsuHasTsuList(){ return this.kitTsuHasTsuList; }
    public void setKitTsuHasTsuList(List<KitTsu> v){ this.kitTsuHasTsuList = v; }
    
    public List<TsuAbordamiento> getTsuAbordamientoHasTsuList(){ return this.tsuAbordamientoHasTsuList; }
    public void setTsuAbordamientoHasTsuList(List<TsuAbordamiento> v){ this.tsuAbordamientoHasTsuList = v; }
    
    public List<DetalleTablaTsu> getDetalleTablaTsuHasTsuList(){ return this.detalleTablaTsuHasTsuList; }
    public void setDetalleTablaTsuHasTsuList(List<DetalleTablaTsu> v){ this.detalleTablaTsuHasTsuList = v; }
    
    public List<MaterialTsu> getMaterialTsuHasTsuList(){ return this.materialTsuHasTsuList; }
    public void setMaterialTsuHasTsuList(List<MaterialTsu> v){ this.materialTsuHasTsuList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTsu).hashCode();
		hash += String.valueOf(cveTsu).hashCode();
		hash += String.valueOf(idRevision).hashCode();
		hash += String.valueOf(clase).hashCode();
		hash += String.valueOf(idIdioma).hashCode();
		hash += String.valueOf(multimedio).hashCode();
		hash += String.valueOf(notas).hashCode();
		hash += String.valueOf(fechaVigenciaInicio).hashCode();
		hash += String.valueOf(fechaVigenciaFin).hashCode();
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
        if (!(o instanceof Tsu)) {
            return false;
        }		
		Tsu other = (Tsu ) o;
		if (!Objects.equals(this.idTsu, other.idTsu)) { return false; }		
		if (!Objects.equals(this.cveTsu, other.cveTsu)) { return false; }		
		if (!Objects.equals(this.idRevision, other.idRevision)) { return false; }		
		if (!Objects.equals(this.clase, other.clase)) { return false; }		
		if (!Objects.equals(this.idIdioma, other.idIdioma)) { return false; }		
		if (!Objects.equals(this.multimedio, other.multimedio)) { return false; }		
		if (!Objects.equals(this.notas, other.notas)) { return false; }		
		if (!Objects.equals(this.fechaVigenciaInicio, other.fechaVigenciaInicio)) { return false; }		
		if (!Objects.equals(this.fechaVigenciaFin, other.fechaVigenciaFin)) { return false; }		
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
		sb.append("Tsu{");
		sb.append("idTsu" ).append("=").append(idTsu).append("|");
		sb.append("cveTsu" ).append("=").append(cveTsu).append("|");
		sb.append("idRevision" ).append("=").append(idRevision).append("|");
		sb.append("clase" ).append("=").append(clase).append("|");
		sb.append("idIdioma" ).append("=").append(idIdioma).append("|");
		sb.append("multimedio" ).append("=").append(multimedio).append("|");
		sb.append("notas" ).append("=").append(notas).append("|");
		sb.append("fechaVigenciaInicio" ).append("=").append(fechaVigenciaInicio).append("|");
		sb.append("fechaVigenciaFin" ).append("=").append(fechaVigenciaFin).append("|");
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
		sb.append(" ").append(idTsu).append(" ");

		return sb.toString().trim();
	}

}
