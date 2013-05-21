package br.com.honorato.view.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.honorato.dao.entity.Sample;
import br.com.honorato.dao.entity.SampleType;
import br.com.honorato.ejb.service.SampleEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.FacesUtil;

@ManagedBean(name = "sampleBean")
@ViewScoped
public class SampleBean implements Serializable {
	
	@EJB
	private SampleEJB SampleEJB;
	private Sample sample;
	private List<SampleType> SampleTypeList; 

	private static final long serialVersionUID = 1L;

	public SampleBean() {
		
		sample = new Sample();
		
	}
	
	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public List<SampleType> getSampleTypeList() {
		return SampleTypeList;
	}

	public void setSampleTypeList(List<SampleType> sampleTypeList) {
		SampleTypeList = sampleTypeList;
	}
	
	public void save() {
		
		try {
			SampleEJB.saveSample(sample);
		} catch (EJBException err) {
			/*TODO recuperar do bundle*/
			FacesUtil.showFatalMessage("Erro Inesperado", err.getMessage(),false);
		}
		
	}
	
}