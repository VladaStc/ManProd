package edu.man.prod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Oprema.
 */
@Entity
@Table(name = "oprema")
public class Oprema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "struktura")
    private String struktura;

    @Column(name = "rukovodilac")
    private String rukovodilac;

    @Column(name = "napomena")
    private String napomena;

    @ManyToOne
    @JsonIgnoreProperties("opremas")
    private ObradniSistem obradniSistem;

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

    public Oprema naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public Oprema lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getStruktura() {
        return struktura;
    }

    public Oprema struktura(String struktura) {
        this.struktura = struktura;
        return this;
    }

    public void setStruktura(String struktura) {
        this.struktura = struktura;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public Oprema rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNapomena() {
        return napomena;
    }

    public Oprema napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public ObradniSistem getObradniSistem() {
        return obradniSistem;
    }

    public Oprema obradniSistem(ObradniSistem obradniSistem) {
        this.obradniSistem = obradniSistem;
        return this;
    }

    public void setObradniSistem(ObradniSistem obradniSistem) {
        this.obradniSistem = obradniSistem;
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
        Oprema oprema = (Oprema) o;
        if (oprema.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), oprema.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Oprema{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", struktura='" + getStruktura() + "'" +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
