package br.com.honorato.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.honorato.dao.enumeration.EModuleType;
//import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="TB_MODULE", uniqueConstraints={@UniqueConstraint(columnNames={"ID_MODULE"}),
		@UniqueConstraint(columnNames={"CD_MODULE"})})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "IN_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MODULE", nullable = false)
	private Integer idModule;

	@Column(name="CD_MODULE", nullable = false, unique=true)
	@NotBlank(message = "{resource.code.noBlank}")
	@Length(max=30, message= "{resource.code.MaxLength}")
	private String code;

	@Column(name="NM_MODULE", nullable = false)
	@NotBlank(message = "{resource.name.noBlank}")
	@Length(max=150, message= "{resource.name.MaxLength}")
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="tb_module_function", joinColumns={@JoinColumn(name="id_module_reference")}, inverseJoinColumns={@JoinColumn(name="id_function")})
	private List<Resource> dependentResources;	

	@ManyToMany(mappedBy="dependentResources", fetch = FetchType.EAGER)
	private List<Resource> referencesResources;	

	@Transient
	private EModuleType type;

	public Resource() {
	}

	public Resource(Integer idModule, String name) {
		super();
		this.idModule = idModule;
		this.name = name;
	}

	public Integer getIdModule() {
		return idModule;
	}

	public void setIdModule(Integer idModule) {
		this.idModule = idModule;
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

	public EModuleType getType() {
		return type;
	}

	protected void setType(EModuleType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime
				* result
				+ ((dependentResources == null) ? 0 : dependentResources
						.hashCode());
		result = prime * result
				+ ((idModule == null) ? 0 : idModule.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((referencesResources == null) ? 0 : referencesResources
						.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Resource other = (Resource) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dependentResources == null) {
			if (other.dependentResources != null)
				return false;
		} else if (!dependentResources.equals(other.dependentResources))
			return false;
		if (idModule == null) {
			if (other.idModule != null)
				return false;
		} else if (!idModule.equals(other.idModule))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (referencesResources == null) {
			if (other.referencesResources != null)
				return false;
		} else if (!referencesResources.equals(other.referencesResources))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public List<Resource> getDependentResources() {
		
		if(dependentResources==null){
			dependentResources = new  ArrayList<Resource>();
		}		
		
		return dependentResources;
	}

	public void setDependentResources(List<Resource> dependentResources) {
		this.dependentResources = dependentResources;
	}

	public List<Resource> getReferencesResources() {
		
		if(referencesResources==null){
			referencesResources = new  ArrayList<Resource>();
		}
		
		return referencesResources;
	}

	public void setReferencesResources(List<Resource> referencesResources) {
		this.referencesResources = referencesResources;
	}

}