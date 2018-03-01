package com.aeromexico.pab.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Alfredo Estrada
 */
public class JSFItemEntityConverter<E> implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        E x=null;
        return x;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
