package com.aeromexico.pab.web.jsf;

import com.aeromexico.pab.entity.Producto;
import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Erick Diaz
 */
@ManagedBean
@FacesConverter(value="productosConverter")
public class ProductosConverter implements Converter{
    
    @PersistenceContext
    private transient EntityManager em;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return em.find(Producto.class, new Integer(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Producto producto;
        producto = (Producto) value;
        return String.valueOf(producto.getIdProducto());

    }
    
}
