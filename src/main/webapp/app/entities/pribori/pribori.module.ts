import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    PriboriComponent,
    PriboriDetailComponent,
    PriboriUpdateComponent,
    PriboriDeletePopupComponent,
    PriboriDeleteDialogComponent,
    priboriRoute,
    priboriPopupRoute
} from './';

const ENTITY_STATES = [...priboriRoute, ...priboriPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PriboriComponent,
        PriboriDetailComponent,
        PriboriUpdateComponent,
        PriboriDeleteDialogComponent,
        PriboriDeletePopupComponent
    ],
    entryComponents: [PriboriComponent, PriboriUpdateComponent, PriboriDeleteDialogComponent, PriboriDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdPriboriModule {}
