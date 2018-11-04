import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Oprema } from 'app/shared/model/oprema.model';
import { OpremaService } from './oprema.service';
import { OpremaComponent } from './oprema.component';
import { OpremaDetailComponent } from './oprema-detail.component';
import { OpremaUpdateComponent } from './oprema-update.component';
import { OpremaDeletePopupComponent } from './oprema-delete-dialog.component';
import { IOprema } from 'app/shared/model/oprema.model';

@Injectable({ providedIn: 'root' })
export class OpremaResolve implements Resolve<IOprema> {
    constructor(private service: OpremaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Oprema> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Oprema>) => response.ok),
                map((oprema: HttpResponse<Oprema>) => oprema.body)
            );
        }
        return of(new Oprema());
    }
}

export const opremaRoute: Routes = [
    {
        path: 'oprema',
        component: OpremaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.oprema.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'oprema/:id/view',
        component: OpremaDetailComponent,
        resolve: {
            oprema: OpremaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.oprema.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'oprema/new',
        component: OpremaUpdateComponent,
        resolve: {
            oprema: OpremaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.oprema.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'oprema/:id/edit',
        component: OpremaUpdateComponent,
        resolve: {
            oprema: OpremaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.oprema.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const opremaPopupRoute: Routes = [
    {
        path: 'oprema/:id/delete',
        component: OpremaDeletePopupComponent,
        resolve: {
            oprema: OpremaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.oprema.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
