import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKonstruktivnaSastavnica } from 'app/shared/model/konstruktivna-sastavnica.model';

type EntityResponseType = HttpResponse<IKonstruktivnaSastavnica>;
type EntityArrayResponseType = HttpResponse<IKonstruktivnaSastavnica[]>;

@Injectable({ providedIn: 'root' })
export class KonstruktivnaSastavnicaService {
    public resourceUrl = SERVER_API_URL + 'api/konstruktivna-sastavnicas';

    constructor(private http: HttpClient) {}

    create(konstruktivnaSastavnica: IKonstruktivnaSastavnica): Observable<EntityResponseType> {
        return this.http.post<IKonstruktivnaSastavnica>(this.resourceUrl, konstruktivnaSastavnica, { observe: 'response' });
    }

    update(konstruktivnaSastavnica: IKonstruktivnaSastavnica): Observable<EntityResponseType> {
        return this.http.put<IKonstruktivnaSastavnica>(this.resourceUrl, konstruktivnaSastavnica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKonstruktivnaSastavnica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKonstruktivnaSastavnica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
