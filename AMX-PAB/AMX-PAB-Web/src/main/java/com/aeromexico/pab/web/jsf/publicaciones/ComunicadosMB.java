package com.aeromexico.pab.web.jsf.publicaciones;

import com.aeromexico.pab.backend.Constantes;
import com.aeromexico.pab.backend.remote.AreaFacadeRemote;
import com.aeromexico.pab.backend.remote.BitacoraComunicadoFacadeRemote;
import com.aeromexico.pab.backend.remote.CiudadFacadeRemote;
import com.aeromexico.pab.backend.remote.ComunicadoAreasFacadeRemote;
import com.aeromexico.pab.backend.remote.ComunicadoFacadeRemote;
import com.aeromexico.pab.backend.remote.ComunicadoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.ContactoProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.EmpleadoFacadeRemote;
import com.aeromexico.pab.backend.remote.EstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.PaisFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.backend.remote.ProveedorEstacionFacadeRemote;
import com.aeromexico.pab.backend.remote.RegionFacadeRemote;
import com.aeromexico.pab.backend.remote.TemaComunicadoFacadeRemote;
import com.aeromexico.pab.entity.BitacoraComunicado;
import com.aeromexico.pab.entity.Comunicado;
import com.aeromexico.pab.entity.ComunicadoAreas;
import com.aeromexico.pab.entity.ComunicadoProveedorEstacion;
import com.aeromexico.pab.entity.ContactoProveedorEstacion;
import com.aeromexico.pab.entity.Empleado;
import com.aeromexico.pab.entity.Parametro;
import com.aeromexico.pab.entity.ProveedorEstacion;
import com.aeromexico.pab.entity.TemaComunicado;
import com.aeromexico.pab.web.jsf.LocaleInfo;
import com.aeromexico.pab.web.jsf.SesionInfo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "comunicadosMB")
@SessionScoped
public class ComunicadosMB implements Serializable {

    public ComunicadosMB() {
    }

    private List<Comunicado> list_comunicado;
    private Comunicado row_selected = null;
    private TemaComunicado row_selected_tc = null;
    boolean modificarRegistro = false;
    private List<Comunicado> all_records;
    private List<TemaComunicado> all_records_tc;

    private Integer asignedRevision;
    private List<SelectItem> selectRevisiones;
    private Integer asignedTema;
    private List<SelectItem> selectTemas;
    private String asignedReferencia;
    private List<SelectItem> selectRefrencias;
    private Integer asignedAccion;
    private List<SelectItem> selectAccioness;
    private Integer asignedIdioma;
    private List<SelectItem> selectIdiomas;
    private Integer asignedRegion;
    private List<SelectItem> selectRegiones;
    private Integer asignedEstacion;
    private List<SelectItem> selectEstaciones;
    private List<Integer> asignedProveedores;
    private List<SelectItem> selectProveedores;
    private List<Integer> asignedArea;
    private List<SelectItem> selectAreas;
    private boolean idiomaIngles;
    private String textoComunicado;
    private String fechaSistema;
    private String titulo;
    private Parametro estatusVigente;
    private Parametro estatusObsoleto;

    private boolean isVistaPreliminar;
    private String textoComunicadoVistaPrevia;
    private String proveedores;
    private String temaString;
    private Date fechaVigenciaDe;
    private Date fechaVigenciaHasta;

    private List<ProveedorEstacion> dirigidos;
    private String fecha_vp;
    private String usuario_vp;
    private String fechaVigenciaDe_vp;
    private String fechaVigenciaHasta_vp;
    private String referencia;
    private List<BitacoraComunicado> all_records_bitacoraLeidos;
    
    private Integer contadorLeidos;
    private Integer contadorTotalesLeidos;
    private Integer calculo_leidos;

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/comunicado_RSB")
    ComunicadoFacadeRemote comunicadoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/temaComunicado_RSB")
    TemaComunicadoFacadeRemote temaComunicadoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote parametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/empleado_RSB")
    EmpleadoFacadeRemote empleadoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/pais_RSB")
    PaisFacadeRemote paisesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/ciudad_RSB")
    CiudadFacadeRemote estadoCiudadFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/comunicadoProveedorEstacion_RSB")
    ComunicadoProveedorEstacionFacadeRemote comunicadoProveedorEstacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/estacion_RSB")
    EstacionFacadeRemote estacioneFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/proveedorEstacion_RSB")
    ProveedorEstacionFacadeRemote proveedorEstacionFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/region_RSB")
    RegionFacadeRemote regionesFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/area_RSB")
    AreaFacadeRemote areaFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/comunicadoAreas_RSB")
    ComunicadoAreasFacadeRemote comunicadoAreaFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/bitacoraComunicado_RSB")
    BitacoraComunicadoFacadeRemote bitacoraComunicadoFacadeRemote;

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
        dirigidos = new ArrayList<>();
        textoComunicadoVistaPrevia = "";
        temaString = "";
        proveedores = "";
        isVistaPreliminar = false;
        list_comunicado = new ArrayList<>();
        idiomaIngles = false;
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        fechaSistema = dateFormat.format(now);
        row_selected_tc = new TemaComunicado();

        row_selected = new Comunicado();

        selectRevisiones = new ArrayList<>();
        selectRevisiones.add(localeInfo.createSelectIntKeyFirstItem());
        selectRevisiones.add(new SelectItem(1, "1"));
        selectRevisiones.add(new SelectItem(2, "2"));
        selectRevisiones.add(new SelectItem(3, "3"));

        asignedArea = new ArrayList<>();
        selectAreas = new ArrayList<>();
        try {
            areaFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectAreas.add(new SelectItem(regs.getIdArea(),
                        (localeInfo.getLanguage().equals("es") ? "[" + regs.getClave() + "]" + " " + regs.getNombreEs() : "[" + regs.getClave() + "]" + " " + regs.getNombreEn())
                ));
            });
        } catch (Exception ex) {

        }

        selectTemas = new ArrayList<>();
        selectTemas.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            temaComunicadoFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectTemas.add(new SelectItem(regs.getIdTemaComunicado(),
                        (localeInfo.getLanguage().equals("es") ? regs.getTemaEspaniol() : regs.getTemaIngles())
                ));
            });
        } catch (Exception ex) {

        }

        selectRefrencias = new ArrayList<>();
        selectRefrencias.add(localeInfo.createSelectStringKeyFirstItem());
        try {
            comunicadoFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectRefrencias.add(new SelectItem(regs.getReferencia(), regs.getReferencia()));
            });
        } catch (Exception ex) {

        }

        selectAccioness = new ArrayList<>();
        selectAccioness.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            parametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("comunicAcciones"))).forEachOrdered((regs) -> {
                selectAccioness.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
        } catch (Exception ex) {
        }

        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("estatusComunicado"))).filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                if (regs.getValorEs().equals("Vigente")) {
                    estatusVigente = regs;
                } else {
                    estatusObsoleto = regs;
                }
            });
        } catch (Exception ex) {
        }

        selectIdiomas = new ArrayList<>();
        try {
            ParametroFacadeRemote.findAll().stream().filter((regs) -> (regs.getClave().equals("idioma"))).filter((regs) -> (!regs.getValorEs().equals("Ambos"))).forEachOrdered((regs) -> {
                Comunicado comunicado = new Comunicado();
                comunicado.setIdIdioma(regs);
                list_comunicado.add(comunicado);
                selectIdiomas.add(new SelectItem(regs.getIdParametro(),
                        localeInfo.getLanguage().equals("es") ? regs.getValorEs() : regs.getValorEn()
                ));
            });
            asignedIdioma = (Integer) selectIdiomas.get(0).getValue();
        } catch (Exception ex) {
        }

        asignedRegion = -1;
        selectRegiones = new ArrayList<>();
        selectRegiones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            regionesFacadeRemote.findAll().stream().filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectRegiones.add(new SelectItem(regs.getIdRegion(), regs.getCveRegion()));
            });
        } catch (Exception ex) {

        }

    }

    public void cambiarIdioma() {

        Comunicado comunicadoEsp = new Comunicado();
        Comunicado comunicadoIng = new Comunicado();
        for (Comunicado com : list_comunicado) {
            if (com.getIdIdioma().getValorEn().equals("English")) {
                comunicadoIng = com;
            } else {
                comunicadoEsp = com;
            }
        }

        if (idiomaIngles) {
            comunicadoIng.setTitulo(titulo);
            comunicadoIng.setMensaje(textoComunicado);
            comunicadoIng.setStatus((short) 6);
        } else {
            comunicadoEsp.setMensaje(textoComunicado);
            comunicadoEsp.setTitulo(titulo);
            comunicadoEsp.setStatus((short) 6);
        }

        titulo = null;
        textoComunicado = null;

        idiomaIngles = ParametroFacadeRemote.findByPK(asignedIdioma).getValorEn().equals("English");
        list_comunicado.forEach((com) -> {
            if (com.getIdIdioma().getValorEn().equals("English") && idiomaIngles) {
                titulo = com.getTitulo();
                textoComunicado = com.getMensaje();
            } else if (com.getIdIdioma().getValorEn().equals("Spanish") && !idiomaIngles) {
                titulo = com.getTitulo();
                textoComunicado = com.getMensaje();
            }
        });

        list_comunicado.clear();
        list_comunicado.add(comunicadoIng);
        list_comunicado.add(comunicadoEsp);

    }

    public List<TemaComunicado> findAllTC() {
        if (all_records_tc == null) {
            try {
                all_records_tc = new ArrayList<>();
                all_records_tc = temaComunicadoFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records_tc = null;
            }
        }
        return all_records_tc;
    }

    public List<Comunicado> findAllVigentes() {
        if (all_records == null) {
            try {
                all_records = new ArrayList<>();
                all_records = comunicadoFacadeRemote.findAll();
            } catch (Exception ex) {
                all_records = null;
            }
        }
        return all_records;
    }

    public void saveTC() {
        try {
        boolean existe=false;    
        for(TemaComunicado tc: temaComunicadoFacadeRemote.findAll()) {
               if(tc.getTemaEspaniol().equals(row_selected_tc.getTemaEspaniol()) && tc.getTemaIngles().equals(row_selected_tc.getTemaIngles())){
                messgeValidateError("form:saveButton", "El Tema ya existe");
                existe=true; 
               }
        }
        if(!existe){    
            row_selected_tc.setStatus((short) 1);
            temaComunicadoFacadeRemote.create(row_selected_tc);
            all_records_tc = null;
            findAllTC();
            init();
            messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
        }
        } catch (Exception e) {
            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public String modifyRowTC(TemaComunicado row_selected_tc) {
        String redirect = "/portal/publicaciones/comunicados/temasComunicados?faces-redirect=true";
        modificarRegistro = true;
        this.row_selected_tc = row_selected_tc;
        return redirect;
    }

    public void updateTC() {
        try {
            temaComunicadoFacadeRemote.update(row_selected_tc);
            all_records_tc = null;
            modificarRegistro = false;
            findAllTC();
            messgeValidateInfo("form:updateButton", "Registro actualizado correctamente ");
        } catch (Exception e) {
            messgeValidateError("form:updateButton", "Error:" + e.getMessage());
        }
    }

    public boolean validate(String boton) {
        boolean retorno = true;
        if (asignedTema == -1) {
            messgeValidateError(boton, "Debe seleccionar un Tema");
            return false;
        }
        if (asignedRevision == -1) {
            messgeValidateError(boton, "Debe seleccionar un Número de Revisión");
            return false;
        }
        if (textoComunicado == null) {
            messgeValidateError(boton, "Debe ingresar un Mensaje");
            return false;
        }
        if (titulo == null) {
            messgeValidateError(boton, "Debe ingresar un Título");
            return false;
        }
        for (Comunicado com : list_comunicado) {
            if (com.getStatus() != null && com.getStatus() == ((short) 6)) {
                if (com.getMensaje() == null || (com.getMensaje() != null && com.getMensaje().length() <= 1)) {
                    messgeValidateError(boton, "Debe ingresar un mensaje");
                    return false;
                }
                if (com.getTitulo() == null || (com.getTitulo() != null && com.getTitulo().length() <= 1)) {
                    messgeValidateError(boton, "Debe ingresar un Título");
                    return false;
                }
            }
        }
        if (fechaVigenciaDe == null) {
            messgeValidateError(boton, "Debe ingresar una fecha de inicio de Publicación");
            return false;
        }
        if (fechaVigenciaHasta == null) {
            messgeValidateError(boton, "Debe ingresar una fecha de fin de Publicación");
            return false;
        }

        return retorno;
    }

    public void closedialogSeguimiento() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('seguimientoDialog').hide();");
    }

    public void seguimiento(Comunicado row) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('seguimientoDialog').show();");

        referencia = row.getReferencia();
        fecha_vp = dateFormat.format(new Date(row.getFechaCreo().getTime()));
        titulo = row.getTitulo();
        ComunicadoProveedorEstacion entity = new ComunicadoProveedorEstacion();
        proveedores = "";
        entity.setComunicado(row);
        Integer contTotales =0;
        try {
            for(ComunicadoProveedorEstacion cpe :comunicadoProveedorEstacionFacadeRemote.findAllLike(entity)){
                proveedores += cpe.getProveedorEstacion().getClaveProveedorEstacion() + " ";
                contTotales= cpe.getContactosDirigidos();
            }
        } catch (Exception ex) {
        }

        all_records_bitacoraLeidos = new ArrayList<>();
        BitacoraComunicado entity_bita = new BitacoraComunicado();
        entity_bita.setComunicado(row);
        try {
            bitacoraComunicadoFacadeRemote.findAllLike(entity_bita).forEach((bc) -> {
                all_records_bitacoraLeidos.add(bc);
            });
        } catch (Exception ex) {
        }
        
        contadorLeidos = all_records_bitacoraLeidos.size();
        contadorTotalesLeidos =contTotales;
        calculo_leidos =(contadorTotalesLeidos-contadorLeidos);
        
    }

    public void closedialogVp() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('vistaPreliminar').hide();");
    }

    public void vistaPrevia(Comunicado row) {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('vistaPreliminar').show();");
        fecha_vp = dateFormat.format(new Date(row.getFechaCreo().getTime()));
        usuario_vp = row.getUsuarioCreo();

        ComunicadoProveedorEstacion entity = new ComunicadoProveedorEstacion();
        proveedores = "";
        entity.setComunicado(row);
        comunicadoProveedorEstacionFacadeRemote.findAllLike(entity).forEach((cpe) -> {
            proveedores += cpe.getProveedorEstacion().getClaveProveedorEstacion() + " ";
        });

        temaString = row.getIdTema().getTemaEspaniol();
        titulo = row.getTitulo();
        textoComunicadoVistaPrevia = row.getMensaje();
        fechaVigenciaDe_vp = dateFormat.format(row.getFechaInicioPublicacion());
        fechaVigenciaHasta_vp = dateFormat.format(row.getFechaFinPublicacion());

        /*Bitacora */
        try {
            ContactoProveedorEstacion cpe = null;
            ContactoProveedorEstacion entity_cpe = new ContactoProveedorEstacion();
            entity_cpe.setUsuario(sesionInfo.getCurrentUser());
            for (ContactoProveedorEstacion cpe_u : contactoProveedorEstacionFacadeRemote.findAllLike(entity_cpe)) {
                cpe = cpe_u;
            }
            ComunicadoProveedorEstacion compe = null;
            if (cpe != null) {
                ComunicadoProveedorEstacion entity_compe = new ComunicadoProveedorEstacion();
                entity_compe.setComunicado(row);
                entity_compe.setProveedorEstacion(cpe.getProveedorEstacion());
                for (ComunicadoProveedorEstacion compe_i : comunicadoProveedorEstacionFacadeRemote.findAllLike(entity_compe)) {
                    compe = compe_i;
                }
            }
            //usu asociado a x Provedor estacion y el comuncado asociado a x Provedor estacion
            if (compe != null) {
                BitacoraComunicado entity_visita = new BitacoraComunicado();
                entity_visita.setComunicado(row);
                entity_visita.setContactoProveedorEstacion(cpe);
                entity_visita.setProveedorEstacion(cpe.getProveedorEstacion());
                if (bitacoraComunicadoFacadeRemote.findAllLike(entity_visita).isEmpty()) {
                    entity_visita.setFecha(new java.sql.Timestamp(now.getTime()));
                    bitacoraComunicadoFacadeRemote.create(entity_visita);
                }
            }
        } catch (Exception ex) {
        }

    }
    
    public boolean validateVP(){
     boolean retorno = true;
        if (asignedTema == -1) {
            messgeValidateError("form:vistaPreliminarButton", "Debe seleccionar un Tema");
            return false;
        }
        if (asignedRevision == -1) {
            messgeValidateError("form:vistaPreliminarButton", "Debe seleccionar un Número de Revisión");
            return false;
        }
        if (textoComunicado == null) {
            messgeValidateError("form:vistaPreliminarButton", "Debe ingresar un Mensaje");
            return false;
        }
        if (titulo == null) {
            messgeValidateError("form:vistaPreliminarButton", "Debe ingresar un Título");
            return false;
        }
        for (Comunicado com : list_comunicado) {
            if (com.getStatus() != null && com.getStatus() == ((short) 6)) {
                if (com.getMensaje() == null || (com.getMensaje() != null && com.getMensaje().length() <= 1)) {
                    messgeValidateError("form:vistaPreliminarButton", "Debe ingresar un mensaje");
                    return false;
                }
                if (com.getTitulo() == null || (com.getTitulo() != null && com.getTitulo().length() <= 1)) {
                    messgeValidateError("form:vistaPreliminarButton", "Debe ingresar un Título");
                    return false;
                }
            }
        }
    
    
    return retorno;    
    }

    public void vistaPreliminar() {
        if (validateVP()) {
        
        isVistaPreliminar = true;

        for (Parametro regs : ParametroFacadeRemote.findAll()) {
            if (regs.getClave().equals("idioma")) {
                if (regs.getValorEn().equals("Spanish")) {
                    asignedIdioma = regs.getIdParametro();
                }
                break;
            }
        }
        cambiarIdioma();

        list_comunicado.stream().filter((com) -> (com.getIdIdioma().getValorEn().equals("Spanish"))).map((com) -> {
            titulo = com.getTitulo();
            return com;
        }).map((com) -> {
            temaString = temaComunicadoFacadeRemote.findByPK(asignedTema).getTemaEspaniol();
            return com;
        }).forEachOrdered((com) -> {
            textoComunicadoVistaPrevia = com.getMensaje();
        });
        }
    }

    public void Regresar() {
        isVistaPreliminar = false;
    }

    public void publicar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('bannerComunicado').show();");
    }

    public void cancelar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('bannerComunicado').hide();");
    }

    public void saveDirigidos() {
        for (Object pe : asignedProveedores) {
            dirigidos.add(proveedorEstacionFacadeRemote.findByPK(new Integer((String) pe)));
        }
    }

    public void otraEstacion() {
        for (Object pe : asignedProveedores) {
            dirigidos.add(proveedorEstacionFacadeRemote.findByPK(new Integer((String) pe)));
        }

        asignedRegion = -1;
        selectEstaciones.clear();
        selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());
        asignedProveedores.clear();
        selectProveedores.clear();
    }

    public void save() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Comunicado comunicado = null;
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy", Locale.US);
        try {
            Empleado empleado = null;
                Empleado entity_empleado = new Empleado();
                entity_empleado.setUsuario(sesionInfo.getCurrentUser());
                for(Empleado emp: empleadoFacadeRemote.findAllLike(entity_empleado)){
                    empleado= emp;
            }
                
            if (empleado == null) {
                 messgeValidateError("form:saveButton", "El usuario no tiene los privilegios para crear un comunicado");   
            }else if (validate("form:saveButton")) {
                row_selected.setStatus((short) 1);
                row_selected.setRefOtroComunicado(asignedReferencia);
                row_selected.setAccionComunicado(asignedReferencia != null ? parametroFacadeRemote.findByPK(asignedAccion) : null);
                row_selected.setEmpleado(empleado);
                row_selected.setArea(empleado.getArea());
                row_selected.setEstatusComunicado(estatusVigente);
                row_selected.setFechaInicioPublicacion(new java.sql.Date(fechaVigenciaDe.getTime()));
                row_selected.setFechaFinPublicacion(new java.sql.Date(fechaVigenciaHasta.getTime()));
                row_selected.setIdIdioma(parametroFacadeRemote.findByPK(asignedIdioma));
                row_selected.setIdTema(temaComunicadoFacadeRemote.findByPK(asignedTema));
                row_selected.setNoRevision(asignedRevision.shortValue());
                String referencia = "";
                row_selected.setReferencia(referencia);
                for (Comunicado com : list_comunicado) {
                    if (com.getStatus() != null && com.getStatus() == ((short) 6)) {
                        row_selected.setMensaje(com.getMensaje());
                        row_selected.setTitulo(com.getTitulo());
                    } else {
                        row_selected.setMensaje(textoComunicado);
                        row_selected.setTitulo(titulo);
                    }
                    row_selected.setDirigidoSiNo(dirigidos.size()>0?Constantes.PERSITENCE_STATUS_ACTIVO:Constantes.PERSITENCE_STATUS_IANACTIVO); 
                    
                    Comunicado newReg = comunicadoFacadeRemote.create(row_selected);
                    referencia = "C"
                            + (parametroFacadeRemote.findByPK(com.getIdIdioma().getIdParametro()).getValorEs()).substring(0, 1).toUpperCase() + "-" //idiomaClave
                            + paisesFacadeRemote.findByPK(estadoCiudadFacadeRemote.findByPK(empleado.getEstacion().getCiudad().getIdCiudad()).getPais().getIdPais()).getRegion().getCveRegion()
                            + empleado.getArea().getClave()
                            + yearFormat.format(now)
                            + (new Formatter().format("%05d", newReg.getIdComunicado()));
                    newReg.setReferencia(referencia);
                    comunicado = comunicadoFacadeRemote.update(newReg);

                    try {
                        for (ProveedorEstacion pe : dirigidos) {
                            ComunicadoProveedorEstacion entity = new ComunicadoProveedorEstacion();
                            entity.setComunicado(comunicado);
                            entity.setProveedorEstacion(pe);
                            int contador=0;
                            for(Object id_pe : asignedProveedores){
                                ProveedorEstacion prv_e=proveedorEstacionFacadeRemote.findByPK(new Integer((String)id_pe));
                                ContactoProveedorEstacion entity_cpe = new ContactoProveedorEstacion();
                                entity_cpe.setProveedorEstacion(prv_e);
                                contador = contactoProveedorEstacionFacadeRemote.findAllLike(entity_cpe).stream().map((_item) -> 1).reduce(contador, Integer::sum);
                            }
                            entity.setContactosDirigidos(contador);
                            comunicadoProveedorEstacionFacadeRemote.create(entity);
                        }
                        if (asignedArea != null && !asignedArea.isEmpty()) {
                            for (Object area : asignedArea) {
                                ComunicadoAreas entity = new ComunicadoAreas();
                                entity.setComunicado(comunicado);
                                entity.setArea(areaFacadeRemote.findByPK(new Integer((String) area)));
                                comunicadoAreaFacadeRemote.create(entity);
                            }
                        }
                    } catch (Exception ex) {
                    }

                }

                all_records = null;

                messgeValidateInfo("form:saveButton", "Registro Cargado correctamente ");
            }
        } catch (Exception e) {

            messgeValidateError("form:saveButton", "Error:" + e.getMessage());
        }
    }

    public void update() {
    }

    public void verUsuarios() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dirigirComunicado').show();");

    }

    public void getProveedoresEstacion() {

        selectProveedores = new ArrayList<>();

        try {
            proveedorEstacionFacadeRemote.findAll().stream().filter((regs) -> (Objects.equals(regs.getEstacion().getIdEstacion(), asignedEstacion))).filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectProveedores.add(new SelectItem(regs.getIdProveedorEstacion(), regs.getClaveProveedorEstacion()));
            });
        } catch (Exception ex) {
        }

    }

    public void getEstaciones() {
        selectEstaciones = new ArrayList<>();
        selectEstaciones.add(localeInfo.createSelectIntKeyFirstItem());
        try {
            estacioneFacadeRemote.findAll().stream().filter((regs) -> (Objects.equals(regs.getCiudad().getPais().getRegion().getIdRegion(), asignedRegion))).filter((regs) -> (regs.getStatus().equals((short) 1))).forEachOrdered((regs) -> {
                selectEstaciones.add(new SelectItem(regs.getIdEstacion(), regs.getClaveIata() + " - " + regs.getNombre()));
            });
        } catch (Exception ex) {
        }
        asignedEstacion = -1;
    }

    public String nuevoTema() {
        String redirect = "/portal/publicaciones/comunicados/temasComunicados?faces-redirect=true";
        modificarRegistro = false;
        all_records_tc = null;
        findAllTC();
        return redirect;
    }

    public String returnPublicar() {
        String redirect = "/portal/publicaciones/comunicados/publicar?faces-redirect=true";
        modificarRegistro = false;
        return redirect;
    }

    public Comunicado getRow_selected() {
        return row_selected;
    }

    public void setRow_selected(Comunicado row_selected) {
        this.row_selected = row_selected;
    }

    public boolean isModificarRegistro() {
        return modificarRegistro;
    }

    public void setModificarRegistro(boolean modificarRegistro) {
        this.modificarRegistro = modificarRegistro;
    }

    public TemaComunicado getRow_selected_tc() {
        return row_selected_tc;
    }

    public void setRow_selected_tc(TemaComunicado row_selected_tc) {
        this.row_selected_tc = row_selected_tc;
    }

    public List<Comunicado> getAll_records() {
        return all_records;
    }

    public void setAll_records(List<Comunicado> all_records) {
        this.all_records = all_records;
    }

    public List<TemaComunicado> getAll_records_tc() {
        return all_records_tc;
    }

    public void setAll_records_tc(List<TemaComunicado> all_records_tc) {
        this.all_records_tc = all_records_tc;
    }

    public Integer getAsignedRevision() {
        return asignedRevision;
    }

    public void setAsignedRevision(Integer asignedRevision) {
        this.asignedRevision = asignedRevision;
    }

    public List<SelectItem> getSelectRevisiones() {
        return selectRevisiones;
    }

    public void setSelectRevisiones(List<SelectItem> selectRevisiones) {
        this.selectRevisiones = selectRevisiones;
    }

    public Integer getAsignedTema() {
        return asignedTema;
    }

    public void setAsignedTema(Integer asignedTema) {
        this.asignedTema = asignedTema;
    }

    public List<SelectItem> getSelectTemas() {
        return selectTemas;
    }

    public void setSelectTemas(List<SelectItem> selectTemas) {
        this.selectTemas = selectTemas;
    }

    public String getAsignedReferencia() {
        return asignedReferencia;
    }

    public void setAsignedReferencia(String asignedReferencia) {
        this.asignedReferencia = asignedReferencia;
    }

    public List<SelectItem> getSelectRefrencias() {
        return selectRefrencias;
    }

    public void setSelectRefrencias(List<SelectItem> selectRefrencias) {
        this.selectRefrencias = selectRefrencias;
    }

    public Integer getAsignedAccion() {
        return asignedAccion;
    }

    public void setAsignedAccion(Integer asignedAccion) {
        this.asignedAccion = asignedAccion;
    }

    public List<SelectItem> getSelectAccioness() {
        return selectAccioness;
    }

    public void setSelectAccioness(List<SelectItem> selectAccioness) {
        this.selectAccioness = selectAccioness;
    }

    public String getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(String fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Parametro getEstatusVigente() {
        return estatusVigente;
    }

    public void setEstatusVigente(Parametro estatusVigente) {
        this.estatusVigente = estatusVigente;
    }

    public Parametro getEstatusObsoleto() {
        return estatusObsoleto;
    }

    public void setEstatusObsoleto(Parametro estatusObsoleto) {
        this.estatusObsoleto = estatusObsoleto;
    }

    public Integer getAsignedIdioma() {
        return asignedIdioma;
    }

    public void setAsignedIdioma(Integer asignedIdioma) {
        this.asignedIdioma = asignedIdioma;
    }

    public List<SelectItem> getSelectIdiomas() {
        return selectIdiomas;
    }

    public void setSelectIdiomas(List<SelectItem> selectIdiomas) {
        this.selectIdiomas = selectIdiomas;
    }

    public boolean isIdiomaIngles() {
        return idiomaIngles;
    }

    public void setIdiomaIngles(boolean idiomaIngles) {
        this.idiomaIngles = idiomaIngles;
    }

    public List<Comunicado> getList_comunicado() {
        return list_comunicado;
    }

    public void setList_comunicado(List<Comunicado> list_comunicado) {
        this.list_comunicado = list_comunicado;
    }

    public String getTextoComunicado() {
        return textoComunicado;
    }

    public void setTextoComunicado(String textoComunicado) {
        this.textoComunicado = textoComunicado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isIsVistaPreliminar() {
        return isVistaPreliminar;
    }

    public void setIsVistaPreliminar(boolean isVistaPreliminar) {
        this.isVistaPreliminar = isVistaPreliminar;
    }

    public String getTextoComunicadoVistaPrevia() {
        return textoComunicadoVistaPrevia;
    }

    public void setTextoComunicadoVistaPrevia(String textoComunicadoVistaPrevia) {
        this.textoComunicadoVistaPrevia = textoComunicadoVistaPrevia;
    }

    public Date getFechaVigenciaDe() {
        return fechaVigenciaDe;
    }

    public void setFechaVigenciaDe(Date fechaVigenciaDe) {
        this.fechaVigenciaDe = fechaVigenciaDe;
    }

    public Date getFechaVigenciaHasta() {
        return fechaVigenciaHasta;
    }

    public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
        this.fechaVigenciaHasta = fechaVigenciaHasta;
    }

    public String getTemaString() {
        return temaString;
    }

    public void setTemaString(String temaString) {
        this.temaString = temaString;
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

    public List<Integer> getAsignedProveedores() {
        return asignedProveedores;
    }

    public void setAsignedProveedores(List<Integer> asignedProveedores) {
        this.asignedProveedores = asignedProveedores;
    }

    public List<SelectItem> getSelectProveedores() {
        return selectProveedores;
    }

    public void setSelectProveedores(List<SelectItem> selectProveedores) {
        this.selectProveedores = selectProveedores;
    }

    public String getProveedores() {
        return proveedores;
    }

    public void setProveedores(String proveedores) {
        this.proveedores = proveedores;
    }

    public List<ProveedorEstacion> getDirigidos() {
        return dirigidos;
    }

    public void setDirigidos(List<ProveedorEstacion> dirigidos) {
        this.dirigidos = dirigidos;
    }

    public List<Integer> getAsignedArea() {
        return asignedArea;
    }

    public void setAsignedArea(List<Integer> asignedArea) {
        this.asignedArea = asignedArea;
    }

    public List<SelectItem> getSelectAreas() {
        return selectAreas;
    }

    public void setSelectAreas(List<SelectItem> selectAreas) {
        this.selectAreas = selectAreas;
    }

    public String getFecha_vp() {
        return fecha_vp;
    }

    public void setFecha_vp(String fecha_vp) {
        this.fecha_vp = fecha_vp;
    }

    public String getUsuario_vp() {
        return usuario_vp;
    }

    public void setUsuario_vp(String usuario_vp) {
        this.usuario_vp = usuario_vp;
    }

    public String getFechaVigenciaDe_vp() {
        return fechaVigenciaDe_vp;
    }

    public void setFechaVigenciaDe_vp(String fechaVigenciaDe_vp) {
        this.fechaVigenciaDe_vp = fechaVigenciaDe_vp;
    }

    public String getFechaVigenciaHasta_vp() {
        return fechaVigenciaHasta_vp;
    }

    public void setFechaVigenciaHasta_vp(String fechaVigenciaHasta_vp) {
        this.fechaVigenciaHasta_vp = fechaVigenciaHasta_vp;
    }

    public List<BitacoraComunicado> getAll_records_bitacoraLeidos() {
        return all_records_bitacoraLeidos;
    }

    public void setAll_records_bitacoraLeidos(List<BitacoraComunicado> all_records_bitacoraLeidos) {
        this.all_records_bitacoraLeidos = all_records_bitacoraLeidos;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getContadorLeidos() {
        return contadorLeidos;
    }

    public void setContadorLeidos(Integer contadorLeidos) {
        this.contadorLeidos = contadorLeidos;
    }

    public Integer getContadorTotalesLeidos() {
        return contadorTotalesLeidos;
    }

    public void setContadorTotalesLeidos(Integer contadorTotalesLeidos) {
        this.contadorTotalesLeidos = contadorTotalesLeidos;
    }

    public Integer getCalculo_leidos() {
        return calculo_leidos;
    }

    public void setCalculo_leidos(Integer calculo_leidos) {
        this.calculo_leidos = calculo_leidos;
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
