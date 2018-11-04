import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    SirovineComponent,
    SirovineDetailComponent,
    SirovineUpdateComponent,
    SirovineDeletePopupComponent,
    SirovineDeleteDialogComponent,
    sirovineRoute,
    sirovinePopupRoute
} from './';

const ENTITY_STATES = [...sirovineRoute, ...sirovinePopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SirovineComponent,
        SirovineDetailComponent,
        SirovineUpdateComponent,
        SirovineDeleteDialogComponent,
        SirovineDeletePopupComponent
    ],
    entryComponents: [SirovineComponent, SirovineUpdateComponent, SirovineDeleteDialogComponent, SirovineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdSirovineModule {}
