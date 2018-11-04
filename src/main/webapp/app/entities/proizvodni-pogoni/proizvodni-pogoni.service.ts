import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProizvodniPogoni } from 'app/shared/model/proizvodni-pogoni.model';

type EntityResponseType = HttpResponse<IProizvodniPogoni>;
type EntityArrayResponseType = HttpResponse<IProizvodniPogoni[]>;

@Injectable({ providedIn: 'root' })
export class ProizvodniPogoniService {
    public resourceUrl = SERVER_API_URL + 'api/proizvodni-pogonis';

    constructor(private http: HttpClient) {}

    create(proizvodniPogoni: IProizvodniPogoni): Observable<EntityResponseType> {
        return this.http.post<IProizvodniPogoni>(this.resourceUrl, proizvodniPogoni, { observe: 'response' });
    }

    update(proizvodniPogoni: IProizvodniPogoni): Observable<EntityResponseType> {
        return this.http.put<IProizvodniPogoni>(this.resourceUrl, proizvodniPogoni, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProizvodniPogoni>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProizvodniPogoni[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
