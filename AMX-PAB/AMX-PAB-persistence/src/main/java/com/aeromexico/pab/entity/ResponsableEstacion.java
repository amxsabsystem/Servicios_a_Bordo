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
 * Class for mapping JPA Entity of Table responsable_estacion.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "responsable_estacion")
@NamedQueries({
    @NamedQuery(name = "ResponsableEstacion.findAll", query = "SELECT r FROM ResponsableEstacion r")
    , @NamedQuery(name = "ResponsableEstacion.countAll", query = "SELECT COUNT(r) FROM ResponsableEstacion r")
    , @NamedQuery(name = "ResponsableEstacion.findByIdResponsableEstacion", query = "SELECT r FROM ResponsableEstacion r WHERE r.idResponsableEstacion = :idResponsableEstacion")
    , @NamedQuery(name = "ResponsableEstacion.findByempleado", query = "SELECT r FROM ResponsableEstacion r WHERE r.empleado = :empleado")
    , @NamedQuery(name = "ResponsableEstacion.findByestacion", query = "SELECT r FROM ResponsableEstacion r WHERE r.estacion = :estacion")
    , @NamedQuery(name = "ResponsableEstacion.findByarea", query = "SELECT r FROM ResponsableEstacion r WHERE r.area = :area")
    , @NamedQuery(name = "ResponsableEstacion.findByempleadoJefe", query = "SELECT r FROM ResponsableEstacion r WHERE r.empleadoJefe = :empleadoJefe")
})
public class      ResponsableEstacion 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -695720447;
    
    /**
    * The 'id responsable estacion' Maps to COLUMN 'id_responsable_estacion'
    */
    
    @Id
    @Column(name = "ID_RESPONSABLE_ESTACION" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idResponsableEstacion;
    
    /**
    * The 'id empleado' Maps to COLUMN 'id_empleado'
    */
    
    @JoinColumn(name = "ID_EMPLEADO" , referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(optional = false )
    private Empleado empleado;
    
    /**
    * The 'id estacion' Maps to COLUMN 'id_estacion'
    */
    
    @JoinColumn(name = "ID_ESTACION" , referencedColumnName = "ID_ESTACION")
    @ManyToOne(optional = false )
    private Estacion estacion;
    
    /**
    * The 'id area' Maps to COLUMN 'id_area'
    */
    
    @JoinColumn(name = "ID_AREA" , referencedColumnName = "ID_AREA")
    @ManyToOne(optional = false )
    private Area area;
    
    /**
    * The 'id empleado jefe' Maps to COLUMN 'id_empleado_jefe'
    */
    
    @JoinColumn(name = "ID_EMPLEADO_JEFE" , referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(optional = false )
    private Empleado empleadoJefe;
    /** 
    * Map the relation to reporte table where has id_responsable_amx object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsableAmx")
    private List<Reporte> reporteHasIdResponsableAmxList;
    
    /** 
    * Map the relation to reporte table where has id_responsable_final_amx object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsableFinalAmx")
    private List<Reporte> reporteHasIdResponsableFinalAmxList;
    
    /** 
    * Map the relation to seguimiento table where has id_responsable_estacion object mapped column of for this class.
    */ 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsableEstacion")
    private List<Seguimiento> seguimientoHasResponsableEstacionList;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name               = "RESPONSABLE_PRODUCTO",
               joinColumns        = {@JoinColumn(name = "ID_RESPONSABLE_ESTACION", referencedColumnName ="ID_RESPONSABLE_ESTACION")},
               inverseJoinColumns = {@JoinColumn(name = "ID_TIPO_PRODUCTO_REPORTE", referencedColumnName ="ID_TIPO_PRODUCTO_REPORTE")}
               )
    private List<TipoProductoReporte> tipoProductoReporteList;
    
	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ResponsableEstacion() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdResponsableEstacion() { return this.idResponsableEstacion;}
    public void setIdResponsableEstacion(Integer v) { this.idResponsableEstacion = v; }
    
    public Empleado getEmpleado(){ return this.empleado ; }
    public void setEmpleado(Empleado x){ this.empleado = x; }
    
    public Estacion getEstacion(){ return this.estacion ; }
    public void setEstacion(Estacion x){ this.estacion = x; }
    
    public Area getArea(){ return this.area ; }
    public void setArea(Area x){ this.area = x; }
    
    public Empleado getEmpleadoJefe(){ return this.empleadoJefe ; }
    public void setEmpleadoJefe(Empleado x){ this.empleadoJefe = x; }
    
    // O2M <*>    
    public List<Reporte> getReporteHasIdResponsableAmxList(){ return this.reporteHasIdResponsableAmxList; }
    public void setReporteHasIdResponsableAmxList(List<Reporte> v){ this.reporteHasIdResponsableAmxList = v; }
    
    public List<Reporte> getReporteHasIdResponsableFinalAmxList(){ return this.reporteHasIdResponsableFinalAmxList; }
    public void setReporteHasIdResponsableFinalAmxList(List<Reporte> v){ this.reporteHasIdResponsableFinalAmxList = v; }
    
    public List<Seguimiento> getSeguimientoHasResponsableEstacionList(){ return this.seguimientoHasResponsableEstacionList; }
    public void setSeguimientoHasResponsableEstacionList(List<Seguimiento> v){ this.seguimientoHasResponsableEstacionList = v; }
    
	// M2M <*>
    public List<TipoProductoReporte> getTipoProductoReporteList() { return this.tipoProductoReporteList; }
    public void setTipoProductoReporteList(List<TipoProductoReporte>  v) { this.tipoProductoReporteList = v; }
    

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idResponsableEstacion).hashCode();
		hash += String.valueOf(empleado).hashCode();
		hash += String.valueOf(estacion).hashCode();
		hash += String.valueOf(area).hashCode();
		hash += String.valueOf(empleadoJefe).hashCode();
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
        if (!(o instanceof ResponsableEstacion)) {
            return false;
        }		
		ResponsableEstacion other = (ResponsableEstacion ) o;
		if (!Objects.equals(this.idResponsableEstacion, other.idResponsableEstacion)) { return false; }		
		if (!Objects.equals(this.empleado, other.empleado)) { return false; }		
		if (!Objects.equals(this.estacion, other.estacion)) { return false; }		
		if (!Objects.equals(this.area, other.area)) { return false; }		
		if (!Objects.equals(this.empleadoJefe, other.empleadoJefe)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ResponsableEstacion{");
		sb.append("idResponsableEstacion" ).append("=").append(idResponsableEstacion).append("|");
		sb.append("empleado" ).append("=").append(empleado).append("|");
		sb.append("estacion" ).append("=").append(estacion).append("|");
		sb.append("area" ).append("=").append(area).append("|");
		sb.append("empleadoJefe" ).append("=").append(empleadoJefe).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idResponsableEstacion).append(" ");

		return sb.toString().trim();
	}

}
