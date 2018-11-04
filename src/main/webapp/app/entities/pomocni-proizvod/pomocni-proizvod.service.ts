import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPomocniProizvod } from 'app/shared/model/pomocni-proizvod.model';

type EntityResponseType = HttpResponse<IPomocniProizvod>;
type EntityArrayResponseType = HttpResponse<IPomocniProizvod[]>;

@Injectable({ providedIn: 'root' })
export class PomocniProizvodService {
    public resourceUrl = SERVER_API_URL + 'api/pomocni-proizvods';

    constructor(private http: HttpClient) {}

    create(pomocniProizvod: IPomocniProizvod): Observable<EntityResponseType> {
        return this.http.post<IPomocniProizvod>(this.resourceUrl, pomocniProizvod, { observe: 'response' });
    }

    update(pomocniProizvod: IPomocniProizvod): Observable<EntityResponseType> {
        return this.http.put<IPomocniProizvod>(this.resourceUrl, pomocniProizvod, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPomocniProizvod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPomocniProizvod[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
