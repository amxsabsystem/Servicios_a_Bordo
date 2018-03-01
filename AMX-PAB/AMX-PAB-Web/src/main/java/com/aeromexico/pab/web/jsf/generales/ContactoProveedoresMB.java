package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoProveedorFacadeRemote;
import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;

import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Proveedor;
import com.aeromexico.pab.entity.ProveedorEstacion;
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

/**
 *
 * @author Erick Diaz
 */
@Named(value = "contactoProveedoresMB")
@SessionScoped
public class ContactoProveedoresMB implements Serializable {
    
    public ContactoProveedoresMB() {
    }
    
    private ContactoProveedorEstacion row_selected = null;
    boolean modificarRegistro = false;
    
    private String asignedProveedor;
    private List<SelectItem> selectProveedores;
    private Integer asignedEstacion;
    private List<SelectItem> selectEstaciones;
    private List<ContactoProveedorEstacion> all_records;
    
    private String labelClave;
    private String labelTipo;
    private String labelRegion;
    private String labelPais;
    private String labelCiudad;
    private String claveProveedor;
    
    private String emailAntes;
    private String email;
    private String nombre;
    private String apellidoPat;
    private String apellidomat;
    
    private boolean directorioSab;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/contactoProveedorEstacion_RSB")
    ContactoProveedorEstacionFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedor_RSB")
    ProveedorFacadeRemote proveedorCorporativoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoProveedor_RSB")
    TipoProveedorFacadeRemote tipoProveedorFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacioneFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/usuario_RSB")
    UsuarioFacadeRemote usuarioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedorEstacion_RSB")
    ProveedorEstacionFacadeRemote proveedorEstacionFacadeRemote;
    
    @Inject
    @Named("directorioProveedoresMB")
    DirectorioProveedoresMB directorioProveedores;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    /**
     * initial values
     */
    @PostConstruct
    public void init() {
        asignedProveedor = "-1";
        asignedEstacion = -1;
        
        selectProveedores = new ArrayList<SelectItem>();
        selectProveedores.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            for (Proveedor regs : proveedorCorporativoFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectProveedores.add(new SelectItem(regs.getClaveProveedor(), regs.getNombre()));
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
        
    }

    /**
     * Get all records
     *
     * @return
     */
    public List<ContactoProveedorEstacion> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<ContactoProveedorEstacion>();
                all_records = servicioFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }
    
    public void updateProveedorClave() {
        claveProveedor = asignedProveedor;
    }
    
    public void updateLabels() {
        
        labelRegion = regionesFacadeRemote.findByPK(paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(estacioneFacadeRemote.findByPK(asignedEstacion).getCiudad().getIdCiudad()).getPais().getIdPais()).getRegion().getIdRegion()).getNombre();
        labelPais = paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(estacioneFacadeRemote.findByPK(asignedEstacion).getCiudad().getIdCiudad()).getPais().getIdPais()).getNombre();
        labelCiudad = estadoCiudadFacadeRemote.findByPK(estacioneFacadeRemote.findByPK(asignedEstacion).getCiudad().getIdCiudad()).getNombre();
        labelClave = asignedProveedor == null ? "" : asignedProveedor + estacioneFacadeRemote.findByPK(asignedEstacion).getClaveIata();
        labelTipo = "";//tipoProveedorFacadeRemote.findByPK(asignedEstacion)
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/contactoProveedores?faces-redirect=true";
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
        String redirect = "/portal/generales/contactoProveedoresDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new ContactoProveedorEstacion();
        claveProveedor = null;
        asignedProveedor = null;
        asignedEstacion = -1;
        nombre = null;
        apellidoPat = null;
        apellidomat = null;
        email = null;
        labelCiudad = null;
        labelClave = null;
        labelPais = null;
        labelRegion = null;
        labelTipo = null;
        return redirect;
    }
    
    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedEstacion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Estación");
            return false;
        }
        if (asignedProveedor == null || asignedProveedor.equals("-1")) {
            messgeValidateError(boton, "Debe seleccionar un Proveedor");
            return false;
        }
        
        if (boton.equals("form:saveButton")) {
            if (usuarioFacadeRemote.findByPK(email) != null) {
                messgeValidateError(boton, "El correo ya existe");
                return false;
            }
        }
        
        try {
            ProveedorEstacion entity = new ProveedorEstacion();
            entity.setProveedor(proveedorCorporativoFacadeRemote.findByPK(asignedProveedor));
            entity.setEstacion(estacioneFacadeRemote.findByPK(asignedEstacion));
            if (proveedorEstacionFacadeRemote.findAllLike(entity).isEmpty()) {
                messgeValidateError(boton, "No existe el Proveedor Estación seleccionado");
                return false;
            }
        } catch (Exception ex) {
            messgeValidateError(boton, "No existe el Proveedor Estación seleccionado");
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
                row_selected.setUsuarioCreo(request.getUserPrincipal().getName());
                row_selected.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                row_selected.setUsuarioModifico(request.getUserPrincipal().getName());
                row_selected.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                
                row_selected.setStatus((short) 1);
                row_selected.setNombre(nombre);
                row_selected.setApellidoPaterno(apellidoPat);
                row_selected.setApellidoMaterno(apellidomat);
                row_selected.setDirectorioPab(directorioSab ? (short) 1 : (short) 0);
                row_selected.setProveedor(proveedorCorporativoFacadeRemote.findByPK(asignedProveedor));
                row_selected.setEstacion(estacioneFacadeRemote.findByPK(asignedEstacion));
                ProveedorEstacion entity = new ProveedorEstacion();
                entity.setEstacion(row_selected.getEstacion());
                entity.setProveedor(row_selected.getProveedor());
                ProveedorEstacion pe = new ProveedorEstacion();
                for (ProveedorEstacion pestacion : proveedorEstacionFacadeRemote.findAllLike(entity)) {
                    pe = pestacion;
                }
                row_selected.setProveedorEstacion(pe);
                
                Usuario usuario = new Usuario();
                usuario.setEmailUsuario(email);
                usuario.setContrasenia("");
                usuario.setNombre(nombre);
                usuario.setApellidoPaterno(apellidoPat);
                usuario.setApellidoMaterno(apellidomat);
                usuario.setStatus((short) 1);
                usuario.setUsuarioCreo(request.getUserPrincipal().getName());
                usuario.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                usuario.setUsuarioModifico(request.getUserPrincipal().getName());
                usuario.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                usuarioFacadeRemote.create(usuario);
                row_selected.setUsuario(usuario);
                /*
                for(ProveedorEstacion pe :proveedorEstacionFacadeRemote.findAll()){
                    if(pe.getEstacion().getIdEstacion() == asignedEstacion && pe.getProveedor().getClaveProveedor().equals(asignedProveedor))
                    row_selected.setProveedorEstacion(pe);
                }
                 */
                
                servicioFacadeRemote.create(row_selected);
                all_records = null;
                
                email = null;
                nombre = null;
                apellidoPat = null;
                apellidomat = null;
                
                claveProveedor = null;
                asignedProveedor = null;
                asignedEstacion = -1;
                labelCiudad = null;
                labelClave = null;
                labelPais = null;
                labelRegion = null;
                labelTipo = null;
                directorioProveedores.setAll_proveedores(null);
                directorioProveedores.findAllProveedores();
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
    public String modifyRow(ContactoProveedorEstacion row_selected) {
        String redirect = "/portal/generales/contactoProveedoresDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        emailAntes = this.row_selected.getUsuario().getEmailUsuario();
        asignedProveedor = this.row_selected.getProveedor().getClaveProveedor();
        asignedEstacion = this.row_selected.getEstacion().getIdEstacion();
        email = this.row_selected.getUsuario().getEmailUsuario();
        nombre = this.row_selected.getUsuario().getNombre();
        apellidoPat = this.row_selected.getUsuario().getApellidoPaterno();
        apellidomat = this.row_selected.getUsuario().getApellidoMaterno();
        directorioSab = this.row_selected.getDirectorioPab() == (short) 1 ? true : false;
        updateLabels();
        return redirect;
    }

    /*
	 * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                row_selected.setStatus((short) 1);
                row_selected.setDirectorioPab(directorioSab ? (short) 1 : (short) 0);
                Proveedor p = new Proveedor();
                p.setClaveProveedor(asignedProveedor);
                row_selected.setProveedor(p);
                
                Estacion e = new Estacion();
                e.setIdEstacion(asignedEstacion);
                row_selected.setEstacion(e);
                
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
                findAll();
                directorioProveedores.setAll_proveedores(null);
                directorioProveedores.findAllProveedores();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {
            
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }
    
    public ContactoProveedorEstacion getRow_selected() {
        return row_selected;
    }
    
    public void setRow_selected(ContactoProveedorEstacion row_selected) {
        this.row_selected = row_selected;
    }
    
    public boolean isModificarRegistro() {
        return modificarRegistro;
    }
    
    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }
    
    public String getAsignedProveedor() {
        return asignedProveedor;
    }
    
    public void setAsignedProveedor(String asignedProveedor) {
        this.asignedProveedor = asignedProveedor;
    }
    
    public List<SelectItem> getSelectProveedores() {
        return selectProveedores;
    }
    
    public void setSelectProveedores(List<SelectItem> selectProveedores) {
        this.selectProveedores = selectProveedores;
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
    
    public String getLabelClave() {
        return labelClave;
    }
    
    public void setLabelClave(String labelClave) {
        this.labelClave = labelClave;
    }
    
    public String getLabelRegion() {
        return labelRegion;
    }
    
    public void setLabelRegion(String labelRegion) {
        this.labelRegion = labelRegion;
    }
    
    public String getLabelPais() {
        return labelPais;
    }
    
    public void setLabelPais(String labelPais) {
        this.labelPais = labelPais;
    }
    
    public String getLabelCiudad() {
        return labelCiudad;
    }
    
    public void setLabelCiudad(String labelCiudad) {
        this.labelCiudad = labelCiudad;
    }
    
    public String getLabelTipo() {
        return labelTipo;
    }
    
    public void setLabelTipo(String labelTipo) {
        this.labelTipo = labelTipo;
    }
    
    public List<ContactoProveedorEstacion> getAll_records() {
        return all_records;
    }
    
    public void setAll_records(List<ContactoProveedorEstacion> all_records) {
        this.all_records = all_records;
    }
    
    public String getClaveProveedor() {
        return claveProveedor;
    }
    
    public void setClaveProveedor(String claveProveedor) {
        this.claveProveedor = claveProveedor;
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
    
    public boolean isDirectorioSab() {
        return directorioSab;
    }
    
    public void setDirectorioSab(boolean directorioSab) {
        this.directorioSab = directorioSab;
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
