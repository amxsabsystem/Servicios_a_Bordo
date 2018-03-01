package com.aeromexico.pab.backend;

/**
 *
 * @author Alfredo Estrada
 */
public final class Constantes {
    
    public static final short PERSITENCE_STATUS_IANACTIVO   =(short)0;
    public static final short PERSITENCE_STATUS_ACTIVO      =(short)1;    

    public static final short PERSITENCE_TRUE    =(short)1;
    public static final short PERSITENCE_FALSE   =(short)0;
    
    public static final String AMX_PAB_MEDIA_FS_STORAGE = "AMX_PAB_MEDIA_FS_STORAGE";
    
    public static enum PERFIL{
        PORTAL_BASICUSER,
        PORTAL_ADMINISTRATOR,
        OPERATION_ADMINISTRATOR,
        DESIGN_ADMINISTRATOR,
        PLANNING_ADMINISTRATOR,
        EVALUATION_ADMINISTRATOR,
        ONBOARD_STD_ADMINISTRATOR,
        FLIGHT_ATTENDANT_ADMINISTRATOR,
        SUPPLIER        
    };
    
    private Constantes(){
    }
    
}
