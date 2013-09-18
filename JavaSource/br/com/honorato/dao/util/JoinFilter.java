package br.com.honorato.dao.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;

public class JoinFilter extends FilterQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private JoinType joinType;
	private String joinName;
	private List<FilterQuery> filterList;
	
	public JoinFilter(){
		
	}

	public JoinFilter(JoinType joinType, String joinName){
		this.joinType = joinType;
		this.joinName = joinName;
	}
	
	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	public String getJoinName() {
		return joinName;
	}

	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}

	public List<FilterQuery> getFilterList() {
		
		if(filterList==null){
			filterList = new ArrayList<FilterQuery>();
		}
		
		return filterList;
	}

	public void setFilterList(List<FilterQuery> filterList) {
		this.filterList = filterList;
	}
	
}