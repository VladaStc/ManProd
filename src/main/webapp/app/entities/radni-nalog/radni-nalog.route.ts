import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RadniNalog } from 'app/shared/model/radni-nalog.model';
import { RadniNalogService } from './radni-nalog.service';
import { RadniNalogComponent } from './radni-nalog.component';
import { RadniNalogDetailComponent } from './radni-nalog-detail.component';
import { RadniNalogUpdateComponent } from './radni-nalog-update.component';
import { RadniNalogDeletePopupComponent } from './radni-nalog-delete-dialog.component';
import { IRadniNalog } from 'app/shared/model/radni-nalog.model';

@Injectable({ providedIn: 'root' })
export class RadniNalogResolve implements Resolve<IRadniNalog> {
    constructor(private service: RadniNalogService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RadniNalog> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RadniNalog>) => response.ok),
                map((radniNalog: HttpResponse<RadniNalog>) => radniNalog.body)
            );
        }
        return of(new RadniNalog());
    }
}

export const radniNalogRoute: Routes = [
    {
        path: 'radni-nalog',
        component: RadniNalogComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radniNalog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radni-nalog/:id/view',
        component: RadniNalogDetailComponent,
        resolve: {
            radniNalog: RadniNalogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radniNalog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radni-nalog/new',
        component: RadniNalogUpdateComponent,
        resolve: {
            radniNalog: RadniNalogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radniNalog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radni-nalog/:id/edit',
        component: RadniNalogUpdateComponent,
        resolve: {
            radniNalog: RadniNalogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radniNalog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const radniNalogPopupRoute: Routes = [
    {
        path: 'radni-nalog/:id/delete',
        component: RadniNalogDeletePopupComponent,
        resolve: {
            radniNalog: RadniNalogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radniNalog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
