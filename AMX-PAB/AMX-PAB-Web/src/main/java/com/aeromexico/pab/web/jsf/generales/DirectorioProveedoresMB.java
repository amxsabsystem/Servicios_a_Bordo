package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorEstacionFacadeRemote;
import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.ProveedorEstacion;
import com.aeromexico.pab.entity.TipoProveedor;
import com.aeromexico.pab.entity.Usuario;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "directorioProveedoresMB")
@SessionScoped
public class DirectorioProveedoresMB implements Serializable {

    private Usuario row_selected = null;
    boolean modificarRegistro = false;

    private List<ContactoProveedorEstacion> all_proveedores;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/contactoProveedorEstacion_RSB")
    ContactoProveedorEstacionFacadeRemote contactoProveedorEstacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedorEstacion_RSB")
    ProveedorEstacionFacadeRemote proveedorEstacionFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    @PostConstruct
    public void init() {

    }

    public List<ContactoProveedorEstacion> findAllProveedores() {
        if (all_proveedores == null) {
            try {
                all_proveedores = new ArrayList<ContactoProveedorEstacion>();
                for (ContactoProveedorEstacion contacto : contactoProveedorEstacionFacadeRemote.findAll()) {
                    if (contacto.getDirectorioPab().equals((short) 1)) {
                        all_proveedores.add(contacto);
                    }
                }
            } catch (Exception ex) {
                all_proveedores = null;
            }
        }
        return all_proveedores;
    }

    public String listaTiposProveedor(Integer idProveedorEstacion) {
        String retorno = "";
        ProveedorEstacion pe = proveedorEstacionFacadeRemote.findByPK_EAGER(idProveedorEstacion);
        List<TipoProveedor> tipoProveedorList = pe.getTipoProveedorList();
        for (TipoProveedor tp : tipoProveedorList) {
         
            retorno += (localeInfo.getLanguage().equals("es") ? tp.getNombreEs() : tp.getNombreEn()) + " ";
        }
        return retorno;
    }


    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/directorioProveedores?faces-redirect=true";
        all_proveedores = null;
        findAllProveedores();
        return redirect;
    }

    public Usuario getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Usuario row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public List<ContactoProveedorEstacion> getAll_proveedores() {
        return all_proveedores;
    }

    public void setAll_proveedores(List<ContactoProveedorEstacion> all_proveedores) {
        this.all_proveedores = all_proveedores;
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

}
