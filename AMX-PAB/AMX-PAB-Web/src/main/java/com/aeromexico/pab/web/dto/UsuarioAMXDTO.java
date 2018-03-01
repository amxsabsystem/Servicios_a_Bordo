package com.aeromexico.pab.web.dto;

/**
 *
 * @author Erick Diaz
 */
public class UsuarioAMXDTO {
    
    public UsuarioAMXDTO(){}
    
    private String emailUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String claveArea;
    private String telefono;
    private String extension;
    private String puestoEsp;
    private String puestoEng;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getClaveArea() {
        return claveArea;
    }

    public void setClaveArea(String claveArea) {
        this.claveArea = claveArea;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPuestoEsp() {
        return puestoEsp;
    }

    public void setPuestoEsp(String puestoEsp) {
        this.puestoEsp = puestoEsp;
    }

    public String getPuestoEng() {
        return puestoEng;
    }

    public void setPuestoEng(String puestoEng) {
        this.puestoEng = puestoEng;
    }
    
    
    
}
