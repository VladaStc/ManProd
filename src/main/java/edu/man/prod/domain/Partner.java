package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Partner.
 */
@Entity
@Table(name = "partner")
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "lokacija")
    private String lokacija;

    @OneToOne    @JoinColumn(unique = true)
    private FinalniProizvod finalniProizvod;

    @OneToOne    @JoinColumn(unique = true)
    private Poluproizvod poluproizvod;

    @OneToOne    @JoinColumn(unique = true)
    private KupovniProizvod kupovniProizvod;

    @OneToOne    @JoinColumn(unique = true)
    private PomocniProizvod pomocniProizvod;

    @OneToOne    @JoinColumn(unique = true)
    private Sirovine sirovine;

    @OneToOne    @JoinColumn(unique = true)
    private KupovniMaterijal kupovniMaterijal;

    @OneToOne    @JoinColumn(unique = true)
    private Komponenete komponente;

    @OneToOne    @JoinColumn(unique = true)
    private PotrosniMaterijal potrosniMaterijal;

    @OneToOne    @JoinColumn(unique = true)
    private OstaliMaterijali ostaliMaterijali;

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

    public Partner naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public Partner lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public FinalniProizvod getFinalniProizvod() {
        return finalniProizvod;
    }

    public Partner finalniProizvod(FinalniProizvod finalniProizvod) {
        this.finalniProizvod = finalniProizvod;
        return this;
    }

    public void setFinalniProizvod(FinalniProizvod finalniProizvod) {
        this.finalniProizvod = finalniProizvod;
    }

    public Poluproizvod getPoluproizvod() {
        return poluproizvod;
    }

    public Partner poluproizvod(Poluproizvod poluproizvod) {
        this.poluproizvod = poluproizvod;
        return this;
    }

    public void setPoluproizvod(Poluproizvod poluproizvod) {
        this.poluproizvod = poluproizvod;
    }

    public KupovniProizvod getKupovniProizvod() {
        return kupovniProizvod;
    }

    public Partner kupovniProizvod(KupovniProizvod kupovniProizvod) {
        this.kupovniProizvod = kupovniProizvod;
        return this;
    }

    public void setKupovniProizvod(KupovniProizvod kupovniProizvod) {
        this.kupovniProizvod = kupovniProizvod;
    }

    public PomocniProizvod getPomocniProizvod() {
        return pomocniProizvod;
    }

    public Partner pomocniProizvod(PomocniProizvod pomocniProizvod) {
        this.pomocniProizvod = pomocniProizvod;
        return this;
    }

    public void setPomocniProizvod(PomocniProizvod pomocniProizvod) {
        this.pomocniProizvod = pomocniProizvod;
    }

    public Sirovine getSirovine() {
        return sirovine;
    }

    public Partner sirovine(Sirovine sirovine) {
        this.sirovine = sirovine;
        return this;
    }

    public void setSirovine(Sirovine sirovine) {
        this.sirovine = sirovine;
    }

    public KupovniMaterijal getKupovniMaterijal() {
        return kupovniMaterijal;
    }

    public Partner kupovniMaterijal(KupovniMaterijal kupovniMaterijal) {
        this.kupovniMaterijal = kupovniMaterijal;
        return this;
    }

    public void setKupovniMaterijal(KupovniMaterijal kupovniMaterijal) {
        this.kupovniMaterijal = kupovniMaterijal;
    }

    public Komponenete getKomponente() {
        return komponente;
    }

    public Partner komponente(Komponenete komponenete) {
        this.komponente = komponenete;
        return this;
    }

    public void setKomponente(Komponenete komponenete) {
        this.komponente = komponenete;
    }

    public PotrosniMaterijal getPotrosniMaterijal() {
        return potrosniMaterijal;
    }

    public Partner potrosniMaterijal(PotrosniMaterijal potrosniMaterijal) {
        this.potrosniMaterijal = potrosniMaterijal;
        return this;
    }

    public void setPotrosniMaterijal(PotrosniMaterijal potrosniMaterijal) {
        this.potrosniMaterijal = potrosniMaterijal;
    }

    public OstaliMaterijali getOstaliMaterijali() {
        return ostaliMaterijali;
    }

    public Partner ostaliMaterijali(OstaliMaterijali ostaliMaterijali) {
        this.ostaliMaterijali = ostaliMaterijali;
        return this;
    }

    public void setOstaliMaterijali(OstaliMaterijali ostaliMaterijali) {
        this.ostaliMaterijali = ostaliMaterijali;
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
        Partner partner = (Partner) o;
        if (partner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Partner{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            "}";
    }
}
