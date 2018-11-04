import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Alati } from 'app/shared/model/alati.model';
import { AlatiService } from './alati.service';
import { AlatiComponent } from './alati.component';
import { AlatiDetailComponent } from './alati-detail.component';
import { AlatiUpdateComponent } from './alati-update.component';
import { AlatiDeletePopupComponent } from './alati-delete-dialog.component';
import { IAlati } from 'app/shared/model/alati.model';

@Injectable({ providedIn: 'root' })
export class AlatiResolve implements Resolve<IAlati> {
    constructor(private service: AlatiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Alati> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Alati>) => response.ok),
                map((alati: HttpResponse<Alati>) => alati.body)
            );
        }
        return of(new Alati());
    }
}

export const alatiRoute: Routes = [
    {
        path: 'alati',
        component: AlatiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.alati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'alati/:id/view',
        component: AlatiDetailComponent,
        resolve: {
            alati: AlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.alati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'alati/new',
        component: AlatiUpdateComponent,
        resolve: {
            alati: AlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.alati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'alati/:id/edit',
        component: AlatiUpdateComponent,
        resolve: {
            alati: AlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.alati.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const alatiPopupRoute: Routes = [
    {
        path: 'alati/:id/delete',
        component: AlatiDeletePopupComponent,
        resolve: {
            alati: AlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.alati.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
