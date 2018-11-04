import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    KupovniMaterijalComponent,
    KupovniMaterijalDetailComponent,
    KupovniMaterijalUpdateComponent,
    KupovniMaterijalDeletePopupComponent,
    KupovniMaterijalDeleteDialogComponent,
    kupovniMaterijalRoute,
    kupovniMaterijalPopupRoute
} from './';

const ENTITY_STATES = [...kupovniMaterijalRoute, ...kupovniMaterijalPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KupovniMaterijalComponent,
        KupovniMaterijalDetailComponent,
        KupovniMaterijalUpdateComponent,
        KupovniMaterijalDeleteDialogComponent,
        KupovniMaterijalDeletePopupComponent
    ],
    entryComponents: [
        KupovniMaterijalComponent,
        KupovniMaterijalUpdateComponent,
        KupovniMaterijalDeleteDialogComponent,
        KupovniMaterijalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdKupovniMaterijalModule {}
