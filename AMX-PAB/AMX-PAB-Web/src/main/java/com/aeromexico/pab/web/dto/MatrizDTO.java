package com.aeromexico.pab.web.dto;

/**
 *
 * @author Alfredo Estrada
 */
public class MatrizDTO {
    private int idMatriz;
    private int producto_idProducto;
    private String producto_nombre;
    private int tipoEquipoAvion_idTipoEquipoAvion;
    private String tipoEquipoAvion_tipoEquipo;
    private int cantidadCj;
    private int cantidadCy;
    
    public MatrizDTO() {
    }

    /**
     * @return the idMatriz
     */
    public int getIdMatriz() {
        return idMatriz;
    }

    /**
     * @param idMatriz the idMatriz to set
     */
    public void setIdMatriz(int idMatriz) {
        this.idMatriz = idMatriz;
    }
    
    /**
     * @return the producto_idProducto
     */
    public int getProducto_idProducto() {
        return producto_idProducto;
    }

    /**
     * @param producto_idProducto the producto_idProducto to set
     */
    public void setProducto_idProducto(int producto_idProducto) {
        this.producto_idProducto = producto_idProducto;
    }

    /**
     * @return the producto_nombre
     */
    public String getProducto_nombre() {
        return producto_nombre;
    }

    /**
     * @param producto_nombre the producto_nombre to set
     */
    public void setProducto_nombre(String producto_nombre) {
        this.producto_nombre = producto_nombre;
    }

    /**
     * @return the tipoEquipoAvion_idTipoEquipoAvion
     */
    public int getTipoEquipoAvion_idTipoEquipoAvion() {
        return tipoEquipoAvion_idTipoEquipoAvion;
    }

    /**
     * @param tipoEquipoAvion_idTipoEquipoAvion the tipoEquipoAvion_idTipoEquipoAvion to set
     */
    public void setTipoEquipoAvion_idTipoEquipoAvion(int tipoEquipoAvion_idTipoEquipoAvion) {
        this.tipoEquipoAvion_idTipoEquipoAvion = tipoEquipoAvion_idTipoEquipoAvion;
    }

    /**
     * @return the tipoEquipoAvion_tipoEquipo
     */
    public String getTipoEquipoAvion_tipoEquipo() {
        return tipoEquipoAvion_tipoEquipo;
    }

    /**
     * @param tipoEquipoAvion_tipoEquipo the tipoEquipoAvion_tipoEquipo to set
     */
    public void setTipoEquipoAvion_tipoEquipo(String tipoEquipoAvion_tipoEquipo) {
        this.tipoEquipoAvion_tipoEquipo = tipoEquipoAvion_tipoEquipo;
    }

    /**
     * @return the cantidadCj
     */
    public int getCantidadCj() {
        return cantidadCj;
    }

    /**
     * @param cantidadCj the cantidadCj to set
     */
    public void setCantidadCj(int cantidadCj) {
        this.cantidadCj = cantidadCj;
    }

    /**
     * @return the cantidadCy
     */
    public int getCantidadCy() {
        return cantidadCy;
    }

    /**
     * @param cantidadCy the cantidadCy to set
     */
    public void setCantidadCy(int cantidadCy) {
        this.cantidadCy = cantidadCy;
    }

}
