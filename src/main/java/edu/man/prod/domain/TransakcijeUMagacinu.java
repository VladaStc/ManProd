package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import edu.man.prod.domain.enumeration.TipMagacina;

/**
 * A TransakcijeUMagacinu.
 */
@Entity
@Table(name = "transakcije_u_magacinu")
public class TransakcijeUMagacinu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ulaz")
    private Double ulaz;

    @Column(name = "izlaz")
    private Double izlaz;

    @Column(name = "stanje")
    private Double stanje;

    @Column(name = "napomena")
    private String napomena;

    @Column(name = "datum")
    private Instant datum;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip_magacina")
    private TipMagacina tipMagacina;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUlaz() {
        return ulaz;
    }

    public TransakcijeUMagacinu ulaz(Double ulaz) {
        this.ulaz = ulaz;
        return this;
    }

    public void setUlaz(Double ulaz) {
        this.ulaz = ulaz;
    }

    public Double getIzlaz() {
        return izlaz;
    }

    public TransakcijeUMagacinu izlaz(Double izlaz) {
        this.izlaz = izlaz;
        return this;
    }

    public void setIzlaz(Double izlaz) {
        this.izlaz = izlaz;
    }

    public Double getStanje() {
        return stanje;
    }

    public TransakcijeUMagacinu stanje(Double stanje) {
        this.stanje = stanje;
        return this;
    }

    public void setStanje(Double stanje) {
        this.stanje = stanje;
    }

    public String getNapomena() {
        return napomena;
    }

    public TransakcijeUMagacinu napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Instant getDatum() {
        return datum;
    }

    public TransakcijeUMagacinu datum(Instant datum) {
        this.datum = datum;
        return this;
    }

    public void setDatum(Instant datum) {
        this.datum = datum;
    }

    public TipMagacina getTipMagacina() {
        return tipMagacina;
    }

    public TransakcijeUMagacinu tipMagacina(TipMagacina tipMagacina) {
        this.tipMagacina = tipMagacina;
        return this;
    }

    public void setTipMagacina(TipMagacina tipMagacina) {
        this.tipMagacina = tipMagacina;
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
        TransakcijeUMagacinu transakcijeUMagacinu = (TransakcijeUMagacinu) o;
        if (transakcijeUMagacinu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transakcijeUMagacinu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransakcijeUMagacinu{" +
            "id=" + getId() +
            ", ulaz=" + getUlaz() +
            ", izlaz=" + getIzlaz() +
            ", stanje=" + getStanje() +
            ", napomena='" + getNapomena() + "'" +
            ", datum='" + getDatum() + "'" +
            ", tipMagacina='" + getTipMagacina() + "'" +
            "}";
    }
}
