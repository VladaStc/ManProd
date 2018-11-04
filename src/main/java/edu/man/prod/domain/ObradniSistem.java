package edu.man.prod.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ObradniSistem.
 */
@Entity
@Table(name = "obradni_sistem")
public class ObradniSistem implements Serializable {

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

    @OneToOne    @JoinColumn(unique = true)
    private KonstruktivnaSastavnica konstruktivnaSastavnica;

    @OneToMany(mappedBy = "obradniSistem")
    private Set<Masina> masinas = new HashSet<>();
    @OneToMany(mappedBy = "obradniSistem")
    private Set<Oprema> opremas = new HashSet<>();
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

    public ObradniSistem naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public ObradniSistem lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getStruktura() {
        return struktura;
    }

    public ObradniSistem struktura(String struktura) {
        this.struktura = struktura;
        return this;
    }

    public void setStruktura(String struktura) {
        this.struktura = struktura;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public ObradniSistem rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNapomena() {
        return napomena;
    }

    public ObradniSistem napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public KonstruktivnaSastavnica getKonstruktivnaSastavnica() {
        return konstruktivnaSastavnica;
    }

    public ObradniSistem konstruktivnaSastavnica(KonstruktivnaSastavnica konstruktivnaSastavnica) {
        this.konstruktivnaSastavnica = konstruktivnaSastavnica;
        return this;
    }

    public void setKonstruktivnaSastavnica(KonstruktivnaSastavnica konstruktivnaSastavnica) {
        this.konstruktivnaSastavnica = konstruktivnaSastavnica;
    }

    public Set<Masina> getMasinas() {
        return masinas;
    }

    public ObradniSistem masinas(Set<Masina> masinas) {
        this.masinas = masinas;
        return this;
    }

    public ObradniSistem addMasina(Masina masina) {
        this.masinas.add(masina);
        masina.setObradniSistem(this);
        return this;
    }

    public ObradniSistem removeMasina(Masina masina) {
        this.masinas.remove(masina);
        masina.setObradniSistem(null);
        return this;
    }

    public void setMasinas(Set<Masina> masinas) {
        this.masinas = masinas;
    }

    public Set<Oprema> getOpremas() {
        return opremas;
    }

    public ObradniSistem opremas(Set<Oprema> opremas) {
        this.opremas = opremas;
        return this;
    }

    public ObradniSistem addOprema(Oprema oprema) {
        this.opremas.add(oprema);
        oprema.setObradniSistem(this);
        return this;
    }

    public ObradniSistem removeOprema(Oprema oprema) {
        this.opremas.remove(oprema);
        oprema.setObradniSistem(null);
        return this;
    }

    public void setOpremas(Set<Oprema> opremas) {
        this.opremas = opremas;
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
        ObradniSistem obradniSistem = (ObradniSistem) o;
        if (obradniSistem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), obradniSistem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObradniSistem{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", struktura='" + getStruktura() + "'" +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
