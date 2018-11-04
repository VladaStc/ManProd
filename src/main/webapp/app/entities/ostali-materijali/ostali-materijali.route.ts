import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OstaliMaterijali } from 'app/shared/model/ostali-materijali.model';
import { OstaliMaterijaliService } from './ostali-materijali.service';
import { OstaliMaterijaliComponent } from './ostali-materijali.component';
import { OstaliMaterijaliDetailComponent } from './ostali-materijali-detail.component';
import { OstaliMaterijaliUpdateComponent } from './ostali-materijali-update.component';
import { OstaliMaterijaliDeletePopupComponent } from './ostali-materijali-delete-dialog.component';
import { IOstaliMaterijali } from 'app/shared/model/ostali-materijali.model';

@Injectable({ providedIn: 'root' })
export class OstaliMaterijaliResolve implements Resolve<IOstaliMaterijali> {
    constructor(private service: OstaliMaterijaliService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OstaliMaterijali> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OstaliMaterijali>) => response.ok),
                map((ostaliMaterijali: HttpResponse<OstaliMaterijali>) => ostaliMaterijali.body)
            );
        }
        return of(new OstaliMaterijali());
    }
}

export const ostaliMaterijaliRoute: Routes = [
    {
        path: 'ostali-materijali',
        component: OstaliMaterijaliComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.ostaliMaterijali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ostali-materijali/:id/view',
        component: OstaliMaterijaliDetailComponent,
        resolve: {
            ostaliMaterijali: OstaliMaterijaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.ostaliMaterijali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ostali-materijali/new',
        component: OstaliMaterijaliUpdateComponent,
        resolve: {
            ostaliMaterijali: OstaliMaterijaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.ostaliMaterijali.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ostali-materijali/:id/edit',
        component: OstaliMaterijaliUpdateComponent,
        resolve: {
            ostaliMaterijali: OstaliMaterijaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.ostaliMaterijali.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ostaliMaterijaliPopupRoute: Routes = [
    {
        path: 'ostali-materijali/:id/delete',
        component: OstaliMaterijaliDeletePopupComponent,
        resolve: {
            ostaliMaterijali: OstaliMaterijaliResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.ostaliMaterijali.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
