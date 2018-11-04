package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import edu.man.prod.domain.enumeration.Status;

/**
 * A RadniNalog.
 */
@Entity
@Table(name = "radni_nalog")
public class RadniNalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "datum_i_vreme_otvaranja")
    private Instant datumIVremeOtvaranja;

    @Column(name = "datum_i_vreme_zatvaranja")
    private Instant datumIVremeZatvaranja;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "nosilac")
    private String nosilac;

    @Column(name = "cena")
    private Double cena;

    @Column(name = "kolicina")
    private Double kolicina;

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

    public RadniNalog naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Instant getDatumIVremeOtvaranja() {
        return datumIVremeOtvaranja;
    }

    public RadniNalog datumIVremeOtvaranja(Instant datumIVremeOtvaranja) {
        this.datumIVremeOtvaranja = datumIVremeOtvaranja;
        return this;
    }

    public void setDatumIVremeOtvaranja(Instant datumIVremeOtvaranja) {
        this.datumIVremeOtvaranja = datumIVremeOtvaranja;
    }

    public Instant getDatumIVremeZatvaranja() {
        return datumIVremeZatvaranja;
    }

    public RadniNalog datumIVremeZatvaranja(Instant datumIVremeZatvaranja) {
        this.datumIVremeZatvaranja = datumIVremeZatvaranja;
        return this;
    }

    public void setDatumIVremeZatvaranja(Instant datumIVremeZatvaranja) {
        this.datumIVremeZatvaranja = datumIVremeZatvaranja;
    }

    public Status getStatus() {
        return status;
    }

    public RadniNalog status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNosilac() {
        return nosilac;
    }

    public RadniNalog nosilac(String nosilac) {
        this.nosilac = nosilac;
        return this;
    }

    public void setNosilac(String nosilac) {
        this.nosilac = nosilac;
    }

    public Double getCena() {
        return cena;
    }

    public RadniNalog cena(Double cena) {
        this.cena = cena;
        return this;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public RadniNalog kolicina(Double kolicina) {
        this.kolicina = kolicina;
        return this;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
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
        RadniNalog radniNalog = (RadniNalog) o;
        if (radniNalog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), radniNalog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RadniNalog{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", datumIVremeOtvaranja='" + getDatumIVremeOtvaranja() + "'" +
            ", datumIVremeZatvaranja='" + getDatumIVremeZatvaranja() + "'" +
            ", status='" + getStatus() + "'" +
            ", nosilac='" + getNosilac() + "'" +
            ", cena=" + getCena() +
            ", kolicina=" + getKolicina() +
            "}";
    }
}
