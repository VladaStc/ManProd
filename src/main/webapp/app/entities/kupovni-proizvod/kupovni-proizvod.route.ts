import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';
import { KupovniProizvodService } from './kupovni-proizvod.service';
import { KupovniProizvodComponent } from './kupovni-proizvod.component';
import { KupovniProizvodDetailComponent } from './kupovni-proizvod-detail.component';
import { KupovniProizvodUpdateComponent } from './kupovni-proizvod-update.component';
import { KupovniProizvodDeletePopupComponent } from './kupovni-proizvod-delete-dialog.component';
import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';

@Injectable({ providedIn: 'root' })
export class KupovniProizvodResolve implements Resolve<IKupovniProizvod> {
    constructor(private service: KupovniProizvodService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<KupovniProizvod> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KupovniProizvod>) => response.ok),
                map((kupovniProizvod: HttpResponse<KupovniProizvod>) => kupovniProizvod.body)
            );
        }
        return of(new KupovniProizvod());
    }
}

export const kupovniProizvodRoute: Routes = [
    {
        path: 'kupovni-proizvod',
        component: KupovniProizvodComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kupovni-proizvod/:id/view',
        component: KupovniProizvodDetailComponent,
        resolve: {
            kupovniProizvod: KupovniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kupovni-proizvod/new',
        component: KupovniProizvodUpdateComponent,
        resolve: {
            kupovniProizvod: KupovniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kupovni-proizvod/:id/edit',
        component: KupovniProizvodUpdateComponent,
        resolve: {
            kupovniProizvod: KupovniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const kupovniProizvodPopupRoute: Routes = [
    {
        path: 'kupovni-proizvod/:id/delete',
        component: KupovniProizvodDeletePopupComponent,
        resolve: {
            kupovniProizvod: KupovniProizvodResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniProizvod.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
