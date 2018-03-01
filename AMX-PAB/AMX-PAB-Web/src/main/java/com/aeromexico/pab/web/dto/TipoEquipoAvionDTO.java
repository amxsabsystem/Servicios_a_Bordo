package com.aeromexico.pab.web.dto;

/**
 *
 * @author Alfredo Estrada
 */
public class TipoEquipoAvionDTO {
    private Integer idTipoEquipoAvion;
    private String tipoEquipo;
    private String modeloAvion_modelo;
    private Short status;

    public TipoEquipoAvionDTO() {
    }

    public TipoEquipoAvionDTO(Integer idTipoEquipoAvion, String tipoEquipo, String modeloAvion_modelo, Short status) {
        this.idTipoEquipoAvion = idTipoEquipoAvion;
        this.tipoEquipo = tipoEquipo;
        this.modeloAvion_modelo = modeloAvion_modelo;
        this.status = status;
    }
    
    /**
     * @return the idTipoEquipoAvion
     */
    public Integer getIdTipoEquipoAvion() {
        return idTipoEquipoAvion;
    }

    /**
     * @param idTipoEquipoAvion the idTipoEquipoAvion to set
     */
    public void setIdTipoEquipoAvion(Integer idTipoEquipoAvion) {
        this.idTipoEquipoAvion = idTipoEquipoAvion;
    }

    /**
     * @return the tipoEquipo
     */
    public String getTipoEquipo() {
        return tipoEquipo;
    }

    /**
     * @param tipoEquipo the tipoEquipo to set
     */
    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    /**
     * @return the modeloAvion_modelo
     */
    public String getModeloAvion_modelo() {
        return modeloAvion_modelo;
    }

    /**
     * @param modeloAvion_modelo the modeloAvion_modelo to set
     */
    public void setModeloAvion_modelo(String modeloAvion_modelo) {
        this.modeloAvion_modelo = modeloAvion_modelo;
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
