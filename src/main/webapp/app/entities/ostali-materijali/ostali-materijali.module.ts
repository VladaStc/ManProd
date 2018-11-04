import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    OstaliMaterijaliComponent,
    OstaliMaterijaliDetailComponent,
    OstaliMaterijaliUpdateComponent,
    OstaliMaterijaliDeletePopupComponent,
    OstaliMaterijaliDeleteDialogComponent,
    ostaliMaterijaliRoute,
    ostaliMaterijaliPopupRoute
} from './';

const ENTITY_STATES = [...ostaliMaterijaliRoute, ...ostaliMaterijaliPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OstaliMaterijaliComponent,
        OstaliMaterijaliDetailComponent,
        OstaliMaterijaliUpdateComponent,
        OstaliMaterijaliDeleteDialogComponent,
        OstaliMaterijaliDeletePopupComponent
    ],
    entryComponents: [
        OstaliMaterijaliComponent,
        OstaliMaterijaliUpdateComponent,
        OstaliMaterijaliDeleteDialogComponent,
        OstaliMaterijaliDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdOstaliMaterijaliModule {}
