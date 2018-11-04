import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Masina } from 'app/shared/model/masina.model';
import { MasinaService } from './masina.service';
import { MasinaComponent } from './masina.component';
import { MasinaDetailComponent } from './masina-detail.component';
import { MasinaUpdateComponent } from './masina-update.component';
import { MasinaDeletePopupComponent } from './masina-delete-dialog.component';
import { IMasina } from 'app/shared/model/masina.model';

@Injectable({ providedIn: 'root' })
export class MasinaResolve implements Resolve<IMasina> {
    constructor(private service: MasinaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Masina> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Masina>) => response.ok),
                map((masina: HttpResponse<Masina>) => masina.body)
            );
        }
        return of(new Masina());
    }
}

export const masinaRoute: Routes = [
    {
        path: 'masina',
        component: MasinaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.masina.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'masina/:id/view',
        component: MasinaDetailComponent,
        resolve: {
            masina: MasinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.masina.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'masina/new',
        component: MasinaUpdateComponent,
        resolve: {
            masina: MasinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.masina.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'masina/:id/edit',
        component: MasinaUpdateComponent,
        resolve: {
            masina: MasinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.masina.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const masinaPopupRoute: Routes = [
    {
        path: 'masina/:id/delete',
        component: MasinaDeletePopupComponent,
        resolve: {
            masina: MasinaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.masina.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
