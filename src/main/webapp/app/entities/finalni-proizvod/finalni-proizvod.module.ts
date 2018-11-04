import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    FinalniProizvodComponent,
    FinalniProizvodDetailComponent,
    FinalniProizvodUpdateComponent,
    FinalniProizvodDeletePopupComponent,
    FinalniProizvodDeleteDialogComponent,
    finalniProizvodRoute,
    finalniProizvodPopupRoute
} from './';

const ENTITY_STATES = [...finalniProizvodRoute, ...finalniProizvodPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FinalniProizvodComponent,
        FinalniProizvodDetailComponent,
        FinalniProizvodUpdateComponent,
        FinalniProizvodDeleteDialogComponent,
        FinalniProizvodDeletePopupComponent
    ],
    entryComponents: [
        FinalniProizvodComponent,
        FinalniProizvodUpdateComponent,
        FinalniProizvodDeleteDialogComponent,
        FinalniProizvodDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdFinalniProizvodModule {}
