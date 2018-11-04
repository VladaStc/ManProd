import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    RadniNalogComponent,
    RadniNalogDetailComponent,
    RadniNalogUpdateComponent,
    RadniNalogDeletePopupComponent,
    RadniNalogDeleteDialogComponent,
    radniNalogRoute,
    radniNalogPopupRoute
} from './';

const ENTITY_STATES = [...radniNalogRoute, ...radniNalogPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RadniNalogComponent,
        RadniNalogDetailComponent,
        RadniNalogUpdateComponent,
        RadniNalogDeleteDialogComponent,
        RadniNalogDeletePopupComponent
    ],
    entryComponents: [RadniNalogComponent, RadniNalogUpdateComponent, RadniNalogDeleteDialogComponent, RadniNalogDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdRadniNalogModule {}
