import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    PomocniProizvodComponent,
    PomocniProizvodDetailComponent,
    PomocniProizvodUpdateComponent,
    PomocniProizvodDeletePopupComponent,
    PomocniProizvodDeleteDialogComponent,
    pomocniProizvodRoute,
    pomocniProizvodPopupRoute
} from './';

const ENTITY_STATES = [...pomocniProizvodRoute, ...pomocniProizvodPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PomocniProizvodComponent,
        PomocniProizvodDetailComponent,
        PomocniProizvodUpdateComponent,
        PomocniProizvodDeleteDialogComponent,
        PomocniProizvodDeletePopupComponent
    ],
    entryComponents: [
        PomocniProizvodComponent,
        PomocniProizvodUpdateComponent,
        PomocniProizvodDeleteDialogComponent,
        PomocniProizvodDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdPomocniProizvodModule {}
