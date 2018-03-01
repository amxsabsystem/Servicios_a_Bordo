package com.aeromexico.pab.web.jsf.generales;

import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.entity.Ciudad;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Pais;
import com.aeromexico.pab.entity.Region;
import com.aeromexico.pab.web.dto.PaisDTO;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "paisesMB")
@SessionScoped
public class PaisesMB implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(PaisesMB.class.getName());

    public PaisesMB() {
    }

    private Pais row_selected = null;
    boolean modificarRegistro = false;
    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private List<PaisDTO> paisDTOList;
    private String estatusAnterior;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote servicioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;
    
    // Lazy-Inject
    EstadoCiudadMB estadoCiudadMB;
    
    // Lazy-Inject
    EstacionesMB estacionesMB;
    
    // Lazy-Inject
    LocaleInfo localeInfo;
    
    /**
     * initial values
     * 
     *
     */
    @PostConstruct
    public void init() {
        asignedRegion = -1;
        selectRegiones = new ArrayList<SelectItem>();
        selectRegiones.add(getLocaleInfo().createSelectIntKeyFirstItem());
        try {
            for (Region regs : regionesFacadeRemote.findAll()) {
                if (regs.getStatus().equals((short) 1)) {
                    selectRegiones.add(new SelectItem(regs.getIdRegion(), regs.getCveRegion()));
                }
            }
        } catch (Exception ex) {
        }
    }
    
    public List<PaisDTO> getPaisDTOList(){
        if(paisDTOList==null){
            try {                
                logger.info("->getPaisDTOList:servicioFacadeRemote="+servicioFacadeRemote);
                final List qr = genericSearchFacadeRemote.loadQuery("SELECT p.idPais,p.region.cveRegion,p.region.nombre,p.nombre,p.status FROM Pais p");
                
                paisDTOList = new ArrayList<PaisDTO>();
                for(Object r: qr){
                    Object[] or=(Object[])r;
                    PaisDTO row = new PaisDTO();
                    row.setIdPais          ((Integer) or[0]);
                    row.setRegion_cveRegion((String ) or[1]);
                    row.setRegion_nombre   ((String ) or[2]);
                    row.setNombre          ((String ) or[3]);
                    row.setStatus          ((Short  ) or[4]);
                    paisDTOList.add(row);
                }
            } catch (Exception ex) {
                logger.error("->getPaisDTOList:",ex);                
            }
        }
        return paisDTOList;
    }

    public Region getRegion(Integer IdRegion) {
        return regionesFacadeRemote.findByPK_EAGER(IdRegion);
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/paises?faces-redirect=true";
        paisDTOList = null;
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRow() {
        String redirect = "/portal/generales/paisesDetalle?faces-redirect=true";
        modificarRegistro = false;
        asignedRegion = -1;
        estatusAnterior =null;
        row_selected = new Pais();
        return redirect;
    }

    /**
     * validate on update and save
     * 
     * @param boton
     * @return 
     */
    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedRegion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Región");
            return false;
        }

        if (boton.equals("form:saveButton")) {
            for (Pais pais : servicioFacadeRemote.findAll()) {
                if (pais.getNombre().equals(this.row_selected.getNombre())
                        && asignedRegion == pais.getRegion().getIdRegion()) {
                    messgeValidateError(boton, "El país ya existe, para la región seleccionada");
                    return false;
                }
            }
        } else {
            for (Pais pais : servicioFacadeRemote.findAll()) {
                if (pais.getNombre().equals(this.row_selected.getNombre())
                        && asignedRegion == this.row_selected.getRegion().getIdRegion()
                        && estatusAnterior.equals(this.row_selected.getStatus() + "")) {
                    messgeValidateError(boton, "El país ya existe, para la región seleccionada");
                    return false;
                }
            }
        }
        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            if (validate("form:saveButton")) {
                row_selected.setStatus((short) 1);
                Region r = new Region();
                r.setIdRegion(asignedRegion);
                row_selected.setRegion(r);
                servicioFacadeRemote.create(row_selected);
                paisDTOList = null;
                getEstadoCiudadMB().init();
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public String modifyPaisRow(PaisDTO rr) {
        row_selected = servicioFacadeRemote.findByPK_EAGER(rr.getIdPais());
        return modifyRow(row_selected);
    }
    
    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Pais row_selected) {
        String redirect = "/portal/generales/paisesDetalle?faces-redirect=true";
        modificarRegistro = true;
        asignedRegion = row_selected.getRegion().getIdRegion();
        this.row_selected = row_selected;
        estatusAnterior = this.row_selected.getStatus() + "";
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {
            if (validate("form:updateButton")) {
                Region r = new Region();
                r.setIdRegion(asignedRegion);
                row_selected.setRegion(r);
                servicioFacadeRemote.update(row_selected);
                //update on cascade dependencies
                try {
                    if (row_selected.getStatus() == (short) 0) {
                        for (Ciudad ciudad : estadoCiudadFacadeRemote.findAll()) {
                            if (ciudad.getPais().getIdPais() == row_selected.getIdPais()) {
                                ciudad.setStatus((short) 0);
                                estadoCiudadFacadeRemote.update(ciudad);

                                for (Estacion estacion : estacionesFacadeRemote.findAll()) {
                                    if (estacion.getCiudad().getIdCiudad() == ciudad.getIdCiudad()) {
                                        estacion.setStatus((short) 0);
                                        estacionesFacadeRemote.update(estacion);

                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                paisDTOList = null;
                getEstadoCiudadMB().init();
//                getEstacionesMB.init();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Pais getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Pais row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedRegion() {
        return asignedRegion;
    }

    public void setAsignedRegion(Integer asignedRegion) {
        this.asignedRegion = asignedRegion;
    }

    public List<SelectItem> getSelectRegiones() {
        return selectRegiones;
    }

    public void setSelectRegiones(List<SelectItem> selectRegiones) {
        this.selectRegiones = selectRegiones;
    }

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }
    
    public EstadoCiudadMB getEstadoCiudadMB() {
        if(estadoCiudadMB==null){
            estadoCiudadMB = (EstadoCiudadMB) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                    .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{estadoCiudadMB}", EstadoCiudadMB.class)
                    .getValue(FacesContext.getCurrentInstance().getELContext());
        }
        return estadoCiudadMB;
    }

    public EstacionesMB getEstacionesMB() {
        if(estacionesMB == null){
            estacionesMB = (EstacionesMB) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                    .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{estacionesMB}", EstacionesMB.class)
                    .getValue(FacesContext.getCurrentInstance().getELContext());
        }
        return estacionesMB;
    }

    public LocaleInfo getLocaleInfo() {
        if(localeInfo == null){
            localeInfo = (LocaleInfo) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                    .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{localeInfo}", LocaleInfo.class)
                    .getValue(FacesContext.getCurrentInstance().getELContext());
        }
        return localeInfo;
    }    
}
