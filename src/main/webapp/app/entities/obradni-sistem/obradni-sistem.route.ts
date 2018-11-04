import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ObradniSistem } from 'app/shared/model/obradni-sistem.model';
import { ObradniSistemService } from './obradni-sistem.service';
import { ObradniSistemComponent } from './obradni-sistem.component';
import { ObradniSistemDetailComponent } from './obradni-sistem-detail.component';
import { ObradniSistemUpdateComponent } from './obradni-sistem-update.component';
import { ObradniSistemDeletePopupComponent } from './obradni-sistem-delete-dialog.component';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';

@Injectable({ providedIn: 'root' })
export class ObradniSistemResolve implements Resolve<IObradniSistem> {
    constructor(private service: ObradniSistemService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObradniSistem> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ObradniSistem>) => response.ok),
                map((obradniSistem: HttpResponse<ObradniSistem>) => obradniSistem.body)
            );
        }
        return of(new ObradniSistem());
    }
}

export const obradniSistemRoute: Routes = [
    {
        path: 'obradni-sistem',
        component: ObradniSistemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.obradniSistem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'obradni-sistem/:id/view',
        component: ObradniSistemDetailComponent,
        resolve: {
            obradniSistem: ObradniSistemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.obradniSistem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'obradni-sistem/new',
        component: ObradniSistemUpdateComponent,
        resolve: {
            obradniSistem: ObradniSistemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.obradniSistem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'obradni-sistem/:id/edit',
        component: ObradniSistemUpdateComponent,
        resolve: {
            obradniSistem: ObradniSistemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.obradniSistem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const obradniSistemPopupRoute: Routes = [
    {
        path: 'obradni-sistem/:id/delete',
        component: ObradniSistemDeletePopupComponent,
        resolve: {
            obradniSistem: ObradniSistemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.obradniSistem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
