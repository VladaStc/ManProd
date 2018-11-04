import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    ProizvodneLinijeComponent,
    ProizvodneLinijeDetailComponent,
    ProizvodneLinijeUpdateComponent,
    ProizvodneLinijeDeletePopupComponent,
    ProizvodneLinijeDeleteDialogComponent,
    proizvodneLinijeRoute,
    proizvodneLinijePopupRoute
} from './';

const ENTITY_STATES = [...proizvodneLinijeRoute, ...proizvodneLinijePopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProizvodneLinijeComponent,
        ProizvodneLinijeDetailComponent,
        ProizvodneLinijeUpdateComponent,
        ProizvodneLinijeDeleteDialogComponent,
        ProizvodneLinijeDeletePopupComponent
    ],
    entryComponents: [
        ProizvodneLinijeComponent,
        ProizvodneLinijeUpdateComponent,
        ProizvodneLinijeDeleteDialogComponent,
        ProizvodneLinijeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdProizvodneLinijeModule {}
