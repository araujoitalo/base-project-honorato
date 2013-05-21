package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_DOMAIN", uniqueConstraints={@UniqueConstraint(columnNames={"NM_DOMAIN","IN_DOMAIN"})})
@DiscriminatorColumn(name="IN_DOMAIN",discriminatorType=DiscriminatorType.STRING)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class DomainBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_DOMAIN")
	private Integer idDomain;

	@Column(name="CD_DOMAIN", nullable = false, length=20)
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser vazio")
	@Length(max=20, message= "Campo não pode exceder 20 caracteres")
	private String code;

	@Column(name="NM_DOMAIN", nullable = false, length=150)
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser vazio")
	@Length(max=150, message= "Campo não pode exceder 150 caracteres")
	private String name;

	@Column(name="DE_DOMAIN", nullable = false, length=250)
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser vazio")	
	@Length(max=250, message= "Campo não pode exceder 250 caracteres")
	private String description;

	@Column(name="NR_PRIORITY")
	private Integer priority;

	public Integer getIdDomain() {
		return idDomain;
	}

	public void setIdDomain(Integer idDomain) {
		this.idDomain = idDomain;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((idDomain == null) ? 0 : idDomain.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DomainBase other = (DomainBase) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (idDomain == null) {
			if (other.idDomain != null)
				return false;
		} else if (!idDomain.equals(other.idDomain))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}