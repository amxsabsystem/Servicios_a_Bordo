package com.aeromexico.pab.backend.reportes;

import com.aeromexico.pab.entity.Tsu;
import javax.ejb.Remote;

/**
 *
 * @author Alfredo Estrada
 */
@Remote
public interface GeneradorReportesPDFFacadeRemote {
    byte[] generarReporteTSU(String cveTsu,boolean dynamic,String urlContextRequest);
}
