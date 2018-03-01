package com.aeromexico.pab.web.dto;

import java.io.Serializable;

/**
 *
 * @author Alfredo Estrada
 */
public class RegionDTO implements Serializable{
    private static final long serialVersionUID = 933699210;
    private Integer idRegion;
    private String cveRegion;
    private String nombre;
    private Short status;

    public RegionDTO() {
    }

    /**
     * @return the idRegion
     */
    public Integer getIdRegion() {
        return idRegion;
    }

    /**
     * @param idRegion the idRegion to set
     */
    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    /**
     * @return the cveRegion
     */
    public String getCveRegion() {
        return cveRegion;
    }

    /**
     * @param cveRegion the cveRegion to set
     */
    public void setCveRegion(String cveRegion) {
        this.cveRegion = cveRegion;
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
