import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Zahvati } from 'app/shared/model/zahvati.model';
import { ZahvatiService } from './zahvati.service';
import { ZahvatiComponent } from './zahvati.component';
import { ZahvatiDetailComponent } from './zahvati-detail.component';
import { ZahvatiUpdateComponent } from './zahvati-update.component';
import { ZahvatiDeletePopupComponent } from './zahvati-delete-dialog.component';
import { IZahvati } from 'app/shared/model/zahvati.model';

@Injectable({ providedIn: 'root' })
export class ZahvatiResolve implements Resolve<IZahvati> {
    constructor(private service: ZahvatiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Zahvati> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Zahvati>) => response.ok),
                map((zahvati: HttpResponse<Zahvati>) => zahvati.body)
            );
        }
        return of(new Zahvati());
    }
}

export const zahvatiRoute: Routes = [
    {
        path: 'zahvati',
        component: ZahvatiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.zahvati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'zahvati/:id/view',
        component: ZahvatiDetailComponent,
        resolve: {
            zahvati: ZahvatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.zahvati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'zahvati/new',
        component: ZahvatiUpdateComponent,
        resolve: {
            zahvati: ZahvatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.zahvati.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'zahvati/:id/edit',
        component: ZahvatiUpdateComponent,
        resolve: {
            zahvati: ZahvatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.zahvati.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const zahvatiPopupRoute: Routes = [
    {
        path: 'zahvati/:id/delete',
        component: ZahvatiDeletePopupComponent,
        resolve: {
            zahvati: ZahvatiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.zahvati.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
