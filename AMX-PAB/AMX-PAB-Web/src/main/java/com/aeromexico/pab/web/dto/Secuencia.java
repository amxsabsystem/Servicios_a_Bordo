package com.aeromexico.pab.web.dto;

import com.aeromexico.pab.entity.CodigoServicio;
import java.util.List;

/**
 *
 * @author Erick Diaz
 */
public class Secuencia {
    private Integer idSecuencia;
    private String codigos_Servicio;
    private Integer idHorarioRegion;
    private Integer idDuracion;

    public Integer getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(Integer idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    public String getCodigos_Servicio() {
        return codigos_Servicio;
    }

    public void setCodigos_Servicio(String codigos_Servicio) {
        this.codigos_Servicio = codigos_Servicio;
    }


    public Integer getIdHorarioRegion() {
        return idHorarioRegion;
    }

    public void setIdHorarioRegion(Integer idHorarioRegion) {
        this.idHorarioRegion = idHorarioRegion;
    }

    public Integer getIdDuracion() {
        return idDuracion;
    }

    public void setIdDuracion(Integer idDuracion) {
        this.idDuracion = idDuracion;
    }
    
    
}
