package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import edu.man.prod.domain.enumeration.JedMere;

/**
 * A OstaliMaterijali.
 */
@Entity
@Table(name = "ostali_materijali")
public class OstaliMaterijali implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sifra")
    private String sifra;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "vrsta")
    private String vrsta;

    @Enumerated(EnumType.STRING)
    @Column(name = "jed_mere")
    private JedMere jedMere;

    @Column(name = "namena")
    private String namena;

    @Column(name = "nabavna_cena")
    private Double nabavnaCena;

    @Column(name = "napomena")
    private String napomena;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifra() {
        return sifra;
    }

    public OstaliMaterijali sifra(String sifra) {
        this.sifra = sifra;
        return this;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public OstaliMaterijali naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public OstaliMaterijali vrsta(String vrsta) {
        this.vrsta = vrsta;
        return this;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public JedMere getJedMere() {
        return jedMere;
    }

    public OstaliMaterijali jedMere(JedMere jedMere) {
        this.jedMere = jedMere;
        return this;
    }

    public void setJedMere(JedMere jedMere) {
        this.jedMere = jedMere;
    }

    public String getNamena() {
        return namena;
    }

    public OstaliMaterijali namena(String namena) {
        this.namena = namena;
        return this;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Double getNabavnaCena() {
        return nabavnaCena;
    }

    public OstaliMaterijali nabavnaCena(Double nabavnaCena) {
        this.nabavnaCena = nabavnaCena;
        return this;
    }

    public void setNabavnaCena(Double nabavnaCena) {
        this.nabavnaCena = nabavnaCena;
    }

    public String getNapomena() {
        return napomena;
    }

    public OstaliMaterijali napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
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
        OstaliMaterijali ostaliMaterijali = (OstaliMaterijali) o;
        if (ostaliMaterijali.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ostaliMaterijali.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OstaliMaterijali{" +
            "id=" + getId() +
            ", sifra='" + getSifra() + "'" +
            ", naziv='" + getNaziv() + "'" +
            ", vrsta='" + getVrsta() + "'" +
            ", jedMere='" + getJedMere() + "'" +
            ", namena='" + getNamena() + "'" +
            ", nabavnaCena=" + getNabavnaCena() +
            ", napomena='" + getNapomena() + "'" +
            "}";
    }
}
