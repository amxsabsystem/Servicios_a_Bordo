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
 * Class for mapping JPA Entity of Table tema_comunicado.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "tema_comunicado")
@NamedQueries({
    @NamedQuery(name = "TemaComunicado.findAll", query = "SELECT t FROM TemaComunicado t")
    , @NamedQuery(name = "TemaComunicado.countAll", query = "SELECT COUNT(t) FROM TemaComunicado t")
    , @NamedQuery(name = "TemaComunicado.findByIdTemaComunicado", query = "SELECT t FROM TemaComunicado t WHERE t.idTemaComunicado = :idTemaComunicado")
    , @NamedQuery(name = "TemaComunicado.findByTemaEspaniol", query = "SELECT t FROM TemaComunicado t WHERE t.temaEspaniol = :temaEspaniol")
    , @NamedQuery(name = "TemaComunicado.findByTemaIngles", query = "SELECT t FROM TemaComunicado t WHERE t.temaIngles = :temaIngles")
    , @NamedQuery(name = "TemaComunicado.findByStatus", query = "SELECT t FROM TemaComunicado t WHERE t.status = :status")
    , @NamedQuery(name = "TemaComunicado.findByUsuarioCreo", query = "SELECT t FROM TemaComunicado t WHERE t.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "TemaComunicado.findByFechaCreo", query = "SELECT t FROM TemaComunicado t WHERE t.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "TemaComunicado.findByUsuarioModifico", query = "SELECT t FROM TemaComunicado t WHERE t.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "TemaComunicado.findByFechaModifico", query = "SELECT t FROM TemaComunicado t WHERE t.fechaModifico = :fechaModifico")
})
public class      TemaComunicado 
       implements java.io.Serializable 
                  , AuditableEntity 
    {
    private static final long serialVersionUID = 2025567858;
    
    /**
    * The 'id tema comunicado' Maps to COLUMN 'id_tema_comunicado'
    */
    
    @Id
    @Column(name = "ID_TEMA_COMUNICADO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idTemaComunicado;
    
    /**
    * The 'tema espaniol' Maps to COLUMN 'tema_espaniol'
    */
    
    @Basic(optional = false)
    @Column(name = "TEMA_ESPANIOL" , length=100, nullable=false)
    private String temaEspaniol;
    
    /**
    * The 'tema ingles' Maps to COLUMN 'tema_ingles'
    */
    
    @Basic(optional = false)
    @Column(name = "TEMA_INGLES" , length=100, nullable=false)
    private String temaIngles;
    
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
    * Map the relation to Comunicado table where has id_tema object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTema")
    private List<Comunicado> comunicadoHasIdTemaList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public TemaComunicado() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTemaComunicado() { return this.idTemaComunicado;}
    public void setIdTemaComunicado(Integer v) { this.idTemaComunicado = v; }
    
    public String getTemaEspaniol() { return this.temaEspaniol;}
    public void setTemaEspaniol(String v) { this.temaEspaniol = v; }
    
    public String getTemaIngles() { return this.temaIngles;}
    public void setTemaIngles(String v) { this.temaIngles = v; }
    
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
    public List<Comunicado> getComunicadoHasIdTemaList(){ return this.comunicadoHasIdTemaList; }
    public void setComunicadoHasIdTemaList(List<Comunicado> v){ this.comunicadoHasIdTemaList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTemaComunicado).hashCode();
		hash += String.valueOf(temaEspaniol).hashCode();
		hash += String.valueOf(temaIngles).hashCode();
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
        if (!(o instanceof TemaComunicado)) {
            return false;
        }		
		TemaComunicado other = (TemaComunicado ) o;
		if (!Objects.equals(this.idTemaComunicado, other.idTemaComunicado)) { return false; }		
		if (!Objects.equals(this.temaEspaniol, other.temaEspaniol)) { return false; }		
		if (!Objects.equals(this.temaIngles, other.temaIngles)) { return false; }		
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
		sb.append("TemaComunicado{");
		sb.append("idTemaComunicado" ).append("=").append(idTemaComunicado).append("|");
		sb.append("temaEspaniol" ).append("=").append(temaEspaniol).append("|");
		sb.append("temaIngles" ).append("=").append(temaIngles).append("|");
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
		sb.append(" ").append(idTemaComunicado).append(" ");

		return sb.toString().trim();
	}

}
