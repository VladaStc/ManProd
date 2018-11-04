package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A OperacijeURadnomNalogu.
 */
@Entity
@Table(name = "operacije_u_radnom_nalogu")
public class OperacijeURadnomNalogu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "vreme_rada")
    private Double vremeRada;

    @Column(name = "pripremno_zavrsno_vreme")
    private Double pripremnoZavrsnoVreme;

    @Column(name = "cena")
    private Double cena;

    @Column(name = "skica")
    private String skica;

    @Column(name = "datum_i_vreme_pocetka")
    private Instant datumIVremePocetka;

    @Column(name = "datum_i_vreme_zavrsetka")
    private Instant datumIVremeZavrsetka;

    @OneToOne    @JoinColumn(unique = true)
    private RadniNalog radniNalog;

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

    public OperacijeURadnomNalogu naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getVremeRada() {
        return vremeRada;
    }

    public OperacijeURadnomNalogu vremeRada(Double vremeRada) {
        this.vremeRada = vremeRada;
        return this;
    }

    public void setVremeRada(Double vremeRada) {
        this.vremeRada = vremeRada;
    }

    public Double getPripremnoZavrsnoVreme() {
        return pripremnoZavrsnoVreme;
    }

    public OperacijeURadnomNalogu pripremnoZavrsnoVreme(Double pripremnoZavrsnoVreme) {
        this.pripremnoZavrsnoVreme = pripremnoZavrsnoVreme;
        return this;
    }

    public void setPripremnoZavrsnoVreme(Double pripremnoZavrsnoVreme) {
        this.pripremnoZavrsnoVreme = pripremnoZavrsnoVreme;
    }

    public Double getCena() {
        return cena;
    }

    public OperacijeURadnomNalogu cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getSkica() {
        return skica;
    }

    public OperacijeURadnomNalogu skica(String skica) {
        this.skica = skica;
        return this;
    }

    public void setSkica(String skica) {
        this.skica = skica;
    }

    public Instant getDatumIVremePocetka() {
        return datumIVremePocetka;
    }

    public OperacijeURadnomNalogu datumIVremePocetka(Instant datumIVremePocetka) {
        this.datumIVremePocetka = datumIVremePocetka;
        return this;
    }

    public void setDatumIVremePocetka(Instant datumIVremePocetka) {
        this.datumIVremePocetka = datumIVremePocetka;
    }

    public Instant getDatumIVremeZavrsetka() {
        return datumIVremeZavrsetka;
    }

    public OperacijeURadnomNalogu datumIVremeZavrsetka(Instant datumIVremeZavrsetka) {
        this.datumIVremeZavrsetka = datumIVremeZavrsetka;
        return this;
    }

    public void setDatumIVremeZavrsetka(Instant datumIVremeZavrsetka) {
        this.datumIVremeZavrsetka = datumIVremeZavrsetka;
    }

    public RadniNalog getRadniNalog() {
        return radniNalog;
    }

    public OperacijeURadnomNalogu radniNalog(RadniNalog radniNalog) {
        this.radniNalog = radniNalog;
        return this;
    }

    public void setRadniNalog(RadniNalog radniNalog) {
        this.radniNalog = radniNalog;
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
        OperacijeURadnomNalogu operacijeURadnomNalogu = (OperacijeURadnomNalogu) o;
        if (operacijeURadnomNalogu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), operacijeURadnomNalogu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OperacijeURadnomNalogu{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", vremeRada=" + getVremeRada() +
            ", pripremnoZavrsnoVreme=" + getPripremnoZavrsnoVreme() +
            ", cena=" + getCena() +
            ", skica='" + getSkica() + "'" +
            ", datumIVremePocetka='" + getDatumIVremePocetka() + "'" +
            ", datumIVremeZavrsetka='" + getDatumIVremeZavrsetka() + "'" +
            "}";
    }
}
