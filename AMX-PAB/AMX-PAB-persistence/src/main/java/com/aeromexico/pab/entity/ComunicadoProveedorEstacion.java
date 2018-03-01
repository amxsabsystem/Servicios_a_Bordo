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
 * Class for mapping JPA Entity of Table comunicado_proveedor_estacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "comunicado_proveedor_estacion")
@NamedQueries({
    @NamedQuery(name = "ComunicadoProveedorEstacion.findAll", query = "SELECT c FROM ComunicadoProveedorEstacion c")
    , @NamedQuery(name = "ComunicadoProveedorEstacion.countAll", query = "SELECT COUNT(c) FROM ComunicadoProveedorEstacion c")
    , @NamedQuery(name = "ComunicadoProveedorEstacion.findByIdComunicadoProveedorEstacion", query = "SELECT c FROM ComunicadoProveedorEstacion c WHERE c.idComunicadoProveedorEstacion = :idComunicadoProveedorEstacion")
    , @NamedQuery(name = "ComunicadoProveedorEstacion.findBycomunicado", query = "SELECT c FROM ComunicadoProveedorEstacion c WHERE c.comunicado = :comunicado")
    , @NamedQuery(name = "ComunicadoProveedorEstacion.findByproveedorEstacion", query = "SELECT c FROM ComunicadoProveedorEstacion c WHERE c.proveedorEstacion = :proveedorEstacion")
    , @NamedQuery(name = "ComunicadoProveedorEstacion.findByContactosDirigidos", query = "SELECT c FROM ComunicadoProveedorEstacion c WHERE c.contactosDirigidos = :contactosDirigidos")
})
public class      ComunicadoProveedorEstacion 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 1764898056;
    
    /**
    * The 'id comunicado proveedor estacion' Maps to COLUMN 'id_comunicado_proveedor_estacion'
    */
    
    @Id
    @Column(name = "ID_COMUNICADO_PROVEEDOR_ESTACION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idComunicadoProveedorEstacion;
    
    /**
    * The 'id comunicado' Maps to COLUMN 'id_comunicado'
    */
    
    @JoinColumn(name = "ID_COMUNICADO" , referencedColumnName = "ID_COMUNICADO")
    @ManyToOne(optional = false )
    private Comunicado comunicado;
    
    /**
    * The 'id proveedor estacion' Maps to COLUMN 'id_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_PROVEEDOR_ESTACION" , referencedColumnName = "ID_PROVEEDOR_ESTACION")
    @ManyToOne(optional = false )
    private ProveedorEstacion proveedorEstacion;
    
    /**
    * The 'contactos dirigidos' Maps to COLUMN 'contactos_dirigidos'
    */
    
    @Basic(optional = true)
    @Column(name = "CONTACTOS_DIRIGIDOS" , nullable=true)
    private Integer contactosDirigidos;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ComunicadoProveedorEstacion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdComunicadoProveedorEstacion() { return this.idComunicadoProveedorEstacion;}
    public void setIdComunicadoProveedorEstacion(Integer v) { this.idComunicadoProveedorEstacion = v; }
    
    public Comunicado getComunicado(){ return this.comunicado ; }
    public void setComunicado(Comunicado x){ this.comunicado = x; }
    
    public ProveedorEstacion getProveedorEstacion(){ return this.proveedorEstacion ; }
    public void setProveedorEstacion(ProveedorEstacion x){ this.proveedorEstacion = x; }
    
    public Integer getContactosDirigidos() { return this.contactosDirigidos;}
    public void setContactosDirigidos(Integer v) { this.contactosDirigidos = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idComunicadoProveedorEstacion).hashCode();
		hash += String.valueOf(comunicado).hashCode();
		hash += String.valueOf(proveedorEstacion).hashCode();
		hash += String.valueOf(contactosDirigidos).hashCode();
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
        if (!(o instanceof ComunicadoProveedorEstacion)) {
            return false;
        }		
		ComunicadoProveedorEstacion other = (ComunicadoProveedorEstacion ) o;
		if (!Objects.equals(this.idComunicadoProveedorEstacion, other.idComunicadoProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.comunicado, other.comunicado)) { return false; }		
		if (!Objects.equals(this.proveedorEstacion, other.proveedorEstacion)) { return false; }		
		if (!Objects.equals(this.contactosDirigidos, other.contactosDirigidos)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ComunicadoProveedorEstacion{");
		sb.append("idComunicadoProveedorEstacion" ).append("=").append(idComunicadoProveedorEstacion).append("|");
		sb.append("comunicado" ).append("=").append(comunicado).append("|");
		sb.append("proveedorEstacion" ).append("=").append(proveedorEstacion).append("|");
		sb.append("contactosDirigidos" ).append("=").append(contactosDirigidos).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idComunicadoProveedorEstacion).append(" ");

		return sb.toString().trim();
	}

}
