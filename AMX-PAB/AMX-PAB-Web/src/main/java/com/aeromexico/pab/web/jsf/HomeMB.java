package com.aeromexico.pab.web.jsf;

import com.aeromexico.pab.backend.remote.ComunicadoAreasFacadeRemote;
import com.aeromexico.pab.backend.remote.ComunicadoFacadeRemote;
import com.aeromexico.pab.backend.remote.ParametroFacadeRemote;
import com.aeromexico.pab.entity.ComunicadoAreas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erick Diaz
 */
@Named(value = "homeMB")
@SessionScoped
public class HomeMB implements Serializable {

    public HomeMB() {
    }

    private List<Banner> list_banner;

    private static Logger logger = LoggerFactory.getLogger(HomeMB.class.getName());

    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/parametro_RSB")
    ParametroFacadeRemote ParametroFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/comunicado_RSB")
    ComunicadoFacadeRemote comunicadoFacadeRemote;
    @EJB(lookup = "java:global/AMX-PAB-BackEnd-ear-2.0.2/AMX-PAB-BackEnd-ejb-2.0.2/comunicadoAreas_RSB")
    ComunicadoAreasFacadeRemote comunicadoAreaFacadeRemote;

    @Inject
    @Named("localeInfo")
    LocaleInfo localeInfo;

    @PostConstruct
    public void init() {
        list_banner = new ArrayList<Banner>();
        int id_int = 0;
        try {
            List<ComunicadoAreas> lista = comunicadoAreaFacadeRemote.findAll();
            if (!lista.isEmpty()) {
                for (ComunicadoAreas ca : lista) {
                    Banner banner = new Banner();
                    banner.setMensaje(ca.getComunicado().getMensajeSlider());
                    banner.setTitulo(ca.getComunicado().getTitulo());
                    banner.setUrlImagen(ca.getArea().getUrlMultimedia());
                    banner.setId_int(id_int + "");
                    banner.setClase(id_int == 0 ? "active" : "");
                    list_banner.add(banner);
                    id_int++;
                }
            } else {
                Banner banner = new Banner();
                banner.setMensaje("");
                banner.setTitulo("");
                banner.setUrlImagen("/resources/img/vacio.png");
                banner.setId_int("0");
                banner.setClase("active");
                list_banner.add(banner);
            }
        } catch (Exception ex) {
            Banner banner = new Banner();
            banner.setMensaje("");
            banner.setTitulo("");
            banner.setUrlImagen("/resources/img/vacio.png");
            banner.setId_int("0");
            banner.setClase("active");
            list_banner.add(banner);

        }

    }

    public List<Banner> getList_banner() {
        return list_banner;
    }

    public void setList_banner(List<Banner> list_banner) {
        this.list_banner = list_banner;
    }

}
