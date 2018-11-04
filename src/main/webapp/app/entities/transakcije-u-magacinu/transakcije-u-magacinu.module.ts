import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ManProdSharedModule } from 'app/shared';
import {
    TransakcijeUMagacinuComponent,
    TransakcijeUMagacinuDetailComponent,
    TransakcijeUMagacinuUpdateComponent,
    TransakcijeUMagacinuDeletePopupComponent,
    TransakcijeUMagacinuDeleteDialogComponent,
    transakcijeUMagacinuRoute,
    transakcijeUMagacinuPopupRoute
} from './';

const ENTITY_STATES = [...transakcijeUMagacinuRoute, ...transakcijeUMagacinuPopupRoute];

@NgModule({
    imports: [ManProdSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransakcijeUMagacinuComponent,
        TransakcijeUMagacinuDetailComponent,
        TransakcijeUMagacinuUpdateComponent,
        TransakcijeUMagacinuDeleteDialogComponent,
        TransakcijeUMagacinuDeletePopupComponent
    ],
    entryComponents: [
        TransakcijeUMagacinuComponent,
        TransakcijeUMagacinuUpdateComponent,
        TransakcijeUMagacinuDeleteDialogComponent,
        TransakcijeUMagacinuDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManProdTransakcijeUMagacinuModule {}
