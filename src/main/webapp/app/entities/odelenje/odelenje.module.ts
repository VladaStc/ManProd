import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    OdelenjeComponent,
    OdelenjeDetailComponent,
    OdelenjeUpdateComponent,
    OdelenjeDeletePopupComponent,
    OdelenjeDeleteDialogComponent,
    odelenjeRoute,
    odelenjePopupRoute
} from './';

const ENTITY_STATES = [...odelenjeRoute, ...odelenjePopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OdelenjeComponent,
        OdelenjeDetailComponent,
        OdelenjeUpdateComponent,
        OdelenjeDeleteDialogComponent,
        OdelenjeDeletePopupComponent
    ],
    entryComponents: [OdelenjeComponent, OdelenjeUpdateComponent, OdelenjeDeleteDialogComponent, OdelenjeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdOdelenjeModule {}
