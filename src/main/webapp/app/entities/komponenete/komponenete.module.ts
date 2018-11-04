import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    KomponeneteComponent,
    KomponeneteDetailComponent,
    KomponeneteUpdateComponent,
    KomponeneteDeletePopupComponent,
    KomponeneteDeleteDialogComponent,
    komponeneteRoute,
    komponenetePopupRoute
} from './';

const ENTITY_STATES = [...komponeneteRoute, ...komponenetePopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KomponeneteComponent,
        KomponeneteDetailComponent,
        KomponeneteUpdateComponent,
        KomponeneteDeleteDialogComponent,
        KomponeneteDeletePopupComponent
    ],
    entryComponents: [KomponeneteComponent, KomponeneteUpdateComponent, KomponeneteDeleteDialogComponent, KomponeneteDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdKomponeneteModule {}
