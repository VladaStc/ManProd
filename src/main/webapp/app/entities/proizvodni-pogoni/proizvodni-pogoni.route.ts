import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';
import { ProizvodniPogoniService } from './proizvodni-pogoni.service';
import { ProizvodniPogoniComponent } from './proizvodni-pogoni.component';
import { ProizvodniPogoniDetailComponent } from './proizvodni-pogoni-detail.component';
import { ProizvodniPogoniUpdateComponent } from './proizvodni-pogoni-update.component';
import { ProizvodniPogoniDeletePopupComponent } from './proizvodni-pogoni-delete-dialog.component';
import { IProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';

@Injectable({ providedIn: 'root' })
export class ProizvodniPogoniResolve implements Resolve<IProizvodniPogoni> {
    constructor(private service: ProizvodniPogoniService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProizvodniPogoni> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProizvodniPogoni>) => response.ok),
                map((proizvodniPogoni: HttpResponse<ProizvodniPogoni>) => proizvodniPogoni.body)
            );
        }
        return of(new ProizvodniPogoni());
    }
}

export const proizvodniPogoniRoute: Routes = [
    {
        path: 'proizvodni-pogoni',
        component: ProizvodniPogoniComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodniPogoni.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proizvodni-pogoni/:id/view',
        component: ProizvodniPogoniDetailComponent,
        resolve: {
            proizvodniPogoni: ProizvodniPogoniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodniPogoni.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proizvodni-pogoni/new',
        component: ProizvodniPogoniUpdateComponent,
        resolve: {
            proizvodniPogoni: ProizvodniPogoniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodniPogoni.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proizvodni-pogoni/:id/edit',
        component: ProizvodniPogoniUpdateComponent,
        resolve: {
            proizvodniPogoni: ProizvodniPogoniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodniPogoni.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const proizvodniPogoniPopupRoute: Routes = [
    {
        path: 'proizvodni-pogoni/:id/delete',
        component: ProizvodniPogoniDeletePopupComponent,
        resolve: {
            proizvodniPogoni: ProizvodniPogoniResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodniPogoni.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
