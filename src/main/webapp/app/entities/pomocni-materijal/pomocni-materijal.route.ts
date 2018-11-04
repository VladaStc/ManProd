import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';
import { PomocniMaterijalService } from './pomocni-materijal.service';
import { PomocniMaterijalComponent } from './pomocni-materijal.component';
import { PomocniMaterijalDetailComponent } from './pomocni-materijal-detail.component';
import { PomocniMaterijalUpdateComponent } from './pomocni-materijal-update.component';
import { PomocniMaterijalDeletePopupComponent } from './pomocni-materijal-delete-dialog.component';
import { IPomocniMaterijal } from 'app/shared/model/pomocni-materijal.model';

@Injectable({ providedIn: 'root' })
export class PomocniMaterijalResolve implements Resolve<IPomocniMaterijal> {
    constructor(private service: PomocniMaterijalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PomocniMaterijal> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PomocniMaterijal>) => response.ok),
                map((pomocniMaterijal: HttpResponse<PomocniMaterijal>) => pomocniMaterijal.body)
            );
        }
        return of(new PomocniMaterijal());
    }
}

export const pomocniMaterijalRoute: Routes = [
    {
        path: 'pomocni-materijal',
        component: PomocniMaterijalComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pomocni-materijal/:id/view',
        component: PomocniMaterijalDetailComponent,
        resolve: {
            pomocniMaterijal: PomocniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pomocni-materijal/new',
        component: PomocniMaterijalUpdateComponent,
        resolve: {
            pomocniMaterijal: PomocniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pomocni-materijal/:id/edit',
        component: PomocniMaterijalUpdateComponent,
        resolve: {
            pomocniMaterijal: PomocniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pomocniMaterijalPopupRoute: Routes = [
    {
        path: 'pomocni-materijal/:id/delete',
        component: PomocniMaterijalDeletePopupComponent,
        resolve: {
            pomocniMaterijal: PomocniMaterijalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.pomocniMaterijal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
