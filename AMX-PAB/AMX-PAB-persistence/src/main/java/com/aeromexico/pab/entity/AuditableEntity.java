package com.aeromexico.pab.entity;

/**
 * Interface for Auditable Operations definition.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

public interface AuditableEntity {
    Short getStatus();
    void setStatus(Short v);
    
    String getUsuarioCreo();
    void setUsuarioCreo(String v);
    
    java.sql.Timestamp getFechaCreo();
    void setFechaCreo(java.sql.Timestamp v);
    
    String getUsuarioModifico();
    void setUsuarioModifico(String v) ;
    
    java.sql.Timestamp getFechaModifico() ;
    void setFechaModifico(java.sql.Timestamp v) ;
    
}
