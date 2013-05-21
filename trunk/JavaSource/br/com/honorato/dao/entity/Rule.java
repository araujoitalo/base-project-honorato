package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="TB_RULE", uniqueConstraints={@UniqueConstraint(columnNames={"CD_RULE"})})
public class Rule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CD_RULE", unique=true, length=50)
	private String codeRule;

	@Column(name="DE_RULE")
	private String description;

	public Rule() {
    }
    
	public Rule(String codeRule, String descriptionRule) {
		super();
		this.codeRule = codeRule;
		this.description = descriptionRule;
	}

	public String getCodeRule() {
		return codeRule;
	}

	public void setCodeRule(String codeRule) {
		this.codeRule = codeRule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Rule [codeRule=" + codeRule + ", description=" + description
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeRule == null) ? 0 : codeRule.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (codeRule == null) {
			if (other.codeRule != null)
				return false;
		} else if (!codeRule.equals(other.codeRule))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}