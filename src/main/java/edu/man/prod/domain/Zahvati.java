package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Zahvati.
 */
@Entity
@Table(name = "zahvati")
public class Zahvati implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "vreme_rada")
    private Double vremeRada;

    @Column(name = "pomocno_vreme")
    private Double pomocnoVreme;

    @Column(name = "cena")
    private Double cena;

    @Column(name = "skica")
    private String skica;

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

    public Zahvati naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getVremeRada() {
        return vremeRada;
    }

    public Zahvati vremeRada(Double vremeRada) {
        this.vremeRada = vremeRada;
        return this;
    }

    public void setVremeRada(Double vremeRada) {
        this.vremeRada = vremeRada;
    }

    public Double getPomocnoVreme() {
        return pomocnoVreme;
    }

    public Zahvati pomocnoVreme(Double pomocnoVreme) {
        this.pomocnoVreme = pomocnoVreme;
        return this;
    }

    public void setPomocnoVreme(Double pomocnoVreme) {
        this.pomocnoVreme = pomocnoVreme;
    }

    public Double getCena() {
        return cena;
    }

    public Zahvati cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getSkica() {
        return skica;
    }

    public Zahvati skica(String skica) {
        this.skica = skica;
        return this;
    }

    public void setSkica(String skica) {
        this.skica = skica;
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
        Zahvati zahvati = (Zahvati) o;
        if (zahvati.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), zahvati.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Zahvati{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", vremeRada=" + getVremeRada() +
            ", pomocnoVreme=" + getPomocnoVreme() +
            ", cena=" + getCena() +
            ", skica='" + getSkica() + "'" +
            "}";
    }
}
