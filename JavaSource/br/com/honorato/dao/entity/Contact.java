package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="TB_CONTACT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CONTACT")
	private Integer idContact;

	@Column(name="DE_CONTENT")
	/*TODO RECUPERAR DO BUNDLE*/
	@NotBlank(message = "Campo no não pode ser branco nem vazio")
	@Length(max=150,message="Informe no máximo 150 caracteres")
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)  
	@JoinColumn(name = "ID_TYPE_CONTACT", updatable = false, insertable = false)
	@NotNull(message = "Campo no não pode ser branco nem vazio")
	private TypeContact type;

    @ManyToOne
	@JoinColumn(name="ID_USER")
    @NotNull(message = "Campo não pode ser branco nem vazio")
	private User owner;		
	
	public Contact() {
    }

	public Integer getIdContact() {
		return idContact;
	}

	public void setIdContact(Integer idContact) {
		this.idContact = idContact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TypeContact getType() {
		return type;
	}

	public void setType(TypeContact type) {
		this.type = type;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((idContact == null) ? 0 : idContact.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Contact other = (Contact) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (idContact == null) {
			if (other.idContact != null)
				return false;
		} else if (!idContact.equals(other.idContact))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
    
	
}