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
 * Class for mapping JPA Entity of Table seguimiento.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "seguimiento")
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s")
    , @NamedQuery(name = "Seguimiento.countAll", query = "SELECT COUNT(s) FROM Seguimiento s")
    , @NamedQuery(name = "Seguimiento.findByIdSeguimiento", query = "SELECT s FROM Seguimiento s WHERE s.idSeguimiento = :idSeguimiento")
    , @NamedQuery(name = "Seguimiento.findByMensajeRespuesta", query = "SELECT s FROM Seguimiento s WHERE s.mensajeRespuesta = :mensajeRespuesta")
    , @NamedQuery(name = "Seguimiento.findByareaResponsable", query = "SELECT s FROM Seguimiento s WHERE s.areaResponsable = :areaResponsable")
    , @NamedQuery(name = "Seguimiento.findByresponsableEstacion", query = "SELECT s FROM Seguimiento s WHERE s.responsableEstacion = :responsableEstacion")
    , @NamedQuery(name = "Seguimiento.findByproveedorEstacion", query = "SELECT s FROM Seguimiento s WHERE s.proveedorEstacion = :proveedorEstacion")
    , @NamedQuery(name = "Seguimiento.findBycontactoProveedorEstacion", query = "SELECT s FROM Seguimiento s WHERE s.contactoProveedorEstacion = :contactoProveedorEstacion")
    , @NamedQuery(name = "Seguimiento.findByreporte", query = "SELECT s FROM Seguimiento s WHERE s.reporte = :reporte")
    , @NamedQuery(name = "Seguimiento.findByFecha", query = "SELECT s FROM Seguimiento s WHERE s.fecha = :fecha")
})
public class      Seguimiento 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -678569111;
    
    /**
    * The 'id seguimiento' Maps to COLUMN 'id_seguimiento'
    */
    
    @Id
    @Column(name = "ID_SEGUIMIENTO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idSeguimiento;
    
    /**
    * The 'mensaje respuesta' Maps to COLUMN 'mensaje_respuesta'
    */
    
    @Basic(optional = false)
    @Column(name = "MENSAJE_RESPUESTA" , length=512, nullable=false)
    private String mensajeRespuesta;
    
    /**
    * The 'id area responsable' Maps to COLUMN 'id_area_responsable'
    */
    
    @JoinColumn(name = "ID_AREA_RESPONSABLE" , referencedColumnName = "ID_AREA")
    @ManyToOne(optional = true )
    private Area areaResponsable;
    
    /**
    * The 'id responsable estacion' Maps to COLUMN 'id_responsable_estacion'
    */
    
    @JoinColumn(name = "ID_RESPONSABLE_ESTACION" , referencedColumnName = "ID_RESPONSABLE_ESTACION")
    @ManyToOne(optional = true )
    private ResponsableEstacion responsableEstacion;
    
    /**
    * The 'id proveedor estacion' Maps to COLUMN 'id_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_PROVEEDOR_ESTACION" , referencedColumnName = "ID_PROVEEDOR_ESTACION")
    @ManyToOne(optional = true )
    private ProveedorEstacion proveedorEstacion;
    
    /**
    * The 'id contacto proveedor estacion' Maps to COLUMN 'id_contacto_proveedor_estacion'
    */
    
    @JoinColumn(name = "ID_CONTACTO_PROVEEDOR_ESTACION" , referencedColumnName = "ID_CONTACTO_PROVEEDOR_ESTACION")
    @ManyToOne(optional = true )
    private ContactoProveedorEstacion contactoProveedorEstacion;
    
    /**
    * The 'id reporte' Maps to COLUMN 'id_reporte'
    */
    
    @JoinColumn(name = "ID_REPORTE" , referencedColumnName = "ID_REPORTE")
    @ManyToOne(optional = false )
    private Reporte reporte;
    
    /**
    * The 'fecha' Maps to COLUMN 'fecha'
    */
    
    @Basic(optional = false)
    @Column(name = "FECHA" , nullable=false)
    private java.sql.Timestamp fecha;
    /** 
    * Map the relation to evidencia table where has id_seguimiento object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seguimiento")
    private List<Evidencia> evidenciaHasSeguimientoList;
    

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Seguimiento() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdSeguimiento() { return this.idSeguimiento;}
    public void setIdSeguimiento(Integer v) { this.idSeguimiento = v; }
    
    public String getMensajeRespuesta() { return this.mensajeRespuesta;}
    public void setMensajeRespuesta(String v) { this.mensajeRespuesta = v; }
    
    public Area getAreaResponsable(){ return this.areaResponsable ; }
    public void setAreaResponsable(Area x){ this.areaResponsable = x; }
    
    public ResponsableEstacion getResponsableEstacion(){ return this.responsableEstacion ; }
    public void setResponsableEstacion(ResponsableEstacion x){ this.responsableEstacion = x; }
    
    public ProveedorEstacion getProveedorEstacion(){ return this.proveedorEstacion ; }
    public void setProveedorEstacion(ProveedorEstacion x){ this.proveedorEstacion = x; }
    
    public ContactoProveedorEstacion getContactoProveedorEstacion(){ return this.contactoProveedorEstacion ; }
    public void setContactoProveedorEstacion(ContactoProveedorEstacion x){ this.contactoProveedorEstacion = x; }
    
    public Reporte getReporte(){ return this.reporte ; }
    public void setReporte(Reporte x){ this.reporte = x; }
    
    public java.sql.Timestamp getFecha() { return this.fecha;}
    public void setFecha(java.sql.Timestamp v) { this.fecha = v; }
    
    // O2M <*>    
    public List<Evidencia> getEvidenciaHasSeguimientoList(){ return this.evidenciaHasSeguimientoList; }
    public void setEvidenciaHasSeguimientoList(List<Evidencia> v){ this.evidenciaHasSeguimientoList = v; }
    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idSeguimiento).hashCode();
		hash += String.valueOf(mensajeRespuesta).hashCode();
		hash += String.valueOf(areaResponsable).hashCode();
		hash += String.valueOf(responsableEstacion).hashCode();
		hash += String.valueOf(proveedorEstacion).hashCode();
		hash += String.valueOf(contactoProveedorEstacion).hashCode();
		hash += String.valueOf(reporte).hashCode();
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
        if (!(o instanceof Seguimiento)) {
            return false;
        }		
		Seguimiento other = (Seguimiento ) o;
		if (!Objects.equals(this.idSeguimiento, other.idSeguimiento)) { return false; }		
		if (!Objects.equals(this.mensajeRespuesta, other.mensajeRespuesta)) { return false; }		
		if (!Objects.equals(this.areaResponsable, other.areaResponsable)) { return false; }		
		if (!Objects.equals(this.responsableEstacion, other.responsableEstacion)) { return false; }		
		if (!Objects.equals(this.proveedorEstacion, other.proveedorEstacion)) { return false; }		
		if (!Objects.equals(this.contactoProveedorEstacion, other.contactoProveedorEstacion)) { return false; }		
		if (!Objects.equals(this.reporte, other.reporte)) { return false; }		
		if (!Objects.equals(this.fecha, other.fecha)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Seguimiento{");
		sb.append("idSeguimiento" ).append("=").append(idSeguimiento).append("|");
		sb.append("mensajeRespuesta" ).append("=").append(mensajeRespuesta).append("|");
		sb.append("areaResponsable" ).append("=").append(areaResponsable).append("|");
		sb.append("responsableEstacion" ).append("=").append(responsableEstacion).append("|");
		sb.append("proveedorEstacion" ).append("=").append(proveedorEstacion).append("|");
		sb.append("contactoProveedorEstacion" ).append("=").append(contactoProveedorEstacion).append("|");
		sb.append("reporte" ).append("=").append(reporte).append("|");
		sb.append("fecha" ).append("=").append(fecha).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idSeguimiento).append(" ");

		return sb.toString().trim();
	}

}
