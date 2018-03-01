package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.backend.remote.GenericSearchFacadeRemote;
import com.aeromexico.pab.backend.remote.util.PaginatedResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * SSB genericSearchFacade
 * 
 * @author Alfredo Estrada
 */
@Stateless(
    name         = "genericSearch_RSB",
    mappedName   = "GenericSearch_RSB",
    description  = "genericSearchFacadeRemote-Stateless-Session EJB-3.1"
)
public class GenericSearchFacade implements GenericSearchFacadeRemote {
    @Resource SessionContext ctx;

	@PersistenceContext(unitName = "AMX_PAB_PU")
	private EntityManager em;

    protected EntityManager getEntityManager() {
		return em;
	}

	public GenericSearchFacade() {		
	}

    @Override
    @TransactionAttribute(REQUIRED)
    public List<Object[]> loadQuery(String jql) {
        
        final Query q = getEntityManager().createQuery(jql);
        
        final List<Object[]> resultList = q.getResultList();
        
        return resultList;
    }

    @Override
    @TransactionAttribute(REQUIRED)
    public PaginatedResult<Object[]> loadPaginated(int first,int pageSize,String jqlBase,String sortField,Object sortOrder, Map<String,Object> filters) {
        PaginatedResult<Object[]> r=null;
        List<Object[]> resultList=null;
        
        StringBuilder sbBaseQuery=new StringBuilder();
        
        sbBaseQuery.append("from ");
        sbBaseQuery.append(jqlBase);
        sbBaseQuery.append(" _x ");
        
        boolean filterAdded=false;
        if(filters !=null && !filters.isEmpty()){
            filterAdded = true;
            sbBaseQuery.append(" where ( 1=1 ");
            final Set<String> keySet = filters.keySet();
            for(String k: keySet){
                sbBaseQuery.append(" and _x.").append(k);
                Object v=filters.get(k);
                if( v.getClass().equals(String.class)){
                    sbBaseQuery.append(" like ").append(" :").append(k);                
                } else {
                    sbBaseQuery.append(" = ").append(" :").append(k);
                }
            }
            sbBaseQuery.append(" ) ");
        }

        StringBuilder sbAQSort = new StringBuilder();

        if(sortField != null ){        
            StringBuilder sbSortExp=new StringBuilder(" order by ");
            sbSortExp.append(sortField);
            if(sortOrder != null ){
                if(sortOrder.toString().equalsIgnoreCase("ASCENDING")){
                    sbSortExp.append(" ASC ");
                    sbAQSort.append(sbSortExp);
                }else if(sortOrder.toString().equalsIgnoreCase("DESCENDING")){
                    sbSortExp.append(" DESC ");
                    sbAQSort.append(sbSortExp);
                }else if(sortOrder.toString().equalsIgnoreCase("UNSORTED")){
                    // ABORT SORTING !
                }
            }else {
                // ABORT SORTING !
            }
        }
        
        StringBuilder sbQc=new StringBuilder("select count(*) ").append(sbBaseQuery);
        StringBuilder sbQr=new StringBuilder("select _x       ").append(sbBaseQuery).append(sbAQSort);
        
        final TypedQuery<Long>        qc = getEntityManager().createQuery(sbQc.toString(),Long.class);        
        final Query                   qr = getEntityManager().createQuery(sbQr.toString()           );
        
        if(filterAdded){
            final Set<String> keySet = filters.keySet();
            for(String k: keySet){
                
                Object v=filters.get(k);
                if( v.getClass().equals(String.class)){
                    if(v != null){
                        StringBuilder sbqs=new StringBuilder();
                        final String ve = sbqs.append("%").append(v).append("%").toString();
                        qc.setParameter(k, ve);
                        qr.setParameter(k, ve);
                    } else{
                        qc.setParameter(k, null);
                        qr.setParameter(k, null);
                    }
                } else {
                    if(v != null){
                        qc.setParameter(k, v);
                        qr.setParameter(k, v);
                    } else{
                        qc.setParameter(k, null);
                        qr.setParameter(k, null);
                    }
                }
            }
        }
        
        Long totalCount = qc.getSingleResult();
        
        qr.setFirstResult(first);
        qr.setMaxResults (pageSize);
        
        resultList = qr.getResultList();
        
        r = new PaginatedResult<Object[]>(resultList, totalCount.intValue(), pageSize);
                
		return r;
	}    
}
