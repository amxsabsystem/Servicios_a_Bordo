package com.aeromexico.pab.backend.remote.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Paginated Result Generic Result for paginated Data-Table.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

public class PaginatedResult<T> implements java.io.Serializable {
    private List<T> paginatedResultList;
    private int     totalCount;        
    private int     pageSize;

    private PaginatedResult(){
    }

    public PaginatedResult(List<T> paginatedResultList, int totalCount, int pageSize) {
        this.paginatedResultList = paginatedResultList;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
    }

    /**
     * @return the paginatedResultList
     */
    public List<T> getPaginatedResultList() {
        return paginatedResultList;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }    
}
