import { IFinalniProizvod } from 'app/shared/model//finalni-proizvod.model';
import { IPoluproizvod } from 'app/shared/model//poluproizvod.model';
import { IKupovniProizvod } from 'app/shared/model//kupovni-proizvod.model';
import { IPomocniProizvod } from 'app/shared/model//pomocni-proizvod.model';
import { ISirovine } from 'app/shared/model//sirovine.model';
import { IKupovniMaterijal } from 'app/shared/model//kupovni-materijal.model';
import { IKomponenete } from 'app/shared/model//komponenete.model';
import { IPotrosniMaterijal } from 'app/shared/model//potrosni-materijal.model';
import { IOstaliMaterijali } from 'app/shared/model//ostali-materijali.model';

export interface IPartner {
    id?: number;
    naziv?: string;
    lokacija?: string;
    finalniProizvod?: IFinalniProizvod;
    poluproizvod?: IPoluproizvod;
    kupovniProizvod?: IKupovniProizvod;
    pomocniProizvod?: IPomocniProizvod;
    sirovine?: ISirovine;
    kupovniMaterijal?: IKupovniMaterijal;
    komponente?: IKomponenete;
    potrosniMaterijal?: IPotrosniMaterijal;
    ostaliMaterijali?: IOstaliMaterijali;
}

export class Partner implements IPartner {
    constructor(
        public id?: number,
        public naziv?: string,
        public lokacija?: string,
        public finalniProizvod?: IFinalniProizvod,
        public poluproizvod?: IPoluproizvod,
        public kupovniProizvod?: IKupovniProizvod,
        public pomocniProizvod?: IPomocniProizvod,
        public sirovine?: ISirovine,
        public kupovniMaterijal?: IKupovniMaterijal,
        public komponente?: IKomponenete,
        public potrosniMaterijal?: IPotrosniMaterijal,
        public ostaliMaterijali?: IOstaliMaterijali
    ) {}
}
