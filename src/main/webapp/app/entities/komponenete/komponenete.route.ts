import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Komponenete } from 'app/shared/model/komponenete.model';
import { KomponeneteService } from './komponenete.service';
import { KomponeneteComponent } from './komponenete.component';
import { KomponeneteDetailComponent } from './komponenete-detail.component';
import { KomponeneteUpdateComponent } from './komponenete-update.component';
import { KomponeneteDeletePopupComponent } from './komponenete-delete-dialog.component';
import { IKomponenete } from 'app/shared/model/komponenete.model';

@Injectable({ providedIn: 'root' })
export class KomponeneteResolve implements Resolve<IKomponenete> {
    constructor(private service: KomponeneteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Komponenete> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Komponenete>) => response.ok),
                map((komponenete: HttpResponse<Komponenete>) => komponenete.body)
            );
        }
        return of(new Komponenete());
    }
}

export const komponeneteRoute: Routes = [
    {
        path: 'komponenete',
        component: KomponeneteComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.komponenete.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'komponenete/:id/view',
        component: KomponeneteDetailComponent,
        resolve: {
            komponenete: KomponeneteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.komponenete.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'komponenete/new',
        component: KomponeneteUpdateComponent,
        resolve: {
            komponenete: KomponeneteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.komponenete.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'komponenete/:id/edit',
        component: KomponeneteUpdateComponent,
        resolve: {
            komponenete: KomponeneteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.komponenete.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const komponenetePopupRoute: Routes = [
    {
        path: 'komponenete/:id/delete',
        component: KomponeneteDeletePopupComponent,
        resolve: {
            komponenete: KomponeneteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.komponenete.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
