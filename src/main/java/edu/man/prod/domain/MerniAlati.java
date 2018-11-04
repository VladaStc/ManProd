package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import edu.man.prod.domain.enumeration.JedMere;

/**
 * A MerniAlati.
 */
@Entity
@Table(name = "merni_alati")
public class MerniAlati implements Serializable {

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

    @Column(name = "bazdarenje")
    private Instant bazdarenje;

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

    public MerniAlati naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public MerniAlati vrsta(String vrsta) {
        this.vrsta = vrsta;
        return this;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public JedMere getJedMere() {
        return jedMere;
    }

    public MerniAlati jedMere(JedMere jedMere) {
        this.jedMere = jedMere;
        return this;
    }

    public void setJedMere(JedMere jedMere) {
        this.jedMere = jedMere;
    }

    public String getNamena() {
        return namena;
    }

    public MerniAlati namena(String namena) {
        this.namena = namena;
        return this;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Double getCena() {
        return cena;
    }

    public MerniAlati cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Instant getTrajanje() {
        return trajanje;
    }

    public MerniAlati trajanje(Instant trajanje) {
        this.trajanje = trajanje;
        return this;
    }

    public void setTrajanje(Instant trajanje) {
        this.trajanje = trajanje;
    }

    public Instant getBazdarenje() {
        return bazdarenje;
    }

    public MerniAlati bazdarenje(Instant bazdarenje) {
        this.bazdarenje = bazdarenje;
        return this;
    }

    public void setBazdarenje(Instant bazdarenje) {
        this.bazdarenje = bazdarenje;
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
        MerniAlati merniAlati = (MerniAlati) o;
        if (merniAlati.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), merniAlati.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MerniAlati{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", vrsta='" + getVrsta() + "'" +
            ", jedMere='" + getJedMere() + "'" +
            ", namena='" + getNamena() + "'" +
            ", cena=" + getCena() +
            ", trajanje='" + getTrajanje() + "'" +
            ", bazdarenje='" + getBazdarenje() + "'" +
            "}";
    }
}
