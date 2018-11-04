import { IStavkeUMagacinu } from 'app/shared/model//stavke-u-magacinu.model';
import { ITransakcijeUMagacinu } from 'app/shared/model//transakcije-u-magacinu.model';
import { IAlati } from 'app/shared/model//alati.model';
import { IPribori } from 'app/shared/model//pribori.model';
import { IMerniAlati } from 'app/shared/model//merni-alati.model';
import { IFinalniProizvod } from 'app/shared/model//finalni-proizvod.model';
import { IPoluproizvod } from 'app/shared/model//poluproizvod.model';
import { IKupovniProizvod } from 'app/shared/model//kupovni-proizvod.model';
import { IPomocniProizvod } from 'app/shared/model//pomocni-proizvod.model';
import { ISirovine } from 'app/shared/model//sirovine.model';
import { IKupovniMaterijal } from 'app/shared/model//kupovni-materijal.model';
import { IKomponenete } from 'app/shared/model//komponenete.model';
import { IPotrosniMaterijal } from 'app/shared/model//potrosni-materijal.model';
import { IOstaliMaterijali } from 'app/shared/model//ostali-materijali.model';
import { IKonstruktivnaSastavnica } from 'app/shared/model//konstruktivna-sastavnica.model';

export const enum TipMagacina {
    MAGACIN_SIROVINA = 'MAGACIN_SIROVINA',
    MAGACIN_GOTOVIH_PROIZVODA = 'MAGACIN_GOTOVIH_PROIZVODA',
    MAGACIN_PROIZVODNJE_U_TOKU = 'MAGACIN_PROIZVODNJE_U_TOKU'
}

export interface IMagacini {
    id?: number;
    naziv?: string;
    lokacija?: string;
    namena?: string;
    rukovodilac?: string;
    napomena?: string;
    tipMagacina?: TipMagacina;
    stavkeUMagacinu?: IStavkeUMagacinu;
    tansakcijeUMagacinu?: ITransakcijeUMagacinu;
    alati?: IAlati;
    pribori?: IPribori;
    merniAlati?: IMerniAlati;
    finalniProizvod?: IFinalniProizvod;
    poluproizvod?: IPoluproizvod;
    kupovniProizvod?: IKupovniProizvod;
    pomocniProizvod?: IPomocniProizvod;
    sirovine?: ISirovine;
    kupovniMaterijal?: IKupovniMaterijal;
    komponente?: IKomponenete;
    potrosniMaterijal?: IPotrosniMaterijal;
    ostaliMaterijali?: IOstaliMaterijali;
    konstruktivnaSastavnica?: IKonstruktivnaSastavnica;
}

export class Magacini implements IMagacini {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public namena?: string,
        public rukovodilac?: string,
        public napomena?: string,
        public tipMagacina?: TipMagacina,
        public stavkeUMagacinu?: IStavkeUMagacinu,
        public tansakcijeUMagacinu?: ITransakcijeUMagacinu,
        public alati?: IAlati,
        public pribori?: IPribori,
        public merniAlati?: IMerniAlati,
        public finalniProizvod?: IFinalniProizvod,
        public poluproizvod?: IPoluproizvod,
        public kupovniProizvod?: IKupovniProizvod,
        public pomocniProizvod?: IPomocniProizvod,
        public sirovine?: ISirovine,
        public kupovniMaterijal?: IKupovniMaterijal,
        public komponente?: IKomponenete,
        public potrosniMaterijal?: IPotrosniMaterijal,
        public ostaliMaterijali?: IOstaliMaterijali,
        public konstruktivnaSastavnica?: IKonstruktivnaSastavnica
    ) {}
}
