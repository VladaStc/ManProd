import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Sirovine } from 'app/shared/model/sirovine.model';
import { SirovineService } from './sirovine.service';
import { SirovineComponent } from './sirovine.component';
import { SirovineDetailComponent } from './sirovine-detail.component';
import { SirovineUpdateComponent } from './sirovine-update.component';
import { SirovineDeletePopupComponent } from './sirovine-delete-dialog.component';
import { ISirovine } from 'app/shared/model/sirovine.model';

@Injectable({ providedIn: 'root' })
export class SirovineResolve implements Resolve<ISirovine> {
    constructor(private service: SirovineService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Sirovine> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Sirovine>) => response.ok),
                map((sirovine: HttpResponse<Sirovine>) => sirovine.body)
            );
        }
        return of(new Sirovine());
    }
}

export const sirovineRoute: Routes = [
    {
        path: 'sirovine',
        component: SirovineComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.sirovine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sirovine/:id/view',
        component: SirovineDetailComponent,
        resolve: {
            sirovine: SirovineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.sirovine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sirovine/new',
        component: SirovineUpdateComponent,
        resolve: {
            sirovine: SirovineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.sirovine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sirovine/:id/edit',
        component: SirovineUpdateComponent,
        resolve: {
            sirovine: SirovineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.sirovine.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sirovinePopupRoute: Routes = [
    {
        path: 'sirovine/:id/delete',
        component: SirovineDeletePopupComponent,
        resolve: {
            sirovine: SirovineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.sirovine.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
