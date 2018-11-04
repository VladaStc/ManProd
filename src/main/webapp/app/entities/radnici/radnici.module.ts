import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    RadniciComponent,
    RadniciDetailComponent,
    RadniciUpdateComponent,
    RadniciDeletePopupComponent,
    RadniciDeleteDialogComponent,
    radniciRoute,
    radniciPopupRoute
} from './';

const ENTITY_STATES = [...radniciRoute, ...radniciPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RadniciComponent,
        RadniciDetailComponent,
        RadniciUpdateComponent,
        RadniciDeleteDialogComponent,
        RadniciDeletePopupComponent
    ],
    entryComponents: [RadniciComponent, RadniciUpdateComponent, RadniciDeleteDialogComponent, RadniciDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdRadniciModule {}
