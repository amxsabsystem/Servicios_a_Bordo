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
import com.aeromexico.pab.web.dto.RegionDTO;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "regionesMB")
@SessionScoped
public class RegionesMB implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(RegionesMB.class.getName());

    public RegionesMB() {}
    
    List<RegionDTO> regionDTOList;
    private Region row_selected = null;
    boolean modificarRegistro = false;
    private String claveAnterior = null;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote servicioFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;
    
    // Lazy-Inject
    PaisesMB paisesMB;
    
    // Lazy-Inject
    EstadoCiudadMB estadoCiudadMB;
    
    // Lazy-Inject
    EstacionesMB estacionesMB;
    
    // Lazy-Inject
    LocaleInfo localeInfo;
    
    public List<RegionDTO> getRegionDTOList(){
        if(regionDTOList==null){
            try {                
                logger.info("->findAll:servicioFacadeRemote="+servicioFacadeRemote);
                final List qr = genericSearchFacadeRemote.loadQuery("SELECT r.idRegion,r.cveRegion,r.nombre,r.status FROM Region r");
                
                regionDTOList = new ArrayList<RegionDTO>();
                for(Object r: qr){
                    Object[] or=(Object[])r;
                    RegionDTO row = new RegionDTO();
                    row.setIdRegion  ( (Integer) or[0]);
                    row.setCveRegion ( (String ) or[1]);
                    row.setNombre    ( (String ) or[2]);
                    row.setStatus    ( (Short  ) or[3]);
                    regionDTOList.add(row);
                }
            } catch (Exception ex) {
                logger.error("->getRegionRowList:",ex);                
            }
        }
        return regionDTOList;
    }

    /**
     * Go back to Master Page
     *
     * @return
     */
    public String returnMaster() {
        String redirect = "/portal/generales/regiones?faces-redirect=true";
        regionDTOList = null;        
        return redirect;
    }

    /**
     * Go to new Row Page Detail
     *
     * @return
     */
    public String addRow() {
        String redirect = "/portal/generales/regionesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected = new Region();
        claveAnterior = null;
        return redirect;
    }

    /**
     * validate Save and update
     *
     * @param boton
     * @return
     */
    public boolean validate(String boton) {
        boolean retorno = true;
        try {
            
            for (Region regs : servicioFacadeRemote.findAll()) {
                if (boton.equals("form:saveButton")) {
                    if (row_selected.getCveRegion().toUpperCase().equals(regs.getCveRegion().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Región ya existe");
                        retorno = false;
                        break;
                    }
                }
                if (boton.equals("form:updateButton")) {
                    if (!row_selected.getCveRegion().toUpperCase().equals(claveAnterior)
                            && row_selected.getCveRegion().toUpperCase().equals(regs.getCveRegion().toUpperCase())) {
                        messgeValidateError(boton, "La clave de Región ya existe");
                        retorno = false;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
        }

        return retorno;
    }

    /**
     * Save a new record
     */
    public void save() {
        try {
            if (validate("form:saveButton")) {
                String claveUpper = row_selected.getCveRegion().toUpperCase();
                row_selected.setCveRegion(claveUpper);
                row_selected.setStatus((short) 1);
                servicioFacadeRemote.create(row_selected);                
                getPaisesMB().init();
                getEstadoCiudadMB().init();
                getEstacionesMB().init();
                addRow();
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public String modifyRegionesRow(RegionDTO rr) {
        row_selected = servicioFacadeRemote.findByPK_EAGER(rr.getIdRegion());
        return modifyRow(row_selected);
    }
    /**
     * Go to edit Row selected Page Detail
     *
     * @param row_selected
     * @return
     */
    public String modifyRow(Region row_selected) {
        String redirect = "/portal/generales/regionesDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected = row_selected;
        claveAnterior = this.row_selected.getCveRegion();
        return redirect;
    }

    /**
     * Update selected record
     */
    public void update() {
        try {

            if (validate("form:updateButton")) {
                String claveUpper = row_selected.getCveRegion().toUpperCase();
                row_selected.setCveRegion(claveUpper);
                servicioFacadeRemote.update(row_selected);
                // update on cascade dependencies
                try {
                    final List<Pais> paisesList = paisesFacadeRemote.findAll();
                    final List<Estacion> estadionesList = estacionesFacadeRemote.findAll();
                    final List<Ciudad> ciudadList = estadoCiudadFacadeRemote.findAll();
                    if (row_selected.getStatus() == (short) 0) {
                        
                        for (Pais pais : paisesList) {
                            if (pais.getRegion().getIdRegion() == row_selected.getIdRegion()) {
                                pais.setStatus((short) 0);
                                paisesFacadeRemote.update(pais);
                                
                                for (Ciudad ciudad : ciudadList) {
                                    if (ciudad.getPais().getIdPais() == pais.getIdPais()) {
                                        ciudad.setStatus((short) 0);
                                        estadoCiudadFacadeRemote.update(ciudad);
                                        
                                        for (Estacion estacion : estadionesList) {
                                            if (estacion.getCiudad().getIdCiudad() == ciudad.getIdCiudad()) {
                                                estacion.setStatus((short) 0);
                                                estacionesFacadeRemote.update(estacion);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                getPaisesMB().init();
                getEstadoCiudadMB().init();
                getEstacionesMB().init();
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public Region getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Region row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }
    
    public PaisesMB getPaisesMB() {
        if(paisesMB==null){
            paisesMB = (PaisesMB) FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
                    .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{paisesMB}", PaisesMB.class)
                    .getValue(FacesContext.getCurrentInstance().getELContext());
        }
        return paisesMB;
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
