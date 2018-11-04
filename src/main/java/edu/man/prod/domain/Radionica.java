package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Radionica.
 */
@Entity
@Table(name = "radionica")
public class Radionica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "povrsina")
    private Double povrsina;

    @Column(name = "rukovodilac")
    private String rukovodilac;

    @Column(name = "napomena")
    private String napomena;

    @OneToOne    @JoinColumn(unique = true)
    private RadnoMesto radnoMesto;

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

    public Radionica naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public Radionica lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Double getPovrsina() {
        return povrsina;
    }

    public Radionica povrsina(Double povrsina) {
        this.povrsina = povrsina;
        return this;
    }

    public void setPovrsina(Double povrsina) {
        this.povrsina = povrsina;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public Radionica rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNapomena() {
        return napomena;
    }

    public Radionica napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public RadnoMesto getRadnoMesto() {
        return radnoMesto;
    }

    public Radionica radnoMesto(RadnoMesto radnoMesto) {
        this.radnoMesto = radnoMesto;
        return this;
    }

    public void setRadnoMesto(RadnoMesto radnoMesto) {
        this.radnoMesto = radnoMesto;
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
        Radionica radionica = (Radionica) o;
        if (radionica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), radionica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Radionica{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", povrsina=" + getPovrsina() +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
