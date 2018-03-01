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
public class CicloMes {

    public CicloMes() {
    }

    private Integer idCiclo;
    private Parametro idOrigenvuelo;
    private Parametro idTipociclo;
    private Region region;
    private Estacion estacion;
    private Proveedor proveedor;
    private Short dobleAbastecido;

    private ConfiguracionCiclo cicloEnero;
    private ConfiguracionCiclo cicloFebrero;
    private ConfiguracionCiclo cicloMarzo;
    private ConfiguracionCiclo cicloAbril;
    private ConfiguracionCiclo cicloMayo;
    private ConfiguracionCiclo cicloJunio;
    private ConfiguracionCiclo cicloJulio;
    private ConfiguracionCiclo cicloAgosto;
    private ConfiguracionCiclo cicloSeptiembre;
    private ConfiguracionCiclo cicloOctubre;
    private ConfiguracionCiclo cicloNoviembre;
    private ConfiguracionCiclo cicloDiciembre;

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

    public ConfiguracionCiclo getCicloEnero() {
        return cicloEnero;
    }

    public void setCicloEnero(ConfiguracionCiclo cicloEnero) {
        this.cicloEnero = cicloEnero;
    }

    public ConfiguracionCiclo getCicloFebrero() {
        return cicloFebrero;
    }

    public void setCicloFebrero(ConfiguracionCiclo cicloFebrero) {
        this.cicloFebrero = cicloFebrero;
    }

    public ConfiguracionCiclo getCicloMarzo() {
        return cicloMarzo;
    }

    public void setCicloMarzo(ConfiguracionCiclo cicloMarzo) {
        this.cicloMarzo = cicloMarzo;
    }

    public ConfiguracionCiclo getCicloAbril() {
        return cicloAbril;
    }

    public void setCicloAbril(ConfiguracionCiclo cicloAbril) {
        this.cicloAbril = cicloAbril;
    }

    public ConfiguracionCiclo getCicloMayo() {
        return cicloMayo;
    }

    public void setCicloMayo(ConfiguracionCiclo cicloMayo) {
        this.cicloMayo = cicloMayo;
    }

    public ConfiguracionCiclo getCicloJunio() {
        return cicloJunio;
    }

    public void setCicloJunio(ConfiguracionCiclo cicloJunio) {
        this.cicloJunio = cicloJunio;
    }

    public ConfiguracionCiclo getCicloJulio() {
        return cicloJulio;
    }

    public void setCicloJulio(ConfiguracionCiclo cicloJulio) {
        this.cicloJulio = cicloJulio;
    }

    public ConfiguracionCiclo getCicloAgosto() {
        return cicloAgosto;
    }

    public void setCicloAgosto(ConfiguracionCiclo cicloAgosto) {
        this.cicloAgosto = cicloAgosto;
    }

    public ConfiguracionCiclo getCicloSeptiembre() {
        return cicloSeptiembre;
    }

    public void setCicloSeptiembre(ConfiguracionCiclo cicloSeptiembre) {
        this.cicloSeptiembre = cicloSeptiembre;
    }

    public ConfiguracionCiclo getCicloOctubre() {
        return cicloOctubre;
    }

    public void setCicloOctubre(ConfiguracionCiclo cicloOctubre) {
        this.cicloOctubre = cicloOctubre;
    }

    public ConfiguracionCiclo getCicloNoviembre() {
        return cicloNoviembre;
    }

    public void setCicloNoviembre(ConfiguracionCiclo cicloNoviembre) {
        this.cicloNoviembre = cicloNoviembre;
    }

    public ConfiguracionCiclo getCicloDiciembre() {
        return cicloDiciembre;
    }

    public void setCicloDiciembre(ConfiguracionCiclo cicloDiciembre) {
        this.cicloDiciembre = cicloDiciembre;
    }

}
