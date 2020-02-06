package root.demo.model;


import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

@Table
@Entity(name = "naucnaoblast")
public class NaucnaOblast implements Serializable {

    private  static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column
    private String name;

    public NaucnaOblast(String name) {
        this.name = name;
    }

    public NaucnaOblast() {
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
