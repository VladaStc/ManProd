import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MerniAlati } from 'app/shared/model/merni-alati.model';
import { MerniAlatiService } from './merni-alati.service';
import { MerniAlatiComponent } from './merni-alati.component';
import { MerniAlatiDetailComponent } from './merni-alati-detail.component';
import { MerniAlatiUpdateComponent } from './merni-alati-update.component';
import { MerniAlatiDeletePopupComponent } from './merni-alati-delete-dialog.component';
import { IMerniAlati } from 'app/shared/model/merni-alati.model';

@Injectable({ providedIn: 'root' })
export class MerniAlatiResolve implements Resolve<IMerniAlati> {
    constructor(private service: MerniAlatiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MerniAlati> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MerniAlati>) => response.ok),
                map((merniAlati: HttpResponse<MerniAlati>) => merniAlati.body)
            );
        }
        return of(new MerniAlati());
    }
}

export const merniAlatiRoute: Routes = [
    {
        path: 'merni-alati',
        component: MerniAlatiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.merniAlati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'merni-alati/:id/view',
        component: MerniAlatiDetailComponent,
        resolve: {
            merniAlati: MerniAlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.merniAlati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'merni-alati/new',
        component: MerniAlatiUpdateComponent,
        resolve: {
            merniAlati: MerniAlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.merniAlati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'merni-alati/:id/edit',
        component: MerniAlatiUpdateComponent,
        resolve: {
            merniAlati: MerniAlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.merniAlati.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const merniAlatiPopupRoute: Routes = [
    {
        path: 'merni-alati/:id/delete',
        component: MerniAlatiDeletePopupComponent,
        resolve: {
            merniAlati: MerniAlatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.merniAlati.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
