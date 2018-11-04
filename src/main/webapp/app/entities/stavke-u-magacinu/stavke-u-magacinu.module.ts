import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    StavkeUMagacinuComponent,
    StavkeUMagacinuDetailComponent,
    StavkeUMagacinuUpdateComponent,
    StavkeUMagacinuDeletePopupComponent,
    StavkeUMagacinuDeleteDialogComponent,
    stavkeUMagacinuRoute,
    stavkeUMagacinuPopupRoute
} from './';

const ENTITY_STATES = [...stavkeUMagacinuRoute, ...stavkeUMagacinuPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StavkeUMagacinuComponent,
        StavkeUMagacinuDetailComponent,
        StavkeUMagacinuUpdateComponent,
        StavkeUMagacinuDeleteDialogComponent,
        StavkeUMagacinuDeletePopupComponent
    ],
    entryComponents: [
        StavkeUMagacinuComponent,
        StavkeUMagacinuUpdateComponent,
        StavkeUMagacinuDeleteDialogComponent,
        StavkeUMagacinuDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdStavkeUMagacinuModule {}
