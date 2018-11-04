import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Odelenje } from 'app/shared/model/odelenje.model';
import { OdelenjeService } from './odelenje.service';
import { OdelenjeComponent } from './odelenje.component';
import { OdelenjeDetailComponent } from './odelenje-detail.component';
import { OdelenjeUpdateComponent } from './odelenje-update.component';
import { OdelenjeDeletePopupComponent } from './odelenje-delete-dialog.component';
import { IOdelenje } from 'app/shared/model/odelenje.model';

@Injectable({ providedIn: 'root' })
export class OdelenjeResolve implements Resolve<IOdelenje> {
    constructor(private service: OdelenjeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Odelenje> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Odelenje>) => response.ok),
                map((odelenje: HttpResponse<Odelenje>) => odelenje.body)
            );
        }
        return of(new Odelenje());
    }
}

export const odelenjeRoute: Routes = [
    {
        path: 'odelenje',
        component: OdelenjeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.odelenje.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'odelenje/:id/view',
        component: OdelenjeDetailComponent,
        resolve: {
            odelenje: OdelenjeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.odelenje.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'odelenje/new',
        component: OdelenjeUpdateComponent,
        resolve: {
            odelenje: OdelenjeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.odelenje.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'odelenje/:id/edit',
        component: OdelenjeUpdateComponent,
        resolve: {
            odelenje: OdelenjeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.odelenje.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const odelenjePopupRoute: Routes = [
    {
        path: 'odelenje/:id/delete',
        component: OdelenjeDeletePopupComponent,
        resolve: {
            odelenje: OdelenjeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.odelenje.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
