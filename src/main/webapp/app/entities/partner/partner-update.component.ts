import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPartner } from 'app/shared/model/partner.model';
import { PartnerService } from './partner.service';
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

@Component({
    selector: 'jhi-partner-update',
    templateUrl: './partner-update.component.html'
})
export class PartnerUpdateComponent implements OnInit {
    partner: IPartner;
    isSaving: boolean;

    finalniproizvods: IFinalniProizvod[];

    poluproizvods: IPoluproizvod[];

    kupovniproizvods: IKupovniProizvod[];

    pomocniproizvods: IPomocniProizvod[];

    sirovines: ISirovine[];

    kupovnimaterijals: IKupovniMaterijal[];

    komponentes: IKomponenete[];

    potrosnimaterijals: IPotrosniMaterijal[];

    ostalimaterijalis: IOstaliMaterijali[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private partnerService: PartnerService,
        private finalniProizvodService: FinalniProizvodService,
        private poluproizvodService: PoluproizvodService,
        private kupovniProizvodService: KupovniProizvodService,
        private pomocniProizvodService: PomocniProizvodService,
        private sirovineService: SirovineService,
        private kupovniMaterijalService: KupovniMaterijalService,
        private komponeneteService: KomponeneteService,
        private potrosniMaterijalService: PotrosniMaterijalService,
        private ostaliMaterijaliService: OstaliMaterijaliService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ partner }) => {
            this.partner = partner;
        });
        this.finalniProizvodService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IFinalniProizvod[]>) => {
                if (!this.partner.finalniProizvod || !this.partner.finalniProizvod.id) {
                    this.finalniproizvods = res.body;
                } else {
                    this.finalniProizvodService.find(this.partner.finalniProizvod.id).subscribe(
                        (subRes: HttpResponse<IFinalniProizvod>) => {
                            this.finalniproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.poluproizvodService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IPoluproizvod[]>) => {
                if (!this.partner.poluproizvod || !this.partner.poluproizvod.id) {
                    this.poluproizvods = res.body;
                } else {
                    this.poluproizvodService.find(this.partner.poluproizvod.id).subscribe(
                        (subRes: HttpResponse<IPoluproizvod>) => {
                            this.poluproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.kupovniProizvodService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IKupovniProizvod[]>) => {
                if (!this.partner.kupovniProizvod || !this.partner.kupovniProizvod.id) {
                    this.kupovniproizvods = res.body;
                } else {
                    this.kupovniProizvodService.find(this.partner.kupovniProizvod.id).subscribe(
                        (subRes: HttpResponse<IKupovniProizvod>) => {
                            this.kupovniproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pomocniProizvodService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IPomocniProizvod[]>) => {
                if (!this.partner.pomocniProizvod || !this.partner.pomocniProizvod.id) {
                    this.pomocniproizvods = res.body;
                } else {
                    this.pomocniProizvodService.find(this.partner.pomocniProizvod.id).subscribe(
                        (subRes: HttpResponse<IPomocniProizvod>) => {
                            this.pomocniproizvods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.sirovineService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<ISirovine[]>) => {
                if (!this.partner.sirovine || !this.partner.sirovine.id) {
                    this.sirovines = res.body;
                } else {
                    this.sirovineService.find(this.partner.sirovine.id).subscribe(
                        (subRes: HttpResponse<ISirovine>) => {
                            this.sirovines = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.kupovniMaterijalService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IKupovniMaterijal[]>) => {
                if (!this.partner.kupovniMaterijal || !this.partner.kupovniMaterijal.id) {
                    this.kupovnimaterijals = res.body;
                } else {
                    this.kupovniMaterijalService.find(this.partner.kupovniMaterijal.id).subscribe(
                        (subRes: HttpResponse<IKupovniMaterijal>) => {
                            this.kupovnimaterijals = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.komponeneteService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IKomponenete[]>) => {
                if (!this.partner.komponente || !this.partner.komponente.id) {
                    this.komponentes = res.body;
                } else {
                    this.komponeneteService.find(this.partner.komponente.id).subscribe(
                        (subRes: HttpResponse<IKomponenete>) => {
                            this.komponentes = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.potrosniMaterijalService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IPotrosniMaterijal[]>) => {
                if (!this.partner.potrosniMaterijal || !this.partner.potrosniMaterijal.id) {
                    this.potrosnimaterijals = res.body;
                } else {
                    this.potrosniMaterijalService.find(this.partner.potrosniMaterijal.id).subscribe(
                        (subRes: HttpResponse<IPotrosniMaterijal>) => {
                            this.potrosnimaterijals = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.ostaliMaterijaliService.query({ filter: 'partner-is-null' }).subscribe(
            (res: HttpResponse<IOstaliMaterijali[]>) => {
                if (!this.partner.ostaliMaterijali || !this.partner.ostaliMaterijali.id) {
                    this.ostalimaterijalis = res.body;
                } else {
                    this.ostaliMaterijaliService.find(this.partner.ostaliMaterijali.id).subscribe(
                        (subRes: HttpResponse<IOstaliMaterijali>) => {
                            this.ostalimaterijalis = [subRes.body].concat(res.body);
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
        if (this.partner.id !== undefined) {
            this.subscribeToSaveResponse(this.partnerService.update(this.partner));
        } else {
            this.subscribeToSaveResponse(this.partnerService.create(this.partner));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPartner>>) {
        result.subscribe((res: HttpResponse<IPartner>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
