import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Dobavljac } from 'app/shared/model/dobavljac.model';
import { DobavljacService } from './dobavljac.service';
import { DobavljacComponent } from './dobavljac.component';
import { DobavljacDetailComponent } from './dobavljac-detail.component';
import { DobavljacUpdateComponent } from './dobavljac-update.component';
import { DobavljacDeletePopupComponent } from './dobavljac-delete-dialog.component';
import { IDobavljac } from 'app/shared/model/dobavljac.model';

@Injectable({ providedIn: 'root' })
export class DobavljacResolve implements Resolve<IDobavljac> {
    constructor(private service: DobavljacService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Dobavljac> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Dobavljac>) => response.ok),
                map((dobavljac: HttpResponse<Dobavljac>) => dobavljac.body)
            );
        }
        return of(new Dobavljac());
    }
}

export const dobavljacRoute: Routes = [
    {
        path: 'dobavljac',
        component: DobavljacComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.dobavljac.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dobavljac/:id/view',
        component: DobavljacDetailComponent,
        resolve: {
            dobavljac: DobavljacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.dobavljac.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dobavljac/new',
        component: DobavljacUpdateComponent,
        resolve: {
            dobavljac: DobavljacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.dobavljac.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dobavljac/:id/edit',
        component: DobavljacUpdateComponent,
        resolve: {
            dobavljac: DobavljacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.dobavljac.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dobavljacPopupRoute: Routes = [
    {
        path: 'dobavljac/:id/delete',
        component: DobavljacDeletePopupComponent,
        resolve: {
            dobavljac: DobavljacResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.dobavljac.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
