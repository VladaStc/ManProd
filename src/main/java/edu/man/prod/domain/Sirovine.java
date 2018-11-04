package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import edu.man.prod.domain.enumeration.JedMere;

/**
 * A Sirovine.
 */
@Entity
@Table(name = "sirovine")
public class Sirovine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra")
    private String sifra;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "vrsta")
    private String vrsta;

    @Enumerated(EnumType.STRING)
    @Column(name = "jed_mere")
    private JedMere jedMere;

    @Column(name = "namena")
    private String namena;

    @Column(name = "nabavna_cena")
    private Double nabavnaCena;

    @Column(name = "napomena")
    private String napomena;

    @OneToOne    @JoinColumn(unique = true)
    private OperacijeURadnomNalogu operacijeURadnomNalogu;

    @OneToOne    @JoinColumn(unique = true)
    private Operacije operacije;

    @OneToOne    @JoinColumn(unique = true)
    private Zahvati zahvati;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifra() {
        return sifra;
    }

    public Sirovine sifra(String sifra) {
        this.sifra = sifra;
        return this;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public Sirovine naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public Sirovine vrsta(String vrsta) {
        this.vrsta = vrsta;
        return this;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public JedMere getJedMere() {
        return jedMere;
    }

    public Sirovine jedMere(JedMere jedMere) {
        this.jedMere = jedMere;
        return this;
    }

    public void setJedMere(JedMere jedMere) {
        this.jedMere = jedMere;
    }

    public String getNamena() {
        return namena;
    }

    public Sirovine namena(String namena) {
        this.namena = namena;
        return this;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Double getNabavnaCena() {
        return nabavnaCena;
    }

    public Sirovine nabavnaCena(Double nabavnaCena) {
        this.nabavnaCena = nabavnaCena;
        return this;
    }

    public void setNabavnaCena(Double nabavnaCena) {
        this.nabavnaCena = nabavnaCena;
    }

    public String getNapomena() {
        return napomena;
    }

    public Sirovine napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public OperacijeURadnomNalogu getOperacijeURadnomNalogu() {
        return operacijeURadnomNalogu;
    }

    public Sirovine operacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
        return this;
    }

    public void setOperacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
    }

    public Operacije getOperacije() {
        return operacije;
    }

    public Sirovine operacije(Operacije operacije) {
        this.operacije = operacije;
        return this;
    }

    public void setOperacije(Operacije operacije) {
        this.operacije = operacije;
    }

    public Zahvati getZahvati() {
        return zahvati;
    }

    public Sirovine zahvati(Zahvati zahvati) {
        this.zahvati = zahvati;
        return this;
    }

    public void setZahvati(Zahvati zahvati) {
        this.zahvati = zahvati;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sirovine sirovine = (Sirovine) o;
        if (sirovine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sirovine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sirovine{" +
            "id=" + getId() +
            ", sifra='" + getSifra() + "'" +
            ", naziv='" + getNaziv() + "'" +
            ", vrsta='" + getVrsta() + "'" +
            ", jedMere='" + getJedMere() + "'" +
            ", namena='" + getNamena() + "'" +
            ", nabavnaCena=" + getNabavnaCena() +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
