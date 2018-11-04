import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOprema } from 'app/shared/model/oprema.model';

type EntityResponseType = HttpResponse<IOprema>;
type EntityArrayResponseType = HttpResponse<IOprema[]>;

@Injectable({ providedIn: 'root' })
export class OpremaService {
    public resourceUrl = SERVER_API_URL + 'api/opremas';

    constructor(private http: HttpClient) {}

    create(oprema: IOprema): Observable<EntityResponseType> {
        return this.http.post<IOprema>(this.resourceUrl, oprema, { observe: 'response' });
    }

    update(oprema: IOprema): Observable<EntityResponseType> {
        return this.http.put<IOprema>(this.resourceUrl, oprema, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOprema>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOprema[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
