package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import edu.man.prod.domain.enumeration.JedMere;

/**
 * A PomocniProizvod.
 */
@Entity
@Table(name = "pomocni_proizvod")
public class PomocniProizvod implements Serializable {

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

    @Column(name = "napomena")
    private String napomena;

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

    public PomocniProizvod naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public PomocniProizvod vrsta(String vrsta) {
        this.vrsta = vrsta;
        return this;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public JedMere getJedMere() {
        return jedMere;
    }

    public PomocniProizvod jedMere(JedMere jedMere) {
        this.jedMere = jedMere;
        return this;
    }

    public void setJedMere(JedMere jedMere) {
        this.jedMere = jedMere;
    }

    public String getNamena() {
        return namena;
    }

    public PomocniProizvod namena(String namena) {
        this.namena = namena;
        return this;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Double getCena() {
        return cena;
    }

    public PomocniProizvod cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNapomena() {
        return napomena;
    }

    public PomocniProizvod napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
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
        PomocniProizvod pomocniProizvod = (PomocniProizvod) o;
        if (pomocniProizvod.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pomocniProizvod.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PomocniProizvod{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", vrsta='" + getVrsta() + "'" +
            ", jedMere='" + getJedMere() + "'" +
            ", namena='" + getNamena() + "'" +
            ", cena=" + getCena() +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
