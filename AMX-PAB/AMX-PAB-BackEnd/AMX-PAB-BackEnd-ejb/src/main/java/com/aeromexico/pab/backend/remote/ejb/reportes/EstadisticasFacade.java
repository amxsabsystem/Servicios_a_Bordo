package com.aeromexico.pab.backend.remote.ejb.reportes;

import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.reportes.EstadisticasFacadeRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aeromexico.pab.backend.reportes.GeneradorReportesPDFFacadeRemote;
import com.aeromexico.pab.backend.reportes.SeriesDTO;
import com.aeromexico.pab.backend.reportes.YearDataSeriesDTO;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.persistence.queryloader.NativeQueryLaoder;
import com.aeromexico.pab.persistence.queryloader.dto.NativePreparedStatement;
import com.aeromexico.pab.persistence.queryloader.dto.PreparedStatements;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.ejb.EJB;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.Query;

/**
 *
 * @author Alfredo Estrada
 */
@Stateless(
    name ="estadisticas_RSB",
    mappedName = "GeneradorReportesPDF_RSB",
    description = "estadisticas-Stateles-Session EJB-3.1"
)
public class EstadisticasFacade implements EstadisticasFacadeRemote{
    private final static Logger logger = Logger.getLogger(GeneradorReportesPDFFacadeRemote.class.getName());
        
    @EJB(lookup = "java:module/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;

    @EJB(lookup = "java:module/estacion_RSB")
    EstacionFacadeRemote estacionFacadeRemote;

    @Resource 
    SessionContext ctx;
    
	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;
    
    @PostConstruct
    @TransactionAttribute(NOT_SUPPORTED)
    public void init(){
        logger.log(Level.INFO,"init: ");
    }
    
    @PreDestroy
    @TransactionAttribute(NOT_SUPPORTED)
    public void preDestroy(){        
        logger.log(Level.INFO,"predestroy: ");
    }
    
    @TransactionAttribute(SUPPORTS)
    @Override
    public List<Integer> allAnios(){
        List<Integer> anios=null;
        
        PreparedStatements preparedStatements = NativeQueryLaoder.getPreparedStatements();
        NativePreparedStatement npsRep = preparedStatements.getNativePreparedStatement("QUERY_ALL_ANIOS");
        String sqlQuery = npsRep.getSQLQuery();
        Query nq = em.createNativeQuery(sqlQuery);

        anios = nq.getResultList();
        logger.log(Level.INFO,"anios="+anios);
        return anios;
    }
    
    @TransactionAttribute(SUPPORTS)
    @Override
    public YearDataSeriesDTO estadistica_Tiporeporte_Anio(int anio,Locale locale){                
        List<SeriesDTO> seriesDataList=null;
        YearDataSeriesDTO yearDataSeriesDTO;
        
        logger.log(Level.INFO,"anio="+anio);
        LinkedHashMap<Integer,String> tiposReportes = new LinkedHashMap<Integer,String>();
        final Parametro ptr = new Parametro();
        ptr.setClave("tipoReporte");
        final List<Parametro> paramTRList = parametroFacadeRemote.findAllLike(ptr);
        for (Parametro p : paramTRList) {
            if (locale.getLanguage().equals("es")) {
                tiposReportes.put(p.getIdParametro(), p.getValorEs());
            } else {
                tiposReportes.put(p.getIdParametro(), p.getValorEn());
            }
        }
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] months = dfs.getMonths();
        
        LinkedHashMap<Integer,String> meses = new LinkedHashMap<Integer,String>();
        for(int m=1;m<=12;m++){
            final String monthName3Ltr = months[m-1].toUpperCase().substring(0, 3);
            meses.put(m, monthName3Ltr);
        }
        logger.log(Level.INFO,"meses="+meses);
        List<Object[]> resultList = null;
        if(anio != -1){
            PreparedStatements preparedStatements = NativeQueryLaoder.getPreparedStatements();
            NativePreparedStatement npsRep = preparedStatements.getNativePreparedStatement("QUERY_TIPO_REP_X_ANIO");
            String sqlQuery = npsRep.getSQLQuery();
            Query nq = em.createNativeQuery(sqlQuery);        
            nq.setParameter("anio", anio);

            resultList = nq.getResultList();
            logger.log(Level.INFO,"resultList{");
            for(Object[] r:resultList){
                logger.log(Level.INFO,"\tR:"+Arrays.asList(r));
            }
            logger.log(Level.INFO,"}");
        }else{
            logger.log(Level.INFO,"NO QUERY, EMPTY RESULTS");
        }
        seriesDataList = new ArrayList<SeriesDTO>();
        final Set<Integer> tipoRepSet = tiposReportes.keySet();
        for(Integer tr: tipoRepSet){
            String tipoReporteNombre = tiposReportes.get(tr);
            SeriesDTO seriesDTO = new SeriesDTO(tipoReporteNombre);
            
            Set<Integer> mesesSet = meses.keySet();
            for(Integer nm: mesesSet){                
                seriesDTO.getDataSet().put(meses.get(nm), 0);
                if(resultList != null){
                    for(Object[] r:resultList){                    
                        Integer  numRep = new Integer (r[0].toString());
                        Integer  numMes = new Integer (r[1].toString());
                        Integer  tipoRe = new Integer (r[2].toString());
                        if( numMes.intValue() == nm.intValue() &&
                            tipoRe.intValue() == tr.intValue()    ){
                            seriesDTO.getDataSet().put(meses.get(nm), numRep);
                        }
                    }
                }
            }
            seriesDataList.add(seriesDTO);
        }        
        yearDataSeriesDTO=new YearDataSeriesDTO(anio, seriesDataList);
        return yearDataSeriesDTO;
    }
    
    @TransactionAttribute(SUPPORTS)
    @Override
    public YearDataSeriesDTO estadistica_Tiporeporte_Anio_estacion(int anio,Integer idEstacionOrigen,Locale locale){                
        List<SeriesDTO> seriesDataList=null;
        YearDataSeriesDTO yearDataSeriesDTO;
        logger.log(Level.INFO,"anio="+anio+", idEstacionOrigen="+idEstacionOrigen);
        LinkedHashMap<Integer,String> tiposReportes = new LinkedHashMap<Integer,String>();
        final Parametro ptr = new Parametro();
        ptr.setClave("tipoReporte");
        final List<Parametro> paramTRList = parametroFacadeRemote.findAllLike(ptr);
        for (Parametro p : paramTRList) {
            if (locale.getLanguage().equals("es")) {
                tiposReportes.put(p.getIdParametro(), p.getValorEs());
            } else {
                tiposReportes.put(p.getIdParametro(), p.getValorEn());
            }
        }
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] months = dfs.getMonths();
        
        LinkedHashMap<Integer,String> meses = new LinkedHashMap<Integer,String>();
        for(int m=1;m<=12;m++){
            final String monthName3Ltr = months[m-1].toUpperCase().substring(0, 3);
            meses.put(m, monthName3Ltr);
        }
        logger.log(Level.INFO,"meses="+meses);
        List<Object[]> resultList = null;
        if(anio!=-1 && idEstacionOrigen!=-1){
            PreparedStatements preparedStatements = NativeQueryLaoder.getPreparedStatements();
            NativePreparedStatement npsRep = preparedStatements.getNativePreparedStatement("QUERY_TIPO_REP_X_ANIO_ESTACION");        
            String sqlQuery = npsRep.getSQLQuery();
            Query nq = em.createNativeQuery(sqlQuery);

            nq.setParameter("anio", anio);
            nq.setParameter("idEstacionOrigen", idEstacionOrigen);

            resultList = nq.getResultList();
            logger.log(Level.INFO,"resultList{");
            for(Object[] r:resultList){
                logger.log(Level.INFO,"\tR:"+Arrays.asList(r));
            }
            logger.log(Level.INFO,"}");
        }else{
            logger.log(Level.INFO,"NO QUERY, EMPTY RESULTS");
        }
        seriesDataList = new ArrayList<SeriesDTO>();
        final Set<Integer> tipoRepSet = tiposReportes.keySet();
        for(Integer tr: tipoRepSet){
            String tipoReporteNombre = tiposReportes.get(tr);
            SeriesDTO seriesDTO = new SeriesDTO(tipoReporteNombre);
            
            Set<Integer> mesesSet = meses.keySet();
            for(Integer nm: mesesSet){                
                seriesDTO.getDataSet().put(meses.get(nm), 0);
                if(resultList != null){
                    for(Object[] r:resultList){                    
                        Integer  numRep = new Integer (r[0].toString());
                        Integer  numMes = new Integer (r[1].toString());
                        Integer  tipoRe = new Integer (r[2].toString());
                        if( numMes.intValue() == nm.intValue() &&
                            tipoRe.intValue() == tr.intValue()    ){
                            seriesDTO.getDataSet().put(meses.get(nm), numRep);
                        }
                    }
                }
            }
            seriesDataList.add(seriesDTO);
        }
        yearDataSeriesDTO=new YearDataSeriesDTO(anio, seriesDataList);
        return yearDataSeriesDTO;
    }

}
