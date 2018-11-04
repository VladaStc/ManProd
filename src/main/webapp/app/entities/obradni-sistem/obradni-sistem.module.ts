import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    ObradniSistemComponent,
    ObradniSistemDetailComponent,
    ObradniSistemUpdateComponent,
    ObradniSistemDeletePopupComponent,
    ObradniSistemDeleteDialogComponent,
    obradniSistemRoute,
    obradniSistemPopupRoute
} from './';

const ENTITY_STATES = [...obradniSistemRoute, ...obradniSistemPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ObradniSistemComponent,
        ObradniSistemDetailComponent,
        ObradniSistemUpdateComponent,
        ObradniSistemDeleteDialogComponent,
        ObradniSistemDeletePopupComponent
    ],
    entryComponents: [
        ObradniSistemComponent,
        ObradniSistemUpdateComponent,
        ObradniSistemDeleteDialogComponent,
        ObradniSistemDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdObradniSistemModule {}
