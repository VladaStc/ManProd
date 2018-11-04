import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    MasinaComponent,
    MasinaDetailComponent,
    MasinaUpdateComponent,
    MasinaDeletePopupComponent,
    MasinaDeleteDialogComponent,
    masinaRoute,
    masinaPopupRoute
} from './';

const ENTITY_STATES = [...masinaRoute, ...masinaPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [MasinaComponent, MasinaDetailComponent, MasinaUpdateComponent, MasinaDeleteDialogComponent, MasinaDeletePopupComponent],
    entryComponents: [MasinaComponent, MasinaUpdateComponent, MasinaDeleteDialogComponent, MasinaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdMasinaModule {}
