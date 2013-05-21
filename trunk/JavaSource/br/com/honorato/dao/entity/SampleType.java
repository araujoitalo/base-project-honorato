package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="TB_SAMPLE_TYPE", uniqueConstraints={@UniqueConstraint(columnNames={"ID_SAMPLE_TYPE"}),@UniqueConstraint(columnNames={"NM_SAMPLE_TYPE"})})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class SampleType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SAMPLE_TYPE", nullable = false)
	private Integer idSampleType;
	
	@Column(name="NM_SAMPLE_TYPE", nullable = false, unique=true)
	private String name;

	public SampleType() {
    }
    
	public SampleType(Integer idSampleType, String name) {
		super();
		this.idSampleType = idSampleType;
		this.name = name;
	}

	public Integer getIdSampleType() {
		return idSampleType;
	}

	public void setIdSampleType(Integer idSampleType) {
		this.idSampleType = idSampleType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}