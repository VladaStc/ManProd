import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMasina } from 'app/shared/model/masina.model';

type EntityResponseType = HttpResponse<IMasina>;
type EntityArrayResponseType = HttpResponse<IMasina[]>;

@Injectable({ providedIn: 'root' })
export class MasinaService {
    public resourceUrl = SERVER_API_URL + 'api/masinas';

    constructor(private http: HttpClient) {}

    create(masina: IMasina): Observable<EntityResponseType> {
        return this.http.post<IMasina>(this.resourceUrl, masina, { observe: 'response' });
    }

    update(masina: IMasina): Observable<EntityResponseType> {
        return this.http.put<IMasina>(this.resourceUrl, masina, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMasina>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMasina[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
