package com.aeromexico.pab.persistence.queryloader.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
/**
 *
 * @author Alfredo Estrada
 */
@XmlRootElement(name = "StatementParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatementParameter implements Serializable{
    
    @XmlAttribute(name = "paranName",required = true)
    private String paranName;
    
    @XmlAttribute(name = "paramValue",required = false)
    private String paramValue;
    
    @XmlAttribute(name = "javaClass",required = false)
    private String javaClass;
    
    @XmlAttribute(name = "posParam",required = false)
    private Integer posParam;

    public StatementParameter() {
        javaClass = "java.lang.String";
    }

    /**
     * @return the paranName
     */
    public String getParanName() {
        return paranName;
    }

    /**
     * @param paranName the paranName to set
     */
    public void setParanName(String paranName) {
        this.paranName = paranName;
    }

    /**
     * @return the paramValue
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @param paramValue the paramValue to set
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * @return the javaClass
     */
    public String getJavaClass() {
        return javaClass;
    }

    /**
     * @param javaClass the javaClass to set
     */
    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }
    
    /**
     * @return the posParam
     */
    public Integer getPosParam() {
        return posParam;
    }

    /**
     * @param posParam the posParam to set
     */
    public void setPosParam(Integer posParam) {
        this.posParam = posParam;
    }
    
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("StatementParameter:{");
        if(posParam != null){
            sb.append("[").append(posParam).append("]");        
        }
        if(!javaClass.equals(String.class.getName())){
            sb.append("(").append(javaClass).append(")");
        }
        sb.append(paranName).append("=").append(paramValue);
        sb.append("}");
        return sb.toString();
    }    
}
