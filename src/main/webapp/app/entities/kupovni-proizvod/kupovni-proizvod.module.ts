import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    KupovniProizvodComponent,
    KupovniProizvodDetailComponent,
    KupovniProizvodUpdateComponent,
    KupovniProizvodDeletePopupComponent,
    KupovniProizvodDeleteDialogComponent,
    kupovniProizvodRoute,
    kupovniProizvodPopupRoute
} from './';

const ENTITY_STATES = [...kupovniProizvodRoute, ...kupovniProizvodPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KupovniProizvodComponent,
        KupovniProizvodDetailComponent,
        KupovniProizvodUpdateComponent,
        KupovniProizvodDeleteDialogComponent,
        KupovniProizvodDeletePopupComponent
    ],
    entryComponents: [
        KupovniProizvodComponent,
        KupovniProizvodUpdateComponent,
        KupovniProizvodDeleteDialogComponent,
        KupovniProizvodDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdKupovniProizvodModule {}
