import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    DobavljacComponent,
    DobavljacDetailComponent,
    DobavljacUpdateComponent,
    DobavljacDeletePopupComponent,
    DobavljacDeleteDialogComponent,
    dobavljacRoute,
    dobavljacPopupRoute
} from './';

const ENTITY_STATES = [...dobavljacRoute, ...dobavljacPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DobavljacComponent,
        DobavljacDetailComponent,
        DobavljacUpdateComponent,
        DobavljacDeleteDialogComponent,
        DobavljacDeletePopupComponent
    ],
    entryComponents: [DobavljacComponent, DobavljacUpdateComponent, DobavljacDeleteDialogComponent, DobavljacDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdDobavljacModule {}
