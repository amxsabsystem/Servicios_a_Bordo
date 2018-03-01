package com.aeromexico.pab.web.jsf;

/**
 *
 * @author Erick Diaz
 */
public class Banner {

    public Banner() {
    }

    private String urlImagen;
    private String titulo;
    private String mensaje;
    private String clase;
    private String id_int;

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getId_int() {
        return id_int;
    }

    public void setId_int(String id_int) {
        this.id_int = id_int;
    }

}
