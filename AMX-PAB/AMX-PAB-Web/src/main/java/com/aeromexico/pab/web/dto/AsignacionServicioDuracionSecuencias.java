package com.aeromexico.pab.web.dto;

import com.aeromexico.pab.entity.AsignacionServicio;
import com.aeromexico.pab.entity.CodigoServicio;
import com.aeromexico.pab.entity.DuracionVuelos;
import com.aeromexico.pab.entity.HorarioRegion;

/**
 *
 * @author Erick Diaz
 */
public class AsignacionServicioDuracionSecuencias {
    
    private Integer idAsignacionServicioDuracion;
    private CodigoServicio codigoServicio;
    private DuracionVuelos duracionVuelos;
    private AsignacionServicio asignacionServicio;
    private HorarioRegion horarioRegion;
    private String secuencia;

    public Integer getIdAsignacionServicioDuracion() {
        return idAsignacionServicioDuracion;
    }

    public void setIdAsignacionServicioDuracion(Integer idAsignacionServicioDuracion) {
        this.idAsignacionServicioDuracion = idAsignacionServicioDuracion;
    }

    public CodigoServicio getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(CodigoServicio codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public DuracionVuelos getDuracionVuelos() {
        return duracionVuelos;
    }

    public void setDuracionVuelos(DuracionVuelos duracionVuelos) {
        this.duracionVuelos = duracionVuelos;
    }

    public AsignacionServicio getAsignacionServicio() {
        return asignacionServicio;
    }

    public void setAsignacionServicio(AsignacionServicio asignacionServicio) {
        this.asignacionServicio = asignacionServicio;
    }

    public HorarioRegion getHorarioRegion() {
        return horarioRegion;
    }

    public void setHorarioRegion(HorarioRegion horarioRegion) {
        this.horarioRegion = horarioRegion;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }
    
    
    
}
