package com.aeromexico.pab.web.jsf;

import com.aeromexico.pab.web.dto.VersionInfo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author alfredo.estrada
 */
@Named(value = "SystemInfo")
@SessionScoped
public class SystemInfo implements Serializable {

	/**
	 * Creates a new instance of SystemInfo
	 */
	public SystemInfo() {
	}
	
	public VersionInfo getVersionInfo(){
        VersionInfo vi=new VersionInfo();
        return vi;		
	}
}
