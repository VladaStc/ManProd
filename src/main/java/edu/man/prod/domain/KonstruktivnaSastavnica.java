package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A KonstruktivnaSastavnica.
 */
@Entity
@Table(name = "konstruktivna_sastavnica")
public class KonstruktivnaSastavnica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra_dela")
    private String sifraDela;

    @Column(name = "naziv_dela")
    private String nazivDela;

    @Column(name = "kolicina")
    private Double kolicina;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifraDela() {
        return sifraDela;
    }

    public KonstruktivnaSastavnica sifraDela(String sifraDela) {
        this.sifraDela = sifraDela;
        return this;
    }

    public void setSifraDela(String sifraDela) {
        this.sifraDela = sifraDela;
    }

    public String getNazivDela() {
        return nazivDela;
    }

    public KonstruktivnaSastavnica nazivDela(String nazivDela) {
        this.nazivDela = nazivDela;
        return this;
    }

    public void setNazivDela(String nazivDela) {
        this.nazivDela = nazivDela;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public KonstruktivnaSastavnica kolicina(Double kolicina) {
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
        KonstruktivnaSastavnica konstruktivnaSastavnica = (KonstruktivnaSastavnica) o;
        if (konstruktivnaSastavnica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), konstruktivnaSastavnica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KonstruktivnaSastavnica{" +
            "id=" + getId() +
            ", sifraDela='" + getSifraDela() + "'" +
            ", nazivDela='" + getNazivDela() + "'" +
            ", kolicina=" + getKolicina() +
            "}";
    }
}
