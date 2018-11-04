import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    MagaciniComponent,
    MagaciniDetailComponent,
    MagaciniUpdateComponent,
    MagaciniDeletePopupComponent,
    MagaciniDeleteDialogComponent,
    magaciniRoute,
    magaciniPopupRoute
} from './';

const ENTITY_STATES = [...magaciniRoute, ...magaciniPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MagaciniComponent,
        MagaciniDetailComponent,
        MagaciniUpdateComponent,
        MagaciniDeleteDialogComponent,
        MagaciniDeletePopupComponent
    ],
    entryComponents: [MagaciniComponent, MagaciniUpdateComponent, MagaciniDeleteDialogComponent, MagaciniDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdMagaciniModule {}
