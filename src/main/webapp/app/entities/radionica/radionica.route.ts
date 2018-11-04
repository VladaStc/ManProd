import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Radionica } from 'app/shared/model/radionica.model';
import { RadionicaService } from './radionica.service';
import { RadionicaComponent } from './radionica.component';
import { RadionicaDetailComponent } from './radionica-detail.component';
import { RadionicaUpdateComponent } from './radionica-update.component';
import { RadionicaDeletePopupComponent } from './radionica-delete-dialog.component';
import { IRadionica } from 'app/shared/model/radionica.model';

@Injectable({ providedIn: 'root' })
export class RadionicaResolve implements Resolve<IRadionica> {
    constructor(private service: RadionicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Radionica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Radionica>) => response.ok),
                map((radionica: HttpResponse<Radionica>) => radionica.body)
            );
        }
        return of(new Radionica());
    }
}

export const radionicaRoute: Routes = [
    {
        path: 'radionica',
        component: RadionicaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radionica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radionica/:id/view',
        component: RadionicaDetailComponent,
        resolve: {
            radionica: RadionicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radionica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radionica/new',
        component: RadionicaUpdateComponent,
        resolve: {
            radionica: RadionicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radionica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radionica/:id/edit',
        component: RadionicaUpdateComponent,
        resolve: {
            radionica: RadionicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radionica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const radionicaPopupRoute: Routes = [
    {
        path: 'radionica/:id/delete',
        component: RadionicaDeletePopupComponent,
        resolve: {
            radionica: RadionicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.radionica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
