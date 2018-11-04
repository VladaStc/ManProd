package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Operacije.
 */
@Entity
@Table(name = "operacije")
public class Operacije implements Serializable {

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

    public Operacije naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getVremeRada() {
        return vremeRada;
    }

    public Operacije vremeRada(Double vremeRada) {
        this.vremeRada = vremeRada;
        return this;
    }

    public void setVremeRada(Double vremeRada) {
        this.vremeRada = vremeRada;
    }

    public Double getPomocnoVreme() {
        return pomocnoVreme;
    }

    public Operacije pomocnoVreme(Double pomocnoVreme) {
        this.pomocnoVreme = pomocnoVreme;
        return this;
    }

    public void setPomocnoVreme(Double pomocnoVreme) {
        this.pomocnoVreme = pomocnoVreme;
    }

    public Double getCena() {
        return cena;
    }

    public Operacije cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getSkica() {
        return skica;
    }

    public Operacije skica(String skica) {
        this.skica = skica;
        return this;
    }

    public void setSkica(String skica) {
        this.skica = skica;
    }

    public Zahvati getZahvati() {
        return zahvati;
    }

    public Operacije zahvati(Zahvati zahvati) {
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
        Operacije operacije = (Operacije) o;
        if (operacije.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), operacije.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Operacije{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", vremeRada=" + getVremeRada() +
            ", pomocnoVreme=" + getPomocnoVreme() +
            ", cena=" + getCena() +
            ", skica='" + getSkica() + "'" +
            "}";
    }
}
