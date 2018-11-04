import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';
import { PotrosniMaterijalService } from './potrosni-materijal.service';
import { PotrosniMaterijalComponent } from './potrosni-materijal.component';
import { PotrosniMaterijalDetailComponent } from './potrosni-materijal-detail.component';
import { PotrosniMaterijalUpdateComponent } from './potrosni-materijal-update.component';
import { PotrosniMaterijalDeletePopupComponent } from './potrosni-materijal-delete-dialog.component';
import { IPotrosniMaterijal } from 'app/shared/model/potrosni-materijal.model';

@Injectable({ providedIn: 'root' })
export class PotrosniMaterijalResolve implements Resolve<IPotrosniMaterijal> {
    constructor(private service: PotrosniMaterijalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PotrosniMaterijal> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PotrosniMaterijal>) => response.ok),
                map((potrosniMaterijal: HttpResponse<PotrosniMaterijal>) => potrosniMaterijal.body)
            );
        }
        return of(new PotrosniMaterijal());
    }
}

export const potrosniMaterijalRoute: Routes = [
    {
        path: 'potrosni-materijal',
        component: PotrosniMaterijalComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.potrosniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'potrosni-materijal/:id/view',
        component: PotrosniMaterijalDetailComponent,
        resolve: {
            potrosniMaterijal: PotrosniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.potrosniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'potrosni-materijal/new',
        component: PotrosniMaterijalUpdateComponent,
        resolve: {
            potrosniMaterijal: PotrosniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.potrosniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'potrosni-materijal/:id/edit',
        component: PotrosniMaterijalUpdateComponent,
        resolve: {
            potrosniMaterijal: PotrosniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.potrosniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const potrosniMaterijalPopupRoute: Routes = [
    {
        path: 'potrosni-materijal/:id/delete',
        component: PotrosniMaterijalDeletePopupComponent,
        resolve: {
            potrosniMaterijal: PotrosniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.potrosniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
