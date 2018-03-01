package com.aeromexico.pab.persistence.queryloader.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alfredo Estrada
 */

@XmlRootElement(name = "PreparedStatements")
public class PreparedStatements implements Serializable{
    @XmlElementWrapper(name="NativePreparedStatementList")
    @XmlElement(name = "NativePreparedStatement")            
    List<NativePreparedStatement> NativePreparedStatementList;

    private transient LinkedHashMap<String,NativePreparedStatement> nativePreparedStatementMap;
    
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("PreparedStatements{");
        sb.append("NativePreparedStatementList:");
        sb.append(NativePreparedStatementList);
        sb.append("}");
        return sb.toString();
    }

    public PreparedStatements() {
    }
    
    public PreparedStatements(List<NativePreparedStatement> NativePreparedStatementList) {
        this.NativePreparedStatementList = NativePreparedStatementList;
    } 

    public NativePreparedStatement getNativePreparedStatement(String id) {
        if(nativePreparedStatementMap == null){
            nativePreparedStatementMap = new LinkedHashMap<String,NativePreparedStatement>();
            for(NativePreparedStatement nps: NativePreparedStatementList){
                nativePreparedStatementMap.put(nps.id, nps);
            }
        }
        return nativePreparedStatementMap.get(id);
    }
    
}
