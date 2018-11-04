import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    ProizvodniPogoniComponent,
    ProizvodniPogoniDetailComponent,
    ProizvodniPogoniUpdateComponent,
    ProizvodniPogoniDeletePopupComponent,
    ProizvodniPogoniDeleteDialogComponent,
    proizvodniPogoniRoute,
    proizvodniPogoniPopupRoute
} from './';

const ENTITY_STATES = [...proizvodniPogoniRoute, ...proizvodniPogoniPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProizvodniPogoniComponent,
        ProizvodniPogoniDetailComponent,
        ProizvodniPogoniUpdateComponent,
        ProizvodniPogoniDeleteDialogComponent,
        ProizvodniPogoniDeletePopupComponent
    ],
    entryComponents: [
        ProizvodniPogoniComponent,
        ProizvodniPogoniUpdateComponent,
        ProizvodniPogoniDeleteDialogComponent,
        ProizvodniPogoniDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdProizvodniPogoniModule {}
