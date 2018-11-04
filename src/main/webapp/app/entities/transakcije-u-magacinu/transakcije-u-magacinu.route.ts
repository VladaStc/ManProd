import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';
import { TransakcijeUMagacinuService } from './transakcije-u-magacinu.service';
import { TransakcijeUMagacinuComponent } from './transakcije-u-magacinu.component';
import { TransakcijeUMagacinuDetailComponent } from './transakcije-u-magacinu-detail.component';
import { TransakcijeUMagacinuUpdateComponent } from './transakcije-u-magacinu-update.component';
import { TransakcijeUMagacinuDeletePopupComponent } from './transakcije-u-magacinu-delete-dialog.component';
import { ITransakcijeUMagacinu } from 'app/shared/model/transakcije-u-magacinu.model';

@Injectable({ providedIn: 'root' })
export class TransakcijeUMagacinuResolve implements Resolve<ITransakcijeUMagacinu> {
    constructor(private service: TransakcijeUMagacinuService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TransakcijeUMagacinu> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TransakcijeUMagacinu>) => response.ok),
                map((transakcijeUMagacinu: HttpResponse<TransakcijeUMagacinu>) => transakcijeUMagacinu.body)
            );
        }
        return of(new TransakcijeUMagacinu());
    }
}

export const transakcijeUMagacinuRoute: Routes = [
    {
        path: 'transakcije-u-magacinu',
        component: TransakcijeUMagacinuComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.transakcijeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transakcije-u-magacinu/:id/view',
        component: TransakcijeUMagacinuDetailComponent,
        resolve: {
            transakcijeUMagacinu: TransakcijeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.transakcijeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transakcije-u-magacinu/new',
        component: TransakcijeUMagacinuUpdateComponent,
        resolve: {
            transakcijeUMagacinu: TransakcijeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.transakcijeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transakcije-u-magacinu/:id/edit',
        component: TransakcijeUMagacinuUpdateComponent,
        resolve: {
            transakcijeUMagacinu: TransakcijeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.transakcijeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transakcijeUMagacinuPopupRoute: Routes = [
    {
        path: 'transakcije-u-magacinu/:id/delete',
        component: TransakcijeUMagacinuDeletePopupComponent,
        resolve: {
            transakcijeUMagacinu: TransakcijeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.transakcijeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
