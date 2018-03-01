package com.aeromexico.pab.web.jsf.diseno;

import com.aeromexico.pab.backend.remote.CicloFacadeRemote;
import com.aeromexico.pab.backend.remote.ConfiguracionCicloFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.MaterialFacadeRemote;
import com.aeromexico.pab.backend.remote.ModeloAvionFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.backend.remote.TablaAbordamientoFacadeRemote;
import com.aeromexico.pab.backend.remote.TsuFacadeRemote;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Material;
import com.aeromexico.pab.entity.ModeloAvion;
import com.aeromexico.pab.entity.Pais;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Region;
import com.aeromexico.pab.entity.Tsu;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "tablasDeAbordamientoMB")
@SessionScoped
public class TablasDeAbordamientoMB implements Serializable{
    
    public TablasDeAbordamientoMB(){}
    
    private Empleado row_selected =null;
    boolean modificarRegistro =false;
    private List<Estacion> list_estaciones;
    private Integer asignedOrigen;
    private List<SelectItem> selectOrigenes;
    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private Parametro origenDesdeMexico;
    private Parametro origenHaciaMexico;
    private boolean checkTodasRegiones;
    private boolean checkTodasEstaciones;
    private Map<Integer, Integer> asignedEstaciones;
    private boolean checkTodosLosModelos;
    private List<ModeloAvion> list_modelos;
    private Map<Integer, Integer> asignedModelos;
    private List<Tsu> list_TSUs;
    private Map<Integer, Integer> asignedTSUs;
    private Map<Integer, Integer> asignedTSUsPercentage;
    private boolean checkNoAplicaTSU;
    
    private String alimento;
    private String porcentaje;
    private String asignedNoParteTSU;
    private String asignedPropiedadAMXMaterialExtra;
    private String propiedadComisariatoMaterialExtra;
    private String asignedNoParteMontaje;
    private String asignedPropiedadAMXMontaje;
    private String propiedadComisariatoMontaje;
    private String intruccionesNotas;
    private String servicioComplementario;
    private List<SelectItem> selectMateriales;
  
    private boolean checkNoAplicaSobreAbordaje;
    private String asignedNoParteSobreAbordaje;
    private String cantidadSobreAbordaje;
    private String instrucciones;
	
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tablaAbordamiento_RSB")	
	TablaAbordamientoFacadeRemote tablaAbordamientoFacadeRemote;
	@EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")	
	ParametroFacadeRemote parametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciclo_RSB")
    CicloFacadeRemote servicioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/configuracionCiclo_RSB")
    ConfiguracionCicloFacadeRemote configuracionCicloFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/modeloAvion_RSB")
    ModeloAvionFacadeRemote modeloAvionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tsu_RSB")
    TsuFacadeRemote tsuFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/material_RSB")
    MaterialFacadeRemote materialFacadeRemote;
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    
    @PostConstruct
    public void init() {
        selectOrigenes = new ArrayList<>();
        parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("origenVuelo") && regs.getStatus().equals((short) 1))).map((regs) -> {
            asignedOrigen = regs.getIdParametro();
            return regs;
        }).map((regs) -> {
            if(regs.getValorEn().equals("From Mexico"))
                origenDesdeMexico =regs;
            else
                origenHaciaMexico=regs;
            return regs;
        }).forEachOrdered((regs) -> {
            selectOrigenes.add(new SelectItem(regs.getIdParametro(),
                    (localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn())
            ));
        });

        updateRegiones();
        
        asignedEstaciones = new HashMap<>();
        asignedModelos = new HashMap<>();
        asignedTSUs = new HashMap<>();
        asignedTSUsPercentage = new HashMap<>();
        
        list_modelos = new ArrayList<>();
        try {
            modeloAvionFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                list_modelos.add(regs);
            });
        } catch (Exception ex) {
        }
        
        list_TSUs = new ArrayList<>();
        try {
            tsuFacadeRemote.findAll().stream().filter((tsu) -> (tsu.getStatus().equals((short) 1))).forEachOrdered((tsu) -> {
                list_TSUs.add(tsu);
            });
        } catch (Exception ex) {
        }
        
        
        asignedNoParteTSU = "-1";
        asignedPropiedadAMXMaterialExtra= "-1";
        asignedNoParteMontaje= "-1";
        asignedPropiedadAMXMontaje= "-1";
        
        selectMateriales = new ArrayList<SelectItem>();
        selectMateriales.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            for (Material regs : materialFacadeRemote.findAll()) {
                selectMateriales.add(new SelectItem(regs.getNumeroParte(), regs.getNumeroParte() + " - "
                        + (localeInfo.getLanguage().equals("es") ? regs.getDescripcionEs() : regs.getDescripcionEn())
                ));
            }
        } catch (Exception ex) {
        }
        
        asignedNoParteSobreAbordaje="-1";
        cantidadSobreAbordaje=null;
        instrucciones=null;
    }
    
    public List<Empleado> findAll(){
            List<Empleado> retorno=new ArrayList<>();
            return retorno;
    }
        
    /**
     * Go back to Master Page
     * @return 
     */
    public String returnMaster(){
		String	redirect=  "/portal/sab/diseno/consulta?faces-redirect=true";
                findAll();
		return redirect;
    }
    
    /**
	 * Go to new Row Page Detail
     * @return 
	 */
    public String addRow(){
		String	redirect=  "/portal/sab/diseno/tablasDeAbordamiento/detalle?faces-redirect=true";
                modificarRegistro = false;
                row_selected = new Empleado();
		return redirect;
    }
    
        /**
	 * Save a new record
	 */
        public void save() {
		try {
                    row_selected.setStatus((short)1);
                    //ParametroFacadeRemote.create(row_selected);
			addRow();
                       messgeValidateInfo("form:saveButton","Registro Cargado correctamente "); 
		} catch (Exception e) {
			
                        messgeValidateError("form:saveButton","Error:"+e.getMessage());
		}
	}    
    

    
        /**
	 * Update selected record
	 */
        public void update() {
		try {
                    row_selected.setStatus((short)1);
                    //ParametroFacadeRemote.update(row_selected);
                    findAll();
                    messgeValidateInfo("form:updateButton","Registro actualizado correctamente ");
		} catch (Exception e) {
			
                        messgeValidateError("form:updateButton","Error:"+e.getMessage());
		}
	}
    
     public void updateRegiones(){
        asignedRegion = -1;
        selectRegiones = new ArrayList<>();
        selectRegiones.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            Pais mexico =null;
            for(Pais pais: paisesFacadeRemote.findAll()){
                if(pais.getNombre().equals("México")){
                mexico=pais;
                }
            }
            if(Objects.equals(origenDesdeMexico.getIdParametro(), parametroFacadeRemote.findByPK(asignedOrigen).getIdParametro()))
                paisesFacadeRemote.findAll().stream().filter((pais) -> (pais.getNombre().equals("México") && pais.getStatus().equals((short) 1) )).forEachOrdered((pais) -> {
                    selectRegiones.add(new SelectItem(pais.getRegion().getIdRegion(), pais.getRegion().getCveRegion()));
            });
            if(Objects.equals(origenHaciaMexico.getIdParametro(), parametroFacadeRemote.findByPK(asignedOrigen).getIdParametro()))
              for(Region reg: regionesFacadeRemote.findAll()){
                  if(!Objects.equals(mexico.getRegion().getIdRegion(), reg.getIdRegion()))
                  selectRegiones.add(new SelectItem(reg.getIdRegion(),reg.getCveRegion()));
              }
        } catch (Exception ex) {
        }
    }
     
    public void putEstaciones(){
        try {
            list_estaciones = new ArrayList<>();
            estacionFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                Integer idRegion = regs.getCiudad().getPais().getRegion().getIdRegion();
                if (Objects.equals(asignedRegion, idRegion)) {
                    list_estaciones.add(regs);                }
            });
        } catch (Exception ex) {
        }
    } 
        
    public void todasLasRegiones(){}
    public void todasLasEstaciones(){}
    public void todasLosModelos(){}
    public void addRegionEstacion(){}
    public void noAplicaTSU(){}
    
        

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

    public List<Estacion> getList_estaciones() {
        return list_estaciones;
    }

    public void setList_estaciones(List<Estacion> list_estaciones) {
        this.list_estaciones = list_estaciones;
    }

    public Integer getAsignedOrigen() {
        return asignedOrigen;
    }

    public void setAsignedOrigen(Integer asignedOrigen) {
        this.asignedOrigen = asignedOrigen;
    }

    public List<SelectItem> getSelectOrigenes() {
        return selectOrigenes;
    }

    public void setSelectOrigenes(List<SelectItem> selectOrigenes) {
        this.selectOrigenes = selectOrigenes;
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

    public Parametro getOrigenDesdeMexico() {
        return origenDesdeMexico;
    }

    public void setOrigenDesdeMexico(Parametro origenDesdeMexico) {
        this.origenDesdeMexico = origenDesdeMexico;
    }

    public Parametro getOrigenHaciaMexico() {
        return origenHaciaMexico;
    }

    public void setOrigenHaciaMexico(Parametro origenHaciaMexico) {
        this.origenHaciaMexico = origenHaciaMexico;
    }

    public boolean isCheckTodasRegiones() {
        return checkTodasRegiones;
    }

    public void setCheckTodasRegiones(boolean checkTodasRegiones) {
        this.checkTodasRegiones = checkTodasRegiones;
    }

    public boolean isCheckTodasEstaciones() {
        return checkTodasEstaciones;
    }

    public void setCheckTodasEstaciones(boolean checkTodasEstaciones) {
        this.checkTodasEstaciones = checkTodasEstaciones;
    }

    public Map<Integer, Integer> getAsignedEstaciones() {
        return asignedEstaciones;
    }

    public void setAsignedEstaciones(Map<Integer, Integer> asignedEstaciones) {
        this.asignedEstaciones = asignedEstaciones;
    }

    public boolean isCheckTodosLosModelos() {
        return checkTodosLosModelos;
    }

    public void setCheckTodosLosModelos(boolean checkTodosLosModelos) {
        this.checkTodosLosModelos = checkTodosLosModelos;
    }

    public List<ModeloAvion> getList_modelos() {
        return list_modelos;
    }

    public void setList_modelos(List<ModeloAvion> list_modelos) {
        this.list_modelos = list_modelos;
    }

    public Map<Integer, Integer> getAsignedModelos() {
        return asignedModelos;
    }

    public void setAsignedModelos(Map<Integer, Integer> asignedModelos) {
        this.asignedModelos = asignedModelos;
    }

    public List<Tsu> getList_TSUs() {
        return list_TSUs;
    }

    public void setList_TSUs(List<Tsu> list_TSUs) {
        this.list_TSUs = list_TSUs;
    }

    public Map<Integer, Integer> getAsignedTSUs() {
        return asignedTSUs;
    }

    public void setAsignedTSUs(Map<Integer, Integer> asignedTSUs) {
        this.asignedTSUs = asignedTSUs;
    }

    public Map<Integer, Integer> getAsignedTSUsPercentage() {
        return asignedTSUsPercentage;
    }

    public void setAsignedTSUsPercentage(Map<Integer, Integer> asignedTSUsPercentage) {
        this.asignedTSUsPercentage = asignedTSUsPercentage;
    }

    public boolean isCheckNoAplicaTSU() {
        return checkNoAplicaTSU;
    }

    public void setCheckNoAplicaTSU(boolean checkNoAplicaTSU) {
        this.checkNoAplicaTSU = checkNoAplicaTSU;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getPropiedadComisariatoMaterialExtra() {
        return propiedadComisariatoMaterialExtra;
    }

    public void setPropiedadComisariatoMaterialExtra(String propiedadComisariatoMaterialExtra) {
        this.propiedadComisariatoMaterialExtra = propiedadComisariatoMaterialExtra;
    }

    public String getPropiedadComisariatoMontaje() {
        return propiedadComisariatoMontaje;
    }

    public void setPropiedadComisariatoMontaje(String propiedadComisariatoMontaje) {
        this.propiedadComisariatoMontaje = propiedadComisariatoMontaje;
    }

    public String getIntruccionesNotas() {
        return intruccionesNotas;
    }

    public void setIntruccionesNotas(String intruccionesNotas) {
        this.intruccionesNotas = intruccionesNotas;
    }

    public String getAsignedNoParteTSU() {
        return asignedNoParteTSU;
    }

    public void setAsignedNoParteTSU(String asignedNoParteTSU) {
        this.asignedNoParteTSU = asignedNoParteTSU;
    }

    public String getAsignedPropiedadAMXMaterialExtra() {
        return asignedPropiedadAMXMaterialExtra;
    }

    public void setAsignedPropiedadAMXMaterialExtra(String asignedPropiedadAMXMaterialExtra) {
        this.asignedPropiedadAMXMaterialExtra = asignedPropiedadAMXMaterialExtra;
    }

    public String getAsignedNoParteMontaje() {
        return asignedNoParteMontaje;
    }

    public void setAsignedNoParteMontaje(String asignedNoParteMontaje) {
        this.asignedNoParteMontaje = asignedNoParteMontaje;
    }

    public String getAsignedPropiedadAMXMontaje() {
        return asignedPropiedadAMXMontaje;
    }

    public void setAsignedPropiedadAMXMontaje(String asignedPropiedadAMXMontaje) {
        this.asignedPropiedadAMXMontaje = asignedPropiedadAMXMontaje;
    }

    public List<SelectItem> getSelectMateriales() {
        return selectMateriales;
    }

    public void setSelectMateriales(List<SelectItem> selectMateriales) {
        this.selectMateriales = selectMateriales;
    }

    public String getServicioComplementario() {
        return servicioComplementario;
    }

    public void setServicioComplementario(String servicioComplementario) {
        this.servicioComplementario = servicioComplementario;
    }

    public boolean isCheckNoAplicaSobreAbordaje() {
        return checkNoAplicaSobreAbordaje;
    }

    public void setCheckNoAplicaSobreAbordaje(boolean checkNoAplicaSobreAbordaje) {
        this.checkNoAplicaSobreAbordaje = checkNoAplicaSobreAbordaje;
    }

    public String getAsignedNoParteSobreAbordaje() {
        return asignedNoParteSobreAbordaje;
    }

    public void setAsignedNoParteSobreAbordaje(String asignedNoParteSobreAbordaje) {
        this.asignedNoParteSobreAbordaje = asignedNoParteSobreAbordaje;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getCantidadSobreAbordaje() {
        return cantidadSobreAbordaje;
    }

    public void setCantidadSobreAbordaje(String cantidadSobreAbordaje) {
        this.cantidadSobreAbordaje = cantidadSobreAbordaje;
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
