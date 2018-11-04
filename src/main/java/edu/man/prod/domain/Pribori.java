package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import edu.man.prod.domain.enumeration.JedMere;

/**
 * A Pribori.
 */
@Entity
@Table(name = "pribori")
public class Pribori implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "vrsta")
    private String vrsta;

    @Enumerated(EnumType.STRING)
    @Column(name = "jed_mere")
    private JedMere jedMere;

    @Column(name = "namena")
    private String namena;

    @Column(name = "cena")
    private Double cena;

    @Column(name = "trajanje")
    private Instant trajanje;

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

    public Pribori naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public Pribori vrsta(String vrsta) {
        this.vrsta = vrsta;
        return this;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public JedMere getJedMere() {
        return jedMere;
    }

    public Pribori jedMere(JedMere jedMere) {
        this.jedMere = jedMere;
        return this;
    }

    public void setJedMere(JedMere jedMere) {
        this.jedMere = jedMere;
    }

    public String getNamena() {
        return namena;
    }

    public Pribori namena(String namena) {
        this.namena = namena;
        return this;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Double getCena() {
        return cena;
    }

    public Pribori cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Instant getTrajanje() {
        return trajanje;
    }

    public Pribori trajanje(Instant trajanje) {
        this.trajanje = trajanje;
        return this;
    }

    public void setTrajanje(Instant trajanje) {
        this.trajanje = trajanje;
    }

    public Zahvati getZahvati() {
        return zahvati;
    }

    public Pribori zahvati(Zahvati zahvati) {
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
        Pribori pribori = (Pribori) o;
        if (pribori.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pribori.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pribori{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", vrsta='" + getVrsta() + "'" +
            ", jedMere='" + getJedMere() + "'" +
            ", namena='" + getNamena() + "'" +
            ", cena=" + getCena() +
            ", trajanje='" + getTrajanje() + "'" +
            "}";
    }
}
