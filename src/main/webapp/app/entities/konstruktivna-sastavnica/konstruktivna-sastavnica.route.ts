import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';
import { KonstruktivnaSastavnicaService } from './konstruktivna-sastavnica.service';
import { KonstruktivnaSastavnicaComponent } from './konstruktivna-sastavnica.component';
import { KonstruktivnaSastavnicaDetailComponent } from './konstruktivna-sastavnica-detail.component';
import { KonstruktivnaSastavnicaUpdateComponent } from './konstruktivna-sastavnica-update.component';
import { KonstruktivnaSastavnicaDeletePopupComponent } from './konstruktivna-sastavnica-delete-dialog.component';
import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';

@Injectable({ providedIn: 'root' })
export class KonstruktivnaSastavnicaResolve implements Resolve<IKonstruktivnaSastavnica> {
    constructor(private service: KonstruktivnaSastavnicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<KonstruktivnaSastavnica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KonstruktivnaSastavnica>) => response.ok),
                map((konstruktivnaSastavnica: HttpResponse<KonstruktivnaSastavnica>) => konstruktivnaSastavnica.body)
            );
        }
        return of(new KonstruktivnaSastavnica());
    }
}

export const konstruktivnaSastavnicaRoute: Routes = [
    {
        path: 'konstruktivna-sastavnica',
        component: KonstruktivnaSastavnicaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.konstruktivnaSastavnica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'konstruktivna-sastavnica/:id/view',
        component: KonstruktivnaSastavnicaDetailComponent,
        resolve: {
            konstruktivnaSastavnica: KonstruktivnaSastavnicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.konstruktivnaSastavnica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'konstruktivna-sastavnica/new',
        component: KonstruktivnaSastavnicaUpdateComponent,
        resolve: {
            konstruktivnaSastavnica: KonstruktivnaSastavnicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.konstruktivnaSastavnica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'konstruktivna-sastavnica/:id/edit',
        component: KonstruktivnaSastavnicaUpdateComponent,
        resolve: {
            konstruktivnaSastavnica: KonstruktivnaSastavnicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.konstruktivnaSastavnica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const konstruktivnaSastavnicaPopupRoute: Routes = [
    {
        path: 'konstruktivna-sastavnica/:id/delete',
        component: KonstruktivnaSastavnicaDeletePopupComponent,
        resolve: {
            konstruktivnaSastavnica: KonstruktivnaSastavnicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.konstruktivnaSastavnica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
