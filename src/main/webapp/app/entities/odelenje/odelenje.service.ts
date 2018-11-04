import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOdelenje } from 'app/shared/model/odelenje.model';

type EntityResponseType = HttpResponse<IOdelenje>;
type EntityArrayResponseType = HttpResponse<IOdelenje[]>;

@Injectable({ providedIn: 'root' })
export class OdelenjeService {
    public resourceUrl = SERVER_API_URL + 'api/odelenjes';

    constructor(private http: HttpClient) {}

    create(odelenje: IOdelenje): Observable<EntityResponseType> {
        return this.http.post<IOdelenje>(this.resourceUrl, odelenje, { observe: 'response' });
    }

    update(odelenje: IOdelenje): Observable<EntityResponseType> {
        return this.http.put<IOdelenje>(this.resourceUrl, odelenje, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOdelenje>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOdelenje[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
