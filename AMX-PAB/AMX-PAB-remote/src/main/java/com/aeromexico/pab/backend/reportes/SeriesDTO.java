package com.aeromexico.pab.backend.reportes;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author Alfredo Estrada
 */
public class SeriesDTO implements Serializable{
    String label;
    LinkedHashMap<String,Integer> dataSet;

    public SeriesDTO(String label) {
        this.label = label;
        this.dataSet = new LinkedHashMap<String, Integer> ();
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public LinkedHashMap<String, Integer> getDataSet() {
        return dataSet;
    }
    
    
}
