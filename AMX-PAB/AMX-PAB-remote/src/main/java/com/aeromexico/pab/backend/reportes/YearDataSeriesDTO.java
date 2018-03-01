package com.aeromexico.pab.backend.reportes;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Alfredo Estrada
 */
public class YearDataSeriesDTO implements Serializable{
    Integer year;
    List<SeriesDTO> seriesDataDTOLIst;
    Integer maxSeriesValue;
    Integer minSeriesValue;

    public YearDataSeriesDTO(Integer year, List<SeriesDTO> seriesDataDTOLIst) {
        this.year = year;
        this.seriesDataDTOLIst = seriesDataDTOLIst;
    }

    public Integer getYear() {
        return year;
    }

    public List<SeriesDTO> getSeriesDataDTOLIst() {
        return seriesDataDTOLIst;
    }

    public Integer getMinSeriesValue() {
        if(minSeriesValue == null){
            minSeriesValue=0;
            for(SeriesDTO s: seriesDataDTOLIst){
                LinkedHashMap<String, Integer> dataSet = s.getDataSet();
                Collection<Integer> values = dataSet.values();
                for(Integer v: values){
                    if(v < minSeriesValue){
                        minSeriesValue = v;
                    }
                }
            }
        }
        return minSeriesValue;
    }
    
    public Integer getMaxSeriesValue() {
        if(maxSeriesValue == null){
            maxSeriesValue=0;
            for(SeriesDTO s: seriesDataDTOLIst){
                LinkedHashMap<String, Integer> dataSet = s.getDataSet();
                Collection<Integer> values = dataSet.values();
                for(Integer v: values){
                    if(v> maxSeriesValue){
                        maxSeriesValue = v;
                    }
                }
            }
        }
        return maxSeriesValue;
    }
    
    
}
