import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';
import { OperacijeURadnomNaloguService } from './operacije-u-radnom-nalogu.service';
import { OperacijeURadnomNaloguComponent } from './operacije-u-radnom-nalogu.component';
import { OperacijeURadnomNaloguDetailComponent } from './operacije-u-radnom-nalogu-detail.component';
import { OperacijeURadnomNaloguUpdateComponent } from './operacije-u-radnom-nalogu-update.component';
import { OperacijeURadnomNaloguDeletePopupComponent } from './operacije-u-radnom-nalogu-delete-dialog.component';
import { IOperacijeURadnomNalogu } from 'app/shared/model/operacije-u-radnom-nalogu.model';

@Injectable({ providedIn: 'root' })
export class OperacijeURadnomNaloguResolve implements Resolve<IOperacijeURadnomNalogu> {
    constructor(private service: OperacijeURadnomNaloguService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OperacijeURadnomNalogu> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OperacijeURadnomNalogu>) => response.ok),
                map((operacijeURadnomNalogu: HttpResponse<OperacijeURadnomNalogu>) => operacijeURadnomNalogu.body)
            );
        }
        return of(new OperacijeURadnomNalogu());
    }
}

export const operacijeURadnomNaloguRoute: Routes = [
    {
        path: 'operacije-u-radnom-nalogu',
        component: OperacijeURadnomNaloguComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacijeURadnomNalogu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operacije-u-radnom-nalogu/:id/view',
        component: OperacijeURadnomNaloguDetailComponent,
        resolve: {
            operacijeURadnomNalogu: OperacijeURadnomNaloguResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacijeURadnomNalogu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operacije-u-radnom-nalogu/new',
        component: OperacijeURadnomNaloguUpdateComponent,
        resolve: {
            operacijeURadnomNalogu: OperacijeURadnomNaloguResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacijeURadnomNalogu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'operacije-u-radnom-nalogu/:id/edit',
        component: OperacijeURadnomNaloguUpdateComponent,
        resolve: {
            operacijeURadnomNalogu: OperacijeURadnomNaloguResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacijeURadnomNalogu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const operacijeURadnomNaloguPopupRoute: Routes = [
    {
        path: 'operacije-u-radnom-nalogu/:id/delete',
        component: OperacijeURadnomNaloguDeletePopupComponent,
        resolve: {
            operacijeURadnomNalogu: OperacijeURadnomNaloguResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.operacijeURadnomNalogu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
