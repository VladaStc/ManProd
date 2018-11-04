import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    OperacijeURadnomNaloguComponent,
    OperacijeURadnomNaloguDetailComponent,
    OperacijeURadnomNaloguUpdateComponent,
    OperacijeURadnomNaloguDeletePopupComponent,
    OperacijeURadnomNaloguDeleteDialogComponent,
    operacijeURadnomNaloguRoute,
    operacijeURadnomNaloguPopupRoute
} from './';

const ENTITY_STATES = [...operacijeURadnomNaloguRoute, ...operacijeURadnomNaloguPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OperacijeURadnomNaloguComponent,
        OperacijeURadnomNaloguDetailComponent,
        OperacijeURadnomNaloguUpdateComponent,
        OperacijeURadnomNaloguDeleteDialogComponent,
        OperacijeURadnomNaloguDeletePopupComponent
    ],
    entryComponents: [
        OperacijeURadnomNaloguComponent,
        OperacijeURadnomNaloguUpdateComponent,
        OperacijeURadnomNaloguDeleteDialogComponent,
        OperacijeURadnomNaloguDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdOperacijeURadnomNaloguModule {}
