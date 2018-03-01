package com.aeromexico.pab.web.jsf.sab.operaciones;

import com.aeromexico.pab.entity.Producto;
import com.aeromexico.pab.entity.TipoEquipoAvion;

/**
 *
 * @author Erick Diaz
 */
public class EquiposProducto {
    
    public EquiposProducto(){}
    
    private String idequipoProducto;
    private TipoEquipoAvion equipo;
    private Producto producto;
    private boolean isChecked;

    public String getIdequipoProducto() {
        return idequipoProducto;
    }

    public void setIdequipoProducto(String idequipoProducto) {
        this.idequipoProducto = idequipoProducto;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public TipoEquipoAvion getEquipo() {
        return equipo;
    }

    public void setEquipo(TipoEquipoAvion equipo) {
        this.equipo = equipo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    
    
}
