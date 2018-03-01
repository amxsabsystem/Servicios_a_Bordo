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
 * Class for mapping JPA Entity of Table evidencia.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "evidencia")
@NamedQueries({
    @NamedQuery(name = "Evidencia.findAll", query = "SELECT e FROM Evidencia e")
    , @NamedQuery(name = "Evidencia.countAll", query = "SELECT COUNT(e) FROM Evidencia e")
    , @NamedQuery(name = "Evidencia.findByIdEvidencia", query = "SELECT e FROM Evidencia e WHERE e.idEvidencia = :idEvidencia")
    , @NamedQuery(name = "Evidencia.findBymultimedio", query = "SELECT e FROM Evidencia e WHERE e.multimedio = :multimedio")
    , @NamedQuery(name = "Evidencia.findByseguimiento", query = "SELECT e FROM Evidencia e WHERE e.seguimiento = :seguimiento")
    , @NamedQuery(name = "Evidencia.findByreporte", query = "SELECT e FROM Evidencia e WHERE e.reporte = :reporte")
})
public class      Evidencia 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -1012912794;
    
    /**
    * The 'id evidencia' Maps to COLUMN 'id_evidencia'
    */
    
    @Id
    @Column(name = "ID_EVIDENCIA" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEvidencia;
    
    /**
    * The 'id multimedio' Maps to COLUMN 'id_multimedio'
    */
    
    @JoinColumn(name = "ID_MULTIMEDIO" , referencedColumnName = "ID_MULTIMEDIO")
    @ManyToOne(optional = false )
    private Multimedio multimedio;
    
    /**
    * The 'id seguimiento' Maps to COLUMN 'id_seguimiento'
    */
    
    @JoinColumn(name = "ID_SEGUIMIENTO" , referencedColumnName = "ID_SEGUIMIENTO")
    @ManyToOne(optional = true )
    private Seguimiento seguimiento;
    
    /**
    * The 'id reporte' Maps to COLUMN 'id_reporte'
    */
    
    @JoinColumn(name = "ID_REPORTE" , referencedColumnName = "ID_REPORTE")
    @ManyToOne(optional = false )
    private Reporte reporte;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public Evidencia() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdEvidencia() { return this.idEvidencia;}
    public void setIdEvidencia(Integer v) { this.idEvidencia = v; }
    
    public Multimedio getMultimedio(){ return this.multimedio ; }
    public void setMultimedio(Multimedio x){ this.multimedio = x; }
    
    public Seguimiento getSeguimiento(){ return this.seguimiento ; }
    public void setSeguimiento(Seguimiento x){ this.seguimiento = x; }
    
    public Reporte getReporte(){ return this.reporte ; }
    public void setReporte(Reporte x){ this.reporte = x; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idEvidencia).hashCode();
		hash += String.valueOf(multimedio).hashCode();
		hash += String.valueOf(seguimiento).hashCode();
		hash += String.valueOf(reporte).hashCode();
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
        if (!(o instanceof Evidencia)) {
            return false;
        }		
		Evidencia other = (Evidencia ) o;
		if (!Objects.equals(this.idEvidencia, other.idEvidencia)) { return false; }		
		if (!Objects.equals(this.multimedio, other.multimedio)) { return false; }		
		if (!Objects.equals(this.seguimiento, other.seguimiento)) { return false; }		
		if (!Objects.equals(this.reporte, other.reporte)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Evidencia{");
		sb.append("idEvidencia" ).append("=").append(idEvidencia).append("|");
		sb.append("multimedio" ).append("=").append(multimedio).append("|");
		sb.append("seguimiento" ).append("=").append(seguimiento).append("|");
		sb.append("reporte" ).append("=").append(reporte).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idEvidencia).append(" ");

		return sb.toString().trim();
	}

}
