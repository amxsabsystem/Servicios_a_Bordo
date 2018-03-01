package com.aeromexico.pab.web.jsf;

import java.lang.annotation.Inherited;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.omnifaces.cdi.GraphicImageBean;

/**
 *
 * @author Erick Diaz
 */
@ManagedBean(name = "imageView")
@GraphicImageBean
public class ImageView {
    

    public  byte[] getBytesImage(String filename_bd){
        byte[] content =null;
        try {
            Path path = Paths.get(System.getProperty("AMX_PAB_MEDIA_FS_STORAGE") + "/" +  filename_bd );
            content = Files.readAllBytes(path);
        } catch (Exception e) {
            
        }
        return content;
    }
}
