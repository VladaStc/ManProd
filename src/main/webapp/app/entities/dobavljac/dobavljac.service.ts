import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDobavljac } from 'app/shared/model/dobavljac.model';

type EntityResponseType = HttpResponse<IDobavljac>;
type EntityArrayResponseType = HttpResponse<IDobavljac[]>;

@Injectable({ providedIn: 'root' })
export class DobavljacService {
    public resourceUrl = SERVER_API_URL + 'api/dobavljacs';

    constructor(private http: HttpClient) {}

    create(dobavljac: IDobavljac): Observable<EntityResponseType> {
        return this.http.post<IDobavljac>(this.resourceUrl, dobavljac, { observe: 'response' });
    }

    update(dobavljac: IDobavljac): Observable<EntityResponseType> {
        return this.http.put<IDobavljac>(this.resourceUrl, dobavljac, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDobavljac>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDobavljac[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
