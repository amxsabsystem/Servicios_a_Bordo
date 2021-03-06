package com.aeromexico.pab.backend.remote.ejb;

import com.aeromexico.pab.backend.remote.util.PaginatedResult;
import com.aeromexico.pab.entity.AuditableEntity;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.ejb.TransactionAttribute;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.TypedQuery;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

/**
 * SSB JPA Entity AbstractFacade.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/jpa-builder
 */

public abstract class AbstractFacade<T> {
    @Resource SessionContext ctx;

	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	@TransactionAttribute(REQUIRED)
	public abstract T create(T entity);

	@TransactionAttribute(REQUIRED)
	public abstract T update(T entity);

	@TransactionAttribute(REQUIRED)
	public void remove(T entity) {
        List<T> removeList = findAllLike(entity);
        for(T entityForRemove: removeList){
            getEntityManager().remove(entityForRemove);
        }
		getEntityManager().flush();
	}
    
	@TransactionAttribute(REQUIRED)
	public void removeByPK(Object pk) {
        T entity = getEntityManager().find(entityClass, pk);
        getEntityManager().remove(entity);
		getEntityManager().flush();
	}

	@TransactionAttribute(SUPPORTS)
	public T findByPK(Object pk) {
		return getEntityManager().find(entityClass, pk);
	}

    @TransactionAttribute(REQUIRED)
	public abstract T findByPK_EAGER(Object pk);

	@TransactionAttribute(SUPPORTS)
	public abstract List<T> findAllLike(T entity);

	@TransactionAttribute(SUPPORTS)
	public abstract List<T> findAll();

	@TransactionAttribute(SUPPORTS)
	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}
	
	@TransactionAttribute(SUPPORTS)
	public Long count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return (Long) q.getSingleResult();
	}

	@TransactionAttribute(SUPPORTS)
	public abstract Long countAll();

    @TransactionAttribute(SUPPORTS)
    public PaginatedResult<T> loadPaginated(int first,int pageSize,String sortField,Object sortOrder, Map<String,Object> filters) {
        return loadPaginated(first,pageSize,null,sortField,sortOrder,filters);
    }
    
    @TransactionAttribute(SUPPORTS)
    public PaginatedResult<T> loadPaginated(int first,int pageSize,String jqlBase,String sortField,Object sortOrder, Map<String,Object> filters) {
        PaginatedResult<T> r=null;
        List<T> resultList=null;
        
        StringBuilder sbBaseQuery=new StringBuilder();
        if(jqlBase == null){
            sbBaseQuery.append("from ");
            sbBaseQuery.append(entityClass.getName());
            sbBaseQuery.append(" _x ");
        } else {
            sbBaseQuery.append("from ");
            sbBaseQuery.append(jqlBase);
            sbBaseQuery.append(" _x ");
        }
        
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
        final TypedQuery              qr = getEntityManager().createQuery(sbQr.toString(),entityClass);
        
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
        
        r = new PaginatedResult<T>(resultList, totalCount.intValue(), pageSize);
                
		return r;
	}
}
