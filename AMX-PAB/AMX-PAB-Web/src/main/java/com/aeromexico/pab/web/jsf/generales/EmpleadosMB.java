package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.AreaFacadeRemote;
import com.aeromexico.pab.backend.remote.CompaniaFacadeRemote;
import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;
import com.aeromexico.pab.entity.Area;
import com.aeromexico.pab.entity.Compania;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Usuario;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "empleadosMB")
@SessionScoped
public class EmpleadosMB implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(EmpleadosMB.class.getName());
    
    private Empleado row_selected = null;
    boolean modificarRegistro = false;
    private Integer asignedArea;
    private List<SelectItem> selectAreas;
    private Integer asignedEstacion;
    private List<SelectItem> selectEstaciones;
    private Integer asignedCompania;
    private List<SelectItem> selectCompanias;
    boolean directorioSab = false;
    private List<Empleado> all_records;

    private String emailAntes;
    private String email;
    private String nombre;
    private String apellidoPat;
    private String apellidomat;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/empleado_RSB")
    EmpleadoFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/area_RSB")
    AreaFacadeRemote areasDeSABFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/compania_RSB")
    CompaniaFacadeRemote companiaFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/usuario_RSB")
    UsuarioFacadeRemote usuarioFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    @Inject
    @Named("directorioAMXMB")
    DirectorioAMXMB directorioAMXMB;

    public EmpleadosMB() {
    }
    
    @PostConstruct
    public void init() {
        directorioSab = false;
        asignedArea = -1;
        asignedEstacion = -1;
        asignedCompania = -1;

        selectAreas = new ArrayList<SelectItem>();
        selectAreas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Area regs : areasDeSABFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectAreas.add(new SelectItem(regs.getIdArea(),
                            localeInfo.getLanguage().equals("es") ? regs.getNombreEs() : regs.getNombreEn()
                    ));
                }
            }
        } catch (Exception ex) {
        }

        selectEstaciones = new ArrayList<SelectItem>();
        selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Estacion regs : estacionesFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectEstaciones.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata() + " - " + regs.getNombre()));
                }
            }
        } catch (Exception ex) {

        }

        selectCompanias = new ArrayList<SelectItem>();
        selectCompanias.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            for (Compania regs : companiaFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectCompanias.add(new SelectItem(regs.getIdCompania(), regs.getNombre()));
                }
            }
        } catch (Exception ex) {

        }

    }

    public List<Empleado> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<Empleado>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public Area getArea(int idArea) {
        return areasDeSABFacadeRemote.findByPK(idArea);
    }

    public Estacion getEstacion(int idEstacion) {
        return estacionesFacadeRemote.findByPK(idEstacion);
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/empleados?faces-redirect=true";
        all_records = null;
        findAll();
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRow() {
        String redirect = "/portal/generales/empleadosDetalle?faces-redirect=true";

        modificarRegistro = false;
        directorioSab = false;
        asignedArea = -1;
        asignedCompania = -1;
        asignedEstacion = -1;
        nombre = null;
        apellidoPat = null;
        apellidomat = null;
        email = null;
        row_selected = new Empleado();
        return redirect;
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedArea == -1) {
            messgeValidateError(boton, "Debe seleccionar una Area");
            return false;
        }
        if (asignedEstacion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Estación");
            return false;
        }
        if (asignedCompania == -1) {
            messgeValidateError(boton, "Debe seleccionar una Compañia");
            return false;
        }

        if (boton.equals("form:saveButton") && usuarioFacadeRemote.findByPK(email) != null) {
            messgeValidateError(boton, "El correo ya existe");
            return false;
        }

        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            if (validate("form:saveButton")) {
                logger.debug("-------------->>>save");
                row_selected.setStatus((short) 1);
                row_selected.setDirectorioSab(directorioSab ? (short) 1 : (short) 0);
                Area a = new Area();
                a.setIdArea(asignedArea);
                row_selected.setArea(a);
                Estacion e = new Estacion();
                e.setIdEstacion(asignedEstacion);
                row_selected.setEstacion(e);
                Compania c = new Compania();
                c.setIdCompania(asignedCompania);
                row_selected.setCompania(c);

                Usuario usuario = new Usuario();
                usuario.setEmailUsuario(email);
                usuario.setContrasenia("");
                usuario.setNombre(nombre);
                usuario.setApellidoPaterno(apellidoPat);
                usuario.setApellidoMaterno(apellidomat);
                usuario.setStatus((short) 1);

                usuario = usuarioFacadeRemote.create(usuario);
                logger.debug("-------------->>>saved: usuario:"+usuario.toString());
                
                row_selected.setUsuario(usuario);

                servicioFacadeRemote.create(row_selected);
                logger.debug("-------------->>>saved??: emeplado:"+row_selected.toString());
                
                asignedArea = -1;
                asignedCompania = -1;
                asignedEstacion = -1;
                email = null;
                nombre = null;
                apellidoPat = null;
                apellidomat = null;
                directorioAMXMB.setAll_records(null);
                directorioAMXMB.findAllUsers();
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Empleado row_selected) {
        String redirect = "/portal/generales/empleadosDetalle?faces-redirect=true";
        modificarRegistro = true;

        this.row_selected = row_selected;
        emailAntes = this.row_selected.getUsuario().getEmailUsuario();
        email = this.row_selected.getUsuario().getEmailUsuario();
        nombre = this.row_selected.getUsuario().getNombre();
        apellidoPat = this.row_selected.getUsuario().getApellidoPaterno();
        apellidomat = this.row_selected.getUsuario().getApellidoMaterno();
        asignedArea = this.row_selected.getArea().getIdArea();
        asignedCompania = this.row_selected.getCompania().getIdCompania();
        asignedEstacion = this.row_selected.getEstacion().getIdEstacion();
        directorioSab = this.row_selected.getDirectorioSab() == (short) 1 ? true : false;
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            if (validate("form:updateButton")) {
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                row_selected.setDirectorioSab(directorioSab ? (short) 1 : (short) 0);
                Area a = new Area();
                a.setIdArea(asignedArea);
                row_selected.setArea(a);
                Estacion e = new Estacion();
                e.setIdEstacion(asignedEstacion);
                row_selected.setEstacion(e);
                Compania c = new Compania();
                c.setIdCompania(asignedCompania);
                row_selected.setCompania(c);

                if (!email.equals(emailAntes)) {
                    //baja el anterior Alta el nuevo   
                    Usuario usu = usuarioFacadeRemote.findByPK(email);
                    usu.setStatus((short) 0);
                    usuarioFacadeRemote.update(usu);

                    Usuario usuario = new Usuario();
                    usuario.setEmailUsuario(email);
                    usuario.setContrasenia("");
                    usuario.setNombre(nombre);
                    usuario.setApellidoPaterno(apellidoPat);
                    usuario.setApellidoMaterno(apellidomat);
                    usuario.setStatus((short) 1);
                    usuarioFacadeRemote.create(usuario);
                } else {
                    Usuario usu = usuarioFacadeRemote.findByPK(email);
                    usu.setNombre(nombre);
                    usu.setApellidoPaterno(apellidoPat);
                    usu.setApellidoMaterno(apellidomat);
                    usuarioFacadeRemote.update(usu);
                }

                servicioFacadeRemote.update(row_selected);
                all_records = null;
                directorioAMXMB.setAll_records(null);
                directorioAMXMB.findAllUsers();
                findAll();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Empleado getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Empleado row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedArea() {
        return asignedArea;
    }

    public void setAsignedArea(Integer asignedArea) {
        this.asignedArea = asignedArea;
    }

    public List<SelectItem> getSelectAreas() {
        return selectAreas;
    }

    public void setSelectAreas(List<SelectItem> selectAreas) {
        this.selectAreas = selectAreas;
    }

    public Integer getAsignedEstacion() {
        return asignedEstacion;
    }

    public void setAsignedEstacion(Integer asignedEstacion) {
        this.asignedEstacion = asignedEstacion;
    }

    public List<SelectItem> getSelectEstaciones() {
        return selectEstaciones;
    }

    public void setSelectEstaciones(List<SelectItem> selectEstaciones) {
        this.selectEstaciones = selectEstaciones;
    }

    public boolean isDirectorioSab() {
        return directorioSab;
    }

    public void setDirectorioSab(boolean directorioSab) {
        this.directorioSab = directorioSab;
    }

    public List<Empleado> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Empleado> all_records) {
        this.all_records = all_records;
    }

    public Integer getAsignedCompania() {
        return asignedCompania;
    }

    public void setAsignedCompania(Integer asignedCompania) {
        this.asignedCompania = asignedCompania;
    }

    public List<SelectItem> getSelectCompanias() {
        return selectCompanias;
    }

    public void setSelectCompanias(List<SelectItem> selectCompanias) {
        this.selectCompanias = selectCompanias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidomat() {
        return apellidomat;
    }

    public void setApellidomat(String apellidomat) {
        this.apellidomat = apellidomat;
    }

    public String getEmailAntes() {
        return emailAntes;
    }

    public void setEmailAntes(String emailAntes) {
        this.emailAntes = emailAntes;
    }

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

}
