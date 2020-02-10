package root.demo.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class CasopisDTO {


    private Long id;
    private String naslovRada;
    private String apstrakt;
    private String kljucniPojmovi;
    private ArrayList<AutorDTO> autori = new ArrayList<AutorDTO>();
    private List<String> naucnaOblast;
    private String nazivCasopisa;
    private String rad;
    private String filename;
    private String komentarDorada;

    public CasopisDTO() {
    }

    public CasopisDTO(String naslovRada, String apstrakt, String kljucniPojmovi) {
        this.naslovRada = naslovRada;
        this.apstrakt = apstrakt;
        this.kljucniPojmovi = kljucniPojmovi;
    }

    public CasopisDTO(String rad, String filename) {
        this.rad = rad;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslovRada() {
        return naslovRada;
    }

    public void setNaslovRada(String naslovRada) {
        this.naslovRada = naslovRada;
    }

    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
    }

    public String getKljucniPojmovi() {
        return kljucniPojmovi;
    }

    public void setKljucniPojmovi(String kljucniPojmovi) {
        this.kljucniPojmovi = kljucniPojmovi;
    }

    public ArrayList<AutorDTO> getAutori() {
        return autori;
    }

    public void setAutori(ArrayList<AutorDTO> autori) {
        this.autori = autori;
    }

    public List<String> getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(List<String> naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public String getNazivCasopisa() {
        return nazivCasopisa;
    }

    public void setNazivCasopisa(String nazivCasopisa) {
        this.nazivCasopisa = nazivCasopisa;
    }

    public String getRad() {
        return rad;
    }

    public void setRad(String rad) {
        this.rad = rad;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKomentarDorada() {
        return komentarDorada;
    }

    public void setKomentarDorada(String komentarDorada) {
        this.komentarDorada = komentarDorada;
    }
}
