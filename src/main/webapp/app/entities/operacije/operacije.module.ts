import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    OperacijeComponent,
    OperacijeDetailComponent,
    OperacijeUpdateComponent,
    OperacijeDeletePopupComponent,
    OperacijeDeleteDialogComponent,
    operacijeRoute,
    operacijePopupRoute
} from './';

const ENTITY_STATES = [...operacijeRoute, ...operacijePopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OperacijeComponent,
        OperacijeDetailComponent,
        OperacijeUpdateComponent,
        OperacijeDeleteDialogComponent,
        OperacijeDeletePopupComponent
    ],
    entryComponents: [OperacijeComponent, OperacijeUpdateComponent, OperacijeDeleteDialogComponent, OperacijeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdOperacijeModule {}
