package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import edu.man.prod.domain.enumeration.TipRadnika;

/**
 * A Radnici.
 */
@Entity
@Table(name = "radnici")
public class Radnici implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "jmbg")
    private Long jmbg;

    @Column(name = "kvalifikacija")
    private String kvalifikacija;

    @Column(name = "koeficijent")
    private Double koeficijent;

    @Column(name = "sertifikat")
    private String sertifikat;

    @Column(name = "pol")
    private String pol;

    @Column(name = "napomena")
    private String napomena;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip_radnika")
    private TipRadnika tipRadnika;

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

    public String getIme() {
        return ime;
    }

    public Radnici ime(String ime) {
        this.ime = ime;
        return this;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Radnici prezime(String prezime) {
        this.prezime = prezime;
        return this;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Long getJmbg() {
        return jmbg;
    }

    public Radnici jmbg(Long jmbg) {
        this.jmbg = jmbg;
        return this;
    }

    public void setJmbg(Long jmbg) {
        this.jmbg = jmbg;
    }

    public String getKvalifikacija() {
        return kvalifikacija;
    }

    public Radnici kvalifikacija(String kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
        return this;
    }

    public void setKvalifikacija(String kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }

    public Double getKoeficijent() {
        return koeficijent;
    }

    public Radnici koeficijent(Double koeficijent) {
        this.koeficijent = koeficijent;
        return this;
    }

    public void setKoeficijent(Double koeficijent) {
        this.koeficijent = koeficijent;
    }

    public String getSertifikat() {
        return sertifikat;
    }

    public Radnici sertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
        return this;
    }

    public void setSertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
    }

    public String getPol() {
        return pol;
    }

    public Radnici pol(String pol) {
        this.pol = pol;
        return this;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getNapomena() {
        return napomena;
    }

    public Radnici napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public TipRadnika getTipRadnika() {
        return tipRadnika;
    }

    public Radnici tipRadnika(TipRadnika tipRadnika) {
        this.tipRadnika = tipRadnika;
        return this;
    }

    public void setTipRadnika(TipRadnika tipRadnika) {
        this.tipRadnika = tipRadnika;
    }

    public OperacijeURadnomNalogu getOperacijeURadnomNalogu() {
        return operacijeURadnomNalogu;
    }

    public Radnici operacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
        return this;
    }

    public void setOperacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
    }

    public Operacije getOperacije() {
        return operacije;
    }

    public Radnici operacije(Operacije operacije) {
        this.operacije = operacije;
        return this;
    }

    public void setOperacije(Operacije operacije) {
        this.operacije = operacije;
    }

    public Zahvati getZahvati() {
        return zahvati;
    }

    public Radnici zahvati(Zahvati zahvati) {
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
        Radnici radnici = (Radnici) o;
        if (radnici.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), radnici.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Radnici{" +
            "id=" + getId() +
            ", ime='" + getIme() + "'" +
            ", prezime='" + getPrezime() + "'" +
            ", jmbg=" + getJmbg() +
            ", kvalifikacija='" + getKvalifikacija() + "'" +
            ", koeficijent=" + getKoeficijent() +
            ", sertifikat='" + getSertifikat() + "'" +
            ", pol='" + getPol() + "'" +
            ", napomena='" + getNapomena() + "'" +
            ", tipRadnika='" + getTipRadnika() + "'" +
            "}";
    }
}
