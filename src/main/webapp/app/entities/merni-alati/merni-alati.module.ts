import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    MerniAlatiComponent,
    MerniAlatiDetailComponent,
    MerniAlatiUpdateComponent,
    MerniAlatiDeletePopupComponent,
    MerniAlatiDeleteDialogComponent,
    merniAlatiRoute,
    merniAlatiPopupRoute
} from './';

const ENTITY_STATES = [...merniAlatiRoute, ...merniAlatiPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MerniAlatiComponent,
        MerniAlatiDetailComponent,
        MerniAlatiUpdateComponent,
        MerniAlatiDeleteDialogComponent,
        MerniAlatiDeletePopupComponent
    ],
    entryComponents: [MerniAlatiComponent, MerniAlatiUpdateComponent, MerniAlatiDeleteDialogComponent, MerniAlatiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdMerniAlatiModule {}
