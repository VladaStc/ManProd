import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IZahvati } from 'app/shared/model/zahvati.model';

type EntityResponseType = HttpResponse<IZahvati>;
type EntityArrayResponseType = HttpResponse<IZahvati[]>;

@Injectable({ providedIn: 'root' })
export class ZahvatiService {
    public resourceUrl = SERVER_API_URL + 'api/zahvatis';

    constructor(private http: HttpClient) {}

    create(zahvati: IZahvati): Observable<EntityResponseType> {
        return this.http.post<IZahvati>(this.resourceUrl, zahvati, { observe: 'response' });
    }

    update(zahvati: IZahvati): Observable<EntityResponseType> {
        return this.http.put<IZahvati>(this.resourceUrl, zahvati, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IZahvati>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IZahvati[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
