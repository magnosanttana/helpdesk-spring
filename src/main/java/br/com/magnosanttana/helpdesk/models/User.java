package br.com.magnosanttana.helpdesk.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(unique = true)
	@Email(message = "Por favor, informe um email valido")
	@NotEmpty(message = "Não pode ser vazio")
	private String email;
	
	@Column
	@NotEmpty(message = "Não pode ser vazio")
	private String name;
	
	@Column
	@NotEmpty(message = "Não pode ser vazio")
	@Length(min = 5, message = "A senha deve conter pelo menos 5 caracteres")
	private String password;
	
	@Column
	private Boolean active = true;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userOpen")
	private Set<Ticket> tickets;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "technician")
	private Set<Ticket> ticketsTechnician;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userInteraction")
	private Set<Interaction> interactions;
	
	public User() {}
	
	public User(String email, String name, String password, Boolean active) {
		this.email		= email;
		this.name		= name;
		this.password	= password;
		this.active		= active;
	}
	
	public User(Long id, String email, String name, String password, Boolean active) {
		this.id			= id;
		this.email		= email;
		this.name		= name;
		this.password	= password;
		this.active		= active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
