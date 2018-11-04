import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Poluproizvod } from 'app/shared/model/poluproizvod.model';
import { PoluproizvodService } from './poluproizvod.service';
import { PoluproizvodComponent } from './poluproizvod.component';
import { PoluproizvodDetailComponent } from './poluproizvod-detail.component';
import { PoluproizvodUpdateComponent } from './poluproizvod-update.component';
import { PoluproizvodDeletePopupComponent } from './poluproizvod-delete-dialog.component';
import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';

@Injectable({ providedIn: 'root' })
export class PoluproizvodResolve implements Resolve<IPoluproizvod> {
    constructor(private service: PoluproizvodService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Poluproizvod> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Poluproizvod>) => response.ok),
                map((poluproizvod: HttpResponse<Poluproizvod>) => poluproizvod.body)
            );
        }
        return of(new Poluproizvod());
    }
}

export const poluproizvodRoute: Routes = [
    {
        path: 'poluproizvod',
        component: PoluproizvodComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.poluproizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poluproizvod/:id/view',
        component: PoluproizvodDetailComponent,
        resolve: {
            poluproizvod: PoluproizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.poluproizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poluproizvod/new',
        component: PoluproizvodUpdateComponent,
        resolve: {
            poluproizvod: PoluproizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.poluproizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poluproizvod/:id/edit',
        component: PoluproizvodUpdateComponent,
        resolve: {
            poluproizvod: PoluproizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.poluproizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const poluproizvodPopupRoute: Routes = [
    {
        path: 'poluproizvod/:id/delete',
        component: PoluproizvodDeletePopupComponent,
        resolve: {
            poluproizvod: PoluproizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.poluproizvod.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
