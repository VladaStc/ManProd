package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Dobavljac.
 */
@Entity
@Table(name = "dobavljac")
public class Dobavljac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "kolicina")
    private Double kolicina;

    @OneToOne    @JoinColumn(unique = true)
    private Alati alati;

    @OneToOne    @JoinColumn(unique = true)
    private Pribori pribori;

    @OneToOne    @JoinColumn(unique = true)
    private MerniAlati merniAlati;

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

    public Dobavljac naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public Dobavljac lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public Dobavljac kolicina(Double kolicina) {
        this.kolicina = kolicina;
        return this;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    public Alati getAlati() {
        return alati;
    }

    public Dobavljac alati(Alati alati) {
        this.alati = alati;
        return this;
    }

    public void setAlati(Alati alati) {
        this.alati = alati;
    }

    public Pribori getPribori() {
        return pribori;
    }

    public Dobavljac pribori(Pribori pribori) {
        this.pribori = pribori;
        return this;
    }

    public void setPribori(Pribori pribori) {
        this.pribori = pribori;
    }

    public MerniAlati getMerniAlati() {
        return merniAlati;
    }

    public Dobavljac merniAlati(MerniAlati merniAlati) {
        this.merniAlati = merniAlati;
        return this;
    }

    public void setMerniAlati(MerniAlati merniAlati) {
        this.merniAlati = merniAlati;
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
        Dobavljac dobavljac = (Dobavljac) o;
        if (dobavljac.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dobavljac.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Dobavljac{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", kolicina=" + getKolicina() +
            "}";
    }
}
