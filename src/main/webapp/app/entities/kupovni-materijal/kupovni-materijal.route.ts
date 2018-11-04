import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';
import { KupovniMaterijalService } from './kupovni-materijal.service';
import { KupovniMaterijalComponent } from './kupovni-materijal.component';
import { KupovniMaterijalDetailComponent } from './kupovni-materijal-detail.component';
import { KupovniMaterijalUpdateComponent } from './kupovni-materijal-update.component';
import { KupovniMaterijalDeletePopupComponent } from './kupovni-materijal-delete-dialog.component';
import { IKupovniMaterijal } from 'app/shared/model/kupovni-materijal.model';

@Injectable({ providedIn: 'root' })
export class KupovniMaterijalResolve implements Resolve<IKupovniMaterijal> {
    constructor(private service: KupovniMaterijalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<KupovniMaterijal> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KupovniMaterijal>) => response.ok),
                map((kupovniMaterijal: HttpResponse<KupovniMaterijal>) => kupovniMaterijal.body)
            );
        }
        return of(new KupovniMaterijal());
    }
}

export const kupovniMaterijalRoute: Routes = [
    {
        path: 'kupovni-materijal',
        component: KupovniMaterijalComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kupovni-materijal/:id/view',
        component: KupovniMaterijalDetailComponent,
        resolve: {
            kupovniMaterijal: KupovniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kupovni-materijal/new',
        component: KupovniMaterijalUpdateComponent,
        resolve: {
            kupovniMaterijal: KupovniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'kupovni-materijal/:id/edit',
        component: KupovniMaterijalUpdateComponent,
        resolve: {
            kupovniMaterijal: KupovniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const kupovniMaterijalPopupRoute: Routes = [
    {
        path: 'kupovni-materijal/:id/delete',
        component: KupovniMaterijalDeletePopupComponent,
        resolve: {
            kupovniMaterijal: KupovniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.kupovniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
