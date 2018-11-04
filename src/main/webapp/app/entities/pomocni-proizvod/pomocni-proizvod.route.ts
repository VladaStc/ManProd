import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';
import { PomocniProizvodService } from './pomocni-proizvod.service';
import { PomocniProizvodComponent } from './pomocni-proizvod.component';
import { PomocniProizvodDetailComponent } from './pomocni-proizvod-detail.component';
import { PomocniProizvodUpdateComponent } from './pomocni-proizvod-update.component';
import { PomocniProizvodDeletePopupComponent } from './pomocni-proizvod-delete-dialog.component';
import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';

@Injectable({ providedIn: 'root' })
export class PomocniProizvodResolve implements Resolve<IPomocniProizvod> {
    constructor(private service: PomocniProizvodService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PomocniProizvod> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PomocniProizvod>) => response.ok),
                map((pomocniProizvod: HttpResponse<PomocniProizvod>) => pomocniProizvod.body)
            );
        }
        return of(new PomocniProizvod());
    }
}

export const pomocniProizvodRoute: Routes = [
    {
        path: 'pomocni-proizvod',
        component: PomocniProizvodComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pomocni-proizvod/:id/view',
        component: PomocniProizvodDetailComponent,
        resolve: {
            pomocniProizvod: PomocniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pomocni-proizvod/new',
        component: PomocniProizvodUpdateComponent,
        resolve: {
            pomocniProizvod: PomocniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pomocni-proizvod/:id/edit',
        component: PomocniProizvodUpdateComponent,
        resolve: {
            pomocniProizvod: PomocniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pomocniProizvodPopupRoute: Routes = [
    {
        path: 'pomocni-proizvod/:id/delete',
        component: PomocniProizvodDeletePopupComponent,
        resolve: {
            pomocniProizvod: PomocniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
