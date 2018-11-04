import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    AlatiComponent,
    AlatiDetailComponent,
    AlatiUpdateComponent,
    AlatiDeletePopupComponent,
    AlatiDeleteDialogComponent,
    alatiRoute,
    alatiPopupRoute
} from './';

const ENTITY_STATES = [...alatiRoute, ...alatiPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [AlatiComponent, AlatiDetailComponent, AlatiUpdateComponent, AlatiDeleteDialogComponent, AlatiDeletePopupComponent],
    entryComponents: [AlatiComponent, AlatiUpdateComponent, AlatiDeleteDialogComponent, AlatiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdAlatiModule {}
