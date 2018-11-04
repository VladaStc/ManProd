import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    RadionicaComponent,
    RadionicaDetailComponent,
    RadionicaUpdateComponent,
    RadionicaDeletePopupComponent,
    RadionicaDeleteDialogComponent,
    radionicaRoute,
    radionicaPopupRoute
} from './';

const ENTITY_STATES = [...radionicaRoute, ...radionicaPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RadionicaComponent,
        RadionicaDetailComponent,
        RadionicaUpdateComponent,
        RadionicaDeleteDialogComponent,
        RadionicaDeletePopupComponent
    ],
    entryComponents: [RadionicaComponent, RadionicaUpdateComponent, RadionicaDeleteDialogComponent, RadionicaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdRadionicaModule {}
