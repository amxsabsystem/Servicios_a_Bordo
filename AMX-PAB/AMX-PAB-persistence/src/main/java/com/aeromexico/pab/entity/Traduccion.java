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
 * Class for mapping JPA Entity of Table traduccion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "traduccion")
@NamedQueries({
    @NamedQuery(name = "Traduccion.findAll", query = "SELECT t FROM Traduccion t")
    , @NamedQuery(name = "Traduccion.countAll", query = "SELECT COUNT(t) FROM Traduccion t")
    , @NamedQuery(name = "Traduccion.findByIdTraduccion", query = "SELECT t FROM Traduccion t WHERE t.idTraduccion = :idTraduccion")
    , @NamedQuery(name = "Traduccion.findByNombreEntidad", query = "SELECT t FROM Traduccion t WHERE t.nombreEntidad = :nombreEntidad")
    , @NamedQuery(name = "Traduccion.findByIdEntidad", query = "SELECT t FROM Traduccion t WHERE t.idEntidad = :idEntidad")
    , @NamedQuery(name = "Traduccion.findByCampo", query = "SELECT t FROM Traduccion t WHERE t.campo = :campo")
    , @NamedQuery(name = "Traduccion.findByValor", query = "SELECT t FROM Traduccion t WHERE t.valor = :valor")
    , @NamedQuery(name = "Traduccion.findByStatus", query = "SELECT t FROM Traduccion t WHERE t.status = :status")
    , @NamedQuery(name = "Traduccion.findByUsuarioCreo", query = "SELECT t FROM Traduccion t WHERE t.usuarioCreo = :usuarioCreo")
    , @NamedQuery(name = "Traduccion.findByFechaCreo", query = "SELECT t FROM Traduccion t WHERE t.fechaCreo = :fechaCreo")
    , @NamedQuery(name = "Traduccion.findByUsuarioModifico", query = "SELECT t FROM Traduccion t WHERE t.usuarioModifico = :usuarioModifico")
    , @NamedQuery(name = "Traduccion.findByFechaModifico", query = "SELECT t FROM Traduccion t WHERE t.fechaModifico = :fechaModifico")
})
public class      Traduccion 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1958050124;
    
    /**
    * The 'id traduccion' Maps to COLUMN 'id_traduccion'
    */
    
    @Id
    @Column(name = "ID_TRADUCCION" , nullable=false  )
    private Integer idTraduccion;
    
    /**
    * The 'nombre entidad' Maps to COLUMN 'nombre_entidad'
    */
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_ENTIDAD" , length=50, nullable=false)
    private String nombreEntidad;
    
    /**
    * The 'id entidad' Maps to COLUMN 'id_entidad'
    */
    
    @Basic(optional = false)
    @Column(name = "ID_ENTIDAD" , nullable=false)
    private int idEntidad;
    
    /**
    * The 'campo' Maps to COLUMN 'campo'
    */
    
    @Basic(optional = false)
    @Column(name = "CAMPO" , length=50, nullable=false)
    private String campo;
    
    /**
    * The 'valor' Maps to COLUMN 'valor'
    */
    
    @Basic(optional = false)
    @Column(name = "VALOR" , length=100, nullable=false)
    private String valor;
    
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

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Traduccion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdTraduccion() { return this.idTraduccion;}
    public void setIdTraduccion(Integer v) { this.idTraduccion = v; }
    
    public String getNombreEntidad() { return this.nombreEntidad;}
    public void setNombreEntidad(String v) { this.nombreEntidad = v; }
    
    public int getIdEntidad() { return this.idEntidad;}
    public void setIdEntidad(int v) { this.idEntidad = v; }
    
    public String getCampo() { return this.campo;}
    public void setCampo(String v) { this.campo = v; }
    
    public String getValor() { return this.valor;}
    public void setValor(String v) { this.valor = v; }
    
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
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idTraduccion).hashCode();
		hash += String.valueOf(nombreEntidad).hashCode();
		hash += String.valueOf(idEntidad).hashCode();
		hash += String.valueOf(campo).hashCode();
		hash += String.valueOf(valor).hashCode();
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
        if (!(o instanceof Traduccion)) {
            return false;
        }		
		Traduccion other = (Traduccion ) o;
		if (!Objects.equals(this.idTraduccion, other.idTraduccion)) { return false; }		
		if (!Objects.equals(this.nombreEntidad, other.nombreEntidad)) { return false; }		
		if (!Objects.equals(this.idEntidad, other.idEntidad)) { return false; }		
		if (!Objects.equals(this.campo, other.campo)) { return false; }		
		if (!Objects.equals(this.valor, other.valor)) { return false; }		
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
		sb.append("Traduccion{");
		sb.append("idTraduccion" ).append("=").append(idTraduccion).append("|");
		sb.append("nombreEntidad" ).append("=").append(nombreEntidad).append("|");
		sb.append("idEntidad" ).append("=").append(idEntidad).append("|");
		sb.append("campo" ).append("=").append(campo).append("|");
		sb.append("valor" ).append("=").append(valor).append("|");
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
		sb.append(" ").append(idTraduccion).append(" ");

		return sb.toString().trim();
	}

}
