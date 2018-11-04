package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ProizvodniPogoni.
 */
@Entity
@Table(name = "proizvodni_pogoni")
public class ProizvodniPogoni implements Serializable {

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
    private ProizvodneLinije proizvodneLinije;

    @OneToOne    @JoinColumn(unique = true)
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

    public ProizvodniPogoni naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public ProizvodniPogoni lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Double getPovrsina() {
        return povrsina;
    }

    public ProizvodniPogoni povrsina(Double povrsina) {
        this.povrsina = povrsina;
        return this;
    }

    public void setPovrsina(Double povrsina) {
        this.povrsina = povrsina;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public ProizvodniPogoni rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNapomena() {
        return napomena;
    }

    public ProizvodniPogoni napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public ProizvodneLinije getProizvodneLinije() {
        return proizvodneLinije;
    }

    public ProizvodniPogoni proizvodneLinije(ProizvodneLinije proizvodneLinije) {
        this.proizvodneLinije = proizvodneLinije;
        return this;
    }

    public void setProizvodneLinije(ProizvodneLinije proizvodneLinije) {
        this.proizvodneLinije = proizvodneLinije;
    }

    public ObradniSistem getObradniSistem() {
        return obradniSistem;
    }

    public ProizvodniPogoni obradniSistem(ObradniSistem obradniSistem) {
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
        ProizvodniPogoni proizvodniPogoni = (ProizvodniPogoni) o;
        if (proizvodniPogoni.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proizvodniPogoni.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProizvodniPogoni{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", povrsina=" + getPovrsina() +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
