import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKupovniProizvod } from 'app/shared/model/kupovni-proizvod.model';

type EntityResponseType = HttpResponse<IKupovniProizvod>;
type EntityArrayResponseType = HttpResponse<IKupovniProizvod[]>;

@Injectable({ providedIn: 'root' })
export class KupovniProizvodService {
    public resourceUrl = SERVER_API_URL + 'api/kupovni-proizvods';

    constructor(private http: HttpClient) {}

    create(kupovniProizvod: IKupovniProizvod): Observable<EntityResponseType> {
        return this.http.post<IKupovniProizvod>(this.resourceUrl, kupovniProizvod, { observe: 'response' });
    }

    update(kupovniProizvod: IKupovniProizvod): Observable<EntityResponseType> {
        return this.http.put<IKupovniProizvod>(this.resourceUrl, kupovniProizvod, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IKupovniProizvod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IKupovniProizvod[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
