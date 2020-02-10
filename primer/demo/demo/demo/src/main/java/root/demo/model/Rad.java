package root.demo.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "rad")
public class Rad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "naslov", nullable = true)
    private String naslov;


    @Column(name = "kljucniPojmovi", nullable = true)
    private String kljucniPojmovi;

    @Column(name = "apstract", nullable = true)
    private String apstract;

    @Column(name = "filename", nullable = true)
    private String filename;


    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "koautor_rada", joinColumns = @JoinColumn(name = "rad_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Collection<Coauthor> koautori = new ArrayList<Coauthor>();

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Casopis casopis;


    @Column(name="cena")
    private Double cena;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NaucnaOblast> naucna_oblast;

    public Rad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getKljucniPojmovi() {
        return kljucniPojmovi;
    }

    public void setKljucniPojmovi(String kljucniPojmovi) {
        this.kljucniPojmovi = kljucniPojmovi;
    }

    public String getApstract() {
        return apstract;
    }

    public void setApstract(String apstract) {
        this.apstract = apstract;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Collection<Coauthor> getKoautori() {
        return koautori;
    }

    public void setKoautori(Collection<Coauthor> koautori) {
        this.koautori = koautori;
    }

    public Casopis getCasopis() {
        return casopis;
    }

    public void setCasopis(Casopis casopis) {
        this.casopis = casopis;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public List<NaucnaOblast> getNaucna_oblast() {
        return naucna_oblast;
    }

    public void setNaucna_oblast(List<NaucnaOblast> naucna_oblast) {
        this.naucna_oblast = naucna_oblast;
    }
}
