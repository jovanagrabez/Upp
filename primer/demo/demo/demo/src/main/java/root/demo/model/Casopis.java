package root.demo.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "casopis")
public class Casopis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long casopisId;

    @Column
    private String naziv;

    @Column
    private String issn;

    @ManyToOne
    private User mainEditor;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NaucnaOblast> naucna_oblast;

    @OneToMany
    private List<User> scientificAreasEditors;

    @ManyToMany
    @JoinTable(name = "magazine_reviewer",
            joinColumns = @JoinColumn(name = "magazine_id"),
            inverseJoinColumns = @JoinColumn(name = "reviewer_id"))
    private List<User> reviewers;

    @Column
    private double cijena;
    @Column
    private boolean activeStatus;
    @Column
    private String processId;

    @Column
    private Boolean potrebnaDopuna;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casopis")
    private List<Rad> radovi;

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
    }

    public Casopis() {
    }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getMainEditor() {
        return mainEditor;
    }

    public void setMainEditor(User mainEditor) {
        this.mainEditor = mainEditor;
    }

    public List<NaucnaOblast> getNaucna_oblast() {
        return naucna_oblast;
    }

    public void setNaucna_oblast(List<NaucnaOblast> naucna_oblast) {
        this.naucna_oblast = naucna_oblast;
    }

    public List<User> getScientificAreasEditors() {
        return scientificAreasEditors;
    }

    public void setScientificAreasEditors(List<User> scientificAreasEditors) {
        this.scientificAreasEditors = scientificAreasEditors;
    }

    public List<User> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<User> reviewers) {
        this.reviewers = reviewers;
    }

    public Casopis(String naziv, String issn, Double cijena) {
        this.naziv = naziv;
        this.issn = issn;
        this.cijena= cijena;
    }

    public Casopis(String naziv, String issn, User mainEditor, List<NaucnaOblast> naucna_oblast, List<User> scientificAreasEditors, List<User> reviewers, double cijena, boolean activeStatus, String processId, Boolean potrebnaDopuna) {
        this.naziv = naziv;
        this.issn = issn;
        this.mainEditor = mainEditor;
        this.naucna_oblast = naucna_oblast;
        this.scientificAreasEditors = scientificAreasEditors;
        this.reviewers = reviewers;
        this.cijena = cijena;
        this.activeStatus = activeStatus;
        this.processId = processId;
        this.potrebnaDopuna = potrebnaDopuna;
    }

    public Long getCasopisId() {
        return casopisId;
    }

    public void setCasopisId(Long casopisId) {
        this.casopisId = casopisId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }



    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Boolean getPotrebnaDopuna() {
        return potrebnaDopuna;
    }

    public void setPotrebnaDopuna(Boolean potrebnaDopuna) {
        this.potrebnaDopuna = potrebnaDopuna;
    }
}
