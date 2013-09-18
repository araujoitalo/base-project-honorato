package br.com.honorato.dao.implement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.honorato.dao.util.EqualFilter;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.dao.util.IsNullFilter;
import br.com.honorato.dao.util.JoinFilter;
import br.com.honorato.dao.util.LikeFilter;
import br.com.honorato.exception.DAOException;

public class JpaDAO<T> implements GenericDAO<T>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Class<T> persistentClass;
	private EntityManager manager;
	private ArrayList<Predicate> predicates;
	private CriteriaQuery<T> criteriaQuery;
	private Root<T> fromRoot;
	private Query query;

	@SuppressWarnings("unchecked")
	public JpaDAO(EntityManager manager) {

		Type superClasse = getClass().getGenericSuperclass();
		Type tipo = ((ParameterizedType) superClasse).getActualTypeArguments()[0];
		this.persistentClass = (Class<T>) tipo;
		this.manager = manager;

	}

	@Override
	public EntityManager getEntityManager()	{

		return this.manager;

	}


	@Override
	public T selectByKey(Object id) {
		return (T) this.getEntityManager().find(this.persistentClass, id);
	}

	@Override
	public List<T> selectAll() {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(this.persistentClass);
		c.select(c.from(this.persistentClass));
		List<T> result = this.getEntityManager().createQuery(c).getResultList();
		return result;

	}

	@Override
	public T save(T object) throws DAOException {

		try {

			object = this.getEntityManager().merge(object);
			return object;

		}catch(PersistenceException ex){
			throw new DAOException("",ex.getMessage());
		}

	}

	@Override
	public void delete(T object) throws DAOException {
		
		try {

			this.getEntityManager().remove(object);

		}catch(Exception  ex){
			throw new DAOException("",ex.getMessage());
		}

	}

	public CriteriaBuilder getCriteriaBuilder() {
		return this.getEntityManager().getCriteriaBuilder();
	}

	public ArrayList<Predicate> getPredicates() {
		
		if (predicates==null)
			predicates = new ArrayList<Predicate>();
		return predicates;
	}

	public void setPredicates(ArrayList<Predicate> predicates) {
		this.predicates = predicates;
	}

	public CriteriaQuery<T> getCriteriaQuery() {
		return criteriaQuery;
	}

	public void setCriteriaQuery(CriteriaQuery<T> query) {
		this.criteriaQuery = query;
	}

	public Root<T> getFromRoot() {
		return fromRoot;
	}

	public void setFromRoot(Root<T> fromRoot) {
		this.fromRoot = fromRoot;
	}
	
	public TypedQuery<T> getTypeQuery(){
		 return this.getEntityManager().createQuery(getCriteriaQuery());
	}
	
	public void setWhereInQueryWhithPredicatea(){
		
		if (getPredicates().size() > 0){
			Predicate[] params = new Predicate[getPredicates().size()];
			getCriteriaQuery().where(getPredicates().toArray(params));
		}		

	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public final void setParameters(Query query, Object[] args) {

		if ((args != null) && (query != null)) {	
			for (int i = 0; i < args.length; i++) {	
				Object arg = args[i];
				query.setParameter(i+1, arg);
			}
		}
	}
	
	protected List<T> recoveryByFilter (Class<T> classe, ArrayList<FilterQuery> filters) throws DAOException {

		setCriteriaQuery(getCriteriaBuilder().createQuery(classe));
		setFromRoot((getCriteriaQuery().from(classe)));
		getCriteriaQuery().select(getFromRoot());
		
		putPredicateRoot(filters,getFromRoot());
		
		//for (FilterQuery filter : filters) {
			
			
			
//			if (filter instanceof LikeFilter) {
//				LikeFilter like = (LikeFilter) filter;
//				Predicate codePredicate = getCriteriaBuilder().like(getFromRoot().<String>get(like.getName()), like.getFullExpression());
//				getPredicates().add(codePredicate);
//			}else if(filter instanceof EqualFilter){
//				EqualFilter equal = (EqualFilter) filter;
//				Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get(equal.getName()), equal.getValue());
//				getPredicates().add(codePredicate);
//			}
		//}		
		
		setWhereInQueryWhithPredicatea();
		
		return getTypeQuery().getResultList();			

	}
	
	private void putPredicateRoot(List<FilterQuery> filters, From from){
		
		for (FilterQuery filter : filters) {
			
			if (filter instanceof LikeFilter) {
				LikeFilter like = (LikeFilter) filter;
				Predicate codePredicate = getCriteriaBuilder().like(getFromRoot().<String>get(like.getName()), like.getFullExpression());
				getPredicates().add(codePredicate);
			}else if(filter instanceof EqualFilter){
				EqualFilter equal = (EqualFilter) filter;
				Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get(equal.getName()), equal.getValue());
				getPredicates().add(codePredicate);
			}else if(filter instanceof IsNullFilter){
				IsNullFilter isNullFilter = (IsNullFilter) filter;
				Predicate codePredicate = getCriteriaBuilder().isNull(getFromRoot().get(isNullFilter.getName()));
				getPredicates().add(codePredicate);
			}else if(filter instanceof JoinFilter){
				
				JoinFilter joinFilter = (JoinFilter) filter;
				Join join = getFromRoot().join(joinFilter.getJoinName(), joinFilter.getJoinType());
				Predicate codePredicate = getCriteriaBuilder().isNull(from.get("idModule"));
				putPredicateRoot(joinFilter.getFilterList(),join);
				
				//Predicate codePredicate = getCriteriaBuilder().isNull(join.get("idModule"));
				
//				JoinFilter joinFilter = (JoinFilter) filter;
//				joinFilter.makeJoin();
//				putPredicateJoin(joinFilter.getFilterList(),joinFilter.getJoin());
				
			}
		}			

//		if (filter instanceof LikeFilter) {
//			LikeFilter like = (LikeFilter) filter;
//			Predicate codePredicate = getCriteriaBuilder().like(getFromRoot().<String>get(like.getName()), like.getFullExpression());
//			getPredicates().add(codePredicate);
//		}else if(filter instanceof EqualFilter){
//			EqualFilter equal = (EqualFilter) filter;
//			Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get(equal.getName()), equal.getValue());
//			getPredicates().add(codePredicate);
//		}else if(filter instanceof JoinFilter){
//			
//			JoinFilter joinFilter = (JoinFilter) filter;
//			joinFilter.makeJoin();
//			putPredicate(joinFilter.getFilterList());
//			
//		}

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void putPredicateJoin(List<FilterQuery> filters,  Join join){
		
		for (FilterQuery filter : filters) {
			
			if (filter instanceof LikeFilter) {
				LikeFilter like = (LikeFilter) filter;
				Predicate codePredicate = getCriteriaBuilder().like(join.get(like.getName()), like.getFullExpression());
				getPredicates().add(codePredicate);
			}else if(filter instanceof EqualFilter){
				EqualFilter equal = (EqualFilter) filter;
				Predicate codePredicate = getCriteriaBuilder().equal(join.get(equal.getName()), equal.getValue());
				getPredicates().add(codePredicate);
			}else if(filter instanceof IsNullFilter){
				IsNullFilter isNullFilter = (IsNullFilter) filter;
				Predicate codePredicate = getCriteriaBuilder().isNull(join.get(isNullFilter.getName()));
				getPredicates().add(codePredicate);
			}
		}		
	}
}