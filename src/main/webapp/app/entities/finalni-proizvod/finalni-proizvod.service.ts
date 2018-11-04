import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFinalniProizvod } from 'app/shared/model/finalni-proizvod.model';

type EntityResponseType = HttpResponse<IFinalniProizvod>;
type EntityArrayResponseType = HttpResponse<IFinalniProizvod[]>;

@Injectable({ providedIn: 'root' })
export class FinalniProizvodService {
    public resourceUrl = SERVER_API_URL + 'api/finalni-proizvods';

    constructor(private http: HttpClient) {}

    create(finalniProizvod: IFinalniProizvod): Observable<EntityResponseType> {
        return this.http.post<IFinalniProizvod>(this.resourceUrl, finalniProizvod, { observe: 'response' });
    }

    update(finalniProizvod: IFinalniProizvod): Observable<EntityResponseType> {
        return this.http.put<IFinalniProizvod>(this.resourceUrl, finalniProizvod, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFinalniProizvod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFinalniProizvod[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
