package com.aeromexico.pab.web.dto;

/**
 *
 * @author Alfredo Estrada
 */
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private Short status;

    public ProductoDTO() {
    }

    public ProductoDTO(Integer idProducto, String nombre, Short status) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.status = status;
    }
    
    /**
     * @return the idProducto
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Short status) {
        this.status = status;
    }

}
