package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="person",schema = "public")
public class User {
	public User(){}
	
    @ManyToMany(cascade = { CascadeType.REMOVE },fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<UserRole> roles = new HashSet<UserRole>();

    


	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    private String firstname;
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLasttname() {
		return lasttname;
	}

	public void setLasttname(String lasttname) {
		this.lasttname = lasttname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	private String password;

	private String lasttname;

    @Column(unique=true)
    private String email;

    private String telephone;
    @Column(name = "active", columnDefinition = "boolean default false", nullable = false)

    private Boolean active;

    @Column(unique=true)
	private String login;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id")
    private PersonData person_data;


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public PersonData getPerson_data() {
        return person_data;
    }

    public void setPerson_data(PersonData person_data) {
        this.person_data = person_data;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
