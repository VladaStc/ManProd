import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FinalniProizvod } from 'app/shared/model/finalni-proizvod.model';
import { FinalniProizvodService } from './finalni-proizvod.service';
import { FinalniProizvodComponent } from './finalni-proizvod.component';
import { FinalniProizvodDetailComponent } from './finalni-proizvod-detail.component';
import { FinalniProizvodUpdateComponent } from './finalni-proizvod-update.component';
import { FinalniProizvodDeletePopupComponent } from './finalni-proizvod-delete-dialog.component';
import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';

@Injectable({ providedIn: 'root' })
export class FinalniProizvodResolve implements Resolve<IFinalniProizvod> {
    constructor(private service: FinalniProizvodService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FinalniProizvod> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FinalniProizvod>) => response.ok),
                map((finalniProizvod: HttpResponse<FinalniProizvod>) => finalniProizvod.body)
            );
        }
        return of(new FinalniProizvod());
    }
}

export const finalniProizvodRoute: Routes = [
    {
        path: 'finalni-proizvod',
        component: FinalniProizvodComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.finalniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'finalni-proizvod/:id/view',
        component: FinalniProizvodDetailComponent,
        resolve: {
            finalniProizvod: FinalniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.finalniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'finalni-proizvod/new',
        component: FinalniProizvodUpdateComponent,
        resolve: {
            finalniProizvod: FinalniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.finalniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'finalni-proizvod/:id/edit',
        component: FinalniProizvodUpdateComponent,
        resolve: {
            finalniProizvod: FinalniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.finalniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const finalniProizvodPopupRoute: Routes = [
    {
        path: 'finalni-proizvod/:id/delete',
        component: FinalniProizvodDeletePopupComponent,
        resolve: {
            finalniProizvod: FinalniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.finalniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
