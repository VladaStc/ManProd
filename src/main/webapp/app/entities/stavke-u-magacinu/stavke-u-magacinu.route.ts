import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';
import { StavkeUMagacinuService } from './stavke-u-magacinu.service';
import { StavkeUMagacinuComponent } from './stavke-u-magacinu.component';
import { StavkeUMagacinuDetailComponent } from './stavke-u-magacinu-detail.component';
import { StavkeUMagacinuUpdateComponent } from './stavke-u-magacinu-update.component';
import { StavkeUMagacinuDeletePopupComponent } from './stavke-u-magacinu-delete-dialog.component';
import { IStavkeUMagacinu } from 'app/shared/model/stavke-u-magacinu.model';

@Injectable({ providedIn: 'root' })
export class StavkeUMagacinuResolve implements Resolve<IStavkeUMagacinu> {
    constructor(private service: StavkeUMagacinuService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StavkeUMagacinu> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StavkeUMagacinu>) => response.ok),
                map((stavkeUMagacinu: HttpResponse<StavkeUMagacinu>) => stavkeUMagacinu.body)
            );
        }
        return of(new StavkeUMagacinu());
    }
}

export const stavkeUMagacinuRoute: Routes = [
    {
        path: 'stavke-u-magacinu',
        component: StavkeUMagacinuComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.stavkeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stavke-u-magacinu/:id/view',
        component: StavkeUMagacinuDetailComponent,
        resolve: {
            stavkeUMagacinu: StavkeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.stavkeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stavke-u-magacinu/new',
        component: StavkeUMagacinuUpdateComponent,
        resolve: {
            stavkeUMagacinu: StavkeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.stavkeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stavke-u-magacinu/:id/edit',
        component: StavkeUMagacinuUpdateComponent,
        resolve: {
            stavkeUMagacinu: StavkeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.stavkeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stavkeUMagacinuPopupRoute: Routes = [
    {
        path: 'stavke-u-magacinu/:id/delete',
        component: StavkeUMagacinuDeletePopupComponent,
        resolve: {
            stavkeUMagacinu: StavkeUMagacinuResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.stavkeUMagacinu.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
