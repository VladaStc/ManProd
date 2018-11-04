package edu.man.prod.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import edu.man.prod.domain.enumeration.TipMagacina;

/**
 * A Magacini.
 */
@Entity
@Table(name = "magacini")
public class Magacini implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "namena")
    private String namena;

    @Column(name = "rukovodilac")
    private String rukovodilac;

    @Column(name = "napomena")
    private String napomena;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip_magacina")
    private TipMagacina tipMagacina;

    @OneToOne    @JoinColumn(unique = true)
    private StavkeUMagacinu stavkeUMagacinu;

    @OneToOne    @JoinColumn(unique = true)
    private TransakcijeUMagacinu tansakcijeUMagacinu;

    @OneToOne    @JoinColumn(unique = true)
    private Alati alati;

    @OneToOne    @JoinColumn(unique = true)
    private Pribori pribori;

    @OneToOne    @JoinColumn(unique = true)
    private MerniAlati merniAlati;

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

    @OneToOne    @JoinColumn(unique = true)
    private KonstruktivnaSastavnica konstruktivnaSastavnica;

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

    public Magacini naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public Magacini lokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getNamena() {
        return namena;
    }

    public Magacini namena(String namena) {
        this.namena = namena;
        return this;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public String getRukovodilac() {
        return rukovodilac;
    }

    public Magacini rukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
        return this;
    }

    public void setRukovodilac(String rukovodilac) {
        this.rukovodilac = rukovodilac;
    }

    public String getNapomena() {
        return napomena;
    }

    public Magacini napomena(String napomena) {
        this.napomena = napomena;
        return this;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public TipMagacina getTipMagacina() {
        return tipMagacina;
    }

    public Magacini tipMagacina(TipMagacina tipMagacina) {
        this.tipMagacina = tipMagacina;
        return this;
    }

    public void setTipMagacina(TipMagacina tipMagacina) {
        this.tipMagacina = tipMagacina;
    }

    public StavkeUMagacinu getStavkeUMagacinu() {
        return stavkeUMagacinu;
    }

    public Magacini stavkeUMagacinu(StavkeUMagacinu stavkeUMagacinu) {
        this.stavkeUMagacinu = stavkeUMagacinu;
        return this;
    }

    public void setStavkeUMagacinu(StavkeUMagacinu stavkeUMagacinu) {
        this.stavkeUMagacinu = stavkeUMagacinu;
    }

    public TransakcijeUMagacinu getTansakcijeUMagacinu() {
        return tansakcijeUMagacinu;
    }

    public Magacini tansakcijeUMagacinu(TransakcijeUMagacinu transakcijeUMagacinu) {
        this.tansakcijeUMagacinu = transakcijeUMagacinu;
        return this;
    }

    public void setTansakcijeUMagacinu(TransakcijeUMagacinu transakcijeUMagacinu) {
        this.tansakcijeUMagacinu = transakcijeUMagacinu;
    }

    public Alati getAlati() {
        return alati;
    }

    public Magacini alati(Alati alati) {
        this.alati = alati;
        return this;
    }

    public void setAlati(Alati alati) {
        this.alati = alati;
    }

    public Pribori getPribori() {
        return pribori;
    }

    public Magacini pribori(Pribori pribori) {
        this.pribori = pribori;
        return this;
    }

    public void setPribori(Pribori pribori) {
        this.pribori = pribori;
    }

    public MerniAlati getMerniAlati() {
        return merniAlati;
    }

    public Magacini merniAlati(MerniAlati merniAlati) {
        this.merniAlati = merniAlati;
        return this;
    }

    public void setMerniAlati(MerniAlati merniAlati) {
        this.merniAlati = merniAlati;
    }

    public FinalniProizvod getFinalniProizvod() {
        return finalniProizvod;
    }

    public Magacini finalniProizvod(FinalniProizvod finalniProizvod) {
        this.finalniProizvod = finalniProizvod;
        return this;
    }

    public void setFinalniProizvod(FinalniProizvod finalniProizvod) {
        this.finalniProizvod = finalniProizvod;
    }

    public Poluproizvod getPoluproizvod() {
        return poluproizvod;
    }

    public Magacini poluproizvod(Poluproizvod poluproizvod) {
        this.poluproizvod = poluproizvod;
        return this;
    }

    public void setPoluproizvod(Poluproizvod poluproizvod) {
        this.poluproizvod = poluproizvod;
    }

    public KupovniProizvod getKupovniProizvod() {
        return kupovniProizvod;
    }

    public Magacini kupovniProizvod(KupovniProizvod kupovniProizvod) {
        this.kupovniProizvod = kupovniProizvod;
        return this;
    }

    public void setKupovniProizvod(KupovniProizvod kupovniProizvod) {
        this.kupovniProizvod = kupovniProizvod;
    }

    public PomocniProizvod getPomocniProizvod() {
        return pomocniProizvod;
    }

    public Magacini pomocniProizvod(PomocniProizvod pomocniProizvod) {
        this.pomocniProizvod = pomocniProizvod;
        return this;
    }

    public void setPomocniProizvod(PomocniProizvod pomocniProizvod) {
        this.pomocniProizvod = pomocniProizvod;
    }

    public Sirovine getSirovine() {
        return sirovine;
    }

    public Magacini sirovine(Sirovine sirovine) {
        this.sirovine = sirovine;
        return this;
    }

    public void setSirovine(Sirovine sirovine) {
        this.sirovine = sirovine;
    }

    public KupovniMaterijal getKupovniMaterijal() {
        return kupovniMaterijal;
    }

    public Magacini kupovniMaterijal(KupovniMaterijal kupovniMaterijal) {
        this.kupovniMaterijal = kupovniMaterijal;
        return this;
    }

    public void setKupovniMaterijal(KupovniMaterijal kupovniMaterijal) {
        this.kupovniMaterijal = kupovniMaterijal;
    }

    public Komponenete getKomponente() {
        return komponente;
    }

    public Magacini komponente(Komponenete komponenete) {
        this.komponente = komponenete;
        return this;
    }

    public void setKomponente(Komponenete komponenete) {
        this.komponente = komponenete;
    }

    public PotrosniMaterijal getPotrosniMaterijal() {
        return potrosniMaterijal;
    }

    public Magacini potrosniMaterijal(PotrosniMaterijal potrosniMaterijal) {
        this.potrosniMaterijal = potrosniMaterijal;
        return this;
    }

    public void setPotrosniMaterijal(PotrosniMaterijal potrosniMaterijal) {
        this.potrosniMaterijal = potrosniMaterijal;
    }

    public OstaliMaterijali getOstaliMaterijali() {
        return ostaliMaterijali;
    }

    public Magacini ostaliMaterijali(OstaliMaterijali ostaliMaterijali) {
        this.ostaliMaterijali = ostaliMaterijali;
        return this;
    }

    public void setOstaliMaterijali(OstaliMaterijali ostaliMaterijali) {
        this.ostaliMaterijali = ostaliMaterijali;
    }

    public KonstruktivnaSastavnica getKonstruktivnaSastavnica() {
        return konstruktivnaSastavnica;
    }

    public Magacini konstruktivnaSastavnica(KonstruktivnaSastavnica konstruktivnaSastavnica) {
        this.konstruktivnaSastavnica = konstruktivnaSastavnica;
        return this;
    }

    public void setKonstruktivnaSastavnica(KonstruktivnaSastavnica konstruktivnaSastavnica) {
        this.konstruktivnaSastavnica = konstruktivnaSastavnica;
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
        Magacini magacini = (Magacini) o;
        if (magacini.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), magacini.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Magacini{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", lokacija='" + getLokacija() + "'" +
            ", namena='" + getNamena() + "'" +
            ", rukovodilac='" + getRukovodilac() + "'" +
            ", napomena='" + getNapomena() + "'" +
            ", tipMagacina='" + getTipMagacina() + "'" +
            "}";
    }
}
