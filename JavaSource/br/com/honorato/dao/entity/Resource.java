package br.com.honorato.dao.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="TB_MODULE", uniqueConstraints={@UniqueConstraint(columnNames={"ID_MODULE"})})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "IN_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MODULE", nullable = false)
	private Integer idModule;
	
	@Column(name="CD_MODULE", nullable = false, unique=true)
	private String code;

	@Column(name="NM_MODULE", nullable = false)
	private String name;

	@Column(name="IN_TYPE", nullable = false)
	private String type;
	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)  
	@JoinColumn(name = "ID_MODULE_REFERENCE", referencedColumnName = "ID_MODULE", updatable = false, insertable = false)
	private Resource moduleReference;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "moduleReference")
	@OrderBy("name")
	private List<Resource> resources;	
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Resource getModuleReference() {
		return moduleReference;
	}

	public void setModuleReference(Resource moduleReference) {
		this.moduleReference = moduleReference;
	}
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}