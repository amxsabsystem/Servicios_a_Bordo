package com.aeromexico.pab.web.dto;

/**
 *
 * @author Erick Diaz
 */
public class MasterMaterial {
    
    public MasterMaterial(){}
    
    private String numeroParte;
    private String descripcionEs;
    private String descripcionEn;
    private String estacionesNac;
    private String estacionesInter;
    private int cantidad;
    private double peso;
    private String um;
    private boolean isReciclable;
    private String observaciones;

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getDescripcionEs() {
        return descripcionEs;
    }

    public void setDescripcionEs(String descripcionEs) {
        this.descripcionEs = descripcionEs;
    }

    public String getDescripcionEn() {
        return descripcionEn;
    }

    public void setDescripcionEn(String descripcionEn) {
        this.descripcionEn = descripcionEn;
    }

    public String getEstacionesNac() {
        return estacionesNac;
    }

    public void setEstacionesNac(String estacionesNac) {
        this.estacionesNac = estacionesNac;
    }

    public String getEstacionesInter() {
        return estacionesInter;
    }

    public void setEstacionesInter(String estacionesInter) {
        this.estacionesInter = estacionesInter;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public boolean isIsReciclable() {
        return isReciclable;
    }

    public void setIsReciclable(boolean isReciclable) {
        this.isReciclable = isReciclable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    
}
