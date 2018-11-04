import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ManProdProizvodniPogoniModule } from './proizvodni-pogoni/proizvodni-pogoni.module';
import { ManProdProizvodneLinijeModule } from './proizvodne-linije/proizvodne-linije.module';
import { ManProdOdelenjeModule } from './odelenje/odelenje.module';
import { ManProdRadionicaModule } from './radionica/radionica.module';
import { ManProdRadnoMestoModule } from './radno-mesto/radno-mesto.module';
import { ManProdObradniSistemModule } from './obradni-sistem/obradni-sistem.module';
import { ManProdMasinaModule } from './masina/masina.module';
import { ManProdOpremaModule } from './oprema/oprema.module';
import { ManProdMagaciniModule } from './magacini/magacini.module';
import { ManProdStavkeUMagacinuModule } from './stavke-u-magacinu/stavke-u-magacinu.module';
import { ManProdTransakcijeUMagacinuModule } from './transakcije-u-magacinu/transakcije-u-magacinu.module';
import { ManProdDobavljacModule } from './dobavljac/dobavljac.module';
import { ManProdAlatiModule } from './alati/alati.module';
import { ManProdPriboriModule } from './pribori/pribori.module';
import { ManProdMerniAlatiModule } from './merni-alati/merni-alati.module';
import { ManProdPartnerModule } from './partner/partner.module';
import { ManProdFinalniProizvodModule } from './finalni-proizvod/finalni-proizvod.module';
import { ManProdPoluproizvodModule } from './poluproizvod/poluproizvod.module';
import { ManProdKupovniProizvodModule } from './kupovni-proizvod/kupovni-proizvod.module';
import { ManProdPomocniProizvodModule } from './pomocni-proizvod/pomocni-proizvod.module';
import { ManProdSirovineModule } from './sirovine/sirovine.module';
import { ManProdKupovniMaterijalModule } from './kupovni-materijal/kupovni-materijal.module';
import { ManProdKomponeneteModule } from './komponenete/komponenete.module';
import { ManProdPotrosniMaterijalModule } from './potrosni-materijal/potrosni-materijal.module';
import { ManProdPomocniMaterijalModule } from './pomocni-materijal/pomocni-materijal.module';
import { ManProdOstaliMaterijaliModule } from './ostali-materijali/ostali-materijali.module';
import { ManProdKonstruktivnaSastavnicaModule } from './konstruktivna-sastavnica/konstruktivna-sastavnica.module';
import { ManProdRadniciModule } from './radnici/radnici.module';
import { ManProdRadniNalogModule } from './radni-nalog/radni-nalog.module';
import { ManProdOperacijeURadnomNaloguModule } from './operacije-u-radnom-nalogu/operacije-u-radnom-nalogu.module';
import { ManProdOperacijeModule } from './operacije/operacije.module';
import { ManProdZahvatiModule } from './zahvati/zahvati.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ManProdProizvodniPogoniModule,
        ManProdProizvodneLinijeModule,
        ManProdOdelenjeModule,
        ManProdRadionicaModule,
        ManProdRadnoMestoModule,
        ManProdObradniSistemModule,
        ManProdMasinaModule,
        ManProdOpremaModule,
        ManProdMagaciniModule,
        ManProdStavkeUMagacinuModule,
        ManProdTransakcijeUMagacinuModule,
        ManProdDobavljacModule,
        ManProdAlatiModule,
        ManProdPriboriModule,
        ManProdMerniAlatiModule,
        ManProdPartnerModule,
        ManProdFinalniProizvodModule,
        ManProdPoluproizvodModule,
        ManProdKupovniProizvodModule,
        ManProdPomocniProizvodModule,
        ManProdSirovineModule,
        ManProdKupovniMaterijalModule,
        ManProdKomponeneteModule,
        ManProdPotrosniMaterijalModule,
        ManProdPomocniMaterijalModule,
        ManProdOstaliMaterijaliModule,
        ManProdKonstruktivnaSastavnicaModule,
        ManProdRadniciModule,
        ManProdRadniNalogModule,
        ManProdOperacijeURadnomNaloguModule,
        ManProdOperacijeModule,
        ManProdZahvatiModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdEntityModule {}
