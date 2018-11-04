import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';
import { ProizvodneLinijeService } from './proizvodne-linije.service';
import { ProizvodneLinijeComponent } from './proizvodne-linije.component';
import { ProizvodneLinijeDetailComponent } from './proizvodne-linije-detail.component';
import { ProizvodneLinijeUpdateComponent } from './proizvodne-linije-update.component';
import { ProizvodneLinijeDeletePopupComponent } from './proizvodne-linije-delete-dialog.component';
import { IProizvodneLinije } from 'app/shared/model/proizvodne-linije.model';

@Injectable({ providedIn: 'root' })
export class ProizvodneLinijeResolve implements Resolve<IProizvodneLinije> {
    constructor(private service: ProizvodneLinijeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProizvodneLinije> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProizvodneLinije>) => response.ok),
                map((proizvodneLinije: HttpResponse<ProizvodneLinije>) => proizvodneLinije.body)
            );
        }
        return of(new ProizvodneLinije());
    }
}

export const proizvodneLinijeRoute: Routes = [
    {
        path: 'proizvodne-linije',
        component: ProizvodneLinijeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodneLinije.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proizvodne-linije/:id/view',
        component: ProizvodneLinijeDetailComponent,
        resolve: {
            proizvodneLinije: ProizvodneLinijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodneLinije.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proizvodne-linije/new',
        component: ProizvodneLinijeUpdateComponent,
        resolve: {
            proizvodneLinije: ProizvodneLinijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodneLinije.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'proizvodne-linije/:id/edit',
        component: ProizvodneLinijeUpdateComponent,
        resolve: {
            proizvodneLinije: ProizvodneLinijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodneLinije.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const proizvodneLinijePopupRoute: Routes = [
    {
        path: 'proizvodne-linije/:id/delete',
        component: ProizvodneLinijeDeletePopupComponent,
        resolve: {
            proizvodneLinije: ProizvodneLinijeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'manProdApp.proizvodneLinije.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
