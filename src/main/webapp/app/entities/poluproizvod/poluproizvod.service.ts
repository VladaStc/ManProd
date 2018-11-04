import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPoluproizvod } from 'app/shared/model/poluproizvod.model';

type EntityResponseType = HttpResponse<IPoluproizvod>;
type EntityArrayResponseType = HttpResponse<IPoluproizvod[]>;

@Injectable({ providedIn: 'root' })
export class PoluproizvodService {
    public resourceUrl = SERVER_API_URL + 'api/poluproizvods';

    constructor(private http: HttpClient) {}

    create(poluproizvod: IPoluproizvod): Observable<EntityResponseType> {
        return this.http.post<IPoluproizvod>(this.resourceUrl, poluproizvod, { observe: 'response' });
    }

    update(poluproizvod: IPoluproizvod): Observable<EntityResponseType> {
        return this.http.put<IPoluproizvod>(this.resourceUrl, poluproizvod, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPoluproizvod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPoluproizvod[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
