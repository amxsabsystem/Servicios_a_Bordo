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
 * Class for mapping JPA Entity of Table Proceso.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Proceso")
@NamedQueries({
    @NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
    , @NamedQuery(name = "Proceso.countAll", query = "SELECT COUNT(p) FROM Proceso p")
    , @NamedQuery(name = "Proceso.findByIdProceso", query = "SELECT p FROM Proceso p WHERE p.idProceso = :idProceso")
    , @NamedQuery(name = "Proceso.findByidIdioma", query = "SELECT p FROM Proceso p WHERE p.idIdioma = :idIdioma")
    , @NamedQuery(name = "Proceso.findBytipoProceso", query = "SELECT p FROM Proceso p WHERE p.tipoProceso = :tipoProceso")
    , @NamedQuery(name = "Proceso.findByTitulo", query = "SELECT p FROM Proceso p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Proceso.findByRevision", query = "SELECT p FROM Proceso p WHERE p.revision = :revision")
    , @NamedQuery(name = "Proceso.findByStatus", query = "SELECT p FROM Proceso p WHERE p.status = :status")
    , @NamedQuery(name = "Proceso.findByUsuarioCreo", query = "SELECT p FROM Proceso p WHERE p.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Proceso.findByFechaCreo", query = "SELECT p FROM Proceso p WHERE p.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Proceso.findByUsuarioModifico", query = "SELECT p FROM Proceso p WHERE p.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Proceso.findByFechaModifico", query = "SELECT p FROM Proceso p WHERE p.fechaModifico = :fechaModifico")
})
public class      Proceso 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 1355134539;
    
    /**
    * The 'id proceso' Maps to COLUMN 'id_proceso'
    */
    
    @Id
    @Column(name = "ID_PROCESO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idProceso;
    
    /**
    * The 'id idioma' Maps to COLUMN 'id_idioma'
    */
    
    @JoinColumn(name = "ID_IDIOMA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro idIdioma;
    
    /**
    * The 'tipo proceso' Maps to COLUMN 'tipo_proceso'
    */
    
    @JoinColumn(name = "TIPO_PROCESO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = false )
    private Parametro tipoProceso;
    
    /**
    * The 'titulo' Maps to COLUMN 'titulo'
    */
    
    @Basic(optional = true)
    @Column(name = "TITULO" , length=255, nullable=true)
    private String titulo;
    
    /**
    * The 'revision' Maps to COLUMN 'revision'
    */
    
    @Basic(optional = false)
    @Column(name = "REVISION" , nullable=false)
    private int revision;
    
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
    * Map the relation to archivo table where has id_proceso object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proceso")
    private List<Archivo> archivoHasProcesoList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Proceso() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdProceso() { return this.idProceso;}
    public void setIdProceso(Integer v) { this.idProceso = v; }
    
    public Parametro getIdIdioma(){ return this.idIdioma ; }
    public void setIdIdioma(Parametro x){ this.idIdioma = x; }
    
    public Parametro getTipoProceso(){ return this.tipoProceso ; }
    public void setTipoProceso(Parametro x){ this.tipoProceso = x; }
    
    public String getTitulo() { return this.titulo;}
    public void setTitulo(String v) { this.titulo = v; }
    
    public int getRevision() { return this.revision;}
    public void setRevision(int v) { this.revision = v; }
    
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
    public List<Archivo> getArchivoHasProcesoList(){ return this.archivoHasProcesoList; }
    public void setArchivoHasProcesoList(List<Archivo> v){ this.archivoHasProcesoList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idProceso).hashCode();
		hash += String.valueOf(idIdioma).hashCode();
		hash += String.valueOf(tipoProceso).hashCode();
		hash += String.valueOf(titulo).hashCode();
		hash += String.valueOf(revision).hashCode();
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
        if (!(o instanceof Proceso)) {
            return false;
        }		
		Proceso other = (Proceso ) o;
		if (!Objects.equals(this.idProceso, other.idProceso)) { return false; }		
		if (!Objects.equals(this.idIdioma, other.idIdioma)) { return false; }		
		if (!Objects.equals(this.tipoProceso, other.tipoProceso)) { return false; }		
		if (!Objects.equals(this.titulo, other.titulo)) { return false; }		
		if (!Objects.equals(this.revision, other.revision)) { return false; }		
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
		sb.append("Proceso{");
		sb.append("idProceso" ).append("=").append(idProceso).append("|");
		sb.append("idIdioma" ).append("=").append(idIdioma).append("|");
		sb.append("tipoProceso" ).append("=").append(tipoProceso).append("|");
		sb.append("titulo" ).append("=").append(titulo).append("|");
		sb.append("revision" ).append("=").append(revision).append("|");
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
		sb.append(" ").append(idProceso).append(" ");

		return sb.toString().trim();
	}

}
