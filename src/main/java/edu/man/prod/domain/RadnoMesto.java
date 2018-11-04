package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RadnoMesto.
 */
@Entity
@Table(name = "radno_mesto")
public class RadnoMesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "povrsina")
    private Double povrsina;

    @Column(name = "rukovodilac")
    private String rukovodilac;

    @Column(name = "norma_sat")
    private Long normaSat;

    @OneToOne    @JoinColumn(unique = true)
    private Radnici radnici;

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

    public String getNaziv() {
        return naziv;
    }

    public RadnoMesto naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public RadnoMesto lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Double getPovrsina() {
        return povrsina;
    }

    public RadnoMesto povrsina(Double povrsina) {
        this.povrsina = povrsina;
        return this;
    }

    public void setPovrsina(Double povrsina) {
        this.povrsina = povrsina;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public RadnoMesto rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public Long getNormaSat() {
        return normaSat;
    }

    public RadnoMesto normaSat(Long normaSat) {
        this.normaSat = normaSat;
        return this;
    }

    public void setNormaSat(Long normaSat) {
        this.normaSat = normaSat;
    }

    public Radnici getRadnici() {
        return radnici;
    }

    public RadnoMesto radnici(Radnici radnici) {
        this.radnici = radnici;
        return this;
    }

    public void setRadnici(Radnici radnici) {
        this.radnici = radnici;
    }

    public OperacijeURadnomNalogu getOperacijeURadnomNalogu() {
        return operacijeURadnomNalogu;
    }

    public RadnoMesto operacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
        return this;
    }

    public void setOperacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
    }

    public Operacije getOperacije() {
        return operacije;
    }

    public RadnoMesto operacije(Operacije operacije) {
        this.operacije = operacije;
        return this;
    }

    public void setOperacije(Operacije operacije) {
        this.operacije = operacije;
    }

    public Zahvati getZahvati() {
        return zahvati;
    }

    public RadnoMesto zahvati(Zahvati zahvati) {
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
        RadnoMesto radnoMesto = (RadnoMesto) o;
        if (radnoMesto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), radnoMesto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RadnoMesto{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", povrsina=" + getPovrsina() +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", normaSat=" + getNormaSat() +
            "}";
    }
}
