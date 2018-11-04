import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    PotrosniMaterijalComponent,
    PotrosniMaterijalDetailComponent,
    PotrosniMaterijalUpdateComponent,
    PotrosniMaterijalDeletePopupComponent,
    PotrosniMaterijalDeleteDialogComponent,
    potrosniMaterijalRoute,
    potrosniMaterijalPopupRoute
} from './';

const ENTITY_STATES = [...potrosniMaterijalRoute, ...potrosniMaterijalPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PotrosniMaterijalComponent,
        PotrosniMaterijalDetailComponent,
        PotrosniMaterijalUpdateComponent,
        PotrosniMaterijalDeleteDialogComponent,
        PotrosniMaterijalDeletePopupComponent
    ],
    entryComponents: [
        PotrosniMaterijalComponent,
        PotrosniMaterijalUpdateComponent,
        PotrosniMaterijalDeleteDialogComponent,
        PotrosniMaterijalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdPotrosniMaterijalModule {}
