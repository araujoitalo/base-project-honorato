package br.com.honorato.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.honorato.constraints.UniqueLoginCheck;
import br.com.honorato.dao.enumeration.EUserStatus;

/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="TB_USER", uniqueConstraints={@UniqueConstraint(columnNames={"CD_LOGIN"})})
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER")
	private Integer idUser;

	@Column(name="CD_LOGIN")
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser branco nem vazio")
	@Length(max=100,message="Informe no máximo 100 caracteres")
	@UniqueLoginCheck(value="CD_LOGIN")
	private String login;

	@Column(name="DE_PASSWORD")
	private String password;

	@Column(name="NM_USER")
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser branco nem vazio")
	@Length(max=100,message="Informe no máximo 100 caracteres")
	private String name;
	
	
	@ManyToOne(fetch = FetchType.EAGER)  
	@JoinColumn(name = "ID_DM_CHANGE_PASSWORD")
	/*TODO RECUPERAR DO BUNDLE*/
	@NotNull(message = "Campo no não pode ser branco nem vazio")
	private DYesNo changePassword;	
	
	@Enumerated(EnumType.STRING)
	/*@SampleStatusCheck(properties={"status","name"})*/
	@Column(name ="IN_STATUS", nullable = false)
	public EUserStatus status;
	
	@OneToMany(mappedBy="owner",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Contact> contactList;	
	
	public User() {
    }
    
	public User(Integer iduser, String login, String password) {
		super();
		this.idUser = iduser;
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	 public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EUserStatus getStatus() {
		return status;
	}

	public void setStatus(EUserStatus status) {
		this.status = status;
	}

	public List<Contact> getContactList() {
		if (contactList==null)
			contactList = new ArrayList<Contact>();
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
	public DYesNo getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(DYesNo changePassword) {
		this.changePassword = changePassword;
	}	

	@Override
	 public boolean equals(Object obj) {

		 if (this == obj){
			 return true;
		 }
		 if (obj == null){
			 return false;
		 }
		 if (getClass() != obj.getClass()){
			 return false;
		 }

		 if (!login.equals(((User)obj).getLogin())) {
			 return false;
		 } else {
			 return true;
		 }

	 }
    
	 @Override
	 public String toString() {

		 return "Identificador: " + idUser + " Login: " + login;

	 }
	
}