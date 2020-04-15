package br.com.magnosanttana.helpdesk.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	@NotBlank(message = "Name is mandatory")
	@Length(min = 2, message = "O nome deve conter pelo menos 2 caracter")
	private String name;
	
	public Role() {}
	
	public Role(String name) {
		this.name = name;
	}
	
	public Role(Long id, String name) {
		this.id 	= id;
		this.name	= name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
