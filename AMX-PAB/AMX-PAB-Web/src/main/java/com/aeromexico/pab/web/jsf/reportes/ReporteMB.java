package com.aeromexico.pab.web.jsf.reportes;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.mediastorage.StorageServiceFacadeRemote;
import com.aeromexico.pab.backend.remote.AreaFacadeRemote;
import com.aeromexico.pab.backend.remote.ClaseFacadeRemote;
import com.aeromexico.pab.backend.remote.ConfiguracionReporteDetalleFacadeRemote;
import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.EvidenciaFacadeRemote;
import com.aeromexico.pab.backend.remote.MultimedioFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ReporteFacadeRemote;
import com.aeromexico.pab.backend.remote.ResponsableEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ResponsableProductoFacadeRemote;
import com.aeromexico.pab.backend.remote.SeguimientoFacadeRemote;
import com.aeromexico.pab.backend.remote.TipoProductoReporteFacadeRemote;
import com.aeromexico.pab.backend.remote.UsuarioFacadeRemote;
import com.aeromexico.pab.entity.Area;
import com.aeromexico.pab.entity.ConfiguracionReporteDetalle;
import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Evidencia;
import com.aeromexico.pab.entity.Multimedio;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.Perfil;
import com.aeromexico.pab.entity.ProveedorEstacion;
import com.aeromexico.pab.entity.Reporte;
import com.aeromexico.pab.entity.ResponsableEstacion;
import com.aeromexico.pab.entity.ResponsableProducto;
import com.aeromexico.pab.entity.Seguimiento;
import com.aeromexico.pab.entity.TipoProductoReporte;
import com.aeromexico.pab.entity.Usuario;
import com.aeromexico.pab.web.jsf.FileDTO;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import com.aeromexico.pab.web.jsf.SesionInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "reporteMB")
@SessionScoped
public class ReporteMB implements Serializable{
    
    private static Logger logger = LoggerFactory.getLogger(ReporteMB.class.getName());
    
    public ReporteMB(){}
    private Reporte row_selected = null;
    private ResponsableProducto row_selected_Respnsable = null;
    private List<Reporte> all_records;
    private List<ResponsableProducto> all_records_reponsable;
    private boolean modificarRegistro;
    private Integer asignedTipoResponsable;
    private List<SelectItem> selectTiposResponsables;
    private Integer asignedArea;
    private List<SelectItem> selectAreas;
    private Integer asignedEstacion;
    private List<SelectItem> selectEstaciones;
    private Integer asignedResponsable;
    private List<SelectItem> selectResponsables;
    private Integer asignedProveedor;
    private List<SelectItem> selectProveedores;
    private Integer asignedEmpleado;
    private Integer asignedEmpleadoJefe;
    private List<SelectItem> selectEmpleados;
    private List<String> asignedtipos;
    private List<SelectItem> selectTiposProductos;
    private String areaDesc;
    private List<Multimedio> archivos_reportes;
    private String observaciones;
    private InputStream inputStreamFile;
    private String fileName;
    private String contentTypeUploaded;
    private Parametro asignedEstatusActive;
    private boolean reasignar;
    private String estado;
    private boolean isCancelado;
    private boolean isConfirmado;
    private boolean isAdministrador;
    private Parametro estatusAsignado;
    private Parametro estatusConfirmado;
    private Parametro estatusCerrado;
    private Parametro estatusDefasado;
    private List<FileDTO> fileDTO_List;
    private List<Seguimiento> seguimiento_List;
    private boolean is_EVALUATION_ADMINISTRATOR;
    private boolean is_SUPPLIER;
    
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/reporte_RSB")
    ReporteFacadeRemote reporteFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/clase_RSB")
    ClaseFacadeRemote claseFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/area_RSB")
    AreaFacadeRemote areasDeSABFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/storageService_RSB")
    StorageServiceFacadeRemote storageServiceFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/responsableEstacion_RSB")
    ResponsableEstacionFacadeRemote responsableEstacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/responsableProducto_RSB")
    ResponsableProductoFacadeRemote responsableProductoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/usuario_RSB")
    UsuarioFacadeRemote usuarioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/empleado_RSB")
    EmpleadoFacadeRemote empleadoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/tipoProductoReporte_RSB")
    TipoProductoReporteFacadeRemote tipoProductoReporteFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/configuracionReporteDetalle_RSB")
    ConfiguracionReporteDetalleFacadeRemote configuracionReporteDetalleFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedorEstacion_RSB")
    ProveedorEstacionFacadeRemote proveedorEstacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/seguimiento_RSB")
    SeguimientoFacadeRemote seguimientoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/multimedio_RSB")
    MultimedioFacadeRemote multimedioFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/evidencia_RSB")
    EvidenciaFacadeRemote evidenciaFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/contactoProveedorEstacion_RSB")
    ContactoProveedorEstacionFacadeRemote contactoProveedorEstacionFacadeRemote;
    
    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;
    
    @Inject
    @Named("sesionInfo")
    SesionInfo sesionInfo;

    @PostConstruct
    public void init() {
        is_EVALUATION_ADMINISTRATOR=false;
        is_SUPPLIER=false;
        for(Perfil perfil:sesionInfo.getCurrentUser().getPerfilList()){
            if(perfil.getPerfil().equals(Constantes.PERFIL.EVALUATION_ADMINISTRATOR.toString()))
            is_EVALUATION_ADMINISTRATOR=true;
            if(perfil.getPerfil().equals(Constantes.PERFIL.SUPPLIER.toString()))
            is_SUPPLIER=true;
        }
                
                
        isAdministrador=false;
        isCancelado=false;
        isConfirmado=false;
        fileDTO_List = new ArrayList<>();
        seguimiento_List = new ArrayList<>();
        reasignar=false;
        modificarRegistro=false;
        selectTiposResponsables= new ArrayList<>();
        selectTiposResponsables.add(new SelectItem(0,(localeInfo.getLanguage().equals("es") ? "Área de PAB" : "PAB Area")));
        selectTiposResponsables.add(new SelectItem(1,(localeInfo.getLanguage().equals("es") ? "Proveedor" : "Supplier")));
        asignedTipoResponsable =0;
        asignedArea = -1;
        selectAreas = new ArrayList<>();
        selectAreas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            areasDeSABFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectAreas.add(new SelectItem(regs.getIdArea(),localeInfo.getLanguage().equals("es") ? regs.getNombreEs() : regs.getNombreEn()));
            });
        } catch (Exception ex) {
        }
        asignedEstacion=-1;
        selectEstaciones = new ArrayList<>();
        selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            estacionesFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectEstaciones.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata() + " - " + regs.getNombre()));
            });
        } catch (Exception ex) {
        }
        asignedEmpleado=-1;
        selectEmpleados = new ArrayList<>();
        selectEmpleados.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            empleadoFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectEmpleados.add(new SelectItem(regs.getIdEmpleado(), regs.getUsuario().getNombre() + " " + regs.getUsuario().getApellidoPaterno() + " " + regs.getUsuario().getApellidoMaterno()));
            });
        } catch (Exception ex) {
        }
        
        parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("estatus"))).filter((regs) -> (regs.getValorEn().equals("Active"))).forEachOrdered((regs) -> {
            asignedEstatusActive = regs;
        });
        
        asignedtipos = new ArrayList<>();
        selectTiposProductos = new ArrayList<>();
                try {
            tipoProductoReporteFacadeRemote.findAll().stream().filter((regs) -> (Objects.equals(regs.getEstatus().getIdParametro(), asignedEstatusActive.getIdParametro()) ) ).forEachOrdered((regs) -> {
                selectTiposProductos.add(new SelectItem(regs.getIdTipoProductoReporte(), regs.getDescripcion()));
            });
        } catch (Exception ex) {
        }
        
        parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals(Constantes.PERSITENCE_STATUS_ACTIVO) && regs.getClave().equals("estatusReporte"))).map((regs) -> {
            if(regs.getValorEn().equals("Assigned"))
                estatusAsignado =regs;
            return regs;
        }).map((regs) -> {
            if(regs.getValorEn().equals("Confirmed"))
                estatusConfirmado =regs;
            return regs;
        }).map((regs) -> {
            if(regs.getValorEn().equals("Closed"))
                estatusCerrado =regs;
            return regs;
        }).filter((regs) -> (regs.getValorEn().equals("Defected"))).forEachOrdered((regs) -> {
            estatusDefasado =regs;
        });        
                
    }
    
    public List<Reporte> findAll() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<>();
                Usuario usuario =sesionInfo.getCurrentUser();
                
                ContactoProveedorEstacion contactoProveedor = null;
                ContactoProveedorEstacion entity_contactoProveedor = new ContactoProveedorEstacion();
                entity_contactoProveedor.setUsuario(usuario);
                for(ContactoProveedorEstacion cpe: contactoProveedorEstacionFacadeRemote.findAllLike(entity_contactoProveedor)){
                   contactoProveedor=cpe;
                }
                
                
                for(Reporte rep: reporteFacadeRemote.findAll()){
                    //Empleado
                    if(
                        (rep.getIdResponsableAmx()!=null && rep.getIdResponsableAmx().getEmpleado().getUsuario().getEmailUsuario()
                            .equals(usuario.getEmailUsuario()) )
                       || (rep.getIdResponsableFinalAmx()!=null && rep.getIdResponsableFinalAmx().getEmpleado().getUsuario().getEmailUsuario()
                            .equals(usuario.getEmailUsuario()) )  
                             
                      ){
                        all_records.add(rep);
                    }
                    //Jefe del Empleado
                    if(
                       (rep.getIdResponsableAmx()!=null &&  rep.getIdResponsableAmx().getEmpleadoJefe().getUsuario().getEmailUsuario()
                            .equals(usuario.getEmailUsuario()) )
                       || (rep.getIdResponsableFinalAmx()!=null && rep.getIdResponsableFinalAmx().getEmpleadoJefe().getUsuario().getEmailUsuario()
                            .equals(usuario.getEmailUsuario()) )
                             
                      ){
                        all_records.add(rep);
                    }
                    //proveedor
                    if(contactoProveedor!=null){
                        if( 
                            (rep.getIdResponsableProveedorEstacion()!=null && Objects.equals(rep.getIdResponsableProveedorEstacion().getIdProveedorEstacion(), 
                                contactoProveedor.getProveedorEstacion().getIdProveedorEstacion()) )
                            || (rep.getIdResponsableProveedorEstacionFinal()!=null && Objects.equals(rep.getIdResponsableProveedorEstacionFinal().getIdProveedorEstacion(), 
                                    contactoProveedor.getProveedorEstacion().getIdProveedorEstacion()) 
                                )    
                                ){
                            all_records.add(rep);
                        }
                    }
                }
               if(is_EVALUATION_ADMINISTRATOR){
                    all_records = new ArrayList<>();
                    all_records = reporteFacadeRemote.findAll();
                }
                
                
                
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }
    public List<ResponsableProducto> findAll_Responsables() {
        if (all_records_reponsable == null) {
            try {
                all_records_reponsable = new ArrayList<>();
                all_records_reponsable = responsableProductoFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records_reponsable = null;
            }
        }
        return all_records_reponsable;
    }
    
    public void mostrarResponsablesEstación(){
        ResponsableEstacion entity = new ResponsableEstacion(); 
        entity.setArea(areasDeSABFacadeRemote.findByPK(asignedArea));
        selectResponsables = new ArrayList<>();
        selectResponsables.add(localeInfo.createSelectIntKeyFirstItem());
        
        for(ResponsableEstacion responsable: responsableEstacionFacadeRemote.findAllLike(entity)){
            if(!responsable.getEmpleado().getUsuario().getEmailUsuario().equals(row_selected.getIdResponsableAmx().getEmpleado().getUsuario().getEmailUsuario()) || is_EVALUATION_ADMINISTRATOR)
            selectResponsables.add(new SelectItem(responsable.getIdResponsableEstacion(),responsable.getEmpleado().getUsuario().getEmailUsuario()));
        }
                
    }
    
    public void mostrarProveedoresEstación(){
        ProveedorEstacion entity = new ProveedorEstacion(); 
        entity.setEstacion(estacionesFacadeRemote.findByPK(asignedEstacion));
        selectProveedores = new ArrayList<>();
        selectProveedores.add(localeInfo.createSelectIntKeyFirstItem());
        proveedorEstacionFacadeRemote.findAllLike(entity).forEach((reg) -> {
            selectProveedores.add(new SelectItem(reg.getIdProveedorEstacion(),reg.getClaveProveedorEstacion()));
        });
    }
    
    public void limpiarasignacion(){
        if(asignedTipoResponsable==0){
            asignedEstacion=-1;
            asignedProveedor= -1;
            selectProveedores = new ArrayList<>();
            selectProveedores.add(localeInfo.createSelectIntKeyFirstItem());
        }else{
            asignedResponsable =-1;
            asignedArea=-1;
            selectResponsables = new ArrayList<>();
            selectResponsables.add(localeInfo.createSelectIntKeyFirstItem());
        }
    }
    
    public String addRow_Responsable(){
        String redirect = "/portal/reportes/configuracionResponsablesDetalle?faces-redirect=true";
        modificarRegistro = false;
        row_selected_Respnsable = new ResponsableProducto();
        return redirect;
    }
    
     public boolean validate(String boton) {
        boolean retorno = true;
        
        if (asignedEmpleado == -1) {
            messgeValidateError(boton, "Debe seleccionar un Empleado");
            return false;
        }
        if (Objects.equals(asignedEmpleado, asignedEmpleadoJefe)) {
            messgeValidateError(boton, "El Empleado jefe debe ser diferente al empleado");
            return false;
        }
        
        if (asignedEstacion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Estación");
            return false;
        }

        if (asignedtipos!=null && asignedtipos.isEmpty()) {
            messgeValidateError(boton, "Debe seleccionar un Tipo de Producto");
            return false;
        }
     
     return retorno;
     }
     
    public void limpiarCampos(){
        asignedEmpleado =-1;
        asignedEstacion =-1;
        asignedEmpleadoJefe =-1;
        asignedtipos.clear();
        areaDesc=null;
        row_selected_Respnsable =new ResponsableProducto();
    
    } 
    
    public void saveResponsable(){
        try {
            if (validate("form:saveButton")) {
                ResponsableEstacion responsableEstacion = new ResponsableEstacion();
                Empleado empleado = empleadoFacadeRemote.findByPK(asignedEmpleado);
                responsableEstacion.setArea(empleado.getArea());
                responsableEstacion.setEmpleado(empleado);
                responsableEstacion.setEstacion(estacionesFacadeRemote.findByPK(asignedEstacion));
                responsableEstacion.setEmpleadoJefe(empleadoFacadeRemote.findByPK(asignedEmpleadoJefe));
                final ArrayList<TipoProductoReporte> tipoProductoTipoList = new ArrayList<>();
                asignedtipos.forEach((tipo) -> {
                    tipoProductoTipoList.add(tipoProductoReporteFacadeRemote.findByPK(new Integer(tipo)));
                });
                responsableEstacion.setTipoProductoReporteList(tipoProductoTipoList);
                ResponsableEstacion re =responsableEstacionFacadeRemote.create(responsableEstacion);
                all_records_reponsable = null;
                addRow_Responsable();
                
                limpiarCampos();
                
                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }
    
    public void modifyRow(Reporte row){
        modificarRegistro=true;
    }
    
    public String modifyRowResponsable(ResponsableProducto row){
        String redirect = "/portal/reportes/configuracionResponsablesDetalle?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected_Respnsable = responsableProductoFacadeRemote.findByPK_EAGER(row.getResponsableProductoPK());
        Empleado empleado =this.row_selected_Respnsable.getResponsableEstacion().getEmpleado();
        asignedEmpleado = empleado.getIdEmpleado();
        areaDesc =empleado.getArea().getClave();
        asignedEstacion = this.row_selected_Respnsable.getResponsableEstacion().getEstacion().getIdEstacion();
        asignedEmpleadoJefe = this.row_selected_Respnsable.getResponsableEstacion().getEmpleadoJefe().getIdEmpleado();
        
        asignedtipos = new ArrayList<>();
        responsableProductoFacadeRemote.findAll().stream().filter((rp) -> (Objects.equals(rp.getResponsableEstacion().getIdResponsableEstacion(), this.row_selected_Respnsable.getResponsableEstacion().getIdResponsableEstacion()))).forEachOrdered((rp) -> {
            asignedtipos.add(String.valueOf(rp.getTipoProductoReporte().getIdTipoProductoReporte()));
        });

        return redirect;
    }
    
    public void updateResponsable(){
        try {
            if (validate("form:updateButton")) {            
                responsableProductoFacadeRemote.findAll().stream().filter((rp) -> (Objects.equals(rp.getResponsableEstacion().getIdResponsableEstacion(), this.row_selected_Respnsable.getResponsableEstacion().getIdResponsableEstacion()))).forEachOrdered((rp) -> {
                    responsableProductoFacadeRemote.remove(rp);
                });

                ResponsableEstacion responsableEstacion = new ResponsableEstacion();
                Empleado empleado = empleadoFacadeRemote.findByPK(asignedEmpleado);
                responsableEstacion.setArea(empleado.getArea());
                responsableEstacion.setEmpleado(empleado);
                responsableEstacion.setEstacion(estacionesFacadeRemote.findByPK(asignedEstacion));
                responsableEstacion.setEmpleadoJefe(empleadoFacadeRemote.findByPK(asignedEmpleadoJefe));
                final ArrayList<TipoProductoReporte> tipoProductoTipoList = new ArrayList<>();
                asignedtipos.forEach((tipo) -> {
                    tipoProductoTipoList.add(tipoProductoReporteFacadeRemote.findByPK(new Integer(tipo)));
                });
                responsableEstacion.setTipoProductoReporteList(tipoProductoTipoList);
                ResponsableEstacion re = responsableEstacionFacadeRemote.create(responsableEstacion);

                all_records_reponsable = null;
                modificarRegistro = false;
                findAll_Responsables();
                
                messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }
    
    public boolean validateConfirmarResponsable(String boton){
    boolean retorno =true;
    if (asignedTipoResponsable==0 && asignedArea == -1) {
            messgeValidateError(boton, "Debe seleccionar un Área");
            return false;
    }
    if (asignedTipoResponsable==1 && asignedEstacion == -1) {
            messgeValidateError(boton, "Debe seleccionar una Estación");
            return false;
    }
    if (asignedTipoResponsable==0 && asignedResponsable == -1) {
            messgeValidateError(boton, "Debe seleccionar un Responsable");
            return false;
    }
    if (asignedTipoResponsable==1 && asignedProveedor == -1) {
            messgeValidateError(boton, "Debe seleccionar un Proveedor");
            return false;
    }
    if(observaciones.isEmpty()){
            messgeValidateError(boton, "Debe ingresar observaciones");
            return false;    
    }
    
    return retorno;    
    }
    
    public List<Multimedio> fill_evidenciaList(Integer id_seguimiento){
        List<Multimedio> retorno = new ArrayList<>();
        Evidencia entity_evidencia =new Evidencia();
        entity_evidencia.setSeguimiento(seguimientoFacadeRemote.findByPK(id_seguimiento));
        evidenciaFacadeRemote.findAllLike(entity_evidencia).forEach((evidencia) -> {
            retorno.add(evidencia.getMultimedio());
        });
        return retorno;
    }
    
    public void confirmarResponsable(){
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try{
            if (validateConfirmarResponsable("form:confirmarResponsable")) {
                ResponsableEstacion responsable=null;
                Area area = null;
                ProveedorEstacion proveedorEstacion= null;
                if(asignedTipoResponsable==0){
                    responsable =responsableEstacionFacadeRemote.findByPK(asignedResponsable);
                    area = areasDeSABFacadeRemote.findByPK(asignedArea);
                }else{
                    proveedorEstacion =proveedorEstacionFacadeRemote.findByPK(asignedProveedor);
                }
                
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setAreaResponsable(row_selected.getAreaResponsable());
                seguimiento.setResponsableEstacion(row_selected.getIdResponsableAmx());
                seguimiento.setProveedorEstacion(row_selected.getIdResponsableProveedorEstacion());
                seguimiento.setContactoProveedorEstacion(null);
                seguimiento.setFecha(new java.sql.Timestamp(now.getTime()));
                seguimiento.setMensajeRespuesta(observaciones);
                seguimiento.setReporte(row_selected);
                
                Seguimiento respuesta= seguimientoFacadeRemote.create(seguimiento);
                
                for(FileDTO fileDTO: fileDTO_List) {
                    Multimedio mm = saveFileOnServer_urlUploaded(fileDTO.getInputStreamFile(),fileDTO.getFileName(),fileDTO.getContentTypeUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(mm.getUrl());
                    mm.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                    mm.setUsuarioCreo(request.getUserPrincipal().getName());
                    mm.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                    mm.setUsuarioModifico(request.getUserPrincipal().getName());
                    mm.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    Multimedio resp = multimedioFacadeRemote.create(mm);
                    Evidencia evidencia = new Evidencia();
                    evidencia.setMultimedio(resp);
                    evidencia.setSeguimiento(respuesta);
                    evidencia.setReporte(row_selected);
                    evidenciaFacadeRemote.create(evidencia);
                }
                
                if(is_EVALUATION_ADMINISTRATOR)
                    row_selected.setIdEstatusReporteActual(estatusConfirmado);
                else    
                    row_selected.setIdEstatusReporteActual(estatusAsignado);
                
//                row_selected.setAreaResponsable(area);
                row_selected.setIdResponsableAmx(responsable);
                row_selected.setIdResponsableFinalAmx(null);
                row_selected.setIdResponsableProveedorEstacion(proveedorEstacion);
                row_selected.setIdResponsableProveedorEstacionFinal(null);
                reporteFacadeRemote.update(row_selected);
                all_records=null;
                findAll();
                
                messgeValidateInfo("form:confirmarResponsable","Registro guardado correctamente");
            }
        } catch (Exception e) {

            messgeValidateError("form:confirmarResponsable", "Error:" + e.getMessage());
        }
    }
    
    public String detalle(Reporte row){
        String redirect = "/portal/reportes/detalle?faces-redirect=true";
        modificarRegistro = false;
        this.row_selected =row;
        archivos_reportes = new ArrayList<>();
        Evidencia entity_evidencia =new Evidencia();
        entity_evidencia.setReporte(row);
        evidenciaFacadeRemote.findAllLike(entity_evidencia).stream().filter((evidencia) -> (evidencia.getSeguimiento()==null)).forEachOrdered((evidencia) -> {
            archivos_reportes.add(evidencia.getMultimedio());
        });
        ConfiguracionReporteDetalle entity = new ConfiguracionReporteDetalle();
        entity.setReporte(row);
        estado ="";
        configuracionReporteDetalleFacadeRemote.findAllLike(entity).forEach((crd) -> {
            estado += crd.getTipoIrregularidad().getDescripcion() +"("+ crd.getCriterioIrregularidad().getDescripcion()+ ")   ";
        });
        seguimiento_List = new ArrayList<>();
        Seguimiento entity_seguimiento= new Seguimiento();
        entity_seguimiento.setReporte(row);
        seguimientoFacadeRemote.findAllLike(entity_seguimiento).forEach((segRep) -> {
            seguimiento_List.add(segRep);
        });
        
        isCancelado=this.row_selected.getIdEstatusReporteActual().getIdParametro() ==estatusCerrado.getIdParametro()?true:false;
        isConfirmado=this.row_selected.getIdEstatusReporteActual().getIdParametro() ==estatusConfirmado.getIdParametro()?true:false;
        
        if(is_EVALUATION_ADMINISTRATOR ){   
            isAdministrador=true;
        }
        return redirect;
    
    }
    
    public String returnMaster_Responsable(){
        String redirect = "/portal/reportes/configuracionResponsables?faces-redirect=true";
        all_records_reponsable = null;
        findAll_Responsables();
        return redirect;
    }
    
    public String returnConsulta(){
        String redirect = "/portal/reportes/consulta?faces-redirect=true";
        all_records = null;
        modificarRegistro=false;
        findAll();
        return redirect;
    }
    
    public void reasignarReporte(){
        reasignar =reasignar?false:true;
    }
    
    public void updateAreaDesc(){
        areaDesc =empleadoFacadeRemote.findByPK(asignedEmpleado).getArea().getClave();
    }
    
    
    public boolean validateConfirmarAceptacion(String boton){
    boolean retorno =true;
        if (observaciones.isEmpty()) {
            messgeValidateError(boton, "Debe ingresar una descripción");
            return false;
        }
    
    return retorno;
    }
    
    public boolean validateConfirmarCerrar(String boton){
    boolean retorno =true;
        if (observaciones.isEmpty()) {
            messgeValidateError(boton, "Debe ingresar una descripción");
            return false;
        }
    return retorno;
    }
    
    
    public void cerrarReporte(){
    Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try{
            if (validateConfirmarCerrar("form:cerrarResponsable")) {
                ResponsableEstacion responsable=null;
                Area area = null;
                ProveedorEstacion proveedorEstacion= null;
                if(row_selected.getIdResponsableAmx()!=null){
                    responsable =row_selected.getIdResponsableAmx();
                    area = row_selected.getAreaResponsable();
                }else{
                    proveedorEstacion =row_selected.getIdResponsableProveedorEstacion();
                }
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setAreaResponsable(row_selected.getAreaResponsable());
                seguimiento.setResponsableEstacion(row_selected.getIdResponsableAmx());
                seguimiento.setProveedorEstacion(row_selected.getIdResponsableProveedorEstacion());
                seguimiento.setContactoProveedorEstacion(null);
                seguimiento.setFecha(new java.sql.Timestamp(now.getTime()));
                seguimiento.setMensajeRespuesta(observaciones);
                seguimiento.setReporte(row_selected);
                Seguimiento respuesta= seguimientoFacadeRemote.create(seguimiento);
                
                for(FileDTO fileDTO: fileDTO_List) {
                    Multimedio mm = saveFileOnServer_urlUploaded(fileDTO.getInputStreamFile(),fileDTO.getFileName(),fileDTO.getContentTypeUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(mm.getUrl());
                    mm.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                    mm.setUsuarioCreo(request.getUserPrincipal().getName());
                    mm.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                    mm.setUsuarioModifico(request.getUserPrincipal().getName());
                    mm.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    Multimedio resp = multimedioFacadeRemote.create(mm);
                    Evidencia evidencia = new Evidencia();
                    evidencia.setMultimedio(resp);
                    evidencia.setSeguimiento(respuesta);
                    evidencia.setReporte(row_selected);
                    evidenciaFacadeRemote.create(evidencia);
                }
                
                row_selected.setIdEstatusReporteActual(estatusCerrado);
                reporteFacadeRemote.update(row_selected);
                all_records=null;
                findAll();
                
                messgeValidateInfo("form:cerrarResponsable","Registro guardado correctamente");
            }
        } catch (Exception e) {

            messgeValidateError("form:cerrarResponsable", "Error:" + e.getMessage());
        }
    }
    
    public void aceptarReporte(){
    Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try{
            if (validateConfirmarAceptacion("form:aceptarReporteButton")) {
                ResponsableEstacion responsable=null;
                Area area = null;
                ProveedorEstacion proveedorEstacion= null;
                if(row_selected.getIdResponsableAmx()!=null){
                    responsable =row_selected.getIdResponsableAmx();
                    area = row_selected.getAreaResponsable();
                }else{
                    proveedorEstacion =row_selected.getIdResponsableProveedorEstacion();
                }
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setAreaResponsable(row_selected.getAreaResponsable());
                seguimiento.setResponsableEstacion(row_selected.getIdResponsableAmx());
                seguimiento.setProveedorEstacion(row_selected.getIdResponsableProveedorEstacion());
                seguimiento.setContactoProveedorEstacion(null);
                seguimiento.setFecha(new java.sql.Timestamp(now.getTime()));
                seguimiento.setMensajeRespuesta(observaciones);
                seguimiento.setReporte(row_selected);
                Seguimiento respuesta= seguimientoFacadeRemote.create(seguimiento);
                
                for(FileDTO fileDTO: fileDTO_List) {
                    Multimedio mm = saveFileOnServer_urlUploaded(fileDTO.getInputStreamFile(),fileDTO.getFileName(),fileDTO.getContentTypeUploaded());
                    storageServiceFacadeRemote.commitSaveOrUpdateMedia(mm.getUrl());
                    mm.setStatus(Constantes.PERSITENCE_STATUS_ACTIVO);
                    mm.setUsuarioCreo(request.getUserPrincipal().getName());
                    mm.setFechaCreo(new java.sql.Timestamp(now.getTime()));
                    mm.setUsuarioModifico(request.getUserPrincipal().getName());
                    mm.setFechaModifico(new java.sql.Timestamp(now.getTime()));
                    Multimedio resp = multimedioFacadeRemote.create(mm);
                    Evidencia evidencia = new Evidencia();
                    evidencia.setMultimedio(resp);
                    evidencia.setSeguimiento(respuesta);
                    evidencia.setReporte(row_selected);
                    evidenciaFacadeRemote.create(evidencia);
                }
                row_selected.setIdEstatusReporteActual(estatusConfirmado);
                row_selected.setAreaResponsable(area);
                row_selected.setIdResponsableAmx(responsable);
                row_selected.setIdResponsableFinalAmx(responsable);
                row_selected.setIdResponsableProveedorEstacion(proveedorEstacion);
                row_selected.setIdResponsableProveedorEstacionFinal(proveedorEstacion);
                reporteFacadeRemote.update(row_selected);
                all_records=null;
                findAll();
                
                messgeValidateInfo("form:aceptarReporteButton","Registro guardado correctamente");
            }
        } catch (Exception e) {

            messgeValidateError("form:aceptarReporteButton", "Error:" + e.getMessage());
        }
    
    }
    
    
    public Multimedio saveFileOnServer_urlUploaded(InputStream inputStreamFile, String fileName, String contentTypeUploaded) throws FileNotFoundException, IOException {
        OutputStream os = new ByteArrayOutputStream();                
        byte[] buffer = new byte[1024*32];
        int r;
        while((r = inputStreamFile.read(buffer, 0, buffer.length))!= -1){
            os.write(buffer, 0, r);
            os.flush();
        }
        byte[] contentUploaded = ((ByteArrayOutputStream)os).toByteArray();
        final String httpSessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);        
        Multimedio multimedio = storageServiceFacadeRemote.saveOrUpdateMultimedio(contentUploaded, fileName, contentTypeUploaded, httpSessionId);
        
        return multimedio;
    }

    public void fileListener(FileUploadEvent event) {
        try {

            if (event.getFile() != null) {
                UploadedFile uploadedFile = event.getFile();
                if ((uploadedFile != null && uploadedFile.getSize() > 0)) {
                    InputStream inputStreamFile = uploadedFile.getInputstream();
                    if (inputStreamFile != null) {
                        FileDTO fileDTO=new FileDTO();
                        fileDTO.setInputStreamFile(inputStreamFile);
                        fileDTO.setFileName(uploadedFile.getFileName());
                        fileDTO.setContentTypeUploaded(uploadedFile.getContentType());
                        fileDTO_List.add(fileDTO);
                    }
                }
            } else {
                logger.debug("El archivo es requerido");
            }
        } catch (Exception ex) {

        }

    }    
    

    public List<Reporte> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Reporte> all_records) {
        this.all_records = all_records;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public Integer getAsignedTipoResponsable() {
        return asignedTipoResponsable;
    }

    public void setAsignedTipoResponsable(Integer asignedTipoResponsable) {
        this.asignedTipoResponsable = asignedTipoResponsable;
    }

    public List<SelectItem> getSelectTiposResponsables() {
        return selectTiposResponsables;
    }

    public void setSelectTiposResponsables(List<SelectItem> selectTiposResponsables) {
        this.selectTiposResponsables = selectTiposResponsables;
    }

    public Integer getAsignedArea() {
        return asignedArea;
    }

    public void setAsignedArea(Integer asignedArea) {
        this.asignedArea = asignedArea;
    }

    public List<SelectItem> getSelectAreas() {
        return selectAreas;
    }

    public void setSelectAreas(List<SelectItem> selectAreas) {
        this.selectAreas = selectAreas;
    }

    public Integer getAsignedEstacion() {
        return asignedEstacion;
    }

    public void setAsignedEstacion(Integer asignedEstacion) {
        this.asignedEstacion = asignedEstacion;
    }

    public List<SelectItem> getSelectEstaciones() {
        return selectEstaciones;
    }

    public void setSelectEstaciones(List<SelectItem> selectEstaciones) {
        this.selectEstaciones = selectEstaciones;
    }

    public Reporte getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Reporte row_selected) {
        this.row_selected = row_selected;
    }

    public List<Multimedio> getArchivos_reportes() {
        return archivos_reportes;
    }

    public void setArchivos_reportes(List<Multimedio> archivos_reportes) {
        this.archivos_reportes = archivos_reportes;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ReporteMB.logger = logger;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public InputStream getInputStreamFile() {
        return inputStreamFile;
    }

    public void setInputStreamFile(InputStream inputStreamFile) {
        this.inputStreamFile = inputStreamFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentTypeUploaded() {
        return contentTypeUploaded;
    }

    public void setContentTypeUploaded(String contentTypeUploaded) {
        this.contentTypeUploaded = contentTypeUploaded;
    }

    public Integer getAsignedResponsable() {
        return asignedResponsable;
    }

    public void setAsignedResponsable(Integer asignedResponsable) {
        this.asignedResponsable = asignedResponsable;
    }

    public List<SelectItem> getSelectResponsables() {
        return selectResponsables;
    }

    public void setSelectResponsables(List<SelectItem> selectResponsables) {
        this.selectResponsables = selectResponsables;
    }

    public Integer getAsignedProveedor() {
        return asignedProveedor;
    }

    public void setAsignedProveedor(Integer asignedProveedor) {
        this.asignedProveedor = asignedProveedor;
    }

    public List<SelectItem> getSelectProveedores() {
        return selectProveedores;
    }

    public void setSelectProveedores(List<SelectItem> selectProveedores) {
        this.selectProveedores = selectProveedores;
    }

    public List<ResponsableProducto> getAll_records_reponsable() {
        return all_records_reponsable;
    }

    public void setAll_records_reponsable(List<ResponsableProducto> all_records_reponsable) {
        this.all_records_reponsable = all_records_reponsable;
    }

    public ResponsableProducto getRow_selected_Respnsable() {
        return row_selected_Respnsable;
    }

    public void setRow_selected_Respnsable(ResponsableProducto row_selected_Respnsable) {
        this.row_selected_Respnsable = row_selected_Respnsable;
    }

    public Integer getAsignedEmpleado() {
        return asignedEmpleado;
    }

    public void setAsignedEmpleado(Integer asignedEmpleado) {
        this.asignedEmpleado = asignedEmpleado;
    }

    public List<SelectItem> getSelectEmpleados() {
        return selectEmpleados;
    }

    public void setSelectEmpleados(List<SelectItem> selectEmpleados) {
        this.selectEmpleados = selectEmpleados;
    }

    public List<String> getAsignedtipos() {
        return asignedtipos;
    }

    public void setAsignedtipos(List<String> asignedtipos) {
        this.asignedtipos = asignedtipos;
    }

    public List<SelectItem> getSelectTiposProductos() {
        return selectTiposProductos;
    }

    public void setSelectTiposProductos(List<SelectItem> selectTiposProductos) {
        this.selectTiposProductos = selectTiposProductos;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public Parametro getAsignedEstatusActive() {
        return asignedEstatusActive;
    }

    public void setAsignedEstatusActive(Parametro asignedEstatusActive) {
        this.asignedEstatusActive = asignedEstatusActive;
    }

    public Integer getAsignedEmpleadoJefe() {
        return asignedEmpleadoJefe;
    }

    public void setAsignedEmpleadoJefe(Integer asignedEmpleadoJefe) {
        this.asignedEmpleadoJefe = asignedEmpleadoJefe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isReasignar() {
        return reasignar;
    }

    public void setReasignar(boolean reasignar) {
        this.reasignar = reasignar;
    }

    public List<FileDTO> getFileDTO_List() {
        return fileDTO_List;
    }

    public void setFileDTO_List(List<FileDTO> fileDTO_List) {
        this.fileDTO_List = fileDTO_List;
    }

    public List<Seguimiento> getSeguimiento_List() {
        return seguimiento_List;
    }

    public void setSeguimiento_List(List<Seguimiento> seguimiento_List) {
        this.seguimiento_List = seguimiento_List;
    }

    public boolean isIsCancelado() {
        return isCancelado;
    }

    public void setIsCancelado(boolean isCancelado) {
        this.isCancelado = isCancelado;
    }

    public boolean isIsConfirmado() {
        return isConfirmado;
    }

    public void setIsConfirmado(boolean isConfirmado) {
        this.isConfirmado = isConfirmado;
    }

    public boolean isIsAdministrador() {
        return isAdministrador;
    }

    public void setIsAdministrador(boolean isAdministrador) {
        this.isAdministrador = isAdministrador;
    }

    public boolean isIs_EVALUATION_ADMINISTRATOR() {
        return is_EVALUATION_ADMINISTRATOR;
    }

    public void setIs_EVALUATION_ADMINISTRATOR(boolean is_EVALUATION_ADMINISTRATOR) {
        this.is_EVALUATION_ADMINISTRATOR = is_EVALUATION_ADMINISTRATOR;
    }

    public boolean isIs_SUPPLIER() {
        return is_SUPPLIER;
    }

    public void setIs_SUPPLIER(boolean is_SUPPLIER) {
        this.is_SUPPLIER = is_SUPPLIER;
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
