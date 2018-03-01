package com.aeromexico.pab.amx.pab.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alfredo Estrada
 */
public class App {
    public static void main(String[] args) {
        HashMap<String,Object> parameters = new HashMap<>();
        Collection<Map<String,?>> colData = new ArrayList<Map<String,?>>();            
        
        parameters.put("cveTsu"      ,"TSUJ0001");
        parameters.put("nombreClase" ,"PREMIER");
        parameters.put("notas"       ,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "+
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "+
                "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non "+
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum");
        for(int i=0; i<10; i++){
            Map<String,Object> regMap = new HashMap<String, Object>();

            regMap.put("equipo"  , "EQUIPO["+i+"]");
            regMap.put("noParte" , "00000"+i);
            regMap.put("cantidad", "00"+i);

            colData.add(regMap);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = "./target/REPORTE_TSU_"+sdf.format(new Date())+".pdf"; //args[0];
        byte[] resultPDF = null;
        FileOutputStream fos = null;
        try {
            
            resultPDF = GeneradorReportes.generarReporteTSU(parameters, colData, true);
            
            if(resultPDF!=null){
                fos = new FileOutputStream(new File(fileName));

                fos.write(resultPDF);
                fos.flush();
                fos.close();
            }
        }catch(IOException ioe){
            ioe.printStackTrace(System.err);
        }        
    }
}
