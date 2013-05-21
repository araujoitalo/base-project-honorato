package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

import br.com.honorato.dao.enumeration.ESampleStatus;

@Entity
@Table(name="TB_SAMPLE", uniqueConstraints={@UniqueConstraint(columnNames={"ID_SAMPLE"}),@UniqueConstraint(columnNames={"NM_SAMPLE"})})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Sample implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SAMPLE", nullable = false)
	private Integer idSample;
	
	@Column(name="NM_SAMPLE", nullable = false, unique=true)
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser vazio")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="IN_STATUS", nullable = false)
	public ESampleStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)  
	@JoinColumn(name = "ID_SAMPLE", referencedColumnName = "ID_SAMPLE_TYPE", updatable = false, insertable = false)
	private SampleType type;

	public Sample() {
    }
    
	public Sample(Integer idSample, String name, SampleType type) {
		super();
		this.idSample = idSample;
		this.name = name;
		this.type = type;
	}

	public Integer getIdSample() {
		return idSample;
	}

	public void setIdSample(Integer idSample) {
		this.idSample = idSample;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ESampleStatus getStatus() {
		return status;
	}

	public void setStatus(ESampleStatus status) {
		this.status = status;
	}

	public SampleType getType() {
		return type;
	}

	public void setType(SampleType type) {
		this.type = type;
	}

}