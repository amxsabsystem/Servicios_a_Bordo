package com.aeromexico.pab.web.jsf.reportes;

import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ReporteFacadeRemote;
import com.aeromexico.pab.backend.reportes.EstadisticasFacadeRemote;
import com.aeromexico.pab.backend.reportes.SeriesDTO;
import com.aeromexico.pab.backend.reportes.YearDataSeriesDTO;
import com.aeromexico.pab.entity.Estacion;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Reporte;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import com.aeromexico.pab.web.jsf.SesionInfo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alfredo Estrada
 */
@Named(value = "estadisiticasMB")
@SessionScoped
public class EstadisiticasMB implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(EstadisiticasMB.class.getName());

    private List<Reporte> all_records;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/reporte_RSB")
    ReporteFacadeRemote reporteFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/genericSearch_RSB")
    GenericSearchFacadeRemote genericSearchFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estadisticas_RSB")
    EstadisticasFacadeRemote estadisticasFacadeRemote;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    @Inject
    @Named("sesionInfo")
    SesionInfo sesionInfo;

    private List<SelectItem> selectTiposReportes;

    private List<Integer> selectedTiposReportes;

    private Integer tipoReporte;

    private List<SelectItem> selectAnios;
    
    private List<SelectItem> selectEstaciones;

    private Integer idEstacionSelected;

    private Integer anio;

    private BarChartModel barModel;

    public EstadisiticasMB() {
    }

    @PostConstruct
    public void init() {
        selectedTiposReportes = new ArrayList<Integer>();
        anio = -1;
        idEstacionSelected = -1;
        updateChart();
        logger.info("EstadisiticasMB");
    }

    public String getMsg() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(new Date());
    }

    public void messgeValidateInfo(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public void messgeValidateError(String elemento, String mensaje) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(elemento, msg);
    }

    public List<SelectItem> getSelectTiposReportes() {
        if (selectTiposReportes == null) {
            selectTiposReportes = new ArrayList<>();
            final Parametro ptr = new Parametro();
            ptr.setClave("tipoReporte");
            final List<Parametro> paramTRList = parametroFacadeRemote.findAllLike(ptr);
            for (Parametro p : paramTRList) {
                if (localeInfo.getLanguage().equals("es")) {
                    selectTiposReportes.add(new SelectItem(p.getIdParametro(), p.getValorEs()));
                } else {
                    selectTiposReportes.add(new SelectItem(p.getIdParametro(), p.getValorEn()));
                }
            }
        }
        return selectTiposReportes;
    }

    public List<SelectItem> getSelectEstaciones() {
        if (selectEstaciones == null) {
            selectEstaciones = new ArrayList<>();
            final List<Estacion> estacionList = estacionFacadeRemote.findAll();
            selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());
            for (Estacion p : estacionList) {
                selectEstaciones.add(new SelectItem(p.getIdEstacion(), p.getClaveIata()+" "+p.getNombre()));                
            }
        }
        return selectEstaciones;
    }

    public Integer getIdEstacionSelected() {
        return idEstacionSelected;
    }

    public void setIdEstacionSelected(Integer idEstacionSelected) {
        this.idEstacionSelected = idEstacionSelected;
    }
    
    public void estacionSelected() {
        logger.info("->estacionSelected:idEstacionSelected=" + idEstacionSelected);
    }

    public List<Integer> getSelectedTiposReportes() {
        return selectedTiposReportes;
    }

    public void setSelectedTiposReportes(List<Integer> selectedTiposReportes) {
        this.selectedTiposReportes = selectedTiposReportes;
    }

    public Integer getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(Integer tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public List<SelectItem> getSelectAnios() {
        if (selectAnios == null) {
            selectAnios = new ArrayList<>();
            selectAnios.add(localeInfo.createSelectIntKeyFirstItem());
            
            List<Integer> allAnios = estadisticasFacadeRemote.allAnios();
            for(Integer a: allAnios){
                selectAnios.add(new SelectItem(a, String.valueOf(a)));
            }
        }
        return selectAnios;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public void anioSelected() {
        logger.info("->anioSelected:anio=" + anio);
    }
    
    public void actualizar() {
        logger.info("->actualizar: anio=" + anio+", selectedTiposReportes=" + selectedTiposReportes);
        updateChart();
    }
    
    public void updateChart(){
        barModel = new BarChartModel();
        
        logger.info("->Now calling EJB: estadistica_Tiporeporte_Anio="+estadisticasFacadeRemote);
        YearDataSeriesDTO yearDataSeriesDTO=null;
        List<SeriesDTO> seriesDataList=null;

        try{
            yearDataSeriesDTO=estadisticasFacadeRemote.estadistica_Tiporeporte_Anio_estacion(anio, idEstacionSelected, localeInfo.getLocale());
//            logger.info("->After call:seriesDataSet="+seriesDataList);
            if(yearDataSeriesDTO != null){                
                seriesDataList = yearDataSeriesDTO.getSeriesDataDTOLIst();
                for(SeriesDTO s: seriesDataList){
                    ChartSeries cs = new ChartSeries();
                    cs.setLabel(s.getLabel());
//                    logger.info("->adding ChartSeries: label"+s.getLabel());
                    LinkedHashMap<String, Integer> dataSet = s.getDataSet();
                    Set<String> keySet = dataSet.keySet();
                    for(String ksM: keySet){
                        cs.set(ksM, dataSet.get(ksM));
//                        logger.info("->\tadding ChartSeries data: "+ksM+","+dataSet.get(ksM));
                    }
                    barModel.addSeries(cs);
                }
                
                Axis xAxis = barModel.getAxis(AxisType.X);
                xAxis.setLabel("MES");

                Axis yAxis = barModel.getAxis(AxisType.Y);
                yAxis.setLabel("CANTIDAD");
                yAxis.setMin(yearDataSeriesDTO.getMinSeriesValue());
                yAxis.setMax((yearDataSeriesDTO.getMaxSeriesValue()*150)/100);
            }
        } catch(Exception e){
            logger.warn("call fail:"+e.getMessage());
        }

        barModel.setTitle("REPORTE DE TIPO / AÑO");
        barModel.setLegendPosition("ne");
    }
    
    public BarChartModel getBarModel_Estacion() {
        if(barModel == null){
            updateChart();
        }
        return barModel;
    }
}
