package com.aeromexico.pab.web.dto;

import java.util.List;

/**
 *
 * @author Erick Diaz
 */
public class AsignacionServicioHorariosDuracion {
    
    private Integer idAsignacionServicio;
    private Integer idModeloAvion;
    private Integer idOrigen;
    private Integer idClase;
    private Integer idRegion;
    private List<HorarioRegionDuracion> horarioRegionDuracion;

    public Integer getIdAsignacionServicio() {
        return idAsignacionServicio;
    }

    public void setIdAsignacionServicio(Integer idAsignacionServicio) {
        this.idAsignacionServicio = idAsignacionServicio;
    }

    public Integer getIdModeloAvion() {
        return idModeloAvion;
    }

    public void setIdModeloAvion(Integer idModeloAvion) {
        this.idModeloAvion = idModeloAvion;
    }

    public Integer getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Integer idOrigen) {
        this.idOrigen = idOrigen;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public List<HorarioRegionDuracion> getHorarioRegionDuracion() {
        return horarioRegionDuracion;
    }

    public void setHorarioRegionDuracion(List<HorarioRegionDuracion> horarioRegionDuracion) {
        this.horarioRegionDuracion = horarioRegionDuracion;
    }
    
    
}
