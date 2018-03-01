package com.aeromexico.pab.web.dto;

import com.aeromexico.pab.entity.CodigoServicio;
import com.aeromexico.pab.entity.DuracionVuelos;
import com.aeromexico.pab.entity.HorarioRegion;
import java.util.List;

/**
 *
 * @author Erick Diaz
 */
public class HorarioRegionDuracion {

    private HorarioRegion horarioRegion;
    private Integer idDuraciones;
    private List<CodigoServicio> codigoServicios;

    public HorarioRegion getHorarioRegion() {
        return horarioRegion;
    }

    public void setHorarioRegion(HorarioRegion horarioRegion) {
        this.horarioRegion = horarioRegion;
    }

    public Integer getIdDuraciones() {
        return idDuraciones;
    }

    public void setIdDuraciones(Integer idDuraciones) {
        this.idDuraciones = idDuraciones;
    }

    public List<CodigoServicio> getCodigoServicios() {
        return codigoServicios;
    }

    public void setCodigoServicios(List<CodigoServicio> codigoServicios) {
        this.codigoServicios = codigoServicios;
    }

    

}
