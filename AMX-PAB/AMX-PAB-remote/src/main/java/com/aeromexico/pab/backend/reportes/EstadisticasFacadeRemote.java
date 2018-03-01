package com.aeromexico.pab.backend.reportes;

import java.util.List;
import java.util.Locale;
import javax.ejb.Remote;

/**
 *
 * @author Alfredo Estrada
 */
@Remote
public interface EstadisticasFacadeRemote {
    List<Integer>     allAnios();
    YearDataSeriesDTO estadistica_Tiporeporte_Anio(int anio,Locale locale);
    YearDataSeriesDTO estadistica_Tiporeporte_Anio_estacion(int anio,Integer idEstacion,Locale locale);
}
