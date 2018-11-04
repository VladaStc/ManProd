import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    PoluproizvodComponent,
    PoluproizvodDetailComponent,
    PoluproizvodUpdateComponent,
    PoluproizvodDeletePopupComponent,
    PoluproizvodDeleteDialogComponent,
    poluproizvodRoute,
    poluproizvodPopupRoute
} from './';

const ENTITY_STATES = [...poluproizvodRoute, ...poluproizvodPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PoluproizvodComponent,
        PoluproizvodDetailComponent,
        PoluproizvodUpdateComponent,
        PoluproizvodDeleteDialogComponent,
        PoluproizvodDeletePopupComponent
    ],
    entryComponents: [
        PoluproizvodComponent,
        PoluproizvodUpdateComponent,
        PoluproizvodDeleteDialogComponent,
        PoluproizvodDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdPoluproizvodModule {}
