package com.aeromexico.pab.persistence.queryloader.dto;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alfredo Estrada
 */
@XmlRootElement(name = "NativePreparedStatement")
@XmlAccessorType(XmlAccessType.FIELD)
public class NativePreparedStatement  implements Serializable{
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    String id;    

    @XmlElementWrapper(name="StatementParameters",required = false, nillable = true)
    @XmlElement(name = "StatementParameter")
    List<StatementParameter> StatementParameters;
    
    @XmlElement(name = "SQLQuery")
    @XmlJavaTypeAdapter(AdapterCDATA.class)
    String SQLQuery;    

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("NativePreparedStatement{");
        
        
        sb.append("id:");
        sb.append(id);
        sb.append(", StatementParameters:");
        sb.append(StatementParameters);
        sb.append(", SQLQuery:");
        sb.append(SQLQuery);
        sb.append("}");
        
        return sb.toString();
    }

    public String getSQLQuery() {
        return SQLQuery;
    }

    public Iterator<StatementParameter> getStatementParameters() {
        return StatementParameters.iterator();
    }    
}
