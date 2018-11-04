import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Pribori } from 'app/shared/model/pribori.model';
import { PriboriService } from './pribori.service';
import { PriboriComponent } from './pribori.component';
import { PriboriDetailComponent } from './pribori-detail.component';
import { PriboriUpdateComponent } from './pribori-update.component';
import { PriboriDeletePopupComponent } from './pribori-delete-dialog.component';
import { IPribori } from 'app/shared/model/pribori.model';

@Injectable({ providedIn: 'root' })
export class PriboriResolve implements Resolve<IPribori> {
    constructor(private service: PriboriService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Pribori> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Pribori>) => response.ok),
                map((pribori: HttpResponse<Pribori>) => pribori.body)
            );
        }
        return of(new Pribori());
    }
}

export const priboriRoute: Routes = [
    {
        path: 'pribori',
        component: PriboriComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pribori.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pribori/:id/view',
        component: PriboriDetailComponent,
        resolve: {
            pribori: PriboriResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pribori.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pribori/new',
        component: PriboriUpdateComponent,
        resolve: {
            pribori: PriboriResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pribori.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pribori/:id/edit',
        component: PriboriUpdateComponent,
        resolve: {
            pribori: PriboriResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pribori.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const priboriPopupRoute: Routes = [
    {
        path: 'pribori/:id/delete',
        component: PriboriDeletePopupComponent,
        resolve: {
            pribori: PriboriResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pribori.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
