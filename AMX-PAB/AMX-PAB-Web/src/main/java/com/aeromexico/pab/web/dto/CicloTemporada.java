package com.aeromexico.pab.web.dto;

import com.aeromexico.pab.entity.ConfiguracionCiclo;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.Region;

/**
 *
 * @author Erick Diaz
 */
public class CicloTemporada {

    public CicloTemporada() {
    }

    private Integer idCiclo;
    private Parametro idOrigenvuelo;
    private Parametro idTipociclo;
    private Region region;
    private Estacion estacion;
    private Proveedor proveedor;
    private Short dobleAbastecido;

    private ConfiguracionCiclo cicloPrimaveraInicio;
    private ConfiguracionCiclo cicloPrimaveraFin;
    private ConfiguracionCiclo cicloVeranoInicio;
    private ConfiguracionCiclo cicloVeranoFin;
    private ConfiguracionCiclo cicloOtonoInicio;
    private ConfiguracionCiclo cicloOtonoFin;
    private ConfiguracionCiclo cicloInviernoInicio;
    private ConfiguracionCiclo cicloInviernoFin;

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Parametro getIdOrigenvuelo() {
        return idOrigenvuelo;
    }

    public void setIdOrigenvuelo(Parametro idOrigenvuelo) {
        this.idOrigenvuelo = idOrigenvuelo;
    }

    public Parametro getIdTipociclo() {
        return idTipociclo;
    }

    public void setIdTipociclo(Parametro idTipociclo) {
        this.idTipociclo = idTipociclo;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Short getDobleAbastecido() {
        return dobleAbastecido;
    }

    public void setDobleAbastecido(Short dobleAbastecido) {
        this.dobleAbastecido = dobleAbastecido;
    }

    public ConfiguracionCiclo getCicloPrimaveraInicio() {
        return cicloPrimaveraInicio;
    }

    public void setCicloPrimaveraInicio(ConfiguracionCiclo cicloPrimaveraInicio) {
        this.cicloPrimaveraInicio = cicloPrimaveraInicio;
    }

    public ConfiguracionCiclo getCicloPrimaveraFin() {
        return cicloPrimaveraFin;
    }

    public void setCicloPrimaveraFin(ConfiguracionCiclo cicloPrimaveraFin) {
        this.cicloPrimaveraFin = cicloPrimaveraFin;
    }

    public ConfiguracionCiclo getCicloVeranoInicio() {
        return cicloVeranoInicio;
    }

    public void setCicloVeranoInicio(ConfiguracionCiclo cicloVeranoInicio) {
        this.cicloVeranoInicio = cicloVeranoInicio;
    }

    public ConfiguracionCiclo getCicloVeranoFin() {
        return cicloVeranoFin;
    }

    public void setCicloVeranoFin(ConfiguracionCiclo cicloVeranoFin) {
        this.cicloVeranoFin = cicloVeranoFin;
    }

    public ConfiguracionCiclo getCicloOtonoInicio() {
        return cicloOtonoInicio;
    }

    public void setCicloOtonoInicio(ConfiguracionCiclo cicloOtonoInicio) {
        this.cicloOtonoInicio = cicloOtonoInicio;
    }

    public ConfiguracionCiclo getCicloOtonoFin() {
        return cicloOtonoFin;
    }

    public void setCicloOtonoFin(ConfiguracionCiclo cicloOtonoFin) {
        this.cicloOtonoFin = cicloOtonoFin;
    }

    public ConfiguracionCiclo getCicloInviernoInicio() {
        return cicloInviernoInicio;
    }

    public void setCicloInviernoInicio(ConfiguracionCiclo cicloInviernoInicio) {
        this.cicloInviernoInicio = cicloInviernoInicio;
    }

    public ConfiguracionCiclo getCicloInviernoFin() {
        return cicloInviernoFin;
    }

    public void setCicloInviernoFin(ConfiguracionCiclo cicloInviernoFin) {
        this.cicloInviernoFin = cicloInviernoFin;
    }

}
