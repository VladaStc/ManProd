import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Radnici } from 'app/shared/model/radnici.model';
import { RadniciService } from './radnici.service';
import { RadniciComponent } from './radnici.component';
import { RadniciDetailComponent } from './radnici-detail.component';
import { RadniciUpdateComponent } from './radnici-update.component';
import { RadniciDeletePopupComponent } from './radnici-delete-dialog.component';
import { IRadnici } from 'app/shared/model/radnici.model';

@Injectable({ providedIn: 'root' })
export class RadniciResolve implements Resolve<IRadnici> {
    constructor(private service: RadniciService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Radnici> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Radnici>) => response.ok),
                map((radnici: HttpResponse<Radnici>) => radnici.body)
            );
        }
        return of(new Radnici());
    }
}

export const radniciRoute: Routes = [
    {
        path: 'radnici',
        component: RadniciComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'manProdApp.radnici.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radnici/:id/view',
        component: RadniciDetailComponent,
        resolve: {
            radnici: RadniciResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnici.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radnici/new',
        component: RadniciUpdateComponent,
        resolve: {
            radnici: RadniciResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnici.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radnici/:id/edit',
        component: RadniciUpdateComponent,
        resolve: {
            radnici: RadniciResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnici.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const radniciPopupRoute: Routes = [
    {
        path: 'radnici/:id/delete',
        component: RadniciDeletePopupComponent,
        resolve: {
            radnici: RadniciResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnici.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
