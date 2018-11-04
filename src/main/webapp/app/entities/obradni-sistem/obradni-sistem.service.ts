import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IObradniSistem } from 'app/shared/model/obradni-sistem.model';

type EntityResponseType = HttpResponse<IObradniSistem>;
type EntityArrayResponseType = HttpResponse<IObradniSistem[]>;

@Injectable({ providedIn: 'root' })
export class ObradniSistemService {
    public resourceUrl = SERVER_API_URL + 'api/obradni-sistems';

    constructor(private http: HttpClient) {}

    create(obradniSistem: IObradniSistem): Observable<EntityResponseType> {
        return this.http.post<IObradniSistem>(this.resourceUrl, obradniSistem, { observe: 'response' });
    }

    update(obradniSistem: IObradniSistem): Observable<EntityResponseType> {
        return this.http.put<IObradniSistem>(this.resourceUrl, obradniSistem, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IObradniSistem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IObradniSistem[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
