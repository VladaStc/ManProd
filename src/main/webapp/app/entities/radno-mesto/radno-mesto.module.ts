import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    RadnoMestoComponent,
    RadnoMestoDetailComponent,
    RadnoMestoUpdateComponent,
    RadnoMestoDeletePopupComponent,
    RadnoMestoDeleteDialogComponent,
    radnoMestoRoute,
    radnoMestoPopupRoute
} from './';

const ENTITY_STATES = [...radnoMestoRoute, ...radnoMestoPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RadnoMestoComponent,
        RadnoMestoDetailComponent,
        RadnoMestoUpdateComponent,
        RadnoMestoDeleteDialogComponent,
        RadnoMestoDeletePopupComponent
    ],
    entryComponents: [RadnoMestoComponent, RadnoMestoUpdateComponent, RadnoMestoDeleteDialogComponent, RadnoMestoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdRadnoMestoModule {}
