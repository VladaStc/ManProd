import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    PomocniMaterijalComponent,
    PomocniMaterijalDetailComponent,
    PomocniMaterijalUpdateComponent,
    PomocniMaterijalDeletePopupComponent,
    PomocniMaterijalDeleteDialogComponent,
    pomocniMaterijalRoute,
    pomocniMaterijalPopupRoute
} from './';

const ENTITY_STATES = [...pomocniMaterijalRoute, ...pomocniMaterijalPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PomocniMaterijalComponent,
        PomocniMaterijalDetailComponent,
        PomocniMaterijalUpdateComponent,
        PomocniMaterijalDeleteDialogComponent,
        PomocniMaterijalDeletePopupComponent
    ],
    entryComponents: [
        PomocniMaterijalComponent,
        PomocniMaterijalUpdateComponent,
        PomocniMaterijalDeleteDialogComponent,
        PomocniMaterijalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdPomocniMaterijalModule {}
