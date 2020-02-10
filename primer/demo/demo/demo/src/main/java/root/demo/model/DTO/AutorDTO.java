package root.demo.model.DTO;

import java.io.Serializable;

public class AutorDTO implements Serializable {

    String firstname;
    String lastname;
    String email;
    String city;
    String state;

    public AutorDTO(String firstname, String lastname, String email, String city, String state) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.city = city;
        this.state = state;
    }

    public AutorDTO() {
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
}
