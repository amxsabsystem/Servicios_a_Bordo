package com.aeromexico.pab.web.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author alfredo
 */

@Named(value = "localeInfo")
@SessionScoped
public class LocaleInfo implements Serializable{
	private static Logger logger = LoggerFactory.getLogger(LocaleInfo.class.getName());
    private Locale locale;
    private ResourceBundle rb;
	
	public LocaleInfo(){
		logger.debug("->LocaleInfo():");
	}

    @PostConstruct
    public void init() {
		Locale currentLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();		
        logger.debug("->init():currentLocale="+currentLocale);
    }

    public Locale getLocale() {
		if(locale == null){
			locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();			
		}
        return locale;
    }

    public String getLanguage() {		
        return getLocale().getLanguage();
    }
    
    public String getLanguage_localized() {
        final String sysLocale = "SYS_LOCALE_"+getLocale().getLanguage().toUpperCase();        
        final String lan_i18n = getLocalized(sysLocale);        
		return lan_i18n;
    }

    public void setLanguage(String language) {
		logger.debug("->setLanguage: "+language);
        locale = new Locale(language);
		logger.debug("->setLanguage: now locale = "+locale);                 
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        rb = null;
    }
    
    public SelectItem createSelectIntKeyFirstItem(){
        return new SelectItem(-1, getLocalized("SELECT_ONE_LBL"));
    }
    
    public SelectItem createSelectStringKeyFirstItem(){
        return new SelectItem("-1", getLocalized("SELECT_ONE_LBL"));
    }

    
    public String getLocalized(String msg){
        return getRB().getString(msg);
    }
    
    public ResourceBundle getRB(){
        if(rb == null){
            rb = ResourceBundle.getBundle("/com/aeromexico/pab/web/res_i18n", getLocale());
        }
        return rb;
    }

}