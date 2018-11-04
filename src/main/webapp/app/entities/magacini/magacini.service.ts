import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMagacini } from 'app/shared/model/magacini.model';

type EntityResponseType = HttpResponse<IMagacini>;
type EntityArrayResponseType = HttpResponse<IMagacini[]>;

@Injectable({ providedIn: 'root' })
export class MagaciniService {
    public resourceUrl = SERVER_API_URL + 'api/magacinis';

    constructor(private http: HttpClient) {}

    create(magacini: IMagacini): Observable<EntityResponseType> {
        return this.http.post<IMagacini>(this.resourceUrl, magacini, { observe: 'response' });
    }

    update(magacini: IMagacini): Observable<EntityResponseType> {
        return this.http.put<IMagacini>(this.resourceUrl, magacini, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMagacini>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMagacini[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
