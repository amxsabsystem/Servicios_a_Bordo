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
 * Class for mapping JPA Entity of Table bitacora_comunicado.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "bitacora_comunicado")
@NamedQueries({
    @NamedQuery(name = "BitacoraComunicado.findAll", query = "SELECT b FROM BitacoraComunicado b")
    , @NamedQuery(name = "BitacoraComunicado.countAll", query = "SELECT COUNT(b) FROM BitacoraComunicado b")
    , @NamedQuery(name = "BitacoraComunicado.findByIdBitacora", query = "SELECT b FROM BitacoraComunicado b WHERE b.idBitacora = :idBitacora")
    , @NamedQuery(name = "BitacoraComunicado.findBycontactoProveedorEstacion", query = "SELECT b FROM BitacoraComunicado b WHERE b.contactoProveedorEstacion = :contactoProveedorEstacion")
    , @NamedQuery(name = "BitacoraComunicado.findBycomunicado", query = "SELECT b FROM BitacoraComunicado b WHERE b.comunicado = :comunicado")
    , @NamedQuery(name = "BitacoraComunicado.findByproveedorEstacion", query = "SELECT b FROM BitacoraComunicado b WHERE b.proveedorEstacion = :proveedorEstacion")
    , @NamedQuery(name = "BitacoraComunicado.findByFecha", query = "SELECT b FROM BitacoraComunicado b WHERE b.fecha = :fecha")
})
public class      BitacoraComunicado 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = 1555004776;
    
    /**
    * The 'id bitacora' Maps to COLUMN 'id_bitacora'
    */
    
    @Id
    @Column(name = "ID_BITACORA" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idBitacora;
    
    /**
    * The 'id contacto proveedor estacion' Maps to COLUMN 'id_contacto_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_CONTACTO_PROVEEDOR_ESTACION" , referencedColumnName = "ID_CONTACTO_PROVEEDOR_ESTACION")
    @ManyToOne(optional = false )
    private ContactoProveedorEstacion contactoProveedorEstacion;
    
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
    * The 'fecha' Maps to COLUMN 'fecha'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA" , nullable=false)
    private java.sql.Timestamp fecha;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public BitacoraComunicado() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdBitacora() { return this.idBitacora;}
    public void setIdBitacora(Integer v) { this.idBitacora = v; }
    
    public ContactoProveedorEstacion getContactoProveedorEstacion(){ return this.contactoProveedorEstacion ; }
    public void setContactoProveedorEstacion(ContactoProveedorEstacion x){ this.contactoProveedorEstacion = x; }
    
    public Comunicado getComunicado(){ return this.comunicado ; }
    public void setComunicado(Comunicado x){ this.comunicado = x; }
    
    public ProveedorEstacion getProveedorEstacion(){ return this.proveedorEstacion ; }
    public void setProveedorEstacion(ProveedorEstacion x){ this.proveedorEstacion = x; }
    
    public java.sql.Timestamp getFecha() { return this.fecha;}
    public void setFecha(java.sql.Timestamp v) { this.fecha = v; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idBitacora).hashCode();
		hash += String.valueOf(contactoProveedorEstacion).hashCode();
		hash += String.valueOf(comunicado).hashCode();
		hash += String.valueOf(proveedorEstacion).hashCode();
		hash += String.valueOf(fecha).hashCode();
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
        if (!(o instanceof BitacoraComunicado)) {
            return false;
        }		
		BitacoraComunicado other = (BitacoraComunicado ) o;
		if (!Objects.equals(this.idBitacora, other.idBitacora)) { return false; }		
		if (!Objects.equals(this.contactoProveedorEstacion, other.contactoProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.comunicado, other.comunicado)) { return false; }		
		if (!Objects.equals(this.proveedorEstacion, other.proveedorEstacion)) { return false; }		
		if (!Objects.equals(this.fecha, other.fecha)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("BitacoraComunicado{");
		sb.append("idBitacora" ).append("=").append(idBitacora).append("|");
		sb.append("contactoProveedorEstacion" ).append("=").append(contactoProveedorEstacion).append("|");
		sb.append("comunicado" ).append("=").append(comunicado).append("|");
		sb.append("proveedorEstacion" ).append("=").append(proveedorEstacion).append("|");
		sb.append("fecha" ).append("=").append(fecha).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idBitacora).append(" ");

		return sb.toString().trim();
	}

}
