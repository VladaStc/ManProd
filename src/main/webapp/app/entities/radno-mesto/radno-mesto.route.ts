import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RadnoMesto } from 'app/shared/model/radno-mesto.model';
import { RadnoMestoService } from './radno-mesto.service';
import { RadnoMestoComponent } from './radno-mesto.component';
import { RadnoMestoDetailComponent } from './radno-mesto-detail.component';
import { RadnoMestoUpdateComponent } from './radno-mesto-update.component';
import { RadnoMestoDeletePopupComponent } from './radno-mesto-delete-dialog.component';
import { IRadnoMesto } from 'app/shared/model/radno-mesto.model';

@Injectable({ providedIn: 'root' })
export class RadnoMestoResolve implements Resolve<IRadnoMesto> {
    constructor(private service: RadnoMestoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RadnoMesto> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RadnoMesto>) => response.ok),
                map((radnoMesto: HttpResponse<RadnoMesto>) => radnoMesto.body)
            );
        }
        return of(new RadnoMesto());
    }
}

export const radnoMestoRoute: Routes = [
    {
        path: 'radno-mesto',
        component: RadnoMestoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnoMesto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radno-mesto/:id/view',
        component: RadnoMestoDetailComponent,
        resolve: {
            radnoMesto: RadnoMestoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnoMesto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radno-mesto/new',
        component: RadnoMestoUpdateComponent,
        resolve: {
            radnoMesto: RadnoMestoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnoMesto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radno-mesto/:id/edit',
        component: RadnoMestoUpdateComponent,
        resolve: {
            radnoMesto: RadnoMestoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnoMesto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const radnoMestoPopupRoute: Routes = [
    {
        path: 'radno-mesto/:id/delete',
        component: RadnoMestoDeletePopupComponent,
        resolve: {
            radnoMesto: RadnoMestoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radnoMesto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
