import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Operacije } from 'app/shared/model/operacije.model';
import { OperacijeService } from './operacije.service';
import { OperacijeComponent } from './operacije.component';
import { OperacijeDetailComponent } from './operacije-detail.component';
import { OperacijeUpdateComponent } from './operacije-update.component';
import { OperacijeDeletePopupComponent } from './operacije-delete-dialog.component';
import { IOperacije } from 'app/shared/model/operacije.model';

@Injectable({ providedIn: 'root' })
export class OperacijeResolve implements Resolve<IOperacije> {
    constructor(private service: OperacijeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Operacije> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Operacije>) => response.ok),
                map((operacije: HttpResponse<Operacije>) => operacije.body)
            );
        }
        return of(new Operacije());
    }
}

export const operacijeRoute: Routes = [
    {
        path: 'operacije',
        component: OperacijeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacije.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operacije/:id/view',
        component: OperacijeDetailComponent,
        resolve: {
            operacije: OperacijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacije.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operacije/new',
        component: OperacijeUpdateComponent,
        resolve: {
            operacije: OperacijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacije.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operacije/:id/edit',
        component: OperacijeUpdateComponent,
        resolve: {
            operacije: OperacijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacije.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const operacijePopupRoute: Routes = [
    {
        path: 'operacije/:id/delete',
        component: OperacijeDeletePopupComponent,
        resolve: {
            operacije: OperacijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacije.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
