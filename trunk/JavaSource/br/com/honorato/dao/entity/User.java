package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="TB_USER", uniqueConstraints={@UniqueConstraint(columnNames={"cd_login"})})
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private Integer identificador;
	
	@Column(name="cd_login")
	private String login;

	@Column(name="de_password")
	private String senha;

	public User() {
    }
    
	public User(Integer identificador, String login, String senha) {
		super();
		this.identificador = identificador;
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	 public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
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

		 return "Identificador: " + identificador + " Login: " + login;

	 }	
}