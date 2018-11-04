package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import edu.man.prod.domain.enumeration.TipMagacina;

/**
 * A StavkeUMagacinu.
 */
@Entity
@Table(name = "stavke_u_magacinu")
public class StavkeUMagacinu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "stanje")
    private Double stanje;

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

    public Double getStanje() {
        return stanje;
    }

    public StavkeUMagacinu stanje(Double stanje) {
        this.stanje = stanje;
        return this;
    }

    public void setStanje(Double stanje) {
        this.stanje = stanje;
    }

    public TipMagacina getTipMagacina() {
        return tipMagacina;
    }

    public StavkeUMagacinu tipMagacina(TipMagacina tipMagacina) {
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
        StavkeUMagacinu stavkeUMagacinu = (StavkeUMagacinu) o;
        if (stavkeUMagacinu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stavkeUMagacinu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StavkeUMagacinu{" +
            "id=" + getId() +
            ", stanje=" + getStanje() +
            ", tipMagacina='" + getTipMagacina() + "'" +
            "}";
    }
}
