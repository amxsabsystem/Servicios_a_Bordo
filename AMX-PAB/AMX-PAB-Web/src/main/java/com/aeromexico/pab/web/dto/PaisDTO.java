package com.aeromexico.pab.web.dto;

import java.io.Serializable;

/**
 *
 * @author Alfredo Estrada
 */
public class PaisDTO implements Serializable{
    private static final long serialVersionUID = 2003749084;
    private Integer idPais;
    private String nombre;
    private String region_nombre;
    private String region_cveRegion;
    private Short status;

    public PaisDTO() {
    }
    
    /**
     * @return the idPais
     */
    public Integer getIdPais() {
        return idPais;
    }

    /**
     * @param idPais the idPais to set
     */
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the region_nombre
     */
    public String getRegion_nombre() {
        return region_nombre;
    }

    /**
     * @param region_nombre the region_nombre to set
     */
    public void setRegion_nombre(String region_nombre) {
        this.region_nombre = region_nombre;
    }

    /**
     * @return the region_cveRegion
     */
    public String getRegion_cveRegion() {
        return region_cveRegion;
    }

    /**
     * @param region_cveRegion the region_cveRegion to set
     */
    public void setRegion_cveRegion(String region_cveRegion) {
        this.region_cveRegion = region_cveRegion;
    }

    /**
     * @return the status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Short status) {
        this.status = status;
    }

}
