import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    KonstruktivnaSastavnicaComponent,
    KonstruktivnaSastavnicaDetailComponent,
    KonstruktivnaSastavnicaUpdateComponent,
    KonstruktivnaSastavnicaDeletePopupComponent,
    KonstruktivnaSastavnicaDeleteDialogComponent,
    konstruktivnaSastavnicaRoute,
    konstruktivnaSastavnicaPopupRoute
} from './';

const ENTITY_STATES = [...konstruktivnaSastavnicaRoute, ...konstruktivnaSastavnicaPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KonstruktivnaSastavnicaComponent,
        KonstruktivnaSastavnicaDetailComponent,
        KonstruktivnaSastavnicaUpdateComponent,
        KonstruktivnaSastavnicaDeleteDialogComponent,
        KonstruktivnaSastavnicaDeletePopupComponent
    ],
    entryComponents: [
        KonstruktivnaSastavnicaComponent,
        KonstruktivnaSastavnicaUpdateComponent,
        KonstruktivnaSastavnicaDeleteDialogComponent,
        KonstruktivnaSastavnicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdKonstruktivnaSastavnicaModule {}
