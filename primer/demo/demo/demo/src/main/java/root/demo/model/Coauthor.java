package root.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "coauthor")
public class Coauthor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;
	
	@Column(name = "firstname", nullable = true)
	private String firstname;
	
	@Column(name = "lastname", nullable = true)
	private String lastname;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "city", nullable = true)
	private String city;
	
	@Column(name = "state", nullable = true)
	private String state;
	
	@ManyToMany(mappedBy = "koautori")
	@Fetch(value = FetchMode.SUBSELECT)
    private Collection<Rad> articles;
	
	public Coauthor() {
		
	}

	public Coauthor(Long id, String firstname, String lastname, String email, String city, String state,
                    Collection<Rad> articles) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.city = city;
		this.state = state;
		this.articles = articles;
	}

	
	public Coauthor(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Collection<Rad> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Rad> articles) {
		this.articles = articles;
	}

	
	
}
