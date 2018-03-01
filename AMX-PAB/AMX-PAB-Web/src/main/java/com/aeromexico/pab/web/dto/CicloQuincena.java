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
public class CicloQuincena {

    public CicloQuincena() {
    }

    private Integer idCiclo;
    private Parametro idOrigenvuelo;
    private Parametro idTipociclo;
    private Region region;
    private Estacion estacion;
    private Proveedor proveedor;
    private Short dobleAbastecido;

    private ConfiguracionCiclo cicloEneroQ1;
    private ConfiguracionCiclo cicloFebreroQ1;
    private ConfiguracionCiclo cicloMarzoQ1;
    private ConfiguracionCiclo cicloAbrilQ1;
    private ConfiguracionCiclo cicloMayoQ1;
    private ConfiguracionCiclo cicloJunioQ1;
    private ConfiguracionCiclo cicloJulioQ1;
    private ConfiguracionCiclo cicloAgostoQ1;
    private ConfiguracionCiclo cicloSeptiembreQ1;
    private ConfiguracionCiclo cicloOctubreQ1;
    private ConfiguracionCiclo cicloNoviembreQ1;
    private ConfiguracionCiclo cicloDiciembreQ1;

    private ConfiguracionCiclo cicloEneroQ2;
    private ConfiguracionCiclo cicloFebreroQ2;
    private ConfiguracionCiclo cicloMarzoQ2;
    private ConfiguracionCiclo cicloAbrilQ2;
    private ConfiguracionCiclo cicloMayoQ2;
    private ConfiguracionCiclo cicloJunioQ2;
    private ConfiguracionCiclo cicloJulioQ2;
    private ConfiguracionCiclo cicloAgostoQ2;
    private ConfiguracionCiclo cicloSeptiembreQ2;
    private ConfiguracionCiclo cicloOctubreQ2;
    private ConfiguracionCiclo cicloNoviembreQ2;
    private ConfiguracionCiclo cicloDiciembreQ2;

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

    public ConfiguracionCiclo getCicloEneroQ1() {
        return cicloEneroQ1;
    }

    public void setCicloEneroQ1(ConfiguracionCiclo cicloEneroQ1) {
        this.cicloEneroQ1 = cicloEneroQ1;
    }

    public ConfiguracionCiclo getCicloFebreroQ1() {
        return cicloFebreroQ1;
    }

    public void setCicloFebreroQ1(ConfiguracionCiclo cicloFebreroQ1) {
        this.cicloFebreroQ1 = cicloFebreroQ1;
    }

    public ConfiguracionCiclo getCicloMarzoQ1() {
        return cicloMarzoQ1;
    }

    public void setCicloMarzoQ1(ConfiguracionCiclo cicloMarzoQ1) {
        this.cicloMarzoQ1 = cicloMarzoQ1;
    }

    public ConfiguracionCiclo getCicloAbrilQ1() {
        return cicloAbrilQ1;
    }

    public void setCicloAbrilQ1(ConfiguracionCiclo cicloAbrilQ1) {
        this.cicloAbrilQ1 = cicloAbrilQ1;
    }

    public ConfiguracionCiclo getCicloMayoQ1() {
        return cicloMayoQ1;
    }

    public void setCicloMayoQ1(ConfiguracionCiclo cicloMayoQ1) {
        this.cicloMayoQ1 = cicloMayoQ1;
    }

    public ConfiguracionCiclo getCicloJunioQ1() {
        return cicloJunioQ1;
    }

    public void setCicloJunioQ1(ConfiguracionCiclo cicloJunioQ1) {
        this.cicloJunioQ1 = cicloJunioQ1;
    }

    public ConfiguracionCiclo getCicloJulioQ1() {
        return cicloJulioQ1;
    }

    public void setCicloJulioQ1(ConfiguracionCiclo cicloJulioQ1) {
        this.cicloJulioQ1 = cicloJulioQ1;
    }

    public ConfiguracionCiclo getCicloAgostoQ1() {
        return cicloAgostoQ1;
    }

    public void setCicloAgostoQ1(ConfiguracionCiclo cicloAgostoQ1) {
        this.cicloAgostoQ1 = cicloAgostoQ1;
    }

    public ConfiguracionCiclo getCicloSeptiembreQ1() {
        return cicloSeptiembreQ1;
    }

    public void setCicloSeptiembreQ1(ConfiguracionCiclo cicloSeptiembreQ1) {
        this.cicloSeptiembreQ1 = cicloSeptiembreQ1;
    }

    public ConfiguracionCiclo getCicloOctubreQ1() {
        return cicloOctubreQ1;
    }

    public void setCicloOctubreQ1(ConfiguracionCiclo cicloOctubreQ1) {
        this.cicloOctubreQ1 = cicloOctubreQ1;
    }

    public ConfiguracionCiclo getCicloNoviembreQ1() {
        return cicloNoviembreQ1;
    }

    public void setCicloNoviembreQ1(ConfiguracionCiclo cicloNoviembreQ1) {
        this.cicloNoviembreQ1 = cicloNoviembreQ1;
    }

    public ConfiguracionCiclo getCicloDiciembreQ1() {
        return cicloDiciembreQ1;
    }

    public void setCicloDiciembreQ1(ConfiguracionCiclo cicloDiciembreQ1) {
        this.cicloDiciembreQ1 = cicloDiciembreQ1;
    }

    public ConfiguracionCiclo getCicloEneroQ2() {
        return cicloEneroQ2;
    }

    public void setCicloEneroQ2(ConfiguracionCiclo cicloEneroQ2) {
        this.cicloEneroQ2 = cicloEneroQ2;
    }

    public ConfiguracionCiclo getCicloFebreroQ2() {
        return cicloFebreroQ2;
    }

    public void setCicloFebreroQ2(ConfiguracionCiclo cicloFebreroQ2) {
        this.cicloFebreroQ2 = cicloFebreroQ2;
    }

    public ConfiguracionCiclo getCicloMarzoQ2() {
        return cicloMarzoQ2;
    }

    public void setCicloMarzoQ2(ConfiguracionCiclo cicloMarzoQ2) {
        this.cicloMarzoQ2 = cicloMarzoQ2;
    }

    public ConfiguracionCiclo getCicloAbrilQ2() {
        return cicloAbrilQ2;
    }

    public void setCicloAbrilQ2(ConfiguracionCiclo cicloAbrilQ2) {
        this.cicloAbrilQ2 = cicloAbrilQ2;
    }

    public ConfiguracionCiclo getCicloMayoQ2() {
        return cicloMayoQ2;
    }

    public void setCicloMayoQ2(ConfiguracionCiclo cicloMayoQ2) {
        this.cicloMayoQ2 = cicloMayoQ2;
    }

    public ConfiguracionCiclo getCicloJunioQ2() {
        return cicloJunioQ2;
    }

    public void setCicloJunioQ2(ConfiguracionCiclo cicloJunioQ2) {
        this.cicloJunioQ2 = cicloJunioQ2;
    }

    public ConfiguracionCiclo getCicloJulioQ2() {
        return cicloJulioQ2;
    }

    public void setCicloJulioQ2(ConfiguracionCiclo cicloJulioQ2) {
        this.cicloJulioQ2 = cicloJulioQ2;
    }

    public ConfiguracionCiclo getCicloAgostoQ2() {
        return cicloAgostoQ2;
    }

    public void setCicloAgostoQ2(ConfiguracionCiclo cicloAgostoQ2) {
        this.cicloAgostoQ2 = cicloAgostoQ2;
    }

    public ConfiguracionCiclo getCicloSeptiembreQ2() {
        return cicloSeptiembreQ2;
    }

    public void setCicloSeptiembreQ2(ConfiguracionCiclo cicloSeptiembreQ2) {
        this.cicloSeptiembreQ2 = cicloSeptiembreQ2;
    }

    public ConfiguracionCiclo getCicloOctubreQ2() {
        return cicloOctubreQ2;
    }

    public void setCicloOctubreQ2(ConfiguracionCiclo cicloOctubreQ2) {
        this.cicloOctubreQ2 = cicloOctubreQ2;
    }

    public ConfiguracionCiclo getCicloNoviembreQ2() {
        return cicloNoviembreQ2;
    }

    public void setCicloNoviembreQ2(ConfiguracionCiclo cicloNoviembreQ2) {
        this.cicloNoviembreQ2 = cicloNoviembreQ2;
    }

    public ConfiguracionCiclo getCicloDiciembreQ2() {
        return cicloDiciembreQ2;
    }

    public void setCicloDiciembreQ2(ConfiguracionCiclo cicloDiciembreQ2) {
        this.cicloDiciembreQ2 = cicloDiciembreQ2;
    }

}
