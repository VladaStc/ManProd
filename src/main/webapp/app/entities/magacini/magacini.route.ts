import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Magacini } from 'app/shared/model/magacini.model';
import { MagaciniService } from './magacini.service';
import { MagaciniComponent } from './magacini.component';
import { MagaciniDetailComponent } from './magacini-detail.component';
import { MagaciniUpdateComponent } from './magacini-update.component';
import { MagaciniDeletePopupComponent } from './magacini-delete-dialog.component';
import { IMagacini } from 'app/shared/model/magacini.model';

@Injectable({ providedIn: 'root' })
export class MagaciniResolve implements Resolve<IMagacini> {
    constructor(private service: MagaciniService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Magacini> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Magacini>) => response.ok),
                map((magacini: HttpResponse<Magacini>) => magacini.body)
            );
        }
        return of(new Magacini());
    }
}

export const magaciniRoute: Routes = [
    {
        path: 'magacini',
        component: MagaciniComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.magacini.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'magacini/:id/view',
        component: MagaciniDetailComponent,
        resolve: {
            magacini: MagaciniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.magacini.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'magacini/new',
        component: MagaciniUpdateComponent,
        resolve: {
            magacini: MagaciniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.magacini.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'magacini/:id/edit',
        component: MagaciniUpdateComponent,
        resolve: {
            magacini: MagaciniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.magacini.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const magaciniPopupRoute: Routes = [
    {
        path: 'magacini/:id/delete',
        component: MagaciniDeletePopupComponent,
        resolve: {
            magacini: MagaciniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.magacini.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
