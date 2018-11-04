import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    OpremaComponent,
    OpremaDetailComponent,
    OpremaUpdateComponent,
    OpremaDeletePopupComponent,
    OpremaDeleteDialogComponent,
    opremaRoute,
    opremaPopupRoute
} from './';

const ENTITY_STATES = [...opremaRoute, ...opremaPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [OpremaComponent, OpremaDetailComponent, OpremaUpdateComponent, OpremaDeleteDialogComponent, OpremaDeletePopupComponent],
    entryComponents: [OpremaComponent, OpremaUpdateComponent, OpremaDeleteDialogComponent, OpremaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdOpremaModule {}
