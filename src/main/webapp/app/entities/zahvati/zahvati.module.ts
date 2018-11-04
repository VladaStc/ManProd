import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    ZahvatiComponent,
    ZahvatiDetailComponent,
    ZahvatiUpdateComponent,
    ZahvatiDeletePopupComponent,
    ZahvatiDeleteDialogComponent,
    zahvatiRoute,
    zahvatiPopupRoute
} from './';

const ENTITY_STATES = [...zahvatiRoute, ...zahvatiPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ZahvatiComponent,
        ZahvatiDetailComponent,
        ZahvatiUpdateComponent,
        ZahvatiDeleteDialogComponent,
        ZahvatiDeletePopupComponent
    ],
    entryComponents: [ZahvatiComponent, ZahvatiUpdateComponent, ZahvatiDeleteDialogComponent, ZahvatiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdZahvatiModule {}
