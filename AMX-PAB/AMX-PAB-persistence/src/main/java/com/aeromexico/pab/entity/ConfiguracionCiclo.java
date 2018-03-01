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
 * Class for mapping JPA Entity of Table Configuracion_Ciclo.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

@Entity
@Table(name = "Configuracion_Ciclo")
@NamedQueries({
    @NamedQuery(name = "ConfiguracionCiclo.findAll", query = "SELECT c FROM ConfiguracionCiclo c")
    , @NamedQuery(name = "ConfiguracionCiclo.countAll", query = "SELECT COUNT(c) FROM ConfiguracionCiclo c")
    , @NamedQuery(name = "ConfiguracionCiclo.findByIdConfiguracionCiclo", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idConfiguracionCiclo = :idConfiguracionCiclo")
    , @NamedQuery(name = "ConfiguracionCiclo.findByciclo", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.ciclo = :ciclo")
    , @NamedQuery(name = "ConfiguracionCiclo.findByidMes", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idMes = :idMes")
    , @NamedQuery(name = "ConfiguracionCiclo.findByidPeriodicidad", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idPeriodicidad = :idPeriodicidad")
    , @NamedQuery(name = "ConfiguracionCiclo.findByidNociclo", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idNociclo = :idNociclo")
    , @NamedQuery(name = "ConfiguracionCiclo.findByidTemporada", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idTemporada = :idTemporada")
    , @NamedQuery(name = "ConfiguracionCiclo.findByidTipoQuincena", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idTipoQuincena = :idTipoQuincena")
    , @NamedQuery(name = "ConfiguracionCiclo.findByidTipoTemporada", query = "SELECT c FROM ConfiguracionCiclo c WHERE c.idTipoTemporada = :idTipoTemporada")
})
public class      ConfiguracionCiclo 
       implements java.io.Serializable 
                   
    {
    private static final long serialVersionUID = -522555608;
    
    /**
    * The 'id configuracion ciclo' Maps to COLUMN 'id_configuracion_ciclo'
    */
    
    @Id
    @Column(name = "ID_CONFIGURACION_CICLO" , nullable=false  )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idConfiguracionCiclo;
    
    /**
    * The 'id ciclo' Maps to COLUMN 'id_ciclo'
    */
    
    @JoinColumn(name = "ID_CICLO" , referencedColumnName = "ID_CICLO")
    @ManyToOne(optional = false )
    private Ciclo ciclo;
    
    /**
    * The 'id mes' Maps to COLUMN 'id_mes'
    */
    
    @JoinColumn(name = "ID_MES" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idMes;
    
    /**
    * The 'id periodicidad' Maps to COLUMN 'id_periodicidad'
    */
    
    @JoinColumn(name = "ID_PERIODICIDAD" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idPeriodicidad;
    
    /**
    * The 'id noCiclo' Maps to COLUMN 'id_noCiclo'
    */
    
    @JoinColumn(name = "ID_NOCICLO" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idNociclo;
    
    /**
    * The 'id temporada' Maps to COLUMN 'id_temporada'
    */
    
    @JoinColumn(name = "ID_TEMPORADA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idTemporada;
    
    /**
    * The 'id tipo quincena' Maps to COLUMN 'id_tipo_quincena'
    */
    
    @JoinColumn(name = "ID_TIPO_QUINCENA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idTipoQuincena;
    
    /**
    * The 'id tipo temporada' Maps to COLUMN 'id_tipo_temporada'
    */
    
    @JoinColumn(name = "ID_TIPO_TEMPORADA" , referencedColumnName = "ID_PARAMETRO")
    @ManyToOne(optional = true )
    private Parametro idTipoTemporada;

	// =========================================================================
    // =========================================================================
    /** 
     * Default Constructor
     */
    public ConfiguracionCiclo() {
    }
    
    /**
     * Getters and Setters
     */
    public Integer getIdConfiguracionCiclo() { return this.idConfiguracionCiclo;}
    public void setIdConfiguracionCiclo(Integer v) { this.idConfiguracionCiclo = v; }
    
    public Ciclo getCiclo(){ return this.ciclo ; }
    public void setCiclo(Ciclo x){ this.ciclo = x; }
    
    public Parametro getIdMes(){ return this.idMes ; }
    public void setIdMes(Parametro x){ this.idMes = x; }
    
    public Parametro getIdPeriodicidad(){ return this.idPeriodicidad ; }
    public void setIdPeriodicidad(Parametro x){ this.idPeriodicidad = x; }
    
    public Parametro getIdNociclo(){ return this.idNociclo ; }
    public void setIdNociclo(Parametro x){ this.idNociclo = x; }
    
    public Parametro getIdTemporada(){ return this.idTemporada ; }
    public void setIdTemporada(Parametro x){ this.idTemporada = x; }
    
    public Parametro getIdTipoQuincena(){ return this.idTipoQuincena ; }
    public void setIdTipoQuincena(Parametro x){ this.idTipoQuincena = x; }
    
    public Parametro getIdTipoTemporada(){ return this.idTipoTemporada ; }
    public void setIdTipoTemporada(Parametro x){ this.idTipoTemporada = x; }
    
    // O2M <*>    
	// M2M <*>

    @Override
    public int hashCode() {
        int hash = 0;
		hash += String.valueOf(idConfiguracionCiclo).hashCode();
		hash += String.valueOf(ciclo).hashCode();
		hash += String.valueOf(idMes).hashCode();
		hash += String.valueOf(idPeriodicidad).hashCode();
		hash += String.valueOf(idNociclo).hashCode();
		hash += String.valueOf(idTemporada).hashCode();
		hash += String.valueOf(idTipoQuincena).hashCode();
		hash += String.valueOf(idTipoTemporada).hashCode();
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
        if (!(o instanceof ConfiguracionCiclo)) {
            return false;
        }		
		ConfiguracionCiclo other = (ConfiguracionCiclo ) o;
		if (!Objects.equals(this.idConfiguracionCiclo, other.idConfiguracionCiclo)) { return false; }		
		if (!Objects.equals(this.ciclo, other.ciclo)) { return false; }		
		if (!Objects.equals(this.idMes, other.idMes)) { return false; }		
		if (!Objects.equals(this.idPeriodicidad, other.idPeriodicidad)) { return false; }		
		if (!Objects.equals(this.idNociclo, other.idNociclo)) { return false; }		
		if (!Objects.equals(this.idTemporada, other.idTemporada)) { return false; }		
		if (!Objects.equals(this.idTipoQuincena, other.idTipoQuincena)) { return false; }		
		if (!Objects.equals(this.idTipoTemporada, other.idTipoTemporada)) { return false; }		
    	return true;
    }

	/**
	* to-do override using commons
	*/
    public String toLargeString() {
		StringBuilder sb=new StringBuilder();
		sb.append("ConfiguracionCiclo{");
		sb.append("idConfiguracionCiclo" ).append("=").append(idConfiguracionCiclo).append("|");
		sb.append("ciclo" ).append("=").append(ciclo).append("|");
		sb.append("idMes" ).append("=").append(idMes).append("|");
		sb.append("idPeriodicidad" ).append("=").append(idPeriodicidad).append("|");
		sb.append("idNociclo" ).append("=").append(idNociclo).append("|");
		sb.append("idTemporada" ).append("=").append(idTemporada).append("|");
		sb.append("idTipoQuincena" ).append("=").append(idTipoQuincena).append("|");
		sb.append("idTipoTemporada" ).append("=").append(idTipoTemporada).append("|");
		//sb.append("serialVersionUID=").append(serialVersionUID).append("}");
        sb.append("}");
		return sb.toString();
	}

    @Override
    public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(" ").append(idConfiguracionCiclo).append(" ");

		return sb.toString().trim();
	}

}
