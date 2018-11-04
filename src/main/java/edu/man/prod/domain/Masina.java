package edu.man.prod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Masina.
 */
@Entity
@Table(name = "masina")
public class Masina implements Serializable {

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
    @JsonIgnoreProperties("masinas")
    private ObradniSistem obradniSistem;

    @OneToOne    @JoinColumn(unique = true)
    private OperacijeURadnomNalogu operacijeURadnomNalogu;

    @OneToOne    @JoinColumn(unique = true)
    private Operacije operacije;

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

    public Masina naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public Masina lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getStruktura() {
        return struktura;
    }

    public Masina struktura(String struktura) {
        this.struktura = struktura;
        return this;
    }

    public void setStruktura(String struktura) {
        this.struktura = struktura;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public Masina rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNapomena() {
        return napomena;
    }

    public Masina napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public ObradniSistem getObradniSistem() {
        return obradniSistem;
    }

    public Masina obradniSistem(ObradniSistem obradniSistem) {
        this.obradniSistem = obradniSistem;
        return this;
    }

    public void setObradniSistem(ObradniSistem obradniSistem) {
        this.obradniSistem = obradniSistem;
    }

    public OperacijeURadnomNalogu getOperacijeURadnomNalogu() {
        return operacijeURadnomNalogu;
    }

    public Masina operacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
        return this;
    }

    public void setOperacijeURadnomNalogu(OperacijeURadnomNalogu operacijeURadnomNalogu) {
        this.operacijeURadnomNalogu = operacijeURadnomNalogu;
    }

    public Operacije getOperacije() {
        return operacije;
    }

    public Masina operacije(Operacije operacije) {
        this.operacije = operacije;
        return this;
    }

    public void setOperacije(Operacije operacije) {
        this.operacije = operacije;
    }

    public Zahvati getZahvati() {
        return zahvati;
    }

    public Masina zahvati(Zahvati zahvati) {
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
        Masina masina = (Masina) o;
        if (masina.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masina.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Masina{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", struktura='" + getStruktura() + "'" +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
