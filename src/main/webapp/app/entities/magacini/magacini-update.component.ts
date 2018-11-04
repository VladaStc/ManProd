import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMagacini } from 'app/shared/model/magacini.model';
import { MagaciniService } from './magacini.service';
import { IStavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';
import { StavkeUMagacinuService } from 'app/entities/stavke-u-magacinu';
import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';
import { TransakcijeUMagacinuService } from 'app/entities/transakcije-u-magacinu';
import { IAlati } from 'app/shared/model/alati.model';
import { AlatiService } from 'app/entities/alati';
import { IPribori } from 'app/shared/model/pribori.model';
import { PriboriService } from 'app/entities/pribori';
import { IMerniAlati } from 'app/shared/model/merni-alati.model';
import { MerniAlatiService } from 'app/entities/merni-alati';
import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';
import { FinalniProizvodService } from 'app/entities/finalni-proizvod';
import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';
import { PoluproizvodService } from 'app/entities/poluproizvod';
import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';
import { KupovniProizvodService } from 'app/entities/kupovni-proizvod';
import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';
import { PomocniProizvodService } from 'app/entities/pomocni-proizvod';
import { ISirovine } from 'app/shared/model/sirovine.model';
import { SirovineService } from 'app/entities/sirovine';
import { IKupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';
import { KupovniMaterijalService } from 'app/entities/kupovni-materijal';
import { IKomponenete } from 'app/shared/model/komponenete.model';
import { KomponeneteService } from 'app/entities/komponenete';
import { IPotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';
import { PotrosniMaterijalService } from 'app/entities/potrosni-materijal';
import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';
import { OstaliMaterijaliService } from 'app/entities/ostali-materijali';
import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';
import { KonstruktivnaSastavnicaService } from 'app/entities/konstruktivna-sastavnica';

@Component({
    selector: 'jhi-magacini-update',
    templateUrl: './magacini-update.component.html'
})
export class MagaciniUpdateComponent implements OnInit {
    magacini: IMagacini;
    isSaving: boolean;

    stavkeumagacinus: IStavkeUMagacinu[];

    tansakcijeumagacinus: ITransakcijeUMagacinu[];

    alatis: IAlati[];

    priboris: IPribori[];

    mernialatis: IMerniAlati[];

    finalniproizvods: IFinalniProizvod[];

    poluproizvods: IPoluproizvod[];

    kupovniproizvods: IKupovniProizvod[];

    pomocniproizvods: IPomocniProizvod[];

    sirovines: ISirovine[];

    kupovnimaterijals: IKupovniMaterijal[];

    komponentes: IKomponenete[];

    potrosnimaterijals: IPotrosniMaterijal[];

    ostalimaterijalis: IOstaliMaterijali[];

    konstruktivnasastavnicas: IKonstruktivnaSastavnica[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private magaciniService: MagaciniService,
        private stavkeUMagacinuService: StavkeUMagacinuService,
        private transakcijeUMagacinuService: TransakcijeUMagacinuService,
        private alatiService: AlatiService,
        private priboriService: PriboriService,
        private merniAlatiService: MerniAlatiService,
        private finalniProizvodService: FinalniProizvodService,
        private poluproizvodService: PoluproizvodService,
        private kupovniProizvodService: KupovniProizvodService,
        private pomocniProizvodService: PomocniProizvodService,
        private sirovineService: SirovineService,
        private kupovniMaterijalService: KupovniMaterijalService,
        private komponeneteService: KomponeneteService,
        private potrosniMaterijalService: PotrosniMaterijalService,
        private ostaliMaterijaliService: OstaliMaterijaliService,
        private konstruktivnaSastavnicaService: KonstruktivnaSastavnicaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ magacini }) => {
            this.magacini = magacini;
        });
        this.stavkeUMagacinuService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IStavkeUMagacinu[]>) => {
                if (!this.magacini.stavkeUMagacinu || !this.magacini.stavkeUMagacinu.id) {
                    this.stavkeumagacinus = res.body;
                } else {
                    this.stavkeUMagacinuService.find(this.magacini.stavkeUMagacinu.id).subscribe(
                        (subRes: HttpResponse<IStavkeUMagacinu>) => {
                            this.stavkeumagacinus = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.transakcijeUMagacinuService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<ITransakcijeUMagacinu[]>) => {
                if (!this.magacini.tansakcijeUMagacinu || !this.magacini.tansakcijeUMagacinu.id) {
                    this.tansakcijeumagacinus = res.body;
                } else {
                    this.transakcijeUMagacinuService.find(this.magacini.tansakcijeUMagacinu.id).subscribe(
                        (subRes: HttpResponse<ITransakcijeUMagacinu>) => {
                            this.tansakcijeumagacinus = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.alatiService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IAlati[]>) => {
                if (!this.magacini.alati || !this.magacini.alati.id) {
                    this.alatis = res.body;
                } else {
                    this.alatiService.find(this.magacini.alati.id).subscribe(
                        (subRes: HttpResponse<IAlati>) => {
                            this.alatis = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.priboriService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IPribori[]>) => {
                if (!this.magacini.pribori || !this.magacini.pribori.id) {
                    this.priboris = res.body;
                } else {
                    this.priboriService.find(this.magacini.pribori.id).subscribe(
                        (subRes: HttpResponse<IPribori>) => {
                            this.priboris = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.merniAlatiService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IMerniAlati[]>) => {
                if (!this.magacini.merniAlati || !this.magacini.merniAlati.id) {
                    this.mernialatis = res.body;
                } else {
                    this.merniAlatiService.find(this.magacini.merniAlati.id).subscribe(
                        (subRes: HttpResponse<IMerniAlati>) => {
                            this.mernialatis = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.finalniProizvodService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IFinalniProizvod[]>) => {
                if (!this.magacini.finalniProizvod || !this.magacini.finalniProizvod.id) {
                    this.finalniproizvods = res.body;
                } else {
                    this.finalniProizvodService.find(this.magacini.finalniProizvod.id).subscribe(
                        (subRes: HttpResponse<IFinalniProizvod>) => {
                            this.finalniproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.poluproizvodService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IPoluproizvod[]>) => {
                if (!this.magacini.poluproizvod || !this.magacini.poluproizvod.id) {
                    this.poluproizvods = res.body;
                } else {
                    this.poluproizvodService.find(this.magacini.poluproizvod.id).subscribe(
                        (subRes: HttpResponse<IPoluproizvod>) => {
                            this.poluproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.kupovniProizvodService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IKupovniProizvod[]>) => {
                if (!this.magacini.kupovniProizvod || !this.magacini.kupovniProizvod.id) {
                    this.kupovniproizvods = res.body;
                } else {
                    this.kupovniProizvodService.find(this.magacini.kupovniProizvod.id).subscribe(
                        (subRes: HttpResponse<IKupovniProizvod>) => {
                            this.kupovniproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pomocniProizvodService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IPomocniProizvod[]>) => {
                if (!this.magacini.pomocniProizvod || !this.magacini.pomocniProizvod.id) {
                    this.pomocniproizvods = res.body;
                } else {
                    this.pomocniProizvodService.find(this.magacini.pomocniProizvod.id).subscribe(
                        (subRes: HttpResponse<IPomocniProizvod>) => {
                            this.pomocniproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sirovineService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<ISirovine[]>) => {
                if (!this.magacini.sirovine || !this.magacini.sirovine.id) {
                    this.sirovines = res.body;
                } else {
                    this.sirovineService.find(this.magacini.sirovine.id).subscribe(
                        (subRes: HttpResponse<ISirovine>) => {
                            this.sirovines = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.kupovniMaterijalService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IKupovniMaterijal[]>) => {
                if (!this.magacini.kupovniMaterijal || !this.magacini.kupovniMaterijal.id) {
                    this.kupovnimaterijals = res.body;
                } else {
                    this.kupovniMaterijalService.find(this.magacini.kupovniMaterijal.id).subscribe(
                        (subRes: HttpResponse<IKupovniMaterijal>) => {
                            this.kupovnimaterijals = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.komponeneteService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IKomponenete[]>) => {
                if (!this.magacini.komponente || !this.magacini.komponente.id) {
                    this.komponentes = res.body;
                } else {
                    this.komponeneteService.find(this.magacini.komponente.id).subscribe(
                        (subRes: HttpResponse<IKomponenete>) => {
                            this.komponentes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.potrosniMaterijalService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IPotrosniMaterijal[]>) => {
                if (!this.magacini.potrosniMaterijal || !this.magacini.potrosniMaterijal.id) {
                    this.potrosnimaterijals = res.body;
                } else {
                    this.potrosniMaterijalService.find(this.magacini.potrosniMaterijal.id).subscribe(
                        (subRes: HttpResponse<IPotrosniMaterijal>) => {
                            this.potrosnimaterijals = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.ostaliMaterijaliService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IOstaliMaterijali[]>) => {
                if (!this.magacini.ostaliMaterijali || !this.magacini.ostaliMaterijali.id) {
                    this.ostalimaterijalis = res.body;
                } else {
                    this.ostaliMaterijaliService.find(this.magacini.ostaliMaterijali.id).subscribe(
                        (subRes: HttpResponse<IOstaliMaterijali>) => {
                            this.ostalimaterijalis = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.konstruktivnaSastavnicaService.query({ filter: 'magacini-is-null' }).subscribe(
            (res: HttpResponse<IKonstruktivnaSastavnica[]>) => {
                if (!this.magacini.konstruktivnaSastavnica || !this.magacini.konstruktivnaSastavnica.id) {
                    this.konstruktivnasastavnicas = res.body;
                } else {
                    this.konstruktivnaSastavnicaService.find(this.magacini.konstruktivnaSastavnica.id).subscribe(
                        (subRes: HttpResponse<IKonstruktivnaSastavnica>) => {
                            this.konstruktivnasastavnicas = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.magacini.id !== undefined) {
            this.subscribeToSaveResponse(this.magaciniService.update(this.magacini));
        } else {
            this.subscribeToSaveResponse(this.magaciniService.create(this.magacini));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMagacini>>) {
        result.subscribe((res: HttpResponse<IMagacini>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackStavkeUMagacinuById(index: number, item: IStavkeUMagacinu) {
        return item.id;
    }

    trackTransakcijeUMagacinuById(index: number, item: ITransakcijeUMagacinu) {
        return item.id;
    }

    trackAlatiById(index: number, item: IAlati) {
        return item.id;
    }

    trackPriboriById(index: number, item: IPribori) {
        return item.id;
    }

    trackMerniAlatiById(index: number, item: IMerniAlati) {
        return item.id;
    }

    trackFinalniProizvodById(index: number, item: IFinalniProizvod) {
        return item.id;
    }

    trackPoluproizvodById(index: number, item: IPoluproizvod) {
        return item.id;
    }

    trackKupovniProizvodById(index: number, item: IKupovniProizvod) {
        return item.id;
    }

    trackPomocniProizvodById(index: number, item: IPomocniProizvod) {
        return item.id;
    }

    trackSirovineById(index: number, item: ISirovine) {
        return item.id;
    }

    trackKupovniMaterijalById(index: number, item: IKupovniMaterijal) {
        return item.id;
    }

    trackKomponeneteById(index: number, item: IKomponenete) {
        return item.id;
    }

    trackPotrosniMaterijalById(index: number, item: IPotrosniMaterijal) {
        return item.id;
    }

    trackOstaliMaterijaliById(index: number, item: IOstaliMaterijali) {
        return item.id;
    }

    trackKonstruktivnaSastavnicaById(index: number, item: IKonstruktivnaSastavnica) {
        return item.id;
    }
}
